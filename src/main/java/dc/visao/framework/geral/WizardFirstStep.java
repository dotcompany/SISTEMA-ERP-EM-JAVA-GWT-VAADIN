package dc.visao.framework.geral;

import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.VerticalLayout;

import dc.entidade.geral.Usuario;
import dc.visao.spring.SecuritySessionProvider;

public class WizardFirstStep extends BaseWizardStep{
	
	private OptionGroup group;
	private ConfiguraNovaContaController controller;

	public WizardFirstStep(ConfiguraNovaContaController controller) {
		// TODO Auto-generated constructor stub
		this.controller = controller;
	}

	@Override
	protected void fillMainPanel(VerticalLayout mainPanel) {
		Label question = new Label("Que tipo de notas fiscais eletr�nicas (NF-e) Você precisa emitir?");
        question.setSizeUndefined();
        question.addStyleName("h4");
        
        mainPanel.addComponent(question);
        
        group = new OptionGroup("");
         
        group.addItem("Automação Comercial");
        group.addItem("Lojas");
        group.addItem("Distribuidora");
        group.addItem("Com�rcio e serviço");
        group.addItem("serviço");
        group.addItem("Confecção");
        group.addItem("Industria");
        group.addItem("Industria e com�rcio");
        group.addItem("Centro automotivo");
        group.addItem("Livraria");
        group.addItem("�ticas");
        group.addItem("Moto Pe�as");
        group.addItem("Conserto de Ve�culos pesados");
        group.addItem("Personalizado");
        group.addItem("Locação");
        group.addItem("Outros");
        mainPanel.addComponent(group);
		
	}

	@Override
	protected boolean validate() {
		System.out.println(group.getValue());
		return group.getValue() != null;
	}

	@Override
	public String getCaption() {
		return "Determine seu tipo de atividade";
	}

	@Override
	protected void saveConfiguration() {
		Usuario u = SecuritySessionProvider.getUsuario();
		Integer contaId = u.getConta().getId();
		controller.salvarPrimeiraPergunta(group.getValue(),contaId);
		
	}

}
