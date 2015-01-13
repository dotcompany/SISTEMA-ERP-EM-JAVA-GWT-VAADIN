package dc.visao.suprimento.estoque;

import java.util.List;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.suprimento.estoque.ContagemFormController;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.suprimentos.estoque.ContagemDetalheEntity;
import dc.visao.framework.component.SubFormComponent;
import dc.visao.framework.util.ComponentUtil;

public class ContagemFormView extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private GridLayout glGeral;

	@AutoGenerated
	private TabSheet tsGeral;

	@AutoGenerated
	private VerticalLayout vlProduto;

	@AutoGenerated
	private Panel plProduto;

	@AutoGenerated
	private PopupDateField pdfDataContagem;

	private SubFormComponent<ContagemDetalheEntity, Integer> sfContagemDetalhe;

	private ContagemFormController controller;

	public ContagemFormView(ContagemFormController controller) {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		this.controller = controller;
	}

	public PopupDateField getPdfDataContagem() {
		return pdfDataContagem;
	}

	public void setPdfDataContagem(PopupDateField pdfDataContagem) {
		this.pdfDataContagem = pdfDataContagem;
	}

	public SubFormComponent<ContagemDetalheEntity, Integer> getSfContagemDetalhe() {
		return sfContagemDetalhe;
	}

	public void setSfContagemDetalhe(
			SubFormComponent<ContagemDetalheEntity, Integer> sfContagemDetalhe) {
		this.sfContagemDetalhe = sfContagemDetalhe;
	}

	@AutoGenerated
	private void buildMainLayout() {
		// the main layout and components will be created here
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

		tsGeral = new TabSheet();
		tsGeral.setImmediate(true);
		tsGeral.setSizeFull();

		tsGeral.addTab(bvlProduto(), 0);

		mainLayout.addComponent(tsGeral);
		mainLayout.setExpandRatio(tsGeral, 1);
	}

	@AutoGenerated
	private GridLayout bglGeral() {
		// common part: create layout
		glGeral = new GridLayout();
		glGeral.setImmediate(false);
		glGeral.setWidth("100.0%");
		glGeral.setHeight("-1px");
		glGeral.setMargin(false);
		glGeral.setSpacing(true);
		glGeral.setRows(6);
		glGeral.setColumns(1);

		// pdfDataContagem
		pdfDataContagem = new PopupDateField();
		pdfDataContagem.setCaption("Data da contagem");
		pdfDataContagem.setRequired(true);
		pdfDataContagem.setImmediate(false);
		glGeral.addComponent(pdfDataContagem, 0, 0);

		return glGeral;
	}

	@AutoGenerated
	private VerticalLayout bvlProduto() {
		// common part: create layout
		vlProduto = new VerticalLayout();
		vlProduto.setImmediate(false);
		vlProduto.setWidth("100.0%");
		vlProduto.setHeight("100.0%");
		vlProduto.setMargin(true);
		vlProduto.setSpacing(true);
		vlProduto.setCaption("Produto");

		//
		vlProduto.addComponent(bplProduto());

		return vlProduto;
	}

	@AutoGenerated
	private Panel bplProduto() {
		// common part: create layout
		plProduto = new Panel();
		plProduto.setImmediate(false);
		plProduto.setSizeFull();

		plProduto.setContent(buildProdutoSubForm());

		return plProduto;
	}

	@AutoGenerated
	private SubFormComponent<ContagemDetalheEntity, Integer> buildProdutoSubForm() {
		sfContagemDetalhe = new SubFormComponent<ContagemDetalheEntity, Integer>(
				ContagemDetalheEntity.class, new String[] { "produto",
						"quantidadeSistema", "quantidadeContada",
						"acuracidade", "divergencia" }, new String[] {
						"Produto", "Quantidade no sistema",
						"Quantidade contada", "Acuracidade", "Divergência" }) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public Field<?> createField(Container container,
							Object itemId, Object propertyId,
							Component uiContext) {

						if ("produto".equals(propertyId)) {
							ComboBox cbProduto = ComponentUtil
									.buildComboBox(null);

							BeanItemContainer<ProdutoEntity> bic = controller
									.getProdutoBic();
							cbProduto.setContainerDataSource(bic);
							cbProduto.setItemCaptionPropertyId("nome");

							return cbProduto;
						}

						if ("quantidadeContada".equals(propertyId)) {
							TextField tfQuantidadeContada = ComponentUtil
									.buildNumberField(null);

							return tfQuantidadeContada;
						}

						if ("quantidadeSistema".equals(propertyId)) {
							TextField tfQuantidadeSistema = ComponentUtil
									.buildNumberField(null);

							return tfQuantidadeSistema;
						}

						if ("acuracidade".equals(propertyId)) {
							TextField tfAcuracidade = ComponentUtil
									.buildNumberField(null);

							return tfAcuracidade;
						}

						if ("divergencia".equals(propertyId)) {
							TextField tfDivergencia = ComponentUtil
									.buildNumberField(null);

							return tfDivergencia;
						}

						return null;
					}

				};
			}

			@Override
			public boolean validateItems(List<ContagemDetalheEntity> items) {
				// TODO Auto-generated method stub
				return true;
			}

			protected ContagemDetalheEntity getNovo() {
				ContagemDetalheEntity contagemDetalhe = controller
						.adicionarContagemDetalhe();

				return contagemDetalhe;
			}

			@Override
			protected void getRemoverSelecionados(
					List<ContagemDetalheEntity> values) {
				// TODO Auto-generated method stub
				controller.removerContagemDetalhe(values);
			}

		};

		return sfContagemDetalhe;

	}

}