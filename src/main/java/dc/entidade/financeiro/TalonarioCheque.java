package dc.entidade.financeiro;

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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.financeiro.type.StatusChequeType;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

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
public class TalonarioCheque extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "talonario_cheque_id_seq")
	@SequenceGenerator(name = "talonario_cheque_id_seq", sequenceName = "talonario_cheque_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@NotNull
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Talao")
	@Column(name = "TALAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Talão é obrigatório") 
	private String talao;

	@Field
	@Caption("Numero")
	@Column(name = "NUMERO")
	@NotNull(message = "Numero é obrigatório") 
	private Integer numero;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Status Talao")
	@Column(name = "STATUS_TALAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Status Talão é Obrigatório!")
	private StatusChequeType statusTalao;

	@Caption("Conta Caixa")
	//@ManyToOne(cascade = { CascadeType.PERSIST })
	@ManyToOne
	@JoinColumn(name = "ID_CONTA_CAIXA", nullable = true)
	@NotNull(message = "Conta Caixa é obrigatório") 
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

	public StatusChequeType getStatusTalao() {
		return statusTalao;
	}

	public void setStatusTalao(StatusChequeType statusTalao) {
		this.statusTalao = statusTalao;
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
	
	/**
	 * TO STRING
	 **/

	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	          return true;
	    }

	    if (!(obj instanceof TalonarioCheque)) {
	           return false;
	    }

	    TalonarioCheque that = (TalonarioCheque) obj;
	    EqualsBuilder eb = new EqualsBuilder();
	    eb.append(getId(), that.getId());
	    return eb.isEquals();
	}

	@Override
	public int hashCode() {
	    if (getId() == null) {
	          return super.hashCode();
	    } else {
	          return new HashCodeBuilder()
	                    .append(id)
	                    .toHashCode();
	    }
	}

}