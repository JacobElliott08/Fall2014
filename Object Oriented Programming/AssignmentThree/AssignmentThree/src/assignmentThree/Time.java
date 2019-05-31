package assignmentThree;

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
		this.setYear(Year);
		this.setMonth(Month);
		this.setDay(Day);
		this.setHour(Hour);
		this.setMinute(Minute);

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
		String [] Months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
		int i;
		for(i = 0; i < Months.length; i ++)
		{
			if(Months[i].equalsIgnoreCase(getMonth()))
			{
				break;
			}
		}
		String one = this.getYear()+""+i+""+this.getDay()+""+this.getHour()+""+this.getMinute()+"";
		for(i = 0; i < Months.length; i ++)
		{
			if(Months[i].equalsIgnoreCase(t.getMonth()))
			{
				break;
			}
		}
		
		String two = t.getYear()+""+i+""+t.getDay()+""+t.getHour()+""+t.getMinute()+"";
		
		int timeOne = Integer.parseInt(one);
		int timeTwo = Integer.parseInt(two);
		
		if(timeOne == timeTwo)
			return 0;
		if(timeOne > timeTwo)
			return 1;
		if(timeOne < timeTwo)
			return -1;
		
		return 0;
	}
	
}
