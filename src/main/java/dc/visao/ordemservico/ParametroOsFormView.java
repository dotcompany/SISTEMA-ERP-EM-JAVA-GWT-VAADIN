package dc.visao.ordemservico;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.ordemservico.ParametroOsFormController;
import dc.visao.framework.util.ComponentUtil;

public class ParametroOsFormView extends CustomComponent {

	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private GridLayout fields;
	@AutoGenerated
	private TabSheet subForms;
	@AutoGenerated
	private OptionGroup optBaixaProduto;
	
	private ParametroOsFormController controller;
	
	
	@AutoGenerated
	private CheckBox cbLimparBD,cbVendedorProduto,cbValorPagoPeca,cbDesconto,cbTecnicoProduto;

	private CheckBox cbVendedorAtendente,cbVendedorServico,cbOsSimplificada,cbProximaRevisao;

	private TextField tfMatricialLinhas,tfEntrelinhas,tfDiasPadrao;
	
	private TextArea taDefeitoApresentado, taObsPadrao, taObsPadraoSimpes;

	public ParametroOsFormView(ParametroOsFormController controller) {
		this.controller = controller;
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}
	
	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		setSizeFull();
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setSizeFull();
		mainLayout.setMargin(false);
		mainLayout.setSpacing(true);
		setWidth("100.0%");
		
		// common part: create layout
		subForms = new TabSheet();
		subForms.setWidth("100.0%");
		subForms.setHeight("100.0%");
		subForms.setSizeFull();
		subForms.setImmediate(true);
		mainLayout.addComponent(subForms);

		buildAbaOrdemServico();

