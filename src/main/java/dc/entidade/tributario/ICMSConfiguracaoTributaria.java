package dc.entidade.tributario;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.geral.tabela.Cfop;
import dc.entidade.geral.tabela.Csosnb;
import dc.entidade.geral.tabela.CstIcmsB;

@Entity
@Table(name = "tribut_icms_uf")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class ICMSConfiguracaoTributaria extends AbstractMultiEmpresaModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "icm")
	@SequenceGenerator(name = "icm", sequenceName = "tribut_icms_uf_id_seq", allocationSize = 1)
	private Integer id;

	@Column(name="uf_destino")
	private String ufDestino;

	@ManyToOne
	@JoinColumn(name="cfop")
	private Cfop cfop;

	@Column(name="csosn_b")
	private String csosnB;

	@Column(name="cst_b")
	private String cstB;

	@Column(name="modalidade_bc")
	String modalidadeBc;

	@Column(name="aliquota")
	Integer aliquota;

	@Column(name="valor_pauta")
	BigDecimal valorPauta;

	@Column(name="valor_preco_maximo")
	BigDecimal valorPrecoMaximo;

	@ManyToOne
	@JoinColumn(name="id_tribut_configura_of_gt")
	ConfiguracaoTributaria configuracaoTributaria;
	
	@Transient
	private Csosnb csosn;
	
	@Transient
	private CstIcmsB cst;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUfDestino() {
		return ufDestino;
	}

	public void setUfDestino(String ufDestino) {
		this.ufDestino = ufDestino;
	}

	

	

	public String getCsosnB() {
		return csosnB;
	}

	public void setCsosnB(String csosnB) {
		this.csosnB = csosnB;
	}

	public String getCstB() {
		return cstB;
	}

	public void setCstB(String cstB) {
		this.cstB = cstB;
	}

	public String getModalidadeBc() {
		return modalidadeBc;
	}

	public void setModalidadeBc(String modalidadeBc) {
		this.modalidadeBc = modalidadeBc;
	}

	

	public BigDecimal getValorPauta() {
		return valorPauta;
	}

	public void setValorPauta(BigDecimal valorPauta) {
		this.valorPauta = valorPauta;
	}

	public BigDecimal getValorPrecoMaximo() {
		return valorPrecoMaximo;
	}

	public void setValorPrecoMaximo(BigDecimal valorPrecoMaximo) {
		this.valorPrecoMaximo = valorPrecoMaximo;
	}

	public ConfiguracaoTributaria getConfiguracaoTributaria() {
		return configuracaoTributaria;
	}

	public void setConfiguracaoTributaria(
			ConfiguracaoTributaria configuracaoTributaria) {
		this.configuracaoTributaria = configuracaoTributaria;
	}

	public Cfop getCfop() {
		return cfop;
	}

	public void setCfop(Cfop cfop) {
		this.cfop = cfop;
	}

	public Integer getAliquota() {
		return aliquota;
	}

	public void setAliquota(Integer aliquota) {
		this.aliquota = aliquota;
	}

	public Csosnb getCsosn() {
		return csosn;
	}

	public void setCsosn(Csosnb csosn) {
		this.csosn = csosn;
	}

	public CstIcmsB getCst() {
		return cst;
	}

	public void setCst(CstIcmsB cst) {
		this.cst = cst;
	}
	

}
