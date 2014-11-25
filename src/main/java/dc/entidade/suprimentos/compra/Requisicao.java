package dc.entidade.suprimentos.compra;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboValue;
import dc.entidade.pessoal.Colaborador;
import dc.entidade.suprimentos.RequisicaoDetalhe;

/**
 * The persistent class for the compra_requisicao database table.
 * 
 */
@Entity
@Table(name = "compra_requisicao")
@SuppressWarnings("serial")
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class Requisicao extends AbstractMultiEmpresaModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Caption("Id")
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_requisicao")
	@Caption("Data de Requisição")
	private Date dataRequisicao;

	@Lob
	@Field
	@Caption("Observacao")
	@Column(name = "OBSERVACAO")
	@Type(type = "text")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String observacao;

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_colaborador")
	@Caption("Requisitante")
	private Colaborador colaborador;

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_compra_tipo_requisicao")
	@Caption("Tipo Requisição")
	private TipoRequisicao tipoRequisicao;

	@OneToMany(mappedBy = "requisicao", cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.JOIN)
	private List<RequisicaoDetalhe> requisicaoDetalhes = new ArrayList<>();

	public Requisicao() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataRequisicao() {
		return this.dataRequisicao;
	}

	public void setDataRequisicao(Date dataRequisicao) {
		this.dataRequisicao = dataRequisicao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public TipoRequisicao getTipoRequisicao() {
		return this.tipoRequisicao;
	}

	public void setTipoRequisicao(TipoRequisicao tipoRequisicao) {
		this.tipoRequisicao = tipoRequisicao;
	}

	public List<RequisicaoDetalhe> getRequisicaoDetalhes() {
		return this.requisicaoDetalhes;
	}

	public RequisicaoDetalhe addRequisicaoDetalhe(
			RequisicaoDetalhe compraRequisicaoDetalhe) {
		getRequisicaoDetalhes().add(compraRequisicaoDetalhe);
		compraRequisicaoDetalhe.setRequisicao(this);

		return compraRequisicaoDetalhe;
	}

	public RequisicaoDetalhe removeRequisicaoDetalhe(
			RequisicaoDetalhe compraRequisicaoDetalhe) {
		getRequisicaoDetalhes().remove(compraRequisicaoDetalhe);
		compraRequisicaoDetalhe.setRequisicao(null);

		return compraRequisicaoDetalhe;
	}

}