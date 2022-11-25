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

@ManagedBean
@SessionScoped
public class CidadeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Cidade cidade = new Cidade();
	private List<Cidade> cidades;
	private String consulta;
	private Custo custo;

	public Custo getCusto() {
		return custo;
	}

	public void setCusto(Custo custo) {
		this.custo = custo;
	}

	public CidadeBean() {
		CidadeRESTClient rest = new CidadeRESTClient();
		cidades = rest.findAll();
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	public String pagPrincipal() {
		CidadeRESTClient rest = new CidadeRESTClient();
		cidades = rest.findAll();
		return "/protected/cidades?faces-redirect=true";
	}

	public String pagCidade() {
		this.cidade = new Cidade();
		return "/protected/cidade?faces-redirect=true";
	}

	public String pagCidade(Cidade cidade) {
		this.cidade = cidade;
		return "/protected/cidade?faces-redirect=true";
	}

	public String gravar() {
		CidadeRESTClient rest = new CidadeRESTClient();
		if (cidade.getId() == null) {
			rest.create(cidade);
			cidade = new Cidade();
		} else {
			cidade = rest.edit(cidade);
		}
		return pagPrincipal();
	}

	public String gravarComCusto() {
		CidadeRESTClient rest = new CidadeRESTClient();
		rest.create(cidade);

		this.custo = new Custo();
		custo.setCodCidade(cidade);
		return "/protected/custo?faces-redirect=true";
	}

	public String excluir(Cidade c) {
		CidadeRESTClient rest = new CidadeRESTClient();
		if (!rest.delete(c.getId())) {

			FacesMessage msg = new FacesMessage("Não foi possível excluir a cidade " + c.getMunicipio());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} else {
			cidades.remove(c);
		}
		return null;
	}

}
