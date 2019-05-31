package assignmentThree;

public class SchoolActivity extends Activity
{
	/**
	 * @param description A short description of the School Activity
	 * @param startTime An instance of the Time class for the starting time of the activity.
	 * @param endTime An instance of the Time class for the ending time of the activity
	 * @param comments An optional comment about the activity.
	 */
	public SchoolActivity(String description, Time startTime, Time endTime, String comments) {
            super(description,startTime,endTime,comments);
	}
	
        /**
         * Empty constructor returns a new instance of the Activity class
         */
        public SchoolActivity()
        {
            super();
        }
        
        /**
         * @param startTime an instance of the Time Class
         * @param endTime an instance of the Time Class, must occur after the start time
         */
        public SchoolActivity(Time startTime, Time endTime)
        {
            super(startTime,endTime);
        }
}

