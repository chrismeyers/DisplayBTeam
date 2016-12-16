package com.missionse.codeathon;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchParams extends JPanel {
	public SearchParams() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(0,2));
		
		time_lbl = new JLabel("Time of last sighting: ");
		time_lbl.setHorizontalAlignment(JTextField.RIGHT);
		avg_spd_lbl = new JLabel("Average speed (mph): ");
		avg_spd_lbl.setHorizontalAlignment(JTextField.RIGHT);
		lat_lbl = new JLabel("Latitude: ");
		lat_lbl.setHorizontalAlignment(JTextField.RIGHT);
		lon_lbl = new JLabel("Longitude: ");
		lon_lbl.setHorizontalAlignment(JTextField.RIGHT);
		
		blank_lbl = new JLabel("");
		
		time_txt = new JTextField("Time");
		avg_spd_txt = new JTextField("3");
		
		lat_txt = new JTextField();
		lon_txt = new JTextField();
		
		set_last_pos = new JButton("Capture last position");
		
		panel.add(time_lbl);
		panel.add(time_txt);		
		panel.add(avg_spd_lbl);		
		panel.add(avg_spd_txt);
		
		panel.add(lat_lbl);
		panel.add(lat_txt);
		panel.add(lon_lbl);
		panel.add(lon_txt);
	
		panel.add(blank_lbl);		
		panel.add(set_last_pos);
		
		add(panel);
	}
	
	JLabel blank_lbl;
	JLabel time_lbl;
	JLabel avg_spd_lbl;
	JLabel lat_lbl;
	JLabel lon_lbl;
	JTextField time_txt;
	JTextField avg_spd_txt;
	JTextField lat_txt;
	JTextField lon_txt;	
	JButton set_last_pos;
}
