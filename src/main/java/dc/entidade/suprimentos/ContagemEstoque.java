package dc.entidade.suprimentos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.Empresa;

@Entity
@Table(name = "estoque_contagem_cabecalho")
@SuppressWarnings("serial")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class ContagemEstoque extends AbstractModel<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cnt")
	@SequenceGenerator(name = "cnt", sequenceName = "estoque_contagem_cabecalho_id_seq", allocationSize = 1)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ID_EMPRESA", nullable = false)
	private Empresa empresa;

	@Caption("Data")
	@Column(name="data_contagem")
	@Temporal(TemporalType.DATE)
	private Date data;

	@Caption("Estoque Atualizado")
	@Column(name="estoque_atualizado")
	private String estoqueAtualizado;
	
	@OneToMany(mappedBy = "contagem", orphanRemoval = true,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@Caption("Detalhe")
	private List<ContagemEstoqueDetalhe> contagemDetalhes;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getEstoqueAtualizado() {
		return estoqueAtualizado;
	}

	public void setEstoqueAtualizado(String estoqueAtualizado) {
		this.estoqueAtualizado = estoqueAtualizado;
	}

	public List<ContagemEstoqueDetalhe> getContagemDetalhes() {
		return contagemDetalhes;
	}

	public void setContagemDetalhes(List<ContagemEstoqueDetalhe> contagemDetalhes) {
		this.contagemDetalhes = contagemDetalhes;
	}

	public ContagemEstoqueDetalhe addContagemDetalhe(ContagemEstoqueDetalhe contagemEstoqueDetalhe) {
		if(contagemDetalhes==null) contagemDetalhes = new ArrayList();
		getContagemDetalhes().add(contagemEstoqueDetalhe);
		contagemEstoqueDetalhe.setContagem(this);
		return contagemEstoqueDetalhe;
	}
	

}
