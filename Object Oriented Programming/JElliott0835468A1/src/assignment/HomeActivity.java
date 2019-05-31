package assignment;

public class HomeActivity{
	
	private String description; 
	private Time startTime;
	private Time endTime;
	private String comments;
	
	/**
	 * @param description A short description of the School Activity
	 * @param startTime An instance of the Time class for the starting time of the activity.
	 * @param endTime An instance of the Time class for the ending time of the activity
	 * @param comments An optional comment about the activity.
	 */
	public HomeActivity(String description, Time startTime, Time endTime, String comments)
	{
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
		this.comments = comments;
	}
	

	public String getDescription() {
		return description;
	}
	
	/**
	 * @return returns a string of the format "Description :        Start time :        End Time :        Comments :             
	 */
	public String toString()
	{
		return "Description : "+getDescription()+" Start time : "+getStartTime().toString()+" End Time : "+getEndTime().toString()+" Comments : "+getComments();
	}
	
	/**
	 * @param a another instance of OtherActivity
	 * @return returns true if the current instance is equal to the parameter and returns false is they are not equal.
	 */
	public boolean equals(HomeActivity a)
	{
		if(a.getDescription().equalsIgnoreCase(this.description))
		{
			if(a.getStartTime().equals(this.getStartTime()))
			{
				if(a.getEndTime().equals(this.getEndTime()))
				{
					if(a.getComments().equalsIgnoreCase(this.getComments()))
					{
						return true;
					}
				}
			}
		}
		return false;
	}

	public String getComments() {
		return comments;
	}

	public Time getStartTime() {
		return startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
}
