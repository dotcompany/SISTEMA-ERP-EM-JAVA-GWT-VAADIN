package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.enums.SimNaoEn;
import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.AtividadeForCliEntity;
import dc.entidade.geral.pessoal.FornecedorEntity;
import dc.entidade.geral.pessoal.PessoaEntity;
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

	/**
	 * ENTITY
	 */

	private FornecedorEntity entity;

	/**
	 * BUSINESS
	 */

	/**
	 * DAO
	 */

	@Autowired
	private FornecedorDAO dao;

	@Autowired
	private AtividadeForCliDAO atividadeForCliDAO;

	@Autowired
	private ContabilContaDAO contabilContaDAO;

	@Autowired
	private SituacaoForCliDAO situacaoForCliDAO;

	@Autowired
	private PessoaDAO pessoaDAO;

	/**
	 * CONSTRUTOR
	 */

	public FornecedorFormController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getNome() {
		return "Fornecedor";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	public FornecedorEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new FornecedorFormView(this);

			DefaultManyToOneComboModel<AtividadeForCliEntity> atividadeForCliModel = new DefaultManyToOneComboModel<AtividadeForCliEntity>(
					AtividadeForCliListController.class,
					this.atividadeForCliDAO, super.getMainController());

			this.subView.getMocAtividadeForCli().setModel(atividadeForCliModel);

			DefaultManyToOneComboModel<SituacaoForCliEntity> situacaoForCliModel = new DefaultManyToOneComboModel<SituacaoForCliEntity>(
					SituacaoForCliListController.class, this.situacaoForCliDAO,
					super.getMainController());

			this.subView.getMocSituacaoForCli().setModel(situacaoForCliModel);

			DefaultManyToOneComboModel<PessoaEntity> pessoaModel = new DefaultManyToOneComboModel<PessoaEntity>(
					PessoaListController.class, this.pessoaDAO,
					super.getMainController());

			this.subView.getMocPessoa().setModel(pessoaModel);

			for (SimNaoEn value : SimNaoEn.values()) {
				this.subView.getCbSofreRentencao().addItem(value);
				this.subView.getCbGerarFaturamento().addItem(value);
				this.subView.getCbOptanteSimples().addItem(value);
			}

			for (Localizacao value : Localizacao.values()) {
				this.subView.getCbLocalizacao().addItem(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateObject(subView.getMocPessoa().getValue())) {
			adicionarErroDeValidacao(subView.getMocPessoa(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getMocAtividadeForCli()
				.getValue())) {
			adicionarErroDeValidacao(subView.getMocAtividadeForCli(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator
				.validateObject(subView.getMocSituacaoForCli().getValue())) {
			adicionarErroDeValidacao(subView.getMocSituacaoForCli(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getPdfDesde().getValue())) {
			adicionarErroDeValidacao(subView.getPdfDesde(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTfContaRemetente().getValue())) {
			adicionarErroDeValidacao(subView.getTfContaRemetente(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTfChequeNominalA().getValue())) {
			adicionarErroDeValidacao(subView.getTfChequeNominalA(),
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

		if (!Validator.validateNumber(subView.getTfNumDiasIntervalo()
				.getValue())) {
			adicionarErroDeValidacao(subView.getTfNumDiasIntervalo(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTfPrazoMedioEntrega()
				.getValue())) {
			adicionarErroDeValidacao(subView.getTfPrazoMedioEntrega(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTfNumDiasPrimeiroVenc()
				.getValue())) {
			adicionarErroDeValidacao(subView.getTfNumDiasPrimeiroVenc(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTfQuantidadesParcelas()
				.getValue())) {
			adicionarErroDeValidacao(subView.getTfQuantidadesParcelas(),
					"Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void actionSalvar() {
		try {
			this.subView.preencheBean(this.entity);

			this.entity.setEmpresa(SecuritySessionProvider.getUsuario()
					.getConta().getEmpresa());

			this.dao.saveOrUpdate(this.entity);

			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.entity = this.dao.find(id);

			this.subView.preencheForm(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new FornecedorEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void quandoNovo() {
		try {
			this.entity = new FornecedorEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.dao.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		// TODO Auto-generated method stub

	}

}