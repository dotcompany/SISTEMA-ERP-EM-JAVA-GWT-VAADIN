package dc.visao.framework.geral;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.server.UserError;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

import dc.framework.DcConstants;

/**
 * 
 * @author Wesley Jr /* Nessa classe temos a configuração da Tela, todos os
 *         controllers. Essa é a classe principal. Temos a configuração dos
 *         Bot�es SALVAR, CRIAR Tem o m�todo também do CARREGAR, que pega as
 *         informações contida na Tela, que est� salvo no Banco de Dados
 * 
 */

public abstract class CRUDFormController<E> extends ControllerTask implements
		Controller, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6944317085399570143L;

	CRUDFormView view;

	private Type beanType;

	@Autowired
	private MainController mainController;

	private CRUDListController listController;

	private Map<String, AbstractComponent> validatableComponents;

	private boolean novo;

	private boolean active;

	private String id;

	private boolean taskable = true;

	@PostConstruct
	public void init() {
		validatableComponents = new HashMap<String, AbstractComponent>();

		initSubView();
		// Configura Titulo
		view = new CRUDFormView();
		// view.getLblNome().setContentMode(ContentMode.HTML);
		view.getLblNome().setValue(getNome());

		// Configura Conteudo

		Layout panel = view.getPnlContent();

		if (isFullSized()) {
			panel.setHeight("100%");
		}
		panel.addComponent(getSubView());

		/*
		 * Panel p = view.getPnlContent(); if(isFullSized()){
		 * view.setHeight("100%"); p.setHeight("100%"); }
		 * p.setContent(getSubView());
		 */

		// Configura Botoes;
		view.getBtnSalvar().addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				limpaValidacoes();
				if (validaSalvar()) {
					if (isNovo()) {
						if (listController.autoriaCriacao()) {
							actionSalvar();
						} else {
							mensagemErro(DcConstants.PERMISSAO_NEGADA);
						}
					} else {
						if (listController.autoriaAlteracao()) {
							actionSalvar();
						} else {
							mensagemErro(DcConstants.PERMISSAO_NEGADA);
						}
					}
				} else {
					mensagemErro(DcConstants.OPERATION_SAVE_NOT_OK);
				}
			}
		});

		view.getBtnNovo().addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				init();
				if(isTaskable()){
					mainController.showTaskableContent(CRUDFormController.this);	
				}
				
				criarNovo();

			}
		});

		view.getBtnCancelar().addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				if(isTaskable()){
					mainController.removeTask(CRUDFormController.this, false);
					mainController.showTaskableContent((Task) listController);	
				}else{
					close();
				}
				
			}

			

			
		});
	}
	
	private void close() {
		// TODO Auto-generated method stub
		
	}
	private boolean isTaskable() {
		return taskable;
	}
	
	private void setTaskable(boolean task) {
		this.taskable = task;
	}

	protected boolean isFullSized() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Utilizado para limpar validacoes nos componentes VAADIN
	 */
	protected void limpaValidacoes() {
		// TODO Auto-generated method stub
		Iterator<String> it = validatableComponents.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			AbstractComponent c = validatableComponents.get(key);
			c.setComponentError(null);
		}

	}

	/*
	 * Classes que estendem CRUDFormController devem implementar valida�oes para
	 * salvar(novo e alterar) nesse metodo
	 */
	protected abstract boolean validaSalvar();

	protected abstract void criarNovoBean();

	public Type getBeanType() {
		return this.beanType;
	}

	protected abstract void initSubView();

	public void mostrar(Serializable id) {
		this.novo = false;
		carregar(id);
	}

	public void criarNovo() {
		this.novo = true;
		novo();
	}

	protected abstract void carregar(Serializable id);

	protected abstract void actionSalvar();

	protected void novo() {
		criarNovoBean();
		quandoNovo();
	}

	public void mensagemSalvoOK() {
		new Notification("Gravado!", "Registro gravado com sucesso",
				Notification.TYPE_HUMANIZED_MESSAGE, true).show(Page
				.getCurrent());
	}

	protected abstract void quandoNovo();

	protected abstract Component getSubView();

	protected abstract String getNome();

	public Component getContent() {
		return view;
	}

	public void setListController(CRUDListController c) {
		this.listController = c;
	}

	protected abstract void remover(List<Serializable> ids);

	protected abstract void removerEmCascata(List<Serializable> objetos);

	public void mensagemRemovidoOK() {
		new Notification(DcConstants.DELETE_TITLE_OK, DcConstants.DELETE_OK,
				Notification.TYPE_HUMANIZED_MESSAGE, true).show(Page
				.getCurrent());
	}

	public void mensagemErro(String message) {
		// TODO Auto-generated method stub
		new Notification(DcConstants.ERROR_TITLE, message,
				Notification.TYPE_ERROR_MESSAGE, true).show(Page.getCurrent());
	}

	public void mensagemAtencao(String message) {
		new Notification(DcConstants.CAUTION_PLEASE, message,
				Notification.TYPE_WARNING_MESSAGE, true)
				.show(Page.getCurrent());
	}

	public View getView() {
		return view;
	}

	protected void adicionarErroDeValidacao(AbstractComponent c,
			String errorMessage) {
		validatableComponents.put(c.getId(), c);
		c.setComponentError(new UserError(errorMessage));
	}

	protected boolean estaVazio(TextField txtField) {
		if (txtField.getValue() == null || txtField.getValue().isEmpty()) {
			adicionarErroDeValidacao(txtField, "Não pode ficar em branco");
			return true;
		}
		;
		return false;
	}

	public boolean isNovo() {
		return novo;
	}

	@Override
	public String getControllerTitle() {
		return this.listController.getTitulo();
	}

	@Override
	public Controller getController() {
		return this;
	}

	public void setReadOnly(boolean readonly) {
		view.getBtnNovo().setVisible(!readonly);
	}

	@Override
	public Task getParent() {
		return listController;
	}

	@Override
	public String getModuleId() {
		return getParent().getModuleId();
	}

	@Override
	public void setModuleId(String id) {
		// nothing yet
	}

}