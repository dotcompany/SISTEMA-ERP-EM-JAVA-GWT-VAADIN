package dc.entidade.geral.eventos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	@Column(name = "telefone")
	private String telefone;

	@Column(name = "celular")
	private String celular;
	
	@Column(name = "contato")
	private String contato;

	@Column(name = "cep")
	private String cep;

	@Column(name = "endereco")
	private String endereco;

	@Column(name = "bairro")
	private String bairro;

	@Column(name = "cidade")
	private String cidade;

	@Column(name = "uf")
	private String uf;
	
	@Field
	@Caption()
	@Column(name = "EMAIL", length = 250)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String email;
	
	/*@OneToMany(mappedBy = "cerimonialEventos", fetch = FetchType.LAZY)
	private List<ContratoEventosEntity> contratoEventosList;*/

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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*public List<ContratoEventosEntity> getContratoEventosList() {
		return contratoEventosList;
	}

	public void setContratoEventosList(
			List<ContratoEventosEntity> contratoEventosList) {
		this.contratoEventosList = contratoEventosList;
	}*/
	
}
