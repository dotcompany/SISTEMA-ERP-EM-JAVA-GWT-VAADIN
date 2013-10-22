package dc.entidade.contabilidade.cadastro;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
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
 * @author Gutemberg A. Da Silva
 * 
 */

@Entity
@Table(name = "contabil_parametros")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ParametrosEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contabil_parametros_id_seq")
	@SequenceGenerator(name = "contabil_parametros_id_seq", sequenceName = "contabil_parametros_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "mascara")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "mascara")
	private String mascara = "";

	@Field
	@Column(name = "niveis")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "niveis")
	private Integer niveis = new Integer(0);

	@Field
	@Column(name = "informar_conta_por")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "informar_conta_por")
	private String informarContaPor = "";

	@Field
	@Column(name = "compartilha_plano_conta")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "compartilha_plano_conta")
	private String compartilhaPlanoConta = "";

	@Field
	@Column(name = "compartilha_historicos")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "compartilha_historicos")
	private String compartilhaHistoricos = "";

	@Field
	@Column(name = "altera_lancamento_outro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "altera_lancamento_outro")
	private String alteraLancamentoOutro = "";

	@Field
	@Column(name = "historico_obrigatorio")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "historico_obrigatorio")
	private String historicoObrigatorio = "";

	@Field
	@Column(name = "permite_lancamento_zerado")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "permite_lancamento_zerado")
	private String permiteLancamentoZerado = "";

	@Field
	@Column(name = "gera_informativo_sped")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "gera_informativo_sped")
	private String geraInformativoSped = "";

	@Field
	@Column(name = "sped_forma_escrit_diario")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "sped_forma_escrit_diario")
	private String spedFormaEscritDiario = "";

	@Field
	@Column(name = "sped_nome_livro_diario")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "sped_nome_livro_diario")
	private String spedNomeLivroDiario = "";

	@Field
	@Column(name = "assinatura_direita")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "assinatura_direita")
	private String assinaturaDireita = "";

	@Field
	@Column(name = "assinatura_esquerda")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "assinatura_esquerda")
	private String assinaturaEsquerda = "";

	@Field
	@Column(name = "conta_ativo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "conta_ativo")
	private String contaAtivo = "";

	@Field
	@Column(name = "conta_passivo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "conta_passivo")
	private String contaPassivo = "";

	@Field
	@Column(name = "conta_patrimonio_liquido")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "conta_patrimonio_liquido")
	private String contaPatrimonioLiquido = "";

	@Field
	@Column(name = "conta_depreciacao_acumulada")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "conta_depreciacao_acumulada")
	private String contaDepreciacaoAcumulada = "";

	@Field
	@Column(name = "conta_capital_social")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "conta_capital_social")
	private String contaCapitalSocial = "";

	@Field
	@Column(name = "conta_resultado_exercicio")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "conta_resultado_exercicio")
	private String contaResultadoExercicio = "";

	@Field
	@Column(name = "conta_prejuizo_acumulado")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "conta_prejuizo_acumulado")
	private String contaPrejuizoAcumulado = "";

	@Field
	@Column(name = "conta_lucro_acumulado")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "conta_lucro_acumulado")
	private String contaLucroAcumulado = "";

	@Field
	@Column(name = "conta_titulo_pagar")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "conta_titulo_pagar")
	private String contaTituloPagar = "";

	@Field
	@Column(name = "conta_titulo_receber")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "conta_titulo_receber")
	private String contaTituloReceber = "";

	@Field
	@Column(name = "conta_juros_passivo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "conta_juros_passivo")
	private String contaJurosPassivo = "";

	@Field
	@Column(name = "conta_juros_ativo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "conta_juros_ativo")
	private String contaJurosAtivo = "";

	@Field
	@Column(name = "conta_desconto_obtido")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "conta_desconto_obtido")
	private String contaDescontoObtido = "";

	@Field
	@Column(name = "conta_desconto_concedido")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "conta_desconto_concedido")
	private String contaDescontoConcedido = "";

	@Field
	@Column(name = "conta_cmv")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "conta_cmv")
	private String contaCmv = "";

	@Field
	@Column(name = "conta_venda")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "conta_venda")
	private String contaVenda = "";

	@Field
	@Column(name = "conta_venda_servico")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "conta_venda_servico")
	private String contaVendaServico = "";

	@Field
	@Column(name = "conta_estoque")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "conta_estoque")
	private String contaEstoque = "";

	@Field
	@Column(name = "conta_apura_resultado")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "conta_apura_resultado")
	private String contaApuraResultado = "";

	@Field
	@Column(name = "conta_juros_apropriar")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "conta_juros_apropriar")
	private String contaJurosApropriar = "";

	/**
	 * REFERENCIA - FK
	 */

	// id_empresa integer NOT NULL,

	// id_hist_padrao_resultado integer,

	@Field
	@Column(name = "id_hist_padrao_resultado")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "id_hist_padrao_resultado")
	private Integer histPadraoResultado = new Integer(0);

	// id_hist_padrao_lucro integer,

	@Field
	@Column(name = "id_hist_padrao_lucro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "id_hist_padrao_lucro")
	private Integer histPadraoLucro = new Integer(0);

	// id_hist_padrao_prejuizo integer,

	@Field
	@Column(name = "id_hist_padrao_prejuizo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "id_hist_padrao_prejuizo")
	private Integer histPadraoPrejuizo = new Integer(0);

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public ParametrosEntity() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * GETS AND SETS
	 */

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMascara() {
		return mascara;
	}

	public void setMascara(String mascara) {
		this.mascara = mascara;
	}

	public Integer getNiveis() {
		return niveis;
	}

	public void setNiveis(Integer niveis) {
		this.niveis = niveis;
	}

	public String getInformarContaPor() {
		return informarContaPor;
	}

	public void setInformarContaPor(String informarContaPor) {
		this.informarContaPor = informarContaPor;
	}

	public String getCompartilhaPlanoConta() {
		return compartilhaPlanoConta;
	}

	public void setCompartilhaPlanoConta(String compartilhaPlanoConta) {
		this.compartilhaPlanoConta = compartilhaPlanoConta;
	}

	public String getCompartilhaHistoricos() {
		return compartilhaHistoricos;
	}

	public void setCompartilhaHistoricos(String compartilhaHistoricos) {
		this.compartilhaHistoricos = compartilhaHistoricos;
	}

	public String getAlteraLancamentoOutro() {
		return alteraLancamentoOutro;
	}

	public void setAlteraLancamentoOutro(String alteraLancamentoOutro) {
		this.alteraLancamentoOutro = alteraLancamentoOutro;
	}

	public String getHistoricoObrigatorio() {
		return historicoObrigatorio;
	}

	public void setHistoricoObrigatorio(String historicoObrigatorio) {
		this.historicoObrigatorio = historicoObrigatorio;
	}

	public String getPermiteLancamentoZerado() {
		return permiteLancamentoZerado;
	}

	public void setPermiteLancamentoZerado(String permiteLancamentoZerado) {
		this.permiteLancamentoZerado = permiteLancamentoZerado;
	}

	public String getGeraInformativoSped() {
		return geraInformativoSped;
	}

	public void setGeraInformativoSped(String geraInformativoSped) {
		this.geraInformativoSped = geraInformativoSped;
	}

	public String getSpedFormaEscritDiario() {
		return spedFormaEscritDiario;
	}

	public void setSpedFormaEscritDiario(String spedFormaEscritDiario) {
		this.spedFormaEscritDiario = spedFormaEscritDiario;
	}

	public String getSpedNomeLivroDiario() {
		return spedNomeLivroDiario;
	}

	public void setSpedNomeLivroDiario(String spedNomeLivroDiario) {
		this.spedNomeLivroDiario = spedNomeLivroDiario;
	}

	public String getAssinaturaDireita() {
		return assinaturaDireita;
	}

	public void setAssinaturaDireita(String assinaturaDireita) {
		this.assinaturaDireita = assinaturaDireita;
	}

	public String getAssinaturaEsquerda() {
		return assinaturaEsquerda;
	}

	public void setAssinaturaEsquerda(String assinaturaEsquerda) {
		this.assinaturaEsquerda = assinaturaEsquerda;
	}

	public String getContaAtivo() {
		return contaAtivo;
	}

	public void setContaAtivo(String contaAtivo) {
		this.contaAtivo = contaAtivo;
	}

	public String getContaPassivo() {
		return contaPassivo;
	}

	public void setContaPassivo(String contaPassivo) {
		this.contaPassivo = contaPassivo;
	}

	public String getContaPatrimonioLiquido() {
		return contaPatrimonioLiquido;
	}

	public void setContaPatrimonioLiquido(String contaPatrimonioLiquido) {
		this.contaPatrimonioLiquido = contaPatrimonioLiquido;
	}

	public String getContaDepreciacaoAcumulada() {
		return contaDepreciacaoAcumulada;
	}

	public void setContaDepreciacaoAcumulada(String contaDepreciacaoAcumulada) {
		this.contaDepreciacaoAcumulada = contaDepreciacaoAcumulada;
	}

	public String getContaCapitalSocial() {
		return contaCapitalSocial;
	}

	public void setContaCapitalSocial(String contaCapitalSocial) {
		this.contaCapitalSocial = contaCapitalSocial;
	}

	public String getContaResultadoExercicio() {
		return contaResultadoExercicio;
	}

	public void setContaResultadoExercicio(String contaResultadoExercicio) {
		this.contaResultadoExercicio = contaResultadoExercicio;
	}

	public String getContaPrejuizoAcumulado() {
		return contaPrejuizoAcumulado;
	}

	public void setContaPrejuizoAcumulado(String contaPrejuizoAcumulado) {
		this.contaPrejuizoAcumulado = contaPrejuizoAcumulado;
	}

	public String getContaLucroAcumulado() {
		return contaLucroAcumulado;
	}

	public void setContaLucroAcumulado(String contaLucroAcumulado) {
		this.contaLucroAcumulado = contaLucroAcumulado;
	}

	public String getContaTituloPagar() {
		return contaTituloPagar;
	}

	public void setContaTituloPagar(String contaTituloPagar) {
		this.contaTituloPagar = contaTituloPagar;
	}

	public String getContaTituloReceber() {
		return contaTituloReceber;
	}

	public void setContaTituloReceber(String contaTituloReceber) {
		this.contaTituloReceber = contaTituloReceber;
	}

	public String getContaJurosPassivo() {
		return contaJurosPassivo;
	}

	public void setContaJurosPassivo(String contaJurosPassivo) {
		this.contaJurosPassivo = contaJurosPassivo;
	}

	public String getContaJurosAtivo() {
		return contaJurosAtivo;
	}

	public void setContaJurosAtivo(String contaJurosAtivo) {
		this.contaJurosAtivo = contaJurosAtivo;
	}

	public String getContaDescontoObtido() {
		return contaDescontoObtido;
	}

	public void setContaDescontoObtido(String contaDescontoObtido) {
		this.contaDescontoObtido = contaDescontoObtido;
	}

	public String getContaDescontoConcedido() {
		return contaDescontoConcedido;
	}

	public void setContaDescontoConcedido(String contaDescontoConcedido) {
		this.contaDescontoConcedido = contaDescontoConcedido;
	}

	public String getContaCmv() {
		return contaCmv;
	}

	public void setContaCmv(String contaCmv) {
		this.contaCmv = contaCmv;
	}

	public String getContaVenda() {
		return contaVenda;
	}

	public void setContaVenda(String contaVenda) {
		this.contaVenda = contaVenda;
	}

	public String getContaVendaServico() {
		return contaVendaServico;
	}

	public void setContaVendaServico(String contaVendaServico) {
		this.contaVendaServico = contaVendaServico;
	}

	public String getContaEstoque() {
		return contaEstoque;
	}

	public void setContaEstoque(String contaEstoque) {
		this.contaEstoque = contaEstoque;
	}

	public String getContaApuraResultado() {
		return contaApuraResultado;
	}

	public void setContaApuraResultado(String contaApuraResultado) {
		this.contaApuraResultado = contaApuraResultado;
	}

	public String getContaJurosApropriar() {
		return contaJurosApropriar;
	}

	public void setContaJurosApropriar(String contaJurosApropriar) {
		this.contaJurosApropriar = contaJurosApropriar;
	}

	public Integer getHistPadraoResultado() {
		return histPadraoResultado;
	}

	public void setHistPadraoResultado(Integer histPadraoResultado) {
		this.histPadraoResultado = histPadraoResultado;
	}

	public Integer getHistPadraoLucro() {
		return histPadraoLucro;
	}

	public void setHistPadraoLucro(Integer histPadraoLucro) {
		this.histPadraoLucro = histPadraoLucro;
	}

	public Integer getHistPadraoPrejuizo() {
		return histPadraoPrejuizo;
	}

	public void setHistPadraoPrejuizo(Integer histPadraoPrejuizo) {
		this.histPadraoPrejuizo = histPadraoPrejuizo;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}