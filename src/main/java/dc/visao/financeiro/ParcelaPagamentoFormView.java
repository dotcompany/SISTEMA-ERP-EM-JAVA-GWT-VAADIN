package dc.visao.financeiro;

import java.math.BigDecimal;
import java.util.List;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Container;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.control.enums.TipoBaixaEn;
import dc.controller.financeiro.ParcelaPagamentoFormController;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.financeiro.ParcelaPagamento;
import dc.entidade.financeiro.StatusParcela;
import dc.entidade.financeiro.TipoPagamento;
import dc.visao.financeiro.converters.TipoPagamentoConverter;
import dc.visao.framework.component.SubFormComponent;
import dc.visao.framework.component.manytoonecombo.ManyToOneCombo;
import dc.visao.framework.util.ComponentUtil;

public class ParcelaPagamentoFormView extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private GridLayout fields;

	@AutoGenerated
	private GridLayout pagamentoButtons;

	@AutoGenerated
	private HorizontalLayout pagamentosButtonsContainer;

	@AutoGenerated
	private PopupDateField dtDataVencimento;

	@AutoGenerated
	private PopupDateField dtDataPagamento;

	@AutoGenerated
	private ComboBox cbTipoBaixa;

	private ManyToOneCombo<TipoPagamento> cbTipoPagamento;
	private ManyToOneCombo<ContaCaixa> cbContaCaixa;
	private ManyToOneCombo<StatusParcela> cbStatusParcela;

	@AutoGenerated
	private TextField txValorPagar;

	@AutoGenerated
	private TextField txTaxaJuro;

	@AutoGenerated
	private TextField txValorJuro;

	@AutoGenerated
	private TextField txTaxaMulta;

	@AutoGenerated
	private TextField txValorMulta;

	@AutoGenerated
	private TextField txTaxaDesconto;

	@AutoGenerated
	private TextField txValorDesconto;

	@AutoGenerated
	private TextField txValorPago;

	@AutoGenerated
	private TextArea txaHistorico;

	@AutoGenerated
	private Button btnEfetuaPagamento;

	@AutoGenerated
	private Button btnExcluiPagamento;

	@AutoGenerated
	private SubFormComponent<ParcelaPagamento, Integer> pagamentosSubForm;

	public ParcelaPagamentoFormView(ParcelaPagamentoFormController parcelaPagamentoFormController) {
		buildMainLayout();
		setCompositionRoot(mainLayout);
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

		pagamentosSubForm = buildSubFormParcelaPagamento();
		mainLayout.addComponent(pagamentosSubForm);

		mainLayout.setExpandRatio(pagamentosSubForm, 1.0f);
		
		for (TipoBaixaEn value : TipoBaixaEn.values()) {
			cbTipoBaixa.addItem(value);
		}

		return mainLayout;
	}
 
 
	@AutoGenerated
	private GridLayout buildFields() {
		// common part: create layout
		fields = new GridLayout(5, 6);
		fields.setImmediate(false);
		fields.setWidth("100.0%");
		fields.setHeight("-1px");
		fields.setMargin(false);
		fields.setSpacing(true);

		pagamentoButtons = buildPagamentoButtons();
		fields.addComponent(pagamentoButtons, 0, 0, 1, 0);

		cbTipoBaixa = ComponentUtil.buildComboBox("Tipo Baixa");
		fields.addComponent(cbTipoBaixa, 0, 1);

		cbTipoPagamento = new ManyToOneCombo<>();
		cbTipoPagamento.setCaption("Tipo Pagamento");
		fields.addComponent(cbTipoPagamento, 1, 1);

		cbContaCaixa = new ManyToOneCombo<>();
		cbContaCaixa.setCaption("Conta Caixa");
		cbContaCaixa.setSizeFull();
		//cbContaCaixa.setHeight("-1px");
		//cbContaCaixa.setWidth("200px");
		fields.addComponent(cbContaCaixa, 2, 1);
		
		cbStatusParcela= new ManyToOneCombo<>();
		cbStatusParcela.setCaption("Status Parcela");
		cbStatusParcela.setSizeFull();
		//cbContaCaixa.setHeight("-1px");
		//cbContaCaixa.setWidth("200px");
		fields.addComponent(cbStatusParcela, 3, 1);
		//

		dtDataVencimento = ComponentUtil.buildPopupDateField("Data Vencimento");
		fields.addComponent(dtDataVencimento, 0, 2);

		dtDataPagamento = ComponentUtil.buildPopupDateField("Data Pagamento");
		fields.addComponent(dtDataPagamento, 1, 2);

		txValorPagar = ComponentUtil.buildCurrencyField("Valor à Pagar");
		fields.addComponent(txValorPagar, 2, 2);

		txTaxaJuro = ComponentUtil.buildPercentageField("Taxa Juro");
		fields.addComponent(txTaxaJuro, 3, 2);

		txValorJuro = ComponentUtil.buildCurrencyField("Valor Juro");
		fields.addComponent(txValorJuro, 4, 2);
		//

		txTaxaMulta = ComponentUtil.buildPercentageField("Taxa Multa");
		fields.addComponent(txTaxaMulta, 0, 3);

		txValorMulta = ComponentUtil.buildCurrencyField("Valor Multa");
		fields.addComponent(txValorMulta, 1, 3);

		txTaxaDesconto = ComponentUtil.buildPercentageField("Taxa Desconto");
		fields.addComponent(txTaxaDesconto, 2, 3);

		txValorDesconto = ComponentUtil.buildCurrencyField("Valor Desconto");
		fields.addComponent(txValorDesconto, 3, 3);

		txValorPago = ComponentUtil.buildCurrencyField("Valor Pago");
		fields.addComponent(txValorPago, 4, 3);

		txaHistorico = ComponentUtil.buildTextArea("Histórico");
		fields.addComponent(txaHistorico, 0, 4, 4, 5);

		return fields;
	}

	private GridLayout buildPagamentoButtons() {
		pagamentoButtons = new GridLayout(2, 1);
		pagamentoButtons.setImmediate(false);
		pagamentoButtons.setSizeFull();
		pagamentoButtons.setMargin(false);
		pagamentoButtons.setSpacing(true);

		btnEfetuaPagamento = new Button("Efetua Pagamento");
		btnEfetuaPagamento.setImmediate(true);
		pagamentoButtons.addComponent(btnEfetuaPagamento, 0, 0);

		btnExcluiPagamento = new Button("Exclui Pagamento");
		btnExcluiPagamento.setImmediate(true);
		pagamentoButtons.addComponent(btnExcluiPagamento, 1, 0);

		return pagamentoButtons;
	}

	private SubFormComponent<ParcelaPagamento, Integer> buildSubFormParcelaPagamento() {

		String[] atributos = new String[] { "tipoPagamento", "dataPagamento", "taxaJuro", "valorJuro", "taxaMulta", "valorMulta", "taxaDesconto",
				"valorDesconto", "valorPago", "historico" };

		String[] headers = new String[] { "Tipo Pagamento", "Data Pagamento", "Taxa Juro", "Valor Juro", "Taxa Multa", "Valor Multa",
				"Taxa Desconto", "Valor Desconto", "Valor Pago", "Histórico" };

		this.pagamentosSubForm = new SubFormComponent<ParcelaPagamento, Integer>(ParcelaPagamento.class, atributos, headers) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void adicionarBotoes(Table table) {

			}

			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("rawtypes")
					@Override
					public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {

						if ("dataPagamento".equals(propertyId)) {
							DateField dateField = new DateField();
							dateField.setSizeFull();
							dateField.setEnabled(false);
							return dateField;
						} else if ("tipoPagamento".equals(propertyId)) {
							TextField field = ComponentUtil.buildTextField(null);
							field.setConverter(new TipoPagamentoConverter());
							field.setEnabled(false);
							return field;
						} else if ("taxaJuro".equals(propertyId)) {
							Field field = ComponentUtil.buildPercentageField(null);
							field.setEnabled(false);
							return field;
						} else if ("taxaMulta".equals(propertyId)) {
							Field field = ComponentUtil.buildPercentageField(null);
							field.setEnabled(false);
							return field;
						} else if ("taxaDesconto".equals(propertyId)) {
							Field field = ComponentUtil.buildPercentageField(null);
							field.setEnabled(false);
							return field;
						} else if ("valorJuro".equals(propertyId)) {
							Field field = ComponentUtil.buildCurrencyField(null);
							field.setEnabled(false);
							return field;
						} else if ("valorMulta".equals(propertyId)) {
							Field field = ComponentUtil.buildCurrencyField(null);
							field.setEnabled(false);
							return field;
						} else if ("valorDesconto".equals(propertyId)) {
							Field field = ComponentUtil.buildCurrencyField(null);
							field.setEnabled(false);
							return field;
						} else if ("valorPago".equals(propertyId)) {
							Field field = ComponentUtil.buildCurrencyField(null);
							field.setEnabled(false);
							return field;
						} else {
							Field field = ComponentUtil.buildTextField(null);
							field.setEnabled(false);
							return field;
						}
					}

				};
			}

			@Override
			public boolean validateItems(List<ParcelaPagamento> items) {
				return true;
			}
		};

		return this.pagamentosSubForm;
	}

	public void preencheBean(ParcelaPagamento currentBean) {
		currentBean.setDataPagamento(dtDataPagamento.getValue());
		currentBean.setContaCaixa((ContaCaixa) cbContaCaixa.getValue());
		currentBean.setTipoPagamento((TipoPagamento) cbTipoPagamento.getValue());
		currentBean.setTaxaJuro((BigDecimal) txTaxaJuro.getConvertedValue());
		currentBean.setValorJuro((BigDecimal) txValorJuro.getConvertedValue());
		currentBean.setTaxaMulta((BigDecimal) txTaxaMulta.getConvertedValue());
		currentBean.setValorMulta((BigDecimal) txValorMulta.getConvertedValue());
		currentBean.setTaxaDesconto((BigDecimal) txTaxaDesconto.getConvertedValue());
		currentBean.setValorDesconto((BigDecimal) txValorDesconto.getConvertedValue());
		currentBean.setValorPago((BigDecimal) txValorPago.getConvertedValue());
		currentBean.setHistorico(txaHistorico.getValue());
	}

	public void preencheForm(ParcelaPagamento currentBean) {
		dtDataVencimento.setValue(currentBean.getParcelaPagar().getDataVencimento());
		dtDataPagamento.setValue(currentBean.getDataPagamento());
		txValorPagar.setConvertedValue(currentBean.getParcelaPagar().getValor());
		cbContaCaixa.setValue(currentBean.getContaCaixa());
		if(currentBean.getTipoPagamento() != null){		
			cbTipoPagamento.setValue(currentBean.getTipoPagamento());
		}
		txTaxaJuro.setConvertedValue(currentBean.getTaxaJuro());
		txValorJuro.setConvertedValue(currentBean.getValorJuro());
		txTaxaMulta.setConvertedValue(currentBean.getTaxaMulta());
		txValorMulta.setConvertedValue(currentBean.getValorMulta());
		txTaxaDesconto.setConvertedValue(currentBean.getTaxaDesconto());
		txValorDesconto.setConvertedValue(currentBean.getValorDesconto());
		txValorPago.setConvertedValue(currentBean.getValorPago());
		txaHistorico.setValue(currentBean.getHistorico());

	}
	
	public ManyToOneCombo<StatusParcela> getCbStatusParcela() {
		return cbStatusParcela;
	}

	public void setCbStatusParcela(ManyToOneCombo<StatusParcela> cbStatusParcela) {
		this.cbStatusParcela = cbStatusParcela;
	}

	public GridLayout getFields() {
		return fields;
	}

	public void setFields(GridLayout fields) {
		this.fields = fields;
	}

	public PopupDateField getDtDataVencimento() {
		return dtDataVencimento;
	}

	public void setDtDataVencimento(PopupDateField dtDataVencimento) {
		this.dtDataVencimento = dtDataVencimento;
	}

	public PopupDateField getDtDataPagamento() {
		return dtDataPagamento;
	}

	public void setDtDataPagamento(PopupDateField dtDataPagamento) {
		this.dtDataPagamento = dtDataPagamento;
	}

	public ComboBox getCbTipoBaixa() {
		return cbTipoBaixa;
	}

	public void setCbTipoBaixa(ComboBox cbTipoBaixa) {
		this.cbTipoBaixa = cbTipoBaixa;
	}

	public TextField getTxValorPagar() {
		return txValorPagar;
	}

	public void setTxValorPagar(TextField txValorPagar) {
		this.txValorPagar = txValorPagar;
	}

	public TextField getTxTaxaJuro() {
		return txTaxaJuro;
	}

	public void setTxTaxaJuro(TextField txTaxaJuro) {
		this.txTaxaJuro = txTaxaJuro;
	}

	public TextField getTxValorJuro() {
		return txValorJuro;
	}

	public void setTxValorJuro(TextField txValorJuro) {
		this.txValorJuro = txValorJuro;
	}

	public TextField getTxTaxaMulta() {
		return txTaxaMulta;
	}

	public void setTxTaxaMulta(TextField txTaxaMulta) {
		this.txTaxaMulta = txTaxaMulta;
	}

	public TextField getTxValorMulta() {
		return txValorMulta;
	}

	public void setTxValorMulta(TextField txValorMulta) {
		this.txValorMulta = txValorMulta;
	}

	public TextField getTxTaxaDesconto() {
		return txTaxaDesconto;
	}

	public void setTxTaxaDesconto(TextField txTaxaDesconto) {
		this.txTaxaDesconto = txTaxaDesconto;
	}

	public TextField getTxValorDesconto() {
		return txValorDesconto;
	}

	public void setTxValorDesconto(TextField txValorDesconto) {
		this.txValorDesconto = txValorDesconto;
	}

	public TextField getTxValorPago() {
		return txValorPago;
	}

	public void setTxValorPago(TextField txValorPago) {
		this.txValorPago = txValorPago;
	}

	public TextArea getTxaHistorico() {
		return txaHistorico;
	}

	public void setTxaHistorico(TextArea txaHistorico) {
		this.txaHistorico = txaHistorico;
	}

	public SubFormComponent<ParcelaPagamento, Integer> getPagamentosSubForm() {
		return pagamentosSubForm;
	}

	public void setPagamentosSubForm(SubFormComponent<ParcelaPagamento, Integer> pagamentosSubForm) {
		this.pagamentosSubForm = pagamentosSubForm;
	}

	public void fillParcelaPagamentosSubForm(List<ParcelaPagamento> parcelasPagar) {
		pagamentosSubForm.fillWith(parcelasPagar);
	}

	public Button getBtnEfetuaPagamento() {
		return btnEfetuaPagamento;
	}

	public void setBtnEfetuaPagamento(Button btnEfetuaPagamento) {
		this.btnEfetuaPagamento = btnEfetuaPagamento;
	}

	public Button getBtnExcluiPagamento() {
		return btnExcluiPagamento;
	}

	public void setBtnExcluiPagamento(Button btnExcluiPagamento) {
		this.btnExcluiPagamento = btnExcluiPagamento;
	}

	public ManyToOneCombo<TipoPagamento> getCbTipoPagamento() {
		return cbTipoPagamento;
	}

	public void setCbTipoPagamento(ManyToOneCombo<TipoPagamento> cbTipoPagamento) {
		this.cbTipoPagamento = cbTipoPagamento;
	}

	public ManyToOneCombo<ContaCaixa> getCbContaCaixa() {
		return cbContaCaixa;
	}

	public void setCbContaCaixa(ManyToOneCombo<ContaCaixa> cbContaCaixa) {
		this.cbContaCaixa = cbContaCaixa;
	}

}
