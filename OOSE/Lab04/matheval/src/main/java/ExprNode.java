package edu.curtin.matheval;

/**
 * Represents a component of a parsed expression. ExprNode objects form a tree structure, where 
 * each node is a number, or a variable value, or an operator.
 */
public interface ExprNode
{
    /** 
     * Evaluates the expression (or sub-expression) represented by this node, given a specific 
     * value for the 'x' variable (which may or may not be needed).
     */
    double evaluate(double x);
    
    /**
     * Formats the node back into a human-readable mathematical expression.
     */
    @Override
    String toString();
}
