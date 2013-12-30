package dc.entidade.framework;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import com.sun.istack.logging.Logger;
import com.vaadin.ui.MenuBar.Command;

import dc.anotacoes.Caption;

@Entity
@Table(name = "fm_menu")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class FmMenu extends AbstractModel<Integer> implements Serializable {

	private static final long serialVersionUID = 3022660314863012474L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;

	@Field
	@Caption("URL (identificador)")
	@Column(name = "URL_ID")
	private String urlId;

	@Field()
	@Caption("Caption")
	@Column(name = "CAPTION")
	private String caption;

	@Field()
	@Caption("Classe do controller")
	@Column(name = "controller", nullable = false)
	private String controllerClass;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = FmModulo.class)
	private FmModulo fmModulo;

	@Transient
	private Integer parentId;

	@ManyToOne(fetch = FetchType.EAGER)
	private FmMenu parent;

	@Field()
	@Caption("Permissão de operação")
	@Column(name = "sn_permissao_operacao")
	private Integer permissaoOperacao = new Integer(0);

	/*
	 * @Field()
	 * 
	 * @Caption("Consulta por multiempresa")
	 * 
	 * @Column(name = "sn_consulta_multiempresa") private Integer
	 * consultaMultiempresa = new Integer(0);
	 */

	@OneToMany(mappedBy = "parent", orphanRemoval = true)
	private List<FmMenu> menusFilho;

	@OneToMany(mappedBy = "menu", orphanRemoval = true)
	private List<PapelMenu> papeisMenu;

	private static Logger logger = Logger.getLogger(FmMenu.class);

	public FmMenu() {

	}

	public FmMenu(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrlId() {
		return urlId;
	}

	public void setUrlId(String urlID) {
		this.urlId = urlID;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public FmModulo getFmModulo() {
		return fmModulo;
	}

	public void setFmModulo(FmModulo m) {
		this.fmModulo = m;
	}

	public void setParent(FmMenu m) {
		this.parent = m;
	}

	public FmMenu getParent() {
		return this.parent;
	}

	public Integer getPermissaoOperacao() {
		return permissaoOperacao;
	}

	public void setPermissaoOperacao(Integer permissaoOperacao) {
		this.permissaoOperacao = (permissaoOperacao == null ? new Integer(0) : permissaoOperacao);
	}

	/*
	 * public Integer getConsultaMultiempresa() { return consultaMultiempresa; }
	 * 
	 * public void setConsultaMultiempresa(Integer consultaMultiempresa) {
	 * this.consultaMultiempresa = (consultaMultiempresa == null ? new Integer(
	 * 0) : consultaMultiempresa); }
	 */

	public String getControllerClass() {
		return controllerClass;
	}

	public void setControllerClass(String controllerClass) {
		this.controllerClass = controllerClass;
	}

	public Integer getParentId() {
		if (parent != null) {
			return parent.id;
		} else {
			return parentId;
		}
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Command getCommand() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PapelMenu> getPapeisMenu() {
		return papeisMenu;
	}

	public void setPapeisMenu(List<PapelMenu> papeisMenu) {
		this.papeisMenu = papeisMenu;
	}

	public List<FmMenu> getMenusFilho() {
		return menusFilho;
	}

	public void setMenusFilho(List<FmMenu> menusFilho) {
		this.menusFilho = menusFilho;
	}

}