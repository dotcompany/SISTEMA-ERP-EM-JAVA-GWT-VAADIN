package dc.visao.comercial;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.addons.maskedtextfield.MaskedTextField;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.comercial.VendaFormController;
import dc.entidade.comercial.CondicaoPagamento;
import dc.entidade.comercial.Frete;
import dc.entidade.comercial.ItemOrcamento;
import dc.entidade.comercial.NotaFiscalTipo;
import dc.entidade.comercial.Orcamento;
import dc.entidade.comercial.VendaDetalhe;
import dc.entidade.folhapagamento.VendedorEntity;
import dc.entidade.geral.pessoal.ClienteEntity;
import dc.entidade.geral.pessoal.TransportadoraEntity;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.visao.framework.component.SubFormComponent;
import dc.visao.framework.component.manytoonecombo.ManyToOneComboField;
import dc.visao.framework.util.ComponentUtil;

public class VendaFormView extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private VerticalLayout mainLayout;

	VendaFormController controller;

	@AutoGenerated
	private GridLayout fields;

	ComboBox cmbTipoVenda;
	
	ManyToOneComboField<Orcamento> cmbOrcamento;
	
	ManyToOneComboField<NotaFiscalTipo> cmbTipoNotaFiscal;
	ManyToOneComboField<VendedorEntity> cmbVendedor;
	ManyToOneComboField<ClienteEntity> cmbCliente;
	ManyToOneComboField<TransportadoraEntity> cmbTransportadora;
	ManyToOneComboField<CondicaoPagamento> cmbCondicoesPagamento;
	
	ComboBox cmbFormaPagamento;
	
	ManyToOneComboField<Frete> cmbFrete;

	PopupDateField dataVenda, dataSaida;
	TextField txtNumeroFatura, txtLocalEntrega, txtLocalCobranca,
			txtTaxaDesconto, txtValorDesconto, txtValorTotal;

	MaskedTextField txtHoraSaida;

	TextField txtValorSubTotal, txtValorFrete, txtTaxaComissao,
			txtValorComissao, txtObservacoes;

	private SubFormComponent<VendaDetalhe, Integer> vendaDetalheSubForm;

	@AutoGenerated
	private TabSheet subForms;

	public VendaFormView(VendaFormController controller) {
		this.controller = controller;
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setSizeFull();
		mainLayout.setMargin(false);
		mainLayout.setSpacing(true);
		setHeight("100%");

		subForms = new TabSheet();
		subForms = buildSubForms();
		subForms.setSizeFull();
		subForms.setImmediate(true);
		mainLayout.addComponent(subForms);
		mainLayout.setExpandRatio(subForms, 1);
		return mainLayout;

	}

	@AutoGenerated
	private GridLayout buildFields() {
		// common part: create layout
		fields = new GridLayout(8, 8);
		fields.setImmediate(false);
		fields.setWidth("100.0%");
		fields.setHeight("-1px");
		fields.setMargin(true);
		fields.setSpacing(true);

		cmbTipoVenda = ComponentUtil.buildComboBox("Tipo de Venda");
		carregarTipoVenda();
		cmbTipoVenda.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				TIPO_VENDA tipo = (TIPO_VENDA) event.getProperty().getValue();

				if (tipo == null) {
					cmbOrcamento.setReadOnly(true);
				} else {

					if (tipo.equals(TIPO_VENDA.ORCAMENTO)) {
						cmbOrcamento.setReadOnly(false);
					} else {
						cmbOrcamento.setReadOnly(true);
					}
				}
			}
		});

		cmbOrcamento = new ManyToOneComboField<>(Orcamento.class);
		cmbOrcamento.setCaption("Orçamento");
		cmbOrcamento.setReadOnly(true);

		cmbOrcamento.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				Orcamento orcamento = (Orcamento) event.getProperty()
						.getValue();
				cmbCliente.setValue(orcamento.getCliente());
				cmbVendedor.setValue(orcamento.getVendedor());
				cmbCondicoesPagamento.setValue(orcamento.getCondicaoPagamento());
				if (orcamento.getValorSubTotal() != null)
					txtValorSubTotal.setValue(orcamento.getValorSubTotal()
							.toString());
				if (orcamento.getValorFrete() != null)
					txtValorFrete
							.setValue(orcamento.getValorFrete().toString());
				if (orcamento.getTaxaComissao() != null)
					txtTaxaComissao.setValue(orcamento.getTaxaComissao()
							.toString());
				if (orcamento.getValorComissao() != null)
					txtValorComissao.setValue(orcamento.getValorComissao()
							.toString());

				if (orcamento.getTaxaDesconto() != null)
					txtTaxaDesconto.setValue(orcamento.getTaxaDesconto()
							.toString());
				if (orcamento.getValorDesconto() != null)
					txtValorDesconto.setValue(orcamento.getValorDesconto()
							.toString());
				if (orcamento.getValorTotal() != null)
					txtValorTotal
							.setValue(orcamento.getValorTotal().toString());

				List<ItemOrcamento> itensOrcamento = controller
						.carregarItensOrcamento(orcamento);

				List<VendaDetalhe> detalhes = new ArrayList<>();
				for (ItemOrcamento item : itensOrcamento) {
					VendaDetalhe vendaDetalhe = new VendaDetalhe();
					vendaDetalhe.setProduto(item.getProduto());
					vendaDetalhe.setQuantidade(item.getQuantidade());
					vendaDetalhe.setValorUnitario(item.getValorUnitario());
					vendaDetalhe.setValorSubTotal(item.getValorSubTotal());
					vendaDetalhe.setValorDesconto(item.getValorDesconto());
					vendaDetalhe.setValorTotal(item.getValorTotal());
					detalhes.add(vendaDetalhe);
				}
				preencheSubForm(detalhes);
				controller.preencherDetalhes(detalhes);

			}
		});

		cmbTipoNotaFiscal = new ManyToOneComboField<>(NotaFiscalTipo.class);
		cmbTipoNotaFiscal.setCaption("Tipo Nota Fiscal");

		cmbVendedor = new ManyToOneComboField<>(VendedorEntity.class);
		cmbVendedor.setCaption("Vendedor");

		cmbCliente = new ManyToOneComboField<>(ClienteEntity.class);
		cmbCliente.setCaption("Cliente");

		cmbTransportadora = new ManyToOneComboField<>(TransportadoraEntity.class);
		cmbTransportadora.setCaption("Transportadora");

		cmbCondicoesPagamento = new ManyToOneComboField<>(CondicaoPagamento.class);
		cmbCondicoesPagamento.setCaption("Condição Pagamento");

		cmbFormaPagamento = ComponentUtil.buildComboBox("Forma Pagamento");
		cmbFrete = new ManyToOneComboField<>(Frete.class);
		cmbFrete.setCaption("Frete");
		dataVenda = ComponentUtil.buildPopupDateField("Data Venda");
		dataSaida = ComponentUtil.buildPopupDateField("Data Saida");
		txtHoraSaida = ComponentUtil
				.buildMaskedTextField("Hora Saida", "##:##");
		txtNumeroFatura = ComponentUtil.buildNumericField("Numero da Fatura");

		fields.addComponent(cmbTipoVenda, 0, 0);

		fields.addComponent(cmbOrcamento, 1, 0,2,0);
		fields.addComponent(cmbTipoNotaFiscal, 3, 0,4,0);
		fields.addComponent(cmbVendedor, 0, 1);
		fields.addComponent(cmbCliente, 2,1,3, 1);

		fields.addComponent(cmbTransportadora, 4, 1);
		fields.addComponent(cmbCondicoesPagamento, 0, 2);

		fields.addComponent(cmbFormaPagamento, 2, 2);
		fields.addComponent(cmbFrete, 3, 2);

		fields.addComponent(dataVenda, 0, 3);
		fields.addComponent(dataSaida, 1, 3);
		fields.addComponent(txtHoraSaida, 2, 3);
		fields.addComponent(txtNumeroFatura, 3, 3);

		txtLocalEntrega = ComponentUtil.buildTextField("Local Entrega");
		txtLocalCobranca = ComponentUtil.buildTextField("Local Cobrança");
		fields.addComponent(txtLocalEntrega, 0, 4, 2, 4);
		fields.addComponent(txtLocalCobranca, 3, 4, 5, 4);

		txtValorSubTotal = ComponentUtil.buildCurrencyField("Valor SubTotal");
		txtValorFrete = ComponentUtil.buildCurrencyField("Valor Frete");
		txtTaxaComissao = ComponentUtil.buildNumberField("Taxa Comissão");

		txtValorComissao = ComponentUtil.buildCurrencyField("Valor Comissão");
		txtTaxaDesconto = ComponentUtil.buildNumberField("Taxa Desconto");
		txtValorDesconto = ComponentUtil.buildCurrencyField("Valor Desconto");
		txtValorTotal = ComponentUtil.buildCurrencyField("Valor Total");

		fields.addComponent(txtValorSubTotal, 0, 5);
		fields.addComponent(txtValorFrete, 1, 5);
		fields.addComponent(txtTaxaComissao, 2, 5);

		fields.addComponent(txtValorComissao, 3, 5);

		fields.addComponent(txtTaxaDesconto, 0, 6);
		fields.addComponent(txtValorDesconto, 1, 6);
		fields.addComponent(txtValorTotal, 2, 6);

		txtObservacoes = ComponentUtil.buildTextField("Observações");
		fields.addComponent(txtObservacoes, 0, 7, 5, 7);

		return fields;

	}

	public enum TIPO_VENDA {

		ORCAMENTO("Do Orçamento", "1"), VENDA_DIRETA("Venda Direta", "2");

		private TIPO_VENDA(String label, String codigo) {
			this.label = label;
			this.codigo = codigo;
		}

		private String label;
		private String codigo;

		public static TIPO_VENDA getTipoVenda(String codigo) {
			if (codigo.equals("0")) {
				return ORCAMENTO;
			}
			if (codigo.equals("1")) {
				return VENDA_DIRETA;
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

	public void carregarTipoVenda() {
		cmbTipoVenda.removeAllItems();
		cmbTipoVenda.addItem(TIPO_VENDA.ORCAMENTO);
		cmbTipoVenda.addItem(TIPO_VENDA.VENDA_DIRETA);
	}

	@AutoGenerated
	@SuppressWarnings("serial")
	private TabSheet buildSubForms() {

		vendaDetalheSubForm = new SubFormComponent<VendaDetalhe, Integer>(
				VendaDetalhe.class, new String[] { "produto", "quantidade",
						"valorUnitario", "valorSubTotal", "valorDesconto",
						"valorTotal" }, new String[] { "Produto", "Quantidade",
						"Valor Unitário", "Valor SubTotal", "Desconto",
						"Valor Total" }) {
			// "produto" }, new String[] {"Produto" }) {

			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					@Override
					public Field<?> createField(Container container,
							Object itemId, Object propertyId,
							Component uiContext) {

						if ("produto".equals(propertyId)) {
							ComboBox comboBox = ComponentUtil.buildComboBox(null);
							BeanItemContainer<ProdutoEntity> produtoContainer = new BeanItemContainer<>(ProdutoEntity.class,
									controller.buscarProdutos());
							
							produtoContainer.addNestedContainerProperty("nome");
							comboBox.setContainerDataSource(produtoContainer);
							comboBox.setItemCaptionMode(ItemCaptionMode.PROPERTY);
							comboBox.setImmediate(true);
							comboBox.setSizeFull();
							comboBox.setStyleName("manyToOneCombo");
							comboBox.setItemCaptionPropertyId("nome");
							comboBox.addValueChangeListener(new Property.ValueChangeListener() {
								@Override
								public void valueChange(
										ValueChangeEvent event) {
									// Will display 'null selected' when
									// nullPerson is selected.
									Notification.show(event
											.getProperty().getValue()
											+ " selected");
								}
							});

							return comboBox;
						}

						if ("quantidade".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildNumberField(null);
							return textField;
						}

						if ("valorUnitario".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildCurrencyField("Valor Unitário");
							return textField;
						}

						if ("valorSubTotal".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildCurrencyField("SubTotal");
							return textField;
						}

						if ("valorDesconto".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildCurrencyField("Desconto");
							return textField;
						}

						if ("valorTotal".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildCurrencyField("Valor Total");
							return textField;
						}

						return null;
					}
				};
			}

			protected VendaDetalhe getNovo() {
				VendaDetalhe detalhe = controller.novoDetalhe();
				return detalhe;
			}

			@Override
			public boolean validateItems(List<VendaDetalhe> items) {
				// TODO Auto-generated method stub
				return true;
			}
		};
		subForms.addTab(buildFields(), "Dados Principais", null);
		subForms.addTab(vendaDetalheSubForm, "Itens da Venda", null);
		return subForms;

	}

	public VerticalLayout getMainLayout() {
		return mainLayout;
	}

	public void setMainLayout(VerticalLayout mainLayout) {
		this.mainLayout = mainLayout;
	}

	public VendaFormController getController() {
		return controller;
	}

	public void setController(VendaFormController controller) {
		this.controller = controller;
	}

	public GridLayout getFields() {
		return fields;
	}

	public void setFields(GridLayout fields) {
		this.fields = fields;
	}

	public ComboBox getCmbTipoVenda() {
		return cmbTipoVenda;
	}

	public void setCmbTipoVenda(ComboBox cmbTipoVenda) {
		this.cmbTipoVenda = cmbTipoVenda;
	}
	
	public ManyToOneComboField<Orcamento> getCmbOrcamento() {
		return cmbOrcamento;
	}

	public void setCmbOrcamento(ManyToOneComboField<Orcamento> cmbOrcamento) {
		this.cmbOrcamento = cmbOrcamento;
	}

	public ManyToOneComboField<NotaFiscalTipo> getCmbTipoNotaFiscal() {
		return cmbTipoNotaFiscal;
	}

	public void setCmbTipoNotaFiscal(
			ManyToOneComboField<NotaFiscalTipo> cmbTipoNotaFiscal) {
		this.cmbTipoNotaFiscal = cmbTipoNotaFiscal;
	}

	public ManyToOneComboField<VendedorEntity> getCmbVendedor() {
		return cmbVendedor;
	}

	public void setCmbVendedor(ManyToOneComboField<VendedorEntity> cmbVendedor) {
		this.cmbVendedor = cmbVendedor;
	}

	public ManyToOneComboField<ClienteEntity> getCmbCliente() {
		return cmbCliente;
	}

	public void setCmbCliente(ManyToOneComboField<ClienteEntity> cmbCliente) {
		this.cmbCliente = cmbCliente;
	}

	public ManyToOneComboField<TransportadoraEntity> getCmbTransportadora() {
		return cmbTransportadora;
	}

	public void setCmbTransportadora(
			ManyToOneComboField<TransportadoraEntity> cmbTransportadora) {
		this.cmbTransportadora = cmbTransportadora;
	}

	public ManyToOneComboField<CondicaoPagamento> getCmbCondicoesPagamento() {
		return cmbCondicoesPagamento;
	}

	public void setCmbCondicoesPagamento(
			ManyToOneComboField<CondicaoPagamento> cmbCondicoesPagamento) {
		this.cmbCondicoesPagamento = cmbCondicoesPagamento;
	}

	public ComboBox getCmbFormaPagamento() {
		return cmbFormaPagamento;
	}

	public void setCmbFormaPagamento(ComboBox cmbFormaPagamento) {
		this.cmbFormaPagamento = cmbFormaPagamento;
	}

	public ManyToOneComboField<Frete> getCmbFrete() {
		return cmbFrete;
	}

	public void setCmbFrete(ManyToOneComboField<Frete> cmbFrete) {
		this.cmbFrete = cmbFrete;
	}

	public PopupDateField getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(PopupDateField dataVenda) {
		this.dataVenda = dataVenda;
	}

	public PopupDateField getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(PopupDateField dataSaida) {
		this.dataSaida = dataSaida;
	}

	public MaskedTextField getTxtHoraSaida() {
		return txtHoraSaida;
	}

	public void setTxtHoraSaida(MaskedTextField txtHoraSaida) {
		this.txtHoraSaida = txtHoraSaida;
	}

	public TextField getTxtNumeroFatura() {
		return txtNumeroFatura;
	}

	public void setTxtNumeroFatura(TextField txtNumeroFatura) {
		this.txtNumeroFatura = txtNumeroFatura;
	}

	public TextField getTxtLocalEntrega() {
		return txtLocalEntrega;
	}

	public void setTxtLocalEntrega(TextField txtLocalEntrega) {
		this.txtLocalEntrega = txtLocalEntrega;
	}

	public TextField getTxtLocalCobranca() {
		return txtLocalCobranca;
	}

	public void setTxtLocalCobranca(TextField txtLocalCobranca) {
		this.txtLocalCobranca = txtLocalCobranca;
	}

	public TextField getTxtTaxaDesconto() {
		return txtTaxaDesconto;
	}

	public void setTxtTaxaDesconto(TextField txtTaxaDesconto) {
		this.txtTaxaDesconto = txtTaxaDesconto;
	}

	public TextField getTxtValorDesconto() {
		return txtValorDesconto;
	}

	public void setTxtValorDesconto(TextField txtValorDesconto) {
		this.txtValorDesconto = txtValorDesconto;
	}

	public TextField getTxtValorTotal() {
		return txtValorTotal;
	}

	public void setTxtValorTotal(TextField txtValorTotal) {
		this.txtValorTotal = txtValorTotal;
	}

	public TextField getTxtValorSubTotal() {
		return txtValorSubTotal;
	}

	public void setTxtValorSubTotal(TextField txtValorSubTotal) {
		this.txtValorSubTotal = txtValorSubTotal;
	}

	public TextField getTxtValorFrete() {
		return txtValorFrete;
	}

	public void setTxtValorFrete(TextField txtValorFrete) {
		this.txtValorFrete = txtValorFrete;
	}

	public TextField getTxtTaxaComissao() {
		return txtTaxaComissao;
	}

	public void setTxtTaxaComissao(TextField txtTaxaComissao) {
		this.txtTaxaComissao = txtTaxaComissao;
	}

	public TextField getTxtValorComissao() {
		return txtValorComissao;
	}

	public void setTxtValorComissao(TextField txtValorComissao) {
		this.txtValorComissao = txtValorComissao;
	}

	public TextField getTxtObservacoes() {
		return txtObservacoes;
	}

	public void setTxtObservacoes(TextField txtObservacoes) {
		this.txtObservacoes = txtObservacoes;
	}

	public SubFormComponent<VendaDetalhe, Integer> getVendaDetalheSubForm() {
		return vendaDetalheSubForm;
	}

	public void setVendaDetalheSubForm(
			SubFormComponent<VendaDetalhe, Integer> vendaDetalheSubForm) {
		this.vendaDetalheSubForm = vendaDetalheSubForm;
	}

	public TabSheet getSubForms() {
		return subForms;
	}

	public void setSubForms(TabSheet subForms) {
		this.subForms = subForms;
	}

	public void preencheSubForm(List<VendaDetalhe> detalhes) {
		vendaDetalheSubForm.fillWith(detalhes);
	}

}