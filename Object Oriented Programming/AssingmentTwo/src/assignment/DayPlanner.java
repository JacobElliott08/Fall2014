package assignment;

import java.util.*;
import java.io.*;

public class DayPlanner {

    private ArrayList<Activity> activities;
    private HashMap<String, ArrayList<Integer>> search;
    private File outPutFile;
    private File inPutFile;

    /**
     * Calls the constructor with an outPut file and no input file.
     *
     * @param outPutFile
     */
    public DayPlanner(File outPutFile) {
        this(outPutFile, null);
    }

    /**
     * @return Returns the output file.
     */
    public File getOutPutFile() {
        return outPutFile;
    }

    /**
     * @return Returns the input file, may return null if no input file was
     * specified.
     */
    public File getInPutFile() {
        return inPutFile;
    }

    /**
     * @param outPutFile The output file in which the contents of the day
     * planner will be placed.
     * @param inputFile The input file from which the day planner will be
     * parsed. Initializes and imports the data from the input file, if it's not
     * null.
     */
    public DayPlanner(File outPutFile, File inputFile) {
        this.outPutFile = outPutFile;
        this.activities = new ArrayList<Activity>();
        importData(inputFile);
    }

    /**
     * @return Returns the activities.
     */
    public ArrayList<Activity> getActivities() {
        return activities;
    }

    /**
     * @param toAdd An instance of the Activity class to add to the current
     * activities.
     */
    public void addActivity(Activity toAdd) {
        activities.add(toAdd);
    }

    /**
     * @param f f is the input file in which the data will be parsed. It is
     * automatically placed within the activities array.
     */
    private void importData(File f) {
        Scanner sc = null;
        if (f == null) {
            return;
        }
        try {
            sc = new Scanner(new FileInputStream(f));
        } catch (Exception e) {
            System.out.println("Cannot open file : " + f.getName());
        }
        if (sc != null) {
            while (sc.hasNextLine()) {
                Activity newActivity = parseActivityFromString(sc.nextLine());
                activities.add(newActivity);
            }
        }
        if (sc != null) {
            sc.close();
        }

        buildHashMap(activities);

        /*
         Iterator it = search.entrySet().iterator();
         while (it.hasNext())
         {
         Map.Entry pairs = (Map.Entry)it.next();
         System.out.println(pairs.getKey() + " = " + pairs.getValue());
         }  */
    }

    /**
     * @param toParse String in the format
     * "Type,Description,StartTime,EndTime,Comments,Location
     * @return Returns an activity created based on the toParse
     */
    private Activity parseActivityFromString(String toParse) {
        Activity toReturn = null;
        String type, description = "", startYear = "", startMonth = "", startDay = "", startHour = "", startMinute = "", endYear = "", endMonth = "", endDay = "", endHour = "", endMinute = "", comments = "", location = "";
        StringTokenizer st = new StringTokenizer(toParse, ",");

        type = st.nextToken(); //Home

        if (st.hasMoreTokens()) {
            description = st.nextToken(); //Test
        }
        if (st.hasMoreTokens()) {
            startYear = st.nextToken(); //0
        }
        if (st.hasMoreTokens()) {
            startMonth = st.nextToken(); //April
        }
        if (st.hasMoreTokens()) {
            startDay = st.nextToken();
        }
        if (st.hasMoreTokens()) {
            startHour = st.nextToken();
        }
        if (st.hasMoreTokens()) {
            startMinute = st.nextToken();
        }

        if (st.hasMoreTokens()) {
            endYear = st.nextToken();
        }
        if (st.hasMoreTokens()) {
            endMonth = st.nextToken();
        }
        if (st.hasMoreTokens()) {
            endDay = st.nextToken();
        }
        if (st.hasMoreTokens()) {
            endHour = st.nextToken();
        }
        if (st.hasMoreTokens()) {
            endMinute = st.nextToken();
        }
        if (st.hasMoreTokens()) {
            comments = st.nextToken();
        }
        if (st.hasMoreTokens()) {
            location = st.nextToken();
        }

        Time startTime = new Time(Integer.parseInt(startYear), Integer.parseInt(startMonth), Integer.parseInt(startDay), Integer.parseInt(startHour), Integer.parseInt(startMinute));
        Time endTime = new Time(Integer.parseInt(endYear), Integer.parseInt(endMonth), Integer.parseInt(endDay), Integer.parseInt(endHour), Integer.parseInt(endMinute));

        if (type.equalsIgnoreCase("home")) {
            toReturn = new HomeActivity(description, startTime, endTime, comments);
        }

        if (type.equalsIgnoreCase("school")) {
            toReturn = new SchoolActivity(description, startTime, endTime, comments);
        }
        if (type.equalsIgnoreCase("other")) {
            toReturn = new OtherActivity(description, startTime, endTime, comments, location);
        }
        return toReturn;
    }

