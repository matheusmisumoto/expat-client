package expat.control;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import expat.model.Usuario;
import expat.rest.client.CadastroRESTClient;

@ManagedBean
@RequestScoped
public class CadastroBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios;
	private String consulta;

	public CadastroBean()  {
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public List<Usuario> getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	public String getConsulta() {
		return consulta;
	}


	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}


	public String gravar() {
		CadastroRESTClient rest = new CadastroRESTClient();
		this.usuario.setPerfis(new HashSet<>(Arrays.asList(2)));
		rest.create(usuario);

		return "/login?faces-redirect=true";
	}

}
