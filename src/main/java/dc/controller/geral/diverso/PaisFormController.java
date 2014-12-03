package dc.controller.geral.diverso;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.geral.diverso.PaisEntity;
import dc.servicos.dao.geral.diverso.PaisDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.diverso.PaisFormView;

/**
 * @author Wesley Jr /* Nessa classe ela pega a classe principal que é o CRUD,
 *         que tem todos os controllers da Tela, onde quando extendemos herdamos
 *         os métodos que temos na tela principal. Temos o botão Novo que é para
 *         Criar uma nova Tela, para adicionar informações novas, e dentro temos
 *         o Button Salvar que é para salvar as informações no Banco de Dados
 *         Temos o carregar também que é para pegar as informações que
 *         desejarmos quando formos pesquisar na Tela.
 */

@Controller
@Scope("prototype")
public class PaisFormController extends CRUDFormController<PaisEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PaisFormView subView;

	/** DAO'S */

	@Autowired
	private PaisDAO paisDAO;

	/** ENTITIES */

	private PaisEntity currentBean;

	/** CONSTRUTOR */

	public PaisFormController() {
		if (this.currentBean == null) {
			this.currentBean = new PaisEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Pais";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			currentBean.setNomePtbr(subView.getTxtNome().getValue());
			currentBean.setNomeEn(subView.getTxtNomeIngles().getValue());
			currentBean.setSigla2(subView.getTxtSigla2().getValue());
			currentBean.setSigla3(subView.getTxtSigla3().getValue());

			paisDAO.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			currentBean = paisDAO.find(id);

			subView.getTxtNome().setValue(currentBean.getNomePtbr());
			subView.getTxtNomeIngles().setValue(currentBean.getNomeEn());
			subView.getTxtSigla2().setValue(currentBean.getSigla2());
			subView.getTxtSigla3().setValue(currentBean.getSigla3());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {
		try {
			this.currentBean = new PaisEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		subView = new PaisFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.currentBean = new PaisEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		paisDAO.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtNomeIngles().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNomeIngles(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtSigla2().getValue())) {
			adicionarErroDeValidacao(subView.getTxtSigla2(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtSigla3().getValue())) {
			adicionarErroDeValidacao(subView.getTxtSigla3(),
					"Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "paisForm";
	}

	/** COMBOS */

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	@Override
	public PaisEntity getModelBean() {
		return currentBean;
	}

}