		return mainLayout;
	}

	public void buildAbaOrdemServico() {

		GridLayout gridLayout_1 = new GridLayout(7, 7);
		gridLayout_1.setImmediate(false);
		gridLayout_1.setWidth("100.0%");
		gridLayout_1.setMargin(true);
		gridLayout_1.setSpacing(true);
		gridLayout_1.setRows(8);
		gridLayout_1.setColumns(6);

		cbLimparBD = ComponentUtil.buildCheckBox("Limpar banco de dados automaticamente");
		gridLayout_1.addComponent(cbLimparBD,0,1,1,1);

		cbVendedorAtendente = ComponentUtil.buildCheckBox("Usar vendedor\\atendente");
		gridLayout_1.addComponent(cbVendedorAtendente,2,1,3,1);

		cbVendedorProduto= ComponentUtil.buildCheckBox("Usar vendedor no lançamento de produto");
		gridLayout_1.addComponent(cbVendedorProduto,4,1,5,1);

		cbValorPagoPeca= ComponentUtil.buildCheckBox("Usar campo valor pago na peça\\produto");
		gridLayout_1.addComponent(cbValorPagoPeca,0,2,1,2);

		cbDesconto= ComponentUtil.buildCheckBox("Usar desconto geral");
		gridLayout_1.addComponent(cbDesconto,2,2,3,2);

		cbTecnicoProduto= ComponentUtil.buildCheckBox("Usar técnico no lançamento de produto");
		gridLayout_1.addComponent(cbTecnicoProduto,4,2,5,2);

		cbVendedorServico= ComponentUtil.buildCheckBox("Usar vendedor no lançamento de seviço");
		gridLayout_1.addComponent(cbVendedorServico,0,3,1,3);

		cbProximaRevisao = ComponentUtil.buildCheckBox("Perguntar quantidade de dias próxima revisão");
		gridLayout_1.addComponent(cbProximaRevisao,2,3,3,3);

		tfDiasPadrao = ComponentUtil.buildNumericField("Quantidade dias padrão");
		gridLayout_1.addComponent(tfDiasPadrao,4,3,5,3);

		// Baixa de produto no estoque
		optBaixaProduto = new OptionGroup();
		optBaixaProduto.setStyleName("horizontal");
		optBaixaProduto.setCaption("Baixar produto no estoque");
		optBaixaProduto.setImmediate(false);
		optBaixaProduto.setWidth("-1px");
		optBaixaProduto.setHeight("-1px");
		optBaixaProduto.addItem("1");
		optBaixaProduto.setItemCaption("1", "No lançamento do produto");
		optBaixaProduto.addItem("2");
		optBaixaProduto.setItemCaption("2", "Na efetivação da O.S");
		gridLayout_1.addComponent(optBaixaProduto,0,5,4,5);

		taDefeitoApresentado = ComponentUtil.buildTextArea("Obs. defeito apresentado padrão");
		gridLayout_1.addComponent(taDefeitoApresentado,0,6,1,7);

		taObsPadrao = ComponentUtil.buildTextArea("Obs. padrão");
		gridLayout_1.addComponent(taObsPadrao,2,6,3,7);

		taObsPadraoSimpes = ComponentUtil.buildTextArea("Obs. padrão na O.S simples");
		gridLayout_1.addComponent(taObsPadraoSimpes,4,6,5,7);

		subForms.addTab(gridLayout_1, "Ordem serviço", null);
	}

	/*public enum SimNao {

		SIM("Sim", true), NAO("Não", false);

		private SimNao(String label, Boolean valor) {
			this.label = label;
			this.valor = valor;
		}

		private String label;
		private Boolean valor;

		public static SimNao getSimNao(Boolean valor) {
			for (SimNao e : SimNao.values()) {
				if (e.getValor().equals(valor)) {
					return e;
				}
			}

			return null;
		}

		public Boolean getValor() {
			return valor;
		}

		public String getLabel() {
			return label;
		}

		
		@Override
		public String toString() {
			return label;
		}
	}*/

	public OptionGroup getOptBaixaProduto() {
		return optBaixaProduto;
	}

	public void setOptBaixaProduto(OptionGroup optBaixaProduto) {
		this.optBaixaProduto = optBaixaProduto;
	}

	public CheckBox getCbLimparBD() {
		return cbLimparBD;
	}

	public void setCbLimparBD(CheckBox cbLimparBD) {
		this.cbLimparBD = cbLimparBD;
	}

	public CheckBox getCbVendedorProduto() {
		return cbVendedorProduto;
	}

	public void setCbVendedorProduto(CheckBox cbVendedorProduto) {
		this.cbVendedorProduto = cbVendedorProduto;
	}

	public CheckBox getCbValorPagoPeca() {
		return cbValorPagoPeca;
	}

	public void setCbValorPagoPeca(CheckBox cbValorPagoPeca) {
		this.cbValorPagoPeca = cbValorPagoPeca;
	}

	public CheckBox getCbDesconto() {
		return cbDesconto;
	}

	public void setCbDesconto(CheckBox cbDesconto) {
		this.cbDesconto = cbDesconto;
	}

	public CheckBox getCbTecnicoProduto() {
		return cbTecnicoProduto;
	}

	public void setCbTecnicoProduto(CheckBox cbTecnicoProduto) {
		this.cbTecnicoProduto = cbTecnicoProduto;
	}

	public CheckBox getCbVendedorAtendente() {
		return cbVendedorAtendente;
	}

	public void setCbVendedorAtendente(CheckBox cbVendedorAtendente) {
		this.cbVendedorAtendente = cbVendedorAtendente;
	}

	public CheckBox getCbVendedorServico() {
		return cbVendedorServico;
	}

	public void setCbVendedorServico(CheckBox cbVendedorServico) {
		this.cbVendedorServico = cbVendedorServico;
	}

	public CheckBox getCbOsSimplificada() {
		return cbOsSimplificada;
	}

	public void setCbOsSimplificada(CheckBox cbOsSimplificada) {
		this.cbOsSimplificada = cbOsSimplificada;
	}

	public CheckBox getCbProximaRevisao() {
		return cbProximaRevisao;
	}

	public void setCbProximaRevisao(CheckBox cbProximaRevisao) {
		this.cbProximaRevisao = cbProximaRevisao;
	}

	public TextField getTfMatricialLinhas() {
		return tfMatricialLinhas;
	}

	public void setTfMatricialLinhas(TextField tfMatricialLinhas) {
		this.tfMatricialLinhas = tfMatricialLinhas;
	}

	public TextField getTfEntrelinhas() {
		return tfEntrelinhas;
	}

	public void setTfEntrelinhas(TextField tfEntrelinhas) {
		this.tfEntrelinhas = tfEntrelinhas;
	}

	public TextField getTfDiasPadrao() {
		return tfDiasPadrao;
	}

	public void setTfDiasPadrao(TextField tfDiasPadrao) {
		this.tfDiasPadrao = tfDiasPadrao;
	}

	public TextArea getTaDefeitoApresentado() {
		return taDefeitoApresentado;
	}

	public void setTaDefeitoApresentado(TextArea taDefeitoApresentado) {
		this.taDefeitoApresentado = taDefeitoApresentado;
	}

	public TextArea getTaObsPadrao() {
		return taObsPadrao;
	}

	public void setTaObsPadrao(TextArea taObsPadrao) {
		this.taObsPadrao = taObsPadrao;
	}

	public TextArea getTaObsPadraoSimpes() {
		return taObsPadraoSimpes;
	}

	public void setTaObsPadraoSimpes(TextArea taObsPadraoSimpes) {
		this.taObsPadraoSimpes = taObsPadraoSimpes;
	}

}