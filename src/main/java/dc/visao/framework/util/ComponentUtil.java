package dc.visao.framework.util;

import org.vaadin.addons.maskedtextfield.MaskedTextField;
import org.vaadin.addons.maskedtextfield.NumericField;

import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

import dc.control.converter.CurrencyConverter;
import dc.visao.framework.component.BigDecimalConverter;
import dc.visao.framework.component.LookupComponent;

public final class ComponentUtil {

	public static TextField buildPercentageField(String caption) {
		TextField textField = new TextField();
		textField.setNullRepresentation("");
		textField.setCaption(caption);
		textField.setImmediate(true);
		textField.setSizeFull();
		textField.setConverter(new BigDecimalConverter("", " %"));

		return textField;
	}

	/**
	 * Use somente para campos do tipo BigDecimal.
	 * 
	 * @param caption
	 * @return
	 */
	public static TextField buildNumberField(String caption) {
		TextField textField = new TextField();
		textField.setNullRepresentation("");
		textField.setCaption(caption);
		textField.setImmediate(true);
		textField.setSizeFull();
		textField.setConverter(new BigDecimalConverter());

		return textField;
	}

	public static TextField buildCurrencyField(String caption) {
		final TextField textField = new TextField();
		textField.setNullRepresentation("");
		textField.setCaption(caption);
		textField.setImmediate(true);
		textField.setSizeFull();
		textField.setConverter(new BigDecimalConverter("R$ "));
		textField.addTextChangeListener(event -> CurrencyConverter.vceMask(
				event, textField));

		return textField;
	}

	public static TextField buildTextField(String caption) {
		TextField textField = new TextField();
		textField.setNullRepresentation("");
		textField.setCaption(caption);
		textField.setImmediate(true);
		textField.setSizeFull();

		return textField;
	}

	public static ComboBox buildComboBox(String caption) {
		ComboBox comboBox = new ComboBox();
		comboBox.setCaption(caption);
		comboBox.setImmediate(true);
		comboBox.setSizeFull();
		comboBox.setFilteringMode(FilteringMode.CONTAINS);

		return comboBox;
	}

	public static <ID, T> LookupComponent<ID, T> buildLookup(
			String codeCaption, String comboCaption) {
		LookupComponent<ID, T> lookupComponent = new LookupComponent<>();
		lookupComponent.setCodeCaption(codeCaption);
		lookupComponent.setComboCaption(comboCaption);
		lookupComponent.setSizeFull();

		return lookupComponent;
	}

	public static RichTextArea buildRichTextArea(String caption) {
		RichTextArea richTextArea = new RichTextArea();
		richTextArea.setNullRepresentation("");
		richTextArea.setCaption(caption);
		richTextArea.setImmediate(true);
		richTextArea.setSizeFull();

		return richTextArea;
	}

	public static TextArea buildTextArea(String caption) {
		TextArea textArea = new TextArea();
		textArea.setNullRepresentation("");
		textArea.setCaption(caption);
		textArea.setImmediate(true);
		textArea.setSizeFull();

		return textArea;
	}

	public static PopupDateField buildPopupDateField(String caption) {
		PopupDateField data = new PopupDateField();
		data.setCaption(caption);
		data.setImmediate(true);
		data.setSizeFull();
		// data.setWidth("-1px");
		// data.setHeight("-1px");

		return data;
	}

	public static MaskedTextField buildMaskedTextField(String caption,
			String mask) {
		MaskedTextField textField = new MaskedTextField();
		textField.setMask(mask);
		textField.setNullRepresentation("");
		textField.setCaption(caption);
		textField.setImmediate(true);
		textField.setMaskClientOnly(true);
		// textField.setSizeFull();

		return textField;
	}

	public static CheckBox buildCheckBox(String caption) {
		CheckBox checkBox = new CheckBox();
		checkBox.setCaption(caption);
		checkBox.setImmediate(true);
		checkBox.setSizeFull();

		return checkBox;
	}

	public static PasswordField buildPasswordField(String caption) {
		PasswordField textField = new PasswordField();
		textField.setNullRepresentation("");
		textField.setCaption(caption);
		textField.setImmediate(true);
		textField.setSizeFull();

		return textField;
	}

	public static NumericField buildNumericField(String caption) {
		NumericField textField = new NumericField(caption);
		textField.setNullRepresentation("");
		textField.setImmediate(true);
		textField.setMaxLength(3);
		textField.setSizeFull();

		return textField;
	}

}