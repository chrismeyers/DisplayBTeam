package com.missionse.codeathon;

import java.awt.Color;

import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfaceSquare;

public class GridSquare {
	
	SurfaceSquare surfaceSquare = null;
	int x = 0;
	int y = 0;

	public GridSquare(LatLon position, int x, int y)
	{
		this.x = x;
		this.y = y;
		this.surfaceSquare = new SurfaceSquare(position, 1000);
	}
	
	public SurfaceSquare getSquare()
	{
		return this.surfaceSquare;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public void update(double value)
	{
		if (value > 0)
		{
			this.surfaceSquare.setVisible(true);
			
			ShapeAttributes attribute = new BasicShapeAttributes();
			attribute.setInteriorOpacity(.50);
            attribute.setInteriorMaterial(new Material(new Color(1f, 0f, 0f)));
            this.surfaceSquare.setAttributes(attribute);
		}
	}
	
}
