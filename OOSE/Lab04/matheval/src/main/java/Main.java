package edu.curtin.matheval;

import java.util.*;

/**
 * Entry point for out mathematical expression app.
 */
public class Main
{
    public static void main(String[] args)
    {
        /**
         * Some expression checking needs to be done here to ensure:
         * 1) if there are x values, there must be x - 1 operators (#1)
         * 1.1) for every x operators there should be x + 1 values (#7)
         * 2) bracket checking, ( should be closed and a ) should be opened first (#2, 3 & 4)
         * 3) there should be only 1 variable, any expressions with 2 or more are errors (#5)
         * 4) With real numbers there can be only 1 . symbol (#6)
         * 5) User should enter input (#8) DONE
         * 6) User shouldn't enter the wrong characters
         */
        Scanner sc = new Scanner(System.in);
        String expression;
        String m = "Enter a mathematical expression: ";
        do   
        {
            System.out.print(m);
            expression = sc.nextLine();
            m = "No input detected, Please enter a mathematical expression: ";
        }while(expression.isEmpty());

        try
        {
            ExprNode exprRoot = new ExprParser().parse(expression);
            System.out.printf("Parsed expression: '%s'\nEvaluating for different values of x...\n", exprRoot.toString());
            for(double x = 0.0; x <= 10.00001; x += 0.5)
            {
                System.out.printf("(%.2f, %.2f)\n", x, exprRoot.evaluate(x));
            }
        }
        catch(NumberFormatException e)
        {
            System.out.println("ERROR, please use a mathematical expression with character 'x' as the variable only.\n" + e.getMessage());
        }
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("ERROR, there is an incorrect number of expressions to operators.\n");
        }
    }
}
