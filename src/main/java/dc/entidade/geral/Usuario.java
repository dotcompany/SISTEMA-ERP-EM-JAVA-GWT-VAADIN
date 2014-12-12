package dc.entidade.geral;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.framework.Papel;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.entidade.sistema.ContaEmpresa;

@Entity
@Table(name = "usuario")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class Usuario extends AbstractMultiEmpresaModel<Integer> implements
		Serializable, UserDetails {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_id_seq")
	@SequenceGenerator(name = "usuario_id_seq", sequenceName = "usuario_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Login")
	@Column(name = "LOGIN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String login;

	@Field
	@Caption("Senha")
	@Column(name = "SENHA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String senha;

	@Column(name = "DATA_CADASTRO")
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	@Column(name = "ADMINISTRADOR")
	private boolean administrador = false;

	@Field
	@Caption("Confirmado")
	@Column(name = "CONFIRMADO")
	private boolean confirmado;

	@Field
	@Caption("Nome")
	@Column(name = "usernome")
	private String usernome;

	/**
	 * REFERENCIA - FK
	 */

	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_COLABORADOR", referencedColumnName = "ID")
	private ColaboradorEntity colaborador;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PAPEL", referencedColumnName = "ID")
	private Papel papel;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST,
			CascadeType.DETACH })
	private ContaEmpresa contaEmpresa;

	/**
	 * TRANSIENT
	 */

	@Transient
	private Integer consultaMultiempresa = new Integer(0);

	/**
	 * CONSTRUTOR
	 */

	public Usuario() {

	}

	public Usuario(Integer id) {
		this.id = id;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public boolean getAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	/** @return the colaborador */
	public ColaboradorEntity getColaborador() {
		return colaborador;
	}

	/**
	 * @param colaborador
	 *            the colaborador to set
	 */
	public void setColaborador(ColaboradorEntity colaborador) {
		this.colaborador = colaborador;
	}

	/** @return the papel */
	public Papel getPapel() {
		return papel;
	}

	/**
	 * @param papel
	 *            the papel to set
	 */
	public void setPapel(Papel papel) {
		this.papel = papel;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedAuths = new ArrayList<>();
		grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));

		return grantedAuths;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public boolean isConfirmado() {
		return confirmado;
	}

	public void setConfirmado(boolean confirmado) {
		this.confirmado = confirmado;
	}

	public ContaEmpresa getConta() {
		return contaEmpresa;
	}

	public void setConta(ContaEmpresa contaEmpresa) {
		this.contaEmpresa = contaEmpresa;
	}

	public String getUsernome() {
		return usernome;
	}

	public void setUsernome(String usernome) {
		this.usernome = usernome;
	}

	public Integer getConsultaMultiempresa() {
		return consultaMultiempresa;
	}

	public void setConsultaMultiempresa(Integer consultaMultiempresa) {
		this.consultaMultiempresa = (consultaMultiempresa == null ? new Integer(
				0) : consultaMultiempresa);
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}