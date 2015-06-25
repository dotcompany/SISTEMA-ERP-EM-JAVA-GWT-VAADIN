/*package dc.entidade.geral.eventos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.control.enums.TipoPessoaEn;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.pessoal.ClienteEntity;
import dc.entidade.geral.pessoal.PessoaContatoEntity;
import dc.entidade.geral.pessoal.PessoaEnderecoEntity;
import dc.entidade.geral.pessoal.PessoaFisicaEntity;
import dc.entidade.geral.pessoal.PessoaJuridicaEntity;

@Entity
@Table(name = "pessoa_eventos")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class PessoaEventosEntity extends AbstractMultiEmpresaModel<Integer> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_eventos_id_seq")
	@SequenceGenerator(name = "pessoa_eventos_id_seq", sequenceName = "pessoa_eventos_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;
	
	@Field
	@Caption("Nome")
	@Column(name = "NOME")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nome = "";
	
	@Field
	@Caption("Email")
	@Column(name = "EMAIL")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String email = "";

	@Field
	@Caption("Site")
	@Column(name = "SITE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String site = "";

	/*@Field
	@Caption("Cliente")
	@Column(name = "CLIENTE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String tipoCliente = "0";*/

	/*@Enumerated(EnumType.STRING)
	@Field
	@Caption("Tipo Pessoa")
	@Column(name = "TIPO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private TipoPessoaEn tipoPessoa;
	
	/**
	 * REFERENCIA - FK
	 */

	/*@OneToOne(mappedBy = "pessoa_eventos", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private PessoaFisicaEntity pessoaFisica;

	@OneToOne(mappedBy = "pessoa_eventos", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private PessoaJuridicaEntity pessoaJuridica;

	@OneToOne(mappedBy = "pessoa_eventos", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private ClienteEntity cliente;

	/**
	 * REFERENCIA - LIST
	 */

	/*@OneToMany(mappedBy = "pessoa_eventos", fetch = FetchType.LAZY)
	private List<PessoaContatoEntity> pessoaContatoList = new ArrayList<>();

	@OneToMany(mappedBy = "pessoa_eventos", fetch = FetchType.LAZY)
	private List<PessoaEnderecoEntity> pessoaEnderecoList = new ArrayList<>();

	// @OneToMany(mappedBy = "pessoa_eventos", fetch = FetchType.LAZY)
	// private List<ClienteEntity> clienteList;


	/*public PessoaEventosEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public PessoaEventosEntity(Integer id) {
		this.id = id;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = (nome == null ? "".trim() : nome.toUpperCase().trim());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = (email == null ? "".trim() : email.toLowerCase().trim());
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = (site == null ? "".trim() : site.toLowerCase().trim());
	}

	public TipoPessoaEn getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoaEn tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public PessoaFisicaEntity getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisicaEntity pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public PessoaJuridicaEntity getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridicaEntity pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public List<PessoaContatoEntity> getPessoaContatoList() {
		return pessoaContatoList;
	}

	public void setPessoaContatoList(List<PessoaContatoEntity> pessoaContatoList) {
		this.pessoaContatoList = pessoaContatoList;
	}

	public List<PessoaEnderecoEntity> getPessoaEnderecoList() {
		return pessoaEnderecoList;
	}

	public void setPessoaEnderecoList(List<PessoaEnderecoEntity> pessoaEnderecoList) {
		this.pessoaEnderecoList = pessoaEnderecoList;
	}
	
	/**
	 * TO STRING
	 */

	/*@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


}*/
