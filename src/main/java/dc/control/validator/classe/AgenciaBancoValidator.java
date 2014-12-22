package dc.control.validator.classe;

import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.financeiro.AgenciaBancoFormView;

public class AgenciaBancoValidator {

	public static void validaSalvar(AgenciaBancoFormView subView)
			throws DotErpException {
		String nome = subView.getTfNome().getValue();

		if (StringUtils.isBlank(nome)) {
			throw new DotErpException(subView.getTfNome(),
					"::DotERP - Não pode ficar em branco");
		}

		String logradouro = subView.getTfLogradouro().getValue();

		if (StringUtils.isBlank(logradouro)) {
			throw new DotErpException(subView.getTfLogradouro(),
					"::DotERP - Não pode ficar em branco");
		}

		String bairro = subView.getTfBairro().getValue();

		if (StringUtils.isBlank(bairro)) {
			throw new DotErpException(subView.getTfBairro(),
					"::DotERP - Não pode ficar em branco");
		}

		String numero = subView.getTfNumero().getValue();

		if (StringUtils.isBlank(numero)) {
			throw new DotErpException(subView.getTfNumero(),
					"::DotERP - Não pode ficar em branco");
		}

		String cep = subView.getTfCep().getValue();

		if (StringUtils.isBlank(cep)) {
			throw new DotErpException(subView.getTfCep(),
					"::DotERP - Não pode ficar em branco");
		}

		String municipio = subView.getTfMunicipio().getValue();

		if (StringUtils.isBlank(municipio)) {
			throw new DotErpException(subView.getTfMunicipio(),
					"::DotERP - Não pode ficar em branco");
		}
	}

}