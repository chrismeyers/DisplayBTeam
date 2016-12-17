package com.missionse.codeathon;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import gov.nasa.worldwind.awt.WorldWindowGLCanvas;

public class GuiPanelManager extends JPanel {
	JTabbedPane tabbedPanel = null;
	LineManager lm = null;
	SearchParams sp = null;
	
	public GuiPanelManager(int w, int h, WorldWindowGLCanvas wwd) {		
		tabbedPanel = new JTabbedPane();
		tabbedPanel.setPreferredSize(new Dimension(w-10,h-15));
		
    	this.add(tabbedPanel);
    	
    	lm = new LineManager(wwd, null, null);    	
    	tabbedPanel.addTab("Trails", null, new LinePanel(wwd, lm),
                "Important Paths");
    	
    	sp = new SearchParams(lm, wwd);
    	tabbedPanel.addTab("Parameters", null, sp,
                "Important Paths");
	}

}
