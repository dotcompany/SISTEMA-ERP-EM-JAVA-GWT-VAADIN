package dc.controller.geral.produto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.controller.diversos.AlmoxarifadoListController;
import dc.controller.tributario.GrupoTributarioListController;
import dc.controller.tributario.ICMSCustomizadoListController;
import dc.entidade.diversos.Almoxarifado;
import dc.entidade.geral.produto.GrupoProdutoEntity;
import dc.entidade.geral.produto.MarcaProdutoEntity;
import dc.entidade.geral.produto.NcmEntity;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.geral.produto.SubGrupoProdutoEntity;
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
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.MainController;
import dc.visao.geral.produto.ProdutosFormView;
import dc.visao.geral.produto.ProdutosFormView.CLASSE;
import dc.visao.geral.produto.ProdutosFormView.IAT;
import dc.visao.geral.produto.ProdutosFormView.IPPT;
import dc.visao.geral.produto.ProdutosFormView.SIM_NAO;
import dc.visao.geral.produto.ProdutosFormView.TIPO_SPED;
import dc.visao.geral.produto.ProdutosFormView.TIPO_VENDA;

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

	@Autowired
	MainController mainController;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new ProdutoEntity();
	}

	@Override
	protected void initSubView() {
		subView = new ProdutosFormView(this);
		try {
			DefaultManyToOneComboModel<SubGrupoProdutoEntity> comboSubGrupo = new DefaultManyToOneComboModel<SubGrupoProdutoEntity>(
					SubGrupoProdutoListController.class, subGrupoProdutoDAO,
					mainController);
			subView.getCmbSubGrupoProduto().setModel(comboSubGrupo);
			// //
			DefaultManyToOneComboModel<UnidadeProdutoEntity> comboUnidade = new DefaultManyToOneComboModel<UnidadeProdutoEntity>(
					UnidadeProdutoListController.class, unidadeProdutoDAO,
					mainController);
			subView.getCmbUnidadeProduto().setModel(comboUnidade);
			//
			DefaultManyToOneComboModel<MarcaProdutoEntity> comboMarca = new DefaultManyToOneComboModel<MarcaProdutoEntity>(
					MarcaProdutoListController.class, marcaProdutoDAO,
					mainController);
			subView.getCmbMarcaProduto().setModel(comboMarca);
			// //
			DefaultManyToOneComboModel<Almoxarifado> comboAlmoxarifado = new DefaultManyToOneComboModel<Almoxarifado>(
					AlmoxarifadoListController.class, almoxarifadoDAO,
					mainController);
			subView.getCmbAlmoxarifado().setModel(comboAlmoxarifado);
			// //

			DefaultManyToOneComboModel<ICMSCustomizado> comboIcmsCustomizado = new DefaultManyToOneComboModel<ICMSCustomizado>(
					ICMSCustomizadoListController.class, icmsCustomizadoDAO,
					mainController);
			subView.getCmbIcmsCustomizado().setModel(comboIcmsCustomizado);
			// //

			DefaultManyToOneComboModel<GrupoTributario> comboGrupo = new DefaultManyToOneComboModel<GrupoTributario>(
					GrupoTributarioListController.class, grupoTributarioDAO,
					mainController);
			subView.getCmbGrupoTributario().setModel(comboGrupo);
			// //
			DefaultManyToOneComboModel<GrupoProdutoEntity> comboGrupoProduto = new DefaultManyToOneComboModel<GrupoProdutoEntity>(
					GrupoProdutoListController.class, grupoProdutoDAO,
					mainController);
			subView.getCmbGrupoProduto().setModel(comboGrupoProduto);
			// //
			DefaultManyToOneComboModel<NcmEntity> comboNCM = new DefaultManyToOneComboModel<NcmEntity>(
					NcmListController.class, ncmDAO, mainController);
			subView.getCmbNcm().setModel(comboNCM);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void carregar(Serializable id) {

		try {
			currentBean = produtoDAO.find(id);

			subView.getCmbSubGrupoProduto().setValue(currentBean.getSubGrupo());
			subView.getCmbUnidadeProduto().setValue(currentBean.getUnidade());
			subView.getCmbMarcaProduto()
					.setValue(currentBean.getMarcaProduto());
			subView.getCmbGrupoTributario().setValue(
					currentBean.getGrupoTributario());

			subView.getTxtGtin().setValue(currentBean.getGtin());
			subView.getTxtCodigoInterno().setValue(currentBean.getGtin());
			subView.getTxtNome().setValue(currentBean.getNome());
			subView.getTxtDescricao().setValue(currentBean.getDescricao());
			subView.getTxtDescricaoPdv()
					.setValue(currentBean.getDescricaoPdv());

			String inativo = currentBean.getInativo();
			if (Validator.validateString(inativo)) {
				subView.getCmbInativo().setValue(SIM_NAO.getValor(inativo));
			}

			String classe = currentBean.getClasse();
			if (Validator.validateString(classe)) {
				subView.getCmbClasse().setValue(CLASSE.getClasse(classe));
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
				subView.getCmbTipo().setValue(TIPO_VENDA.getTipoVenda(tipo));
			}

			String iat = currentBean.getIat();
			if (iat != null) {
				subView.getCmbIat().setValue(IAT.getIat(iat));
			}

			String ippt = currentBean.getIppt();
			if (ippt != null) {
				subView.getCmbIppt().setValue(IPPT.getiPPT(ippt));
			}

			String sped = currentBean.getTipoItemSped();
			if (sped != null) {
				subView.getCmbTipoItemSped().setValue(
						TIPO_SPED.getTipoSped(sped));
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

			subView.getCmbAlmoxarifado()
					.setValue(currentBean.getAlmoxarifado());
			subView.getCmbGrupoProduto().setValue(currentBean.getGrupo());
			subView.getCmbNcm().setValue(currentBean.getNcm());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void actionSalvar() {

		try {

			SubGrupoProdutoEntity subgrupo = subView.getCmbSubGrupoProduto()
					.getValue();
			UnidadeProdutoEntity unidade = subView.getCmbUnidadeProduto()
					.getValue();

			MarcaProdutoEntity marca = subView.getCmbMarcaProduto().getValue();
			Almoxarifado almoxarifado = subView.getCmbAlmoxarifado().getValue();

			if (!Validator.validateObject(subgrupo)) {
				throw new ErroValidacaoException("Informe o SubGrupo");
			}

			if (!Validator.validateObject(unidade)) {
				throw new ErroValidacaoException("Informe a Unidade");
			}
			currentBean.setSubGrupo(subgrupo);
			currentBean.setUnidade(unidade);
			currentBean.setNcm(subView.getCmbNcm().getValue());

			currentBean.setMarcaProduto(marca);
			currentBean.setAlmoxarifado(almoxarifado);
			currentBean.setGrupoTributario(subView.getCmbGrupoTributario()
					.getValue());

			String gtin = subView.getTxtGtin().getValue();
			currentBean.setGtin(gtin);

			String codigoInterno = subView.getTxtCodigoInterno().getValue();
			currentBean.setCodigoInterno(codigoInterno);

			SIM_NAO enumInativo = (SIM_NAO) (subView.getCmbInativo().getValue());
			if (Validator.validateObject(enumInativo)) {
				String inativo = (enumInativo).getCodigo();
				currentBean.setInativo(inativo);
			}

			CLASSE enumClasse = (CLASSE) subView.getCmbClasse().getValue();
			if (enumClasse != null) {
				currentBean.setClasse(enumClasse.getCodigo());
			}

			String nome = subView.getTxtNome().getValue();
			currentBean.setNome(nome);

			String descricao = subView.getTxtDescricao().getValue();
			currentBean.setDescricao(descricao);

			String descricaoPdv = subView.getTxtDescricaoPdv().getValue();
			currentBean.setDescricaoPdv(descricaoPdv);

			currentBean.setValorVenda((BigDecimal) subView.getTxtValorVenda()
					.getConvertedValue());

			currentBean.setValorCompra((BigDecimal) subView.getTxtValorCompra()
					.getConvertedValue());

			currentBean.setPrecoVendaMinimo((BigDecimal) subView
					.getTxtValorVendaMinimo().getConvertedValue());

			currentBean.setPrecoSugerido((BigDecimal) subView
					.getTxtValorSugerido().getConvertedValue());

			currentBean.setCustoMedioLiquido((BigDecimal) subView
					.getTxtCustoMedioLiquido().getConvertedValue());

			currentBean.setPrecoLucroZero((BigDecimal) subView
					.getTxtPrecoLucroZero().getConvertedValue());

			currentBean.setPrecoLucroMinimo((BigDecimal) subView
					.getTxtPrecoLucroMinimo().getConvertedValue());

			currentBean.setPrecoLucroMaximo((BigDecimal) subView
					.getTxtPrecoLucroMaximo().getConvertedValue());

			currentBean.setMarkup((BigDecimal) subView.getTxtMarkup()
					.getConvertedValue());

			currentBean.setQuantidadeEstoque((BigDecimal) subView
					.getTxtQuantidadeEstoque().getConvertedValue());

			currentBean.setQuantidadeEstoqueAnterior((BigDecimal) subView
					.getTxtQuantidadeEstoqueAnterior().getConvertedValue());

			currentBean.setEstoqueIdeal((BigDecimal) subView
					.getTxtEstoqueIdeal().getConvertedValue());

			currentBean.setEstoqueMinimo((BigDecimal) subView
					.getTxtEstoqueMinimo().getConvertedValue());

			currentBean.setEstoqueMaximo((BigDecimal) subView
					.getTxtEstoqueMaximo().getConvertedValue());

			String lst = subView.getTxtLst().getValue();
			if (Validator.validateString(lst)) {
				currentBean.setCodigoLst(lst);
			}

			String extipi = subView.getTxtExtipi().getValue();
			if (Validator.validateString(extipi)) {
				currentBean.setExTipi(extipi);
			}

			TIPO_VENDA enumTipoVenda = (TIPO_VENDA) subView.getCmbTipo()
					.getValue();
			if (Validator.validateObject(enumTipoVenda)) {
				String tipo = enumTipoVenda.getCodigo();
				currentBean.setTipo(tipo);
			}

			IAT enumIat = (IAT) subView.getCmbIat().getValue();
			if (Validator.validateObject(enumIat)) {
				String iat = enumIat.getCodigo();
				currentBean.setIat(iat);
			}

			IPPT enumIppt = (IPPT) subView.getCmbIppt().getValue();
			if (Validator.validateObject(enumIppt)) {
				String ippt = enumIppt.getCodigo();
				currentBean.setIppt(ippt);
			}

			TIPO_SPED enumSped = (TIPO_SPED) subView.getCmbTipoItemSped()
					.getValue();
			if (Validator.validateObject(enumSped)) {
				String sped = enumSped.getCodigo();
				currentBean.setTipoItemSped(sped);
			}

			String totalizadorParcial = subView.getTxtTotalizadorParcial()
					.getValue();
			currentBean.setTotalizadorParcial(totalizadorParcial);

			String codigoBalanca = subView.getTxtCodigoBalanca().getValue();
			if (Validator.validateString(codigoBalanca)) {
				currentBean.setCodigoBalanca(new Integer(codigoBalanca));
			}

			currentBean.setPeso((BigDecimal) subView.getTxtPeso()
					.getConvertedValue());

			currentBean.setTaxaComissao((BigDecimal) subView
					.getTxtTaxaComissao().getConvertedValue());

			currentBean.setPontoPedido((BigDecimal) subView.getTxtPontoPedido()
					.getConvertedValue());

			currentBean.setLoteEconomicoCompra((BigDecimal) subView
					.getTxtLoteEconomicoCompra().getConvertedValue());

			currentBean.setAliquotaIcms((BigDecimal) subView
					.getTxtAliquotaICms().getConvertedValue());

			currentBean.setAliquotaIssqn((BigDecimal) subView
					.getTxtAliquotaIssqn().getConvertedValue());

			produtoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception ex) {
			ex.printStackTrace();
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
		produtoDAO.deleteAllByIds(ids);

		mensagemRemovidoOK();
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
		for (NcmEntity ncm : ncmDAO.listaTodos()) {
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

}