package dc.controller.tabelas;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.controller.geral.UfListController;
import dc.entidade.geral.UfEntity;
import dc.entidade.tabelas.Feriados;
import dc.servicos.dao.geral.UFDAO;
import dc.servicos.dao.tabelas.FeriadosDAO;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.tabelas.FeriadosFormView;

/** @author Wesley Jr /* */

@Controller
@Scope("prototype")
public class FeriadosFormController extends CRUDFormController<Feriados> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FeriadosFormView subView;

	@Autowired
	private FeriadosDAO feriadosDAO;

	@Autowired
	private UFDAO ufDAO;

	private Feriados currentBean;

	@Override
	protected String getNome() {
		return "Feriados";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		currentBean.setAno(subView.getTxtAno().getValue());
		currentBean.setNome(subView.getTxtNome().getValue());

		try {
			feriadosDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = feriadosDAO.find(id);
		subView.getTxtAno().setValue(currentBean.getAno());
		subView.getTxtNome().setValue(currentBean.getNome());
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void initSubView() {
		subView = new FeriadosFormView();

		DefaultManyToOneComboModel<UfEntity> model = new DefaultManyToOneComboModel<UfEntity>(UfListController.class, this.ufDAO, super.getMainController()) {
			@Override
			public String getCaptionProperty() {
				return "nome";
			}
		};
		this.subView.getCmbUf().setModel(model);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new Feriados();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		feriadosDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		if (subView.getTxtNome().getValue() == null || subView.getTxtNome().getValue().isEmpty()) {
			adicionarErroDeValidacao(subView.getTxtNome(), "Não pode ficar em Branco!");

			return false;
		}

		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "feriadosForm";
	}

	@Override
	protected boolean isFullSized() {

		return true;
	}

	@Override
	public Feriados getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}