package com.missionse.codeathon;

import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.SurfaceSquare;

public class GridLayer {
	
	private RenderableLayer gridLayer;
	private LayerManager layerManager;
	
	public GridLayer(LayerManager layerManager)
	{
		this.layerManager = layerManager;
		this.gridLayer = new RenderableLayer();
		this.gridLayer.addRenderable(createSquare());
		layerManager.addLayer(this.gridLayer);
	}
	
	public void addShape(Renderable shape)
	{
		this.gridLayer.addRenderable(shape);
	}
	
	public SurfaceSquare createSquare()
	{
        LatLon position = new LatLon(Angle.fromDegrees(38), Angle.fromDegrees(-105));
        return new SurfaceSquare(position, 100e3);
	}
	

}
