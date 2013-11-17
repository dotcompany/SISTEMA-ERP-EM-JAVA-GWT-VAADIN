package dc.entidade.comercial;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.entidade.framework.AbstractMultiEmpresaModel;

@Entity
@Table(name = "venda_detalhe")
@SuppressWarnings("serial")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class VendaDetalhe extends AbstractMultiEmpresaModel<Integer> {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vnd")
	@SequenceGenerator(name = "vnd", sequenceName = "venda_detalhe_id_seq", allocationSize = 1)
	private Integer id;
	
	Integer quantidade;
	
	@ManyToOne
	@JoinColumn(name="venda_cabecalho_id")
	Venda venda;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	
	
	
}
