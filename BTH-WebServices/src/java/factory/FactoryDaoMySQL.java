package factory;

import dal.Configuracion;
import dao.*;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public class FactoryDaoMySQL extends FactoryDao {

    private FactoryDaoMySQL() {
        ;
    }

    public static FactoryDao getFactoryInstance() {
        if (instancia == null) {
            instancia = new FactoryDaoMySQL();
        }
        return instancia;
    }

    @Override
    public ColegioDao getNewColegioDao() {
        return new ColegioDaoMySQL();
    }

    @Override
    public DocenteDao getNewDocenteDao() {
        return new DocenteDaoMySQL();
    }

    @Override
    public UserDao getNewUserDao() {
       return new UserDaoMySQL();
    }

    @Override
    public ParametroDao getNewParametroDao() {
        return new ParametroDaoMySQL();
    }

    @Override
    public GrupoDao getNewGrupoDao() {
        return new GrupoDaoMysql();
    }

    @Override
    public MovilInfoEstudianteDao getNewMovilInfoEstudiante() {
        return new MovilInfoEstudianteDaoMysql();
    }

    @Override
    public PagoDao getNewPagoDao() {
        return new PagoDaoMysql();
    }
    
    @Override
    public indexInformacionDaoMySQL getindexInformacionDao() {
        return new indexInformacionDaoMySQL();
    }

    @Override
    public ProyectosDao getProyectosDao() {
        return new ProyectosDaoMySQL();
    }

    @Override
    public EstudianteDao getNewEstudianteDao() {
     return new EstudianteDaoMySQL();   
    }
    
    @Override
    public NotaDao getNewNotaeDao() {
        return new NotaDaoMysql();
    }

    @Override
    public LoginDao getNewLoginDao() {
        return new LoginDaoMysql();
    }

    @Override
    public CarreraDao getNewCarreraDao() {
        return new CarreraDaoMySQL();
    }

    @Override
    public GruposDao getNewGruposDao() {
       return new GruposDaoMySQL();
    }
	
    @Override
    public AsistenciaDao getNewAsistenciaDao() {
        return new AsistenciaDaoMysql();
    }

}
