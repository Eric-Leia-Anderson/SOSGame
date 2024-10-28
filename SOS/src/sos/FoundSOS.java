package sos;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class FoundSOS {

	public List<Point> coordinates;
	public Color lineColor;
	
	/**
	 * Setup FoundSOS with a new list for coordinates.
	 * */
	public FoundSOS() {
		coordinates = new ArrayList<Point>();
	}
	
	/**
	 * Returns coordinates.
	 * */
	public List<Point> getCoordinates() {
		return coordinates;
	}
	
	/**
	 * Sets coordinates list to points.
	 * */
	public void setCoordinates(List<Point> points) {
		this.coordinates = points;
	}
	
	/**
	 * Adds Point p to coordinates list.
	 * */
	public void addCoordinate(Point p) {
		coordinates.add(p);
	}

	/**
	 * Returns line color.
	 * */
	public Color getLineColor() {
		return lineColor;
	}

	/**
	 * Sets line color.
	 * */
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}
	
	/**
	 * Get the first of the coordinates.
	 * */
	public Point getFirst() {
		return coordinates.get(0);
	}
	
	/**
	 * Get the last of the coordinates.
	 * */
	public Point getLast() {
		return coordinates.get(2);
	}
}
