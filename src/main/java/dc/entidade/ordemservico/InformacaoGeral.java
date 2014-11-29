package dc.entidade.ordemservico;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Field;

import dc.anotacoes.Caption;
import dc.entidade.financeiro.TipoPagamento;
import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.pessoal.ColaboradorEntity;

@Entity
@Table(name = "os_informacao_geral")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class InformacaoGeral extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_ordem_servico", referencedColumnName = "id")
	private OrdemServico ordemServico;

	@Field
	@Caption("NR COMANDA")
	@Column(name = "numero_comanda")
	private Integer numeroComanda;

	@Field
	@Caption("NR TELEFONE")
	@Column(name = "telefone")
	private String telefone;
	
	@ManyToOne
	@JoinColumn(name = "id_status", referencedColumnName = "id")
	private StatusOs statusOs;

	@ManyToOne
	@JoinColumn(name = "id_situacao_servico", referencedColumnName = "id")
	private SituacaoServico situacaoServico;

	@ManyToOne
	@JoinColumn(name = "id_tipo_servico", referencedColumnName = "id")
	private TipoServico tipoServico;
	
	@ManyToOne
	@JoinColumn(name = "id_colaborador", referencedColumnName = "id")
	private ColaboradorEntity atendente;

	@ManyToOne
	@JoinColumn(name = "id_carro", referencedColumnName = "id")
	private Carro carro;

	@ManyToOne
	@JoinColumn(name = "id_tipo_pgto", referencedColumnName = "id")
	private TipoPagamento tipoPagamento;

	@Field
	@Caption("KM RODADO")
	@Column(name = "km_hor_rodado")
	private Integer kmHorRodado;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_entrada")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataEntrada;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_efetivacao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataEfetivacao;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_prox_revisao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataProximaRevisao;
	
	@Field
	@Caption("Observacao")
	@Lob
	@Column(name = "observacao")
	@Type(type = "text")
	private String observacao;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_entrega")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataEntrega;
	
	@Column(name = "ver_data_entrega")
	private Boolean verDataEntrega;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public Integer getNumeroComanda() {
		return numeroComanda;
	}

	public void setNumeroComanda(Integer numeroComanda) {
		this.numeroComanda = numeroComanda;
	}

	public StatusOs getStatusOs() {
		return statusOs;
	}

	public void setStatusOs(StatusOs statusOs) {
		this.statusOs = statusOs;
	}

	public SituacaoServico getSituacaoServico() {
		return situacaoServico;
	}

	public void setSituacaoServico(SituacaoServico situacaoServico) {
		this.situacaoServico = situacaoServico;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public Integer getKmHorRodado() {
		return kmHorRodado;
	}

	public void setKmHorRodado(Integer kmHorRodado) {
		this.kmHorRodado = kmHorRodado;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataEfetivacao() {
		return dataEfetivacao;
	}

	public void setDataEfetivacao(Date dataEfetivacao) {
		this.dataEfetivacao = dataEfetivacao;
	}

	public Date getDataProximaRevisao() {
		return dataProximaRevisao;
	}

	public void setDataProximaRevisao(Date dataProximaRevisao) {
		this.dataProximaRevisao = dataProximaRevisao;
	}

	public String getObservacao() {
		return observacao; 
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	} 

	public Boolean getVerDataEntrega() {
		return verDataEntrega;
	}

	public void setVerDataEntrega(Boolean verDataEntrega) {
		this.verDataEntrega = verDataEntrega;
	}

	public ColaboradorEntity getAtendente() {
		return atendente;
	}

	public void setAtendente(ColaboradorEntity atendente) {
		this.atendente = atendente;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	
}
