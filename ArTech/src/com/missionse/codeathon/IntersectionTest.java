package com.missionse.codeathon;

import java.awt.Color;
import java.util.ArrayList;

import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.globes.Globe;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Polyline;
import gov.nasa.worldwind.render.ShapeAttributes;

public class IntersectionTest {
	
	private GridGenerator gg = null;
	private Polyline polyline = null;
	private Globe globe = null;
	private GridLayer gridLayer = null;
	
	public IntersectionTest(GridGenerator gg, Globe globe, GridLayer gridLayer)
	{
		this.gg = gg;
		this.polyline = new Polyline();
		this.globe = globe;
		this.gridLayer = gridLayer;
		
		//38, -105
		Position l1 = new Position(new LatLon(Angle.fromDegrees(37.732), Angle.fromDegrees(-104.859)), 5);	
		Position l2 = new Position(new LatLon(Angle.fromDegrees(37.7511), Angle.fromDegrees(-104.7342)), 5);
		Position l3 = new Position(new LatLon(Angle.fromDegrees(37.7599), Angle.fromDegrees(-104.7798)), 5);
		
		ArrayList<Position> lls = new ArrayList<Position>();
		lls.add(l1);
		lls.add(l2);
		lls.add(l3);
		
		this.polyline.setPositions(lls);
        this.polyline.setColor(new Color(0f, 1f, 1f));
        this.polyline.setFilled(true);
        this.polyline.setLineWidth(10);		
		this.gridLayer.addShape(this.polyline);
		
		IntersectionFinder f = new IntersectionFinder(gg.getGridSquares(), this.polyline, this.globe);
	}

}
