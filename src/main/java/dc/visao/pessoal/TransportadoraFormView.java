package dc.visao.pessoal;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.entidade.contabilidade.ContabilContaEntity;
import dc.entidade.geral.PessoaEntity;
import dc.visao.framework.component.manytoonecombo.ManyToOneCombo;

public class TransportadoraFormView extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private GridLayout fields;
	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private TextField txtObservacao;

	private ManyToOneCombo<ContabilContaEntity> cmbContContabil;
	private ManyToOneCombo<PessoaEntity> cmbPessoa;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public TransportadoraFormView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {

		// common part: create layout
		setSizeFull();
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setSizeFull();
		mainLayout.setMargin(false);
		mainLayout.setSpacing(true);

		// top-level component properties
		setWidth("100.0%");
		// setHeight("100.0%");

		fields = buildFields();

		mainLayout.addComponent(fields);
	
		return mainLayout;
	}

	
	private GridLayout buildFields() {
		fields = new GridLayout();
		fields.setImmediate(false);
		fields.setWidth("100.0%");
		fields.setHeight("-1px");
		fields.setMargin(false);
		fields.setSpacing(true);
		fields.setRows(4);

		// cmbPessoa
		cmbPessoa = new ManyToOneCombo<>();
		cmbPessoa.setCaption("Pessoa");
		cmbPessoa.setImmediate(false);
		cmbPessoa.setWidth("640px");
		cmbPessoa.setHeight("-1px");
		cmbPessoa.setSizeFull();
		fields.addComponent(cmbPessoa, 0, 0);

		// cmbContContabil
		cmbContContabil = new ManyToOneCombo<>();
		cmbContContabil.setCaption("Conta Contábil");
		cmbContContabil.setImmediate(false);
		cmbContContabil.setWidth("640px");
		cmbContContabil.setHeight("-1px");
		cmbContContabil.setSizeFull();
		fields.addComponent(cmbContContabil, 0, 1);

		// txtObservacao
		txtObservacao = new TextField();
		txtObservacao.setCaption("Observação");
		txtObservacao.setImmediate(false);
		txtObservacao.setWidth("640px");
		txtObservacao.setHeight("-1px");
		txtObservacao.setSizeFull();
		fields.addComponent(txtObservacao, 0, 2);

		return fields;
	}

	public GridLayout getMainLayout() {
		return fields;
	}

	public void setMainLayout(GridLayout fields) {
		this.fields = fields;
	}

	public VerticalLayout getVerticalLayout_1() {
		return mainLayout;
	}

	public void setVerticalLayout_1(VerticalLayout mainLayout) {
		this.mainLayout = mainLayout;
	}

	public TextField getTxtObservacao() {
		return txtObservacao;
	}

	public void setTxtObservacao(TextField txtObservacao) {
		this.txtObservacao = txtObservacao;
	}

	public ManyToOneCombo<ContabilContaEntity> getCmbContContabil() {
		return cmbContContabil;
	}

	public void setCmbContContabil(ManyToOneCombo<ContabilContaEntity> cmbContContabil) {
		this.cmbContContabil = cmbContContabil;
	}

	public ManyToOneCombo<PessoaEntity> getCmbPessoa() {
		return cmbPessoa;
	}

	public void setCmbPessoa(ManyToOneCombo<PessoaEntity> cmbPessoa) {
		this.cmbPessoa = cmbPessoa;
	}

}