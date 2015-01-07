package dc.controller.geral.produto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.enums.ClasseEn;
import dc.control.enums.IatEn;
import dc.control.enums.IpptEn;
import dc.control.enums.SimNaoEn;
import dc.control.enums.TipoSpedEn;
import dc.control.enums.VendaTipoVendaEn;
import dc.control.util.ClassUtils;
import dc.control.util.classes.ProdutoUtils;
import dc.control.validator.DotErpException;
import dc.controller.geral.diverso.AlmoxarifadoListController;
import dc.controller.tributario.GrupoTributarioListController;
import dc.controller.tributario.IcmsCustomizadoListController;
import dc.entidade.geral.diverso.AlmoxarifadoEntity;
import dc.entidade.geral.produto.GrupoEntity;
import dc.entidade.geral.produto.MarcaEntity;
import dc.entidade.geral.produto.NcmEntity;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.geral.produto.SubGrupoEntity;
import dc.entidade.geral.produto.UnidadeProdutoEntity;
import dc.entidade.tributario.GrupoTributarioEntity;
import dc.entidade.tributario.IcmsCustomizadoEntity;
import dc.model.business.geral.produto.ProdutoBusiness;
import dc.servicos.dao.geral.diverso.AlmoxarifadoDAO;
import dc.servicos.dao.geral.produto.GrupoDAO;
import dc.servicos.dao.geral.produto.MarcaDAO;
import dc.servicos.dao.geral.produto.NcmDAO;
import dc.servicos.dao.geral.produto.SubGrupoDAO;
import dc.servicos.dao.geral.produto.UnidadeProdutoDAO;
import dc.servicos.dao.tributario.GrupoTributarioDAO;
import dc.servicos.dao.tributario.IcmsCustomizadoDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.produto.ProdutoFormView;

