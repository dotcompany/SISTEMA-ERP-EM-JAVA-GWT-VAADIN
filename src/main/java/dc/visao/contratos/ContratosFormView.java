package dc.visao.contratos;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import org.vaadin.addons.maskedtextfield.MaskedTextField;
import org.vaadin.addons.maskedtextfield.NumericField;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.contratos.ContratoFormController;
import dc.entidade.contabilidade.ContabilConta;
import dc.entidade.contratos.Contrato;
import dc.entidade.contratos.ContratoHistFaturamento;
import dc.entidade.contratos.ContratoHistoricoReajuste;
import dc.entidade.contratos.ContratoPrevFaturamento;
import dc.entidade.contratos.ContratoProduto;
import dc.entidade.contratos.ContratoSolicitacaoServico;
import dc.entidade.contratos.Template;
import dc.entidade.contratos.TipoContrato;
import dc.entidade.geral.Pessoa;
import dc.entidade.produto.Produto;
import dc.framework.StringToBigDecimalConverter;
import dc.visao.framework.component.ComboItemValue;
import dc.visao.framework.component.IntegerConverter;
import dc.visao.framework.component.SubFormComponent;
import dc.visao.framework.component.manytoonecombo.ManyToOneCombo;
import dc.visao.framework.util.ComponentUtil;

