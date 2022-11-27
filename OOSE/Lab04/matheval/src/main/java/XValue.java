package edu.curtin.matheval;

/** 
 * An ExprNode that evaluates to the value of the 'x' variable (whose actual value is given as a 
 * parameter during evaluation). This is a leaf node in the tree.
 */
public class XValue implements ExprNode
{
    public XValue() {}
    
    @Override 
    public double evaluate(double x)
    {
        return x;
    }
    
    @Override
    public String toString()
    {
        return "x";
    }
}
