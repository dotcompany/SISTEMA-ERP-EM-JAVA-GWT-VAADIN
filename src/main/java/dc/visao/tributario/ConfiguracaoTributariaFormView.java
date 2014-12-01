package dc.visao.tributario;

import java.util.List;

import org.vaadin.addons.maskedtextfield.NumericField;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.tributario.ConfiguracaoTributariaFormController;
import dc.entidade.geral.UfEntity;
import dc.entidade.tabelas.CstCofins;
import dc.entidade.tabelas.CstIpi;
import dc.entidade.tabelas.CstPis;
import dc.entidade.tabelas.EfdTabela435;
import dc.entidade.tabelas.TipoReceitaDipi;
import dc.entidade.tributario.ConfiguracaoTributaria;
import dc.entidade.tributario.GrupoTributarioEntity;
import dc.entidade.tributario.ICMSConfiguracaoTributaria;
import dc.entidade.tributario.OperacaoFiscalEntity;
import dc.visao.framework.component.SubFormComponent;
import dc.visao.framework.component.manytoonecombo.ManyToOneCombo;
import dc.visao.framework.util.ComponentUtil;

public class ConfiguracaoTributariaFormView extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private GridLayout fields;

	public ConfiguracaoTributariaFormController controller;

	ConfiguracaoTributaria currentBean;

	private ManyToOneCombo<GrupoTributarioEntity> cmbGrupoTributario;

	private ManyToOneCombo<OperacaoFiscalEntity> cmbOperacaoFiscal;

	@AutoGenerated
	private TabSheet subForms;

	TextField txtCstPis, txtDescricaoCstPis;

	TextField txtEfdPis, txtDescricaoEfdPis;

	ComboBox cmbModalidadeBcPis;

	TextField txtPorcentoBcPis, txtAliquotaBcPis, txtAliquotaUnidadePis,
			txtValorPrecoMaximoPis, txtValorPautaFiscalPis;

	TextField txtCstCofins, txtDescricaoCstCofins;

	TextField txtEfdCofins, txtDescricaoEfdCofins;

	ComboBox cmbModalidadeBcCofins;

	TextField txtPorcentoBcCofins, txtAliquotaBcCofins,
			txtAliquotaUnidadeCofins, txtValorPrecoMaximoCofins,
			txtValorPautaFiscalCofins;

	TextField txtCstIPI, txtDescricaoCstIPI;

	TextField txtDipi, txtDescricaoDipi;
	ComboBox cmbModalidadeBcIPI;

	TextField txtPorcentoBcIPI, txtAliquotaBcIPI, txtAliquotaUnidadeIPI,
			txtValorPrecoMaximoIPI, txtValorPautaFiscalIPI;

	private SubFormComponent<ICMSConfiguracaoTributaria, Integer> icmsSubForm;

	public ConfiguracaoTributariaFormView(
			ConfiguracaoTributariaFormController controller) {
		this.controller = controller;
		buildMainLayout();
		setCompositionRoot(mainLayout);

	}

	@AutoGenerated
	private GridLayout buildFields() {
		// common part: create layout
		fields = new GridLayout(6, 3);
		fields.setImmediate(false);
		fields.setWidth("100.0%");
		fields.setHeight("-1px");
		fields.setMargin(false);
		fields.setSpacing(true);

		// calDataRequisicao

		// cmbGrupoTributario = ComponentUtil.buildComboBox("Grupo Tributário");
		cmbGrupoTributario = new ManyToOneCombo<GrupoTributarioEntity>();
		cmbGrupoTributario.setCaption("Grupo Tributário");
		// cmbOperacaoFiscal = ComponentUtil.buildComboBox("Operação Fiscal");
		cmbOperacaoFiscal = new ManyToOneCombo<OperacaoFiscalEntity>();
		cmbOperacaoFiscal.setCaption("Operação Fiscal");
		cmbOperacaoFiscal.setReadOnly(true);
		// montaGrupoTributario();
		// montaOperacaoFiscal();
		fields.addComponent(cmbGrupoTributario, 0, 0, 1, 0);
		fields.addComponent(cmbOperacaoFiscal, 2, 0, 3, 0);
		return fields;
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

		// fields
		fields = buildFields();
		mainLayout.addComponent(fields);

		subForms = new TabSheet();
		subForms.setWidth("100.0%");
		subForms.setHeight("100%");
		subForms.setSizeFull();
		subForms.setImmediate(true);
		buildAbaICMS();
		buildAbaPIS();
		buildAbaCofins();
		buildAbaIPI();
		mainLayout.addComponent(subForms);
		mainLayout.setExpandRatio(subForms, 1);

		return mainLayout;
	}

	public VerticalLayout getMainLayout() {
		return mainLayout;
	}

	public ManyToOneCombo<GrupoTributarioEntity> getCmbGrupoTributario() {
		return cmbGrupoTributario;
	}

	public void setCmbGrupoTributario(
			ManyToOneCombo<GrupoTributarioEntity> cmbGrupoTributario) {
		this.cmbGrupoTributario = cmbGrupoTributario;
	}

	public ManyToOneCombo<OperacaoFiscalEntity> getCmbOperacaoFiscal() {
		return cmbOperacaoFiscal;
	}

	public void setCmbOperacaoFiscal(
			ManyToOneCombo<OperacaoFiscalEntity> cmbOperacaoFiscal) {
		this.cmbOperacaoFiscal = cmbOperacaoFiscal;
	}

	public TextField getTxtCstPis() {
		return txtCstPis;
	}

	public void setTxtCstPis(TextField txtCstPis) {
		this.txtCstPis = txtCstPis;
	}

	public void buildAbaICMS() {

		GridLayout layout = new GridLayout(1, 1);
		layout.setImmediate(false);
		layout.setWidth("100.0%");
		layout.setHeight("100.0%");
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setSizeFull();
		layout.addComponent(buildIcmsSubForm());
		subForms.addTab(layout, "ICMS", null);
	}

	public void buildAbaPIS() {

		GridLayout layout = new GridLayout(14, 3);
		layout.setImmediate(false);
		layout.setMargin(true);
		layout.setSpacing(true);

		txtCstPis = ComponentUtil.buildTextField("CST");
		txtCstPis.setMaxLength(2);
		txtCstPis.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				String codigo = (String) event.getProperty().getValue();
				if (codigo != null && !(codigo.isEmpty())) {
					CstPis cst = controller.consultarCstPis(codigo);
					if (cst != null)
						txtDescricaoCstPis.setValue(cst.getDescricao());
				}

			}
		});

		layout.addComponent(txtCstPis, 0, 0);
		txtDescricaoCstPis = ComponentUtil.buildTextField("");
		txtDescricaoCstPis.setWidth("700");
		layout.addComponent(txtDescricaoCstPis, 1, 0, 8, 0);

		txtEfdPis = ComponentUtil.buildTextField("EFD Tabela 435");
		txtEfdPis.setMaxLength(2);
		txtEfdPis.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				String codigo = (String) event.getProperty().getValue();
				if (codigo != null && !(codigo.isEmpty())) {
					EfdTabela435 efd = controller.consultarEfd(codigo);
					if (efd != null)
						txtDescricaoEfdPis.setValue(efd.getDescricao());
				}

			}
		});

		layout.addComponent(txtEfdPis, 0, 1);
		txtDescricaoEfdPis = ComponentUtil.buildTextField("");
		txtDescricaoEfdPis.setWidth("700");
		layout.addComponent(txtDescricaoEfdPis, 1, 1, 8, 1);

		cmbModalidadeBcPis = ComponentUtil
				.buildComboBox("Modalidade Base de Cálculo");
		carregarModalidadePis();
		layout.addComponent(cmbModalidadeBcPis, 0, 2, 1, 2);

		txtPorcentoBcPis = ComponentUtil.buildNumericField("Porcento BC");
		layout.addComponent(txtPorcentoBcPis, 2, 2, 3, 2);

		txtAliquotaBcPis = ComponentUtil.buildNumericField("Aliquota Porcento");
		layout.addComponent(txtAliquotaBcPis, 4, 2, 5, 2);

		txtAliquotaUnidadePis = ComponentUtil
				.buildNumericField("Aliquota Unidade");
		layout.addComponent(txtAliquotaUnidadePis, 6, 2, 7, 2);

		txtValorPrecoMaximoPis = ComponentUtil
				.buildCurrencyField("Preço Máximo");
		layout.addComponent(txtValorPrecoMaximoPis, 8, 2, 10, 2);

		txtValorPautaFiscalPis = ComponentUtil
				.buildCurrencyField("Valor Pauta Fiscal");
		layout.addComponent(txtValorPautaFiscalPis, 11, 2, 13, 2);

		subForms.addTab(layout, "PIS", null);
	}

	public void buildAbaCofins() {

		GridLayout layout = new GridLayout(14, 3);
		layout.setImmediate(false);
		layout.setMargin(true);
		layout.setSpacing(true);

		txtCstCofins = ComponentUtil.buildTextField("CST");
		txtCstCofins.setMaxLength(2);
		txtCstCofins.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				String codigo = (String) event.getProperty().getValue();
				if (codigo != null && !(codigo.isEmpty())) {
					CstCofins cst = controller.consultarCstCofins(codigo);
					if (cst != null)
						txtDescricaoCstCofins.setValue(cst.getDescricao());
				}

			}
		});

		layout.addComponent(txtCstCofins, 0, 0);
		txtDescricaoCstCofins = ComponentUtil.buildTextField("");
		txtDescricaoCstCofins.setWidth("700");
		layout.addComponent(txtDescricaoCstCofins, 1, 0, 8, 0);

		txtEfdCofins = ComponentUtil.buildTextField("Código Apuração EFD");
		txtEfdCofins.setMaxLength(2);
		txtEfdCofins.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				String codigo = (String) event.getProperty().getValue();
				if (codigo != null && !(codigo.isEmpty())) {
					EfdTabela435 efd = controller.consultarEfd(codigo);
					if (efd != null)
						txtDescricaoEfdCofins.setValue(efd.getDescricao());
				}

			}
		});

		layout.addComponent(txtEfdCofins, 0, 1);
		txtDescricaoEfdCofins = ComponentUtil.buildTextField("");
		txtDescricaoEfdCofins.setWidth("700");
		layout.addComponent(txtDescricaoEfdCofins, 1, 1, 8, 1);

		cmbModalidadeBcCofins = ComponentUtil
				.buildComboBox("Modalidade Base de Cálculo");
		carregarModalidadeCofins();
		layout.addComponent(cmbModalidadeBcCofins, 0, 2, 1, 2);

		txtPorcentoBcCofins = ComponentUtil.buildNumericField("Porcento BC");
		layout.addComponent(txtPorcentoBcCofins, 2, 2, 3, 2);

		txtAliquotaBcCofins = ComponentUtil
				.buildNumericField("Aliquota Porcento");
		layout.addComponent(txtAliquotaBcCofins, 4, 2, 5, 2);

		txtAliquotaUnidadeCofins = ComponentUtil
				.buildNumericField("Aliquota Unidade");
		layout.addComponent(txtAliquotaUnidadeCofins, 6, 2, 7, 2);

		txtValorPrecoMaximoCofins = ComponentUtil
				.buildCurrencyField("Preço Máximo");
		layout.addComponent(txtValorPrecoMaximoCofins, 8, 2, 10, 2);

		txtValorPautaFiscalCofins = ComponentUtil
				.buildCurrencyField("Valor Pauta Fiscal");
		layout.addComponent(txtValorPautaFiscalCofins, 11, 2, 13, 2);

		subForms.addTab(layout, "COFINS", null);
	}

	public void buildAbaIPI() {

		GridLayout layout = new GridLayout(14, 3);
		layout.setImmediate(false);
		layout.setMargin(true);
		layout.setSpacing(true);

		txtCstIPI = ComponentUtil.buildTextField("CST");
		txtCstIPI.setMaxLength(2);
		txtCstIPI.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				String codigo = (String) event.getProperty().getValue();
				if (codigo != null && !(codigo.isEmpty())) {
					CstIpi cst = controller.consultarCstIpi(codigo);
					if (cst != null)
						txtDescricaoCstIPI.setValue(cst.getDescricao());
				}

			}
		});

		layout.addComponent(txtCstIPI, 0, 0);
		txtDescricaoCstIPI = ComponentUtil.buildTextField("");
		txtDescricaoCstIPI.setWidth("700");
		layout.addComponent(txtDescricaoCstIPI, 1, 0, 8, 0);

		txtDipi = ComponentUtil.buildTextField("Tipo Receita Dipi");
		txtDipi.setMaxLength(2);
		txtDipi.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				String codigo = (String) event.getProperty().getValue();
				if (codigo != null && !(codigo.isEmpty())) {
					TipoReceitaDipi tipo = controller.consultarDipi(codigo);
					if (tipo != null)
						txtDescricaoDipi.setValue(tipo.getDescricao());
				}

			}
		});

		layout.addComponent(txtDipi, 0, 1);
		txtDescricaoDipi = ComponentUtil.buildTextField("");
		txtDescricaoDipi.setWidth("700");
		layout.addComponent(txtDescricaoDipi, 1, 1, 8, 1);

		cmbModalidadeBcIPI = ComponentUtil
				.buildComboBox("Modalidade Base de Cálculo");
		carregarModalidadeIpi();
		layout.addComponent(cmbModalidadeBcIPI, 0, 2, 1, 2);

		txtPorcentoBcIPI = ComponentUtil.buildNumericField("Porcento BC");
		layout.addComponent(txtPorcentoBcIPI, 2, 2, 3, 2);

		txtAliquotaBcIPI = ComponentUtil.buildNumericField("Aliquota Porcento");
		layout.addComponent(txtAliquotaBcIPI, 4, 2, 5, 2);

		txtAliquotaUnidadeIPI = ComponentUtil
				.buildNumericField("Aliquota Unidade");
		layout.addComponent(txtAliquotaUnidadeIPI, 6, 2, 7, 2);

		txtValorPrecoMaximoIPI = ComponentUtil
				.buildCurrencyField("Preço Máximo");
		layout.addComponent(txtValorPrecoMaximoIPI, 8, 2, 10, 2);

		txtValorPautaFiscalIPI = ComponentUtil
				.buildCurrencyField("Valor Pauta Fiscal");
		layout.addComponent(txtValorPautaFiscalIPI, 11, 2, 13, 2);

		subForms.addTab(layout, "IPI", null);
	}

	private SubFormComponent<ICMSConfiguracaoTributaria, Integer> buildIcmsSubForm() {
		// common part: create layout

		icmsSubForm = new SubFormComponent<ICMSConfiguracaoTributaria, Integer>(
				ICMSConfiguracaoTributaria.class, new String[] { "ufDestino",
						"cfop", "csosn", "cst", "modalidadeBc", "aliquota",
						"valorPauta", "valorPrecoMaximo" }, new String[] {
						"UF", "CFOP", "CSOSN_B", "CST_B", "Modalidade BC",
						"Aliquota", "Valor Pauta", "Valor Preço Máximo" }) {

			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					@Override
					public Field<?> createField(Container container,
							Object itemId, Object propertyId,
							Component uiContext) {

						if ("ufDestino".equals(propertyId)) {
							ComboBox cmb = ComponentUtil.buildComboBox("UF");
							// BeanItemContainer<UF> containerUf = new
							// BeanItemContainer<>(controller.listarUfs());
							// cmb.setContainerDataSource(containerUf);
							// cmb.setItemCaptionMode(ItemCaptionMode.PROPERTY);
							// cmb.setItemCaptionPropertyId("sigla");
							List<UfEntity> ufs = controller.listarUfs();
							BeanItemContainer<String> beanUf = new BeanItemContainer<>(
									String.class);
							for (UfEntity u : ufs) {
								beanUf.addBean(u.getSigla());
							}
							cmb.setContainerDataSource(beanUf);
							return cmb;
						}

						if ("cfop".equals(propertyId)) {
							ComboBox combo = ComponentUtil
									.buildComboBox("CFOP");
							combo.setContainerDataSource(controller
									.carregarCfop());
							return combo;
						}

						if ("csosn".equals(propertyId)) {
							ComboBox combo = ComponentUtil
									.buildComboBox("CSOSN_B");
							combo.setContainerDataSource(controller
									.carregarCsosnb());
							return combo;
						}

						if ("cst".equals(propertyId)) {
							ComboBox combo = ComponentUtil
									.buildComboBox("CST_B");
							combo.setContainerDataSource(controller
									.carregarCstB());
							return combo;
						}

						if ("modalidadeBc".equals(propertyId)) {
							ComboBox field = ComponentUtil
									.buildComboBox("Modalidade Base de Cálculo");
							field.removeAllItems();
							field.addItem(MODALIDADE_BC.PERCENTUAL);
							field.addItem(MODALIDADE_BC.TESTE);
							return field;
						}

						if ("aliquota".equals(propertyId)) {
							NumericField field = ComponentUtil
									.buildNumericField("Aliquota");
							return field;
						}

						if ("valorPauta".equals(propertyId)) {
							TextField field = ComponentUtil
									.buildCurrencyField("Valor Pauta");
							return field;
						}

						if ("valorPrecoMaximo".equals(propertyId)) {
							TextField field = ComponentUtil
									.buildCurrencyField("Valor Preço Máximo");
							return field;
						}
						return null;
					}
				};
			}

			protected ICMSConfiguracaoTributaria getNovo() {
				ICMSConfiguracaoTributaria detalhe = controller.novoIcms();
				return detalhe;
			}

			@Override
			public boolean validateItems(List<ICMSConfiguracaoTributaria> items) {
				// TODO Auto-generated method stub
				return true;
			}
		};

		return icmsSubForm;

	}

	public void preencherIcmsSubForm(List<ICMSConfiguracaoTributaria> detalhes) {
		try {
			icmsSubForm.fillWith(detalhes);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public enum MODALIDADE_BC {

		PERCENTUAL("Percentual", "0"), TESTE("Teste", "1");

		private MODALIDADE_BC(String label, String codigo) {
			this.label = label;
			this.codigo = codigo;
		}

		private String label;
		private String codigo;

		public static MODALIDADE_BC getModalidadeBc(String codigo) {
			if (codigo.equals("0")) {
				return PERCENTUAL;
			}
			if (codigo.equals("1")) {
				return TESTE;
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

	public void carregarModalidadePis() {
		cmbModalidadeBcPis.removeAllItems();
		cmbModalidadeBcPis.addItem(MODALIDADE_BC.PERCENTUAL);
		cmbModalidadeBcPis.addItem(MODALIDADE_BC.TESTE);
	}

	public void carregarModalidadeCofins() {
		cmbModalidadeBcCofins.removeAllItems();
		cmbModalidadeBcCofins.addItem(MODALIDADE_BC.PERCENTUAL);
		cmbModalidadeBcCofins.addItem(MODALIDADE_BC.TESTE);
	}

	public void carregarModalidadeIpi() {
		cmbModalidadeBcIPI.removeAllItems();
		cmbModalidadeBcIPI.addItem(MODALIDADE_BC.PERCENTUAL);
		cmbModalidadeBcIPI.addItem(MODALIDADE_BC.TESTE);
	}

	public GridLayout getFields() {
		return fields;
	}

	public void setFields(GridLayout fields) {
		this.fields = fields;
	}

	public ConfiguracaoTributariaFormController getController() {
		return controller;
	}

	public void setController(ConfiguracaoTributariaFormController controller) {
		this.controller = controller;
	}

	public ConfiguracaoTributaria getCurrentBean() {
		return currentBean;
	}

	public void setCurrentBean(ConfiguracaoTributaria currentBean) {
		this.currentBean = currentBean;
	}

	public TabSheet getSubForms() {
		return subForms;
	}

	public void setSubForms(TabSheet subForms) {
		this.subForms = subForms;
	}

	public TextField getTxtDescricaoCstPis() {
		return txtDescricaoCstPis;
	}

	public void setTxtDescricaoCstPis(TextField txtDescricaoCstPis) {
		this.txtDescricaoCstPis = txtDescricaoCstPis;
	}

	public TextField getTxtEfdPis() {
		return txtEfdPis;
	}

	public void setTxtEfdPis(TextField txtEfdPis) {
		this.txtEfdPis = txtEfdPis;
	}

	public TextField getTxtDescricaoEfdPis() {
		return txtDescricaoEfdPis;
	}

	public void setTxtDescricaoEfdPis(TextField txtDescricaoEfdPis) {
		this.txtDescricaoEfdPis = txtDescricaoEfdPis;
	}

	public ComboBox getCmbModalidadeBcPis() {
		return cmbModalidadeBcPis;
	}

	public void setCmbModalidadeBcPis(ComboBox cmbModalidadeBcPis) {
		this.cmbModalidadeBcPis = cmbModalidadeBcPis;
	}

	public TextField getTxtPorcentoBcPis() {
		return txtPorcentoBcPis;
	}

	public void setTxtPorcentoBcPis(TextField txtPorcentoBcPis) {
		this.txtPorcentoBcPis = txtPorcentoBcPis;
	}

	public TextField getTxtAliquotaBcPis() {
		return txtAliquotaBcPis;
	}

	public void setTxtAliquotaBcPis(TextField txtAliquotaBcPis) {
		this.txtAliquotaBcPis = txtAliquotaBcPis;
	}

	public TextField getTxtAliquotaUnidadePis() {
		return txtAliquotaUnidadePis;
	}

	public void setTxtAliquotaUnidadePis(TextField txtAliquotaUnidadePis) {
		this.txtAliquotaUnidadePis = txtAliquotaUnidadePis;
	}

	public TextField getTxtValorPrecoMaximoPis() {
		return txtValorPrecoMaximoPis;
	}

	public void setTxtValorPrecoMaximoPis(TextField txtValorPrecoMaximoPis) {
		this.txtValorPrecoMaximoPis = txtValorPrecoMaximoPis;
	}

	public TextField getTxtValorPautaFiscalPis() {
		return txtValorPautaFiscalPis;
	}

	public void setTxtValorPautaFiscalPis(TextField txtValorPautaFiscalPis) {
		this.txtValorPautaFiscalPis = txtValorPautaFiscalPis;
	}

	public TextField getTxtCstCofins() {
		return txtCstCofins;
	}

	public void setTxtCstCofins(TextField txtCstCofins) {
		this.txtCstCofins = txtCstCofins;
	}

	public TextField getTxtDescricaoCstCofins() {
		return txtDescricaoCstCofins;
	}

	public void setTxtDescricaoCstCofins(TextField txtDescricaoCstCofins) {
		this.txtDescricaoCstCofins = txtDescricaoCstCofins;
	}

	public TextField getTxtEfdCofins() {
		return txtEfdCofins;
	}

	public void setTxtEfdCofins(TextField txtEfdCofins) {
		this.txtEfdCofins = txtEfdCofins;
	}

	public TextField getTxtDescricaoEfdCofins() {
		return txtDescricaoEfdCofins;
	}

	public void setTxtDescricaoEfdCofins(TextField txtDescricaoEfdCofins) {
		this.txtDescricaoEfdCofins = txtDescricaoEfdCofins;
	}

	public ComboBox getCmbModalidadeBcCofins() {
		return cmbModalidadeBcCofins;
	}

	public void setCmbModalidadeBcCofins(ComboBox cmbModalidadeBcCofins) {
		this.cmbModalidadeBcCofins = cmbModalidadeBcCofins;
	}

	public TextField getTxtPorcentoBcCofins() {
		return txtPorcentoBcCofins;
	}

	public void setTxtPorcentoBcCofins(TextField txtPorcentoBcCofins) {
		this.txtPorcentoBcCofins = txtPorcentoBcCofins;
	}

	public TextField getTxtAliquotaBcCofins() {
		return txtAliquotaBcCofins;
	}

	public void setTxtAliquotaBcCofins(TextField txtAliquotaBcCofins) {
		this.txtAliquotaBcCofins = txtAliquotaBcCofins;
	}

	public TextField getTxtAliquotaUnidadeCofins() {
		return txtAliquotaUnidadeCofins;
	}

	public void setTxtAliquotaUnidadeCofins(TextField txtAliquotaUnidadeCofins) {
		this.txtAliquotaUnidadeCofins = txtAliquotaUnidadeCofins;
	}

	public TextField getTxtValorPrecoMaximoCofins() {
		return txtValorPrecoMaximoCofins;
	}

	public void setTxtValorPrecoMaximoCofins(TextField txtValorPrecoMaximoCofins) {
		this.txtValorPrecoMaximoCofins = txtValorPrecoMaximoCofins;
	}

	public TextField getTxtValorPautaFiscalCofins() {
		return txtValorPautaFiscalCofins;
	}

	public void setTxtValorPautaFiscalCofins(TextField txtValorPautaFiscalCofins) {
		this.txtValorPautaFiscalCofins = txtValorPautaFiscalCofins;
	}

	public TextField getTxtCstIPI() {
		return txtCstIPI;
	}

	public void setTxtCstIPI(TextField txtCstIPI) {
		this.txtCstIPI = txtCstIPI;
	}

	public TextField getTxtDescricaoCstIPI() {
		return txtDescricaoCstIPI;
	}

	public void setTxtDescricaoCstIPI(TextField txtDescricaoCstIPI) {
		this.txtDescricaoCstIPI = txtDescricaoCstIPI;
	}

	public TextField getTxtDipi() {
		return txtDipi;
	}

	public void setTxtDipi(TextField txtDipi) {
		this.txtDipi = txtDipi;
	}

	public TextField getTxtDescricaoDipi() {
		return txtDescricaoDipi;
	}

	public void setTxtDescricaoDipi(TextField txtDescricaoDipi) {
		this.txtDescricaoDipi = txtDescricaoDipi;
	}

	public ComboBox getCmbModalidadeBcIPI() {
		return cmbModalidadeBcIPI;
	}

	public void setCmbModalidadeBcIPI(ComboBox cmbModalidadeBcIPI) {
		this.cmbModalidadeBcIPI = cmbModalidadeBcIPI;
	}

	public TextField getTxtPorcentoBcIPI() {
		return txtPorcentoBcIPI;
	}

	public void setTxtPorcentoBcIPI(TextField txtPorcentoBcIPI) {
		this.txtPorcentoBcIPI = txtPorcentoBcIPI;
	}

	public TextField getTxtAliquotaBcIPI() {
		return txtAliquotaBcIPI;
	}

	public void setTxtAliquotaBcIPI(TextField txtAliquotaBcIPI) {
		this.txtAliquotaBcIPI = txtAliquotaBcIPI;
	}

	public TextField getTxtAliquotaUnidadeIPI() {
		return txtAliquotaUnidadeIPI;
	}

	public void setTxtAliquotaUnidadeIPI(TextField txtAliquotaUnidadeIPI) {
		this.txtAliquotaUnidadeIPI = txtAliquotaUnidadeIPI;
	}

	public TextField getTxtValorPrecoMaximoIPI() {
		return txtValorPrecoMaximoIPI;
	}

	public void setTxtValorPrecoMaximoIPI(TextField txtValorPrecoMaximoIPI) {
		this.txtValorPrecoMaximoIPI = txtValorPrecoMaximoIPI;
	}

	public TextField getTxtValorPautaFiscalIPI() {
		return txtValorPautaFiscalIPI;
	}

	public void setTxtValorPautaFiscalIPI(TextField txtValorPautaFiscalIPI) {
		this.txtValorPautaFiscalIPI = txtValorPautaFiscalIPI;
	}

	public SubFormComponent<ICMSConfiguracaoTributaria, Integer> getIcmsSubForm() {
		return icmsSubForm;
	}

	public void setIcmsSubForm(
			SubFormComponent<ICMSConfiguracaoTributaria, Integer> icmsSubForm) {
		this.icmsSubForm = icmsSubForm;
	}

	public void setMainLayout(VerticalLayout mainLayout) {
		this.mainLayout = mainLayout;
	}

}