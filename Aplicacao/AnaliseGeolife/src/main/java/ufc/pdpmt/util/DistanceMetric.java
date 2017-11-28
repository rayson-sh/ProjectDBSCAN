package ufc.pdpmt.util;


import ufc.pdpmt.model.Point;

public class DistanceMetric {

    public static Double distance(Point pointA, Point pointB){
        double differenceLatitude = pointA.getTrajectory().getLatitude() - pointB.getTrajectory().getLatitude();
        double differenceLongitude = pointA.getTrajectory().getLongitude() - pointB.getTrajectory().getLongitude();

        double x = Math.pow((differenceLatitude), 2);
        double y = Math.pow((differenceLongitude), 2);

        return  Math.sqrt(x + y);
    }

}
