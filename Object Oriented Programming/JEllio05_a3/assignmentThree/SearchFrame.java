/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentThree;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


/**
 *
 * @author jacobelliott
 */
public class SearchFrame extends JPanel {
	DayPlanner instance;
 
	JTextField description;
    JTextArea results;
  
    JComboBox<String> typeOfActivity;
    
    TimeComponent endTimePanel;
    TimeComponent startTimePanel;
    
    /**
     * @param p An instance of the DayPlanner class to use.
     * Creates and initalizes the SearchFrame adding all its panels.
     */
    public SearchFrame(DayPlanner p){
    	instance = p;
    	
    	JPanel typePanel = new JPanel();
    	typePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    	JLabel activityLabel = new JLabel("Select type of activity : ");
    	typeOfActivity = new JComboBox<String>();
        
        typeOfActivity.addItem("");
        typeOfActivity.addItem("Home");
        typeOfActivity.addItem("School");
        typeOfActivity.addItem("Other");
        typePanel.add(activityLabel);
        typePanel.add(typeOfActivity,FlowLayout.CENTER);
       
    	JPanel descriptionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	JLabel descriptionLabel = new JLabel("Description : ");
    	description = new JTextField(20);
    	descriptionPanel.add(descriptionLabel);
    	descriptionPanel.add(description);
    	
    	startTimePanel = new TimeComponent("Start Time   : ", new FlowLayout(FlowLayout.LEFT));
    	
    	
    	endTimePanel = new TimeComponent("End Time     : ", new FlowLayout(FlowLayout.LEFT));
    	
    	JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    	JButton search = new JButton("Search");
    	JButton reset = new JButton("Reset");
    	reset.addActionListener(new resetAction());
    	buttonPanel.add(search);
    	
    	search.addActionListener(new searchAction());
    	buttonPanel.add(reset);
    	
    	JPanel resultsPanel = new JPanel(new BorderLayout());
    	JLabel resultsLabel = new JLabel("Possible Results : ");
    	
    	results = new JTextArea();
    	results.setBorder(new EmptyBorder(20,20,20,20));
    	resultsPanel.add(resultsLabel,BorderLayout.NORTH);
    	resultsPanel.add(results,BorderLayout.CENTER);
    	resultsPanel.setBorder(new EmptyBorder(20,20,20,20));
    	results.setWrapStyleWord(true);
    	resultsPanel.add(new JScrollPane(results));
    	
    	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    	this.add(typePanel);
    	this.add(descriptionPanel);
    	this.add(startTimePanel);
    	this.add(endTimePanel);
       	this.add(resultsPanel);
    	this.add(buttonPanel);
    
    }
    
	private void reset() 
	{
		startTimePanel.reset();
		endTimePanel.reset();
		description.setText("");
		description.setBackground(Color.white);
		results.setText("");
	}	
    
    private class searchAction implements ActionListener
    {

		public void actionPerformed(ActionEvent e) 
		{
			ArrayList<Activity> arrResults = instance.buildResults((String)typeOfActivity.getSelectedItem(),description.getText(),startTimePanel,endTimePanel);
			results.setText("");
			if(arrResults != null)
			{
				for(Activity a : arrResults)
				{
					results.append(a.toString()+"\n");
				}	
			}
			
		}
    }
    
    private class resetAction implements ActionListener
    {
    	public void actionPerformed(ActionEvent e) 
    	{
    		reset();
    	}
    }
}  
	