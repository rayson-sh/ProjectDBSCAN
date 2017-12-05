package ufc.pdpmt.model;

import java.util.BitSet;

public class Point {
    public final static int RUIDO = -1;
    private BitSet idBitSet;
	private Trajectory trajectory;
    private Status status;

    private Integer idCluster;
    private boolean core;

    public Point(Trajectory trajectory, int id) {
    	idBitSet = new BitSet(8);
        idBitSet.set(id);

        this.status = Status.UNVISITED;
        this.idCluster = RUIDO;
        this.core = false;
        this.trajectory = trajectory;

    }

	public BitSet getIdBitSet() {
		return idBitSet;
	}

	public void setIdBitSet(BitSet idBitSet) {
		this.idBitSet = idBitSet;
	}

	public Trajectory getTrajectory() {
		return trajectory;
	}

	public void setTrajectory(Trajectory trajectory) {
		this.trajectory = trajectory;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getIdCluster() {
		return idCluster;
	}

	public void setIdCluster(Integer idCluster) {
		this.idCluster = idCluster;
	}

	public boolean isCore() {
		return core;
	}

	public void setCore(boolean core) {
		this.core = core;
	}

	public String toCSV() {
        String resultado =
                this.trajectory.getIdPerson() + ";" +
                this.trajectory.getLine() + ";" +
                this.trajectory.getAltitude() + ";" +
                this.trajectory.getLatitude() + ";" +
                this.trajectory.getLongitude() + ";" +
                this.trajectory.getDate() + ";" +
                this.trajectory.getHour() + ";" +
                this.status + ";" +
                this.core + ";" +
                this.idCluster;

        return resultado;
    }

}
