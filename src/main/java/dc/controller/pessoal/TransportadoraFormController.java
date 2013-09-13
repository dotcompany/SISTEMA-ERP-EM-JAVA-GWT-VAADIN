package dc.controller.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;

import dc.entidade.contabilidade.ContabilConta;
import dc.entidade.geral.Pessoa;
import dc.entidade.pessoal.Transportadora;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.servicos.dao.pessoal.PessoaDAO;
import dc.servicos.dao.pessoal.TransportadoraDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.ManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.pessoal.TransportadoraFormView;


@Controller
@Scope("prototype")
public class TransportadoraFormController extends CRUDFormController<Transportadora> {

	private  TransportadoraFormView subView;

	@Autowired
	private TransportadoraDAO transportadoraDAO;
	
	@Autowired
	private PessoaDAO pessoaDAO;
	
	@Autowired
	private ContabilContaDAO contabilContaDAO;
	
	private Transportadora currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtObservacao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtObservacao(),
					"não pode ficar em branco");
			valido = false;
		}
		return valido;
	}
	
	@Override
	protected void criarNovoBean() {
		currentBean = new Transportadora();
		
	}

	@Override
	protected void initSubView() {
		subView = new TransportadoraFormView();
		
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = transportadoraDAO.find(id);
		
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
		
		
		/* Configura combo Contabil Conta  */
		ManyToOneComboModel<ContabilConta> modelconta = new ManyToOneComboModel<ContabilConta>() {
			
			@Override
			public void onCriarNovo(String filter) {
				Notification.show("Selecionado Criar Novo: " + filter);
			}
			
			@Override
			public List<ContabilConta> getResultado(String q) {
				return contabilContaDAO.query(q);
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
		subView.getCmbContContabil().setModel(modelconta);
		subView.getCmbContContabil().setValue(currentBean.getContaContabil());
	}

	@Override
	protected void actionSalvar() {

		currentBean.setPessoa((Pessoa) subView.getCmbPessoa().getValue());
		currentBean.setContaContabil((ContabilConta) subView.getCmbContContabil().getValue());
		currentBean.setObservacao(subView.getTxtObservacao().getValue());
		
		try {
			transportadoraDAO.saveOrUpdate(currentBean);


			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		
	}

	@Override
	protected void quandoNovo() {
	}

	@Override
	protected String getNome() {
		return "Transportadora";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		
		transportadoraDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}
	
	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}
	
	@Override
	public String getViewIdentifier() {
		return "transportadoraForm";
	}
	
	@Override
	public boolean isFullSized(){
	   return true;
	}

	@Override
	protected Component getSubView() {
		return subView;
	}
}