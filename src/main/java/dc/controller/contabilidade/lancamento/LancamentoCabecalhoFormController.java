package dc.controller.contabilidade.lancamento;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.lancamento.LancamentoCabecalhoEntity;
import dc.entidade.contabilidade.lancamento.LoteEntity;
import dc.servicos.dao.contabilidade.lancamento.LancamentoCabecalhoDAO;
import dc.servicos.dao.contabilidade.lancamento.LoteDAO;
import dc.visao.contabilidade.lancamento.LancamentoCabecalhoFormView;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class LancamentoCabecalhoFormController extends
		CRUDFormController<LancamentoCabecalhoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LancamentoCabecalhoFormView subView;

	/**
	 * DAO'S
	 */

	@Autowired
	private LancamentoCabecalhoDAO pDAO;

	@Autowired
	private LoteDAO lDAO;

	/**
	 * ENTITIES
	 */

	private LancamentoCabecalhoEntity pEntity;

	/**
	 * CONSTRUTOR
	 */

	public LancamentoCabecalhoFormController() {
		if (this.pEntity == null) {
			this.pEntity = new LancamentoCabecalhoEntity();
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
			Date dataLancamento = this.subView.getPdfDataLancamento()
					.getValue();
			Date dataInclusao = this.subView.getPdfDataInclusao().getValue();
			String tipo = this.subView.getTfTipo().getValue();
			String liberado = this.subView.getTfLiberado().getValue();
			Double valor = Double.parseDouble(this.subView.getTfValor()
					.getValue());

			this.pEntity.setDataLancamento(dataLancamento);
			this.pEntity.setDataInclusao(dataInclusao);
			this.pEntity.setTipo(tipo);
			this.pEntity.setLiberado(liberado);
			this.pEntity.setValor(valor);

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
		this.subView = new LancamentoCabecalhoFormView(this);

		popularCombo();
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

	private void popularCombo() {
		try {
			DefaultManyToOneComboModel<LoteEntity> model = new DefaultManyToOneComboModel<LoteEntity>(
					LoteListController.class, this.lDAO,
					super.getMainController());

			this.subView.getCbLote().setModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
				this.pEntity = new LancamentoCabecalhoEntity();
			} else {
				this.pEntity = this.pDAO.find(id);
			}

			this.subView.getPdfDataLancamento().setValue(
					this.pEntity.getDataLancamento());
			this.subView.getPdfDataInclusao().setValue(
					this.pEntity.getDataInclusao());
			this.subView.getTfTipo().setValue(this.pEntity.getTipo());
			this.subView.getTfLiberado().setValue(this.pEntity.getLiberado());
			this.subView.getTfValor().setValue(
					this.pEntity.getValor().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}