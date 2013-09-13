package dc.visao.contratos;

import java.math.BigDecimal;
import java.util.List;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Container;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

import dc.controller.contratos.ContratoFormController;
import dc.entidade.contabilidade.ContabilConta;
import dc.entidade.contratos.Contrato;
import dc.entidade.contratos.ContratoHistFaturamento;
import dc.entidade.contratos.ContratoHistoricoReajuste;
import dc.entidade.contratos.ContratoPrevFaturamento;
import dc.entidade.contratos.ContratoSolicitacaoServico;
import dc.entidade.contratos.TipoContrato;
import dc.entidade.ged.Documento;
import dc.framework.StringToBigDecimalConverter;
import dc.visao.framework.component.SubFormComponent;

@SuppressWarnings("serial")
public class ContratoFormView extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private TabSheet tabSheet_2;
	@AutoGenerated
	private AbsoluteLayout absoluteLayout_6;
	@AutoGenerated
	private AbsoluteLayout absoluteLayout_4;
	@AutoGenerated
	private AbsoluteLayout absoluteLayout_3;
	@AutoGenerated
	private Table table_2;
	@AutoGenerated
	private TabSheet tabSheet_1;
	@AutoGenerated
	private AbsoluteLayout absoluteLayout_2;
	@AutoGenerated
	private TextArea txaObservacoes;
	@AutoGenerated
	private TextArea txaDescricao;
	@AutoGenerated
	private TextField txtIntervaloParcelas;
	@AutoGenerated
	private TextField txtQuantidadeParcelas;
	@AutoGenerated
	private TextField txtValor;
	@AutoGenerated
	private TextField txtDiaFaturamento;
	@AutoGenerated
	private PopupDateField dtFimVigencia;
	@AutoGenerated
	private PopupDateField dtVigencia;
	@AutoGenerated
	private PopupDateField dtCadastro;
	@AutoGenerated
	private AbsoluteLayout absoluteLayout_5;
	@AutoGenerated
	private ComboBox cmbSolicitacaoServico;
	@AutoGenerated
	private TextField txtNome;
	@AutoGenerated
	private TextField txtNumero;
	@AutoGenerated
	private ComboBox cbmContabilConta;
	@AutoGenerated
	private ComboBox cbmTipoContrato;
	@AutoGenerated
	private ComboBox cbmDocumento;
	@AutoGenerated
	private SubFormComponent<ContratoHistFaturamento, Integer> historicoFaturamentoSubForm;
	@AutoGenerated
	private SubFormComponent<ContratoHistoricoReajuste, Integer> historicoReajustesSubForm;
	@AutoGenerated
	private SubFormComponent<ContratoPrevFaturamento, Integer> previsaoFaturamentoSubForm;
	@AutoGenerated
	private Button btnGerarContrato;

	private ContratoFormController controller;

	public ContratoFormView(ContratoFormController contratoFormController) {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		this.controller = contratoFormController;
	}

	public AbsoluteLayout getMainLayout() {
		return mainLayout;
	}

	public void setMainLayout(AbsoluteLayout mainLayout) {
		this.mainLayout = mainLayout;
	}

	public Table getTable_2() {
		return table_2;
	}

	public void setTable_2(Table table_2) {
		this.table_2 = table_2;
	}

	public TextArea getTxaObservacoes() {
		return txaObservacoes;
	}

	public void setTxaObservacoes(TextArea txaObservacoes) {
		this.txaObservacoes = txaObservacoes;
	}

	public TextArea getTxaDescricao() {
		return txaDescricao;
	}

	public void setTxaDescricao(TextArea txaDescricao) {
		this.txaDescricao = txaDescricao;
	}

	public TextField getTxtIntervaloParcelas() {
		return txtIntervaloParcelas;
	}

	public void setTxtIntervaloParcelas(TextField txtIntervaloParcelas) {
		this.txtIntervaloParcelas = txtIntervaloParcelas;
	}

	public TextField getTxtQuantidadeParcelas() {
		return txtQuantidadeParcelas;
	}

	public void setTxtQuantidadeParcelas(TextField txtQuantidadeParcelas) {
		this.txtQuantidadeParcelas = txtQuantidadeParcelas;
	}

	public TextField getTxtValor() {
		return txtValor;
	}

	public void setTxtValor(TextField txtValor) {
		this.txtValor = txtValor;
	}

	public TextField getTxtDiaFaturamento() {
		return txtDiaFaturamento;
	}

	public void setTxtDiaFaturamento(TextField txtDiaFaturamento) {
		this.txtDiaFaturamento = txtDiaFaturamento;
	}

	public PopupDateField getDtFimVigencia() {
		return dtFimVigencia;
	}

	public void setDtFimVigencia(PopupDateField dtFimVigencia) {
		this.dtFimVigencia = dtFimVigencia;
	}

	public PopupDateField getDtVigencia() {
		return dtVigencia;
	}

	public void setDtVigencia(PopupDateField dtVigencia) {
		this.dtVigencia = dtVigencia;
	}

	public PopupDateField getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(PopupDateField dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public TextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(TextField txtNome) {
		this.txtNome = txtNome;
	}

	public TextField getTxtNumero() {
		return txtNumero;
	}

	public void setTxtNumero(TextField txtNumero) {
		this.txtNumero = txtNumero;
	}

	public ComboBox getCbmContabilConta() {
		return cbmContabilConta;
	}

	public void setCbmContabilConta(ComboBox cbmContabilConta) {
		this.cbmContabilConta = cbmContabilConta;
	}

	public ComboBox getCbmTipoContrato() {
		return cbmTipoContrato;
	}

	public void setCbmTipoContrato(ComboBox cbmTipoContrato) {
		this.cbmTipoContrato = cbmTipoContrato;
	}

	public void carregaComboTipoContrato(List<TipoContrato> tipos) {
		this.cbmTipoContrato.removeAllItems();
		for (TipoContrato tipo : tipos) {
			this.cbmTipoContrato.addItem(tipo);
		}
	}

	public void carregaComboContabilConta(List<ContabilConta> contas) {
		this.cbmContabilConta.removeAllItems();
		for (ContabilConta conta : contas) {
			this.cbmContabilConta.addItem(conta);
		}
	}

	public void carregaComboSolicitacaoServico(
			List<ContratoSolicitacaoServico> solicitacaoServicos) {
		this.cmbSolicitacaoServico.removeAllItems();
		for (ContratoSolicitacaoServico servico : solicitacaoServicos) {
			this.cmbSolicitacaoServico.addItem(servico);
		}
	}

	public void carregaComboDocumento(List<Documento> documentos) {
		this.cbmDocumento.removeAllItems();
		for (Documento documento : documentos) {
			this.cbmDocumento.addItem(documento);
		}
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("500px");

		// top-level component properties
		setWidth("100.0%");
		setHeight("500px");

		// tabSheet_1
		tabSheet_1 = buildTabSheet_1();
		mainLayout.addComponent(tabSheet_1,
				"top:20.0px;right:63.0px;left:20.0px;");

		// tabSheet_2
		tabSheet_2 = buildTabSheet_2();
		mainLayout.addComponent(tabSheet_2, "top:320.0px;left:21.0px;");

		return mainLayout;
	}

	@AutoGenerated
	private TabSheet buildTabSheet_1() {
		// common part: create layout
		tabSheet_1 = new TabSheet();
		tabSheet_1.setImmediate(true);
		tabSheet_1.setWidth("100.0%");
		tabSheet_1.setHeight("285px");

		// absoluteLayout_5
		absoluteLayout_5 = buildAbsoluteLayout_5();
		tabSheet_1.addTab(absoluteLayout_5, "Dados B�sicos", null);

		// absoluteLayout_2
		absoluteLayout_2 = buildAbsoluteLayout_2();
		tabSheet_1.addTab(absoluteLayout_2, "Dados Complementares", null);

		return tabSheet_1;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsoluteLayout_5() {
		// common part: create layout
		absoluteLayout_5 = new AbsoluteLayout();
		absoluteLayout_5.setImmediate(false);
		absoluteLayout_5.setWidth("100.0%");
		absoluteLayout_5.setHeight("250px");

		// cbmTipoContrato
		cbmTipoContrato = new ComboBox();
		cbmTipoContrato.setCaption("Tipo de Contrato");
		cbmTipoContrato.setImmediate(false);
		cbmTipoContrato.setWidth("-1px");
		cbmTipoContrato.setHeight("-1px");
		absoluteLayout_5.addComponent(cbmTipoContrato,
				"top:28.0px;left:21.0px;");

		// cbmContabilConta
		cbmContabilConta = new ComboBox();
		cbmContabilConta.setCaption("Conta Contábil");
		cbmContabilConta.setImmediate(false);
		cbmContabilConta.setWidth("-1px");
		cbmContabilConta.setHeight("-1px");
		absoluteLayout_5.addComponent(cbmContabilConta,
				"top:84.0px;left:21.0px;");

		// txtNumero
		txtNumero = new TextField();
		txtNumero.setCaption("Número");
		txtNumero.setImmediate(false);
		txtNumero.setWidth("141px");
		txtNumero.setHeight("-1px");
		absoluteLayout_5.addComponent(txtNumero, "top:142;left:21.0px;");

		// txtNome
		txtNome = new TextField();
		txtNome.setCaption("Nome");
		txtNome.setImmediate(false);
		txtNome.setWidth("341px");
		txtNome.setHeight("-1px");
		absoluteLayout_5.addComponent(txtNome, "top:142;left:218.0px;");

		// cmbSolicitacaoServico
		cmbSolicitacaoServico = new ComboBox();
		cmbSolicitacaoServico.setCaption("Solicitação de serviço");
		cmbSolicitacaoServico.setImmediate(false);
		cmbSolicitacaoServico.setWidth("-1px");
		cmbSolicitacaoServico.setHeight("-1px");
		absoluteLayout_5.addComponent(cmbSolicitacaoServico,
				"top:84.0px;left:218.0px;");
		
		cbmDocumento = new ComboBox();
		cbmDocumento.setCaption("Modelo Documento");
		cbmDocumento.setImmediate(false);
		cbmDocumento.setWidth("-1px");
		cbmDocumento.setHeight("-1px");
		absoluteLayout_5.addComponent(cbmDocumento,
				"top:204.0px;left:21.0px;");

		btnGerarContrato = new Button("Gerar Contrato");
		btnGerarContrato.setVisible(false);

		absoluteLayout_5.addComponent(btnGerarContrato,
				"top:204.0px;left:218.0px;");

		return absoluteLayout_5;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsoluteLayout_2() {
		// common part: create layout
		absoluteLayout_2 = new AbsoluteLayout();
		absoluteLayout_2.setImmediate(false);
		absoluteLayout_2.setWidth("100.0%");
		absoluteLayout_2.setHeight("250px");

		// dtCadastro
		dtCadastro = new PopupDateField();
		dtCadastro.setCaption("Data Cadastro");
		dtCadastro.setImmediate(false);
		dtCadastro.setWidth("-1px");
		dtCadastro.setHeight("-1px");
		absoluteLayout_2.addComponent(dtCadastro, "top:28.0px;left:21.0px;");

		// dtVigencia
		dtVigencia = new PopupDateField();
		dtVigencia.setCaption("Data Vigência");
		dtVigencia.setImmediate(false);
		dtVigencia.setWidth("-1px");
		dtVigencia.setHeight("-1px");
		absoluteLayout_2.addComponent(dtVigencia, "top:28.0px;left:220.0px;");

		// dtFimVigencia
		dtFimVigencia = new PopupDateField();
		dtFimVigencia.setCaption("Data Fim Vigência");
		dtFimVigencia.setImmediate(false);
		dtFimVigencia.setWidth("-1px");
		dtFimVigencia.setHeight("-1px");
		absoluteLayout_2
				.addComponent(dtFimVigencia, "top:28.0px;left:419.0px;");

		// txtDiaFaturamento
		txtDiaFaturamento = new TextField();
		txtDiaFaturamento.setCaption("Dia de Faturamento");
		txtDiaFaturamento.setImmediate(false);
		txtDiaFaturamento.setWidth("-1px");
		txtDiaFaturamento.setHeight("-1px");
		absoluteLayout_2.addComponent(txtDiaFaturamento,
				"top:27.0px;left:602.0px;");

		// txtValor
		txtValor = new TextField();
		txtValor.setCaption("Valor");
		txtValor.setImmediate(false);
		txtValor.setWidth("-1px");
		txtValor.setHeight("-1px");
		absoluteLayout_2.addComponent(txtValor, "top:84.0px;left:21.0px;");

		// txtQuantidadeParcelas
		txtQuantidadeParcelas = new TextField();
		txtQuantidadeParcelas.setCaption("Quantidade de Parcelas");
		txtQuantidadeParcelas.setImmediate(false);
		txtQuantidadeParcelas.setWidth("-1px");
		txtQuantidadeParcelas.setHeight("-1px");
		absoluteLayout_2.addComponent(txtQuantidadeParcelas,
				"top:84.0px;left:220.0px;");

		// txtIntervaloParcelas
		txtIntervaloParcelas = new TextField();
		txtIntervaloParcelas.setCaption("Intervalo entre Parcelas");
		txtIntervaloParcelas.setImmediate(false);
		txtIntervaloParcelas.setWidth("-1px");
		txtIntervaloParcelas.setHeight("-1px");
		absoluteLayout_2.addComponent(txtIntervaloParcelas,
				"top:84.0px;left:418.0px;");

		// txaDescricao
		txaDescricao = new TextArea();
		txaDescricao.setCaption("Descrição");
		txaDescricao.setImmediate(false);
		txaDescricao.setWidth("339px");
		txaDescricao.setHeight("-1px");
		absoluteLayout_2.addComponent(txaDescricao, "top:150.0px;left:21.0px;");

		// txaObservacoes
		txaObservacoes = new TextArea();
		txaObservacoes.setCaption("Observações");
		txaObservacoes.setImmediate(false);
		txaObservacoes.setWidth("339px");
		txaObservacoes.setHeight("-1px");
		absoluteLayout_2.addComponent(txaObservacoes,
				"top:150.0px;left:420.0px;");

		return absoluteLayout_2;
	}

	@AutoGenerated
	private TabSheet buildTabSheet_2() {
		// common part: create layout
		tabSheet_2 = new TabSheet();
		tabSheet_2.setImmediate(true);

		historicoFaturamentoSubForm = buildHistoricoFaturaSubForm();
		historicoReajustesSubForm = buildHistoricoReajustesSubForm();
		previsaoFaturamentoSubForm = buildPrevisaoFaturamentoSubForm();

		tabSheet_2.addTab(historicoFaturamentoSubForm, "Hist�rico Faturamento",
				null);

		tabSheet_2.addTab(historicoReajustesSubForm, "Hist�rico de Reajustes",
				null);

		tabSheet_2.addTab(previsaoFaturamentoSubForm,
				"Previsão de Faturamento", null);
		
		

		return tabSheet_2;
	}

	private SubFormComponent<ContratoPrevFaturamento, Integer> buildPrevisaoFaturamentoSubForm() {
		SubFormComponent<ContratoPrevFaturamento, Integer> subForm = new SubFormComponent<ContratoPrevFaturamento, Integer>(
				ContratoPrevFaturamento.class, new String[] { "dataPrevista",
						"valor" }, new String[] { "Data Prevista", "Valor" }) {
			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					@Override
					public Field<?> createField(Container container,
							Object itemId, Object propertyId,
							Component uiContext) {

						if ("dataPrevista".equals(propertyId)) {
							DateField dateField = new DateField();
							dateField.setSizeFull();
							return dateField;
						} else if ("valor".equals(propertyId)) {
							TextField textField = new TextField();
							textField.setSizeFull();
							textField
									.setConverter(new StringToBigDecimalConverter());
							return textField;
						}

						return null;
					}

				};
			}

			protected ContratoPrevFaturamento getNovo() {
				ContratoPrevFaturamento contratoPrevFaturamento = controller
						.novoContratoPrevFaturamento();
				return contratoPrevFaturamento;
			}

			protected void getRemoverSelecionados(
					List<ContratoPrevFaturamento> values) {
				controller.removerContratoPrevFaturamento(values);
			}

			@Override
			public boolean validateItems(List<ContratoPrevFaturamento> items) {
				return true;
			}
		};

		return subForm;
	}

	private SubFormComponent<ContratoHistoricoReajuste, Integer> buildHistoricoReajustesSubForm() {
		SubFormComponent<ContratoHistoricoReajuste, Integer> subForm = new SubFormComponent<ContratoHistoricoReajuste, Integer>(
				ContratoHistoricoReajuste.class, new String[] { "indice",
						"valorAnterior", "valorAtual", "dataReajuste",
						"observacao" }, new String[] { "�ndice",
						"Valor Anterior", "Valor Atual", "Data Reajuste",
						"Observação" }) {
			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					@Override
					public Field<?> createField(Container container,
							Object itemId, Object propertyId,
							Component uiContext) {

						if ("indice".equals(propertyId)) {
							TextField textField = new TextField();
							textField.setSizeFull();
							textField
									.setConverter(new StringToBigDecimalConverter());
							return textField;
						} else if ("valorAnterior".equals(propertyId)) {
							TextField textField = new TextField();
							textField.setSizeFull();
							textField
									.setConverter(new StringToBigDecimalConverter());
							return textField;
						} else if ("valorAtual".equals(propertyId)) {
							TextField textField = new TextField();
							textField.setSizeFull();
							textField
									.setConverter(new StringToBigDecimalConverter());
							return textField;
						} else if ("observacao".equals(propertyId)) {
							TextField textField = new TextField();
							textField.setSizeFull();

							return textField;
						} else if ("dataReajuste".equals(propertyId)) {
							DateField dateField = new DateField();
							dateField.setSizeFull();
							return dateField;
						}

						return null;
					}

				};
			}

			protected ContratoHistoricoReajuste getNovo() {
				ContratoHistoricoReajuste contratoHistoricoReajuste = controller
						.novoContratoHistoricoReajuste();
				return contratoHistoricoReajuste;
			}

			protected void getRemoverSelecionados(
					List<ContratoHistoricoReajuste> values) {
				controller.removerContratoHistoricoReajuste(values);
			}

			@Override
			public boolean validateItems(List<ContratoHistoricoReajuste> items) {
				// TODO Auto-generated method stub
				return true;
			}
		};

		return subForm;
	}

	private SubFormComponent<ContratoHistFaturamento, Integer> buildHistoricoFaturaSubForm() {
		SubFormComponent<ContratoHistFaturamento, Integer> subForm = new SubFormComponent<ContratoHistFaturamento, Integer>(
				ContratoHistFaturamento.class, new String[] { "dataFatura",
						"valor" }, new String[] { "Data Fatura", "Valor" }) {
			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					@Override
					public Field<?> createField(Container container,
							Object itemId, Object propertyId,
							Component uiContext) {

						if ("dataFatura".equals(propertyId)) {
							DateField dateField = new DateField();
							dateField.setSizeFull();
							return dateField;
						} else if ("valor".equals(propertyId)) {
							TextField textField = new TextField();
							textField.setSizeFull();
							textField
									.setConverter(new StringToBigDecimalConverter());
							return textField;
						}

						return null;
					}

				};
			}

			protected ContratoHistFaturamento getNovo() {
				ContratoHistFaturamento contratoHistFaturamento = controller
						.novoContratoHistFaturamento();
				return contratoHistFaturamento;
			}

			protected void getRemoverSelecionados(
					List<ContratoHistFaturamento> values) {
				controller.removerContratoHistFaturamento(values);
			}

			@Override
			public boolean validateItems(List<ContratoHistFaturamento> items) {
			
				return true;
			}
		};

		return subForm;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsoluteLayout_3() {
		// common part: create layout
		absoluteLayout_3 = new AbsoluteLayout();
		absoluteLayout_3.setImmediate(false);
		absoluteLayout_3.setWidth("100.0%");
		absoluteLayout_3.setHeight("100.0%");

		// table_2
		table_2 = new Table();
		table_2.setImmediate(false);
		table_2.setWidth("680px");
		table_2.setHeight("100px");
		absoluteLayout_3.addComponent(table_2, "top:0.0px;left:58.0px;");

		return absoluteLayout_3;
	}

	public ComboBox getCmbSolicitacaoServico() {
		return cmbSolicitacaoServico;
	}

	public void setCmbSolicitacaoServico(ComboBox cmbSolicitacaoServico) {
		this.cmbSolicitacaoServico = cmbSolicitacaoServico;
	}

	public void preencherHistoricoFaturamento(
			List<ContratoHistFaturamento> historicosFaturamentos) {
		historicoFaturamentoSubForm.fillWith(historicosFaturamentos);
	}

	public void fillContratoHistoricoFaturamentoSubForm(
			List<ContratoHistFaturamento> contratosHistoricosFaturamentos) {
		this.historicoFaturamentoSubForm
				.fillWith(contratosHistoricosFaturamentos);

	}

	public void fillContratoHistoricosReajustesSubForm(
			List<ContratoHistoricoReajuste> contratosHistoricosReajustes) {
		this.historicoReajustesSubForm.fillWith(contratosHistoricosReajustes);

	}

	public void fillContratosPrevisoesFaturamentosSubForm(
			List<ContratoPrevFaturamento> contratosPrevisoesFaturamentos) {
		this.previsaoFaturamentoSubForm
				.fillWith(contratosPrevisoesFaturamentos);

	}

	public void preencheContrato(Contrato contrato) {

		contrato.setContabilConta((ContabilConta) getCbmContabilConta()
				.getValue());
		contrato.setContratoSolicitacaoServico((ContratoSolicitacaoServico) getCmbSolicitacaoServico()
				.getValue());
		contrato.setDataCadastro(getDtCadastro().getValue());
		contrato.setDataFimVigencia(getDtFimVigencia().getValue());
		contrato.setDataInicioVigencia(getDtVigencia().getValue());
		contrato.setDescricao(getTxaDescricao().getValue());
		contrato.setDiaFaturamento(getTxtDiaFaturamento().getValue());
		contrato.setIntervaloEntreParcelas(Integer
				.parseInt(getTxtIntervaloParcelas().getValue()));
		contrato.setNome(getTxtNome().getValue());
		contrato.setNumero(getTxtNumero().getValue());
		contrato.setObservacao(getTxaObservacoes().getValue());
		contrato.setQuantidadeParcelas(Integer
				.parseInt(getTxtQuantidadeParcelas().getValue()));
		contrato.setTipoContrato((TipoContrato) getCbmTipoContrato().getValue());
		contrato.setValor(new BigDecimal(getTxtValor().getValue()));

	}

	public void preencheContratoForm(Contrato contrato) {
		getCbmContabilConta().setValue(contrato.getContabilConta());
		getCmbSolicitacaoServico().setValue(
				contrato.getContratoSolicitacaoServico());
		getDtCadastro().setValue(contrato.getDataCadastro());
		getDtFimVigencia().setValue(contrato.getDataFimVigencia());
		getDtVigencia().setValue(contrato.getDataInicioVigencia());
		getTxaDescricao().setValue(contrato.getDescricao());
		getTxtDiaFaturamento().setValue(contrato.getDiaFaturamento());
		getTxtIntervaloParcelas().setValue(
				String.valueOf(contrato.getIntervaloEntreParcelas()));
		getTxtNome().setValue(contrato.getNome());
		getTxtNumero().setValue(contrato.getNumero());
		getTxaObservacoes().setValue(contrato.getObservacao());
		getTxtQuantidadeParcelas().setValue(
				String.valueOf(contrato.getQuantidadeParcelas()));
		getCbmTipoContrato().setValue(contrato.getTipoContrato());
		getTxtValor().setValue(contrato.getValor().toString());
		
		
		this.fillContratoHistoricoFaturamentoSubForm(contrato
				.getContratosHistoricosFaturamentos());

		this.fillContratoHistoricosReajustesSubForm(contrato
				.getContratosHistoricosReajustes());

		this.fillContratosPrevisoesFaturamentosSubForm(contrato
				.getContratosPrevisoesFaturamentos());

	}

	public Button getBtnGerarContrato() {
		return btnGerarContrato;
	}

	public void setBtnGerarContrato(Button btnGerarContrato) {
		this.btnGerarContrato = btnGerarContrato;
	}

	public ComboBox getCbmDocumento() {
		return cbmDocumento;
	}

	public void setCbmDocumento(ComboBox cbmDocumento) {
		this.cbmDocumento = cbmDocumento;
	}
}
