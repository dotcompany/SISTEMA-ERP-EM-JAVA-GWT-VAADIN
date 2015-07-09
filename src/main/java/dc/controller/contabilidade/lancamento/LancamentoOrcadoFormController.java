package dc.controller.contabilidade.lancamento;

import java.io.Serializable;
import java.math.BigDecimal;
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
import dc.servicos.util.Validator;
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
			if(subView.getTfJaneiro()!=null){
				String janeiro = subView.getTfJaneiro().getValue();
				if (Validator.validateString(janeiro)) {
					janeiro = formataBigDecimal(janeiro);
					this.pEntity.setJaneiro(new BigDecimal(janeiro));
				}
			}
			if(subView.getTfFevereiro()!=null){
				String fevereiro = subView.getTfFevereiro().getValue();
				if (Validator.validateString(fevereiro)) {
					fevereiro = formataBigDecimal(fevereiro);
					this.pEntity.setFevereiro(new BigDecimal(fevereiro));
				}
			}
			if(subView.getTfMarco()!=null){
				String marco = subView.getTfMarco().getValue();
				if (Validator.validateString(marco)) {
					marco = formataBigDecimal(marco);
					this.pEntity.setMarco(new BigDecimal(marco));
				}
			}
			if(subView.getTfAbril()!=null){
				String abril = subView.getTfAbril().getValue();
				if (Validator.validateString(abril)) {
					abril = formataBigDecimal(abril);
					this.pEntity.setAbril(new BigDecimal(abril));
				}
			}
			if(subView.getTfMaio()!=null){
				String maio = subView.getTfMaio().getValue();
				if (Validator.validateString(maio)) {
					maio = formataBigDecimal(maio);
					this.pEntity.setMaio(new BigDecimal(maio));
				}
			}
			if(subView.getTfJunho()!=null){
				String junho = subView.getTfJunho().getValue();
				if (Validator.validateString(junho)) {
					junho = formataBigDecimal(junho);
					this.pEntity.setJunho(new BigDecimal(junho));
				}
			}
			if(subView.getTfJulho()!=null){
				String julho = subView.getTfJulho().getValue();
				if (Validator.validateString(julho)) {
					julho = formataBigDecimal(julho);
					this.pEntity.setJulho(new BigDecimal(julho));
				}
			}
			
			if(subView.getTfAgosto()!=null){
				String agosto = subView.getTfAgosto().getValue();
				if (Validator.validateString(agosto)) {
					agosto = formataBigDecimal(agosto);
					this.pEntity.setAgosto(new BigDecimal(agosto));
				}
			}
			if(subView.getTfSetembro()!=null){
				String setembro = subView.getTfSetembro().getValue();
				if (Validator.validateString(setembro)) {
					setembro = formataBigDecimal(setembro);
					this.pEntity.setSetembro(new BigDecimal(setembro));
				}
			}
			if(subView.getTfOutubro()!=null){
				String outubro = subView.getTfOutubro().getValue();
				if (Validator.validateString(outubro)) {
					outubro = formataBigDecimal(outubro);
					this.pEntity.setOutubro(new BigDecimal(outubro));
				}
			}
			if(subView.getTfNovembro()!=null){
				String novembro = subView.getTfNovembro().getValue();
				if (Validator.validateString(novembro)) {
					novembro = formataBigDecimal(novembro);
					this.pEntity.setNovembro(new BigDecimal(novembro));
				}
			}
			if(subView.getTfDezembro()!=null){
				String dezembro = subView.getTfDezembro().getValue();
				if (Validator.validateString(dezembro)) {
					dezembro = formataBigDecimal(dezembro);
					this.pEntity.setDezembro(new BigDecimal(dezembro));
				}
			}

			ContaEntity conta = this.subView.getCbConta().getValue();

			//this.pEntity.setAno(ano);

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
	
	public String formataBigDecimal(String valor) {
		String format = "";
		format = valor.replace(".", "").replace(",", ".");
		return format;
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

		    //this.subView.getTfAno().setValue(this.pEntity.getAno());
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