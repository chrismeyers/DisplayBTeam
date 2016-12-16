package com.missionse.codeathon;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import gov.nasa.worldwind.awt.WorldWindowGLCanvas;

public class GuiPanelManager extends JPanel {
	JTabbedPane tabbedPanel = null;
	LineManager lm = null;
	
	public GuiPanelManager(WorldWindowGLCanvas wwd) {
		this.setPreferredSize(new Dimension(400,1000));
		
		tabbedPanel = new JTabbedPane();
		tabbedPanel.setPreferredSize(new Dimension(390,985));
		
    	this.add(tabbedPanel);
    	
    	lm = new LineManager(wwd, null, null);    	
    	tabbedPanel.addTab("Paths", null, new LinePanel(wwd, lm),
                "Important Paths");
	}

}
