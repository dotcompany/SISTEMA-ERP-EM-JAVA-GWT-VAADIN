package dc.controller.contabilidade.lancamento;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.validator.ObjectValidator;
import dc.controller.contabilidade.planoconta.ContaListController;
import dc.entidade.contabilidade.lancamento.LancamentoOrcadoEntity;
import dc.entidade.contabilidade.planoconta.ContaEntity;
import dc.servicos.dao.contabilidade.lancamento.LancamentoOrcadoDAO;
import dc.servicos.dao.contabilidade.planoconta.ContaDAO;
import dc.visao.contabilidade.lancamento.LancamentoOrcadoFormView;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;

/** @author Gutemberg A. Da Silva */

@Controller
@Scope("prototype")
public class LancamentoOrcadoFormController extends
		CRUDFormController<LancamentoOrcadoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LancamentoOrcadoFormView subView;

	/** DAO'S */

	@Autowired
	private LancamentoOrcadoDAO pDAO;

	@Autowired
	private ContaDAO cDAO;

	/** ENTITIES */

	private LancamentoOrcadoEntity pEntity;

	/** CONSTRUTOR */

	public LancamentoOrcadoFormController() {
		if (this.pEntity == null) {
			this.pEntity = new LancamentoOrcadoEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Lançamento orçado";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String ano = this.subView.getTfAno().getValue();
			Double janeiro = Double.parseDouble(this.subView.getTfJaneiro()
					.getValue());
			Double fevereiro = Double.parseDouble(this.subView.getTfFevereiro()
					.getValue());
			Double marco = Double.parseDouble(this.subView.getTfMarco()
					.getValue());
			Double abril = Double.parseDouble(this.subView.getTfAbril()
					.getValue());
			Double maio = Double.parseDouble(this.subView.getTfMaio()
					.getValue());
			Double junho = Double.parseDouble(this.subView.getTfJunho()
					.getValue());
			Double julho = Double.parseDouble(this.subView.getTfJulho()
					.getValue());
			Double agosto = Double.parseDouble(this.subView.getTfAgosto()
					.getValue());
			Double setembro = Double.parseDouble(this.subView.getTfSetembro()
					.getValue());
			Double outubro = Double.parseDouble(this.subView.getTfOutubro()
					.getValue());
			Double novembro = Double.parseDouble(this.subView.getTfNovembro()
					.getValue());
			Double dezembro = Double.parseDouble(this.subView.getTfDezembro()
					.getValue());

			ContaEntity conta = this.subView.getCbConta().getValue();

			this.pEntity.setAno(ano);
			this.pEntity.setJaneiro(janeiro);
			this.pEntity.setFevereiro(fevereiro);
			this.pEntity.setMarco(marco);
			this.pEntity.setAbril(abril);
			this.pEntity.setMaio(maio);
			this.pEntity.setJunho(junho);
			this.pEntity.setJulho(julho);
			this.pEntity.setAgosto(agosto);
			this.pEntity.setSetembro(setembro);
			this.pEntity.setOutubro(outubro);
			this.pEntity.setNovembro(novembro);
			this.pEntity.setDezembro(dezembro);

			this.pEntity.setConta(conta);

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
		this.subView = new LancamentoOrcadoFormView(this);

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
		String janeiro = this.subView.getTfJaneiro().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(janeiro)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfJaneiro(), msg);

			return false;
		}

		String fevereiro = this.subView.getTfFevereiro().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(fevereiro)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfFevereiro(), msg);

			return false;
		}

		String marco = this.subView.getTfMarco().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(marco)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfMarco(), msg);

			return false;
		}

		String abril = this.subView.getTfAbril().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(abril)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfAbril(), msg);

			return false;
		}

		String maio = this.subView.getTfMaio().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(maio)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfMaio(), msg);

			return false;
		}

		String junho = this.subView.getTfJunho().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(junho)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfJunho(), msg);

			return false;
		}

		String julho = this.subView.getTfJulho().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(julho)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfJulho(), msg);

			return false;
		}

		String agosto = this.subView.getTfAgosto().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(agosto)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfAgosto(), msg);

			return false;
		}

		String setembro = this.subView.getTfSetembro().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(setembro)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfSetembro(), msg);

			return false;
		}

		String outubro = this.subView.getTfOutubro().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(outubro)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfOutubro(), msg);

			return false;
		}

		String novembro = this.subView.getTfNovembro().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(novembro)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfNovembro(), msg);

			return false;
		}

		String dezembro = this.subView.getTfDezembro().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(dezembro)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfDezembro(), msg);

			return false;
		}

		/** REQUIRED */

		ContaEntity conta = this.subView.getCbConta().getValue();

		if (!ObjectValidator.validateObject(conta)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getCbConta(), msg);

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
			DefaultManyToOneComboModel<ContaEntity> model = new DefaultManyToOneComboModel<ContaEntity>(
					ContaListController.class, this.cDAO,
					super.getMainController());

			this.subView.getCbConta().setModel(model);
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
				this.pEntity = new LancamentoOrcadoEntity();

				// this.subView.getCbConta().setValue(this.pEntity.getConta());
			} else {
				this.pEntity = this.pDAO.find(id);

				this.subView.getCbConta().setValue(this.pEntity.getConta());
			}

			this.subView.getTfAno().setValue(this.pEntity.getAno());
			this.subView.getTfJaneiro().setValue(
					this.pEntity.getJaneiro().toString());
			this.subView.getTfFevereiro().setValue(
					this.pEntity.getFevereiro().toString());
			this.subView.getTfMarco().setValue(
					this.pEntity.getMarco().toString());
			this.subView.getTfAbril().setValue(
					this.pEntity.getAbril().toString());
			this.subView.getTfMaio()
					.setValue(this.pEntity.getMaio().toString());
			this.subView.getTfJunho().setValue(
					this.pEntity.getJunho().toString());
			this.subView.getTfJulho().setValue(
					this.pEntity.getJulho().toString());
			this.subView.getTfAgosto().setValue(
					this.pEntity.getAgosto().toString());
			this.subView.getTfSetembro().setValue(
					this.pEntity.getSetembro().toString());
			this.subView.getTfOutubro().setValue(
					this.pEntity.getOutubro().toString());
			this.subView.getTfNovembro().setValue(
					this.pEntity.getNovembro().toString());
			this.subView.getTfDezembro().setValue(
					this.pEntity.getDezembro().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public LancamentoOrcadoEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}