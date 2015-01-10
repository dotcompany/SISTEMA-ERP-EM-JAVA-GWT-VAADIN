package dc.entidade.tributario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.control.enums.OrigemMercadoriaEn;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

@Entity
@Table(name = "tribut_icms_custom_cab")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class IcmsCustomizadoCabecalhoEntity extends
		AbstractMultiEmpresaModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tribut_icms_custom_cab_id_seq")
	@SequenceGenerator(name = "tribut_icms_custom_cab_id_seq", sequenceName = "tribut_icms_custom_cab_id_seq", allocationSize = 1)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Descrição")
	@Column(name = "descricao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nome;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Origem da mercadoria")
	@Column(name = "origem_mercadoria")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private OrigemMercadoriaEn origemMercadoria;

	/**
	 * REFERENCIA - FK
	 */

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "icmsCustomizado", cascade = CascadeType.REMOVE)
	private List<IcmsCustomizadoDetalheEntity> detalhes = new ArrayList<IcmsCustomizadoDetalheEntity>();

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public IcmsCustomizadoCabecalhoEntity() {
		// TODO Auto-generated constructor stub
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
		this.nome = nome;
	}

	public OrigemMercadoriaEn getOrigemMercadoria() {
		return origemMercadoria;
	}

	public void setOrigemMercadoria(OrigemMercadoriaEn origemMercadoria) {
		this.origemMercadoria = origemMercadoria;
	}

	public List<IcmsCustomizadoDetalheEntity> getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(List<IcmsCustomizadoDetalheEntity> detalhes) {
		this.detalhes = detalhes;
	}

	public void adicionarDetalhe(IcmsCustomizadoDetalheEntity detalhe) {
		getDetalhes().add(detalhe);
		detalhe.setIcmsCustomizado(this);
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}