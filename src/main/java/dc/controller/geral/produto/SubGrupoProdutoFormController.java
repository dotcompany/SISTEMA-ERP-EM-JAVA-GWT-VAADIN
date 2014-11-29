package dc.controller.geral.produto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.produto.GrupoProdutoEntity;
import dc.entidade.geral.produto.SubGrupoProdutoEntity;
import dc.servicos.dao.geral.produto.GrupoProdutoDAO;
import dc.servicos.dao.geral.produto.SubGrupoProdutoDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.produto.SubGrupoProdutoFormView;

@Controller
@Scope("prototype")
public class SubGrupoProdutoFormController extends
		CRUDFormController<SubGrupoProdutoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SubGrupoProdutoFormView subView;

	@Autowired
	private SubGrupoProdutoDAO subGrupoProdutoDAO;

	@Autowired
	private GrupoProdutoDAO grupoProdutoDAO;

	private SubGrupoProdutoEntity currentBean;

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
		currentBean = new SubGrupoProdutoEntity();
	}

	@Override
	protected void initSubView() {
		subView = new SubGrupoProdutoFormView();

		DefaultManyToOneComboModel<GrupoProdutoEntity> modelGrupoProduto = new DefaultManyToOneComboModel<GrupoProdutoEntity>(
				GrupoProdutoListController.class, grupoProdutoDAO,
				super.getMainController());

		subView.getCmbGrupoProduto().setModel(modelGrupoProduto);
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = subGrupoProdutoDAO.find(id);

		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtDescricao().setValue(currentBean.getDescricao());

		DefaultManyToOneComboModel<GrupoProdutoEntity> modelGrupoProduto = new DefaultManyToOneComboModel<GrupoProdutoEntity>(
				GrupoProdutoListController.class, grupoProdutoDAO,
				super.getMainController());

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
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
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
	public SubGrupoProdutoEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}