package payroll;
/**
 * This class defines the properties of a Date object.
 *
 * @author Sailokesh Mondi, Tanay Somisetty
 */

import java.util.Calendar;
import java.util.StringTokenizer;


public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int YearThresholdOne = 1900;
    public static final int YearThresholdTwo = 2021;


    /**
     Compares two date objects and determines which date is older/newer.
     First compares the years, then the months, then the days
     @param 'Date' object
     @return 1, 0, or -1 if this class is after, the same, or before the parameter Date respectively
     */
    @Override
    public int compareTo(Date date) {
        if (this.year > date.year) {
            return 1;
        }
        else if (this.year < date.year) {
            return -1;
        }
        else {
            if (this.month > date.month) {
                return 1;
            }
            else if (this.month < date.month) {
                return -1;
            }
            else {
                if (this.day > this.day) {
                    return 1;
                }
                else if (this.day < this.day) {
                    return -1;
                }
                else {
                    return 0;
                }
            }
        }
    }

    /**
     This is the constructor to take mm/dd/yyyy and create a Date object.
     Tokenizes the string with '/' as a deliminator.
     @param date of the book
     */
    public Date(String date) {
        StringTokenizer st = new StringTokenizer(date, "/", false);

        this.month = Integer.parseInt(st.nextToken());
        this.day = Integer.parseInt(st.nextToken());
        this.year = Integer.parseInt(st.nextToken());
    }

    /**
     This constructor returns today's date.
     Uses the Calendar class.
     @param 'none'
     */
    public Date() {
        Calendar today = Calendar.getInstance();

        this.year = today.get(today.YEAR);
        this.month = today.get(today.MONTH) + 1;
        this.day = today.get(today.DAY_OF_MONTH);
    }


    /**
     Getter method for the date class.
     @param 'none'
     @return String in the format mm/dd/yyyy
     */
    public String getDate() {
        return this.month + "/" + this.day + "/" + this.year;
    }

    /**
     This method checks to see whether a given date is valid or  not
     @param 'none'
     @return true if the date is valid, false otherwise
     */
    public boolean isValid() {

        final int LONG_MONTH = 31;
        final int SHORT_MONTH = 30;
        final int LONG_FEB = 29;
        final int SHORT_FEB = 28;


        Date todayDate = new Date();

        final int CURRENT_DAY = todayDate.day;
        final int CURRENT_MONTH = todayDate.month;

        // year Validation
        if (year < YearThresholdOne || year >= YearThresholdTwo) {
            if ((year == YearThresholdTwo) && (month <= CURRENT_MONTH && month >= 1) && (day <= CURRENT_DAY && day >= 1)) {
                return true;
            }
            return false;
        }


        // Day Validation
        if ((month == Calendar.JANUARY + 1 || month == Calendar.MARCH + 1 || month == Calendar.MAY + 1 || month == Calendar.JULY + 1
                || month == Calendar.AUGUST + 1 || month == Calendar.OCTOBER + 1 || month == Calendar.DECEMBER + 1) && day > LONG_MONTH) {
            return false;
        }

        if ((month == Calendar.APRIL + 1 || month == Calendar.JUNE + 1 || month == Calendar.SEPTEMBER + 1
             || month == Calendar.NOVEMBER + 1) && day > SHORT_MONTH) {
            return false;
        }

        if (month == Calendar.FEBRUARY + 1 && isLeapYear() && day > LONG_FEB) {
            return false;

        }

        if (month == Calendar.FEBRUARY + 1 && !isLeapYear() && day > SHORT_FEB) {

            return false;

        }

        // Month Validation
        if (month < Calendar.JANUARY + 1 || month > Calendar.DECEMBER + 1) {
            return false;
        }

        return true;
    }

    private boolean isLeapYear() {
        if (year % QUADRENNIAL != 0) {
            return false;
        } else if (year % CENTENNIAL == 0 && year % QUATERCENTENNIAL == 0) {
            return true;
        }

        return true;
    }

    public int getYear() {
        return year;
    }


    public int getMonth() {
        return month;
    }


    public int getDay() {
        return day;
    }

}