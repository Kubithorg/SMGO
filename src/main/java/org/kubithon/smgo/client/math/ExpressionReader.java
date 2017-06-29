package org.kubithon.smgo.client.math;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.kubithon.smgo.client.math.AOperator.AInclusiveOperator;
import org.kubithon.smgo.client.math.AOperator.ALeftOperator;
import org.kubithon.smgo.client.math.AOperator.AMiddleOperator;
import org.kubithon.smgo.client.math.AOperator.SpecialConstant;
import org.kubithon.smgo.client.math.IExpression.Constant;
import org.kubithon.smgo.client.math.IExpression.Operation;
import org.kubithon.smgo.client.math.IExpression.Variable;

import net.minecraft.util.math.MathHelper;

public class ExpressionReader {

    public static SortedSet<AOperator>     operators  = new TreeSet<>();
    public static List<AInclusiveOperator> inclOp     = new ArrayList<>();
    public static List<SpecialConstant>    spConstant = new ArrayList<>();

    static {
        // Constant : Highest Priority (-128 -> -44) Order doesn't matter unless
        // multiple name match, should always be capital letters
        operators.add(new SpecialConstant("PI", (float) Math.PI, -128));

        // Brackets : Higher Priority (-43) (Must be between Function and
        // Constant)
        AInclusiveOperator brackets = new AInclusiveOperator("(", ")", -43) {
            @Override
            public float applyOperator(IExpression leftExpr) {
                return leftExpr.getValue();
            }
        };
        operators.add(brackets);
        inclOp.add(brackets);

        // Functions : Medium Priority (-42 -> 42) Order doesn't matter unless
        // multiple name match, should always be capital letters
        operators.add(new ALeftOperator("SIN", -42) {
            @Override
            public float applyOperator(IExpression leftExpr) {
                return MathHelper.sin(leftExpr.getValue());
            }
        });
        operators.add(new ALeftOperator("COS", -41) {
            @Override
            public float applyOperator(IExpression leftExpr) {
                return MathHelper.cos(leftExpr.getValue());
            }
        });
        operators.add(new ALeftOperator("ABS", -40) {
            @Override
            public float applyOperator(IExpression leftExpr) {
                return Math.abs(leftExpr.getValue());
            }
        });
        operators.add(new ALeftOperator("SQRT", -39) {
            @Override
            public float applyOperator(IExpression leftExpr) {
                return (float) Math.sqrt(leftExpr.getValue());
            }
        });
        operators.add(new ALeftOperator("ASN", -38) {
            @Override
            public float applyOperator(IExpression leftExpr) {
                return (float) Math.asin(leftExpr.getValue());
            }
        });
        operators.add(new ALeftOperator("ACS", -37) {
            @Override
            public float applyOperator(IExpression leftExpr) {
                return (float) Math.acos(leftExpr.getValue());
            }
        });

        // Operators : Low Priority (43 -> 127)
        operators.add(new ALeftOperator("-", 43) {
            @Override
            public float applyOperator(IExpression leftExpr) {
                return -leftExpr.getValue();
            }
        });
        operators.add(new AMiddleOperator("^", 44) {
            @Override
            public float applyOperator(IExpression leftExpr, IExpression rightExpr) {
                return (float) Math.pow(leftExpr.getValue(), rightExpr.getValue());
            }
        });
        operators.add(new AMiddleOperator("*", 46) {
            @Override
            public float applyOperator(IExpression leftExpr, IExpression rightExpr) {
                return leftExpr.getValue() * rightExpr.getValue();
            }
        });
        operators.add(new AMiddleOperator("/", 45) {
            @Override
            public float applyOperator(IExpression leftExpr, IExpression rightExpr) {
                return leftExpr.getValue() / rightExpr.getValue();
            }
        });
        operators.add(new AMiddleOperator("%", 47) {
            @Override
            public float applyOperator(IExpression leftExpr, IExpression rightExpr) {
                return leftExpr.getValue() % rightExpr.getValue();
            }
        });
        operators.add(new AMiddleOperator("+", 48) {
            @Override
            public float applyOperator(IExpression leftExpr, IExpression rightExpr) {
                return leftExpr.getValue() + rightExpr.getValue();
            }
        });
        operators.add(new AMiddleOperator("-", 49) {
            @Override
            public float applyOperator(IExpression leftExpr, IExpression rightExpr) {
                return leftExpr.getValue() - rightExpr.getValue();
            }
        });
    }

    public static IExpression read(String str) {
        int index;
        boolean flag;
        while ((index = str.indexOf(" ")) >= 0)
            str = str.substring(0, index) + str.substring(index + 1);

        Integer inclusiveBlocks[][] = getInclusiveBlocks(str);
        for (AOperator op : operators) {
            index = -1;
            while ((index = str.indexOf(op.operator, index + 1)) >= 0) {
                flag = false;
                for (Integer[] border : inclusiveBlocks)
                    if (index > border[0] && index < border[1]) {
                        flag = true;
                        continue;
                    }
                if (flag)
                    continue;
                try {
                    return new Operation(str, index, op).optimize();
                } catch (Exception e) {}
            }
        }
        try {
            return new Constant(str);
        } catch (Exception e) {}

        return new Variable(str);
    }

    public static Integer[][] getInclusiveBlocks(String str) {
        List<Integer[]> blocks = new ArrayList<>();
        int index1, index2, index, nextOpIndex;
        String workingStr;

        for (AInclusiveOperator op : inclOp) {
            workingStr = str;
            while ((index1 = workingStr.indexOf(op.operator)) >= 0) {
                workingStr = workingStr.substring(index1 + op.operator.length());
                index = str.lastIndexOf(workingStr) - op.operator.length();
                while ((index2 = workingStr.indexOf(op.secondOperator)) >= (nextOpIndex = workingStr
                        .indexOf(op.operator)) && nextOpIndex >= 0 && index2 >= 0)
                    workingStr = workingStr.substring(index2 + op.secondOperator.length());
                if (index2 < 0)
                    return null;
                blocks.add(new Integer[] { index, str.lastIndexOf(workingStr) + index2 });
                workingStr = str.substring(str.lastIndexOf(workingStr) + index2 + op.secondOperator.length());
            }
        }
        Integer tab[][] = new Integer[0][];
        return blocks.toArray(tab);
    }
}
