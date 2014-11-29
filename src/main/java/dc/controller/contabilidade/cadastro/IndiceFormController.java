package dc.controller.contabilidade.cadastro;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.validator.ObjectValidator;
import dc.controller.financeiro.IndiceEconomicoListController;
import dc.entidade.contabilidade.cadastro.IndiceEntity;
import dc.entidade.financeiro.IndiceEconomicoEntity;
import dc.servicos.dao.contabilidade.cadastro.IndiceDAO;
import dc.servicos.dao.financeiro.IndiceEconomicoDAO;
import dc.visao.contabilidade.cadastro.IndiceFormView;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;

/** @author Gutemberg A. Da Silva */

@Controller
@Scope("prototype")
public class IndiceFormController extends CRUDFormController<IndiceEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IndiceFormView subView;

	/** DAO'S */

	@Autowired
	private IndiceDAO pDAO;

	@Autowired
	private IndiceEconomicoDAO ieDAO;

	/** ENTITIES */

	private IndiceEntity pEntity;

	/** CONSTRUTOR */

	public IndiceFormController() {
		if (this.pEntity == null) {
			this.pEntity = new IndiceEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Índice";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String periocidade = this.subView.getTfPeriodicidade().getValue();
			Date diarioPartirDe = this.subView.getPdfDiarioPartirDe()
					.getValue();
			String mensalMesAno = this.subView.getTfMensalMesAno().getValue();

			IndiceEconomicoEntity indiceEconomico = this.subView
					.getCbIndiceEconomico().getValue();

			this.pEntity.setPeriodicidade(periocidade);
			this.pEntity.setDiarioPartirDe(diarioPartirDe);
			this.pEntity.setMensalMesAno(mensalMesAno);

			this.pEntity.setIndiceEconomico(indiceEconomico);

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
		this.subView = new IndiceFormView(this);

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
		Object diarioPartirDe = this.subView.getPdfDiarioPartirDe().getValue();

		if (!ObjectValidator.validateNotRequiredDate(diarioPartirDe)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDiarioPartirDe(), msg);

			return false;
		}

		/** REQUIRED */

		IndiceEconomicoEntity indiceEconomico = this.subView
				.getCbIndiceEconomico().getValue();

		if (!ObjectValidator.validateObject(indiceEconomico)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getCbIndiceEconomico(), msg);

			return false;
		}

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
		return ClassUtils.getUrl(this);
	}

	/** COMBOS */

	private void popularCombo() {
		try {
			DefaultManyToOneComboModel<IndiceEconomicoEntity> model = new DefaultManyToOneComboModel<IndiceEconomicoEntity>(
					IndiceEconomicoListController.class, this.ieDAO,
					super.getMainController()) {

				@Override
				public String getCaptionProperty() {
					return "nome";
				}
			};

			this.subView.getCbIndiceEconomico().setModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	/** ************************************** */

	private void novoObjeto(Serializable id) {
		try {
			if (id.equals(0) || id == null) {
				this.pEntity = new IndiceEntity();

				// this.subView.getCbIndiceEconomico().setValue(
				// this.pEntity.getIndiceEconomico());
			} else {
				this.pEntity = this.pDAO.find(id);

				this.subView.getCbIndiceEconomico().setValue(
						this.pEntity.getIndiceEconomico());
			}

			this.subView.getTfPeriodicidade().setValue(
					this.pEntity.getPeriodicidade());
			this.subView.getPdfDiarioPartirDe().setValue(
					this.pEntity.getDiarioPartirDe());
			this.subView.getTfMensalMesAno().setValue(
					this.pEntity.getMensalMesAno());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public IndiceEntity getModelBean() {
		return pEntity;
	}

}