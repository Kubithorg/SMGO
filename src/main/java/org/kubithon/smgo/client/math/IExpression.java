package org.kubithon.smgo.client.math;

public interface IExpression {

    public float getValue();

    public void setVariable(String name, float value);

    public boolean isConstant();

    public Constant toConstant();

    public IExpression optimize();

    // Static Classes //

    public static class Constant implements IExpression {
        protected float constant;

        public Constant(String str) throws Exception {
            this.constant = Float.valueOf(str);
        }

        public Constant(float value) {
            this.constant = value;
        }

        @Override
        public float getValue() {
            return this.constant;
        }

        @Override
        public void setVariable(String name, float value) {}

        @Override
        public boolean isConstant() {
            return true;
        }

        @Override
        public Constant toConstant() {
            return this;
        }

        @Override
        public IExpression optimize() {
            return this;
        }

        @Override
        public String toString() {
            return String.valueOf(this.constant);
        }
    }

    public static class Variable implements IExpression {
        protected String name;
        protected float  value;

        public Variable(String str) {
            this.name = str;
        }

        @Override
        public float getValue() {
            return this.value;
        }

        @Override
        public void setVariable(String name, float value) {
            if (name.equals(this.name))
                this.value = value;
        }

        @Override
        public boolean isConstant() {
            return false;
        }

        @Override
        public Constant toConstant() {
            return null;
        }

        @Override
        public IExpression optimize() {
            return this;
        }

        @Override
        public String toString() {
            return String.valueOf(this.name);
        }
    }

    public static class Operation implements IExpression {
        IExpression         expressions[];
        protected AOperator operator;

        public Operation(String str, int index, AOperator op) throws Exception {
            this.operator = op;
            operator.read(str, index, this);
        }

        @Override
        public float getValue() {
            return this.operator.applyOperator(this.expressions);
        }

        @Override
        public void setVariable(String name, float value) {
            for (IExpression e : this.expressions)
                e.setVariable(name, value);
        }

        @Override
        public boolean isConstant() {
            for (IExpression e : this.expressions)
                if (!e.isConstant())
                    return false;
            return true;
        }

        @Override
        public Constant toConstant() {
            try {
                return new Constant(String.valueOf(this.getValue()));
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        public IExpression optimize() {
            if (this.isConstant())
                return this.toConstant();
            for (int i = 0; i < this.expressions.length; i++)
                if (this.expressions[i].isConstant())
                    this.expressions[i] = this.expressions[i].toConstant();
                else
                    this.expressions[i].optimize();
            return this;
        }

        @Override
        public String toString() {
            return this.operator.toString(this.expressions);
        }
    }
}
