package dc.entidade.sistema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;

import dc.anotacoes.Caption;
import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.geral.Usuario;

@Entity
@Table(name = "conta_empresa")
@XmlRootElement
public class ContaEmpresa extends AbstractModel<Integer> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conta_empresa_id_seq")
	@SequenceGenerator(name = "conta_empresa_id_seq", sequenceName = "conta_empresa_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field()
	@Caption("E-mail")
	@Column(name = "email")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio")
	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "Endereço de e-mail inválido")
	private String email;

	@Field()
	@Caption("Nome")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio")
	@Column(name = "nome", nullable = false)
	private String nome;

	@Field()
	@Caption("Telefone")
	@Column(name = "telefone", nullable = false)
	private String telefone;

	/**
	 * REFERENCIA - FK
	 */

	@OneToOne()
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	private EmpresaEntity empresa;

	@OneToOne()
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	private Usuario usuarioCriador;

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany()
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.DETACH })
	private List<Usuario> usuarioList;

	/**
	 * CONSTRUTOR
	 */

	public ContaEmpresa() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * GETS AND SETS
	 */

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public EmpresaEntity getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaEntity empresa) {
		this.empresa = empresa;
	}

	public Usuario getUsuarioCriador() {
		return usuarioCriador;
	}

	public void setUsuarioCriador(Usuario usuarioCriador) {
		this.usuarioCriador = usuarioCriador;
	}

	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}

	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}

	public void setPrimeiroUsuario(Usuario u) {
		this.usuarioCriador = u;
		List<Usuario> usuarioList = new ArrayList<Usuario>();
		usuarioList.add(u);
		this.setUsuarioList(usuarioList);
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}