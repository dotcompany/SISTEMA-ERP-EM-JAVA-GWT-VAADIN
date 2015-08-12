package dc.visao.ordemservico;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class MarcaFormView extends CustomComponent {

	private static final long serialVersionUID = 1L;
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
 
	
	@AutoGenerated 
	private GridLayout mainLayout;
	@AutoGenerated
	private VerticalLayout verticalLayout_1;
	@AutoGenerated
	private TextField txtNome;
	
	public MarcaFormView() {
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
		
		// top-level component properties
		setWidth("-1px");
		setHeight("-1px");
		
		// verticalLayout_1
		verticalLayout_1 = buildVerticalLayout_1();
		mainLayout.addComponent(verticalLayout_1, 0, 0);
		
		return mainLayout;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_1() {
		// common part: create layout
		verticalLayout_1 = new VerticalLayout();
		verticalLayout_1.setImmediate(false);
		verticalLayout_1.setWidth("-1px");
		verticalLayout_1.setHeight("-1px");
		verticalLayout_1.setMargin(false);
		verticalLayout_1.setSpacing(true);
		
		// txtObservacao
		txtNome = new TextField();
		txtNome.setCaption("Nome");
		txtNome.setImmediate(false);
		txtNome.setWidth("640px");
		txtNome.setHeight("-1px");
		verticalLayout_1.addComponent(txtNome);
		
		return verticalLayout_1;
	}
	
	public GridLayout getMainLayout() {
		return mainLayout;
	}

	public void setMainLayout(GridLayout mainLayout) {
		this.mainLayout = mainLayout;
	}

	public VerticalLayout getVerticalLayout_1() {
		return verticalLayout_1;
	}

	public void setVerticalLayout_1(VerticalLayout verticalLayout_1) {
		this.verticalLayout_1 = verticalLayout_1;
	}

	public TextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(TextField txtNome) {
		this.txtNome = txtNome;
	}
}

