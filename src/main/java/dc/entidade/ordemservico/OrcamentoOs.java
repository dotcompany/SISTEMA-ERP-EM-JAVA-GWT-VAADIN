package dc.entidade.ordemservico;

import java.math.BigDecimal;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Field;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboValue;
import dc.entidade.suprimentos.PedidoDetalhe;

@Entity
@Table(name = "os_orcamento")
@SuppressWarnings("serial")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class OrcamentoOs extends AbstractMultiEmpresaModel<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orc")
	@SequenceGenerator(name = "orc", sequenceName = "os_orcamento_id_seq", allocationSize = 1)
	private Integer id;

	@Field
	@Caption("Nome")
	@Column(name = "nome")
	private String nome;

	@Field
	@Caption("Nome vendedor")
	@Column(name = "nome_vendedor")
	private String nomeVendedor;

	@Field
	@Caption("Forma pagamento")
	@Column(name = "forma_pagamento")
	private String formaPagamento;

	@Field
	@Caption("Endereço")
	@Column(name = "endereco")
	private String endereco;
  
	@Field
	@Caption("Fone")
	@Column(name = "fone")
	private String fone;

	@Field
	@Caption("Placa")
	@Column(name = "placa")
	private String placa;

	@ManyToOne
	@JoinColumn(name = "id_marca", referencedColumnName = "id")
	private Marca marca;

	@ManyToOne
	@JoinColumn(name = "id_modelo", referencedColumnName = "id")
	private Modelo modelo;

	@ManyToOne
	@JoinColumn(name = "id_cor", referencedColumnName = "id")
	private Cor cor;
	
	@Field
	@Caption("Ano")
	@Column(name = "ano")
	private Integer ano;

	@Field
	@Caption("Motor")
	@Column(name = "motor")
	private String motor;

	@Field
	@Caption("Motorização")
	@Column(name = "motorizacao")
	private String motorizacao;

	@Column(name = "vlr_mao_obra")
	private BigDecimal valorMaoObra;

	@Column(name = "vlr_produto")
	private BigDecimal valorProduto;
	
	@Column(name = "vlr_outros")
	private BigDecimal valorOutros;

	@Column(name = "vlr_desconto")
	private BigDecimal valorDesconto;

	@Column(name = "vlr_total")
	private BigDecimal valorTotal;

	@Field
	@Caption("Data Cadastro")
	@Column(name = "data_cadastro")
	@Temporal(TemporalType.DATE)
	@Generated(GenerationTime.INSERT)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataCadastro;

	@OneToMany(mappedBy = "orcamentoOs", cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.JOIN)
	private List<OrcamentoOsItem> itens = new ArrayList<>();
	
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

	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public String getMotorizacao() {
		return motorizacao;
	}

	public void setMotorizacao(String motorizacao) {
		this.motorizacao = motorizacao;
	}

	public BigDecimal getValorMaoObra() {
		return valorMaoObra;
	}

	public void setValorMaoObra(BigDecimal valorMaoObra) {
		this.valorMaoObra = valorMaoObra;
	}

	public BigDecimal getValorProduto() {
		return valorProduto;
	}

	public void setValorProduto(BigDecimal valorProduto) {
		this.valorProduto = valorProduto;
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

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public List<OrcamentoOsItem> getItens() {
		return itens;
	}

	public void setItens(List<OrcamentoOsItem> itens) {
		this.itens = itens;
	}

	public OrcamentoOsItem addOrcamentoOsItem(OrcamentoOsItem orcamentoOsItem) {
		getItens().add(orcamentoOsItem);
		orcamentoOsItem.setOrcamentoOs(this);;

		return orcamentoOsItem;
	}

	public OrcamentoOsItem removeOrcamentoOsItem(OrcamentoOsItem orcamentoOsItem) {
		getItens().remove(orcamentoOsItem);
		orcamentoOsItem.setOrcamentoOs(null);
		return orcamentoOsItem;
	}
	
	
}
