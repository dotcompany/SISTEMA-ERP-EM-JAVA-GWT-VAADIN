package dc.visao.administrativo.seguranca;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;

import dc.control.enums.SimNaoEn;
import dc.control.util.ObjectUtils;
import dc.controller.administrativo.seguranca.PapelFormController;
import dc.entidade.administrativo.seguranca.PapelEntity;
import dc.entidade.framework.FmMenu;
import dc.entidade.framework.FmModulo;
import dc.entidade.framework.PapelMenu;
import dc.visao.sistema.CustomFieldFactory;
import dc.visao.sistema.CustomTableFieldFactory;

public class PapelFormView extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Object NAME_PROPERTY = "nome";

	private ComboBox comboModulos;

	private HorizontalLayout linhaMenus;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;

	private BeanFieldGroup<PapelEntity> binder;

	private TextArea descricao;

	private HorizontalLayout linhaInicial;

	private TextField txtNome;

	private TreeTable treeTablePapeisMenu;

	private VerticalLayout vltTabela;

	private PapelFormController controller;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */

	private static Logger logger = Logger.getLogger(PapelFormView.class
			.getName());

	public PapelFormView(final PapelFormController controller) {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		this.controller = controller;

		linhaMenus = new HorizontalLayout();
		linhaMenus.setSpacing(true);

		linhaInicial = new HorizontalLayout();
		linhaInicial.setSpacing(true);

		mainLayout.setSpacing(true);
		mainLayout.setMargin(true);

		binder = new BeanFieldGroup<PapelEntity>(PapelEntity.class);
		binder.setItemDataSource(controller.getCurrentBean());
		binder.setFieldFactory(new CustomFieldFactory());

		this.txtNome = (TextField) binder.buildAndBind("Nome", "nome");
		txtNome.setWidth("100%");
		linhaInicial.addComponent(txtNome);

		mainLayout.addComponent(linhaInicial);

		descricao = new TextArea();
		descricao.setCaption("Descrição");
		descricao.setNullRepresentation("");
		descricao.setWidth("100%");
		descricao.setRows(2);
		binder.bind(descricao, "descricao");
		mainLayout.addComponent(descricao);

		buildTable(controller.getCurrentBean());

		this.comboModulos = new ComboBox("Módulo", new ArrayList<>());
		this.comboModulos.setItemCaptionPropertyId("caption");
		this.comboModulos.setNullSelectionAllowed(false);

		Button adicionaModuloBTN = new Button("Adicionar");
		adicionaModuloBTN.addClickListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				FmModulo modulo = (FmModulo) comboModulos.getValue();

				if (modulo != null
						&& !treeTablePapeisMenu.containsId(modulo.getCaption())) {
					if (modulo != null) {
						controller.loadModules(modulo);
					}
				}
			}

		});

		Button removeMenuBTN = new Button("Remover");
		removeMenuBTN.addClickListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				FmModulo modulo = (FmModulo) comboModulos.getValue();

				if (modulo != null
						&& treeTablePapeisMenu.containsId(modulo.getCaption())) {
					HierarchicalContainer container = (HierarchicalContainer) treeTablePapeisMenu
							.getContainerDataSource();
					container.removeItemRecursively(modulo.getCaption());
				}
			}

		});

		linhaMenus.addComponent(comboModulos);
		linhaMenus.addComponent(adicionaModuloBTN);
		linhaMenus.addComponent(removeMenuBTN);
		linhaMenus.setComponentAlignment(comboModulos, Alignment.BOTTOM_RIGHT);
		linhaMenus.setComponentAlignment(adicionaModuloBTN,
				Alignment.BOTTOM_RIGHT);
		linhaMenus.setComponentAlignment(removeMenuBTN, Alignment.BOTTOM_RIGHT);

		mainLayout.addComponent(linhaMenus);

		vltTabela = new VerticalLayout();
		vltTabela.setImmediate(false);
		vltTabela.setSizeFull();
		vltTabela.setMargin(false);
		vltTabela.setSpacing(true);
		vltTabela.addComponent(treeTablePapeisMenu);
		mainLayout.addComponent(vltTabela);

		// TODO: alternativa para ocupar a tela toda.
		mainLayout.setExpandRatio(vltTabela, 1);
	}

	public void buildTable(PapelEntity p) {
		treeTablePapeisMenu = new TreeTable();
		treeTablePapeisMenu.setSizeFull();
		treeTablePapeisMenu.setColumnCollapsingAllowed(true);
		treeTablePapeisMenu.setImmediate(true);
		treeTablePapeisMenu.setTableFieldFactory(new CustomTableFieldFactory(
				true));
		treeTablePapeisMenu.setEditable(true);
		treeTablePapeisMenu.addContainerProperty(NAME_PROPERTY, String.class,
				"");
		treeTablePapeisMenu.addContainerProperty(
				CustomTableFieldFactory.INSERE_PROPERTY, Character.class, null);
		treeTablePapeisMenu.addContainerProperty(
				CustomTableFieldFactory.ALTERA_PROPERTY, Character.class, null);
		treeTablePapeisMenu.addContainerProperty(
				CustomTableFieldFactory.CONSULTA_PROPERTY, Character.class,
				null);
		treeTablePapeisMenu.addContainerProperty(
				CustomTableFieldFactory.EXCLUI_PROPERTY, Character.class, null);
		treeTablePapeisMenu.addContainerProperty(
				CustomTableFieldFactory.HABILITA_PROPERTY, Character.class,
				null);
		treeTablePapeisMenu.addContainerProperty(
				CustomTableFieldFactory.OBJ_PROPERTY, PapelMenu.class, null);
		treeTablePapeisMenu.setVisibleColumns(new Object[] { NAME_PROPERTY,
				CustomTableFieldFactory.INSERE_PROPERTY,
				CustomTableFieldFactory.ALTERA_PROPERTY,
				CustomTableFieldFactory.CONSULTA_PROPERTY,
				CustomTableFieldFactory.EXCLUI_PROPERTY,
				CustomTableFieldFactory.HABILITA_PROPERTY,
				CustomTableFieldFactory.OBJ_PROPERTY });
		treeTablePapeisMenu.setColumnCollapsed(
				CustomTableFieldFactory.OBJ_PROPERTY, true);
		treeTablePapeisMenu.setColumnHeader(NAME_PROPERTY, "Menu");
		treeTablePapeisMenu.setColumnHeader(
				CustomTableFieldFactory.INSERE_PROPERTY, "Pode inserir");
		treeTablePapeisMenu.setColumnHeader(
				CustomTableFieldFactory.ALTERA_PROPERTY, "Pode alterar");
		treeTablePapeisMenu.setColumnHeader(
				CustomTableFieldFactory.CONSULTA_PROPERTY, "Pode consultar");
		treeTablePapeisMenu.setColumnHeader(
				CustomTableFieldFactory.EXCLUI_PROPERTY, "Pode excluir");
		treeTablePapeisMenu.setColumnHeader(
				CustomTableFieldFactory.HABILITA_PROPERTY, "Habilitado");
		treeTablePapeisMenu.setColumnExpandRatio(NAME_PROPERTY, 2.0f);
		treeTablePapeisMenu.setColumnExpandRatio(
				CustomTableFieldFactory.INSERE_PROPERTY, 1.0f);
		treeTablePapeisMenu.setColumnExpandRatio(
				CustomTableFieldFactory.ALTERA_PROPERTY, 1.0f);
		treeTablePapeisMenu.setColumnExpandRatio(
				CustomTableFieldFactory.CONSULTA_PROPERTY, 1.0f);
		treeTablePapeisMenu.setColumnExpandRatio(
				CustomTableFieldFactory.EXCLUI_PROPERTY, 1.0f);
		treeTablePapeisMenu.setColumnExpandRatio(
				CustomTableFieldFactory.HABILITA_PROPERTY, 1.0f);
		treeTablePapeisMenu.setColumnExpandRatio(
				CustomTableFieldFactory.OBJ_PROPERTY, 0);
		treeTablePapeisMenu.setCollapsed(CustomTableFieldFactory.OBJ_PROPERTY,
				true);
		treeTablePapeisMenu.addValueChangeListener(new ValueChangeListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(final ValueChangeEvent event) {

			}

		});
	}

	public void populaPapelMenu(List<PapelMenu> pms) {
		CustomTableFieldFactory f = (CustomTableFieldFactory) treeTablePapeisMenu
				.getTableFieldFactory();
		f.setCheckChanges(false);
		treeTablePapeisMenu.removeAllItems();
		treeTablePapeisMenu.getContainerDataSource().removeAllItems();

		for (PapelMenu pm : pms) {
			FmModulo modulo = pm.getMenu().getFmModulo();
			addToTree(pm, pm.getMenu(), modulo);
		}

		f.setCheckChanges(true);
	}

	private void addToTree(PapelMenu pm, FmMenu menu, FmModulo modulo) {
		Object moduloId = addModuloToTree(modulo);

		if (menu.getParentId() == null) {
			addModuloChild(moduloId, menu, pm);
		} else {
			addMenuChild(menu, pm);
		}
	}

	public void populaModulos(List<FmModulo> modulos) {
		BeanItemContainer<FmModulo> objects = new BeanItemContainer(
				FmModulo.class, modulos);
		this.comboModulos.setContainerDataSource(objects);
	}

	public BeanFieldGroup<PapelEntity> getBinder() {
		return this.binder;
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		return mainLayout;
	}

	public List<PapelMenu> getPapelMenus() {
		ArrayList<PapelMenu> papeisMenu = new ArrayList<PapelMenu>();

		for (Iterator i = treeTablePapeisMenu.getItemIds().iterator(); i
				.hasNext();) {
			Object iid = i.next();

			if (iid instanceof Integer) {
				FmMenu fm = new FmMenu((Integer) iid);

				Item data = treeTablePapeisMenu.getContainerDataSource()
						.getItem(iid);

				populatePapelMenuLis(papeisMenu, data, fm);
			} else if (iid instanceof FmModulo) {
				FmModulo fmModulo = (FmModulo) iid;

				Item data = treeTablePapeisMenu.getContainerDataSource()
						.getItem(iid);

				List<FmMenu> menus = controller.getFmMenus(fmModulo);

				for (FmMenu fm : menus) {
					populatePapelMenuLis(papeisMenu, data, fm);
				}
			}
		}

		return papeisMenu;
	}

	private void populatePapelMenuLis(final ArrayList<PapelMenu> papeisMenu,
			Item data, FmMenu fm) {
		Object pmValue = data.getItemProperty(
				CustomTableFieldFactory.OBJ_PROPERTY).getValue();
		PapelMenu pm = new PapelMenu(fm, binder.getItemDataSource().getBean());

		if (ObjectUtils.isNotBlank(pmValue)) {
			pm = (PapelMenu) pmValue;
			pm.setPapel(binder.getItemDataSource().getBean());
		}

		Object pValue = data.getItemProperty(
				CustomTableFieldFactory.ALTERA_PROPERTY).getValue();

		if (ObjectUtils.isNotBlank(pValue)) {
			Character altera = (Character) pValue;
			pm.setPodeAlterar(altera.equals('S') ? SimNaoEn.S : SimNaoEn.N);
		} else {
			pm.setPodeAlterar(SimNaoEn.N);
		}

		Object pValue2 = data.getItemProperty(
				CustomTableFieldFactory.INSERE_PROPERTY).getValue();

		if (ObjectUtils.isNotBlank(pValue2)) {
			Character insere = (Character) pValue2;
			pm.setPodeInserir(insere.equals('S') ? SimNaoEn.S : SimNaoEn.N);
		} else {
			pm.setPodeInserir(SimNaoEn.N);
		}

		Object pValue3 = data.getItemProperty(
				CustomTableFieldFactory.CONSULTA_PROPERTY).getValue();

		if (ObjectUtils.isNotBlank(pValue3)) {
			Character consulta = (Character) pValue3;
			pm.setPodeConsultar(consulta.equals('S') ? SimNaoEn.S : SimNaoEn.N);
		} else {
			pm.setPodeConsultar(SimNaoEn.N);
		}

		Object pValue4 = data.getItemProperty(
				CustomTableFieldFactory.EXCLUI_PROPERTY).getValue();

		if (ObjectUtils.isNotBlank(pValue4)) {
			Character exclui = (Character) pValue4;
			pm.setPodeExcluir(exclui.equals('S') ? SimNaoEn.S : SimNaoEn.N);
		} else {
			pm.setPodeExcluir(SimNaoEn.N);
		}

		Object pValue5 = data.getItemProperty(
				CustomTableFieldFactory.HABILITA_PROPERTY).getValue();

		if (ObjectUtils.isNotBlank(pValue5)) {
			Character habilitado = (Character) pValue5;
			pm.setHabilitado(habilitado.equals('S') ? SimNaoEn.S : SimNaoEn.N);
		} else {
			pm.setHabilitado(SimNaoEn.N);
		}

		if (!papeisMenu.contains(pm)) {
			papeisMenu.add(pm);
		}
	}

	public TextField getTxtNome() {
		return this.txtNome;
	}

	public AbstractComponent getTreeTable() {
		return treeTablePapeisMenu;
	}

	public void buildTree(List<FmMenu> menus, FmModulo modulo) {
		if (modulo != null) {
			Object moduloId = addModuloToTree(modulo);

			for (FmMenu m : menus) {
				PapelMenu p = new PapelMenu(m, binder.getItemDataSource()
						.getBean());
				if (m.getParentId() == null) {
					addModuloChild(moduloId, m, p);
				} else {
					addMenuChild(m, p);
				}
			}
		}
	}

	private void addMenuChild(FmMenu m, PapelMenu p) {
		if (!treeTablePapeisMenu.containsId(m.getId())) {
			Object itemId = treeTablePapeisMenu.addItem(
					new Object[] { p.getMenu().getCaption(),
							p.getPodeInserir(), p.getPodeAlterar(),
							p.getPodeConsultar(), p.getPodeExcluir(),
							p.getHabilitado(), p }, m.getId());
			treeTablePapeisMenu.setParent(itemId, p.getMenu().getParentId());
			treeTablePapeisMenu.setCollapsed(itemId, true);
		}
	}

	private void addModuloChild(Object moduloId, FmMenu m, PapelMenu p) {
		if (!treeTablePapeisMenu.containsId(m.getId())) {
			Object itemId = treeTablePapeisMenu.addItem(
					new Object[] { p.getMenu().getCaption(),
							p.getPodeInserir(), p.getPodeAlterar(),
							p.getPodeConsultar(), p.getPodeExcluir(),
							p.getHabilitado(), p }, m.getId());
			treeTablePapeisMenu.setParent(itemId, moduloId);
			treeTablePapeisMenu.setCollapsed(itemId, true);
		}
	}

	private Object addModuloToTree(FmModulo modulo) {
		if (!treeTablePapeisMenu.containsId(modulo)) {
			Object moduloId = treeTablePapeisMenu.addItem(
					new Object[] { modulo.getCaption(), null, null, null, null,
							null, null }, modulo);
			treeTablePapeisMenu.setChildrenAllowed(moduloId, true);
			treeTablePapeisMenu.setCollapsed(moduloId, true);

			return moduloId;
		}

		return modulo;
	}

}