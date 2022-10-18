package expat.rest.client;
import java.util.List;

import javax.ws.rs.core.Response;

public interface RESTClientInterface<T> {
	public static final int STATUS_OK = Response.Status.OK.getStatusCode();
	public static final int STATUS_UNAUTHORIZED = Response.Status.UNAUTHORIZED.getStatusCode();
	public static final int STATUS_NOT_ACCEPTABLE = Response.Status.NOT_ACCEPTABLE.getStatusCode();

    public static final String REST_WEBSERVICE_URL = System.getProperty("API_URL");
    public static final String REST_CADASTRO_URL = "api/usuario/";
    public static final String REST_CIDADE_URL = "api/cidade/";
    public static final String REST_CUSTO_URL = "api/custo/";

    public List<T> findAll();
    public T find(Long id);
    public T create(T obj);
    public T edit(T obj);
    public boolean delete(Long id);
}
