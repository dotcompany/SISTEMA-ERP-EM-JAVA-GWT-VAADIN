package dc.entidade.adm.dotcompany;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import com.sun.istack.logging.Logger;

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
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int ID_MODULO_ADM = -1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parametro")
	@SequenceGenerator(name = "parametro", sequenceName = "parametro_id_seq", allocationSize = 1)
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
	
	@Lob
	@Field
	@Caption("Observacao Fechamento")
	@Type(type = "text")
	@Column(name = "OBSERVACAO_FECHAMENTO", length = 65535)
	@Analyzer(definition = "dc_combo_analyzer")
	private String observacaoFechamento;
	
	@Field
	@Caption("Data Entrada")
	@Column(name = "DATA_ENTRADA")
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataEntrada;
	
	@Field
	@Caption("Vencimento Promoção")
	@Column(name = "VENCIMENTO_ENTRADA")
	@Analyzer(definition = "dc_combo_analyzer")
	private Date vencimentoPromocao;
	
	@Column(name = "DIA_VENCIMENTO")
	@Analyzer(definition = "dc_combo_analyzer")
	private String diaVencimento;
	
	
	private static Logger logger = Logger.getLogger(ParametroCliente.class);
	
	public ParametroCliente() {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[] { "id" });
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof ParametroCliente == false)
			return false;
		if (this == object)
			return true;
		final ParametroCliente other = (ParametroCliente) object;
		return EqualsBuilder.reflectionEquals(this, other);
	}

	@Override
	public String toString() {
		return tipoDeSistema;
	}

}
