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
	private GridGenerator gg = null;
	
	public GridHighlighter(WorldWindowGLCanvas wwd, GridGenerator gg)
	{
		this.wwd = wwd;
		this.gg = gg;
		setupSelection();
	}

	private void setupSelection()
    {
        this.wwd.addSelectListener(new SelectListener()
        {
        	private WorldWindowGLCanvas privateWWD = wwd;
        	private GridGenerator privateGG = gg;
        	
            public void selected(SelectEvent event)
            {
                if (event.getEventAction().equals(SelectEvent.LEFT_CLICK))
                {
                	ArrayList<GridSquare> grid = privateGG.getGridSquares();
                	for (GridSquare g : grid)
                	{
                		g.clearHighlight();
                	}
                	
                	Position pos = privateWWD.getCurrentPosition();
                	
                	if(pos == null)
                	{
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
