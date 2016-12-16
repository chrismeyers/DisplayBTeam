package com.missionse.codeathon;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.event.SelectListener;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.LayerList;
import gov.nasa.worldwind.layers.ViewControlsLayer;
import gov.nasa.worldwind.layers.ViewControlsSelectListener;

import java.awt.Dimension;

import javax.swing.*;

public class WorldwindMain extends JFrame {
	
	WorldWindowGLCanvas wwd = null;
	LayerManager layerManager = null;
	JPanel gui = null;
	GridLayer gridLayer = null;
	
    public WorldwindMain()
    {
    	this.gui = new JPanel();
    	this.gui.setPreferredSize(new Dimension(400,1000));
        this.wwd = new WorldWindowGLCanvas();
        wwd.setPreferredSize(new java.awt.Dimension(1000, 1000));
        this.getContentPane().add(this.gui, java.awt.BorderLayout.WEST);
        this.getContentPane().add(wwd, java.awt.BorderLayout.CENTER);
        wwd.setModel(new BasicModel());
        
        this.layerManager = new LayerManager(this.wwd.getModel());
        this.gridLayer = new GridLayer(this.getLayerManager());
        
        ViewControlsLayer viewControlsLayer = new ViewControlsLayer();
        layerManager.addLayer(viewControlsLayer);
        this.wwd.addSelectListener(new ViewControlsSelectListener(this.wwd, viewControlsLayer));
    }
    
    public LayerManager getLayerManager()
    {
      return this.layerManager;
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
