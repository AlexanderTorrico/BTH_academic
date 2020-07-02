package dao;

import dal.Conexion;
import dto.Carrera;
import dto.ReportesPagos;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LEO
 */
public class ReportesPagosDaoMySQL extends ReportesPagosDao {

    @Override
    public ArrayList<ReportesPagos> getList(ReportesPagos objReporte) {
        int idCarrera = objReporte.getIdCarrera();
        int idColegio = objReporte.getIdColegio();
        String fecha = objReporte.getFecha();
        String wheres = "";
        if (idCarrera != 0 || idColegio != 0) {
            wheres = "where ";
        }

        if (idCarrera != 0) {
            wheres += " ca.id = " + idCarrera;
        }
        if (idColegio != 0) {
            if (idCarrera == 0) {
                wheres += " co.id = " + idColegio;
            } else {
                wheres += " and co.id = " + idColegio;
            }

        }
        ArrayList<ReportesPagos> lista = new ArrayList<ReportesPagos>();
        String query = "select g.id, g.estado, co.id as idColegio, co.nombre as  nombreColegio , ca.id as idCarrera, ca.nombre as nombreCarrera, g.costo   from tblgrupos g join tblcolegios co on g.idColegio=co.id\n"
                + "				join tblcarreras ca on g.idCarrera = ca.id " + wheres;

        try {
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                ReportesPagos obj = new ReportesPagos();

                obj.setIdGrupo(objResultSet.getInt("id"));
                obj.setEstado(objResultSet.getString("estado"));
                obj.setIdColegio(objResultSet.getInt("idColegio"));
                obj.setNombreColegio(objResultSet.getString("nombreColegio"));
                obj.setIdCarrera(objResultSet.getInt("idCarrera"));
                obj.setNombreCarrera(objResultSet.getString("nombreCarrera"));
                obj.setCosto(objResultSet.getDouble("costo"));

                lista.add(obj);
            }
        } catch (Exception ex) {
            try {
                throw new Exception("El registro no pudo ser obtenido " + query);
            } catch (Exception ex1) {
                Logger.getLogger(ReportesPagosDaoMySQL.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return lista;
    }

}
