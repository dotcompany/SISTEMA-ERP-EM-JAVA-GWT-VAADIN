package dc.entidade.pessoal;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.contabilidade.ContabilConta;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.Pessoa;

/**
 * 
 * @author Wesley Jr
 * 
 */

@Entity
@Table(name = "transportadora")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class Transportadora extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transportadora_id_seq")
	@SequenceGenerator(name = "transportadora_id_seq", sequenceName = "transportadora_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Lob
	@Field
	@Caption("Observacao")
	@Type(type = "text")
	@Column(name = "OBSERVACAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String observacao;

	@JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Pessoa pessoa;

	@JoinColumn(name = "ID_CONTABIL_CONTA", referencedColumnName = "ID")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private ContabilConta contaContabil;

	public Transportadora() {

	}

	public Transportadora(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public ContabilConta getContaContabil() {
		return contaContabil;
	}

	public void setContaContabil(ContabilConta contaContabil) {
		this.contaContabil = contaContabil;
	}

	@Override
	public String toString() {
		return pessoa.getNome();
	}

}