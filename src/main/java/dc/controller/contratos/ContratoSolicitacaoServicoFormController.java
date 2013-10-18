package dc.controller.contratos;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.contratos.ContratoSolicitacaoServico;
import dc.entidade.contratos.ContratoTipoServico;
import dc.entidade.diversos.Setor;
import dc.entidade.geral.Fornecedor;
import dc.entidade.pessoal.Cliente;
import dc.entidade.pessoal.Colaborador;
import dc.servicos.dao.contratos.ContratoSolicitacaoServicoDAO;
import dc.servicos.dao.contratos.ContratoTipoServicoDAO;
import dc.servicos.dao.diversos.SetorDAO;
import dc.servicos.dao.geral.FornecedorDAO;
import dc.servicos.dao.pessoal.ClienteDAO;
import dc.servicos.dao.pessoal.ColaboradorDAO;
import dc.servicos.util.Validator;
import dc.visao.contratos.ContratoSolicitacaoServicoFormView;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class ContratoSolicitacaoServicoFormController extends CRUDFormController<ContratoSolicitacaoServico> {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ContratoSolicitacaoServicoFormView subView;

	@Autowired
	private ContratoSolicitacaoServicoDAO contratoSolicitacaoServicoDAO;

	@Autowired
	private FornecedorDAO fornecedorDAO;
	@Autowired
	private SetorDAO setorDAO;
	@Autowired
	private ContratoTipoServicoDAO contratoTipoServicoDAO;
	@Autowired
	private ColaboradorDAO colaboradorDAO;
	@Autowired
	private ClienteDAO clienteDAO;

	private ContratoSolicitacaoServico currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateObject(subView.getCmbCliente().getValue())) {
			adicionarErroDeValidacao(subView.getCmbCliente(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCmbFornecedor().getValue())) {
			adicionarErroDeValidacao(subView.getCmbFornecedor(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCmbSetor().getValue())) {
			adicionarErroDeValidacao(subView.getCmbSetor(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCmbColaborador().getValue())) {
			adicionarErroDeValidacao(subView.getCmbColaborador(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCmbTipoServico().getValue())) {
			adicionarErroDeValidacao(subView.getCmbTipoServico(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getDtSolicitacao().getValue())) {
			adicionarErroDeValidacao(subView.getDtSolicitacao(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getDtDesejadaInicio().getValue())) {
			adicionarErroDeValidacao(subView.getDtDesejadaInicio(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCmbUrgente().getValue())) {
			adicionarErroDeValidacao(subView.getCmbUrgente(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxaDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTxaDescricao(), "Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new ContratoSolicitacaoServico();
		carregarCombosView();

	}

	private void carregarCombosView() {
		subView.carregarClientes(clienteDAO.getAll(Cliente.class));
		subView.carregarColaboradores(colaboradorDAO.getAll(Colaborador.class));
		subView.carregarFornecedores(fornecedorDAO.getAll(Fornecedor.class));
		subView.carregarSetores(setorDAO.getAll(Setor.class));
		subView.carregarTipoServicos(contratoTipoServicoDAO.getAll(ContratoTipoServico.class));
		subView.carregarStatusSituacao();
		subView.carregarUrgente();
	}

	@Override
	protected void initSubView() {
		subView = new ContratoSolicitacaoServicoFormView();

	}

	@Override
	protected void carregar(Serializable id) {
		carregarCombosView();
		currentBean = contratoSolicitacaoServicoDAO.find(id);
		subView.carregarView(currentBean);
	}

	@Override
	protected void actionSalvar() {
		subView.preencherObjeto(currentBean);
		currentBean.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
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

	}

	@Override
	public String getViewIdentifier() {

		return null;
	}

}
