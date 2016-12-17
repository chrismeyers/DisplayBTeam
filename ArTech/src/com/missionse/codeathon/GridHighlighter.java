package com.missionse.codeathon;

import java.util.ArrayList;

import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.event.SelectListener;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.pick.PickedObjectList;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.util.WWMath;

public class GridHighlighter {
	
	private WorldWindowGLCanvas wwd = null; 
	private GridLayer gridLayer = null;
	
	public GridHighlighter(WorldWindowGLCanvas wwd, GridLayer gridLayer)
	{
		this.wwd = wwd;
		this.gridLayer = gridLayer;
		setupSelection();
	}

	private void setupSelection()
    {
        this.wwd.addSelectListener(new SelectListener()
        {
        	private WorldWindowGLCanvas privateWWD = wwd;
        	
            public void selected(SelectEvent event)
            {
            	GridGenerator gg = gridLayer.getGridGenerator();
            	
            	if (gg == null)
            	{
            		System.out.println("No grid generator");
            		return;
            	}
            	
                if (event.getEventAction().equals(SelectEvent.LEFT_CLICK))
                {
                	System.out.println("GridHighlighter got left click ");

                	ArrayList<GridSquare> grid = gg.getGridSquares();
                	for (GridSquare g : grid)
                	{
                		g.clearHighlight();
                	}
                	
                	Position pos = privateWWD.getCurrentPosition();
                	
                	if (pos == null)
                	{
                    	System.out.println("position was null");

                		return;
                	}
                	                	
          		    for (GridSquare g : grid)
        		    {
        			 if(WWMath.isLocationInside(pos, g.getSquare().getLocations(privateWWD.getModel().getGlobe())))
        			 {
        				 g.setHighlight();
        			 }
        		  }
                }
            }
        });
    }	
}
