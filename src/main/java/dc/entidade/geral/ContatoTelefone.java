package dc.entidade.geral;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "contato_telefone")
public class ContatoTelefone implements Serializable {
	
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "TIPO_TELEFONE_ID", nullable = false)
    private int tipoTelefoneId;
    
    @Basic(optional = false)
    @Column(name = "CONTATO_ID", nullable = false)
    private int contatoId;
    
    @Column(name = "TELEFONE", length = 10)
    private String telefone;

    public ContatoTelefone() {
    }

    public ContatoTelefone(Integer id) {
        this.id = id;
    }

    public ContatoTelefone(Integer id, int tipoTelefoneId, int contatoId) {
        this.id = id;
        this.tipoTelefoneId = tipoTelefoneId;
        this.contatoId = contatoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipoTelefoneId() {
        return tipoTelefoneId;
    }

    public void setTipoTelefoneId(int tipoTelefoneId) {
        this.tipoTelefoneId = tipoTelefoneId;
    }

    public int getContatoId() {
        return contatoId;
    }

    public void setContatoId(int contatoId) {
        this.contatoId = contatoId;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContatoTelefone)) {
            return false;
        }
        ContatoTelefone other = (ContatoTelefone) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.t2tierp.cadastros.java.ContatoTelefone[id=" + id + "]";
    }

}

