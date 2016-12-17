package com.missionse.codeathon;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Position;

public class NotePanel extends JPanel {
	public NotePanel(Balltab bt, WorldWindowGLCanvas wwd) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,2));
		
		blank1_lbl = new JLabel("");
		blank2_lbl = new JLabel("");
		blank3_lbl = new JLabel("");
		blank4_lbl = new JLabel("");
		blank5_lbl = new JLabel("");
		blank6_lbl = new JLabel("");
		blank7_lbl = new JLabel("");
		blank8_lbl = new JLabel("");
		
		image_path_lbl = new JLabel("Image Path: ");
		
		lat_lbl = new JLabel("Latitude: ");
		lon_lbl = new JLabel("Longitude: ");
		image_path_txt = new JTextField("");
		image_path_txt.setPreferredSize(new java.awt.Dimension(150, 20));
		
		lat_txt = new JTextField("");
		lon_txt = new JTextField("");
		browse_btn = new JButton("Browse");
		get_position_btn = new JToggleButton("Set Note Position");
		
		apply_btn = new JButton("Apply");
		reset_btn = new JButton("Reset");
		
		panel.add(image_path_lbl);
		panel.add(image_path_txt);	
		panel.add(blank1_lbl);
		panel.add(browse_btn);
		
		panel.add(blank2_lbl);
		panel.add(blank3_lbl);
		
		panel.add(lat_lbl);
		panel.add(lat_txt);
		
		panel.add(lon_lbl);
		panel.add(lon_txt);
		
		panel.add(blank4_lbl);
		panel.add(get_position_btn);
		
		panel.add(blank5_lbl);
		panel.add(blank6_lbl);
		
		panel.add(apply_btn);
		panel.add(reset_btn);		
		
		add(panel);
		
		wwd.getInputHandler().addMouseListener(new MouseAdapter()
        {
            public void mouseReleased(MouseEvent mouseEvent)
            {
            	if(get_position_btn.isSelected())
				{
            		Position pos = bt.getBalltabPostion();
					if(pos != null)
					{
						System.out.println("set lat lon");
						
						lat_txt.setText(String.format("%7.3f", pos.getLatitude().getDegrees()));
						lon_txt.setText(String.format("%7.3f", pos.getLongitude().getDegrees()));
						get_position_btn.setSelected(false);
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
		
		browse_btn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Create a file chooser
				final JFileChooser fc = new JFileChooser();
				
				int returnVal = fc.showOpenDialog(NotePanel.this);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            
		            //This is where a real application would open the file.
		            image_path_txt.setText(file.getPath());
		        } else {
		        	image_path_txt.setText("");
		        }
				
			}
        });
		
		apply_btn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
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
		image_path_txt.setText("");
		lat_txt.setText("");
		lon_txt.setText("");
		
	}
	
	
	JLabel blank1_lbl;
	JLabel blank2_lbl;
	JLabel blank3_lbl;
	JLabel blank4_lbl;
	JLabel blank5_lbl;
	JLabel blank6_lbl;
	JLabel blank7_lbl;
	JLabel blank8_lbl;
	
	JLabel image_path_lbl;
	JLabel lat_lbl;
	JLabel lon_lbl;
	JTextField image_path_txt;
	JTextField lat_txt;
	JTextField lon_txt;
	JButton browse_btn;
	JToggleButton get_position_btn;
	
	JButton apply_btn;
	JButton reset_btn;

}
