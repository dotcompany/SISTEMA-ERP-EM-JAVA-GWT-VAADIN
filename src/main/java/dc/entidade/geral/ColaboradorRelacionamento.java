package dc.entidade.geral;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

/**
*
* @author Wesley Jr
/*
*Classe que possui o TO, ou seja, o mapeamento com todos os campos que vamos ter 
*no nosso Banco de Dados 
** Nessa classe temos o equals, hashCode e o ToString, no nosso novo mapeamento, pegamos
* e mudamos, est� diferente do mapeamento do T2Ti.
* *
* Colocamos também algumas anotações, na classe e em alguns campos, onde temos as anotações
* que é o Field e Caption, o Caption colocamos o nome do campo que queremos que pesquise
* na Tela, pegando os dados que estão salvos no Banco de Dados.
*/


@Entity
@Table(name = "colaborador_relacionamento")
@XmlRootElement
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class ColaboradorRelacionamento implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    
    @Field
    @Caption("Nome")
    @Column(name = "NOME", length = 100)
    private String nome;
    
    @Column(name = "DATA_NASCIMENTO")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    
    @Column(name = "CPF", length = 11)
    private String cpf;
    
    @Column(name = "REGISTRO_MATRICULA", length = 50)
    private String registroMatricula;
    
    @Column(name = "REGISTRO_CARTORIO", length = 50)
    private String registroCartorio;
    
    @Column(name = "REGISTRO_CARTORIO_NUMERO", length = 50)
    private String registroCartorioNumeor;
    
    @Column(name = "REGISTRO_NUMERO_LIVRO", length = 10)
    private String registroNumeroLivro;
    
    @Column(name = "REGISTRO_NUMERO_FOLHA", length = 10)
    private String registroNumeroFolha;
    
    @Column(name = "DATA_ENTREGA_DOCUMENTO")
    @Temporal(TemporalType.DATE)
    private Date dataEntregaDocumento;
    
    @Column(name="SALARIO_FAMILIA")
    private Character salarioFamilia;
    
    @Column(name="SALARIO_FAMILIA_IDADE_LIMITE")
    private Integer salarioFamiliaIdadeLimite;
    
    @Column(name = "SALARIO_FAMILIA_DATA_FIM")
    @Temporal(TemporalType.DATE)
    private Date salarioFamiliaDataFim;
    
    @Column(name="IMPOSTO_RENDA_DATA_FIM")
    private Integer impostoRendaDataFim;

    public ColaboradorRelacionamento() {
    }

    public ColaboradorRelacionamento(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[] {"id"});
    }

    @Override
    public boolean equals(Object object) {
    	if (object instanceof ColaboradorRelacionamento == false) return false;
    	if (this == object) return true;
    	final ColaboradorRelacionamento other = (ColaboradorRelacionamento) object;
    	return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRegistroMatricula() {
		return registroMatricula;
	}

	public void setRegistroMatricula(String registroMatricula) {
		this.registroMatricula = registroMatricula;
	}

	public String getRegistroCartorio() {
		return registroCartorio;
	}

	public void setRegistroCartorio(String registroCartorio) {
		this.registroCartorio = registroCartorio;
	}

	public String getRegistroCartorioNumeor() {
		return registroCartorioNumeor;
	}

	public void setRegistroCartorioNumeor(String registroCartorioNumeor) {
		this.registroCartorioNumeor = registroCartorioNumeor;
	}

	public String getRegistroNumeroLivro() {
		return registroNumeroLivro;
	}

	public void setRegistroNumeroLivro(String registroNumeroLivro) {
		this.registroNumeroLivro = registroNumeroLivro;
	}

	public String getRegistroNumeroFolha() {
		return registroNumeroFolha;
	}

	public void setRegistroNumeroFolha(String registroNumeroFolha) {
		this.registroNumeroFolha = registroNumeroFolha;
	}

	public Date getDataEntregaDocumento() {
		return dataEntregaDocumento;
	}

	public void setDataEntregaDocumento(Date dataEntregaDocumento) {
		this.dataEntregaDocumento = dataEntregaDocumento;
	}

	public Character getSalarioFamilia() {
		return salarioFamilia;
	}

	public void setSalarioFamilia(Character salarioFamilia) {
		this.salarioFamilia = salarioFamilia;
	}

	public Integer getSalarioFamiliaIdadeLimite() {
		return salarioFamiliaIdadeLimite;
	}

	public void setSalarioFamiliaIdadeLimite(Integer salarioFamiliaIdadeLimite) {
		this.salarioFamiliaIdadeLimite = salarioFamiliaIdadeLimite;
	}

	public Date getSalarioFamiliaDataFim() {
		return salarioFamiliaDataFim;
	}

	public void setSalarioFamiliaDataFim(Date salarioFamiliaDataFim) {
		this.salarioFamiliaDataFim = salarioFamiliaDataFim;
	}

	public Integer getImpostoRendaDataFim() {
		return impostoRendaDataFim;
	}

	public void setImpostoRendaDataFim(Integer impostoRendaDataFim) {
		this.impostoRendaDataFim = impostoRendaDataFim;
	}

}

