package dc.entidade.adm.dotcompany;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Type;
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
 * 
 * @author Wesley Jr
 *
 */

@Entity
@Table(name = "dc_empresa_parametro")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ParametroCliente extends AbstractModel<Integer> implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int ID_MODULO_ADM = -1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dc_empresa_parametro")
	@SequenceGenerator(name = "dc_empresa_parametro", sequenceName = "dc_empresa_parametro_id_seq", allocationSize = 1)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;
	
	/////////////////////////////////////////////////////////// INFORMAÇÃO //////////////////////////////////////////////////////////////

	@Field
	@Caption("Tipo de Sistema")
	@Column(name = "TIPO_DE_SISTEMA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String tipoDeSistema;
	
	@Field
	@Caption("Usa Nfe")
	@Column(name = "USA_NFE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String usaNfe;
	
	@Field
	@Caption("Liberado por Quem")
	@Column(name = "LIBERADO_POR_QUEM")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String liberadoQuem;
	
	@Field
	@Caption("Caminho do Banco")
	@Column(name = "CAMINHO_BANCO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String caminhoBanco;
	
	@Field
	@Caption("Vendedor")
	@Column(name = "VENDEDOR")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String vendedor;
	
	@Field
	@Caption("Comissao Vendedor")
	@Column(name = "COMISSAO_VENDEDOR")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String comissaoVendedor;
	
	
	@Field
	@Caption("Agente")
	@Column(name = "AGENTE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String agente;
	
	@Field
	@Caption("Comissao Agente")
	@Column(name = "COMISSAO_AGENTE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String comissaoAgente;
	
	
    /////////////////////////////////////////////////////////// Financeiro (GERAL) //////////////////////////////////////////////////////////////

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
	
	/*@Caption(value = "Quantidade Parcela")
	@Column(name = "QUANTIDADE_PARCELA")
	private Integer quantidadeParcela;*/
	
	@Lob
	@Field
	@Caption("Observacao Fechamento")
	@Type(type = "text")
	@Basic(fetch = javax.persistence.FetchType.LAZY)
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
	private Date diaVencimento;
	
	@Field
	@Caption("Empresa Liberada")
	@Column(name = "EMPRESA_LIBERADA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String empresaLiberada;
	
	@Field
	@Caption("Empresa Bloqueada")
	@Column(name = "EMPRESA_BLOQUEADA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String empresaBloqueada;
	
	@Field
	@Caption("Mostrando Aviso")
	@Column(name = "MOSTRANDO_AVISO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String mostrandoAviso;
	
	@Field
	@Caption("Empresa Bloqueada1")
	@Column(name = "EMPRESA_BLOQUEADA1")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String empresaBloqueada1;
	
	@Lob
	@Field
	@Type(type = "text")
	@Basic(fetch = javax.persistence.FetchType.LAZY)
	@Caption(value = "Obs Fechamento")
	@Column(name = "OBS_FECHAMENTO", length = 632355)
	private String obsFechamento;
	
    /////////////////////////////////////////////////////////// Dados Cobrança //////////////////////////////////////////////////////////////
	
	@Field
	@Caption("Email Principal")
	@Column(name = "EMAIL_PRINCIPAL")
	private String emailPrincipal;
	
	@Field
	@Caption("Email Secundario")
	@Column(name = "EMAIL_SECUNDARIO")
	private String emailSecundario;
	
	@Field
	@Caption("Nome Responsavel")
	@Column(name = "NOME_RESPONSAVEL")
	private String nomeResponsavel;
	
	@Field
	@Caption("Telefone")
	@Column(name = "TELEFONE")
	private String telefone;
	
	@ManyToOne
	@JoinColumn(name = "id_empresa", nullable = false)
	@Caption("Empresa")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private Empresa empresa;
	
	/*@Field
	@Caption("Empresa")
	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
	private Empresa empresa;*/
	
	
	public ParametroCliente() {

	}
	
	public ParametroCliente(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTipoDeSistema() {
		return tipoDeSistema;
	}

	public void setTipoDeSistema(String tipoDeSistema) {
		this.tipoDeSistema = tipoDeSistema;
	}

	public BigDecimal getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(BigDecimal valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public BigDecimal getValorMensPromocional() {
		return valorMensPromocional;
	}

	public void setValorMensPromocional(BigDecimal valorMensPromocional) {
		this.valorMensPromocional = valorMensPromocional;
	}

	public BigDecimal getValorMensalidade() {
		return valorMensalidade;
	}

	public void setValorMensalidade(BigDecimal valorMensalidade) {
		this.valorMensalidade = valorMensalidade;
	}

	public String getTipoDeFatura() {
		return tipoDeFatura;
	}

	public void setTipoDeFatura(String tipoDeFatura) {
		this.tipoDeFatura = tipoDeFatura;
	}

	public String getObservacaoFechamento() {
		return observacaoFechamento;
	}

	public void setObservacaoFechamento(String observacaoFechamento) {
		this.observacaoFechamento = observacaoFechamento;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getVencimentoPromocao() {
		return vencimentoPromocao;
	}

	public void setVencimentoPromocao(Date vencimentoPromocao) {
		this.vencimentoPromocao = vencimentoPromocao;
	}

	public Date getDiaVencimento() {
		return diaVencimento;
	}

	public void setDiaVencimento(Date diaVencimento) {
		this.diaVencimento = diaVencimento;
	}
	
	/*public Integer getQuantidadeParcela() {
		return quantidadeParcela;
	}

	public void setQuantidadeParcela(Integer quantidadeParcela) {
		this.quantidadeParcela = quantidadeParcela;
	}*/
	
	public String getUsaNfe() {
		return usaNfe;
	}

	public void setUsaNfe(String usaNfe) {
		this.usaNfe = usaNfe;
	}

	public String getLiberadoQuem() {
		return liberadoQuem;
	}

	public void setLiberadoQuem(String liberadoQuem) {
		this.liberadoQuem = liberadoQuem;
	}

	public String getCaminhoBanco() {
		return caminhoBanco;
	}

	public void setCaminhoBanco(String caminhoBanco) {
		this.caminhoBanco = caminhoBanco;
	}

	public String getEmpresaLiberada() {
		return empresaLiberada;
	}

	public void setEmpresaLiberada(String empresaLiberada) {
		this.empresaLiberada = empresaLiberada;
	}

	public String getEmpresaBloqueada() {
		return empresaBloqueada;
	}

	public void setEmpresaBloqueada(String empresaBloqueada) {
		this.empresaBloqueada = empresaBloqueada;
	}

	public String getMostrandoAviso() {
		return mostrandoAviso;
	}

	public void setMostrandoAviso(String mostrandoAviso) {
		this.mostrandoAviso = mostrandoAviso;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public String getComissaoVendedor() {
		return comissaoVendedor;
	}

	public void setComissaoVendedor(String comissaoVendedor) {
		this.comissaoVendedor = comissaoVendedor;
	}

	public String getAgente() {
		return agente;
	}

	public void setAgente(String agente) {
		this.agente = agente;
	}

	public String getComissaoAgente() {
		return comissaoAgente;
	}

	public void setComissaoAgente(String comissaoAgente) {
		this.comissaoAgente = comissaoAgente;
	}

	public String getEmpresaBloqueada1() {
		return empresaBloqueada1;
	}

	public void setEmpresaBloqueada1(String empresaBloqueada1) {
		this.empresaBloqueada1 = empresaBloqueada1;
	}

	public String getObsFechamento() {
		return obsFechamento;
	}

	public void setObsFechamento(String obsFechamento) {
		this.obsFechamento = obsFechamento;
	}

	public String getEmailPrincipal() {
		return emailPrincipal;
	}

	public void setEmailPrincipal(String emailPrincipal) {
		this.emailPrincipal = emailPrincipal;
	}

	public String getEmailSecundario() {
		return emailSecundario;
	}

	public void setEmailSecundario(String emailSecundario) {
		this.emailSecundario = emailSecundario;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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
