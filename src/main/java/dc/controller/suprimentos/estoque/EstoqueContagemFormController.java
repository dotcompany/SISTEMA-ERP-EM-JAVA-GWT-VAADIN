package dc.controller.suprimentos.estoque;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClasseUtil;
import dc.entidade.suprimentos.estoque.EstoqueContagemEntity;
import dc.servicos.dao.suprimentos.estoque.EstoqueContagemDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.suprimentos.estoque.EstoqueContagemFormView;

/** @author Gutemberg A. Da Silva */

@Controller
@Scope("prototype")
public class EstoqueContagemFormController extends
		CRUDFormController<EstoqueContagemEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EstoqueContagemFormView subView;

	/** DAO'S */

	@Autowired
	private EstoqueContagemDAO pDAO;

	/** ENTITIES */

	private EstoqueContagemEntity pEntity;

	/** CONSTRUTOR */

	public EstoqueContagemFormController() {
		if (this.pEntity == null) {
			this.pEntity = new EstoqueContagemEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Contagem de estoque";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			this.pEntity = new EstoqueContagemEntity();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		this.pEntity = this.pDAO.find(id);
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
		this.subView = new EstoqueContagemFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		this.pEntity = new EstoqueContagemEntity();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		this.pDAO.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return ClasseUtil.getUrl(this);
	}

	@Override
	public EstoqueContagemEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

	/** COMBOS */

}