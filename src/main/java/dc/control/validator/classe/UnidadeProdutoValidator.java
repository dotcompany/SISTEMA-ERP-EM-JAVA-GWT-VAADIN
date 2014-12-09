package dc.control.validator.classe;

import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.geral.produto.UnidadeProdutoFormView;

public class UnidadeProdutoValidator {

	public static void validaSalvar(UnidadeProdutoFormView subView)
			throws DotErpException {
		String nome = subView.getTfSigla().getValue();

		if (StringUtils.isBlank(nome)) {
			throw new DotErpException(subView.getTfSigla(),
					"::DotERP - Não pode ficar em branco");
		}

		String descricao = subView.getTfDescricao().getValue();

		if (StringUtils.isBlank(descricao)) {
			throw new DotErpException(subView.getTfDescricao(),
					"::DotERP - Não pode ficar em branco");
		}
	}

}