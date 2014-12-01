package dc.entidade.diversos;

import java.io.Serializable;
import java.math.BigDecimal;

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
import dc.entidade.contabilidade.ContabilContaEntity;
import dc.entidade.financeiro.ContaCaixa;
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
@Table(name = "operadora_cartao")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class OperadoraCartao extends AbstractMultiEmpresaModel<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Bandeira")
	@Column(name = "BANDEIRA", length = 30)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String bandeira;

	@Field
	@Caption("Nome")
	@Column(name = "NOME", length = 50)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nome;

	@Column(name = "TAXA_ADM", precision = 14, scale = 0)
	private BigDecimal taxaAdm;

	@Column(name = "TAXA_ADM_DEBITO", precision = 14, scale = 0)
	private BigDecimal taxaAdmDebito;

	@Column(name = "VALOR_ALUGUEL_POS_PIN", precision = 14, scale = 0)
	private BigDecimal valorAluguelPosPin;

	@Column(name = "VENCIMENTO_ALUGUEL")
	private Integer vencimentoAluguel;

	@Column(name = "FONE1")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String fone1;

	@Column(name = "FONE2")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String fone2;
	
	@ManyToOne
	@JoinColumn(name = "ID_CONTA_CAIXA", nullable = false)
	@Caption("Conta Caixa")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private ContaCaixa contaCaixa;

	@JoinColumn(name = "ID_CONTABIL_CONTA", referencedColumnName = "ID")
	@ManyToOne
	private ContabilContaEntity contabilConta;

	public OperadoraCartao() {

	}

	public OperadoraCartao(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getTaxaAdm() {
		return taxaAdm;
	}

	public void setTaxaAdm(BigDecimal taxaAdm) {
		this.taxaAdm = taxaAdm;
	}

	public BigDecimal getTaxaAdmDebito() {
		return taxaAdmDebito;
	}

	public void setTaxaAdmDebito(BigDecimal taxaAdmDebito) {
		this.taxaAdmDebito = taxaAdmDebito;
	}

	public BigDecimal getValorAluguelPosPin() {
		return valorAluguelPosPin;
	}

	public void setValorAluguelPosPin(BigDecimal valorAluguelPosPin) {
		this.valorAluguelPosPin = valorAluguelPosPin;
	}

	public Integer getVencimentoAluguel() {
		return vencimentoAluguel;
	}

	public void setVencimentoAluguel(Integer vencimentoAluguel) {
		this.vencimentoAluguel = vencimentoAluguel;
	}

	public String getFone1() {
		return fone1;
	}

	public void setFone1(String fone1) {
		this.fone1 = fone1;
	}

	public String getFone2() {
		return fone2;
	}

	public void setFone2(String fone2) {
		this.fone2 = fone2;
	}
	
	public ContaCaixa getContaCaixa() {
		return contaCaixa;
	}

	public void setContaCaixa(ContaCaixa contaCaixa) {
		this.contaCaixa = contaCaixa;
	}

	public ContabilContaEntity getContabilConta() {
		return contabilConta;
	}

	public void setContabilConta(ContabilContaEntity contabilConta) {
		this.contabilConta = contabilConta;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[] { "id" });
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof OperadoraCartao == false)
			return false;

		if (this == object)
			return true;

		final OperadoraCartao other = (OperadoraCartao) object;

		return EqualsBuilder.reflectionEquals(this, other);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}