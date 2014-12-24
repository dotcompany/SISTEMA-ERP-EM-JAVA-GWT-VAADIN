package dc.visao.ordemservico;

import java.util.List;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Container;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.ordemservico.OrdemServicoFormController;
import dc.entidade.ordemservico.VendaPecaEntity;
import dc.visao.framework.component.SubFormComponent;
import dc.visao.framework.util.ComponentUtil;

public class VendaProdutoFormView extends CustomComponent {

	private static final long serialVersionUID = 1L;
	
	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private GridLayout fields;

	@AutoGenerated
	private TabSheet tabSheet;
	
	private OrdemServicoFormController controller;

	private SubFormComponent<VendaPecaEntity, Integer> vendaPecaSubForm;

	public VendaProdutoFormView(OrdemServicoFormController controller) {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		this.controller = controller;
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		setSizeFull();
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setSizeFull();
		mainLayout.setMargin(false);
		mainLayout.setSpacing(true);

		// top-level component properties
		setWidth("100.0%");
		// setHeight("100.0%");

		// subForm
		tabSheet = BuildTabs();
		mainLayout.addComponent(tabSheet);
		mainLayout.setExpandRatio(tabSheet, 1);

		return mainLayout;
	}

	private TabSheet BuildTabs() {
		tabSheet = new TabSheet();
		tabSheet.setImmediate(true);
		tabSheet.setSizeFull();

		tabSheet.addTab(buildSubFormVendaPeca(), "Venda de peça", null);

		return tabSheet;
	}

	
	private Component buildSubFormVendaPeca() {
		String[] atributos = new String[] {"vendedor.pessoa.nome", "tecnico.pessoa.nome","produto.descricao","produto.unidade","tipo","quantidadeServico","valorUnitario","valorCobrado","percentualDesconto","valorDesconto","valorTotal"};
		String[] headers = new String[] {"Nome vendedor", "Técnico", "Descrição produto","Tipo", "Qtd", "Valor unitário", "Valor cobrado", "Desconot %", "Valor desconto", "Valor Total"};
		
		this.vendaPecaSubForm = new SubFormComponent<VendaPecaEntity, Integer>(VendaPecaEntity.class, atributos, headers) {

			private static final long serialVersionUID = 1L;

			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {

						if ("produto.descricao".equals(propertyId)) {
							TextField textField = ComponentUtil.buildNumberField(null);
							return textField;
						} else if ("valorUnitario".equals(propertyId)) {
							return ComponentUtil.buildCurrencyField(null);
						}else {
							return ComponentUtil.buildTextField(null);
						}
					}

				};
			}

			@Override
			public boolean validateItems(List<VendaPecaEntity> items) {

				return true;
			}

			protected VendaPecaEntity getNovo() {
//				VendaPeca vendaPeca = controller.novoVendaPeca();
//				return vendaPeca;
				return null;
			}
	
			protected void getRemoverSelecionados(List<VendaPecaEntity> values) {
//				controller.removerVendaPecaItem(values);
			}
		};

		return this.vendaPecaSubForm;
	}
	
}
