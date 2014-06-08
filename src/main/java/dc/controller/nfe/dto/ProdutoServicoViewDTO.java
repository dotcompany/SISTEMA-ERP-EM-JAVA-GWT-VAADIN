package dc.controller.nfe.dto;

import java.io.Serializable;

import dc.control.converter.ObjectConverter;
import dc.control.enums.CsosnEn;
import dc.control.enums.CstIcmsEn;
import dc.entidade.nfe.NfeCabecalhoEntity;
import dc.entidade.nfe.NfeDetEspecificoCombustivelEntity;
import dc.entidade.nfe.NfeDetEspecificoVeiculoEntity;
import dc.entidade.nfe.NfeDetalheEntity;
import dc.entidade.nfe.NfeDetalheImpIpiEntity;
import dc.entidade.nfe.NfeDetalheImpostoCofinsEntity;
import dc.entidade.nfe.NfeDetalheImpostoIcmsEntity;
import dc.entidade.nfe.NfeDetalheImpostoIiEntity;
import dc.entidade.nfe.NfeDetalheImpostoIssqnEntity;
import dc.entidade.nfe.NfeDetalheImpostoPisEntity;
import dc.entidade.pessoal.Cliente;
import dc.entidade.tributario.OperacaoFiscal;
import dc.visao.nfe.ProdutoServicoFormView;