    /**
     * @param f f is used to dump the data from all the activities into one
     * file.
     */
    public void saveData(File f) {
        if (getActivities() == null) {
            return;
        }
        PrintWriter pw = null;

        try {
            pw = new PrintWriter(new FileOutputStream(f));
        } catch (Exception e) {
            System.out.println("Can't open file : " + f.getName());
        }
        for (Activity activity : getActivities()) {
            String s = "";
            OtherActivity forLocation = null;
            if (activity instanceof SchoolActivity) {
                s = "School";
            }
            if (activity instanceof HomeActivity) {
                s = "Home";
            }
            if (activity instanceof OtherActivity) {
                s = "Other";
                forLocation = (OtherActivity) activity;
            }
            if (pw != null) {
                pw.print(s + "," + activity.getDescription() + ","
                        + activity.getStartTime().getYear() + ","
                        + activity.getStartTime().getMonth() + ","
                        + activity.getStartTime().getDay() + ","
                        + activity.getStartTime().getHour() + ","
                        + activity.getStartTime().getMinute() + ","
                        + activity.getEndTime().getYear() + ","
                        + activity.getEndTime().getMonth() + ","
                        + activity.getEndTime().getDay() + ","
                        + activity.getEndTime().getHour() + ","
                        + activity.getEndTime().getMinute() + ", "
                        + activity.getComments());
                if (forLocation != null) {
                    pw.print("," + forLocation.getLocation());
                }
                pw.println("");
            }
        }
        if (pw != null) {
            pw.close();
        }

    }

    /**
     * @param a A is the array of activities in which you will build your
     * hashmap from. The hashmap is automatically placed in the class level
     * hashmap 'search' Can also be used to update the hashmap
     */
    private void buildHashMap(ArrayList<Activity> a) {
        if (search == null) {
            search = new HashMap<String, ArrayList<Integer>>();
        }
        for (int i = 0; i < a.size(); i++) {
            StringTokenizer st = new StringTokenizer(a.get(i).getDescription());
            String token;
            while (st.hasMoreTokens()) {
                token = st.nextToken();
                token = token.toLowerCase();
                if (search.containsKey(token)) {
                    search.get(token).add(i);
                }
                if (!search.containsKey(token)) {
                    ArrayList<Integer> newArray = new ArrayList<>();
                    newArray.add(i);
                    search.put(token, newArray);
                }

            }
        }
    }

    /**
     * @param description String of a single description that might be located
     * within the hash map.
     * @return returns the arraylist corresponding to the description
     */
    public ArrayList<Integer> searchForItem(String description) {
        if (search.containsKey(description)) {
            return search.get(description);
        }
        return null;
    }

    public Activity buildActivity() {
        Activity toReturn = null;
        Scanner sc = new Scanner(System.in);
        String option = "";

        String description = "";
        String comments = "";
        String location = "";

        Time startTime = null;
        Time endTime = null;

        int type = 0;

        while (!option.equalsIgnoreCase("1") && !option.equalsIgnoreCase("School") && !option.equalsIgnoreCase("2") && !option.equalsIgnoreCase("Home") && !option.equalsIgnoreCase("3") && !option.equalsIgnoreCase("Other")) {
            System.out.println("Which type of activity do you want to enter?");
            System.out.println("1. School");
            System.out.println("2. Home");
            System.out.println("3. Other");
            option = sc.nextLine();
        }
        if (option.equalsIgnoreCase("1") || option.equalsIgnoreCase("School")) {
            System.out.println("To enter a new school activity, you need to enter some additional criteria.");
            type = 1;
        }
        if (option.equalsIgnoreCase("2") || option.equalsIgnoreCase("Home")) {
            System.out.println("To enter a new home activity, you need to enter some additional criteria.");
            type = 2;
        }
        if (option.equalsIgnoreCase("3") || option.equalsIgnoreCase("Other")) {
            System.out.println("To enter a new 'other' activity, you need to enter some criteria.");
            type = 3;
        }

        while (description.equalsIgnoreCase("")) {
            System.out.println("Please enter a short description : ");
            description = sc.nextLine();
        }

        System.out.println("The next five prompts are for the start time.");

        while (startTime == null) {
            startTime = Time.buildNewTime();
        }
        System.out.println("The next five prompts are for the end time.");
        while (endTime == null) {
            endTime = Time.buildNewTime();
            if (startTime.compareTo(endTime) == 1) {
                endTime = null;
                System.out.println("Your end time must occur at a time after the start time.");
            }
        }

        System.out.println("Please enter optional comments : ");
        comments = sc.nextLine();

        switch (type) {
            case 1:
                toReturn = new SchoolActivity(description, startTime, endTime, comments);
                break;
            case 2:
                toReturn = new HomeActivity(description, startTime, endTime, comments);
                break;
            case 3:
                while (location.equalsIgnoreCase("")) {
                    System.out.println("Please enter a location : ");
                    location = sc.nextLine();
                }
                toReturn = new OtherActivity(description, startTime, endTime, comments, location);

                break;
        }
        return toReturn;
    }

