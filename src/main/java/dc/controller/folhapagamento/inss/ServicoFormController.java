package dc.controller.folhapagamento.inss;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.folhapagamento.inss.ServicoEntity;
import dc.servicos.dao.folhapagamento.inss.ServicoDAO;
import dc.visao.folhapagamento.inss.ServicoFormView;
import dc.visao.framework.geral.CRUDFormController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class ServicoFormController extends CRUDFormController<ServicoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ServicoFormView subView;

	/**
	 * DAO'S
	 */

	@Autowired
	private ServicoDAO pDAO;

	/**
	 * ENTITIES
	 */

	private ServicoEntity pEntity;

	/**
	 * CONSTRUTOR
	 */

	public ServicoFormController() {
		if (this.pEntity == null) {
			this.pEntity = new ServicoEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Serviço";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String codigo = this.subView.getTfCodigo().getValue();
			String nome = this.subView.getTfNome().getValue();

			this.pEntity.setCodigo(codigo);
			this.pEntity.setNome(nome);

			this.pDAO.saveOrUpdate(this.pEntity);

			mensagemSalvoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			this.pEntity = new ServicoEntity();

			this.subView.getTfCodigo().setValue(this.pEntity.getCodigo());
			this.subView.getTfNome().setValue(this.pEntity.getNome());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = this.pDAO.find(id);

			this.subView.getTfCodigo().setValue(this.pEntity.getCodigo());
			this.subView.getTfNome().setValue(this.pEntity.getNome());
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
			this.pEntity = new ServicoEntity();

			this.subView.getTfCodigo().setValue(this.pEntity.getCodigo());
			this.subView.getTfNome().setValue(this.pEntity.getNome());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new ServicoFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.pEntity = new ServicoEntity();

			this.subView.getTfCodigo().setValue(this.pEntity.getCodigo());
			this.subView.getTfNome().setValue(this.pEntity.getNome());
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
		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "folhapagamento_inss_servico_fc";
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