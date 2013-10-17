package dc.controller.contabilidade.livrocontabil;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.livrocontabil.TermoEntity;
import dc.servicos.dao.contabilidade.livrocontabil.TermoDAO;
import dc.visao.contabilidade.livrocontabil.TermoFormView;
import dc.visao.framework.geral.CRUDFormController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class TermoFormController extends CRUDFormController<TermoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TermoFormView subView;

	/**
	 * DAO'S
	 */

	@Autowired
	private TermoDAO pDAO;

	/**
	 * ENTITIES
	 */

	private TermoEntity pEntity;

	/**
	 * CONSTRUTOR
	 */

	public TermoFormController() {
		if (this.pEntity == null) {
			this.pEntity = new TermoEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Termo";
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
			novoObjeto(0);
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			novoObjeto(id);
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
			novoObjeto(0);
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
			novoObjeto(0);
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

	/**
	 * **************************************
	 */

	private void novoObjeto(Serializable id) {
		try {
			if (id.equals(0) || id == null) {
				this.pEntity = new TermoEntity();
			} else {
				this.pEntity = this.pDAO.find(id);
			}

			// this.subView.getTfValor13().setValue(
			// String.valueOf(this.pEntity.getValor13()));
			// this.subView.getTfValorMensal().setValue(
			// String.valueOf(this.pEntity.getValorMensal()));

			// this.subView.getCbInss().setData(this.inssListarTodos());
			// this.subView.getCbServico().setData(this.servicoListarTodos());

			// this.subView.getCbInss().setValue(this.pEntity.getInss());
			// this.subView.getCbServico().setValue(this.pEntity.getServico());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}