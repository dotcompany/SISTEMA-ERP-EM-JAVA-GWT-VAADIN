package dc.control.util.classes;

import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.entidade.contabilidade.ContabilContaEntity;
import dc.visao.geral.pessoal.PessoaFormView;
import dc.visao.geral.pessoal.TransportadoraFormView;

public class TransportadoraUtils {

	public static void validateRequiredFields(TransportadoraFormView subView)
			throws DotErpException {

	}

	public static void validateRequiredFields(PessoaFormView subView)
			throws DotErpException {
		System.out.println(":: valida transportadora");

		ContabilContaEntity contabilConta = subView
				.getMocTransportadoraContabilConta().getValue();

		if (ObjectUtils.isBlank(contabilConta)) {
			throw new DotErpException(
					subView.getMocTransportadoraContabilConta(),
					"::DotERP - Não pode ficar em branco");
		}

		String observacao = subView.getTaColaboradorObservacao().getValue();

		if (StringUtils.isBlank(observacao)) {
			throw new DotErpException(subView.getTaColaboradorObservacao(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	//

	public static void validateFieldValue(TransportadoraFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(TransportadoraFormView subView) {

	}

}