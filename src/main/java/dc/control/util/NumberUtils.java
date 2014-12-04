package dc.control.util;

public class NumberUtils extends org.apache.commons.lang3.math.NumberUtils {

	public static boolean isBlank(Integer number) {
		boolean b = isNull(number);

		if (b) {
			return b;
		}

		b = number.equals("");

		return b;
	}

	public static boolean isNotBlank(Integer number) {
		boolean b = isNotNull(number);

		if (!b) {
			return b;
		}

		b = number.equals("");

		return !b;
	}

	public static boolean isNotNull(Integer number) {
		return number != null;
	}

	public static boolean isNull(Integer number) {
		return number == null;
	}

}