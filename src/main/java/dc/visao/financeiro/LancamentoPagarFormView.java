package dc.visao.financeiro;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import org.vaadin.addons.maskedtextfield.NumericField;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Container;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.control.enums.SimNaoEn;
import dc.control.enums.TipoVencimentoEn;
import dc.controller.financeiro.LancamentoPagarFormController;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.financeiro.DocumentoOrigem;
import dc.entidade.financeiro.LancamentoPagarEntity;
import dc.entidade.financeiro.LctoPagarNtFinanceira;
import dc.entidade.financeiro.NaturezaFinanceira;
import dc.entidade.financeiro.ParcelaPagar;
import dc.entidade.geral.pessoal.FornecedorEntity;
import dc.visao.framework.component.IntegerConverter;
import dc.visao.framework.component.SubFormComponent;
import dc.visao.framework.component.manytoonecombo.ManyToOneCombo;
import dc.visao.framework.util.ComponentUtil;

public class LancamentoPagarFormView extends CustomComponent {

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
	private ManyToOneCombo<FornecedorEntity> cbFornecedor;

	@AutoGenerated
	private ManyToOneCombo<DocumentoOrigem> cbDocumentoOrigem;

	@AutoGenerated
	private TextField txNumeroDocumento;

	@AutoGenerated
	private ComboBox cbPagamentoCompartilhado;

	@AutoGenerated
	private ManyToOneCombo<ContaCaixa> cbContaCaixa;

	@AutoGenerated
	private PopupDateField dtLancamento;

	@AutoGenerated
	private TextField txValorTotal;

	@AutoGenerated
	private TextField txValorPagar;

	@AutoGenerated
	private NumericField txQuantidadeParcela;

	@AutoGenerated
	private NumericField txIntervaloParcela;

	@AutoGenerated
	private PopupDateField dtPrimeiroVencimento;

	@AutoGenerated
	private TabSheet tabSheet;

	private SubFormComponent<ParcelaPagar, Integer> parcelasSubForm;
	private SubFormComponent<LctoPagarNtFinanceira, Integer> naturezaFinanceiraSubForm;
	private LancamentoPagarFormController controller;
	private Button btnGerarParcelas;
	private VerticalLayout parcelasLayout;

	private ComboBox cbTipoVencimento;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public LancamentoPagarFormView(LancamentoPagarFormController controller) {
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

		tabSheet = BuildTabs();
		mainLayout.addComponent(tabSheet);
		mainLayout.setExpandRatio(tabSheet, 1);

		for (SimNaoEn en : SimNaoEn.values()) {
			cbPagamentoCompartilhado.addItem(en);
		}

		for (TipoVencimentoEn en : TipoVencimentoEn.values()) {
			cbTipoVencimento.addItem(en);
		}

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

		cbFornecedor = new ManyToOneCombo<>();
		fields.addComponent(cbFornecedor, 0, 0, 1, 0);
		cbFornecedor.setCaption("Fornecedor");

		cbDocumentoOrigem = new ManyToOneCombo<>();
		cbDocumentoOrigem.setCaption("Documento Origem");
		fields.addComponent(cbDocumentoOrigem, 0, 1, 1, 1);

		txNumeroDocumento = ComponentUtil.buildTextField("Número Documento");
		fields.addComponent(txNumeroDocumento, 2, 1);

		cbPagamentoCompartilhado = ComponentUtil
				.buildComboBox("Pagamento Compartilhado");
		fields.addComponent(cbPagamentoCompartilhado, 3, 1);

		dtLancamento = ComponentUtil.buildPopupDateField("Data Lançamento");
		fields.addComponent(dtLancamento, 0, 2);

		txValorTotal = ComponentUtil.buildCurrencyField("Valor Total");
		fields.addComponent(txValorTotal, 1, 2);

		txValorPagar = ComponentUtil.buildCurrencyField("Valor à Pagar");
		fields.addComponent(txValorPagar, 2, 2);

		txQuantidadeParcela = ComponentUtil
				.buildNumericField("Quantidade Parcelas");
		fields.addComponent(txQuantidadeParcela, 3, 2);
		txQuantidadeParcela.setConverter(new IntegerConverter());

		cbTipoVencimento = ComponentUtil.buildComboBox("Tipo Vencimento");
		fields.addComponent(cbTipoVencimento, 4, 2);

		txIntervaloParcela = ComponentUtil
				.buildNumericField("Intervalos Parcelas");
		fields.addComponent(txIntervaloParcela, 5, 2);
		txIntervaloParcela.setConverter(new IntegerConverter());

		dtPrimeiroVencimento = ComponentUtil
				.buildPopupDateField("Primeiro Vencimento");
		fields.addComponent(dtPrimeiroVencimento, 6, 2);

		cbContaCaixa = new ManyToOneCombo<>();
		cbContaCaixa.setCaption("Conta Caixa");
		fields.addComponent(cbContaCaixa, 0, 3, 1, 3);

		btnGerarParcelas = new Button("Gerar Parcelas");
		fields.addComponent(btnGerarParcelas, 0, 4);

		return fields;
	}

