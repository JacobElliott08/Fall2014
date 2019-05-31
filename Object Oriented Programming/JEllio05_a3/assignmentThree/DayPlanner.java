package assignmentThree;

import java.util.*;
import java.io.*;



public class DayPlanner {

    private ArrayList<Activity> activities;
    private HashMap<String, ArrayList<Integer>> search;
    private File outPutFile;
    private File inPutFile;

    public void setInputFile(File f)
    {
    	this.inPutFile = f;
    }
    
    public void setOutputFile(File f)
    {
    	this.outPutFile = f;
    }
    
    
    public DayPlanner()
    {
    	this(null,null);
    }
    /**
     * Calls the constructor with an outPut file and no input file.
     *
     * @param outPutFile The file in which the contents of the activities will be printed out to.
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
    public void importData(File f) {
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

//        buildHashMap(activities);

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

        Time startTime = new Time(Integer.parseInt(startYear), startMonth, Integer.parseInt(startDay), Integer.parseInt(startHour), Integer.parseInt(startMinute));
        Time endTime = new Time(Integer.parseInt(endYear), endMonth, Integer.parseInt(endDay), Integer.parseInt(endHour), Integer.parseInt(endMinute));

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
    public void saveData(File f) throws NullPointerException {
        if (getActivities() == null) {
            throw new NullPointerException();
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
                    ArrayList<Integer> newArray = new ArrayList<Integer>();
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
    private ArrayList<Activity> getOtherActivities()
    {
    	ArrayList<Activity> toReturn = new ArrayList<Activity>();
    	for(Activity a : getActivities())
    	{
    		if(a instanceof OtherActivity)
    		{
    			toReturn.add(a);
    		}
    	}
    	return toReturn; 
    }
    

	private ArrayList<Activity> getSchoolActivities()
    {
    	ArrayList<Activity> toReturn = new ArrayList<Activity>();
    	for(Activity a : getActivities())
    	{
    		if(a instanceof SchoolActivity)
    		{
    			toReturn.add(a);
    		}
    	}
    	return toReturn; 
    }
    
    private ArrayList<Activity> getHomeActivities()
    {
    	ArrayList<Activity> toReturn = new ArrayList<Activity>();
    	for(Activity a : getActivities())
    	{
    		if(a.getClass().equals(HomeActivity.class))
    		{
    			toReturn.add(a);
    		}
    	}
    	return toReturn; 
    }

	/**
	 * @param title A short description of a title, this parameter may be null
	 * @param start An instance of the time class, this parameter may be null
	 * @param end An instance of the time class, this parameter may be null
	 * @return returns an array list of all the possible matches given the input criteria
	 */
	public ArrayList<Activity> searchForHomeActivity(String title, Time start, Time end)
	{
		if(title.isEmpty() && start == null && end == null)
		{
			return getHomeActivities();
		}
		ArrayList<Activity> possibleMatches = new ArrayList<Activity>();

		for(Activity s : getHomeActivities())
		{
			for(String str : title.split(" "))
			{
				if(s.getDescription().contains(str))
				{
					if(!possibleMatches.contains(s))
					{
						possibleMatches.add(s);
					}
				}
			}
			if(start != null)
			{
				if(start.compareTo(s.getStartTime()) == 0 || start.compareTo(s.getStartTime()) == -1)
				{
					if(!possibleMatches.contains(s))
					{
						possibleMatches.add(s);
					}
				}
			}
			if(end != null)
			{
				if(end.compareTo(s.getEndTime()) == 0 || end.compareTo(s.getEndTime()) == -1)
				{
					if(!possibleMatches.contains(s))
					{
						possibleMatches.add(s);
					}
				}
			}
		}
		return possibleMatches;
	}
	
	/**
	 * @param title A short description of a title, this parameter may be null
	 * @param start An instance of the time class, this parameter may be null
	 * @param end An instance of the time class, this parameter may be null
	 * @return returns an array list of all the possible matches given the input criteria
	 */
	public ArrayList<Activity> searchForOtherActivity(String title, Time start, Time end)
	{
		if(title.isEmpty() && start == null && end == null)
		{
			return getOtherActivities();
		}
		ArrayList<Activity> possibleMatches = new ArrayList<Activity>();

		for(Activity s : getOtherActivities())
		{
			for(String str : title.split(" "))
			{
				if(s.getDescription().contains(str))
				{
					if(!possibleMatches.contains(s))
					{
						possibleMatches.add(s);
					}
				}
			}
			if(start != null)
			{
				if(start.compareTo(s.getStartTime()) == 0 || start.compareTo(s.getStartTime()) == -1)
				{
					if(!possibleMatches.contains(s))
					{
						possibleMatches.add(s);
					}
				}
			}
			if(end != null)
			{
				if(end.compareTo(s.getEndTime()) == 0 || end.compareTo(s.getEndTime()) == -1)
				{
					if(!possibleMatches.contains(s))
					{
						possibleMatches.add(s);
					}
				}
			}
		}
		return possibleMatches;
	}
	
	/**
	 * @param title A short description of a title, this parameter may be null
	 * @param start An instance of the time class, this parameter may be null
	 * @param end An instance of the time class, this parameter may be null
	 * @return returns an array list of all the possible matches given the input criteria
	 */
	public ArrayList<Activity> searchForSchoolActivity(String title, Time start, Time end)
	{
		if(title.isEmpty() && start == null && end == null)
		{
			return getSchoolActivities();
		}
		ArrayList<Activity> possibleMatches = new ArrayList<Activity>();

		for(Activity s : getSchoolActivities())
		{
			if(!title.isEmpty())
			{
				for(String str : title.split(" "))
				{
					if(s.getDescription().contains(str))
					{
						if(!possibleMatches.contains(s))
						{
							possibleMatches.add(s);
						}
					}
				}
			}
			if(start != null)
			{
				if(start.compareTo(s.getStartTime()) == 1)
				{
					if(!possibleMatches.contains(s))
					{
						possibleMatches.add(s);
					}
				}
			}
			if(end != null)
			{
				if(end.compareTo(s.getEndTime()) == -1)
				{
					if(!possibleMatches.contains(s))
					{
						possibleMatches.add(s);
					}
				}
			}
		}
		return possibleMatches;
	}
	
	/**
	 * @param selectedItem either Home, School, Other, or left empty
	 * @param text Description of the activity
	 * @param startTimePanel an instance of the TimeComponent class pertaining to the start time of the activity
	 * @param endTimePanel an instance of the TimeComponent class pertaining to the end time of the activity
	 * @return Returns the array of activities pertaining to the set of criteria given in the parameters
	 */
	public ArrayList<Activity> buildResults(String selectedItem, String text, TimeComponent startTimePanel, TimeComponent endTimePanel) {
		if(selectedItem.equalsIgnoreCase(""))
		{
			return getActivities();
		}
		if(selectedItem.equalsIgnoreCase("School"))
		{
			return searchForSchoolActivity(text, startTimePanel.createInstance(), endTimePanel.createInstance());
		}
		if(selectedItem.equalsIgnoreCase("Other"))
		{
			return searchForOtherActivity(text, startTimePanel.createInstance(), endTimePanel.createInstance());
		}
		if(selectedItem.equalsIgnoreCase("Home"))
		{
			return searchForHomeActivity(text, startTimePanel.createInstance(), endTimePanel.createInstance());
		}
		return null;
	}
	
	
	
	




}
