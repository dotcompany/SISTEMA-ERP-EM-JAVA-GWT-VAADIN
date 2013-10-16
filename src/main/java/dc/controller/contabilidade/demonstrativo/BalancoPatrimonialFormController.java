package dc.controller.contabilidade.demonstrativo;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.demonstrativo.BalancoPatrimonialEntity;
import dc.servicos.dao.contabilidade.demonstrativo.BalancoPatrimonialDAO;
import dc.visao.contabilidade.demonstrativo.BalancoPatrimonialFormView;
import dc.visao.framework.geral.CRUDFormController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class BalancoPatrimonialFormController extends
		CRUDFormController<BalancoPatrimonialEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BalancoPatrimonialFormView subView;

	/**
	 * DAO'S
	 */

	@Autowired
	private BalancoPatrimonialDAO pDAO;

	/**
	 * ENTITIES
	 */

	private BalancoPatrimonialEntity pEntity;

	/**
	 * CONSTRUTOR
	 */

	public BalancoPatrimonialFormController() {
		if (this.pEntity == null) {
			this.pEntity = new BalancoPatrimonialEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Balanço patrimonial";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {

		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = this.pDAO.find(id);
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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initSubView() {
		// this.subView = new AidfAimdfFormView(null);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.pEntity = new BalancoPatrimonialEntity();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.pDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getViewIdentifier() {
		String sUrl = ClasseUtil.getUrl(this);

		return sUrl;
	}

	/**
	 * COMBOS
	 */

	/**
	 * **************************************
	 */

	@Override
	protected boolean isFullSized() {
		return true;
	}

}