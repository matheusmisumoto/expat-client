package expat.control;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import expat.dto.CredenciaisDTO;
import expat.rest.client.UsuarioRESTClient;
import expat.util.SessionContext;

@ManagedBean
@RequestScoped
public class UsuarioBean {
	private CredenciaisDTO usuario = new CredenciaisDTO();

	public UsuarioBean() {
	}

	public CredenciaisDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(CredenciaisDTO usuario) {
		this.usuario = usuario;
	}
	
	public String autenticar() {
		UsuarioRESTClient client = new UsuarioRESTClient();
		if (client.authenticate(usuario)) {
			return "/protected/cidades?faces-redirect=true";
		}
		FacesMessage msg = new FacesMessage("Login/senha incorretos");
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return null;
	}
			
	public String logout() {
		SessionContext.getInstance().encerrarSessao();
		return "/login?faces-redirect=true";
	}
}
