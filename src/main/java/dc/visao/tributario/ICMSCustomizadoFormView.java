package dc.visao.tributario;

import java.util.List;

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
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.control.enums.OrigemMercadoriaEn;
import dc.controller.tributario.IcmsCustomizadoFormController;
import dc.entidade.geral.diverso.UfEntity;
import dc.entidade.geral.tabela.CfopEntity;
import dc.entidade.geral.tabela.CsosnbEntity;
import dc.entidade.geral.tabela.CstIcmsbEntity;
import dc.entidade.tributario.IcmsCustomizadoDetalheEntity;
import dc.visao.framework.component.SubFormComponent;
import dc.visao.framework.util.ComponentUtil;

public class ICMSCustomizadoFormView extends CustomComponent {

	private static final long serialVersionUID = 1L;
	
	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private GridLayout glGeral;
	@AutoGenerated
	private TabSheet subForms;
	
	@AutoGenerated
	private ComboBox cmbOrigemMercadoria;
	@AutoGenerated
	private TextArea txaDescricao;
	
	private SubFormComponent<IcmsCustomizadoDetalheEntity, Integer> detalheSubForm;
	
	IcmsCustomizadoFormController controller;

	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public ICMSCustomizadoFormView(IcmsCustomizadoFormController controller) {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		this.controller = controller;
	}

	@AutoGenerated
	private void buildMainLayout() {
		// the main layout and components will be created here
		setSizeFull();
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		glGeral = bglGeral();
		mainLayout.addComponent(glGeral);
		
		subForms = new TabSheet();
		subForms.setWidth("100.0%");
		subForms.setHeight("100.0%");
		subForms.setSizeFull();
		subForms.setImmediate(true);
		
		subForms.addTab(bvlDetalhes(), 0);		
		
		mainLayout.addComponent(subForms);
		
		//subForms = buildSubFormDetalhe();

		
		for (OrigemMercadoriaEn en : OrigemMercadoriaEn.values()) {
			cmbOrigemMercadoria.addItem(en);
		}
	}

	/**
	 * GERAL
	 */

	@AutoGenerated
	private GridLayout bglGeral() {
		// common part: create layout
		glGeral = new GridLayout(3, 3);
		glGeral.setImmediate(false);
		glGeral.setWidth("100.0%");
		glGeral.setHeight("-1px");
		glGeral.setMargin(false);
		glGeral.setSpacing(true);
		glGeral.setRows(3);
		glGeral.setColumns(3);
		
		// cmbOrigemMercadoria
		cmbOrigemMercadoria = ComponentUtil.buildComboBox("Origem de Mercadoria");
		cmbOrigemMercadoria.setImmediate(true);
		cmbOrigemMercadoria.setHeight("-1px");
		glGeral.addComponent(cmbOrigemMercadoria, 0, 0);
		
		txaDescricao = ComponentUtil.buildTextArea("Descrição");
		txaDescricao.setImmediate(true);
		txaDescricao.setHeight("-1px");
		glGeral.addComponent(txaDescricao, 0, 1);
		
		return glGeral;
		
	}
	
	public VerticalLayout getMainLayout() {
		return mainLayout;
	}

	public void setMainLayout(VerticalLayout mainLayout) {
		this.mainLayout = mainLayout;
	}

	public GridLayout getGlGeral() {
		return glGeral;
	}

	public void setGlGeral(GridLayout glGeral) {
		this.glGeral = glGeral;
	}

	public ComboBox getCmbOrigemMercadoria() {
		return cmbOrigemMercadoria;
	}

	public void setCmbOrigemMercadoria(ComboBox cmbOrigemMercadoria) {
		this.cmbOrigemMercadoria = cmbOrigemMercadoria;
	}

	public TextArea getTxaDescricao() {
		return txaDescricao;
	}

	public void setTxaDescricao(TextArea txaDescricao) {
		this.txaDescricao = txaDescricao;
	}
	
	@AutoGenerated
	private VerticalLayout bvlDetalhes() {
		// common part: create layout
		VerticalLayout vlPessoaContato = new VerticalLayout();
		vlPessoaContato.setImmediate(false);
		vlPessoaContato.setWidth("100.0%");
		vlPessoaContato.setHeight("100.0%");
		vlPessoaContato.setMargin(true);
		vlPessoaContato.setSpacing(true);
		vlPessoaContato.setCaption("Detalhes");

		//
		vlPessoaContato.addComponent(buildSubFormDetalhe());

		return vlPessoaContato;
	}
	
