
package dc.entidade.tributario;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;
import org.springframework.beans.factory.annotation.Autowired;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.Empresa;
import dc.servicos.dao.tributario.GrupoTributarioDAO;



@Entity
@Table(name = "tribut_configura_of_gt")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class ConfiguracaoTributaria extends AbstractModel<Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cfg")
	@SequenceGenerator(name = "cfg", sequenceName = "tribut_configura_of_gt_id_seq", allocationSize = 1)
	private Integer id;
       
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Empresa empresa;
    
    @JoinColumn(name = "id_tribut_grupo_tributario", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    @Caption("Grupo Tributário")
    private GrupoTributario grupoTributario;
    
    @JoinColumn(name = "id_tribut_operacao_fiscal", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    @Caption("Operação Fiscal")
    private OperacaoFiscal operacaoFiscal;
    
    
    
    public ConfiguracaoTributaria() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

	public GrupoTributario getGrupoTributario() {
		return grupoTributario;
	}

	public void setGrupoTributario(GrupoTributario grupoTributario) {
		this.grupoTributario = grupoTributario;
	}

	public OperacaoFiscal getOperacaoFiscal() {
		return operacaoFiscal;
	}

	public void setOperacaoFiscal(OperacaoFiscal operacaoFiscal) {
		this.operacaoFiscal = operacaoFiscal;
	}

	
   
}
