package com.missionse.codeathon;

import java.util.ArrayList;
import java.util.Vector;

import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.globes.Globe;

public class GridGenerator {

	ArrayList<GridSquare> grid = null;
	Position basePosition = null;
	Globe globe = null;
	GridLayer gridLayer = null;

	public GridGenerator(Position position, Globe globe, GridLayer gridLayer) {
		this.gridLayer = gridLayer;
		this.basePosition = position;
		this.globe = globe;
		this.grid = new ArrayList<GridSquare>();

		double distanceRadians = ((180 * (32 * 1000) / Math.PI) / globe.getRadius());
		LatLon westCentralPoint = LatLon.greatCircleEndPosition(this.basePosition, Angle.fromDegrees(270),
				Angle.fromDegrees(distanceRadians));
		
		LatLon northwestMostPoint = LatLon.greatCircleEndPosition(new Position(westCentralPoint, 0),
				Angle.fromDegrees(0), Angle.fromDegrees(distanceRadians));
			
		double distanceOneUnit = ((180 * 1000) / Math.PI) /globe.getRadius();
		
		LatLon row = northwestMostPoint;
		
		for (int j = 0; j <= 63; j++)
		{
		  for (int i = 0; i <= 63; i++)
		  {
			double dist = (180*i*1000) / Math.PI / globe.getRadius();
			LatLon point = LatLon.greatCircleEndPosition(row, Angle.fromDegrees(90),
					Angle.fromDegrees(dist));
			GridSquare square = new GridSquare(point, i, j);
			gridLayer.addShape(square.getSquare());		
			grid.add(square);
			
			if (i == 1)
			{
				square.update(1);
			}
		  }
		  
		  row = LatLon.greatCircleEndPosition(new Position(row, 0), 
				  Angle.fromDegrees(180), 
				  Angle.fromDegrees(distanceOneUnit));	
		  
		}
	}
	
	public ArrayList<GridSquare> getGridSquares()
	{
		return this.grid;
	}
	
}
