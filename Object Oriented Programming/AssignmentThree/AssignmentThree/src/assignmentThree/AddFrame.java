/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentThree;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author jacobelliott
 */
public class AddFrame extends JPanel
{
    JTextField description;
    JTextField comments;
  
    JComboBox<String> typeOfActivity;
    
    JComboBox<String> startYear;
    JComboBox<String> startMonth;
    JComboBox<String> startDay;
    JComboBox<String> startHour;
    JComboBox<String> startMinute;
    
    JComboBox<String> endYear;
    JComboBox<String> endMonth;
    JComboBox<String> endDay;
    JComboBox<String> endHour;
    JComboBox<String> endMinute;
    
    JTextField location;
    
    JButton add;
    JButton reset;
    
    JTextArea message;
    
    JPanel descriptionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel startTimePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel endTimePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel commentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel messagePanel = new JPanel(new BorderLayout());
    
    DayPlanner dayPlanner;
    /**
     * @param p An instance of the DayPlanner class used when adding new activities
     */
    public AddFrame(DayPlanner p)
    {   
        JLabel descriptionLabel;
        JLabel startTimeLabel = null;
        JLabel endTimeLabel = null;
        JLabel commentLabel = null;
        JLabel locationLabel = null;
        
        JPanel activityType = new JPanel(new FlowLayout());
        JLabel typeOfActivityLabel = null;
        typeOfActivity = new JComboBox<String>();
        
        typeOfActivity.addItem("");
        typeOfActivity.addItem("Home");
        typeOfActivity.addItem("School");
        typeOfActivity.addItem("Other");
        typeOfActivity.addActionListener(new activitySelector());
        
        typeOfActivityLabel = new JLabel("Please select :");
        activityType.add(typeOfActivityLabel);
        activityType.add(typeOfActivity);
        
        
        description = new JTextField(20);
        description.setName("Description");
        description.addFocusListener(new blah());
        descriptionLabel = new JLabel("Description : ");
        descriptionPanel.add(descriptionLabel);
        descriptionPanel.add(description);
        descriptionPanel.setVisible(false);
        
        startYear = new JComboBox<String>();
        startMonth = new JComboBox<String>();
        startDay = new JComboBox<String>();
        startHour = new JComboBox<String>();
        startMinute = new JComboBox<String>();
        startTimeLabel = new JLabel("  Start Time : "); 
        startTimePanel.add(startTimeLabel);
        startTimePanel.add(startYear);
        startTimePanel.add(startMonth);
        startTimePanel.add(startDay);
        startTimePanel.add(startHour);
        startTimePanel.add(startMinute);
        startTimePanel.setVisible(false);
        
        startYear.setName("Start Year");
        startYear.addFocusListener(new blah());
        
        startMonth.setName("Start Month");
        startMonth.addFocusListener(new blah());
        
        startDay.setName("Start Day");
        startDay.addFocusListener(new blah());
        
        startHour.setName("Start Hour");
        startHour.addFocusListener(new blah());
        
        startMinute.setName("Start Minute");
        startMinute.addFocusListener(new blah());
        
        
        
         endYear = new JComboBox<String>();
         endMonth = new JComboBox<String>();
         endDay = new JComboBox<String>();
         endHour = new JComboBox<String>();
         endMinute = new JComboBox<String>();
        						 
        endTimeLabel = new JLabel("    End Time : ");
        endTimePanel.add(endTimeLabel);
        endTimePanel.add(endYear);
        endTimePanel.add(endMonth);
        endTimePanel.add(endDay);
        endTimePanel.add(endHour);
        endTimePanel.add(endMinute);
        endTimePanel.setVisible(false);
        
        endYear.setName("End Year");
        endYear.addFocusListener(new blah());
        
        endMonth.setName("End Month");
        endMonth.addFocusListener(new blah());
        
        endDay.setName("End Day");
        endDay.addFocusListener(new blah());
        
        endHour.setName("End Hour");
        endHour.addFocusListener(new blah());
        
        endMinute.setName("End Minute");
        endMinute.addFocusListener(new blah());
        
        populateFields();
        
        add = new JButton("Add");
        add.addActionListener(new addAction());
        reset = new JButton("Reset");
        reset.addActionListener(new resetAction());
        buttonPanel.add(add);
        buttonPanel.add(reset);
        buttonPanel.setVisible(false);
        
        commentLabel = new JLabel("Please enter additional comments : ");
        comments = new JTextField(20);
        commentPanel.add(commentLabel,BorderLayout.NORTH);
        commentPanel.add(comments,BorderLayout.CENTER);
        commentPanel.setVisible(false);
         
        locationLabel = new JLabel("     Location : ");
        location = new JTextField(20);
        location.addFocusListener(new blah());
        location.setName("Location");
        locationPanel.add(locationLabel);
        locationPanel.add(location);
        locationPanel.setVisible(false);
        
        message = new JTextArea();
        JLabel messageLabel = new JLabel("Messages ");
        messagePanel.add(message,BorderLayout.CENTER);
        messagePanel.add(messageLabel,BorderLayout.NORTH);
        messagePanel.setBorder(new EmptyBorder(10,10,10,10));
        
        messagePanel.add(new JScrollPane(message));
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(activityType);
        
        JTextField buffer = new JTextField();
        buffer.setEditable(false);
        
        commentPanel.setBorder(new EmptyBorder(10,10,10,10));
        this.add(descriptionPanel);
        this.add(startTimePanel);
        this.add(endTimePanel);
        this.add(locationPanel);
        this.add(commentPanel);
        this.add(messagePanel);
        this.add(buttonPanel);
        
        dayPlanner = p;
    }

