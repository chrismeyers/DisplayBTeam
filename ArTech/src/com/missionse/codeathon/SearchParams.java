package com.missionse.codeathon;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchParams extends JPanel {
	public SearchParams() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(3,2));
		
		time_lbl = new JLabel("Time of last sighting: ");
		avg_spd_lbl = new JLabel("Average speed (mph): ");
		blank_lbl = new JLabel("");
		
		time_text = new JTextField("Time");
		avg_spd = new JTextField("3");
		
		set_last_pos = new JButton("Capture last known position");
		
		panel.add(time_lbl);
		time_lbl.setHorizontalAlignment(JTextField.RIGHT);
		panel.add(time_text);
		
		panel.add(avg_spd_lbl);
		avg_spd_lbl.setHorizontalAlignment(JTextField.RIGHT);
		panel.add(avg_spd);
		
		panel.add(blank_lbl);		
		panel.add(set_last_pos);
		
		add(panel);
	}
	
	JLabel blank_lbl;
	JLabel time_lbl;
	JLabel avg_spd_lbl;
	JTextField time_text;
	JTextField avg_spd;
	JButton set_last_pos;
}
