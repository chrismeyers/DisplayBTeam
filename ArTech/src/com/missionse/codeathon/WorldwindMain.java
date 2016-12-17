package com.missionse.codeathon;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.ViewControlsLayer;
import gov.nasa.worldwind.layers.ViewControlsSelectListener;
import gov.nasa.worldwind.util.StatusBar;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

public class WorldwindMain extends JFrame {
	
	WorldWindowGLCanvas wwd = null;
	LayerManager layerManager = null;
	JPanel gui = null;
	GridLayer gridLayer = null;
	GridGenerator gridGenerator = null;
	GridHighlighter gridHighlighter = null;
	GuiPanelManager gui_panel_manager = null;
	
	Annotations anno = null;
	
    public WorldwindMain()
    {
    	int h = 1000;
    	int w = 400;
    	
    	gui = new JPanel();
    	gui.setPreferredSize(new Dimension(w,h));
    	
        wwd = new WorldWindowGLCanvas();
        wwd.setPreferredSize(new java.awt.Dimension(1000, 1000));
        this.getContentPane().add(gui, java.awt.BorderLayout.WEST);
        this.getContentPane().add(wwd, java.awt.BorderLayout.CENTER);
        wwd.setModel(new BasicModel());
        
        layerManager = new LayerManager(wwd.getModel());
        gridLayer = new GridLayer(getLayerManager());
        
        anno = new Annotations(wwd);
        
    	gui_panel_manager = new GuiPanelManager(w, h, wwd, this.gridLayer);
    	gui.add(gui_panel_manager);
        
        ViewControlsLayer viewControlsLayer = new ViewControlsLayer();
        layerManager.addLayer(viewControlsLayer);
        wwd.addSelectListener(new ViewControlsSelectListener(wwd, viewControlsLayer));
        
        wwd.getView().setEyePosition(new Position(new LatLon(Angle.fromDegrees(64.2008), Angle.fromDegrees(-149.4937)),
        		200000));
        
        StatusBar statusBar = new StatusBar();
        statusBar.setEventSource(wwd);
        add(statusBar, BorderLayout.SOUTH);
    }
    
    public LayerManager getLayerManager()
    {
      return layerManager;
    }
    

    public static void main(String[] args)
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {            	
                JFrame frame = new WorldwindMain();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

}