	private TabSheet BuildTabs() {
		tabSheet = new TabSheet();
		tabSheet.setImmediate(true);
		tabSheet.setSizeFull();

		tabSheet.addTab(buildSubFormParcelas(), "Parcelas", null);
		tabSheet.addTab(buildSubFormNaturezaFinanceira(),
				"Naturezas Financeiras Vinculadas", null);

		return tabSheet;
	}
	
	/*private SubFormComponent<LctoPagarNtFinanceira, Integer> buildSubFormNaturezaFinanceira() {
	// common part: create layout
	String[] atributos = new String[] {  "naturezaFinanceira","dataInclusao", "valor" };
	String[] headers = new String[] { "Natureza Financeira","Data Inclusão", "Valor" };

	naturezaFinanceiraSubForm = new SubFormComponent<LctoPagarNtFinanceira, Integer>(LctoPagarNtFinanceira.class, atributos, headers) {

		/**
		 * 
		 */
		/*private static final long serialVersionUID = 1L;

		@Override
		protected TableFieldFactory getFieldFactory() {
			return new TableFieldFactory() {

				/**
				 * 
				 */
				/*private static final long serialVersionUID = 1L;

				@Override
				public Field<?> createField(Container container,Object itemId, Object propertyId,Component uiContext) {
					
					if ("dataInclusao".equals(propertyId)) {
						DateField dateField = new DateField();
						dateField.setSizeFull();
						return dateField;
					} 
					
					if ("valor".equals(propertyId)) {
						return ComponentUtil.buildCurrencyField(null);
						
					} 
					
					if ("naturezaFinanceira".equals(propertyId)) {
						ComboBox cmb = ComponentUtil.buildComboBox("Natureza Financeira");
						cmb.removeAllItems();
						
						BeanItemContainer<NaturezaFinanceira> bic = controller.getNaturezaFinanceiraBic();
						cmb.setContainerDataSource(bic);
						cmb.setItemCaptionPropertyId("nome");

						return cmb;

						/*List<NaturezaFinanceira> naturezaFinanceiras = controller.getNaturezasFinanceiras();
						for (NaturezaFinanceira naturezaFinanceira : naturezaFinanceiras) {
							cmb.addItem(naturezaFinanceira);
						}

						return cmb;*/
					/*}
					

					return null;
				}

			};
		}

		@Override
		public boolean validateItems(List<LctoPagarNtFinanceira> items) {
			// TODO Auto-generated method stub
			return true;
		}

		protected LctoPagarNtFinanceira getNovo() {
			LctoPagarNtFinanceira LlctoPagarNtFinanceira = controller.novoLctoPagarNtFinanceira();

			return LlctoPagarNtFinanceira;
		}

		@Override
		protected void getRemoverSelecionados(List<LctoPagarNtFinanceira> values) {
			// TODO Auto-generated method stub
			controller.removerLctoPagarNtFinanceira(values);
		}

	};

	return this.naturezaFinanceiraSubForm;
}*/

