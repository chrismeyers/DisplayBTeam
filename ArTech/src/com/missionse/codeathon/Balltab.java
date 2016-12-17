package com.missionse.codeathon;

import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Position;

public class Balltab {
	private Position balltabPos;
	private final WorldWindow wwd;
	
	public Balltab(WorldWindow wwd)
	{
		this.wwd = wwd;
	}
	
	public void updateBalltabPosition()
	{
		Position curPos = this.wwd.getCurrentPosition();
		if(curPos != null)
		{
			balltabPos = curPos;
		}
	}
	
	public Position getBalltabPostion()
	{
		return balltabPos;
	}
}
