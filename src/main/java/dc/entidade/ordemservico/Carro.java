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
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.pessoal.ClienteEntity;

@Entity
@Table(name = "os_carro")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class Carro extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;
	
	@Field
	@Caption("Placa")
	@Column(name = "placa")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String placa;

	@Field
	@Caption("Data Cadastro")
	@Column(name = "data_cadastro")
	@Temporal(TemporalType.DATE)
	@Generated(GenerationTime.INSERT)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataCadastro;

	@Field
	@Caption("Motorização")
	@Column(name = "motorizacao")
	private String motorizacao;

	@Field
	@Caption("Nº Chassis")
	@Column(name = "chassi")
	private String chassi;
	
	@Field
	@Caption("Ano")
	@Column(name = "ano")
	private Integer ano;

	@Caption("Cliente")
	@JoinColumn(name = "id_cliente", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private ClienteEntity cliente;

	@Caption("Marca")
	@JoinColumn(name = "id_marca", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Marca marca;

	@Caption("Cor")
	@JoinColumn(name = "id_cor", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Cor cor;
 
	@Caption("Modelo")
	@JoinColumn(name = "id_modelo", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Modelo modelo;

	@Caption("Combustível")
	@JoinColumn(name = "id_combustivel", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Combustivel combustivel;

	@Field 
	@Caption("Observacao")
	@Lob
	@Column(name = "observacao")
	private String observacao;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public String getMotorizacao() {
		return motorizacao;
	}

	public void setMotorizacao(String motorizacao) {
		this.motorizacao = motorizacao;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Combustivel getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(Combustivel combustivel) {
		this.combustivel = combustivel;
	}

	/*public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}*/

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}
}