    /**
     * @return returns the text of the description text field
     */
    public String getDescription()
    {
    	return description.getText();
    }
    /**
     * @return returns the text in the comment text field
     */
    public String getComments()
    {
    	return comments.getText();
    }
    /**
     * @return returns the text in the start year field
     */
    public String getStartYear()
    {
    	return (String) startYear.getSelectedItem();
    }
    /**
     *
     * @return returns the text in the start month field
     */
    public String getStartMonth()
    {
    	return (String) startMonth.getSelectedItem();
    }
    /**
     * 
     * @return returns the text in the start day field
     */
    public String getStartDay()
    {
    	return (String) startDay.getSelectedItem();
    }
    
    /**
     * 
     * @return returns the text in the start hour field
     */
    public String getStartHour()
    {
    	return (String) startHour.getSelectedItem();
    }
    /**
     * 
     * @return returns the text in the start minute field
     */
    public String getStartMinute()
    {
    	return (String) startMinute.getSelectedItem();
    }
    
    
    /**
     * 
     * @return returns the text in the end year field
     */
    public String getEndYear()
    {
    	return (String) endYear.getSelectedItem();
    }
    
    /**
     * 
     * @return returns the text in the end month field
     */
    public String getEndMonth()
    {
    	return (String) endMonth.getSelectedItem();
    }
    
    /**
     * 
     * @return returns the text in the end day field
     */
    public String getEndDay()
    {
    	return (String) endDay.getSelectedItem();
    }
    
    /**
     * 
     * @return returns the text in the end hour field
     */
    public String getEndHour()
    {
    	return (String) endHour.getSelectedItem();
    }
    
    /**
     * 
     * @return returns the text in the end minute field
     */
    public String getEndMinute()
    {
    	return (String) endMinute.getSelectedItem();
    }
    
    /**
     * 
     * @return returns the text in the activity field
     */
    public String getActivityType()
    {
    	return (String) typeOfActivity.getSelectedItem();
    }
    
    /**
     * 
     * @return returns the text in the location field
     */
    public String getLocationString()
    {
    	return (String) location.getText();
    }
    
    /**
     * populates the fields of the time related fields
     */
    public void populateFields()
    {
    	String [] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
    	startDay.addItem("Day");
		endDay.addItem("Day");
		startMonth.addItem("Month");
		endMonth.addItem("Month");
		startYear.addItem("Year");
		endYear.addItem("Year");
		startHour.addItem("Hour");
		endHour.addItem("Hour");
		startMinute.addItem("Minute");
		endMinute.addItem("Minute");
    	for(int i = 1; i <= 31; i ++)
    	{
    		startDay.addItem(i+"");
    		endDay.addItem(i+"");
    	}
    	for(String s : months)
    	{
    		startMonth.addItem(s);
    		endMonth.addItem(s);
    	}
    	for(int i = 2000; i <= 2030; i ++)
    	{
    		startYear.addItem(i+"");
    		endYear.addItem(i+"");
    	}
    	for(int i = 0; i < 24; i ++)
    	{
    		startHour.addItem(i+"");
    		endHour.addItem(i+"");
    	}
    	for(int i = 0; i < 60; i ++)
    	{
    		startMinute.addItem(i+"");
    		endMinute.addItem(i+"");
    	}
    }
    
