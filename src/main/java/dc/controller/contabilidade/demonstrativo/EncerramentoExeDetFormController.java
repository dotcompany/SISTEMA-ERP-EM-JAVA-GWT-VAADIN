package dc.controller.contabilidade.demonstrativo;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.demonstrativo.EncerramentoExeDetEntity;
import dc.servicos.dao.contabilidade.demonstrativo.EncerramentoExeDetDAO;
import dc.visao.contabilidade.demonstrativo.EncerramentoExeDetFormView;
import dc.visao.framework.geral.CRUDFormController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class EncerramentoExeDetFormController extends
		CRUDFormController<EncerramentoExeDetEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EncerramentoExeDetFormView subView;

	/**
	 * DAO'S
	 */

	@Autowired
	private EncerramentoExeDetDAO pDAO;

	/**
	 * ENTITIES
	 */

	private EncerramentoExeDetEntity pEntity;

	/**
	 * CONSTRUTOR
	 */

	public EncerramentoExeDetFormController() {
		if (this.pEntity == null) {
			this.pEntity = new EncerramentoExeDetEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Encerramento de exercício detalhe";
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
		this.subView = new EncerramentoExeDetFormView(this);
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
				this.pEntity = new EncerramentoExeDetEntity();
			} else {
				this.pEntity = this.pDAO.find(id);
			}

			this.subView.getTfSaldoAnterior().setValue(
					this.pEntity.getSaldoAnterior().toString());
			this.subView.getTfValorDebito().setValue(
					this.pEntity.getValorDebito().toString());
			this.subView.getTfValorCredito().setValue(
					this.pEntity.getValorCredito().toString());
			this.subView.getTfSaldo().setValue(
					this.pEntity.getSaldo().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}