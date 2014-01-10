package dc.visao.financeiro;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;

import dc.entidade.contabilidade.ContabilConta;
import dc.visao.framework.component.manytoonecombo.ManyToOneCombo;

public class SindicatoFormView extends CustomComponent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private GridLayout mainLayout;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;
	@AutoGenerated
	private TextField txtNome;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_2;
	@AutoGenerated
	private ManyToOneCombo<ContabilConta> cmbContabilConta;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_3;
	@AutoGenerated
	private TextField txtCodigoBanco;
	@AutoGenerated
	private TextField txtCodigoAgencia;
	@AutoGenerated
	private TextField txtContaBanco;
	@AutoGenerated
	private TextField txtCodigoCedente;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_4;
	@AutoGenerated
	private TextField txtLogradouro;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_5;
	@AutoGenerated
	private TextField txtNumero;
	@AutoGenerated
	private TextField txtBairro;
	@AutoGenerated
	private TextField txtMunicipioIbge;
	@AutoGenerated
	private TextField txtTelefone1;
	@AutoGenerated
	private TextField txtTelefone2;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_6;
	@AutoGenerated
	private TextField txtEmail;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_7;
	@AutoGenerated
	private PopupDateField dtDataBase;
	@AutoGenerated
	private ComboBox cmbTipo;
	@AutoGenerated
	private TextField txtPisoSalarial;
	@AutoGenerated
	private TextField txtCnpj;

	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public SindicatoFormView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
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
		mainLayout.setRows(7);

		// top-level component properties
		setWidth("-1px");
		setHeight("-1px");

		// horizontalLayout_1
		horizontalLayout_1 = buildHorizontalLayout_1();
		mainLayout.addComponent(horizontalLayout_1, 0, 0);
		
		// horizontalLayout_2
		horizontalLayout_2 = buildHorizontalLayout_2();
		mainLayout.addComponent(horizontalLayout_2, 0, 1);
		
		// horizontalLayout_3
		horizontalLayout_3 = buildHorizontalLayout_3();
		mainLayout.addComponent(horizontalLayout_3, 0, 2);
		
		// horizontalLayout_4
		horizontalLayout_4 = buildHorizontalLayout_4();
		mainLayout.addComponent(horizontalLayout_4, 0, 3);
		
		// horizontalLayout_5
		horizontalLayout_5 = buildHorizontalLayout_5();
		mainLayout.addComponent(horizontalLayout_5, 0, 4);
		
		// horizontalLayout_6
		horizontalLayout_6 = buildHorizontalLayout_6();
		mainLayout.addComponent(horizontalLayout_6, 0, 5);
		
		// horizontalLayout_7
		horizontalLayout_7 = buildHorizontalLayout_7();
		mainLayout.addComponent(horizontalLayout_7, 0, 6);
		
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

		// txtNome
		txtNome = new TextField();
		txtNome.setCaption("Nome");
		txtNome.setImmediate(false);
		txtNome.setWidth("600px");
		txtNome.setHeight("-1px");
		horizontalLayout_1.addComponent(txtNome);


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

		// cmbContabilConta
		cmbContabilConta = new ManyToOneCombo<>();
		cmbContabilConta.setCaption("Contábil Conta");
		cmbContabilConta.setWidth("680px");
		cmbContabilConta.setHeight("-1px");
		horizontalLayout_2.addComponent(cmbContabilConta);

		return horizontalLayout_2;
	}
	
	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_3() {
		// common part: create layout
		horizontalLayout_3 = new HorizontalLayout();
		horizontalLayout_3.setImmediate(false);
		horizontalLayout_3.setWidth("-1px");
		horizontalLayout_3.setHeight("-1px");
		horizontalLayout_3.setMargin(false);
		horizontalLayout_3.setSpacing(true);

		// txtCodigoBanco
		txtCodigoBanco = new TextField();
		txtCodigoBanco.setCaption("Código Banco");
		txtCodigoBanco.setImmediate(false);
		txtCodigoBanco.setWidth("-1px");
		txtCodigoBanco.setHeight("-1px");
		horizontalLayout_3.addComponent(txtCodigoBanco);

		// txtCodigoAgencia
		txtCodigoAgencia = new TextField();
		txtCodigoAgencia.setCaption("Código Agência");
		txtCodigoAgencia.setImmediate(false);
		txtCodigoAgencia.setWidth("123px");
		txtCodigoAgencia.setHeight("-1px");
		horizontalLayout_3.addComponent(txtCodigoAgencia);

		// txtContaBanco
		txtContaBanco = new TextField();
		txtContaBanco.setCaption("Conta Banco");
		txtContaBanco.setImmediate(false);
		txtContaBanco.setWidth("134px");
		txtContaBanco.setHeight("-1px");
		horizontalLayout_3.addComponent(txtContaBanco);
		
		// txtCodigoCedente
		txtCodigoCedente = new TextField();
		txtCodigoCedente.setCaption("Código Cedente");
		txtCodigoCedente.setImmediate(false);
		txtCodigoCedente.setWidth("134px");
		txtCodigoCedente.setHeight("-1px");
		horizontalLayout_3.addComponent(txtCodigoCedente);

		return horizontalLayout_3;
	}
	
	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_4() {
		// common part: create layout
		horizontalLayout_4 = new HorizontalLayout();
		horizontalLayout_4.setImmediate(false);
		horizontalLayout_4.setWidth("-1px");
		horizontalLayout_4.setHeight("-1px");
		horizontalLayout_4.setMargin(false);
		horizontalLayout_4.setSpacing(true);

		// txtLogradouro
		txtLogradouro = new TextField();
		txtLogradouro.setCaption("Logradouro");
		txtLogradouro.setImmediate(false);
		txtLogradouro.setWidth("600px");
		txtLogradouro.setHeight("-1px");
		txtLogradouro.setRequired(true);
		horizontalLayout_4.addComponent(txtLogradouro);


		return horizontalLayout_4;
	}
	
	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_5() {
		// common part: create layout
		horizontalLayout_5 = new HorizontalLayout();
		horizontalLayout_5.setImmediate(false);
		horizontalLayout_5.setWidth("-1px");
		horizontalLayout_5.setHeight("-1px");
		horizontalLayout_5.setMargin(false);
		horizontalLayout_5.setSpacing(true);

		// txtNumero
		txtNumero = new TextField();
		txtNumero.setCaption("Número");
		txtNumero.setImmediate(false);
		txtNumero.setWidth("180px");
		txtNumero.setHeight("-1px");
		horizontalLayout_5.addComponent(txtNumero);
		
		// txtBairro
		txtBairro = new TextField();
		txtBairro.setCaption("Bairro");
		txtBairro.setImmediate(false);
		txtBairro.setWidth("280px");
		txtBairro.setHeight("-1px");
		horizontalLayout_5.addComponent(txtBairro);
		
		// txtMunicipioIbge
		txtMunicipioIbge = new TextField();
		txtMunicipioIbge.setCaption("Município IBGE");
		txtMunicipioIbge.setImmediate(false);
		txtMunicipioIbge.setWidth("180px");
		txtMunicipioIbge.setHeight("-1px");
		horizontalLayout_5.addComponent(txtMunicipioIbge);
		
		// txtTelefone1
		txtTelefone1 = new TextField();
		txtTelefone1.setCaption("Telefone 1");
		txtTelefone1.setImmediate(false);
		txtTelefone1.setWidth("180px");
		txtTelefone1.setHeight("-1px");
		horizontalLayout_5.addComponent(txtTelefone1);
		
		// txtTelefone2
		txtTelefone2 = new TextField();
		txtTelefone2.setCaption("Telefone 2");
		txtTelefone2.setImmediate(false);
		txtTelefone2.setWidth("180px");
		txtTelefone2.setHeight("-1px");
		horizontalLayout_5.addComponent(txtTelefone2);

		return horizontalLayout_5;
	}
	
	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_6() {
		// common part: create layout
		horizontalLayout_6 = new HorizontalLayout();
		horizontalLayout_6.setImmediate(false);
		horizontalLayout_6.setWidth("-1px");
		horizontalLayout_6.setHeight("-1px");
		horizontalLayout_6.setMargin(false);
		horizontalLayout_6.setSpacing(true);
		
		// txtEmail
		txtEmail = new TextField();
		txtEmail.setCaption("Email");
		txtEmail.setImmediate(false);
		txtEmail.setWidth("680px");
		txtEmail.setHeight("-1px");
		horizontalLayout_6.addComponent(txtEmail);
		
		return horizontalLayout_6;
		
	}
	
	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_7() {
		// common part: create layout
		horizontalLayout_7 = new HorizontalLayout();
		horizontalLayout_7.setImmediate(false);
		horizontalLayout_7.setWidth("-1px");
		horizontalLayout_7.setHeight("-1px");
		horizontalLayout_7.setMargin(false);
		horizontalLayout_7.setSpacing(true);
		
		// cmbTipo
		cmbTipo = new ComboBox();
		cmbTipo.setCaption("Tipo");
		cmbTipo.setWidth("180px");
		cmbTipo.setHeight("-1px");
		horizontalLayout_7.addComponent(cmbTipo);
		carregarTipo();
		
		// dtDataBase
		dtDataBase = new PopupDateField();
		dtDataBase.setCaption("Data Base");
		dtDataBase.setImmediate(false);
		dtDataBase.setWidth("-1px");
		dtDataBase.setHeight("-1px");
		horizontalLayout_7.addComponent(dtDataBase);
		
		// txtPisoSalarial
		txtPisoSalarial = new TextField();
		txtPisoSalarial.setCaption("Piso Salarial");
		txtPisoSalarial.setImmediate(false);
		txtPisoSalarial.setWidth("180px");
		txtPisoSalarial.setHeight("-1px");
		horizontalLayout_7.addComponent(txtPisoSalarial);
		
		// txtCnpj
		txtCnpj = new TextField();
		txtCnpj.setCaption("CNPJ");
		txtCnpj.setImmediate(false);
		txtCnpj.setWidth("180px");
		txtCnpj.setHeight("-1px");
		horizontalLayout_7.addComponent(txtCnpj);
		
	
		return horizontalLayout_7;
		
	}
	
	public void carregarTipo() {
		cmbTipo.removeAllItems();
		cmbTipo.addItem(TIPO.PATRONAL);
		cmbTipo.addItem(TIPO.EMPREGADOS);
	}
	
	public enum TIPO {

		PATRONAL("Patronal", "P"), EMPREGADOS("Empregados", "E");

		private TIPO(String label, String codigo) {
			this.label = label;
			this.codigo = codigo;
		}

		private String label;
		private String codigo;

		public static TIPO getValor(String codigo) {
			if (codigo.equals("P")) {
				return PATRONAL;
			}
			if (codigo.equals("E")) {
				return EMPREGADOS;
			}
			return null;
		}

		public String getCodigo() {
			return codigo;
		}

		public String getLabel() {
			return label;
		}

		@Override
		public String toString() {
			return label;
		}
	}

	public GridLayout getMainLayout() {
		return mainLayout;
	}

	public void setMainLayout(GridLayout mainLayout) {
		this.mainLayout = mainLayout;
	}

	public HorizontalLayout getHorizontalLayout_1() {
		return horizontalLayout_1;
	}

	public void setHorizontalLayout_1(HorizontalLayout horizontalLayout_1) {
		this.horizontalLayout_1 = horizontalLayout_1;
	}

	public TextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(TextField txtNome) {
		this.txtNome = txtNome;
	}

	public HorizontalLayout getHorizontalLayout_2() {
		return horizontalLayout_2;
	}

	public void setHorizontalLayout_2(HorizontalLayout horizontalLayout_2) {
		this.horizontalLayout_2 = horizontalLayout_2;
	}

	public ManyToOneCombo<ContabilConta> getCmbContabilConta() {
		return cmbContabilConta;
	}

	public void setCmbContabilConta(ManyToOneCombo<ContabilConta> cmbContabilConta) {
		this.cmbContabilConta = cmbContabilConta;
	}

	public HorizontalLayout getHorizontalLayout_3() {
		return horizontalLayout_3;
	}

	public void setHorizontalLayout_3(HorizontalLayout horizontalLayout_3) {
		this.horizontalLayout_3 = horizontalLayout_3;
	}

	public TextField getTxtCodigoBanco() {
		return txtCodigoBanco;
	}

	public void setTxtCodigoBanco(TextField txtCodigoBanco) {
		this.txtCodigoBanco = txtCodigoBanco;
	}

	public TextField getTxtCodigoAgencia() {
		return txtCodigoAgencia;
	}

	public void setTxtCodigoAgencia(TextField txtCodigoAgencia) {
		this.txtCodigoAgencia = txtCodigoAgencia;
	}

	public TextField getTxtContaBanco() {
		return txtContaBanco;
	}

	public void setTxtContaBanco(TextField txtContaBanco) {
		this.txtContaBanco = txtContaBanco;
	}

	public TextField getTxtCodigoCedente() {
		return txtCodigoCedente;
	}

	public void setTxtCodigoCedente(TextField txtCodigoCedente) {
		this.txtCodigoCedente = txtCodigoCedente;
	}

	public HorizontalLayout getHorizontalLayout_4() {
		return horizontalLayout_4;
	}

	public void setHorizontalLayout_4(HorizontalLayout horizontalLayout_4) {
		this.horizontalLayout_4 = horizontalLayout_4;
	}

	public TextField getTxtLogradouro() {
		return txtLogradouro;
	}

	public void setTxtLogradouro(TextField txtLogradouro) {
		this.txtLogradouro = txtLogradouro;
	}

	public HorizontalLayout getHorizontalLayout_5() {
		return horizontalLayout_5;
	}

	public void setHorizontalLayout_5(HorizontalLayout horizontalLayout_5) {
		this.horizontalLayout_5 = horizontalLayout_5;
	}

	public TextField getTxtNumero() {
		return txtNumero;
	}

	public void setTxtNumero(TextField txtNumero) {
		this.txtNumero = txtNumero;
	}

	public TextField getTxtBairro() {
		return txtBairro;
	}

	public void setTxtBairro(TextField txtBairro) {
		this.txtBairro = txtBairro;
	}

	public TextField getTxtMunicipioIbge() {
		return txtMunicipioIbge;
	}

	public void setTxtMunicipioIbge(TextField txtMunicipioIbge) {
		this.txtMunicipioIbge = txtMunicipioIbge;
	}

	public TextField getTxtTelefone1() {
		return txtTelefone1;
	}

	public void setTxtTelefone1(TextField txtTelefone1) {
		this.txtTelefone1 = txtTelefone1;
	}

	public TextField getTxtTelefone2() {
		return txtTelefone2;
	}

	public void setTxtTelefone2(TextField txtTelefone2) {
		this.txtTelefone2 = txtTelefone2;
	}

	public HorizontalLayout getHorizontalLayout_6() {
		return horizontalLayout_6;
	}

	public void setHorizontalLayout_6(HorizontalLayout horizontalLayout_6) {
		this.horizontalLayout_6 = horizontalLayout_6;
	}

	public TextField getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(TextField txtEmail) {
		this.txtEmail = txtEmail;
	}

	public HorizontalLayout getHorizontalLayout_7() {
		return horizontalLayout_7;
	}

	public void setHorizontalLayout_7(HorizontalLayout horizontalLayout_7) {
		this.horizontalLayout_7 = horizontalLayout_7;
	}

	public PopupDateField getDtDataBase() {
		return dtDataBase;
	}

	public void setDtDataBase(PopupDateField dtDataBase) {
		this.dtDataBase = dtDataBase;
	}

	public ComboBox getCmbTipo() {
		return cmbTipo;
	}

	public void setCmbTipo(ComboBox cmbTipo) {
		this.cmbTipo = cmbTipo;
	}

	public TextField getTxtPisoSalarial() {
		return txtPisoSalarial;
	}

	public void setTxtPisoSalarial(TextField txtPisoSalarial) {
		this.txtPisoSalarial = txtPisoSalarial;
	}

	public TextField getTxtCnpj() {
		return txtCnpj;
	}

	public void setTxtCnpj(TextField txtCnpj) {
		this.txtCnpj = txtCnpj;
	}
	
}
