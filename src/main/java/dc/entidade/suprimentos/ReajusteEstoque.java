package dc.entidade.suprimentos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractModel;
import dc.entidade.pessoal.Colaborador;


@Entity
@Table(name = "estoque_reajuste_cabecalho")
@SuppressWarnings("serial")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class ReajusteEstoque 
extends AbstractModel<Integer> implements Serializable{

//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Caption("Id")
//	private Integer id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rje")
	@SequenceGenerator(name = "rje", sequenceName = "estoque_reajuste_cabecalho_id_seq", allocationSize = 1)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_reajuste")
	@Caption("Data do Reajuste")
	private Date dataReajuste;

	@Caption("Porcentagem")
	private BigDecimal porcentagem;

	@Caption("Tipo de Reajuste")
	@Column(name="tipo_reajuste")
	private String tipo;
	
	@Caption("Tipo de Reajuste")
	@Transient
	private String tipoString;

	@Caption("Colaborador")
	@ManyToOne
	@JoinColumn(name="id_colaborador")
	private Colaborador colaborador; 

	@OneToMany(mappedBy = "reajuste", cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.JOIN)
	private List<ReajusteEstoqueDetalhe> detalhes = new ArrayList<ReajusteEstoqueDetalhe>();

	@Transient
	static Integer AUMENTAR = 1;

	@Transient
	static 	Integer DIMINUIR = 2;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataReajuste() {
		return dataReajuste;
	}

	public void setDataReajuste(Date dataReajuste) {
		this.dataReajuste = dataReajuste;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	

	public BigDecimal getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(BigDecimal porcentagem) {
		this.porcentagem = porcentagem;
	}

	public List<ReajusteEstoqueDetalhe> getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(List<ReajusteEstoqueDetalhe> detalhes) {
		this.detalhes = detalhes;
	}

	public ReajusteEstoqueDetalhe addDetalhe(ReajusteEstoqueDetalhe detalhe){
		getDetalhes().add(detalhe);
		detalhe.setReajuste(this);
		return detalhe;
	}


	public static BigDecimal valorAumentado(BigDecimal valorOriginal,BigDecimal porcentagem,Integer tipo){
		BigDecimal valorFinal = new BigDecimal(0);
		BigDecimal valorPorcentagem = porcentagem.multiply(valorOriginal);
		if(tipo.equals(AUMENTAR)){
			valorFinal = valorOriginal.add(valorPorcentagem);	 
		}
		
		if(tipo.equals( DIMINUIR)){
			valorFinal = valorOriginal.subtract(valorPorcentagem);	 
		}

		return valorFinal;
	}

	public String getTipoString() {
		return tipo.equals("1")?"Aumentar" : "Diminuir" ;
	}

	public void setTipoString(String tipoString) {
		this.tipoString = tipoString;
	}
	
	



}
