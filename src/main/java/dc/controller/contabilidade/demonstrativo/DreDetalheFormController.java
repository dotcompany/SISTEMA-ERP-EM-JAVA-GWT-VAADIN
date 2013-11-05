package dc.controller.contabilidade.demonstrativo;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.demonstrativo.DreDetalheEntity;
import dc.servicos.dao.contabilidade.demonstrativo.DreDetalheDAO;
import dc.visao.contabilidade.demonstrativo.DreDetalheFormView;
import dc.visao.framework.geral.CRUDFormController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class DreDetalheFormController extends
		CRUDFormController<DreDetalheEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DreDetalheFormView subView;

	/**
	 * DAO'S
	 */

	@Autowired
	private DreDetalheDAO pDAO;

	/**
	 * ENTITIES
	 */

	private DreDetalheEntity pEntity;

	/**
	 * CONSTRUTOR
	 */

	public DreDetalheFormController() {
		if (this.pEntity == null) {
			this.pEntity = new DreDetalheEntity();
		}
	}

	@Override
	protected String getNome() {
		return "DRE detalhe";
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
		this.subView = new DreDetalheFormView(this);
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
				this.pEntity = new DreDetalheEntity();
			} else {
				this.pEntity = this.pDAO.find(id);
			}

			this.subView.getTfClassificacao().setValue(
					this.pEntity.getClassificacao());
			this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());
			this.subView.getTfFormaCalculo().setValue(
					this.pEntity.getFormaCalculo());
			this.subView.getTfSinal().setValue(this.pEntity.getSinal());
			this.subView.getTfNatureza().setValue(this.pEntity.getNatureza());
			this.subView.getTfValor().setValue(
					this.pEntity.getValor().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}