public class ProdutoServicoViewDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static synchronized void setNfeCabecalhoSubView(
			NfeCabecalhoEntity nfeCabecalho, ProdutoServicoFormView subView) {
		OperacaoFiscal operacaoFiscal = nfeCabecalho.getTributOperacaoFiscal();

		if (operacaoFiscal != null) {
			subView.getMtoOperacaoFiscal().setValue(operacaoFiscal);
			// this.subView.getTfOperacaoFiscalId().setValue(
			// operacaoFiscal.getDescricaoNaNF());
		}

		// this.subView.getTfVenda().setValue(nfeCabecalho.getVendaCabecalho().toString());
		subView.getTfModeloNotaFiscal().setValue(
				nfeCabecalho.getCodigoModelo().trim());
		subView.getTfNaturezaOperacao().setValue(
				nfeCabecalho.getNaturezaOperacao().trim());
		subView.getTfChaveAcesso().setValue(
				nfeCabecalho.getChaveAcesso().trim());
		subView.getTfDigitoChaveAcesso().setValue(
				nfeCabecalho.getDigitoChaveAcesso().trim());
		subView.getTfCodigoNumerico().setValue(
				nfeCabecalho.getCodigoNumerico().trim());
		subView.getTfSerie().setValue(nfeCabecalho.getSerie().trim());
		subView.getTfNumero().setValue(nfeCabecalho.getNumero().trim());
		subView.getPdfDataEmissao().setValue(nfeCabecalho.getDataEmissao());
		subView.getPdfDataEntradaSaida().setValue(
				nfeCabecalho.getDataEntradaSaida());
		subView.getTfHoraEntradaSaida().setValue(
				nfeCabecalho.getHoraEntradaSaida());
		subView.getTfTipoOperacao().setValue(
				nfeCabecalho.getTipoOperacao().trim());
		// this.subView.getTfTipoEmissao().setValue(
		// this.nfeCabecalho.getTipoEmissao().trim());
		// this.subView.getTfFinalidadeEmissao().setValue(
		// this.nfeCabecalho.getFinalidadeEmissao().trim());
		// this.subView.getTfFormatoImpressaoDanfe().setValue(
		// this.nfeCabecalho.getFormatoImpressaoDanfe().trim());
		// this.subView.getTfFormaPagamento().setValue(
		// this.nfeCabecalho.getIndicadorFormaPagamento().trim());

		//
		subView.getPlNfeDetalheSubForm().setCaption(
				"NFE CABEÇALHO " + nfeCabecalho.getNumero());
	}

	public static synchronized void setNfeDestinatarioSubView(
			NfeCabecalhoEntity nfeCabecalho, ProdutoServicoFormView subView)
			throws Exception {
		subView.getTfEmailDestinatario().setValue(
				nfeCabecalho.getNfeDestinatario().getEmail());
		subView.getTfSuframaDestinatario().setValue(
				nfeCabecalho.getNfeDestinatario().getSuframa());
		subView.getTfTelefoneDestinatario().setValue(
				nfeCabecalho.getNfeDestinatario().getTelefone());
		subView.getTfInscricaoEstadualDestinatario().setValue(
				nfeCabecalho.getNfeDestinatario().getInscricaoEstadual());
		subView.getTfUfDestinatario().setValue(
				nfeCabecalho.getNfeDestinatario().getUf());
		// this.subView.getTfCidadeDestinatario().setValue(this.nfeCabecalho.getNfeDestinatario().get);
		// this.subView.getTfCodigoIbgeDestinatario().setValue(this.nfeCabecalho.getNfeDestinatario().getco);
		subView.getTfBairroLogradouroDestinatario().setValue(
				nfeCabecalho.getNfeDestinatario().getBairro());
		subView.getTfLogradouroComplementoDestinatario().setValue(
				nfeCabecalho.getNfeDestinatario().getComplemento());
		subView.getTfLogradouroNumeroDestinatario().setValue(
				nfeCabecalho.getNfeDestinatario().getNumero());
		subView.getTfLogradouroDestinatario().setValue(
				nfeCabecalho.getNfeDestinatario().getLogradouro());
		subView.getTfCepDestinatario().setValue(
				nfeCabecalho.getNfeDestinatario().getCep());
		subView.getTfRazaoSocialDestinatario().setValue(
				nfeCabecalho.getNfeDestinatario().getRazaoSocial());
		subView.getTfCpfCnpjDestinatario().setValue(
				nfeCabecalho.getNfeDestinatario().getCpfCnpj());
		// this.subView.getTfIdDestinatario().setValue();

		Cliente cliente = nfeCabecalho.getCliente();

		if (cliente != null) {
			subView.getMtoCliente().setValue(nfeCabecalho.getCliente());
		}
	}

	public static synchronized void setNfeDetalheListSubView(
			NfeCabecalhoEntity nfeCabecalho, ProdutoServicoFormView subView)
			throws Exception {
		subView.carregarSfNfeDetalhe(nfeCabecalho.getNfeDetalheList());
	}

	public static synchronized void setNfeDetalheSubView(
			NfeDetalheEntity nfeDetalhe, ProdutoServicoFormView subView)
			throws Exception {
		subView.getTfNumeroItem().setValue(
				nfeDetalhe.getNumeroItem().toString());
		subView.getTfGtin().setValue(nfeDetalhe.getGtin());

		if (nfeDetalhe.getProduto() != null) {
			subView.getMtoProduto().setValue(nfeDetalhe.getProduto());
			subView.getTfCodigoProduto().setValue(
					nfeDetalhe.getProduto().getCodigoInterno());
		} else {
			subView.getTfCodigoProduto().setValue("");
		}

		subView.getTfNcm().setValue(nfeDetalhe.getNcm());
		subView.getTfExTipi().setValue(nfeDetalhe.getExTipi().toString());
		subView.getTfCfop().setValue(nfeDetalhe.getCfop().toString());
		subView.getTfUnidadeComercial().setValue(
				nfeDetalhe.getUnidadeComercial());
		subView.getTfQuantidadeComercial().setValue(
				ObjectConverter.valueToString(nfeDetalhe
						.getQuantidadeComercial()));
		subView.getTfValorUnitarioComercial().setValue(
				nfeDetalhe.getValorUnitarioComercial().toString());
		subView.getTfValorBrutoProduto().setValue(
				nfeDetalhe.getValorBrutoProduto().toString());
		subView.getTfGtinUnidadeTributavel().setValue(
				nfeDetalhe.getGtinUnidadeTributavel());
		subView.getTfUnidadeTributavel().setValue(
				nfeDetalhe.getUnidadeTributavel());
		subView.getTfQuantidadeTributavel().setValue(
				nfeDetalhe.getQuantidadeTributavel().toString());
		subView.getTfValorUnitarioTributavel().setValue(
				nfeDetalhe.getValorUnitarioTributavel().toString());
		subView.getTfValorFrete().setValue(
				nfeDetalhe.getValorFrete().toString());
		subView.getTfValorSeguro().setValue(
				nfeDetalhe.getValorSeguro().toString());
		subView.getTfValorDesconto().setValue(
				nfeDetalhe.getValorDesconto().toString());
		subView.getTfValorOutrasDespesas().setValue(
				nfeDetalhe.getValorOutrasDespesas().toString());
		subView.getTfEntraTotal().setValue(nfeDetalhe.getEntraTotal());
		subView.getTfValorSubtotal().setValue(
				nfeDetalhe.getValorSubtotal().toString());
		subView.getTfValorTotal().setValue(
				nfeDetalhe.getValorTotal().toString());
		subView.getTfNumeroPedidoCompra().setValue(
				nfeDetalhe.getNumeroPedidoCompra());
		subView.getTfItemPedidoCompra().setValue(
				nfeDetalhe.getItemPedidoCompra().toString());
		subView.getTfInformacoesAdicionais().setValue(
				nfeDetalhe.getInformacoesAdicionais());
	}

	public static synchronized void setNdiCofinsSubView(
			NfeDetalheEntity nfeDetalhe, ProdutoServicoFormView subView)
			throws Exception {
		NfeDetalheImpostoCofinsEntity ndiCofins = nfeDetalhe
				.getNfeDetalheImpostoCofins();

		// if (entCofins == null) {
		// entCofins = new NfeDetalheImpostoCofinsEntity();
		// entCofins.setNfeDetalhe(this.nfeCabecalho.getNfeDetalhe());

		// this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpostoCofins(
		// entCofins);
		// }

		subView.getPlNdiCofins().setCaption(
				"NFE DETALHE: " + nfeDetalhe.getNumeroItem());

		// this.subView.getTfCstCofins().setValue(
		// entCofins.getCstCofins().trim());
		subView.getTfQtdVendidaCofins().setValue(
				ndiCofins.getQuantidadeVendida().toString().trim());
		subView.getTfBaseCalculoBcCofins().setValue(
				ndiCofins.getBaseCalculoCofins().toString().trim());
		subView.getTfAliquotaPercentualCofins().setValue(
				ndiCofins.getAliquotaCofinsPercentual().toString().trim());
		subView.getTfAliquotaReaisCofins().setValue(
				ndiCofins.getAliquotaCofinsReais().toString().trim());
		subView.getTfValorCofins().setValue(
				ndiCofins.getValorCofins().toString().trim());
	}

	public static synchronized void setNdiIcmsSubView(
			NfeDetalheEntity nfeDetalhe, ProdutoServicoFormView subView)
			throws Exception {
		NfeDetalheImpostoIcmsEntity entIcms = nfeDetalhe
				.getNfeDetalheImpostoIcms();

		// if (entIcms == null) {
		// entIcms = new NfeDetalheImpostoIcmsEntity();
		// entIcms.setNfeDetalhe(this.nfeCabecalho.getNfeDetalhe());

		// this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpostoIcms(entIcms);
		// }

		subView.getPlNdiIcms().setCaption(
				"NFE DETALHE: " + nfeDetalhe.getNumeroItem());

		subView.getTfOrigemMercadoriaIcms().setValue(
				entIcms.getOrigemMercadoria().trim());

		// this.subView.getCbCstIcms().setValue(entIcms.getCstIcms() == null
		// ? null : CstIcmsEn.valueOf("_"
		// + entIcms.getCstIcms()));

		if (!entIcms.getCstIcms().isEmpty()) {
			CstIcmsEn cstIcms = CstIcmsEn.valueOf("_" + entIcms.getCstIcms());

			subView.getCbCstIcms().setValue(cstIcms);
		} else {
			subView.getCbCstIcms().setValue(null);
		}

		if (!entIcms.getCsosn().isEmpty()) {
			CsosnEn csosn = CsosnEn.valueOf("_" + entIcms.getCsosn());

			subView.getCbCsosnIcms().setValue(csosn);
		} else {
			subView.getCbCsosnIcms().setValue(null);
		}

		subView.getTfModalidadeBcIcms().setValue(
				entIcms.getModalidadeBcIcms().trim());
		subView.getTfTaxaReducaoBcIcms().setValue(
				entIcms.getTaxaReducaoBcIcms().toString().trim());
		subView.getTfBaseCalculoBcIcms().setValue(
				entIcms.getBaseCalculoIcms().toString().trim());
		subView.getTfAliquotaIcms().setValue(
				entIcms.getAliquotaIcms().toString().trim());
		subView.getTfValorIcms().setValue(
				entIcms.getValorIcms().toString().trim());
		subView.getTfMotivoDesoneracaoIcms().setValue(
				entIcms.getMotivoDesoneracaoIcms().trim());
		subView.getTfModalidadeBcStIcms().setValue(
				entIcms.getModalidadeBcIcmsSt().trim());
		subView.getTfPercentualMvaStIcms().setValue(
				entIcms.getPercentualMvaIcmsSt().toString().trim());
		// this.subView.getTfTaxaReducaoBcStIcms().setValue(entIcms.get);
		subView.getTfBaseCalculoStIcms().setValue(
				entIcms.getValorBaseCalculoIcmsSt().toString().trim());
		subView.getTfAliquotaStIcms().setValue(
				entIcms.getAliquotaIcmsSt().toString().trim());
		subView.getTfValorStIcms().setValue(
				entIcms.getValorIcmsSt().toString().trim());
		subView.getTfBcStRetidoIcms().setValue(
				entIcms.getValorBcIcmsStRetido().toString().trim());
		subView.getTfValorStRetidoIcms().setValue(
				entIcms.getValorIcmsStRetido().toString().trim());
		subView.getTfBcStDestinoIcms().setValue(
				entIcms.getValorBcIcmsStDestino().toString().trim());
		subView.getTfValorStDestinoIcms().setValue(
				entIcms.getValorIcmsStDestino().toString().trim());
		subView.getTfAliquotaCreditoSnIcms().setValue(
				entIcms.getAliquotaCreditoIcmsSn().toString().trim());
		subView.getTfValorCreditoSnIcms().setValue(
				entIcms.getValorCreditoIcmsSn().toString().trim());
		subView.getTfPercentualBcOperacaoPropriaIcms().setValue(
				entIcms.getPercentualBcOperacaoPropria().toString().trim());
		subView.getTfUfStIcms().setValue(entIcms.getUfSt().trim());
	}

	public static synchronized void setNdiIiSubView(
			NfeDetalheEntity nfeDetalhe, ProdutoServicoFormView subView)
			throws Exception {
		NfeDetalheImpostoIiEntity entIi = nfeDetalhe.getNfeDetalheImpostoIi();

		// if (entIi == null) {
		// entIi = new NfeDetalheImpostoIiEntity();
		// entIi.setNfeDetalhe(this.nfeCabecalho.getNfeDetalhe());

		// this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpostoIi(entIi);
		// }

		subView.getPlNdiIi().setCaption(
				"NFE DETALHE: " + nfeDetalhe.getNumeroItem());

		subView.getTfBaseCalculoBcImpostoImportacao().setValue(
				entIi.getValorBcIi().toString().trim());
		subView.getTfDespesasAduaneirasImpostoImportacao().setValue(
				entIi.getValorDespesasAduaneiras().toString().trim());
		subView.getTfValorImpostoImportacao().setValue(
				entIi.getValorImpostoImportacao().toString().trim());
		subView.getTfIofImpostoImportacao().setValue(
				entIi.getValorIof().toString().trim());
	}

	public static synchronized void setNdiIpiSubView(
			NfeDetalheEntity nfeDetalhe, ProdutoServicoFormView subView)
			throws Exception {
		// NfeDetalheImpostoIpiEntity ndiIpi = new
		// NfeDetalheImpostoIpiEntity();

		NfeDetalheImpIpiEntity ndiIpi = nfeDetalhe.getNfeDetalheImpIpi();

		// if (ndiIpi == null) {
		// ndiIpi = new NfeDetalheImpIpiEntity();
		// ndiIpi.setNfeDetalhe(this.nfeCabecalho.getNfeDetalhe());

		// this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpIpi(ndiIpi);
		// }

		subView.getPlNdiIpi().setCaption(
				"NFE DETALHE: " + nfeDetalhe.getNumeroItem());

		// this.subView.getTfCstIpi().setValue(ndiIpi.getCstIpi());
		subView.getTfBaseCalculoBcIpi().setValue(
				ndiIpi.getValorBaseCalculoIpi().toString());
		subView.getTfAliquotaIpi().setValue(ndiIpi.getAliquotaIpi().toString());
		subView.getTfQtdUndTributavelIpi().setValue(
				ndiIpi.getQuantidadeUnidadeTributavel().toString());
		subView.getTfValorUndTributavelIpi().setValue(
				ndiIpi.getValorUnidadeTributavel().toString());
		subView.getTfValorIpi().setValue(ndiIpi.getValorIpi().toString());
		subView.getTfEnquadramentoIpi().setValue(ndiIpi.getEnquadramentoIpi());
		subView.getTfEnquadramentoLegalIpi().setValue(
				ndiIpi.getEnquadramentoLegalIpi());
		subView.getTfCnpjProdutorIpi().setValue(ndiIpi.getCnpjProdutorIpi());
		subView.getTfQtdSeloIpi().setValue(
				ndiIpi.getQuantidadeSeloIpi().toString());
		subView.getTfCodigoSeloIpi().setValue(ndiIpi.getCodigoSeloIpi());
	}

	public static synchronized void setNdiIssqnSubView(
			NfeDetalheEntity nfeDetalhe, ProdutoServicoFormView subView)
			throws Exception {
		NfeDetalheImpostoIssqnEntity entIssqn = nfeDetalhe
				.getNfeDetalheImpostoIssqn();

		// if (entIssqn == null) {
		// entIssqn = new NfeDetalheImpostoIssqnEntity();
		// entIssqn.setNfeDetalhe(this.nfeCabecalho.getNfeDetalhe());

		// this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpostoIssqn(
		// entIssqn);
		// }

		subView.getPlNdiIssqn().setCaption(
				"NFE DETALHE: " + nfeDetalhe.getNumeroItem());

		subView.getTfBaseCalculoBcIssqn().setValue(
				entIssqn.getBaseCalculoIssqn().toString().trim());
		subView.getTfAliquotaIssqn().setValue(
				entIssqn.getAliquotaIssqn().toString().trim());
		subView.getTfValorIssqn().setValue(
				entIssqn.getValorIssqn().toString().trim());
		subView.getTfMunicipioIssqn().setValue(
				entIssqn.getMunicipioIssqn().toString().trim());
		subView.getTfItemListaServicosIssqn().setValue(
				entIssqn.getItemListaServicos().toString().trim());
		subView.getTfTributacaoIssqn().setValue(
				entIssqn.getTributacaoIssqn().trim());
	}

	public static synchronized void setNdiPisSubView(
			NfeDetalheEntity nfeDetalhe, ProdutoServicoFormView subView)
			throws Exception {
		NfeDetalheImpostoPisEntity entPis = nfeDetalhe
				.getNfeDetalheImpostoPis();

		// if (entPis == null) {
		// entPis = new NfeDetalheImpostoPisEntity();
		// entPis.setNfeDetalhe(this.nfeCabecalho.getNfeDetalhe());

		// this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpostoPis(entPis);
		// }

		// this.subView.getTfCstPis().setValue(entPis.getCstPis().trim());

		subView.getPlNdiPis().setCaption(
				"NFE DETALHE: " + nfeDetalhe.getNumeroItem());

		subView.getTfQtdVendidaPis().setValue(
				entPis.getQuantidadeVendida().toString().trim());
		subView.getTfBaseCalculoBcPis().setValue(
				entPis.getValorBaseCalculoPis().toString().trim());
		subView.getTfAliquotaPercentualPis().setValue(
				entPis.getAliquotaPisPercentual().toString().trim());
		subView.getTfAliquotaReaisPis().setValue(
				entPis.getAliquotaPisReais().toString().trim());
		subView.getTfValorPis()
				.setValue(entPis.getValorPis().toString().trim());
	}

	public static synchronized void setNdeCombustivelSubView(
			NfeDetalheEntity nfeDetalhe, ProdutoServicoFormView subView)
			throws Exception {
		NfeDetEspecificoCombustivelEntity entCombustivel = nfeDetalhe
				.getNfeDetEspecificoCombustivel();

		// if (entCombustivel == null) {
		// entCombustivel = new NfeDetEspecificoCombustivelEntity();
		// entCombustivel.setNfeDetalhe(this.nfeCabecalho.getNfeDetalhe());

		// this.nfeCabecalho.getNfeDetalhe()
		// .setNfeDetEspecificoCombustivel(entCombustivel);
		// }

		subView.getPlNdeCombustivel().setCaption(
				"NFE DETALHE: " + nfeDetalhe.getNumeroItem());

		subView.getTfCodigoAnpCombustivel().setValue(
				entCombustivel.getCodigoAnp().toString().trim());
		subView.getTfCodifCombustivel().setValue(
				entCombustivel.getCodif().trim());
		subView.getTfQtdeTempAmbienteCombustivel().setValue(
				entCombustivel.getQuantidadeTempAmbiente().toString().trim());
		subView.getTfUfConsumoCombustivel().setValue(
				entCombustivel.getUfConsumo().trim());
		subView.getTfBcCideCombustivel().setValue(
				entCombustivel.getBaseCalculoCide().toString().trim());
		subView.getTfAliquotaCideCombustivel().setValue(
				entCombustivel.getAliquotaCide().toString().trim());
		subView.getTfValorCideCombustivel().setValue(
				entCombustivel.getValorCide().toString().trim());
	}

	public static synchronized void setNdeVeiculoSubView(
			NfeDetalheEntity nfeDetalhe, ProdutoServicoFormView subView)
			throws Exception {
		NfeDetEspecificoVeiculoEntity entVeiculo = nfeDetalhe
				.getNfeDetEspecificoVeiculo();

		// if (entVeiculo == null) {
		// entVeiculo = new NfeDetEspecificoVeiculoEntity();
		// entVeiculo.setNfeDetalhe(this.nfeCabecalho.getNfeDetalhe());

		// this.nfeCabecalho.getNfeDetalhe().setNfeDetEspecificoVeiculo(
		// entVeiculo);
		// }

		subView.getPlNdeVeiculo().setCaption(
				"NFE DETALHE: " + nfeDetalhe.getNumeroItem());

		subView.getTfTipoOperacaoVeiculo().setValue(
				entVeiculo.getTipoOperacao());
		subView.getTfChassiVeiculo().setValue(entVeiculo.getChassi());
		subView.getTfCodigoCorVeiculo().setValue(entVeiculo.getCodigoCor());
		subView.getTfDescricaoCorVeiculo().setValue(
				entVeiculo.getDescricaoCor());
		subView.getTfPotenciaMotorVeiculo().setValue(
				entVeiculo.getPotenciaMotor());
		subView.getTfCilindradasVeiculo().setValue(entVeiculo.getCilindradas());
		subView.getTfPesoLiquidoVeiculo().setValue(entVeiculo.getPesoLiquido());
		subView.getTfPesoBrutoVeiculo().setValue(entVeiculo.getPesoBruto());
		subView.getTfNumeroSerieVeiculo().setValue(entVeiculo.getNumeroSerie());
		subView.getTfCombustivelVeiculo().setValue(
				entVeiculo.getTipoCombustivel());
		subView.getTfNumeroMotorVeiculo().setValue(entVeiculo.getNumeroMotor());
		subView.getTfCapacidadeTracaoVeiculo().setValue(
				entVeiculo.getCapacidadeMaximaTracao());
		subView.getTfDistanciaEixosVeiculo().setValue(
				entVeiculo.getDistanciaEixos());
		subView.getTfAnoModeloVeiculo().setValue(entVeiculo.getAnoModelo());
		subView.getTfAnoFabricacaoVeiculo().setValue(
				entVeiculo.getAnoFabricacao());
		subView.getTfTipoPinturaVeiculo().setValue(entVeiculo.getTipoPintura());
		subView.getTfTipoVeiculo().setValue(entVeiculo.getTipoVeiculo());
		subView.getTfEspecieVeiculo().setValue(entVeiculo.getEspecieVeiculo());
		subView.getTfCondicaoVinVeiculo().setValue(entVeiculo.getCondicaoVin());
		subView.getTfCondicaoVeiculo()
				.setValue(entVeiculo.getCondicaoVeiculo());
		subView.getTfCodigoMarcaModeloVeiculo().setValue(
				entVeiculo.getCodigoMarcaModelo());
		subView.getTfCodigoCorDenatranVeiculo().setValue(entVeiculo.getCor());
		subView.getTfLotacaoVeiculo().setValue(
				entVeiculo.getLotacao().toString());
		subView.getTfRestricaoVeiculo().setValue(entVeiculo.getRestricao());
	}

	/**
	 * LIMPAR
	 * 
	 * @param item
	 */

	public static synchronized void subViewClean(ProdutoServicoFormView subView) {
		NfeDetalheEntity nfeDetalhe = new NfeDetalheEntity();

		subView.getTfNumeroItem().setValue(
				nfeDetalhe.getNumeroItem().toString());
		subView.getTfCodigoProduto().setValue(nfeDetalhe.getCodigoProduto());
		subView.getTfGtin().setValue(nfeDetalhe.getGtin());
		// this.subView.getTfNomeProduto().setValue(nfeDetalhe.getNomeProduto());
		// this.subView.getCbLivro().setValue(this.pEntity.getLivro());
		// this.subView.getMtoProduto().setValue(new Produto());

		// this.subView.getMtoProduto().setValue(nfeDetalhe.getProduto());
		subView.getTfNcm().setValue(nfeDetalhe.getNcm());
		subView.getTfExTipi().setValue(nfeDetalhe.getExTipi().toString());
		subView.getTfCfop().setValue(nfeDetalhe.getCfop().toString());
		subView.getTfUnidadeComercial().setValue(
				nfeDetalhe.getUnidadeComercial());
		subView.getTfQuantidadeComercial().setValue(
				nfeDetalhe.getQuantidadeComercial().toString());
		subView.getTfValorUnitarioComercial().setValue(
				nfeDetalhe.getValorUnitarioComercial().toString());
		subView.getTfValorBrutoProduto().setValue(
				nfeDetalhe.getValorBrutoProduto().toString());
		subView.getTfGtinUnidadeTributavel().setValue(
				nfeDetalhe.getGtinUnidadeTributavel());
		subView.getTfUnidadeTributavel().setValue(
				nfeDetalhe.getUnidadeTributavel());
		subView.getTfQuantidadeTributavel().setValue(
				nfeDetalhe.getQuantidadeTributavel().toString());
		subView.getTfValorUnitarioTributavel().setValue(
				nfeDetalhe.getValorUnitarioTributavel().toString());
		subView.getTfValorFrete().setValue(
				nfeDetalhe.getValorFrete().toString());
		subView.getTfValorSeguro().setValue(
				nfeDetalhe.getValorSeguro().toString());
		subView.getTfValorDesconto().setValue(
				nfeDetalhe.getValorDesconto().toString());
		subView.getTfValorOutrasDespesas().setValue(
				nfeDetalhe.getValorOutrasDespesas().toString());
		subView.getTfEntraTotal().setValue(nfeDetalhe.getEntraTotal());
		subView.getTfValorSubtotal().setValue(
				nfeDetalhe.getValorSubtotal().toString());
		subView.getTfValorTotal().setValue(
				nfeDetalhe.getValorTotal().toString());
		subView.getTfNumeroPedidoCompra().setValue(
				nfeDetalhe.getNumeroPedidoCompra());
		subView.getTfItemPedidoCompra().setValue(
				nfeDetalhe.getItemPedidoCompra().toString());
		subView.getTfInformacoesAdicionais().setValue(
				nfeDetalhe.getInformacoesAdicionais());

		/**
		 * 
		 */

		NfeDetalheImpostoCofinsEntity ndiCofins = new NfeDetalheImpostoCofinsEntity();

		// this.subView.getTfCstCofins().setValue(entCofins.getCstCofins());
		subView.getTfQtdVendidaCofins().setValue(
				ndiCofins.getQuantidadeVendida().toString());
		subView.getTfBaseCalculoBcCofins().setValue(
				ndiCofins.getBaseCalculoCofins().toString());
		subView.getTfAliquotaPercentualCofins().setValue(
				ndiCofins.getAliquotaCofinsPercentual().toString());
		subView.getTfAliquotaReaisCofins().setValue(
				ndiCofins.getAliquotaCofinsReais().toString());
		subView.getTfValorCofins().setValue(
				ndiCofins.getValorCofins().toString());

		/**
		 * 
		 */

		NfeDetalheImpostoIcmsEntity entIcms = new NfeDetalheImpostoIcmsEntity();

		subView.getTfOrigemMercadoriaIcms().setValue(
				entIcms.getOrigemMercadoria());
		// this.subView.getTfCstIcms().setValue(entIcms.getCstIcms());
		// this.subView.getTfCsosnIcms().setValue(entIcms.getCsosn());
		subView.getTfModalidadeBcIcms().setValue(entIcms.getModalidadeBcIcms());
		subView.getTfTaxaReducaoBcIcms().setValue(
				entIcms.getTaxaReducaoBcIcms().toString());
		subView.getTfBaseCalculoBcIcms().setValue(
				entIcms.getBaseCalculoIcms().toString());
		subView.getTfAliquotaIcms().setValue(
				entIcms.getAliquotaIcms().toString());
		subView.getTfValorIcms().setValue(entIcms.getValorIcms().toString());
		subView.getTfMotivoDesoneracaoIcms().setValue(
				entIcms.getMotivoDesoneracaoIcms());
		subView.getTfModalidadeBcStIcms().setValue(
				entIcms.getModalidadeBcIcmsSt());
		subView.getTfPercentualMvaStIcms().setValue(
				entIcms.getPercentualMvaIcmsSt().toString());
		// this.subView.getTfTaxaReducaoBcStIcms().setValue(entIcms.get);
		subView.getTfBaseCalculoStIcms().setValue(
				entIcms.getValorBaseCalculoIcmsSt().toString());
		subView.getTfAliquotaStIcms().setValue(
				entIcms.getAliquotaIcmsSt().toString());
		subView.getTfValorStIcms()
				.setValue(entIcms.getValorIcmsSt().toString());
		subView.getTfBcStRetidoIcms().setValue(
				entIcms.getValorBcIcmsStRetido().toString());
		subView.getTfValorStRetidoIcms().setValue(
				entIcms.getValorIcmsStRetido().toString());
		subView.getTfBcStDestinoIcms().setValue(
				entIcms.getValorBcIcmsStDestino().toString());
		subView.getTfValorStDestinoIcms().setValue(
				entIcms.getValorIcmsStDestino().toString());
		subView.getTfAliquotaCreditoSnIcms().setValue(
				entIcms.getAliquotaCreditoIcmsSn().toString());
		subView.getTfValorCreditoSnIcms().setValue(
				entIcms.getValorCreditoIcmsSn().toString());
		subView.getTfPercentualBcOperacaoPropriaIcms().setValue(
				entIcms.getPercentualBcOperacaoPropria().toString());
		subView.getTfUfStIcms().setValue(entIcms.getUfSt());

		/**
		 * 
		 */

		NfeDetalheImpostoIiEntity ndiIi = new NfeDetalheImpostoIiEntity();

		subView.getTfBaseCalculoBcImpostoImportacao().setValue(
				ndiIi.getValorBcIi().toString());
		subView.getTfDespesasAduaneirasImpostoImportacao().setValue(
				ndiIi.getValorDespesasAduaneiras().toString());
		subView.getTfValorImpostoImportacao().setValue(
				ndiIi.getValorImpostoImportacao().toString());
		subView.getTfIofImpostoImportacao().setValue(
				ndiIi.getValorIof().toString());

		/**
		 * 
		 */

		NfeDetalheImpIpiEntity ndiIpi = new NfeDetalheImpIpiEntity();

		// this.subView.getTfCstIpi().setValue(ndiIpi.getCstIpi());
		subView.getTfBaseCalculoBcIpi().setValue(
				ndiIpi.getValorBaseCalculoIpi().toString());
		subView.getTfAliquotaIpi().setValue(ndiIpi.getAliquotaIpi().toString());
		subView.getTfQtdUndTributavelIpi().setValue(
				ndiIpi.getQuantidadeUnidadeTributavel().toString());
		subView.getTfValorUndTributavelIpi().setValue(
				ndiIpi.getValorUnidadeTributavel().toString());
		subView.getTfValorIpi().setValue(ndiIpi.getValorIpi().toString());
		subView.getTfEnquadramentoIpi().setValue(ndiIpi.getEnquadramentoIpi());
		subView.getTfEnquadramentoLegalIpi().setValue(
				ndiIpi.getEnquadramentoLegalIpi());
		subView.getTfCnpjProdutorIpi().setValue(ndiIpi.getCnpjProdutorIpi());
		subView.getTfQtdSeloIpi().setValue(
				ndiIpi.getQuantidadeSeloIpi().toString());
		subView.getTfCodigoSeloIpi().setValue(ndiIpi.getCodigoSeloIpi());

		/**
		 * 
		 */

		NfeDetalheImpostoIssqnEntity ndiIssqn = new NfeDetalheImpostoIssqnEntity();

		subView.getTfBaseCalculoBcIssqn().setValue(
				ndiIssqn.getBaseCalculoIssqn().toString());
		subView.getTfAliquotaIssqn().setValue(
				ndiIssqn.getAliquotaIssqn().toString());
		subView.getTfValorIssqn().setValue(ndiIssqn.getValorIssqn().toString());
		subView.getTfMunicipioIssqn().setValue(
				ndiIssqn.getMunicipioIssqn().toString());
		subView.getTfItemListaServicosIssqn().setValue(
				ndiIssqn.getItemListaServicos().toString());
		subView.getTfTributacaoIssqn().setValue(ndiIssqn.getTributacaoIssqn());

		/**
		 * 
		 */

		NfeDetalheImpostoPisEntity ndiPis = new NfeDetalheImpostoPisEntity();

		// this.subView.getTfCstPis().setValue(entPis.getCstPis());
		subView.getTfQtdVendidaPis().setValue(
				ndiPis.getQuantidadeVendida().toString());
		subView.getTfBaseCalculoBcPis().setValue(
				ndiPis.getValorBaseCalculoPis().toString());
		subView.getTfAliquotaPercentualPis().setValue(
				ndiPis.getAliquotaPisPercentual().toString());
		subView.getTfAliquotaReaisPis().setValue(
				ndiPis.getAliquotaPisReais().toString());
		subView.getTfValorPis().setValue(ndiPis.getValorPis().toString());

		/**
		 * 
		 */

		NfeDetEspecificoCombustivelEntity ndeCombustivel = new NfeDetEspecificoCombustivelEntity();

		subView.getTfCodigoAnpCombustivel().setValue(
				ndeCombustivel.getCodigoAnp().toString());
		subView.getTfCodifCombustivel().setValue(ndeCombustivel.getCodif());
		subView.getTfQtdeTempAmbienteCombustivel().setValue(
				ndeCombustivel.getQuantidadeTempAmbiente().toString());
		subView.getTfUfConsumoCombustivel().setValue(
				ndeCombustivel.getUfConsumo());
		subView.getTfBcCideCombustivel().setValue(
				ndeCombustivel.getBaseCalculoCide().toString());
		subView.getTfAliquotaCideCombustivel().setValue(
				ndeCombustivel.getAliquotaCide().toString());
		subView.getTfValorCideCombustivel().setValue(
				ndeCombustivel.getValorCide().toString());

		/**
		 * 
		 */

		NfeDetEspecificoVeiculoEntity entVeiculo = new NfeDetEspecificoVeiculoEntity();

		subView.getTfTipoOperacaoVeiculo().setValue(
				entVeiculo.getTipoOperacao());
		subView.getTfChassiVeiculo().setValue(entVeiculo.getChassi());
		subView.getTfCodigoCorVeiculo().setValue(entVeiculo.getCodigoCor());
		subView.getTfDescricaoCorVeiculo().setValue(
				entVeiculo.getDescricaoCor());
		subView.getTfPotenciaMotorVeiculo().setValue(
				entVeiculo.getPotenciaMotor());
		subView.getTfCilindradasVeiculo().setValue(entVeiculo.getCilindradas());
		subView.getTfPesoLiquidoVeiculo().setValue(entVeiculo.getPesoLiquido());
		subView.getTfPesoBrutoVeiculo().setValue(entVeiculo.getPesoBruto());
		subView.getTfNumeroSerieVeiculo().setValue(entVeiculo.getNumeroSerie());
		subView.getTfCombustivelVeiculo().setValue(
				entVeiculo.getTipoCombustivel());
		subView.getTfNumeroMotorVeiculo().setValue(entVeiculo.getNumeroMotor());
		subView.getTfCapacidadeTracaoVeiculo().setValue(
				entVeiculo.getCapacidadeMaximaTracao());
		subView.getTfDistanciaEixosVeiculo().setValue(
				entVeiculo.getDistanciaEixos());
		subView.getTfAnoModeloVeiculo().setValue(entVeiculo.getAnoModelo());
		subView.getTfAnoFabricacaoVeiculo().setValue(
				entVeiculo.getAnoFabricacao());
		subView.getTfTipoPinturaVeiculo().setValue(entVeiculo.getTipoPintura());
		subView.getTfTipoVeiculo().setValue(entVeiculo.getTipoVeiculo());
		subView.getTfEspecieVeiculo().setValue(entVeiculo.getEspecieVeiculo());
		subView.getTfCondicaoVinVeiculo().setValue(entVeiculo.getCondicaoVin());
		subView.getTfCondicaoVeiculo()
				.setValue(entVeiculo.getCondicaoVeiculo());
		subView.getTfCodigoMarcaModeloVeiculo().setValue(
				entVeiculo.getCodigoMarcaModelo());
		subView.getTfCodigoCorDenatranVeiculo().setValue(entVeiculo.getCor());
		subView.getTfLotacaoVeiculo().setValue(
				entVeiculo.getLotacao().toString());
		subView.getTfRestricaoVeiculo().setValue(entVeiculo.getRestricao());

		// this.subView.carregarSfNdeMedicamento(this.nfeCabecalho.getNfeDetalhe()
		// .getNdeMedicamentoList());

		// this.ndeMedicamentoSelecionado = new
		// NfeDetEspecificoMedicamentoEntity();

		// this.subView.getTfNumeroLoteMedicamento().setValue(
		// this.ndeMedicamentoSelecionado.getNumeroLote());
		// this.subView.getTfQuantidadeLoteMedicamento().setValue(
		// this.ndeMedicamentoSelecionado.getQuantidadeLote().toString());
		// this.subView.getPdfDataFabricacaoMedicamento().setValue(
		// this.ndeMedicamentoSelecionado.getDataFabricacao());
		// this.subView.getPdfDataValidadeMedicamento().setValue(
		// this.ndeMedicamentoSelecionado.getDataValidade());
		// this.subView.getTfPrecoMaximoConsumidorMedicamento().setValue(
		// this.ndeMedicamentoSelecionado.getPrecoMaximoConsumidor()
		// .toString());

		// this.subView.getGlNdeMedicamento().setEnabled(false);
	}

	/**
	 * 
	 */
	public static synchronized void tabEnabled(NfeDetalheEntity nfeDetalhe,
			ProdutoServicoFormView subView) {
		if (nfeDetalhe == null) {
			subView.getGlNfeDetalhe().setEnabled(false);
			subView.getGlIcms().setEnabled(false);
			subView.getGlPis().setEnabled(false);
			subView.getGlCofins().setEnabled(false);
			subView.getGlIpi().setEnabled(false);
			subView.getGlImpostoImportacao().setEnabled(false);
			subView.getGlIssqn().setEnabled(false);
			subView.getNdeGlCombustivel().setEnabled(false);
			subView.getNdeGlVeiculo().setEnabled(false);
			subView.getPlNdeMedicamentoSubForm().setEnabled(false);
			subView.getPlNdeArmamentoSubForm().setEnabled(false);
		} else {
			subView.getGlNfeDetalhe().setEnabled(true);
			subView.getGlIcms().setEnabled(true);
			subView.getGlPis().setEnabled(true);
			subView.getGlCofins().setEnabled(true);
			subView.getGlIpi().setEnabled(true);
			subView.getGlImpostoImportacao().setEnabled(true);
			subView.getGlIssqn().setEnabled(true);
			subView.getNdeGlCombustivel().setEnabled(true);
			subView.getNdeGlVeiculo().setEnabled(true);
			subView.getPlNdeMedicamentoSubForm().setEnabled(true);
			subView.getPlNdeArmamentoSubForm().setEnabled(true);
		}
	}

}