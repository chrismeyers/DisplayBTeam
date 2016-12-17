package com.missionse.codeathon;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Position;

public class TeamPlanningPanel extends JPanel {
	private GridLayer gridLayer = null;
	private GridGenerator gridGenerator = null;
	private GroupHighlighter group_highlighter = null;
	
	
	public TeamPlanningPanel(Balltab bt, WorldWindowGLCanvas wwd, GridLayer gridLayer) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,2));
		
		group_highlighter = new GroupHighlighter(wwd, gridLayer);
		
		teams_lbl = new JLabel("Teams: ");
		teams_lbl.setHorizontalAlignment(JTextField.RIGHT);
		time_lbl = new JLabel("Time: ");
		time_lbl.setHorizontalAlignment(JTextField.RIGHT);
		blank1_lbl = new JLabel("");
		blank2_lbl = new JLabel("");
		blank3_lbl = new JLabel("");
		
		time_txt = new JTextField();
		
		team_list_cbo = new ComboBoxString();
		
		capture_grid_btn = new JToggleButton("Set Search Areas");
		apply_btn = new JButton("Apply");
		reset_btn = new JButton("Reset");
		
		panel.add(teams_lbl);
		panel.add(team_list_cbo);
		
		panel.add(time_lbl);
		panel.add(time_txt);
		
		panel.add(blank1_lbl);
		panel.add(capture_grid_btn);
		
		panel.add(blank2_lbl);
		panel.add(blank3_lbl);
		
		panel.add(apply_btn);
		panel.add(reset_btn);
		
		add(panel);
		
		capture_grid_btn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!capture_grid_btn.isSelected())
				{
            		group_highlighter.setGroupNumber(-1);
				}
			}
        });

		apply_btn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
        });
		
		reset_btn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
			}
        });
	}
	
	private void reset() {
		time_txt.setText("");
		team_list_cbo.removeAllItems();;
	}
	
	public class ComboBoxString extends JComboBox<String> implements ActionListener {
		public ComboBoxString() {
			setEditable(true);
	        addActionListener(this);    	
	    }

	    public void actionPerformed(ActionEvent e) {
	        String entered_text = (String)getSelectedItem();
	        
	        group_highlighter.setGroupNumber(getSelectedIndex());
	        
	        System.out.println("String entered: " + entered_text);
	        
	        for (int i=0; i < getItemCount(); i++)
	        {
	        	if (entered_text.equals(getItemAt(i)) || entered_text.equals("")) {
	        		System.out.println("String already in box: " + entered_text);
	        		//Item already in list.
	        		return;
	        	}
	        }
	        
	        System.out.println("Adding a new string:" + entered_text);
	        addItem(entered_text);
	    }
	    
	}
	
	JLabel blank1_lbl;
	JLabel blank2_lbl;
	JLabel blank3_lbl;
	
	JLabel teams_lbl;
	JLabel time_lbl;
	JTextField time_txt;
	JToggleButton capture_grid_btn;
	ComboBoxString team_list_cbo;
	JButton apply_btn;
	JButton reset_btn;
}
