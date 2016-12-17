package com.missionse.codeathon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Polyline;

import com.missionse.codeathon.LineManager;

public class LinePanel extends JPanel
{
    private final WorldWindow wwd;
    private final LineManager lm;
    private JButton newButton;
    private JButton pauseButton;
    private JButton endButton;
    private JLabel[] pointLabels;

    public LinePanel(WorldWindow wwd, LineManager lm)
    {
        super(new BorderLayout());
        this.wwd = wwd;
        this.lm = lm;
        this.makePanel(new Dimension(200, 400));
        lm.addPropertyChangeListener(new PropertyChangeListener()
        {
            public void propertyChange(PropertyChangeEvent propertyChangeEvent)
            {
                fillPointsPanel();
            }
        });
    }

    private void makePanel(Dimension size)
    {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        newButton = new JButton("New");
        newButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                lm.clear();
                lm.setArmed(true);
                pauseButton.setText("Pause");
                pauseButton.setEnabled(true);
                endButton.setEnabled(true);
                newButton.setEnabled(false);
                ((Component) wwd).setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }
        });
        buttonPanel.add(newButton);
        newButton.setEnabled(true);

        pauseButton = new JButton("Pause");
        pauseButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                lm.setArmed(!lm.isArmed());
                pauseButton.setText(!lm.isArmed() ? "Resume" : "Pause");
                ((Component) wwd).setCursor(Cursor.getDefaultCursor());
            }
        });
        buttonPanel.add(pauseButton);
        pauseButton.setEnabled(false);

        endButton = new JButton("End");
        endButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                lm.setArmed(false);
                newButton.setEnabled(true);
                pauseButton.setEnabled(false);
                pauseButton.setText("Pause");
                endButton.setEnabled(false);
                ((Component) wwd).setCursor(Cursor.getDefaultCursor());
                
                GridLayer gridLayer = new GridLayer(new LayerManager(wwd.getModel()));
                GridGenerator gridGenerator = new GridGenerator(
                		new Position(new LatLon(Angle.fromDegrees(38), Angle.fromDegrees(-105)), 0),
                		wwd.getModel().getGlobe(),
                		gridLayer);
        		
                IntersectionFinder f = new IntersectionFinder(gridGenerator.getGridSquares(), lm.getLine(), wwd.getModel().getGlobe());
            }
        });
        buttonPanel.add(endButton);
        endButton.setEnabled(false);

        JPanel pointPanel = new JPanel(new GridLayout(0, 1, 0, 10));
        pointPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.pointLabels = new JLabel[10];
        for (int i = 0; i < this.pointLabels.length; i++)
        {
            this.pointLabels[i] = new JLabel("");
            pointPanel.add(this.pointLabels[i]);
        }

        // Put the point panel in a container to prevent scroll panel from stretching the vertical spacing.
        JPanel dummyPanel = new JPanel(new BorderLayout());
        dummyPanel.add(pointPanel, BorderLayout.NORTH);

        // Put the point panel in a scroll bar.
        JScrollPane scrollPane = new JScrollPane(dummyPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        if (size != null)
            scrollPane.setPreferredSize(size);

        // Add the buttons, scroll bar and inner panel to a titled panel that will resize with the main window.
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(
            new CompoundBorder(BorderFactory.createEmptyBorder(9, 9, 9, 9), new TitledBorder("Paths")));
        outerPanel.setToolTipText("Path control and info");
        outerPanel.add(buttonPanel, BorderLayout.NORTH);
        outerPanel.add(scrollPane, BorderLayout.CENTER);
        this.add(outerPanel, BorderLayout.CENTER);
    }

    private void fillPointsPanel()
    {
        int i = 0;
        for (Position pos : lm.getLine().getPositions())
        {
            if (i == this.pointLabels.length)
               break;

            String las = String.format("Lat %7.4f\u00B0", pos.getLatitude().getDegrees());
            String los = String.format("Lon %7.4f\u00B0", pos.getLongitude().getDegrees());
            pointLabels[i++].setText(i + " " + las + "  " + los);
        }
        for (; i < this.pointLabels.length; i++)
            pointLabels[i++].setText("");
    }
}
