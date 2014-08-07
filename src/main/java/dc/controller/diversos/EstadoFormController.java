package dc.controller.diversos;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.diversos.Estado;
import dc.entidade.diversos.Pais;
import dc.servicos.dao.diversos.EstadoDAO;
import dc.servicos.dao.diversos.PaisDAO;
import dc.visao.diversos.EstadoFormView;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;

/** @author Wesley Jr /* Nessa classe ela pega a classe principal que Ã© o CRUD,
 *         que tem todos os controllers da Tela, onde quando extendemos herdamos
 *         os mÃ©todos que temos na tela principal. Temos o botÃ£o Novo que Ã©
 *         para Criar uma nova Tela, para adicionar informaÃ§Ãµes novas, e
 *         dentro temos o Button Salvar que Ã© para salvar as informaÃ§Ãµes no
 *         Banco de Dados Temos o carregar tambÃ©m que Ã© para pegar as
 *         informaÃ§Ãµes que desejarmos quando formos pesquisar na Tela. */

@Controller
@Scope("prototype")
public class EstadoFormController extends CRUDFormController<Estado> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EstadoFormView subView;

	@Autowired
	private EstadoDAO estadoDAO;

	@Autowired
	private PaisDAO paisDAO;

	private Estado currentBean;

	// private MainController mainController;

	@Override
	protected String getNome() {
		return "Estado";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		currentBean.setNome(subView.getTxtNome().getValue());
		currentBean.setSigla(subView.getTxtSigla().getValue());

		try {
			estadoDAO.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = estadoDAO.find(id);

		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtSigla().setValue(currentBean.getSigla());
		subView.getTxtCodigoIBGE().setValue(currentBean.getCodigoIbge().toString());

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
		try {
			subView = new EstadoFormView();

			DefaultManyToOneComboModel<Pais> paisModel = new DefaultManyToOneComboModel<Pais>(PaisListController.class, this.paisDAO,
					super.getMainController()) {

				@Override
				public String getCaptionProperty() {
					return "nomePtbr";
				}
			};

			this.subView.getCmbPais().setModel(paisModel);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new Estado();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		estadoDAO.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		if (subView.getTxtNome().getValue() == null || subView.getTxtNome().getValue().isEmpty()) {
			// Utilizar adicionarErroDeValidacao() para adicionar mensagem de
			// erro para o campo que esta sendo validado
			adicionarErroDeValidacao(subView.getTxtNome(), "Não pode ficar em branco!");

			return false;
		}

		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "estadoForm";
	}

	@Override
	public Estado getModelBean() {
		return currentBean;
	}

}