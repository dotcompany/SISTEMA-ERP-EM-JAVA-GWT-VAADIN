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
@Table(name = "tipo_nota_fiscal")
@SuppressWarnings("serial")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class TipoNotaFiscal extends AbstractMultiEmpresaModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tnf")
	@SequenceGenerator(name = "tnf", sequenceName = "tipo_nota_fiscal_id_seq", allocationSize = 1)
	private Integer id;
	
	@Caption("Modelo")
	String modelo;
	
	@Caption("Série")
	String serie;
	
	@Caption("Nome")
	String nome;
	
	@Caption("Descrição")
	String descricao;
	
	String template;
	
	@Column(name="numero_itens")
	Integer numeroItens;
	
	@Column(name="ultimo_impresso")
	Integer ultimoImpresso;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
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

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Integer getNumeroItens() {
		return numeroItens;
	}

	public void setNumeroItens(Integer numeroItens) {
		this.numeroItens = numeroItens;
	}

	public Integer getUltimoImpresso() {
		return ultimoImpresso;
	}

	public void setUltimoImpresso(Integer ultimoImpresso) {
		this.ultimoImpresso = ultimoImpresso;
	}
	
	
	
	
	
}
