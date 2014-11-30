package dc.controller.geral;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.controller.contabilidade.ContabilContaListController;
import dc.controller.geral.pessoal.AtividadeForCliListController;
import dc.controller.geral.pessoal.PessoaListController;
import dc.controller.geral.pessoal.SituacaoForCliListController;
import dc.entidade.contabilidade.ContabilConta;
import dc.entidade.geral.FornecedorEntity;
import dc.entidade.geral.PessoaEntity;
import dc.entidade.geral.pessoal.AtividadeForCliEntity;
import dc.entidade.geral.pessoal.SituacaoForCliEntity;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.servicos.dao.geral.FornecedorDAO;
import dc.servicos.dao.geral.pessoal.AtividadeForCliDAO;
import dc.servicos.dao.geral.pessoal.PessoaDAO;
import dc.servicos.dao.geral.pessoal.SituacaoForCliDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.FornecedorFormView;
import dc.visao.geral.FornecedorFormView.Localizacao;
import dc.visao.geral.FornecedorFormView.SimNao;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class FornecedorFormController extends
		CRUDFormController<FornecedorEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FornecedorFormView subView;

	@Autowired
	private FornecedorDAO fornecedorDAO;

	private FornecedorEntity currentBean;

	@Autowired
	private AtividadeForCliDAO atividadeForCliDAO;

	@Autowired
	private ContabilContaDAO contabilContaDAO;

	@Autowired
	private SituacaoForCliDAO situacaoForCliDAO;

	@Autowired
	private PessoaDAO pessoaDAO;

	@Override
	protected String getNome() {
		return "Fornecedor";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		subView.preencheBean(currentBean);

		try {
			currentBean.setEmpresa(SecuritySessionProvider.getUsuario()
					.getConta().getEmpresa());
			fornecedorDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = fornecedorDAO.find(id);
		subView.preencheForm(currentBean);
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padr�o
	 */
	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void initSubView() {
		subView = new FornecedorFormView();

		carregarCombos();
	}

	private void carregarCombos() {
		DefaultManyToOneComboModel<AtividadeForCliEntity> atividadeModel = new DefaultManyToOneComboModel<AtividadeForCliEntity>(
				AtividadeForCliListController.class, this.atividadeForCliDAO,
				super.getMainController());

		DefaultManyToOneComboModel<SituacaoForCliEntity> situacaoModel = new DefaultManyToOneComboModel<SituacaoForCliEntity>(
				SituacaoForCliListController.class, this.situacaoForCliDAO,
				super.getMainController());

		DefaultManyToOneComboModel<ContabilConta> contabilContaModel = new DefaultManyToOneComboModel<ContabilConta>(
				ContabilContaListController.class, this.contabilContaDAO,
				super.getMainController()) {

			@Override
			public String getCaptionProperty() {
				return "codigoReduzido";
			}

		};

		DefaultManyToOneComboModel<PessoaEntity> pessoaModel = new DefaultManyToOneComboModel<PessoaEntity>(
				PessoaListController.class, this.pessoaDAO,
				super.getMainController());

		subView.getCbAtividade().setModel(atividadeModel);
		subView.getCbSituacao().setModel(situacaoModel);
		subView.getCbContabilConta().setModel(contabilContaModel);
		subView.getCbPessoa().setModel(pessoaModel);

		for (SimNao value : SimNao.values()) {
			subView.getCbSofreRentencao().addItem(value);
			subView.getCbGerarFaturamento().addItem(value);
			subView.getCbOptanteSimples().addItem(value);
		}

		for (Localizacao value : Localizacao.values()) {
			subView.getCbLocalizacao().addItem(value);
		}
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new FornecedorEntity();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		fornecedorDAO.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateObject(subView.getCbPessoa().getValue())) {
			adicionarErroDeValidacao(subView.getCbPessoa(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCbAtividade().getValue())) {
			adicionarErroDeValidacao(subView.getCbAtividade(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCbSituacao().getValue())) {
			adicionarErroDeValidacao(subView.getCbSituacao(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCbContabilConta().getValue())) {
			adicionarErroDeValidacao(subView.getCbContabilConta(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getDtDesde().getValue())) {
			adicionarErroDeValidacao(subView.getDtDesde(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxContaRemetente().getValue())) {
			adicionarErroDeValidacao(subView.getTxContaRemetente(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxChequeNominalA().getValue())) {
			adicionarErroDeValidacao(subView.getTxChequeNominalA(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCbGerarFaturamento()
				.getValue())) {
			adicionarErroDeValidacao(subView.getCbGerarFaturamento(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCbLocalizacao().getValue())) {
			adicionarErroDeValidacao(subView.getCbLocalizacao(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCbOptanteSimples().getValue())) {
			adicionarErroDeValidacao(subView.getCbOptanteSimples(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCbSofreRentencao().getValue())) {
			adicionarErroDeValidacao(subView.getCbSofreRentencao(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTxNumDiasIntervalo()
				.getValue())) {
			adicionarErroDeValidacao(subView.getTxNumDiasIntervalo(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTxPrazoMedioEntrega()
				.getValue())) {
			adicionarErroDeValidacao(subView.getTxPrazoMedioEntrega(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTxNumDiasPrimeiroVenc()
				.getValue())) {
			adicionarErroDeValidacao(subView.getTxNumDiasPrimeiroVenc(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTxQuantidadesParcelas()
				.getValue())) {
			adicionarErroDeValidacao(subView.getTxQuantidadesParcelas(),
					"Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public FornecedorEntity getModelBean() {
		return currentBean;
	}

}