package dc.visao.financeiro;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.financeiro.FluxoCaixaPeriodoFormController;
import dc.entidade.financeiro.ContaCaixa;
import dc.visao.framework.component.manytoonecombo.ManyToOneComboField;
import dc.visao.framework.util.ComponentUtil;

public class FluxoCaixaPeriodoFormView extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;
	
	@AutoGenerated
	private GridLayout fields;
	
	@AutoGenerated
	private TextField txNome, txNrPeriodo;
	
	@AutoGenerated
	private ManyToOneComboField<ContaCaixa> cbContaCaixa;
	
	FluxoCaixaPeriodoFormController controller;

	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public FluxoCaixaPeriodoFormView(FluxoCaixaPeriodoFormController controller) {
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
		fields.setRows(7);
		fields.setColumns(7);
		
		cbContaCaixa= new ManyToOneComboField<>(ContaCaixa.class);
		cbContaCaixa.setCaption("Conta Caixa");
		fields.addComponent(cbContaCaixa, 0, 0, 1, 0);
		
		txNome = ComponentUtil.buildTextField("Nome");
		fields.addComponent(txNome, 0, 1, 1,1);
		
		txNrPeriodo = ComponentUtil.buildTextField("Nr. Períodos");
		fields.addComponent(txNrPeriodo, 0, 2);
		
		return fields;
		
	}

	public TextField getTxNome() {
		return txNome;
	}

	public void setTxNome(TextField txNome) {
		this.txNome = txNome;
	}

	public TextField getTxNrPeriodo() {
		return txNrPeriodo;
	}

	public void setTxNrPeriodo(TextField txNrPeriodo) {
		this.txNrPeriodo = txNrPeriodo;
	}

	public ManyToOneComboField<ContaCaixa> getCbContaCaixa() {
		return cbContaCaixa;
	}

	public void setCbContaCaixa(ManyToOneComboField<ContaCaixa> cbContaCaixa) {
		this.cbContaCaixa = cbContaCaixa;
	}
	
}
