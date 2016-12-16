package com.missionse.codeathon;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import gov.nasa.worldwind.awt.WorldWindowGLCanvas;

public class GuiPanelManager extends JPanel {
	JTabbedPane tabbedPanel = null;
	LineManager lm = null;
	
	public GuiPanelManager(WorldWindowGLCanvas wwd) {
		tabbedPanel = new JTabbedPane();
    	this.add(tabbedPanel, java.awt.BorderLayout.WEST);
    	
    	lm = new LineManager(wwd, null, null);    	
    	tabbedPanel.addTab("Paths", null, new LinePanel(wwd, lm),
                "Important Paths");
	}
	
	protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

}
