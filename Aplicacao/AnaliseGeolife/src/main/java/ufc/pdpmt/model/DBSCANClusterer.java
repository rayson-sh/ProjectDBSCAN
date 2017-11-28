package ufc.pdpmt.model;

import ufc.pdpmt.util.DistanceMetric;

import java.util.List;

public class DBSCANClusterer {

	private static int idCluster;

	public DBSCANClusterer() {
		idCluster = 0;
	}

	public List<Point> dbsacan(List<Point> dataSet, double epsilon, int minPts) {
		for (Point p : dataSet) {
			if (p.getStatus() == Status.VISITED) {
				continue;
			}

			p.setStatus(Status.VISITED);

			Neighborhood neighborhood = rangeQuery(p, dataSet, epsilon);

			if (neighborhood.getNumberOfNeighbors() >= minPts) {
				++idCluster;

				p.setIdCluster(idCluster);
				p.setCore(true);
				System.out.println("Ponto: " + p.toCSV());
				expandCluster(dataSet, idCluster, neighborhood, epsilon, minPts);
			}
		}
		
		return dataSet;

	}
	
	private Neighborhood rangeQuery(Point corePoint, List<Point> dataSet, double epsilon) {
		Neighborhood neighborhood = new Neighborhood();

		for (Point neighborPoint : dataSet) {
			if (neighborPoint.getTrajectory().getIdPerson() != corePoint.getTrajectory().getIdPerson()) {
				
				double distance = DistanceMetric.distance(corePoint, neighborPoint);
				
				if (distance <= epsilon) {
					neighborhood.addNeighbor(neighborPoint);
					neighborhood.addIdOwner(neighborPoint.getTrajectory().getIdPerson());// bitset
				}
			}
		}

		return neighborhood;
	}

	private void expandCluster(List<Point> dataSet, int idCluster, Neighborhood neighborhood, double epsilon, int minPts) {
		for (int i = 0; i < neighborhood.getNeighbors().size(); i++) {
			Point neighborPoint = neighborhood.getNeighbors().get(i);

			if (neighborPoint.getStatus() == Status.VISITED) {
				continue;
			}

			neighborPoint.setStatus(Status.VISITED);
			Neighborhood newNeighbors = rangeQuery(neighborPoint, dataSet, epsilon);

			if (newNeighbors.getNumberOfNeighbors() >= minPts) {
				neighborPoint.setCore(true);
				for (Point pointNewNeighbor : newNeighbors.getNeighbors()) {

					if (!pointNewNeighbor.getIdBitSet().intersects(neighborhood.getIdPersons())) {
						neighborhood.addNeighbor(pointNewNeighbor);
					}
				}
			}

			if (neighborPoint.getIdCluster() == Point.RUIDO) {
				neighborPoint.setIdCluster(idCluster);
			}
		}
	}


	public static int totalDeClusters() {
		return idCluster;
	}

}
