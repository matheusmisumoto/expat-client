package expat.rest.client;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import expat.dto.CredenciaisDTO;
import expat.util.SessionContext;


public class UsuarioRESTClient {
	private Response response;

	public boolean authenticate(CredenciaisDTO usuario) {
		this.response = ClientBuilder.newClient().
				target(System.getenv("API_URL") + "login").
	    		queryParam("usuario", usuario).
	    		request(MediaType.APPLICATION_JSON).
	    		post(Entity.entity(usuario, MediaType.APPLICATION_JSON));
		if (response.getStatus() == Response.Status.OK.getStatusCode()) {
			String _admin = (response.getHeaderString("x-admin"));
			usuario.setAdmin(_admin.equals("yes"));
			SessionContext.getInstance().setAttribute("usuario", usuario);
			String token = response.getHeaderString("Authentication");
			SessionContext.getInstance().setAttribute("token", token);
			return true;
		}
		return false;
	}
}

