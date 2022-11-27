import java.util.*;
public class Test
{
    public static void main(String[] args)
    {
        String terms[] = new String[5];
        String s = "AX5MYT67";//testing string
        String fil;
        int j = 0;//For the array index - the one that stores terms
        int currentCharIndex=0;//Holds the position of the last char
        int StrinIndex= s.length()-1;//Holds length of the string in 0, 1, 2 format
        for(int i=0; i<=StrinIndex; i++)
        {
            if(Character.isLetter(s.charAt(i)))//Is current char a letter
            {
                if(i<StrinIndex && Character.isLetter(s.charAt(i+1)))
                /*makes sure i isnt the last characterr AND IF
                next char a letter too. if thats the case 
                current char is 1 element as next char is a letter*/
                {
                    terms[j]= s.substring(i,i+1);//element was 1
                    j++;
                }
                currentCharIndex=i;//set the position last character - useful later
            }
            if(Character.isDigit(s.charAt(i)))//Is current char a number
            {
                if(i<StrinIndex && Character.isDigit(s.charAt(i+1)))
                //makes sure i isnt the last character AND if next char
                //is a number.
                {
                }//do nothing because the next number that has a character 
                 //next to it will do the substring below.
                
                else//which means the if statement above didn't work
                //next char is a letter (next element) do substring now
                {
                    fil = s.substring(currentCharIndex, i+1);
                    terms[j] = fil;
                    j++;
                }
            }
        }
        for(int x=0; x<terms.length; x++)
        {
            System.out.println(terms[x]);
        }//this displays the array holding elements
    }
}
