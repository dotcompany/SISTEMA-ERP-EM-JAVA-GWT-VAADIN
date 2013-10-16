package dc.control.util;

import dc.controller.contabilidade.cadastro.AidfAimdfFormController;
import dc.visao.framework.geral.ControllerTask;

public class ClasseUtil {

	public static synchronized String getUrl(ControllerTask c) {
		String s = c.getClass().getName();

		String[] s1 = s.split("[.]");

		StringBuilder sUrl = new StringBuilder("");

		for (int i = 2; i < s1.length; i++) {
			sUrl = sUrl.append(s1[i].toLowerCase());

			if (i < (s1.length - 1)) {
				sUrl = sUrl.append("_");
			}
		}

		return sUrl.toString();
	}

	/**
	 * Para realizar testes
	 */

	public static void main(String[] args) {
		try {
			System.out.println(ClasseUtil.getUrl(AidfAimdfFormController.class
					.newInstance()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}