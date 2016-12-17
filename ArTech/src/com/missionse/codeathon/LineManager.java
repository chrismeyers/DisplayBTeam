package com.missionse.codeathon;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import gov.nasa.worldwind.*;
import gov.nasa.worldwind.event.*;
import gov.nasa.worldwind.avlist.*;
import gov.nasa.worldwind.geom.*;
import gov.nasa.worldwind.layers.*;
import gov.nasa.worldwind.render.*;

public class LineManager  extends AVListImpl {
	
    private final WorldWindow wwd;
    private boolean armed = false;
    private ArrayList<Position> positions = new ArrayList<Position>();
    private final RenderableLayer layer;
    private final Polyline line;
    private boolean active = false;
    private Position balltabPos; 
    
	public LineManager(final WorldWindow wwd, RenderableLayer lineLayer, Polyline polyline, Balltab bt) {
		this.wwd = wwd;

        if (polyline != null)
        {
            line = polyline;
        }
        else
        {
            this.line = new Polyline();
            this.line.setFollowTerrain(true);
        }
        //line.setColor(Color.YELLOW);
        
        this.layer = lineLayer != null ? lineLayer : new RenderableLayer();
        this.layer.addRenderable(this.line);
        this.wwd.getModel().getLayers().add(this.layer);
        
        this.wwd.getInputHandler().addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent mouseEvent)
            {
            	bt.updateBalltabPosition();
                
            	if (armed && mouseEvent.getButton() == MouseEvent.BUTTON1)
                {
                    if (armed && (mouseEvent.getModifiersEx() & MouseEvent.BUTTON1_DOWN_MASK) != 0)
                    {
                        if (!mouseEvent.isControlDown())
                        {
                            active = true;
                            addPosition();
                        }
                    }
                    mouseEvent.consume();
                }
            }

            public void mouseReleased(MouseEvent mouseEvent)
            {
                if (armed && mouseEvent.getButton() == MouseEvent.BUTTON1)
                {
                    if (positions.size() == 1)
                        removePosition();
                    active = false;
                    mouseEvent.consume();
                }
            }

            public void mouseClicked(MouseEvent mouseEvent)
            {
                if (armed && mouseEvent.getButton() == MouseEvent.BUTTON1)
                {
                    if (mouseEvent.isControlDown())
                        removePosition();
                    mouseEvent.consume();
                }
            }
        });

        this.wwd.getInputHandler().addMouseMotionListener(new MouseMotionAdapter()
        {
            public void mouseDragged(MouseEvent mouseEvent)
            {
                if (armed && (mouseEvent.getModifiersEx() & MouseEvent.BUTTON1_DOWN_MASK) != 0)
                {
                    // Don't update the polyline here because the wwd current cursor position will not
                    // have been updated to reflect the current mouse position. Wait to update in the
                    // position listener, but consume the event so the view doesn't respond to it.
                    if (active)
                        mouseEvent.consume();
                }
            }
        });

        this.wwd.addPositionListener(new PositionListener()
        {
            public void moved(PositionEvent event)
            {
                if (!active)
                    return;

                if (positions.size() == 1)
                    addPosition();
                else
                    replacePosition();
            }
        });
	}
	
	/**
     * Returns the layer holding the polyline being created.
     *
     * @return the layer containing the polyline.
     */
    public RenderableLayer getLayer()
    {
        return this.layer;
    }

    /**
     * Returns the layer currently used to display the polyline.
     *
     * @return the layer holding the polyline.
     */
    public Polyline getLine()
    {
        return this.line;
    }

    /**
     * Removes all positions from the polyline.
     */
    public void clear()
    {
        while (this.positions.size() > 0)
            this.removePosition();
    }

    /**
     * Identifies whether the line manager is armed.
     *
     * @return true if armed, false if not armed.
     */
    public boolean isArmed()
    {
        return this.armed;
    }

    /**
     * Arms and disarms the line manager. When armed, the line manager monitors user input and builds the polyline in
     * response to the actions mentioned in the overview above. When disarmed, the line manager ignores all user input.
     *
     * @param armed true to arm the line manager, false to disarm it.
     */
    public void setArmed(boolean armed)
    {
        this.armed = armed;
    }

    private void addPosition()
    {
        Position curPos = this.wwd.getCurrentPosition();
        if (curPos == null)
            return;

        System.out.println(curPos);
        this.positions.add(curPos);
        this.line.setPositions(this.positions);
        this.firePropertyChange("LineManager.AddPosition", null, curPos);
        this.wwd.redraw();        
    }

    private void replacePosition()
    {
        Position curPos = this.wwd.getCurrentPosition();
        if (curPos == null)
            return;

        int index = this.positions.size() - 1;
        if (index < 0)
            index = 0;

        Position currentLastPosition = this.positions.get(index);
        this.positions.set(index, curPos);
        this.line.setPositions(this.positions);
        this.firePropertyChange("LineManager.ReplacePosition", currentLastPosition, curPos);
        this.wwd.redraw();
    }

    private void removePosition()
    {
        if (this.positions.size() == 0)
            return;

        Position currentLastPosition = this.positions.get(this.positions.size() - 1);
        this.positions.remove(this.positions.size() - 1);
        this.line.setPositions(this.positions);
        this.firePropertyChange("LineManager.RemovePosition", currentLastPosition, null);
        this.wwd.redraw();
    }

}
