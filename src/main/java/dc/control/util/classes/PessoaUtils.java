package dc.control.util.classes;

import dc.control.enums.TipoPessoaEn;
import dc.control.util.NumberUtils;
import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.entidade.geral.pessoal.EstadoCivilEntity;
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

	public static void validateFieldValue(PessoaFormView subView)
			throws DotErpException {
		String tituloSecao = (String) subView.getTfTituloSecao().getValue();

		if (!NumberUtils.isNumber(tituloSecao)) {
			throw new DotErpException(subView.getTfTituloSecao(),
					"::DotERP - O campo não é um número");
		}

		String tituloEleitoralZona = (String) subView.getTfTituloZona()
				.getValue();

		if (!NumberUtils.isNumber(tituloEleitoralZona)) {
			throw new DotErpException(subView.getTfTituloZona(),
					"::DotERP - O campo não é um número");
		}
	}

	public static void clearFormFields(PessoaFormView subView) {

	}

}