	private SubFormComponent<LctoPagarNtFinanceira, Integer> buildSubFormNaturezaFinanceira() {
	//private Component buildSubFormNaturezaFinanceira() {
		
		String[] atributos = new String[] { "naturezaFinanceira","dataInclusao", "valor" };
		String[] headers = new String[] { "Natureza Financeira","Data Inclusão", "Valor" };

		this.naturezaFinanceiraSubForm = new SubFormComponent<LctoPagarNtFinanceira, Integer>(
				LctoPagarNtFinanceira.class, atributos, headers) {

			private static final long serialVersionUID = 1L;

			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public Field<?> createField(Container container,
							Object itemId, Object propertyId,
							Component uiContext) {

						if ("dataInclusao".equals(propertyId)) {
							DateField dateField = new DateField();
							dateField.setSizeFull();
							return dateField;
						} else if ("valor".equals(propertyId)) {
							return ComponentUtil.buildCurrencyField(null);
						} else if ("naturezaFinanceira".equals(propertyId)) {
							/*ComboBox cmb = ComponentUtil.buildComboBox(null);
							cmb.removeAllItems();
							List<NaturezaFinanceira> naturezaFinanceiras = controller.getNaturezasFin();
							for (NaturezaFinanceira naturezaFinanceira : naturezaFinanceiras) {
								cmb.addItem(naturezaFinanceira);
							}

							return cmb;*/
							
							ComboBox cmb = ComponentUtil.buildComboBox(null);
							cmb.removeAllItems();
							List<LctoPagarNtFinanceira> naturezaFinanceiras = controller.getNaturezasFinanceiras();
							for (LctoPagarNtFinanceira naturezaFinanceira : naturezaFinanceiras) {
								cmb.addItem(naturezaFinanceira);
							}

							return cmb;
						}

						else {
							return ComponentUtil.buildTextField(null);
						}
					}

				};
			}

			@Override
			public boolean validateItems(List<LctoPagarNtFinanceira> items) {

				return true;
			}

			protected LctoPagarNtFinanceira getNovo() {
				LctoPagarNtFinanceira LlctoPagarNtFinanceira = controller
						.novoLctoPagarNtFinanceira();
				return LlctoPagarNtFinanceira;
			}

