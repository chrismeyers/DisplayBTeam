package com.missionse.codeathon;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Position;

public class SearchResultPanel extends JPanel {
	public SearchResultPanel(LineManager lm, WorldWindowGLCanvas wwd) {
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(0,2));
		
		lat_lbl = new JLabel("Latitude: ");
		lat_lbl.setHorizontalAlignment(JTextField.RIGHT);
		lon_lbl = new JLabel("Longitude: ");
		lon_lbl.setHorizontalAlignment(JTextField.RIGHT);
		
		blank_lbl = new JLabel("");
		
		lat_txt = new JTextField();
		lon_txt = new JTextField();
		
		set_last_pos = new JToggleButton("Capture search position");
		
		panel.add(lat_lbl);
		panel.add(lat_txt);
		panel.add(lon_lbl);
		panel.add(lon_txt);
	
		panel.add(blank_lbl);		
		panel.add(set_last_pos);
		
		add(panel);
		
        wwd.getInputHandler().addMouseListener(new MouseAdapter()
        {
            public void mouseReleased(MouseEvent mouseEvent)
            {
            	if(set_last_pos.isSelected())
				{
            		Position pos = lm.getBalltabPostion();
					if(pos != null)
					{
						System.out.println("set lat lon");
						
						lat_txt.setText(String.format("%7.3f", pos.getLatitude().getDegrees()));
						lon_txt.setText(String.format("%7.3f", pos.getLongitude().getDegrees()));
						set_last_pos.setSelected(false);
					}
				}
            }

            public void mousePressed(MouseEvent mouseEvent)
            {
            }

            public void mouseClicked(MouseEvent mouseEvent)
            {
            }
        });
	}
	
	JLabel blank_lbl;
	JLabel lat_lbl;
	JLabel lon_lbl;
	JTextField lat_txt;
	JTextField lon_txt;	
	JToggleButton set_last_pos;
}
