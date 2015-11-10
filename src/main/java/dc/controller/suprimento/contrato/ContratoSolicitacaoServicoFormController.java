package dc.controller.suprimento.contrato;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.controller.geral.diverso.SetorListController;
import dc.controller.geral.pessoal.ClienteListController;
import dc.controller.geral.pessoal.ColaboradorListController;
import dc.controller.geral.pessoal.FornecedorListController;
import dc.entidade.geral.diverso.SetorEntity;
import dc.entidade.geral.pessoal.ClienteEntity;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.entidade.geral.pessoal.FornecedorEntity;
import dc.entidade.suprimentos.contrato.SolicitacaoServicoEntity;
import dc.entidade.suprimentos.contrato.TipoServicoEntity;
import dc.model.dao.geral.diverso.ISetorDAO;
import dc.model.dao.geral.pessoal.IClienteDAO;
import dc.model.dao.geral.pessoal.IColaboradorDAO;
import dc.servicos.dao.geral.IFornecedorDAO;
import dc.servicos.dao.suprimentos.contrato.ISolicitacaoServicoDAO;
import dc.servicos.dao.suprimentos.contrato.ITipoServicoDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;
import dc.visao.suprimento.contrato.ContratoSolicitacaoServicoFormView;
import dc.visao.suprimento.contrato.ContratoSolicitacaoServicoFormView.ResponsavelEnum;

@Controller
@Scope("prototype")
public class ContratoSolicitacaoServicoFormController extends
		CRUDFormController<SolicitacaoServicoEntity> {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ContratoSolicitacaoServicoFormView subView;

	@Autowired
	private ISolicitacaoServicoDAO contratoSolicitacaoServicoDAO;

	@Autowired
	private IFornecedorDAO fornecedorDAO;
	@Autowired
	private ISetorDAO setorDAO;
	@Autowired
	private ITipoServicoDAO contratoTipoServicoDAO;
	@Autowired
	private IColaboradorDAO colaboradorDAO;
	@Autowired
	private IClienteDAO clienteDAO;

	private SolicitacaoServicoEntity currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if(subView.getGrpTipo().getValue().equals(ResponsavelEnum.FORNECEDOR)) {
			if (!Validator.validateObject(subView.getCbFornecedor().getValue())) {
				adicionarErroDeValidacao(subView.getCbFornecedor(),
						"Não pode ficar em branco");
				valido = false;
			}	
		}
		else{
			if (!Validator.validateObject(subView.getCbCliente().getValue())) {
				adicionarErroDeValidacao(subView.getCbCliente(),
						"Não pode ficar em branco");
				valido = false;
			}
		}
		

		if (!Validator.validateObject(subView.getCbSetor().getValue())) {
			adicionarErroDeValidacao(subView.getCbSetor(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCbColaborador().getValue())) {
			adicionarErroDeValidacao(subView.getCbColaborador(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCbTipoServico().getValue())) {
			adicionarErroDeValidacao(subView.getCbTipoServico(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getDtSolicitacao().getValue())) {
			adicionarErroDeValidacao(subView.getDtSolicitacao(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getDtDesejadaInicio().getValue())) {
			adicionarErroDeValidacao(subView.getDtDesejadaInicio(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCmbUrgente().getValue())) {
			adicionarErroDeValidacao(subView.getCmbUrgente(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxaDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTxaDescricao(),
					"Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new SolicitacaoServicoEntity();
	}

	private void carregarCombosView() {
		DefaultManyToOneComboModel<FornecedorEntity> fornecedorModel = new DefaultManyToOneComboModel<FornecedorEntity>(
				FornecedorListController.class, this.fornecedorDAO,
				super.getMainController()) {

			@Override
			public String getCaptionProperty() {
				return "pessoa.nome";
			}

		};

		DefaultManyToOneComboModel<TipoServicoEntity> contratoTipoServicoModel = new DefaultManyToOneComboModel<TipoServicoEntity>(
				ContratoTipoServicoListController.class,
				this.contratoTipoServicoDAO, super.getMainController());

		DefaultManyToOneComboModel<ColaboradorEntity> colaboradorModel = new DefaultManyToOneComboModel<ColaboradorEntity>(
				ColaboradorListController.class, this.colaboradorDAO,
				super.getMainController()) {

			@Override
			public String getCaptionProperty() {
				return "pessoa.nome";
			}

		};

		DefaultManyToOneComboModel<SetorEntity> setorModel = new DefaultManyToOneComboModel<SetorEntity>(
				SetorListController.class, this.setorDAO,
				super.getMainController());

		DefaultManyToOneComboModel<ClienteEntity> clienteModel = new DefaultManyToOneComboModel<ClienteEntity>(
				ClienteListController.class, this.clienteDAO,
				super.getMainController()) {

			@Override
			public String getCaptionProperty() {
				return "pessoa.nome";
			}

		};

		subView.getCbFornecedor().setModel(fornecedorModel);
		subView.getCbCliente().setModel(clienteModel);
		subView.getCbColaborador().setModel(colaboradorModel);
		subView.getCbSetor().setModel(setorModel);
		subView.getCbTipoServico().setModel(contratoTipoServicoModel);

		subView.carregarStatusSituacao();
		subView.carregarUrgente();
	}

	@Override
	protected void initSubView() {
		subView = new ContratoSolicitacaoServicoFormView();
		carregarCombosView();
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = contratoSolicitacaoServicoDAO.find(id);
		subView.carregarView(currentBean);
	}

	@Override
	protected void actionSalvar() {
		subView.preencherObjeto(currentBean);
		currentBean.setEmpresa(SecuritySessionProvider.getUsuario().getConta()
				.getEmpresa());
		contratoSolicitacaoServicoDAO.saveOrUpdate(currentBean);

		notifiyFrameworkSaveOK(this.currentBean);
	}

	@Override
	protected void quandoNovo() {

	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Solicitação de serviço";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		contratoSolicitacaoServicoDAO.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
			for (Serializable id : ids) {
				SolicitacaoServicoEntity sol = (SolicitacaoServicoEntity) id;

				try {
					contratoSolicitacaoServicoDAO.delete(sol);
				} catch (Exception e) {
					e.printStackTrace();
					mensagemErro(e.getMessage());
				}
			}
			
			mensagemRemovidoOK();
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean isFullSized() {
		return true;
	}

	@Override
	public SolicitacaoServicoEntity getModelBean() {
		return currentBean;
	}

}