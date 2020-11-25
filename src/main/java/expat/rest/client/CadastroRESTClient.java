package expat.rest.client;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import expat.model.Usuario;
import expat.util.SessionContext;

public class CadastroRESTClient implements RESTClientInterface<Usuario> {
	private Response response;
	private String token = (String) SessionContext.getInstance().getAttribute("token");

	@Override
	public Usuario find(Long id) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_CADASTRO_URL + id)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.get();
		if (response.getStatus() == STATUS_OK) {
			Usuario usuario = this.response.readEntity(Usuario.class);
			return usuario;
		}
		return null;
	}


	@Override
	public Usuario create(Usuario obj) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_CADASTRO_URL)
				.queryParam("usuario", obj)
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(obj, MediaType.APPLICATION_JSON));
		return null;
	}

	@Override
	public Usuario edit(Usuario obj) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_CADASTRO_URL)
				.queryParam("usuario", obj)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.put(Entity.entity(obj, MediaType.APPLICATION_JSON));
		if (this.response.getStatus() == STATUS_OK) {
			Usuario usuario = this.response.readEntity(Usuario.class);
			return usuario;
		}
		return null;
	}

	@Override
	public boolean delete(Long id) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_CADASTRO_URL + id)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.delete();
		return this.response.getStatus() == STATUS_OK;
	}


	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

