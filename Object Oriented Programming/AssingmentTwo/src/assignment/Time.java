package assignment;

import java.util.Scanner;

public class Time {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute; //Year Month Day Hour Minute

    /**
     * Constructor Creates a new scanner object and uses prompts to get all the
     * information to initialize all the values needed (Year, Month, Day, Hour,
     * Minute.
     */
    public Time() {
        this.year = 0;
        this.month = 0;
        this.day = 0;
        this.hour = 0;
        this.minute = 0;
    }

    /**
     *
     * @param Year Integer value representing a time of year, it must be greater
     * than 2014.
     * @param Month String value representing the month, it must be the
     * non-abbreviated month.
     * @param Day Integer value between 0 and 30 representing the day of the
     * month.
     * @param Hour Integer value between 0 and 24 representing the hour.
     * @param Minute Integer value representing the minute and it must be
     * between 0 and 59.
     */
    public Time(int Year, int Month, int Day, int Hour, int Minute) {
        if (timeOK(year, month, day, hour, minute)) {
            this.year = Year;
            this.month = Month;
            this.day = Day;
            this.hour = Hour;
            this.minute = Minute;
        } else {
            System.out.println("Fatal Error for Time");
            System.exit(0);
        }
    }
    
    /**
     * @see 
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @return 
     */
    static public boolean timeOK(int year, int month, int day, int hour, int minute) {
        return (year >= 0) && (month >= 0) && (day >= 0)
                && (hour >= 0) && (minute >= 0);
    }

    /**
     * @return returns a string in the format Month/Day/Year at Hour:Minute.
     */
    public String toString() {
        return getMonth() + "/" + getDay() + "/" + getYear() + " at " + getHour() + ":" + getMinute();
    }

    /**
     *
     * @param s The string should be a non-abbreviated month.
     * @return returns true if s is a non-abbreviated month and returns false if
     * s is anything else.
     */
    public static boolean isMonth(String s) {
        String[] Months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        for (String a : Months) {
            if (s.equalsIgnoreCase(a)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return returns the field year.
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year Internal function to reset the year.
     */
    private void setYear(int year) {
        this.year = year;
    }

    /**
     * @return returns the field Month;
     */
    public int getMonth() {
        return month;
    }

    /**
     * @param month Internal function to reset the value of the month field.
     */
    private void setMonth(int month) {
        this.month = month;
    }

    /**
     * @return Day, returns the field Day.
     */
    public int getDay() {
        return day;
    }

    /**
     * @param day Integer value. Internal function to reset the value of the day
     * field.
     */
    private void setDay(int day) {
        this.day = day;
    }

    /**
     * @return returns the value of the Hour field.
     */
    public int getHour() {
        return hour;
    }

    /**
     * @param hour private method to reset the private hour field.
     */
    private void setHour(int hour) {
        this.hour = hour;
    }

    /**
     * @return returns the private integer field Minute.
     */
    public int getMinute() {
        return this.minute;
    }

    /**
     * @param minute private method to reset the minute function.
     */
    private void setMinute(int minute) {
        this.minute = minute;
    }

    /**
     *
     * @param t takes in another instance of the Time class.
     * @return returns True of the the two instances are equal and false if they
     * are not equal.
     */
    public boolean equals(Time t) {
        if (this.getYear() == t.getYear()) {
            if (this.getMonth() == t.getMonth()) {
                if (this.getDay() == t.getDay()) {
                    if (this.getHour() == t.getHour()) {
                        if (this.getMinute() == t.getMinute()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * @param other Takes in another instance of the Time class to compare it to
     * the current instance.
     * @return Returns 0 if they are equal using the equals method, returns 1 if
     * the current instance is greater than the second instance and returns -1
     * if the current instance is less than the parameter.
     */
    public int compareTo(Time other) {
        if (year < other.year) {
            return -1;
        } else if (year > other.year) {
            return 1;
        } else if (month < other.month) {
            return -1;
        } else if (month > other.month) {
            return 1;
        } else if (day < other.day) {
            return -1;
        } else if (day > other.day) {
            return 1;
        } else if (hour < other.hour) {
            return -1;
        } else if (hour > other.hour) {
            return 1;
        } else if (minute < other.minute) {
            return -1;
        } else if (minute > other.minute) {
            return 1;
        } else {
            return 0;
        }

    }

    /**
     * @return Returns a new instance of the Time class (Uses prompts and scanner class)
     */
    public static Time buildNewTime() {
        Scanner sc = new Scanner(System.in);
        int year = -1;
        int day = -1;
        int hour = -1;
        int minute = -1;
        int month = -1;

        while (year == -1 || year <= 0) {
            System.out.println("Please enter a Year (2014 - 2030) : ");
            try {
                year = sc.nextInt();
            } catch (Exception e) {
                sc.nextLine();
                year = -1;
            }
        }
        sc.nextLine();
        while (month == -1 || year <= 0) {
            System.out.println("Please enter a Month : ");
            try {
                month = sc.nextInt();
            } catch (Exception e) {
                sc.nextLine();
                year = -1;
            }
        }

        while (day == -1 || day <= 0) {
            System.out.println("Please enter a Day : ");
            try {
                day = sc.nextInt();
            } catch (Exception e) {
                sc.nextLine();
                day = -1;
            }
        }

        while (hour == -1 || hour <= 0) {
            System.out.println("Please enter an hour: ");
            try {
                hour = sc.nextInt();
            } catch (Exception e) {
                sc.nextLine();
                hour = -1;
            }
        }

        while (minute == -1 || minute <= 0) {
            System.out.println("Please enter an minute : ");
            try {
                minute = sc.nextInt();
            } catch (Exception e) {
                sc.nextLine();
                minute = -1;
            }
        }
        return new Time(year, month, hour, day, minute);
    }

}
