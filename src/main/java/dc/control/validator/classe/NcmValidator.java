package dc.control.validator.classe;

import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.geral.produto.NcmFormView;

public class NcmValidator {

	public static void validaSalvar(NcmFormView subView) throws DotErpException {
		String nome = subView.getTfCodigo().getValue();

		if (StringUtils.isBlank(nome)) {
			throw new DotErpException(subView.getTfCodigo(),
					"::DotERP - Não pode ficar em branco");
		}

		String descricao = subView.getTfDescricao().getValue();

		if (StringUtils.isBlank(descricao)) {
			throw new DotErpException(subView.getTfDescricao(),
					"::DotERP - Não pode ficar em branco");
		}

		String observacao = subView.getTfObservacao().getValue();

		if (StringUtils.isBlank(observacao)) {
			throw new DotErpException(subView.getTfObservacao(),
					"::DotERP - Não pode ficar em branco");
		}
	}

}