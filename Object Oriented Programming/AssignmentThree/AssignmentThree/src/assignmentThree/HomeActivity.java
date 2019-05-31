package assignmentThree;

public class HomeActivity extends Activity{
	
	/**
	 * @param description A short description of the School Activity
	 * @param startTime An instance of the Time class for the starting time of the activity.
	 * @param endTime An instance of the Time class for the ending time of the activity
	 * @param comments An optional comment about the activity.
	 */
	public HomeActivity(String description, Time startTime, Time endTime, String comments)
	{
            super(description, startTime,endTime,comments);
	}
        
        /**
         * @param st A start time, instance of the Time class
         * @param et An end time, also an instance of the Time class, but occur after the start time
         */
        public HomeActivity(Time st, Time et)
        {
            this("",st,et,"");
        }
        public HomeActivity()
        {
            this("",null,null,"");
        }
        
	
}
