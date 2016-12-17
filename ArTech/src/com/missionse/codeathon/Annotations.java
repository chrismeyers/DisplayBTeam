package com.missionse.codeathon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.avlist.AVList;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.event.SelectListener;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.IconLayer;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.LayerList;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.layers.placename.PlaceNameLayer;
import gov.nasa.worldwind.pick.PickedObject;
import gov.nasa.worldwind.render.UserFacingIcon;
import gov.nasa.worldwind.render.WWIcon;
import gov.nasa.worldwind.util.BasicDragger;
import gov.nasa.worldwind.util.Logging;
import gov.nasa.worldwindx.examples.util.DialogAnnotation;
import gov.nasa.worldwindx.examples.util.DialogAnnotationController;
import gov.nasa.worldwindx.examples.util.SlideShowAnnotation;
import gov.nasa.worldwindx.examples.util.SlideShowAnnotationController;

public class Annotations implements SelectListener {
	
    protected final static String IMAGES = "Images";

    protected final static String ICON_IMAGES = "gov/nasa/worldwindx/examples/images/imageicon-64.png";

    protected final static String IMAGE_PATH_WITNESS1 = "res/images/witness1.jpg";
    protected final static String IMAGE_PATH_WITNESS2 = "res/images/witness2.jpg";
    protected final static String IMAGE_PATH_WITNESS3 = "res/images/witness3.jpg";

	protected IconLayer iconLayer = null;
    protected RenderableLayer contentLayer = null;
    protected WWIcon highlit = null;
    protected BasicDragger dragger = null;
    protected WorldWindow wwd = null;
    protected JFrame frame = null;
    
    public Annotations(WorldWindow wwd) {
    	iconLayer = createIconLayer();
        contentLayer = new RenderableLayer();
        dragger = new BasicDragger(wwd);
        
        insertBeforePlacenames(wwd, iconLayer);
        insertBeforePlacenames(wwd, contentLayer);

        wwd.addSelectListener(this);
        
        this.wwd = wwd;
    }
    
    public static void insertBeforePlacenames(WorldWindow wwd, Layer layer)
    {
        // Insert the layer into the layer list just before the placenames.
        int compassPosition = 0;
        LayerList layers = wwd.getModel().getLayers();
        for (Layer l : layers)
        {
            if (l instanceof PlaceNameLayer)
                compassPosition = layers.indexOf(l);
        }
        layers.add(compassPosition, layer);
    }
    
    public static IconLayer createIconLayer()
    {
        IconLayer layer = new IconLayer();
        layer.setPickEnabled(true);

        WWIcon icon = createIcon(IMAGES, Position.fromDegrees(46.1912, -122.1944, 0), "",
            java.util.Arrays.asList(IMAGE_PATH_WITNESS1));
        layer.addIcon(icon);

        icon = createIcon(IMAGES, Position.fromDegrees(-12, -70, 0), "",
            java.util.Arrays.asList(IMAGE_PATH_WITNESS2, IMAGE_PATH_WITNESS3));
        layer.addIcon(icon);

        return layer;
    }
    
    public static WWIcon createIcon(Object type, Position position, String title, Object data)
    {
        if (position == null)
        {
            String message = Logging.getMessage("nullValue.PositionIsNull");
            Logging.logger().severe(message);
            throw new IllegalArgumentException(message);
        }

        if (title == null)
        {
            String message = Logging.getMessage("nullValue.StringIsNull");
            Logging.logger().severe(message);
            throw new IllegalArgumentException(message);
        }

        if (data == null)
        {
            String message = Logging.getMessage("nullValue.DataSetIsNull");
            Logging.logger().severe(message);
            throw new IllegalArgumentException(message);
        }

        String iconPath = ICON_IMAGES;

        UserFacingIcon icon = new UserFacingIcon(iconPath, position);
        icon.setSize(new java.awt.Dimension(64, 64));
        icon.setValue(AVKey.DATA_TYPE, type);
        icon.setValue(AVKey.TITLE, title);
        icon.setValue(AVKey.URL, data);
        return icon;
    }

    @SuppressWarnings( {"StringEquality"})
    public void selected(SelectEvent e)
    {
        if (e == null)
            return;

        PickedObject topPickedObject = e.getTopPickedObject();

        if (e.getEventAction() == SelectEvent.LEFT_PRESS)
        {
            if (topPickedObject != null && topPickedObject.getObject() instanceof WWIcon)
            {
                WWIcon selected = (WWIcon) topPickedObject.getObject();
                highlight(selected);
            }
            else
            {
                highlight(null);
            }
        }
        else if (e.getEventAction() == SelectEvent.LEFT_DOUBLE_CLICK)
        {
            if (topPickedObject != null && topPickedObject.getObject() instanceof WWIcon)
            {
                WWIcon selected = (WWIcon) topPickedObject.getObject();
                highlight(selected);
                openResource(selected);
            }
        }
        else if (e.getEventAction() == SelectEvent.DRAG || e.getEventAction() == SelectEvent.DRAG_END)
        {
            //this.dragger.selected(e);
        }
    }
    
