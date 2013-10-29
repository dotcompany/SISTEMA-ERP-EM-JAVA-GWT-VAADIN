package dc.entidade.contabilidade.livrocontabil;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
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

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Entity
@Table(name = "contabil_termo")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class TermoEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contabil_termo_id_seq")
	@SequenceGenerator(name = "contabil_termo_id_seq", sequenceName = "contabil_termo_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "abertura_encerramento")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Abertura encerramento")
	private String aberturaEncerramento = "";

	@Field
	@Column(name = "numero")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Número")
	private Integer numero = new Integer(0);

	@Field
	@Column(name = "pagina_inicial")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Página inicial")
	private Integer paginaInicial = new Integer(0);

	@Field
	@Column(name = "pagina_final")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Página final")
	private Integer paginaFinal = new Integer(0);

	@Field
	@Column(name = "registrado")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Registrado")
	private String registrado = "";

	@Field
	@Column(name = "numero_registro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Número do registro")
	private String numeroRegistro = "";

	@Field
	@Column(name = "data_despacho")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Data do despacho")
	private Date dataDespacho;

	@Field
	@Column(name = "data_abertura")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Data da abertura")
	private Date dataAbertura;

	@Field
	@Column(name = "data_encerramento")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Data do encerramento")
	private Date dataEncerramento;

	@Field
	@Column(name = "escrituracao_inicio")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Escrituração início")
	private Date escrituracaoInicio;

	@Field
	@Column(name = "escrituracao_fim")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Escrituração término")
	private Date escrituracaoFim;

	@Field
	@Column(name = "texto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Texto")
	private String texto = "";

	/**
	 * REFERENCIA - FK
	 */

	// id_empresa integer,

	// id_contabil_livro integer NOT NULL,

	@ManyToOne
	@JoinColumn(name = "id_contabil_livro", nullable = false)
	@Caption("Livro")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private LivroEntity livro;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public TermoEntity() {
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

	public String getAberturaEncerramento() {
		return aberturaEncerramento;
	}

	public void setAberturaEncerramento(String aberturaEncerramento) {
		this.aberturaEncerramento = (aberturaEncerramento == null ? ""
				: aberturaEncerramento.toUpperCase());
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = (numero == null ? new Integer(0) : numero);
	}

	public Integer getPaginaInicial() {
		return paginaInicial;
	}

	public void setPaginaInicial(Integer paginaInicial) {
		this.paginaInicial = (paginaInicial == null ? new Integer(0)
				: paginaInicial);
	}

	public Integer getPaginaFinal() {
		return paginaFinal;
	}

	public void setPaginaFinal(Integer paginaFinal) {
		this.paginaFinal = (paginaFinal == null ? new Integer(0) : paginaFinal);
	}

	public String getRegistrado() {
		return registrado;
	}

	public void setRegistrado(String registrado) {
		this.registrado = (registrado == null ? "" : registrado.toUpperCase());
	}

	public String getNumeroRegistro() {
		return numeroRegistro;
	}

	public void setNumeroRegistro(String numeroRegistro) {
		this.numeroRegistro = (numeroRegistro == null ? "" : numeroRegistro
				.toUpperCase());
	}

	public Date getDataDespacho() {
		return dataDespacho;
	}

	public void setDataDespacho(Date dataDespacho) {
		this.dataDespacho = dataDespacho;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public Date getEscrituracaoInicio() {
		return escrituracaoInicio;
	}

	public void setEscrituracaoInicio(Date escrituracaoInicio) {
		this.escrituracaoInicio = escrituracaoInicio;
	}

	public Date getEscrituracaoFim() {
		return escrituracaoFim;
	}

	public void setEscrituracaoFim(Date escrituracaoFim) {
		this.escrituracaoFim = escrituracaoFim;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = (texto == null ? "" : texto.toUpperCase());
	}

	public LivroEntity getLivro() {
		return livro;
	}

	public void setLivro(LivroEntity livro) {
		this.livro = livro;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}