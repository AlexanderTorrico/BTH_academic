package factory;

import dal.Configuracion;
import dao.*;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public abstract class FactoryDao {

    protected static FactoryDao instancia;

    public static FactoryDao getFactoryInstance() {
        if (instancia == null) {
//			Configuracion config = Configuracion.getConfiguracion();
//			if(config.getDbEngine().equals("MySQL"));
            instancia = FactoryDaoMySQL.getFactoryInstance();
        }
        return instancia;
    }

    // DESDE AQUI SE COMIENZA EL NUEVO CODIGO
    public abstract ColegioDao getNewColegioDao();

    public abstract DocenteDao getNewDocenteDao();

    public abstract UserDao getNewUserDao();

    public abstract ParametroDao getNewParametroDao();

    public abstract GrupoDao getNewGrupoDao();

    public abstract PagoDao getNewPagoDao();

    public abstract MovilInfoEstudianteDao getNewMovilInfoEstudiante();

    public abstract indexInformacionDao getindexInformacionDao();

    public abstract ProyectosDao getProyectosDao();

    public abstract EstudianteDao getNewEstudianteDao();

}
