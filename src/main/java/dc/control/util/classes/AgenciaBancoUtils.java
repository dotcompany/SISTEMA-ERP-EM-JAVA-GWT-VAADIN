package dc.control.util.classes;

import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.entidade.financeiro.BancoEntity;
import dc.entidade.geral.UfEntity;
import dc.visao.financeiro.AgenciaBancoFormView;

public class AgenciaBancoUtils {

	public static void validateRequiredFields(AgenciaBancoFormView subView)
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

		BancoEntity banco = (BancoEntity) subView.getMocBanco().getValue();

		if (ObjectUtils.isBlank(banco)) {
			throw new DotErpException(subView.getMocBanco(),
					"::DotERP - Não pode ficar em branco");
		}

		UfEntity uf = (UfEntity) subView.getCbUf().getValue();

		if (ObjectUtils.isBlank(uf)) {
			throw new DotErpException(subView.getCbUf(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	public static void validateFieldValue(AgenciaBancoFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(AgenciaBancoFormView subView) {

	}

}