package utils;

public class Credentials {

	private String usuario;
	private String password;
	private String ip;
	
	public Credentials() {
		usuario = "remote";
		password = "549Dijpa$!";
		ip = "192.168.1.128";
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getIp() {
		return ip;
	}
}
