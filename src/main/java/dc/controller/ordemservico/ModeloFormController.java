package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.ordemservico.MarcaOsEntity;
import dc.entidade.ordemservico.ModeloEntity;
import dc.servicos.dao.ordemservico.MarcaDAO;
import dc.servicos.dao.ordemservico.ModeloDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.ModeloFormView;
import dc.visao.spring.SecuritySessionProvider;

/** @author Paulo Sérgio */

@Controller
@Scope("prototype")
public class ModeloFormController extends CRUDFormController<ModeloEntity> {

	private static final long serialVersionUID = 1L;

	ModeloFormView subView;

	@Autowired
	ModeloDAO modeloDAO;

	@Autowired
	MarcaDAO marcaDAO;

	private ModeloEntity currentBean;

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
		
		MarcaOsEntity marca = (MarcaOsEntity) subView.getCbMarca().getValue();
		currentBean.setMarca(marca); 
		currentBean.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
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
		currentBean = new ModeloEntity();
	}

	private void preencheCombos() {

		DefaultManyToOneComboModel<MarcaOsEntity> marca = new DefaultManyToOneComboModel<MarcaOsEntity>(MarcaListController.class, this.marcaDAO,
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
	public ModeloEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}
}
