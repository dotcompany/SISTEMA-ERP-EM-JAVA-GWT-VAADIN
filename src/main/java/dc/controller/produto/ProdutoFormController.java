package dc.controller.produto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.controller.diversos.AlmoxarifadoListController;
import dc.controller.tributario.GrupoTributarioListController;
import dc.entidade.diversos.Almoxarifado;
import dc.entidade.produto.MarcaProduto;
import dc.entidade.produto.NCM;
import dc.entidade.produto.Produto;
import dc.entidade.produto.SubGrupoProduto;
import dc.entidade.produto.UnidadeProduto;
import dc.entidade.tributario.GrupoTributario;
import dc.entidade.tributario.ICMSCustomizado;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.diversos.AlmoxarifadoDAO;
import dc.servicos.dao.produto.MarcaProdutoDAO;
import dc.servicos.dao.produto.NCMDAO;
import dc.servicos.dao.produto.ProdutoDAO;
import dc.servicos.dao.produto.SubGrupoProdutoDAO;
import dc.servicos.dao.produto.UnidadeProdutoDAO;
import dc.servicos.dao.tributario.GrupoTributarioDAO;
import dc.servicos.dao.tributario.ICMSCustomizadoDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.MainController;
import dc.visao.produto.ProdutoFormView;
import dc.visao.produto.ProdutoFormView.CLASSE;
import dc.visao.produto.ProdutoFormView.IAT;
import dc.visao.produto.ProdutoFormView.IPPT;
import dc.visao.produto.ProdutoFormView.SIM_NAO;
import dc.visao.produto.ProdutoFormView.TIPO_SPED;
import dc.visao.produto.ProdutoFormView.TIPO_VENDA;

