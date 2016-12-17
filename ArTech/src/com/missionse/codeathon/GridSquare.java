package com.missionse.codeathon;

import java.awt.Color;

import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfaceSquare;

public class GridSquare {

	SurfaceSquare surfaceSquare = null;
	ShapeAttributes defaultAttribute = null;
	ShapeAttributes probabilityAttribute = null;
	ShapeAttributes teamAttribute = null;
	String activeDisplay = null;

	int x = 0;
	int y = 0;
	boolean reachable = true;
	boolean hasPath = false;
	double probability = 0.0;
	public int numLines = 0;

	public GridSquare(LatLon position, int x, int y) {
		this.x = x;
		this.y = y;
		this.surfaceSquare = new SurfaceSquare(position, 1000);
		this.defaultAttribute = surfaceSquare.getAttributes();
		if (this.defaultAttribute == null)
		{
			this.defaultAttribute = new BasicShapeAttributes();
		}
		this.defaultAttribute.setInteriorOpacity(.25);
		this.defaultAttribute.setOutlineWidth(this.defaultAttribute.getOutlineWidth()*2);
		this.surfaceSquare.setAttributes(defaultAttribute);
		this.surfaceSquare.setVisible(true);
		activeDisplay = "Default";
		
	}
	
	public void setTeamColor(Color color)
	{
		if (this.teamAttribute == null)
		{
			this.teamAttribute = new BasicShapeAttributes();
		}
			this.teamAttribute.setInteriorMaterial(new Material (color));
			this.surfaceSquare.setAttributes(this.teamAttribute);
	}
	
	public void displayTeamColor()
	{
		this.surfaceSquare.setVisible(false);
		activeDisplay = "Team";
		if (this.teamAttribute != null)
		{
			this.surfaceSquare.setAttributes(this.teamAttribute);
			this.surfaceSquare.setVisible(true);
		}
	}
	
	public void displayProbabilityColor()
	{
		this.activeDisplay = "Probability";
		this.surfaceSquare.setVisible(false);
		if (this.probabilityAttribute != null)
		{
			this.surfaceSquare.setAttributes(this.probabilityAttribute);
			this.surfaceSquare.setVisible(true);
		}
	}

	public boolean isReachable() {
		return reachable;
	}

	public void setReachable(boolean isPossible) {
		this.reachable = isPossible;
	}

	public boolean hasPath() {
		return hasPath;
	}

	public double getProbablity() {
		return probability;
	}

	public void setProbablity(double probablity) {
		this.probability = probablity;
		
		if (null == this.probabilityAttribute)
		{
           this.probabilityAttribute = new BasicShapeAttributes();
		}

		this.surfaceSquare.setVisible(true);
		if (this.probability == 0) {
			this.surfaceSquare.setVisible(false);
		} else if (this.probability < .10) {
			probabilityAttribute.setInteriorMaterial(new Material(new Color(0, 255, 0)));
			this.surfaceSquare.setAttributes(probabilityAttribute);
		} else if (this.probability < .20) {
			probabilityAttribute.setInteriorMaterial(new Material(new Color(46, 255, 0)));
			this.surfaceSquare.setAttributes(probabilityAttribute);
		} else if (this.probability < .30) {
			probabilityAttribute.setInteriorMaterial(new Material(new Color(92, 255, 0)));
			this.surfaceSquare.setAttributes(probabilityAttribute);
		} else if (this.probability < .40) {
			probabilityAttribute.setInteriorMaterial(new Material(new Color(139, 255, 0)));
			this.surfaceSquare.setAttributes(probabilityAttribute);
		} else if (this.probability < .50) {
			probabilityAttribute.setInteriorMaterial(new Material(new Color(231, 255, 0)));
			this.surfaceSquare.setAttributes(probabilityAttribute);
		} else if (this.probability < .60) {
			probabilityAttribute.setInteriorMaterial(new Material(new Color(254, 231, 0)));
			this.surfaceSquare.setAttributes(probabilityAttribute);
		} else if (this.probability < .70) {
			probabilityAttribute.setInteriorMaterial(new Material(new Color(253, 185, 0)));
			this.surfaceSquare.setAttributes(probabilityAttribute);
		} else if (this.probability < .80) {
			probabilityAttribute.setInteriorMaterial(new Material(new Color(252, 139, 0)));
			this.surfaceSquare.setAttributes(probabilityAttribute);
		} else if (this.probability < .90) {
			probabilityAttribute.setInteriorMaterial(new Material(new Color(250, 46, 0)));
			this.surfaceSquare.setAttributes(probabilityAttribute);
		} else if (this.probability <= 1) {
			probabilityAttribute.setInteriorMaterial(new Material(new Color(250, 0, 0)));
			this.surfaceSquare.setAttributes(probabilityAttribute);
		}

	}

	public int getNumLines() {
		return this.numLines;
	}

	public void setNumLines(int numLines) {
		this.numLines = numLines;
	}

	public SurfaceSquare getSquare() {
		return this.surfaceSquare;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setHighlight() {
		ShapeAttributes attribute = new BasicShapeAttributes();
		attribute.setInteriorMaterial(new Material(new Color(0f, 0f, 1f)));
		this.surfaceSquare.setAttributes(attribute);
	}

	public void clearHighlight() {
		if (this.activeDisplay == "Default")
		{
			this.surfaceSquare.setAttributes(defaultAttribute);
		}
		if (this.activeDisplay == "Team")
		{
			this.surfaceSquare.setAttributes(teamAttribute);
		}
		if (this.activeDisplay == "Probability")
		{
			this.surfaceSquare.setAttributes(probabilityAttribute);
		}
	}

	public void setHasPath(boolean state) {
		this.hasPath = state;
		if (this.hasPath) {
			ShapeAttributes attribute = new BasicShapeAttributes();
			attribute.setInteriorMaterial(new Material(new Color(0f, 1f, 0f)));
			this.surfaceSquare.setAttributes(attribute);
		}
	}

}
