package dc.visao.suprimentos.estoque;

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

import dc.controller.suprimentos.estoque.RequisicaoInternaFormController;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.suprimentos.RequisicaoInternaDetalheEntity;
import dc.entidade.suprimentos.estoque.RequisicaoInterna;
import dc.visao.framework.component.SubFormComponent;
import dc.visao.framework.util.ComponentUtil;

public class RequisicaoInternaFormView extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private TabSheet subForms;

	@AutoGenerated
	private TabSheet produtos;

	@AutoGenerated
	private GridLayout topFields;

	@AutoGenerated
	private GridLayout fields;

	@AutoGenerated
	private PopupDateField dataRequisicao;

	RequisicaoInternaFormController controller;

	RequisicaoInterna currentBean;

	private SubFormComponent<RequisicaoInternaDetalheEntity, Integer> requisicaoDetalheSubForm;

	public RequisicaoInternaFormView(RequisicaoInternaFormController controller) {
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
		dataRequisicao = new PopupDateField();
		dataRequisicao.setCaption("Data da Requisicao");
		dataRequisicao.setImmediate(false);
		fields.addComponent(dataRequisicao, 0, 0);

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
		// common part: create layout
		subForms = new TabSheet();
		subForms.setImmediate(true);

		requisicaoDetalheSubForm = new SubFormComponent<RequisicaoInternaDetalheEntity, Integer>(
				RequisicaoInternaDetalheEntity.class, new String[] { "produto",
						"quantidade" },
				new String[] { "Produto", "Quantidade" }) {

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
							// textField.addBlurListener(getBlurListener(container,
							// itemId, propertyId));
							return textField;
						}
						return null;
					}
				};
			}

			protected RequisicaoInternaDetalheEntity getNovo() {
				RequisicaoInternaDetalheEntity detalhe = controller
						.novoRequisicaoDetalhe();
				return detalhe;
			}

			@Override
			public boolean validateItems(
					List<RequisicaoInternaDetalheEntity> items) {

				return true;
			}
		};

		subForms.addTab(requisicaoDetalheSubForm, "Produtos", null);

		return subForms;
	}

	public PopupDateField getDataRequisicao() {
		return dataRequisicao;
	}

	public void setDataRequisicao(PopupDateField dataRequisicao) {
		this.dataRequisicao = dataRequisicao;
	}

	public void fillRequisicaoDetalhesSubForm(
			List<RequisicaoInternaDetalheEntity> requisicaoDetalhes) {
		requisicaoDetalheSubForm.fillWith(requisicaoDetalhes);
	}

	public SubFormComponent<RequisicaoInternaDetalheEntity, Integer> getRequisicaoDetalheSubForm() {
		return requisicaoDetalheSubForm;
	}

	public void setRequisicaoDetalheSubForm(
			SubFormComponent<RequisicaoInternaDetalheEntity, Integer> requisicaoDetalheSubForm) {
		this.requisicaoDetalheSubForm = requisicaoDetalheSubForm;
	}

}