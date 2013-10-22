package dc.entidade.contratos;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.diversos.Setor;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.geral.Fornecedor;
import dc.entidade.pessoal.Cliente;
import dc.entidade.pessoal.Colaborador;

@Entity
@Table(name = "CONTRATO_SOLICITACAO_SERVICO")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ContratoSolicitacaoServico extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	@Field
	@Caption("Id")
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
	@Caption("Descrição")
	@Column(name = "DESCRICAO")
	private String descricao;

	@Caption("Tipo serviço")
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

	@Override
	public String toString() {
		return descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataDesejadaInicio == null) ? 0 : dataDesejadaInicio.hashCode());
		result = prime * result + ((dataSolicitacao == null) ? 0 : dataSolicitacao.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((statusSolicitacao == null) ? 0 : statusSolicitacao.hashCode());
		result = prime * result + ((urgente == null) ? 0 : urgente.hashCode());
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
		ContratoSolicitacaoServico other = (ContratoSolicitacaoServico) obj;
		if (dataDesejadaInicio == null) {
			if (other.dataDesejadaInicio != null)
				return false;
		} else if (!dataDesejadaInicio.equals(other.dataDesejadaInicio))
			return false;
		if (dataSolicitacao == null) {
			if (other.dataSolicitacao != null)
				return false;
		} else if (!dataSolicitacao.equals(other.dataSolicitacao))
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
		if (statusSolicitacao == null) {
			if (other.statusSolicitacao != null)
				return false;
		} else if (!statusSolicitacao.equals(other.statusSolicitacao))
			return false;
		if (urgente == null) {
			if (other.urgente != null)
				return false;
		} else if (!urgente.equals(other.urgente))
			return false;
		return true;
	}

}
