package dc.visao.geral.produto;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import dc.controller.geral.produto.NcmFormController;

public class NcmFormView extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private GridLayout mainLayout;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_2;

	@AutoGenerated
	private TextField tfObservacao;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;

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
	private GridLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new GridLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("-1px");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		mainLayout.setRows(2);

		// top-level component properties
		setWidth("-1px");

		// horizontalLayout_1
		horizontalLayout_1 = buildHorizontalLayout_1();
		mainLayout.addComponent(horizontalLayout_1, 0, 0);

		// horizontalLayout_2
		horizontalLayout_2 = buildHorizontalLayout_2();
		mainLayout.addComponent(horizontalLayout_2, 0, 1);

		return mainLayout;
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

		// tfCodigo
		tfCodigo = new TextField();
		tfCodigo.setCaption("Código");
		tfCodigo.setImmediate(false);
		tfCodigo.setWidth("-1px");
		tfCodigo.setHeight("-1px");
		tfCodigo.setRequired(true);
		horizontalLayout_1.addComponent(tfCodigo);

		// tfDescricao
		tfDescricao = new TextField();
		tfDescricao.setCaption("Descrição");
		tfDescricao.setImmediate(false);
		tfDescricao.setWidth("533px");
		tfDescricao.setHeight("-1px");
		tfDescricao.setRequired(true);
		horizontalLayout_1.addComponent(tfDescricao);

		return horizontalLayout_1;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_2() {
		// common part: create layout
		horizontalLayout_2 = new HorizontalLayout();
		horizontalLayout_2.setImmediate(false);
		horizontalLayout_2.setWidth("-1px");
		horizontalLayout_2.setHeight("-1px");
		horizontalLayout_2.setMargin(false);
		horizontalLayout_2.setSpacing(true);

		// tfObservacao
		tfObservacao = new TextField();
		tfObservacao.setCaption("Observação");
		tfObservacao.setImmediate(false);
		tfObservacao.setWidth("690px");
		tfObservacao.setHeight("-1px");
		horizontalLayout_2.addComponent(tfObservacao);

		return horizontalLayout_2;
	}

}