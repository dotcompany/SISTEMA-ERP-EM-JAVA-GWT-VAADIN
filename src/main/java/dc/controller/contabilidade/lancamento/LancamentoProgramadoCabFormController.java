package dc.controller.contabilidade.lancamento;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.lancamento.LancamentoProgramadoCabEntity;
import dc.entidade.contabilidade.lancamento.LoteEntity;
import dc.servicos.dao.contabilidade.lancamento.LancamentoProgramadoCabDAO;
import dc.servicos.dao.contabilidade.lancamento.LoteDAO;
import dc.visao.contabilidade.lancamento.LancamentoProgramadoCabFormView;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class LancamentoProgramadoCabFormController extends
		CRUDFormController<LancamentoProgramadoCabEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LancamentoProgramadoCabFormView subView;

	/**
	 * DAO'S
	 */

	@Autowired
	private LancamentoProgramadoCabDAO pDAO;

	@Autowired
	private LoteDAO lDAO;

	/**
	 * ENTITIES
	 */

	private LancamentoProgramadoCabEntity pEntity;

	/**
	 * CONSTRUTOR
	 */

	public LancamentoProgramadoCabFormController() {
		if (this.pEntity == null) {
			this.pEntity = new LancamentoProgramadoCabEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Lançamento programado cab";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			Date dataInclusao = this.subView.getPdfDataInclusao().getValue();
			Date dataRealizada = this.subView.getPdfDataRealizada().getValue();
			Date dataPrevista = this.subView.getPdfDataPrevista().getValue();
			String tipo = this.subView.getTfTipo().getValue();
			String liberado = this.subView.getTfLiberado().getValue();

			LoteEntity lote = (LoteEntity) this.subView.getCbLote().getValue();

			this.pEntity.setDataInclusao(dataInclusao);
			this.pEntity.setDataRealizada(dataRealizada);
			this.pEntity.setDataPrevista(dataPrevista);
			this.pEntity.setTipo(tipo);
			this.pEntity.setLiberado(liberado);

			this.pEntity.setLote(lote);

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
		this.subView = new LancamentoProgramadoCabFormView(this);

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
			DefaultManyToOneComboModel<LoteEntity> model1 = new DefaultManyToOneComboModel<LoteEntity>(
					LoteListController.class, this.lDAO,
					super.getMainController());

			this.subView.getCbLote().setModel(model1);
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
				this.pEntity = new LancamentoProgramadoCabEntity();
			} else {
				this.pEntity = this.pDAO.find(id);
			}

			this.subView.getPdfDataInclusao().setValue(
					this.pEntity.getDataInclusao());
			this.subView.getPdfDataRealizada().setValue(
					this.pEntity.getDataRealizada());
			this.subView.getPdfDataPrevista().setValue(
					this.pEntity.getDataPrevista());
			this.subView.getTfTipo().setValue(this.pEntity.getTipo());
			this.subView.getTfLiberado().setValue(this.pEntity.getLiberado());

			this.subView.getCbLote().setValue(this.pEntity.getLote());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}