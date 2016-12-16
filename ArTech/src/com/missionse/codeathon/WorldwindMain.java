package com.missionse.codeathon;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.event.SelectListener;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.LayerList;
import gov.nasa.worldwind.layers.ViewControlsLayer;
import gov.nasa.worldwind.layers.ViewControlsSelectListener;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

public class WorldwindMain extends JFrame {
	
	WorldWindowGLCanvas wwd = null;
	LayerManager layerManager = null;
	JPanel gui = null;
	GridLayer gridLayer = null;
	
	GuiPanelManager gui_panel_manager = null;
	
    public WorldwindMain()
    {
    	gui = new JPanel();
    	gui.setPreferredSize(new Dimension(400,1000));
    	
        wwd = new WorldWindowGLCanvas();
        wwd.setPreferredSize(new java.awt.Dimension(1000, 1000));
        this.getContentPane().add(gui, java.awt.BorderLayout.WEST);
        this.getContentPane().add(wwd, java.awt.BorderLayout.CENTER);
        wwd.setModel(new BasicModel());
        
    	gui_panel_manager = new GuiPanelManager(wwd);
    	gui.add(gui_panel_manager, java.awt.BorderLayout.WEST);
        
        layerManager = new LayerManager(wwd.getModel());
        gridLayer = new GridLayer(getLayerManager());
        
        ViewControlsLayer viewControlsLayer = new ViewControlsLayer();
        layerManager.addLayer(viewControlsLayer);
        wwd.addSelectListener(new ViewControlsSelectListener(wwd, viewControlsLayer));
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
