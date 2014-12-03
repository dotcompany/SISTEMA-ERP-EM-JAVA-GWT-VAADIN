package dc.control.util;

public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils {

	public static boolean isNotBlank(Object obj) {
		boolean b = isNotNull(obj);

		if (!b) {
			return b;
		}

		b = obj.equals("");

		return !b;
	}

	public static boolean isNotNull(Object obj) {
		return obj != null;
	}

	/**
	 * Teste
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		System.out.println(isNotBlank(null));
	}

}