package assignmentThree;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TimeComponent extends JPanel{
    
	private JComboBox<String> year;
    private JComboBox<String> month;
    private JComboBox<String> day;
    private JComboBox<String> hour;
    private JComboBox<String> minute;
    
    public TimeComponent(String s,LayoutManager layout)
    {
        year = new JComboBox<String>();
        month = new JComboBox<String>();
        day = new JComboBox<String>();
        hour = new JComboBox<String>();
        minute = new JComboBox<String>();
        JLabel title = new JLabel(s);
        populateFields();
        
        this.setLayout(layout);
        this.add(title);
        this.add(year);
        this.add(month);
        this.add(day);
        this.add(hour);
        this.add(minute);
        
    }
    
    public JComboBox<String> getYear()
    {
    	return year;
    }
    public void setYear(JComboBox<String> year)
    {
    	this.year = year;
    }
    
    public JComboBox<String> getMonth()
    {
    	return month;
    }
    public void setMonth(JComboBox<String> month)
    {
    	this.month = month;
    }
    
    public JComboBox<String> getDay()
    {
    	return day;
    }
    public void setDat(JComboBox<String> day)
    {
    	this.day = day;
    }
    
    public JComboBox<String> getHour()
    {
    	return hour;
    }
    public void setHour(JComboBox<String> hour)
    {
    	this.hour = hour;
    }
    
    public JComboBox<String> getMinute()
    {
    	return minute;
    }
    public void setMinute(JComboBox<String> minute)
    {
    	this.minute = minute;
    }
    
   
    
    public void highLightInRed()
    {
		year.setBackground(Color.RED); 
    	month.setBackground(Color.RED);
    	day.setBackground(Color.RED);
    	hour.setBackground(Color.RED);
    	minute.setBackground(Color.RED);
    }
    
    public void highLightInWhite()
    {
		year.setBackground(Color.WHITE); 
    	month.setBackground(Color.WHITE);
    	day.setBackground(Color.WHITE);
    	hour.setBackground(Color.WHITE);
    	minute.setBackground(Color.WHITE);

    }
    
	public void reset() {
		
		year.setSelectedIndex(0); 
    	month.setSelectedIndex(0);
    	day.setSelectedIndex(0);
    	hour.setSelectedIndex(0);
    	minute.setSelectedIndex(0);		
    	highLightInWhite();
	}
    public boolean highLightIncorrect()
    {
    	boolean toReturn = false;
    	if(getYear().getSelectedItem().equals("Year"))
    	{
    		year.setBackground(Color.RED); toReturn = true;
    	}
    	if(getMonth().getSelectedItem().equals("Month"))
    	{
    		month.setBackground(Color.RED); toReturn = true;
    	}
    	if(getDay().getSelectedItem().equals("Day"))
    	{
    		day.setBackground(Color.RED); toReturn = true;
    	}
    	if(getHour().getSelectedItem().equals("Hour"))
    	{
    		hour.setBackground(Color.RED); toReturn = true;
    	}
    	if(getMinute().getSelectedItem().equals("Minute"))
    	{
    		minute.setBackground(Color.RED); toReturn = true;
    	}
    	

    	return toReturn;
    }
    
    public void populateFields()
    {
    	String [] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
    	day.addItem("Day");
		month.addItem("Month");
		year.addItem("Year");
		hour.addItem("Hour");
		
		minute.addItem("Minute");
	
    	for(int i = 1; i <= 31; i ++)
    	{
    		day.addItem(i+"");
    	}
    	for(String s : months)
    	{
    		month.addItem(s);
    	}
    	for(int i = 2000; i <= 2030; i ++)
    	{
    		year.addItem(i+"");
    	}
    	for(int i = 0; i < 24; i ++)
    	{
    		hour.addItem(i+"");
    	}
    	for(int i = 0; i < 60; i ++)
    	{
    		minute.addItem(i+"");
    	}
    }
    
    public boolean isNull()
    {
    	if(getYear().getSelectedItem().equals("Year") && getMonth().getSelectedItem().equals("Month") && getDay().getSelectedItem().equals("Day") && getHour().getSelectedItem().equals("Hour") && getMinute().getSelectedItem().equals("Minute"))
    	{
    		return true;
    	}
    	return false;
    }
    
    public Time createInstance() throws NullPointerException
    {
    	Time toReturn = null;
    	if(!isNull())
    	{
    		toReturn = new Time(Integer.parseInt((String) year.getSelectedItem()), (String) month.getSelectedItem(), Integer.parseInt((String) day.getSelectedItem()), Integer.parseInt((String) hour.getSelectedItem()), Integer.parseInt((String) minute.getSelectedItem()));
    	}
    	else
    	{
    		//throw new NullPointerException();
    	}
    
    	
    	return toReturn;
    }
}
