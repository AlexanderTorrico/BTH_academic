package dao;

import dal.Conexion;
import dto.Carrera;
import dto.ReportesNotas;
import dto.ReportesPagos;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LEO
 */
public class ReportesNotasDaoMySQL extends ReportesNotasDao {

    @Override
    public ArrayList<ReportesNotas> getList(ReportesNotas obj) {

        int idCarrera = obj.getIdCarreras();
        int idColegio = obj.getIdColegios();

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

        ArrayList<ReportesNotas> lista = new ArrayList<ReportesNotas>();

        String query = "select c.id as idColegio, c.nombre, g.id as idGrupos , g.nivel , n.id as idNota , n.nota , e.nombre as estudiante , ca.id as idCarrera  , ca.nombre as nombreCarrera\n"
                + "		from tblnotas n \n"
                + "        join tblestudiantes_grupos eg on eg.id = n.idEstudiante_grupo\n"
                + "        join tblestudiantes e on e.id = eg.idEstudiante\n"
                + "        join tblgrupos g on eg.idGrupo = g.id \n"
                + "        join tblcolegios c on c.id = g.idColegio \n"
                + "        join tblcarreras ca on g.idCarrera = ca.id;" + wheres;

        try {
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {

                ReportesNotas n = new ReportesNotas();

                n.setIdColegios(objResultSet.getInt("idColegio"));
                n.setNombreColegio(objResultSet.getString("nombre"));
                n.setIdGrupos(objResultSet.getInt("idGrupos"));
                n.setNivel(objResultSet.getString("nivel"));
                n.setIdNotas(objResultSet.getInt("idNota"));
                n.setNotas(objResultSet.getString("nota"));
                n.setEstudiante(objResultSet.getString("estudiante"));
                n.setIdCarreras(objResultSet.getInt("idCarrera"));
                n.setCarrera(objResultSet.getString("nombreCarrera"));

                lista.add(n);
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
