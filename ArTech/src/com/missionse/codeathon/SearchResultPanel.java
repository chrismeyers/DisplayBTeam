package com.missionse.codeathon;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Position;

public class SearchResultPanel extends JPanel {
	GridLayer gridLayer = null;
	GridHighlighter gridHighlighter = null;
	public SearchResultPanel(Balltab bt, WorldWindowGLCanvas wwd, GridLayer gridLayer) {
		this.gridLayer = gridLayer;
		this.gridHighlighter = new GridHighlighter(wwd, gridLayer);
		
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(0,2));
		
		lat_lbl = new JLabel("Latitude: ");
		lat_lbl.setHorizontalAlignment(JTextField.RIGHT);
		lon_lbl = new JLabel("Longitude: ");
		lon_lbl.setHorizontalAlignment(JTextField.RIGHT);
		
		blank_lbl = new JLabel("");
		blank1_lbl = new JLabel("");
		blank2_lbl = new JLabel("");
		
		lat_txt = new JTextField();
		lon_txt = new JTextField();
		
		set_last_pos = new JToggleButton("Capture search position");
		save_btn = new JButton("Save");
		reset_btn = new JButton("Reset");
		
		panel.add(lat_lbl);
		panel.add(lat_txt);
		panel.add(lon_lbl);
		panel.add(lon_txt);
	
		panel.add(blank_lbl);
		panel.add(set_last_pos);
		
		panel.add(blank1_lbl);	
		panel.add(blank2_lbl);	
		
		panel.add(save_btn);
		panel.add(reset_btn);
		
		add(panel);
		
        wwd.getInputHandler().addMouseListener(new MouseAdapter()
        {
            public void mouseReleased(MouseEvent mouseEvent)
            {
            	if(set_last_pos.isSelected())
				{
            		Position pos = bt.getBalltabPostion();
					if(pos != null)
					{
						System.out.println("set lat lon");
						
						lat_txt.setText(String.format("%7.3f", pos.getLatitude().getDegrees()));
						lon_txt.setText(String.format("%7.3f", pos.getLongitude().getDegrees()));
						set_last_pos.setSelected(false);
						
						if (gridLayer.getGridGenerator() != null)
						{
							
						}
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
        
        reset_btn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				resetTab();
			}
        	
        });
	}
	
	private void resetTab() {
		lat_txt.setText("");
		lon_txt.setText("");
		set_last_pos.setSelected(false);
	}
	
	JLabel blank_lbl;
	JLabel blank1_lbl;
	JLabel blank2_lbl;
	
	JLabel lat_lbl;
	JLabel lon_lbl;
	JTextField lat_txt;
	JTextField lon_txt;	
	JToggleButton set_last_pos;
	JButton save_btn;
	JButton reset_btn;
}
