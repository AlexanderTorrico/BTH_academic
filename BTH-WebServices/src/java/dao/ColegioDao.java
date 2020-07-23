package dao;

import dto.CarrerasHabilitadasColegio;
import dto.Colegio;
import dto.ColegioReporte;
import dto.Docente;
import java.util.ArrayList;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public abstract class ColegioDao {

    public abstract int insert(Colegio obj) throws Exception;

    public abstract int update(Colegio obj) throws Exception;

    public abstract void ValidarCuenta(Colegio obj) throws Exception;

    public abstract void ActivarCuenta(String username) throws Exception;

    public abstract void delete(int id);

    public abstract ArrayList<Colegio> getList();

    public abstract Colegio get(int id);

    public abstract Colegio get(String username, String correo);
    
    public abstract ArrayList<ColegioReporte> getListReportesCarrera(int id);

    public abstract ArrayList<ColegioReporte> getListReportesGrupo(int id);

    public abstract ArrayList<ColegioReporte> getListReportesNotasCarrera(int id);

    public abstract ArrayList<ColegioReporte> getListReportesNotasGrupo(int id);

    public abstract ArrayList<CarrerasHabilitadasColegio> getListCarrerasHabilitadas(int id);
   
}
