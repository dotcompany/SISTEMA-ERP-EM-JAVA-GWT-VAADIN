package dc.visao.empresa;

import java.util.List;

import org.vaadin.addons.maskedtextfield.MaskedTextField;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Container;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.empresa.SocioFormController;
import dc.entidade.empresa.Dependente;
import dc.entidade.empresa.ParticipacaoSocietaria;
import dc.entidade.empresa.Socio;
import dc.entidade.geral.pessoal.TipoRelacionamentoEntity;
import dc.visao.framework.component.SubFormComponent;
import dc.visao.framework.util.ComponentUtil;

@SuppressWarnings("serial")
public class SocioFormView extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private GridLayout fields;

	SocioFormController controller;

	Socio currentBean;

	ComboBox cmbQuadroSocietario,cmbUF;
	
	TextField txtNome, txtCpf;

	TextField txtLogradouro, txtNumero, txtComplemento;

	TextField txtBairro, txtMunicipio, txtUf, txtCep;

	TextField txtFone,  txtEmail;

	MaskedTextField txtCelular;

	TextField txtParticipacao, txtQuotas, txtIntegralizar;

	PopupDateField dataIngresso, dataSaida;

	private SubFormComponent<Dependente, Integer> dependentesSubForm;

	private SubFormComponent<ParticipacaoSocietaria, Integer> participacoesSubForm;

	@AutoGenerated
	private TabSheet subForms;

	public SocioFormView(SocioFormController controller) {
		this.controller = controller;
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	@AutoGenerated
	private GridLayout buildFields() {
		// common part: create layout
		fields = new GridLayout(6, 6);
		fields.setImmediate(false);
		fields.setWidth("100.0%");
		fields.setHeight("-1px");
		fields.setMargin(false);
		fields.setSpacing(true);

		cmbQuadroSocietario = ComponentUtil.buildComboBox("Quadro Societário");
		cmbQuadroSocietario.setContainerDataSource(controller.carregarQuadros());
		cmbQuadroSocietario.setRequired(true);
		fields.addComponent(cmbQuadroSocietario, 0, 0);
		
		txtNome = ComponentUtil.buildTextField("Nome");
		fields.addComponent(txtNome, 1, 0, 2, 0);
		
		txtCpf = ComponentUtil.buildTextField("CPF");
		fields.addComponent(txtCpf, 3, 0);

		txtLogradouro = ComponentUtil.buildTextField("Logradouro");
		fields.addComponent(txtLogradouro, 0, 1, 1, 1);

		txtNumero = ComponentUtil.buildTextField("Número");
		fields.addComponent(txtNumero, 2, 1);

		txtComplemento = ComponentUtil.buildTextField("Complemento");
		fields.addComponent(txtComplemento, 3, 1, 4, 1);

		txtBairro = ComponentUtil.buildTextField("Bairro");
		fields.addComponent(txtBairro, 0, 2);

		txtMunicipio = ComponentUtil.buildTextField("Municipio");
		fields.addComponent(txtMunicipio, 1, 2);

		cmbUF = ComponentUtil.buildComboBox("UF");
		cmbUF.setContainerDataSource(controller.carregarUFs());
		fields.addComponent(cmbUF, 2, 2);

		txtCep = ComponentUtil.buildMaskedTextField("CEP","##.###-###");
		fields.addComponent(txtCep, 3, 2,4,2);

		txtFone = ComponentUtil.buildMaskedTextField("Fone","(##)####-####");
		fields.addComponent(txtFone, 0, 3);

		txtCelular = ComponentUtil.buildMaskedTextField("Celular","(##)####-####");
		fields.addComponent(txtCelular, 1, 3);

		txtEmail = ComponentUtil.buildTextField("Email");
		fields.addComponent(txtEmail, 2, 3, 4, 3);

		txtParticipacao = ComponentUtil.buildCurrencyField("Participação");
		fields.addComponent(txtParticipacao, 0, 4);

		txtQuotas = ComponentUtil.buildNumericField("Quotas");
		fields.addComponent(txtQuotas, 1, 4);

		txtIntegralizar = ComponentUtil.buildCurrencyField("Valor a Integralizar");
		fields.addComponent(txtIntegralizar, 2, 4,3,4);

		dataIngresso = ComponentUtil.buildPopupDateField("Data Ingresso");
		fields.addComponent(dataIngresso, 4, 4);

		dataSaida = ComponentUtil.buildPopupDateField("Data Saida");
		fields.addComponent(dataSaida, 5, 4);

		return fields;
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setSizeFull();
		mainLayout.setMargin(false);
		mainLayout.setSpacing(true);

		// top-level component properties
		setWidth("100.0%");
		setHeight("110.0%");

		// fields
		fields = buildFields();
		mainLayout.addComponent(fields);
		subForms = new TabSheet();
		subForms.setWidth("100.0%");
		subForms.setHeight("30.0%");
		subForms.setSizeFull();
		subForms.setImmediate(true);
		montaAbaDependentes();
		montaAbaParticipacao();
		mainLayout.addComponent(subForms);

		return mainLayout;
	}

	public VerticalLayout getMainLayout() {
		return mainLayout;
	}

	public void setMainLayout(VerticalLayout mainLayout) {
		this.mainLayout = mainLayout;
	}

	public GridLayout getFields() {
		return fields;
	}

	public void setFields(GridLayout fields) {
		this.fields = fields;
	}

	public SocioFormController getController() {
		return controller;
	}

	public void setController(SocioFormController controller) {
		this.controller = controller;
	}

	public Socio getCurrentBean() {
		return currentBean;
	}

	public void setCurrentBean(Socio currentBean) {
		this.currentBean = currentBean;
	}

	public ComboBox getCmbQuadroSocietario() {
		return cmbQuadroSocietario;
	}

	public void setCmbQuadroSocietario(ComboBox cmbQuadroSocietario) {
		this.cmbQuadroSocietario = cmbQuadroSocietario;
	}
	
	public TextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(TextField txtNome) {
		this.txtNome = txtNome;
	}

	public TextField getTxtCpf() {
		return txtCpf;
	}

	public void setTxtCpf(TextField txtCpf) {
		this.txtCpf = txtCpf;
	}

	public TextField getTxtLogradouro() {
		return txtLogradouro;
	}

	public void setTxtLogradouro(TextField txtLogradouro) {
		this.txtLogradouro = txtLogradouro;
	}

	public TextField getTxtNumero() {
		return txtNumero;
	}

	public void setTxtNumero(TextField txtNumero) {
		this.txtNumero = txtNumero;
	}

	public TextField getTxtComplemento() {
		return txtComplemento;
	}

	public void setTxtComplemento(TextField txtComplemento) {
		this.txtComplemento = txtComplemento;
	}

	public TextField getTxtBairro() {
		return txtBairro;
	}

	public void setTxtBairro(TextField txtBairro) {
		this.txtBairro = txtBairro;
	}

	public TextField getTxtMunicipio() {
		return txtMunicipio;
	}

	public void setTxtMunicipio(TextField txtMunicipio) {
		this.txtMunicipio = txtMunicipio;
	}

	public TextField getTxtUf() {
		return txtUf;
	}

	public void setTxtUf(TextField txtUf) {
		this.txtUf = txtUf;
	}

	public TextField getTxtCep() {
		return txtCep;
	}

	public void setTxtCep(TextField txtCep) {
		this.txtCep = txtCep;
	}

	public TextField getTxtFone() {
		return txtFone;
	}

	public void setTxtFone(TextField txtFone) {
		this.txtFone = txtFone;
	}



	public MaskedTextField getTxtCelular() {
		return txtCelular;
	}

	public void setTxtCelular(MaskedTextField txtCelular) {
		this.txtCelular = txtCelular;
	}

	public TextField getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(TextField txtEmail) {
		this.txtEmail = txtEmail;
	}

	public TextField getTxtParticipacao() {
		return txtParticipacao;
	}

	public void setTxtParticipacao(TextField txtParticipacao) {
		this.txtParticipacao = txtParticipacao;
	}

	public TextField getTxtQuotas() {
		return txtQuotas;
	}

	public void setTxtQuotas(TextField txtQuotas) {
		this.txtQuotas = txtQuotas;
	}

	public TextField getTxtIntegralizar() {
		return txtIntegralizar;
	}

	public void setTxtIntegralizar(TextField txtIntegralizar) {
		this.txtIntegralizar = txtIntegralizar;
	}

	public PopupDateField getDataIngresso() {
		return dataIngresso;
	}

	public void setDataIngresso(PopupDateField dataIngresso) {
		this.dataIngresso = dataIngresso;
	}

	public PopupDateField getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(PopupDateField dataSaida) {
		this.dataSaida = dataSaida;
	}
	
	public ComboBox getCmbUF() {
		return cmbUF;
	}

	public void setCmbUF(ComboBox cmbUF) {
		this.cmbUF = cmbUF;
	}

	public void montaAbaDependentes() {
		TabSheet sub = new TabSheet();
		
		GridLayout layout = new GridLayout(1, 1);
		layout.setImmediate(false);
		layout.setWidth("100.0%");
		layout.setHeight("20.0%");
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setSizeFull();
		layout.addComponent(buildDependentesSubForm());
		subForms.addTab(layout, "Dependentes", null);
	}

	public void montaAbaParticipacao() {
		TabSheet sub = new TabSheet();
		sub.setWidth("100.0%");
		sub.setHeight("100.0%");
		sub.setSizeFull();
		sub.setImmediate(true);
		GridLayout layout = new GridLayout(1, 1);
		layout.setImmediate(false);
		layout.setWidth("100.0%");
		layout.setHeight("20.0%");
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setSizeFull();
		layout.addComponent(buildParticipacoesSubForm());
		subForms.addTab(layout, "Participação Societária", null);
	}

	private SubFormComponent<Dependente, Integer> buildDependentesSubForm() {
		// common part: create layout
		TabSheet sub = new TabSheet();
		sub.setWidth("100.0%");
		sub.setHeight("50.0%");
		sub.setSizeFull();
		sub.setImmediate(true);
		sub.addTab(dependentesSubForm, "", null);

		dependentesSubForm = new SubFormComponent<Dependente, Integer>(
				Dependente.class, new String[] { "tipoRelacionamento","nome","dataNascimento","dataInicioDependencia","dataFimDependencia","cpf" },
				new String[] { "Tipo Relacionamento","Nome","Data de Nascimento","Data Inicio Dependência","Data Fim Dependência","CPF" }) {

			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					@Override
					public Field<?> createField(Container container,
							Object itemId, Object propertyId,
							Component uiContext) {

						if ("tipoRelacionamento".equals(propertyId)) {
							ComboBox combo = ComponentUtil.buildComboBox("Tipo Relacionamento");

							for(TipoRelacionamentoEntity t : controller.carregarTipoRelacionamento()){
								combo.addItem(t);
							}
							return combo;
						}


						if ("nome".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildTextField("Nome");
							return textField;
						}

						if ("dataNascimento".equals(propertyId)) {
							PopupDateField textField = ComponentUtil
									.buildPopupDateField("Data Nascimento");
							return textField;
						}

						if ("dataInicioDependencia".equals(propertyId)) {
							PopupDateField textField = ComponentUtil
									.buildPopupDateField("Data Inicio Dependência");
							return textField;
						}

						if ("dataFimDependencia".equals(propertyId)) {
							PopupDateField textField = ComponentUtil
									.buildPopupDateField("Data Fim Dependência");
							return textField;
						}

						if ("cpf".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildMaskedTextField("CPF","###.###.##-##");
							return textField;
						}

						return null;
					}
				};
			}

			protected Dependente getNovo() {
				Dependente detalhe = controller.adicionarDependente();
				return detalhe;
			}

			@Override
			public boolean validateItems(List<Dependente> items) {
				// TODO Auto-generated method stub
				return true;
			}
		};

		return dependentesSubForm;
	}

	private SubFormComponent<ParticipacaoSocietaria, Integer> buildParticipacoesSubForm() {
		// common part: create layout
		TabSheet sub = new TabSheet();

		participacoesSubForm = new SubFormComponent<ParticipacaoSocietaria, Integer>(
				ParticipacaoSocietaria.class, new String[] { "cnpj","razaoSocial","dataEntrada" ,"dataSaida","participacao","dirigenteEnum" },
				new String[] { "CNPJ","Razão Social","Data Entrada","Data Saida","Participação","Dirigente" }) {

			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					@Override
					public Field<?> createField(Container container,
							Object itemId, Object propertyId,
							Component uiContext) {

						if ("cnpj".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildMaskedTextField("CNPJ","##.###.###/####-##");
							return textField;
						}

						if ("razaoSocial".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildTextField("Razão Social");
							return textField;
						}

						if ("dataEntrada".equals(propertyId)) {
							PopupDateField textField = ComponentUtil
									.buildPopupDateField("Data Entrada");
							return textField;
						}

						if ("dataSaida".equals(propertyId)) {
							PopupDateField textField = ComponentUtil
									.buildPopupDateField("Data Saida");
							return textField;
						}

						if ("participacao".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildCurrencyField("Participação");
							return textField;
						}

						if ("dirigenteEnum".equals(propertyId)) {
							ComboBox combo = ComponentUtil
									.buildComboBox("Dirigente");
							combo.addItem(DIRIGENTE.NAO);
							combo.addItem(DIRIGENTE.SIM);
							return combo;
						}

						return null;
					}
				};
			}

			protected ParticipacaoSocietaria getNovo() {
				ParticipacaoSocietaria detalhe = controller
						.adicionarParticipacao();
				return detalhe;
			}

			@Override
			public boolean validateItems(List<ParticipacaoSocietaria> items) {
				// TODO Auto-generated method stub
				return true;
			}
		};

		return participacoesSubForm;
	}

	public enum DIRIGENTE{

		NAO("0","Não"),SIM("1","Sim");

		String label,codigo;

		private DIRIGENTE(String codigo,String label){
			this.codigo = codigo;
			this.label= label;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public String getCodigo() {
			return codigo;
		}

		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}

		public static DIRIGENTE getDirigente(String codigo) {
			if (codigo.equals("0")) {
				return NAO;
			}
			if (codigo.equals("1")) {
				return SIM;
			} 

			return null;
		}

	}
	
	public void preencherSubFormDependente(List<Dependente> lista) {
		this.dependentesSubForm.fillWith(lista);

	}
	
	public void preencherSubFormParticipacao(List<ParticipacaoSocietaria> lista) {
		this.participacoesSubForm.fillWith(lista);

	}

	public SubFormComponent<ParticipacaoSocietaria, Integer> getParticipacoesSubForm() {
		return participacoesSubForm;
	}

	public void setParticipacoesSubForm(
			SubFormComponent<ParticipacaoSocietaria, Integer> participacoesSubForm) {
		this.participacoesSubForm = participacoesSubForm;
	}
	
	
}