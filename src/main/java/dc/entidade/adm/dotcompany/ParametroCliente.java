package dc.entidade.adm.dotcompany;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

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
 * 
 * @author Wesley Jr
 *
 */

@Entity
@Table(name = "dc_empresa_parametro")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ParametroCliente extends AbstractMultiEmpresaModel<Integer> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pes")
	@SequenceGenerator(name = "pes", sequenceName = "pessoa_id_seq", allocationSize = 1)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Tipo de Sistema")
	@Column(name = "TIPO_DE_SISTEMA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String tipoDeSistema;

	@Field
	@Caption("Valor Entrada")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Column(name = "VALOR_ENTRADA", precision = 18, scale = 6)
	private BigDecimal valorEntrada;

	@Field
	@Caption("Valor Mensalidade Promocional")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Column(name = "VALOR_MENS_PROMOCIONAL", precision = 18, scale = 6)
	private BigDecimal valorMensPromocional;
	
	@Field
	@Caption("Valor Mensalidade")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Column(name = "VALOR_MENSALIDADE", precision = 18, scale = 6)
	private BigDecimal valorMensalidade;
	
	@Field
	@Caption("Tipo de Fatura")
	@Column(name = "TIPO_DE_FATURA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String tipoDeFatura;

	@Override
	public Integer getId() {
		return null;
	}
	
	
	

}
