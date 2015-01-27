package dc.entidade.comercial;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;


import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.AbstractMultiEmpresaModel;

@Entity
@Table(name = "nota_fiscal_tipo")
@SuppressWarnings("serial")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class NotaFiscalTipo extends AbstractMultiEmpresaModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tnf")
	@SequenceGenerator(name = "tnf", sequenceName = "nota_fiscal_tipo_id_seq", allocationSize = 1)
	private Integer id;
	
	@Caption("Série")
	String serie;
	
	@Caption("Nome")
	String nome;
	
	@Caption("Descrição")
	String descricao;
	
	@Column(name="ultimo_impresso")
	Integer ultimoImpresso;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getUltimoImpresso() {
		return ultimoImpresso;
	}

	public void setUltimoImpresso(Integer ultimoImpresso) {
		this.ultimoImpresso = ultimoImpresso;
	}

	@Override
	public String toString() {
		return nome;
	}
	
	
	
}
