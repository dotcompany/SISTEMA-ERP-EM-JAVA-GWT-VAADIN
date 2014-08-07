package dc.controller.produto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.produto.GrupoProduto;
import dc.entidade.produto.SubGrupoProduto;
import dc.servicos.dao.produto.GrupoProdutoDAO;
import dc.servicos.dao.produto.SubGrupoProdutoDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.produto.SubGrupoProdutoFormView;

@Controller
@Scope("prototype")
public class SubGrupoProdutoFormController extends CRUDFormController<SubGrupoProduto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SubGrupoProdutoFormView subView;

	@Autowired
	private SubGrupoProdutoDAO subGrupoProdutoDAO;

	@Autowired
	private GrupoProdutoDAO grupoProdutoDAO;

	private SubGrupoProduto currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtDescricao(), "Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new SubGrupoProduto();
	}

	@Override
	protected void initSubView() {
		subView = new SubGrupoProdutoFormView();

		DefaultManyToOneComboModel<GrupoProduto> modelGrupoProduto = new DefaultManyToOneComboModel<GrupoProduto>(GrupoProdutoListController.class,
				grupoProdutoDAO, super.getMainController());

		subView.getCmbGrupoProduto().setModel(modelGrupoProduto);
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = subGrupoProdutoDAO.find(id);

		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtDescricao().setValue(currentBean.getDescricao());

		DefaultManyToOneComboModel<GrupoProduto> modelGrupoProduto = new DefaultManyToOneComboModel<GrupoProduto>(GrupoProdutoListController.class,
				grupoProdutoDAO, super.getMainController());

		subView.getCmbGrupoProduto().setModel(modelGrupoProduto);
	}

	@Override
	protected void actionSalvar() {
		currentBean.setNome(subView.getTxtNome().getValue());
		currentBean.setDescricao(subView.getTxtDescricao().getValue());

		try {
			subGrupoProdutoDAO.saveOrUpdate(currentBean);

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
		return "Sub Grupo Produto";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		subGrupoProdutoDAO.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "subGrupoProdutoForm";
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	public SubGrupoProduto getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}