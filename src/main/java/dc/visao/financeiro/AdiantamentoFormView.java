package dc.visao.financeiro;

import java.math.BigDecimal;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.entidade.financeiro.Adiantamento;
import dc.entidade.financeiro.LancamentoPagar;
import dc.visao.framework.component.manytoonecombo.ManyToOneCombo;
import dc.visao.framework.util.ComponentUtil;

public class AdiantamentoFormView extends CustomComponent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private GridLayout fields;
	@AutoGenerated
	private TextArea txtObservacoes;
	@AutoGenerated
	private TextField txtValor;
	@AutoGenerated
	private PopupDateField dtAdiantamento;
	
	private ManyToOneCombo<LancamentoPagar> cmbLancamentoPagar;
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public AdiantamentoFormView() {
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
		
		// cmbLancamentoPagar
		cmbLancamentoPagar = new ManyToOneCombo<>();
		cmbLancamentoPagar.setCaption("Lançamento Pagar");
		cmbLancamentoPagar.setSizeFull();
		fields.addComponent(cmbLancamentoPagar, 0, 0);
		
		// dtAdiantamento
		dtAdiantamento = ComponentUtil.buildPopupDateField("Data Adiantamento");
		dtAdiantamento.setSizeFull();
		fields.addComponent(dtAdiantamento, 0, 1);
		
		// txtValor
		txtValor = ComponentUtil.buildTextField("Valor");
		txtValor.setSizeFull();
		fields.addComponent(txtValor, 1, 1);
		
		// txtObservacoes
		txtObservacoes = ComponentUtil.buildTextArea("Observações");
		fields.addComponent(txtObservacoes, 0, 2);
		
		return fields;
	}
	
	public void preencheAdiantamento(Adiantamento adiantamento) {

		adiantamento.setIdLancamentoPagar((LancamentoPagar) cmbLancamentoPagar.getValue());
		adiantamento.setDataAdiantamento(dtAdiantamento.getValue());
		adiantamento.setObservacoes(txtObservacoes.getValue());
		adiantamento.setValor((BigDecimal) txtValor.getConvertedValue());

	}

	public void preencheAdiantamentoForm(Adiantamento adiantamento) {
		cmbLancamentoPagar.setValue(adiantamento.getIdLancamentoPagar());
		dtAdiantamento.setValue(adiantamento.getDataAdiantamento());
		txtObservacoes.setValue(adiantamento.getObservacoes());
		txtValor.setConvertedValue(adiantamento.getValor());

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

	public TextArea getTxtObservacoes() {
		return txtObservacoes;
	}

	public void setTxtObservacoes(TextArea txtObservacoes) {
		this.txtObservacoes = txtObservacoes;
	}

	public TextField getTxtValor() {
		return txtValor;
	}

	public void setTxtValor(TextField txtValor) {
		this.txtValor = txtValor;
	}

	public PopupDateField getDtAdiantamento() {
		return dtAdiantamento;
	}

	public void setDtAdiantamento(PopupDateField dtAdiantamento) {
		this.dtAdiantamento = dtAdiantamento;
	}

	public ManyToOneCombo<LancamentoPagar> getCmbLancamentoPagar() {
		return cmbLancamentoPagar;
	}

	public void setCmbLancamentoPagar(
			ManyToOneCombo<LancamentoPagar> cmbLancamentoPagar) {
		this.cmbLancamentoPagar = cmbLancamentoPagar;
	}
	
}