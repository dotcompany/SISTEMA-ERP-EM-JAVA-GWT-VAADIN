package dc.control.validator.classe;

import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.geral.pessoal.NivelFormacaoFormView;

public class NivelFormacaoValidator {

	public static void validaSalvar(NivelFormacaoFormView subView)
			throws DotErpException {
		String nome = subView.getTfNome().getValue();

		if (StringUtils.isBlank(nome)) {
			throw new DotErpException(subView.getTfNome(),
					"::DotERP - Não pode ficar em branco");
		}
	}

}