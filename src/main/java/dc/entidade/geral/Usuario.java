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
import dc.entidade.framework.Papel;
import dc.entidade.pessoal.Colaborador;
import dc.entidade.sistema.ContaEmpresa;

/** @author Wesley Jr /* Classe que possui o TO, ou seja, o mapeamento com todos
 *         os campos que vamos ter no nosso Banco de Dados Nessa classe temos o
 *         equals, hashCode e o ToString, no nosso novo mapeamento, pegamos e
 *         mudamos, está diferente do mapeamento do T2Ti. * Colocamos também
 *         algumas anotações, na classe e em alguns campos, onde temos as
 *         anotações que é o Field e Caption, o Caption colocamos o nome do
 *         campo que queremos que pesquise na Tela, pegando os dados que estão
 *         salvos no Banco de Dados. */

@Entity
@Table(name = "usuario")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class Usuario extends AbstractMultiEmpresaModel<Integer> implements Serializable, UserDetails {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;

	@Field
	@Caption("Login")
	@Column(name = "LOGIN")
	private String login;

	@Field
	@Caption("Senha")
	@Column(name = "SENHA")
	private String senha;

	@Column(name = "DATA_CADASTRO")
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	@Column(name = "ADMINISTRADOR")
	private Boolean administrador = false;

	@JoinColumn(name = "ID_COLABORADOR", referencedColumnName = "ID")
	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	private Colaborador colaborador;

	@JoinColumn(name = "ID_PAPEL", referencedColumnName = "ID")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Papel papel;

	@Field
	@Caption("Confirmado")
	@Column(name = "CONFIRMADO")
	private boolean confirmado;

	@Field
	@Caption("Nome")
	@Column(name = "usernome")
	private String usernome;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.DETACH })
	private ContaEmpresa conta;

	/** TRANSIENT */

	@Transient
	private Integer consultaMultiempresa = new Integer(0);

	/** CONSTRUTOR */

	public Usuario() {

	}

	public Usuario(Integer id) {
		this.id = id;
	}

	/** GETS AND SETS */

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

	public Boolean getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Boolean administrador) {
		this.administrador = administrador;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/** @return the colaborador */
	public Colaborador getColaborador() {
		return colaborador;
	}

	/** @param colaborador
	 *            the colaborador to set */
	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	/** @return the papel */
	public Papel getPapel() {
		return papel;
	}

	/** @param papel
	 *            the papel to set */
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
		return conta;
	}

	public void setConta(ContaEmpresa conta) {
		this.conta = conta;
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
		this.consultaMultiempresa = (consultaMultiempresa == null ? new Integer(0) : consultaMultiempresa);
	}
}