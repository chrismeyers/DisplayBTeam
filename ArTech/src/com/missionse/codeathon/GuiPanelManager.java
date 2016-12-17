package com.missionse.codeathon;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import gov.nasa.worldwind.awt.WorldWindowGLCanvas;

public class GuiPanelManager extends JPanel {
	JTabbedPane tabbedPanel = null;
	LineManager lm = null;
	SearchParamsPanel sp = null;
	
	public GuiPanelManager(int w, int h, WorldWindowGLCanvas wwd) {		
		tabbedPanel = new JTabbedPane();
		tabbedPanel.setPreferredSize(new Dimension(w-10,h-15));
		
    	this.add(tabbedPanel);
    	
    	Balltab bt = new Balltab(wwd);
    	
    	lm = new LineManager(wwd, null, null, bt);    	
    	tabbedPanel.addTab("Trails", null, new LinePanel(wwd, lm),
                "Important Paths");
    	
    	sp = new SearchParamsPanel(lm, wwd, bt);
    	tabbedPanel.addTab("Parameters", null, sp,
                "Search Parameters");
    	
    	//NotePanel np = new NotePanel();
    	//tabbedPanel.addTab("Notes", null, np,
        //        "Search Notes");
    	
    	SearchResultPanel sr = new SearchResultPanel(bt, wwd);
    	tabbedPanel.addTab("Search Entry", null, sr,
                "Search Result Entry");
    	
    	PlaybackPanel pp = new PlaybackPanel(wwd);
    	tabbedPanel.addTab("Planning Control", null, pp,
                "Planning Controls");
    	
    	TeamPlanningPanel tp = new TeamPlanningPanel(wwd);
    	tabbedPanel.addTab("Team Planning", null, tp,
                "Team Planning");
	}

}
