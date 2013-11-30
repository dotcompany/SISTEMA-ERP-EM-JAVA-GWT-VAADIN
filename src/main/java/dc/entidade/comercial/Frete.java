package dc.entidade.comercial;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.pessoal.Transportadora;

@Entity
@Table(name = "venda_frete")
@SuppressWarnings("serial")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class Frete extends AbstractMultiEmpresaModel<Integer> {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "frt")
	@SequenceGenerator(name = "frt", sequenceName = "venda_frete_id_seq", allocationSize = 1)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="id_transportadora")
	@Caption("Transportadora")
	Transportadora transportadora;
	
	@ManyToOne
	@JoinColumn(name="id_venda_cabecalho")
	@Caption("ID Venda")
    Venda venda;
	
	Integer conhecimento;
	
	String responsavel;
	
	String placa;
	
	@Column(name="uf_placa")
	String ufPlaca;
	
	@Column(name="selo_fiscal")
	Integer seloFiscal;
	
	@Column(name="quantidade_volume")
	BigDecimal quantidadeVolume;
	
	@Column(name="marca_volume")
	String marcaVolume;
	
	@Column(name="especie_volume")
	String especieVolume;
	
	@Column(name="peso_bruto")
	BigDecimal pesoBruto;
	
	@Column(name="peso_liquido")
	BigDecimal pesoLiquido;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Transportadora getTransportadora() {
		return transportadora;
	}

	public void setTransportadora(Transportadora transportadora) {
		this.transportadora = transportadora;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Integer getConhecimento() {
		return conhecimento;
	}

	public void setConhecimento(Integer conhecimento) {
		this.conhecimento = conhecimento;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getUfPlaca() {
		return ufPlaca;
	}

	public void setUfPlaca(String ufPlaca) {
		this.ufPlaca = ufPlaca;
	}

	public Integer getSeloFiscal() {
		return seloFiscal;
	}

	public void setSeloFiscal(Integer seloFiscal) {
		this.seloFiscal = seloFiscal;
	}

	public BigDecimal getQuantidadeVolume() {
		return quantidadeVolume;
	}

	public void setQuantidadeVolume(BigDecimal quantidadeVolume) {
		this.quantidadeVolume = quantidadeVolume;
	}

	public String getMarcaVolume() {
		return marcaVolume;
	}

	public void setMarcaVolume(String marcaVolume) {
		this.marcaVolume = marcaVolume;
	}

	public BigDecimal getPesoBruto() {
		return pesoBruto;
	}

	public void setPesoBruto(BigDecimal pesoBruto) {
		this.pesoBruto = pesoBruto;
	}

	public BigDecimal getPesoLiquido() {
		return pesoLiquido;
	}

	public void setPesoLiquido(BigDecimal pesoLiquido) {
		this.pesoLiquido = pesoLiquido;
	}

	public String getEspecieVolume() {
		return especieVolume;
	}

	public void setEspecieVolume(String especieVolume) {
		this.especieVolume = especieVolume;
	}
	
	
	
}
