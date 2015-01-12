package dc.visao.suprimentos.compra;

import java.util.List;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.suprimentos.compra.ContagemCabecalhoFormController;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.suprimentos.estoque.ContagemCabecalhoEntity;
import dc.entidade.suprimentos.estoque.ContagemDetalheEntity;
import dc.visao.framework.component.SubFormComponent;
import dc.visao.framework.util.ComponentUtil;

public class ContagemEstoqueFormView extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private GridLayout fields;

	@AutoGenerated
	private GridLayout topFields;

	@AutoGenerated
	private PopupDateField dataContagem;

	private SubFormComponent<ContagemDetalheEntity, Integer> contagemEstoqueDetalheSubForm;

	ContagemCabecalhoFormController controller;

	ContagemCabecalhoEntity currentBean;

	@AutoGenerated
	private TabSheet subForms;

	@AutoGenerated
	private TabSheet produtos;

	public ContagemEstoqueFormView(ContagemCabecalhoFormController controller) {
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

		// calDataRequisicao
		dataContagem = new PopupDateField();
		dataContagem.setCaption("Data Contagem");
		dataContagem.setImmediate(false);
		fields.addComponent(dataContagem, 0, 0);

		return fields;
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setSizeFull();
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		topFields = new GridLayout(6, 1);
		topFields.setImmediate(false);
		topFields.setSizeFull();
		topFields.setSpacing(true);

		mainLayout.addComponent(topFields);

		// fields
		fields = buildFields();
		mainLayout.addComponent(fields);

		subForms = buildSubForms();
		mainLayout.addComponent(subForms);

		subForms.setHeight("100%");
		mainLayout.setExpandRatio(subForms, 1);

		// subForm
		// mainLayout.addComponent(buildSubForms());

		return mainLayout;
	}

	@AutoGenerated
	private TabSheet buildSubForms() {

		subForms = new TabSheet();
		subForms.setImmediate(true);

		contagemEstoqueDetalheSubForm = new SubFormComponent<ContagemDetalheEntity, Integer>(
				ContagemDetalheEntity.class, new String[] { "produto",
						"quantidadeSistema", "quantidadeContada",
						"acuracidade", "divergencia" }, new String[] {
						"Produto", "Quantidade Sistema", "Quantidade Contada",
						"Acuracidade", "Divergência" }) {
			// "produto" }, new String[] {"Produto" }) {

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
						}

						if ("quantidadeContada".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildNumberField(null);
							return textField;
						}

						if ("quantidadeSistema".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildNumberField(null);
							return textField;
						}

						if ("acuracidade".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildNumberField(null);
							return textField;
						}

						if ("divergencia".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildNumberField(null);
							return textField;
						}
						return ComponentUtil.buildTextField(null);
					}
				};
			}

			protected ContagemDetalheEntity getNovo() {
				ContagemDetalheEntity contagemEstoqueDetalhe = controller
						.novoContagemEstoqueDetalhe();
				return contagemEstoqueDetalhe;
			}

			@Override
			public boolean validateItems(List<ContagemDetalheEntity> items) {
				// TODO Auto-generated method stub
				return true;
			}
		};
		subForms.addTab(contagemEstoqueDetalheSubForm, "Produtos", null);

		return subForms;

	}

	/*
	 * public ContagemEstoqueDetalhe novoContagemEstoqueDetalhe() {
	 * ContagemEstoqueDetalhe contagemEstoqueDetalhe = new
	 * ContagemEstoqueDetalhe();
	 * currentBean.addContagemDetalhe(contagemEstoqueDetalhe); return
	 * contagemEstoqueDetalhe; }
	 */

	public void fillContagemEstoqueDetalhesSubForm(
			List<ContagemDetalheEntity> contagemEstoqueDetalhes) {
		contagemEstoqueDetalheSubForm.fillWith(contagemEstoqueDetalhes);
	}

	public PopupDateField getDataContagem() {
		return dataContagem;
	}

	public VerticalLayout getMainLayout() {
		return mainLayout;
	}

	public void setDataContagem(PopupDateField dataContagem) {
		this.dataContagem = dataContagem;
	}

	public GridLayout getFields() {
		return fields;
	}

	public void setFields(GridLayout fields) {
		this.fields = fields;
	}

	public void setMainLayout(VerticalLayout mainLayout) {
		this.mainLayout = mainLayout;
	}

	public SubFormComponent<ContagemDetalheEntity, Integer> getContagemEstoqueDetalheSubForm() {
		return contagemEstoqueDetalheSubForm;
	}

	public void setContagemEstoqueDetalheSubForm(
			SubFormComponent<ContagemDetalheEntity, Integer> contagemEstoqueDetalheSubForm) {
		this.contagemEstoqueDetalheSubForm = contagemEstoqueDetalheSubForm;
	}

}