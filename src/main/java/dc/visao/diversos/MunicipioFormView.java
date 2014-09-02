package dc.visao.diversos;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.visao.framework.util.ComponentUtil;

public class MunicipioFormView extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private GridLayout fields;
	

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private TextField txtCodigoEstadual;
	@AutoGenerated
	private TextField txtCodigoReceitaFederal;
	@AutoGenerated
	private TextField txtCodigoIbge;
	@AutoGenerated
	private TextField txtNome;
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public MunicipioFormView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
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
		fields.setRows(4);
		fields.setColumns(4);
		
		
		// txtNome
		txtNome = ComponentUtil.buildTextField("Nome");
		txtNome.setImmediate(false);
		txtNome.setWidth("440px");
		fields.addComponent(txtNome, 0, 0);
				
		// txtCodigoIbge
		txtCodigoIbge = ComponentUtil.buildTextField("Código IBGE");
		txtCodigoIbge.setImmediate(false);
		fields.addComponent(txtCodigoIbge, 0, 1);
		
		// txtCodigoReceitaFederal
		txtCodigoReceitaFederal = ComponentUtil.buildTextField("Código Receita Federal");
		txtCodigoReceitaFederal.setImmediate(false);
		fields.addComponent(txtCodigoReceitaFederal, 1, 1);
				
		// txtCodigoEstadual
		txtCodigoEstadual = ComponentUtil.buildTextField("Código Estadual");
		txtCodigoEstadual.setImmediate(false);
		fields.addComponent(txtCodigoEstadual, 2, 1);
		
		return fields;
	}
		

	public GridLayout getMainLayout() {
		return fields;
	}

	public void setMainLayout(GridLayout fields) {
		this.fields = fields;
	}

	public VerticalLayout getVerticalLayout_1() {
		return mainLayout;
	}

	public void setVerticalLayout_1(VerticalLayout mainLayout) {
		this.mainLayout = mainLayout;
	}

	public TextField getTxtCodigoEstadual() {
		return txtCodigoEstadual;
	}

	public void setTxtCodigoEstadual(TextField txtCodigoEstadual) {
		this.txtCodigoEstadual = txtCodigoEstadual;
	}

	public TextField getTxtCodigoReceitaFederal() {
		return txtCodigoReceitaFederal;
	}

	public void setTxtCodigoReceitaFederal(TextField txtCodigoReceitaFederal) {
		this.txtCodigoReceitaFederal = txtCodigoReceitaFederal;
	}

	public TextField getTxtCodigoIbge() {
		return txtCodigoIbge;
	}

	public void setTxtCodigoIbge(TextField txtCodigoIbge) {
		this.txtCodigoIbge = txtCodigoIbge;
	}

	public TextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(TextField txtNome) {
		this.txtNome = txtNome;
	}

}
