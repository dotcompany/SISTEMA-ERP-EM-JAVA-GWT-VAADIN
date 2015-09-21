package dc.entidade.suprimentos.contrato;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.diverso.SetorEntity;
import dc.entidade.geral.pessoal.ClienteEntity;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.entidade.geral.pessoal.FornecedorEntity;

@Entity
@Table(name = "contrato_solicitacao_servico")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class SolicitacaoServicoEntity extends
		AbstractMultiEmpresaModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "contrato_solicitacao_servico_id_seq")
	@SequenceGenerator(name = "contrato_solicitacao_servico_id_seq", sequenceName = "contrato_solicitacao_servico_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_SOLICITACAO")
	@Field
	@Caption("Data da Solicitação")
	private Date dataSolicitacao;

	@Field
	@Caption("Data Desejada Início")
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_DESEJADA_INICIO")
	private Date dataDesejadaInicio;

	@Field
	@Caption("Urgente")
	@Column(name = "URGENTE")
	private String urgente;

	@Field
	@Caption("Status Solicitação")
	@Column(name = "STATUS_SOLICITACAO")
	private String statusSolicitacao;

	@Field
	@Column(name = "DESCRICAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Descrição")
	private String descricao;

	/**
	 * Alterações para aparecer no ComoBox na Tela de Contrato
	 */

	@Caption("Contrato Tipo serviço")
	@JoinColumn(name = "ID_CONTRATO_TIPO_SERVICO", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private TipoServicoEntity contratoTipoServico;

	@JoinColumn(name = "ID_COLABORADOR", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	@Caption("Colaborador")
	private ColaboradorEntity colaborador;

	@JoinColumn(name = "ID_SETOR", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	@Caption("Setor")
	private SetorEntity setor;

	@Caption("Cliente")
	@JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID")
	@ManyToOne
	private ClienteEntity cliente;

	@Caption("Fornecedor")
	@JoinColumn(name = "ID_FORNECEDOR", referencedColumnName = "ID")
	@ManyToOne
	private FornecedorEntity fornecedor;

	public SolicitacaoServicoEntity() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public Date getDataDesejadaInicio() {
		return dataDesejadaInicio;
	}

	public void setDataDesejadaInicio(Date dataDesejadaInicio) {
		this.dataDesejadaInicio = dataDesejadaInicio;
	}

	public String getUrgente() {
		return urgente;
	}

	public void setUrgente(String urgente) {
		this.urgente = urgente;
	}

	public String getStatusSolicitacao() {
		return statusSolicitacao;
	}

	public void setStatusSolicitacao(String statusSolicitacao) {
		this.statusSolicitacao = statusSolicitacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoServicoEntity getContratoTipoServico() {
		return contratoTipoServico;
	}

	public void setContratoTipoServico(TipoServicoEntity contratoTipoServico) {
		this.contratoTipoServico = contratoTipoServico;
	}

	public ColaboradorEntity getColaborador() {
		return colaborador;
	}

	public void setColaborador(ColaboradorEntity colaborador) {
		this.colaborador = colaborador;
	}

	public SetorEntity getSetor() {
		return setor;
	}

	public void setSetor(SetorEntity setor) {
		this.setor = setor;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public FornecedorEntity getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(FornecedorEntity fornecedor) {
		this.fornecedor = fornecedor;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return descricao;
	}

}