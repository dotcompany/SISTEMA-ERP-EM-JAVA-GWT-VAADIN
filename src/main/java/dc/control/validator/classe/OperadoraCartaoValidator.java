package dc.control.validator.classe;

import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.entidade.financeiro.ContaCaixa;
import dc.visao.geral.diverso.OperadoraCartaoFormView;

public class OperadoraCartaoValidator {

	public static void validaSalvar(OperadoraCartaoFormView subView)
			throws DotErpException {
		ContaCaixa contaCaixa = (ContaCaixa) subView.getMocContaCaixa()
				.getValue();

		if (ObjectUtils.isBlank(contaCaixa)) {
			throw new DotErpException(subView.getMocContaCaixa(),
					"::DotERP - Não pode ficar em branco");
		}

		String bandeira = subView.getTfBandeira().getValue();

		if (StringUtils.isBlank(bandeira)) {
			throw new DotErpException(subView.getTfBandeira(),
					"::DotERP - Não pode ficar em branco");
		}

		String nome = subView.getTfNome().getValue();

		if (StringUtils.isBlank(nome)) {
			throw new DotErpException(subView.getTfNome(),
					"::DotERP - Não pode ficar em branco");
		}

		String vencimentoAluguel = subView.getTfVencimentoAluguel().getValue();

		if (StringUtils.isBlank(vencimentoAluguel)) {
			throw new DotErpException(subView.getTfVencimentoAluguel(),
					"::DotERP - Não pode ficar em branco");
		}
	}

}