package dc.entidade.geral.eventos;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.diverso.UfEntity;

@Entity
@Table(name = "cerimonial_eventos")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
@SuppressWarnings("serial")
public class CerimonialEventosEntity extends AbstractMultiEmpresaModel<Integer> implements Serializable {
	
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cerimonial_eventos_id_seq")
	@SequenceGenerator(name = "cerimonial_eventos_id_seq", sequenceName = "cerimonial_eventos_id_seq", allocationSize = 1, initialValue = 0)
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
	@Caption("CNPJ")
	@Column(name = "CNPJ")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cnpj = "";
	
	@Field
	@Caption("Endereco")
	@Column(name = "ENDERECO", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String endereco = "";

	@Field
	@Caption("Número")
	@Column(name = "NUMERO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer numero = new Integer(0);

	@Field
	@Caption("Complemento")
	@Column(name = "COMPLEMENTO", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String complemento = "";

	@Field
	@Caption("Bairro")
	@Column(name = "BAIRRO", length = 50)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String bairro = "";

	@Field
	@Caption("Cidade")
	@Column(name = "CIDADE", length = 50)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cidade = "";

	@Field
	@Caption("Cep")
	@Column(name = "CEP", length = 8)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cep = "";

	@Field
	@Caption("Telefone")
	@Column(name = "TELEFONE", length = 14)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String telefone;

	@Field
	@Caption("Celular")
	@Column(name = "CELULAR", length = 14)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String celular;
	
	@Field
	@Caption("Contato")
	@Column(name = "CONTATO", length = 14)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String contato;

	@Field
	@Caption("UF sigla")
	@Column(name = "uf", length = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String siglaUf = "";
	
	@Field
	@Caption()
	@Column(name = "EMAIL", length = 250)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String email;
	
	/*@OneToMany(mappedBy = "cerimonialEventos", fetch = FetchType.LAZY)
	private List<ContratoEventosEntity> contratoEventosList;*/
	
	@Caption("UF")
	@ManyToOne(cascade = { CascadeType.PERSIST })
	@JoinColumn(name = "id_uf")
	private UfEntity uf;

	public CerimonialEventosEntity() {
    }

    public CerimonialEventosEntity(Integer id) {
	   this.id = id;
    }

    @Override
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
		this.nome = (nome == null ? "".trim() : nome.toUpperCase().trim());
	}
	
	public String getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(String cnpj) {
		this.cnpj = (cnpj == null ? "".trim() : cnpj.toUpperCase().trim());
	}
	
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = (endereco == null ? "".trim() : endereco.toUpperCase().trim());
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = (telefone == null ? "".trim() : telefone.toUpperCase().trim());
	}
	
	public String getCelular() {
		return celular;
	}
	
	public void setCelular(String celular) {
		this.celular = (celular == null ? "".trim() : celular.toUpperCase().trim());
	}
	
	public String getContato() {
		return contato;
	}
	
	public void setContato(String contato) {
		this.contato = (contato == null ? "".trim() : contato.toUpperCase().trim());
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = (cep == null ? "".trim() : cep.toUpperCase().trim());
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = (numero == null ? new Integer(0) : numero);
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = (complemento == null ? "".trim() : complemento
				.toUpperCase().trim());
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = (bairro == null ? "".trim() : bairro.toUpperCase().trim());
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = (cidade == null ? "".trim() : cidade.toUpperCase().trim());
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = (email == null ? "".trim() : email.toUpperCase().trim());
	}
	
	public String getSiglaUf() {
		return siglaUf;
	}

	public void setSiglaUf(String siglaUf) {
		this.siglaUf = siglaUf;
	}

	public UfEntity getUf() {
		return uf;
	}

	public void setUf(UfEntity uf) {
		this.uf = uf;
	}

	/*public List<ContratoEventosEntity> getContratoEventosList() {
		return contratoEventosList;
	}

	public void setContratoEventosList(
			List<ContratoEventosEntity> contratoEventosList) {
		this.contratoEventosList = contratoEventosList;
	}*/
	
}
