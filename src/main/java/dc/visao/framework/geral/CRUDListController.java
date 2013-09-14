package dc.visao.framework.geral;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.addons.lazyquerycontainer.BeanQueryFactory;
import org.vaadin.addons.lazyquerycontainer.CompositeItem;
import org.vaadin.addons.lazyquerycontainer.LazyQueryContainer;
import org.vaadin.addons.lazyquerycontainer.LazyQueryView;
import org.vaadin.addons.lazyquerycontainer.NestingBeanItem;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.dialogs.DefaultConfirmDialogFactory;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Property;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.Table.ColumnGenerator;

import dc.anotacoes.AnotacoesUtil;
import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.PapelMenu;
import dc.framework.DcConstants;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;
import dc.servicos.dao.framework.geral.GenericListDAO;
import dc.visao.framework.component.manytoonecombo.ModalWindowSaveListener;
import dc.visao.spring.SecuritySessionProvider;

/**
*
* @author Wesley Jr
/*
 * Nessa classe temos a Classe principal que é abstract, onde implementa o Controller e pega
 * um Component.
 * Nessa tela temos as informações de todos os botões a configuração deles
 * SALVAR, PESQUISAR E CRIAR
 * Onde temos a configuração deles, o que cada botão faz!
 * Temos a configuração da Tabela, pois pegamos informações da Tela CRUDListView
 * Temos a configuração do Container também.
 *
*/

public abstract class CRUDListController<E> extends ControllerTask implements Controller,ControllerAcesso {

	public static Logger logger = Logger.getLogger(CRUDListController.class);
	private static final Object CUSTOM_SELECT_ID = "Selecionar";
	private static final int PAGE_SIZE = 100;
	CRUDListView view;
	private Table table;

	private HashMap selected = new HashMap<Object,Object>();

	@Autowired
	private MainController mainController;
	private PapelMenu papelMenu;
	private boolean acessoLiberado = false;
	
	@Autowired
	private GenericListDAO genericDAO;
	
	
	private Window window = null;
	private ModalWindowSaveListener saveListener;
			
