package dc.visao.geral.produto;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.geral.produto.NcmFormController;
import dc.visao.framework.util.ComponentUtil;

public class NcmFormView extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private GridLayout fields;

	@AutoGenerated
	private TextField tfObservacao;

	@AutoGenerated
	private TextField tfDescricao;

	@AutoGenerated
	private TextField tfCodigo;

	private NcmFormController controller;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public NcmFormView(NcmFormController controller) {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		this.controller = controller;
	}

	public TextField getTfObservacao() {
		return tfObservacao;
	}

	public void setTfObservacao(TextField tfObservacao) {
		this.tfObservacao = tfObservacao;
	}

	public TextField getTfDescricao() {
		return tfDescricao;
	}

	public void setTfDescricao(TextField tfDescricao) {
		this.tfDescricao = tfDescricao;
	}

	public TextField getTfCodigo() {
		return tfCodigo;
	}

	public void setTfCodigo(TextField tfCodigo) {
		this.tfCodigo = tfCodigo;
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
		fields.setRows(2);
		fields.setColumns(2);

		// tfCodigo
		tfCodigo = ComponentUtil.buildTextField("Código");
		tfCodigo.setImmediate(false);
		fields.addComponent(tfCodigo, 0,0);

		// tfDescricao
		tfDescricao = ComponentUtil.buildTextField("Descrição");
		tfDescricao.setImmediate(false);
		fields.addComponent(tfDescricao, 1,0);

		// tfObservacao
		tfObservacao = ComponentUtil.buildTextField("Observação");
		tfObservacao.setImmediate(false);
		fields.addComponent(tfObservacao,0,1);

		return fields;
	}

}