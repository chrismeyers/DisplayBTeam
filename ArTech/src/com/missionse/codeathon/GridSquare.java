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
	boolean pathIntersection = false;
	double probability_value = 0.0;

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
	
	public boolean hasPathIntersection()
	{
		return this.pathIntersection;
	}
	
	public void setHighlight()
	{
		ShapeAttributes attribute = new BasicShapeAttributes();
        attribute.setInteriorMaterial(new Material(new Color(0f, 0f, 1f)));
        this.surfaceSquare.setAttributes(attribute);		
	}
	
	public void clearHighlight()
	{
		ShapeAttributes attribute = new BasicShapeAttributes();
		this.surfaceSquare.setVisible(true);
		attribute.setInteriorOpacity(.50);
        attribute.setInteriorMaterial(new Material(new Color(1f, 0f, 0f)));
        this.surfaceSquare.setAttributes(attribute);		
	}
	
	public void setPathIntersection(boolean state)
	{
		this.pathIntersection = state;
		if (this.pathIntersection)
		{
		ShapeAttributes attribute = new BasicShapeAttributes();
		attribute.setInteriorMaterial(new Material(new Color(0f, 1f, 0f)));
		this.surfaceSquare.setAttributes(attribute);
		}
	}
	
	public void update(double value)
	{
		this.probability_value = value;
		
		ShapeAttributes attribute = new BasicShapeAttributes();

		if (value == 0)
		{
			attribute.setInteriorOpacity(0);
			this.surfaceSquare.setAttributes(attribute);
		}
		
		if (value > 0)
		{
			this.surfaceSquare.setVisible(true);
			attribute.setInteriorOpacity(.50);
            attribute.setInteriorMaterial(new Material(new Color(1f, 0f, 0f)));
            this.surfaceSquare.setAttributes(attribute);
		}
	}
	
}
