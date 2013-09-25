package dc.entidade.financeiro;

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
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.framework.Empresa;

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
@Table(name = "talonario_cheque")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class TalonarioCheque extends AbstractModel<Integer> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Talao")
	@Column(name = "TALAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String talao;

	@Field
	@Caption("Numero")
	@Column(name = "NUMERO")
	private Integer numero;

	@Field
	@Caption("Status Talao")
	@Column(name = "STATUS_TALAO")
	private String statusTalao;

	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "talonarioCheque")
	// private List<ChequeVO> chequeVOList;
	@JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Empresa empresa;

	@JoinColumn(name = "ID_CONTA_CAIXA", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private ContaCaixa contaCaixa;

	public TalonarioCheque() {

	}

	public TalonarioCheque(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTalao() {
		return talao;
	}

	public void setTalao(String talao) {
		this.talao = talao;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[] { "id" });
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof TalonarioCheque == false)
			return false;

		if (this == object)
			return true;

		final TalonarioCheque other = (TalonarioCheque) object;

		return EqualsBuilder.reflectionEquals(this, other);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getStatusTalao() {
		return statusTalao;
	}

	public void setStatusTalao(String statusTalao) {
		this.statusTalao = statusTalao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa
	 *            the empresa to set
	 */
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return the contaCaixa
	 */
	public ContaCaixa getContaCaixa() {
		return contaCaixa;
	}

	/**
	 * @param contaCaixa
	 *            the contaCaixa to set
	 */
	public void setContaCaixa(ContaCaixa contaCaixa) {
		this.contaCaixa = contaCaixa;
	}

}