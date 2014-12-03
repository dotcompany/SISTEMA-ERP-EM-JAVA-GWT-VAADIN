package dc.control.validator.classe;

import dc.control.enums.TipoPessoaEn;
import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.geral.pessoal.PessoaFormView;

public class PessoaValidator {

	public static void validaSalvar(PessoaFormView subView)
			throws DotErpException {
		TipoPessoaEn tipoPessoaEn = (TipoPessoaEn) subView.getCmbTipoPessoa()
				.getValue();

		if (ObjectUtils.isBlank(tipoPessoaEn)) {
			throw new DotErpException(subView.getCmbTipoPessoa(),
					"::DotERP - Não pode ficar em branco");
		}

		String email = subView.getTxtEmail().getValue();

		if (StringUtils.isBlank(email)) {
			throw new DotErpException(subView.getTxtEmail(),
					"::DotERP - Não pode ficar em branco");
		}

		String nome = subView.getTxtNome().getValue();

		if (StringUtils.isBlank(nome)) {
			throw new DotErpException(subView.getTxtNome(),
					"::DotERP - Não pode ficar em branco");
		}

		String site = subView.getTxtSite().getValue();

		if (StringUtils.isBlank(site)) {
			throw new DotErpException(subView.getTxtSite(),
					"::DotERP - Não pode ficar em branco");
		}
	}

}