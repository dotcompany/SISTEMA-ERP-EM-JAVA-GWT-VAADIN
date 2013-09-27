package dc.entidade.tributario;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.framework.Empresa;

@Entity
@Table(name = "tribut_configura_of_gt")
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ConfiguracaoTributaria extends AbstractModel<Integer> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cfg")
	@SequenceGenerator(name = "cfg", sequenceName = "tribut_configura_of_gt_id_seq", allocationSize = 1)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Empresa empresa;

	@JoinColumn(name = "id_tribut_grupo_tributario", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	@Caption("Grupo Tributário")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private GrupoTributario grupoTributario;

	@JoinColumn(name = "id_tribut_operacao_fiscal", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	@Caption("Operação Fiscal")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private OperacaoFiscal operacaoFiscal;

	public ConfiguracaoTributaria() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public GrupoTributario getGrupoTributario() {
		return grupoTributario;
	}

	public void setGrupoTributario(GrupoTributario grupoTributario) {
		this.grupoTributario = grupoTributario;
	}

	public OperacaoFiscal getOperacaoFiscal() {
		return operacaoFiscal;
	}

	public void setOperacaoFiscal(OperacaoFiscal operacaoFiscal) {
		this.operacaoFiscal = operacaoFiscal;
	}

}