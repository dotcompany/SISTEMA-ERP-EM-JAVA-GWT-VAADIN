package dc.controller.contabilidade.lancamento;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.lancamento.LancamentoDetalheEntity;
import dc.servicos.dao.contabilidade.lancamento.LancamentoDetalheDAO;
import dc.visao.contabilidade.lancamento.LancamentoDetalheFormView;
import dc.visao.framework.geral.CRUDFormController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class LancamentoDetalheFormController extends
		CRUDFormController<LancamentoDetalheEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LancamentoDetalheFormView subView;

	/**
	 * DAO'S
	 */

	@Autowired
	private LancamentoDetalheDAO pDAO;

	/**
	 * ENTITIES
	 */

	private LancamentoDetalheEntity pEntity;

	/**
	 * CONSTRUTOR
	 */

	public LancamentoDetalheFormController() {
		if (this.pEntity == null) {
			this.pEntity = new LancamentoDetalheEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Lançamento lote";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String descricaoHistorico = this.subView.getTfDescricaoHistorico()
					.getValue();
			Double valor = Double.parseDouble(this.subView.getTfValor()
					.getValue());
			String tipo = this.subView.getTfTipo().getValue();

			this.pEntity.setDescricaoHistorico(descricaoHistorico);
			this.pEntity.setValor(valor);
			this.pEntity.setTipo(tipo);

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
		this.subView = new LancamentoDetalheFormView(this);
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
				this.pEntity = new LancamentoDetalheEntity();
			} else {
				this.pEntity = this.pDAO.find(id);
			}

			this.subView.getTfDescricaoHistorico().setValue(
					this.pEntity.getDescricaoHistorico());
			this.subView.getTfValor().setValue(
					this.pEntity.getValor().toString());
			this.subView.getTfTipo().setValue(this.pEntity.getTipo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}