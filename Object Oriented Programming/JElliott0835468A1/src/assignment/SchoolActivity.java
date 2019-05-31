package assignment;

public class SchoolActivity
{
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
	public SchoolActivity(String description, Time startTime, Time endTime, String comments) {
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
	public boolean equals(SchoolActivity a)
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

	/**
	 * @return returns the comments field.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 *	@return Returns the Time instances associated with the start time
	 */
	public Time getStartTime() {
		return startTime;
	}
	/**
	 * @return Returns the Time instance associated with the end time
	 */
	public Time getEndTime() {
		return endTime;
	}
}

