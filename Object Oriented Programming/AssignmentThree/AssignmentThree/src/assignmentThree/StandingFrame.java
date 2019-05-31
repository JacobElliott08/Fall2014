/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentThree;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


/**
 *
 * @author jacobelliott
 */
public class StandingFrame extends JPanel{
    JPanel thePanel = this;
    DayPlanner planner;
    public StandingFrame(DayPlanner p)
    {
        this.setLayout(new BorderLayout());
        
        JTextArea helloMessage = new JTextArea("Welcome to the day planner.\nYou may select a function from the above menu.\nYou may select to either add a new activity using the add function from above, \nor you may search through the existing activities.\n\n\nYou may use the load and save buttons to either : \nLoad pre-exsiting file\nSave current data.");

        
        JPanel messagePanel = new JPanel(new BorderLayout());
        JLabel messageLabel = new JLabel("Hello!");
        helloMessage.setBorder(new EmptyBorder(10,10,10,10));
        
        helloMessage.setWrapStyleWord(true);
        messagePanel.add(messageLabel,BorderLayout.NORTH);
        messagePanel.add(new JScrollPane(helloMessage),BorderLayout.CENTER);
        
        messagePanel.add(helloMessage);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        JButton loadButton = new JButton("Load");
        JButton saveButton = new JButton("Save");
        
        loadButton.addActionListener(new loadAction());
        saveButton.addActionListener(new saveAction());
        
        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);
        
        this.add(messagePanel,BorderLayout.NORTH);
        this.add(buttonPanel,BorderLayout.SOUTH);
        
        planner = p;
    }
    
    private class loadAction implements ActionListener
    {

		public void actionPerformed(ActionEvent a) 
		{
			JFileChooser open = new JFileChooser();
			open.showOpenDialog(thePanel);
			try
			{
			 

				planner.importData(open.getSelectedFile());
				JOptionPane.showMessageDialog(thePanel,"File Loaded.","A plain message",JOptionPane.PLAIN_MESSAGE);
			}
			catch(NullPointerException e)
			{
				JOptionPane.showMessageDialog(thePanel,"Invalid File.","A plain message",JOptionPane.PLAIN_MESSAGE);
			}
		}
    	
    }
    
    private class saveAction implements ActionListener
    {
		public void actionPerformed(ActionEvent a) 
		{
			JFileChooser save = new JFileChooser();
			save.showOpenDialog(thePanel);
			try
			{
				planner.saveData(save.getSelectedFile());
				JOptionPane.showMessageDialog(thePanel,"File Saved.","A plain message",JOptionPane.PLAIN_MESSAGE);
			}
			catch(NullPointerException e)
			{
				JOptionPane.showMessageDialog(thePanel,"Invalid File.","A plain message",JOptionPane.PLAIN_MESSAGE);
			}
		}
    }
    
}
