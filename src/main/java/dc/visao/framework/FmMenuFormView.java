package dc.visao.framework;

import java.util.List;

import com.sun.istack.logging.Logger;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.entidade.framework.FmMenu;
import dc.entidade.framework.FmModulo;

/**
 * 
 * @author Wesley Jr /* Nessa classe temos a Tela (DESIGN), onde criamos o
 *         campos que a Tela contém tudo que ela precisa ter
 * 
 */

public class FmMenuFormView extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private Panel panel_1;

	@AutoGenerated
	private VerticalLayout verticalLayout_2;

	@AutoGenerated
	private GridLayout gridLayout_1;

	@AutoGenerated
	private TextField txtController;

	@AutoGenerated
	private OptionGroup tipoMenu;

	@AutoGenerated
	private HorizontalLayout layoutBase;

	@AutoGenerated
	private TextField txtURL;

	@AutoGenerated
	private TextField txtCaption;

	@AutoGenerated
	private ComboBox cbMenu;

	@AutoGenerated
	private ComboBox cbModulo;

	@AutoGenerated
	private CheckBox ckbPermissaoOperacao;

	@AutoGenerated
	private CheckBox ckbConsultaMultiempresa;

	private CheckBox ckbConsultaFilterTable;

	Logger logger = Logger.getLogger(FmMenuFormView.class);

	private FmMenuFormController controller;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public FmMenuFormView(final FmMenuFormController controller) {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		this.controller = controller;

		/*
		 * cbModulo.setItemCaptionPropertyId("caption");
		 * cbModulo.setImmediate(true); cbModulo.setNullSelectionAllowed(false);
		 * cbModulo.addValueChangeListener(new ValueChangeListener() {
		 * 
		 * @Override public void valueChange(ValueChangeEvent event) { FmModulo
		 * m = (FmModulo) event.getProperty().getValue();
		 * logger.info("Modulo changed:" + m.getCaption()); //
		 * controller.carregaComboMenus(m); } });
		 * 
		 * cbMenu.setVisible(false); cbMenu.setItemCaptionPropertyId("caption");
		 * cbMenu.setNullSelectionAllowed(false); cbMenu.setImmediate(true);
		 * 
		 * tipoMenu.addItem(FILHO_MODULO); tipoMenu.addItem(FILHO_MENU);
		 * tipoMenu.setImmediate(true);
		 * 
		 * txtController.setVisible(false);
		 * 
		 * tipoMenu.addValueChangeListener(new ValueChangeListener() {
		 * 
		 * @Override public void valueChange(ValueChangeEvent event) { if
		 * (event.getProperty().getValue() == FILHO_MENU) {
		 * cbMenu.setVisible(true); txtController.setVisible(true); } else if
		 * (event.getProperty().getValue() == FILHO_MODULO) {
		 * cbMenu.setVisible(false); txtController.setVisible(false);
		 * txtController.setValue(""); }
		 * 
		 * } });
		 */
	}

	public OptionGroup getTipoMenu() {
		return tipoMenu;
	}

	public void setTipoMenu(OptionGroup tipoMenu) {
		this.tipoMenu = tipoMenu;
	}

	public TextField getTxtURL() {
		return txtURL;
	}

	public void setTxtURL(TextField txtURL) {
		this.txtURL = txtURL;
	}

	public TextField getTxtCaption() {
		return txtCaption;
	}

	public void setTxtCaption(TextField txtCaption) {
		this.txtCaption = txtCaption;
	}

	public ComboBox getCbMenu() {
		return cbMenu;
	}

	public void setCbMenu(ComboBox cbMenu) {
		this.cbMenu = cbMenu;
	}

	public ComboBox getCbModulo() {
		return cbModulo;
	}

	public void setCbModulo(ComboBox cbModulo) {
		this.cbModulo = cbModulo;
	}

	public TextField getTxtController() {
		return txtController;
	}

	public void setTxtController(TextField txtController) {
		this.txtController = txtController;
	}

	public CheckBox getCkbPermissaoOperacao() {
		return ckbPermissaoOperacao;
	}

	public void setCkbPermissaoOperacao(CheckBox ckbPermissaoOperacao) {
		this.ckbPermissaoOperacao = ckbPermissaoOperacao;
	}

	public CheckBox getCkbConsultaMultiempresa() {
		return ckbConsultaMultiempresa;
	}

	public void setCkbConsultaMultiempresa(CheckBox ckbConsultaMultiempresa) {
		this.ckbConsultaMultiempresa = ckbConsultaMultiempresa;
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

		// panel_1
		panel_1 = buildPanel_1();
		mainLayout.addComponent(panel_1);

		return mainLayout;
	}

	@AutoGenerated
	private Panel buildPanel_1() {
		// common part: create layout
		panel_1 = new Panel();
		panel_1.setImmediate(false);
		panel_1.setWidth("100.0%");
		panel_1.setHeight("100.0%");

		// verticalLayout_2
		verticalLayout_2 = buildVerticalLayout_2();
		panel_1.setContent(verticalLayout_2);

		return panel_1;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_2() {
		// common part: create layout
		verticalLayout_2 = new VerticalLayout();
		verticalLayout_2.setImmediate(false);
		verticalLayout_2.setWidth("100.0%");
		verticalLayout_2.setHeight("100.0%");
		verticalLayout_2.setMargin(false);

		// gridLayout_1
		gridLayout_1 = buildGridLayout_1();
		verticalLayout_2.addComponent(gridLayout_1);

		return verticalLayout_2;
	}

	@AutoGenerated
	private GridLayout buildGridLayout_1() {
		// common part: create layout
		gridLayout_1 = new GridLayout();
		gridLayout_1.setImmediate(false);
		gridLayout_1.setWidth("100.0%");
		// gridLayout_1.setHeight("100.0%");
		gridLayout_1.setMargin(false);
		gridLayout_1.setSpacing(true);
		gridLayout_1.setRows(20);
		gridLayout_1.setColumns(2);

		// txtCaption
		txtCaption = new TextField();
		txtCaption.setCaption("Caption:");
		txtCaption.setImmediate(false);
		txtCaption.setWidth("-1px");
		txtCaption.setHeight("-1px");
		txtCaption.setSizeFull();
		gridLayout_1.addComponent(txtCaption, 0, 1);

		// txtURL
		txtURL = new TextField();
		txtURL.setCaption("Identificador URL:");
		txtURL.setImmediate(false);
		txtURL.setWidth("-1px");
		txtURL.setHeight("-1px");
		txtURL.setSizeFull();
		gridLayout_1.addComponent(txtURL, 1, 1);

		// ckbPermissaoOperacao
		ckbPermissaoOperacao = new CheckBox();
		ckbPermissaoOperacao.setCaption("Não Permitir Alteração no (CRUD):");
		ckbPermissaoOperacao.setImmediate(false);
		ckbPermissaoOperacao.setWidth("-1px");
		ckbPermissaoOperacao.setHeight("-1px");
		gridLayout_1.addComponent(ckbPermissaoOperacao, 0, 3);

		// ckbConsultaMultiempresa
		ckbConsultaMultiempresa = new CheckBox();
		ckbConsultaMultiempresa.setCaption("Permitir Consulta Multiempresa:");
		ckbConsultaMultiempresa.setImmediate(false);
		ckbConsultaMultiempresa.setWidth("-1px");
		ckbConsultaMultiempresa.setHeight("-1px");
		gridLayout_1.addComponent(ckbConsultaMultiempresa, 1, 3);

		// ckbPermissaoOperacao
		ckbConsultaFilterTable = new CheckBox();
		ckbConsultaFilterTable
				.setCaption("Permitir Filtrar Registros Na Tabela:");
		ckbConsultaFilterTable.setImmediate(false);
		ckbConsultaFilterTable.setWidth("-1px");
		ckbConsultaFilterTable.setHeight("-1px");
		gridLayout_1.addComponent(ckbConsultaFilterTable, 0, 4);

		// cbModulo
		cbModulo = new ComboBox();
		cbModulo.setCaption("Módulo Pai");
		cbModulo.setWidth("-1px");
		cbModulo.setHeight("-1px");
		cbModulo.setNullSelectionAllowed(false);
		cbModulo.setImmediate(true);
		cbModulo.setSizeFull();
		cbModulo.addValueChangeListener(new ValueChangeListener() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				if (event.getProperty().getValue() != null) {
					controller.menu((FmModulo) event.getProperty().getValue());
				}
			}
		});
		gridLayout_1.addComponent(cbModulo, 0, 5);

		// tipoMenu
		tipoMenu = new OptionGroup();
		tipoMenu.setCaption("Tipo de menu");
		tipoMenu.setWidth("-1px");
		tipoMenu.setHeight("-1px");
		tipoMenu.setImmediate(true);
		tipoMenu.addValueChangeListener(new ValueChangeListener() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				controller.verificarTipo(event);
			}
		});
		gridLayout_1.addComponent(tipoMenu, 0, 6);

		// cbMenu
		cbMenu = new ComboBox();
		cbMenu.setCaption("Menu Pai");
		cbMenu.setImmediate(false);
		cbMenu.setWidth("-1px");
		cbMenu.setHeight("-1px");
		cbMenu.setSizeFull();
		gridLayout_1.addComponent(cbMenu, 0, 7);

		// txtController
		txtController = new TextField();
		txtController.setCaption("Classe do Controller");
		txtController.setImmediate(false);
		txtController.setWidth("-1px");
		txtController.setHeight("-1px");
		txtController.setSizeFull();
		gridLayout_1.addComponent(txtController, 0, 8);

		return gridLayout_1;
	}

	/**
	 * COMBOS
	 */

	public void carregarCmbModulo(List<FmModulo> lista) {
		BeanItemContainer<FmModulo> bic = new BeanItemContainer<FmModulo>(
				FmModulo.class, lista);
		this.cbModulo.setContainerDataSource(bic);
		this.cbModulo.setItemCaptionPropertyId("caption");
	}

	public void carregarCmbMenu(List<FmMenu> lista) {
		BeanItemContainer<FmMenu> bic = new BeanItemContainer<FmMenu>(
				FmMenu.class, lista);
		this.cbMenu.setContainerDataSource(bic);
		this.cbMenu.setItemCaptionPropertyId("caption");
	}

	public CheckBox getCkbConsultaFilterTable() {
		return ckbConsultaFilterTable;
	}

	public void setCkbConsultaFilterTable(CheckBox ckbConsultaFilterTable) {
		this.ckbConsultaFilterTable = ckbConsultaFilterTable;
	}

}