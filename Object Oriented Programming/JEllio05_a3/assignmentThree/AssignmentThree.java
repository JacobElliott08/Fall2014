/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentThree;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AssignmentThree extends JFrame
{
    public DayPlanner dayPlanner;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 500;
    
    private JPanel searchPanel;
    private JPanel addPanel;
    private JPanel standingPanel;

    private class searchListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            addPanel.setVisible(false);
            searchPanel.setVisible(true);
            standingPanel.setVisible(false);
        }
    } //End of RedListener inner class

    private class addListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            addPanel.setVisible(true);
            searchPanel.setVisible(false);
            standingPanel.setVisible(false);
        }
    } //End of WhiteListener inner class

    private class homeListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            addPanel.setVisible(false);
            searchPanel.setVisible(false);
            standingPanel.setVisible(true);
        }
    } //End of BlueListener inner class

    public static void main(String[] args)
    {
        AssignmentThree gui = new AssignmentThree( );
        gui.setVisible(true);
    }

    public AssignmentThree( )
    {
    	super("Day Planner");
    	dayPlanner = new DayPlanner();
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        searchPanel = new SearchFrame(dayPlanner);
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setVisible(false);
        add(searchPanel);

        addPanel = new AddFrame(dayPlanner);
        addPanel.setBackground(Color.WHITE);
        addPanel.setVisible(false);
        add(addPanel);

        standingPanel = new StandingFrame(dayPlanner);
        standingPanel.setBackground(Color.WHITE);
        standingPanel.setVisible(true);
        add(standingPanel);
        
        JMenu colorMenu = new JMenu("Functions");

        JMenuItem searchChoice = new JMenuItem("Search");
        searchChoice.addActionListener(new searchListener( ));
        colorMenu.add(searchChoice);

        JMenuItem addChoice = new JMenuItem("Add");
        addChoice.addActionListener(new addListener( ));
        colorMenu.add(addChoice);

        JMenuItem standingChoice = new JMenuItem("Home");
        standingChoice.addActionListener(new homeListener( ));
        colorMenu.add(standingChoice);

        JMenuBar bar = new JMenuBar( );
        bar.add(colorMenu);
        setJMenuBar(bar);
    }

}


