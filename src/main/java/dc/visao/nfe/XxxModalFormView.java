package dc.visao.nfe;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import dc.control.validator.ObjectValidator;
import dc.controller.nfe.ProdutoServicoFormController;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.visao.framework.component.manytoonecombo.ManyToOneCombo;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

public class XxxModalFormView extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProdutoServicoFormController controller;

	/**
	 * NFEDETALHE
	 */

	@AutoGenerated
	private VerticalLayout vlNfeDetalhe;

	@AutoGenerated
	private Panel plNfeDetalheSubForm;

	@AutoGenerated
	private Panel pNfeDetalhe;

	@AutoGenerated
	private GridLayout glNfeDetalhe;

	@AutoGenerated
	private TextField tfNumeroItem;

	@AutoGenerated
	private TextField tfCodigoProduto;

	@AutoGenerated
	private TextField tfGtin;

	@AutoGenerated
	private ManyToOneCombo<ProdutoEntity> mtoProduto;

	@AutoGenerated
	private TextField tfNcm;

	@AutoGenerated
	private TextField tfExTipi;

	@AutoGenerated
	private TextField tfCfop;

	@AutoGenerated
	private TextField tfUnidadeComercial;

	@AutoGenerated
	private TextField tfQuantidadeComercial;

	@AutoGenerated
	private TextField tfValorUnitarioComercial;

	@AutoGenerated
	private TextField tfValorBrutoProduto;

	@AutoGenerated
	private TextField tfGtinUnidadeTributavel;

	@AutoGenerated
	private TextField tfUnidadeTributavel;

	@AutoGenerated
	private TextField tfQuantidadeTributavel;

	@AutoGenerated
	private TextField tfValorUnitarioTributavel;

	@AutoGenerated
	private TextField tfValorFrete;

	@AutoGenerated
	private TextField tfValorSeguro;

	@AutoGenerated
	private TextField tfValorDesconto;

	@AutoGenerated
	private TextField tfValorOutrasDespesas;

	@AutoGenerated
	private TextField tfEntraTotal;

	@AutoGenerated
	private TextField tfValorSubtotal;

	@AutoGenerated
	private TextField tfValorTotal;

	@AutoGenerated
	private TextField tfNumeroPedidoCompra;

	@AutoGenerated
	private TextField tfItemPedidoCompra;

	@AutoGenerated
	private TextField tfInformacoesAdicionais;

	public Panel getPlNfeDetalheSubForm() {
		return plNfeDetalheSubForm;
	}

	public GridLayout getGlNfeDetalhe() {
		return glNfeDetalhe;
	}

	public TextField getTfNumeroItem() {
		return tfNumeroItem;
	}

	public void setTfNumeroItem(TextField tfNumeroItem) {
		this.tfNumeroItem = tfNumeroItem;
	}

	public TextField getTfCodigoProduto() {
		return tfCodigoProduto;
	}

	public void setTfCodigoProduto(TextField tfCodigoProduto) {
		this.tfCodigoProduto = tfCodigoProduto;
	}

	public TextField getTfGtin() {
		return tfGtin;
	}

	public void setTfGtin(TextField tfGtin) {
		this.tfGtin = tfGtin;
	}

	public ManyToOneCombo<ProdutoEntity> getMtoProduto() {
		return mtoProduto;
	}

	public void setMtoProduto(ManyToOneCombo<ProdutoEntity> mtoProduto) {
		this.mtoProduto = mtoProduto;
	}

	public TextField getTfNcm() {
		return tfNcm;
	}

	public void setTfNcm(TextField tfNcm) {
		this.tfNcm = tfNcm;
	}

	public TextField getTfExTipi() {
		return tfExTipi;
	}

	public void setTfExTipi(TextField tfExTipi) {
		this.tfExTipi = tfExTipi;
	}

	public TextField getTfCfop() {
		return tfCfop;
	}

	public void setTfCfop(TextField tfCfop) {
		this.tfCfop = tfCfop;
	}

	public TextField getTfUnidadeComercial() {
		return tfUnidadeComercial;
	}

	public void setTfUnidadeComercial(TextField tfUnidadeComercial) {
		this.tfUnidadeComercial = tfUnidadeComercial;
	}

	public TextField getTfQuantidadeComercial() {
		return tfQuantidadeComercial;
	}

	public void setTfQuantidadeComercial(TextField tfQuantidadeComercial) {
		this.tfQuantidadeComercial = tfQuantidadeComercial;
	}

	public TextField getTfValorUnitarioComercial() {
		return tfValorUnitarioComercial;
	}

	public void setTfValorUnitarioComercial(TextField tfValorUnitarioComercial) {
		this.tfValorUnitarioComercial = tfValorUnitarioComercial;
	}

	public TextField getTfValorBrutoProduto() {
		return tfValorBrutoProduto;
	}

	public void setTfValorBrutoProduto(TextField tfValorBrutoProduto) {
		this.tfValorBrutoProduto = tfValorBrutoProduto;
	}

	public TextField getTfGtinUnidadeTributavel() {
		return tfGtinUnidadeTributavel;
	}

	public void setTfGtinUnidadeTributavel(TextField tfGtinUnidadeTributavel) {
		this.tfGtinUnidadeTributavel = tfGtinUnidadeTributavel;
	}

	public TextField getTfUnidadeTributavel() {
		return tfUnidadeTributavel;
	}

	public void setTfUnidadeTributavel(TextField tfUnidadeTributavel) {
		this.tfUnidadeTributavel = tfUnidadeTributavel;
	}

	public TextField getTfQuantidadeTributavel() {
		return tfQuantidadeTributavel;
	}

	public void setTfQuantidadeTributavel(TextField tfQuantidadeTributavel) {
		this.tfQuantidadeTributavel = tfQuantidadeTributavel;
	}

	public TextField getTfValorUnitarioTributavel() {
		return tfValorUnitarioTributavel;
	}

	public void setTfValorUnitarioTributavel(TextField tfValorUnitarioTributavel) {
		this.tfValorUnitarioTributavel = tfValorUnitarioTributavel;
	}

	public TextField getTfValorFrete() {
		return tfValorFrete;
	}

	public void setTfValorFrete(TextField tfValorFrete) {
		this.tfValorFrete = tfValorFrete;
	}

	public TextField getTfValorSeguro() {
		return tfValorSeguro;
	}

	public void setTfValorSeguro(TextField tfValorSeguro) {
		this.tfValorSeguro = tfValorSeguro;
	}

	public TextField getTfValorDesconto() {
		return tfValorDesconto;
	}

	public void setTfValorDesconto(TextField tfValorDesconto) {
		this.tfValorDesconto = tfValorDesconto;
	}

	public TextField getTfValorOutrasDespesas() {
		return tfValorOutrasDespesas;
	}

	public void setTfValorOutrasDespesas(TextField tfValorOutrasDespesas) {
		this.tfValorOutrasDespesas = tfValorOutrasDespesas;
	}

	public TextField getTfEntraTotal() {
		return tfEntraTotal;
	}

	public void setTfEntraTotal(TextField tfEntraTotal) {
		this.tfEntraTotal = tfEntraTotal;
	}

	public TextField getTfValorSubtotal() {
		return tfValorSubtotal;
	}

	public void setTfValorSubtotal(TextField tfValorSubtotal) {
		this.tfValorSubtotal = tfValorSubtotal;
	}

	public TextField getTfValorTotal() {
		return tfValorTotal;
	}

	public void setTfValorTotal(TextField tfValorTotal) {
		this.tfValorTotal = tfValorTotal;
	}

	public TextField getTfNumeroPedidoCompra() {
		return tfNumeroPedidoCompra;
	}

	public void setTfNumeroPedidoCompra(TextField tfNumeroPedidoCompra) {
		this.tfNumeroPedidoCompra = tfNumeroPedidoCompra;
	}

	public TextField getTfItemPedidoCompra() {
		return tfItemPedidoCompra;
	}

	public void setTfItemPedidoCompra(TextField tfItemPedidoCompra) {
		this.tfItemPedidoCompra = tfItemPedidoCompra;
	}

	public TextField getTfInformacoesAdicionais() {
		return tfInformacoesAdicionais;
	}

	public void setTfInformacoesAdicionais(TextField tfInformacoesAdicionais) {
		this.tfInformacoesAdicionais = tfInformacoesAdicionais;
	}

	public XxxModalFormView(ProdutoServicoFormController controller) {
		super("NFE Detalhe");
		super.setModal(true);
		super.setWidth("70%");
		super.setHeight("70%");

		VerticalLayout layout = new VerticalLayout();
		layout.setSizeUndefined();
		layout.setSpacing(true);
		layout.setMargin(true);

		layout.addComponent(bvlNfeDetalhe());

		super.setContent(layout);
	}

	@AutoGenerated
	private VerticalLayout bvlNfeDetalhe() {
		// common part: create layout
		vlNfeDetalhe = new VerticalLayout();
		vlNfeDetalhe.setImmediate(false);
		vlNfeDetalhe.setWidth("100.0%");
		vlNfeDetalhe.setHeight("100.0%");
		// vlNfeDetalhe.setSizeFull();
		vlNfeDetalhe.setMargin(true);
		vlNfeDetalhe.setSpacing(true);

		// panel_3
		// pNfeDetalheSelecionado = bpNfeDetalheSelecionado();
		vlNfeDetalhe.addComponent(bplNfeDetalhe());

		return vlNfeDetalhe;
	}

	@AutoGenerated
	private Panel bplNfeDetalhe() {
		// common part: create layout
		pNfeDetalhe = new Panel();
		pNfeDetalhe.setImmediate(false);
		// pNfeDetalhe.setWidth("100.0%");
		// pNfeDetalhe.setHeight("100.0%");
		pNfeDetalhe.setSizeFull();

		pNfeDetalhe.setContent(bglNfeDetalhe());

		return pNfeDetalhe;
	}

	@AutoGenerated
	private GridLayout bglNfeDetalhe() {
		// common part: create layout
		glNfeDetalhe = new GridLayout();
		glNfeDetalhe.setImmediate(false);
		glNfeDetalhe.setSizeUndefined();
		glNfeDetalhe.setMargin(true);
		glNfeDetalhe.setSpacing(true);
		glNfeDetalhe.setRows(9);
		glNfeDetalhe.setColumns(4);
		glNfeDetalhe.setEnabled(false);

		// tfNumeroItem
		tfNumeroItem = new TextField("Número do item:");
		tfNumeroItem.setWidth("-1px");
		tfNumeroItem.setHeight("-1px");
		tfNumeroItem.setSizeFull();
		tfNumeroItem.setNullRepresentation("");
		tfNumeroItem.setImmediate(true);
		tfNumeroItem.setId("tfNumeroItem");
		tfNumeroItem.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateInteger(event)) {
						controller.nfeDetalheSetarValor(tfNumeroItem.getId(),
								event.getProperty().getValue());
					}
				}
			}
		});
		glNfeDetalhe.addComponent(tfNumeroItem, 0, 0);

		// tfGtin
		tfGtin = new TextField("GTIN:");
		tfGtin.setWidth("-1px");
		tfGtin.setHeight("-1px");
		tfGtin.setSizeFull();
		tfGtin.setNullRepresentation("");
		tfGtin.setImmediate(true);
		tfGtin.setId("tfGtin");
		tfGtin.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.nfeDetalheSetarValor(tfGtin.getId(), event
							.getProperty().getValue());
				}
			}
		});
		glNfeDetalhe.addComponent(tfGtin, 1, 0);

		// tfNcm
		tfNcm = new TextField("NCM:");
		tfNcm.setWidth("-1px");
		tfNcm.setHeight("-1px");
		tfNcm.setSizeFull();
		tfNcm.setNullRepresentation("");
		tfNcm.setImmediate(true);
		tfNcm.setId("tfNcm");
		tfNcm.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.nfeDetalheSetarValor(tfNcm.getId(), event
							.getProperty().getValue());
				}
			}
		});
		glNfeDetalhe.addComponent(tfNcm, 2, 0);

		// tfCodigoProduto
		tfCodigoProduto = new TextField("Código do produto:");
		tfCodigoProduto.setWidth("-1px");
		tfCodigoProduto.setHeight("-1px");
		tfCodigoProduto.setSizeFull();
		tfCodigoProduto.setNullRepresentation("");
		// tfCodigoProduto.setImmediate(true);
		tfCodigoProduto.setId("tfCodigoProduto");
		tfCodigoProduto.setEnabled(false);
		glNfeDetalhe.addComponent(tfCodigoProduto, 0, 1);

		// mtoProduto
		mtoProduto = new ManyToOneCombo<>();
		mtoProduto.setCaption("Produto:");
		mtoProduto.setWidth("175px");
		mtoProduto.setHeight("-1px");
		mtoProduto.setSizeFull();
		mtoProduto.setImmediate(true);
		mtoProduto.setId("mtoProduto");
		mtoProduto.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.nfeDetalheSetarValor(mtoProduto.getId(), event
							.getProperty().getValue());
				}
			}
		});
		glNfeDetalhe.addComponent(mtoProduto, 1, 1);

		// tfExTipi
		tfExTipi = new TextField("EX TIPI:");
		tfExTipi.setWidth("-1px");
		tfExTipi.setHeight("-1px");
		tfExTipi.setSizeFull();
		tfExTipi.setNullRepresentation("");
		tfExTipi.setImmediate(true);
		tfExTipi.setId("tfExTipi");
		tfExTipi.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateInteger(event)) {
						controller.nfeDetalheSetarValor(tfExTipi.getId(), event
								.getProperty().getValue());
					}
				}
			}
		});
		glNfeDetalhe.addComponent(tfExTipi, 2, 1);

		// tfCfop
		tfCfop = new TextField("CFOP:");
		tfCfop.setWidth("-1px");
		tfCfop.setHeight("-1px");
		tfCfop.setSizeFull();
		tfCfop.setNullRepresentation("");
		tfCfop.setImmediate(true);
		tfCfop.setId("tfCfop");
		tfCfop.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateInteger(event)) {
						controller.nfeDetalheSetarValor(tfCfop.getId(), event
								.getProperty().getValue());
					}
				}
			}
		});
		glNfeDetalhe.addComponent(tfCfop, 0, 3);

		// tfUnidadeComercial
		tfUnidadeComercial = new TextField("Unidade comercial:");
		tfUnidadeComercial.setWidth("-1px");
		tfUnidadeComercial.setHeight("-1px");
		tfUnidadeComercial.setSizeFull();
		tfUnidadeComercial.setNullRepresentation("");
		tfUnidadeComercial.setImmediate(true);
		tfUnidadeComercial.setId("tfUnidadeComercial");
		tfUnidadeComercial.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.nfeDetalheSetarValor(tfUnidadeComercial.getId(),
							event.getProperty().getValue());
				}
			}
		});
		glNfeDetalhe.addComponent(tfUnidadeComercial, 1, 3);

		// tfQuantidadeComercial
		tfQuantidadeComercial = new TextField("Quantidade comercial:");
		tfQuantidadeComercial.setWidth("-1px");
		tfQuantidadeComercial.setHeight("-1px");
		tfQuantidadeComercial.setSizeFull();
		tfQuantidadeComercial.setNullRepresentation("");
		tfQuantidadeComercial.setImmediate(true);
		tfQuantidadeComercial.setId("tfQuantidadeComercial");
		tfQuantidadeComercial.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateValue(event)) {
						controller.nfeDetalheSetarValor(tfQuantidadeComercial
								.getId(), event.getProperty().getValue());
					}
				}
			}
		});
		glNfeDetalhe.addComponent(tfQuantidadeComercial, 2, 3);

		// tfValorUnitarioComercial
		tfValorUnitarioComercial = new TextField("Valor unitário comercial:");
		tfValorUnitarioComercial.setWidth("-1px");
		tfValorUnitarioComercial.setHeight("-1px");
		tfValorUnitarioComercial.setSizeFull();
		tfValorUnitarioComercial.setNullRepresentation("");
		tfValorUnitarioComercial.setImmediate(true);
		tfValorUnitarioComercial.setId("tfValorUnitarioComercial");
		tfValorUnitarioComercial
				.addValueChangeListener(new ValueChangeListener() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						// TODO Auto-generated method stub
						if (ObjectValidator.validateEventValue(event)) {
							if (ObjectValidator.validateValue(event)) {
								controller.nfeDetalheSetarValor(
										tfValorUnitarioComercial.getId(), event
												.getProperty().getValue());
							}
						}
					}
				});
		glNfeDetalhe.addComponent(tfValorUnitarioComercial, 0, 4);

		// tfValorBrutoProduto
		tfValorBrutoProduto = new TextField("Valor bruto do produto:");
		tfValorBrutoProduto.setWidth("-1px");
		tfValorBrutoProduto.setHeight("-1px");
		tfValorBrutoProduto.setSizeFull();
		tfValorBrutoProduto.setNullRepresentation("");
		tfValorBrutoProduto.setImmediate(true);
		tfValorBrutoProduto.setId("tfValorBrutoProduto");
		tfValorBrutoProduto.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateValue(event)) {
						controller.nfeDetalheSetarValor(tfValorBrutoProduto
								.getId(), event.getProperty().getValue());
					}
				}
			}
		});
		glNfeDetalhe.addComponent(tfValorBrutoProduto, 1, 4);

		// tfGtinUnidadeTributavel
		tfGtinUnidadeTributavel = new TextField("GTIN unidade tributável:");
		tfGtinUnidadeTributavel.setWidth("-1px");
		tfGtinUnidadeTributavel.setHeight("-1px");
		tfGtinUnidadeTributavel.setSizeFull();
		tfGtinUnidadeTributavel.setNullRepresentation("");
		tfGtinUnidadeTributavel.setImmediate(true);
		tfGtinUnidadeTributavel.setId("tfGtinUnidadeTributavel");
		tfGtinUnidadeTributavel
				.addValueChangeListener(new ValueChangeListener() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						// TODO Auto-generated method stub
						if (ObjectValidator.validateEventValue(event)) {
							controller.nfeDetalheSetarValor(
									tfGtinUnidadeTributavel.getId(), event
											.getProperty().getValue());
						}
					}
				});
		glNfeDetalhe.addComponent(tfGtinUnidadeTributavel, 2, 4);

		// tfUnidadeTributavel
		tfUnidadeTributavel = new TextField("Unidade tributável:");
		tfUnidadeTributavel.setWidth("-1px");
		tfUnidadeTributavel.setHeight("-1px");
		tfUnidadeTributavel.setSizeFull();
		tfUnidadeTributavel.setNullRepresentation("");
		tfUnidadeTributavel.setImmediate(true);
		tfUnidadeTributavel.setId("tfUnidadeTributavel");
		tfUnidadeTributavel.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.nfeDetalheSetarValor(
							tfUnidadeTributavel.getId(), event.getProperty()
									.getValue());
				}
			}
		});
		glNfeDetalhe.addComponent(tfUnidadeTributavel, 3, 4);

		// tfQuantidadeTributavel
		tfQuantidadeTributavel = new TextField("Quantidade tributável:");
		tfQuantidadeTributavel.setWidth("-1px");
		tfQuantidadeTributavel.setHeight("-1px");
		tfQuantidadeTributavel.setSizeFull();
		tfQuantidadeTributavel.setNullRepresentation("");
		tfQuantidadeTributavel.setImmediate(true);
		tfQuantidadeTributavel.setId("tfQuantidadeTributavel");
		tfQuantidadeTributavel
				.addValueChangeListener(new ValueChangeListener() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						// TODO Auto-generated method stub
						if (ObjectValidator.validateEventValue(event)) {
							if (ObjectValidator.validateValue(event)) {
								controller.nfeDetalheSetarValor(
										tfQuantidadeTributavel.getId(), event
												.getProperty().getValue());
							}
						}
					}
				});
		glNfeDetalhe.addComponent(tfQuantidadeTributavel, 0, 5);

		// tfValorUnitarioTributavel
		tfValorUnitarioTributavel = new TextField("Valor unitário tributável:");
		tfValorUnitarioTributavel.setWidth("-1px");
		tfValorUnitarioTributavel.setHeight("-1px");
		tfValorUnitarioTributavel.setSizeFull();
		tfValorUnitarioTributavel.setNullRepresentation("");
		tfValorUnitarioTributavel.setImmediate(true);
		tfValorUnitarioTributavel.setId("tfValorUnitarioTributavel");
		tfValorUnitarioTributavel
				.addValueChangeListener(new ValueChangeListener() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						// TODO Auto-generated method stub
						if (ObjectValidator.validateEventValue(event)) {
							if (ObjectValidator.validateValue(event)) {
								controller.nfeDetalheSetarValor(
										tfValorUnitarioTributavel.getId(),
										event.getProperty().getValue());
							}
						}
					}
				});
		glNfeDetalhe.addComponent(tfValorUnitarioTributavel, 1, 5);

		// tfValorFrete
		tfValorFrete = new TextField("Valor do frete:");
		tfValorFrete.setWidth("-1px");
		tfValorFrete.setHeight("-1px");
		tfValorFrete.setSizeFull();
		tfValorFrete.setNullRepresentation("");
		tfValorFrete.setImmediate(true);
		tfValorFrete.setId("tfValorFrete");
		tfValorFrete.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateValue(event)) {
						controller.nfeDetalheSetarValor(tfValorFrete.getId(),
								event.getProperty().getValue());
					}
				}
			}
		});
		glNfeDetalhe.addComponent(tfValorFrete, 2, 5);

		// tfValorSeguro
		tfValorSeguro = new TextField("Valor do seguro:");
		tfValorSeguro.setWidth("-1px");
		tfValorSeguro.setHeight("-1px");
		tfValorSeguro.setSizeFull();
		tfValorSeguro.setNullRepresentation("");
		tfValorSeguro.setImmediate(true);
		tfValorSeguro.setId("tfValorSeguro");
		tfValorSeguro.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateValue(event)) {
						controller.nfeDetalheSetarValor(tfValorSeguro.getId(),
								event.getProperty().getValue());
					}
				}
			}
		});
		glNfeDetalhe.addComponent(tfValorSeguro, 0, 6);

		// tfValorDesconto
		tfValorDesconto = new TextField("Valor do desconto:");
		tfValorDesconto.setWidth("-1px");
		tfValorDesconto.setHeight("-1px");
		tfValorDesconto.setSizeFull();
		tfValorDesconto.setNullRepresentation("");
		tfValorDesconto.setImmediate(true);
		tfValorDesconto.setId("tfValorDesconto");
		tfValorDesconto.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateValue(event)) {
						controller.nfeDetalheSetarValor(
								tfValorDesconto.getId(), event.getProperty()
										.getValue());
					}
				}
			}
		});
		glNfeDetalhe.addComponent(tfValorDesconto, 1, 6);

		// tfValorOutrasDespesas
		tfValorOutrasDespesas = new TextField("Valor de outras despesas:");
		tfValorOutrasDespesas.setWidth("-1px");
		tfValorOutrasDespesas.setHeight("-1px");
		tfValorOutrasDespesas.setSizeFull();
		tfValorOutrasDespesas.setNullRepresentation("");
		tfValorOutrasDespesas.setImmediate(true);
		tfValorOutrasDespesas.setId("tfValorOutrasDespesas");
		tfValorOutrasDespesas.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateValue(event)) {
						controller.nfeDetalheSetarValor(tfValorOutrasDespesas
								.getId(), event.getProperty().getValue());
					}
				}
			}
		});
		glNfeDetalhe.addComponent(tfValorOutrasDespesas, 2, 6);

		// tfEntraTotal
		tfEntraTotal = new TextField("Entra total:");
		tfEntraTotal.setWidth("-1px");
		tfEntraTotal.setHeight("-1px");
		tfEntraTotal.setSizeFull();
		tfEntraTotal.setNullRepresentation("");
		tfEntraTotal.setImmediate(true);
		tfEntraTotal.setId("tfEntraTotal");
		tfEntraTotal.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.nfeDetalheSetarValor(tfEntraTotal.getId(), event
							.getProperty().getValue());
				}
			}
		});
		glNfeDetalhe.addComponent(tfEntraTotal, 0, 7);

		// tfValorSubtotal
		tfValorSubtotal = new TextField("Valor subtotal:");
		tfValorSubtotal.setWidth("-1px");
		tfValorSubtotal.setHeight("-1px");
		tfValorSubtotal.setSizeFull();
		tfValorSubtotal.setNullRepresentation("");
		tfValorSubtotal.setImmediate(true);
		tfValorSubtotal.setId("tfValorSubtotal");
		tfValorSubtotal.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateValue(event)) {
						controller.nfeDetalheSetarValor(
								tfValorSubtotal.getId(), event.getProperty()
										.getValue());
					}
				}
			}
		});
		glNfeDetalhe.addComponent(tfValorSubtotal, 1, 7);

		// tfValorTotal
		tfValorTotal = new TextField("Valor total:");
		tfValorTotal.setWidth("-1px");
		tfValorTotal.setHeight("-1px");
		tfValorTotal.setSizeFull();
		tfValorTotal.setNullRepresentation("");
		tfValorTotal.setImmediate(true);
		tfValorTotal.setId("tfValorTotal");
		tfValorTotal.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateValue(event)) {
						controller.nfeDetalheSetarValor(tfValorTotal.getId(),
								event.getProperty().getValue());
					}
				}
			}
		});
		glNfeDetalhe.addComponent(tfValorTotal, 2, 7);

		// tfNumeroPedidoCompra
		tfNumeroPedidoCompra = new TextField("Número do pedido de compra:");
		tfNumeroPedidoCompra.setWidth("-1px");
		tfNumeroPedidoCompra.setHeight("-1px");
		tfNumeroPedidoCompra.setSizeFull();
		tfNumeroPedidoCompra.setNullRepresentation("");
		tfNumeroPedidoCompra.setImmediate(true);
		tfNumeroPedidoCompra.setId("tfNumeroPedidoCompra");
		tfNumeroPedidoCompra.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.nfeDetalheSetarValor(tfNumeroPedidoCompra
							.getId(), event.getProperty().getValue());
				}
			}
		});
		glNfeDetalhe.addComponent(tfNumeroPedidoCompra, 0, 8);

		// tfItemPedidoCompra
		tfItemPedidoCompra = new TextField("Item do pedido de compra:");
		tfItemPedidoCompra.setWidth("-1px");
		tfItemPedidoCompra.setHeight("-1px");
		tfItemPedidoCompra.setSizeFull();
		tfItemPedidoCompra.setNullRepresentation("");
		tfItemPedidoCompra.setImmediate(true);
		tfItemPedidoCompra.setId("tfItemPedidoCompra");
		tfItemPedidoCompra.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateInteger(event)) {
						controller.nfeDetalheSetarValor(tfItemPedidoCompra
								.getId(), event.getProperty().getValue());
					}
				}
			}
		});
		glNfeDetalhe.addComponent(tfItemPedidoCompra, 1, 8);

		// tfInformacoesAdicionais
		tfInformacoesAdicionais = new TextField("Informações adicionais:");
		tfInformacoesAdicionais.setWidth("-1px");
		tfInformacoesAdicionais.setHeight("-1px");
		tfInformacoesAdicionais.setSizeFull();
		tfInformacoesAdicionais.setNullRepresentation("");
		tfInformacoesAdicionais.setImmediate(true);
		tfInformacoesAdicionais.setId("tfInformacoesAdicionais");
		tfInformacoesAdicionais
				.addValueChangeListener(new ValueChangeListener() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						// TODO Auto-generated method stub
						if (ObjectValidator.validateEventValue(event)) {
							controller.nfeDetalheSetarValor(
									tfInformacoesAdicionais.getId(), event
											.getProperty().getValue());
						}
					}
				});
		glNfeDetalhe.addComponent(tfInformacoesAdicionais, 2, 8);

		return glNfeDetalhe;
	}

}