@Controller
@Scope("prototype")
public class ProdutoFormController extends CRUDFormController<Produto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProdutoFormView subView;

	@Autowired
	private ICMSCustomizadoDAO icmsCustomizadoDAO;
	
	@Autowired
	private ProdutoDAO produtoDAO;

	/*
	 * @Autowired private SubGrupoProdutoDAO subGrupoProdutoDAO;
	 */

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
	private NCMDAO ncmDAO;


	private Produto currentBean;
	
	@Autowired
	MainController mainController;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new Produto();
	}

	@Override
	protected void initSubView() {
		subView = new ProdutoFormView(this);
		try{
			DefaultManyToOneComboModel<SubGrupoProduto> comboSubGrupo = new DefaultManyToOneComboModel<SubGrupoProduto>(SubGrupoProduto.class, subGrupoProdutoDAO, mainController);
			subView.getCmbSubGrupoProduto().setModel(comboSubGrupo);
//			
			DefaultManyToOneComboModel<UnidadeProduto> comboUnidade = new DefaultManyToOneComboModel<UnidadeProduto>(UnidadeProduto.class, unidadeProdutoDAO, mainController);
			subView.getCmbUnidadeProduto().setModel(comboUnidade);
//			
			DefaultManyToOneComboModel<MarcaProduto> comboMarca = new DefaultManyToOneComboModel<MarcaProduto>(MarcaProduto.class, marcaProdutoDAO, mainController);
			subView.getCmbMarcaProduto().setModel(comboMarca);
//			
			DefaultManyToOneComboModel<Almoxarifado> comboAlmoxarifado = new DefaultManyToOneComboModel<Almoxarifado>(Almoxarifado.class, almoxarifadoDAO, mainController);
			subView.getCmbAlmoxarifado().setModel(comboAlmoxarifado);
			
			DefaultManyToOneComboModel<GrupoTributario> comboGrupo = new DefaultManyToOneComboModel<GrupoTributario>(GrupoTributario.class, grupoTributarioDAO, mainController);
			subView.getCmbGrupoTributario().setModel(comboGrupo);
			
			DefaultManyToOneComboModel<ICMSCustomizado> comboIcms = new DefaultManyToOneComboModel<ICMSCustomizado>(ICMSCustomizado.class, icmsCustomizadoDAO, mainController);
			subView.getCmbIcmsCustomizado().setModel(comboIcms);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	
	}

	@Override
	protected void carregar(Serializable id) {
		
		
		try{
			currentBean = produtoDAO.find(id);
			
			subView.getCmbSubGrupoProduto().setValue(currentBean.getSubGrupo());
			subView.getCmbUnidadeProduto().setValue(currentBean.getUnidade());
			
			subView.getTxtGtin().setValue(currentBean.getGtin());
			subView.getTxtCodigoInterno().setValue(currentBean.getGtin());
			subView.getTxtNome().setValue(currentBean.getNome());
			subView.getTxtDescricao().setValue(currentBean.getDescricao());
			subView.getTxtDescricaoPdv().setValue(currentBean.getDescricaoPdv());
		
		     String inativo = currentBean.getInativo();
		     if(Validator.validateString(inativo)) {
		    	subView.getCmbInativo().setValue(SIM_NAO.getValor(inativo)); 
		     }
		     
		     String classe = currentBean.getClasse();
		     if(Validator.validateString(classe)) {
			    	subView.getCmbClasse().setValue(CLASSE.getClasse(classe)); 
			     }
		     
		     BigDecimal valorCompra = currentBean.getValorCompra();
		     if(valorCompra!=null){
		    	 subView.getTxtValorCompra().setValue(valorCompra.toString());
		     }
		     
		     BigDecimal valorVenda = currentBean.getValorVenda();
		     if(valorVenda!=null){
		    	 subView.getTxtValorVenda().setValue(valorVenda.toString());
		     }
		     
		     BigDecimal precoVendaMinimo = currentBean.getPrecoVendaMinimo();
		     if(valorVenda!=null){
		    	 subView.getTxtValorVendaMinimo().setValue(precoVendaMinimo.toString());
		     }
		     
		     BigDecimal precoSugerido = currentBean.getPrecoSugerido();
		     if(precoSugerido!=null){
		    	 subView.getTxtValorSugerido().setValue(precoSugerido.toString());
		     }
		     
		     BigDecimal custoMedioLiquido = currentBean.getCustoMedioLiquido();
		     if(custoMedioLiquido!=null){
		    	 subView.getTxtCustoMedioLiquido().setValue(custoMedioLiquido.toString());
		     }
		     
		     BigDecimal precoLucroZero = currentBean.getCustoMedioLiquido();
		     if(precoLucroZero!=null){
		    	 subView.getTxtPrecoLucroZero().setValue(precoLucroZero.toString());
		     }
		     
		     BigDecimal precoLucroMinimo = currentBean.getPrecoLucroMinimo();
		     if(precoLucroMinimo!=null){
		    	 subView.getTxtPrecoLucroMinimo().setValue(precoLucroMinimo.toString());
		     }
		     
		     BigDecimal precoLucroMaximo = currentBean.getPrecoLucroMaximo();
		     if(precoLucroMaximo!=null){
		    	 subView.getTxtPrecoLucroMaximo().setValue(precoLucroMaximo.toString());
		     }
		     
		     BigDecimal markup = currentBean.getMarkup();
		     if(markup!=null){
		    	 subView.getTxtMarkup().setValue(markup.toString());
		     }
		     
		     BigDecimal quantidadeEstoque = currentBean.getQuantidadeEstoque();
		     if(quantidadeEstoque!=null){
		    	 subView.getTxtQuantidadeEstoque().setValue(quantidadeEstoque.toString());
		     }
		     
		     BigDecimal quantidadeEstoqueAnterior = currentBean.getQuantidadeEstoqueAnterior();
		     if(quantidadeEstoqueAnterior!=null){
		    	 subView.getTxtQuantidadeEstoqueAnterior().setValue(quantidadeEstoqueAnterior.toString());
		     }
		     
		     BigDecimal estoqueIdeal = currentBean.getEstoqueIdeal();
		     if(estoqueIdeal!=null){
		    	 subView.getTxtEstoqueIdeal().setValue(estoqueIdeal.toString());
		     }
		     
		     BigDecimal estoqueMinimo = currentBean.getEstoqueMinimo();
		     if(estoqueMinimo!=null){
		    	 subView.getTxtEstoqueMinimo().setValue(estoqueIdeal.toString());
		     }
		     
		     BigDecimal estoqueMaximo = currentBean.getEstoqueMaximo();
		     if(estoqueMaximo!=null){
		    	 subView.getTxtEstoqueMaximo().setValue(estoqueMaximo.toString());
		     }
		     
		     String lst = currentBean.getCodigoLst();
		     if(lst!=null){
		    	 subView.getTxtLst().setValue(lst);
		     }
		     
		     String extipi = currentBean.getExTipi();
		     if(extipi!=null){
		    	 subView.getTxtExtipi().setValue(extipi);
		     }
		     
		     String tipo = currentBean.getTipo();
		     if(tipo!=null){
		    	 subView.getCmbTipo().setValue(TIPO_VENDA.getTipoVenda(tipo));
		     }
		     
		     String iat = currentBean.getIat();
		     if(iat!=null){
		    	 subView.getCmbIat().setValue(IAT.getIat(iat));
		     }
		     
		     String ippt = currentBean.getIppt();
		     if(ippt!=null){
		    	 subView.getCmbIppt().setValue(IPPT.getiPPT(ippt));
		     }
		     
		     String sped = currentBean.getTipoItemSped();
		     if(sped!=null){
		    	 subView.getCmbTipoItemSped().setValue(TIPO_SPED.getTipoSped(sped));
		     }
		     
		     String totalizadorParcial = currentBean.getTotalizadorParcial();
		     if(totalizadorParcial!=null){
		    	 subView.getTxtTotalizadorParcial().setValue(totalizadorParcial);
		     }
		     
		     Integer codigoBalanca = currentBean.getCodigoBalanca();
		     if(codigoBalanca!=null){
		    	 subView.getTxtCodigoBalanca().setValue(codigoBalanca.toString());
		     }
		     
		     BigDecimal peso = currentBean.getPeso();
		     if(peso!=null){
		    	 subView.getTxtPeso().setValue(peso.toString());
		     }
		     
		     BigDecimal taxaComissao = currentBean.getTaxaComissao();
		     if(taxaComissao!=null){
		    	 subView.getTxtTaxaComissao().setValue(taxaComissao.toString());
		     }
		     
		     BigDecimal pontoPedido = currentBean.getPontoPedido();
		     if(pontoPedido!=null){
		    	 subView.getTxtPontoPedido().setValue(pontoPedido.toString());
		     }
		     
		     BigDecimal loteEconomicoCompra = currentBean.getTaxaComissao();
		     if(loteEconomicoCompra!=null){
		    	 subView.getTxtLoteEconomicoCompra().setValue(loteEconomicoCompra.toString());
		     }
		     
		     BigDecimal aliquotaIcms = currentBean.getAliquotaIcms();
		     if(aliquotaIcms!=null){
		    	 subView.getTxtAliquotaICms().setValue(aliquotaIcms.toString());
		     }
		     
		     BigDecimal aliquotaIssqn = currentBean.getAliquotaIssqn();
		     if(aliquotaIssqn!=null){
		    	 subView.getTxtAliquotaIssqn().setValue(aliquotaIssqn.toString());
		     }
				
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
//		subView.getCmbUnidadeProduto().setValue(currentBean.getUnidade());

		//	subView.getTxtNome().setValue(currentBean.getNome());
		//subView.getTxtDescricao().setValue(currentBean.getDescricao());

		/* Configura combo Sub Grupo Produto */
		/*
		 * ManyToOneComboModel<SubGrupoProduto> modelsub = new
		 * ManyToOneComboModel<SubGrupoProduto>() {
		 * 
		 * @Override public void onCriarNovo(String filter) {
		 * Notification.show("Selecionado Criar Novo: " + filter); }
		 * 
		 * @Override public List<SubGrupoProduto> getResultado(String q) {
		 * return subGrupoProdutoDAO.query(q); }
		 * 
		 * @Override public Class<SubGrupoProduto> getEntityClass() { return
		 * SubGrupoProduto.class; }
		 * 
		 * @Override public String getCaptionProperty() { return "descricao"; }
		 * 
		 * @Override public void onEditar(SubGrupoProduto value) {
		 * Notification.show("Selecionado Editar: " + value.getDescricao());
		 * 
		 * } }; subView.getCmbSubGrupoProduto().setModel(modelsub);
		 * subView.getCmbSubGrupoProduto
		 * ().setValue(currentBean.getIdSubGrupo());
		 */

		/* Configura combo Unidade Produto */
		/*
		 * ManyToOneComboModel<UnidadeProduto> model = new
		 * ManyToOneComboModel<UnidadeProduto>() {
		 * 
		 * @Override public void onCriarNovo(String filter) {
		 * Notification.show("Selecionado Criar Novo: " + filter); }
		 * 
		 * @Override public List<UnidadeProduto> getResultado(String q) { return
		 * unidadeProdutoDAO.query(q); }
		 * 
		 * @Override public Class<UnidadeProduto> getEntityClass() { return
		 * UnidadeProduto.class; }
		 * 
		 * @Override public String getCaptionProperty() { return "sigla"; }
		 * 
		 * @Override public void onEditar(UnidadeProduto value) {
		 * Notification.show("Selecionado Editar: " + value.getSigla());
		 * 
		 * } }; subView.getCmbUnidadeProduto().setModel(model);
		 * subView.getCmbUnidadeProduto().setValue(currentBean.getUnidade());
		 */

		/* Configura combo Marca Produto */
		/*
		 * ManyToOneComboModel<MarcaProduto> modelmarca = new
		 * ManyToOneComboModel<MarcaProduto>() {
		 * 
		 * @Override public void onCriarNovo(String filter) {
		 * Notification.show("Selecionado Criar Novo: " + filter); }
		 * 
		 * @Override public List<MarcaProduto> getResultado(String q) { return
		 * marcaProdutoDAO.query(q); }
		 * 
		 * @Override public Class<MarcaProduto> getEntityClass() { return
		 * MarcaProduto.class; }
		 * 
		 * @Override public String getCaptionProperty() { return "nome"; }
		 * 
		 * @Override public void onEditar(MarcaProduto value) {
		 * Notification.show("Selecionado Editar: " + value.getNome());
		 * 
		 * } }; subView.getCmbMarcaProduto().setModel(modelmarca);
		 * subView.getCmbMarcaProduto
		 * ().setValue(currentBean.getIdMarcaProduto());
		 */

		DefaultManyToOneComboModel<Almoxarifado> model = new DefaultManyToOneComboModel<Almoxarifado>(
				AlmoxarifadoListController.class, this.almoxarifadoDAO,
				super.getMainController());

		subView.getCmbAlmoxarifado().setModel(model);
		//subView.getCmbAlmoxarifado().setValue(currentBean.getIdAlmoxarifado());

		DefaultManyToOneComboModel<GrupoTributario> model1 = new DefaultManyToOneComboModel<GrupoTributario>(
				GrupoTributarioListController.class, this.grupoTributarioDAO,
				super.getMainController());

		subView.getCmbGrupoTributario().setModel(model1);
		//	subView.getCmbGrupoTributario().setValue(currentBean.getIdGrupoTributario());

		DefaultManyToOneComboModel<UnidadeProduto> model2 = new DefaultManyToOneComboModel<UnidadeProduto>(
				UnidadeProdutoListController.class, this.unidadeProdutoDAO,
				super.getMainController());

		subView.getCmbUnidadeProduto().setModel(model2);
		//subView.getCmbUnidadeProduto()		.setValue(currentBean.getUnidadeProduto());

		subView.getCmbSubGrupoProduto().setValue(new SubGrupoProduto());
		subView.getCmbMarcaProduto().setValue(new MarcaProduto());
	}

	@Override
	protected void actionSalvar() {
		//currentBean.setNome(subView.getTxtNome().getValue());
		//currentBean.setDescricao(subView.getTxtDescricao().getValue());

		try {

			SubGrupoProduto subgrupo = subView.getCmbSubGrupoProduto().getValue();
			UnidadeProduto unidade = subView.getCmbUnidadeProduto().getValue();
			
			MarcaProduto marca = subView.getCmbMarcaProduto().getValue();
			Almoxarifado almoxarifado = subView.getCmbAlmoxarifado().getValue();
			
			if(!Validator.validateObject(subgrupo)){
				throw new ErroValidacaoException("Informe o SubGrupo");
			}
			
			if(!Validator.validateObject(unidade)){
				throw new ErroValidacaoException("Informe a Unidade");
			}
			currentBean.setSubGrupo(subgrupo);
			currentBean.setUnidade(unidade);
			
//			currentBean.setMarcaProduto(marca);
//			currentBean.setAlmoxarifado(almoxarifado);
		
			String gtin = subView.getTxtGtin().getValue();
			currentBean.setGtin(gtin);
			
			String codigoInterno = subView.getTxtCodigoInterno().getValue();
			currentBean.setCodigoInterno(codigoInterno);
			
			SIM_NAO enumInativo = (SIM_NAO)(subView.getCmbInativo().getValue());
			if(Validator.validateObject(enumInativo)){
				String inativo = (enumInativo).getCodigo();
				currentBean.setInativo(inativo);
			}
			
			
			CLASSE enumClasse = (CLASSE)subView.getCmbClasse().getValue();
			if(enumClasse!=null){
				currentBean.setClasse(enumClasse.getCodigo());
			}
					
			
			String nome = subView.getTxtNome().getValue();
			currentBean.setNome(nome);
			
			String descricao = subView.getTxtDescricao().getValue();
			currentBean.setDescricao(descricao);
			
			String descricaoPdv = subView.getTxtDescricaoPdv().getValue();
			currentBean.setDescricaoPdv(descricaoPdv);
			
			
			String valorVenda = subView.getTxtValorVenda().getValue();
			if(Validator.validateString(valorVenda)){
				currentBean.setValorVenda(formataValor(valorVenda));
			}
			
			String valorCompra = subView.getTxtValorCompra().getValue();
			if(Validator.validateString(valorCompra)){
				currentBean.setValorCompra(formataValor(valorCompra));
			}
			
			String precoVendaMinimo = subView.getTxtValorVendaMinimo().getValue();
			if(Validator.validateString(precoVendaMinimo)){
				currentBean.setPrecoVendaMinimo(formataValor(precoVendaMinimo));
			}
			
			String precoSugerido = subView.getTxtValorSugerido().getValue();
			if(Validator.validateString(precoSugerido)){
				currentBean.setPrecoSugerido(formataValor(precoSugerido));
			}
			
			String custoMedioLiquido = subView.getTxtCustoMedioLiquido().getValue();
			if(Validator.validateString(custoMedioLiquido)){
				currentBean.setCustoMedioLiquido(formataValor(custoMedioLiquido));
			}
			
			String precoLucroZero = subView.getTxtPrecoLucroZero().getValue();
			if(Validator.validateString(precoLucroZero)){
				currentBean.setPrecoLucroZero(formataValor(precoLucroZero));
			}
			
			String precoLucroMinimo = subView.getTxtPrecoLucroMinimo().getValue();
			if(Validator.validateString(precoLucroMinimo)){
				currentBean.setPrecoLucroMinimo(formataValor(precoLucroMinimo));
			}
			
			String precoLucroMaximo = subView.getTxtPrecoLucroMaximo().getValue();
			if(Validator.validateString(precoLucroMaximo)){
				currentBean.setPrecoLucroMaximo(formataValor(precoLucroMaximo));
			}
			
			String markup = subView.getTxtMarkup().getValue();
			if(Validator.validateString(markup)){
				currentBean.setMarkup(formataValor(markup));
			}
			
			String quantidadeEstoque = subView.getTxtQuantidadeEstoque().getValue();
			if(Validator.validateString(quantidadeEstoque)){
				currentBean.setQuantidadeEstoque(formataValor(quantidadeEstoque));
			}
			
			String quantidadeEstoqueAnterior = subView.getTxtQuantidadeEstoqueAnterior().getValue();
			if(Validator.validateString(quantidadeEstoqueAnterior)){
				currentBean.setQuantidadeEstoqueAnterior(formataValor(quantidadeEstoqueAnterior));
			}
			
			String estoqueIdeal = subView.getTxtEstoqueIdeal().getValue();
			if(Validator.validateString(estoqueIdeal)){
				currentBean.setEstoqueIdeal(formataBigDecimal(estoqueIdeal));
			}
			
			String estoqueMinimo = subView.getTxtEstoqueMinimo().getValue();
			if(Validator.validateString(estoqueMinimo)){
				currentBean.setEstoqueMinimo(formataBigDecimal(estoqueMinimo));
			}
			
			String estoqueMaximo = subView.getTxtEstoqueMaximo().getValue();
			if(Validator.validateString(estoqueMaximo)){
				currentBean.setEstoqueMaximo (formataBigDecimal(estoqueMaximo));
			}
									
			String lst = subView.getTxtLst().getValue();
			if(Validator.validateString(lst)){
				currentBean.setCodigoLst(lst);
			}
			
			String extipi = subView.getTxtExtipi().getValue();
			if(Validator.validateString(extipi)){
				currentBean.setExTipi(extipi);
			}
			
			TIPO_VENDA enumTipoVenda = (TIPO_VENDA)subView.getCmbTipo().getValue();
			if(Validator.validateObject(enumTipoVenda)){
				String tipo = enumTipoVenda.getCodigo();
				currentBean.setTipo(tipo);
			}
			
			IAT enumIat = (IAT)subView.getCmbIat().getValue();
			if(Validator.validateObject(enumIat)){
				String iat = enumIat.getCodigo();
				currentBean.setIat(iat);
			}
			
			IPPT enumIppt = (IPPT)subView.getCmbIppt().getValue();
			if(Validator.validateObject(enumIppt)){
				String ippt = enumIppt.getCodigo();
				currentBean.setIppt(ippt);
			}
			
			TIPO_SPED enumSped = (TIPO_SPED)subView.getCmbTipoItemSped().getValue();
			if(Validator.validateObject(enumSped)){
				String sped = enumSped.getCodigo();
				currentBean.setTipoItemSped(sped);
			}
			
			String totalizadorParcial = subView.getTxtTotalizadorParcial().getValue();
			currentBean.setTotalizadorParcial(totalizadorParcial);
			
			String codigoBalanca = subView.getTxtCodigoBalanca().getValue();
			if(Validator.validateString(codigoBalanca)){
				currentBean.setCodigoBalanca(new Integer(codigoBalanca));
			}
			
			String peso = subView.getTxtPeso().getValue();
			if(Validator.validateString(codigoBalanca)){
				currentBean.setPeso(formataBigDecimal(peso));
			}
						
			String taxaComissao = subView.getTxtTaxaComissao().getValue();
			if(Validator.validateString(taxaComissao)){
				currentBean.setTaxaComissao(formataBigDecimal(taxaComissao));
			}
						
			String pontoPedido = subView.getTxtPontoPedido().getValue();
			if(Validator.validateString(pontoPedido)){
				currentBean.setPontoPedido(formataBigDecimal(pontoPedido));
			}
				
			String loteEconomicoCompra = subView.getTxtLoteEconomicoCompra().getValue();
			if(Validator.validateString(loteEconomicoCompra)){
				currentBean.setLoteEconomicoCompra(formataBigDecimal(loteEconomicoCompra));
			}
			
			String aliquotaIcms = subView.getTxtAliquotaICms().getValue();
			if(Validator.validateString(aliquotaIcms)){
				currentBean.setAliquotaIcms(formataBigDecimal(aliquotaIcms));
			}
			
			String aliquotaIssqn = subView.getTxtAliquotaIssqn().getValue();
			if(Validator.validateString(aliquotaIssqn)){
				currentBean.setAliquotaIssqn(formataBigDecimal(aliquotaIssqn));
			}
			
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
		return "produtoForm";
	}

	public Produto getCurrentBean() {
		return currentBean;
	}

	public void setCurrentBean(Produto currentBean) {
		this.currentBean = currentBean;
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	public BeanItemContainer<NCM> carregarNCM(){
		BeanItemContainer<NCM> container = new BeanItemContainer<>(NCM.class);
		for(NCM ncm : ncmDAO.listaTodos()){
			container.addItem(ncm);
		}
		return container;
	}
	
	public BigDecimal formataValor(String valor){
		String format = "";
		format = valor.replace("R$","").
				substring(0,valor.indexOf(",")).
				
				replaceAll( ",","" ).trim();
		return new BigDecimal(format);
	}
	
	public BigDecimal formataBigDecimal(String valor){
		return new BigDecimal(valor.replace(',', '.'));
	}

}