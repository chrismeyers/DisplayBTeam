package com.missionse.codeathon;

import java.util.ArrayList;

import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Line;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.globes.Globe;
import gov.nasa.worldwind.render.Polyline;
import gov.nasa.worldwind.util.WWMath;

public class IntersectionFinder {
	
	private ArrayList<GridSquare> grid = null;
	@SuppressWarnings("deprecation")
	private Polyline polyline = null;
	private Globe globe = null;
	
	public IntersectionFinder(ArrayList<GridSquare> grid,
			Polyline polyline, Globe globe){
		this.grid = grid;
		this.polyline = polyline;
		this.globe = globe;
		
		calculate();		
	}

	public void calculate()
	{
		Iterable<Position> linePositions = this.polyline.getPositions();
		//System.out.println(linePositions);
		for (Position pos : linePositions)
		{
		  for (GridSquare g : grid)
		  {
			 // System.out.println(g.getSquare().getLocations(this.globe));
			g.setHasPath(WWMath.isLocationInside(pos, g.getSquare().getLocations(this.globe)));
			
		  }
		}
		
		Position prev = null;
		
		for (Position pos : linePositions)
		{
			if (prev != null)
			{
				Angle angle = LatLon.greatCircleAzimuth(prev,  pos);
				double distance = LatLon.ellipsoidalDistance(prev, pos, 
						this.globe.getEquatorialRadius(), this.globe.getPolarRadius());
				//System.out.println(angle);
				//System.out.println(distance);
				double unit = distance/64;
				//System.out.println(unit);
				
				for (int i = 0; i < 64; i++)
				{
					double myDistance = (180*i*unit) / Math.PI / this.globe.getRadius();
					//System.out.println(Angle.fromDegrees(unit*i*180/Math.PI));
					LatLon point = LatLon.greatCircleEndPosition(prev, Angle.fromDegrees(angle.getDegrees()),
							Angle.fromDegrees(myDistance));
					//System.out.println(point);
					for (GridSquare g : grid)
					{
						if (!g.hasPath())
						{
						  g.setHasPath(WWMath.isLocationInside(point, g.getSquare().getLocations(this.globe)));
						}
					}
				}
				
			}
			
			prev = pos;
		}
	}
}
