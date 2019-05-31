/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentThree;

/**
 *
 * @author jacobelliott
 */
public class Activity {
    private String description; 
    private Time startTime;
    private Time endTime;
    private String comments;
    
    /**
     * 
     * @param d Description of activity
     * @param st Start Time must be an instance of the Time class
     * @param et End Time must be an instance of the Time class, and must occur after Start Time
     * @param c Optional comments string may be left null.
     */
    public Activity(String d, Time st, Time et, String c)
    {
       this.description = d;
       this.startTime = st;
       this.endTime = et;
       this.comments = c;
    }
    
    /**
     * @param st Start Time, must be an instance of the Time class
     * @param et End Time, must be an instance of the Time class, and occur after Start Time
     */
    public Activity(Time st, Time et)
    {
        this("",st,et,"");
    }
        
    /**
     * Returns an empty instance of the Activity
     */
    public Activity()
    {
        this("",new Time(0,"",0,0,0),new Time(0,"",0,0,0),"");
    }
    
    /**
     * @return Returns the description of the activity
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * @return Returns the start time of the activity 
     */
    public Time getStartTime()
    {
        return startTime;
    }
    
    /**
     * @return Returns the end time of the activity
     */
    public Time getEndTime()
    {
        return endTime;
    }
    
    /**
     * @return Returns the comment of the activity
     */
    public String getComments()
    {
        return comments;
    }
    
    /**
     * @param o Instance of Object class that could be the same class as Activity
     * @return Returns true if the o is equal to the current instance of Activity
     */
    @Override
    public boolean equals(Object o)
    {
        if(o.getClass() == Activity.class)
        {
            Activity a = (Activity)o;
            if(a.getComments().equalsIgnoreCase(this.getComments()))
            {
                if(a.getDescription().equalsIgnoreCase(this.getDescription()))
                {
                    if(a.getStartTime().equals(this.getStartTime()))
                    {
                        if(a.getEndTime().equals(this.getEndTime()))
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    @Override
    public String toString()
    {
        return (getDescription()+ " At "+getStartTime().toString()+" ending at "+getEndTime().toString()+" Comments : "+getComments());
    }
}
