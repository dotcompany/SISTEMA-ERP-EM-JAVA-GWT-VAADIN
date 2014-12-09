package dc.control.validator.classe;

import dc.control.enums.ClasseEn;
import dc.control.enums.IatEn;
import dc.control.enums.IpptEn;
import dc.control.enums.SimNaoEn;
import dc.control.enums.TipoSpedEn;
import dc.control.enums.VendaTipoVendaEn;
import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.entidade.geral.diverso.AlmoxarifadoEntity;
import dc.entidade.geral.produto.GrupoEntity;
import dc.entidade.geral.produto.MarcaEntity;
import dc.entidade.geral.produto.NcmEntity;
import dc.entidade.geral.produto.SubGrupoEntity;
import dc.entidade.geral.produto.UnidadeProdutoEntity;
import dc.visao.geral.produto.ProdutoFormView;

public class ProdutoValidator {

	public static void validaSalvar(ProdutoFormView subView)
			throws DotErpException {
		SubGrupoEntity subgrupo = subView.getMocSubGrupoProduto().getValue();

		if (ObjectUtils.isBlank(subgrupo)) {
			throw new DotErpException(subView.getMocSubGrupoProduto(),
					"::DotERP - Não pode ficar em branco");
		}

		GrupoEntity grupo = subView.getMocGrupoProduto().getValue();

		if (ObjectUtils.isBlank(grupo)) {
			throw new DotErpException(subView.getMocGrupoProduto(),
					"::DotERP - Não pode ficar em branco");
		}

		UnidadeProdutoEntity unidadeProduto = subView.getMocUnidadeProduto()
				.getValue();

		if (ObjectUtils.isBlank(unidadeProduto)) {
			throw new DotErpException(subView.getMocUnidadeProduto(),
					"::DotERP - Não pode ficar em branco");
		}

		MarcaEntity marca = subView.getMocMarcaProduto().getValue();

		if (ObjectUtils.isBlank(marca)) {
			throw new DotErpException(subView.getMocMarcaProduto(),
					"::DotERP - Não pode ficar em branco");
		}

		AlmoxarifadoEntity almoxarifado = subView.getMocAlmoxarifado()
				.getValue();

		if (ObjectUtils.isBlank(almoxarifado)) {
			throw new DotErpException(subView.getMocAlmoxarifado(),
					"::DotERP - Não pode ficar em branco");
		}

		NcmEntity ncm = subView.getMocNcm().getValue();

		if (ObjectUtils.isBlank(ncm)) {
			throw new DotErpException(subView.getMocNcm(),
					"::DotERP - Não pode ficar em branco");
		}

		/*
		 * GrupoTributarioEntity grupoTributario = this.subView
		 * .getMocGrupoTributario().getValue();
		 * 
		 * if (!Validator.validateObject(grupoTributario)) {
		 * adicionarErroDeValidacao(this.subView.getMocGrupoTributario(),
		 * "Não pode ficar em branco");
		 * 
		 * valido = false; }
		 */

		SimNaoEn inativoEn = (SimNaoEn) subView.getCbInativo().getValue();

		if (ObjectUtils.isBlank(inativoEn)) {
			throw new DotErpException(subView.getCbInativo(),
					"::DotERP - Não pode ficar em branco");
		}

		ClasseEn classeEn = (ClasseEn) subView.getCbClasse().getValue();

		if (ObjectUtils.isBlank(classeEn)) {
			throw new DotErpException(subView.getCbClasse(),
					"::DotERP - Não pode ficar em branco");
		}

		String lst = subView.getTxtLst().getValue();

		if (StringUtils.isBlank(lst)) {
			throw new DotErpException(subView.getTxtLst(),
					"::DotERP - Não pode ficar em branco");
		}

		String extipi = subView.getTxtExtipi().getValue();

		if (StringUtils.isBlank(extipi)) {
			throw new DotErpException(subView.getTxtExtipi(),
					"::DotERP - Não pode ficar em branco");
		}

		VendaTipoVendaEn tipoVendaEn = (VendaTipoVendaEn) subView
				.getCbTipoVenda().getValue();

		if (ObjectUtils.isBlank(tipoVendaEn)) {
			throw new DotErpException(subView.getCbTipoVenda(),
					"::DotERP - Não pode ficar em branco");
		}

		IatEn iatEn = (IatEn) subView.getCbIat().getValue();

		if (ObjectUtils.isBlank(iatEn)) {
			throw new DotErpException(subView.getCbIat(),
					"::DotERP - Não pode ficar em branco");
		}

		IpptEn ipptEn = (IpptEn) subView.getCbIppt().getValue();

		if (ObjectUtils.isBlank(ipptEn)) {
			throw new DotErpException(subView.getCbIppt(),
					"::DotERP - Não pode ficar em branco");
		}

		TipoSpedEn tipoSpedEn = (TipoSpedEn) subView.getCbTipoItemSped()
				.getValue();

		if (ObjectUtils.isBlank(tipoSpedEn)) {
			throw new DotErpException(subView.getCbTipoItemSped(),
					"::DotERP - Não pode ficar em branco");
		}

		String codigoBalanca = subView.getTxtCodigoBalanca().getValue();

		if (StringUtils.isBlank(codigoBalanca)) {
			throw new DotErpException(subView.getTxtCodigoBalanca(),
					"::DotERP - Não pode ficar em branco");
		}
	}

}