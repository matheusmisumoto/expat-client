package expat.control;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import expat.model.Cidade;
import expat.model.Custo;
import expat.rest.client.CidadeRESTClient;
import expat.rest.client.CustoRESTClient;

@ManagedBean
@SessionScoped
public class CustoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Custo custo = new Custo();
	private List<Custo> custos;
	private Cidade cidade = new Cidade();
	
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	private String consulta;

	public CustoBean() {
		CustoRESTClient rest = new CustoRESTClient();
		custos = rest.findAll();
	}

	public Custo getCusto() {
		return custo;
	}

	public void setCusto(Custo custo) {
		this.custo = custo;
	}

	public List<Custo> getCustos() {
		return custos;
	}

	public void setCustos(List<Custo> custos) {
		this.custos = custos;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	public String pagPrincipal() {
		CustoRESTClient rest = new CustoRESTClient();
		custos = rest.findAll();
		return "/protected/categoria?faces-redirect=true";
	}

	public String pagCusto() {
		this.custo = new Custo();
		return "/protected/custo?faces-redirect=true";
	}

	public String pagCusto(Custo custo) {
		this.custo = custo;
		return "/protected/editar-custo?faces-redirect=true";
	}

	public String pagCusto(Cidade cidade) {
		this.cidade = cidade;
		this.custo = new Custo();
		custo.setCodCidade(cidade);
		return "/protected/custo2?faces-redirect=true";
	}

	public String gravar() {
		CustoRESTClient rest = new CustoRESTClient();
		if (custo.getId() == null) {
			rest.create(custo);
			custo = new Custo();
		} else {
			custo = rest.edit(custo);
		}
		return null;
	}

	public String excluir(Custo c) {
		CustoRESTClient rest = new CustoRESTClient();
		if (!rest.delete(c.getId())) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage("Não foi possível excluir o registro");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		} else {
			custos.remove(c);
		}
		return null;
	}
	
	
	public String buscaCidade() {
		CidadeRESTClient rest = new CidadeRESTClient();
		Cidade resultado = rest.busca(cidade.getEstado(), cidade.getMunicipio());

		if(resultado != null){
			this.custo = new Custo();
			custo.setCodCidade(resultado);
			custo.setAlmoco(0);
			custo.setAluguel(0);
			custo.setCestabasica(40);
			custo.setOnibus(0);
			
			return "/protected/novo-custo-registro?faces-redirect=true";
		} else {
			return "/protected/cidade";
		}
	}
}
