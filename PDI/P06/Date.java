import java.util.*;
public class Date
{
    public static final String[] monthNames = {"January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private int year, month, day;

    public Date()
    {
        year = 2000;
        month = 8;
        day = 26;
    }

    public Date(int inYear, int inMonth, int inDay)
    {
        setYear(inYear);
        setMonth(inMonth);
        setDay(inDay);
    }

    public Date(Date inDate)
    {
        year = inDate.getYear();
        month = inDate.getMonth();
        day = inDate.getDay();
    }

    public void setYear(int inYear)
    {
        if (validateYear(inYear))
        {
            year = inYear;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Year");
        }
    }

    public void setMonth(int inMonth)
    {
        if (validate(inMonth))
        {
            month = inMonth;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Month");
        }
    }

    public void setDay(int inDay)
    {
        if (validateDay(inDay))
        {
            day = inDay;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Day");
        }
    }

    public int getYear()
    {
        return year;
    }

    public int getMonth()
    {
        return month;
    }

    public int getDay()
    {
        return day;
    }

    public String getMonthName(int inMonth)
    {
        String monthName = monthNames[inMonth + 1];
        return monthName; 
    }

    public String getSuffix(int inDay)
    {
        
    }

    public Date clone()
    {
        return new Date(this);
    }

    public String toString()
    {
        string = "It is the " + day + suffix + " day of " + monthName + " in " + year + "."
    }
    
    public boolean equals(Object inObj)
    {
        boolean same = false;
        if (inObj instanceof Date)
        {
            Date inDate = (Date)inObj;
            same = 
        }
    }
}