    /**
     * @return true if a field was changed from white to red
     * highlights all time fields that were filled in incorrectly
     */
    public boolean highLightIncorrect()
    {
    	boolean toReturn = false;
    	if(getStartYear().equalsIgnoreCase("Year"))
    	{
    		startYear.setBackground(Color.RED);
    		toReturn = true;
    		message.append("Start Year may not be the value 'Year'.\n");
    	}
    	if(getStartMonth().equalsIgnoreCase("Month"))
    	{
    		startMonth.setBackground(Color.RED); 
    		toReturn = true;
    		message.append("Start Month may not be the value 'Month'.\n");
    	}
    	if(getStartDay().equalsIgnoreCase("Day"))
    	{
    		startDay.setBackground(Color.RED); 
    		toReturn = true;
    		message.append("Start Day may not be the value 'Day'.\n");
    	}
    	if(getStartHour().equalsIgnoreCase("Hour"))
    	{
    		startHour.setBackground(Color.RED); 
    		toReturn = true;
    		message.append("Start Hour may not be the value 'Hour'.\n");
    	}
    	if(getStartMinute().equalsIgnoreCase("Minute"))
    	{
    		startMinute.setBackground(Color.RED); 
    		toReturn = true;
    		message.append("Start Minute may not be the value 'Minute'.\n");
    	}
    	
    	
    	if(getEndYear().equalsIgnoreCase("Year"))
    	{
    		endYear.setBackground(Color.RED); 
    		toReturn = true;
    		message.append("End Year may not be the value 'Year'.\n");
    	}
    	if(getEndMonth().equalsIgnoreCase("Month"))
    	{
    		endMonth.setBackground(Color.RED); 
    		toReturn = true;
    		message.append("End Month may not be the value 'Month'.\n");
    	}
    	if(getEndDay().equalsIgnoreCase("Day"))
    	{
    		endDay.setBackground(Color.RED); 
    		toReturn = true;
    		message.append("End Day may not be the value 'Day'.\n");
    	}
    	if(getEndHour().equalsIgnoreCase("Hour"))
    	{
    		endHour.setBackground(Color.RED); 
    		toReturn = true;
    		message.append("End Hour may not be the value 'Hour'.\n");
    	}
    	if(getEndMinute().equalsIgnoreCase("Minute"))
    	{
    		endMinute.setBackground(Color.RED); 
    		toReturn = true;
    		message.append("End Minute may not be the value 'Minute'.\n");
    	}
    	
    	return toReturn;
    }
    
    /**
     * highlights all time related fields in red
     */
    private void highLightInRed()
    {
		endYear.setBackground(Color.RED); 
    	endMonth.setBackground(Color.RED);
    	endDay.setBackground(Color.RED);
    	endHour.setBackground(Color.RED);
    	endMinute.setBackground(Color.RED);
    	startYear.setBackground(Color.RED);
    	startMonth.setBackground(Color.RED);
    	startDay.setBackground(Color.RED);
    	startMinute.setBackground(Color.RED);
    	startHour.setBackground(Color.RED);
    }
    
    /**
     * highlights all time related fields in white
     */
    private void highLightInWhite()
    {
		endYear.setBackground(Color.WHITE); 
    	endMonth.setBackground(Color.WHITE);
    	endDay.setBackground(Color.WHITE);
    	endHour.setBackground(Color.WHITE);
    	endMinute.setBackground(Color.WHITE);
    	startYear.setBackground(Color.WHITE);
    	startMonth.setBackground(Color.WHITE);
    	startDay.setBackground(Color.WHITE);
    	startMinute.setBackground(Color.WHITE);
    	startHour.setBackground(Color.WHITE);
    	description.setBackground(Color.WHITE);
    	location.setBackground(Color.WHITE);
    	message.setText("");
    }
    
    /**
     * resets all fields to the default values
     */
	private void reset() {
		
		endYear.setSelectedIndex(0); 
    	endMonth.setSelectedIndex(0);
    	endDay.setSelectedIndex(0);
    	endHour.setSelectedIndex(0);
    	endMinute.setSelectedIndex(0);
    	startYear.setSelectedIndex(0);
    	startMonth.setSelectedIndex(0);
    	startDay.setSelectedIndex(0);
    	startMinute.setSelectedIndex(0);
    	startHour.setSelectedIndex(0);
    	description.setText("");
    	location.setText("");
    	comments.setText("");
    	highLightInWhite();
    	message.setText("");
		
	}
	
