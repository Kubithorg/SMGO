package org.kubithon.smgo.client.math;

import org.kubithon.smgo.client.math.IExpression.Constant;
import org.kubithon.smgo.client.math.IExpression.Operation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class AOperator implements Comparable<AOperator> {
    public final String operator;
    public final byte   priority;

    public AOperator(String operatorIn, int priorityIn) {
        this.operator = operatorIn;
        this.priority = (byte) priorityIn;
    }

    public abstract float applyOperator(IExpression expressions[]);

    public abstract void read(String str, int index, Operation op) throws Exception;

    public abstract String toString(IExpression exprs[]);

    @Override
    public int compareTo(AOperator o) {
        return o.priority - this.priority;
    }

    @Override
    public String toString() {
        return this.operator;
    }

    // Static Classes //

    public static abstract class ALeftOperator extends AOperator {

        public ALeftOperator(String operatorIn, int priorityIn) {
            super(operatorIn, priorityIn);
        }

        public abstract float applyOperator(IExpression leftExpr);

        @Override
        public float applyOperator(IExpression expressions[]) {
            if (expressions.length < 1)
                throw new ArithmeticException("Not enough argument for the operator : " + this.operator);
            return this.applyOperator(expressions[0]);
        }

        @Override
        public void read(String str, int index, Operation op) {
            op.expressions = new IExpression[1];
            op.expressions[0] = ExpressionReader
                    .read(str.substring(0, index) + str.substring(index + this.operator.length()));
        }

        @Override
        public String toString(IExpression exprs[]) {
            return this.operator + exprs[0];
        }
    }

    public static abstract class AMiddleOperator extends AOperator {

        public AMiddleOperator(String operatorIn, int priorityIn) {
            super(operatorIn, priorityIn);
        }

        public abstract float applyOperator(IExpression leftExpr, IExpression rightExpr);

        @Override
        public float applyOperator(IExpression expressions[]) {
            if (expressions.length < 2)
                throw new ArithmeticException("Not enough argument for the operator : " + this.operator);
            return this.applyOperator(expressions[0], expressions[1]);
        }

        @Override
        public void read(String str, int index, Operation op) throws Exception {
            if (index < 1 || index >= str.length() - 1)
                throw new Exception();
            op.expressions = new IExpression[2];
            op.expressions[0] = ExpressionReader.read(str.substring(0, index));
            op.expressions[1] = ExpressionReader.read(str.substring(index + this.operator.length()));
        }

        @Override
        public String toString(IExpression exprs[]) {
            return exprs[0] + this.operator + exprs[1];
        }
    }

    public static abstract class AInclusiveOperator extends ALeftOperator {
        public final String secondOperator;

        public AInclusiveOperator(String operatorIn, String secondOperatorIn, int priorityIn) {
            super(operatorIn, priorityIn);
            this.secondOperator = secondOperatorIn;
        }

        @Override
        public void read(String str, int index, Operation op) {
            int secondIndex = str.lastIndexOf(this.secondOperator);
            if (secondIndex < 0)
                throw new ArithmeticException("Missing '" + this.secondOperator + "' after a '" + this.operator + "'");
            op.expressions = new IExpression[1];
            op.expressions[0] = ExpressionReader.read(str.substring(index + this.operator.length(), secondIndex));
        }

        @Override
        public String toString(IExpression exprs[]) {
            return this.operator + exprs[0] + this.secondOperator;
        }
    }

    public static class SpecialConstant extends AOperator {
        protected float value;

        public SpecialConstant(String operatorIn, float value, int priorityIn) {
            super(operatorIn, priorityIn);
            this.value = value;
        }

        @Override
        public float applyOperator(IExpression[] expressions) {
            return this.value;
        }

        @Override
        public void read(String str, int index, Operation op) {
            op.expressions = new IExpression[1];
            op.expressions[0] = new Constant(this.value);
        }

        @Override
        public String toString(IExpression exprs[]) {
            return this.operator;
        }
    }
}
