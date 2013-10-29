package dc.visao.contabilidade.cadastro;

import java.util.List;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Container;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.contabilidade.cadastro.IndiceFormController;
import dc.controller.contabilidade.cadastro.IndiceValorFormController;
import dc.entidade.contabilidade.cadastro.IndiceValorEntity;
import dc.visao.framework.component.SubFormComponent;
import dc.visao.framework.util.ComponentUtil;

public class IndiceFormView extends CustomComponent {

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
	private TextField tfPeriodicidade;

	@AutoGenerated
	private PopupDateField pdfDiarioPartirDe;

	@AutoGenerated
	private TextField tfMensalMesAno;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private IndiceFormController controller;

	public IndiceFormView(final IndiceFormController controller) {
		this.controller = controller;

		buildMainLayout();
		setCompositionRoot(this.mainLayout);
	}

	public TextField getTfPeriodicidade() {
		return tfPeriodicidade;
	}

	public void setTfPeriodicidade(TextField tfPeriodicidade) {
		this.tfPeriodicidade = tfPeriodicidade;
	}

	public PopupDateField getPdfDiarioPartirDe() {
		return pdfDiarioPartirDe;
	}

	public void setPdfDiarioPartirDe(PopupDateField pdfDiarioPartirDe) {
		this.pdfDiarioPartirDe = pdfDiarioPartirDe;
	}

	public TextField getTfMensalMesAno() {
		return tfMensalMesAno;
	}

	public void setTfMensalMesAno(TextField tfMensalMesAno) {
		this.tfMensalMesAno = tfMensalMesAno;
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

		// buildDetalhesSubForm();

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

		// gridLayout_2
		// gridLayout_2 = buildGridLayout_2();
		// verticalLayout_2.addComponent(gridLayout_2);

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

		// tfPeriodicidade
		tfPeriodicidade = new TextField();
		tfPeriodicidade.setCaption("Periodicidade:");
		tfPeriodicidade.setImmediate(false);
		tfPeriodicidade.setWidth("175px");
		tfPeriodicidade.setHeight("-1px");
		tfPeriodicidade.setSizeFull();
		tfPeriodicidade.setNullRepresentation("");
		gridLayout_1.addComponent(tfPeriodicidade, 0, 1);

		// pdfDiarioPartirDe
		pdfDiarioPartirDe = new PopupDateField();
		pdfDiarioPartirDe.setCaption("Diário a partir de:");
		pdfDiarioPartirDe.setImmediate(false);
		pdfDiarioPartirDe.setWidth("175px");
		pdfDiarioPartirDe.setHeight("-1px");
		pdfDiarioPartirDe.setSizeFull();
		gridLayout_1.addComponent(pdfDiarioPartirDe, 1, 1);

		// tfMensalMesAno
		tfMensalMesAno = new TextField();
		tfMensalMesAno.setCaption("Mensal mês / ano:");
		tfMensalMesAno.setImmediate(false);
		tfMensalMesAno.setWidth("175px");
		tfMensalMesAno.setHeight("-1px");
		tfMensalMesAno.setSizeFull();
		tfMensalMesAno.setNullRepresentation("");
		gridLayout_1.addComponent(tfMensalMesAno, 0, 2);

		return gridLayout_1;
	}

	/**
	 * *******************************************
	 */

	/**
	 * 
	 */

	@AutoGenerated
	private GridLayout gridLayout_2;

	@AutoGenerated
	private TabSheet subForms;

	private SubFormComponent<IndiceValorEntity, Integer> detalhesSubForm;
	@AutoGenerated
	private PopupDateField pdfDataIndice;

	@AutoGenerated
	private TextField tfValor;

	private GridLayout buildGridLayout_2() {
		// common part: create layout
		gridLayout_2 = new GridLayout();
		gridLayout_2.setImmediate(false);
		gridLayout_2.setWidth("100.0%");
		gridLayout_2.setHeight("100.0%");
		gridLayout_2.setMargin(true);
		gridLayout_2.setSpacing(true);
		gridLayout_2.setRows(20);
		gridLayout_2.setColumns(1);
		// gridLayout_2.setSizeFull();

		// pdfDataIndice
		pdfDataIndice = new PopupDateField();
		pdfDataIndice.setCaption("Data do índice:");
		pdfDataIndice.setImmediate(false);
		pdfDataIndice.setWidth("175px");
		pdfDataIndice.setHeight("-1px");
		pdfDataIndice.setSizeFull();
		gridLayout_2.addComponent(pdfDataIndice, 0, 0);

		// tfValor
		tfValor = new TextField();
		tfValor.setCaption("Valor:");
		tfValor.setImmediate(false);
		tfValor.setWidth("175px");
		tfValor.setHeight("-1px");
		tfValor.setSizeFull();
		tfValor.setNullRepresentation("");
		gridLayout_2.addComponent(tfValor, 0, 1);

		// common part: create layout

		detalhesSubForm = new SubFormComponent<IndiceValorEntity, Integer>(
				IndiceValorEntity.class, new String[] { "valor" },
				new String[] { "Valor" }) {

			/**
			 * 
			 */

			private static final long serialVersionUID = 1L;

			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public Field<?> createField(Container container,
							Object itemId, Object propertyId,
							Component uiContext) {
						if ("valor".equals(propertyId)) {
							TextField field = ComponentUtil
									.buildTextField("Valor");
							field.setMaxLength(1);

							return field;
						}

						return null;
					}
				};
			}

			protected IndiceValorEntity getNovo() {
				IndiceValorFormController ivfc = new IndiceValorFormController();
				ivfc.criarNovo();
				// IndiceValorEntity detalhe = controller.novoDetalhe();
				return null;
			}

			@Override
			public boolean validateItems(List<IndiceValorEntity> items) {
				// TODO Auto-generated method stub
				return true;
			}

		};

		gridLayout_2.addComponent(detalhesSubForm, 0, 2);

		// subForms
		subForms = new TabSheet();
		subForms.setWidth("100.0%");
		subForms.setHeight("100.0%");
		subForms.setSizeFull();
		subForms.addTab(gridLayout_2, "Índice valor", null);

		return gridLayout_2;
	}

	public void preencheSubForm(List<IndiceValorEntity> detalhes) {
		detalhesSubForm.fillWith(detalhes);
	}

}