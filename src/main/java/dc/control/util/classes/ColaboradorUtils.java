package dc.control.util.classes;

import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.geral.pessoal.ColaboradorFormView;

public class ColaboradorUtils {

	public static void validateRequiredFields(ColaboradorFormView subView)
			throws DotErpException {
		String matricula = subView.getTfMatricula().getValue();

		if (StringUtils.isBlank(matricula)) {
			throw new DotErpException(subView.getTfMatricula(),
					"::DotERP - Não pode ficar em branco");
		}

		String categoria = subView.getTfCategoria().getValue();

		if (StringUtils.isBlank(categoria)) {
			throw new DotErpException(subView.getTfCategoria(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	public static void validateFieldValue(ColaboradorFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(ColaboradorFormView subView) {

	}

}