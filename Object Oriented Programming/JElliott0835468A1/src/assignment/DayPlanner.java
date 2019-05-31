package assignment;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

import javax.net.ssl.SSLContext;


public class DayPlanner 
{
	public static final int MAXIMUM_HOLD = 10;
	private ArrayList<HomeActivity> homeActivities;
	private ArrayList<SchoolActivity> schoolActivities;
	private ArrayList<OtherActivity> otherActivities;

	/**
	 * Creates and initializes the three arrays.
	 */
	public DayPlanner()
	{
		homeActivities = new ArrayList<HomeActivity>();
		schoolActivities = new ArrayList<SchoolActivity>();
		otherActivities = new ArrayList<OtherActivity>(); 
	}
	/**
	 * @return Returns the home activities Array
	 */
	public ArrayList<HomeActivity> getHomeActivities() 
	{
		return homeActivities;
	}

	/**
	 * @param i, Integer i is a location in which you wish to access from the Home Array
	 * @return Returns the specific element at location i, in the home array.
	 */
	public HomeActivity getHomeActivity(int i)
	{
		if (getHomeActivities().size() > i)
		{
			return getHomeActivities().get(i);
		}
		else
		{
			return null;
		}
	}

	/**
	 * @param i, Integer i is a location in which you wish to access from the School Array
	 * @return Returns the specific element at location i, in the School Array.
	 */
	public SchoolActivity getSchoolActivity(int i)
	{
		if(getSchoolActivities().size() > i)
		{
			return getSchoolActivities().get(i);
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * @param i, Integer i is a location in which you wish to access from the Other Array
	 * @return Returns the specific element at location i, in the Other Array.
	 */
	public OtherActivity getOtherActivity(int i)
	{
		if(getOtherActivities().size() > i)
		{
			return  getOtherActivities().get(i);
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * 
	 * @param h is a new instance of the a home activity the user wishes to add to the home array
	 * @return Returns true if the activity was added successfully or false  if the activity could not be added.
	 */
	public boolean addHomeActivity(HomeActivity h)
	{
		if(homeActivities.contains(h))
		{
			if(homeActivities.size() >= MAXIMUM_HOLD)
			{
			return false;
			}
		}
		return getHomeActivities().add(h);
	}
		
	@SuppressWarnings("unused")
	private void setHomeActivities(ArrayList<HomeActivity> homeActivities) 
	{	
		this.homeActivities = homeActivities;
	}
	
	
	public boolean addSchooolActivity(SchoolActivity s)
	{
		return getSchoolActivities().add(s);
	}
	
	/**
	 * @return returns the school activities array list.
	 */
	public ArrayList<SchoolActivity> getSchoolActivities() 
	{
		return schoolActivities;
	}

	@SuppressWarnings("unused")
	private void setSchoolActivities(ArrayList<SchoolActivity> schoolActivities) 
	{
		this.schoolActivities = schoolActivities;
	}

	public boolean addOtherActivity(OtherActivity o)
	{
		return getOtherActivities().add(o);
	}
	
	/**
	 * @return returns the other activities array list.
	 */
	public ArrayList<OtherActivity> getOtherActivities() 
	{
		return otherActivities;
	}

	@SuppressWarnings("unused")
	private void setOtherActivities(ArrayList<OtherActivity> otherActivities) 
	{
		this.otherActivities = otherActivities;
	}
	
	/**
	 * @param title A short description of a title, this parameter may be null
	 * @param start An instance of the time class, this parameter may be null
	 * @param end An instance of the time class, this parameter may be null
	 * @return returns an array list of all the possible matches given the input criteria
	 */
	public ArrayList<HomeActivity> searchForHomeActivity(String title, Time start, Time end)
	{
		if(title.isEmpty() && start == null && end == null)
		{
			return homeActivities;
		}
		ArrayList<HomeActivity> possibleMatches = new ArrayList<HomeActivity>();

		for(HomeActivity s : getHomeActivities())
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
				if(start.compareTo(s.getStartTime()) == -1)
				{
					if(!possibleMatches.contains(s))
					{
						possibleMatches.add(s);
					}
				}
			}
			if(end != null)
			{
				if(end.compareTo(s.getEndTime()) == 1)
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
	public ArrayList<OtherActivity> searchForOtherActivity(String title, Time start, Time end)
	{
		if(title.isEmpty() && start == null && end == null)
		{
			return otherActivities;
		}
		ArrayList<OtherActivity> possibleMatches = new ArrayList<OtherActivity>();

		for(OtherActivity s : getOtherActivities())
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
				if(start.compareTo(s.getStartTime()) == -1)
				{
					if(!possibleMatches.contains(s))
					{
						possibleMatches.add(s);
					}
				}
			}
			if(end != null)
			{
				if(end.compareTo(s.getEndTime()) == 1)
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
	public ArrayList<SchoolActivity> searchForSchoolActivity(String title, Time start, Time end)
	{
		if(title.isEmpty() && start == null && end == null)
		{
			return schoolActivities;
		}
		ArrayList<SchoolActivity> possibleMatches = new ArrayList<SchoolActivity>();

		for(SchoolActivity s : getSchoolActivities())
		{
			for(String str : title.split(" "))
			{
                if(!title.isEmpty())
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
				if(start.compareTo(s.getStartTime()) == -1)
				{
					if(!possibleMatches.contains(s))
					{
						possibleMatches.add(s);
					}
				}
			}
			if(end != null)
			{
				if(end.compareTo(s.getEndTime()) == 1)
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
	
	
	
	
	
}	



