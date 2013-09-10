package dc.controller.folhapagamento.movimento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.validator.Validator;
import dc.entidade.folhapagamento.movimento.HistoricoSalarialEntity;
import dc.entidade.pessoal.Colaborador;
import dc.servicos.dao.folhapagamento.movimento.HistoricoSalarialDAO;
import dc.servicos.dao.pessoal.ColaboradorDAO;
import dc.visao.folhapagamento.movimento.HistoricoSalarialFormView;
import dc.visao.framework.geral.CRUDFormController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class HistoricoSalarialFormController extends
		CRUDFormController<HistoricoSalarialEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HistoricoSalarialFormView subView;

	/**
	 * DAO'S
	 */

	@Autowired
	private HistoricoSalarialDAO pDAO;

	@Autowired
	private ColaboradorDAO cDAO;

	/**
	 * ENTITIES
	 */

	private HistoricoSalarialEntity pEntity;

	/**
	 * CONSTRUTOR
	 */

	public HistoricoSalarialFormController() {
		if (this.pEntity == null) {
			this.pEntity = new HistoricoSalarialEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Histórico salarial";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String competencia = this.subView.getTfCompetencia().getValue();
			Double salarioAtual = Double.parseDouble(this.subView
					.getTfSalarioAtual().getValue());
			Double percentualAumento = Double.parseDouble(this.subView
					.getTfPercentualAumento().getValue());
			Double salarioNovo = Double.parseDouble(this.subView
					.getTfSalarioNovo().getValue());
			String validoAPartir = this.subView.getTfValidoAPartir().getValue();
			String motivo = this.subView.getTfMotivo().getValue();

			Colaborador colaborador = (Colaborador) this.subView
					.getCbColaborador().getValue();

			this.pEntity.setCompetencia(competencia);
			this.pEntity.setSalarioAtual(salarioAtual);
			this.pEntity.setPercentualAumento(percentualAumento);
			this.pEntity.setSalarioNovo(salarioNovo);
			this.pEntity.setValidoAPartir(validoAPartir);
			this.pEntity.setMotivo(motivo);

			this.pEntity.setColaborador(colaborador);

			this.pDAO.saveOrUpdate(this.pEntity);

			mensagemSalvoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			this.pEntity = new HistoricoSalarialEntity();

			this.subView.getTfCompetencia().setValue(
					this.pEntity.getCompetencia());
			this.subView.getTfSalarioAtual().setValue(
					String.valueOf(this.pEntity.getSalarioAtual()));
			this.subView.getTfPercentualAumento().setValue(
					String.valueOf(this.pEntity.getPercentualAumento()));
			this.subView.getTfSalarioNovo().setValue(
					String.valueOf(this.pEntity.getSalarioNovo()));
			this.subView.getTfValidoAPartir().setValue(
					this.pEntity.getValidoAPartir());
			this.subView.getTfMotivo().setValue(this.pEntity.getMotivo());

			this.subView.carregarCmbColaborador(this.colaboradorListarTodos());

			this.subView.getCbColaborador().setValue(
					this.pEntity.getColaborador());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = this.pDAO.find(id);

			this.subView.getTfCompetencia().setValue(
					this.pEntity.getCompetencia());
			this.subView.getTfSalarioAtual().setValue(
					String.valueOf(this.pEntity.getSalarioAtual()));
			this.subView.getTfPercentualAumento().setValue(
					String.valueOf(this.pEntity.getPercentualAumento()));
			this.subView.getTfSalarioNovo().setValue(
					String.valueOf(this.pEntity.getSalarioNovo()));
			this.subView.getTfValidoAPartir().setValue(
					this.pEntity.getValidoAPartir());
			this.subView.getTfMotivo().setValue(this.pEntity.getMotivo());

			this.subView.carregarCmbColaborador(this.colaboradorListarTodos());

			this.subView.getCbColaborador().setValue(
					this.pEntity.getColaborador());
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
			this.pEntity = new HistoricoSalarialEntity();

			this.subView.getTfCompetencia().setValue(
					this.pEntity.getCompetencia());
			this.subView.getTfSalarioAtual().setValue(
					String.valueOf(this.pEntity.getSalarioAtual()));
			this.subView.getTfPercentualAumento().setValue(
					String.valueOf(this.pEntity.getPercentualAumento()));
			this.subView.getTfSalarioNovo().setValue(
					String.valueOf(this.pEntity.getSalarioNovo()));
			this.subView.getTfValidoAPartir().setValue(
					this.pEntity.getValidoAPartir());
			this.subView.getTfMotivo().setValue(this.pEntity.getMotivo());

			this.subView.carregarCmbColaborador(this.colaboradorListarTodos());

			this.subView.getCbColaborador().setValue(
					this.pEntity.getColaborador());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new HistoricoSalarialFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.pEntity = new HistoricoSalarialEntity();

			this.subView.getTfCompetencia().setValue(
					this.pEntity.getCompetencia());
			this.subView.getTfSalarioAtual().setValue(
					String.valueOf(this.pEntity.getSalarioAtual()));
			this.subView.getTfPercentualAumento().setValue(
					String.valueOf(this.pEntity.getPercentualAumento()));
			this.subView.getTfSalarioNovo().setValue(
					String.valueOf(this.pEntity.getSalarioNovo()));
			this.subView.getTfValidoAPartir().setValue(
					this.pEntity.getValidoAPartir());
			this.subView.getTfMotivo().setValue(this.pEntity.getMotivo());

			this.subView.carregarCmbColaborador(this.colaboradorListarTodos());

			this.subView.getCbColaborador().setValue(
					this.pEntity.getColaborador());
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
		String salarioAtual = this.subView.getTfSalarioAtual().getValue();

		if (!Validator.validateNotRequiredNumber(salarioAtual)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfSalarioAtual(), msg);

			return false;
		}

		String percentualAumento = this.subView.getTfPercentualAumento()
				.getValue();

		if (!Validator.validateNotRequiredNumber(percentualAumento)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfPercentualAumento(), msg);

			return false;
		}

		String salarioNovo = this.subView.getTfSalarioNovo().getValue();

		if (!Validator.validateNotRequiredNumber(salarioNovo)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfSalarioNovo(), msg);

			return false;
		}

		/**
		 * REQUIRED
		 */

		Colaborador colaborador = (Colaborador) this.subView.getCbColaborador()
				.getValue();

		if (!Validator.validateObject(colaborador)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getCbColaborador(), msg);

			return false;
		}

		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "folhapagamento_movimento_historico_salarial_fc";
	}

	/**
	 * COMBOS
	 */

	public List<Colaborador> colaboradorListarTodos() {
		List<Colaborador> auxLista = new ArrayList<Colaborador>();

		auxLista = this.cDAO.colaboradorLista();

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