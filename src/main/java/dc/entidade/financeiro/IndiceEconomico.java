package dc.entidade.financeiro;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Field;

import dc.anotacoes.Caption;
import dc.entidade.contabilidade.cadastro.IndiceEntity;
import dc.entidade.diversos.Pais;

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
@Table(name = "indice_economico")
public class IndiceEconomico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	private Integer id;

	/*
	 * @Basic(optional = false)
	 * 
	 * @Column(name = "PAIS_ID", nullable = false) private int paisId;
	 */
	@Field
	@Caption("Sigla")
	@Column(name = "SIGLA", length = 50)
	private String sigla;

	@Field
	@Caption("Nome")
	@Column(name = "NOME", length = 50)
	private String nome;

	@Lob
	@Field
	@Caption("Descricao")
	@Type(type = "text")
	@Column(name = "DESCRICAO", length = 65535)
	private String descricao;

	/*
	 * Mapeamento Pais-Indice
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PAIS_ID", insertable = true, updatable = true)
	@Fetch(FetchMode.JOIN)
	private Pais paisId;

	/**
	 * @autor Gutemberg A. Da Silva
	 * 
	 * @module CONTABILIDADE
	 */

	@OneToMany(mappedBy = "indiceEconomico")
	private List<IndiceEntity> indiceList;

	/**
	 * CONSTRUTOR
	 */

	public IndiceEconomico() {

	}

	public IndiceEconomico(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Pais getPaisId() {
		return paisId;
	}

	public void setPaisId(Pais paisId) {
		this.paisId = paisId;
	}

	public List<IndiceEntity> getIndiceList() {
		return indiceList;
	}

	public void setIndiceList(List<IndiceEntity> indiceList) {
		this.indiceList = indiceList;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[] { "id" });
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof IndiceEconomico == false)
			return false;
		if (this == object)
			return true;
		final IndiceEconomico other = (IndiceEconomico) object;
		return EqualsBuilder.reflectionEquals(this, other);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}