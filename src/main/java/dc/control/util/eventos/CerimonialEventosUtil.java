package dc.control.util.eventos;


import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.entidade.geral.diverso.UfEntity;
import dc.visao.financeiro.AgenciaBancoFormView;
import dc.visao.geral.eventos.CerimonialEventosFormView;

public class CerimonialEventosUtil {

	public static void validateRequiredFields(CerimonialEventosFormView subView)
			throws DotErpException {
		String nome = subView.getTxtNome().getValue();

		if (StringUtils.isBlank(nome)) {
			throw new DotErpException(subView.getTxtNome(),
					"::DotERP - Não pode ficar em branco");
		}

		String endereco = subView.getTfEndereco().getValue();

		if (StringUtils.isBlank(endereco)) {
			throw new DotErpException(subView.getTfEndereco(),
					"::DotERP - Não pode ficar em branco");
		}

		String bairro = subView.getTfBairro().getValue();

		if (StringUtils.isBlank(bairro)) {
			throw new DotErpException(subView.getTfBairro(),
					"::DotERP - Não pode ficar em branco");
		}

		String numero = subView.getTxtNumero().getValue();

		if (StringUtils.isBlank(numero)) {
			throw new DotErpException(subView.getTxtNumero(),
					"::DotERP - Não pode ficar em branco");
		}

		String cep = subView.getTfCep().getValue();

		if (StringUtils.isBlank(cep)) {
			throw new DotErpException(subView.getTfCep(),
					"::DotERP - Não pode ficar em branco");
		}

		UfEntity uf = (UfEntity) subView.getCmbUf().getValue();

		if (ObjectUtils.isBlank(uf)) {
			throw new DotErpException(subView.getCmbUf(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	public static void validateFieldValue(AgenciaBancoFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(AgenciaBancoFormView subView) {

	}

}
