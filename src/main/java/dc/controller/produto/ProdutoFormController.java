package dc.controller.produto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.controller.diversos.AlmoxarifadoListController;
import dc.controller.tributario.GrupoTributarioListController;
import dc.entidade.diversos.Almoxarifado;
import dc.entidade.produto.MarcaProduto;
import dc.entidade.produto.Produto;
import dc.entidade.produto.SubGrupoProduto;
import dc.entidade.produto.UnidadeProduto;
import dc.entidade.tributario.GrupoTributario;
import dc.servicos.dao.diversos.AlmoxarifadoDAO;
import dc.servicos.dao.produto.MarcaProdutoDAO;
import dc.servicos.dao.produto.ProdutoDAO;
import dc.servicos.dao.produto.SubGrupoProdutoDAO;
import dc.servicos.dao.produto.UnidadeProdutoDAO;
import dc.servicos.dao.tributario.GrupoTributarioDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.produto.ProdutoFormView;

@Controller
@Scope("prototype")
public class ProdutoFormController extends CRUDFormController<Produto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProdutoFormView subView;

	@Autowired
	private ProdutoDAO produtoDAO;

	/*
	 * @Autowired private SubGrupoProdutoDAO subGrupoProdutoDAO;
	 */

	@Autowired
	private UnidadeProdutoDAO unidadeProdutoDAO;

	@Autowired
	private MarcaProdutoDAO marcaProdutoDAO;

	@Autowired
	private AlmoxarifadoDAO almoxarifadoDAO;

	@Autowired
	private GrupoTributarioDAO grupoTributarioDAO;

	@Autowired
	private SubGrupoProdutoDAO subGrupoProdutoDAO;

	private Produto currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtDescricao(),
					"Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new Produto();
	}

	@Override
	protected void initSubView() {
		subView = new ProdutoFormView();
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = produtoDAO.find(id);

		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtDescricao().setValue(currentBean.getDescricao());

		/* Configura combo Sub Grupo Produto */
		/*
		 * ManyToOneComboModel<SubGrupoProduto> modelsub = new
		 * ManyToOneComboModel<SubGrupoProduto>() {
		 * 
		 * @Override public void onCriarNovo(String filter) {
		 * Notification.show("Selecionado Criar Novo: " + filter); }
		 * 
		 * @Override public List<SubGrupoProduto> getResultado(String q) {
		 * return subGrupoProdutoDAO.query(q); }
		 * 
		 * @Override public Class<SubGrupoProduto> getEntityClass() { return
		 * SubGrupoProduto.class; }
		 * 
		 * @Override public String getCaptionProperty() { return "descricao"; }
		 * 
		 * @Override public void onEditar(SubGrupoProduto value) {
		 * Notification.show("Selecionado Editar: " + value.getDescricao());
		 * 
		 * } }; subView.getCmbSubGrupoProduto().setModel(modelsub);
		 * subView.getCmbSubGrupoProduto
		 * ().setValue(currentBean.getIdSubGrupo());
		 */

		/* Configura combo Unidade Produto */
		/*
		 * ManyToOneComboModel<UnidadeProduto> model = new
		 * ManyToOneComboModel<UnidadeProduto>() {
		 * 
		 * @Override public void onCriarNovo(String filter) {
		 * Notification.show("Selecionado Criar Novo: " + filter); }
		 * 
		 * @Override public List<UnidadeProduto> getResultado(String q) { return
		 * unidadeProdutoDAO.query(q); }
		 * 
		 * @Override public Class<UnidadeProduto> getEntityClass() { return
		 * UnidadeProduto.class; }
		 * 
		 * @Override public String getCaptionProperty() { return "sigla"; }
		 * 
		 * @Override public void onEditar(UnidadeProduto value) {
		 * Notification.show("Selecionado Editar: " + value.getSigla());
		 * 
		 * } }; subView.getCmbUnidadeProduto().setModel(model);
		 * subView.getCmbUnidadeProduto().setValue(currentBean.getUnidade());
		 */

		/* Configura combo Marca Produto */
		/*
		 * ManyToOneComboModel<MarcaProduto> modelmarca = new
		 * ManyToOneComboModel<MarcaProduto>() {
		 * 
		 * @Override public void onCriarNovo(String filter) {
		 * Notification.show("Selecionado Criar Novo: " + filter); }
		 * 
		 * @Override public List<MarcaProduto> getResultado(String q) { return
		 * marcaProdutoDAO.query(q); }
		 * 
		 * @Override public Class<MarcaProduto> getEntityClass() { return
		 * MarcaProduto.class; }
		 * 
		 * @Override public String getCaptionProperty() { return "nome"; }
		 * 
		 * @Override public void onEditar(MarcaProduto value) {
		 * Notification.show("Selecionado Editar: " + value.getNome());
		 * 
		 * } }; subView.getCmbMarcaProduto().setModel(modelmarca);
		 * subView.getCmbMarcaProduto
		 * ().setValue(currentBean.getIdMarcaProduto());
		 */

		DefaultManyToOneComboModel<Almoxarifado> model = new DefaultManyToOneComboModel<Almoxarifado>(
				AlmoxarifadoListController.class, this.almoxarifadoDAO,
				super.getMainController());

		subView.getCmbAlmoxarifado().setModel(model);
		subView.getCmbAlmoxarifado().setValue(currentBean.getIdAlmoxarifado());

		DefaultManyToOneComboModel<GrupoTributario> model1 = new DefaultManyToOneComboModel<GrupoTributario>(
				GrupoTributarioListController.class, this.grupoTributarioDAO,
				super.getMainController());

		subView.getCmbGrupoTributario().setModel(model1);
		subView.getCmbGrupoTributario().setValue(
				currentBean.getIdGrupoTributario());

		DefaultManyToOneComboModel<UnidadeProduto> model2 = new DefaultManyToOneComboModel<UnidadeProduto>(
				UnidadeProdutoListController.class, this.unidadeProdutoDAO,
				super.getMainController());

		subView.getCmbUnidadeProduto().setModel(model2);
		subView.getCmbUnidadeProduto()
				.setValue(currentBean.getUnidadeProduto());

		subView.getCmbSubGrupoProduto().setValue(new SubGrupoProduto());
		subView.getCmbMarcaProduto().setValue(new MarcaProduto());
	}

	@Override
	protected void actionSalvar() {
		currentBean.setNome(subView.getTxtNome().getValue());
		currentBean.setDescricao(subView.getTxtDescricao().getValue());

		try {
			produtoDAO.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void quandoNovo() {

	}

	@Override
	protected String getNome() {
		return "Produto";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		produtoDAO.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "produtoForm";
	}

	public Produto getCurrentBean() {
		return currentBean;
	}

	public void setCurrentBean(Produto currentBean) {
		this.currentBean = currentBean;
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

}