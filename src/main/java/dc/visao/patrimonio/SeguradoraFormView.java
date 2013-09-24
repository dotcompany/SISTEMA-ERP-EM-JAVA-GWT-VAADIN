package dc.visao.patrimonio;

import org.vaadin.addons.maskedtextfield.MaskedTextField;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.patrimonio.SeguradoraFormController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

public class SeguradoraFormView extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private Panel panel_1;

	@AutoGenerated
	private VerticalLayout verticalLayout_2;

	@AutoGenerated
	private GridLayout gridLayout_1;

	@AutoGenerated
	private MaskedTextField tfTelefone;

	@AutoGenerated
	private TextField tfContato;

	@AutoGenerated
	private TextField tfNome;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private SeguradoraFormController controller;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public SeguradoraFormView(final SeguradoraFormController controller) {
		this.controller = controller;

		buildMainLayout();
		setCompositionRoot(this.mainLayout);
	}

	public TextField getTfNome() {
		return tfNome;
	}

	public void setTfNome(TextField tfNome) {
		this.tfNome = tfNome;
	}

	public TextField getTfContato() {
		return tfContato;
	}

	public void setTfContato(TextField tfContato) {
		this.tfContato = tfContato;
	}

	public MaskedTextField getTfTelefone() {
		return tfTelefone;
	}

	public void setTfTelefone(MaskedTextField tfTelefone) {
		this.tfTelefone = tfTelefone;
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
		gridLayout_1.setColumns(3);

		// tfNome
		tfNome = new TextField();
		tfNome.setCaption("Nome:");
		tfNome.setNullRepresentation("");
		tfNome.setImmediate(false);
		tfNome.setWidth("160px");
		tfNome.setHeight("-1px");
		tfNome.setRequired(true);
		tfNome.setSizeFull();
		gridLayout_1.addComponent(tfNome, 0, 1);

		// tfContato
		tfContato = new TextField();
		tfContato.setCaption("Contato:");
		tfContato.setNullRepresentation("");
		tfContato.setImmediate(false);
		tfContato.setWidth("160px");
		tfContato.setHeight("-1px");
		tfContato.setRequired(true);
		tfContato.setSizeFull();
		gridLayout_1.addComponent(tfContato, 1, 1);

		// tfTelefone
		tfTelefone = new MaskedTextField();
		tfTelefone.setCaption("Telefone:");
		tfTelefone.setNullRepresentation("");
		tfTelefone.setMask("(##) ####-####");
		tfTelefone.setImmediate(false);
		tfTelefone.setWidth("160px");
		tfTelefone.setHeight("-1px");
		gridLayout_1.addComponent(tfTelefone, 0, 2);

		return gridLayout_1;
	}

	/**
	 * COMBOS
	 */

}