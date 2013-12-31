package dc.visao.framework.geral;



import com.vaadin.annotations.AutoGenerated;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.framework.SearchTextFieldExtension;
import dc.visao.framework.component.export.ExcelExporter;
import dc.visao.framework.component.export.PdfExporter;

/**
*
* @author Wesley Jr
/*
 * Nessa classe temos a Tela (DESIGN), onde tem todos os botões nessa classe
 * 
 *
*/

public class CRUDListView extends CustomComponent implements View{

    @AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private VerticalLayout vltTabela;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_2;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_3;
	
	@AutoGenerated
	private HorizontalLayout horizontalLayout_4;
	
	@AutoGenerated
	private HorizontalLayout horizontalLayout_5;
	
	@AutoGenerated
	private Button btnFechar;

	@AutoGenerated
	private Button btnDeleteSelected;

	@AutoGenerated
	private Button btnCriar;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;

	@AutoGenerated
	private Button btnPesquisa;

	@AutoGenerated
	private TextField txtPesquisa;

	@AutoGenerated
	private Label lblNome;

	private CRUDListController controller;
	
	private ExcelExporter excelExporter;
	
	private PdfExporter pdfExporter;
	
	

	
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public CRUDListView(CRUDListController controller) {
		setSizeFull();
		this.controller = controller;
		buildMainLayout();
		setCompositionRoot(mainLayout);
		txtPesquisa.setImmediate(true);
		new SearchTextFieldExtension(controller).extend(txtPesquisa);
		
	}


	public Button getBtnPesquisa() {
		return btnPesquisa;
	}

	public void setBtnPesquisa(Button btnPesquisa) {
		this.btnPesquisa = btnPesquisa;
	}

	public TextField getTxtPesquisa() {
		return txtPesquisa;
	}

	public void setTxtPesquisa(TextField txtPesquisa) {
		this.txtPesquisa = txtPesquisa;
	}

	public Label getLblNome() {
		return lblNome;
	}

	public void setLblNome(Label lblNome) {
		this.lblNome = lblNome;
	}

	public VerticalLayout getMainLayout() {
		return mainLayout;
	}

	public void setMainLayout(VerticalLayout mainLayout) {
		this.mainLayout = mainLayout;
	}

	public VerticalLayout getVltTabela() {
		return vltTabela;
	}

	public void setVltTabela(VerticalLayout vltTabela) {
		this.vltTabela = vltTabela;
	}

	public Button getBtnCriar() {
		return btnCriar;
	}

	public void setBtnCriar(Button btnCriar) {
		this.btnCriar = btnCriar;
	}
	
	public Button getBtnFechar() {
		return btnFechar;
	}

	public void setBtnFechar(Button btnFechar) {
		this.btnFechar = btnFechar;
	}

