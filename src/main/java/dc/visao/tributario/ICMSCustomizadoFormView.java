package dc.visao.tributario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Container;
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

import dc.controller.geral.diverso.UfFormController;
import dc.controller.tributario.IcmsCustomizadoFormController;
import dc.entidade.geral.diverso.UfEntity;
import dc.entidade.tributario.IcmsCustomizadoCabecalhoEntity;
import dc.entidade.tributario.IcmsCustomizadoDetalheEntity;
import dc.servicos.dao.geral.UfDAO;
import dc.visao.framework.component.SubFormComponent;
import dc.visao.framework.util.ComponentUtil;

@SuppressWarnings("serial")
public class ICMSCustomizadoFormView extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private GridLayout fields;

	@AutoGenerated
	private TextField txtDescricao;

	@AutoGenerated
	ComboBox origemMercadoria;

	IcmsCustomizadoFormController controller;

	IcmsCustomizadoCabecalhoEntity currentBean;

	private SubFormComponent<IcmsCustomizadoDetalheEntity, Integer> detalhesSubForm;

	@AutoGenerated
	private TabSheet subForms;

	@Autowired
	UfDAO ufDAO;

	@Autowired
	UfFormController ufController;

	public ICMSCustomizadoFormView(IcmsCustomizadoFormController controller) {
		this.controller = controller;
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	@AutoGenerated
	private GridLayout buildFields() {
		// common part: create layout
		fields = new GridLayout(6, 1);
		fields.setImmediate(false);
		fields.setWidth("100.0%");
		fields.setHeight("-1px");
		fields.setMargin(false);
		fields.setSpacing(true);

		txtDescricao = ComponentUtil.buildTextField("Descrição");
		txtDescricao.setRequired(true);
		fields.addComponent(txtDescricao, 0, 0, 3, 0);

		origemMercadoria = new ComboBox("Origem Mercadoria");
		origemMercadoria.setRequired(true);
		carregarOrigemMercadoria();
		fields.addComponent(origemMercadoria, 4, 0);

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
		setHeight("100%");

		// fields
		fields = buildFields();
		mainLayout.addComponent(fields);

		subForms = new TabSheet();
		subForms.setWidth("100.0%");
		subForms.setHeight("100.0%");
		subForms.setSizeFull();
		subForms.setImmediate(true);

		buildDetalhesSubForm();
		mainLayout.addComponent(subForms);
		mainLayout.setExpandRatio(subForms, 2);

		return mainLayout;
	}

	public VerticalLayout getMainLayout() {
		return mainLayout;
	}

	public enum ORIGEM_MERCADORIA {

		NACIONAL("Nacional", "0"), ESTRANGEIRA("Estrangeira", "1");

		private ORIGEM_MERCADORIA(String label, String codigo) {
			this.label = label;
			this.codigo = codigo;
		}

		private String label;
		private String codigo;

		public static ORIGEM_MERCADORIA getOrigemMercadoria(String codigo) {
			if (codigo.equals("0")) {
				return NACIONAL;
			}

			if (codigo.equals("1")) {
				return ESTRANGEIRA;
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

	public void carregarOrigemMercadoria() {
		this.origemMercadoria.removeAllItems();
		this.origemMercadoria.addItem(ORIGEM_MERCADORIA.NACIONAL);
		this.origemMercadoria.addItem(ORIGEM_MERCADORIA.ESTRANGEIRA);
	}

	public TextField getTxtDescricao() {
		return txtDescricao;
	}

	public void setTxtDescricao(TextField txtDescricao) {
		this.txtDescricao = txtDescricao;
	}

	public ComboBox getOrigemMercadoria() {
		return origemMercadoria;
	}

	public void setOrigemMercadoria(ComboBox origemMercadoria) {
		this.origemMercadoria = origemMercadoria;
	}

	private void buildDetalhesSubForm() {
		// common part: create layout

		detalhesSubForm = new SubFormComponent<IcmsCustomizadoDetalheEntity, Integer>(
				IcmsCustomizadoDetalheEntity.class, new String[] { "ufDestino",
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
							ComboBox combox = ComponentUtil
									.buildComboBox("CST_B");
							combox.setContainerDataSource(controller
									.carregarCstb());
							return combox;

						}

						if ("modalidadeBc".equals(propertyId)) {
							TextField field = ComponentUtil
									.buildTextField("Modalidade BC");
							field.setMaxLength(1);
							return field;
						}

						if ("aliquota".equals(propertyId)) {
							TextField field = ComponentUtil
									.buildTextField("Aliquota");
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

			protected IcmsCustomizadoDetalheEntity getNovo() {
				IcmsCustomizadoDetalheEntity detalhe = controller.novoDetalhe();
				return detalhe;
			}

			@Override
			public boolean validateItems(List<IcmsCustomizadoDetalheEntity> items) {
				// TODO Auto-generated method stub
				return true;
			}

		};

		GridLayout layout = new GridLayout(1, 1);
		layout.setImmediate(false);
		layout.setWidth("100.0%");
		layout.setHeight("100.0%");
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setSizeFull();
		layout.addComponent(detalhesSubForm);
		subForms.addTab(layout, "ICMS", null);
	}

	public void preencheSubForm(List<IcmsCustomizadoDetalheEntity> detalhes) {
		detalhesSubForm.fillWith(detalhes);
	}

	public SubFormComponent<IcmsCustomizadoDetalheEntity, Integer> getDetalhesSubForm() {
		return detalhesSubForm;
	}

	public void setDetalhesSubForm(
			SubFormComponent<IcmsCustomizadoDetalheEntity, Integer> detalhesSubForm) {
		this.detalhesSubForm = detalhesSubForm;
	}

}
