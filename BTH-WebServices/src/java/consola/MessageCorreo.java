/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consola;

import java.util.Base64;

/**
 *
 * @author MarceloVillca
 */
public class MessageCorreo {

    private String correo;
    private String asunto;
    private String verificacion;
    private String recuperacion;
    private String codigo;
    private int id;

    public MessageCorreo(String correo, String username, int id, String asunto, String tipo) {
        this.correo = correo;
        this.asunto = asunto;
        this.id = id;
        verificacion = VerificacionHtml(tipo,username);
//        recuperacion = RecuperacionHtml();
        // hace referencia si es docente o colegio
    }

    private String VerificacionHtml(String tipo,String username) {
        String token = codificador(username);
        String verificacion = "<a href=\"http://localhost:8080/bth/verificar.html?tipo=" + tipo + "&token=" + token + "\">CLICK PARA VERIFICAR TU CUENTA</a>";
        return verificacion;
    }

    private String RecuperacionHtml() {
        String verificacion = "";

        return verificacion;
    }

    private String codificador(String username) {
        String token = username;
        byte[] encodedBytes = Base64.getEncoder().encode(token.getBytes());
        String code = new String(encodedBytes);
        return code;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getVerificacion() {
        return verificacion;
    }

    public void setVerificacion(String verificacion) {
        this.verificacion = verificacion;
    }

    public String getRecuperacion() {
        return recuperacion;
    }

    public void setRecuperacion(String recuperacion) {
        this.recuperacion = recuperacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
