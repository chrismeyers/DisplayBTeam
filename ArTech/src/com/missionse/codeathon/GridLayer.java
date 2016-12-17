package com.missionse.codeathon;

import java.util.ArrayList;

import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.layers.MarkerLayer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.SurfaceSquare;
import gov.nasa.worldwind.render.markers.Marker;

public class GridLayer {
	
	private RenderableLayer gridLayer = null;
	private LayerManager layerManager = null;
	private GridGenerator gridGenerator = null;
	private MarkerLayer markerLayer = null;
	
	public GridLayer(LayerManager layerManager)
	{
		this.layerManager = layerManager;
		this.gridLayer = new RenderableLayer();
		this.markerLayer = new MarkerLayer();
		
		this.layerManager.addLayer(this.markerLayer);;
		this.layerManager.addLayer(this.gridLayer);
	}
	
	public void addShape(Renderable shape)
	{
		this.gridLayer.addRenderable(shape);
	}
	
	public void addMarker(ArrayList<Marker> marker)
	{
		this.markerLayer.setMarkers(marker);
	}
	
	public void setGridGenerator(GridGenerator gridGenerator)
	{
		this.gridGenerator = gridGenerator;
	}
	
	public GridGenerator getGridGenerator()
	{
		return this.gridGenerator;
	}
	
	public void clearGrid()
	{
		this.gridLayer.removeAllRenderables();
	}

}
