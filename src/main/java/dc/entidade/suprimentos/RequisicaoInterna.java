package dc.entidade.suprimentos;

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

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.pessoal.Colaborador;

@Entity
@Table(name = "requisicao_interna_cabecalho")
@SuppressWarnings("serial")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class RequisicaoInterna extends AbstractMultiEmpresaModel<Integer> {

//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Caption("Id")
//	private Integer id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "req")
	@SequenceGenerator(name = "req", sequenceName = "requisicao_interna_cabecalho_id_seq", allocationSize = 1)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_requisicao")
	@Caption("Data de requisição")
	private Date dataRequisicao;
	
	@Caption("Colaborador")
	@ManyToOne
	@JoinColumn(name="id_colaborador")
	private Colaborador colaborador; 

	@OneToMany(mappedBy = "requisicao", cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.JOIN)
	private List<RequisicaoInternaDetalhe> detalhes = new ArrayList<RequisicaoInternaDetalhe>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataRequisicao() {
		return dataRequisicao;
	}

	public void setDataRequisicao(Date dataRequisicao) {
		this.dataRequisicao = dataRequisicao;
	}

	public List<RequisicaoInternaDetalhe> getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(List<RequisicaoInternaDetalhe> detalhes) {
		this.detalhes = detalhes;
	}

	public RequisicaoInternaDetalhe addRequisicaoDetalhe(RequisicaoInternaDetalhe requisicaoDetalhe){
		getDetalhes().add(requisicaoDetalhe);
		requisicaoDetalhe.setRequisicao(this);
		return requisicaoDetalhe;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

}
