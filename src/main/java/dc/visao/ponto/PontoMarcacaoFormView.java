package dc.visao.ponto;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.visao.framework.util.ComponentUtil;

public class PontoMarcacaoFormView extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private GridLayout gridLayout_1;
	@AutoGenerated
	private TextField txUsuario;
	@AutoGenerated
	private PasswordField pwSenha;
	@AutoGenerated
	private Button btnMarcar;
	@AutoGenerated
	private Label lblDataHora;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public PontoMarcacaoFormView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
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

		// gridLayout_1
		gridLayout_1 = buildGridLayout_1();
		mainLayout.addComponent(gridLayout_1);

		return mainLayout;
	}

	@AutoGenerated
	private GridLayout buildGridLayout_1() {
		// common part: create layout
		gridLayout_1 = new GridLayout();
		gridLayout_1.setImmediate(false);
		gridLayout_1.setSpacing(true);
		gridLayout_1.setWidth("50%");
		gridLayout_1.setColumns(1);
		gridLayout_1.setRows(4);

		lblDataHora = new Label();
		lblDataHora.setImmediate(true);
		lblDataHora.setSizeFull();
		gridLayout_1.addComponent(lblDataHora, 0, 0);

		txUsuario = ComponentUtil.buildTextField("Usuário");
		gridLayout_1.addComponent(txUsuario, 0, 1);

		pwSenha = ComponentUtil.buildPasswordField("Senha");
		gridLayout_1.addComponent(pwSenha, 0, 2);

		btnMarcar = new Button("Marcar");
		btnMarcar.setImmediate(true);
		gridLayout_1.addComponent(btnMarcar, 0, 3);

		return gridLayout_1;
	}

	public TextField getTxUsuario() {
		return txUsuario;
	}

	public void setTxUsuario(TextField txUsuario) {
		this.txUsuario = txUsuario;
	}

	public PasswordField getPwSenha() {
		return pwSenha;
	}

	public void setPwSenha(PasswordField pwSenha) {
		this.pwSenha = pwSenha;
	}

	public Button getBtnMarcar() {
		return btnMarcar;
	}

	public void setBtnMarcar(Button btnMarcar) {
		this.btnMarcar = btnMarcar;
	}

	public Label getLblDataHora() {
		return lblDataHora;
	}

	public void setLblDataHora(Label lblDataHora) {
		this.lblDataHora = lblDataHora;
	}

}
