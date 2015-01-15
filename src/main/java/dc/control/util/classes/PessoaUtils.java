package dc.control.util.classes;

import java.util.Date;

import dc.control.enums.TipoPessoaEn;
import dc.control.util.DateUtils;
import dc.control.util.NumberUtils;
import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.entidade.contabilidade.ContabilContaEntity;
import dc.entidade.geral.pessoal.AtividadeForCliEntity;
import dc.entidade.geral.pessoal.EstadoCivilEntity;
import dc.entidade.geral.pessoal.SituacaoForCliEntity;
import dc.visao.geral.pessoal.PessoaFormView;

public class PessoaUtils {

	public static void validateRequiredFields(PessoaFormView subView)
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

		//

		if (tipoPessoaEn.equals(TipoPessoaEn.F)) {
			EstadoCivilEntity estadoCivil = subView.getMocEstadoCivil()
					.getValue();

			if (ObjectUtils.isBlank(estadoCivil)) {
				throw new DotErpException(subView.getCbTipoPessoa(),
						"::DotERP - Não pode ficar em branco");
			}
		}

		//

		if (subView.getCkCliente().getValue()) {
			System.out.println(":: valida cliente");

			Date dataDesde = (Date) subView.getPdfClienteDesde().getValue();

			if (DateUtils.isBlank(dataDesde)) {
				throw new DotErpException(subView.getPdfClienteDesde(),
						"::DotERP - Não pode ficar em branco");
			}

			String contaTomador = subView.getTfClienteContaTomador().getValue();

			if (StringUtils.isBlank(contaTomador)) {
				throw new DotErpException(subView.getTfClienteContaTomador(),
						"::DotERP - Não pode ficar em branco");
			}

			String limiteCredito = subView.getTfClienteLimiteCredito()
					.getConvertedValue().toString();

			if (StringUtils.isBlank(limiteCredito)) {
				throw new DotErpException(subView.getTfClienteLimiteCredito(),
						"::DotERP - Não pode ficar em branco");
			}

			SituacaoForCliEntity situacao = subView.getMocClienteSituacao()
					.getValue();

			if (ObjectUtils.isBlank(situacao)) {
				throw new DotErpException(subView.getMocClienteSituacao(),
						"::DotERP - Não pode ficar em branco");
			}

			AtividadeForCliEntity atividade = subView.getMocClienteAtividade()
					.getValue();

			if (ObjectUtils.isBlank(atividade)) {
				throw new DotErpException(subView.getMocClienteAtividade(),
						"::DotERP - Não pode ficar em branco");
			}
		}

		//

		if (subView.getCkColaborador().getValue()) {
			System.out.println(":: valida colaborador");

			String matricula = subView.getTfColaboradorMatricula().getValue();

			if (StringUtils.isBlank(matricula)) {
				throw new DotErpException(subView.getTfColaboradorMatricula(),
						"::DotERP - Não pode ficar em branco");
			}

			String categoria = subView.getTfColaboradorCategoriaSefip()
					.getValue();

			if (StringUtils.isBlank(categoria)) {
				throw new DotErpException(
						subView.getTfColaboradorCategoriaSefip(),
						"::DotERP - Não pode ficar em branco");
			}
		}

		//

		if (subView.getCkFornecedor().getValue()) {
			System.out.println(":: valida fornecedor");

			AtividadeForCliEntity atividadeForCli = subView
					.getMocFornecedorAtividadeForCli().getValue();

			if (ObjectUtils.isBlank(atividadeForCli)) {
				throw new DotErpException(
						subView.getMocFornecedorAtividadeForCli(),
						"::DotERP - Não pode ficar em branco");
			}

			SituacaoForCliEntity situacaoForCli = subView
					.getMocFornecedorSituacaoForCli().getValue();

			if (ObjectUtils.isBlank(situacaoForCli)) {
				throw new DotErpException(
						subView.getMocFornecedorSituacaoForCli(),
						"::DotERP - Não pode ficar em branco");
			}

			ContabilContaEntity contabilConta = subView
					.getMocFornecedorContabilConta().getValue();

			if (ObjectUtils.isBlank(contabilConta)) {
				throw new DotErpException(
						subView.getMocFornecedorContabilConta(),
						"::DotERP - Não pode ficar em branco");
			}

			Date desde = subView.getPdfFornecedorDesde().getValue();

			if (DateUtils.isBlank(desde)) {
				throw new DotErpException(subView.getPdfFornecedorDesde(),
						"::DotERP - Não pode ficar em branco");
			}

			String contaRemetente = subView.getTfFornecedorContaRemetente()
					.getValue();

			if (StringUtils.isBlank(contaRemetente)) {
				throw new DotErpException(
						subView.getTfFornecedorContaRemetente(),
						"::DotERP - Não pode ficar em branco");
			}

			String chequeNominalA = subView.getTfFornecedorChequeNominalA()
					.getValue();

			if (StringUtils.isBlank(chequeNominalA)) {
				throw new DotErpException(
						subView.getTfFornecedorChequeNominalA(),
						"::DotERP - Não pode ficar em branco");
			}

			Object geraFaturamento = subView.getCbFornecedorGeraFaturamento()
					.getValue();

			if (ObjectUtils.isBlank(geraFaturamento)) {
				throw new DotErpException(
						subView.getCbFornecedorGeraFaturamento(),
						"::DotERP - Não pode ficar em branco");
			}

			Object localizacao = subView.getCbFornecedorLocalizacao()
					.getValue();

			if (ObjectUtils.isBlank(localizacao)) {
				throw new DotErpException(subView.getCbFornecedorLocalizacao(),
						"::DotERP - Não pode ficar em branco");
			}

			Object optanteSimples = subView.getCbFornecedorOptanteSimples()
					.getValue();

			if (ObjectUtils.isBlank(optanteSimples)) {
				throw new DotErpException(
						subView.getCbFornecedorOptanteSimples(),
						"::DotERP - Não pode ficar em branco");
			}

			Object sofreRetencao = subView.getCbFornecedorSofreRetencao()
					.getValue();

			if (ObjectUtils.isBlank(sofreRetencao)) {
				throw new DotErpException(
						subView.getCbFornecedorSofreRetencao(),
						"::DotERP - Não pode ficar em branco");
			}

			String numDiasIntervalo = subView.getTfFornecedorNumDiasIntervalo()
					.getValue();

			if (StringUtils.isBlank(numDiasIntervalo)) {
				throw new DotErpException(
						subView.getTfFornecedorNumDiasIntervalo(),
						"::DotERP - Não pode ficar em branco");
			}

			String prazoMedioEntrega = subView
					.getTfFornecedorPrazoMedioEntrega().getValue();

			if (StringUtils.isBlank(prazoMedioEntrega)) {
				throw new DotErpException(
						subView.getTfFornecedorPrazoMedioEntrega(),
						"::DotERP - Não pode ficar em branco");
			}

			String numDiasPrimeiroVenc = subView
					.getTfFornecedorNumDiasPrimeiroVenc().getValue();

			if (StringUtils.isBlank(numDiasPrimeiroVenc)) {
				throw new DotErpException(
						subView.getTfFornecedorNumDiasPrimeiroVenc(),
						"::DotERP - Não pode ficar em branco");
			}

			String quantidadeParcelas = subView
					.getTfFornecedorQuantidadeParcelas().getValue();

			if (StringUtils.isBlank(quantidadeParcelas)) {
				throw new DotErpException(
						subView.getTfFornecedorQuantidadeParcelas(),
						"::DotERP - Não pode ficar em branco");
			}
		}

		//

		if (subView.getCkTransportadora().getValue()) {
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
	}

	//

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