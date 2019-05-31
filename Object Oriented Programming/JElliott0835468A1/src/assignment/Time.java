package assignment;

import java.util.Scanner;

public class Time {
	private int Year;
	private String Month;
	private int Day;
	private int Hour;
	private int Minute; //Year Month Day Hour Minute
	/**
	 * Constructor
	 * Creates a new scanner object and uses prompts to get all the information to initialize all the values needed (Year, Month, Day, Hour, Minute.
	 */
	public Time()
	{
		Scanner sc = new Scanner(System.in);
		int year = -1;
		int day = -1;
		int hour = -1;
		int minute = -1;
		String month;
		
		year = -1;
		month = "";
		day = -1;
		hour = -1;
		minute = -1;
		while(year == -1 || year > 2030 || year < 2014)
		{
			System.out.println("Please enter a Year (2014 - 2030) : ");
			try
			{
				year = sc.nextInt();
			}
			catch(Exception e)
			{
				sc.nextLine();
				year = -1;
			}
		}
		sc.nextLine();
		while(!Time.isMonth(month))
		{
			System.out.println("Please enter a Month : ");
	
			month = sc.nextLine();
		}
		
		while(day == -1 || day > 31 || day < 0)
		{
			System.out.println("Please enter a Day (0-30) : ");
			try
			{
				day = sc.nextInt();
			}
			catch(Exception e)
			{
				sc.nextLine();
				day = -1;
			}
		}
		
		while(hour == -1 || hour > 25 || hour < 0)
		{
			System.out.println("Please enter an hour (0-24) : ");
			try
			{
				hour = sc.nextInt();
			}
			catch(Exception e)
			{
				sc.nextLine();
				hour = -1;
			}
		}
		
		while(minute == -1 || minute > 60 || minute < 0)
		{
			System.out.println("Please enter an minute (0-59) : ");
			try
			{
				minute = sc.nextInt();
			}
			catch(Exception e)
			{
				sc.nextLine();
				minute = -1;
			}
		}
		this.setYear(year);
		this.setMonth(month);
		this.setDay(day);
		this.setHour(hour);
		this.setMinute(minute);

	}
	/**
	 * 
	 * @param Year Integer value representing a time of year, it must be greater than 2014.
	 * @param Month String value representing the month, it must be the non-abbreviated month.
	 * @param Day Integer value between 0 and 30 representing the day of the month.
	 * @param Hour Integer value between 0 and 24 representing the hour.
	 * @param Minute Integer value representing the minute and it must be between 0 and 59.
	 */
	public Time(int Year, String Month, int Day, int Hour, int Minute)
	{
		if(Minute < 60 && Minute > 0)
		{
			if(Hour < 24 && Hour > 0)
			{
				if(Day < 30 && Day > 0)
				{
					if(isMonth(Month))
					{
						if(Year > 2013 && Year < 2100)
						{
							this.setYear(Year);
							this.setMonth(Month);
							this.setDay(Day);
							this.setHour(Hour);
							this.setMinute(Minute);
						}
					}
				}
			}
		}
	}
	/**
	 * @return returns a string in the format Month/Day/Year at Hour:Minute.
	 */
	public String toString()
	{
		return getMonth()+"/"+getDay()+"/"+getYear()+ " at "+getHour()+":"+getMinute();
	}
	
	/**
	 * 
	 * @param s The string should be a non-abbreviated month.
	 * @return returns true if s is a non-abbreviated month and returns false if s is anything else.
	 */
	public static boolean isMonth(String s)
	{
		String [] Months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
		for(String a : Months)
		{
			if(s.equalsIgnoreCase(a)){return true;}
		}
		return false;
	}

	/**
	 * @return returns the field year.
	 */
	public int getYear() {
		return Year;
	}

	/**
	 * @param year Internal function to reset the year.
	 */
	private void setYear(int year) {
		Year = year;
	}

	/**
	 * @return returns the field Month;
	 */
	public String getMonth() {
		return Month;
	}

	/**
	 * @param month Internal function to reset the value of the month field.
	 */
	private void setMonth(String month) {
		Month = month;
	}

	/**
	 * @return Day, returns the field Day.
	 */
	public int getDay() {
		return Day;
	}

	/**
	 * @param day Integer value. Internal function to reset the value of the day field.
	 */
	private void setDay(int day) {
		Day = day;
	}

	/**
	 * @return returns the value of the Hour field.
	 */
	public int getHour() {
		return Hour;
	}

	/**
	 * @param hour private method to reset the private hour field.
	 */
	private void setHour(int hour) {
		Hour = hour;
	}
	/**
	 * @return returns the private integer field Minute.
	 */
	public int getMinute() {
		return Minute;
	}
	/**
	 * @param minute private method to reset the minute function.
	 */
	private void setMinute(int minute) {
		Minute = minute;
	}
	/**
	 * 
	 * @param t takes in another instance of the Time class.
	 * @return returns True of the the two instances are equal and false if they are not equal.
	 */
	public boolean equals(Time t)
	{
		if(this.getYear() == t.getYear())
		{
			if(this.getMonth().equalsIgnoreCase(t.getMonth()))
			{
				if(this.getDay() == t.getDay())
				{
					if(this.getHour() == t.getHour())
					{
						if(this.getMinute() == t.getMinute())
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	/**
	 * @param t Takes in another instance of the Time class to compare it to the current instance.
	 * @return Returns 0 if they are equal using the equals method, returns 1 if the current instance is greater than the second instance and returns -1 if the current instance is less than the parameter.
	 */
	public int compareTo(Time t)
	{
		if(t.equals(this))
		{
			return 0;
		}
		
		if(this.getYear() > t.getYear())
		{
			return 1;
		}
		
		if(this.getYear() <= t.getYear() && this.getDay() > t.getDay())
		{
			return 1;
		}
		
		if(this.getYear() <= t.getYear() && this.getDay() <= t.getDay() && this.getHour() > t.getHour())
		{
			return 1;
		}
		
		if(this.getYear() <= t.getYear() && this.getDay() <= t.getDay() && this.getHour() <= t.getHour() && this.getMinute() > t.getMinute())
		{
			return 1;
		}
		
		return -1;
	}
	
}
