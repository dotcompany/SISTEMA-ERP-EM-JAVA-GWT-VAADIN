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
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;
import org.hibernate.search.annotations.Field;

import com.sun.istack.logging.Logger;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.Empresa;
import dc.entidade.geral.Usuario;

@Entity
@Table(name = "conta_empresa")
@XmlRootElement
public class ContaEmpresa extends AbstractModel<Integer> implements Serializable{

	private static final long serialVersionUID = 3022660314863012474L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    
	@OneToOne()
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	private Empresa empresa;
	
	@OneToMany()
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,org.hibernate.annotations.CascadeType.DETACH})
	private List<Usuario> usuarios;
	
	@OneToOne()
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	private Usuario usuarioCriador;
    
    @Field()
    @Caption("E-mail")
    @Column(name = "email" )
    @javax.validation.constraints.NotNull(message="Não pode estar vazio")
    @Pattern(regexp = ".+@.+\\.[a-z]+",message= "Endereço de e-mail inválido")
    private String email;
    
    @Field()
    @Caption("Nome")
    @javax.validation.constraints.NotNull(message="Não pode estar vazio")
    @Column(name = "nome" , nullable=false)
    private String nome;
    
    @Field()
    @Caption("Telefone")
    @Column(name = "telefone" , nullable=false)
    private String telefone;

    private static Logger logger = Logger.getLogger(ContaEmpresa.class);

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


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuarioCriador() {
		return usuarioCriador;
	}

	public void setUsuarioCriador(Usuario usuarioCriador) {
		this.usuarioCriador = usuarioCriador;
	}

	public void setPrimeiroUsuario(Usuario u) {
		this.usuarioCriador = u;
		List<Usuario> usuariosNovos = new ArrayList<Usuario>();
		usuariosNovos.add(u);
		this.setUsuarios(usuariosNovos);
	}

	



    
	
}
