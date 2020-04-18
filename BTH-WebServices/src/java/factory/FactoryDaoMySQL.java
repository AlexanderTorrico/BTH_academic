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

}
