package dto;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public class Usuario{

	private int usuarioId;
	private String nombreCompleto;
	private String username;
	private String password;

	public Usuario(){
	
	}

	public void setUsuarioId(int usuarioId){
		this.usuarioId = usuarioId;
	}

	public int getUsuarioId(){
		return this.usuarioId;
	}

	public void setNombreCompleto(String nombreCompleto){
		this.nombreCompleto = nombreCompleto;
	}

	public String getNombreCompleto(){
		return this.nombreCompleto;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return this.username;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return this.password;
	}

}

