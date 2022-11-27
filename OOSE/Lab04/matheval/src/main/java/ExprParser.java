package edu.curtin.matheval;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses mathematical expressions, and builds a tree of ExprNode objects to represent the parsed 
 * expression.
 */
public class ExprParser
{
    // The regex pattern used for tokenisation purposes. Basically, this skips any amount of 
    // whitespace, then checks for a fractional number (containing and possibly starting with "."),
    // then checks for an integer, and finally falls back to a single other character of any type.
    private static final Pattern TOKEN = Pattern.compile("\\s*([0-9]*\\.[0-9]+|[0-9]+|.)");
    
    public ExprParser() {}

    /**
     * Parses a string, which is assumed to contain a mathematical expression. Returns the root 
     * node of the resulting object tree.
     */
    public ExprNode parse(String s)
    {
        List<String> tokens = new LinkedList<>();

        // Tokenise the string, by repeatedly applying the 'TOKEN' regular expression until it 
        // doesn't match anymore (which should only happen at the end of the string).
        String substr = s;
        boolean done = false;
        do
        {
            Matcher matcher = TOKEN.matcher(substr);
            if(matcher.lookingAt())
            {
                tokens.add(matcher.group(1));
                substr = substr.substring(matcher.end());
            }
            else
            {
                done = true;
                // Exit loop
            }
        }
        while(!done);

        // Invoke the actual parsing logic.
        return parseAdd(tokens);
    }

    /** 
     * Parses a sequence of zero-or-more "+" / "-" operators (the lowest operator precedence
     * level).
     */
    private ExprNode parseAdd(List<String> tokens)
    {
        ExprNode node = parseMul(tokens);
        boolean end = false;
        while(!end && !tokens.isEmpty())
        {
            // Expect next token to be '+' or '-'
            String token = tokens.remove(0);
            switch(token)
            {
                case "+": 
                    node = new AddOperator(node, parseMul(tokens));
                    break;
                    
                case "-":
                    node = new SubOperator(node, parseMul(tokens));
                    break;
                    
                default:
                    // The next token isn't "+" or "-", which means we assume this additive 
                    // sequence is over, so push the token back onto the list, and end.
                    tokens.add(0, token);
                    end = true;
                    break;
            }
        }
        return node;
    }
    
    /**
     * Parses a sequence of zero-or-more "*" / "/" operators.
     */
    private ExprNode parseMul(List<String> tokens)
    {
        ExprNode node = parsePrimary(tokens);
        boolean end = false;
        while(!end && !tokens.isEmpty())
        {
            // Expect next token to be "*" or "/"
            String token = tokens.remove(0);
            switch(token)
            {
                case "*":
                    node = new MulOperator(node, parsePrimary(tokens));
                    break;
                    
                case "/":
                    node = new DivOperator(node, parsePrimary(tokens));
                    break;
                    
                default:
                    // The next token isn't "*" or "/", which means we assume this multiplicative
                    // sequence is over, so push the token back onto the list, and end.
                    tokens.add(0, token);
                    end = true;
                    break;
            }
        }
        return node;
    }
    
    /**
     * Parses a "primary" value, which can be either a sub-expression in brackets, a negation "-"
     * operator, a reference to the "x" variable, or a literal number.
     */
    private ExprNode parsePrimary(List<String> tokens)
    {
        ExprNode node;
        String token = tokens.remove(0); // Obtain the next token
        switch(token)
        {
            case "(":
                // Sub-expression inside brackets.
                node = parseAdd(tokens);
                System.out.println(tokens.remove(0)); // Remove closing ")"
                break;
                
            case "-":
                // Inverted value (e.g., -(x+1))
                node = new NegationOperator(parsePrimary(tokens));
                break;
                
            case "x":
                // Variable value
                node = new XValue();
                break;
                
            default:
                // Literal number
                node = new Value(Double.parseDouble(token));
                break;
        }
        return node;
    }
}
