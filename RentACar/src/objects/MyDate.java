package objects;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MyDate implements Serializable
{
  private int year;
  private int month;
  private int day;

  public MyDate(int day, int month, int year)
  {
    setYear(year);
    setMonth(month);
    setDay(day);
  }

  public MyDate()
  {
    Calendar calendar = GregorianCalendar.getInstance();
    day = calendar.get(Calendar.DAY_OF_MONTH);
    month = calendar.get(Calendar.MONTH)+1;
    year = calendar.get(Calendar.YEAR);
  }

  public boolean equals(MyDate date){
    if(this.year==date.getYear() && this.month==date.getMonth() && this.day==date.getDay())
      return true;
    else return false;
  }


  public String toString(){
    return day+"/"+month+"/"+year;
  }

  public String reverseToString(){
    return year + "/" + month + "/" + day;
  }


  /**
   * this method checks if the date comes before the other date.
   * @param obj
   * @return
   */
  public boolean isBefore(Object obj)
  {
    if (obj instanceof MyDate)
    {
      MyDate myDate = (MyDate)obj;
      if (this.year < myDate.getYear())
      {
        return true;
      }
      else if (this.year == myDate.getYear() && this.month < myDate.getMonth())
      {
        return true;
      }
      else if (this.year == myDate.getYear() && this.month == myDate.getMonth() && this.day < myDate.getDay())
      {
        return true;
      }
    }
    return false;
  }


  /**
   * this method changes the date to one day after.
   */
  public void oneDayForward()
  {
    if(day == 28 && month == 2 && !isLeapYear())
    {
      day = 1;
      month++;
    }
    else if (day == 30 && (month == 4 || month == 6 || month == 9 || month == 11))
    {
      day = 1;
      month++;
    }
    else if(day == 31 && (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12))
    {
      day = 1;
      month++;
    }
    else
    {
      day++;
    }
    if(month > 12)
    {
      month = 1;
      year++;
    }
  }


  /**
   * this method checks if the date comes after the other date.
   * @param obj
   * @return boolean
   */
  public boolean isAfter(Object obj)
  {
    if (obj instanceof MyDate)
    {
      MyDate myDate = (MyDate)obj;
      if (this.year > myDate.getYear())
      {
        return true;
      }
      else if (this.year == myDate.getYear() && this.month > myDate.getMonth())
      {
        return true;
      }
      else if (this.year == myDate.getYear() && this.month == myDate.getMonth() && this.day > myDate.getDay())
      {
        return true;
      }
    }
    return false;
  }


  /**
   * this method checks if the current year in the myDate is in a leap year.
   * Author:
   */
  public boolean isLeapYear()
  {
    int year = this.year;

    if (this.year % 4 == 0 && this.year % 100 != 0 || this.year % 400 == 0)
      return true;

    return false;
  }


  /**
   * daysBetween method returns the number of days between the 2 myDates selected.
   * Author:
   */
  /*
  // modificeret metode.
/*
   */

  public int daysBetween(MyDate otherDate) {
    Calendar startDate = new GregorianCalendar(this.year, this.month - 1, this.day);
    Calendar endDate = new GregorianCalendar(otherDate.year, otherDate.month - 1, otherDate.day);

    if (startDate.after(endDate)) {
      Calendar temp = startDate;
      startDate = endDate;
      endDate = temp;
    }

    int daysBetween = 0;
    while (startDate.before(endDate)) {
      startDate.add(Calendar.DAY_OF_MONTH, 1);
      daysBetween++;
    }

    return daysBetween;
  }




  /**
   * getAge method returns the age of the date, be checking how many days there is between the date and the current method.
   * Author:
   */

  //modificeret metode:
  public int getAge()
  {
    MyDate today = new MyDate();

    int age = today.year - this.year;

    if (today.month < this.month || today.month == this.month && today.day < this.day)
    {
      age--;
    }
    return age;
  }

  public LocalDate toLocalDate()
  {
    return LocalDate.of(year,month,day);
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

  public void setYear(int year)
  {
    this.year = year;
  }

  public void setMonth(int month)
  {
    if (month >= 1 && month <= 12)
    {
      this.month = month;
    }
  }

  public void setDay(int day)
  {
    if (day > 0 && day <= 31)
    {
      if (month == 2 && isLeapYear() && day <= 29)
      {
        this.day = day;
      }
      else if (month == 2 && !isLeapYear() && day <= 28)
      {
        this.day = day;
      }
      else if (day <= 30 && (month == 4 || month == 6 || month == 9 || month == 11))
      {
        this.day = day;
      }
      else if (day <= 31 && (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12))
      {
        this.day = day;
      }
    }
  }
}
