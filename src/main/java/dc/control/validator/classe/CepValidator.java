package dc.control.validator.classe;

import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.geral.diverso.CepFormView;

public class CepValidator {

	public static void validaSalvar(CepFormView subView) throws DotErpException {
		String cep = subView.getTfCep().getValue();

		if (StringUtils.isBlank(cep)) {
			throw new DotErpException(subView.getTfCep(),
					"::DotERP - Não pode ficar em branco");
		}
	}

}