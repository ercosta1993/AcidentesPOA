package gui;

import java.awt.Color;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

/**
 * Um waypoint que tem uma cor e um texto associados
 *
 * @author Martin Steiger, Marcelo Cohen
 */
public class MyWaypoint extends DefaultWaypoint {

    private final Color color;
    private final String label;
    private final double size;

    /**
     * @param color a cor
     * @param label
     * @param coord a localização
     * @param size
     */
    public MyWaypoint(Color color, String label, GeoPosition coord, double size) {
        super(coord);
        this.color = color;
        this.label = label;
        this.size = size;
    }

    /**
     * @return 
     * @returns a cor do waypoint
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return 
     * @returns o texto do waypoint
     */
    public String getLabel() {
        return label;
    }

    /**
     *
     * @return 
     * @returns o tamanho do waypoint
     */
    public double getSize() {
        return size;
    }
}
