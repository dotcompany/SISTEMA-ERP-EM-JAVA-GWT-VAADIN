package dc.control.validator.classe;

import dc.control.enums.TipoPessoaEn;
import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.geral.pessoal.PessoaFormView;

public class PessoaValidator {

	public static void validaSalvar(PessoaFormView subView)
			throws DotErpException {
		TipoPessoaEn tipoPessoaEn = (TipoPessoaEn) subView.getCbTipoPessoa()
				.getValue();

		if (ObjectUtils.isBlank(tipoPessoaEn)) {
			throw new DotErpException(subView.getCbTipoPessoa(),
					"::DotERP - Não pode ficar em branco");
		}

		String email = subView.getTfEmail().getValue();

		if (StringUtils.isBlank(email)) {
			throw new DotErpException(subView.getTfEmail(),
					"::DotERP - Não pode ficar em branco");
		}

		String nome = subView.getTfNome().getValue();

		if (StringUtils.isBlank(nome)) {
			throw new DotErpException(subView.getTfNome(),
					"::DotERP - Não pode ficar em branco");
		}

		String site = subView.getTfSite().getValue();

		if (StringUtils.isBlank(site)) {
			throw new DotErpException(subView.getTfSite(),
					"::DotERP - Não pode ficar em branco");
		}
	}

}