package dc.control.validator.classe;

import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.entidade.geral.UfEntity;
import dc.visao.geral.diverso.MunicipioFormView;

public class MunicipioValidator {

	public static void validaSalvar(MunicipioFormView subView)
			throws DotErpException {
		String nome = subView.getTfNome().getValue();

		if (StringUtils.isBlank(nome)) {
			throw new DotErpException(subView.getTfNome(),
					"::DotERP - Não pode ficar em branco");
		}

		UfEntity uf = subView.getMocUf().getValue();

		if (ObjectUtils.isBlank(uf)) {
			throw new DotErpException(subView.getMocUf(),
					"::DotERP - Não pode ficar em branco");
		}
	}

}