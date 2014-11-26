package dc.entidade.suprimentos.estoque;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.pessoal.Colaborador;
import dc.entidade.suprimentos.RequisicaoInternaDetalheEntity;

@Entity
@Table(name = "requisicao_interna_cabecalho")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class RequisicaoInterna extends AbstractMultiEmpresaModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "requisicao_interna_cabecalho_id_seq")
	@SequenceGenerator(name = "requisicao_interna_cabecalho_id_seq", sequenceName = "requisicao_interna_cabecalho_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_requisicao")
	@Caption("Data de requisição")
	private Date dataRequisicao;

	@Caption("Colaborador")
	@ManyToOne
	@JoinColumn(name = "id_colaborador")
	private Colaborador colaborador;

	@OneToMany(mappedBy = "requisicao", cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.JOIN)
	private List<RequisicaoInternaDetalheEntity> detalhes = new ArrayList<RequisicaoInternaDetalheEntity>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataRequisicao() {
		return dataRequisicao;
	}

	public void setDataRequisicao(Date dataRequisicao) {
		this.dataRequisicao = dataRequisicao;
	}

	public List<RequisicaoInternaDetalheEntity> getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(List<RequisicaoInternaDetalheEntity> detalhes) {
		this.detalhes = detalhes;
	}

	public RequisicaoInternaDetalheEntity addRequisicaoDetalhe(
			RequisicaoInternaDetalheEntity requisicaoDetalhe) {
		getDetalhes().add(requisicaoDetalhe);
		requisicaoDetalhe.setRequisicao(this);
		return requisicaoDetalhe;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}