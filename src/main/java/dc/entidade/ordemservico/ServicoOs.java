package dc.entidade.ordemservico;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Field;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

@Entity
@Table(name = "os_servico")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class ServicoOs extends AbstractModel<Integer> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;
	
	@Field
	@Caption("DESCRICAO")
	@Column(name = "descricao")
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "id_grupo", referencedColumnName = "id")
	private Grupo grupo;

	@ManyToOne
	@JoinColumn(name = "id_sub_grupo", referencedColumnName = "id")
	private SubGrupo subGrupo;
	
	@Column(name = "aliquota_issqn")
	private Double aliquotaIssqn;

	@Column(name = "comissao_tecnico_reais")
	private Boolean comissaoTecnicoReais;

	@Column(name = "valor_comissao_tecnico")
	private Double valorComissaoTecnico;

	@Column(name = "comissao_vendedor_reais")
	private Boolean comissaoVendedorReais;

	@Column(name = "valor_comissao_vendedor")
	private Double valorComissaoVendedor;

	@Column(name = "valor_servico")
	private Double valorServico;

	@Column(name = "valor_minimo_servico")
	private Double valorMinimoServico;

	@Column(name = "garantia_dia")
	private Integer garantiaDia;

	@Column(name = "retorno")
	private Integer retorno;

	@Column(name = "hora_gasta")
	private Double horaGasta;

	@Column(name = "ativa")
	private Boolean ativa;

	@Column(name = "valor_promocional")
	private Double valorPromocional;

	@Column(name = "vencimento_promocao")
	private Date vencimentoPromocao;
	
	@Field
	@Caption("Observacao")
	@Lob
	@Column(name = "OBSERVACAO")
	@Type(type = "text")
	private String observacao;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_cadastro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataCadastro;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public SubGrupo getSubGrupo() {
		return subGrupo;
	}

	public void setSubGrupo(SubGrupo subGrupo) {
		this.subGrupo = subGrupo;
	}

	public Double getAliquotaIssqn() {
		return aliquotaIssqn;
	}

	public void setAliquotaIssqn(Double aliquotaIssqn) {
		this.aliquotaIssqn = aliquotaIssqn;
	}

	public Double getValorComissaoTecnico() {
		return valorComissaoTecnico;
	}

	public void setValorComissaoTecnico(Double valorComissaoTecnico) {
		this.valorComissaoTecnico = valorComissaoTecnico;
	}

	public Double getValorComissaoVendedor() {
		return valorComissaoVendedor;
	}

	public void setValorComissaoVendedor(Double valorComissaoVendedor) {
		this.valorComissaoVendedor = valorComissaoVendedor;
	}

	public Boolean getComissaoTecnicoReais() {
		return comissaoTecnicoReais;
	}

	public void setComissaoTecnicoReais(Boolean comissaoTecnicoReais) {
		this.comissaoTecnicoReais = comissaoTecnicoReais;
	}

	public Boolean getComissaoVendedorReais() {
		return comissaoVendedorReais;
	}

	public void setComissaoVendedorReais(Boolean comissaoVendedorReais) {
		this.comissaoVendedorReais = comissaoVendedorReais;
	}

	public Double getValorServico() {
		return valorServico;
	}

	public void setValorServico(Double valorServico) {
		this.valorServico = valorServico;
	}

	public Double getValorMinimoServico() {
		return valorMinimoServico;
	}

	public void setValorMinimoServico(Double valorMinimoServico) {
		this.valorMinimoServico = valorMinimoServico;
	}

	public Integer getGarantiaDia() {
		return garantiaDia;
	}

	public void setGarantiaDia(Integer garantiaDia) {
		this.garantiaDia = garantiaDia;
	}

	public Integer getRetorno() {
		return retorno;
	}

	public void setRetorno(Integer retorno) {
		this.retorno = retorno;
	}

	public Double getHoraGasta() {
		return horaGasta;
	}

	public void setHoraGasta(Double horaGasta) {
		this.horaGasta = horaGasta;
	}

	public Boolean getAtiva() {
		return ativa;
	}

	public void setAtiva(Boolean ativa) {
		this.ativa = ativa;
	}

	public Double getValorPromocional() {
		return valorPromocional;
	}

	public void setValorPromocional(Double valorPromocional) {
		this.valorPromocional = valorPromocional;
	}

	public Date getVencimentoPromocao() {
		return vencimentoPromocao;
	}

	public void setVencimentoPromocao(Date vencimentoPromocao) {
		this.vencimentoPromocao = vencimentoPromocao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
