package dc.visao.diversos;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import dc.entidade.contabilidade.ContabilConta;
import dc.visao.framework.component.manytoonecombo.ManyToOneCombo;

public class OperadoraPlanoSaudeFormView extends CustomComponent {

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
	private TextField txtNome;

	@AutoGenerated
	private TextField txtRegistroANS;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;

	private ManyToOneCombo<ContabilConta> cmbContabilConta;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public OperadoraPlanoSaudeFormView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	@AutoGenerated
	private GridLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new GridLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("-1px");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		mainLayout.setRows(2);

		// top-level component properties
		setWidth("-1px");
		setHeight("-1px");

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

		// cmbContabilConta

		cmbContabilConta = new ManyToOneCombo<ContabilConta>();
		cmbContabilConta.setCaption("Contabil Conta");
		cmbContabilConta.setImmediate(false);
		cmbContabilConta.setWidth("640px");
		cmbContabilConta.setHeight("-1px");
		horizontalLayout_1.addComponent(cmbContabilConta);

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

		// txtRegistroANS
		txtRegistroANS = new TextField();
		txtRegistroANS.setCaption("Registro ANS");
		txtRegistroANS.setImmediate(false);
		txtRegistroANS.setWidth("-1px");
		txtRegistroANS.setHeight("-1px");
		txtRegistroANS.setRequired(true);
		horizontalLayout_2.addComponent(txtRegistroANS);

		// txtNome
		txtNome = new TextField();
		txtNome.setCaption("Nome");
		txtNome.setImmediate(false);
		txtNome.setWidth("393px");
		txtNome.setHeight("-1px");
		txtNome.setRequired(true);
		horizontalLayout_2.addComponent(txtNome);

		return horizontalLayout_2;
	}

	public GridLayout getMainLayout() {
		return mainLayout;
	}

	public void setMainLayout(GridLayout mainLayout) {
		this.mainLayout = mainLayout;
	}

	public HorizontalLayout getHorizontalLayout_2() {
		return horizontalLayout_2;
	}

	public void setHorizontalLayout_2(HorizontalLayout horizontalLayout_2) {
		this.horizontalLayout_2 = horizontalLayout_2;
	}

	public TextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(TextField txtNome) {
		this.txtNome = txtNome;
	}

	public TextField getTxtRegistroANS() {
		return txtRegistroANS;
	}

	public void setTxtRegistroANS(TextField txtRegistroANS) {
		this.txtRegistroANS = txtRegistroANS;
	}

	public HorizontalLayout getHorizontalLayout_1() {
		return horizontalLayout_1;
	}

	public void setHorizontalLayout_1(HorizontalLayout horizontalLayout_1) {
		this.horizontalLayout_1 = horizontalLayout_1;
	}

	public ManyToOneCombo<ContabilConta> getCmbContabilConta() {
		return cmbContabilConta;
	}

	public void setCmbContabilConta(
			ManyToOneCombo<ContabilConta> cmbContabilConta) {
		this.cmbContabilConta = cmbContabilConta;
	}

}