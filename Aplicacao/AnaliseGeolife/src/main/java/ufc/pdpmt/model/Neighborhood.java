package ufc.pdpmt.model;

import java.util.*;

public class Neighborhood {
    private BitSet idPersons;
    private BitSet idPoints;
    private List<Point> neighbors;

    public Neighborhood() {
        neighbors = new ArrayList<Point>();
        idPersons = new BitSet(8);
    }

    public void addNeighbor(Point point){
        idPersons.set(point.getTrajectory().getIdPerson());
        idPoints.set(point.getId());
        neighbors.add(point);
    }

    public void addIdOwner(Integer idOwner) {
        idPersons.set(idOwner);
    }

    public int getNumberOfNeighbors() {
        return idPersons.cardinality();
    }

    public List<Point> getNeighbors() {
        return neighbors;
    }

    public BitSet getIdPersons() {
        return idPersons;
    }

	public BitSet getIdPoints() {
		return idPoints;
	}
    
    
}
