package dc.visao.nfe;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.control.enums.CsosnEn;
import dc.control.enums.CstIcmsEn;
import dc.control.validator.ObjectValidator;
import dc.controller.nfe.ProdutoServicoFormController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

public class NfeDetalheImpostoIcmsFormView extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProdutoServicoFormController controller;

	/**
	 * 
	 */

	@AutoGenerated
	private String titulo = "ICMS";

	@AutoGenerated
	private VerticalLayout vlNdiIcms;

	@AutoGenerated
	private Panel plNdiIcms;

	@AutoGenerated
	private GridLayout glNdiIcms;

	@AutoGenerated
	private TextField tfOrigemMercadoriaIcms;

	@AutoGenerated
	private ComboBox cbCstIcms;

	@AutoGenerated
	private ComboBox cbCsosnIcms;

	@AutoGenerated
	private TextField tfModalidadeBcIcms;

	@AutoGenerated
	private TextField tfTaxaReducaoBcIcms;

	@AutoGenerated
	private TextField tfBaseCalculoBcIcms;

	@AutoGenerated
	private TextField tfAliquotaIcms;

	@AutoGenerated
	private TextField tfValorIcms;

	@AutoGenerated
	private TextField tfMotivoDesoneracaoIcms;

	@AutoGenerated
	private TextField tfModalidadeBcStIcms;

	@AutoGenerated
	private TextField tfPercentualMvaStIcms;

	@AutoGenerated
	private TextField tfTaxaReducaoBcStIcms;

	@AutoGenerated
	private TextField tfBaseCalculoStIcms;

	@AutoGenerated
	private TextField tfAliquotaStIcms;

	@AutoGenerated
	private TextField tfValorStIcms;

	@AutoGenerated
	private TextField tfBcStRetidoIcms;

	@AutoGenerated
	private TextField tfValorStRetidoIcms;

	@AutoGenerated
	private TextField tfBcStDestinoIcms;

	@AutoGenerated
	private TextField tfValorStDestinoIcms;

	@AutoGenerated
	private TextField tfAliquotaCreditoSnIcms;

	@AutoGenerated
	private TextField tfValorCreditoSnIcms;

	@AutoGenerated
	private TextField tfPercentualBcOperacaoPropriaIcms;

	@AutoGenerated
	private TextField tfUfStIcms;

	public String getTitulo() {
		return titulo;
	}

	public GridLayout getGlNdiIcms() {
		return glNdiIcms;
	}

	public TextField getTfOrigemMercadoriaIcms() {
		return tfOrigemMercadoriaIcms;
	}

	public void setTfOrigemMercadoriaIcms(TextField tfOrigemMercadoriaIcms) {
		this.tfOrigemMercadoriaIcms = tfOrigemMercadoriaIcms;
	}

	public ComboBox getCbCstIcms() {
		return cbCstIcms;
	}

	public void setCbCstIcms(ComboBox cbCstIcms) {
		this.cbCstIcms = cbCstIcms;
	}

	public ComboBox getCbCsosnIcms() {
		return cbCsosnIcms;
	}

	public void setCbCsosnIcms(ComboBox cbCsosnIcms) {
		this.cbCsosnIcms = cbCsosnIcms;
	}

	public TextField getTfModalidadeBcIcms() {
		return tfModalidadeBcIcms;
	}

	public void setTfModalidadeBcIcms(TextField tfModalidadeBcIcms) {
		this.tfModalidadeBcIcms = tfModalidadeBcIcms;
	}

	public TextField getTfTaxaReducaoBcIcms() {
		return tfTaxaReducaoBcIcms;
	}

	public void setTfTaxaReducaoBcIcms(TextField tfTaxaReducaoBcIcms) {
		this.tfTaxaReducaoBcIcms = tfTaxaReducaoBcIcms;
	}

	public TextField getTfBaseCalculoBcIcms() {
		return tfBaseCalculoBcIcms;
	}

	public void setTfBaseCalculoBcIcms(TextField tfBaseCalculoBcIcms) {
		this.tfBaseCalculoBcIcms = tfBaseCalculoBcIcms;
	}

	public TextField getTfAliquotaIcms() {
		return tfAliquotaIcms;
	}

	public void setTfAliquotaIcms(TextField tfAliquotaIcms) {
		this.tfAliquotaIcms = tfAliquotaIcms;
	}

	public TextField getTfValorIcms() {
		return tfValorIcms;
	}

	public void setTfValorIcms(TextField tfValorIcms) {
		this.tfValorIcms = tfValorIcms;
	}

	public TextField getTfMotivoDesoneracaoIcms() {
		return tfMotivoDesoneracaoIcms;
	}

	public void setTfMotivoDesoneracaoIcms(TextField tfMotivoDesoneracaoIcms) {
		this.tfMotivoDesoneracaoIcms = tfMotivoDesoneracaoIcms;
	}

	public TextField getTfModalidadeBcStIcms() {
		return tfModalidadeBcStIcms;
	}

	public void setTfModalidadeBcStIcms(TextField tfModalidadeBcStIcms) {
		this.tfModalidadeBcStIcms = tfModalidadeBcStIcms;
	}

	public TextField getTfPercentualMvaStIcms() {
		return tfPercentualMvaStIcms;
	}

	public void setTfPercentualMvaStIcms(TextField tfPercentualMvaStIcms) {
		this.tfPercentualMvaStIcms = tfPercentualMvaStIcms;
	}

	public TextField getTfTaxaReducaoBcStIcms() {
		return tfTaxaReducaoBcStIcms;
	}

	public void setTfTaxaReducaoBcStIcms(TextField tfTaxaReducaoBcStIcms) {
		this.tfTaxaReducaoBcStIcms = tfTaxaReducaoBcStIcms;
	}

	public TextField getTfBaseCalculoStIcms() {
		return tfBaseCalculoStIcms;
	}

	public void setTfBaseCalculoStIcms(TextField tfBaseCalculoStIcms) {
		this.tfBaseCalculoStIcms = tfBaseCalculoStIcms;
	}

	public TextField getTfAliquotaStIcms() {
		return tfAliquotaStIcms;
	}

	public void setTfAliquotaStIcms(TextField tfAliquotaStIcms) {
		this.tfAliquotaStIcms = tfAliquotaStIcms;
	}

	public TextField getTfValorStIcms() {
		return tfValorStIcms;
	}

	public void setTfValorStIcms(TextField tfValorStIcms) {
		this.tfValorStIcms = tfValorStIcms;
	}

	public TextField getTfBcStRetidoIcms() {
		return tfBcStRetidoIcms;
	}

	public void setTfBcStRetidoIcms(TextField tfBcStRetidoIcms) {
		this.tfBcStRetidoIcms = tfBcStRetidoIcms;
	}

	public TextField getTfValorStRetidoIcms() {
		return tfValorStRetidoIcms;
	}

	public void setTfValorStRetidoIcms(TextField tfValorStRetidoIcms) {
		this.tfValorStRetidoIcms = tfValorStRetidoIcms;
	}

	public TextField getTfBcStDestinoIcms() {
		return tfBcStDestinoIcms;
	}

	public void setTfBcStDestinoIcms(TextField tfBcStDestinoIcms) {
		this.tfBcStDestinoIcms = tfBcStDestinoIcms;
	}

	public TextField getTfValorStDestinoIcms() {
		return tfValorStDestinoIcms;
	}

	public void setTfValorStDestinoIcms(TextField tfValorStDestinoIcms) {
		this.tfValorStDestinoIcms = tfValorStDestinoIcms;
	}

	public TextField getTfAliquotaCreditoSnIcms() {
		return tfAliquotaCreditoSnIcms;
	}

	public void setTfAliquotaCreditoSnIcms(TextField tfAliquotaCreditoSnIcms) {
		this.tfAliquotaCreditoSnIcms = tfAliquotaCreditoSnIcms;
	}

	public TextField getTfValorCreditoSnIcms() {
		return tfValorCreditoSnIcms;
	}

	public void setTfValorCreditoSnIcms(TextField tfValorCreditoSnIcms) {
		this.tfValorCreditoSnIcms = tfValorCreditoSnIcms;
	}

	public TextField getTfPercentualBcOperacaoPropriaIcms() {
		return tfPercentualBcOperacaoPropriaIcms;
	}

	public void setTfPercentualBcOperacaoPropriaIcms(
			TextField tfPercentualBcOperacaoPropriaIcms) {
		this.tfPercentualBcOperacaoPropriaIcms = tfPercentualBcOperacaoPropriaIcms;
	}

	public TextField getTfUfStIcms() {
		return tfUfStIcms;
	}

	public void setTfUfStIcms(TextField tfUfStIcms) {
		this.tfUfStIcms = tfUfStIcms;
	}

	/**
	 * CONSTRUTOR
	 * 
	 * @param controller
	 */

	public NfeDetalheImpostoIcmsFormView(ProdutoServicoFormController controller) {
		this.controller = controller;
	}

	/**
	 * GET / SET
	 */

	@AutoGenerated
	public VerticalLayout bvlNdiIcms() {
		// common part: create layout
		vlNdiIcms = new VerticalLayout();
		vlNdiIcms.setImmediate(false);
		vlNdiIcms.setWidth("100.0%");
		vlNdiIcms.setHeight("100.0%");
		vlNdiIcms.setMargin(true);
		vlNdiIcms.setSpacing(true);

		// panel_2
		vlNdiIcms.addComponent(bplNdiIcms());

		return vlNdiIcms;
	}

	@AutoGenerated
	private Panel bplNdiIcms() {
		// common part: create layout
		plNdiIcms = new Panel();
		plNdiIcms.setImmediate(false);
		plNdiIcms.setSizeFull();

		plNdiIcms.setContent(bglNdiIcms());

		return plNdiIcms;
	}

	@AutoGenerated
	private GridLayout bglNdiIcms() {
		// common part: create layout
		glNdiIcms = new GridLayout();
		glNdiIcms.setImmediate(false);
		glNdiIcms.setSizeUndefined();
		glNdiIcms.setMargin(true);
		glNdiIcms.setSpacing(true);
		glNdiIcms.setRows(6);
		glNdiIcms.setColumns(4);
		glNdiIcms.setEnabled(false);

		// tfOrigemMercadoriaIcms
		tfOrigemMercadoriaIcms = new TextField("Origem da mercadoria:");
		tfOrigemMercadoriaIcms.setWidth("-1px");
		tfOrigemMercadoriaIcms.setHeight("-1px");
		tfOrigemMercadoriaIcms.setSizeFull();
		tfOrigemMercadoriaIcms.setNullRepresentation("");
		tfOrigemMercadoriaIcms.setImmediate(true);
		tfOrigemMercadoriaIcms.setId("tfOrigemMercadoriaIcms");
		tfOrigemMercadoriaIcms
				.addValueChangeListener(new ValueChangeListener() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						// TODO Auto-generated method stub
						if (ObjectValidator.validateEventValue(event)) {
							controller.ndiIcmsSetarValor(tfOrigemMercadoriaIcms
									.getId(), event.getProperty().getValue());
						}
					}
				});
		glNdiIcms.addComponent(tfOrigemMercadoriaIcms, 0, 0);

		// cbCstIcms
		cbCstIcms = new ComboBox("CST:");
		cbCstIcms.setWidth("-1px");
		cbCstIcms.setHeight("-1px");
		cbCstIcms.setSizeFull();
		cbCstIcms.setImmediate(true);
		cbCstIcms.setId("tfCstIcms");
		cbCstIcms.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.ndiIcmsSetarValor(cbCstIcms.getId(), event
							.getProperty().getValue());
				}
			}
		});
		glNdiIcms.addComponent(cbCstIcms, 1, 0);

		// cbCsosnIcms
		cbCsosnIcms = new ComboBox("CSOSN:");
		cbCsosnIcms.setWidth("-1px");
		cbCsosnIcms.setHeight("-1px");
		cbCsosnIcms.setSizeFull();
		cbCsosnIcms.setImmediate(true);
		cbCsosnIcms.setId("tfCsosnIcms");
		cbCsosnIcms.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.ndiIcmsSetarValor(cbCsosnIcms.getId(), event
							.getProperty().getValue());
				}
			}
		});
		glNdiIcms.addComponent(cbCsosnIcms, 2, 0);

		// tfModalidadeBcIcms
		tfModalidadeBcIcms = new TextField("Modalidade (BC):");
		tfModalidadeBcIcms.setWidth("-1px");
		tfModalidadeBcIcms.setHeight("-1px");
		tfModalidadeBcIcms.setSizeFull();
		tfModalidadeBcIcms.setNullRepresentation("");
		tfModalidadeBcIcms.setImmediate(true);
		tfModalidadeBcIcms.setId("tfModalidadeBcIcms");
		tfModalidadeBcIcms.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.ndiIcmsSetarValor(tfModalidadeBcIcms.getId(),
							event.getProperty().getValue());
				}
			}
		});
		glNdiIcms.addComponent(tfModalidadeBcIcms, 3, 0);

		// tfTaxaReducaoBcIcms
		tfTaxaReducaoBcIcms = new TextField("Taxa de redução (BC):");
		tfTaxaReducaoBcIcms.setWidth("-1px");
		tfTaxaReducaoBcIcms.setHeight("-1px");
		tfTaxaReducaoBcIcms.setSizeFull();
		tfTaxaReducaoBcIcms.setNullRepresentation("tfTaxaReducaoBcIcms");
		tfTaxaReducaoBcIcms.setImmediate(true);
		tfTaxaReducaoBcIcms.setId("tfTaxaReducaoBcIcms");
		tfTaxaReducaoBcIcms.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateValue(event)) {
						controller.ndiIcmsSetarValor(tfTaxaReducaoBcIcms
								.getId(), event.getProperty().getValue());
					}
				}
			}
		});
		glNdiIcms.addComponent(tfTaxaReducaoBcIcms, 0, 1);

		// tfBaseCalculoBcIcms
		tfBaseCalculoBcIcms = new TextField("Base de cálculo (BC):");
		tfBaseCalculoBcIcms.setWidth("-1px");
		tfBaseCalculoBcIcms.setHeight("-1px");
		tfBaseCalculoBcIcms.setSizeFull();
		tfBaseCalculoBcIcms.setNullRepresentation("");
		tfBaseCalculoBcIcms.setImmediate(true);
		tfBaseCalculoBcIcms.setId("tfBaseCalculoBcIcms");
		tfBaseCalculoBcIcms.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateValue(event)) {
						controller.ndiIcmsSetarValor(tfBaseCalculoBcIcms
								.getId(), event.getProperty().getValue());
					}
				}
			}
		});
		glNdiIcms.addComponent(tfBaseCalculoBcIcms, 1, 1);

		// tfAliquotaIcms
		tfAliquotaIcms = new TextField("Alíquota:");
		tfAliquotaIcms.setWidth("-1px");
		tfAliquotaIcms.setHeight("-1px");
		tfAliquotaIcms.setSizeFull();
		tfAliquotaIcms.setNullRepresentation("");
		tfAliquotaIcms.setImmediate(true);
		tfAliquotaIcms.setId("tfAliquotaIcms");
		tfAliquotaIcms.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateValue(event)) {
						controller.ndiIcmsSetarValor(tfAliquotaIcms.getId(),
								event.getProperty().getValue());
					}
				}
			}
		});
		glNdiIcms.addComponent(tfAliquotaIcms, 2, 1);

		// tfValorIcms
		tfValorIcms = new TextField("Valor:");
		tfValorIcms.setWidth("-1px");
		tfValorIcms.setHeight("-1px");
		tfValorIcms.setSizeFull();
		tfValorIcms.setNullRepresentation("");
		tfValorIcms.setImmediate(true);
		tfValorIcms.setId("tfValorIcms");
		tfValorIcms.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateValue(event)) {
						controller.ndiIcmsSetarValor(tfValorIcms.getId(), event
								.getProperty().getValue());
					}
				}
			}
		});
		glNdiIcms.addComponent(tfValorIcms, 3, 1);

		// tfMotivoDesoneracaoIcms
		tfMotivoDesoneracaoIcms = new TextField("Motivo desoneração:");
		tfMotivoDesoneracaoIcms.setWidth("-1px");
		tfMotivoDesoneracaoIcms.setHeight("-1px");
		tfMotivoDesoneracaoIcms.setSizeFull();
		tfMotivoDesoneracaoIcms.setNullRepresentation("");
		tfMotivoDesoneracaoIcms.setImmediate(true);
		tfMotivoDesoneracaoIcms.setId("tfMotivoDesoneracaoIcms");
		tfMotivoDesoneracaoIcms
				.addValueChangeListener(new ValueChangeListener() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						// TODO Auto-generated method stub
						if (ObjectValidator.validateEventValue(event)) {
							controller.ndiIcmsSetarValor(
									tfMotivoDesoneracaoIcms.getId(), event
											.getProperty().getValue());
						}
					}
				});
		glNdiIcms.addComponent(tfMotivoDesoneracaoIcms, 0, 2);

		// tfModalidadeBcStIcms
		tfModalidadeBcStIcms = new TextField("Motivo desoneração BC ST:");
		tfModalidadeBcStIcms.setWidth("-1px");
		tfModalidadeBcStIcms.setHeight("-1px");
		tfModalidadeBcStIcms.setSizeFull();
		tfModalidadeBcStIcms.setNullRepresentation("");
		tfModalidadeBcStIcms.setImmediate(true);
		tfModalidadeBcStIcms.setId("tfModalidadeBcStIcms");
		tfModalidadeBcStIcms.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.ndiIcmsSetarValor(tfModalidadeBcStIcms.getId(),
							event.getProperty().getValue());
				}
			}
		});
		glNdiIcms.addComponent(tfModalidadeBcStIcms, 1, 2);

		// tfPercentualMvaStIcms
		tfPercentualMvaStIcms = new TextField("Percentual MVA ST:");
		tfPercentualMvaStIcms.setWidth("-1px");
		tfPercentualMvaStIcms.setHeight("-1px");
		tfPercentualMvaStIcms.setSizeFull();
		tfPercentualMvaStIcms.setNullRepresentation("");
		tfPercentualMvaStIcms.setImmediate(true);
		tfPercentualMvaStIcms.setId("tfPercentualMvaStIcms");
		tfPercentualMvaStIcms.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateValue(event)) {
						controller.ndiIcmsSetarValor(tfPercentualMvaStIcms
								.getId(), event.getProperty().getValue());
					}
				}
			}
		});
		glNdiIcms.addComponent(tfPercentualMvaStIcms, 2, 2);

		// tfTaxaReducaoBcStIcms
		tfTaxaReducaoBcStIcms = new TextField("Taxa de redução BC ST:");
		tfTaxaReducaoBcStIcms.setWidth("-1px");
		tfTaxaReducaoBcStIcms.setHeight("-1px");
		tfTaxaReducaoBcStIcms.setSizeFull();
		tfTaxaReducaoBcStIcms.setNullRepresentation("");
		tfTaxaReducaoBcStIcms.setImmediate(true);
		tfTaxaReducaoBcStIcms.setId("tfTaxaReducaoBcStIcms");
		tfTaxaReducaoBcStIcms.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateValue(event)) {
						controller.ndiIcmsSetarValor(tfTaxaReducaoBcStIcms
								.getId(), event.getProperty().getValue());
					}
				}
			}
		});
		glNdiIcms.addComponent(tfTaxaReducaoBcStIcms, 3, 2);

		// tfBaseCalculoStIcms
		tfBaseCalculoStIcms = new TextField("Base de cálculo ST:");
		tfBaseCalculoStIcms.setWidth("-1px");
		tfBaseCalculoStIcms.setHeight("-1px");
		tfBaseCalculoStIcms.setSizeFull();
		tfBaseCalculoStIcms.setNullRepresentation("");
		tfBaseCalculoStIcms.setImmediate(true);
		tfBaseCalculoStIcms.setId("tfBaseCalculoStIcms");
		tfBaseCalculoStIcms.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateValue(event)) {
						controller.ndiIcmsSetarValor(tfBaseCalculoStIcms
								.getId(), event.getProperty().getValue());
					}
				}
			}
		});
		glNdiIcms.addComponent(tfBaseCalculoStIcms, 0, 3);

		// tfAliquotaStIcms
		tfAliquotaStIcms = new TextField("Alíquota ST:");
		tfAliquotaStIcms.setWidth("-1px");
		tfAliquotaStIcms.setHeight("-1px");
		tfAliquotaStIcms.setSizeFull();
		tfAliquotaStIcms.setNullRepresentation("");
		tfAliquotaStIcms.setImmediate(true);
		tfAliquotaStIcms.setId("tfAliquotaStIcms");
		tfAliquotaStIcms.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateValue(event)) {
						controller.ndiIcmsSetarValor(tfAliquotaStIcms.getId(),
								event.getProperty().getValue());
					}
				}
			}
		});
		glNdiIcms.addComponent(tfAliquotaStIcms, 1, 3);

		// tfValorStIcms
		tfValorStIcms = new TextField("Valor ST:");
		tfValorStIcms.setWidth("-1px");
		tfValorStIcms.setHeight("-1px");
		tfValorStIcms.setSizeFull();
		tfValorStIcms.setNullRepresentation("");
		tfValorStIcms.setImmediate(true);
		tfValorStIcms.setId("tfValorStIcms");
		tfValorStIcms.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateValue(event)) {
						controller.ndiIcmsSetarValor(tfValorStIcms.getId(),
								event.getProperty().getValue());
					}
				}
			}
		});
		glNdiIcms.addComponent(tfValorStIcms, 2, 3);

		// tfBcStRetidoIcms
		tfBcStRetidoIcms = new TextField("BC ST retido:");
		tfBcStRetidoIcms.setWidth("-1px");
		tfBcStRetidoIcms.setHeight("-1px");
		tfBcStRetidoIcms.setSizeFull();
		tfBcStRetidoIcms.setNullRepresentation("");
		tfBcStRetidoIcms.setImmediate(true);
		tfBcStRetidoIcms.setId("tfBcStRetidoIcms");
		tfBcStRetidoIcms.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateValue(event)) {
						controller.ndiIcmsSetarValor(tfBcStRetidoIcms.getId(),
								event.getProperty().getValue());
					}
				}
			}
		});
		glNdiIcms.addComponent(tfBcStRetidoIcms, 3, 3);

		// tfValorStRetidoIcms
		tfValorStRetidoIcms = new TextField("Valor ST retido:");
		tfValorStRetidoIcms.setWidth("-1px");
		tfValorStRetidoIcms.setHeight("-1px");
		tfValorStRetidoIcms.setSizeFull();
		tfValorStRetidoIcms.setNullRepresentation("");
		tfValorStRetidoIcms.setImmediate(true);
		tfValorStRetidoIcms.setId("tfValorStRetidoIcms");
		tfValorStRetidoIcms.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateValue(event)) {
						controller.ndiIcmsSetarValor(tfValorStRetidoIcms
								.getId(), event.getProperty().getValue());
					}
				}
			}
		});
		glNdiIcms.addComponent(tfValorStRetidoIcms, 0, 4);

		// tfBcStDestinoIcms
		tfBcStDestinoIcms = new TextField("BC ST destino:");
		tfBcStDestinoIcms.setWidth("-1px");
		tfBcStDestinoIcms.setHeight("-1px");
		tfBcStDestinoIcms.setSizeFull();
		tfBcStDestinoIcms.setNullRepresentation("");
		tfBcStDestinoIcms.setImmediate(true);
		tfBcStDestinoIcms.setId("tfBcStDestinoIcms");
		tfBcStDestinoIcms.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateValue(event)) {
						controller.ndiIcmsSetarValor(tfBcStDestinoIcms.getId(),
								event.getProperty().getValue());
					}
				}
			}
		});
		glNdiIcms.addComponent(tfBcStDestinoIcms, 1, 4);

		// tfValorStDestinoIcms
		tfValorStDestinoIcms = new TextField("Valor ST destino:");
		tfValorStDestinoIcms.setWidth("-1px");
		tfValorStDestinoIcms.setHeight("-1px");
		tfValorStDestinoIcms.setSizeFull();
		tfValorStDestinoIcms.setNullRepresentation("");
		tfValorStDestinoIcms.setImmediate(true);
		tfValorStDestinoIcms.setId("tfValorStDestinoIcms");
		tfValorStDestinoIcms.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateValue(event)) {
						controller.ndiIcmsSetarValor(tfValorStDestinoIcms
								.getId(), event.getProperty().getValue());
					}
				}
			}
		});
		glNdiIcms.addComponent(tfValorStDestinoIcms, 2, 4);

		// tfAliquotaCreditoSnIcms
		tfAliquotaCreditoSnIcms = new TextField("Alíquota de crédito SN:");
		tfAliquotaCreditoSnIcms.setWidth("-1px");
		tfAliquotaCreditoSnIcms.setHeight("-1px");
		tfAliquotaCreditoSnIcms.setSizeFull();
		tfAliquotaCreditoSnIcms.setNullRepresentation("");
		tfAliquotaCreditoSnIcms.setImmediate(true);
		tfAliquotaCreditoSnIcms.setId("tfAliquotaCreditoSnIcms");
		tfAliquotaCreditoSnIcms
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
								controller.ndiIcmsSetarValor(
										tfAliquotaCreditoSnIcms.getId(), event
												.getProperty().getValue());
							}
						}
					}
				});
		glNdiIcms.addComponent(tfAliquotaCreditoSnIcms, 3, 4);

		// tfValorCreditoSnIcms
		tfValorCreditoSnIcms = new TextField("Valor de crédito SN:");
		tfValorCreditoSnIcms.setWidth("-1px");
		tfValorCreditoSnIcms.setHeight("-1px");
		tfValorCreditoSnIcms.setSizeFull();
		tfValorCreditoSnIcms.setNullRepresentation("");
		tfValorCreditoSnIcms.setImmediate(true);
		tfValorCreditoSnIcms.setId("tfValorCreditoSnIcms");
		tfValorCreditoSnIcms.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateValue(event)) {
						controller.ndiIcmsSetarValor(tfValorCreditoSnIcms
								.getId(), event.getProperty().getValue());
					}
				}
			}
		});
		glNdiIcms.addComponent(tfValorCreditoSnIcms, 0, 5);

		// tfPercentualBcOperacaoPropriaIcms
		tfPercentualBcOperacaoPropriaIcms = new TextField(
				"Percentual BC operação própria:");
		tfPercentualBcOperacaoPropriaIcms.setWidth("-1px");
		tfPercentualBcOperacaoPropriaIcms.setHeight("-1px");
		tfPercentualBcOperacaoPropriaIcms.setSizeFull();
		tfPercentualBcOperacaoPropriaIcms.setNullRepresentation("");
		tfPercentualBcOperacaoPropriaIcms.setImmediate(true);
		tfPercentualBcOperacaoPropriaIcms
				.setId("tfPercentualBcOperacaoPropriaIcms");
		tfPercentualBcOperacaoPropriaIcms
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
								controller.ndiIcmsSetarValor(
										tfPercentualBcOperacaoPropriaIcms
												.getId(), event.getProperty()
												.getValue());
							}
						}
					}
				});
		glNdiIcms.addComponent(tfPercentualBcOperacaoPropriaIcms, 1, 5);

		// tfUfStIcms
		tfUfStIcms = new TextField("UF ST:");
		tfUfStIcms.setWidth("-1px");
		tfUfStIcms.setHeight("-1px");
		tfUfStIcms.setSizeFull();
		tfUfStIcms.setNullRepresentation("");
		tfUfStIcms.setImmediate(true);
		tfUfStIcms.setId("tfUfStIcms");
		tfUfStIcms.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.ndiIcmsSetarValor(tfUfStIcms.getId(), event
							.getProperty().getValue());
				}
			}
		});
		glNdiIcms.addComponent(tfUfStIcms, 2, 5);

		return glNdiIcms;
	}

	/**
	 * COMBOBOX
	 */

	public void carregarComboBox() {
		for (CsosnEn en : CsosnEn.values()) {
			this.cbCsosnIcms.addItem(en);
		}

		for (CstIcmsEn en : CstIcmsEn.values()) {
			this.cbCstIcms.addItem(en);
		}
	}

}