	public Button getBtnRemover() {
		// TODO Auto-generated method stub
		return btnDeleteSelected;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		controller.show(this);
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		
		
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// horizontalLayout_2
		horizontalLayout_2 = buildHorizontalLayout_2();
		mainLayout.addComponent(horizontalLayout_2);
		
		horizontalLayout_4 = buildHorizontalLayout_4();
		mainLayout.addComponent(horizontalLayout_4);
		
		// vltTabela
		vltTabela = new VerticalLayout();
		vltTabela.setImmediate(false);
		vltTabela.setWidth("100.0%");
		vltTabela.setHeight("100.0%");
		vltTabela.setMargin(false);
		mainLayout.addComponent(vltTabela);
		mainLayout.setExpandRatio(vltTabela, 4.0f);
		
		return mainLayout;
	}
	
	
	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_4() {
		// common part: create layout
		horizontalLayout_4 = new HorizontalLayout();
		horizontalLayout_4.setImmediate(false);
		horizontalLayout_4.setWidth("-1px");
		horizontalLayout_4.setHeight("-1px");
		horizontalLayout_4.setMargin(true);
		horizontalLayout_4.setSpacing(true);
		
		
		/*PopupButton popupButton = new PopupButton("Exportar Dados");
		popupButton.setIcon(new ThemeResource("img/export.png"));

		GridLayout gl = new GridLayout(2, 1);
		gl.setWidth("150px");
		gl.setHeight("100px");
		
		excelExporter = new ExcelExporter();
		 excelExporter.setCaption("   Excel");
		 excelExporter.setIcon(new ThemeResource("img/iconExcel.png"));
		 
		 excelExporter.addClickListener(new Button.ClickListener() {
			    public void buttonClick(ClickEvent event) {
			    	excelExporter.setTableToBeExported(controller.getTable());
			    }
			});
		 
		
		
		gl.addComponent(excelExporter);
		
		 pdfExporter = new PdfExporter();
		 pdfExporter.setCaption("   PDF");
		 pdfExporter.setIcon(new ThemeResource("img/iconPDF.gif"));
		 pdfExporter.addClickListener(new Button.ClickListener() {
			    public void buttonClick(ClickEvent event) {
			    	pdfExporter.setTableToBeExported(controller.getTable());
			    	
			    }
			});
		
		gl.addComponent(pdfExporter);
		
		popupButton.setContent(gl);
		
		horizontalLayout_4.addComponent(popupButton);*/
		
		
		/**
		 * cria as opcoes para exportar pra PDF, EXCEL.
		 */
		
		 excelExporter = new ExcelExporter();
		 excelExporter.setCaption("   Exportar Excel");
		 excelExporter.setIcon(new ThemeResource("img/iconExcel.png"));
		 excelExporter.setDownloadFileName(controller.getTitulo()+".xls");
		 
		 excelExporter.addClickListener(new Button.ClickListener() {
			    public void buttonClick(ClickEvent event) {
			    	excelExporter.setTableToBeExported(controller.getTable());
			    }
			});
		 
		 
		 horizontalLayout_4.addComponent(excelExporter);

		 pdfExporter = new PdfExporter();
		 pdfExporter.setCaption("   Exportar PDF");
		 pdfExporter.setIcon(new ThemeResource("img/iconPDF.gif"));
		 pdfExporter.setDownloadFileName(controller.getTitulo()+".pdf");
		 pdfExporter.addClickListener(new Button.ClickListener() {
			    public void buttonClick(ClickEvent event) {
			    	pdfExporter.setTableToBeExported(controller.getTable());
			    	
			    }
			});
		 
		 horizontalLayout_4.addComponent(pdfExporter);
	
		
		return horizontalLayout_4;
	}
	
	
	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_2() {
		// common part: create layout
		horizontalLayout_2 = new HorizontalLayout();
		horizontalLayout_2.setImmediate(false);
		horizontalLayout_2.setWidth("-1px");
		horizontalLayout_2.setHeight("-1px");
		horizontalLayout_2.setMargin(true);
		horizontalLayout_2.setSpacing(true);
		
		// lblNome
		lblNome = new Label();
		lblNome.setStyleName("h2");
		lblNome.setImmediate(false);
		lblNome.setWidth("-1px");
		lblNome.setHeight("-1px");
		lblNome.setValue("Titulo");
		horizontalLayout_2.addComponent(lblNome);
		horizontalLayout_2.setExpandRatio(lblNome, 5.0f);
		horizontalLayout_2.setComponentAlignment(lblNome, new Alignment(33));
		
		// horizontalLayout_1
		horizontalLayout_1 = buildHorizontalLayout_1();
		horizontalLayout_2.addComponent(horizontalLayout_1);
		horizontalLayout_2.setExpandRatio(horizontalLayout_1, 3.0f);
		horizontalLayout_2.setComponentAlignment(horizontalLayout_1,
				new Alignment(33));
		
		// horizontalLayout_3
		horizontalLayout_3 = buildHorizontalLayout_3();
		horizontalLayout_2.addComponent(horizontalLayout_3);
		horizontalLayout_2.setExpandRatio(horizontalLayout_3, 2.0f);
		horizontalLayout_2.setComponentAlignment(horizontalLayout_3,
				new Alignment(34));
		
		return horizontalLayout_2;
	}


	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_1() {
		// common part: create layout
		horizontalLayout_1 = new HorizontalLayout();
		horizontalLayout_1.setImmediate(false);
		horizontalLayout_1.setWidth("-1px");
		horizontalLayout_1.setHeight("-1px");
		horizontalLayout_1.setMargin(false);
		horizontalLayout_1.setSpacing(true);
		
		// txtPesquisa
		txtPesquisa = new TextField();
		txtPesquisa.setImmediate(false);
		txtPesquisa.setWidth("100.0%");
		txtPesquisa.setHeight("-1px");
		horizontalLayout_1.addComponent(txtPesquisa);
		horizontalLayout_1.setComponentAlignment(txtPesquisa, new Alignment(6));
		
		// btnPesquisa
		btnPesquisa = new Button();
		btnPesquisa.setCaption("Pesquisar");
		btnPesquisa.setImmediate(true);
		btnPesquisa.setWidth("-1px");
		btnPesquisa.setHeight("-1px");
		horizontalLayout_1.addComponent(btnPesquisa);
		horizontalLayout_1.setComponentAlignment(btnPesquisa, new Alignment(9));
		
		return horizontalLayout_1;
	}


	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_3() {
		// common part: create layout
		horizontalLayout_3 = new HorizontalLayout();
		horizontalLayout_3.setImmediate(false);
		horizontalLayout_3.setWidth("-1px");
		horizontalLayout_3.setHeight("-1px");
		horizontalLayout_3.setMargin(false);
		
		// btnCriar
		btnCriar = new Button();
		btnCriar.setCaption("Criar");
		btnCriar.setImmediate(true);
		btnCriar.setWidth("-1px");
		btnCriar.setHeight("-1px");
		horizontalLayout_3.addComponent(btnCriar);
		
		// btnDeleteSelected
		btnDeleteSelected = new Button();
		btnDeleteSelected.setCaption("Remover Selecionados");
		btnDeleteSelected.setImmediate(true);
		btnDeleteSelected.setWidth("-1px");
		btnDeleteSelected.setHeight("-1px");
		horizontalLayout_3.addComponent(btnDeleteSelected);
		
		// btnFechar
				btnFechar = new Button();
				btnFechar.setCaption("Fechar");
				btnFechar.setImmediate(true);
				btnFechar.setWidth("-5px");
				btnFechar.setHeight("-1px");
				horizontalLayout_3.addComponent(btnFechar);
				horizontalLayout_3.setComponentAlignment(btnFechar, new Alignment(35));
		
		return horizontalLayout_3;
	}
	
	/*@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_5() {
		// common part: create layout
		horizontalLayout_5 = new HorizontalLayout();
		horizontalLayout_5.setImmediate(false);
		horizontalLayout_5.setWidth("-1px");
		horizontalLayout_5.setHeight("-1px");
		horizontalLayout_5.setMargin(false);
		horizontalLayout_1.setSpacing(true);
		
		// btnFechar
		btnFechar = new Button();
		btnFechar.setCaption("Fechar");
		btnFechar.setImmediate(true);
		btnFechar.setWidth("-1px");
		btnFechar.setHeight("-1px");
		horizontalLayout_5.addComponent(btnFechar);
		horizontalLayout_5.setComponentAlignment(btnFechar, new Alignment(20));
		
		return horizontalLayout_5;
	}*/


}