package factory;

import dal.Configuracion;
import dao.*;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public abstract class FactoryDao {

	protected static FactoryDao instancia;
	public static FactoryDao getFactoryInstance(){
		if(instancia == null){
//			Configuracion config = Configuracion.getConfiguracion();
//			if(config.getDbEngine().equals("MySQL"));
                    instancia = FactoryDaoMySQL.getFactoryInstance();
		}
		return instancia;
	}

	public abstract UsuarioDao getNewUsuarioDao();     
        // DESDE AQUI SE COMIENZA EL NUEVO CODIGO
        public abstract ColegioDao getNewColegioDao();
        public abstract DocenteDao getNewDocenteDao();
        public abstract UserDao getNewUserDao();
       

}

