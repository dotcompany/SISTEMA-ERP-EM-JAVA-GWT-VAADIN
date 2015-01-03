package dc.control.util.classes;

import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.geral.diverso.CepFormView;
import dc.visao.geral.pessoal.NivelFormacaoFormView;

public class NivelFormacaoUtils {

	public static void validateRequiredFields(NivelFormacaoFormView subView)
			throws DotErpException {
		String nome = subView.getTfNome().getValue();

		if (StringUtils.isBlank(nome)) {
			throw new DotErpException(subView.getTfNome(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	public static void clearFormFields(CepFormView subView) {

	}

}