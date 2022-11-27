package edu.curtin.matheval;

/** 
 * An ExprNode that evaluates to the negation (the unary "-" operator) of a single operand.
 * (This is a kind of 'decorator' node.)
 */
public class NegationOperator implements ExprNode
{
    private ExprNode node;

    public NegationOperator(ExprNode node)
    {
        this.node = node;
    }
    
    @Override 
    public double evaluate(double x)
    {
        return -node.evaluate(x);
    }
    
    @Override
    public String toString()
    {
        return "-" + node;
    }
}
