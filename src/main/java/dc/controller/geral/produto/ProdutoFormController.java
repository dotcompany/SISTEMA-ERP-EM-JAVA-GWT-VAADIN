package dc.controller.geral.produto;

import java.io.Serializable;
import java.util.ArrayList;
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
	
	private static final long serialVersionUID = 1L;
	
	private ProdutoFormView subView;
	
	private ProdutoEntity entity;
	
	@Autowired
	private ProdutoBusiness<ProdutoEntity> business;
	
	/**
	 * DAO
	 */

	@Autowired
	private SubGrupoDAO subGrupoDAO;
	
	@Autowired
	private UnidadeProdutoDAO unidadeProdutoDAO;
	
	@Autowired
	private AlmoxarifadoDAO almoxarifadoDAO;
	
	@Autowired
	private MarcaDAO marcaDAO;
	
	@Autowired
	private IcmsCustomizadoDAO icmsCustomizadoDAO;
	
	@Autowired
	private GrupoTributarioDAO grupoTributarioDAO;
	
	@Autowired
	private GrupoDAO grupoDAO;
	
	@Autowired
	private NcmDAO ncmDAO;

	
	public ProdutoFormController() {
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
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
	protected void criarNovoBean() {
		try {
			this.entity = new ProdutoEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}

		
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new ProdutoFormView(this);
			
			DefaultManyToOneComboModel<SubGrupoEntity> modelSubGrupo = new DefaultManyToOneComboModel<SubGrupoEntity>(
					SubGrupoListController.class, this.subGrupoDAO, super.getMainController());
			this.subView.getMocSubGrupo().setModel(modelSubGrupo);
			
			DefaultManyToOneComboModel<UnidadeProdutoEntity> modelUnidadeProduto = new DefaultManyToOneComboModel<UnidadeProdutoEntity>(
					UnidadeProdutoListController.class, this.unidadeProdutoDAO, super.getMainController());
			this.subView.getMocUnidadeProduto().setModel(modelUnidadeProduto);
			
			DefaultManyToOneComboModel<MarcaEntity> modelMarca = new DefaultManyToOneComboModel<MarcaEntity>(
					MarcaListController.class, this.marcaDAO, super.getMainController());
			this.subView.getMocMarca().setModel(modelMarca);
			
			DefaultManyToOneComboModel<AlmoxarifadoEntity> modelAlmoxarifado= new DefaultManyToOneComboModel<AlmoxarifadoEntity>(
					AlmoxarifadoListController.class, this.almoxarifadoDAO, super.getMainController());
			this.subView.getMocAlmoxarifado().setModel(modelAlmoxarifado);
			
			DefaultManyToOneComboModel<IcmsCustomizadoCabecalhoEntity> modelIcmsCustomizado = new DefaultManyToOneComboModel<IcmsCustomizadoCabecalhoEntity>(
					IcmsCustomizadoListController.class, this.icmsCustomizadoDAO, super.getMainController());
			this.subView.getMocIcmsCustomizado().setModel(modelIcmsCustomizado);
			
			DefaultManyToOneComboModel<GrupoTributarioEntity> modelGrupoTributario = new DefaultManyToOneComboModel<GrupoTributarioEntity>(
					GrupoTributarioListController.class, this.grupoTributarioDAO, super.getMainController());
			this.subView.getMocGrupoTributario().setModel(modelGrupoTributario);
			
			DefaultManyToOneComboModel<GrupoEntity> modelGrupo = new DefaultManyToOneComboModel<GrupoEntity>(
					GrupoListController.class, this.grupoDAO, super.getMainController());
			this.subView.getMocGrupo().setModel(modelGrupo);

			DefaultManyToOneComboModel<NcmEntity> modelNcm = new DefaultManyToOneComboModel<NcmEntity>(
					NcmListController.class, this.ncmDAO, super.getMainController());
			this.subView.getMocNcm().setModel(modelNcm);


			this.subView.getCbTemIcmsCustomizado().setValue(SimNaoEn.N);


		} catch (Exception e) {
			e.printStackTrace();
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
			this.subView.getTfDescricaoPdv().setValue(this.entity.getDescricaoPdv());
			this.subView.getCbInativo().setValue(this.entity.getInativo());
			this.subView.getCbClasse().setValue(this.entity.getClasse());
			
			if (this.entity.getValorCompra() != null) {
				subView.getCfValorCompra().setConvertedValue(
						this.entity.getValorCompra());
			}
			if (this.entity.getValorVenda() != null) {
				subView.getCfValorVenda().setConvertedValue(
						this.entity.getValorVenda());
			}
			if (this.entity.getPrecoVendaMinimo() != null) {
				subView.getCfValorVendaMinimo().setConvertedValue(
						this.entity.getPrecoVendaMinimo());
			}
			if (this.entity.getPrecoSugerido() != null) {
				subView.getCfValorSugerido().setConvertedValue(
						this.entity.getPrecoSugerido());
			}
			if (this.entity.getCustoMedioLiquido() != null) {
				subView.getCfCustoMedioLiquido().setConvertedValue(
						this.entity.getPrecoSugerido());
			}
			if (this.entity.getCustoMedioLiquido() != null) {
				subView.getCfPrecoLucroZero().setConvertedValue(
						this.entity.getCustoMedioLiquido());
			}
			if (this.entity.getPrecoLucroMinimo() != null) {
				subView.getCfPrecoLucroMinimo().setConvertedValue(
						this.entity.getPrecoSugerido());
			}
			if (this.entity.getPrecoLucroMaximo() != null) {
				subView.getCfPrecoLucroMaximo().setConvertedValue(
						this.entity.getPrecoLucroMaximo());
			}
			if (this.entity.getMarkup() != null) {
				subView.getCfMarkup()
						.setConvertedValue(this.entity.getMarkup());
			}

			this.subView.getTfLst().setValue(this.entity.getCodigoLst());
			this.subView.getTfExtipi().setValue(this.entity.getExTipi());
			this.subView.getCbTipoVenda().setValue(this.entity.getTipoVenda());
			this.subView.getCbIat().setValue(this.entity.getIat());
			this.subView.getCbIppt().setValue(this.entity.getIppt());
			this.subView.getCbTipoItemSped().setValue(this.entity.getTipoSped());
			this.subView.getTfTotalizadorParcial().setValue(this.entity.getTotalizadorParcial());

			this.subView.getTfCodigoBalanca().setConvertedValue(this.entity.getCodigoBalanca());
			this.subView.getTfPeso().setConvertedValue(this.entity.getPeso());

			if (this.entity.getQuantidadeEstoque() != null) {
				subView.getTfQuantidadeEstoque().setConvertedValue(this.entity.getQuantidadeEstoque());
			}
			
			this.subView.getTfQuantidadeEstoque().setConvertedValue(this.entity.getQuantidadeEstoque());
			this.subView.getTfQuantidadeEstoqueAnterior().setConvertedValue(this.entity.getQuantidadeEstoqueAnterior());
			this.subView.getTfEstoqueIdeal().setConvertedValue(this.entity.getEstoqueIdeal());
			this.subView.getTfEstoqueMinimo().setConvertedValue(this.entity.getEstoqueMinimo());
			this.subView.getTfEstoqueMaximo().setConvertedValue(this.entity.getEstoqueMaximo());
			this.subView.getTfTaxaComissao().setConvertedValue(this.entity.getTaxaComissao());
			this.subView.getTfPontoPedido().setConvertedValue(this.entity.getPontoPedido());
			this.subView.getTfLoteEconomicoCompra().setConvertedValue(this.entity.getLoteEconomicoCompra());
			this.subView.getTfAliquotaIcms().setConvertedValue(this.entity.getAliquotaIcms());
			this.subView.getTfAliquotaIssqn().setConvertedValue(this.entity.getAliquotaIssqn());

			this.subView.getMocSubGrupo().setValue(this.entity.getSubGrupo());
			this.subView.getMocUnidadeProduto().setValue(this.entity.getUnidadeProduto());
			this.subView.getMocMarca().setValue(this.entity.getMarca());
			this.subView.getMocGrupoTributario().setValue(this.entity.getGrupoTributario());
			this.subView.getMocAlmoxarifado().setValue(this.entity.getAlmoxarifado());
			this.subView.getMocGrupo().setValue(this.entity.getGrupo());
			this.subView.getMocNcm().setValue(this.entity.getNcm());


		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

	@Override
	protected void actionSalvar() {
		
		try {
			SubGrupoEntity subgrupo = this.subView.getMocSubGrupo().getValue();
			this.entity.setSubGrupo(subgrupo);
			
			UnidadeProdutoEntity unidadeProduto = this.subView.getMocUnidadeProduto().getValue();
			this.entity.setUnidadeProduto(unidadeProduto);
			
			MarcaEntity marca = this.subView.getMocMarca().getValue();
			this.entity.setMarca(marca);
			
			AlmoxarifadoEntity almoxarifado = this.subView.getMocAlmoxarifado().getValue();
			this.entity.setAlmoxarifado(almoxarifado);
			
			GrupoEntity grupo = this.subView.getMocGrupo().getValue();
			this.entity.setGrupo(grupo);
			
			GrupoTributarioEntity grupoTributario = this.subView.getMocGrupoTributario().getValue();
			this.entity.setGrupoTributario(grupoTributario);
			
			NcmEntity ncm = this.subView.getMocNcm().getValue();
			this.entity.setNcm(ncm);
			
			this.entity.setGtin(this.subView.getTfGtin().getValue());
			this.entity.setCodigoInterno(this.subView.getTfCodigoInterno().getValue());
			
			SimNaoEn inativoEn = (SimNaoEn) (this.subView.getCbInativo().getValue());
			this.entity.setInativo(inativoEn);
			
			ClasseEn classeEn = (ClasseEn) this.subView.getCbClasse().getValue();
			this.entity.setClasse(classeEn);

			this.entity.setNome(this.subView.getTfNome().getValue());
			this.entity.setDescricao(this.subView.getTfDescricao().getValue());
			this.entity.setDescricaoPdv(this.subView.getTfDescricaoPdv().getValue());


			this.entity.setValorVenda(NumberUtils.createBigDecimal(this.subView.getCfValorVenda().getConvertedValue()));
			this.entity.setValorCompra(NumberUtils.createBigDecimal(this.subView.getCfValorCompra().getConvertedValue()));
			this.entity.setPrecoVendaMinimo(NumberUtils.createBigDecimal(this.subView.getCfValorVendaMinimo().getConvertedValue()));
			this.entity.setPrecoSugerido(NumberUtils.createBigDecimal(this.subView.getCfValorSugerido().getConvertedValue()));
			this.entity.setCustoMedioLiquido(NumberUtils.createBigDecimal(this.subView.getCfCustoMedioLiquido().getConvertedValue()));
			this.entity.setPrecoLucroZero(NumberUtils.createBigDecimal(this.subView.getCfPrecoLucroZero().getConvertedValue()));
			this.entity.setPrecoLucroMaximo(NumberUtils.createBigDecimal(this.subView.getCfPrecoLucroMaximo().getConvertedValue()));
			this.entity.setPrecoLucroMinimo(NumberUtils.createBigDecimal(this.subView.getCfPrecoLucroMinimo().getConvertedValue()));
			this.entity.setMarkup(NumberUtils.createBigDecimal(this.subView.getCfMarkup().getConvertedValue()));

			this.entity.setCodigoLst(this.subView.getTfLst().getValue());
			this.entity.setExTipi(this.subView.getTfExtipi().getValue());

			VendaTipoVendaEn tipoVendaEn = (VendaTipoVendaEn) this.subView.getCbTipoVenda().getValue();
			this.entity.setTipoVenda(tipoVendaEn);

			IatEn iatEn = (IatEn) this.subView.getCbIat().getValue();
			this.entity.setIat(iatEn);

			IpptEn ipptEn = (IpptEn) this.subView.getCbIppt().getValue();
			this.entity.setIppt(ipptEn);

			TipoSpedEn tipoSpedEn = (TipoSpedEn) this.subView.getCbTipoItemSped().getValue();
			this.entity.setTipoSped(tipoSpedEn);

			this.entity.setTotalizadorParcial(this.subView.getTfTotalizadorParcial().getValue());
			this.entity.setCodigoBalanca(NumberUtils.toInt(this.subView.getTfCodigoBalanca().getValue()));

			this.entity.setQuantidadeEstoque(NumberUtils.createBigDecimal(this.subView.getTfQuantidadeEstoque().getValue()));
			this.entity.setQuantidadeEstoqueAnterior(NumberUtils.createBigDecimal(this.subView.getTfQuantidadeEstoqueAnterior().getConvertedValue()));
			this.entity.setEstoqueIdeal(NumberUtils.createBigDecimal(this.subView.getTfEstoqueIdeal().getConvertedValue()));
			this.entity.setEstoqueMinimo(NumberUtils.createBigDecimal(this.subView.getTfEstoqueMinimo().getConvertedValue()));
			this.entity.setEstoqueMaximo(NumberUtils.createBigDecimal(this.subView.getTfEstoqueMaximo().getConvertedValue()));
			this.entity.setPeso(NumberUtils.createBigDecimal(this.subView.getTfPeso().getConvertedValue()));
			this.entity.setTaxaComissao(NumberUtils.createBigDecimal(this.subView.getTfTaxaComissao().getConvertedValue()));
			this.entity.setPontoPedido(NumberUtils.createBigDecimal(this.subView.getTfPontoPedido().getConvertedValue()));
			this.entity.setLoteEconomicoCompra(NumberUtils.createBigDecimal(this.subView.getTfLoteEconomicoCompra().getConvertedValue()));
			this.entity.setAliquotaIcms(NumberUtils.createBigDecimal(this.subView.getTfAliquotaIcms().getConvertedValue()));
			this.entity.setAliquotaIssqn(NumberUtils.createBigDecimal(this.subView.getTfAliquotaIssqn().getConvertedValue()));

			
			this.business.saveOrUpdate(this.entity);

			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
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
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Produto";
	}
	
	@Override
	public boolean isFullSized() {
		return true;
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
	protected void removerEmCascata(List<Serializable> objetos) {
		
		try {

		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	public ProdutoEntity getModelBean() {
		// TODO Auto-generated method stub
		return entity;
	}
	
	/** COMBO */

	public List<String> getIcmsCustomizado() {
		try {
			List<String> siLista = new ArrayList<String>();

			for (SimNaoEn en : SimNaoEn.values()) {
				siLista.add(en.ordinal(), en.toString());

			}

			return siLista;

		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}
	
	public ProdutoBusiness<ProdutoEntity> getBusiness() {
		return business;
	}

}
