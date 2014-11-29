package dc.controller.folhapagamento.movimento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.validator.ObjectValidator;
import dc.entidade.folhapagamento.movimento.PppEntity;
import dc.entidade.pessoal.ColaboradorEntity;
import dc.servicos.dao.folhapagamento.movimento.PppDAO;
import dc.servicos.dao.pessoal.ColaboradorDAO;
import dc.visao.folhapagamento.movimento.PppFormView;
import dc.visao.framework.geral.CRUDFormController;

/** @author Gutemberg A. Da Silva */

@Controller
@Scope("prototype")
public class PppFormController extends CRUDFormController<PppEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PppFormView subView;

	/** DAO'S */

	@Autowired
	private PppDAO pDAO;

	@Autowired
	private ColaboradorDAO cDAO;

	/** ENTITIES */

	private PppEntity pEntity;

	/** CONSTRUTOR */

	public PppFormController() {
		if (this.pEntity == null) {
			this.pEntity = new PppEntity();
		}
	}

	@Override
	protected String getNome() {
		return "PPP";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String observacao = this.subView.getTfObservacao().getValue();
			ColaboradorEntity colaborador = (ColaboradorEntity) this.subView.getCbColaborador().getValue();

			this.pEntity.setObservacao(observacao);
			this.pEntity.setColaborador(colaborador);

			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			this.pEntity = new PppEntity();

			this.subView.getTfObservacao().setValue(this.pEntity.getObservacao());

			this.subView.getCbColaborador().setValue(this.pEntity.getColaborador());

			this.subView.carregarCmbColaborador(this.colaboradorListarTodos());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = this.pDAO.find(id);

			this.subView.getTfObservacao().setValue(this.pEntity.getObservacao());

			this.subView.carregarCmbColaborador(this.colaboradorListarTodos());

			this.subView.getCbColaborador().setValue(this.pEntity.getColaborador());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {
		try {
			this.pEntity = new PppEntity();

			if (this.cDAO == null) {
				this.cDAO = new ColaboradorDAO();
			}

			this.subView.getTfObservacao().setValue(this.pEntity.getObservacao());

			this.subView.carregarCmbColaborador(this.colaboradorListarTodos());

			this.subView.getCbColaborador().setValue(this.pEntity.getColaborador());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new PppFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.pEntity = new PppEntity();

			if (this.cDAO == null) {
				this.cDAO = new ColaboradorDAO();
			}

			this.subView.getTfObservacao().setValue(this.pEntity.getObservacao());

			this.subView.carregarCmbColaborador(this.colaboradorListarTodos());

			this.subView.getCbColaborador().setValue(this.pEntity.getColaborador());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.pDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		ColaboradorEntity colaborador = (ColaboradorEntity) this.subView.getCbColaborador().getValue();

		if (!ObjectValidator.validateObject(colaborador)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getCbColaborador(), msg);

			return false;
		}

		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "folhapagamento_movimento_ppp_fc";
	}

	/** COMBOS */

	public List<ColaboradorEntity> colaboradorListarTodos() {
		List<ColaboradorEntity> auxLista = new ArrayList<ColaboradorEntity>();

		auxLista = this.cDAO.colaboradorLista();

		return auxLista;
	}

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	@Override
	public PppEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}