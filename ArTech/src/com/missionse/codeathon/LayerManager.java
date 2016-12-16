package com.missionse.codeathon;

import gov.nasa.worldwind.Model;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.LayerList;

public class LayerManager {
	
	Model model = null;
	
	public LayerManager(Model model)
	{
		this.model = model;
	}

	public void addLayer(Layer layer)
    {
    	LayerList layers = model.getLayers();
    	layers.add(layer);
    }
    
    public Layer getLayer(String layerName)
    {
    	LayerList layers = model.getLayers();
    	for (Layer l : layers)
    	{
    		System.out.println(l.getName());
    		if (l.getName() == layerName)
    		{
    			return l;
    		}
    	}
    	return null;
    }
    
    public void removeLayer(String layerName)
    {
    	LayerList layers = model.getLayers();
    	Layer removedLayer = null;
    	for (Layer l : layers)
    	{
    		if (l.getName() == layerName)
    		{
    			removedLayer = l;
    		}
    	}
    	if (removedLayer != null)
    	{
    	  layers.remove(removedLayer);
    	}
    }
    
    
    public void removeLayer(Layer layer)
    {
    	LayerList layers = model.getLayers();
    	Layer removedLayer = null;
    	for (Layer l : layers)
    	{
    		if (l == layer)
    		{
    			removedLayer = l;
    		}
    	}
    	if (removedLayer != null)
    	{
    		layers.remove(removedLayer);
    	}
    }
	
}
