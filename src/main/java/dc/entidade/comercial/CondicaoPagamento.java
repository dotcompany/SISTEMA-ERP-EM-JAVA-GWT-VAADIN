package dc.entidade.comercial;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;

@Entity
@Table(name = "venda_condicoes_pagamento")
@SuppressWarnings("serial")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class CondicaoPagamento extends AbstractMultiEmpresaModel<Integer> {
	
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Nome")
	@Column(name="nome")
	@NotNull(message = "Nome é Obrigatório!")
	String nome;

	@Field
	@Column(name="descricao")
	@Caption("Descrição")
	String descricao;
	
	@Field
	@Column(name="faturamento_minimo")
	BigDecimal faturamentoMinimo;
	
	@Field
	@Column(name="faturamento_maximo")
	BigDecimal faturamentoMaximo;
	
	@Field
	@Column(name="indice_correcao")
	BigDecimal indiceCorrecao;
	
	@Field
	@Column(name="dias_tolerancia")
	Integer diasTolerancia;
	
	@Field
	@Column(name="valor_tolerancia")
	BigDecimal valorTolerancia;
	
	@Field
	@Column(name="prazo_medio")
	Integer prazoMedio;
	
	@OneToMany(mappedBy="condicaoPagamento",cascade=CascadeType.ALL,fetch= FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	List<ParcelaCondicaoPagamento> parcelas = new ArrayList<ParcelaCondicaoPagamento>();

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

	public List<ParcelaCondicaoPagamento> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<ParcelaCondicaoPagamento> parcelas) {
		this.parcelas = parcelas;
	}
	
	public void adicionarParcela(ParcelaCondicaoPagamento parcela){
	    if(parcelas == null) parcelas = new ArrayList<ParcelaCondicaoPagamento>();
		getParcelas().add(parcela);
		parcela.setCondicaoPagamento(this);
	}

	@Override
	public String toString() {
		return  nome ;
	}
	
   @Override
   public boolean equals(Object obj) {
       if (this == obj) {
           return true;
       }

       if (!(obj instanceof CondicaoPagamento)) {
           return false;
       }

       CondicaoPagamento that = (CondicaoPagamento) obj;
       EqualsBuilder eb = new EqualsBuilder();
       eb.append(getId(), that.getId());
       return eb.isEquals();
   }

   @Override
   public int hashCode() {
       if (getId() == null) {
           return super.hashCode();
       } else {
           return new HashCodeBuilder()
                   .append(id)
                   .toHashCode();
       }
   }
	
	

	
}
