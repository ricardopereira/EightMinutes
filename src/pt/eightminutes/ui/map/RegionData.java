package pt.eightminutes.ui.map;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegionData implements IRegion, Serializable {

	private static final long serialVersionUID = 1L;

	List<Point> lst = new ArrayList<Point>();

        List<Shape> areas = new ArrayList<Shape>();

	Map<Shape,String> names = new HashMap<Shape,String>();
	
	public String getAreaName(Shape p) {
            return names.get(p);
	}
        
	@Override
	public List<Shape> getRegions() {
            return areas;
	}
	
	@Override
	public void addNewPoint(Point p) {
            // Implementado só para o editor
	}
	
	@Override
	public void defineNewShape(String name) {
            // Implementado só para o editor
	}
	
	@Override
	public String getRegion(Point p) {
            for (Shape a: areas)
                if (a.contains(p))
                    return names.get(a);
            return null;
	}

	@Override
	public List<Point> getPoints() {
            // Implementado só para o editor
            return lst;
	}

	@Override
	public Point getCenterPoint(String location) {
            Shape s = getShape(location);
            if (s == null)
                return null;
            Rectangle2D bound = s.getBounds2D();
            return new Point((int)(bound.getCenterX()),(int)(bound.getCenterY()));
	}

	@Override
	public Shape getShape(String name) {
            for (Shape s: names.keySet())
                if (names.get(s).equals(name))
                    return s;
            return null;
	}
        
	@Override
	public Shape getRule(String name) {
            for (Shape s: names.keySet())
                if (names.get(s).equals(name))
                    return s;
            return null;
	}

}