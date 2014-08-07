package dc.visao.nfe;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.control.validator.ObjectValidator;
import dc.controller.nfe.ProdutoServicoFormController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

public class NfeDetEspecificoVeiculoFormView extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProdutoServicoFormController controller;

	/**
	 * 
	 */

	@AutoGenerated
	private String titulo = "Veículo";

	/**
	 * VEÍCULO
	 */

	@AutoGenerated
	private VerticalLayout vlNdeVeiculo;

	@AutoGenerated
	private Panel plNdeVeiculo;

	@AutoGenerated
	private GridLayout glNdeVeiculo;

	@AutoGenerated
	private TextField tfTipoOperacaoVeiculo;

	@AutoGenerated
	private TextField tfChassiVeiculo;

	@AutoGenerated
	private TextField tfCodigoCorVeiculo;

	@AutoGenerated
	private TextField tfDescricaoCorVeiculo;

	@AutoGenerated
	private TextField tfPotenciaMotorVeiculo;

	@AutoGenerated
	private TextField tfCilindradasVeiculo;

	@AutoGenerated
	private TextField tfPesoLiquidoVeiculo;

	@AutoGenerated
	private TextField tfPesoBrutoVeiculo;

	@AutoGenerated
	private TextField tfNumeroSerieVeiculo;

	@AutoGenerated
	private TextField tfCombustivelVeiculo;

	@AutoGenerated
	private TextField tfNumeroMotorVeiculo;

	@AutoGenerated
	private TextField tfCapacidadeTracaoVeiculo;

	@AutoGenerated
	private TextField tfDistanciaEixosVeiculo;

	@AutoGenerated
	private TextField tfAnoModeloVeiculo;

	@AutoGenerated
	private TextField tfAnoFabricacaoVeiculo;

	@AutoGenerated
	private TextField tfTipoPinturaVeiculo;

	@AutoGenerated
	private TextField tfTipoVeiculo;

	@AutoGenerated
	private TextField tfEspecieVeiculo;

	@AutoGenerated
	private TextField tfCondicaoVinVeiculo;

	@AutoGenerated
	private TextField tfCondicaoVeiculo;

	@AutoGenerated
	private TextField tfCodigoMarcaModeloVeiculo;

	@AutoGenerated
	private TextField tfCodigoCorDenatranVeiculo;

	@AutoGenerated
	private TextField tfLotacaoVeiculo;

	@AutoGenerated
	private TextField tfRestricaoVeiculo;

	public String getTitulo() {
		return titulo;
	}

	public TextField getTfTipoOperacaoVeiculo() {
		return tfTipoOperacaoVeiculo;
	}

	public void setTfTipoOperacaoVeiculo(TextField tfTipoOperacaoVeiculo) {
		this.tfTipoOperacaoVeiculo = tfTipoOperacaoVeiculo;
	}

	public TextField getTfChassiVeiculo() {
		return tfChassiVeiculo;
	}

	public void setTfChassiVeiculo(TextField tfChassiVeiculo) {
		this.tfChassiVeiculo = tfChassiVeiculo;
	}

	public TextField getTfCodigoCorVeiculo() {
		return tfCodigoCorVeiculo;
	}

	public void setTfCodigoCorVeiculo(TextField tfCodigoCorVeiculo) {
		this.tfCodigoCorVeiculo = tfCodigoCorVeiculo;
	}

	public TextField getTfDescricaoCorVeiculo() {
		return tfDescricaoCorVeiculo;
	}

	public void setTfDescricaoCorVeiculo(TextField tfDescricaoCorVeiculo) {
		this.tfDescricaoCorVeiculo = tfDescricaoCorVeiculo;
	}

	public TextField getTfPotenciaMotorVeiculo() {
		return tfPotenciaMotorVeiculo;
	}

	public void setTfPotenciaMotorVeiculo(TextField tfPotenciaMotorVeiculo) {
		this.tfPotenciaMotorVeiculo = tfPotenciaMotorVeiculo;
	}

	public TextField getTfCilindradasVeiculo() {
		return tfCilindradasVeiculo;
	}

	public void setTfCilindradasVeiculo(TextField tfCilindradasVeiculo) {
		this.tfCilindradasVeiculo = tfCilindradasVeiculo;
	}

	public TextField getTfPesoLiquidoVeiculo() {
		return tfPesoLiquidoVeiculo;
	}

	public void setTfPesoLiquidoVeiculo(TextField tfPesoLiquidoVeiculo) {
		this.tfPesoLiquidoVeiculo = tfPesoLiquidoVeiculo;
	}

	public TextField getTfPesoBrutoVeiculo() {
		return tfPesoBrutoVeiculo;
	}

	public void setTfPesoBrutoVeiculo(TextField tfPesoBrutoVeiculo) {
		this.tfPesoBrutoVeiculo = tfPesoBrutoVeiculo;
	}

	public TextField getTfNumeroSerieVeiculo() {
		return tfNumeroSerieVeiculo;
	}

	public void setTfNumeroSerieVeiculo(TextField tfNumeroSerieVeiculo) {
		this.tfNumeroSerieVeiculo = tfNumeroSerieVeiculo;
	}

	public TextField getTfCombustivelVeiculo() {
		return tfCombustivelVeiculo;
	}

	public void setTfCombustivelVeiculo(TextField tfCombustivelVeiculo) {
		this.tfCombustivelVeiculo = tfCombustivelVeiculo;
	}

	public TextField getTfNumeroMotorVeiculo() {
		return tfNumeroMotorVeiculo;
	}

	public void setTfNumeroMotorVeiculo(TextField tfNumeroMotorVeiculo) {
		this.tfNumeroMotorVeiculo = tfNumeroMotorVeiculo;
	}

	public TextField getTfCapacidadeTracaoVeiculo() {
		return tfCapacidadeTracaoVeiculo;
	}

	public void setTfCapacidadeTracaoVeiculo(TextField tfCapacidadeTracaoVeiculo) {
		this.tfCapacidadeTracaoVeiculo = tfCapacidadeTracaoVeiculo;
	}

	public TextField getTfDistanciaEixosVeiculo() {
		return tfDistanciaEixosVeiculo;
	}

	public void setTfDistanciaEixosVeiculo(TextField tfDistanciaEixosVeiculo) {
		this.tfDistanciaEixosVeiculo = tfDistanciaEixosVeiculo;
	}

	public TextField getTfAnoModeloVeiculo() {
		return tfAnoModeloVeiculo;
	}

	public void setTfAnoModeloVeiculo(TextField tfAnoModeloVeiculo) {
		this.tfAnoModeloVeiculo = tfAnoModeloVeiculo;
	}

	public TextField getTfAnoFabricacaoVeiculo() {
		return tfAnoFabricacaoVeiculo;
	}

	public void setTfAnoFabricacaoVeiculo(TextField tfAnoFabricacaoVeiculo) {
		this.tfAnoFabricacaoVeiculo = tfAnoFabricacaoVeiculo;
	}

	public TextField getTfTipoPinturaVeiculo() {
		return tfTipoPinturaVeiculo;
	}

	public void setTfTipoPinturaVeiculo(TextField tfTipoPinturaVeiculo) {
		this.tfTipoPinturaVeiculo = tfTipoPinturaVeiculo;
	}

	public TextField getTfTipoVeiculo() {
		return tfTipoVeiculo;
	}

	public void setTfTipoVeiculo(TextField tfTipoVeiculo) {
		this.tfTipoVeiculo = tfTipoVeiculo;
	}

	public TextField getTfEspecieVeiculo() {
		return tfEspecieVeiculo;
	}

	public void setTfEspecieVeiculo(TextField tfEspecieVeiculo) {
		this.tfEspecieVeiculo = tfEspecieVeiculo;
	}

	public TextField getTfCondicaoVinVeiculo() {
		return tfCondicaoVinVeiculo;
	}

	public void setTfCondicaoVinVeiculo(TextField tfCondicaoVinVeiculo) {
		this.tfCondicaoVinVeiculo = tfCondicaoVinVeiculo;
	}

	public TextField getTfCondicaoVeiculo() {
		return tfCondicaoVeiculo;
	}

	public void setTfCondicaoVeiculo(TextField tfCondicaoVeiculo) {
		this.tfCondicaoVeiculo = tfCondicaoVeiculo;
	}

	public TextField getTfCodigoMarcaModeloVeiculo() {
		return tfCodigoMarcaModeloVeiculo;
	}

	public void setTfCodigoMarcaModeloVeiculo(
			TextField tfCodigoMarcaModeloVeiculo) {
		this.tfCodigoMarcaModeloVeiculo = tfCodigoMarcaModeloVeiculo;
	}

	public TextField getTfCodigoCorDenatranVeiculo() {
		return tfCodigoCorDenatranVeiculo;
	}

	public void setTfCodigoCorDenatranVeiculo(
			TextField tfCodigoCorDenatranVeiculo) {
		this.tfCodigoCorDenatranVeiculo = tfCodigoCorDenatranVeiculo;
	}

	public TextField getTfLotacaoVeiculo() {
		return tfLotacaoVeiculo;
	}

	public void setTfLotacaoVeiculo(TextField tfLotacaoVeiculo) {
		this.tfLotacaoVeiculo = tfLotacaoVeiculo;
	}

	public TextField getTfRestricaoVeiculo() {
		return tfRestricaoVeiculo;
	}

	public void setTfRestricaoVeiculo(TextField tfRestricaoVeiculo) {
		this.tfRestricaoVeiculo = tfRestricaoVeiculo;
	}

	/**
	 * CONSTRUTOR
	 * 
	 * @param controller
	 */

	public NfeDetEspecificoVeiculoFormView(
			ProdutoServicoFormController controller) {
		this.controller = controller;
	}

	/**
	 * GET / SET
	 */

	@AutoGenerated
	public VerticalLayout bvlNdeVeiculo() {
		// common part: create layout
		vlNdeVeiculo = new VerticalLayout();
		vlNdeVeiculo.setImmediate(false);
		vlNdeVeiculo.setWidth("100.0%");
		vlNdeVeiculo.setHeight("100.0%");
		vlNdeVeiculo.setMargin(true);
		vlNdeVeiculo.setSpacing(true);

		// panel_2
		vlNdeVeiculo.addComponent(bplNdeVeiculo());

		return vlNdeVeiculo;
	}

	@AutoGenerated
	private Panel bplNdeVeiculo() {
		// common part: create layout
		plNdeVeiculo = new Panel();
		plNdeVeiculo.setImmediate(false);
		plNdeVeiculo.setSizeFull();

		plNdeVeiculo.setContent(bglNdeVeiculo());

		return plNdeVeiculo;
	}

	@AutoGenerated
	private GridLayout bglNdeVeiculo() {
		// common part: create layout
		glNdeVeiculo = new GridLayout();
		glNdeVeiculo.setImmediate(false);
		glNdeVeiculo.setSizeUndefined();
		glNdeVeiculo.setMargin(true);
		glNdeVeiculo.setSpacing(true);
		glNdeVeiculo.setRows(6);
		glNdeVeiculo.setColumns(4);
		glNdeVeiculo.setEnabled(false);

		// tfTipoOperacaoVeiculo
		tfTipoOperacaoVeiculo = new TextField("Tipo de operação:");
		tfTipoOperacaoVeiculo.setWidth("-1px");
		tfTipoOperacaoVeiculo.setHeight("-1px");
		tfTipoOperacaoVeiculo.setSizeFull();
		tfTipoOperacaoVeiculo.setNullRepresentation("");
		tfTipoOperacaoVeiculo.setImmediate(true);
		tfTipoOperacaoVeiculo.setId("tfTipoOperacaoVeiculo");
		tfTipoOperacaoVeiculo.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.ndeVeiculoSetarValor(tfTipoOperacaoVeiculo
							.getId(), event.getProperty().getValue());
				}
			}
		});
		glNdeVeiculo.addComponent(tfTipoOperacaoVeiculo, 0, 0);

		// tfChassiVeiculo
		tfChassiVeiculo = new TextField("Chassi:");
		tfChassiVeiculo.setWidth("-1px");
		tfChassiVeiculo.setHeight("-1px");
		tfChassiVeiculo.setSizeFull();
		tfChassiVeiculo.setNullRepresentation("");
		tfChassiVeiculo.setImmediate(true);
		tfChassiVeiculo.setId("tfChassiVeiculo");
		tfChassiVeiculo.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.ndeVeiculoSetarValor(tfChassiVeiculo.getId(),
							event.getProperty().getValue());
				}
			}
		});
		glNdeVeiculo.addComponent(tfChassiVeiculo, 1, 0);

		// tfCodigoCorVeiculo
		tfCodigoCorVeiculo = new TextField("Código da cor:");
		tfCodigoCorVeiculo.setWidth("-1px");
		tfCodigoCorVeiculo.setHeight("-1px");
		tfCodigoCorVeiculo.setSizeFull();
		tfCodigoCorVeiculo.setNullRepresentation("");
		tfCodigoCorVeiculo.setImmediate(true);
		tfCodigoCorVeiculo.setId("tfCodigoCorVeiculo");
		tfCodigoCorVeiculo.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.ndeVeiculoSetarValor(tfCodigoCorVeiculo.getId(),
							event.getProperty().getValue());
				}
			}
		});
		glNdeVeiculo.addComponent(tfCodigoCorVeiculo, 2, 0);

		// tfDescricaoCorVeiculo
		tfDescricaoCorVeiculo = new TextField("Descrição da cor:");
		tfDescricaoCorVeiculo.setWidth("-1px");
		tfDescricaoCorVeiculo.setHeight("-1px");
		tfDescricaoCorVeiculo.setSizeFull();
		tfDescricaoCorVeiculo.setNullRepresentation("");
		tfDescricaoCorVeiculo.setImmediate(true);
		tfDescricaoCorVeiculo.setId("tfDescricaoCorVeiculo");
		tfDescricaoCorVeiculo.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.ndeVeiculoSetarValor(tfDescricaoCorVeiculo
							.getId(), event.getProperty().getValue());
				}
			}
		});
		glNdeVeiculo.addComponent(tfDescricaoCorVeiculo, 3, 0);

		// tfPotenciaMotorVeiculo
		tfPotenciaMotorVeiculo = new TextField("Potência (Motor):");
		tfPotenciaMotorVeiculo.setWidth("-1px");
		tfPotenciaMotorVeiculo.setHeight("-1px");
		tfPotenciaMotorVeiculo.setSizeFull();
		tfPotenciaMotorVeiculo.setNullRepresentation("");
		tfPotenciaMotorVeiculo.setImmediate(true);
		tfPotenciaMotorVeiculo.setId("tfPotenciaMotorVeiculo");
		tfPotenciaMotorVeiculo
				.addValueChangeListener(new ValueChangeListener() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						// TODO Auto-generated method stub
						if (ObjectValidator.validateEventValue(event)) {
							controller.ndeVeiculoSetarValor(
									tfPotenciaMotorVeiculo.getId(), event
											.getProperty().getValue());
						}
					}
				});
		glNdeVeiculo.addComponent(tfPotenciaMotorVeiculo, 0, 1);

		// tfCilindradasVeiculo
		tfCilindradasVeiculo = new TextField("Cilindradas:");
		tfCilindradasVeiculo.setWidth("-1px");
		tfCilindradasVeiculo.setHeight("-1px");
		tfCilindradasVeiculo.setSizeFull();
		tfCilindradasVeiculo.setNullRepresentation("");
		tfCilindradasVeiculo.setImmediate(true);
		tfCilindradasVeiculo.setId("tfCilindradasVeiculo");
		tfCilindradasVeiculo.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.ndeVeiculoSetarValor(tfCilindradasVeiculo
							.getId(), event.getProperty().getValue());
				}
			}
		});
		glNdeVeiculo.addComponent(tfCilindradasVeiculo, 1, 1);

		// tfPesoLiquidoVeiculo
		tfPesoLiquidoVeiculo = new TextField("Peso (Líquido):");
		tfPesoLiquidoVeiculo.setWidth("-1px");
		tfPesoLiquidoVeiculo.setHeight("-1px");
		tfPesoLiquidoVeiculo.setSizeFull();
		tfPesoLiquidoVeiculo.setNullRepresentation("");
		tfPesoLiquidoVeiculo.setImmediate(true);
		tfPesoLiquidoVeiculo.setId("tfPesoLiquidoVeiculo");
		tfPesoLiquidoVeiculo.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.ndeVeiculoSetarValor(tfPesoLiquidoVeiculo
							.getId(), event.getProperty().getValue());
				}
			}
		});
		glNdeVeiculo.addComponent(tfPesoLiquidoVeiculo, 2, 1);

		// tfPesoBrutoVeiculo
		tfPesoBrutoVeiculo = new TextField("Peso (Bruto):");
		tfPesoBrutoVeiculo.setWidth("-1px");
		tfPesoBrutoVeiculo.setHeight("-1px");
		tfPesoBrutoVeiculo.setSizeFull();
		tfPesoBrutoVeiculo.setNullRepresentation("");
		tfPesoBrutoVeiculo.setImmediate(true);
		tfPesoBrutoVeiculo.setId("tfPesoBrutoVeiculo");
		tfPesoBrutoVeiculo.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.ndeVeiculoSetarValor(tfPesoBrutoVeiculo.getId(),
							event.getProperty().getValue());
				}
			}
		});
		glNdeVeiculo.addComponent(tfPesoBrutoVeiculo, 3, 1);

		// tfNumeroSerieVeiculo
		tfNumeroSerieVeiculo = new TextField("Número de série:");
		tfNumeroSerieVeiculo.setWidth("-1px");
		tfNumeroSerieVeiculo.setHeight("-1px");
		tfNumeroSerieVeiculo.setSizeFull();
		tfNumeroSerieVeiculo.setNullRepresentation("");
		tfNumeroSerieVeiculo.setImmediate(true);
		tfNumeroSerieVeiculo.setId("tfNumeroSerieVeiculo");
		tfNumeroSerieVeiculo.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.ndeVeiculoSetarValor(tfNumeroSerieVeiculo
							.getId(), event.getProperty().getValue());
				}
			}
		});
		glNdeVeiculo.addComponent(tfNumeroSerieVeiculo, 0, 2);

		// tfCombustivelVeiculo
		tfCombustivelVeiculo = new TextField("Combustível:");
		tfCombustivelVeiculo.setWidth("-1px");
		tfCombustivelVeiculo.setHeight("-1px");
		tfCombustivelVeiculo.setSizeFull();
		tfCombustivelVeiculo.setNullRepresentation("");
		tfCombustivelVeiculo.setImmediate(true);
		tfCombustivelVeiculo.setId("tfCombustivelVeiculo");
		tfCombustivelVeiculo.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.ndeVeiculoSetarValor(tfCombustivelVeiculo
							.getId(), event.getProperty().getValue());
				}
			}
		});
		glNdeVeiculo.addComponent(tfCombustivelVeiculo, 1, 2);

		// tfNumeroMotorVeiculo
		tfNumeroMotorVeiculo = new TextField("Número (Motor):");
		tfNumeroMotorVeiculo.setWidth("-1px");
		tfNumeroMotorVeiculo.setHeight("-1px");
		tfNumeroMotorVeiculo.setSizeFull();
		tfNumeroMotorVeiculo.setNullRepresentation("");
		tfNumeroMotorVeiculo.setImmediate(true);
		tfNumeroMotorVeiculo.setId("tfNumeroMotorVeiculo");
		tfNumeroMotorVeiculo.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.ndeVeiculoSetarValor(tfNumeroMotorVeiculo
							.getId(), event.getProperty().getValue());
				}
			}
		});
		glNdeVeiculo.addComponent(tfNumeroMotorVeiculo, 2, 2);

		// tfCapacidadeTracaoVeiculo
		tfCapacidadeTracaoVeiculo = new TextField("Capacidade de tração:");
		tfCapacidadeTracaoVeiculo.setWidth("-1px");
		tfCapacidadeTracaoVeiculo.setHeight("-1px");
		tfCapacidadeTracaoVeiculo.setSizeFull();
		tfCapacidadeTracaoVeiculo.setNullRepresentation("");
		tfCapacidadeTracaoVeiculo.setImmediate(true);
		tfCapacidadeTracaoVeiculo.setId("tfCapacidadeTracaoVeiculo");
		tfCapacidadeTracaoVeiculo
				.addValueChangeListener(new ValueChangeListener() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						// TODO Auto-generated method stub
						if (ObjectValidator.validateEventValue(event)) {
							controller.ndeVeiculoSetarValor(
									tfCapacidadeTracaoVeiculo.getId(), event
											.getProperty().getValue());
						}
					}
				});
		glNdeVeiculo.addComponent(tfCapacidadeTracaoVeiculo, 3, 2);

		// tfDistanciaEixosVeiculo
		tfDistanciaEixosVeiculo = new TextField("Distância dos eixos:");
		tfDistanciaEixosVeiculo.setWidth("-1px");
		tfDistanciaEixosVeiculo.setHeight("-1px");
		tfDistanciaEixosVeiculo.setSizeFull();
		tfDistanciaEixosVeiculo.setNullRepresentation("");
		tfDistanciaEixosVeiculo.setImmediate(true);
		tfDistanciaEixosVeiculo.setId("tfDistanciaEixosVeiculo");
		tfDistanciaEixosVeiculo
				.addValueChangeListener(new ValueChangeListener() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						// TODO Auto-generated method stub
						if (ObjectValidator.validateEventValue(event)) {
							controller.ndeVeiculoSetarValor(
									tfDistanciaEixosVeiculo.getId(), event
											.getProperty().getValue());
						}
					}
				});
		glNdeVeiculo.addComponent(tfDistanciaEixosVeiculo, 0, 3);

		// tfAnoModeloVeiculo
		tfAnoModeloVeiculo = new TextField("Ano (Modelo):");
		tfAnoModeloVeiculo.setWidth("-1px");
		tfAnoModeloVeiculo.setHeight("-1px");
		tfAnoModeloVeiculo.setSizeFull();
		tfAnoModeloVeiculo.setNullRepresentation("");
		tfAnoModeloVeiculo.setImmediate(true);
		tfAnoModeloVeiculo.setId("tfAnoModeloVeiculo");
		tfAnoModeloVeiculo.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.ndeVeiculoSetarValor(tfAnoModeloVeiculo.getId(),
							event.getProperty().getValue());
				}
			}
		});
		glNdeVeiculo.addComponent(tfAnoModeloVeiculo, 1, 3);

		// tfAnoFabricacaoVeiculo
		tfAnoFabricacaoVeiculo = new TextField("Ano (Fabricação):");
		tfAnoFabricacaoVeiculo.setWidth("-1px");
		tfAnoFabricacaoVeiculo.setHeight("-1px");
		tfAnoFabricacaoVeiculo.setSizeFull();
		tfAnoFabricacaoVeiculo.setNullRepresentation("");
		tfAnoFabricacaoVeiculo.setImmediate(true);
		tfAnoFabricacaoVeiculo.setId("tfAnoFabricacaoVeiculo");
		tfAnoFabricacaoVeiculo
				.addValueChangeListener(new ValueChangeListener() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						// TODO Auto-generated method stub
						if (ObjectValidator.validateEventValue(event)) {
							controller.ndeVeiculoSetarValor(
									tfAnoFabricacaoVeiculo.getId(), event
											.getProperty().getValue());
						}
					}
				});
		glNdeVeiculo.addComponent(tfAnoFabricacaoVeiculo, 2, 3);

		// tfTipoPinturaVeiculo
		tfTipoPinturaVeiculo = new TextField("Tipo de pintura:");
		tfTipoPinturaVeiculo.setWidth("-1px");
		tfTipoPinturaVeiculo.setHeight("-1px");
		tfTipoPinturaVeiculo.setSizeFull();
		tfTipoPinturaVeiculo.setNullRepresentation("");
		tfTipoPinturaVeiculo.setImmediate(true);
		tfTipoPinturaVeiculo.setId("tfTipoPinturaVeiculo");
		tfTipoPinturaVeiculo.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.ndeVeiculoSetarValor(tfTipoPinturaVeiculo
							.getId(), event.getProperty().getValue());
				}
			}
		});
		glNdeVeiculo.addComponent(tfTipoPinturaVeiculo, 3, 3);

		// tfTipoVeiculo
		tfTipoVeiculo = new TextField("Tipo de veículo:");
		tfTipoVeiculo.setWidth("-1px");
		tfTipoVeiculo.setHeight("-1px");
		tfTipoVeiculo.setSizeFull();
		tfTipoVeiculo.setNullRepresentation("");
		tfTipoVeiculo.setImmediate(true);
		tfTipoVeiculo.setId("tfTipoVeiculo");
		tfTipoVeiculo.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.ndeVeiculoSetarValor(tfTipoVeiculo.getId(),
							event.getProperty().getValue());
				}
			}
		});
		glNdeVeiculo.addComponent(tfTipoVeiculo, 0, 4);

		// tfEspecieVeiculo
		tfEspecieVeiculo = new TextField("Espécie de veículo:");
		tfEspecieVeiculo.setWidth("-1px");
		tfEspecieVeiculo.setHeight("-1px");
		tfEspecieVeiculo.setSizeFull();
		tfEspecieVeiculo.setNullRepresentation("");
		tfEspecieVeiculo.setImmediate(true);
		tfEspecieVeiculo.setId("tfEspecieVeiculo");
		tfEspecieVeiculo.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.ndeVeiculoSetarValor(tfEspecieVeiculo.getId(),
							event.getProperty().getValue());
				}
			}
		});
		glNdeVeiculo.addComponent(tfEspecieVeiculo, 1, 4);

		// tfCondicaoVinVeiculo
		tfCondicaoVinVeiculo = new TextField("Condição VIN:");
		tfCondicaoVinVeiculo.setWidth("-1px");
		tfCondicaoVinVeiculo.setHeight("-1px");
		tfCondicaoVinVeiculo.setSizeFull();
		tfCondicaoVinVeiculo.setNullRepresentation("");
		tfCondicaoVinVeiculo.setImmediate(true);
		tfCondicaoVinVeiculo.setId("tfCondicaoVinVeiculo");
		tfCondicaoVinVeiculo.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.ndeVeiculoSetarValor(tfCondicaoVinVeiculo
							.getId(), event.getProperty().getValue());
				}
			}
		});
		glNdeVeiculo.addComponent(tfCondicaoVinVeiculo, 2, 4);

		// tfCondicaoVeiculo
		tfCondicaoVeiculo = new TextField("Condição do veículo:");
		tfCondicaoVeiculo.setWidth("-1px");
		tfCondicaoVeiculo.setHeight("-1px");
		tfCondicaoVeiculo.setSizeFull();
		tfCondicaoVeiculo.setNullRepresentation("");
		tfCondicaoVeiculo.setImmediate(true);
		tfCondicaoVeiculo.setId("tfCondicaoVeiculo");
		tfCondicaoVeiculo.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.ndeVeiculoSetarValor(tfCondicaoVeiculo.getId(),
							event.getProperty().getValue());
				}
			}
		});
		glNdeVeiculo.addComponent(tfCondicaoVeiculo, 3, 4);

		// tfCodigoMarcaModeloVeiculo
		tfCodigoMarcaModeloVeiculo = new TextField("Código / marca / modelo:");
		tfCodigoMarcaModeloVeiculo.setWidth("-1px");
		tfCodigoMarcaModeloVeiculo.setHeight("-1px");
		tfCodigoMarcaModeloVeiculo.setSizeFull();
		tfCodigoMarcaModeloVeiculo.setNullRepresentation("");
		tfCodigoMarcaModeloVeiculo.setImmediate(true);
		tfCodigoMarcaModeloVeiculo.setId("tfCodigoMarcaModeloVeiculo");
		tfCodigoMarcaModeloVeiculo
				.addValueChangeListener(new ValueChangeListener() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						// TODO Auto-generated method stub
						if (ObjectValidator.validateEventValue(event)) {
							controller.ndeVeiculoSetarValor(
									tfCodigoMarcaModeloVeiculo.getId(), event
											.getProperty().getValue());
						}
					}
				});
		glNdeVeiculo.addComponent(tfCodigoMarcaModeloVeiculo, 0, 5);

		// tfCodigoCorDenatranVeiculo
		tfCodigoCorDenatranVeiculo = new TextField("Código da cor (Denatran):");
		tfCodigoCorDenatranVeiculo.setWidth("-1px");
		tfCodigoCorDenatranVeiculo.setHeight("-1px");
		tfCodigoCorDenatranVeiculo.setSizeFull();
		tfCodigoCorDenatranVeiculo.setNullRepresentation("");
		tfCodigoCorDenatranVeiculo.setImmediate(true);
		tfCodigoCorDenatranVeiculo.setId("tfCodigoCorDenatranVeiculo");
		tfCodigoCorDenatranVeiculo
				.addValueChangeListener(new ValueChangeListener() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						// TODO Auto-generated method stub
						if (ObjectValidator.validateEventValue(event)) {
							controller.ndeVeiculoSetarValor(
									tfCodigoCorDenatranVeiculo.getId(), event
											.getProperty().getValue());
						}
					}
				});
		glNdeVeiculo.addComponent(tfCodigoCorDenatranVeiculo, 1, 5);

		// tfLotacaoVeiculo
		tfLotacaoVeiculo = new TextField("Lotação:");
		tfLotacaoVeiculo.setWidth("-1px");
		tfLotacaoVeiculo.setHeight("-1px");
		tfLotacaoVeiculo.setSizeFull();
		tfLotacaoVeiculo.setNullRepresentation("");
		tfLotacaoVeiculo.setImmediate(true);
		tfLotacaoVeiculo.setId("tfLotacaoVeiculo");
		tfLotacaoVeiculo.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateInteger(event)) {
						controller.ndeVeiculoSetarValor(tfLotacaoVeiculo
								.getId(), event.getProperty().getValue());
					}
				}
			}
		});
		glNdeVeiculo.addComponent(tfLotacaoVeiculo, 2, 5);

		// tfRestricaoVeiculo
		tfRestricaoVeiculo = new TextField("Restrição:");
		tfRestricaoVeiculo.setWidth("-1px");
		tfRestricaoVeiculo.setHeight("-1px");
		tfRestricaoVeiculo.setSizeFull();
		tfRestricaoVeiculo.setNullRepresentation("");
		tfRestricaoVeiculo.setImmediate(true);
		tfRestricaoVeiculo.setId("tfRestricaoVeiculo");
		tfRestricaoVeiculo.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.ndeVeiculoSetarValor(tfRestricaoVeiculo.getId(),
							event.getProperty().getValue());
				}
			}
		});
		glNdeVeiculo.addComponent(tfRestricaoVeiculo, 3, 5);

		return glNdeVeiculo;
	}

}