			@Override
			protected void getRemoverSelecionados(
					List<LctoPagarNtFinanceira> values) {
				controller.removerLctoPagarNtFinanceira(values);
			}
		};

		return this.naturezaFinanceiraSubForm;
	}

	private Component buildSubFormParcelas() {

		parcelasLayout = new VerticalLayout();
		parcelasLayout.setImmediate(false);
		parcelasLayout.setSizeFull();
		parcelasLayout.setMargin(false);
		parcelasLayout.setSpacing(true);

		String[] atributos = new String[] { "contaCaixa", "numeroParcela",
				"dataEmissao", "dataVencimento", "descontoAte",
				"sofreRetencao", "valor", "taxaJuro", "valorJuro", "taxaMulta",
				"valorMulta", "taxaDesconto", "valorDesconto" };

		String[] headers = new String[] { "Conta Caixa", "Número Parcela",
				"Data Emissão", "Data Vencimento", "Desconto Até",
				"Sofre Retenção", "Valor", "Taxa Juro", "Valor Juro",
				"Taxa Multa", "Valor Multa", "Taxa Desconto", "Valor Desconto" };

		this.parcelasSubForm = new SubFormComponent<ParcelaPagar, Integer>(
				ParcelaPagar.class, atributos, headers) {

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
					public Field<?> createField(Container container,
							Object itemId, Object propertyId,
							Component uiContext) {

						if ("dataEmissao".equals(propertyId)
								|| "dataVencimento".equals(propertyId)) {
							DateField dateField = new DateField();
							dateField.setSizeFull();
							return dateField;
						} else if ("valor".equals(propertyId)) {
							return ComponentUtil.buildCurrencyField(null);
						}

						else if ("contaCaixa".equals(propertyId)) {
							TextField contaCaixaText = ComponentUtil
									.buildTextField(null);

							contaCaixaText
									.setConverter(new Converter<String, ContaCaixa>() {

										/**
								 * 
								 */
										private static final long serialVersionUID = 1L;

										@Override
										public ContaCaixa convertToModel(
												String value,
												Class<? extends ContaCaixa> targetType,
												Locale locale)
												throws com.vaadin.data.util.converter.Converter.ConversionException {
											return null;
										}

										@Override
										public String convertToPresentation(
												ContaCaixa value,
												Class<? extends String> targetType,
												Locale locale)
												throws com.vaadin.data.util.converter.Converter.ConversionException {
											return value.getNome();
										}

										@Override
										public Class<ContaCaixa> getModelType() {
											return ContaCaixa.class;
										}

										@Override
										public Class<String> getPresentationType() {
											return String.class;
										}
									});

							contaCaixaText.setReadOnly(true);
							return contaCaixaText;

						} else if ("taxaJuro".equals(propertyId)) {
							Field field = ComponentUtil
									.buildPercentageField(null);
							return field;
						} else if ("taxaMulta".equals(propertyId)) {
							Field field = ComponentUtil
									.buildPercentageField(null);
							return field;
						} else if ("taxaDesconto".equals(propertyId)) {
							Field field = ComponentUtil
									.buildPercentageField(null);
							return field;
						} else if ("valorJuro".equals(propertyId)) {
							Field field = ComponentUtil
									.buildCurrencyField(null);

							return field;
						} else if ("valorMulta".equals(propertyId)) {
							Field field = ComponentUtil
									.buildCurrencyField(null);
							return field;
						} else if ("valorDesconto".equals(propertyId)) {
							Field field = ComponentUtil
									.buildCurrencyField(null);
							return field;
						} else if ("valorPago".equals(propertyId)) {
							Field field = ComponentUtil
									.buildCurrencyField(null);
							return field;
						} else {
							return ComponentUtil.buildTextField(null);
						}
					}

				};
			}

			@Override
			public boolean validateItems(List<ParcelaPagar> items) {

				return true;
			}

			protected ParcelaPagar getNovo() {
				ParcelaPagar parcelaPagar = controller.novoParcelaPagar();
				return parcelaPagar;
			}

			protected void getRemoverSelecionados(List<ParcelaPagar> values) {
				controller.removerParcelaPagar(values);
			}
		};
		parcelasLayout.addComponent(this.parcelasSubForm);
		parcelasLayout.setExpandRatio(parcelasSubForm, 1);

		return parcelasLayout;
	}

	public void preencheBean(LancamentoPagarEntity currentBean) {
		currentBean.setFornecedor((FornecedorEntity) cbFornecedor.getValue());
		currentBean.setDataLancamento(dtLancamento.getValue());
		currentBean.setPrimeiroVencimento(dtPrimeiroVencimento.getValue());
		currentBean.setDocumentoOrigem((DocumentoOrigem) cbDocumentoOrigem
				.getValue());
		currentBean
				.setPagamentoCompartilhado(((SimNaoEn) cbPagamentoCompartilhado
						.getValue()).getCodigo());
		currentBean.setValorAPagar((BigDecimal) txValorPagar
				.getConvertedValue());
		currentBean
				.setValorTotal((BigDecimal) txValorTotal.getConvertedValue());
		currentBean.setIntervaloEntreParcelas(txIntervaloParcela
				.getConvertedValue() != null ? (Integer) txIntervaloParcela
				.getConvertedValue() : 0);
		currentBean.setNumeroDocumento(txNumeroDocumento.getValue());
		currentBean.setQuantidadeParcela((Integer) txQuantidadeParcela
				.getConvertedValue());
	}

	public void preencheForm(LancamentoPagarEntity currentBean) {
		cbFornecedor.setValue(currentBean.getFornecedor());
		dtLancamento.setValue(currentBean.getDataLancamento());
		dtPrimeiroVencimento.setValue(currentBean.getPrimeiroVencimento());
		cbDocumentoOrigem.setValue(currentBean.getDocumentoOrigem());
		cbPagamentoCompartilhado.setValue(SimNaoEn.getEn(currentBean
				.getPagamentoCompartilhado()));
		txValorPagar.setConvertedValue(currentBean.getValorAPagar());
		txValorTotal.setConvertedValue(currentBean.getValorTotal());
		txIntervaloParcela.setConvertedValue(currentBean
				.getIntervaloEntreParcelas());
		txNumeroDocumento.setValue(currentBean.getNumeroDocumento());
		txQuantidadeParcela.setConvertedValue(currentBean
				.getQuantidadeParcela());

		if ((Integer) txIntervaloParcela.getConvertedValue() == 30) {
			cbTipoVencimento.setValue(TipoVencimentoEn.M);
		} else {
			txIntervaloParcela.setEnabled(false);
			cbTipoVencimento.setValue(TipoVencimentoEn.D);
		}

		parcelasSubForm.fillWith(currentBean.getParcelasPagar());
		naturezaFinanceiraSubForm.fillWith(currentBean
				.getLctoPagarNtFinanceiras());
	}

	public ManyToOneCombo<DocumentoOrigem> getCbDocumentoOrigem() {
		return cbDocumentoOrigem;
	}

	public void setCbDocumentoOrigem(
			ManyToOneCombo<DocumentoOrigem> cbDocumentoOrigem) {
		this.cbDocumentoOrigem = cbDocumentoOrigem;
	}

	public TextField getTxNumeroDocumento() {
		return txNumeroDocumento;
	}

	public void setTxNumeroDocumento(TextField txNumeroDocumento) {
		this.txNumeroDocumento = txNumeroDocumento;
	}

	public ComboBox getCbPagamentoCompartilhado() {
		return cbPagamentoCompartilhado;
	}

	public void setCbPagamentoCompartilhado(ComboBox cbPagamentoCompartilhado) {
		this.cbPagamentoCompartilhado = cbPagamentoCompartilhado;
	}

	public PopupDateField getDtLancamento() {
		return dtLancamento;
	}

	public void setDtLancamento(PopupDateField dtLancamento) {
		this.dtLancamento = dtLancamento;
	}

	public TextField getTxValorTotal() {
		return txValorTotal;
	}

	public void setTxValorTotal(TextField txValorTotal) {
		this.txValorTotal = txValorTotal;
	}

	public TextField getTxValorPagar() {
		return txValorPagar;
	}

	public void setTxValorPagar(TextField txValorPagar) {
		this.txValorPagar = txValorPagar;
	}

	public NumericField getTxQuantidadeParcela() {
		return txQuantidadeParcela;
	}

	public void setTxQuantidadeParcela(NumericField txQuantidadeParcela) {
		this.txQuantidadeParcela = txQuantidadeParcela;
	}

	public NumericField getTxIntervaloParcela() {
		return txIntervaloParcela;
	}

	public void setTxIntervaloParcela(NumericField txIntervaloParcela) {
		this.txIntervaloParcela = txIntervaloParcela;
	}

	public PopupDateField getDtPrimeiroVencimento() {
		return dtPrimeiroVencimento;
	}

	public void setDtPrimeiroVencimento(PopupDateField dtPrimeiroVencimento) {
		this.dtPrimeiroVencimento = dtPrimeiroVencimento;
	}

	public TabSheet getTabSheet() {
		return tabSheet;
	}

	public void setTabSheet(TabSheet tabSheet) {
		this.tabSheet = tabSheet;
	}

	public SubFormComponent<ParcelaPagar, Integer> getParcelasSubForm() {
		return parcelasSubForm;
	}

	public void setParcelasSubForm(
			SubFormComponent<ParcelaPagar, Integer> parcelasSubForm) {
		this.parcelasSubForm = parcelasSubForm;
	}

	public SubFormComponent<LctoPagarNtFinanceira, Integer> getNaturezaFinanceiraSubForm() {
		return naturezaFinanceiraSubForm;
	}

	public void setNaturezaFinanceiraSubForm(
			SubFormComponent<LctoPagarNtFinanceira, Integer> naturezaFinanceiraSubForm) {
		this.naturezaFinanceiraSubForm = naturezaFinanceiraSubForm;
	}

	public Button getBtnGerarParcelas() {
		return btnGerarParcelas;
	}

	public void setBtnGerarParcelas(Button btnGerarParcelas) {
		this.btnGerarParcelas = btnGerarParcelas;
	}

	public ManyToOneCombo<ContaCaixa> getCbContaCaixa() {
		return cbContaCaixa;
	}

	public void setCbContaCaixa(ManyToOneCombo<ContaCaixa> cbContaCaixa) {
		this.cbContaCaixa = cbContaCaixa;
	}

	public ManyToOneCombo<FornecedorEntity> getCbFornecedor() {
		return cbFornecedor;
	}

	public void setCbFornecedor(ManyToOneCombo<FornecedorEntity> cbFornecedor) {
		this.cbFornecedor = cbFornecedor;
	}

	public ComboBox getCbTipoVencimento() {
		return cbTipoVencimento;
	}

	public void setCbTipoVencimento(ComboBox cbTipoVencimento) {
		this.cbTipoVencimento = cbTipoVencimento;
	}
	
	public void preencheSubForm(List<LctoPagarNtFinanceira> detalhes) {
		naturezaFinanceiraSubForm.fillWith(detalhes);
	}

}