package com.missionse.codeathon;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Position;

public class PlaybackPanel extends JPanel {
	public PlaybackPanel(WorldWindowGLCanvas wwd) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,3));
		
		blank1_lbl = new JLabel("");
		blank2_lbl = new JLabel("");
		blank3_lbl = new JLabel("");
		blank4_lbl = new JLabel("");
		blank5_lbl = new JLabel("");
		blank6_lbl = new JLabel("");
		blank7_lbl = new JLabel("");
		blank8_lbl = new JLabel("");
		
		play_speed_lbl = new JLabel("Planning Speed:");
		two_spd_btn = new JButton("2x");
		four_spd_btn = new JButton("4x");
		eight_spd_btn = new JButton("8x");
		start_btn = new JButton("Start");
		pause_btn = new JButton("Pause");
		stop_btn = new JButton("Stop");
		
		panel.add(play_speed_lbl);
		panel.add(blank1_lbl);
		panel.add(blank2_lbl);		
		
		panel.add(two_spd_btn);
		panel.add(four_spd_btn);
		panel.add(eight_spd_btn);
		
		panel.add(blank3_lbl);
		panel.add(blank4_lbl);	
		panel.add(blank5_lbl);
		
		panel.add(stop_btn);
		panel.add(pause_btn);
		panel.add(start_btn);
		
		add(panel);
		
		wwd.getInputHandler().addMouseListener(new MouseAdapter()
        {
            public void mouseReleased(MouseEvent mouseEvent)
            {
            }

            public void mousePressed(MouseEvent mouseEvent)
            {
            }

            public void mouseClicked(MouseEvent mouseEvent)
            {
            }
        });
        
		two_spd_btn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
        });
		
		four_spd_btn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
        });
		
		eight_spd_btn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
        });
		
		stop_btn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
        });
		
		pause_btn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
        });
		
		start_btn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
        });
	}
	
	
	JLabel blank1_lbl;
	JLabel blank2_lbl;
	JLabel blank3_lbl;
	JLabel blank4_lbl;
	JLabel blank5_lbl;
	JLabel blank6_lbl;
	JLabel blank7_lbl;
	JLabel blank8_lbl;
	
	JLabel play_speed_lbl;
	JButton two_spd_btn;
	JButton four_spd_btn;
	JButton eight_spd_btn;
	JButton start_btn;
	JButton pause_btn;
	JButton stop_btn;
}
