package dc.visao.financeiro;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

import dc.entidade.financeiro.ParcelaPagar;
import dc.visao.framework.util.ComponentUtil;

public class ParcelaPagarFormView extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private GridLayout mainLayout;
	private TextArea txtDescricao;
	private TextField txtCodigo;
	private TextField txtSiglaDocumento;

	public ParcelaPagarFormView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
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

		txtCodigo = ComponentUtil.builNumericField("Codigo");
		mainLayout.addComponent(txtCodigo);
		txtCodigo.setWidth("-1px");
		txtCodigo.setMaxLength(4);

		txtSiglaDocumento = ComponentUtil.buildTextField("Sigla Documento");
		mainLayout.addComponent(txtSiglaDocumento);
		txtSiglaDocumento.setWidth("-1px");

		// txtDescricao
		txtDescricao = ComponentUtil.buildTextArea("Descrição");
		mainLayout.addComponent(txtDescricao);

		return mainLayout;
	}

	public GridLayout getMainLayout() {
		return mainLayout;
	}

	public void setMainLayout(GridLayout mainLayout) {
		this.mainLayout = mainLayout;
	}

	public TextArea getTxtDescricao() {
		return txtDescricao;
	}

	public void setTxtDescricao(TextArea txtDescricao) {
		this.txtDescricao = txtDescricao;
	}

	public TextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(TextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public TextField getTxtSiglaDocumento() {
		return txtSiglaDocumento;
	}

	public void setTxtSiglaDocumento(TextField txtSiglaDocumento) {
		this.txtSiglaDocumento = txtSiglaDocumento;
	}

	public void preencheBean(ParcelaPagar currentBean) {
		//currentBean.setDescricao(getTxtDescricao().getValue());
		
	}

	public void preencheForm(ParcelaPagar currentBean) {
		//getTxtDescricao().setValue(currentBean.getDescricao());
		
	}
}