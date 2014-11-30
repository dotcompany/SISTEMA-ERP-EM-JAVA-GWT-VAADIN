package dc.controller.geral.produto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.control.enums.ClasseEn;
import dc.control.enums.IatEn;
import dc.control.enums.IpptEn;
import dc.control.enums.SimNaoEn;
import dc.control.enums.TipoSpedEn;
import dc.control.enums.TipoVendaEn;
import dc.control.util.ClassUtils;
import dc.controller.diversos.AlmoxarifadoListController;
import dc.controller.tributario.GrupoTributarioListController;
import dc.controller.tributario.ICMSCustomizadoListController;
import dc.entidade.diversos.Almoxarifado;
import dc.entidade.geral.produto.GrupoEntity;
import dc.entidade.geral.produto.MarcaEntity;
import dc.entidade.geral.produto.NcmEntity;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.geral.produto.SubGrupoEntity;
import dc.entidade.geral.produto.UnidadeProdutoEntity;
import dc.entidade.tributario.GrupoTributario;
import dc.entidade.tributario.ICMSCustomizado;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.diversos.AlmoxarifadoDAO;
import dc.servicos.dao.geral.produto.GrupoProdutoDAO;
import dc.servicos.dao.geral.produto.MarcaProdutoDAO;
import dc.servicos.dao.geral.produto.NcmDAO;
import dc.servicos.dao.geral.produto.ProdutoDAO;
import dc.servicos.dao.geral.produto.SubGrupoProdutoDAO;
import dc.servicos.dao.geral.produto.UnidadeProdutoDAO;
import dc.servicos.dao.tributario.GrupoTributarioDAO;
import dc.servicos.dao.tributario.ICMSCustomizadoDAO;
import dc.servicos.util.Validator;
import dc.visao.adm.dotcompany.ParametroClienteFormView.SIM_NAO;
import dc.visao.comercial.VendaFormView.TIPO_VENDA;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.produto.ProdutosFormView;

