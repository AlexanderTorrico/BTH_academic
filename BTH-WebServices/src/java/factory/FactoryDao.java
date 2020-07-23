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

    public abstract NotaDao getNewNotaeDao();

    public abstract LoginDao getNewLoginDao();

    public abstract CarreraDao getNewCarreraDao();

    public abstract GruposDao getNewGruposDao();

    public abstract AsistenciaDao getNewAsistenciaDao();

    public abstract ParticipantesDao getNewParticipanteDao();

    public abstract GestionDao getNewGestionDao();

    public abstract ReportesNotasDao getNewReportesNotasDao();

    public abstract ReportesPagosDao getNewReportesPagosDao();
    
    public abstract UsuarioDao getNewUsuarioDao();
    
    public abstract RolDao getNewRolDao();
    
    public abstract PermisoDao getNewPermisoDao();

    public abstract UserRolesDao getNewUserRolesDao();

    
}
