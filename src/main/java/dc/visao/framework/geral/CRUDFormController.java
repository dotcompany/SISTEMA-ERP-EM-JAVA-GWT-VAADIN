package dc.visao.framework.geral;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.dialogs.ConfirmDialog;

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

import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.Empresa;
import dc.framework.DcConstants;
import dc.visao.spring.SecuritySessionProvider;

/**
 * 
 * @author Wesley Jr /* Nessa classe temos a configuração da Tela, todos os
 *         controllers. Essa é a classe principal. Temos a configuração dos
 *         botões SALVAR, CRIAR Tem o Método também do CARREGAR, que pega as
 *         informações contida na Tela, que está salvo no Banco de Dados
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

	private boolean newAttemptOpen;

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
                        if (!isOnSeparateWindow()) {
                                mainController.showTaskableContent(CRUDFormController.this);
                        } else {
                                listController.showOnWindow(view);
                                criarNovo();
                        }
                }
        });
        
		view.getBtnCancelar().addClickListener(new ClickListener() {
	
		@Override
			public void buttonClick(ClickEvent event) {
				init();
				////ew.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				if (!hasNewAttemptOpen()) {
					close();
				}else {
					sairFechar();
				}
	       }
		});
}
	
       private void closeFormTaskOrWindow() {
		if (!isOnSeparateWindow()) {
			Task parent = listController;
			mainController.removeTask(CRUDFormController.this, false);
			mainController.showTaskableContent(parent);
			listController.doSearch(((CRUDListView) listController.getView())
					.getTxtPesquisa().getValue());
		} else {
			close();
		}
	}

	public void confirmClose() {
		ConfirmDialog.show(MainUI.getCurrent(), "Tem certeza?",
				"Você não salvou nenhuma de suas alterações.", "Sim", "Não",
				new ConfirmDialog.Listener() {
					public void onClose(ConfirmDialog dialog) {
						if (dialog.isConfirmed()) {
							newAttemptOpen = false;
							closeFormTaskOrWindow();
						}
					}
				});
	}

	private boolean isOnSeparateWindow() {
		return listController.isOnSeparateWindow();
	}

	private void close() {
		if (hasNewAttemptOpen()) {
			confirmClose();
		} else {
			listController.closeWindow();
		}
	}

	protected boolean isFullSized() {
		return false;
	}

	/**
	 * Utilizado para limpar validacoes nos componentes VAADIN
	 */
	protected void limpaValidacoes() {
		Iterator<String> it = validatableComponents.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			AbstractComponent c = validatableComponents.get(key);
			c.setComponentError(null);
		}
	}

	/*
	 * Classes que estendem CRUDFormController devem implementar validaãoes para
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
		this.newAttemptOpen = true;
		this.novo = true;
		novo();
	}
	
	private void sairFechar(){  
        if(javax.swing.JOptionPane.showConfirmDialog(null,"Deseja Fechar?","ATEN��O ",javax.swing.JOptionPane.YES_NO_OPTION )==0){  
            this.dispose();  
            close();
        }  
    }  

	protected abstract void carregar(Serializable id);

	public void load(AbstractModel<Serializable> model) {
		if (model != null) {
			carregar(model.getId());
		}
	}

	protected abstract void actionSalvar();

	protected void novo() {
		criarNovoBean();
		quandoNovo();
	}

	public void notifiyFrameworkSaveOK(E obj) {
		newAttemptOpen = false;
		listController.notifySaved(obj);

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

	public CRUDListController getListController() {
		return listController;
	}

	protected abstract void remover(List<Serializable> ids);

	protected abstract void removerEmCascata(List<Serializable> objetos);

	public void mensagemRemovidoOK() {
		new Notification(DcConstants.DELETE_TITLE_OK, DcConstants.DELETE_OK,
				Notification.TYPE_HUMANIZED_MESSAGE, true).show(Page
				.getCurrent());
	}

	public void mensagemErro(String message) {
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

	public void setEnabled(boolean enabled) {
		view.getBtnNovo().setEnabled(enabled);
		view.getBtnSalvar().setEnabled(enabled);
	}

	@Override
	public Task getParent() {
		return listController;
	}

	public boolean hasNewAttemptOpen() {
		return this.newAttemptOpen;
	}

	@Override
	public void dispose() {
		// view = null;
		// validatableComponents = null;
		// listController = null;
	}

	protected MainController getMainController() {
		return mainController;
	}

	@Override
	public void setChildModuleID(String id) {
		// nothing for now
	}

	public Empresa empresaAtual() {
		return SecuritySessionProvider.getUsuario().getConta().getEmpresa();
	}

}