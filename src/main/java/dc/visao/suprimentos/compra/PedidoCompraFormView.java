package dc.visao.suprimentos.compra;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.suprimentos.compra.PedidoCompraFormController;
import dc.entidade.geral.FornecedorEntity;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.suprimentos.compra.PedidoDetalheEntity;
import dc.entidade.suprimentos.compra.TipoPedidoEntity;
import dc.visao.framework.component.SubFormComponent;
import dc.visao.framework.geral.MainController;
import dc.visao.framework.util.ComponentUtil;

public class PedidoCompraFormView extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private MainController mainController;

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private TabSheet subForms;
	@AutoGenerated
	private TabSheet produtos;
	@AutoGenerated
	private TabSheet fornecedores;
	@AutoGenerated
	private GridLayout fields;
	@AutoGenerated
	private GridLayout topFields;
	@AutoGenerated
	private OptionGroup optFormaPagto;
	@AutoGenerated
	private OptionGroup optTipoFrete;
	@AutoGenerated
	private Label lblId;
	@AutoGenerated
	private TextField txtTotalPedido;
	@AutoGenerated
	private TextField txtValorDesconto;
	@AutoGenerated
	private TextField txtTaxaDesconto;
	@AutoGenerated
	private TextField txtValorSubTotal;
	@AutoGenerated
	private TextField txtLocalCobranca;
	@AutoGenerated
	private TextField txtLocalEntrega;
	@AutoGenerated
	private TextField txtContato;
	@AutoGenerated
	private PopupDateField calDataPagamento;
	@AutoGenerated
	private PopupDateField calDataEntrega;
	@AutoGenerated
	private PopupDateField calDataPedido;
	@AutoGenerated
	private ComboBox cmbFornecedor;
	@AutoGenerated
	private ComboBox cmbTipoPedido;

	private PedidoCompraFormController controller;

	private SubFormComponent<PedidoDetalheEntity, Integer> pedidoDetalheSubForm;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 * 
	 * @param controller
	 */
	public PedidoCompraFormView(PedidoCompraFormController controller) {
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

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		topFields = new GridLayout(6, 1);
		topFields.setImmediate(false);
		topFields.setWidth("100.0%");
		topFields.setSpacing(true);

		// lblId
		lblId = new Label();
		lblId.setCaption("Id");
		lblId.setImmediate(false);
		lblId.setSizeFull();
		topFields.addComponent(lblId, 0, 0);

		// cmbTipoPedido
		cmbTipoPedido = ComponentUtil.buildComboBox("Tipo Pedido de Compra");
		topFields.addComponent(cmbTipoPedido, 1, 0);

		// cmbFornecedor
		cmbFornecedor = ComponentUtil.buildComboBox("Fornecedor");
		topFields.addComponent(cmbFornecedor, 2, 0, 4, 0);

		// calDataPedido
		calDataPedido = new PopupDateField();
		calDataPedido.setCaption("Data Pedido Compra");
		calDataPedido.setImmediate(false);
		calDataPedido.setWidth("-1px");
		calDataPedido.setHeight("-1px");
		topFields.addComponent(calDataPedido, 5, 0);

		mainLayout.addComponent(topFields);
		mainLayout.setExpandRatio(topFields, 1);

		// fields
		buildFields();

		// subForms
		subForms = buildSubForms();
		mainLayout.addComponent(subForms);

		subForms.setHeight("100%");
		mainLayout.setExpandRatio(subForms, 10);

		return mainLayout;
	}

	@AutoGenerated
	private void buildFields() {
		// common part: create layout
		fields = new GridLayout(6, 4);
		fields.setImmediate(false);
		fields.setWidth("100.0%");
		// fields.setHeight("100.0%");
		fields.setMargin(true);
		fields.setSpacing(true);

		// calDataEntrega
		calDataEntrega = new PopupDateField();
		calDataEntrega.setCaption("Data Entrega");
		calDataEntrega.setImmediate(false);
		calDataEntrega.setWidth("-1px");
		calDataEntrega.setHeight("-1px");
		fields.addComponent(calDataEntrega, 0, 0);

		// calDataPagamento
		calDataPagamento = new PopupDateField();
		calDataPagamento.setCaption("Data Pagamento");
		calDataPagamento.setImmediate(false);
		calDataPagamento.setWidth("-1px");
		calDataPagamento.setHeight("-1px");
		fields.addComponent(calDataPagamento, 1, 0);

		// txtContato
		txtContato = ComponentUtil.buildTextField("Contato");
		fields.addComponent(txtContato, 2, 0, 5, 0);

		// txtLocalEntrega
		txtLocalEntrega = ComponentUtil.buildTextField("Local Entrega");
		fields.addComponent(txtLocalEntrega, 0, 1, 2, 1);

		// txtLocalCobranca
		txtLocalCobranca = ComponentUtil.buildTextField("Local Cobrança");
		fields.addComponent(txtLocalCobranca, 3, 1, 5, 1);

		// txtValorSubTotal
		txtValorSubTotal = ComponentUtil.buildCurrencyField("Valor SubTotal");
		fields.addComponent(txtValorSubTotal, 0, 2);

		// txtTaxaDesconto
		txtTaxaDesconto = ComponentUtil.buildPercentageField("Taxa Desconto");
		fields.addComponent(txtTaxaDesconto, 1, 2);

		// txtValorDesconto
		txtValorDesconto = ComponentUtil.buildCurrencyField("Valor Desconto");
		fields.addComponent(txtValorDesconto, 2, 2);

		// txtTotalPedido
		txtTotalPedido = ComponentUtil.buildCurrencyField("Total PedidoCompra");
		fields.addComponent(txtTotalPedido, 3, 2);

		// optTipoFrete
		optTipoFrete = new OptionGroup();
		optTipoFrete.setStyleName("horizontal");
		optTipoFrete.setCaption("Tipo Frete");
		optTipoFrete.setImmediate(false);
		optTipoFrete.setWidth("-1px");
		optTipoFrete.setHeight("-1px");
		optTipoFrete.addItem("C");
		optTipoFrete.setItemCaption("C", "CIF");
		optTipoFrete.addItem("F");
		optTipoFrete.setItemCaption("F", "FOB");

		fields.addComponent(optTipoFrete, 0, 3, 2, 3);

		// optFormaPagto
		optFormaPagto = new OptionGroup();
		optFormaPagto.setStyleName("horizontal");
		optFormaPagto.setCaption("Forma Pagto");
		optFormaPagto.setImmediate(false);
		optFormaPagto.setWidth("-1px");
		optFormaPagto.setHeight("-1px");
		optFormaPagto.addItem("0");
		optFormaPagto.setItemCaption("0", "À Vista");
		optFormaPagto.addItem("1");
		optFormaPagto.setItemCaption("1", "À Prazo");
		optFormaPagto.addItem("2");
		optFormaPagto.setItemCaption("2", "Outros");

		fields.addComponent(optFormaPagto, 3, 3, 5, 3);
	}

	@AutoGenerated
	private TabSheet buildSubForms() {
		// common part: create layout
		subForms = new TabSheet();
		subForms.setImmediate(true);

		subForms.addTab(fields, "Detalhes", null);

		pedidoDetalheSubForm = new SubFormComponent<PedidoDetalheEntity, Integer>(
				PedidoDetalheEntity.class, new String[] { "produto",
						"quantidade", "valorUnitario", "valorSubtotal",
						"valorDesconto", "valorTotal" }, new String[] {
						"Produto", "Quantidade", "Valor Unitário",
						"Valor SubTotal", "Valor Desconto", "Valor Total" },
				new String[] { "valorSubtotal", "valorDesconto", "valorTotal" }) {
			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					@Override
					public Field<?> createField(Container container,
							Object itemId, Object propertyId,
							Component uiContext) {
						if ("produto".equals(propertyId)) {
							ComboBox comboBox = ComponentUtil
									.buildComboBox(null);
							BeanItemContainer<ProdutoEntity> produtoContainer = new BeanItemContainer<>(
									ProdutoEntity.class,
									controller.buscarProdutos());
							comboBox.setContainerDataSource(produtoContainer);
							comboBox.setItemCaptionPropertyId("descricao");
							return comboBox;
						} else if ("quantidade".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildNumberField(null);
							textField.addBlurListener(getBlurListener(
									container, itemId, propertyId));
							return textField;
						} else if ("valorUnitario".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildCurrencyField(null);
							textField.addBlurListener(getBlurListener(
									container, itemId, propertyId));
							return textField;
						} else if ("valorSubtotal".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildCurrencyField(null);
							textField.setReadOnly(true);
							return textField;
						} else if ("valorDesconto".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildCurrencyField(null);
							textField.addBlurListener(getBlurListener(
									container, itemId, propertyId));
							return textField;
						} else if ("valorTotal".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildCurrencyField(null);
							textField.setReadOnly(true);
							return textField;
						}
						return null;
					}

					private BlurListener getBlurListener(
							final Container container, final Object itemId,
							final Object propertyId) {
						return new BlurListener() {
							@Override
							public void blur(BlurEvent event) {
								Property<BigDecimal> quantidade = get("quantidade");
								Property<BigDecimal> valorUnitario = get("valorUnitario");
								Property<BigDecimal> valorSubtotal = get("valorSubtotal");
								Property<BigDecimal> valorDesconto = get("valorDesconto");
								Property<BigDecimal> valorTotal = get("valorTotal");

								if (quantidade.getValue() != null
										&& valorUnitario.getValue() != null) {
									BigDecimal q = quantidade.getValue();
									BigDecimal vu = valorUnitario.getValue();
									valorSubtotal.setValue(q.multiply(vu));
								}

								if (valorSubtotal.getValue() != null) {
									BigDecimal vs = valorSubtotal.getValue();
									BigDecimal vd = BigDecimal.ZERO;
									if (valorDesconto.getValue() != null) {
										vd = valorDesconto.getValue();
									} else {
										valorDesconto.setValue(BigDecimal.ZERO);
									}
									valorTotal.setValue(vs.subtract(vd));
								}

							}

							@SuppressWarnings("unchecked")
							private Property<BigDecimal> get(String property) {
								Item item = container.getItem(itemId);
								return item.getItemProperty(property);
							}
						};
					}
				};
			}

			@Override
			protected PedidoDetalheEntity getNovo() {
				PedidoDetalheEntity pedidoDetalhe = controller
						.novoPedidoDetalhe();
				return pedidoDetalhe;
			}

			@Override
			protected void getRemoverSelecionados(
					List<PedidoDetalheEntity> values) {
				controller.removerPedidoDetalhe(values);
			}

			@Override
			public boolean validateItems(List<PedidoDetalheEntity> items) {
				for (PedidoDetalheEntity pedidoDetalhe : items) {
					if (pedidoDetalhe.getProduto() == null
							|| pedidoDetalhe.getQuantidade() == null) {
						return false;
					}
				}
				return true;
			}
		};

		subForms.addTab(pedidoDetalheSubForm, "Produtos", null);

		return subForms;
	}

	public TabSheet getProdutos() {
		return produtos;
	}

	public OptionGroup getOptFormaPagto() {
		return optFormaPagto;
	}

	public OptionGroup getOptTipoFrete() {
		return optTipoFrete;
	}

	public TextField getTxtTotalPedido() {
		return txtTotalPedido;
	}

	public TextField getTxtValorDesconto() {
		return txtValorDesconto;
	}

	public TextField getTxtTaxaDesconto() {
		return txtTaxaDesconto;
	}

	public TextField getTxtValorSubTotal() {
		return txtValorSubTotal;
	}

	public TextField getTxtLocalCobranca() {
		return txtLocalCobranca;
	}

	public TextField getTxtLocalEntrega() {
		return txtLocalEntrega;
	}

	public TextField getTxtContato() {
		return txtContato;
	}

	public PopupDateField getCalDataPagamento() {
		return calDataPagamento;
	}

	public PopupDateField getCalDataEntrega() {
		return calDataEntrega;
	}

	public PopupDateField getCalDataPedido() {
		return calDataPedido;
	}

	public ComboBox getCmbFornecedor() {
		return cmbFornecedor;
	}

	public ComboBox getCmbTipoPedido() {
		return cmbTipoPedido;
	}

	public void fillCmbTipoPedido(List<TipoPedidoEntity> lista) {
		BeanItemContainer<TipoPedidoEntity> tipoRequisicaoContainer = new BeanItemContainer<>(
				TipoPedidoEntity.class, lista);
		cmbTipoPedido.setContainerDataSource(tipoRequisicaoContainer);
		cmbTipoPedido.setItemCaptionPropertyId("descricao");
	}

	public void fillCmbFornecedor(List<FornecedorEntity> lista) {
		BeanItemContainer<FornecedorEntity> tipoRequisicaoContainer = new BeanItemContainer<>(
				FornecedorEntity.class, lista);
		tipoRequisicaoContainer.addNestedContainerProperty("pessoa.nome");
		cmbFornecedor.setContainerDataSource(tipoRequisicaoContainer);
		cmbFornecedor.setItemCaptionPropertyId("pessoa.nome");
	}

	public void fillPedidoDetalhesSubForm(
			List<PedidoDetalheEntity> pedidoDetalhes) {
		pedidoDetalheSubForm.fillWith(pedidoDetalhes);
	}

	public Label getLblId() {
		return lblId;
	}

}