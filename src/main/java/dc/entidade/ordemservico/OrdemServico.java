package dc.entidade.ordemservico;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.pessoal.Cliente;

@Entity
@Table(name = "os_ordem_servico")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class OrdemServico extends AbstractModel<Integer> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Caption(value = "Cliente")
	@ManyToOne
	@JoinColumn(name = "id_cliente", referencedColumnName = "id")
	private Cliente cliente;

	@Caption(value = "Valor Serviço")
	@Column(name = "valor_servico")
	private BigDecimal valorServico;

	@Caption(value = "Valor Peça")
	@Column(name = "valor_peca")
	private BigDecimal valorPeca;

	@Caption(value = "Valor Frete")
	@Column(name = "valor_frete")
	private BigDecimal valorFrete;	

	@Caption(value = "Valor Outros")
	@Column(name = "valor_outros")
	private BigDecimal valorOutros;	

	@Caption(value = "Valor Desconto")
	@Column(name = "valor_desconto")
	private BigDecimal valorDesconto;	

	@Caption(value = "Valor Total")
	@Column(name = "valor_total_os")
	private BigDecimal valorTotalOs;	

	@Caption(value = "Valor Serviço")
	@Column(name = "valor_total_servico")
	private BigDecimal valorTotalServico;	

	@Caption(value = "Valor Lucro Serviço")
	@Column(name = "valor_lucro_servico")
	private BigDecimal valorLucroServico;	

	@Caption(value = "Valor Comissão Técnico")
	@Column(name = "valor_comissao_tecnico")
	private BigDecimal valorComissaoTecnico;	

	@Caption(value = "Valor Comissão Vendedor")
	@Column(name = "valor_comissao_vendedor")
	private BigDecimal valorComissaoVendedor;	

	@Caption(value = "Valor Comissão Atendente")
	@Column(name = "valor_comissao_atendente")
	private BigDecimal valorComissaoAtendente;	

	@Caption(value = "Valor Lucro Parcial")
	@Column(name = "valor_lucro_parcial")
	private BigDecimal valorLucroParcial;
	
	@Caption(value = "Quantidade parcelas")
	@Column(name = "qtd_parcela_cheque")
	private Integer quantidadeParcelaCheque;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "primeiro_vencimento_cheque")
	@Caption(value = "Primeiro Vencimento")
	private Date primeiroVencimentoCheque;

	@Caption(value = "Quantidade parcelas")
	@Column(name = "qtd_parcela_carne")
	private Integer quantidadeParcelaCarne;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "primeiro_vencimento_carne")
	@Caption(value = "Primeiro Vencimento")
	private Date primeiroVencimentoCarne;

	@Caption(value = "Quantidade parcelas")
	@Column(name = "qtd_parcela_cartao")
	private Integer quantidadeParcelaCartao;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "primeiro_vencimento_cartao")
	@Caption(value = "Primeiro Vencimento")
	private Date primeiroVencimentoCartao;
	
	@Caption(value = "Quantidade parcelas")
	@Column(name = "qtd_parcela_boleto")
	private Integer quantidadeParcelaBoleto;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "primeiro_vencimento_boleto")
	@Caption(value = "Primeiro Vencimento")
	private Date primeiroVencimentoBoleto;

	@Field
	@Caption("Data Cadastro")
	@Column(name = "data_cadastro")
	@Temporal(TemporalType.DATE)
	@Generated(GenerationTime.INSERT)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataCadastro;
	
	@Caption(value = "Data Exclusão")
	@Column(name = "data_exclusao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataExclusao;

	@OneToMany(mappedBy="ordemServico",orphanRemoval = true,cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<EntradaServico> itensEntradaServico = new ArrayList<EntradaServico>();
	
	@OneToMany(mappedBy="ordemServico",orphanRemoval = true,cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<VendaPeca> itensVendaPeca = new ArrayList<VendaPeca>();

	@OneToMany(mappedBy="ordemServico",orphanRemoval = true,cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<MaterialServico> itensMaterialServico = new ArrayList<MaterialServico>();
	
	@OneToMany(mappedBy="ordemServico",orphanRemoval = true,cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<AcessorioOs> itensAcessorioOs = new ArrayList<AcessorioOs>();

	@OneToMany(mappedBy="ordemServico",orphanRemoval = true,cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<OrdemServicoEfetivacao> itensOrdemServicoEfetivacao = new ArrayList<OrdemServicoEfetivacao>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getValorServico() {
		return valorServico;
	}

	public void setValorServico(BigDecimal valorServico) {
		this.valorServico = valorServico;
	}

	public BigDecimal getValorPeca() {
		return valorPeca;
	}

	public void setValorPeca(BigDecimal valorPeca) {
		this.valorPeca = valorPeca;
	}

	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}

	public BigDecimal getValorOutros() {
		return valorOutros;
	}

	public void setValorOutros(BigDecimal valorOutros) {
		this.valorOutros = valorOutros;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getValorTotalOs() {
		return valorTotalOs;
	}

	public void setValorTotalOs(BigDecimal valorTotalOs) {
		this.valorTotalOs = valorTotalOs;
	}

	public BigDecimal getValorTotalServico() {
		return valorTotalServico;
	}

	public void setValorTotalServico(BigDecimal valorTotalServico) {
		this.valorTotalServico = valorTotalServico;
	}

	public BigDecimal getValorLucroServico() {
		return valorLucroServico;
	}

	public void setValorLucroServico(BigDecimal valorLucroServico) {
		this.valorLucroServico = valorLucroServico;
	}

	public BigDecimal getValorComissaoTecnico() {
		return valorComissaoTecnico;
	}

	public void setValorComissaoTecnico(BigDecimal valorComissaoTecnico) {
		this.valorComissaoTecnico = valorComissaoTecnico;
	}

	public BigDecimal getValorComissaoVendedor() {
		return valorComissaoVendedor;
	}

	public void setValorComissaoVendedor(BigDecimal valorComissaoVendedor) {
		this.valorComissaoVendedor = valorComissaoVendedor;
	}

	public BigDecimal getValorComissaoAtendente() {
		return valorComissaoAtendente;
	}

	public void setValorComissaoAtendente(BigDecimal valorComissaoAtendente) {
		this.valorComissaoAtendente = valorComissaoAtendente;
	}

	

	public void setValorLucroParcial(BigDecimal valorLucroParcial) {
		this.valorLucroParcial = valorLucroParcial;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	public List<EntradaServico> getItensEntradaServico() {
		return itensEntradaServico;
	}

	public void setItensEntradaServico(List<EntradaServico> itensEntradaServico) {
		this.itensEntradaServico = itensEntradaServico;
	}
	
	public List<VendaPeca> getItensVendaPeca() {
		return itensVendaPeca;
	}

	public void setItensVendaPeca(List<VendaPeca> itensVendaPeca) {
		this.itensVendaPeca = itensVendaPeca;
	}

	public List<MaterialServico> getItensMaterialServico() {
		return itensMaterialServico;
	}

	public void setItensMaterialServico(List<MaterialServico> itensMaterialServico) {
		this.itensMaterialServico = itensMaterialServico;
	}

	public BigDecimal getValorLucroParcial() {
		return valorLucroParcial;
	}

	public List<AcessorioOs> getItensAcessorioOs() {
		return itensAcessorioOs;
	}

	public void setItensAcessorioOs(List<AcessorioOs> itensAcessorioOs) {
		this.itensAcessorioOs = itensAcessorioOs;
	}

	public Integer getQuantidadeParcelaCheque() {
		return quantidadeParcelaCheque;
	}

	public void setQuantidadeParcelaCheque(Integer quantidadeParcelaCheque) {
		this.quantidadeParcelaCheque = quantidadeParcelaCheque;
	}

	public List<OrdemServicoEfetivacao> getItensOrdemServicoEfetivacao() {
		return itensOrdemServicoEfetivacao;
	}

	public Date getPrimeiroVencimentoCheque() {
		return primeiroVencimentoCheque;
	}

	public void setPrimeiroVencimentoCheque(Date primeiroVencimentoCheque) {
		this.primeiroVencimentoCheque = primeiroVencimentoCheque;
	}

	public Integer getQuantidadeParcelaCarne() {
		return quantidadeParcelaCarne;
	}

	public void setQuantidadeParcelaCarne(Integer quantidadeParcelaCarne) {
		this.quantidadeParcelaCarne = quantidadeParcelaCarne;
	}

	public Date getPrimeiroVencimentoCarne() {
		return primeiroVencimentoCarne;
	}

	public void setPrimeiroVencimentoCarne(Date primeiroVencimentoCarne) {
		this.primeiroVencimentoCarne = primeiroVencimentoCarne;
	}

	public Integer getQuantidadeParcelaCartao() {
		return quantidadeParcelaCartao;
	}

	public void setQuantidadeParcelaCartao(Integer quantidadeParcelaCartao) {
		this.quantidadeParcelaCartao = quantidadeParcelaCartao;
	}

	public Date getPrimeiroVencimentoCartao() {
		return primeiroVencimentoCartao;
	}

	public void setPrimeiroVencimentoCartao(Date primeiroVencimentoCartao) {
		this.primeiroVencimentoCartao = primeiroVencimentoCartao;
	}

	public Integer getQuantidadeParcelaBoleto() {
		return quantidadeParcelaBoleto;
	}

	public void setQuantidadeParcelaBoleto(Integer quantidadeParcelaBoleto) {
		this.quantidadeParcelaBoleto = quantidadeParcelaBoleto;
	}

	public Date getPrimeiroVencimentoBoleto() {
		return primeiroVencimentoBoleto;
	}

	public void setPrimeiroVencimentoBoleto(Date primeiroVencimentoBoleto) {
		this.primeiroVencimentoBoleto = primeiroVencimentoBoleto;
	}

	public void setItensOrdemServicoEfetivacao(List<OrdemServicoEfetivacao> itensOrdemServicoEfetivacao) {
		this.itensOrdemServicoEfetivacao = itensOrdemServicoEfetivacao;
	}

	public EntradaServico adicionarEntradaServico(EntradaServico entradaServico){
		getItensEntradaServico().add(entradaServico);
		entradaServico.setOrdemServico(this);
		return entradaServico;
	}

	public VendaPeca adicionarVendaPeca(VendaPeca vendaPeca){
		getItensVendaPeca().add(vendaPeca);
		vendaPeca.setOrdemServico(this);
		return vendaPeca;
	}

	public MaterialServico adicionarMaterialServico(MaterialServico materialServico){
		getItensMaterialServico().add(materialServico);
		materialServico.setOrdemServico(this);
		return materialServico;
	}

	public AcessorioOs adicionarAcessorioOs(AcessorioOs acessorioOs){
		getItensAcessorioOs().add(acessorioOs);
		acessorioOs.setOrdemServico(this);
		return acessorioOs;
	}
//	public void addParcelaEfetivacao(OrdemServicoEfetivacao parcela) {
//		parcela.setOrdemServico(this);
//		this.itensOrdemServicoEfetivacao.add(parcela);
//	}
	public void addParcelaCheque(OrdemServicoEfetivacao parcela) {
		parcela.setOrdemServico(this);
		this.itensOrdemServicoEfetivacao.add(parcela);
	}
	public void addParcelaCarne(OrdemServicoEfetivacao parcela) {
		parcela.setOrdemServico(this);
		this.itensOrdemServicoEfetivacao.add(parcela);
	}
	public void addParcelaCartao(OrdemServicoEfetivacao parcela) {
		parcela.setOrdemServico(this);
		this.itensOrdemServicoEfetivacao.add(parcela);
	}
	public void addParcelaBoleto(OrdemServicoEfetivacao parcela) {
		parcela.setOrdemServico(this);
		this.itensOrdemServicoEfetivacao.add(parcela);
	}
}
