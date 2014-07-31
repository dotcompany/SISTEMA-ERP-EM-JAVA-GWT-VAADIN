package dc.entidade.relatorio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.FmMenu;

@Entity
@Table(name = "relatorio")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class Relatorio extends AbstractModel<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5030551518611792445L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Column(name = "jasper_path")
	@Caption(value = "Caminho Relatório")
	private String jasperPath;
	@Column()
	@Caption(value = "Nome")
	private String nome;
	@Column()
	@Caption(value = "Descrição")
	private String descricao;

	@Column(name = "tela_parametros")
	@Caption(value = "Classe Tela de Parâmetros")
	private String telaParametros;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = FmMenu.class)
	@JoinColumn(name = "id_fm_menu")
	private FmMenu menu;

	@Column()
	private Integer tipo;

	@OneToMany(mappedBy = "relatorio", orphanRemoval = true)
	private List<RelatorioPapel> relatorioPapeis;

	@Override
	public Integer getId() {
		return id;
	}

	public String getJasperPath() {
		return jasperPath;
	}

	public void setJasperPath(String jasperPath) {
		this.jasperPath = jasperPath;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public FmMenu getMenu() {
		return menu;
	}

	public void setMenu(FmMenu menu) {
		this.menu = menu;
	}

	public List<RelatorioPapel> getRelatorioPapeis() {
		return relatorioPapeis;
	}

	public void setRelatorioPapeis(List<RelatorioPapel> relatorioPapeis) {
		this.relatorioPapeis = relatorioPapeis;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getTelaParametros() {
		return telaParametros;
	}

	public void setTelaParametros(String telaParametros) {
		this.telaParametros = telaParametros;
	}

}
