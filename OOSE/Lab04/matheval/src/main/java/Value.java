package edu.curtin.matheval;

/** 
 * An ExprNode that evaluates to a specific literal value. This is a leaf node in the tree.
 */
public class Value implements ExprNode
{
    private double value;

    public Value(double value)
    {
        this.value = value;
    }
    
    @Override 
    public double evaluate(double x)
    {
        return value;
    }
    
    @Override
    public String toString()
    {
        return String.valueOf(value);
    }
}
