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

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.diversos.Setor;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.Fornecedor;
import dc.entidade.pessoal.Cliente;
import dc.entidade.pessoal.Colaborador;

@Entity
@Table(name = "contrato_solicitacao_servico")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ContratoSolicitacaoServico extends
		AbstractMultiEmpresaModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contrato_solicitacao_servico_id_seq")
	@SequenceGenerator(name = "contrato_solicitacao_servico_id_seq", sequenceName = "contrato_solicitacao_servico_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_SOLICITACAO")
	@Field
	@Caption("Data daSolicitação")
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
	@Caption("StatusSolicitação")
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
	private ContratoTipoServico contratoTipoServico;

	@JoinColumn(name = "ID_COLABORADOR", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	@Caption("Colaborador")
	private Colaborador colaborador;

	@JoinColumn(name = "ID_SETOR", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	@Caption("Setor")
	private Setor setor;

	@Caption("Cliente")
	@JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID")
	@ManyToOne
	private Cliente cliente;

	@Caption("Fornecedor")
	@JoinColumn(name = "ID_FORNECEDOR", referencedColumnName = "ID")
	@ManyToOne
	private Fornecedor fornecedor;

	public ContratoSolicitacaoServico() {

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

	public ContratoTipoServico getContratoTipoServico() {
		return contratoTipoServico;
	}

	public void setContratoTipoServico(ContratoTipoServico contratoTipoServico) {
		this.contratoTipoServico = contratoTipoServico;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}