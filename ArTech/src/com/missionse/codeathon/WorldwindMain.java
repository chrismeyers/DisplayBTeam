package com.missionse.codeathon;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.IconLayer;
import gov.nasa.worldwind.layers.RenderableLayer;
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
        
        anno = new Annotations(wwd);
        
    	gui_panel_manager = new GuiPanelManager(w, h, wwd);
    	gui.add(gui_panel_manager);
        
        layerManager = new LayerManager(wwd.getModel());
        gridLayer = new GridLayer(getLayerManager());
        
        ViewControlsLayer viewControlsLayer = new ViewControlsLayer();
        layerManager.addLayer(viewControlsLayer);
        wwd.addSelectListener(new ViewControlsSelectListener(wwd, viewControlsLayer));
        
        wwd.getView().setEyePosition(new Position(new LatLon(Angle.fromDegrees(38), Angle.fromDegrees(-105)),
        		200000));
        
        gridGenerator = new GridGenerator(
        		new Position(new LatLon(Angle.fromDegrees(38), Angle.fromDegrees(-105)), 0),
        		wwd.getModel().getGlobe(),
        		gridLayer);
        
        StatusBar statusBar = new StatusBar();
        statusBar.setEventSource(wwd);
        this.add(statusBar, BorderLayout.SOUTH);
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