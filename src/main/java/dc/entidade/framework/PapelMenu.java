package dc.entidade.framework;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.entidade.administrativo.seguranca.PapelEntity;

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
@Table(name = "papel_menu")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class PapelMenu implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	private Integer id;

	@ManyToOne()
	@JoinColumn(name = "id_menu")
	private FmMenu menu;

	@ManyToOne()
	@JoinColumn(name = "id_papel")
	private PapelEntity papel;

	@Column(name = "PODE_CONSULTAR")
	private Character podeConsultar;

	@Column(name = "PODE_INSERIR")
	private Character podeInserir;

	@Column(name = "PODE_ALTERAR")
	private Character podeAlterar;

	@Column(name = "PODE_EXCLUIR")
	private Character podeExcluir;

	@Column(name = "HABILITADO")
	private Character habilitado;

	public PapelMenu() {
	}

	public PapelMenu(Integer id) {
		this.id = id;
	}

	public PapelMenu(FmMenu f, PapelEntity p) {
		this.menu = f;
		this.papel = p;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Character getPodeConsultar() {
		return podeConsultar;
	}

	public void setPodeConsultar(Character podeConsultar) {
		this.podeConsultar = podeConsultar;
	}

	public Character getPodeInserir() {
		return podeInserir;
	}

	public void setPodeInserir(Character podeInserir) {
		this.podeInserir = podeInserir;
	}

	public Character getPodeAlterar() {
		return podeAlterar;
	}

	public void setPodeAlterar(Character podeAlterar) {
		this.podeAlterar = podeAlterar;
	}

	public Character getPodeExcluir() {
		return podeExcluir;
	}

	public void setPodeExcluir(Character podeExcluir) {
		this.podeExcluir = podeExcluir;
	}

	public Character getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Character habilitado) {
		this.habilitado = habilitado;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[] { "id" });
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof PapelMenu == false)
			return false;
		if (this == object)
			return true;
		final PapelMenu other = (PapelMenu) object;
		return EqualsBuilder.reflectionEquals(this, other);

	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public FmMenu getMenu() {
		return menu;
	}

	public void setMenu(FmMenu menu) {
		this.menu = menu;
	}

	public PapelEntity getPapel() {
		return papel;
	}

	public void setPapel(PapelEntity papel) {
		this.papel = papel;
	}

}