	@AutoGenerated
	private TabSheet buildSubFormDetalhe() {
		// common part: create layout
		TabSheet sub = new TabSheet();
		sub.setWidth("100.0%");
		sub.setHeight("100.0%");
		sub.setSizeFull();
		sub.setImmediate(true);

		detalheSubForm = new SubFormComponent<IcmsCustomizadoDetalheEntity, Integer>(
				IcmsCustomizadoDetalheEntity.class, 
				new String[] { "ufDestino","cfop", "csosnB", "cstB", "modalidadeBc", "aliquota","valorPauta", "valorPrecoMaximo" },
				new String[] { "UF", "CFOP", "CSOSN_B", "CST_B", "Modalidade BC","Aliquota", "Valor Pauta", "Valor Preço Máximo" }) {
	/*@AutoGenerated
	private TabSheet buildSubFormDetalhe() {
		// common part: create layout
		TabSheet sub = new TabSheet();
		sub.setWidth("100.0%");
		sub.setHeight("100.0%");
		sub.setSizeFull();
		sub.setImmediate(true);

			String[] campos = new String[] { "ufDestino","cfop", "csosnB", "cstB", "modalidadeBc", "aliquota","valorPauta", "valorPrecoMaximo" };
			String[] titulos = new String[] { "UF", "CFOP", "CSOSN_B", "CST_B", "Modalidade BC","Aliquota", "Valor Pauta", "Valor Preço Máximo" };

			detalheSubForm = new SubFormComponent<IcmsCustomizadoDetalheEntity, Integer>(
					IcmsCustomizadoDetalheEntity.class, campos, titulos, new String[] {
							"cfop", "valorPauta", "valorPrecoMaximo" }) {*/
		
					private static final long serialVersionUID = 1L;

					@Override
					protected TableFieldFactory getFieldFactory() {
						return new TableFieldFactory() {

							/**
							 * */
							 
							private static final long serialVersionUID = 1L;

							@Override
							public Field<?> createField(Container container,
									Object itemId, Object propertyId,
									Component uiContext) {

								if ("ufDestino".equals(propertyId)) {
									ComboBox comboBox = ComponentUtil.buildComboBox(null);
									BeanItemContainer<UfEntity> beanUf = new BeanItemContainer<>(UfEntity.class,
											controller.buscarUf());
									beanUf.addNestedContainerProperty("nome");
									comboBox.setContainerDataSource(beanUf);
									comboBox.setItemCaptionPropertyId("nome");
									return comboBox;
								}

								if ("cfop".equals(propertyId)) {
									
									ComboBox comboBox = ComponentUtil.buildComboBox(null);
									BeanItemContainer<CfopEntity> bean = new BeanItemContainer<>(CfopEntity.class,
											controller.buscarCfop());
									bean.addNestedContainerProperty("descricao");
									comboBox.setContainerDataSource(bean);
									comboBox.setItemCaptionPropertyId("descricao");
									return comboBox;
								}

								if ("csosnB".equals(propertyId)) {
									ComboBox comboBox = ComponentUtil.buildComboBox(null);
									BeanItemContainer<CsosnbEntity> bean = new BeanItemContainer<>(CsosnbEntity.class,
											controller.buscarCsosn());
									bean.addNestedContainerProperty("descricao");
									comboBox.setContainerDataSource(bean);
									comboBox.setItemCaptionPropertyId("descricao");
									return comboBox;
								}

								if ("cstB".equals(propertyId)) {
									ComboBox comboBox = ComponentUtil.buildComboBox(null);
									BeanItemContainer<CstIcmsbEntity> bean = new BeanItemContainer<>(CstIcmsbEntity.class,
											controller.buscarCst());
									bean.addNestedContainerProperty("descricao");
									comboBox.setContainerDataSource(bean);
									comboBox.setItemCaptionPropertyId("descricao");
									return comboBox;
								}

								if ("modalidadeBc".equals(propertyId)) {
									TextField field = ComponentUtil.buildTextField("Modalidade BC");
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

								else {
									return ComponentUtil.buildTextField(null);
								}
							}

						};
					}

					@Override
					public boolean validateItems(List<IcmsCustomizadoDetalheEntity> items) {

						return true;
					}

					protected IcmsCustomizadoDetalheEntity getNovo() {
						IcmsCustomizadoDetalheEntity icmsCustomizado = controller.novoDetalhe();
						return icmsCustomizado;
					}

					@Override
					protected void getRemoverSelecionados(List<IcmsCustomizadoDetalheEntity> values) {
						controller.removerDetalhe(values);
					}
				};

				
				sub.addTab(detalheSubForm, "Detalhes", null);

				return sub;
	}
	
	public void preencheSubForm(List<IcmsCustomizadoDetalheEntity> detalhes) {
		detalheSubForm.fillWith(detalhes);
	}


	
}
