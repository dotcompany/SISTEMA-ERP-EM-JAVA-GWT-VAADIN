package dc.controller.folhapagamento.ausencia;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.folhapagamento.ausencia.FeriasPeriodoAquisitivoEntity;
import dc.servicos.dao.folhapagamento.ausencia.FeriasPeriodoAquisitivoDAO;
import dc.visao.folhapagamento.ausencia.FeriasPeriodoAquisitivoFormView;
import dc.visao.framework.geral.CRUDFormController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class FeriasPeriodoAquisitivoFormController extends
		CRUDFormController<FeriasPeriodoAquisitivoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FeriasPeriodoAquisitivoFormView subView;

	/**
	 * DAO'S
	 */

	@Autowired
	private FeriasPeriodoAquisitivoDAO pDAO;

	/**
	 * ENTITIES
	 */

	private FeriasPeriodoAquisitivoEntity pEntity;

	/**
	 * CONSTRUTOR
	 */

	public FeriasPeriodoAquisitivoFormController() {
		if (this.pEntity == null) {
			this.pEntity = new FeriasPeriodoAquisitivoEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Servico";
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
		this.subView = new FeriasPeriodoAquisitivoFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		this.pEntity = new FeriasPeriodoAquisitivoEntity();
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
		return "folhapagamento_ausencia_ferias_periodo_aquisitivo_fc";
	}

	/**
	 * COMBOS
	 */

}