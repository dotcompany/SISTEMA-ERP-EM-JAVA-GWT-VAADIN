package dc.entidade.empresa;


import java.math.BigDecimal;
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
import javax.persistence.Transient;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractModel;
import dc.entidade.geral.pessoal.TipoRelacionamentoEntity;
import dc.visao.empresa.SocioFormView.DIRIGENTE;


@Entity
@Table(name = "socio_participacao_societaria")
@SuppressWarnings("serial")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class ParticipacaoSocietariaEntity extends AbstractModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pps")
	@SequenceGenerator(name = "pps", sequenceName = "socio_participacao_societaria_id_seq", allocationSize = 1)
	private Integer id;
		
	@Caption("nome")
	String cnpj;
	
	@Column(name="razao_social")
	String razaoSocial;
	
	@Column(name="data_entrada")
	@Temporal(TemporalType.DATE)
	Date dataEntrada;
	
	@Column(name="data_saida")
	@Temporal(TemporalType.DATE)
	Date dataSaida;
			
	@ManyToOne
	@JoinColumn(name="id_socio")
	SocioEntity socio;
	
	BigDecimal participacao;

	String dirigente;//0-Não 1-Sim
	
	@Transient
	DIRIGENTE dirigenteEnum;
	
	@Transient
	String dirigenteStr;
	
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

	public SocioEntity getSocio() {
		return socio;
	}

	public void setSocio(SocioEntity socio) {
	    	this.socio = socio;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public BigDecimal getParticipacao() {
		return participacao;
	}

	public void setParticipacao(BigDecimal participacao) {
		this.participacao = participacao;
	}

	public String getDirigente() {
		return dirigente;
	}

	public void setDirigente(String dirigente) {
		this.dirigente = dirigente;
	}

	public String getDirigenteStr() {
		return dirigenteStr;
	}

	public void setDirigenteStr(String dirigenteStr) {
		this.dirigenteStr = dirigenteStr;
	}

	public DIRIGENTE getDirigenteEnum() {
		return dirigenteEnum;
	}

	public void setDirigenteEnum(DIRIGENTE dirigenteEnum) {
		this.dirigenteEnum = dirigenteEnum;
	}

	
	
	
	
	
	
}
