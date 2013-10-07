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
@Table(name = "socio_dependente")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class Dependente extends AbstractModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dpn")
	@SequenceGenerator(name = "dpn", sequenceName = "socio_dependente_id_seq", allocationSize = 1)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="id_tipo_relacionamento")
	TipoRelacionamento tipoRelacionamento;
	
	@Caption("nome")
	String nome;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dataNascimento")
	Date dataNascimento;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_inicio_depedencia")
	Date dataInicioDependencia;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_fim_dependencia")
	Date dataFimDependencia;
	
	String cpf;
	
	@ManyToOne
	@JoinColumn(name="id_socio")
	Socio socio;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoRelacionamento getTipoRelacionamento() {
		return tipoRelacionamento;
	}

	public void setTipoRelacionamento(TipoRelacionamento tipoRelacionamento) {
		this.tipoRelacionamento = tipoRelacionamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataInicioDependencia() {
		return dataInicioDependencia;
	}

	public void setDataInicioDependencia(Date dataInicioDependencia) {
		this.dataInicioDependencia = dataInicioDependencia;
	}

	public Date getDataFimDependencia() {
		return dataFimDependencia;
	}

	public void setDataFimDependencia(Date dataFimDependencia) {
		this.dataFimDependencia = dataFimDependencia;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}
	
	
	
	
	
}
