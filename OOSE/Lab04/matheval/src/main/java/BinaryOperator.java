package edu.curtin.matheval;

/** 
 * An ExprNode that evaluates to the result of an operator (+, -, * or /) applied to two operands.
 * (This is a kind of 'composite' node.)
 */
public abstract class BinaryOperator implements ExprNode
{
    private ExprNode n1;
    private ExprNode n2;
    private char symbol;
    
    public BinaryOperator(ExprNode n1, ExprNode n2, char symbol)
    {
        this.n1 = n1;
        this.n2 = n2;
        this.symbol = symbol;
    }
    
    @Override
    public double evaluate(double x)
    {
        return evalOperator(n1.evaluate(x), n2.evaluate(x));
    }
    
    /**
     * Each subclass defines just the mathematical operation it needs to perform.
     */
    protected abstract double evalOperator(double v1, double v2);
    
    @Override
    public String toString()
    {
        return String.format("(%s %c %s)", n1, symbol, n2);
    }
}
