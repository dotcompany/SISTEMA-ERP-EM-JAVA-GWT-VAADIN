package dc.control.converter;

public class StringConverter {

	public synchronized static String removeSpecialCharacters(String string) {
		return string.replaceAll("\\W", "").trim();
	}

	/**
	 * Teste
	 */

	public static void main(String[] args) {
		System.out
				.println(removeSpecialCharacters("...KKK..;;;[[]]][[{{}}}lll++-eeeee-"));
	}

}