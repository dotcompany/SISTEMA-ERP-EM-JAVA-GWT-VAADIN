package dc.control.util.classes;

import dc.control.enums.TipoPessoaEn;
import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.entidade.geral.pessoal.EstadoCivilEntity;
import dc.visao.geral.diverso.CepFormView;
import dc.visao.geral.pessoal.PessoaFormView;

public class PessoaUtils {

	public static void validateRequiredFields(PessoaFormView subView)
			throws DotErpException {
		EstadoCivilEntity estadoCivil = subView.getMocEstadoCivil().getValue();

		if (ObjectUtils.isBlank(estadoCivil)) {
			throw new DotErpException(subView.getCbTipoPessoa(),
					"::DotERP - Não pode ficar em branco");
		}

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

	public static void clearFormFields(CepFormView subView) {

	}

}