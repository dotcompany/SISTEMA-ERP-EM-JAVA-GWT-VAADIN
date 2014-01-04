package dc.entidade.tributario;

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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

@Entity
@Table(name = "tribut_icms_custom_cab")
@SuppressWarnings("serial")
@XmlRootElement
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class ICMSCustomizado extends AbstractMultiEmpresaModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trb")
	@SequenceGenerator(name = "trb", sequenceName = "tribut_icms_custom_cab_id_seq", allocationSize = 1)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;
	
	
	@Caption("Descrição")
	@Column(name="descricao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	String nome;
	
	@Caption("Origem Mercadoria")
	@Column(name="origem_mercadoria")
	String origemMercadoria;
	
	@Transient
	@Caption("Origem")
	String origemStr;
	
//	@ManyToOne
//	@JoinColumn(name="id_empresa")
//	Empresa empresa;
	
	@OneToMany(mappedBy="icmsCustomizado",cascade=CascadeType.REMOVE,fetch=FetchType.EAGER)
	private List<ICMSCustomizadoDetalhe> detalhes = new ArrayList<ICMSCustomizadoDetalhe>();
	
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

	public String getOrigemMercadoria() {
		return origemMercadoria;
	}

	public void setOrigemMercadoria(String origemMercadoria) {
		this.origemMercadoria = origemMercadoria;
	}

//	public Empresa getEmpresa() {
//		return empresa;
//	}
//
//	public void setEmpresa(Empresa empresa) {
//		this.empresa = empresa;
//	}

	public List<ICMSCustomizadoDetalhe> getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(List<ICMSCustomizadoDetalhe> detalhes) {
		this.detalhes = detalhes;
	}
		
	
    
	public void adicionarDetalhe(ICMSCustomizadoDetalhe detalhe){
    	getDetalhes().add(detalhe);
    	detalhe.setIcmsCustomizado(this);
    }

	public String getOrigemStr() {
		origemStr = origemMercadoria.equals("0") ? "Nacional" : "Estrangeira";
		return origemStr;
	}

	public void setOrigemStr(String origemStr) {
		this.origemStr = origemStr;
	}
	
	
	
	
}
