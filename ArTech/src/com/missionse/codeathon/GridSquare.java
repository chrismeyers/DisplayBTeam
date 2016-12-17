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
    boolean isPossible = true;
    boolean HasPath = false;
    double probability = 0.0;
    public int numLines = 0;

public boolean isIsPossible() {
    return isPossible;
}

public void setIsPossible(boolean isPossible) {
    this.isPossible = isPossible;
}

public boolean isHasPath() {
    return HasPath;
}


public double getProbablity() {
    return probability;
}

public void setProbablity(double probablity) {
    this.probability = probablity;
    
	ShapeAttributes attribute = new BasicShapeAttributes();

    
    this.surfaceSquare.setVisible(true);
    if (this.probability == 0 )
    {
    	this.surfaceSquare.setVisible(false);
    }
    else if (this.probability < .10)
    {
    	attribute.setInteriorMaterial(new Material(new Color(0, 255, 0)));
        this.surfaceSquare.setAttributes(attribute);	
    }
    else if (this.probability < .20)
    {
    	attribute.setInteriorMaterial(new Material(new Color(46,255, 0)));
        this.surfaceSquare.setAttributes(attribute);	    	
    }
    else if (this.probability < .30)
    {
    	attribute.setInteriorMaterial(new Material(new Color(92,255,0)));
        this.surfaceSquare.setAttributes(attribute);	  	
    }
    else if (this.probability < .40)
    {
    	attribute.setInteriorMaterial(new Material(new Color(139,255,0)));
        this.surfaceSquare.setAttributes(attribute);	  	
    }
    else if (this.probability < .50)
    {
    	attribute.setInteriorMaterial(new Material(new Color(231,255,0)));
        this.surfaceSquare.setAttributes(attribute);	 	
    }
    else if (this.probability < .60)
    {
    	attribute.setInteriorMaterial(new Material(new Color(254,231,0)));
        this.surfaceSquare.setAttributes(attribute);	
    }
    else if (this.probability < .70)
    {
    	attribute.setInteriorMaterial(new Material(new Color(253,185,0)));
        this.surfaceSquare.setAttributes(attribute);	  	
    }
    else if (this.probability < .80)
    {
    	attribute.setInteriorMaterial(new Material(new Color(252,139,0)));
        this.surfaceSquare.setAttributes(attribute);	 	
    }
    else if (this.probability < .90)
    {
    	attribute.setInteriorMaterial(new Material(new Color(250,46,0)));
        this.surfaceSquare.setAttributes(attribute);	 	
    }
    else if (this.probability <= 1)
    {
    	attribute.setInteriorMaterial(new Material(new Color(250,0,0)));
        this.surfaceSquare.setAttributes(attribute);	   	
    }
    
}

public int getNumLines() {
    return this.numLines;
}

public void setNumLines(int numLines) {
    this.numLines = numLines;
}
	
	
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
	
	public void setHasPath(boolean state)
	{
		this.HasPath = state;
		if (this.HasPath)
		{
		  ShapeAttributes attribute = new BasicShapeAttributes();
		  attribute.setInteriorMaterial(new Material(new Color(0f, 1f, 0f)));
		  this.surfaceSquare.setAttributes(attribute);
		}
	}
	
}
