package objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyDateTest
{
  MyDate myDate = new MyDate(26,3,2003);
  MyDate myDate2 = new MyDate(26,3,2003);
  MyDate myDate3 = new MyDate();
  MyDate myDate4 = new MyDate(12,6,1987);

  @Test void testEquals()
  {
    assertTrue(myDate.equals(myDate2));
    assertFalse(myDate.equals(myDate3));
    assertFalse(myDate.equals(myDate4));
    assertTrue(myDate3.equals(new MyDate()));
  }

  @Test void testToString()
  {
    assertEquals("26/3/2003", myDate.toString());
    assertEquals("26/3/2003", myDate2.toString());
    assertEquals("12/6/1987", myDate4.toString());
  }

  @Test void reverseToString()
  {
    assertEquals("2003/3/26", myDate.reverseToString());
    assertEquals("2003/3/26", myDate2.reverseToString());
    assertEquals("1987/6/12", myDate4.reverseToString());
  }

  @Test void isBefore()
  {
    assertFalse(myDate.isBefore(myDate2));
    assertTrue(myDate.isBefore(myDate3));
    assertFalse(myDate.isBefore(myDate4));
  }

  @Test void oneDayForward()
  {
    myDate.oneDayForward();
    assertEquals("27/3/2003", myDate.toString());
    MyDate date = new MyDate(28,2,2000);
    date.oneDayForward();
    assertEquals("29/2/2000", date.toString());
    MyDate date2 = new MyDate(28,2,1900);
    date2.oneDayForward();
    assertEquals("1/3/1900", date2.toString());
    MyDate date3 = new MyDate(31,12,2000);
    date3.oneDayForward();
    assertEquals("1/1/2001", date3.toString());
  }

  @Test void isAfter()
  {
    assertFalse(myDate.isAfter(myDate2));
    assertFalse(myDate.isAfter(myDate3));
    assertTrue(myDate.isAfter(myDate4));
  }

  @Test void isLeapYear()
  {
    assertFalse(myDate.isLeapYear());
    MyDate date = new MyDate(19,2,2000);
    assertTrue(date.isLeapYear());
    MyDate date2 = new MyDate(19,2,1900);
    assertFalse(date2.isLeapYear());
    MyDate date3 = new MyDate(19,2,2020);
    assertTrue(date.isLeapYear());
    MyDate date4 = new MyDate(19,2,1600);
    assertTrue(date.isLeapYear());
    MyDate date5 = new MyDate(19,2,1904);
    assertTrue(date.isLeapYear());
  }

  @Test void daysBetween()
  {
    MyDate date = new MyDate(26,3,2002);
    assertEquals(365, date.daysBetween(myDate));
    MyDate date2 = new MyDate(26,3,2004);
    assertEquals(366, date2.daysBetween(myDate2));
  }

  @Test void getAge()
  {
    assertEquals(21, myDate.getAge());
    MyDate date = new MyDate(new MyDate().getDay()-1,new MyDate().getMonth()-1,new MyDate().getYear()-1);
    assertEquals(1, date.getAge());
    MyDate date1 = new MyDate(new MyDate().getDay()+1,new MyDate().getMonth()+1,new MyDate().getYear()-1);
    assertEquals(0, date1.getAge());
  }

  @Test void toLocalDate()
  {
    assertEquals("2003-03-26", myDate.toLocalDate().toString());
  }

  @Test void getYear()
  {
    assertEquals(2003, myDate.getYear());
    assertEquals(2003, myDate2.getYear());
    assertEquals(2024, myDate3.getYear()); // last test in 24th may 2024
    assertEquals(1987, myDate4.getYear());
  }

  @Test void getMonth()
  {
    assertEquals(3, myDate.getMonth());
    assertEquals(3, myDate2.getMonth());
    assertEquals(5, myDate3.getMonth()); // last test in 24th may 2024
    assertEquals(6, myDate4.getMonth());
  }

  @Test void getDay()
  {
    assertEquals(26, myDate.getDay());
    assertEquals(26, myDate2.getDay());
    assertEquals(24, myDate3.getDay()); // last test in 24th may 2024
    assertEquals(12, myDate4.getDay());
  }

  @Test void setYear()
  {
    myDate.setYear(2020);
    assertEquals(2020, myDate.getYear());
  }

  @Test void setMonth()
  {
    myDate.setMonth(7);
    assertEquals(7, myDate.getMonth());
    myDate.setMonth(14);
    assertEquals(7, myDate.getMonth());
    myDate.setMonth(-1);
    assertEquals(7, myDate.getMonth());
  }

  @Test void setDay()
  {
    myDate.setDay(7);
    assertEquals(7, myDate.getDay());
    myDate.setDay(0);
    assertEquals(7, myDate.getDay());
    myDate.setDay(-1);
    assertEquals(7, myDate.getDay());
    myDate.setDay(35);
    assertEquals(7, myDate.getDay());
  }
}