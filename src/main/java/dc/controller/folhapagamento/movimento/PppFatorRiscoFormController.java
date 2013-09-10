package dc.controller.folhapagamento.movimento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.validator.Validator;
import dc.entidade.folhapagamento.movimento.PppEntity;
import dc.entidade.folhapagamento.movimento.PppFatorRiscoEntity;
import dc.servicos.dao.folhapagamento.movimento.PppDAO;
import dc.servicos.dao.folhapagamento.movimento.PppFatorRiscoDAO;
import dc.visao.folhapagamento.movimento.PppFatorRiscoFormView;
import dc.visao.framework.geral.CRUDFormController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class PppFatorRiscoFormController extends
		CRUDFormController<PppFatorRiscoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PppFatorRiscoFormView subView;

	/**
	 * DAO'S
	 */

	@Autowired
	private PppFatorRiscoDAO pDAO;

	@Autowired
	private PppDAO pppDAO;

	/**
	 * ENTITIES
	 */

	private PppFatorRiscoEntity pEntity;

	/**
	 * CONSTRUTOR
	 */

	public PppFatorRiscoFormController() {
		if (this.pEntity == null) {
			this.pEntity = new PppFatorRiscoEntity();
		}
	}

	@Override
	protected String getNome() {
		return "PPP Fator De Risco";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			Date dataInicio = this.subView.getPdfDataInicio().getValue();
			Date dataTermino = this.subView.getPdfDataTermino().getValue();
			String tipo = this.subView.getTfTipo().getValue();
			String fatorRisco = this.subView.getTfFatorRisco().getValue();
			String intensidade = this.subView.getTfIntensidade().getValue();
			String tecnicaUtilizada = this.subView.getTfTecnicaUtilizada()
					.getValue();
			String epcEficaz = this.subView.getTfEpcEficaz().getValue();
			String epiEficaz = this.subView.getTfEpiEficaz().getValue();
			Integer caEpi = Integer.parseInt(this.subView.getTfCaEpi()
					.getValue());
			String atendimentoNr061 = this.subView.getTfAtendimentoNr061()
					.getValue();
			String atendimentoNr062 = this.subView.getTfAtendimentoNr062()
					.getValue();
			String atendimentoNr063 = this.subView.getTfAtendimentoNr063()
					.getValue();
			String atendimentoNr064 = this.subView.getTfAtendimentoNr064()
					.getValue();
			String atendimentoNr065 = this.subView.getTfAtendimentoNr065()
					.getValue();

			PppEntity ppp = (PppEntity) this.subView.getCbPpp().getValue();

			this.pEntity.setDataInicio(dataInicio);
			this.pEntity.setDataTermino(dataTermino);
			this.pEntity.setTipo(tipo);
			this.pEntity.setFatorRisco(fatorRisco);
			this.pEntity.setIntensidade(intensidade);
			this.pEntity.setTecnicaUtilizada(tecnicaUtilizada);
			this.pEntity.setEpcEficaz(epcEficaz);
			this.pEntity.setEpiEficaz(epiEficaz);
			this.pEntity.setCaEpi(caEpi);
			this.pEntity.setAtendimentoNr061(atendimentoNr061);
			this.pEntity.setAtendimentoNr062(atendimentoNr062);
			this.pEntity.setAtendimentoNr063(atendimentoNr063);
			this.pEntity.setAtendimentoNr064(atendimentoNr064);
			this.pEntity.setAtendimentoNr065(atendimentoNr065);

			this.pEntity.setPpp(ppp);

			this.pDAO.saveOrUpdate(this.pEntity);

			mensagemSalvoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			this.pEntity = new PppFatorRiscoEntity();

			this.subView.getPdfDataInicio().setValue(
					this.pEntity.getDataInicio());
			this.subView.getPdfDataTermino().setValue(
					this.pEntity.getDataTermino());
			this.subView.getTfTipo().setValue(this.pEntity.getTipo());
			this.subView.getTfFatorRisco().setValue(
					this.pEntity.getFatorRisco());
			this.subView.getTfIntensidade().setValue(
					this.pEntity.getIntensidade());
			this.subView.getTfTecnicaUtilizada().setValue(
					this.pEntity.getTecnicaUtilizada());
			this.subView.getTfEpcEficaz().setValue(this.pEntity.getEpcEficaz());
			this.subView.getTfEpiEficaz().setValue(this.pEntity.getEpiEficaz());
			this.subView.getTfCaEpi().setValue(
					String.valueOf(this.pEntity.getCaEpi()));
			this.subView.getTfAtendimentoNr061().setValue(
					this.pEntity.getAtendimentoNr061());
			this.subView.getTfAtendimentoNr062().setValue(
					this.pEntity.getAtendimentoNr062());
			this.subView.getTfAtendimentoNr063().setValue(
					this.pEntity.getAtendimentoNr063());
			this.subView.getTfAtendimentoNr064().setValue(
					this.pEntity.getAtendimentoNr064());
			this.subView.getTfAtendimentoNr065().setValue(
					this.pEntity.getAtendimentoNr065());

			this.subView.carregarCmbPpp(this.pppListarTodos());

			this.subView.getCbPpp().setValue(this.pEntity.getPpp());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = this.pDAO.find(id);

			this.subView.getPdfDataInicio().setValue(
					this.pEntity.getDataInicio());
			this.subView.getPdfDataTermino().setValue(
					this.pEntity.getDataTermino());
			this.subView.getTfTipo().setValue(this.pEntity.getTipo());
			this.subView.getTfFatorRisco().setValue(
					this.pEntity.getFatorRisco());
			this.subView.getTfIntensidade().setValue(
					this.pEntity.getIntensidade());
			this.subView.getTfTecnicaUtilizada().setValue(
					this.pEntity.getTecnicaUtilizada());
			this.subView.getTfEpcEficaz().setValue(this.pEntity.getEpcEficaz());
			this.subView.getTfEpiEficaz().setValue(this.pEntity.getEpiEficaz());
			this.subView.getTfCaEpi().setValue(
					String.valueOf(this.pEntity.getCaEpi()));
			this.subView.getTfAtendimentoNr061().setValue(
					this.pEntity.getAtendimentoNr061());
			this.subView.getTfAtendimentoNr062().setValue(
					this.pEntity.getAtendimentoNr062());
			this.subView.getTfAtendimentoNr063().setValue(
					this.pEntity.getAtendimentoNr063());
			this.subView.getTfAtendimentoNr064().setValue(
					this.pEntity.getAtendimentoNr064());
			this.subView.getTfAtendimentoNr065().setValue(
					this.pEntity.getAtendimentoNr065());

			this.subView.carregarCmbPpp(this.pppListarTodos());

			this.subView.getCbPpp().setValue(this.pEntity.getPpp());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa a��o aqui. Ou então deixar em branco, para comportamento padr�o
	 */
	@Override
	protected void quandoNovo() {
		try {
			this.pEntity = new PppFatorRiscoEntity();

			if (this.pppDAO == null) {
				this.pppDAO = new PppDAO();
			}

			this.subView.getPdfDataInicio().setValue(
					this.pEntity.getDataInicio());
			this.subView.getPdfDataTermino().setValue(
					this.pEntity.getDataTermino());
			this.subView.getTfTipo().setValue(this.pEntity.getTipo());
			this.subView.getTfFatorRisco().setValue(
					this.pEntity.getFatorRisco());
			this.subView.getTfIntensidade().setValue(
					this.pEntity.getIntensidade());
			this.subView.getTfTecnicaUtilizada().setValue(
					this.pEntity.getTecnicaUtilizada());
			this.subView.getTfEpcEficaz().setValue(this.pEntity.getEpcEficaz());
			this.subView.getTfEpiEficaz().setValue(this.pEntity.getEpiEficaz());
			this.subView.getTfCaEpi().setValue(
					String.valueOf(this.pEntity.getCaEpi()));
			this.subView.getTfAtendimentoNr061().setValue(
					this.pEntity.getAtendimentoNr061());
			this.subView.getTfAtendimentoNr062().setValue(
					this.pEntity.getAtendimentoNr062());
			this.subView.getTfAtendimentoNr063().setValue(
					this.pEntity.getAtendimentoNr063());
			this.subView.getTfAtendimentoNr064().setValue(
					this.pEntity.getAtendimentoNr064());
			this.subView.getTfAtendimentoNr065().setValue(
					this.pEntity.getAtendimentoNr065());

			this.subView.carregarCmbPpp(this.pppListarTodos());

			this.subView.getCbPpp().setValue(this.pEntity.getPpp());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new PppFatorRiscoFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.pEntity = new PppFatorRiscoEntity();

			if (this.pppDAO == null) {
				this.pppDAO = new PppDAO();
			}

			this.subView.getPdfDataInicio().setValue(
					this.pEntity.getDataInicio());
			this.subView.getPdfDataTermino().setValue(
					this.pEntity.getDataTermino());
			this.subView.getTfTipo().setValue(this.pEntity.getTipo());
			this.subView.getTfFatorRisco().setValue(
					this.pEntity.getFatorRisco());
			this.subView.getTfIntensidade().setValue(
					this.pEntity.getIntensidade());
			this.subView.getTfTecnicaUtilizada().setValue(
					this.pEntity.getTecnicaUtilizada());
			this.subView.getTfEpcEficaz().setValue(this.pEntity.getEpcEficaz());
			this.subView.getTfEpiEficaz().setValue(this.pEntity.getEpiEficaz());
			this.subView.getTfCaEpi().setValue(
					String.valueOf(this.pEntity.getCaEpi()));
			this.subView.getTfAtendimentoNr061().setValue(
					this.pEntity.getAtendimentoNr061());
			this.subView.getTfAtendimentoNr062().setValue(
					this.pEntity.getAtendimentoNr062());
			this.subView.getTfAtendimentoNr063().setValue(
					this.pEntity.getAtendimentoNr063());
			this.subView.getTfAtendimentoNr064().setValue(
					this.pEntity.getAtendimentoNr064());
			this.subView.getTfAtendimentoNr065().setValue(
					this.pEntity.getAtendimentoNr065());

			this.subView.carregarCmbPpp(this.pppListarTodos());

			this.subView.getCbPpp().setValue(this.pEntity.getPpp());
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
		Object dataInicio = this.subView.getPdfDataInicio().getValue();

		if (!Validator.validateNotRequiredDate(dataInicio)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataInicio(), msg);

			return false;
		}

		Object dataTermino = this.subView.getPdfDataTermino().getValue();

		if (!Validator.validateNotRequiredDate(dataTermino)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfDataTermino(), msg);

			return false;
		}

		String caEpi = this.subView.getTfCaEpi().getValue();

		if (!Validator.validateNotRequiredNumber(caEpi)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfCaEpi(), msg);

			return false;
		}

		PppEntity ppp = (PppEntity) this.subView.getCbPpp().getValue();

		if (!Validator.validateObject(ppp)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getCbPpp(), msg);

			return false;
		}

		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "folhapagamento_movimento_ppp_fator_risco_fc";
	}

	/**
	 * COMBOS
	 */

	public List<PppEntity> pppListarTodos() {
		List<PppEntity> auxLista = new ArrayList<PppEntity>();

		auxLista = this.pppDAO.listarTodos();

		return auxLista;
	}

	/**
	 * **************************************
	 */

	@Override
	protected boolean isFullSized() {
		return true;
	}

}