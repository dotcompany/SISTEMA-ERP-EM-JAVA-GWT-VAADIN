package dc.entidade.financeiro;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.contabilidade.ContabilConta;
import dc.entidade.framework.AbstractModel;
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
@Table(name = "conta_caixa")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ContaCaixa extends AbstractModel<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conta_caixa_id_seq")
	@SequenceGenerator(name = "conta_caixa_id_seq", sequenceName = "conta_caixa_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Column(name = "CODIGO")
	private String codigo = "";

	@Field
	@Caption("Digito")
	@Column(name = "DIGITO")
	private String digito = "";

	@Field
	@Caption("Nome")
	@Column(name = "NOME")
	private String nome = "";

	@Lob
	@Field
	@Type(type = "text")
	@Caption("Descricao")
	@Column(name = "DESCRICAO")
	private String descricao = "";

	@Column(name = "TIPO")
	private String tipo = "";

	@ManyToOne
	@JoinColumn(name = "ID_AGENCIA_BANCO", nullable = true)
	private AgenciaBanco agenciaBanco;

	@ManyToOne
	@JoinColumn(name = "ID_EMPRESA", nullable = false)
	private Empresa empresa;

	@ManyToOne
	@JoinColumn(name = "ID_CONTABIL_CONTA", nullable = true)
	private ContabilConta contabilConta;

	public ContaCaixa() {

	}

	public ContaCaixa(Integer id) {
		this.id = id;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = (codigo == null ? "" : codigo.toUpperCase());
	}

	public String getDigito() {
		return digito;
	}

	public void setDigito(String digito) {
		this.digito = (digito == null ? "" : digito.toUpperCase());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = (nome == null ? "" : nome.toUpperCase());
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = (descricao == null ? "" : descricao.toUpperCase());
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = (tipo == null ? "" : tipo.toUpperCase());
	}

	public AgenciaBanco getAgenciaBanco() {
		return agenciaBanco;
	}

	public void setAgenciaBanco(AgenciaBanco agenciaBanco) {
		this.agenciaBanco = agenciaBanco;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public ContabilConta getContabilConta() {
		return contabilConta;
	}

	public void setContabilConta(ContabilConta contabilConta) {
		this.contabilConta = contabilConta;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}