    /**
     * Searches by criteria, uses Scanner instance and prompts and prints all the possible matches based on Descriptions
     */
    public void searchByCriteria() {
        String searchItems = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter some search criteria from the description : ");
        searchItems = sc.nextLine();
        if (searchItems.equalsIgnoreCase("")) {
            System.out.println("Displaying all activities : ");
            for (Activity a : getActivities()) {
                System.out.println(a.toString());
            }
        } else {
            StringTokenizer st = new StringTokenizer(searchItems);
            String token;

            ArrayList<Integer> results = new ArrayList<Integer>();
            ArrayList<ArrayList<Integer>> resultsTwo = new ArrayList<ArrayList<Integer>>();
            /*Find the intersection of all these lists*/

            while (st.hasMoreTokens()) {
                token = st.nextToken();
                token = token.toLowerCase();
                /*We find the lines of the activities that correspond to each of the descriptions*/
                ArrayList<Integer> toAdd = searchForItem(token);

                if (toAdd != null) {
                    /*Add the result to the arraylist of the total results*/
                    resultsTwo.add(toAdd);
                    /*Add each element of each array to one big array (only add if it doesn't already exsist*/
                    for (Integer i : toAdd) {
                        if (!results.contains(i)) {
                            results.add(i);
                        }
                    }
                }
            }
            /*We only retain the elements in the big array that also occur in every element o*/
            for (ArrayList<Integer> toRetain : resultsTwo) {
                results.retainAll(toRetain);
            }
            System.out.println("The results are as follows : ");
            for (Integer i : results) {
                System.out.println(getActivities().get(i).toString());
            }

        }
    }

    /**
     * Uses an instance of Scanner and prompts to get the input for searching by time
     */
    public void searchByTime() {
        Time startTime = null;
        Time endTime = null;
        ArrayList<Activity> possibleMatches = new ArrayList<Activity>();
        Scanner sc = new Scanner(System.in);
        String yesNo;
        do {
            System.out.println("Would you like to enter a start time : (y/n)?");
            yesNo = sc.nextLine();
        } while (!yesNo.equalsIgnoreCase("Yes") && !yesNo.equalsIgnoreCase("y") && !yesNo.equalsIgnoreCase("No") && !yesNo.equalsIgnoreCase("n"));

        if (yesNo.equalsIgnoreCase("Yes") || yesNo.equalsIgnoreCase("y")) {
            startTime = Time.buildNewTime();
        } else if (yesNo.equalsIgnoreCase("No") || yesNo.equalsIgnoreCase("n")) {
            startTime = null;
        }

        yesNo = "";
        do {
            System.out.println("Would you like to enter an end time : (y/n)?");
            yesNo = sc.nextLine();
        } while (!yesNo.equalsIgnoreCase("Yes") && !yesNo.equalsIgnoreCase("y") && !yesNo.equalsIgnoreCase("No") && !yesNo.equalsIgnoreCase("n"));

        if (yesNo.equalsIgnoreCase("Yes") || yesNo.equalsIgnoreCase("y")) {
            endTime = Time.buildNewTime();
        } else if (yesNo.equalsIgnoreCase("No") || yesNo.equalsIgnoreCase("n")) {
            endTime = null;
        }
        for (Activity a : getActivities()) {
            if (startTime != null) {
                if (startTime.compareTo(a.getStartTime()) == 0 || startTime.compareTo(a.getStartTime()) == -1) {
                    if (!possibleMatches.contains(a)) {
                        possibleMatches.add(a);
                    }
                }
            }
            if (endTime != null) {
                if (endTime.compareTo(a.getEndTime()) == 0 || endTime.compareTo(a.getEndTime()) == -1) {
                    if (!possibleMatches.contains(a)) {
                        possibleMatches.add(a);
                    }
                }
            }
        }

        System.out.println("The possible matches are : ");
        for (Activity a : possibleMatches) {
            System.out.println(a.toString());
        }

    }

}
