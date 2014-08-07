package dc.visao.nfe;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.control.enums.CstCofinsEn;
import dc.control.validator.ObjectValidator;
import dc.controller.nfe.ProdutoServicoFormController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

public class NfeDetalheImpostoCofinsFormView extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProdutoServicoFormController controller;

	/**
	 * 
	 */

	@AutoGenerated
	private String titulo = "COFINS";

	@AutoGenerated
	private VerticalLayout vlNdiCofins;

	@AutoGenerated
	private Panel plNdiCofins;

	@AutoGenerated
	private GridLayout glNdiCofins;

	@AutoGenerated
	private ComboBox cbCstCofins;

	@AutoGenerated
	private TextField tfQtdVendidaCofins;

	@AutoGenerated
	private TextField tfBaseCalculoBcCofins;

	@AutoGenerated
	private TextField tfAliquotaPercentualCofins;

	@AutoGenerated
	private TextField tfAliquotaReaisCofins;

	@AutoGenerated
	private TextField tfValorCofins;

	public GridLayout getGlNdiCofins() {
		return glNdiCofins;
	}

	public String getTitulo() {
		return titulo;
	}

	public ComboBox getCbCstCofins() {
		return cbCstCofins;
	}

	public void setCbCstCofins(ComboBox cbCstCofins) {
		this.cbCstCofins = cbCstCofins;
	}

	public TextField getTfQtdVendidaCofins() {
		return tfQtdVendidaCofins;
	}

	public void setTfQtdVendidaCofins(TextField tfQtdVendidaCofins) {
		this.tfQtdVendidaCofins = tfQtdVendidaCofins;
	}

	public TextField getTfBaseCalculoBcCofins() {
		return tfBaseCalculoBcCofins;
	}

	public void setTfBaseCalculoBcCofins(TextField tfBaseCalculoBcCofins) {
		this.tfBaseCalculoBcCofins = tfBaseCalculoBcCofins;
	}

	public TextField getTfAliquotaPercentualCofins() {
		return tfAliquotaPercentualCofins;
	}

	public void setTfAliquotaPercentualCofins(
			TextField tfAliquotaPercentualCofins) {
		this.tfAliquotaPercentualCofins = tfAliquotaPercentualCofins;
	}

	public TextField getTfAliquotaReaisCofins() {
		return tfAliquotaReaisCofins;
	}

	public void setTfAliquotaReaisCofins(TextField tfAliquotaReaisCofins) {
		this.tfAliquotaReaisCofins = tfAliquotaReaisCofins;
	}

	public TextField getTfValorCofins() {
		return tfValorCofins;
	}

	public void setTfValorCofins(TextField tfValorCofins) {
		this.tfValorCofins = tfValorCofins;
	}

	/**
	 * CONSTRUTOR
	 * 
	 * @param controller
	 */

	public NfeDetalheImpostoCofinsFormView(
			ProdutoServicoFormController controller) {
		this.controller = controller;
	}

	/**
	 * GET / SET
	 */

	@AutoGenerated
	public VerticalLayout bvlNdiCofins() {
		// common part: create layout
		vlNdiCofins = new VerticalLayout();
		vlNdiCofins.setImmediate(false);
		vlNdiCofins.setWidth("100.0%");
		vlNdiCofins.setHeight("100.0%");
		vlNdiCofins.setMargin(true);
		vlNdiCofins.setSpacing(true);

		// panel_2
		vlNdiCofins.addComponent(bplNdiCofins());

		return vlNdiCofins;
	}

	@AutoGenerated
	private Panel bplNdiCofins() {
		// common part: create layout
		plNdiCofins = new Panel();
		plNdiCofins.setImmediate(false);
		plNdiCofins.setSizeFull();

		plNdiCofins.setContent(bglCofins());

		return plNdiCofins;
	}

	@AutoGenerated
	private GridLayout bglCofins() {
		// common part: create layout
		glNdiCofins = new GridLayout();
		glNdiCofins.setImmediate(false);
		glNdiCofins.setSizeUndefined();
		glNdiCofins.setMargin(true);
		glNdiCofins.setSpacing(true);
		glNdiCofins.setRows(4);
		glNdiCofins.setColumns(4);
		glNdiCofins.setEnabled(false);

		// cbCstCofins
		cbCstCofins = new ComboBox("CST:");
		cbCstCofins.setWidth("-1px");
		cbCstCofins.setHeight("-1px");
		cbCstCofins.setSizeFull();
		cbCstCofins.setImmediate(true);
		cbCstCofins.setId("cbCstCofins");
		cbCstCofins.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					controller.ndiCofinsSetarValor(cbCstCofins.getId(), event
							.getProperty().getValue());
				}
			}
		});
		glNdiCofins.addComponent(cbCstCofins, 0, 0);

		// tfQtdVendidaCofins
		tfQtdVendidaCofins = new TextField("Quantidade vendida:");
		tfQtdVendidaCofins.setWidth("-1px");
		tfQtdVendidaCofins.setHeight("-1px");
		tfQtdVendidaCofins.setSizeFull();
		tfQtdVendidaCofins.setNullRepresentation("");
		tfQtdVendidaCofins.setImmediate(true);
		tfQtdVendidaCofins.setId("tfQtdVendidaCofins");
		tfQtdVendidaCofins.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateValue(event)) {
						controller.ndiCofinsSetarValor(tfQtdVendidaCofins
								.getId(), event.getProperty().getValue());
					}
				}
			}
		});
		glNdiCofins.addComponent(tfQtdVendidaCofins, 1, 0);

		// tfBaseCalculoBcCofins
		tfBaseCalculoBcCofins = new TextField("Base de cáculo (BC):");
		tfBaseCalculoBcCofins.setWidth("-1px");
		tfBaseCalculoBcCofins.setHeight("-1px");
		tfBaseCalculoBcCofins.setSizeFull();
		tfBaseCalculoBcCofins.setNullRepresentation("");
		tfBaseCalculoBcCofins.setImmediate(true);
		tfBaseCalculoBcCofins.setId("tfBaseCalculoBcCofins");
		tfBaseCalculoBcCofins.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateValue(event)) {
						controller.ndiCofinsSetarValor(tfBaseCalculoBcCofins
								.getId(), event.getProperty().getValue());
					}
				}
			}
		});
		glNdiCofins.addComponent(tfBaseCalculoBcCofins, 2, 0);

		// tfAliquotaPercentualCofins
		tfAliquotaPercentualCofins = new TextField("Alíquota percentual:");
		tfAliquotaPercentualCofins.setWidth("-1px");
		tfAliquotaPercentualCofins.setHeight("-1px");
		tfAliquotaPercentualCofins.setSizeFull();
		tfAliquotaPercentualCofins.setNullRepresentation("");
		tfAliquotaPercentualCofins.setImmediate(true);
		tfAliquotaPercentualCofins.setId("tfAliquotaPercentualCofins");
		tfAliquotaPercentualCofins
				.addValueChangeListener(new ValueChangeListener() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						// TODO Auto-generated method stub
						if (ObjectValidator.validateEventValue(event)) {
							if (ObjectValidator.validateValue(event)) {
								controller.ndiCofinsSetarValor(
										tfAliquotaPercentualCofins.getId(),
										event.getProperty().getValue());
							}
						}
					}
				});
		glNdiCofins.addComponent(tfAliquotaPercentualCofins, 3, 0);

		// tfAliquotaReaisCofins
		tfAliquotaReaisCofins = new TextField("Alíquota reais:");
		tfAliquotaReaisCofins.setWidth("-1px");
		tfAliquotaReaisCofins.setHeight("-1px");
		tfAliquotaReaisCofins.setSizeFull();
		tfAliquotaReaisCofins.setNullRepresentation("");
		tfAliquotaReaisCofins.setImmediate(true);
		tfAliquotaReaisCofins.setId("tfAliquotaReaisCofins");
		tfAliquotaReaisCofins.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateValue(event)) {
						controller.ndiCofinsSetarValor(tfAliquotaReaisCofins
								.getId(), event.getProperty().getValue());
					}
				}
			}
		});
		glNdiCofins.addComponent(tfAliquotaReaisCofins, 0, 1);

		// tfValorCofins
		tfValorCofins = new TextField("Valor:");
		tfValorCofins.setWidth("-1px");
		tfValorCofins.setHeight("-1px");
		tfValorCofins.setSizeFull();
		tfValorCofins.setNullRepresentation("");
		tfValorCofins.setImmediate(true);
		tfValorCofins.setId("tfValorCofins");
		tfValorCofins.addValueChangeListener(new ValueChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (ObjectValidator.validateEventValue(event)) {
					if (ObjectValidator.validateValue(event)) {
						controller.ndiCofinsSetarValor(tfValorCofins.getId(),
								event.getProperty().getValue());
					}
				}
			}
		});
		glNdiCofins.addComponent(tfValorCofins, 1, 1);

		return glNdiCofins;
	}

	/**
	 * COMBOBOX
	 */

	public void carregarComboBox() {
		for (CstCofinsEn en : CstCofinsEn.values()) {
			this.cbCstCofins.addItem(en);
		}
	}

}