package dc.entidade.geral.eventos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.control.enums.TipoSemestre;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

@Entity
@Table(name = "contrato_eventos")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
@SuppressWarnings("serial")
public class ContratoEventosEntity extends AbstractMultiEmpresaModel<Integer> implements Serializable {
	
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contrato_eventos_id_seq")
	@SequenceGenerator(name = "contrato_eventos_id_seq", sequenceName = "contrato_eventos_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;
	
	@Field
	@Caption("Curso")
	@Column(name = "CURSO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Curso é Obrigatório!")
	private String curso = "";
	
	@Field
	@Caption("Unidade")
	@Column(name = "UNIDADE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Unidade é Obrigatório!")
	private String unidade = "";
	
	@Temporal(TemporalType.DATE)
	@Field
	@Caption("Data Contrato")
	@Column(name = "DATA_CONTRATO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataContrato;
	
	@Temporal(TemporalType.DATE)
	@Field
	@Caption("Data Primeiro Evento")
	@Column(name = "DATA_PRIMEIRO_EVENTO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataPrimeiroEvento;
	
	@Field
	@Caption("Quantidade Formandos")
	@Column(name = "QUANTIDADE_FORMANDOS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Quantidade de Formandos é Obrigatório!")
	private Integer quantidadeFormandos;
	
	@Field
	@Caption("Ano Formatura")
	@Column(name = "ANO_FORMATURA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Ano de Formatura é Obrigatório!")
	private String anoFormatura = "";
	
	
	@Caption("Nome Cerimonial")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_nome_cerimonial", nullable = false)
	private CerimonialEventosEntity nomeCerimonial;
	
	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Tipo Semestre")
	@Column(name = "TIPO_SEMESTRE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private TipoSemestre tipoSemestre;
	
	
	public ContratoEventosEntity() {
    }

    public ContratoEventosEntity(Integer id) {
	   this.id = id;
    }

    @Override
    public Integer getId() {
	   return id;
    }

    public void setId(Integer id) {
	   this.id = id;
    }
    
    public String getCurso() {
		return curso;
	}
	
	public void setCurso(String curso) {
		this.curso = (curso == null ? "".trim() : curso.toUpperCase().trim());
	}
	
	public String getUnidade() {
		return unidade;
	}
	
	public void setUnidade(String unidade) {
		this.unidade = (unidade == null ? "".trim() : unidade.toUpperCase().trim());
	}
	
	public Date getDataContrato() {
		return dataContrato;
	}

	public void setDataContrato(Date dataContrato) {
		this.dataContrato = dataContrato;
	}
	
	public Date getDataPrimeiroEvento() {
		return dataPrimeiroEvento;
	}
	
	public void setDataPrimeiroEvento(Date dataPrimeiroEvento) {
		this.dataPrimeiroEvento = dataPrimeiroEvento;
	}
	
	public Integer getQuantidadeFormandos() {
		return quantidadeFormandos;
	}

	public void setQuantidadeFormandos(Integer quantidadeFormandos) {
		this.quantidadeFormandos = quantidadeFormandos;
	}

	public String getAnoFormatura() {
		return anoFormatura;
	}

	public void setAnoFormatura(String anoFormatura) {
		this.anoFormatura = anoFormatura;
	}

	public TipoSemestre getTipoSemestre() {
		return tipoSemestre;
	}

	public void setTipoSemestre(TipoSemestre tipoSemestre) {
		this.tipoSemestre = tipoSemestre;
	}

	/*public CerimonialEventosEntity getNomeCerimonial() {
		return nomeCerimonial;
	}

	public void setNomeCerimonial(CerimonialEventosEntity nomeCerimonial) {
		this.nomeCerimonial = nomeCerimonial;
	}*/
	
	@Override
	public String toString() {
		return curso;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ContratoEventosEntity)) {
            return false;
        }

        ContratoEventosEntity that = (ContratoEventosEntity) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(getId(), that.getId());
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        if (getId() == null) {
            return super.hashCode();
        } else {
            return new HashCodeBuilder()
                    .append(id)
                    .toHashCode();
        }
    }
	
}