    protected void closeResource(ContentAnnotation content)
    {
        if (content == null)
            return;

        content.detach();
    }
    
    protected void openResource(WWIcon icon)
    {
        if (icon == null)
            return;

        ContentAnnotation content = createContent(icon.getPosition(), icon);

        if (content != null)
        {
            content.attach();
        }
    }
    
    protected ContentAnnotation createContent(Position position, AVList params)
    {
        return createContentAnnotation(position, params);
    }
    
    public ContentAnnotation createContentAnnotation(Position position, AVList params)
    {
        if (position == null)
        {
            String message = Logging.getMessage("nullValue.PositionIsNull");
            Logging.logger().severe(message);
            throw new IllegalArgumentException(message);
        }

        if (params == null)
        {
            String message = Logging.getMessage("nullValue.ParamsIsNull");
            Logging.logger().severe(message);
            throw new IllegalArgumentException(message);
        }

        String type = params.getStringValue(AVKey.DATA_TYPE);
        String title = params.getStringValue(AVKey.TITLE);
        Object source = params.getValue(AVKey.URL);

        if (type == IMAGES)
        {
            return createImageAnnotation(position, title, (Iterable) source);
        }

        return null;
    }
    
    public ContentAnnotation createImageAnnotation(Position position, String title,
            Iterable sources)
        {
            if (position == null)
            {
                String message = Logging.getMessage("nullValue.PositionIsNull");
                Logging.logger().severe(message);
                throw new IllegalArgumentException(message);
            }

            if (title == null)
            {
                String message = Logging.getMessage("nullValue.StringIsNull");
                Logging.logger().severe(message);
                throw new IllegalArgumentException(message);
            }

            if (sources == null)
            {
                String message = Logging.getMessage("nullValue.IterableIsNull");
                Logging.logger().severe(message);
                throw new IllegalArgumentException(message);
            }

            SlideShowAnnotation annotation = new SlideShowAnnotation(position);
            annotation.setAlwaysOnTop(true);
            annotation.getTitleLabel().setText(title);

            SlideShowAnnotationController controller = new SlideShowAnnotationController(wwd, annotation,
                sources);

            return new ImageContentAnnotation(annotation, controller);
        }
    
    
    
    public void highlight(WWIcon icon)
    {
        if (highlit == icon)
            return;

        if (highlit != null)
        {
            highlit.setHighlighted(false);
            highlit = null;
        }

        if (icon != null)
        {
            highlit = icon;
            highlit.setHighlighted(true);
        }

        wwd.redraw();
    }
    
    public class ImageContentAnnotation extends ContentAnnotation
    {
        public ImageContentAnnotation(SlideShowAnnotation annnotation,
            SlideShowAnnotationController controller)
        {
            super(annnotation, controller);
        }

        public void detach()
        {
            super.detach();

            // Stop any threads or timers the controller may be actively running.
            SlideShowAnnotationController controller = (SlideShowAnnotationController) this.getController();
            if (controller != null)
            {
                this.stopController(controller);
            }
        }

        @SuppressWarnings( {"StringEquality"})
        protected void stopController(SlideShowAnnotationController controller)
        {
            String state = controller.getState();
            if (state == AVKey.PLAY)
            {
                controller.stopSlideShow();
            }

            controller.stopRetrievalTasks();
        }
    }
    
    public class ContentAnnotation implements ActionListener
    {
        protected DialogAnnotation annnotation;
        protected DialogAnnotationController controller;

        public ContentAnnotation(DialogAnnotation annnotation, DialogAnnotationController controller)
        {
            this.annnotation = annnotation;
            this.annnotation.addActionListener(this);
            this.controller = controller;
        }

        public DialogAnnotation getAnnotation()
        {
            return this.annnotation;
        }

        public DialogAnnotationController getController()
        {
            return this.controller;
        }

        @SuppressWarnings( {"StringEquality"})
        public void actionPerformed(ActionEvent e)
        {
            if (e == null)
                return;

            if (e.getActionCommand() == AVKey.CLOSE)
            {
                closeResource(this);
            }
        }

        public void detach()
        {
            this.getController().setEnabled(false);

            RenderableLayer layer = contentLayer;
            layer.removeRenderable(this.getAnnotation());
        }

        public void attach()
        {
            this.getController().setEnabled(true);

            RenderableLayer layer = contentLayer;
            layer.removeRenderable(this.getAnnotation());
            layer.addRenderable(this.getAnnotation());
        }
    }

}
