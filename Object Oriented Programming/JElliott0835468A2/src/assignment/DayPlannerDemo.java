package assignment;

import java.util.*;
import java.io.*;

public class DayPlannerDemo 
{
    public static void main(String [] args)
    {
        Scanner sc = new Scanner(System.in);
        File inputFile = null;
        File outputFile = null;
        DayPlanner dp = null;
        String option = "";
                        
        if(args.length == 0)
        {
            System.out.println("Invalid command must call DayPlannerDemo <input file> < output file> OR DayPlannerDemo <output file>");
            System.exit(0);
        }   
        if(args.length > 0)
        {
            if(args.length == 1) // output file only
            {
                outputFile = new File(args[0]);
                dp = new DayPlanner(outputFile);
            }
            else if(args.length == 2)
            {
                if(args[0].equalsIgnoreCase(args[1]))
                {
                    System.out.println("Output file cannot be the same as the input file.");
                }
                
                inputFile = new File(args[0]);
                outputFile = new File(args[1]);
                dp = new DayPlanner(outputFile, inputFile);
            }
        }
        
        /*Loop through the menu here*/
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
                Activity activity = dp.buildActivity();
               if(activity == null)
               {
                   System.out.println("Fatal error activity to add is null.");
               }
                dp.addActivity(activity);

            }
            else if(option.equalsIgnoreCase("2") || option.equalsIgnoreCase("Search") || option.equalsIgnoreCase("Seach activity"))
            {
                option = "";
                while(!option.equalsIgnoreCase("1") && !option.equalsIgnoreCase("Description") && !option.equalsIgnoreCase("2") && !option.equalsIgnoreCase("Time"))
                {
                    System.out.println("Would you like to search by : ");
                    System.out.println("1. Description");
                    System.out.println("2. Time");
                    option = sc.nextLine();
                }
                if(option.equalsIgnoreCase("1") || option.equalsIgnoreCase("Description"))
                {
                    dp.searchByCriteria();
                }
                else if (option.equalsIgnoreCase("2") || option.equalsIgnoreCase("Time"))
                {
                    dp.searchByTime();
                }
            }
        }
        if(option.equalsIgnoreCase("q") || option.equalsIgnoreCase("quit"))
        {
            System.out.println("Good bye.");
        }
        dp.saveData(outputFile);
    }
    
}
