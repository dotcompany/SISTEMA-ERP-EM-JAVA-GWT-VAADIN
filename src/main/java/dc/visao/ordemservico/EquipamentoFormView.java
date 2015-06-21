package dc.visao.ordemservico;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.visao.framework.util.ComponentUtil;

public class EquipamentoFormView extends CustomComponent {

	private static final long serialVersionUID = 1L;
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private GridLayout fields;

	@AutoGenerated
	private VerticalLayout mainLayout;
	
	@AutoGenerated
	private TextField tfFilial;
	
	@AutoGenerated
	private TextField tfEquipamento;

	@AutoGenerated
	private TextField tfDescricao;

	@AutoGenerated
	private TextArea taObservacao;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;

	@AutoGenerated
	private VerticalLayout verticalLayout_1;
	
	public EquipamentoFormView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {

		// common part: create layout
		setSizeFull();
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setSizeFull();
		mainLayout.setMargin(false);
		mainLayout.setSpacing(true);

		// top-level component properties
		setWidth("100.0%");
		// setHeight("100.0%");

		fields = buildFields();

		mainLayout.addComponent(fields);

		return mainLayout;
	}

	private GridLayout buildFields() {
		fields = new GridLayout();
		fields.setImmediate(false);
		fields.setWidth("100.0%");
		fields.setHeight("-1px");
		fields.setMargin(false);
		fields.setSpacing(true);
		fields.setRows(5);
		fields.setColumns(3);

		tfFilial = ComponentUtil.buildNumericField("Filial");
		fields.addComponent(tfFilial, 0, 1,0,1);

		
		tfEquipamento= ComponentUtil.buildTextField("Equipamento");
		fields.addComponent(tfEquipamento, 0, 2,1,2);

		tfDescricao= ComponentUtil.buildTextField("Descrição");
		fields.addComponent(tfDescricao, 0, 3,1,3);

		
		taObservacao= ComponentUtil.buildTextArea("Observação");
		fields.addComponent(taObservacao,0,4,1,4);
		
		return fields;
	}
	
	public GridLayout getFields() {
		return fields;
	}

	public void setFields(GridLayout fields) {
		this.fields = fields;
	}

	public VerticalLayout getMainLayout() {
		return mainLayout;
	}

	public void setMainLayout(VerticalLayout mainLayout) {
		this.mainLayout = mainLayout;
	}

	public TextArea getTaObservacao() {
		return taObservacao;
	}

	public void setTaObservacao(TextArea taObservacao) {
		this.taObservacao = taObservacao;
	}

	public TextField getTfFilial() {
		return tfFilial;
	}

	public void setTfFilial(TextField tfFilial) {
		this.tfFilial = tfFilial;
	}

	public TextField getTfEquipamento() {
		return tfEquipamento;
	}

	public void setTfEquipamento(TextField tfEquipamento) {
		this.tfEquipamento = tfEquipamento;
	}

	public TextField getTfDescricao() {
		return tfDescricao;
	}

	public void setTfDescricao(TextField tfDescricao) {
		this.tfDescricao = tfDescricao;
	}

	public VerticalLayout getVerticalLayout_1() {
		return verticalLayout_1;
	}

	public void setVerticalLayout_1(VerticalLayout verticalLayout_1) {
		this.verticalLayout_1 = verticalLayout_1;
	}

}

