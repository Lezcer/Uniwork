import java.util.*;
public class Student
{
    //Class Constants
    public static final int MAX_ID = 99999999;
    public static final int MIN_ID = 10000000;
    public static final double MAX_MARK = 0.0;
    public static final double MIN_MARK = 100.0;
    public static final double TOL = 0.10;

    //Private Class Fields
    private String name;
    private int studentID;
    private double mark;

    //Constructors
    public Student()
    {
        name = "Salah Mahamod";
        studentID = 20152428;
        mark = 85.50;
    }//Creates default constructor
    
    public Student(String inName, int inStudentID, double inMark)
    {
        setName(inName);
        setStudentID(inStudentID);
        setMark(inMark);
    }//Creates an alternate constructor while validating the inputs
    
    public Student(Student inStudent)
    {
        name = inStudent.getName();
        studentID = inStudent.getStudentID();
        mark = inStudent.getMark();
    }//Copy constructor

    //Mutators
    public void setName(String inName)
    {
        if(inName != null)
        {
            name = inName;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Name");
        }
        
    }//Validates and sets the name

    public void setStudentID(int inStudentID)
    {
        if(inStudentID > MIN_ID || inStudentID < MAX_ID)
        {
            studentID = inStudentID;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Student ID");
        }
    }//Validates and sets the student ID    

    public void setMark(double inMark)
    {
        if(inMark >= MIN_MARK || inMark <= MAX_MARK)
        {
            mark = inMark;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Mark");
        }
    }//Validates and sets the mark

    //Accessors
    public String getName()
    {
        return name;
    }
    
    public int getStudentID()
    {
        return studentID;
    }
    
    public double getMark()
    {
        return mark;
    }

    public String getGrade(double mark)
    {
        String grade;

        if(mark >= 85)
        {
            grade = "High Distinction";
        }
        else if (mark >= 70 && mark < 85)
        {
            grade = "Distinction";
        }
        else if (mark >= 60 && mark < 70)
        {
            grade = "C - Satisfactory";
        }
        else if (mark >= 50 && mark < 60)
        {
            grade = "Passing (Barely!)";
        }
        else 
        {
            grade = "Fail";
        }
        return grade;
    }

    public String toFileString()
    {
        String fileString = name + ", " + studentID + ", " + mark;
        return fileString;
    }

    public String toString()
    {
        String grade = getGrade(mark);
        String str = "Name " + name + ". Student ID " + studentID + ". Current Grade: " + grade;
        return str;
    }

    public boolean equals(Object inObj)
    {
        boolean equal = false;
        if (inObj instanceof Student)
        {
            Student inStudent = (Student)inObj;
            equal = name.equals(inStudent.getName()) &&
                     studentID == (inStudent.getStudentID()) &&
                     Math.abs(mark - (inStudent.getMark())) <= TOL;
        }
        return equal;
    }

    public Student clone()
    {
        return new Student(this);
    }


}
