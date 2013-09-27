package dc.entidade.empresa;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractModel;
import dc.entidade.pessoal.TipoRelacionamento;


@Entity
@Table(name = "socio_participacao_societaria")
@SuppressWarnings("serial")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class ParticipacaoSocietaria extends AbstractModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pps")
	@SequenceGenerator(name = "pps", sequenceName = "socio_participacao_societaria_id_seq", allocationSize = 1)
	private Integer id;
	
	
	
	@Caption("nome")
	String cnpj;
		
	
	@ManyToOne
	@JoinColumn(name="id_socio")
	Socio socio;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
	    	this.socio = socio;
	}

	
	
	
	
	
	
}
