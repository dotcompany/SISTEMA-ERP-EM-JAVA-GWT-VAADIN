package dc.entidade.framework;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.control.enums.SimNaoEn;
import dc.entidade.administrativo.seguranca.PapelEntity;

@Entity
@Table(name = "papel_menu")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class PapelMenu implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "papel_menu_id_seq")
	@SequenceGenerator(name = "papel_menu_id_seq", sequenceName = "papel_menu_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Pode consultar")
	@Column(name = "PODE_CONSULTAR")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private SimNaoEn podeConsultar;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Pode inserir")
	@Column(name = "PODE_INSERIR")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private SimNaoEn podeInserir;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Pode alterar")
	@Column(name = "PODE_ALTERAR")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private SimNaoEn podeAlterar;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Pode excluir")
	@Column(name = "PODE_EXCLUIR")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private SimNaoEn podeExcluir;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Habilitado")
	@Column(name = "HABILITADO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private SimNaoEn habilitado;

	/**
	 * REFERENCIA - FK
	 */

	@ManyToOne()
	@JoinColumn(name = "id_menu")
	private FmMenu menu;

	@ManyToOne()
	@JoinColumn(name = "id_papel")
	private PapelEntity papel;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public PapelMenu() {

	}

	public PapelMenu(Integer id) {
		this.id = id;
	}

	public PapelMenu(FmMenu f, PapelEntity p) {
		this.menu = f;
		this.papel = p;
	}

	/**
	 * GETS AND SETS
	 */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SimNaoEn getPodeConsultar() {
		return podeConsultar;
	}

	public void setPodeConsultar(SimNaoEn podeConsultar) {
		this.podeConsultar = podeConsultar;
	}

	public SimNaoEn getPodeInserir() {
		return podeInserir;
	}

	public void setPodeInserir(SimNaoEn podeInserir) {
		this.podeInserir = podeInserir;
	}

	public SimNaoEn getPodeAlterar() {
		return podeAlterar;
	}

	public void setPodeAlterar(SimNaoEn podeAlterar) {
		this.podeAlterar = podeAlterar;
	}

	public SimNaoEn getPodeExcluir() {
		return podeExcluir;
	}

	public void setPodeExcluir(SimNaoEn podeExcluir) {
		this.podeExcluir = podeExcluir;
	}

	public SimNaoEn getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(SimNaoEn habilitado) {
		this.habilitado = habilitado;
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

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}