    private class activitySelector implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(typeOfActivity.getSelectedItem().equals("Home") ||  typeOfActivity.getSelectedItem().equals("School"))
            {
                startTimePanel.setVisible(true);
                endTimePanel.setVisible(true);
                descriptionPanel.setVisible(true);
                commentPanel.setVisible(true);
                locationPanel.setVisible(false);
                buttonPanel.setVisible(true);
                
                
            }
            else if (typeOfActivity.getSelectedItem().equals("Other"))
            {
                startTimePanel.setVisible(true);
                endTimePanel.setVisible(true);
                descriptionPanel.setVisible(true);
                commentPanel.setVisible(true);
            	locationPanel.setVisible(true);
            	buttonPanel.setVisible(true);
            }
            else
            {
                startTimePanel.setVisible(false);
                endTimePanel.setVisible(false);
                descriptionPanel.setVisible(false);    
                locationPanel.setVisible(false);
                commentPanel.setVisible(false);
                buttonPanel.setVisible(false);
                
            }
        }
        
    }

    private class addAction implements ActionListener
    {

		public void actionPerformed(ActionEvent e) 
		{
			highLightInWhite();
			if(highLightIncorrect())
			{
				return;
			}
			Time startTime = new Time(Integer.parseInt(getStartYear()),getStartMonth(),Integer.parseInt(getStartDay()),Integer.parseInt(getStartHour()),Integer.parseInt(getStartMinute()));
			Time endTime = new Time(Integer.parseInt(getEndYear()),getEndMonth(),Integer.parseInt(getEndDay()),Integer.parseInt(getEndHour()),Integer.parseInt(getEndMinute()));
			if(startTime.compareTo(endTime)!= -1)
			{
				highLightInRed();
				message.append("Start Time must occur before End Time.\n");
				return;
			}
			
			if(getDescription().equalsIgnoreCase(""))
			{
				description.setBackground(Color.RED);
				message.append("You must enter a description of the activity.\n");
				return;
			}
			if(getActivityType().equalsIgnoreCase("other"))
			{
				if(getLocationString().equalsIgnoreCase(""))
				{
					message.append("You must enter a location for the activity.\n");
					location.setBackground(Color.RED);
					return;
				}
			}
			Activity activity = null;
			if(getActivityType().equalsIgnoreCase("home"))
			{
				activity = new HomeActivity(getDescription(), startTime, endTime, getComments());
			}
			if(getActivityType().equalsIgnoreCase("school"))
			{
				activity = new SchoolActivity(getDescription(), startTime, endTime, getComments());
			}
			if(getActivityType().equalsIgnoreCase("other"))
			{
				activity = new OtherActivity(getDescription(), startTime, endTime, getComments(), getLocationString());
			}
			dayPlanner.addActivity(activity);
			message.append("Succesfully added : "+activity.toString()+"\n");
			reset();
		}


    	
    }
    private class resetAction implements ActionListener
    {

		public void actionPerformed(ActionEvent arg0) 
		{
			reset();
		}
    	
    }
    
    private class blah implements FocusListener
    {
    	/**
    	 * Resets the colours of the time frames from red back to white when focus is gained.
    	 */
		public void focusGained(FocusEvent e) {
			if(e.getComponent().getName().equalsIgnoreCase("Description"))
			{
				description.setBackground(Color.WHITE);
			}
			if(e.getComponent().getName().equalsIgnoreCase("Start Year"))
			{
				startYear.setBackground(Color.WHITE);
			}
			if(e.getComponent().getName().equalsIgnoreCase("Start Month"))
			{
				startMonth.setBackground(Color.WHITE);
			}
			if(e.getComponent().getName().equalsIgnoreCase("Start Day"))
			{
				startDay.setBackground(Color.WHITE);
			}
			if(e.getComponent().getName().equalsIgnoreCase("Start Hour"))
			{
				startHour.setBackground(Color.WHITE);
			}
			if(e.getComponent().getName().equalsIgnoreCase("Start Minute"))
			{
				startMinute.setBackground(Color.WHITE);
			}
			
			
			if(e.getComponent().getName().equalsIgnoreCase("End Year"))
			{
				endYear.setBackground(Color.WHITE);
			}
			if(e.getComponent().getName().equalsIgnoreCase("End Month"))
			{
				endMonth.setBackground(Color.WHITE);
			}
			if(e.getComponent().getName().equalsIgnoreCase("End Day"))
			{
				endDay.setBackground(Color.WHITE);
			}
			if(e.getComponent().getName().equalsIgnoreCase("End Hour"))
			{
				endHour.setBackground(Color.WHITE);
			}
			if(e.getComponent().getName().equalsIgnoreCase("End Minute"))
			{
				endMinute.setBackground(Color.WHITE);
			}
			if(e.getComponent().getName().equalsIgnoreCase("Location"))
			{
				location.setBackground(Color.WHITE);
			}
		}

		public void focusLost(FocusEvent arg0) {}
    }
}
