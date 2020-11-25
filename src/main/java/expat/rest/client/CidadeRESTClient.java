package expat.rest.client;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import expat.model.Cidade;
import expat.util.SessionContext;

public class CidadeRESTClient implements RESTClientInterface<Cidade> {
	private Response response;
	private String token = (String) SessionContext.getInstance().getAttribute("token");

	@Override
	public List<Cidade> findAll() {
		this.response = ClientBuilder.newClient()
                .target(REST_WEBSERVICE_URL + REST_CIDADE_URL)
                .request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.get();

		List<Cidade> categorias = this.response
				.readEntity(new GenericType<List<Cidade>>() {});
		return categorias;
	}

	@Override
	public Cidade find(Long id) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_CIDADE_URL + id)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.get();
		if (response.getStatus() == STATUS_OK) {
			Cidade cidade = this.response.readEntity(Cidade.class);
			return cidade;
		}
		return null;
	}

	public Cidade busca(String estado, String municipio) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_CIDADE_URL + "busca/" + estado + "/" + municipio)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.get();
		if (response.getStatus() == STATUS_OK) {
			Cidade cidade = this.response.readEntity(Cidade.class);
			return cidade;
		}
		return null;
	}

	
	@Override
	public Cidade create(Cidade obj) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_CIDADE_URL)
				.queryParam("cidade", obj)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.post(Entity.entity(obj, MediaType.APPLICATION_JSON));
		Cidade cidade = this.response.readEntity(Cidade.class);

		return cidade;
	}

	@Override
	public Cidade edit(Cidade obj) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_CIDADE_URL)
				.queryParam("cidade", obj)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.put(Entity.entity(obj, MediaType.APPLICATION_JSON));
		if (this.response.getStatus() == STATUS_OK) {
			Cidade cidade = this.response.readEntity(Cidade.class);
			return cidade;
		}
		return null;
	}

	@Override
	public boolean delete(Long id) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_CIDADE_URL + id)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.delete();
		return this.response.getStatus() == STATUS_OK;
	}

}
