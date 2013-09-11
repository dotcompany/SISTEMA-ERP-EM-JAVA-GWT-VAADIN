package dc.entidade.geral;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.pessoal.EstadoCivil;

/**
*
* @author Wesley Jr
/*
*Classe que possui o TO, ou seja, o mapeamento com todos os campos que vamos ter 
*no nosso Banco de Dados 
** Nessa classe temos o equals, hashCode e o ToString, no nosso novo mapeamento, pegamos
* e mudamos, está diferente do mapeamento do T2Ti.
* *
* Colocamos também algumas anotações, na classe e em alguns campos, onde temos as anotações
* que é o Field e Caption, o Caption colocamos o nome do campo que queremos que pesquise
* na Tela, pegando os dados que estão salvos no Banco de Dados.
*/

@Entity
@Table(name = "pessoa_fisica")
@XmlRootElement
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class PessoaFisica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    
    @Field
    @Caption("CPF")
    @Column(name = "CPF")
    private String cpf;
    
    @Field
    @Caption("RG")
    @Column(name = "RG")
    private String rg;
    
    @Column(name = "ORGAO_RG")
    private String orgaoRg;
    
    @Column(name = "DATA_EMISSAO_RG")
    @Temporal(TemporalType.DATE)
    private Date dataEmissaoRg;
    
    @Column(name = "DATA_NASCIMENTO")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    
    @Column(name = "SEXO")
    private String sexo;
    
    @Column(name="NATURALIDADE", length = 100)
    private String naturalidade;
    
    @Column(name="NACIONALIDADE", length = 100)
    private String nacionalidade;
    
    @Column(name="RACA")
    private Character raca;
    
    @Column(name="TIPO_SANGUE")
    private Character tipoSangue;
    
    @Column(name="CNH_NUMERO", length = 20)
    private String cnhNumero;
    
    @Column(name="CNH_CATEGORIA")
    private Character cnhCategoria;
    
    @Column(name = "CNH_VENCIMENTO")
    @Temporal(TemporalType.DATE)
    private Date cnhVencimento;
    
    @Column(name="TITULO_ELEITORAL_NUMERO", length = 20)
    private String tituloEleitoralNumero;
    
    @Column(name="TITULO_ELEITORAL_ZONA")
    private Integer tituloEleitoralZona;
    
    @Column(name="TITULO_ELEITORAL_SECAO")
    private Integer tituloEleitoralSecao;
    
    @Column(name="RESERVISTA_NUMERO", length = 20)
    private String reservistaNumero;
    
    @Column(name="RESERVISTA_CATEGORIA")
    private Integer reservistaCategoria;
    
    @Column(name="NOME_MAE", length = 100)
    private String nomeMae;
    
    @Column(name="NOME_PAI", length = 100)
    private String nomePai;
    
    @JoinColumn(name = "ID_ESTADO_CIVIL", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EstadoCivil estadoCivil;
    
    @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID")
    @OneToOne(optional = false)
    private Pessoa pessoa;

    public PessoaFisica() {
    }

    public PessoaFisica(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getOrgaoRg() {
        return orgaoRg;
    }

    public void setOrgaoRg(String orgaoRg) {
        this.orgaoRg = orgaoRg;
    }

    public Date getDataEmissaoRg() {
        return dataEmissaoRg;
    }

    public void setDataEmissaoRg(Date dataEmissaoRg) {
        this.dataEmissaoRg = dataEmissaoRg;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[] {"id"});
    }

    @Override
    public boolean equals(Object object) {
    	if (object instanceof PessoaFisica == false) return false;
    	if (this == object) return true;
    	final PessoaFisica other = (PessoaFisica) object;
    	return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
    
    public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public Character getRaca() {
		return raca;
	}

	public void setRaca(Character raca) {
		this.raca = raca;
	}

	public Character getTipoSangue() {
		return tipoSangue;
	}

	public void setTipoSangue(Character tipoSangue) {
		this.tipoSangue = tipoSangue;
	}

	public String getCnhNumero() {
		return cnhNumero;
	}

	public void setCnhNumero(String cnhNumero) {
		this.cnhNumero = cnhNumero;
	}

	public Character getCnhCategoria() {
		return cnhCategoria;
	}

	public void setCnhCategoria(Character cnhCategoria) {
		this.cnhCategoria = cnhCategoria;
	}

	public Date getCnhVencimento() {
		return cnhVencimento;
	}

	public void setCnhVencimento(Date cnhVencimento) {
		this.cnhVencimento = cnhVencimento;
	}

	public String getTituloEleitoralNumero() {
		return tituloEleitoralNumero;
	}

	public void setTituloEleitoralNumero(String tituloEleitoralNumero) {
		this.tituloEleitoralNumero = tituloEleitoralNumero;
	}

	public Integer getTituloEleitoralZona() {
		return tituloEleitoralZona;
	}

	public void setTituloEleitoralZona(Integer tituloEleitoralZona) {
		this.tituloEleitoralZona = tituloEleitoralZona;
	}

	public Integer getTituloEleitoralSecao() {
		return tituloEleitoralSecao;
	}

	public void setTituloEleitoralSecao(Integer tituloEleitoralSecao) {
		this.tituloEleitoralSecao = tituloEleitoralSecao;
	}

	public String getReservistaNumero() {
		return reservistaNumero;
	}

	public void setReservistaNumero(String reservistaNumero) {
		this.reservistaNumero = reservistaNumero;
	}

	public Integer getReservistaCategoria() {
		return reservistaCategoria;
	}

	public void setReservistaCategoria(Integer reservistaCategoria) {
		this.reservistaCategoria = reservistaCategoria;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	/**
     * @return the estadoCivil
     */
    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    /**
     * @param estadoCivil the estadoCivil to set
     */
    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    /**
     * @return the pessoa
     */
    public Pessoa getPessoa() {
        return pessoa;
    }

    /**
     * @param pessoa the pessoa to set
     */
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}

