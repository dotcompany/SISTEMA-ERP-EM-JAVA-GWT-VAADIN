package dc.visao.empresa;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.empresa.EmpresaCnaeFormController;
import dc.entidade.empresa.EmpresaCnae;
import dc.entidade.geral.Cnae;
import dc.visao.framework.util.ComponentUtil;
import dc.visao.suprimentos.NotaFiscalFormView.TIPO_OPERACAO;

@SuppressWarnings("serial")
public class EmpresaCnaeFormView extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private GridLayout fields;

	ComboBox cmbCnae, cmbPrincipal;

	TextField txtRamoAtividade;

	TextArea txtObjetoSocial;

	@Autowired
	EmpresaCnaeFormController controller;

	EmpresaCnae currentBean;

	public EmpresaCnaeFormView(EmpresaCnaeFormController controller) {
		this.controller = controller;
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	@AutoGenerated
	private GridLayout buildFields() {
		// common part: create layout
		fields = new GridLayout(6, 4);
		fields.setImmediate(false);
		fields.setWidth("100.0%");
		fields.setHeight("-1px");
		fields.setMargin(false);
		fields.setSpacing(true);

		cmbCnae = ComponentUtil.buildComboBox("Cnae");
		cmbCnae.setRequired(true);
		montarComboCnae();
		fields.addComponent(cmbCnae, 0, 0);

		cmbPrincipal = ComponentUtil.buildComboBox("Principal");
		cmbPrincipal.setRequired(true);
		cmbPrincipal.addItem(PRINCIPAL.NAO);
		cmbPrincipal.addItem(PRINCIPAL.SIM);
		fields.addComponent(cmbPrincipal, 0, 1);

		txtRamoAtividade = ComponentUtil.buildTextField("Ramo de Atividade");
		txtRamoAtividade.setRequired(true);
		fields.addComponent(txtRamoAtividade, 1, 1, 3, 1);

		txtObjetoSocial = ComponentUtil.buildTextArea("Objeto Social");
		txtObjetoSocial.setRequired(true);
		fields.addComponent(txtObjetoSocial, 0, 2, 3, 2);

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
		setHeight("70.0%");

		// fields
		fields = buildFields();
		mainLayout.addComponent(fields);

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

	public EmpresaCnaeFormController getController() {
		return controller;
	}

	public void setController(EmpresaCnaeFormController controller) {
		this.controller = controller;
	}

	public EmpresaCnae getCurrentBean() {
		return currentBean;
	}

	public void setCurrentBean(EmpresaCnae currentBean) {
		this.currentBean = currentBean;
	}

	public ComboBox getCmbCnae() {
		return cmbCnae;
	}

	public void setCmbCnae(ComboBox cmbCnae) {
		this.cmbCnae = cmbCnae;
	}

	public ComboBox getCmbPrincipal() {
		return cmbPrincipal;
	}

	public void setCmbPrincipal(ComboBox cmbPrincipal) {
		this.cmbPrincipal = cmbPrincipal;
	}

	public TextField getTxtRamoAtividade() {
		return txtRamoAtividade;
	}

	public void setTxtRamoAtividade(TextField txtRamoAtividade) {
		this.txtRamoAtividade = txtRamoAtividade;
	}

	public TextArea getTxtObjetoSocial() {
		return txtObjetoSocial;
	}

	public void setTxtObjetoSocial(TextArea txtObjetoSocial) {
		this.txtObjetoSocial = txtObjetoSocial;
	}
	
	public void montarComboCnae(){
		for(Cnae c : controller.trazerListaCnae()){
			cmbCnae.addItem(c);
		}
	}
	
	public enum PRINCIPAL{
		
		NAO("0","Não"),SIM("1","Sim");
		
		String label,codigo;
		
		private PRINCIPAL(String codigo,String label){
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
		
		public static PRINCIPAL getPrincipal(String codigo) {
			if (codigo.equals("0")) {
				return NAO;
			}
			if (codigo.equals("1")) {
				return SIM;
			} 

			return null;
		}
		
		
		
		
	}

}