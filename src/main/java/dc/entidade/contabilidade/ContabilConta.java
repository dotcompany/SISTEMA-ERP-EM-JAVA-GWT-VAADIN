package dc.entidade.contabilidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

/**
 * <p>
 * Title: T2Ti ERP
 * </p>
 * <p>
 * Description: VO relacionado a tabela [CONTABIL_CONTA]
 * </p>
 * 
 * <p>
 * The MIT License
 * </p>
 * 
 * <p>
 * Copyright: Copyright (C) 2010 T2Ti.COM
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * 
 * The author may be contacted at: t2ti.com@gmail.com
 * </p>
 * 
 * @author Claudio de Barros (t2ti.com@gmail.com)
 * @version 1.0
 */
@Entity
@Table(name = "CONTABIL_CONTA")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ContabilConta extends AbstractMultiEmpresaModel<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Column(name = "CLASSIFICACAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String classificacao;

	@Column(name = "TIPO", columnDefinition = "bpchar")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String tipo;

	@Field
	@Column(name = "DESCRICAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String descricao;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_INCLUSAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataInclusao;

	@Column(name = "SITUACAO", columnDefinition = "bpchar")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String situacao;

	@Column(name = "NATUREZA", columnDefinition = "bpchar")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String natureza;

	@Column(name = "PATRIMONIO_RESULTADO", columnDefinition = "bpchar")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String patrimonioResultado;

	@Column(name = "LIVRO_CAIXA", columnDefinition = "bpchar")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String livroCaixa;

	@Column(name = "DFC", columnDefinition = "bpchar")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String dfc;

	@Column(name = "ORDEM")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String ordem;

	@Column(name = "CODIGO_REDUZIDO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigoReduzido;

	@Column(name = "CODIGO_EFD", columnDefinition = "bpchar")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigoEfd;

	@JoinColumn(name = "ID_PLANO_CONTA_REF_SPED", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private PlanoContaRefSped planoContaRefSped;

	@JoinColumn(name = "ID_PLANO_CONTA", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private PlanoConta planoConta;

	@JoinColumn(name = "ID_CONTABIL_CONTA", referencedColumnName = "ID")
	@ManyToOne
	private ContabilConta contabilConta;

	/**
	 * @autor Gutemberg A. Da Silva
	 * 
	 * @module FINANCEIRO
	 */

	@OneToMany(mappedBy = "contabilConta", fetch = FetchType.LAZY)
	private List<ContaCaixa> contaCaixaList;

	/**
	 * CONSTRUTOR
	 */

	public ContabilConta() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getNatureza() {
		return natureza;
	}

	public void setNatureza(String natureza) {
		this.natureza = natureza;
	}

	public String getPatrimonioResultado() {
		return patrimonioResultado;
	}

	public void setPatrimonioResultado(String patrimonioResultado) {
		this.patrimonioResultado = patrimonioResultado;
	}

	public String getLivroCaixa() {
		return livroCaixa;
	}

	public void setLivroCaixa(String livroCaixa) {
		this.livroCaixa = livroCaixa;
	}

	public String getDfc() {
		return dfc;
	}

	public void setDfc(String dfc) {
		this.dfc = dfc;
	}

	public String getOrdem() {
		return ordem;
	}

	public void setOrdem(String ordem) {
		this.ordem = ordem;
	}

	public String getCodigoReduzido() {
		return codigoReduzido;
	}

	public void setCodigoReduzido(String codigoReduzido) {
		this.codigoReduzido = codigoReduzido;
	}

	public String getCodigoEfd() {
		return codigoEfd;
	}

	public void setCodigoEfd(String codigoEfd) {
		this.codigoEfd = codigoEfd;
	}

	public PlanoContaRefSped getPlanoContaRefSped() {
		return planoContaRefSped;
	}

	public void setPlanoContaRefSped(PlanoContaRefSped planoContaRefSped) {
		this.planoContaRefSped = planoContaRefSped;
	}

	public PlanoConta getPlanoConta() {
		return planoConta;
	}

	public void setPlanoConta(PlanoConta planoConta) {
		this.planoConta = planoConta;
	}

	public ContabilConta getContabilConta() {
		return contabilConta;
	}

	public void setContabilConta(ContabilConta contabilConta) {
		this.contabilConta = contabilConta;
	}

	@Override
	public String toString() {
		if (codigoReduzido == null) {
			return descricao;
		}

		return codigoReduzido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoReduzido == null) ? 0 : codigoReduzido.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContabilConta other = (ContabilConta) obj;
		if (codigoReduzido == null) {
			if (other.codigoReduzido != null)
				return false;
		} else if (!codigoReduzido.equals(other.codigoReduzido))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}