@Controller
@Scope("prototype")
public class ProdutosFormController extends CRUDFormController<ProdutoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProdutosFormView subView;

	@Autowired
	private ICMSCustomizadoDAO icmsCustomizadoDAO;

	@Autowired
	private ProdutoDAO produtoDAO;

	@Autowired
	private UnidadeProdutoDAO unidadeProdutoDAO;

	@Autowired
	private MarcaProdutoDAO marcaProdutoDAO;

	@Autowired
	private AlmoxarifadoDAO almoxarifadoDAO;

	@Autowired
	private GrupoTributarioDAO grupoTributarioDAO;

	@Autowired
	private SubGrupoProdutoDAO subGrupoProdutoDAO;

	@Autowired
	private GrupoProdutoDAO grupoProdutoDAO;

	@Autowired
	private NcmDAO ncmDAO;

	private ProdutoEntity currentBean;

	// @Autowired
	// private MainController mainController;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.currentBean = new ProdutoEntity();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initSubView() {
		try {
			subView = new ProdutosFormView(this);

			DefaultManyToOneComboModel<SubGrupoEntity> comboSubGrupo = new DefaultManyToOneComboModel<SubGrupoEntity>(
					SubGrupoProdutoListController.class, subGrupoProdutoDAO,
					super.getMainController());

			subView.getMocSubGrupoProduto().setModel(comboSubGrupo);
			// //
			DefaultManyToOneComboModel<UnidadeProdutoEntity> comboUnidade = new DefaultManyToOneComboModel<UnidadeProdutoEntity>(
					UnidadeProdutoListController.class, unidadeProdutoDAO,
					super.getMainController());

			subView.getMocUnidadeProduto().setModel(comboUnidade);
			//
			DefaultManyToOneComboModel<MarcaEntity> comboMarca = new DefaultManyToOneComboModel<MarcaEntity>(
					MarcaProdutoListController.class, marcaProdutoDAO,
					super.getMainController());

			subView.getMocMarcaProduto().setModel(comboMarca);
			// //
			DefaultManyToOneComboModel<Almoxarifado> comboAlmoxarifado = new DefaultManyToOneComboModel<Almoxarifado>(
					AlmoxarifadoListController.class, almoxarifadoDAO,
					super.getMainController());

			subView.getMocAlmoxarifado().setModel(comboAlmoxarifado);
			// //

			DefaultManyToOneComboModel<ICMSCustomizado> comboIcmsCustomizado = new DefaultManyToOneComboModel<ICMSCustomizado>(
					ICMSCustomizadoListController.class, icmsCustomizadoDAO,
					super.getMainController());

			subView.getMocIcmsCustomizado().setModel(comboIcmsCustomizado);
			// //

			DefaultManyToOneComboModel<GrupoTributario> comboGrupo = new DefaultManyToOneComboModel<GrupoTributario>(
					GrupoTributarioListController.class, grupoTributarioDAO,
					super.getMainController());

			subView.getMocGrupoTributario().setModel(comboGrupo);
			// //
			DefaultManyToOneComboModel<GrupoEntity> comboGrupoProduto = new DefaultManyToOneComboModel<GrupoEntity>(
					GrupoProdutoListController.class, grupoProdutoDAO,
					super.getMainController());

			subView.getMocGrupoProduto().setModel(comboGrupoProduto);
			// //
			DefaultManyToOneComboModel<NcmEntity> comboNCM = new DefaultManyToOneComboModel<NcmEntity>(
					NcmListController.class, ncmDAO, super.getMainController());

			subView.getMocNcm().setModel(comboNCM);

			comboClasse();
			comboIcms();
			comboInativo();
			carregarTipoVenda();
			carregarSped();
			carregarIAT();
			carregarIPPT();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			currentBean = produtoDAO.find(id);

			subView.getMocSubGrupoProduto().setValue(currentBean.getSubGrupo());
			subView.getMocUnidadeProduto().setValue(currentBean.getUnidade());
			subView.getMocMarcaProduto()
					.setValue(currentBean.getMarcaProduto());
			subView.getMocGrupoTributario().setValue(
					currentBean.getGrupoTributario());

			subView.getTxtGtin().setValue(currentBean.getGtin());
			subView.getTxtCodigoInterno().setValue(currentBean.getGtin());
			subView.getTxtNome().setValue(currentBean.getNome());
			subView.getTxtDescricao().setValue(currentBean.getDescricao());
			subView.getTxtDescricaoPdv()
					.setValue(currentBean.getDescricaoPdv());

			String inativo = currentBean.getInativo();

			if (Validator.validateString(inativo)) {
				subView.getCbInativo().setValue(SIM_NAO.getValor(inativo));
			}

			String classe = currentBean.getClasse();

			if (Validator.validateString(classe)) {
				subView.getCbClasse().setValue(ClasseEn.getClasse(classe));
			}

			BigDecimal valorCompra = currentBean.getValorCompra();

			if (valorCompra != null) {
				subView.getTxtValorCompra().setConvertedValue(valorCompra);
			}

			BigDecimal valorVenda = currentBean.getValorVenda();

			if (valorVenda != null) {
				subView.getTxtValorVenda().setConvertedValue(valorVenda);
			}

			BigDecimal precoVendaMinimo = currentBean.getPrecoVendaMinimo();

			if (valorVenda != null) {
				subView.getTxtValorVendaMinimo().setConvertedValue(
						precoVendaMinimo);
			}

			BigDecimal precoSugerido = currentBean.getPrecoSugerido();

			if (precoSugerido != null) {
				subView.getTxtValorSugerido().setConvertedValue(precoSugerido);
			}

			BigDecimal custoMedioLiquido = currentBean.getCustoMedioLiquido();

			if (custoMedioLiquido != null) {
				subView.getTxtCustoMedioLiquido().setConvertedValue(
						custoMedioLiquido);
			}

			BigDecimal precoLucroZero = currentBean.getCustoMedioLiquido();

			if (precoLucroZero != null) {
				subView.getTxtPrecoLucroZero()
						.setConvertedValue(precoLucroZero);
			}

			BigDecimal precoLucroMinimo = currentBean.getPrecoLucroMinimo();

			if (precoLucroMinimo != null) {
				subView.getTxtPrecoLucroMinimo().setConvertedValue(
						precoLucroMinimo);
			}

			BigDecimal precoLucroMaximo = currentBean.getPrecoLucroMaximo();

			if (precoLucroMaximo != null) {
				subView.getTxtPrecoLucroMaximo().setConvertedValue(
						precoLucroMaximo);
			}

			BigDecimal markup = currentBean.getMarkup();

			if (markup != null) {
				subView.getTxtMarkup().setConvertedValue(markup);
			}

			BigDecimal quantidadeEstoque = currentBean.getQuantidadeEstoque();

			if (quantidadeEstoque != null) {
				subView.getTxtQuantidadeEstoque().setConvertedValue(
						quantidadeEstoque);
			}

			BigDecimal quantidadeEstoqueAnterior = currentBean
					.getQuantidadeEstoqueAnterior();

			if (quantidadeEstoqueAnterior != null) {
				subView.getTxtQuantidadeEstoqueAnterior().setConvertedValue(
						quantidadeEstoqueAnterior);
			}

			BigDecimal estoqueIdeal = currentBean.getEstoqueIdeal();

			if (estoqueIdeal != null) {
				subView.getTxtEstoqueIdeal().setConvertedValue(estoqueIdeal);
			}

			BigDecimal estoqueMinimo = currentBean.getEstoqueMinimo();

			if (estoqueMinimo != null) {
				subView.getTxtEstoqueMinimo().setConvertedValue(estoqueMinimo);
			}

			BigDecimal estoqueMaximo = currentBean.getEstoqueMaximo();

			if (estoqueMaximo != null) {
				subView.getTxtEstoqueMaximo().setConvertedValue(estoqueMaximo);
			}

			String lst = currentBean.getCodigoLst();

			if (lst != null) {
				subView.getTxtLst().setValue(lst);
			}

			String extipi = currentBean.getExTipi();

			if (extipi != null) {
				subView.getTxtExtipi().setValue(extipi);
			}

			String tipo = currentBean.getTipo();

			if (tipo != null) {
				subView.getCbTipo().setValue(TIPO_VENDA.getTipoVenda(tipo));
			}

			String iat = currentBean.getIat();

			if (iat != null) {
				// subView.getCmbIat().setValue(IAT.getIat(iat));
			}

			String ippt = currentBean.getIppt();

			if (ippt != null) {
				// subView.getCmbIppt().setValue(IPPT.getiPPT(ippt));
			}

			String sped = currentBean.getTipoItemSped();

			if (sped != null) {
				// subView.getCmbTipoItemSped().setValue(
				// TIPO_SPED.getTipoSped(sped));
			}

			String totalizadorParcial = currentBean.getTotalizadorParcial();

			if (totalizadorParcial != null) {
				subView.getTxtTotalizadorParcial().setValue(totalizadorParcial);
			}

			Integer codigoBalanca = currentBean.getCodigoBalanca();

			if (codigoBalanca != null) {
				subView.getTxtCodigoBalanca().setConvertedValue(codigoBalanca);
			}

			BigDecimal peso = currentBean.getPeso();

			if (peso != null) {
				subView.getTxtPeso().setConvertedValue(peso);
			}

			BigDecimal taxaComissao = currentBean.getTaxaComissao();

			if (taxaComissao != null) {
				subView.getTxtTaxaComissao().setConvertedValue(taxaComissao);
			}

			BigDecimal pontoPedido = currentBean.getPontoPedido();

			if (pontoPedido != null) {
				subView.getTxtPontoPedido().setConvertedValue(pontoPedido);
			}

			BigDecimal loteEconomicoCompra = currentBean
					.getLoteEconomicoCompra();

			if (loteEconomicoCompra != null) {
				subView.getTxtLoteEconomicoCompra().setConvertedValue(
						loteEconomicoCompra);
			}

			BigDecimal aliquotaIcms = currentBean.getAliquotaIcms();

			if (aliquotaIcms != null) {
				subView.getTxtAliquotaICms().setConvertedValue(aliquotaIcms);
			}

			BigDecimal aliquotaIssqn = currentBean.getAliquotaIssqn();

			if (aliquotaIssqn != null) {
				subView.getTxtAliquotaIssqn().setConvertedValue(aliquotaIssqn);
			}

			subView.getMocAlmoxarifado()
					.setValue(currentBean.getAlmoxarifado());
			subView.getMocGrupoProduto().setValue(currentBean.getGrupo());
			subView.getMocNcm().setValue(currentBean.getNcm());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			SubGrupoEntity subgrupo = this.subView.getMocSubGrupoProduto()
					.getValue();
			UnidadeProdutoEntity unidade = this.subView.getMocUnidadeProduto()
					.getValue();
			MarcaEntity marca = this.subView.getMocMarcaProduto().getValue();
			Almoxarifado almoxarifado = this.subView.getMocAlmoxarifado()
					.getValue();

			if (!Validator.validateObject(subgrupo)) {
				throw new ErroValidacaoException("Informe o SubGrupo");
			}

			if (!Validator.validateObject(unidade)) {
				throw new ErroValidacaoException("Informe a Unidade");
			}

			this.currentBean.setSubGrupo(subgrupo);
			this.currentBean.setUnidade(unidade);
			this.currentBean.setNcm(this.subView.getMocNcm().getValue());

			this.currentBean.setMarcaProduto(marca);
			this.currentBean.setAlmoxarifado(almoxarifado);
			this.currentBean.setGrupoTributario(this.subView
					.getMocGrupoTributario().getValue());

			String gtin = this.subView.getTxtGtin().getValue();
			this.currentBean.setGtin(gtin);

			String codigoInterno = this.subView.getTxtCodigoInterno()
					.getValue();
			this.currentBean.setCodigoInterno(codigoInterno);

			SimNaoEn enumInativo = (SimNaoEn) (this.subView.getCbInativo()
					.getValue());

			if (Validator.validateObject(enumInativo)) {
				String inativo = (enumInativo).getCodigo();
				this.currentBean.setInativo(inativo);
			}

			ClasseEn classeEn = (ClasseEn) this.subView.getCbClasse()
					.getValue();

			if (classeEn != null) {
				this.currentBean.setClasse(classeEn.getCodigo());
			}

			String nome = this.subView.getTxtNome().getValue();
			this.currentBean.setNome(nome);

			String descricao = this.subView.getTxtDescricao().getValue();
			this.currentBean.setDescricao(descricao);

			String descricaoPdv = this.subView.getTxtDescricaoPdv().getValue();
			this.currentBean.setDescricaoPdv(descricaoPdv);

			this.currentBean.setValorVenda((BigDecimal) this.subView
					.getTxtValorVenda().getConvertedValue());

			this.currentBean.setValorCompra((BigDecimal) this.subView
					.getTxtValorCompra().getConvertedValue());

			this.currentBean.setPrecoVendaMinimo((BigDecimal) this.subView
					.getTxtValorVendaMinimo().getConvertedValue());

			this.currentBean.setPrecoSugerido((BigDecimal) this.subView
					.getTxtValorSugerido().getConvertedValue());

			this.currentBean.setCustoMedioLiquido((BigDecimal) this.subView
					.getTxtCustoMedioLiquido().getConvertedValue());

			this.currentBean.setPrecoLucroZero((BigDecimal) this.subView
					.getTxtPrecoLucroZero().getConvertedValue());

			this.currentBean.setPrecoLucroMinimo((BigDecimal) this.subView
					.getTxtPrecoLucroMinimo().getConvertedValue());

			this.currentBean.setPrecoLucroMaximo((BigDecimal) this.subView
					.getTxtPrecoLucroMaximo().getConvertedValue());

			this.currentBean.setMarkup((BigDecimal) this.subView.getTxtMarkup()
					.getConvertedValue());

			this.currentBean.setQuantidadeEstoque((BigDecimal) this.subView
					.getTxtQuantidadeEstoque().getConvertedValue());

			this.currentBean
					.setQuantidadeEstoqueAnterior((BigDecimal) this.subView
							.getTxtQuantidadeEstoqueAnterior()
							.getConvertedValue());

			this.currentBean.setEstoqueIdeal((BigDecimal) this.subView
					.getTxtEstoqueIdeal().getConvertedValue());

			this.currentBean.setEstoqueMinimo((BigDecimal) this.subView
					.getTxtEstoqueMinimo().getConvertedValue());

			this.currentBean.setEstoqueMaximo((BigDecimal) this.subView
					.getTxtEstoqueMaximo().getConvertedValue());

			String lst = this.subView.getTxtLst().getValue();

			if (Validator.validateString(lst)) {
				this.currentBean.setCodigoLst(lst);
			}

			String extipi = this.subView.getTxtExtipi().getValue();

			if (Validator.validateString(extipi)) {
				this.currentBean.setExTipi(extipi);
			}

			TIPO_VENDA enumTipoVenda = (TIPO_VENDA) this.subView.getCbTipo()
					.getValue();

			if (Validator.validateObject(enumTipoVenda)) {
				String tipo = enumTipoVenda.getCodigo();
				this.currentBean.setTipo(tipo);
			}

			IatEn iatEn = (IatEn) this.subView.getCbIat().getValue();

			if (Validator.validateObject(iatEn)) {
				String iat = iatEn.getCodigo();
				this.currentBean.setIat(iat);
			}

			IpptEn ipptEn = (IpptEn) this.subView.getCbIppt().getValue();

			if (Validator.validateObject(ipptEn)) {
				String ippt = ipptEn.getCodigo();
				this.currentBean.setIppt(ippt);
			}

			TipoSpedEn tipoSpedEn = (TipoSpedEn) this.subView
					.getCbTipoItemSped().getValue();

			if (Validator.validateObject(tipoSpedEn)) {
				String sped = tipoSpedEn.getCodigo();
				this.currentBean.setTipoItemSped(sped);
			}

			String totalizadorParcial = this.subView.getTxtTotalizadorParcial()
					.getValue();
			this.currentBean.setTotalizadorParcial(totalizadorParcial);

			String codigoBalanca = this.subView.getTxtCodigoBalanca()
					.getValue();

			if (Validator.validateString(codigoBalanca)) {
				this.currentBean.setCodigoBalanca(new Integer(codigoBalanca));
			}

			this.currentBean.setPeso((BigDecimal) this.subView.getTxtPeso()
					.getConvertedValue());

			this.currentBean.setTaxaComissao((BigDecimal) this.subView
					.getTxtTaxaComissao().getConvertedValue());

			this.currentBean.setPontoPedido((BigDecimal) this.subView
					.getTxtPontoPedido().getConvertedValue());

			this.currentBean.setLoteEconomicoCompra((BigDecimal) this.subView
					.getTxtLoteEconomicoCompra().getConvertedValue());

			this.currentBean.setAliquotaIcms((BigDecimal) this.subView
					.getTxtAliquotaICms().getConvertedValue());

			this.currentBean.setAliquotaIssqn((BigDecimal) this.subView
					.getTxtAliquotaIssqn().getConvertedValue());

			this.produtoDAO.saveOrUpdate(this.currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void quandoNovo() {

	}

	@Override
	protected String getNome() {
		return "Produto";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.produtoDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	public ProdutoEntity getCurrentBean() {
		return currentBean;
	}

	public void setCurrentBean(ProdutoEntity currentBean) {
		this.currentBean = currentBean;
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	public BeanItemContainer<NcmEntity> carregarNCM() {
		BeanItemContainer<NcmEntity> container = new BeanItemContainer<>(
				NcmEntity.class);

		for (NcmEntity ncm : this.ncmDAO.listaTodos()) {
			container.addItem(ncm);
		}

		return container;
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	public ProdutoEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

	// COMBOS

	public void comboIcms() {
		for (SimNaoEn en : SimNaoEn.values()) {
			this.subView.getCbTemIcmsCustomizado().addItem(en);
		}
	}

	public void comboInativo() {
		for (SimNaoEn en : SimNaoEn.values()) {
			this.subView.getCbInativo().addItem(en);
		}
	}

	public void comboClasse() {
		for (ClasseEn en : ClasseEn.values()) {
			this.subView.getCbClasse().addItem(en);
		}
	}

	public void carregarTipoVenda() {
		for (TipoVendaEn en : TipoVendaEn.values()) {
			this.subView.getCbTipo().addItem(en);
		}
	}

	public void carregarSped() {
		for (TipoSpedEn en : TipoSpedEn.values()) {
			this.subView.getCbTipoItemSped().addItem(en);
		}
	}

	public void carregarIAT() {
		for (IatEn en : IatEn.values()) {
			this.subView.getCbIat().addItem(en);
		}
	}

	public void carregarIPPT() {
		for (IpptEn en : IpptEn.values()) {
			this.subView.getCbIppt().addItem(en);
		}
	}

}