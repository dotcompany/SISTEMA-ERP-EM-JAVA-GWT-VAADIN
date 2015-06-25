package dc.controller.geral.produto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Component;

import dc.control.enums.ClasseEn;
import dc.control.enums.IatEn;
import dc.control.enums.IpptEn;
import dc.control.enums.SimNaoEn;
import dc.control.enums.TipoSpedEn;
import dc.control.enums.VendaTipoVendaEn;
import dc.control.util.ClassUtils;
import dc.control.util.NumberUtils;
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
import dc.entidade.tributario.IcmsCustomizadoCabecalhoEntity;
import dc.model.business.geral.produto.ProdutoBusiness;
import dc.servicos.dao.geral.diverso.AlmoxarifadoDAO;
import dc.servicos.dao.geral.produto.GrupoDAO;
import dc.servicos.dao.geral.produto.MarcaDAO;
import dc.servicos.dao.geral.produto.NcmDAO;
import dc.servicos.dao.geral.produto.SubGrupoDAO;
import dc.servicos.dao.geral.produto.UnidadeProdutoDAO;
import dc.servicos.dao.tributario.GrupoTributarioDAO;
import dc.servicos.dao.tributario.IcmsCustomizadoDAO;
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
					SubGrupoListController.class, this.subGrupoDAO,
					super.getMainController());

			this.subView.getMocSubGrupo().setModel(modelSubGrupo);

			DefaultManyToOneComboModel<UnidadeProdutoEntity> modelUnidadeProduto = new DefaultManyToOneComboModel<UnidadeProdutoEntity>(
					UnidadeProdutoListController.class, this.unidadeProdutoDAO,
					super.getMainController());

			this.subView.getMocUnidadeProduto().setModel(modelUnidadeProduto);

			DefaultManyToOneComboModel<MarcaEntity> modelMarca = new DefaultManyToOneComboModel<MarcaEntity>(
					MarcaListController.class, this.marcaDAO,
					super.getMainController());

			this.subView.getMocMarca().setModel(modelMarca);

			DefaultManyToOneComboModel<AlmoxarifadoEntity> modelAlmoxarifado = new DefaultManyToOneComboModel<AlmoxarifadoEntity>(
					AlmoxarifadoListController.class, this.almoxarifadoDAO,
					super.getMainController());

			this.subView.getMocAlmoxarifado().setModel(modelAlmoxarifado);

			DefaultManyToOneComboModel<IcmsCustomizadoCabecalhoEntity> modelIcmsCustomizado = new DefaultManyToOneComboModel<IcmsCustomizadoCabecalhoEntity>(
					IcmsCustomizadoListController.class,
					this.icmsCustomizadoDAO, super.getMainController());

			this.subView.getMocIcmsCustomizado().setModel(modelIcmsCustomizado);

			DefaultManyToOneComboModel<GrupoTributarioEntity> modelGrupoTributario = new DefaultManyToOneComboModel<GrupoTributarioEntity>(
					GrupoTributarioListController.class,
					this.grupoTributarioDAO, super.getMainController());

			this.subView.getMocGrupoTributario().setModel(modelGrupoTributario);

			DefaultManyToOneComboModel<GrupoEntity> modelGrupo = new DefaultManyToOneComboModel<GrupoEntity>(
					GrupoListController.class, this.grupoDAO,
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

			// Valores iniciais

			this.subView.getCbTemIcmsCustomizado().setValue(SimNaoEn.N);
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

			this.entity.setSubGrupo(subgrupo);
			this.entity.setGrupo(grupo);
			this.entity.setUnidadeProduto(unidadeProduto);
			this.entity.setNcm(ncm);
			this.entity.setMarca(marca);
			this.entity.setAlmoxarifado(almoxarifado);
			this.entity.setGrupoTributario(grupoTributario);

			this.entity.setGtin(this.subView.getTfGtin().getValue());
			this.entity.setCodigoInterno(this.subView.getTfCodigoInterno()
					.getValue());

			SimNaoEn inativoEn = (SimNaoEn) (this.subView.getCbInativo()
					.getValue());
			this.entity.setInativo(inativoEn);

			ClasseEn classeEn = (ClasseEn) this.subView.getCbClasse()
					.getValue();
			this.entity.setClasse(classeEn);

			this.entity.setNome(this.subView.getTfNome().getValue());
			this.entity.setDescricao(this.subView.getTfDescricao().getValue());
			this.entity.setDescricaoPdv(this.subView.getTfDescricaoPdv()
					.getValue());
			
			/**************** INFORMAÇÕES DE VALORES *************************************/

			/*this.entity.setValorVenda(this.subView.getCfValorVenda().getBigDecimalValue());
			this.entity.setValorCompra(this.subView.getCfValorCompra().getBigDecimalValue());
			this.entity.setPrecoVendaMinimo(this.subView.getCfValorVendaMinimo().getBigDecimalValue());
			this.entity.setPrecoSugerido(this.subView.getCfValorSugerido().getBigDecimalValue());
			this.entity.setCustoMedioLiquido(this.subView.getCfCustoMedioLiquido().getBigDecimalValue());
			this.entity.setPrecoLucroZero(this.subView.getCfPrecoLucroZero().getBigDecimalValue());
			this.entity.setPrecoLucroMinimo(this.subView.getCfPrecoLucroMinimo().getBigDecimalValue());
			this.entity.setPrecoLucroMaximo(this.subView.getCfPrecoLucroMaximo().getBigDecimalValue());
			this.entity.setMarkup(this.subView.getCfMarkup().getBigDecimalValue());
			
			this.entity.setValorVenda((BigDecimal) this.subView.getCfValorVenda().getConvertedValue());
			this.entity.setValorCompra((BigDecimal) this.subView.getCfValorCompra().getConvertedValue());
			this.entity.setPrecoVendaMinimo((BigDecimal) this.subView.getCfValorVendaMinimo().getConvertedValue());
			this.entity.setPrecoSugerido((BigDecimal) this.subView.getCfValorSugerido().getConvertedValue());
			this.entity.setCustoMedioLiquido((BigDecimal) this.subView.getCfCustoMedioLiquido().getConvertedValue());
			this.entity.setPrecoLucroZero((BigDecimal) this.subView.getCfPrecoLucroZero().getConvertedValue());
			this.entity.setPrecoLucroMaximo((BigDecimal) this.subView.getCfPrecoLucroMaximo().getConvertedValue());
			this.entity.setPrecoLucroMinimo((BigDecimal) this.subView.getCfPrecoLucroMinimo().getConvertedValue());
			this.entity.setMarkup((BigDecimal) this.subView.getCfMarkup().getConvertedValue());*/
			
			this.entity.setValorVenda(NumberUtils.createBigDecimal(this.subView.getCfValorVenda().getConvertedValue()));
            this.entity.setValorCompra(NumberUtils.createBigDecimal(this.subView.getCfValorCompra().getConvertedValue()));
            this.entity.setPrecoVendaMinimo(NumberUtils.createBigDecimal(this.subView.getCfValorVendaMinimo().getConvertedValue()));
            this.entity.setPrecoSugerido(NumberUtils.createBigDecimal(this.subView.getCfValorSugerido().getConvertedValue()));
            this.entity.setCustoMedioLiquido(NumberUtils.createBigDecimal(this.subView.getCfCustoMedioLiquido().getConvertedValue()));
            this.entity.setPrecoLucroZero(NumberUtils.createBigDecimal(this.subView.getCfPrecoLucroZero().getConvertedValue()));
            this.entity.setPrecoLucroMaximo(NumberUtils.createBigDecimal(this.subView.getCfPrecoLucroMaximo().getConvertedValue()));
            this.entity.setPrecoLucroMinimo(NumberUtils.createBigDecimal(this.subView.getCfPrecoLucroMinimo().getConvertedValue()));
            this.entity.setMarkup(NumberUtils.createBigDecimal(this.subView.getCfMarkup().getConvertedValue()));
			
			/*this.entity.setValorVenda(NumberUtils.createBigDecimal(this.subView.getCfValorVenda().getValue()));
            this.entity.setValorCompra(NumberUtils.createBigDecimal(this.subView.getCfValorCompra().getValue()));
            this.entity.setPrecoVendaMinimo(NumberUtils.createBigDecimal(this.subView.getCfValorVendaMinimo().getValue()));
            this.entity.setPrecoSugerido(NumberUtils.createBigDecimal(this.subView.getCfValorSugerido().getValue()));
            this.entity.setCustoMedioLiquido(NumberUtils.createBigDecimal(this.subView.getCfCustoMedioLiquido().getValue()));
            this.entity.setPrecoLucroZero(NumberUtils.createBigDecimal(this.subView.getCfPrecoLucroZero().getValue()));
            this.entity.setPrecoLucroMaximo(NumberUtils.createBigDecimal(this.subView.getCfPrecoLucroMaximo().getValue()));
            this.entity.setPrecoLucroMinimo(NumberUtils.createBigDecimal(this.subView.getCfPrecoLucroMinimo().getValue()));
            this.entity.setMarkup(NumberUtils.createBigDecimal(this.subView.getCfMarkup().getValue()));*/
	

			this.entity.setCodigoLst(this.subView.getTfLst().getValue());
			this.entity.setExTipi(this.subView.getTfExtipi().getValue());

			VendaTipoVendaEn tipoVendaEn = (VendaTipoVendaEn) this.subView
					.getCbTipoVenda().getValue();
			this.entity.setTipoVenda(tipoVendaEn);

			IatEn iatEn = (IatEn) this.subView.getCbIat().getValue();
			this.entity.setIat(iatEn);

			IpptEn ipptEn = (IpptEn) this.subView.getCbIppt().getValue();
			this.entity.setIppt(ipptEn);

			TipoSpedEn tipoSpedEn = (TipoSpedEn) this.subView
					.getCbTipoItemSped().getValue();
			this.entity.setTipoSped(tipoSpedEn);

			this.entity.setTotalizadorParcial(this.subView
					.getTfTotalizadorParcial().getValue());
			this.entity.setCodigoBalanca(NumberUtils.toInt(this.subView
					.getTfCodigoBalanca().getValue()));

			this.entity.setQuantidadeEstoque(NumberUtils
					.createBigDecimal(this.subView.getTfQuantidadeEstoque()
							.getValue()));
			this.entity.setQuantidadeEstoqueAnterior(NumberUtils
					.createBigDecimal(this.subView
							.getTfQuantidadeEstoqueAnterior()
							.getConvertedValue()));
			this.entity.setEstoqueIdeal(NumberUtils
					.createBigDecimal(this.subView.getTfEstoqueIdeal()
							.getConvertedValue()));
			this.entity.setEstoqueMinimo(NumberUtils
					.createBigDecimal(this.subView.getTfEstoqueMinimo()
							.getConvertedValue()));
			this.entity.setEstoqueMaximo(NumberUtils.createBigDecimal(this.subView.getTfEstoqueMaximo().getConvertedValue()));
			this.entity.setPeso(NumberUtils.createBigDecimal(this.subView
					.getTfPeso().getConvertedValue()));
			this.entity.setTaxaComissao(NumberUtils
					.createBigDecimal(this.subView.getTfTaxaComissao()
							.getConvertedValue()));
			this.entity.setPontoPedido(NumberUtils
					.createBigDecimal(this.subView.getTfPontoPedido()
							.getConvertedValue()));
			this.entity.setLoteEconomicoCompra(NumberUtils
					.createBigDecimal(this.subView.getTfLoteEconomicoCompra()
							.getConvertedValue()));
			this.entity.setAliquotaIcms(NumberUtils
					.createBigDecimal(this.subView.getTfAliquotaIcms()
							.getConvertedValue()));
			this.entity.setAliquotaIssqn(NumberUtils
					.createBigDecimal(this.subView.getTfAliquotaIssqn()
							.getConvertedValue()));

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

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.entity = this.business.find(id);

			this.subView.getTfGtin().setValue(this.entity.getGtin());
			this.subView.getTfCodigoInterno().setValue(this.entity.getGtin());
			this.subView.getTfNome().setValue(this.entity.getNome());
			this.subView.getTfDescricao().setValue(this.entity.getDescricao());
			this.subView.getTfDescricaoPdv().setValue(
					this.entity.getDescricaoPdv());
			this.subView.getCbInativo().setValue(this.entity.getInativo());
			this.subView.getCbClasse().setValue(this.entity.getClasse());

			this.subView.getCfValorCompra().setValue(
					this.entity.getValorCompra());
			this.subView.getCfValorVenda()
					.setValue(this.entity.getValorVenda());
			this.subView.getCfValorVendaMinimo().setValue(
					this.entity.getPrecoVendaMinimo());
			this.subView.getCfValorSugerido().setValue(
					this.entity.getPrecoSugerido());
			this.subView.getCfCustoMedioLiquido().setValue(
					this.entity.getCustoMedioLiquido());
			this.subView.getCfPrecoLucroZero().setValue(
					this.entity.getCustoMedioLiquido());
			this.subView.getCfPrecoLucroMinimo().setValue(
					this.entity.getPrecoLucroMinimo());
			this.subView.getCfPrecoLucroMaximo().setValue(
					this.entity.getPrecoLucroMaximo());
			this.subView.getCfMarkup().setValue(this.entity.getMarkup());

			this.subView.getTfLst().setValue(this.entity.getCodigoLst());
			this.subView.getTfExtipi().setValue(this.entity.getExTipi());
			this.subView.getCbTipoVenda().setValue(this.entity.getTipoVenda());
			this.subView.getCbIat().setValue(this.entity.getIat());
			this.subView.getCbIppt().setValue(this.entity.getIppt());
			this.subView.getCbTipoItemSped()
					.setValue(this.entity.getTipoSped());
			this.subView.getTfTotalizadorParcial().setValue(
					this.entity.getTotalizadorParcial());

			this.subView.getTfCodigoBalanca().setConvertedValue(
					this.entity.getCodigoBalanca());
			this.subView.getTfPeso().setConvertedValue(this.entity.getPeso());

			this.subView.getTfQuantidadeEstoque().setValue(
					this.entity.getQuantidadeEstoque());

			this.subView.getTfQuantidadeEstoqueAnterior().setConvertedValue(
					this.entity.getQuantidadeEstoqueAnterior());
			this.subView.getTfEstoqueIdeal().setConvertedValue(
					this.entity.getEstoqueIdeal());
			this.subView.getTfEstoqueMinimo().setConvertedValue(
					this.entity.getEstoqueMinimo());
			this.subView.getTfEstoqueMaximo().setConvertedValue(
					this.entity.getEstoqueMaximo());
			this.subView.getTfTaxaComissao().setConvertedValue(
					this.entity.getTaxaComissao());
			this.subView.getTfPontoPedido().setConvertedValue(
					this.entity.getPontoPedido());
			this.subView.getTfLoteEconomicoCompra().setConvertedValue(
					this.entity.getLoteEconomicoCompra());
			this.subView.getTfAliquotaIcms().setConvertedValue(
					this.entity.getAliquotaIcms());
			this.subView.getTfAliquotaIssqn().setConvertedValue(
					this.entity.getAliquotaIssqn());

			this.subView.getMocSubGrupo().setValue(this.entity.getSubGrupo());
			this.subView.getMocUnidadeProduto().setValue(
					this.entity.getUnidadeProduto());
			this.subView.getMocMarca().setValue(this.entity.getMarca());
			this.subView.getMocGrupoTributario().setValue(
					this.entity.getGrupoTributario());
			this.subView.getMocAlmoxarifado().setValue(
					this.entity.getAlmoxarifado());
			this.subView.getMocGrupo().setValue(this.entity.getGrupo());
			this.subView.getMocNcm().setValue(this.entity.getNcm());
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

			mensagemErro(e.getMessage());
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

	/**
	 * 
	 */

	public void vceTemIcmsCustomizado(ValueChangeEvent event) {
		SimNaoEn en = (SimNaoEn) event.getProperty().getValue();

		if (en.equals(SimNaoEn.S)) {
			this.subView.getGlGeral().removeComponent(
					this.subView.getMocGrupoTributario());
			this.subView.getGlGeral().addComponent(
					this.subView.getMocIcmsCustomizado(), 2, 2);
		}

		if (en.equals(SimNaoEn.N)) {
			this.subView.getGlGeral().removeComponent(
					this.subView.getMocIcmsCustomizado());
			this.subView.getGlGeral().addComponent(
					this.subView.getMocGrupoTributario(), 2, 2);
		}
	}

}