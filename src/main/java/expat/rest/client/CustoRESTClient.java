package expat.rest.client;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import expat.dto.MediaDTO;
import expat.model.Custo;
import expat.util.SessionContext;

public class CustoRESTClient implements RESTClientInterface<Custo> {
	private Response response;
	private String token = (String) SessionContext.getInstance().getAttribute("token");

	@Override
	public List<Custo> findAll() {
		this.response = ClientBuilder.newClient()
                .target(REST_WEBSERVICE_URL + REST_CUSTO_URL)
                .request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.get();

		List<Custo> categorias = this.response
				.readEntity(new GenericType<List<Custo>>() {});
		return categorias;
	}

	@Override
	public Custo find(Long id) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_CUSTO_URL + id)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.get();
		if (response.getStatus() == STATUS_OK) {
			Custo custo = this.response.readEntity(Custo.class);
			return custo;
		}
		return null;
	}


	public MediaDTO media(Long id) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_CUSTO_URL + "cidade/" + id)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.get();
		if (response.getStatus() == STATUS_OK) {
			MediaDTO custo = this.response.readEntity(MediaDTO.class);
			return custo;
		}
		return null;
	}

	@Override
	public Custo create(Custo obj) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_CUSTO_URL)
				.queryParam("custo", obj)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.post(Entity.entity(obj, MediaType.APPLICATION_JSON));
		Custo custo = this.response.readEntity(Custo.class);

		return custo;
	}

	@Override
	public Custo edit(Custo obj) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_CUSTO_URL)
				.queryParam("custo", obj)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.put(Entity.entity(obj, MediaType.APPLICATION_JSON));
		if (this.response.getStatus() == STATUS_OK) {
			Custo custo = this.response.readEntity(Custo.class);
			return custo;
		}
		return null;
	}

	@Override
	public boolean delete(Long id) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_CUSTO_URL + id)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.delete();
		return this.response.getStatus() == STATUS_OK;
	}

}
