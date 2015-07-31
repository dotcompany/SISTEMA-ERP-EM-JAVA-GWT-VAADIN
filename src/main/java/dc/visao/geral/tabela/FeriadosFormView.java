package dc.visao.geral.tabela;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;

import dc.controller.geral.tabela.FeriadosFormController;
import dc.entidade.geral.diverso.UfEntity;
import dc.visao.framework.component.manytoonecombo.ManyToOneComboField;
import dc.visao.framework.util.ComponentUtil;

public class FeriadosFormView extends CustomComponent {

	@AutoGenerated
	private GridLayout mainLayout;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;
	@AutoGenerated
	private TextField txtAno;
	@AutoGenerated
	private TextField txtNome;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_2;
	@AutoGenerated
	private ComboBox cmbAbragencia;
	private ManyToOneComboField<UfEntity> cmbUf;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_3;
	@AutoGenerated
	private TextField txtMunicipioIbge;
	@AutoGenerated
	private ComboBox cmbTipo;
	@AutoGenerated
	private PopupDateField dtData;
	
	FeriadosFormController controller;
	

	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public FeriadosFormView(FeriadosFormController controller) {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		this.controller = controller;
	}

	@AutoGenerated
	private GridLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new GridLayout();
		mainLayout.setImmediate(false);
		//mainLayout.setWidth("-1px");
		mainLayout.setSizeFull();
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		mainLayout.setRows(4);
		
		// top-level component properties
		setWidth("100.0%");
		
		// horizontalLayout_1
		horizontalLayout_1 = buildHorizontalLayout_1();
		mainLayout.addComponent(horizontalLayout_1, 0, 0);
		
		// horizontalLayout_2
		horizontalLayout_2 = buildHorizontalLayout_2();
		mainLayout.addComponent(horizontalLayout_2, 0, 1);
		
		// horizontalLayout_3
		horizontalLayout_3 = buildHorizontalLayout_3();
		mainLayout.addComponent(horizontalLayout_3, 0, 2);
		
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
	
		// txtAno
		txtAno = ComponentUtil.buildTextField("Ano");
		txtAno.setNullRepresentation("");
		horizontalLayout_1.addComponent(txtAno);

		// txtNome
		txtNome = ComponentUtil.buildTextField("Nome");
		txtNome.setImmediate(false);
		txtNome.setWidth("656px");
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

		// cmbAbragencia
		cmbAbragencia = ComponentUtil.buildComboBox("Abrangência");
		cmbAbragencia.setHeight("-1px");
		horizontalLayout_2.addComponent(cmbAbragencia);
		carregarAbragencia();

		// cmbUf
		cmbUf = new ManyToOneComboField<>(UfEntity.class);
		cmbUf.setCaption("UF");
		horizontalLayout_2.addComponent(cmbUf);
		
		// cmbTipo
		cmbTipo = ComponentUtil.buildComboBox("Tipo");
		horizontalLayout_2.addComponent(cmbTipo);
		carregarTipo();
	
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

		// txtMunicipioIbge
		txtMunicipioIbge = ComponentUtil.buildTextField("Município IBGE");
		txtMunicipioIbge.setSizeFull();
		horizontalLayout_3.addComponent(txtMunicipioIbge);
		
		// dtData
		dtData = ComponentUtil.buildPopupDateField("Data Feriado");
		dtData.setImmediate(false);
		horizontalLayout_3.addComponent(dtData);

		return horizontalLayout_3;
	}
	
	public enum ABRAGENCIA {

		FEDERAL("Federal", "F"), ESTADUAL("Estadual", "E"), MUNICIPAL("Municipal", "M");

		private ABRAGENCIA(String label, String codigo) {
			this.label = label;
			this.codigo = codigo;
		}

		private String label;
		private String codigo;

		public static ABRAGENCIA getAbragencia(String codigo) {
			if (codigo.equals("F")) {
				return FEDERAL;
			}
			if (codigo.equals("E")) {
				return ESTADUAL;
			}
			if (codigo.equals("M")) {
				return MUNICIPAL;
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
	
	public void carregarAbragencia() {
		cmbAbragencia.removeAllItems();
		cmbAbragencia.addItem(ABRAGENCIA.FEDERAL);
		cmbAbragencia.addItem(ABRAGENCIA.ESTADUAL);
		cmbAbragencia.addItem(ABRAGENCIA.MUNICIPAL);
	}

	public enum TIPO {

		FIXO("Fixo", "F"), MOVEL("Móvel", "M");

		private TIPO(String label, String codigo) {
			this.label = label;
			this.codigo = codigo;
		}

		private String label;
		private String codigo;

		public static TIPO getTipo(String codigo) {
			if (codigo.equals("F")) {
				return FIXO;
			}
			if (codigo.equals("M")) {
				return MOVEL;
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
	
	public void carregarTipo() {
		cmbTipo.removeAllItems();
		cmbTipo.addItem(TIPO.FIXO);
		cmbTipo.addItem(TIPO.MOVEL);
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

	public TextField getTxtAno() {
		return txtAno;
	}

	public void setTxtAno(TextField txtAno) {
		this.txtAno = txtAno;
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

	public ComboBox getCmbAbragencia() {
		return cmbAbragencia;
	}

	public void setCmbAbragencia(ComboBox cmbAbragencia) {
		this.cmbAbragencia = cmbAbragencia;
	}

	public ManyToOneComboField<UfEntity> getCmbUf() {
		return cmbUf;
	}

	public void setCmbUf(ManyToOneComboField<UfEntity> cmbUf) {
		this.cmbUf = cmbUf;
	}

	public TextField getTxtMunicipioIbge() {
		return txtMunicipioIbge;
	}

	public void setTxtMunicipioIbge(TextField txtMunicipioIbge) {
		this.txtMunicipioIbge = txtMunicipioIbge;
	}

	public ComboBox getCmbTipo() {
		return cmbTipo;
	}

	public void setCmbTipo(ComboBox cmbTipo) {
		this.cmbTipo = cmbTipo;
	}

	public PopupDateField getDtData() {
		return dtData;
	}

	public void setDtData(PopupDateField dtData) {
		this.dtData = dtData;
	}

	public HorizontalLayout getHorizontalLayout_3() {
		return horizontalLayout_3;
	}

	public void setHorizontalLayout_3(HorizontalLayout horizontalLayout_3) {
		this.horizontalLayout_3 = horizontalLayout_3;
	}
	
}
