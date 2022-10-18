package expat.model;

public class Custo {
	private Long id;
	private Cidade codCidade;
	private int codUsuario;
	private double almoco;
	private double aluguel;
	private double cestabasica;
	private double onibus;

	public Custo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cidade getCodCidade() {
		return codCidade;
	}

	public void setCodCidade(Cidade codCidade) {
		this.codCidade = codCidade;
	}

	public int getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}

	public double getAlmoco() {
		return almoco;
	}

	public void setAlmoco(double almoco) {
		this.almoco = almoco;
	}

	public double getAluguel() {
		return aluguel;
	}

	public void setAluguel(double aluguel) {
		this.aluguel = aluguel;
	}

	public double getCestabasica() {
		return cestabasica;
	}

	public void setCestabasica(double cestabasica) {
		this.cestabasica = cestabasica;
	}

	public double getOnibus() {
		return onibus;
	}

	public void setOnibus(double onibus) {
		this.onibus = onibus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Custo other = (Custo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Custo [id=" + id + ", codCidade=" + codCidade + ", codUsuario=" + codUsuario + ", almoco=" + almoco
				+ ", aluguel=" + aluguel + ", cestabasica=" + cestabasica + ", onibus=" + onibus + "]";
	}

}
