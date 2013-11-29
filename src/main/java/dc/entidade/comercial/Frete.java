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
import dc.entidade.pessoal.Transportadora;

@Entity
@Table(name = "venda_frete")
@SuppressWarnings("serial")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class Frete extends AbstractMultiEmpresaModel<Integer> {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "frt")
	@SequenceGenerator(name = "frt", sequenceName = "venda_frete_id_seq", allocationSize = 1)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="id_transportadora")
	Transportadora transportadora;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Transportadora getTransportadora() {
		return transportadora;
	}

	public void setTransportadora(Transportadora transportadora) {
		this.transportadora = transportadora;
	}
	
	
	
}
