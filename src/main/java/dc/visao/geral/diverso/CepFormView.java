package dc.visao.geral.diverso;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.geral.diverso.CepFormController;
import dc.entidade.geral.UfEntity;
import dc.visao.framework.component.manytoonecombo.ManyToOneCombo;
import dc.visao.framework.util.ComponentUtil;

public class CepFormView extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private GridLayout fields;

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private TextField tfCodigoIbgeMunicipio;

	@AutoGenerated
	private TextField tfMunicipio;

	@AutoGenerated
	private TextField tfBairro;

	@AutoGenerated
	private TextField tfComplemento;

	@AutoGenerated
	private TextField tfLogradouro;

	@AutoGenerated
	private TextField tfCep;

	@AutoGenerated
	private ManyToOneCombo<UfEntity> mocUf;

	private CepFormController controller;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public CepFormView(CepFormController controller) {
		this.controller = controller;
		buildMainLayout();
		setCompositionRoot(mainLayout);

		this.controller = controller;
	}

	public TextField getTfCodigoIbgeMunicipio() {
		return tfCodigoIbgeMunicipio;
	}

	public void setTfCodigoIbgeMunicipio(TextField tfCodigoIbgeMunicipio) {
		this.tfCodigoIbgeMunicipio = tfCodigoIbgeMunicipio;
	}

	public TextField getTfMunicipio() {
		return tfMunicipio;
	}

	public void setTfMunicipio(TextField tfMunicipio) {
		this.tfMunicipio = tfMunicipio;
	}

	public TextField getTfBairro() {
		return tfBairro;
	}

	public void setTfBairro(TextField tfBairro) {
		this.tfBairro = tfBairro;
	}

	public TextField getTfComplemento() {
		return tfComplemento;
	}

	public void setTfComplemento(TextField tfComplemento) {
		this.tfComplemento = tfComplemento;
	}

	public TextField getTfLogradouro() {
		return tfLogradouro;
	}

	public void setTfLogradouro(TextField tfLogradouro) {
		this.tfLogradouro = tfLogradouro;
	}

	public TextField getTfCep() {
		return tfCep;
	}

	public void setTfCep(TextField tfCep) {
		this.tfCep = tfCep;
	}

	public ManyToOneCombo<UfEntity> getMocUf() {
		return mocUf;
	}

	public void setMocUf(ManyToOneCombo<UfEntity> mocUf) {
		this.mocUf = mocUf;
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
		fields.setColumns(5);

		// tfCep
		tfCep = ComponentUtil.buildTextField("CEP");
		tfCep.setImmediate(false);
		tfCep.setWidth("280px");
		tfCep.setHeight("-1px");
		tfCep.setSizeFull();
		fields.addComponent(tfCep, 0, 0);

		// tfLogradouro
		tfLogradouro = ComponentUtil.buildTextField("Logradouro");
		tfLogradouro.setWidth("594px");
		tfLogradouro.setHeight("-1px");
		tfLogradouro.setSizeFull();
		fields.addComponent(tfLogradouro, 0, 1);

		// tfComplemento
		tfComplemento = ComponentUtil.buildTextField("Complemento");
		tfComplemento.setImmediate(false);
		tfComplemento.setWidth("340px");
		tfComplemento.setHeight("-1px");
		tfComplemento.setSizeFull();
		fields.addComponent(tfComplemento, 0, 2);

		// tfBairro
		tfBairro = ComponentUtil.buildTextField("Bairro");
		tfBairro.setImmediate(false);
		tfBairro.setWidth("266px");
		tfBairro.setHeight("-1px");
		tfBairro.setSizeFull();
		fields.addComponent(tfBairro, 1, 2);

		// tfMunicipio
		tfMunicipio = ComponentUtil.buildTextField("Município");
		tfMunicipio.setImmediate(false);
		tfMunicipio.setWidth("262px");
		tfMunicipio.setHeight("-1px");
		tfMunicipio.setSizeFull();
		fields.addComponent(tfMunicipio, 2, 2);

		// mocUf
		mocUf = new ManyToOneCombo<>();
		mocUf.setCaption("UF");
		mocUf.setWidth("600px");
		mocUf.setHeight("-1px");
		fields.addComponent(mocUf, 0, 3);

		// tfCodigoIbgeMunicipio
		tfCodigoIbgeMunicipio = ComponentUtil.buildTextField("IBGE Município");
		tfCodigoIbgeMunicipio.setImmediate(false);
		tfCodigoIbgeMunicipio.setWidth("262px");
		tfCodigoIbgeMunicipio.setHeight("-1px");
		tfCodigoIbgeMunicipio.setSizeFull();
		fields.addComponent(tfCodigoIbgeMunicipio, 1, 3);

		return fields;
	}

}