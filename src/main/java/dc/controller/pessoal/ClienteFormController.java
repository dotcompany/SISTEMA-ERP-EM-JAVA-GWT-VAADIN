package dc.controller.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;

import dc.entidade.contabilidade.ContabilConta;
import dc.entidade.tributario.OperacaoFiscal;
import dc.entidade.geral.Pessoa;
import dc.entidade.pessoal.AtividadeForCli;
import dc.entidade.pessoal.Cliente;
import dc.entidade.pessoal.SituacaoForCli;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.servicos.dao.tributario.OperacaoFiscalDAO;
import dc.servicos.dao.pessoal.AtividadeForCliDAO;
import dc.servicos.dao.pessoal.ClienteDAO;
import dc.servicos.dao.pessoal.PessoaDAO;
import dc.servicos.dao.pessoal.SituacaoForCliDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.ManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.pessoal.ClienteFormView;



@Controller
@Scope("prototype")
public class ClienteFormController extends CRUDFormController<Cliente> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ClienteFormView subView;

	@Autowired
	private ClienteDAO clienteDAO;

	@Autowired
	private PessoaDAO pessoaDAO;

	@Autowired
	private SituacaoForCliDAO situacaoDAO;
	
	@Autowired
	private AtividadeForCliDAO atividadeDAO;
	
	@Autowired
	private ContabilContaDAO contaDAO;
	
	@Autowired
	private OperacaoFiscalDAO operacaoDAO;

	private Cliente currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateObject(subView.getDtDesde().getValue())) {
			adicionarErroDeValidacao(subView.getDtDesde(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtContaTomador().getValue())) {
			adicionarErroDeValidacao(subView.getTxtContaTomador(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtTaxaDesconto().getValue())) {
			adicionarErroDeValidacao(subView.getTxtTaxaDesconto(), "Não pode ficar em branco");
			valido = false;
		}
		
		if (!Validator.validateString(subView.getTxtLimiteCredito().getValue())) {
			adicionarErroDeValidacao(subView.getTxtLimiteCredito(), "Não pode ficar em branco");
			valido = false;
		}
		
		Pessoa pessoa = (Pessoa) subView.getCmbPessoa().getValue();
		if (!Validator.validateObject(pessoa)) {
			adicionarErroDeValidacao(subView.getCmbPessoa(), "Não pode ficar em branco");
			valido = false;
		}
		
		SituacaoForCli situacao = (SituacaoForCli) subView.getCmbSituacao().getValue();
		if (!Validator.validateObject(situacao)) {
			adicionarErroDeValidacao(subView.getCmbSituacao(), "Não pode ficar em branco");
			valido = false;
		}
		
		AtividadeForCli atividade = (AtividadeForCli) subView.getCmbAtividade().getValue();
		if (!Validator.validateObject(atividade)) {
			adicionarErroDeValidacao(subView.getCmbAtividade(), "Não pode ficar em branco");
			valido = false;
		}
		
		ContabilConta contabil = (ContabilConta) subView.getCmbContaContabil().getValue();
		if (!Validator.validateObject(contabil)) {
			adicionarErroDeValidacao(subView.getCmbContaContabil(), "Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new Cliente();
		
	}

	@Override
	protected void initSubView() {
		subView = new ClienteFormView();
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = clienteDAO.find(id);
		subView.getDtDesde().setValue(currentBean.getDesde());
		subView.getTxtContaTomador().setValue(currentBean.getContaTomador());
		subView.getTxtObservacao().setValue(currentBean.getObservacao());

		
		/* Configura combo Pessoa */
		ManyToOneComboModel<Pessoa> model = new ManyToOneComboModel<Pessoa>() {
			
			@Override
			public void onCriarNovo(String filter) {
				Notification.show("Selecionado Criar Novo: " + filter);
			}
			
			@Override
			public List<Pessoa> getResultado(String q) {
				return pessoaDAO.query(q);
			}
			
			@Override
			public Class<Pessoa> getEntityClass() {
				return Pessoa.class;
			}
			
			@Override
			public String getCaptionProperty() {
				return "nome";
			}

			@Override
			public void onEditar(Pessoa value) {
				Notification.show("Selecionado Editar: " + value.getNome());
				
			}
		};
		subView.getCmbPessoa().setModel(model);
		subView.getCmbPessoa().setValue(currentBean.getPessoa());
		
		
		/* Configura combo Situacao de Fornecedor e Cliente */
		ManyToOneComboModel<SituacaoForCli> modelsituacao = new ManyToOneComboModel<SituacaoForCli>() {
			
			@Override
			public void onCriarNovo(String filter) {
				Notification.show("Selecionado Criar Novo: " + filter);
			}
			
			@Override
			public List<SituacaoForCli> getResultado(String q) {
				return situacaoDAO.query(q);
			}
			
			@Override
			public Class<SituacaoForCli> getEntityClass() {
				return SituacaoForCli.class;
			}
			
			@Override
			public String getCaptionProperty() {
				return "nome";
			}

			@Override
			public void onEditar(SituacaoForCli value) {
				Notification.show("Selecionado Editar: " + value.getNome());
				
			}
		};
		subView.getCmbSituacao().setModel(modelsituacao);
		subView.getCmbSituacao().setValue(currentBean.getSituacao());
		
		
		/* Configura combo Atividade de Fornecedor e Cliente */
		ManyToOneComboModel<AtividadeForCli> modelatividade = new ManyToOneComboModel<AtividadeForCli>() {
			
			@Override
			public void onCriarNovo(String filter) {
				Notification.show("Selecionado Criar Novo: " + filter);
			}
			
			@Override
			public List<AtividadeForCli> getResultado(String q) {
				return atividadeDAO.query(q);
			}
			
			@Override
			public Class<AtividadeForCli> getEntityClass() {
				return AtividadeForCli.class;
			}
			
			@Override
			public String getCaptionProperty() {
				return "nome";
			}

			@Override
			public void onEditar(AtividadeForCli value) {
				Notification.show("Selecionado Editar: " + value.getNome());
				
			}
		};
		subView.getCmbAtividade().setModel(modelatividade);
		subView.getCmbAtividade().setValue(currentBean.getAtividadeForCli());
		
		
		/* Configura combo Contabil Conta  */
		ManyToOneComboModel<ContabilConta> modelconta = new ManyToOneComboModel<ContabilConta>() {
			
			@Override
			public void onCriarNovo(String filter) {
				Notification.show("Selecionado Criar Novo: " + filter);
			}
			
			@Override
			public List<ContabilConta> getResultado(String q) {
				return contaDAO.query(q);
			}
			
			@Override
			public Class<ContabilConta> getEntityClass() {
				return ContabilConta.class;
			}
			
			@Override
			public String getCaptionProperty() {
				return "nome";
			}

			@Override
			public void onEditar(ContabilConta value) {
				Notification.show("Selecionado Editar: " + value.getClassificacao());
				
			}
		};
		subView.getCmbContaContabil().setModel(modelconta);
		subView.getCmbContaContabil().setValue(currentBean.getContabilConta());
		
		
		/* Configura combo Operação Fiscal  */
		ManyToOneComboModel<OperacaoFiscal> modeloperacao = new ManyToOneComboModel<OperacaoFiscal>() {
			
			@Override
			public void onCriarNovo(String filter) {
				Notification.show("Selecionado Criar Novo: " + filter); 
			}
			
			@Override
			public List<OperacaoFiscal> getResultado(String q) {
				return operacaoDAO.query(q);
			}
			
			@Override
			public Class<OperacaoFiscal> getEntityClass() {
				return OperacaoFiscal.class;
			}
			
			@Override
			public String getCaptionProperty() {
				return "nome";
			}

			@Override
			public void onEditar(OperacaoFiscal value) {
				Notification.show("Selecionado Editar: " + value.getDescricao());
				
			}
		};
		subView.getCmbOperacaoFiscal().setModel(modeloperacao);
		subView.getCmbOperacaoFiscal().setValue(currentBean.getOperacaoFiscal());

	}

	@Override
	protected void actionSalvar() {

		currentBean.setContaTomador(subView.getTxtContaTomador().getValue());
		currentBean.setPessoa((Pessoa) subView.getCmbPessoa().getValue());
		currentBean.setSituacao ((SituacaoForCli) subView.getCmbSituacao().getValue());
		currentBean.setAtividadeForCli ((AtividadeForCli) subView.getCmbAtividade().getValue());
		currentBean.setContabilConta((ContabilConta) subView.getCmbContaContabil().getValue());
		currentBean.setOperacaoFiscal ((OperacaoFiscal) subView.getCmbOperacaoFiscal().getValue());
		currentBean.setObservacao(subView.getTxtObservacao().getValue());

		try {
			clienteDAO.saveOrUpdate(currentBean);

			mensagemSalvoOK();
		} catch (Exception ex) {
			ex.printStackTrace();

		}
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
		return "Cliente";
	}

	@Override
	protected void remover(List<Serializable> ids) {

		clienteDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "clienteForm";
	}

}
