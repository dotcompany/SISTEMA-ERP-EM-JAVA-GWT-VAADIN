package dc.entidade.administrativo.empresa;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.geral.diverso.UfEntity;

@Entity
@Table(name = "socio")
@SuppressWarnings("serial")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class SocioEntity extends AbstractMultiEmpresaModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "soc")
	@SequenceGenerator(name = "soc", sequenceName = "socio_id_seq", allocationSize = 1)
	private Integer id;

	//@ManyToOne
	//@JoinColumn(name="id_quadro_societario", nullable = false)
	//private QuadroSocietarioEntity quadroSocietario;
	
	@Caption("Quadro Societario")
	@OneToOne
	@JoinColumn(name = "id_quadro_societario", insertable = true, updatable = true)
	private QuadroSocietarioEntity quadroSocietario;
	
	@Field
	@Caption("Nome")
	String nome;
	
	@Field
	@Caption("Cpf")
	String cpf;

	@Field
	@Caption("Logradouro")
	String logradouro;

	@Field
	@Caption("Numero")
	Integer numero;

	@Field
	@Caption("Complemento")
	String complemento;

	@Field
	@Caption("Bairro")
	String bairro;

	@Field
	@Caption("Municipio")
	String municipio;

	//String uf;
	
	@Transient
	private UfEntity uf;

	String cep;

	String fone;

	String celular;

	String email;

	BigDecimal participacao;

	Integer quotas;

	BigDecimal integralizar;

	@Column(name="data_ingresso")
	@Temporal(TemporalType.DATE)
	Date dataIngresso;

	@Column(name="data_saida")
	@Temporal(TemporalType.DATE)
	Date dataSaida;

//	@ManyToOne
//	@JoinColumn(name = "ID_EMPRESA", nullable = false)
//	private Empresa empresa;

	@OneToMany(mappedBy="socio",cascade=CascadeType.ALL)
	private List<DependenteEntity> dependentes = new ArrayList<>();
	
	@OneToMany(mappedBy="socio",cascade=CascadeType.ALL)
	private List<ParticipacaoSocietariaEntity> participacoes = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public QuadroSocietarioEntity getQuadroSocietario() {
		return quadroSocietario;
	}

	public void setQuadroSocietario(QuadroSocietarioEntity quadroSocietario) {
		this.quadroSocietario = quadroSocietario;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public UfEntity getUf() {
		return uf;
	}

	public void setUf(UfEntity uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

//	public Empresa getEmpresa() {
//		return empresa;
//	}
//
//	public void setEmpresa(Empresa empresa) {
//		this.empresa = empresa;
//	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getParticipacao() {
		return participacao;
	}

	public void setParticipacao(BigDecimal participacao) {
		this.participacao = participacao;
	}

	public Integer getQuotas() {
		return quotas;
	}

	public void setQuotas(Integer quotas) {
		this.quotas = quotas;
	}

	public BigDecimal getIntegralizar() {
		return integralizar;
	}

	public void setIntegralizar(BigDecimal integralizar) {
		this.integralizar = integralizar;
	}

	public Date getDataIngresso() {
		return dataIngresso;
	}

	public void setDataIngresso(Date dataIngresso) {
		this.dataIngresso = dataIngresso;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public List<DependenteEntity> getDependentes() {
		return dependentes;
	}

	public void setDependentes(List<DependenteEntity> dependentes) {
		this.dependentes = dependentes;
	}
	
	

	public List<ParticipacaoSocietariaEntity> getParticipacoes() {
		return participacoes;
	}

	public void setParticipacoes(List<ParticipacaoSocietariaEntity> participacoes) {
		this.participacoes = participacoes;
	}

	public void adicionarDependente(DependenteEntity d){
		getDependentes().add(d);
		d.setSocio(this);
	}
	
	public void adicionarDependente(ParticipacaoSocietariaEntity p){
		getParticipacoes().add(p);
		p.setSocio(this);
	}





}
