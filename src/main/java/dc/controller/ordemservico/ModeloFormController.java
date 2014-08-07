package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.ordemservico.Marca;
import dc.entidade.ordemservico.Modelo;
import dc.servicos.dao.ordemservico.MarcaDAO;
import dc.servicos.dao.ordemservico.ModeloDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.ModeloFormView;

/** @author Paulo Sérgio */

@Controller
@Scope("prototype")
public class ModeloFormController extends CRUDFormController<Modelo> {

	private static final long serialVersionUID = 1L;

	ModeloFormView subView;

	@Autowired
	ModeloDAO modeloDAO;

	@Autowired
	MarcaDAO marcaDAO;

	private Modelo currentBean;

	@Override
	protected String getNome() {
		return "Modelo";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		currentBean.setNome(subView.getTxtNome().getValue());
		currentBean.setMarca(subView.getCbMarca().getValue());
		try {
			modeloDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = modeloDAO.find(id);
		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getCbMarca().setValue(currentBean.getMarca());
	}

	/*
	 * Callback para quando novo foi acionado. Colocar ProgramaÃ§Ã£o customizada
	 * para essa aÃ§Ã£o aqui. Ou entÃ£o deixar em branco, para comportamento
	 * padrÃ£o
	 */
	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void initSubView() {
		subView = new ModeloFormView();

		preencheCombos();
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new Modelo();
	}

	private void preencheCombos() {

		DefaultManyToOneComboModel<Marca> marca = new DefaultManyToOneComboModel<Marca>(MarcaListController.class, this.marcaDAO,
				super.getMainController());

		this.subView.getCbMarca().setModel(marca);
	}

	@Override
	protected void remover(List<Serializable> ids) {
		modeloDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {

		boolean valido = true;

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(), "Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "modeloForm";
	}

	@Override
	public Modelo getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}
}
