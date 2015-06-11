package dc.visao.ordemservico;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.ordemservico.CarroFormController;
import dc.entidade.ordemservico.CombustivelEntity;
import dc.entidade.ordemservico.CorEntity;
import dc.entidade.ordemservico.MarcaOsEntity;
import dc.entidade.ordemservico.ModeloOsEntity;
import dc.entidade.geral.pessoal.ClienteEntity;
import dc.visao.framework.component.manytoonecombo.ManyToOneCombo;
import dc.visao.framework.util.ComponentUtil;

public class CarroFormView extends CustomComponent {

	private static final long serialVersionUID = 1L;
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private GridLayout fields;

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private TextField tfRazao;

	@AutoGenerated
	private TextField tfPlaca;

	@AutoGenerated
	private TextField tfAno;

	@AutoGenerated
	private TextField tfMotorizacao;

	@AutoGenerated
	private TextField tfChassi;

	@AutoGenerated
	private TextArea taObservacao;

	@AutoGenerated
	private ManyToOneCombo<ClienteEntity> cbCliente;

	@AutoGenerated
	private ManyToOneCombo<MarcaOsEntity> cbMarca;

	@AutoGenerated
	private ManyToOneCombo<CorEntity> cbCor;

	@AutoGenerated
	private ManyToOneCombo<ModeloOsEntity> cbModelo;

	@AutoGenerated
	private ManyToOneCombo<CombustivelEntity> cbCombustivel;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_2;

	private CarroFormController controller;

	public CarroFormView(CarroFormController controller) {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		this.controller = controller;
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

		setWidth("100.0%");
		fields = buildFields();

		mainLayout.addComponent(fields);

		return mainLayout;
	}

	private GridLayout buildFields() {
		fields = new GridLayout();
		fields.setImmediate(false);
		fields.setWidth("100.0%");
		fields.setMargin(false);
		fields.setSpacing(true);
		fields.setRows(7);
		fields.setColumns(6);

		cbCliente = new ManyToOneCombo<ClienteEntity>();
		cbCliente.setCaption("Cliente");
		cbCliente.setRequired(true);
		cbCliente.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				ClienteEntity cli = new ClienteEntity();
				cli = cbCliente.getValue();
				tfRazao.setValue(cli.getPessoa().getNome());
			}
			
		});
		fields.addComponent(cbCliente, 0,1,0,1);

		tfRazao = ComponentUtil.buildTextField("Razão");
		fields.addComponent(tfRazao,1,1,2,1);
		
		tfPlaca = ComponentUtil.buildMaskedTextField("Placa", "UUU-####");
		tfPlaca.setRequired(true);
		fields.addComponent(tfPlaca,0,2,0,2);
		
		cbMarca = new ManyToOneCombo<MarcaOsEntity>();
		cbMarca.setCaption("Marca");
		cbMarca.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				controller.getModelo("marca", cbMarca.getValue().getId());
			}
			
		});
		cbMarca.setRequired(true);
		fields.addComponent(cbMarca, 0, 3, 0, 3);

		cbModelo = new ManyToOneCombo<ModeloOsEntity>();
		cbModelo.setCaption("Modelo");
		cbModelo.setRequired(true);
		fields.addComponent(cbModelo, 1, 3, 1, 3);

		cbCor = new ManyToOneCombo<CorEntity>();
		cbCor.setCaption("Cor");
		cbCor.setRequired(true);
		fields.addComponent(cbCor, 0, 4, 0, 4);

		cbCombustivel = new ManyToOneCombo<CombustivelEntity>();
		cbCombustivel.setCaption("Combustível");
		cbCombustivel.setRequired(true);
		fields.addComponent(cbCombustivel, 1, 4, 1, 4);

		tfAno = new TextField();
		tfAno.setCaption("Ano");
		fields.addComponent(tfAno, 0, 5, 0, 5);

		tfMotorizacao = new TextField();
		tfMotorizacao.setCaption("Motorização");
		fields.addComponent(tfMotorizacao, 1, 5, 1, 5);

		tfChassi = new TextField();
		tfChassi.setCaption("Chassi");
		fields.addComponent(tfChassi,2,5,2,5);
		
		// txtDescricao
		taObservacao = ComponentUtil.buildTextArea("Observação");
		fields.addComponent(taObservacao,0,6);

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

	public TextField getTfPlaca() {
		return tfPlaca;
	}

	public void setTfPlaca(TextField tfPlaca) {
		this.tfPlaca = tfPlaca;
	}

	public ManyToOneCombo<MarcaOsEntity> getCbMarca() {
		return cbMarca;
	}

	public void setCbMarca(ManyToOneCombo<MarcaOsEntity> cbMarca) {
		this.cbMarca = cbMarca;
	}

	public ManyToOneCombo<CorEntity> getCbCor() {
		return cbCor;
	}

	public void setCbCor(ManyToOneCombo<CorEntity> cbCor) {
		this.cbCor = cbCor;
	}

	public ManyToOneCombo<ModeloOsEntity> getCbModelo() {
		return cbModelo;
	}

	public void setCbModelo(ManyToOneCombo<ModeloOsEntity> cbModelo) {
		this.cbModelo = cbModelo;
	}

	public TextField getTfAno() {
		return tfAno;
	}

	public void setTfAno(TextField tfAno) {
		this.tfAno = tfAno;
	}

	public TextField getTfChassi() {
		return tfChassi;
	}

	public void setTfChassi(TextField tfChassi) {
		this.tfChassi = tfChassi;
	}

	public TextArea getTaObservacao() {
		return taObservacao;
	}

	public void setTaObservacao(TextArea taObservacao) {
		this.taObservacao = taObservacao;
	}

	public ManyToOneCombo<CombustivelEntity> getCbCombustivel() {
		return cbCombustivel;
	}

	public void setCbCombustivel(ManyToOneCombo<CombustivelEntity> cbCombustivel) {
		this.cbCombustivel = cbCombustivel;
	}

	public TextField getTfMotorizacao() {
		return tfMotorizacao;
	}

	public void setTfMotorizacao(TextField tfMotorizacao) {
		this.tfMotorizacao = tfMotorizacao;
	}

	public ManyToOneCombo<ClienteEntity> getCbCliente() {
		return cbCliente;
	}

	public void setCbCliente(ManyToOneCombo<ClienteEntity> cbCliente) {
		this.cbCliente = cbCliente;
	}
}
