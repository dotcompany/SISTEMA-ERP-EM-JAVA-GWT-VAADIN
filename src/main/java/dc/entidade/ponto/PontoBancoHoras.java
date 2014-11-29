package dc.entidade.ponto;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.geral.pessoal.ColaboradorEntity;


@Entity
@Table(name = "PONTO_BANCO_HORAS")
public class PontoBancoHoras extends AbstractMultiEmpresaModel<Integer>  {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_TRABALHO")
    @Caption(value = "Data Trabalho")
    private Date dataTrabalho;
    @Column(name = "QUANTIDADE")
    @Caption(value = "Quantidade")
    private String quantidade;
    @Column(name = "SITUACAO")
    @Caption(value = "Situação")
    private String situacao;
    @JoinColumn(name = "ID_COLABORADOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    @Caption(value = "Colaborador")
    private ColaboradorEntity colaborador;

    public PontoBancoHoras() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataTrabalho() {
        return dataTrabalho;
    }

    public void setDataTrabalho(Date dataTrabalho) {
        this.dataTrabalho = dataTrabalho;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public ColaboradorEntity getColaborador() {
        return colaborador;
    }

    public void setColaborador(ColaboradorEntity colaborador) {
        this.colaborador = colaborador;
    }


    @Override
    public String toString() {
        return "com.t2tierp.ponto.java.PontoBancoHoras[id=" + id + "]";
    }

}
