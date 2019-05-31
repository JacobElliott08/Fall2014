package assignment;

public class OtherActivity
{
	private String description; 
	private Time startTime;
	private Time endTime;
	private String comments;
	private String location;

	/**
	 * @param description A short description of the School Activity
	 * @param startTime An instance of the Time class for the starting time of the activity.
	 * @param endTime An instance of the Time class for the ending time of the activity
	 * @param comments An optional comment about the activity.
	 * @param location A location string must be specified, it will accept anything.
	 */
	public OtherActivity(String description, Time startTime, Time endTime, String location, String comments) {
		
		this.location = location;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
		this.comments = comments;
	}
	
	/**
	 * @return returns the location String.
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @return returns the comments String.
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @return returns the Time instance associated with the Start Time.
	 */
	public Time getStartTime() {
		return startTime;
	}
	
	/** 
	 * @return returns the Time instance associated with the Start Time.
	 */
	public Time getEndTime() {
		return endTime;
	}
	/**
	 * @return returns the description parameter.
	 */
	public String getDescription()
	{
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
	 * 
	 * @param o another instance of OtherActivity
	 * @return returns true if the current instance is equal to the parameter and returns false is they are not equal.
	 */
	public boolean equals(OtherActivity o)
	{
		if(o.getDescription().equalsIgnoreCase(this.description))
		{
			if(o.getStartTime().equals(this.getStartTime()))
			{
				if(o.getEndTime().equals(this.getEndTime()))
				{
					if(o.getComments().equalsIgnoreCase(this.getComments()))
					{
						if(o.getLocation().equalsIgnoreCase(this.getLocation()))
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
