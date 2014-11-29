package dc.entidade.suprimentos.compra;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

/**
 * The persistent class for the compra_cotacao database table.
 * 
 */
@Entity
@Table(name = "compra_cotacao")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class CotacaoEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compra_cotacao_id_seq")
	@SequenceGenerator(name = "compra_cotacao_id_seq", sequenceName = "compra_cotacao_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Temporal(TemporalType.DATE)
	@Column(name = "data_cotacao")
	@Caption("Data da cotação")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataCotacao;

	@Field
	@Column(name = "descricao")
	@Caption("Descrição")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String descricao;

	@Field
	@Column(name = "situacao")
	@Caption("Situação")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String situacao;

	@OneToMany(mappedBy = "cotacao", cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	private List<FornecedorCotacaoEntity> compraFornecedorCotacaos = new ArrayList<>();

	@OneToMany(mappedBy = "cotacao", cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	private List<ReqCotacaoDetalheEntity> compraReqCotacaoDetalhes = new ArrayList<>();

	/**
	 * CONSTRUTOR
	 */

	public CotacaoEntity() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return this.id;
	}

	public Date getDataCotacao() {
		return this.dataCotacao;
	}

	public void setDataCotacao(Date dataCotacao) {
		this.dataCotacao = dataCotacao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSituacao() {
		return this.situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public List<FornecedorCotacaoEntity> getCompraFornecedorCotacaos() {
		return compraFornecedorCotacaos;
	}

	public void setCompraFornecedorCotacaos(
			List<FornecedorCotacaoEntity> compraFornecedorCotacaos) {
		this.compraFornecedorCotacaos = compraFornecedorCotacaos;
	}

	public List<ReqCotacaoDetalheEntity> getCompraReqCotacaoDetalhes() {
		return compraReqCotacaoDetalhes;
	}

	public void setCompraReqCotacaoDetalhes(
			List<ReqCotacaoDetalheEntity> compraReqCotacaoDetalhes) {
		this.compraReqCotacaoDetalhes = compraReqCotacaoDetalhes;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}