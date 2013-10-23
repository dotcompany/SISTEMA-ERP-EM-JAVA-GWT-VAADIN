package dc.entidade.comercial;

import java.math.BigDecimal;

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

import dc.entidade.framework.AbstractMultiEmpresaModel;

@Entity
@Table(name = "venda_condicoes_pagamento")
@SuppressWarnings("serial")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class CondicaoPagamento extends AbstractMultiEmpresaModel<Integer>

{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cnd")
	@SequenceGenerator(name = "cnd", sequenceName = "venda_condicoes_pagamento_id_seq", allocationSize = 1)
	private Integer id;

	String nome;

	String descricao;
	
	@Column(name="faturamento_minimo")
	BigDecimal faturamentoMinimo;
	
	@Column(name="faturamento_maximo")
	BigDecimal faturamentoMaximo;
	
	@Column(name="indice_correcao")
	BigDecimal indiceCorrecao;
	
	@Column(name="dias_tolerancia")
	Integer diasTolerancia;
	
	@Column(name="valor_tolerancia")
	BigDecimal valorTolerancia;
	
	@Column(name="prazo_medio")
	Integer prazoMedio;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public BigDecimal getFaturamentoMinimo() {
		return faturamentoMinimo;
	}

	public void setFaturamentoMinimo(BigDecimal faturamentoMinimo) {
		this.faturamentoMinimo = faturamentoMinimo;
	}

	public BigDecimal getFaturamentoMaximo() {
		return faturamentoMaximo;
	}

	public void setFaturamentoMaximo(BigDecimal faturamentoMaximo) {
		this.faturamentoMaximo = faturamentoMaximo;
	}

	public BigDecimal getIndiceCorrecao() {
		return indiceCorrecao;
	}

	public void setIndiceCorrecao(BigDecimal indiceCorrecao) {
		this.indiceCorrecao = indiceCorrecao;
	}

	public Integer getDiasTolerancia() {
		return diasTolerancia;
	}

	public void setDiasTolerancia(Integer diasTolerancia) {
		this.diasTolerancia = diasTolerancia;
	}

	public BigDecimal getValorTolerancia() {
		return valorTolerancia;
	}

	public void setValorTolerancia(BigDecimal valorTolerancia) {
		this.valorTolerancia = valorTolerancia;
	}

	public Integer getPrazoMedio() {
		return prazoMedio;
	}

	public void setPrazoMedio(Integer prazoMedio) {
		this.prazoMedio = prazoMedio;
	}
	
	
	
	

	
}
