package assignment;

import java.util.ArrayList;
import java.util.Scanner;

import javax.print.attribute.standard.MediaSize.Other;

public class DayPlannerDemo {
	public static void main(String [] args)
	{
		Scanner sc = new Scanner(System.in);
		DayPlanner dp = new DayPlanner();
		String option = "";
		String yesNo = "";
		String extraSearchInformation = "";
			
		while(!option.equalsIgnoreCase("q") && !option.equalsIgnoreCase("quit"))
		{
			System.out.println("Welcome to the day planner.");
			System.out.println("Please enter the transaction you wish to compute : ");
			System.out.println("1. Enter an activity.");
			System.out.println("2. Search for an activity.");
			System.out.println("Quit.");
			option = sc.nextLine();
			
			if(option.equalsIgnoreCase("1") || option.equalsIgnoreCase("Enter") || option.equalsIgnoreCase("Enter activity"))
			{
				String description = "";
				String comments = "";
				String location = "";
				
				Time startTime = null;
				Time endTime = null;
				
				int year = -1;
				int day = -1;
				int hour = -1;
				int minute = -1;
				
				String month = "";
				
				option = "";
				while(!option.equalsIgnoreCase("1") && !option.equalsIgnoreCase("School") && !option.equalsIgnoreCase("2") && !option.equalsIgnoreCase("Home") && !option.equalsIgnoreCase("3") && !option.equalsIgnoreCase("Other"))
				{
					System.out.println("Which type of activity do you want to enter?");
					System.out.println("1. School");
					System.out.println("2. Home");
					System.out.println("3. Other");
					option = sc.nextLine();
				}
				
				if(option.equalsIgnoreCase("1") || option.equalsIgnoreCase("School"))
				{
					System.out.println("To enter a new school activity, you need to enter some additional criteria.");
					
				}
				if(option.equalsIgnoreCase("2") || option.equalsIgnoreCase("Home"))
				{
					System.out.println("To enter a new home activity, you need to enter some additional criteria.");
					
				}
				if(option.equalsIgnoreCase("3") || option.equalsIgnoreCase("Other"))
				{
					System.out.println("To enter a new 'other' activity, you need to enter some criteria.");	
				}
				
				//String description, Time startTime, Time endTime, String comments
				//Year Month Day Hour Minute
				while(description.equalsIgnoreCase(""))
				{
					System.out.println("Please enter a short description : ");
					description = sc.nextLine();
				}
				
				System.out.println("The next five prompts are for the start time.");
				
				while(startTime == null)
				{
					startTime = new Time();
				}
				
				System.out.println("The next five prompts are for the end time.");
				
				while(endTime == null)
				{
				
					endTime = new Time();
					
					if(startTime.compareTo(endTime) == 1)
					{
						endTime = null;
						System.out.println("Your end time must occur at a time after the start time.");
					}
				}
				System.out.println("Please enter optional comments : ");
				comments = sc.nextLine();
				
				if(option.equalsIgnoreCase("3") || option.equalsIgnoreCase("Other"))
				{
					while(location.equalsIgnoreCase(""))
					{
						System.out.println("Please enter a location");
						location = sc.nextLine();
					}
					
				}
				
				if(option.equalsIgnoreCase("1") || option.equalsIgnoreCase("School"))
				{
					SchoolActivity school = new SchoolActivity(description, startTime, endTime, comments);
					if(dp.getSchoolActivities().size() < DayPlanner.MAXIMUM_HOLD)
					{
						dp.addSchooolActivity(school);
						System.out.println("The school activity "+school.toString()+" was successfully added.");
					}
					else
					{
						System.out.println("There is currently no more room in the day planner for school activities.");
					}
				}
				if(option.equalsIgnoreCase("2") || option.equalsIgnoreCase("Home"))
				{
					HomeActivity home = new HomeActivity(description, startTime, endTime, comments);
					if(dp.getHomeActivities().size() < DayPlanner.MAXIMUM_HOLD)
					{
						dp.addHomeActivity(home);
						System.out.println("The home activity "+home.toString()+" was successfully added");
					}
					System.out.println("There is currently no more room in the day planner for home activities.");
				}
				if(option.equalsIgnoreCase("3") || option.equalsIgnoreCase("Other"))
				{
					OtherActivity other = new OtherActivity(description,startTime,endTime,location,comments);
					if(dp.getOtherActivities().size() < DayPlanner.MAXIMUM_HOLD)
					{
						dp.addOtherActivity(other);
						System.out.println("The home activity "+other.toString()+" was successfully added");
					}
					System.out.println("There is currently no more room in the day planner for home activities.");
				}	
			}
			if(option.equalsIgnoreCase("2") || option.equalsIgnoreCase("Search") || option.equalsIgnoreCase("Seach activity"))
			{
				System.out.println("To search through out the entered activites you must enter.");
				option = "";
				while(!option.equalsIgnoreCase("1") && !option.equalsIgnoreCase("School") && !option.equalsIgnoreCase("2") && !option.equalsIgnoreCase("Home") && !option.equalsIgnoreCase("3") && !option.equalsIgnoreCase("Other"))
				{
					System.out.println("Which type of activity do you want to enter?");
					System.out.println("1. School");
					System.out.println("2. Home");
					System.out.println("3. Other");
					option = sc.nextLine();
				}
				Time startTime = null;
				Time endTime = null;
				yesNo = "";
				extraSearchInformation = "";
				do
				{
					System.out.println("Would you like to enter a start time : (y/n)?");
					yesNo = sc.nextLine();
				}while(!yesNo.equalsIgnoreCase("Yes") && !yesNo.equalsIgnoreCase("y") && !yesNo.equalsIgnoreCase("No") && !yesNo.equalsIgnoreCase("n"));
				
				if(yesNo.equalsIgnoreCase("Yes") || yesNo.equalsIgnoreCase("y"))
				{
					startTime = new Time();
				}
				else if(yesNo.equalsIgnoreCase("No") || yesNo.equalsIgnoreCase("n"))
				{
					startTime = null;
				}
				
				yesNo = "";
				do
				{
					System.out.println("Would you like to enter an end time : (y/n)?");
					yesNo = sc.nextLine();
				}while(!yesNo.equalsIgnoreCase("Yes") && !yesNo.equalsIgnoreCase("y") && !yesNo.equalsIgnoreCase("No") && !yesNo.equalsIgnoreCase("n"));
				
				if(yesNo.equalsIgnoreCase("Yes") || yesNo.equalsIgnoreCase("y"))
				{
					endTime = new Time();
				}
				else if(yesNo.equalsIgnoreCase("No") || yesNo.equalsIgnoreCase("n"))
				{
					endTime = null;
				}
				
				System.out.println("Please enter any addition information contained in the title you wish to search for : ");
				extraSearchInformation = sc.nextLine();
				
				if(option.equalsIgnoreCase("1") || option.equalsIgnoreCase("School"))
				{
					ArrayList<SchoolActivity> results = dp.searchForSchoolActivity(extraSearchInformation, startTime, endTime);
					System.out.println("Possible results are : ");
					for(SchoolActivity a : results)
					{
						System.out.println(a.toString());
					}
					option = "";
				}
				else if(option.equalsIgnoreCase("2") || option.equalsIgnoreCase("Home"))
				{
					ArrayList<HomeActivity> results = dp.searchForHomeActivity(extraSearchInformation, startTime, endTime);
					System.out.println("Possible results are : ");
					for(HomeActivity a : results)
					{
						System.out.println(a.toString());
					}
					
					option = "";
				}
				else if(option.equalsIgnoreCase("3") || option.equalsIgnoreCase("Other"))
				{
					ArrayList<OtherActivity> results = dp.searchForOtherActivity(extraSearchInformation, startTime, endTime);
					System.out.println("Possible results are : ");
					for(OtherActivity a : results)
					{
						System.out.println(a.toString());
					}
					option = "";
				}	
				else if(option.equalsIgnoreCase("4") || option.equalsIgnoreCase("All"))
				{
					
					option = "";
					
				}
					
			}
		}
	}

}