@Controller
@Scope("prototype")
public class ProdutoFormController extends CRUDFormController<ProdutoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProdutoFormView subView;

	/**
	 * ENTITY
	 */

	private ProdutoEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private ProdutoBusiness<ProdutoEntity> business;

	/**
	 * DAO
	 */

	@Autowired
	private UnidadeProdutoDAO unidadeProdutoDAO;

	@Autowired
	private MarcaDAO marcaDAO;

	@Autowired
	private SubGrupoDAO subGrupoDAO;

	@Autowired
	private GrupoDAO grupoDAO;

	@Autowired
	private NcmDAO ncmDAO;

	@Autowired
	private AlmoxarifadoDAO almoxarifadoDAO;

	@Autowired
	private IcmsCustomizadoDAO icmsCustomizadoDAO;

	@Autowired
	private GrupoTributarioDAO grupoTributarioDAO;

	/**
	 * CONSTRUTOR
	 */

	public ProdutoFormController() {
		// TODO Auto-generated constructor stub
	}

	public ProdutoBusiness<ProdutoEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Produto";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	public ProdutoEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new ProdutoFormView(this);

			DefaultManyToOneComboModel<SubGrupoEntity> modelSubGrupo = new DefaultManyToOneComboModel<SubGrupoEntity>(
					SubGrupoProdutoListController.class, this.subGrupoDAO,
					super.getMainController());

			this.subView.getMocSubGrupo().setModel(modelSubGrupo);

			DefaultManyToOneComboModel<UnidadeProdutoEntity> modelUnidadeProduto = new DefaultManyToOneComboModel<UnidadeProdutoEntity>(
					UnidadeProdutoListController.class, this.unidadeProdutoDAO,
					super.getMainController());

			this.subView.getMocUnidadeProduto().setModel(modelUnidadeProduto);

			DefaultManyToOneComboModel<MarcaEntity> modelMarca = new DefaultManyToOneComboModel<MarcaEntity>(
					MarcaProdutoListController.class, this.marcaDAO,
					super.getMainController());

			this.subView.getMocMarca().setModel(modelMarca);

			DefaultManyToOneComboModel<AlmoxarifadoEntity> modelAlmoxarifado = new DefaultManyToOneComboModel<AlmoxarifadoEntity>(
					AlmoxarifadoListController.class, this.almoxarifadoDAO,
					super.getMainController());

			this.subView.getMocAlmoxarifado().setModel(modelAlmoxarifado);

			DefaultManyToOneComboModel<IcmsCustomizadoEntity> modelIcmsCustomizado = new DefaultManyToOneComboModel<IcmsCustomizadoEntity>(
					IcmsCustomizadoListController.class,
					this.icmsCustomizadoDAO, super.getMainController());

			this.subView.getMocIcmsCustomizado().setModel(modelIcmsCustomizado);

			DefaultManyToOneComboModel<GrupoTributarioEntity> modelGrupoTributario = new DefaultManyToOneComboModel<GrupoTributarioEntity>(
					GrupoTributarioListController.class,
					this.grupoTributarioDAO, super.getMainController());

			this.subView.getMocGrupoTributario().setModel(modelGrupoTributario);

			DefaultManyToOneComboModel<GrupoEntity> modelGrupo = new DefaultManyToOneComboModel<GrupoEntity>(
					GrupoProdutoListController.class, this.grupoDAO,
					super.getMainController());

			this.subView.getMocGrupo().setModel(modelGrupo);

			DefaultManyToOneComboModel<NcmEntity> modelNcm = new DefaultManyToOneComboModel<NcmEntity>(
					NcmListController.class, this.ncmDAO,
					super.getMainController());

			this.subView.getMocNcm().setModel(modelNcm);

			carregarTemIcmsCustomizado();
			carregarClasse();
			carregarInativo();
			carregarTipoVenda();
			carregarTipoSped();
			carregarIat();
			carregarIppt();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {
			ProdutoUtils.validateRequiredFields(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.entity = this.business.find(id);

			this.subView.getMocSubGrupo().setValue(this.entity.getSubGrupo());
			this.subView.getMocUnidadeProduto().setValue(
					this.entity.getUnidadeProduto());
			this.subView.getMocMarca().setValue(this.entity.getMarca());
			this.subView.getMocGrupoTributario().setValue(
					this.entity.getGrupoTributario());

			this.subView.getTxtGtin().setValue(this.entity.getGtin());
			this.subView.getTxtCodigoInterno().setValue(this.entity.getGtin());
			this.subView.getTxtNome().setValue(this.entity.getNome());
			this.subView.getTxtDescricao().setValue(this.entity.getDescricao());
			this.subView.getTxtDescricaoPdv().setValue(
					this.entity.getDescricaoPdv());

			SimNaoEn inativoEn = this.entity.getInativo();

			if (Validator.validateObject(inativoEn)) {
				this.subView.getCbInativo().setValue(inativoEn);
			}

			ClasseEn classeEn = this.entity.getClasse();

			if (Validator.validateObject(classeEn)) {
				this.subView.getCbClasse().setValue(classeEn);
			}

			BigDecimal valorCompra = this.entity.getValorCompra();

			if (valorCompra != null) {
				this.subView.getTxtValorCompra().setConvertedValue(valorCompra);
			}

			BigDecimal valorVenda = this.entity.getValorVenda();

			if (valorVenda != null) {
				this.subView.getTxtValorVenda().setConvertedValue(valorVenda);
			}

			BigDecimal precoVendaMinimo = this.entity.getPrecoVendaMinimo();

			if (valorVenda != null) {
				this.subView.getTxtValorVendaMinimo().setConvertedValue(
						precoVendaMinimo);
			}

			BigDecimal precoSugerido = this.entity.getPrecoSugerido();

			if (precoSugerido != null) {
				this.subView.getTxtValorSugerido().setConvertedValue(
						precoSugerido);
			}

			BigDecimal custoMedioLiquido = this.entity.getCustoMedioLiquido();

			if (custoMedioLiquido != null) {
				this.subView.getTxtCustoMedioLiquido().setConvertedValue(
						custoMedioLiquido);
			}

			BigDecimal precoLucroZero = this.entity.getCustoMedioLiquido();

			if (precoLucroZero != null) {
				this.subView.getTxtPrecoLucroZero().setConvertedValue(
						precoLucroZero);
			}

			BigDecimal precoLucroMinimo = this.entity.getPrecoLucroMinimo();

			if (precoLucroMinimo != null) {
				this.subView.getTxtPrecoLucroMinimo().setConvertedValue(
						precoLucroMinimo);
			}

			BigDecimal precoLucroMaximo = this.entity.getPrecoLucroMaximo();

			if (precoLucroMaximo != null) {
				this.subView.getTxtPrecoLucroMaximo().setConvertedValue(
						precoLucroMaximo);
			}

			BigDecimal markup = this.entity.getMarkup();

			if (markup != null) {
				this.subView.getTxtMarkup().setConvertedValue(markup);
			}

			BigDecimal quantidadeEstoque = this.entity.getQuantidadeEstoque();

			if (quantidadeEstoque != null) {
				this.subView.getTxtQuantidadeEstoque().setConvertedValue(
						quantidadeEstoque);
			}

			BigDecimal quantidadeEstoqueAnterior = this.entity
					.getQuantidadeEstoqueAnterior();

			if (quantidadeEstoqueAnterior != null) {
				this.subView.getTxtQuantidadeEstoqueAnterior()
						.setConvertedValue(quantidadeEstoqueAnterior);
			}

			BigDecimal estoqueIdeal = this.entity.getEstoqueIdeal();

			if (estoqueIdeal != null) {
				this.subView.getTxtEstoqueIdeal().setConvertedValue(
						estoqueIdeal);
			}

			BigDecimal estoqueMinimo = this.entity.getEstoqueMinimo();

			if (estoqueMinimo != null) {
				this.subView.getTxtEstoqueMinimo().setConvertedValue(
						estoqueMinimo);
			}

			BigDecimal estoqueMaximo = this.entity.getEstoqueMaximo();

			if (estoqueMaximo != null) {
				this.subView.getTxtEstoqueMaximo().setConvertedValue(
						estoqueMaximo);
			}

			String lst = this.entity.getCodigoLst();

			if (lst != null) {
				this.subView.getTxtLst().setValue(lst);
			}

			String extipi = this.entity.getExTipi();

			if (extipi != null) {
				this.subView.getTxtExtipi().setValue(extipi);
			}

			VendaTipoVendaEn tipoVendaEn = this.entity.getTipoVenda();

			if (tipoVendaEn != null) {
				this.subView.getCbTipoVenda().setValue(tipoVendaEn);
			}

			IatEn iatEn = this.entity.getIat();

			if (iatEn != null) {
				this.subView.getCbIat().setValue(iatEn);
			}

			IpptEn ipptEn = this.entity.getIppt();

			if (ipptEn != null) {
				this.subView.getCbIppt().setValue(ipptEn);
			}

			TipoSpedEn tipoSpedEn = this.entity.getTipoSped();

			if (tipoSpedEn != null) {
				this.subView.getCbTipoItemSped().setValue(tipoSpedEn);
			}

			String totalizadorParcial = this.entity.getTotalizadorParcial();

			if (totalizadorParcial != null) {
				this.subView.getTxtTotalizadorParcial().setValue(
						totalizadorParcial);
			}

			Integer codigoBalanca = this.entity.getCodigoBalanca();

			if (codigoBalanca != null) {
				this.subView.getTxtCodigoBalanca().setConvertedValue(
						codigoBalanca);
			}

			BigDecimal peso = this.entity.getPeso();

			if (peso != null) {
				this.subView.getTxtPeso().setConvertedValue(peso);
			}

			BigDecimal taxaComissao = this.entity.getTaxaComissao();

			if (taxaComissao != null) {
				this.subView.getTxtTaxaComissao().setConvertedValue(
						taxaComissao);
			}

			BigDecimal pontoPedido = this.entity.getPontoPedido();

			if (pontoPedido != null) {
				this.subView.getTxtPontoPedido().setConvertedValue(pontoPedido);
			}

			BigDecimal loteEconomicoCompra = this.entity
					.getLoteEconomicoCompra();

			if (loteEconomicoCompra != null) {
				this.subView.getTxtLoteEconomicoCompra().setConvertedValue(
						loteEconomicoCompra);
			}

			BigDecimal aliquotaIcms = this.entity.getAliquotaIcms();

			if (aliquotaIcms != null) {
				this.subView.getTxtAliquotaICms().setConvertedValue(
						aliquotaIcms);
			}

			BigDecimal aliquotaIssqn = this.entity.getAliquotaIssqn();

			if (aliquotaIssqn != null) {
				this.subView.getTxtAliquotaIssqn().setConvertedValue(
						aliquotaIssqn);
			}

			this.subView.getMocAlmoxarifado().setValue(
					this.entity.getAlmoxarifado());
			this.subView.getMocGrupo().setValue(this.entity.getGrupo());
			this.subView.getMocNcm().setValue(this.entity.getNcm());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			SubGrupoEntity subgrupo = this.subView.getMocSubGrupo().getValue();
			GrupoEntity grupo = this.subView.getMocGrupo().getValue();
			UnidadeProdutoEntity unidadeProduto = this.subView
					.getMocUnidadeProduto().getValue();
			MarcaEntity marca = this.subView.getMocMarca().getValue();
			AlmoxarifadoEntity almoxarifado = this.subView.getMocAlmoxarifado()
					.getValue();
			NcmEntity ncm = this.subView.getMocNcm().getValue();
			GrupoTributarioEntity grupoTributario = this.subView
					.getMocGrupoTributario().getValue();

			// if (!Validator.validateObject(subgrupo)) {
			// throw new ErroValidacaoException("Informe o SubGrupo");
			// }

			// if (!Validator.validateObject(unidadeProduto)) {
			// throw new ErroValidacaoException("Informe a Unidade");
			// }

			this.entity.setSubGrupo(subgrupo);
			this.entity.setGrupo(grupo);
			this.entity.setUnidadeProduto(unidadeProduto);
			this.entity.setNcm(ncm);
			this.entity.setMarca(marca);
			this.entity.setAlmoxarifado(almoxarifado);
			this.entity.setGrupoTributario(grupoTributario);

			String gtin = this.subView.getTxtGtin().getValue();
			this.entity.setGtin(gtin);

			String codigoInterno = this.subView.getTxtCodigoInterno()
					.getValue();
			this.entity.setCodigoInterno(codigoInterno);

			SimNaoEn inativoEn = (SimNaoEn) (this.subView.getCbInativo()
					.getValue());

			// if (Validator.validateObject(inativoEn)) {
			// String inativo = (inativoEn).getCodigo();
			this.entity.setInativo(inativoEn);
			// }

			ClasseEn classeEn = (ClasseEn) this.subView.getCbClasse()
					.getValue();

			// if (classeEn != null) {
			this.entity.setClasse(classeEn);
			// }

			String nome = this.subView.getTxtNome().getValue();
			this.entity.setNome(nome);

			String descricao = this.subView.getTxtDescricao().getValue();
			this.entity.setDescricao(descricao);

			String descricaoPdv = this.subView.getTxtDescricaoPdv().getValue();
			this.entity.setDescricaoPdv(descricaoPdv);

			this.entity.setValorVenda((BigDecimal) this.subView
					.getTxtValorVenda().getConvertedValue());

			this.entity.setValorCompra((BigDecimal) this.subView
					.getTxtValorCompra().getConvertedValue());

			this.entity.setPrecoVendaMinimo((BigDecimal) this.subView
					.getTxtValorVendaMinimo().getConvertedValue());

			this.entity.setPrecoSugerido((BigDecimal) this.subView
					.getTxtValorSugerido().getConvertedValue());

			this.entity.setCustoMedioLiquido((BigDecimal) this.subView
					.getTxtCustoMedioLiquido().getConvertedValue());

			this.entity.setPrecoLucroZero((BigDecimal) this.subView
					.getTxtPrecoLucroZero().getConvertedValue());

			this.entity.setPrecoLucroMinimo((BigDecimal) this.subView
					.getTxtPrecoLucroMinimo().getConvertedValue());

			this.entity.setPrecoLucroMaximo((BigDecimal) this.subView
					.getTxtPrecoLucroMaximo().getConvertedValue());

			this.entity.setMarkup((BigDecimal) this.subView.getTxtMarkup()
					.getConvertedValue());

			this.entity.setQuantidadeEstoque((BigDecimal) this.subView
					.getTxtQuantidadeEstoque().getConvertedValue());

			this.entity.setQuantidadeEstoqueAnterior((BigDecimal) this.subView
					.getTxtQuantidadeEstoqueAnterior().getConvertedValue());

			this.entity.setEstoqueIdeal((BigDecimal) this.subView
					.getTxtEstoqueIdeal().getConvertedValue());

			this.entity.setEstoqueMinimo((BigDecimal) this.subView
					.getTxtEstoqueMinimo().getConvertedValue());

			this.entity.setEstoqueMaximo((BigDecimal) this.subView
					.getTxtEstoqueMaximo().getConvertedValue());

			String lst = this.subView.getTxtLst().getValue();

			// if (Validator.validateString(lst)) {
			this.entity.setCodigoLst(lst);
			// }

			String extipi = this.subView.getTxtExtipi().getValue();

			// if (Validator.validateString(extipi)) {
			this.entity.setExTipi(extipi);
			// }

			VendaTipoVendaEn tipoVendaEn = (VendaTipoVendaEn) this.subView
					.getCbTipoVenda().getValue();

			// if (Validator.validateObject(tipoVendaEn)) {
			// String tipo = enumTipoVenda.getCodigo();
			this.entity.setTipoVenda(tipoVendaEn);
			// }

			IatEn iatEn = (IatEn) this.subView.getCbIat().getValue();

			// if (Validator.validateObject(iatEn)) {
			// String iat = iatEn.getCodigo();
			this.entity.setIat(iatEn);
			// }

			IpptEn ipptEn = (IpptEn) this.subView.getCbIppt().getValue();

			// if (Validator.validateObject(ipptEn)) {
			// String ippt = ipptEn.getCodigo();
			this.entity.setIppt(ipptEn);
			// }

			TipoSpedEn tipoSpedEn = (TipoSpedEn) this.subView
					.getCbTipoItemSped().getValue();

			// if (Validator.validateObject(tipoSpedEn)) {
			// String sped = tipoSpedEn.getCodigo();
			this.entity.setTipoSped(tipoSpedEn);
			// }

			String totalizadorParcial = this.subView.getTxtTotalizadorParcial()
					.getValue();
			this.entity.setTotalizadorParcial(totalizadorParcial);

			String codigoBalanca = this.subView.getTxtCodigoBalanca()
					.getValue();

			// if (Validator.validateString(codigoBalanca)) {
			this.entity.setCodigoBalanca(new Integer(codigoBalanca));
			// }

			this.entity.setPeso((BigDecimal) this.subView.getTxtPeso()
					.getConvertedValue());

			this.entity.setTaxaComissao((BigDecimal) this.subView
					.getTxtTaxaComissao().getConvertedValue());

			this.entity.setPontoPedido((BigDecimal) this.subView
					.getTxtPontoPedido().getConvertedValue());

			this.entity.setLoteEconomicoCompra((BigDecimal) this.subView
					.getTxtLoteEconomicoCompra().getConvertedValue());

			this.entity.setAliquotaIcms((BigDecimal) this.subView
					.getTxtAliquotaICms().getConvertedValue());

			this.entity.setAliquotaIssqn((BigDecimal) this.subView
					.getTxtAliquotaIssqn().getConvertedValue());

			this.business.saveOrUpdate(this.entity);

			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new ProdutoEntity();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void quandoNovo() {
		try {
			this.entity = new ProdutoEntity();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.business.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		try {

		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/**
	 * COMBOS
	 */

	public void carregarTemIcmsCustomizado() {
		for (SimNaoEn en : SimNaoEn.values()) {
			this.subView.getCbTemIcmsCustomizado().addItem(en);
		}
	}

	public void carregarInativo() {
		for (SimNaoEn en : SimNaoEn.values()) {
			this.subView.getCbInativo().addItem(en);
		}
	}

	public void carregarClasse() {
		for (ClasseEn en : ClasseEn.values()) {
			this.subView.getCbClasse().addItem(en);
		}
	}

	public void carregarTipoVenda() {
		for (VendaTipoVendaEn en : VendaTipoVendaEn.values()) {
			this.subView.getCbTipoVenda().addItem(en);
		}
	}

	public void carregarTipoSped() {
		for (TipoSpedEn en : TipoSpedEn.values()) {
			this.subView.getCbTipoItemSped().addItem(en);
		}
	}

	public void carregarIat() {
		for (IatEn en : IatEn.values()) {
			this.subView.getCbIat().addItem(en);
		}
	}

	public void carregarIppt() {
		for (IpptEn en : IpptEn.values()) {
			this.subView.getCbIppt().addItem(en);
		}
	}

}