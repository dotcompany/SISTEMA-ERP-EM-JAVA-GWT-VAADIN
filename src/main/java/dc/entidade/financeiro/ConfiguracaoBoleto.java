package dc.entidade.financeiro;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;

@Entity
@Table(name = "CONFIGURACAO_BOLETO")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ConfiguracaoBoleto extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;

	@Field
	@Column(name = "INSTRUCAO01")
	@Caption(value = "Instrução01")
	private String instrucao01;

	@Field
	@Column(name = "INSTRUCAO02")
	@Caption(value = "Instrução02")
	private String instrucao02;

	@Field
	@Column(name = "CAMINHO_ARQUIVO_REMESSA")
	@Caption(value = "Caminho Arquivo Remessa")
	private String caminhoArquivoRemessa;

	@Field
	@Column(name = "CAMINHO_ARQUIVO_RETORNO")
	@Caption(value = "Caminho Arquivo Retorno")
	private String caminhoArquivoRetorno;

	@Field
	@Column(name = "CAMINHO_ARQUIVO_LOGOTIPO")
	@Caption(value = "Caminho Arquivo Logotipo")
	private String caminhoArquivoLogotipo;

	@Field
	@Column(name = "CAMINHO_ARQUIVO_PDF")
	@Caption(value = "Caminho Arquivo PDF")
	private String caminhoArquivoPdf;

	@Field
	@Column(name = "MENSAGEM")
	@Caption(value = "Mensagem")
	private String mensagem;

	@Field
	@Column(name = "LOCAL_PAGAMENTO")
	@Caption(value = "Local Pagamento")
	private String localPagamento;

	@Field
	@Column(name = "LAYOUT_REMESSA")
	@Caption(value = "Layout Remessa")
	private String layoutRemessa;

	@Field
	@Column(name = "ACEITE")
	@Caption(value = "Aceite")
	private String aceite;

	@Field
	@Column(name = "ESPECIE")
	@Caption(value = "Espécie")
	private String especie;

	@Field
	@Column(name = "CARTEIRA")
	@Caption(value = "Carteira")
	private String carteira;

	@Field
	@Column(name = "CODIGO_CONVENIO")
	@Caption(value = "Código Convénio")
	private String codigoConvenio;

	@Field
	@Column(name = "CODIGO_CEDENTE")
	@Caption(value = "Código Cedente")
	private String codigoCedente;

	@Field
	@Column(name = "TAXA_MULTA")
	@Caption(value = "Taxa Multa")
	private BigDecimal taxaMulta;

	@JoinColumn(name = "ID_CONTA_CAIXA", referencedColumnName = "ID")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@Caption(value = "Conta Caixa")
	private ContaCaixa contaCaixa;

	public ConfiguracaoBoleto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInstrucao01() {
		return instrucao01;
	}

	public void setInstrucao01(String instrucao01) {
		this.instrucao01 = instrucao01;
	}

	public String getInstrucao02() {
		return instrucao02;
	}

	public void setInstrucao02(String instrucao02) {
		this.instrucao02 = instrucao02;
	}

	public String getCaminhoArquivoRemessa() {
		return caminhoArquivoRemessa;
	}

	public void setCaminhoArquivoRemessa(String caminhoArquivoRemessa) {
		this.caminhoArquivoRemessa = caminhoArquivoRemessa;
	}

	public String getCaminhoArquivoRetorno() {
		return caminhoArquivoRetorno;
	}

	public void setCaminhoArquivoRetorno(String caminhoArquivoRetorno) {
		this.caminhoArquivoRetorno = caminhoArquivoRetorno;
	}

	public String getCaminhoArquivoLogotipo() {
		return caminhoArquivoLogotipo;
	}

	public void setCaminhoArquivoLogotipo(String caminhoArquivoLogotipo) {
		this.caminhoArquivoLogotipo = caminhoArquivoLogotipo;
	}

	public String getCaminhoArquivoPdf() {
		return caminhoArquivoPdf;
	}

	public void setCaminhoArquivoPdf(String caminhoArquivoPdf) {
		this.caminhoArquivoPdf = caminhoArquivoPdf;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getLocalPagamento() {
		return localPagamento;
	}

	public void setLocalPagamento(String localPagamento) {
		this.localPagamento = localPagamento;
	}

	public String getLayoutRemessa() {
		return layoutRemessa;
	}

	public void setLayoutRemessa(String layoutRemessa) {
		this.layoutRemessa = layoutRemessa;
	}

	public String getAceite() {
		return aceite;
	}

	public void setAceite(String aceite) {
		this.aceite = aceite;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getCarteira() {
		return carteira;
	}

	public void setCarteira(String carteira) {
		this.carteira = carteira;
	}

	public String getCodigoConvenio() {
		return codigoConvenio;
	}

	public void setCodigoConvenio(String codigoConvenio) {
		this.codigoConvenio = codigoConvenio;
	}

	public String getCodigoCedente() {
		return codigoCedente;
	}

	public void setCodigoCedente(String codigoCedente) {
		this.codigoCedente = codigoCedente;
	}

	public BigDecimal getTaxaMulta() {
		return taxaMulta;
	}

	public void setTaxaMulta(BigDecimal taxaMulta) {
		this.taxaMulta = taxaMulta;
	}

	public ContaCaixa getContaCaixa() {
		return contaCaixa;
	}

	public void setContaCaixa(ContaCaixa contaCaixa) {
		this.contaCaixa = contaCaixa;
	}

	@Override
	public String toString() {
		return "com.t2tierp.financeiro.java.FinConfiguracaoBoleto[id=" + id + " |instrucao01=" + instrucao01 + "]";
	}
}
