package dc.visao.framework.geral;

import java.io.Serializable;
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
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.Table.ColumnReorderEvent;
import com.vaadin.ui.Table.ColumnReorderListener;
import com.vaadin.ui.Table.ColumnResizeEvent;
import com.vaadin.ui.Table.ColumnResizeListener;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

import dc.anotacoes.AnotacoesUtil;
import dc.anotacoes.Caption;
import dc.control.util.ClasseUtil;
import dc.entidade.framework.FmMenu;
import dc.entidade.framework.FmModulo;
import dc.entidade.framework.PapelMenu;
import dc.entidade.geral.Usuario;
import dc.framework.DcConstants;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;
import dc.servicos.dao.framework.geral.FmMenuDAO;
import dc.servicos.dao.framework.geral.FmModuloDAO;
import dc.servicos.dao.framework.geral.GenericListDAO;
import dc.visao.framework.component.CompanyFileHandler;
import dc.visao.framework.component.CustomListTable;
import dc.visao.framework.component.manytoonecombo.ModalWindowSaveListener;
import dc.visao.framework.component.manytoonecombo.ModalWindowSelectionListener;
import dc.visao.spring.SecuritySessionProvider;

public abstract class CRUDListController<E> extends ControllerTask implements
		Controller, ControllerAcesso {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Logger logger = Logger.getLogger(CRUDListController.class);

	private static final int PAGE_SIZE = 100;
	public static final int WINDOW_LIST = 1;
	public static final int WINDOW_FORM = 2;
	CRUDListView view;
	private CustomListTable table;

	private HashMap selected = new HashMap<Object, Object>();

	@Autowired
	private MainController mainController;

	@Autowired
	private GenericListDAO genericDAO;

	@Autowired
	private CompanyFileHandler fileUtils;

	private PapelMenu papelMenu;
	private boolean acessoLiberado = false;

	private Window window = null;
	private ModalWindowSaveListener saveListener;
	private ModalWindowSelectionListener windowSelectionListener;

	@PostConstruct
	protected void init() {
		getFormController().setListController(this);

		genericDAO.setPojoClass(getEntityClass());
		view = new CRUDListView(this);

		// Titulo
		view.getLblNome().setValue(getTitulo());
		// view.getLblNome().setContentMode(ContentMode.HTML);

		// Botao de pesquisa
		view.getBtnPesquisa().addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				if (papelMenu != null) {
					if (DcConstants.BOOL_CHAR_TRUE.equals(papelMenu
							.getPodeConsultar())) {
						actionPesquisa();
					} else {
						getFormController().mensagemErro(
								DcConstants.PERMISSAO_NEGADA);
					}
				} else {
					if (acessoLiberado) {
						actionPesquisa();
					}
				}
			}
		});

		// Botao Criar (Novo)
		view.getBtnCriar().addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				if (papelMenu != null) {
					if (DcConstants.BOOL_CHAR_TRUE.equals(papelMenu
							.getPodeInserir())) {
						actionCriarNovo();
					} else {
						getFormController().mensagemErro(
								DcConstants.PERMISSAO_NEGADA);
					}
				} else {
					if (acessoLiberado) {
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
				if (papelMenu != null) {
					if (DcConstants.BOOL_CHAR_TRUE.equals(papelMenu
							.getPodeExcluir())) {
						actionRemoverSelecionados();
					} else {
						getFormController().mensagemErro(
								DcConstants.PERMISSAO_NEGADA);
					}
				} else {
					if (acessoLiberado) {
						actionRemoverSelecionados();
					}
				}
			}
		});

		permissaoOperacao();

		actionPesquisa();
	}

	protected void actionAbrir(Object object) {
		if (object == null) {
			getFormController().mensagemAtencao(
					"Escolha um registro para abrir");
		} else {
			Serializable id = (Serializable) object;

			if (isOnSeparateWindow()) {
				notifySelected((E) genericDAO.find(id));
			} else {
				mainController.showTaskableContent(getFormController());
				getFormController().mostrar(id);
			}
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

		if (ids.isEmpty()) {
			getFormController().mensagemAtencao(
					"Nenhum registro selecionado para remoção");
		} else {
			ConfirmDialog
					.show(MainUI.getCurrent(),
							"Confirme a remoção",
							"Você tem certeza? Isso apagará os registros selecionados e Não poderá ser revertido.",
							"Sim", "Não", new ConfirmDialog.Listener() {

								public void onClose(ConfirmDialog dialog) {
									if (dialog.isConfirmed()) {
										try {
											if (deletaEmCascata()) {
												getFormController()
														.removerEmCascata(
																values);
											} else {
												getFormController()
														.remover(ids);
											}

											LazyQueryContainer container = (LazyQueryContainer) table
													.getContainerDataSource();

											for (Object id : ids) {
												container.removeItem(id);
											}

											container.commit();
											container.refresh();
											selected.clear();
											table.refreshRowCache();
										} catch (Exception e) {
											logger.warning(e.getMessage());
											getFormController()
													.mensagemErro(
															"Houve um erro remover registro. Verifique se o mesmo Não tem dependência com outros registros.");
										}
									}
								}

							});

		}
	}

	protected abstract CRUDFormController<E> getFormController();

	protected void actionPesquisa() {
		String valor = view.getTxtPesquisa().getValue();

		// Configura da tabela
		table = new CustomListTable();

		table.setFileHandler(fileUtils);
		table.setEntityName(getEntityClass().getSimpleName());

		// table.setHeight("99%");
		table.setEditable(false);
		table.setImmediate(true);
		table.setSelectable(true);
		// table.setDragMode(TableDragMode.)
		table.setColumnCollapsingAllowed(true);
		table.setColumnReorderingAllowed(true);
		table.setMultiSelect(false);
		table.setPageLength(PAGE_SIZE);

		table.addColumnReorderListener(new ColumnReorderListener() {
			@Override
			public void columnReorder(ColumnReorderEvent event) {
				logger.info("reorder");
				table.saveToFile();
			}
		});

		table.addColumnResizeListener(new ColumnResizeListener() {
			@Override
			public void columnResize(ColumnResizeEvent event) {
				logger.info("resize");
				table.saveToFile();
			}
		});

		table.addListener(new ItemClickEvent.ItemClickListener() {
			private static final long serialVersionUID = 2068314108919135281L;

			public void itemClick(ItemClickEvent event) {
				if (event.isDoubleClick()) {
					actionAbrir(event.getItemId());
				}
			}
		});

		// adiciona checkbox na ultima coluna para marcar para acoes como ex:
		// remover
		//table.setRowHeaderMode(Table.ROW_HEADER_MODE_INDEX);
		
		table.addGeneratedColumn("mycolumn", new ColumnGenerator() {
			public Object generateCell(Table source, Object itemId, Object columnId) {
			   TextField tf = new TextField();
			  return tf;
		}
		});
		
		table.addGeneratedColumn(CustomListTable.CUSTOM_SELECT_ID,
				new ColumnGenerator() {
					@Override
					public Component generateCell(final Table source,
							final Object itemId, final Object columnId) {
						final CompositeItem selectedBeanItem = (CompositeItem) source
								.getContainerDataSource().getItem(itemId);
						final NestingBeanItem nestedItem = (NestingBeanItem) selectedBeanItem
								.getItem("bean");
						
						/*table.addContainerProperty("classification", String.class, null);
					    final ColumnGenerator generator = new ColumnGenerator() {
					        @Override
					        public Object generateCell(Table source, Object itemId,
					                Object columnId) {
					            return "1";
					        }
					    };
					    table.addGeneratedColumn("classification", generator);
					    table.addItem();
					    table.addItem();
					    view.getVltTabela().addComponent(table);
						
						/*table.addContainerProperty("0", String.class, null, "", null, null);
						for (int i=0; i<8; i++)
						    table.addContainerProperty(""+(i+1), String.class, null,
						                         String.valueOf((char) (65+i)), null, null);
						
						/*int rows = 0;
						for (int i = 1; i < rows; i++) { 
							
						} 
						
						final Label label = new Label("1");*/
						final CheckBox checkBox = new CheckBox();
						
						checkBox.setImmediate(true);
						checkBox.addValueChangeListener(new Property.ValueChangeListener() {
							@Override
							public void valueChange(
									com.vaadin.data.Property.ValueChangeEvent event) {
								Boolean select = (Boolean) event.getProperty()
										.getValue();
								if (select) {
									selected.put(itemId, nestedItem.getBean());
								} else {
									selected.remove(itemId);
								}
							}
						});

						checkBox.setValue(selected.containsKey(itemId));

						return checkBox;
					}
				});

		doSearch(valor);

		view.getVltTabela().removeAllComponents();
		view.getVltTabela().addComponent(table);
	}

	public void doSearch(String valor) {
		consultaMultiempresa();

		if (valor == null) {
			valor = "";
		}

		selected.clear();
		table.setWidth("100%");
		table.setColumnWidth(CustomListTable.CUSTOM_SELECT_ID, 80);
		logger.info("valor pesquisado: " + valor);

		BeanQueryFactory queryFactory = null;

		if (genericDAO.isMultiEmpresa(getEntityClass())) {
			if (SecuritySessionProvider.getUsuario().getConsultaMultiempresa()
					.equals(1)) {
				queryFactory = new BeanQueryFactory<DCBeanQueryMultiEmpresa>(
						DCBeanQueryMultiEmpresa.class);
			} else {
				queryFactory = new BeanQueryFactory<DCBeanQuery>(
						DCBeanQuery.class);
			}
		} else {
			queryFactory = new BeanQueryFactory<DCBeanQuery>(DCBeanQuery.class);
		}

		Map<String, Object> conf = new HashMap<String, Object>();
		Integer idEmpresa = SecuritySessionProvider.getUsuario().getConta()
				.getEmpresa().getId();
		conf.put("search", valor);
		genericDAO.setPojoClass(getEntityClass());
		conf.put("dao", getMainDao());
		conf.put("pojoClass", getEntityClass());
		conf.put("id_empresa", idEmpresa);
		queryFactory.setQueryConfiguration(conf);

		LazyQueryContainer container = new LazyQueryContainer(queryFactory,
				getBeanIdProperty(), PAGE_SIZE, true);

		for (String id_coluna : getColunas()) {
			container.addContainerProperty(id_coluna, String.class, "", true,
					true);
		}

		container.addContainerProperty(
				LazyQueryView.DEBUG_PROPERTY_ID_QUERY_INDEX, Integer.class, 0,
				true, false);
		container.addContainerProperty(
				LazyQueryView.DEBUG_PROPERTY_ID_BATCH_INDEX, Integer.class, 0,
				true, false);
		container.addContainerProperty(
				LazyQueryView.DEBUG_PROPERTY_ID_BATCH_QUERY_TIME,
				Integer.class, 0, true, false);

		table.setSortEnabled(true);
		// table.markAsDirty();
		table.setSizeFull();
		table.setContainerDataSource(container);

		for (String prop : getColunas()) {
			Caption captionAnn = AnotacoesUtil.getAnotacao(Caption.class,
					getEntityClass(), prop);

			boolean existe = (captionAnn != null && !captionAnn.value().equals(
					"NULO"));

			if (existe)
				table.setColumnHeader(prop, captionAnn.value());
			else
				table.setColumnHeader(prop, prop);

			// verifica se e uma propriedade de um objeto dentro do bean
			if (prop.contains(".")) {
				// container.addNestedContainerProperty(prop);
			}

			if (prop.equals(CustomListTable.CUSTOM_SELECT_ID)) {
				table.setColumnExpandRatio(prop, 1);
			} else {
				table.setColumnExpandRatio(prop, 3);
			}
		}

		boolean loadedFromFile = table.loadFromFile();
		// boolean loadedFromFile = false;
		if (!loadedFromFile) {
			Object[] cs = new Object[] { CustomListTable.CUSTOM_SELECT_ID };
			Object[] allCollumns = ArrayUtils.addAll(cs, getColunas());
			table.setVisibleColumns(allCollumns);
		}

		if (getColunas() != null && getColunas().length > 1) {
			table.setFooterVisible(true);
			table.setColumnFooter(CustomListTable.CUSTOM_SELECT_ID, "Total: ");
			table.setColumnFooter(getColunas()[0], container.getItemIds()
					.size() + " registro(s) encontrado(s)");
		}
	}

	protected AbstractCrudDAO getMainDao() {
		return genericDAO;
	}

	protected String getBeanIdProperty() {
		return "id";
	}

	@Override
	public void setPapelMenu(PapelMenu papelMenu) {
		logger.info("papel menu setado");
		// logger.info(String.valueOf(papelMenu));
		this.papelMenu = papelMenu;
	}

	@Override
	public void setAcessoLiberado() {
		this.acessoLiberado = true;
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
		return acessoLiberado
				|| DcConstants.BOOL_CHAR_TRUE
						.equals(papelMenu.getPodeAlterar());
	}

	public boolean autoriaCriacao() {
		return acessoLiberado
				|| DcConstants.BOOL_CHAR_TRUE
						.equals(papelMenu.getPodeInserir());
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

	public void setEnabled(boolean enabled) {
		view.getBtnCriar().setEnabled(enabled);
		view.getBtnRemover().setEnabled(enabled);
	}

	public void closeWindow() {
		window.close();
		window = null;
	}

	public void openOnNewWindow(int modalSize, final int mode) {
		window = new Window() {

			private static final long serialVersionUID = 1L;

			public void close() {
				if (mode == WINDOW_FORM) {
					if (getFormController().hasNewAttemptOpen()) {
						getFormController().confirmClose();
					} else {
						super.close();
					}
				} else {
					super.close();
				}
			}

		};

		if (mode == WINDOW_FORM) {
			window.setContent((Component) getFormController().getView());
		} else if (mode == WINDOW_LIST) {
			window.setContent((Component) getView());
		}

		window.center();

		if (modalSize != 1 && modalSize != 2) {
			window.setWidth("70%");
			window.setHeight("80%");
		} else if (modalSize == 1) {
			window.setWidth("100%");
			window.setHeight("100%");
		} else {
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

	public void setSaveListener(ModalWindowSaveListener modalWindowSaveListener) {
		saveListener = modalWindowSaveListener;
	}

	public void setSelectionListener(ModalWindowSelectionListener listener) {
		windowSelectionListener = listener;
	}

	public Table getTable() {
		return table;
	}

	public void notifySaved(E obj) {
		if (saveListener != null) {
			saveListener.onSave(obj);
		}
	}

	public void notifySelected(E obj) {
		if (windowSelectionListener != null) {
			windowSelectionListener.onSelect(obj);
			this.closeWindow();
		}
	}

	public void showOnWindow(Component c) {
		window.setContent(c);
	}

	@Override
	public void dispose() {
		// view = null;
		// table = null;
		// saveListener = null;
		// windowSelectionListener = null;
	}

	@Override
	public void setChildModuleID(String id) {
		getFormController().setModuleId(id);
	}

	/**
	 * VERIFICAR PERMISSÃO PARA SALVAR, CRIAR, REMOVER E EDITAR
	 */

	@Autowired
	private FmMenuDAO meDAO;

	@Autowired
	private FmModuloDAO mDAO;

	private void permissaoOperacao() {
		Usuario usuario = ClasseUtil.getUsuario();

		if (!usuario.getLogin().equals("admin@dotcompanyerp.com.br")) {
			List<FmModulo> auxLista = this.mDAO.getModuloLista(usuario);

			List<FmMenu> auxLista1 = this.meDAO.getMenuLista(auxLista, this
					.getFormController().getListController().getClass()
					.getName());

			for (Object obj : auxLista1) {
				FmMenu menu = (FmMenu) obj;

				if (menu.getControllerClass().equals(this.getClass().getName())) {
					if (menu.getPermissaoOperacao().equals(1)) {
						this.getFormController().getListController()
								.setEnabled(false);
						this.getFormController().setEnabled(false);
					}

					break;
				}
			}
		}
	}

	private void consultaMultiempresa() {

		FmMenu ent = this.meDAO.getMenu(this.getFormController()
				.getListController().getClass().getName());

		SecuritySessionProvider.getUsuario().setConsultaMultiempresa(
				ent.getConsultaMultiempresa());
	}

}