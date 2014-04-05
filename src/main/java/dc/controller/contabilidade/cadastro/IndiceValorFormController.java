package dc.controller.contabilidade.cadastro;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClasseUtil;
import dc.control.validator.ObjectValidator;
import dc.entidade.contabilidade.cadastro.IndiceEntity;
import dc.entidade.contabilidade.cadastro.IndiceValorEntity;
import dc.servicos.dao.contabilidade.cadastro.IndiceDAO;
import dc.servicos.dao.contabilidade.cadastro.IndiceValorDAO;
import dc.visao.contabilidade.cadastro.IndiceValorFormView;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class IndiceValorFormController extends
		CRUDFormController<IndiceValorEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IndiceValorFormView subView;

	/**
	 * DAO'S
	 */

	@Autowired
	private IndiceValorDAO pDAO;

	@Autowired
	private IndiceDAO iDAO;

	/**
	 * ENTITIES
	 */

	private IndiceValorEntity pEntity;

	/**
	 * CONSTRUTOR
	 */

	public IndiceValorFormController() {
		if (this.pEntity == null) {
			this.pEntity = new IndiceValorEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Índice valor";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			Date dataIndice = this.subView.getPdfDataIndice().getValue();
			Double valor = Double.parseDouble(this.subView.getTfValor()
					.getValue());

			IndiceEntity indice = this.subView.getCbIndice().getValue();

			this.pEntity.setDataIndice(dataIndice);
			this.pEntity.setValor(valor);

			this.pEntity.setIndice(indice);

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
		this.subView = new IndiceValorFormView(this);

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
		Object dataIndice = this.subView.getPdfDataIndice().getValue();

		if (!ObjectValidator.validateNotRequiredDate(dataIndice)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataIndice(), msg);

			return false;
		}

		String valor = this.subView.getTfValor().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(valor)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfValor(), msg);

			return false;
		}

		/**
		 * REQUIRED
		 */

		IndiceEntity indice = this.subView.getCbIndice().getValue();

		if (!ObjectValidator.validateObject(indice)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getCbIndice(), msg);

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
		String sUrl = ClasseUtil.getUrl(this);

		return sUrl;
	}

	/**
	 * COMBOS
	 */

	private void popularCombo() {
		try {
			DefaultManyToOneComboModel<IndiceEntity> model = new DefaultManyToOneComboModel<IndiceEntity>(
					IndiceListController.class, this.iDAO,
					super.getMainController());

			this.subView.getCbIndice().setModel(model);
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
				this.pEntity = new IndiceValorEntity();

				// this.subView.getCbIndice().setValue(this.pEntity.getIndice());
			} else {
				this.pEntity = this.pDAO.find(id);

				this.subView.getCbIndice().setValue(this.pEntity.getIndice());
			}

			this.subView.getPdfDataIndice().setValue(
					this.pEntity.getDataIndice());
			this.subView.getTfValor().setValue(
					this.pEntity.getValor().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}