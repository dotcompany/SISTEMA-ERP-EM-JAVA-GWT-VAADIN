package dc.control.validator;

import java.util.Date;

public class Validator {

	/**
	 * 
	 * @param value
	 * @return
	 */
	public synchronized static Boolean validateString(String value) {
		boolean valido = true;

		if (value == null || value.isEmpty()) {
			valido = false;
		}

		return valido;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public synchronized static boolean validateObject(Object value) {
		return value != null;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public synchronized static Boolean validateNumber(String value) {
		boolean valido = validateString(value);

		if (valido) {
			try {
				Double.parseDouble(value);
			} catch (NumberFormatException e) {
				valido = false;
			}
		}

		return valido;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public synchronized static Boolean validateNotRequiredNumber(String value) {
		try {
			if (value != null && !value.equals("") && !value.isEmpty()) {
				Double.parseDouble(value);
			}

			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public synchronized static Boolean validateInteger(String value) {
		boolean valido = validateString(value);

		if (valido) {
			try {
				Integer.parseInt(value);
			} catch (NumberFormatException e) {
				valido = false;
			}
		}

		return valido;
	}

	public synchronized static Boolean validateNotRequiredInteger(String value) {
		try {
			if (value != null && !value.equals("") && !value.isEmpty()) {
				Integer.parseInt(value);
			}

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public synchronized static Boolean validateDate(String value) {
		boolean valido = validateString(value);

		if (valido) {
			try {
				Date.parse(value);
			} catch (Exception e) {
				valido = false;
			}
		}

		return valido;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public synchronized static Boolean validateDate(Object value) {
		boolean valido = validateObject(value);

		if (valido) {
			try {
				Date d1 = (Date) value;
			} catch (Exception e) {
				valido = false;
			}
		}

		return valido;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public synchronized static Boolean validateNotRequiredDate(Object value) {
		try {
			if (value != null) {
				Date d = (Date) value;
			}

			return true;
		} catch (Exception e) {
			return false;
		}
	}

}