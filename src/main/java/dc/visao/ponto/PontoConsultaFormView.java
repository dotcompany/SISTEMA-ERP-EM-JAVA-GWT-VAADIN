package dc.visao.ponto;

import java.util.List;
import java.util.Locale;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Container;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.entidade.ponto.PontoFechamentoJornada;
import dc.entidade.ponto.PontoMarcacao;
import dc.visao.framework.component.SubFormComponent;
import dc.visao.framework.util.ComponentUtil;
import dc.visao.ponto.converters.ColaboradorConverter;
import dc.visao.ponto.converters.PontoClassificacaoJornadaConverter;

public class PontoConsultaFormView extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private GridLayout fields;

	@AutoGenerated
	private GridLayout exportButtons;

	@AutoGenerated
	private HorizontalLayout exportButtonsContainer;

	@AutoGenerated
	private PopupDateField dtDataInicial;

	@AutoGenerated
	private PopupDateField dtDataFinal;

	@AutoGenerated
	private Button btnAFDT;

	@AutoGenerated
	private Button btnACJEF;

	@AutoGenerated
	private Button btnSearch;

	@AutoGenerated
	private TabSheet tabSheet;

	@AutoGenerated
	private SubFormComponent<PontoMarcacao, Integer> marcacoesSubForm;

	@AutoGenerated
	private SubFormComponent<PontoFechamentoJornada, Integer> pontoFechamentoSubForm;

	@AutoGenerated
	private GridLayout searchButtons;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public PontoConsultaFormView() {
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

		// gridLayout_1
		fields = buildFields();
		mainLayout.addComponent(fields);

		tabSheet = buildAbas();
		mainLayout.addComponent(tabSheet);
		mainLayout.setExpandRatio(tabSheet, 1);

		return mainLayout;
	}

	private TabSheet buildAbas() {
		tabSheet = new TabSheet();
		tabSheet.setImmediate(true);
		tabSheet.setSizeFull();

		tabSheet.addTab(buildMarcacoesSubForm(), "Marcações", null);
		tabSheet.addTab(buildSubFormPontoFechamentoJornada(), "Fechamento Jornada", null);

		return tabSheet;
	}

	@AutoGenerated
	private GridLayout buildFields() {
		// common part: create layout
		fields = new GridLayout(3, 1);
		fields.setImmediate(false);
		fields.setWidth("100.0%");
		fields.setHeight("-1px");
		fields.setMargin(false);
		fields.setSpacing(true);

		searchButtons = buildSearchButtons();
		fields.addComponent(searchButtons, 0, 0);

		exportButtonsContainer = new HorizontalLayout();
		exportButtonsContainer.setSizeFull();
		fields.addComponent(exportButtonsContainer, 2, 0);
		exportButtons = buildExportButtons();
		exportButtonsContainer.addComponent(exportButtons);

		return fields;
	}

	private GridLayout buildSearchButtons() {

		searchButtons = new GridLayout(3, 1);
		searchButtons.setImmediate(false);
		searchButtons.setWidth("100.0%");
		searchButtons.setHeight("-1px");
		searchButtons.setMargin(false);
		searchButtons.setSpacing(true);

		// dataInicial
		dtDataInicial = ComponentUtil.buildPopupDateField("Data Inicial");
		searchButtons.addComponent(dtDataInicial, 0, 0);

		// dtDataFinal
		dtDataFinal = ComponentUtil.buildPopupDateField("Data Final");
		searchButtons.addComponent(dtDataFinal, 1, 0);

		btnSearch = new Button("Buscar");
		btnSearch.setImmediate(true);
		searchButtons.addComponent(btnSearch, 2, 0);

		return searchButtons;
	}

	private GridLayout buildExportButtons() {
		exportButtons = new GridLayout(2, 1);
		exportButtons.setImmediate(false);
		exportButtons.setWidth("100.0%");
		exportButtons.setHeight("-1px");
		exportButtons.setMargin(false);
		exportButtons.setSpacing(true);

		btnACJEF = new Button("ACJEF");
		btnACJEF.setImmediate(true);
		exportButtons.addComponent(btnACJEF, 0, 0);

		btnAFDT = new Button("AFDT");
		btnAFDT.setImmediate(true);
		exportButtons.addComponent(btnAFDT, 1, 0);

		return exportButtons;
	}

	private Component buildSubFormPontoFechamentoJornada() {

		String[] atributos = new String[] { "dataFechamento", "diaSemana", "codigoHorario", "cargaHorariaEsperada", "cargaHorariaDiurna",
				"cargaHorariaNoturna", "cargaHorariaTotal", "entrada01", "saida01", "entrada02", "saida02", "entrada03", "saida03", "entrada04",
				"saida04", "entrada05", "saida05", "horaInicioJornada", "horaFimJornada", "horaExtra01", "percentualHoraExtra01",
				"modalidadeHoraExtra01", "horaExtra02", "percentualHoraExtra02", "modalidadeHoraExtra02", "horaExtra03", "percentualHoraExtra03",
				"modalidadeHoraExtra03", "horaExtra04", "percentualHoraExtra04", "modalidadeHoraExtra04", "faltaAtraso", "compensar", "bancoHoras",
				"observacao", "colaborador", "pontoClassificacaoJornada" };
		String[] headers = new String[] { "Data fechamento", "Dia Semana", "Código Horário", "Carga Horária Esperada", "Carga Horaria Diurna",
				"Carga Horária Noturna", "Carga Horária Total", "Entrada01", "Saída01", "Entrada02", "Saída02", "Entrada03", "Saída03", "Entrada04",
				"Saída04", "Entrada05", "Saída05", "Hora Início Jornada", "Hora Fim Jornada", "Hora Extra01", "Percentual Hora Extra01",
				"Modalidade Hora Extra01", "Hora Extra02", "Percentual Hora Extra02", "Modalidade Hora Extra02", "Hora Extra03",
				"Percentual Hora Extra03", "Modalidade Hora Extra03", "Hora Extra04", "Percentual Hora Extra04", "Modalidade Hora Extra04",
				"Falta/Atraso", "Compensar", "Banco de Horas", "Observação", "Colaborador", "Ponto Classificação Jornada" };

		this.pontoFechamentoSubForm = new SubFormComponent<PontoFechamentoJornada, Integer>(PontoFechamentoJornada.class, atributos, headers) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void adicionarBotoes(Table table) {

			}

			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {

						if ("dataMarcacao".equals(propertyId)) {
							DateField dateField = new DateField();
							dateField.setSizeFull();
							return dateField;
						} else if ("colaborador".equals(propertyId)) {
							TextField textField = new TextField();
							textField.setSizeFull();
							textField.setEnabled(false);

							textField.setConverter(new ColaboradorConverter());

							return textField;
						} else if ("pontoClassificacaoJornada".equals(propertyId)) {
							TextField textField = new TextField();
							textField.setSizeFull();
							textField.setEnabled(false);

							textField.setConverter(new PontoClassificacaoJornadaConverter());

							return textField;
						}

						else {
							return ComponentUtil.buildTextField(null);
						}
					}

				};
			}

			@Override
			public boolean validateItems(List<PontoFechamentoJornada> items) {
				return true;
			}
		};

		return this.pontoFechamentoSubForm;
	}

	@SuppressWarnings("serial")
	private SubFormComponent<PontoMarcacao, Integer> buildMarcacoesSubForm() {

		this.marcacoesSubForm = new SubFormComponent<PontoMarcacao, Integer>(PontoMarcacao.class, new String[] { "colaborador", "dataMarcacao",
				"horaMarcacao", "tipoMarcacao", "tipoRegistro", "justificativa" }, new String[] { "Colaborador", "Data Marcação", "Hora Marcação",
				"Tipo Marcação", "Tipo Registro", "Justificativa" }) {

			@Override
			protected void adicionarBotoes(Table table) {

			}

			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					@Override
					public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {

						if ("colaborador".equals(propertyId)) {
							TextField textField = new TextField();
							textField.setSizeFull();
							textField.setEnabled(false);

							textField.setConverter(new Converter<String, ColaboradorEntity>() {

								@Override
								public ColaboradorEntity convertToModel(String value, Class<? extends ColaboradorEntity> targetType, Locale locale)
										throws com.vaadin.data.util.converter.Converter.ConversionException {
									return null;
								}

								@Override
								public String convertToPresentation(ColaboradorEntity value, Class<? extends String> targetType, Locale locale)
										throws com.vaadin.data.util.converter.Converter.ConversionException {
									return value.getPessoa().getNome();
								}

								@Override
								public Class<ColaboradorEntity> getModelType() {
									return ColaboradorEntity.class;
								}

								@Override
								public Class<String> getPresentationType() {
									return String.class;
								}
							});

							return textField;
						} else if ("dataMarcacao".equals(propertyId)) {
							DateField dateField = new DateField();
							dateField.setSizeFull();
							return dateField;
						} else if ("horaMarcacao".equals(propertyId)) {
							return ComponentUtil.buildMaskedTextField(null, "##:##:##");
						} else if ("tipoMarcacao".equals(propertyId)) {
							ComboBox cmb = ComponentUtil.buildComboBox(null);
							cmb.removeAllItems();

							cmb.setConverter(new Converter<Object, String>() {

								@Override
								public String convertToModel(Object value, Class<? extends String> targetType, Locale locale)
										throws com.vaadin.data.util.converter.Converter.ConversionException {

									if (value instanceof String) {
										return TipoMarcacaoPonto.getEnumLabel((String) value).getCodigo();
									} else {
										return ((TipoMarcacaoPonto) value).getCodigo();
									}
								}

								@Override
								public Class<String> getModelType() {
									return String.class;
								}

								@Override
								public Class<Object> getPresentationType() {

									return Object.class;
								}

								@Override
								public Object convertToPresentation(String value, Class<? extends Object> targetType, Locale locale)
										throws com.vaadin.data.util.converter.Converter.ConversionException {
									return TipoMarcacaoPonto.getEnum(value);
								}

							});

							for (TipoMarcacaoPonto e : TipoMarcacaoPonto.values()) {
								cmb.addItem(e);
							}

							return cmb;
						} else if ("tipoRegistro".equals(propertyId)) {
							ComboBox cmb = ComponentUtil.buildComboBox(null);
							cmb.removeAllItems();

							cmb.setConverter(new Converter<Object, String>() {

								@Override
								public String convertToModel(Object value, Class<? extends String> targetType, Locale locale)
										throws com.vaadin.data.util.converter.Converter.ConversionException {

									if (value instanceof String) {
										return TipoRegistroPonto.getEnumLabel((String) value).getCodigo();
									} else {
										return ((TipoRegistroPonto) value).getCodigo();
									}
								}

								@Override
								public Class<String> getModelType() {
									return String.class;
								}

								@Override
								public Class<Object> getPresentationType() {
									return Object.class;
								}

								@Override
								public Object convertToPresentation(String value, Class<? extends Object> targetType, Locale locale)
										throws com.vaadin.data.util.converter.Converter.ConversionException {
									return TipoRegistroPonto.getEnum(value);
								}

							});

							for (TipoRegistroPonto e : TipoRegistroPonto.values()) {
								cmb.addItem(e);
							}

							return cmb;
						} else if ("justificativa".equals(propertyId)) {
							return ComponentUtil.buildTextField(null);
						}

						return null;
					}

				};
			}

			@Override
			public boolean validateItems(List<PontoMarcacao> items) {
				return true;
			}
		};

		return this.marcacoesSubForm;
	}

	public void fillMarcacoesSubForm(List<PontoMarcacao> PontoMarcacacoes) {
		this.marcacoesSubForm.fillWith(PontoMarcacacoes);
	}

	public void fillPontoFechamentoSubForm(List<PontoFechamentoJornada> pontoFechamentoJornadas) {
		this.pontoFechamentoSubForm.fillWith(pontoFechamentoJornadas);
	}

	public PopupDateField getDtDataInicial() {
		return dtDataInicial;
	}

	public void setDtDataInicial(PopupDateField dtDataInicial) {
		this.dtDataInicial = dtDataInicial;
	}

	public PopupDateField getDtDataFinal() {
		return dtDataFinal;
	}

	public void setDtDataFinal(PopupDateField dtDataFinal) {
		this.dtDataFinal = dtDataFinal;
	}

	public Button getBtnAFDT() {
		return btnAFDT;
	}

	public void setBtnAFDT(Button btnAFDT) {
		this.btnAFDT = btnAFDT;
	}

	public Button getBtnACJEF() {
		return btnACJEF;
	}

	public void setBtnACJEF(Button btnACJEF) {
		this.btnACJEF = btnACJEF;
	}

	public enum TipoMarcacaoPonto {
		ENTRADA("Entrada", "E"), SAIDA("Saída", "S"), DESCONSIDERAR("Desconsiderar", "D");

		private TipoMarcacaoPonto(String label, String codigo) {
			this.label = label;
			this.codigo = codigo;
		}

		private String label;
		private String codigo;

		public static TipoMarcacaoPonto getEnum(String codigo) {

			for (TipoMarcacaoPonto e : TipoMarcacaoPonto.values()) {
				if (e.getCodigo().equalsIgnoreCase(codigo)) {
					return e;
				}
			}

			return null;
		}

		public static TipoMarcacaoPonto getEnumLabel(String label) {

			for (TipoMarcacaoPonto e : TipoMarcacaoPonto.values()) {
				if (e.getLabel().equalsIgnoreCase(label)) {
					return e;
				}
			}

			return null;
		}

		public String getCodigo() {
			return codigo;
		}

		public String getLabel() {
			return label;
		}

		@Override
		public String toString() {
			return label;
		}
	}

	public enum TipoRegistroPonto {
		ORIGINAL("Original", "O"), DIGITACAO("Incluído por Digitação", "I"), PREASSINALADO("Intervalo Pré-assinalado", "P");

		private TipoRegistroPonto(String label, String codigo) {
			this.label = label;
			this.codigo = codigo;
		}

		private String label;
		private String codigo;

		public static TipoRegistroPonto getEnum(String codigo) {

			for (TipoRegistroPonto e : TipoRegistroPonto.values()) {
				if (e.getCodigo().equalsIgnoreCase(codigo)) {
					return e;
				}
			}

			return null;
		}

		public static TipoRegistroPonto getEnumLabel(String label) {

			for (TipoRegistroPonto e : TipoRegistroPonto.values()) {
				if (e.getLabel().equalsIgnoreCase(label)) {
					return e;
				}
			}

			return null;
		}

		public String getCodigo() {
			return codigo;
		}

		public String getLabel() {
			return label;
		}

		@Override
		public String toString() {
			return label;
		}
	}

	public Button getBtnSearch() {
		return btnSearch;
	}

	public void setBtnSearch(Button btnSearch) {
		this.btnSearch = btnSearch;
	}

	public SubFormComponent<PontoMarcacao, Integer> getMarcacoesSubForm() {
		return marcacoesSubForm;
	}

	public void setMarcacoesSubForm(SubFormComponent<PontoMarcacao, Integer> marcacoesSubForm) {
		this.marcacoesSubForm = marcacoesSubForm;
	}

	public SubFormComponent<PontoFechamentoJornada, Integer> getPontoFechamentoSubForm() {
		return pontoFechamentoSubForm;
	}

	public void setPontoFechamentoSubForm(SubFormComponent<PontoFechamentoJornada, Integer> pontoFechamentoSubForm) {
		this.pontoFechamentoSubForm = pontoFechamentoSubForm;
	}
}
