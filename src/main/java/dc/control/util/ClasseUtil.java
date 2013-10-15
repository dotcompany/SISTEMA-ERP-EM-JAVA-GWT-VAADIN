package dc.control.util;

import dc.visao.framework.geral.ControllerTask;

public class ClasseUtil {

	public static synchronized String getUrl(ControllerTask c) {
		String s = c.getClass().getName();

		String[] s1 = s.split("[.]");

		String sUrl = "";

		for (int i = 2; i < s1.length; i++) {
			sUrl += s1[i].toLowerCase();

			if (i < (s1.length - 1)) {
				sUrl += "_";
			}
		}

		return sUrl;
	}

}