@SuppressWarnings("serial")
public class ContratosFormView extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private TabSheet tabSheet_2;
	@AutoGenerated
	private AbsoluteLayout absoluteLayout_6;
	@AutoGenerated
	private AbsoluteLayout absoluteLayout_4;
	@AutoGenerated
	private AbsoluteLayout absoluteLayout_3;
	@AutoGenerated
	private Table table_2;
	@AutoGenerated
	private TabSheet tabSheet_1;
	@AutoGenerated
	private GridLayout gridLayout_2;
	@AutoGenerated
	private GridLayout gridLayout_3;
	
	@AutoGenerated
	private TextArea txaObservacoes;
	@AutoGenerated
	private TextArea txaAditivo;
	@AutoGenerated
	private TextArea txaDescricao;
	@AutoGenerated
	private Button btnGerarParcelas;

	private NumericField txtIntervaloParcelas;
	private NumericField txtQuantidadeParcelas;
	// private PopupDateField dtPrimeiroVencimento;

	@AutoGenerated
	private TextField txtValor;
	private NumericField txtDiaFaturamento;
	@AutoGenerated
	private PopupDateField dtFimVigencia;
	@AutoGenerated
	private PopupDateField dtVigencia;

	private PopupDateField dtCadastro;
	@AutoGenerated
	private GridLayout gridLayout_1;
	@AutoGenerated
	private ManyToOneCombo<ContratoSolicitacaoServico> cmbSolicitacaoServico;
	@AutoGenerated
	private TextField txtNome;
	@AutoGenerated
	private TextField txtNumero;
	@AutoGenerated
	private ManyToOneCombo<Pessoa> cbmPessoa;
	@AutoGenerated
	private ManyToOneCombo<ContabilConta> cbmContabilConta;
	@AutoGenerated
	private ManyToOneCombo<TipoContrato> cbmTipoContrato;
	@AutoGenerated
	private ManyToOneCombo<Template> cbmDocumento;
	
	@AutoGenerated
	private ManyToOneCombo<Produto> cmbProduto;
	
	@AutoGenerated
	private GridLayout gridLayout_4;
	@AutoGenerated
	private TextField txtLogradouroObjeto;
	@AutoGenerated
	private TextField txtNumeroObjeto;
	@AutoGenerated
	private TextField txtComplementoObjeto;
	@AutoGenerated
	private TextField txtBairroObjeto;
	@AutoGenerated
	private TextField txtCidadeObjeto;
	@AutoGenerated
	private MaskedTextField txtCEPObjeto;
	
	@AutoGenerated
	private TextField txtValorTarifa;
	
	
	@AutoGenerated
	private ComboBox cmbEstadoObjeto;
	
	private SubFormComponent<ContratoHistFaturamento, Integer> historicoFaturamentoSubForm;
	private SubFormComponent<ContratoHistoricoReajuste, Integer> historicoReajustesSubForm;
	private SubFormComponent<ContratoPrevFaturamento, Integer> previsaoFaturamentoSubForm;
	private SubFormComponent<ContratoProduto, Integer> contratoProdutoSubForm;
	// private SubFormComponent<Documento, Integer> arquivoContratoSubForm;
	@AutoGenerated
	private Button btnGerarContrato;

	
	private ContratoFormController controller;
	private VerticalLayout contratoLayout;

	public ContratosFormView(ContratoFormController contratoFormController) {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		this.controller = contratoFormController;
	}

	public VerticalLayout getMainLayout() {
		return mainLayout;
	}

	public void setMainLayout(VerticalLayout mainLayout) {
		this.mainLayout = mainLayout;
	}

	public Table getTable_2() {
		return table_2;
	}

	public void setTable_2(Table table_2) {
		this.table_2 = table_2;
	}

	public TextArea getTxaObservacoes() {
		return txaObservacoes;
	}

	public void setTxaObservacoes(TextArea txaObservacoes) {
		this.txaObservacoes = txaObservacoes;
	}

	public TextArea getTxaDescricao() {
		return txaDescricao;
	}

	public void setTxaDescricao(TextArea txaDescricao) {
		this.txaDescricao = txaDescricao;
	}

	

	public TextField getTxtIntervaloParcelas() {
		return txtIntervaloParcelas;
	}

	public void setTxtIntervaloParcelas(NumericField txtIntervaloParcelas) {
		this.txtIntervaloParcelas = txtIntervaloParcelas;
	}

	public TextField getTxtQuantidadeParcelas() {
		return txtQuantidadeParcelas;
	}

	public void setTxtQuantidadeParcelas(NumericField txtQuantidadeParcelas) {
		this.txtQuantidadeParcelas = txtQuantidadeParcelas;
	}

	public TextField getTxtValor() {
		return txtValor;
	}

	public void setTxtValor(TextField txtValor) {
		this.txtValor = txtValor;
	}

	public NumericField getTxtDiaFaturamento() {
		return txtDiaFaturamento;
	}

	public void setTxtDiaFaturamento(NumericField txtDiaFaturamento) {
		this.txtDiaFaturamento = txtDiaFaturamento;
	}

	public PopupDateField getDtFimVigencia() {
		return dtFimVigencia;
	}

	public void setDtFimVigencia(PopupDateField dtFimVigencia) {
		this.dtFimVigencia = dtFimVigencia;
	}

	public PopupDateField getDtVigencia() {
		return dtVigencia;
	}

	public void setDtVigencia(PopupDateField dtVigencia) {
		this.dtVigencia = dtVigencia;
	}

	public PopupDateField getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(PopupDateField dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public TextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(TextField txtNome) {
		this.txtNome = txtNome;
	}

	public TextField getTxtNumero() {
		return txtNumero;
	}

	public void setTxtNumero(TextField txtNumero) {
		this.txtNumero = txtNumero;
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
		setHeight("100.0%");

		// tabSheet_1
		tabSheet_1 = buildTabSheet_1();
		mainLayout.addComponent(tabSheet_1);
		mainLayout.setExpandRatio(tabSheet_1, 1.0f);

		// tabSheet_2
		tabSheet_2 = buildTabSheet_2();
		mainLayout.addComponent(tabSheet_2);
		mainLayout.setExpandRatio(tabSheet_2, 1.0f);
		
		cmbProduto = new ManyToOneCombo<>();

		return mainLayout;
	}

	public ManyToOneCombo<Produto> getCmbProduto() {
		return cmbProduto;
	}

	public void setCmbProduto(ManyToOneCombo<Produto> cmbProduto) {
		this.cmbProduto = cmbProduto;
	}

	@AutoGenerated
	private TabSheet buildTabSheet_1() {
		// common part: create layout
		tabSheet_1 = new TabSheet();
		tabSheet_1.setImmediate(false);
		tabSheet_1.setSizeFull();

		// absoluteLayout_5
		gridLayout_1 = buildGridLayout1();
		tabSheet_1.addTab(gridLayout_1, "Dados Básicos", null);

		// absoluteLayout_2
		gridLayout_2 = buildGridLayout_2();
		tabSheet_1.addTab(gridLayout_2, "Dados Complementares", null);
		
		
		tabSheet_1.addTab(buildObjetoProdutoSubForm(), "OBJETO/PROTUTO", null);
		
		gridLayout_4 = buildGridLayout_4();
		tabSheet_1.addTab(gridLayout_4, "Endereço Objeto", null);
		
		
		gridLayout_3 = buildGridLayout_3();
		tabSheet_1.addTab(gridLayout_3, "Aditivo Contrato", null);
		

		return tabSheet_1;
	}

	@AutoGenerated
	private GridLayout buildGridLayout1() {
		// common part: create layout
		gridLayout_1 = new GridLayout(4, 5);
		gridLayout_1.setImmediate(false);
		gridLayout_1.setMargin(true);
		gridLayout_1.setSpacing(true);
		// gridLayout_1.setRows(4);
		// gridLayout_1.setColumns(2);
		gridLayout_1.setWidth("100%");

		// cbmPessoa
		cbmPessoa = new ManyToOneCombo<>();
		cbmPessoa.setCaption("Pessoa");
		cbmPessoa.setSizeFull();
		gridLayout_1.addComponent(cbmPessoa, 0, 0);

		// cbmTipoContrato
		cbmTipoContrato = new ManyToOneCombo<>();
		cbmTipoContrato.setCaption("Tipo de Contrato");
		cbmTipoContrato.setSizeFull();
		gridLayout_1.addComponent(cbmTipoContrato, 1, 0);

		// cbmContabilConta
		cbmContabilConta = new ManyToOneCombo<>();
		cbmContabilConta.setCaption("Conta Contábil");
		cbmContabilConta.setSizeFull();
		gridLayout_1.addComponent(cbmContabilConta, 0, 1);

		// cmbSolicitacaoServico
		cmbSolicitacaoServico = new ManyToOneCombo<>();
		cmbSolicitacaoServico.setCaption("Solicitação de serviço");
		cmbSolicitacaoServico.setSizeFull();
		gridLayout_1.addComponent(cmbSolicitacaoServico, 1, 1);

		// txtNumero
		txtNumero = ComponentUtil.buildTextField("Número");
		gridLayout_1.addComponent(txtNumero, 0, 2);

		// txtNome
		txtNome = ComponentUtil.buildTextField("Nome");
		gridLayout_1.addComponent(txtNome, 1, 2);

		cbmDocumento = new ManyToOneCombo<>();
		cbmDocumento.setCaption("Modelo Documento");
		gridLayout_1.addComponent(cbmDocumento, 0, 3, 1, 3);
		//gridLayout_1.addComponent(cbmDocumento, 0, 3);

		btnGerarContrato = new Button("Gerar Contrato");
		btnGerarContrato.setVisible(true);
		gridLayout_1.addComponent(btnGerarContrato, 0, 4);
		//gridLayout_1.addComponent(btnGerarContrato, 1,3);


		return gridLayout_1;
	}
	
	@AutoGenerated
	private GridLayout buildGridLayout_3() {
		gridLayout_3 = new GridLayout(1, 1);
		gridLayout_3.setImmediate(false);
		gridLayout_3.setMargin(true);
		gridLayout_3.setSpacing(true);
		gridLayout_3.setWidth("100%");

		
		// txaDescricao
		txaAditivo = ComponentUtil.buildTextArea("Aditivo Contrato");
		txaAditivo.setHeight("100%");
		txaAditivo.setWidth("100%");
		txaAditivo.setRows(9);
		
		
		gridLayout_3.addComponent(txaAditivo, 0, 0);

		
		return gridLayout_3;
	}
	
	
	@AutoGenerated
	private GridLayout buildGridLayout_4() {
		gridLayout_4 = new GridLayout(3, 4);
		gridLayout_4.setImmediate(false);
		gridLayout_4.setMargin(true);
		gridLayout_4.setSpacing(true);
		gridLayout_4.setWidth("100%");

		
		// txaDescricao
		txtCEPObjeto = ComponentUtil.buildMaskedTextField("CEP", "#####-###");
		txtCEPObjeto.setMaskClientOnly(true);
		txtCEPObjeto.setWidth("30%");
		gridLayout_4.addComponent(txtCEPObjeto, 0, 0);
		
		txtLogradouroObjeto = ComponentUtil.buildTextField("Logradouro");
		gridLayout_4.addComponent(txtLogradouroObjeto, 0, 1);
		
		txtNumeroObjeto = ComponentUtil.buildNumericField("Número");
		txtNumeroObjeto.setMaxLength(6);
		txtNumeroObjeto.setWidth("20%");
		txtNumeroObjeto.setConverter(new IntegerConverter());
		gridLayout_4.addComponent(txtNumeroObjeto, 1, 1);
		
		txtComplementoObjeto = ComponentUtil.buildTextField("Complemento");
		gridLayout_4.addComponent(txtComplementoObjeto, 0, 2);
		
		txtBairroObjeto = ComponentUtil.buildTextField("Bairro");
		gridLayout_4.addComponent(txtBairroObjeto, 1, 2);
		
		
		txtCidadeObjeto = ComponentUtil.buildTextField("Cidade");
		gridLayout_4.addComponent(txtCidadeObjeto, 0, 3);
		
		
		
		
		cmbEstadoObjeto = ComponentUtil.buildComboBox("UF");
		cmbEstadoObjeto.setCaption("UF");
		cmbEstadoObjeto.setWidth("156px");
		cmbEstadoObjeto.setHeight("-1px");
		cmbEstadoObjeto.setSizeFull();
		
		gridLayout_4.addComponent(cmbEstadoObjeto, 1, 3);
		
		
		return gridLayout_4;
	}
	

	@AutoGenerated
	private GridLayout buildGridLayout_2() {
		// common part: create layout
		gridLayout_2 = new GridLayout(4, 4);
		// gridLayout_2.setRows(4);
		// gridLayout_2.setColumns(4);
		gridLayout_2.setImmediate(false);
		gridLayout_2.setMargin(true);
		gridLayout_2.setSpacing(true);
		gridLayout_2.setWidth("100%");

		// dtCadastro
		dtCadastro = ComponentUtil.buildPopupDateField("Data Cadastro");
		gridLayout_2.addComponent(dtCadastro, 0, 0);

		// dtVigencia
		dtVigencia = ComponentUtil.buildPopupDateField("Data Vigência");
		dtVigencia.setRequired(true);
		gridLayout_2.addComponent(dtVigencia, 1, 0);

		// dtFimVigencia
		dtFimVigencia = ComponentUtil.buildPopupDateField("Data Fim Vigência");
		dtFimVigencia.setRequired(true);
		gridLayout_2.addComponent(dtFimVigencia, 2, 0);

		// txtDiaFaturamento
		txtDiaFaturamento = ComponentUtil.buildNumericField("Dia de Faturamento");
		gridLayout_2.addComponent(txtDiaFaturamento, 3, 0);
		txtDiaFaturamento.setConverter(new IntegerConverter());

		// dtPrimeiroVencimento
		// dtPrimeiroVencimento =
		// ComponentUtil.buildPopupDateField("Primeiro Vencimento");
		// gridLayout_2.addComponent(dtPrimeiroVencimento, 4, 0);

		// txtValor
		txtValor = ComponentUtil.buildCurrencyField("Valor");
		txtValor.setRequired(true);
		gridLayout_2.addComponent(txtValor, 0, 1);

		// txtQuantidadeParcelas
		txtQuantidadeParcelas = ComponentUtil.buildNumericField("Quantidade de Parcelas");
		txtQuantidadeParcelas.setRequired(true);
		gridLayout_2.addComponent(txtQuantidadeParcelas, 1, 1);
		txtQuantidadeParcelas.setConverter(new IntegerConverter());

		// txtIntervaloParcelas
		txtIntervaloParcelas = ComponentUtil.buildNumericField("Intervalo entre Parcelas");
		txtIntervaloParcelas.setNullRepresentation("");
		txtIntervaloParcelas.setRequired(true);
		gridLayout_2.addComponent(txtIntervaloParcelas, 2, 1);
		txtIntervaloParcelas.setConverter(new IntegerConverter());

		btnGerarParcelas = new Button("Gerar Parcelas");
		btnGerarParcelas.setSizeFull();
		gridLayout_2.addComponent(btnGerarParcelas, 3, 1);

		// txaDescricao
		txaDescricao = ComponentUtil.buildTextArea("Descrição");
		gridLayout_2.addComponent(txaDescricao, 0, 2);

		// txaObservacoes
		txaObservacoes = ComponentUtil.buildTextArea("Observações");
		gridLayout_2.addComponent(txaObservacoes, 1, 2);
		
		txtValorTarifa = ComponentUtil.buildCurrencyField("Valor tarifa de transportes");
		gridLayout_2.addComponent(txtValorTarifa, 2, 2);

		return gridLayout_2;
	}

	/** TextArea (TEMPLATE) */

	

	@AutoGenerated
	private TabSheet buildTabSheet_2() {
		// common part: create layout
		tabSheet_2 = new TabSheet();
		tabSheet_2.setImmediate(true);
		tabSheet_2.setWidth("100%");
		tabSheet_2.setHeight("100%");
		// tabSheet_2.setSizeFull();

		// historicoFaturamentoSubForm = buildHistoricoFaturaSubForm();
		// historicoReajustesSubForm = buildHistoricoReajustesSubForm();
		// previsaoFaturamentoSubForm = buildPrevisaoFaturamentoSubForm();

		/*
		 * historicoFaturamentoSubForm.setHeight("100%");
		 * historicoReajustesSubForm.setHeight("100%");
		 * previsaoFaturamentoSubForm.setHeight("100%");
		 */

		tabSheet_2.addTab(buildHistoricoFaturaSubForm(), "Histórico Faturamento", null);

		tabSheet_2.addTab(buildHistoricoReajustesSubForm(), "Histórico de Reajustes", null);

		tabSheet_2.addTab(buildPrevisaoFaturamentoSubForm(), "Previsão de 	Faturamento", null);

		// tabSheet_2.addTab(buildArquivoContratoSubForm(), "Arquivo Contrato",
		// null);

		return tabSheet_2;
	}

	/*
	 * private Component buildHistoricoReajustesSubForm() { String[] atributos =
	 * new String[] { "indice", "valorAnterior", "valorAtual", "dataReajustes",
	 * "observacao" }; String[] headers = new String[] { "Índice",
	 * "Valor Anterior", "Valor Atual", "Data Reajustes", "Observação" };
	 * 
	 * this.historicoReajustesSubForm = new
	 * SubFormComponent<ContratoHistoricoReajuste,
	 * Integer>(ContratoHistoricoReajuste.class, atributos, headers) {
	 * 
	 * private static final long serialVersionUID = 1L;
	 * 
	 * @Override protected TableFieldFactory getFieldFactory() { return new
	 * TableFieldFactory() {
	 * 
	 * /**
	 */
	// private static final long serialVersionUID = 1L;

	private SubFormComponent<ContratoHistoricoReajuste, Integer> buildHistoricoReajustesSubForm() {
		SubFormComponent<ContratoHistoricoReajuste, Integer> subForm = new SubFormComponent<ContratoHistoricoReajuste, Integer>(
				ContratoHistoricoReajuste.class, new String[] { "indice", "valorAnterior", "valorAtual", "dataReajuste", "observacao" },
				new String[] { "Índice", "Valor Anterior", "Valor Atual", "Data Reajuste", "Observação" }) {

			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					@Override
					public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {

						if ("indice".equals(propertyId)) {
							TextField textField = new TextField();
							textField.setSizeFull();
							textField.setConverter(new StringToBigDecimalConverter());
							return textField;
						} else if ("valorAnterior".equals(propertyId)) {
							/*
							 * TextField textField = new TextField();
							 * textField.setSizeFull();
							 * textField.setConverter(new
							 * StringToBigDecimalConverter());
							 */
							return ComponentUtil.buildCurrencyField(null);
						} else if ("valorAtual".equals(propertyId)) {
							/*
							 * TextField textField = new TextField();
							 * textField.setSizeFull();
							 * textField.setConverter(new
							 * StringToBigDecimalConverter());
							 */
							return ComponentUtil.buildCurrencyField(null);
						} else if ("observacao".equals(propertyId)) {
							/*
							 * TextField textField = new TextField();
							 * textField.setSizeFull();
							 */

							return ComponentUtil.buildCurrencyField(null);
						} else if ("dataReajuste".equals(propertyId)) {
							DateField dateField = new DateField();
							dateField.setSizeFull();
							return dateField;
						}

						return ComponentUtil.buildTextField(null);

					}

				};
			}

			protected ContratoHistoricoReajuste getNovo() {
				ContratoHistoricoReajuste contratoHistoricoReajuste = controller.novoContratoHistoricoReajuste();
				return contratoHistoricoReajuste;
			}

			protected void getRemoverSelecionados(List<ContratoHistoricoReajuste> values) {
				controller.removerContratoHistoricoReajuste(values);
			}

			@Override
			public boolean validateItems(List<ContratoHistoricoReajuste> items) {
				// TODO Auto-generated method stub
				return true;
			}
		};

		historicoReajustesSubForm = subForm;

		return subForm;
	}
	
	
	private SubFormComponent<ContratoProduto, Integer> buildObjetoProdutoSubForm() {
		SubFormComponent<ContratoProduto, Integer> subForm = new SubFormComponent<ContratoProduto, Integer>(
				ContratoProduto.class, new String[] { "produto.nome" }, new String[] { "Produto" }) {

			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					@Override
					public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {

						if ("produto.nome".equals(propertyId)) {
							
							List<Produto> list = cmbProduto.getModel().getAll();
						    ComboBox cmbProdutoObjeto = new ComboBox();
						    BeanItemContainer<ComboItemValue> objects =    new BeanItemContainer<ComboItemValue>(ComboItemValue.class);
						    
						    for (Produto val : list) {
						    	ComboItemValue item = new ComboItemValue();
						    	item.setBean(val);
						    	objects.addBean(item);
						    }
						    
						    
						    cmbProdutoObjeto.setContainerDataSource(objects);
						    cmbProdutoObjeto.setItemCaptionMode(ItemCaptionMode.PROPERTY);
						    cmbProdutoObjeto.setImmediate(true);
							cmbProdutoObjeto.setSizeFull();
							cmbProdutoObjeto.setStyleName("manyToOneCombo");
						    cmbProdutoObjeto.setItemCaptionPropertyId("caption");
						    cmbProdutoObjeto.addValueChangeListener(new Property.ValueChangeListener() {
					            @Override
					            public void valueChange(ValueChangeEvent event) {
					                // Will display 'null selected' when nullPerson is selected.
					                Notification.show(event.getProperty().getValue() + " selected");
					            }
					        });
						    
							
							return cmbProdutoObjeto;
							
							
							

						}  else {
							return ComponentUtil.buildTextField(null);
						}
					}

				};
			}

			protected ContratoProduto getNovo() {
				ContratoProduto contratoProduto = controller.novoContratoProduto();
				return contratoProduto;
			}

			protected void getRemoverSelecionados(List<ContratoProduto> values) {
				controller.removerContratoProduto(values);
			}

			@Override
			public boolean validateItems(List<ContratoProduto> items) {

				return true;
			}
		};

		contratoProdutoSubForm = subForm;
		return subForm;
	}
	
	

	/*
	 * private Component buildHistoricoFaturamentoSubForm() { String[] atributos
	 * = new String[] { "dataFatura", "valor" }; String[] headers = new String[]
	 * { "Data Fatura", "Valor" };
	 * 
	 * this.historicoFaturamentoSubForm = new
	 * SubFormComponent<ContratoHistFaturamento,
	 * Integer>(ContratoHistFaturamento.class, atributos, headers) {
	 * 
	 * private static final long serialVersionUID = 1L;
	 * 
	 * @Override protected TableFieldFactory getFieldFactory() { return new
	 * TableFieldFactory() {
	 * 
	 * /**
	 */
	// private static final long serialVersionUID = 1L;

	private SubFormComponent<ContratoHistFaturamento, Integer> buildHistoricoFaturaSubForm() {
		SubFormComponent<ContratoHistFaturamento, Integer> subForm = new SubFormComponent<ContratoHistFaturamento, Integer>(
				ContratoHistFaturamento.class, new String[] { "dataFatura", "valor" }, new String[] { "Data Fatura", "Valor" }) {

			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					@Override
					public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {

						if ("dataFatura".equals(propertyId)) {
							DateField dateField = new DateField();
							dateField.setSizeFull();
							return dateField;

						} else if ("valor".equals(propertyId)) {
							/*
							 * TextField textField = new TextField();
							 * textField.setSizeFull();
							 * textField.setConverter(new
							 * StringToBigDecimalConverter());
							 */
							return ComponentUtil.buildCurrencyField(null);
						} else {
							return ComponentUtil.buildTextField(null);
						}
					}

				};
			}

			protected ContratoHistFaturamento getNovo() {
				ContratoHistFaturamento contratoHistFaturamento = controller.novoContratoHistFaturamento();
				return contratoHistFaturamento;
			}

			protected void getRemoverSelecionados(List<ContratoHistFaturamento> values) {
				controller.removerContratoHistFaturamento(values);
			}

			@Override
			public boolean validateItems(List<ContratoHistFaturamento> items) {

				return true;
			}
		};

		historicoFaturamentoSubForm = subForm;
		return subForm;
	}

	private Component buildPrevisaoFaturamentoSubForm() {

		contratoLayout = new VerticalLayout();
		contratoLayout.setImmediate(false);
		contratoLayout.setSizeFull();
		contratoLayout.setMargin(false);
		contratoLayout.setSpacing(true);

		String[] atributos = new String[] { "pessoa", "numeroParcela", "dataPrevista", "valor" };

		String[] headers = new String[] { "Pessoa", "Número Parcela", "Data Prevista", "Valor" };

		this.previsaoFaturamentoSubForm = new SubFormComponent<ContratoPrevFaturamento, Integer>(ContratoPrevFaturamento.class, atributos, headers) {

			private static final long serialVersionUID = 1L;

			/*
			 * public SubFormComponent<ContratoPrevFaturamento, Integer>
			 * buildPrevisaoFaturamentoSubForm() {
			 * SubFormComponent<ContratoPrevFaturamento, Integer> subForm = new
			 * SubFormComponent<ContratoPrevFaturamento, Integer>(
			 * ContratoPrevFaturamento.class, new String[] { "pessoa",
			 * "dataPrevista", "valor" }, new String[] { "Pessoa",
			 * "Data Prevista", "Valor" }) {
			 */

			@Override
			protected void adicionarBotoes(Table table) {

			}

			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					@Override
					public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {

						if ("dataPrevista".equals(propertyId)) {
							DateField dateField = new DateField();
							dateField.setSizeFull();
							return dateField;

						}

						else if ("pessoa".equals(propertyId)) {
							TextField pessoaText = ComponentUtil.buildTextField(null);

							pessoaText.setConverter(new Converter<String, Pessoa>() {

								/**
								 * 
								 */
								private static final long serialVersionUID = 1L;

								@Override
								public Pessoa convertToModel(String value, Class<? extends Pessoa> targetType, Locale locale)
										throws com.vaadin.data.util.converter.Converter.ConversionException {
									return null;
								}

								@Override
								public String convertToPresentation(Pessoa value, Class<? extends String> targetType, Locale locale)
										throws com.vaadin.data.util.converter.Converter.ConversionException {
									return value.getNome();
								}

								@Override
								public Class<Pessoa> getModelType() {
									return Pessoa.class;
								}

								@Override
								public Class<String> getPresentationType() {
									return String.class;
								}
							});

							pessoaText.setReadOnly(true);
							return pessoaText;

						} else if ("valor".equals(propertyId)) {
							/*
							 * TextField textField = new TextField();
							 * textField.setSizeFull();
							 * textField.setConverter(new
							 * StringToBigDecimalConverter()); return textField;
							 */
							return ComponentUtil.buildCurrencyField(null);
						} else {
							return ComponentUtil.buildTextField(null);
						}
					}

				};
			}

			protected ContratoPrevFaturamento getNovo() {
				ContratoPrevFaturamento contratoPrevFaturamento = controller.novoContratoPrevFaturamento();
				return contratoPrevFaturamento;
			}

			protected void getRemoverSelecionados(List<ContratoPrevFaturamento> values) {
				controller.removerContratoPrevFaturamento(values);
			}

			@Override
			public boolean validateItems(List<ContratoPrevFaturamento> items) {
				return true;
			}
		};

		contratoLayout.addComponent(this.previsaoFaturamentoSubForm);
		contratoLayout.setExpandRatio(previsaoFaturamentoSubForm, 3);

		return contratoLayout;
	}

	public void preencheContrato(Contrato contrato) {

		contrato.setPessoa((Pessoa) cbmPessoa.getValue());
		contrato.setTemplate((Template)  cbmDocumento.getValue());
		contrato.setContabilConta((ContabilConta) cbmContabilConta.getValue());
		contrato.setContratoSolicitacaoServico((ContratoSolicitacaoServico) cmbSolicitacaoServico.getValue());
		contrato.setDataCadastro(dtCadastro.getValue());
		contrato.setDataFimVigencia(dtFimVigencia.getValue());
		contrato.setDataInicioVigencia(dtVigencia.getValue());
		contrato.setDescricao(txaDescricao.getValue());
		contrato.setDiaFaturamento(txtDiaFaturamento.getConvertedValue() != null ? (Integer) txtDiaFaturamento.getConvertedValue() : 0);
		// contrato.setPrimeiroVencimento(dtPrimeiroVencimento.getValue());
		contrato.setIntervaloEntreParcelas(txtIntervaloParcelas.getConvertedValue() != null ? (Integer) txtIntervaloParcelas.getConvertedValue() : 0);
		contrato.setNome(txtNome.getValue());
		contrato.setNumero(txtNumero.getValue());
		contrato.setObservacao(txaObservacoes.getValue());
		
		contrato.setQuantidadeParcelas(Integer.parseInt(getTxtQuantidadeParcelas().getValue()));
		contrato.setTipoContrato((TipoContrato) cbmTipoContrato.getValue());
		contrato.setValor((BigDecimal) txtValor.getConvertedValue());

	}

	public void preencheContratoForm(Contrato contrato) {
		cbmPessoa.setValue(contrato.getPessoa());
		cbmDocumento.setValue(contrato.getTemplate());
		cbmContabilConta.setValue(contrato.getContabilConta());
		cmbSolicitacaoServico.setValue(contrato.getContratoSolicitacaoServico());
		dtCadastro.setValue(contrato.getDataCadastro());
		dtFimVigencia.setValue(contrato.getDataFimVigencia());
		dtVigencia.setValue(contrato.getDataInicioVigencia());
		txaDescricao.setValue(contrato.getDescricao());
		txtDiaFaturamento.setConvertedValue(contrato.getDiaFaturamento());
		// dtPrimeiroVencimento.setValue(contrato.getPrimeiroVencimento());
		txtIntervaloParcelas.setConvertedValue(contrato.getIntervaloEntreParcelas());
		// getTxtIntervaloParcelas().setValue(String.valueOf(contrato.getIntervaloEntreParcelas()));
		txtNome.setValue(contrato.getNome());
		txtNumero.setValue(contrato.getNumero());
		txaObservacoes.setValue(contrato.getObservacao());
	
		txtQuantidadeParcelas.setValue(String.valueOf(contrato.getQuantidadeParcelas()));
		cbmTipoContrato.setValue(contrato.getTipoContrato());
		txtValor.setConvertedValue(contrato.getValor());
		// getTxtValor().setValue(contrato.getValor().toString());

		historicoFaturamentoSubForm.fillWith(contrato.getContratosHistoricosFaturamentos());
		historicoReajustesSubForm.fillWith(contrato.getContratosHistoricosReajustes());
		previsaoFaturamentoSubForm.fillWith(contrato.getContratosPrevisoesFaturamentos());

		/*
		 * this.fillContratoHistoricoFaturamentoSubForm(contrato.
		 * getContratosHistoricosFaturamentos());
		 * 
		 * this.fillContratoHistoricosReajustesSubForm(contrato.
		 * getContratosHistoricosReajustes());
		 * 
		 * this.fillContratosPrevisoesFaturamentosSubForm(contrato.
		 * getContratosPrevisoesFaturamentos());
		 */

	}

	public Button getBtnGerarContrato() {
		return btnGerarContrato;
	}

	public void setBtnGerarContrato(Button btnGerarContrato) {
		this.btnGerarContrato = btnGerarContrato;
	}

	public ManyToOneCombo<ContratoSolicitacaoServico> getCmbSolicitacaoServico() {
		return cmbSolicitacaoServico;
	}

	public void setCmbSolicitacaoServico(ManyToOneCombo<ContratoSolicitacaoServico> cmbSolicitacaoServico) {
		this.cmbSolicitacaoServico = cmbSolicitacaoServico;
	}

	public ManyToOneCombo<ContabilConta> getCbmContabilConta() {
		return cbmContabilConta;
	}

	public void setCbmContabilConta(ManyToOneCombo<ContabilConta> cbmContabilConta) {
		this.cbmContabilConta = cbmContabilConta;
	}

	public ManyToOneCombo<Pessoa> getCbmPessoa() {
		return cbmPessoa;
	}

	public void setCbmPessoa(ManyToOneCombo<Pessoa> cbmPessoa) {
		this.cbmPessoa = cbmPessoa;
	}

	public ManyToOneCombo<TipoContrato> getCbmTipoContrato() {
		return cbmTipoContrato;
	}

	public void setCbmTipoContrato(ManyToOneCombo<TipoContrato> cbmTipoContrato) {
		this.cbmTipoContrato = cbmTipoContrato;
	}

	public void setCbmDocumento(ManyToOneCombo<Template> cbmDocumento) {
		this.cbmDocumento = cbmDocumento;
	}

	public ManyToOneCombo<Template> getCbmDocumento() {
		return cbmDocumento;
	}
	
	
	public Button getBtnGerarParcelas() {
		return btnGerarParcelas;
	}

	public void setBtnGerarParcelas(Button btnGerarParcelas) {
		this.btnGerarParcelas = btnGerarParcelas;
	}

	public SubFormComponent<ContratoPrevFaturamento, Integer> getPrevisaoFaturamentoSubForm() {
		return previsaoFaturamentoSubForm;
	}

	public void setPrevisaoFaturamentoSubForm(SubFormComponent<ContratoPrevFaturamento, Integer> previsaoFaturamentoSubForm) {
		this.previsaoFaturamentoSubForm = previsaoFaturamentoSubForm;
	}

	public SubFormComponent<ContratoHistFaturamento, Integer> getHistoricoFaturamentoSubForm() {
		return historicoFaturamentoSubForm;
	}

	public void setHistoricoFaturamentoSubForm(SubFormComponent<ContratoHistFaturamento, Integer> historicoFaturamentoSubForm) {
		this.historicoFaturamentoSubForm = historicoFaturamentoSubForm;
	}

	public SubFormComponent<ContratoHistoricoReajuste, Integer> getHistoricoReajustesSubForm() {
		return historicoReajustesSubForm;
	}

	public void setHistoricoReajustesSubForm(SubFormComponent<ContratoHistoricoReajuste, Integer> historicoReajustesSubForm) {
		this.historicoReajustesSubForm = historicoReajustesSubForm;
	}

	public TabSheet getTabSheet_2() {
		return tabSheet_2;
	}

	public void setTabSheet_2(TabSheet tabSheet_2) {
		this.tabSheet_2 = tabSheet_2;
	}
	
	public ComboBox getCmbEstadoObjeto() {
		return cmbEstadoObjeto;
	}

	public void setCmbEstadoObjeto(ComboBox cmbEstadoObjeto) {
		this.cmbEstadoObjeto = cmbEstadoObjeto;
	}

	public TabSheet getTabSheet_1() {
		return tabSheet_1;
	}

	public void setTabSheet_1(TabSheet tabSheet_1) {
		this.tabSheet_1 = tabSheet_1;
	}

	public TextArea getTxaAditivo() {
		return txaAditivo;
	}

	public void setTxaAditivo(TextArea txaAditivo) {
		this.txaAditivo = txaAditivo;
	}

	public TextField getTxtLogradouroObjeto() {
		return txtLogradouroObjeto;
	}

	public void setTxtLogradouroObjeto(TextField txtLogradouroObjeto) {
		this.txtLogradouroObjeto = txtLogradouroObjeto;
	}

	public TextField getTxtNumeroObjeto() {
		return txtNumeroObjeto;
	}

	public void setTxtNumeroObjeto(TextField txtNumeroObjeto) {
		this.txtNumeroObjeto = txtNumeroObjeto;
	}

	public TextField getTxtComplementoObjeto() {
		return txtComplementoObjeto;
	}

	public void setTxtComplementoObjeto(TextField txtComplementoObjeto) {
		this.txtComplementoObjeto = txtComplementoObjeto;
	}

	public TextField getTxtBairroObjeto() {
		return txtBairroObjeto;
	}

	public void setTxtBairroObjeto(TextField txtBairroObjeto) {
		this.txtBairroObjeto = txtBairroObjeto;
	}

	public TextField getTxtCidadeObjeto() {
		return txtCidadeObjeto;
	}

	public void setTxtCidadeObjeto(TextField txtCidadeObjeto) {
		this.txtCidadeObjeto = txtCidadeObjeto;
	}

	public MaskedTextField getTxtCEPObjeto() {
		return txtCEPObjeto;
	}

	public void setTxtCEPObjeto(MaskedTextField txtCEPObjeto) {
		this.txtCEPObjeto = txtCEPObjeto;
	}

	public TextField getTxtValorTarifa() {
		return txtValorTarifa;
	}

	public void setTxtValorTarifa(TextField txtValorTarifa) {
		this.txtValorTarifa = txtValorTarifa;
	}

	/*
	 * public PopupDateField getDtPrimeiroVencimento() { return
	 * dtPrimeiroVencimento; }
	 * 
	 * public void setDtPrimeiroVencimento(PopupDateField dtPrimeiroVencimento)
	 * { this.dtPrimeiroVencimento = dtPrimeiroVencimento; }
	 */

}