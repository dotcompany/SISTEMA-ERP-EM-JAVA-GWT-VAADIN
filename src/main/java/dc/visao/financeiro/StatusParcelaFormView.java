package dc.visao.financeiro;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

import dc.entidade.financeiro.StatusParcela;
import dc.visao.framework.util.ComponentUtil;

public class StatusParcelaFormView extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private GridLayout mainLayout;
	private TextField txtDescricao;
	private TextField txtSituacao;
	private TextField txtProcedimento;

	public StatusParcelaFormView() {
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
		mainLayout.setRows(2);
		mainLayout.setColumns(2);

		// top-level component properties
		setWidth("100.0%");
		setHeight("-1px");

		txtSituacao = ComponentUtil.buildTextField("Situação");
		mainLayout.addComponent(txtSituacao, 0, 0);
		txtSituacao.setMaxLength(2);
		txtSituacao.setWidth("50px");

		// txtDescricao
		txtDescricao = ComponentUtil.buildTextField("Descrição");
		mainLayout.addComponent(txtDescricao, 1, 0);

		txtProcedimento = ComponentUtil.buildTextField("Procedimento");
		mainLayout.addComponent(txtProcedimento, 0, 1, 1, 1);

		return mainLayout;
	}

	public void preencheBean(StatusParcela currentBean) {
		currentBean.setDescricao(getTxtDescricao().getValue());
		currentBean.setProcedimento(getTxtProcedimento().getValue());
		currentBean.setSituacao(getTxtSituacao().getValue());
	}

	public void preencheForm(StatusParcela currentBean) {
		getTxtDescricao().setValue(currentBean.getDescricao());
		getTxtProcedimento().setValue(currentBean.getProcedimento());
		getTxtSituacao().setValue(currentBean.getSituacao());
	}

	public TextField getTxtDescricao() {
		return txtDescricao;
	}

	public void setTxtDescricao(TextField txtDescricao) {
		this.txtDescricao = txtDescricao;
	}

	public TextField getTxtSituacao() {
		return txtSituacao;
	}

	public void setTxtSituacao(TextField txtSituacao) {
		this.txtSituacao = txtSituacao;
	}

	public TextField getTxtProcedimento() {
		return txtProcedimento;
	}

	public void setTxtProcedimento(TextField txtProcedimento) {
		this.txtProcedimento = txtProcedimento;
	}
}