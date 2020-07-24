package dao;

import consola.MessageCorreo;
import consola.SendEmail;
import dal.Conexion;
import dto.CarrerasHabilitadasColegio;
import dto.Colegio;
import dto.ColegioReporte;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public class ColegioDaoMySQL extends ColegioDao {

    @Override
    public int insert(Colegio obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();
        int id = 0;
        StringBuilder query = new StringBuilder("INSERT INTO tblcolegios VALUES (");
        query.append("NULL,");
        query.append("'" + obj.getNombre() + "',");
        query.append("'" + obj.getSigep() + "',");
        query.append("'" + obj.getDirector() + "',");
        query.append("'" + obj.getDireccion() + "',");
        query.append("'" + obj.getTelefono() + "',");
        query.append("1,");
        query.append("'" + obj.getCorreo() + "',");
        query.append("'" + obj.getUsername() + "',");
        query.append("hex(aes_encrypt('" + obj.getContrasenia() + "','COL')),");
        query.append("" + obj.getEstado() + "");
        query.append(")");
        id = objConexion.ejecutarInsert(query.toString());
        if (id == 0) {
            throw new Exception("El registro no pudo ser insertado");
        }
        objConexion.desconectar();
        return id;
    }

    @Override
    public void delete(int id) {
        Conexion objConexion = Conexion.getOrCreate();
        StringBuilder query = new StringBuilder("DELETE FROM tblcolegios where id = " + id + ";");
        int i = objConexion.ejecutarSimple(query.toString());
        objConexion.desconectar();
    }

    @Override
    public ArrayList<Colegio> getList() {
        ArrayList<Colegio> lista = new ArrayList<Colegio>();

        String query = "select * from tblcolegios";

        try {
            Conexion objConexion = Conexion.getOrCreate();
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Colegio obj = new Colegio();

                obj.setId(objResultSet.getInt("id"));
                obj.setNombre(objResultSet.getString("nombre"));
                obj.setSigep(objResultSet.getString("sigep"));
                obj.setDirector(objResultSet.getString("director"));
                obj.setDireccion(objResultSet.getString("direccion"));
                obj.setTelefono(objResultSet.getString("telefono"));
                obj.setEsModulo(objResultSet.getInt("esModulo"));
                obj.setCorreo(objResultSet.getString("correo"));
                obj.setUsername(objResultSet.getString("username"));
                obj.setContrasenia(objResultSet.getString("contrasenia"));
                obj.setEstado(objResultSet.getInt("estado"));

                lista.add(obj);
            }
        } catch (Exception ex) {
            try {
                throw new Exception("El registro no pudo ser obtenido " + query);
            } catch (Exception ex1) {
                Logger.getLogger(CarreraDaoMySQL.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return lista;
    }

    @Override
    public Colegio get(int id) {

        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM tblcolegios WHERE id = " + id;
            ResultSet objResultSet = objConexion.ejecutar(query);
            if (objResultSet.next()) {
                Colegio obj = new Colegio();
                int colegioId = objResultSet.getInt("id");
                obj.setId(colegioId);
                String nombre = objResultSet.getString("nombre");
                obj.setNombre(nombre);
                String sigep = objResultSet.getString("sigep");
                obj.setSigep(sigep);
                String director = objResultSet.getString("director");
                obj.setDirector(director);
                String direccion = objResultSet.getString("direccion");
                obj.setDireccion(direccion);
                String telefono = objResultSet.getString("telefono");
                obj.setTelefono(telefono);
                int esModulo = objResultSet.getInt("esModulo");
                obj.setEsModulo(esModulo);
                String correo = objResultSet.getString("correo");
                obj.setCorreo(correo);
                String username = objResultSet.getString("username");
                obj.setUsername(username);
                String contrasenia = objResultSet.getString("contrasenia");
                obj.setContrasenia(contrasenia);
                int estado = objResultSet.getInt("estado");
                obj.setEstado(estado);
                return obj;
            }
        } catch (Exception ex) {
            ;
        }
        return null;
    }

    @Override
    public Colegio get(String _username, String _correo) {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "select * from tblcolegios\n"
                    + "where username='" + _username + "' or correo = '" + _correo + "'";
            ResultSet objResultSet = objConexion.ejecutar(query);
            if (objResultSet.next()) {
                Colegio obj = new Colegio();
                int colegioId = objResultSet.getInt("id");
                obj.setId(colegioId);
                String nombre = objResultSet.getString("nombre");
                obj.setNombre(nombre);
                String sigep = objResultSet.getString("sigep");
                obj.setSigep(sigep);
                String director = objResultSet.getString("director");
                obj.setDirector(director);
                String direccion = objResultSet.getString("direccion");
                obj.setDireccion(direccion);
                String telefono = objResultSet.getString("telefono");
                obj.setTelefono(telefono);
                int esModulo = objResultSet.getInt("esModulo");
                obj.setEsModulo(esModulo);
                String correo = objResultSet.getString("correo");
                obj.setCorreo(correo);
                String username = objResultSet.getString("username");
                obj.setUsername(username);
                String contrasenia = objResultSet.getString("contrasenia");
                obj.setContrasenia(contrasenia);
                int estado = objResultSet.getInt("estado");
                obj.setEstado(estado);
                return obj;
            }
        } catch (Exception ex) {

        }
        return null;
    }

    @Override
    public void ValidarCuenta(Colegio obj) throws Exception {
        SendEmail enviarCorreo = new SendEmail();
        MessageCorreo correo = new MessageCorreo(obj.getCorreo(), obj.getUsername(), obj.getId(), "VERIFICAR CUENTA", "colegio");
        enviarCorreo.SendEmail(correo.getCorreo(), correo.getAsunto(), correo.getVerificacion());
    }

    @Override
    public void ActivarCuenta(String username) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();

        StringBuilder query = new StringBuilder("UPDATE tblcolegios SET estado = 1 WHERE username = '" + username + "'");
        int upd = objConexion.ejecutarSimple(query.toString());
        if (upd == 0) {
            throw new Exception("El registro no pudo ser actualizado");
        }

        objConexion.desconectar();
    }

    @Override
    public int update(Colegio obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();
        int id = 0;
        StringBuilder query = new StringBuilder("UPDATE tblcolegios SET nombre='" + obj.getNombre() + "',sigep= '" + obj.getSigep() + "',director= '" + obj.getDirector() + "',direccion= '" + obj.getDireccion() + "',telefono= '" + obj.getTelefono() + "',esModulo= '" + obj.getEsModulo() + "',correo= '" + obj.getCorreo() + "',username= '" + obj.getUsername() + "',contrasenia= '" + obj.getContrasenia() + "',estado= '" + obj.getEstado() + "' WHERE id= " + obj.getId());

        //StringBuilder query = new StringBuilder("UPDATE tblcarreras SET nombre='" + obj.getNombre() + "', sigla= '" + obj.getSigla() + "',descripcion= '" + obj.getDescripcion() + "',img= '" + obj.getImg() + "' WHERE id= " + obj.getId());
        id = objConexion.ejecutarSimple(query.toString());
        objConexion.desconectar();
        return id;
    }

    @Override
    public ArrayList<ColegioReporte> getListReportesCarrera(int id) {
        ArrayList<ColegioReporte> registros = new ArrayList<ColegioReporte>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "select p.fecha, concat(e.nombre,' ', e.aPaterno,' ', e.aMaterno) as nombre,c.nombre as carrera , p.monto, p.mes as mes from tblpagos p\n"
                    + "inner join tblestudiantes_grupos eg\n"
                    + "on p.idEstudiantes_grupos = eg.id\n"
                    + "inner join tblgrupos g\n"
                    + "on eg.idgrupo = g.id\n"
                    + "inner join tblcarreras c\n"
                    + "on g.idCarrera = c.id\n"
                    + "inner join tblcolegios col\n"
                    + "on g.idColegio = col.id\n"
                    + "inner join tblestudiantes e\n"
                    + "on eg.idEstudiante = e.id\n"
                    + "where col.id =" + id;
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                ColegioReporte obj = new ColegioReporte();
                String _fecha = objResultSet.getString("fecha");
                obj.setFecha(_fecha);

                String _nombre = objResultSet.getString("nombre");
                obj.setNombre(_nombre);

                String _carrera = objResultSet.getString("carrera");
                obj.setCarrera(_carrera);

                String _monto = objResultSet.getString("monto");
                obj.setMonto(_monto);

                String _mes = objResultSet.getString("mes");
                obj.setMes(_mes);

                registros.add(obj);
            }
        } catch (Exception ex) {
            ;
        }
        return registros;
    }

    @Override
    public ArrayList<ColegioReporte> getListReportesGrupo(int id) {
        ArrayList<ColegioReporte> registros = new ArrayList<ColegioReporte>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "select g.id as grupo,p.fecha, ca.nombre as carrera,concat(e.nombre,' ', e.aPaterno,' ', e.aMaterno) as nombre  , p.monto, p.mes from tblcolegios c\n"
                    + "inner join tblgrupos g\n"
                    + "on c.id = g.idColegio\n"
                    + "inner join tblestudiantes_grupos eg\n"
                    + "on g.id = eg.idGrupo\n"
                    + "inner join tblpagos p\n"
                    + "on eg.id = p.idEstudiantes_grupos\n"
                    + "inner join tblcarreras ca\n"
                    + "on ca.id = g.idCarrera\n"
                    + "inner join tblestudiantes e\n"
                    + "on e.id =eg.idEstudiante\n"
                    + "where c.id= " + id;
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                ColegioReporte obj = new ColegioReporte();
                int _grupo = objResultSet.getInt("grupo");
                obj.setGrupo(_grupo);
                String _fecha = objResultSet.getString("fecha");
                obj.setFecha(_fecha);

                String _carrera = objResultSet.getString("carrera");
                obj.setCarrera(_carrera);

                String _nombre = objResultSet.getString("nombre");
                obj.setNombre(_nombre);

                String _monto = objResultSet.getString("monto");
                obj.setMonto(_monto);

                String _mes = objResultSet.getString("mes");
                obj.setMes(_mes);

                registros.add(obj);
            }
        } catch (Exception ex) {
            ;
        }
        return registros;
    }

    @Override
    public ArrayList<ColegioReporte> getListReportesNotasCarrera(int id) {
        ArrayList<ColegioReporte> registros = new ArrayList<ColegioReporte>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "select g.id as grupo,ca.nombre as carrera ,concat(es.nombre,' ', es.aPaterno,' ', es.aMaterno) as nombre , n.nota\n"
                    + "from tblcolegios c\n"
                    + "inner join tblgrupos g\n"
                    + "on c.id = g.idColegio\n"
                    + "inner join tblcarreras ca\n"
                    + "on g.idCarrera = ca.id\n"
                    + "inner join tblestudiantes_grupos eg\n"
                    + "on g.id = eg.idGrupo\n"
                    + "inner join tblestudiantes es\n"
                    + "on eg.idEstudiante = es.id\n"
                    + "inner join tblnotas n\n"
                    + "on es.id = n.idEstudiante_grupo\n"
                    + "where c.id = " + id;
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                ColegioReporte obj = new ColegioReporte();
                int _grupo = objResultSet.getInt("grupo");
                obj.setGrupo(_grupo);

                String _carrera = objResultSet.getString("carrera");
                obj.setCarrera(_carrera);

                String _nombre = objResultSet.getString("nombre");
                obj.setNombre(_nombre);

                int _nota = objResultSet.getInt("nota");
                obj.setNota(_nota);
                registros.add(obj);
            }
        } catch (Exception ex) {
            ;
        }
        return registros;
    }

    @Override
    public ArrayList<ColegioReporte> getListReportesNotasGrupo(int id) {
        ArrayList<ColegioReporte> registros = new ArrayList<ColegioReporte>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "select g.id as grupo,ca.nombre as carrera ,concat(es.nombre,' ', es.aPaterno,' ', es.aMaterno) as nombre , n.nota\n"
                    + "from tblcolegios c\n"
                    + "inner join tblgrupos g\n"
                    + "on c.id = g.idColegio\n"
                    + "inner join tblcarreras ca\n"
                    + "on g.idCarrera = ca.id\n"
                    + "inner join tblestudiantes_grupos eg\n"
                    + "on g.id = eg.idGrupo\n"
                    + "inner join tblestudiantes es\n"
                    + "on eg.idEstudiante = es.id\n"
                    + "inner join tblnotas n\n"
                    + "on es.id = n.idEstudiante_grupo\n"
                    + "where c.id = " + id;
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                ColegioReporte obj = new ColegioReporte();
                int _grupo = objResultSet.getInt("grupo");
                obj.setGrupo(_grupo);

                String _carrera = objResultSet.getString("carrera");
                obj.setCarrera(_carrera);

                String _nombre = objResultSet.getString("nombre");
                obj.setNombre(_nombre);

                int _nota = objResultSet.getInt("nota");
                obj.setNota(_nota);
                registros.add(obj);
            }
        } catch (Exception ex) {
            ;
        }
        return registros;
    }

    @Override
    public ArrayList<CarrerasHabilitadasColegio> getListCarrerasHabilitadas(int id) {
        ArrayList<CarrerasHabilitadasColegio> registros = new ArrayList<CarrerasHabilitadasColegio>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "select  c.id as idcarrera,g.id as idgrupo,c.nombre,g.costo,g.inicio as fechainicio,g.fin as fechafin,h.inicio as horainicio,h.fin as horafin,TIMESTAMPDIFF(MONTH, g.inicio,g.fin) as duracion\n"
                    + "from tblgrupos g\n"
                    + "inner join tblcarreras c\n"
                    + "on g.idCarrera = c.id\n"
                    + "inner join tblgestiones ge\n"
                    + "on g.idGestion = ge.id\n"
                    + "inner join tbldias d\n"
                    + "on g.id = d.idGrupo\n"
                    + "inner join tblhoras h\n"
                    + "on d.idHora = h.id\n"
                    + "inner join tblcolegios co\n"
                    + "on co.id = g.idColegio\n"
                    + "where g.idColegio =" + id;
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                CarrerasHabilitadasColegio obj = new CarrerasHabilitadasColegio();
                int _idcarrera = objResultSet.getInt("idcarrera");
                obj.setIdcarrera(_idcarrera);
                int _idgrupo = objResultSet.getInt("idgrupo");
                obj.setIdgrupo(_idgrupo);
                String _nombre = objResultSet.getString("nombre");
                obj.setNombre(_nombre);
                String _carrera = objResultSet.getString("costo");
                obj.setCosto(_carrera);
                String _fechainicio = objResultSet.getString("fechainicio");
                obj.setFechainicio(_fechainicio);
                String _fechafin = objResultSet.getString("fechafin");
                obj.setFechafin(_fechafin);
                String _horainicio = objResultSet.getString("horainicio");
                obj.setHorainicio(_horainicio);
                String _horafin = objResultSet.getString("horafin");
                obj.setHorafin(_horafin);
                String _duracion = objResultSet.getString("duracion");
                obj.setDuracion(_duracion);
                registros.add(obj);
            }
        } catch (Exception ex) {
            ;
        }
        return registros;
    }

}
