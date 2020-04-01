/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consola;

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
    private String username;

    public MessageCorreo(String correo, String username, int id, String asunto) {
        this.correo = correo;
        this.asunto = asunto;
        this.username = username;
        this.id = id;
        verificacion = VerificacionHtml();
        recuperacion = RecuperacionHtml();
    }

    private String VerificacionHtml() {
        String verificacion = "<a href=\"http://localhost:8080/bth/\">CLICK PARA VERIFICAR TU CUENTA</a>";
        return verificacion;
    }

    private String RecuperacionHtml() {
        String verificacion = "<!doctype html>\n"
                + "<html lang=‘en’>\n"
                + "<head>\n"
                + "  <meta charset=‘utf-8’>\n"
                + "  <title>submit demo</title>\n"
                + "  <style>\n"
                + "  p {\n"
                + "    margin: 0;\n"
                + "    color: blue;\n"
                + "  }\n"
                + "  div,p {\n"
                + "    margin-left: 10px;\n"
                + "  }\n"
                + "  span {\n"
                + "    color: red;\n"
                + "  }\n"
                + "  </style>\n"
                + "  <script src=‘https://code.jquery.com/jquery-3.4.1.js’></script>\n"
                + "</head>\n"
                + "<body>\n"
                + " \n"
                + "<p>Type 'correct' to validate.</p>\n"
                + "<form action=‘javascript:alert( 'success!' );’>\n"
                + "  <div>\n"
                + "    <input type=‘text’>\n"
                + "    <input type=‘submit’>\n"
                + "  </div>\n"
                + "</form>\n"
                + "<span></span>\n"
                + " \n"
                + "<script>\n"
                + "$( ‘form’ ).submit(function( event ) {\n"
                + "  if ( $( ‘input’ ).first().val() === ‘correct’ ) {\n"
                + "    $( ‘span’ ).text( ‘Validated...’ ).show();\n"
                + "    return;\n"
                + "  }\n"
                + " \n"
                + "  $( ‘span’ ).text( ‘Not valid!’ ).show().fadeOut( 1000 );\n"
                + "  event.preventDefault();\n"
                + "});\n"
                + "</script>\n"
                + " \n"
                + "</body>\n"
                + "</html>";

        return verificacion;
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

}
