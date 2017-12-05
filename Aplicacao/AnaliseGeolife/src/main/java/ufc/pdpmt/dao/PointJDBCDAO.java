package ufc.pdpmt.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ufc.pdpmt.model.Point;
import ufc.pdpmt.model.Trajectory;

public class PointJDBCDAO implements PointDAO {
	
	private static int idNext = 1;

    @Override
    public List<Point> find() {
        Connection con = null;
        List<Point> result = null;
        try {
            con = ConnectionFactory.getConnection();
            PreparedStatement pst;

            String sql = "SELECT id, line, latitude, longitude, altitude, data, hora " +
                         "FROM  all_trajectory " +
                         "WHERE data BETWEEN '2008-12-07' and '2008-12-13' " +
                         "AND extract(hour FROM hora) = 7 ORDER BY hora LIMIT 1000";

            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            result = new ArrayList<Point>();
            while (rs.next()) {
            	Point point = mapColunms(rs);
                result.add(point);
            }
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada com sucesso. ", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                throw new DAOException("Não foi possivel fechar a conexão. ", e);
            }
        }
        return result;
    }


    private Point mapColunms(ResultSet rs) throws SQLException {

        Trajectory trajetoria = new Trajectory();
        trajetoria.setIdPerson(rs.getInt("id"));
        trajetoria.setLine(rs.getString("line"));
        trajetoria.setLatitude(rs.getDouble("latitude"));
        trajetoria.setLongitude(rs.getDouble("longitude"));
        trajetoria.setAltitude(rs.getDouble("altitude"));
        trajetoria.setDate(rs.getDate("data"));
        trajetoria.setHour(rs.getString("hora"));

        Point point = new Point(trajetoria, idNext++);

        return point;

    }
}
