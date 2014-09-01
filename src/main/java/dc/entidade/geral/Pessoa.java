package dc.entidade.geral;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;

/**
 * 
 * @author Wesley Jr /* Classe que possui o TO, ou seja, o mapeamento com todos
 *         os campos que vamos ter no nosso Banco de Dados Nessa classe temos o
 *         equals, hashCode e o ToString, no nosso novo mapeamento, pegamos e
 *         mudamos, está diferente do mapeamento do T2Ti. * Colocamos também
 *         algumas anotações, na classe e em alguns campos, onde temos as
 *         anotações que é o Field e Caption, o Caption colocamos o nome do
 *         campo que queremos que pesquise na Tela, pegando os dados que estão
 *         salvos no Banco de Dados.
 */

@Entity
@Table(name = "pessoa")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class Pessoa extends AbstractMultiEmpresaModel<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pes")
	@SequenceGenerator(name = "pes", sequenceName = "pessoa_id_seq", allocationSize = 1)
	private Integer id;

	@Field
	@Caption("Nome")
	@Column(name = "NOME")
	@Analyzer(definition = "dc_combo_analyzer")
	private String nome;

	@Field
	@Caption("Tipo")
	@Column(name = "TIPO")
	private String tipo;

	@Field
	@Caption("Email")
	@Column(name = "EMAIL")
	private String email;

	@Field
	@Caption("Site")
	@Column(name = "SITE")
	@Analyzer(definition = "dc_combo_analyzer")
	private String site;

	@Field
	@Column(name = "CLIENTE")
	private Character cliente;

	@Field
	@Column(name = "FORNECEDOR")
	private Character fornecedor;

	@Field
	@Column(name = "COLABORADOR")
	@Analyzer(definition = "dc_combo_analyzer")
	private Character colaborador;

	@Field
	@Column(name = "CONVENIO")
	private Character convenio;

	@Field
	@Column(name = "TRANSPORTADORA")
	private Character transportadora;

	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<PessoaContato> contatos = new ArrayList<>();

	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<PessoaEndereco> enderecos = new ArrayList<>();

	public Pessoa() {

	}

	public Pessoa(Integer id) {
		this.id = id;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[] { "id" });
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Pessoa == false)
			return false;
		if (this == object)
			return true;
		final Pessoa other = (Pessoa) object;
		return EqualsBuilder.reflectionEquals(this, other);
	}

	@Override
	public String toString() {
		return nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Character getCliente() {
		return cliente;
	}

	public void setCliente(Character cliente) {
		this.cliente = cliente;
	}

	public Character getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Character fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Character getColaborador() {
		return colaborador;
	}

	public void setColaborador(Character colaborador) {
		this.colaborador = colaborador;
	}

	public Character getConvenio() {
		return convenio;
	}

	public void setConvenio(Character convenio) {
		this.convenio = convenio;
	}

	/*public Character getContador() {
		return contador;
	}

	public void setContador(Character contador) {
		this.contador = contador;
	}*/

	public Character getTransportadora() {
		return transportadora;
	}

	public void setTransportadora(Character transportadora) {
		this.transportadora = transportadora;
	}

	public List<PessoaContato> getContatos() {
		return contatos;
	}

	public void setContatos(List<PessoaContato> contatos) {
		this.contatos = contatos;
	}

	public void adicionarContato(PessoaContato c) {
		getContatos().add(c);
		//c.setEmpresa(this.getEmpresa());
		c.setPessoa(this);
	}

	public void adicionarEndereco(PessoaEndereco end) {
		getEnderecos().add(end);
		end.setEmpresa(this.getEmpresa());
		end.setPessoa(this);

	}

	public List<PessoaEndereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<PessoaEndereco> enderecos) {
		this.enderecos = enderecos;
	}
}