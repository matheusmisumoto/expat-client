package expat.control;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import expat.dto.MediaDTO;
import expat.model.Cidade;
import expat.model.Custo;
import expat.rest.client.CidadeRESTClient;
import expat.rest.client.CustoRESTClient;

@ManagedBean
@RequestScoped
public class BuscaBean {
	private Cidade cidade = new Cidade();
	private Cidade resultado = new Cidade();
	private MediaDTO media = new MediaDTO();
	private Custo custo = new Custo();
	
	public BuscaBean() {		
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public MediaDTO getMedia() {
		return media;
	}

	public void setMedia(MediaDTO media) {
		this.media = media;
	}

	public Custo getCusto() {
		return custo;
	}

	public void setCusto(Custo custo) {
		this.custo = custo;
	}

	public String buscaResultado() {
		CidadeRESTClient rest = new CidadeRESTClient();
		Cidade resultado = rest.busca(cidade.getEstado(), cidade.getMunicipio());

		if(resultado != null){
			CustoRESTClient restCusto = new CustoRESTClient();
	
			this.media = restCusto.media(resultado.getId());
			
			return "/resultado";
		} else {
			return "/index";
		}

	}
	public Cidade getResultado() {
		return resultado;
	}
	public void setResultado(Cidade resultado) {
		this.resultado = resultado;
	}

}
