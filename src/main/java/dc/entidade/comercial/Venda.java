package dc.entidade.comercial;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.suprimentos.ContagemEstoqueDetalhe;

@Entity
@Table(name = "venda_cabecalho")
@SuppressWarnings("serial")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class Venda extends AbstractMultiEmpresaModel<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vnd")
	@SequenceGenerator(name = "vnd", sequenceName = "venda_cabecalho_id_seq", allocationSize = 1)
	private Integer id;
	
	@OneToMany(mappedBy = "venda", orphanRemoval = true,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@Caption("Detalhe")
	private List<VendaDetalhe> detalhes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<VendaDetalhe> getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(List<VendaDetalhe> detalhes) {
		this.detalhes = detalhes;
	}
	
	public VendaDetalhe adicionarDetalhe(VendaDetalhe detalhe) {
		if(detalhes==null) detalhes = new ArrayList();
		getDetalhes().add(detalhe);
		detalhe.setVenda(this);
		return detalhe;
	}
		
}
