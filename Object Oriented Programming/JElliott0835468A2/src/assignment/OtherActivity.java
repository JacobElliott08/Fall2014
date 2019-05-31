package assignment;

public class OtherActivity extends Activity
{
	private String location;

	/**
	 * @param description A short description of the School Activity
	 * @param startTime An instance of the Time class for the starting time of the activity.
	 * @param endTime An instance of the Time class for the ending time of the activity
	 * @param comments An optional comment about the activity.
	 * @param location A location string must be specified, it will accept anything.
	 */
	public OtherActivity(String description, Time startTime, Time endTime, String comments,String location) {
            super(description,startTime,endTime,comments);
            this.location = location;
	
	}
        /**
         * @param location Sets the class variable location to the value of the parameter location
         */
        public void setLocation(String location)
        {
            this.location = location;
        }
	
	/**
	 * @return returns the location String.
	 */
	public String getLocation() {
		return location;
	}


	
	/**
	 * @return returns a string of the format "Description :        Start time :        End Time :        Comments :             
	 */
	public String toString()
	{
		return (super.toString()+" Location : "+location);
	}
	
	/**
	 * 
	 * @param o Checks if o is an instance of OtherActivity and returns true if they are equal
	 * @return returns true if the current instance is equal to the parameter and returns false is they are not equal.
	 */
        @Override
	public boolean equals(Object o)
	{
            if(super.equals(o))
            {
                if(o.getClass() == this.getClass())
                {
                    OtherActivity a = (OtherActivity)o;
                    if(a.getLocation().equalsIgnoreCase(this.getLocation()))
                    {
                        return true;
                    }
                }
            }
            return false;
	}
}
