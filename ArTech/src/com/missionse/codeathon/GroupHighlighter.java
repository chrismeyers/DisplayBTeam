package com.missionse.codeathon;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.event.SelectListener;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.util.WWMath;

public class GroupHighlighter {
	private WorldWindowGLCanvas wwd = null; 
	private GridLayer gridLayer = null;
	private int selected_team_value = -1;
	private ArrayList<Color> team_colors = null;
	
	public GroupHighlighter(WorldWindowGLCanvas wwd, GridLayer gridLayer)
	{
		this.wwd = wwd;
		this.gridLayer = gridLayer;
		
		team_colors = new ArrayList<Color>(
				Arrays.asList(Color.WHITE, Color.PINK, Color.CYAN, Color.YELLOW, Color.BLUE)
		);
		
		setupSelection();
	}
	
	public void setGroupNumber(int value) {
		if (value >= 0) {
			selected_team_value = (value % team_colors.size());
		}
		else {
			selected_team_value = -1;
		}
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
                	
                	if (selected_team_value == -1) {
                		return;
                	}

                	ArrayList<GridSquare> grid = gg.getGridSquares();
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
        				 g.setTeamColor(team_colors.get(selected_team_value));
        				 g.displayTeamColor();
        			 }
        		  }
                }
            }
        });
    }	
	
	
}
