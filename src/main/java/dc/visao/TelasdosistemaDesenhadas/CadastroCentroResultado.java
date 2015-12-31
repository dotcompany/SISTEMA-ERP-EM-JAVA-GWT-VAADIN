package dc.visao.TelasdosistemaDesenhadas;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;

public class CadastroCentroResultado extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private GridLayout mainLayout;
	@AutoGenerated
	private TextField txtPercentualRateio;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;
	@AutoGenerated
	private TextField txtDescricao;
	@AutoGenerated
	private OptionGroup optionGroup_1;
	@AutoGenerated
	private ComboBox comboBox_2;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public CadastroCentroResultado() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
	}

	@AutoGenerated
	private GridLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new GridLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		mainLayout.setRows(5);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("-1px");
		
		// comboBox_2
		comboBox_2 = new ComboBox();
		comboBox_2.setCaption("Plano Centro Resultado");
		comboBox_2.setImmediate(false);
		comboBox_2.setWidth("-1px");
		comboBox_2.setHeight("-1px");
		mainLayout.addComponent(comboBox_2, 0, 0);
		
		// optionGroup_1
		optionGroup_1 = new OptionGroup();
		optionGroup_1.setCaption("Sofre Rateio");
		optionGroup_1.setImmediate(false);
		optionGroup_1.setWidth("-1px");
		optionGroup_1.setHeight("-1px");
		mainLayout.addComponent(optionGroup_1, 0, 1);
		
		// horizontalLayout_1
		horizontalLayout_1 = buildHorizontalLayout_1();
		mainLayout.addComponent(horizontalLayout_1, 0, 2);
		
		// txtPercentualRateio
		txtPercentualRateio = new TextField();
		txtPercentualRateio.setCaption("Porcentagem do Rateio");
		txtPercentualRateio.setImmediate(false);
		txtPercentualRateio.setWidth("-1px");
		txtPercentualRateio.setHeight("-1px");
		mainLayout.addComponent(txtPercentualRateio, 0, 3);
		
		return mainLayout;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_1() {
		// common part: create layout
		horizontalLayout_1 = new HorizontalLayout();
		horizontalLayout_1.setImmediate(false);
		horizontalLayout_1.setWidth("-1px");
		horizontalLayout_1.setHeight("100.0%");
		horizontalLayout_1.setMargin(false);
		horizontalLayout_1.setSpacing(true);
		
		// txtDescricao
		txtDescricao = new TextField();
		txtDescricao.setCaption("Descrição");
		txtDescricao.setImmediate(false);
		txtDescricao.setWidth("-1px");
		txtDescricao.setHeight("-1px");
		horizontalLayout_1.addComponent(txtDescricao);
		
		return horizontalLayout_1;
	}

}