	@PostConstruct
	protected void init() {
		getFormController().setListController(this);
		genericDAO.setPojoClass(getEntityClass());
		view = new CRUDListView(this);

		// Titulo
		view.getLblNome().setValue(getTitulo());
		//view.getLblNome().setContentMode(ContentMode.HTML);

		// Botao de pesquisa
		view.getBtnPesquisa().addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				if(papelMenu != null){
					if(DcConstants.BOOL_CHAR_TRUE.equals(papelMenu.getPodeConsultar())){
						actionPesquisa();	
					}else{
						getFormController().mensagemErro(DcConstants.PERMISSAO_NEGADA);
					}	
				}else{
					if(acessoLiberado){
						actionPesquisa();
					}
				}

			}
		});

		// Botao Criar (Novo)
				view.getBtnCriar().addClickListener(new ClickListener() {
					public void buttonClick(ClickEvent event) {
						if(papelMenu != null){
							if(DcConstants.BOOL_CHAR_TRUE.equals(papelMenu.getPodeInserir())){
								actionCriarNovo();	
							}else{
								getFormController().mensagemErro(DcConstants.PERMISSAO_NEGADA);
							}
						}else{
							if(acessoLiberado){
								actionCriarNovo();
							}
						}
					}
				});


				ConfirmDialog.Factory df = new DefaultConfirmDialogFactory() {

				    // We change the default order of the buttons
				    @Override
				    public ConfirmDialog create(String caption, String message,
				            String okCaption, String cancelCaption) {
				        ConfirmDialog d = super.create(caption, message, okCaption,
				        		cancelCaption);
				        d.setStyleName("dc-confirm-dialog");

				        d.setWidth("35%");
				        d.setHeight("20%");	

				        return d;
				    }

				};
				ConfirmDialog.setFactory(df);				

				// Botaao remover selecionados	
				view.getBtnRemover().addClickListener(new ClickListener() {
					public void buttonClick(ClickEvent event) {
						if(papelMenu != null){
							if(DcConstants.BOOL_CHAR_TRUE.equals(papelMenu.getPodeExcluir())){
								actionRemoverSelecionados();	
							}else{
								getFormController().mensagemErro(DcConstants.PERMISSAO_NEGADA);
							}	
						}else{
							if(acessoLiberado){
								actionRemoverSelecionados();
							}
						}

					}
				});
				
				actionPesquisa();
	}

	protected void actionAbrir(Object object) {
		if(object == null){				
			getFormController().mensagemAtencao("Escolha um registro para abrir");
		}else{
			Serializable id = (Serializable) object;
			mainController.showTaskableContent( getFormController());
			getFormController().mostrar(id);		
		}

	}

	protected void actionCriarNovo() {
		criaNovo();
		mainController.showTaskableContent(getFormController());
	}

	public void criaNovo() {
		getFormController().init();
		getFormController().criarNovo();
	}

	protected void actionRemoverSelecionados() {
		final List ids = Arrays.asList(selected.keySet().toArray());
		final List values = Arrays.asList(selected.values().toArray());
		if(ids.isEmpty()){
			getFormController().mensagemAtencao("Nenhum registro selecionado para remoção");
		}else{
			ConfirmDialog.show(MainUI.getCurrent(), "Confirme a remoção", "Você tem certeza? Isso apagará os registros selecionados e Não poderá ser revertido.",
			        "Sim", "Não", new ConfirmDialog.Listener() {

			            public void onClose(ConfirmDialog dialog) {
			                if (dialog.isConfirmed()) {
			                	try{
			                	if(deletaEmCascata()){
			                		getFormController().removerEmCascata(values);
			                	}else{
			                		getFormController().remover(ids);
			                	}
			                		LazyQueryContainer container = (LazyQueryContainer) table.getContainerDataSource();
			            			for(Object id : ids){
			            				   container.removeItem(id);
			            			}
			            			container.commit();
		            				container.refresh();
			            			selected.clear();
			            			table.refreshRowCache();
			            			}catch (Exception e){
			            				logger.warning(e.getMessage());
			            				getFormController().mensagemErro("Houve um erro remover registro. Verifique se o mesmo Não tem dependência com outros registros.");
			            			}
			                } 
			            }
			        });



		}

	}

	protected abstract CRUDFormController<E> getFormController();
	

	protected void actionPesquisa() {
		selected.clear();
		String valor = view.getTxtPesquisa().getValue();

		// Configura da tabela
		table = new Table();
		table.addListener(new ItemClickEvent.ItemClickListener() {
			             private static final long serialVersionUID = 2068314108919135281L;

			             public void itemClick(ItemClickEvent event) {
			                 if (event.isDoubleClick()) {
			                      actionAbrir(event.getItemId());
			                 }
			             }
			         });

		 //adiciona checkbox na ultima coluna para marcar para acoes como ex: remover
		 table.addGeneratedColumn(CUSTOM_SELECT_ID, new ColumnGenerator() {

	            @Override
	            public Component generateCell(final Table source, final Object itemId, final Object columnId) {

	            	final CompositeItem selectedBeanItem = 	(CompositeItem) source.getContainerDataSource().getItem(itemId);
	            	final NestingBeanItem nestedItem = (NestingBeanItem) selectedBeanItem.getItem("bean");
	            	
	                final CheckBox checkBox = new CheckBox();
	                checkBox.setImmediate(true);
	                checkBox.addValueChangeListener(new Property.ValueChangeListener() {

						@Override
						public void valueChange(
								com.vaadin.data.Property.ValueChangeEvent event) {
							Boolean select = (Boolean) event.getProperty().getValue();
	                		if(select){
	                			selected.put(itemId,nestedItem.getBean());
	                		}else{
	                			selected.remove(itemId);
	                		}
							
						}
	                });

	                checkBox.setValue(selected.containsKey(itemId));
	                return checkBox;
	            }
	        });

		table.setWidth("100%");
		//table.setHeight("99%");
		table.setEditable(false);
		table.setImmediate(false);
		table.setSelectable(true);
		table.setMultiSelect(false);
		table.setPageLength(PAGE_SIZE);
		table.setColumnWidth(CUSTOM_SELECT_ID, 80);
		// configuração do Container
		BeanQueryFactory queryFactory = null ;
		if(isMultiEmpresa(getEntityClass())){
			queryFactory = new BeanQueryFactory<DCBeanQueryMultiEmpresa>(DCBeanQueryMultiEmpresa.class);
		}else{
			queryFactory = new BeanQueryFactory<DCBeanQuery>(DCBeanQuery.class);	
		}
		
		
		Map<String, Object> conf = new HashMap<String,Object>();
		conf.put("search",valor);
		conf.put("dao",getMainDao());
		conf.put("pojoClass",getEntityClass());
		conf.put("conta_id",SecuritySessionProvider.getUsuario().getConta().getEmpresa().getId());
		queryFactory.setQueryConfiguration(conf);

		LazyQueryContainer container = new LazyQueryContainer(queryFactory,getBeanIdProperty(),PAGE_SIZE,true);
		for(String id_coluna : getColunas()){
			container.addContainerProperty(id_coluna, String.class, "", true, true);	
		}
		container.addContainerProperty(LazyQueryView.DEBUG_PROPERTY_ID_QUERY_INDEX, Integer.class, 0, true, false);
		container.addContainerProperty(LazyQueryView.DEBUG_PROPERTY_ID_BATCH_INDEX, Integer.class, 0, true, false);
		container.addContainerProperty(LazyQueryView.DEBUG_PROPERTY_ID_BATCH_QUERY_TIME, Integer.class, 0, true, false);
		
		for(String prop : getColunas()) {
			Caption captionAnn = AnotacoesUtil.getAnotacao(Caption.class, getEntityClass(), prop);

			boolean existe = (captionAnn != null && !captionAnn.value().equals("NULO"));
			if (existe)
				table.setColumnHeader(prop, captionAnn.value());

			else
				table.setColumnHeader(prop, prop);

			// verifica se é uma propriedade de um objeto dentro do bean
			if (prop.contains(".")) {
				//container.addNestedContainerProperty(prop);
			}

			if (prop.equals(CUSTOM_SELECT_ID)){
				table.setColumnExpandRatio(prop, 1);	
			}else{
				table.setColumnExpandRatio(prop, 3);
			}

		}	

		table.setContainerDataSource(container);

		Object[] cs = new Object[]  {CUSTOM_SELECT_ID};
		Object[] allCollumns = ArrayUtils.addAll(cs, getColunas());
		
		table.setVisibleColumns(allCollumns);
		table.setSizeFull();

		view.getVltTabela().removeAllComponents();
		view.getVltTabela().addComponent(table);

	}
	
	private boolean isMultiEmpresa(Object o) {
		    return o instanceof AbstractMultiEmpresaModel;
	}


	protected AbstractCrudDAO getMainDao() {
		return genericDAO;
	}

	protected String getBeanIdProperty() {
		return "id";
	}

	@Override
	public void setPapelMenu(PapelMenu papelMenu){
		logger.info("papel menu setado");
		//logger.info(String.valueOf(papelMenu));
		this.papelMenu = papelMenu;
	}

	@Override
	public void setAcessoLiberado(){
		this.acessoLiberado =true;
	}

	protected abstract String[] getColunas();

	protected abstract Class<? super E> getEntityClass();

	protected abstract List<E> pesquisa(String valor);

	protected abstract List<E> pesquisaDefault();

	protected abstract String getTitulo();

	protected abstract boolean deletaEmCascata();

	@Override
	public View getView() {
		return view;
	}

	public void show(CRUDListView crudListView) {
		mainController.showTaskableContent(this);
	}

	public HashMap getSelected() {
		return selected;
	}

	public boolean autoriaAlteracao() {
		return acessoLiberado || DcConstants.BOOL_CHAR_TRUE.equals(papelMenu.getPodeAlterar());
	}

	public boolean autoriaCriacao() {
		return acessoLiberado || DcConstants.BOOL_CHAR_TRUE.equals(papelMenu.getPodeInserir());
	}
	
	@Override
	public String getControllerTitle() {
		return this.getTitulo();
	}
	
	@Override
	public Controller getController() {
		return this;
	}

	public void setReadOnly(boolean readonly) {
		view.getBtnCriar().setVisible(!readonly);
		view.getBtnRemover().setVisible(!readonly);
	}

	public void closeWindow() {
		// TODO Auto-generated method stub
		window.close();
		window = null;
	}
	
	public void openOnNewWindow(int modalSize){
		window = new Window();
		window.setContent((Component) getFormController().getView());
		
		window.center();
		if(modalSize != 1 && modalSize != 2){
			window.setWidth("70%");
			window.setHeight("80%");	
		}else if (modalSize == 1){
			window.setWidth("100%");
			window.setHeight("100%");	
		}else{
			window.setWidth("35%");
			window.setHeight("40%");
		}
		
		window.setModal(true);
		UI.getCurrent().addWindow(window);
	}

	public boolean isOnSeparateWindow() {
		return window != null;
	}

	public CRUDFormController<E> getPublicFormController() {
		return getFormController();
	}

	public void addSaveListener(ModalWindowSaveListener modalWindowSaveListener) {
		saveListener = modalWindowSaveListener;
		
	}

	public void notifySaved(E obj) {
		// TODO Auto-generated method stub
		saveListener.onSave(obj);
	}

}