package dc.controller.tributario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.entidade.framework.Empresa;
import dc.entidade.geral.UF;
import dc.entidade.tabelas.Cfop;
import dc.entidade.tabelas.CodigoApuracaoEfd;
import dc.entidade.tabelas.Csosnb;
import dc.entidade.tabelas.CstCofins;
import dc.entidade.tabelas.CstIcmsB;
import dc.entidade.tabelas.CstIpi;
import dc.entidade.tabelas.CstPis;
import dc.entidade.tabelas.TipoReceitaDipi;
import dc.entidade.tributario.CofinsConfiguracaoTributaria;
import dc.entidade.tributario.ConfiguracaoTributaria;
import dc.entidade.tributario.GrupoTributario;
import dc.entidade.tributario.ICMSConfiguracaoTributaria;
import dc.entidade.tributario.IPIConfiguracaoTributaria;
import dc.entidade.tributario.OperacaoFiscal;
import dc.entidade.tributario.PISConfiguracaoTributaria;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.geral.UFDAO;
import dc.servicos.dao.tabelas.CfopDAO;
import dc.servicos.dao.tabelas.CodigoApuracaoEfdDAO;
import dc.servicos.dao.tabelas.CsosnbDAO;
import dc.servicos.dao.tabelas.CstCofinsDAO;
import dc.servicos.dao.tabelas.CstIcmsBDAO;
import dc.servicos.dao.tabelas.CstIpiDAO;
import dc.servicos.dao.tabelas.CstPisDAO;
import dc.servicos.dao.tabelas.TipoReceitaDipiDAO;
import dc.servicos.dao.tributario.CofinsConfiguracaoTributariaDAO;
import dc.servicos.dao.tributario.ConfiguracaoTributariaDAO;
import dc.servicos.dao.tributario.GrupoTributarioDAO;
import dc.servicos.dao.tributario.ICMSConfiguracaoTributariaDAO;
import dc.servicos.dao.tributario.IPIConfiguracaoTributariaDAO;
import dc.servicos.dao.tributario.OperacaoFiscalDAO;
import dc.servicos.dao.tributario.PISConfiguracaoTributariaDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.MainController;
import dc.visao.spring.SecuritySessionProvider;
import dc.visao.tributario.ConfiguracaoTributariaFormView;
import dc.visao.tributario.ConfiguracaoTributariaFormView.MODALIDADE_BC;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class ConfiguracaoTributariaFormController extends CRUDFormController<ConfiguracaoTributaria> {

	ConfiguracaoTributariaFormView subView;

	@Autowired
	ConfiguracaoTributariaDAO dao;

	@Autowired
	GrupoTributarioDAO grupoTributarioDAO;

	@Autowired
	OperacaoFiscalDAO operacaoFiscalDAO;

	@Autowired
	UFDAO ufDAO;

	ConfiguracaoTributaria currentBean;

	String CAMPO_EM_BRANCO = "Não pode ficar em branco";

	@Autowired
	CstPisDAO cstPisDAO;

	@Autowired
	CstCofinsDAO cstCofinsDAO;

	@Autowired
	CstIpiDAO cstIpiDAO;

	@Autowired
	CodigoApuracaoEfdDAO efdDAO;

	@Autowired
	TipoReceitaDipiDAO dipiDAO;

	@Autowired
	PISConfiguracaoTributariaDAO pisDAO;

	@Autowired
	CofinsConfiguracaoTributariaDAO cofinsDAO;

	@Autowired
	IPIConfiguracaoTributariaDAO ipiDAO;

	@Autowired
	CsosnbDAO csosnbDAO;

	@Autowired
	CstIcmsBDAO cstbDAO;

	@Autowired
	CfopDAO cfopDAO;

	@Autowired
	MainController mainController;

	@Autowired
	ICMSConfiguracaoTributariaDAO icmsDAO;

	@Override
	public String getViewIdentifier() {

		return "ConfiguracaoTributariaForm";
	}

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;
		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new ConfiguracaoTributaria();
	}

	@Override
	protected void initSubView() {
		subView = new ConfiguracaoTributariaFormView(this);
		DefaultManyToOneComboModel<GrupoTributario> comboModel = new DefaultManyToOneComboModel<GrupoTributario>
		(GrupoTributarioListController.class, grupoTributarioDAO, mainController);
		subView.getCmbGrupoTributario().setModel(comboModel);

		DefaultManyToOneComboModel<OperacaoFiscal> comboOperacao = new DefaultManyToOneComboModel<OperacaoFiscal>
		(OperacaoFiscalListController.class, operacaoFiscalDAO, mainController);
		subView.getCmbOperacaoFiscal().setModel(comboOperacao);

	}

	protected void carregar(Serializable id) {
		// TODO Auto-generated method stub
		currentBean = dao.find((Integer) id);
		subView.getCmbGrupoTributario().setValue(currentBean.getGrupoTributario());
		subView.getCmbOperacaoFiscal().setValue(currentBean.getOperacaoFiscal());



		List<ICMSConfiguracaoTributaria> detalhes = currentBean.getListaIcms();

		for(ICMSConfiguracaoTributaria d  : detalhes){
			Integer idCsosn = new Integer(d.getCsosnB().trim());
			d.setCsosn(csosnbDAO.find(idCsosn));

			Integer idCst = new Integer(d.getCstB().trim());
			d.setCst(cstbDAO.find(idCst));

		}

		subView.preencherIcmsSubForm(detalhes); 


		carregarPis(currentBean);
		carregarCofins(currentBean);
		carregarIpi(currentBean);
	}

	public void carregarPis(ConfiguracaoTributaria configuracao){
		PISConfiguracaoTributaria pis = pisDAO.buscarPorConfiguracao(configuracao);

		if(pis!=null){

			String cst = pis.getCst();
			if(cst!=null && !(cst.isEmpty())){
				subView.getTxtCstPis().setValue(pis.getCst());
				subView.getTxtDescricaoCstPis().setValue(consultarCstPis(cst).getDescricao());
			}

			String efd = pis.getCodigoApuracaoEfd();
			if(efd!=null && !(efd.isEmpty())){
				subView.getTxtEfdPis().setValue(efd);
				subView.getTxtDescricaoEfdPis().setValue(consultarEfd(efd).getDescricao());
			}

			String modalidadeBc = pis.getModalidadeBc();
			if(Validator.validateString(modalidadeBc)){
				subView.getCmbModalidadeBcPis().setValue(MODALIDADE_BC.getModalidadeBc(modalidadeBc));
			}

			BigDecimal porcentoBc = pis.getPorcentoBc();
			if(porcentoBc!=null){
				subView.getTxtPorcentoBcPis().setValue(porcentoBc.toString());
			}

			BigDecimal aliquotaPorcento = pis.getAliquotaPorcento();
			if(aliquotaPorcento!=null){
				subView.getTxtAliquotaBcPis().setValue(aliquotaPorcento.toString());
			}

			BigDecimal aliquotaUnidade = pis.getPorcentoBc();
			if(aliquotaUnidade!=null){
				subView.getTxtAliquotaUnidadePis().setValue(aliquotaUnidade.toString());
			}

			BigDecimal precoMaximo = pis.getValorPrecoMaximo();
			if(precoMaximo!=null){
				subView.getTxtValorPrecoMaximoPis().setValue(precoMaximo.toString());
			}

			BigDecimal pautaFiscal = pis.getValorPautaFiscal();
			if(pautaFiscal!=null){
				subView.getTxtValorPautaFiscalPis().setValue(pautaFiscal.toString());
			}


		}


	}

	public void carregarCofins(ConfiguracaoTributaria configuracao){
		CofinsConfiguracaoTributaria cofins = cofinsDAO.buscarPorConfiguracao(configuracao);

		if(cofins!=null){

			String cst = cofins.getCst();
			if(cst!=null && !(cst.isEmpty())){
				subView.getTxtCstCofins().setValue(cofins.getCst());
				subView.getTxtDescricaoCstCofins().setValue(consultarCstCofins(cst).getDescricao());
			}

			String efd = cofins.getCodigoApuracaoEfd();
			if(efd!=null && !(efd.isEmpty())){
				subView.getTxtEfdCofins().setValue(efd);
				subView.getTxtDescricaoEfdCofins().setValue(consultarEfd(efd).getDescricao());
			}

			String modalidadeBc = cofins.getModalidadeBc();
			if(Validator.validateString(modalidadeBc)){
				subView.getCmbModalidadeBcCofins().setValue(MODALIDADE_BC.getModalidadeBc(modalidadeBc));
			}

			BigDecimal porcentoBc = cofins.getPorcentoBc();
			if(porcentoBc!=null){
				subView.getTxtPorcentoBcCofins().setValue(porcentoBc.toString());
			}


			BigDecimal aliquotaPorcento = cofins.getAliquotaPorcento();
			if(aliquotaPorcento!=null){
				subView.getTxtAliquotaBcCofins().setValue(aliquotaPorcento.toString());
			}

			BigDecimal aliquotaUnidade = cofins.getPorcentoBc();
			if(aliquotaUnidade!=null){
				subView.getTxtAliquotaUnidadeCofins().setValue(aliquotaUnidade.toString());
			}

			BigDecimal precoMaximo = cofins.getValorPrecoMaximo();
			if(precoMaximo!=null){
				subView.getTxtValorPrecoMaximoCofins().setValue(precoMaximo.toString());
			}

			BigDecimal pautaFiscal = cofins.getValorPautaFiscal();
			if(pautaFiscal!=null){
				subView.getTxtValorPautaFiscalCofins().setValue(pautaFiscal.toString());
			}


		}


	}


	public void carregarIpi(ConfiguracaoTributaria configuracao){
		IPIConfiguracaoTributaria ipi = ipiDAO.buscarPorConfiguracao(configuracao);

		if(ipi!=null){

			String cst = ipi.getCst();
			if(cst!=null && !(cst.isEmpty())){
				subView.getTxtCstIPI().setValue(ipi.getCst());
				subView.getTxtDescricaoCstIPI().setValue(consultarCstIpi(cst).getDescricao());
			}



			String modalidadeBc = ipi.getModalidadeBc();
			if(Validator.validateString(modalidadeBc)){
				subView.getCmbModalidadeBcIPI().setValue(MODALIDADE_BC.getModalidadeBc(modalidadeBc));
			}

			BigDecimal porcentoBc = ipi.getPorcentoBc();
			if(porcentoBc!=null){
				subView.getTxtPorcentoBcIPI().setValue(porcentoBc.toString());
			}

			BigDecimal aliquotaPorcento = ipi.getAliquotaPorcento();
			if(aliquotaPorcento!=null){
				subView.getTxtAliquotaBcIPI().setValue(aliquotaPorcento.toString());
			}

			BigDecimal aliquotaUnidade = ipi.getPorcentoBc();
			if(aliquotaUnidade!=null){
				subView.getTxtAliquotaUnidadeIPI().setValue(aliquotaUnidade.toString());
			}

			BigDecimal precoMaximo = ipi.getValorPrecoMaximo();
			if(precoMaximo!=null){
				subView.getTxtValorPrecoMaximoIPI().setValue(precoMaximo.toString());
			}

			BigDecimal pautaFiscal = ipi.getValorPautaFiscal();
			if(pautaFiscal!=null){
				subView.getTxtValorPautaFiscalIPI().setValue(pautaFiscal.toString());
			}


		}


	}


	public Empresa empresaAtual(){
		return SecuritySessionProvider.getUsuario().getConta().getEmpresa();
	}

	@Override
	protected void actionSalvar() {
		try{
			currentBean.setEmpresa(empresaAtual());
			String msgErro ="Problema ao realizar operação";
			GrupoTributario grupo = (GrupoTributario)subView.getCmbGrupoTributario().getValue();
			if(grupo == null) {
				msgErro = "Informe o Grupo Tributário";
				throw new ErroValidacaoException(msgErro);
			}

			OperacaoFiscal operacao = (OperacaoFiscal)subView.getCmbOperacaoFiscal().getValue();
			if(operacao == null) {
				msgErro = "Informe a Operação Fiscal";
				throw new ErroValidacaoException(msgErro);
			}

			currentBean.setGrupoTributario(grupo);
			currentBean.setOperacaoFiscal(operacao);
			dao.saveOrUpdate(currentBean);


			List<ICMSConfiguracaoTributaria> detalhes = subView.getIcmsSubForm().getDados();

			if(detalhes!=null) {
				for(ICMSConfiguracaoTributaria detalhe : detalhes){
					detalhe.setConfiguracaoTributaria(currentBean);
					detalhe.setCsosnB(detalhe.getCsosn().getId().toString());
					detalhe.setCstB(detalhe.getCst().getId().toString());

					//detalhe.setModalidadeBc(((MODALIDADE_BC)(detalhe.getModalidadeBc().getValue())).getCodigo());

					icmsDAO.saveOrUpdate(detalhe);
				}
			}

			salvarPis(currentBean);
			salvarCofins(currentBean);
			salvarIpi(currentBean);
			notifiyFrameworkSaveOK(currentBean);
		}	catch (ErroValidacaoException e) {
			mensagemErro(e.montaMensagemErro());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void salvarPis(ConfiguracaoTributaria configuracai){
		PISConfiguracaoTributaria pis = new PISConfiguracaoTributaria();	

		try{
			pis.setConfiguracaoTributaria(currentBean);
			//    pis.setEmpresa(currentBean.getEmpresa());
			pis.setCst(subView.getTxtCstPis().getValue());
			pis.setCodigoApuracaoEfd(subView.getTxtEfdPis().getValue());
			pis.setModalidadeBc(((MODALIDADE_BC)(subView.getCmbModalidadeBcPis().getValue())).getCodigo());
			pis.setPorcentoBc(new BigDecimal(subView.getTxtPorcentoBcPis().getValue()));
			pis.setAliquotaPorcento(new BigDecimal(subView.getTxtAliquotaBcPis().getValue()));
			pis.setAliquotaUnidade(new BigDecimal(subView.getTxtAliquotaUnidadePis().getValue()));
			pis.setValorPautaFiscal(new BigDecimal(formataValor(subView.getTxtValorPautaFiscalPis().getValue())));
			pis.setValorPrecoMaximo(new BigDecimal(formataValor(subView.getTxtValorPrecoMaximoPis().getValue())));
			pisDAO.saveOrUpdate(pis);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void salvarCofins(ConfiguracaoTributaria configuracao){
		CofinsConfiguracaoTributaria cofins = new CofinsConfiguracaoTributaria();	

		try{
			cofins.setConfiguracaoTributaria(currentBean);
			//cofins.setEmpresa(currentBean.getEmpresa());
			cofins.setCst(subView.getTxtCstCofins().getValue());
			cofins.setCodigoApuracaoEfd(subView.getTxtEfdCofins().getValue());
			cofins.setModalidadeBc(((MODALIDADE_BC)(subView.getCmbModalidadeBcCofins().getValue())).getCodigo());
			cofins.setPorcentoBc(new BigDecimal(subView.getTxtPorcentoBcCofins().getValue()));
			cofins.setAliquotaPorcento(new BigDecimal(subView.getTxtAliquotaBcCofins().getValue()));
			cofins.setAliquotaUnidade(new BigDecimal(subView.getTxtAliquotaUnidadeCofins().getValue()));
			cofins.setValorPautaFiscal(new BigDecimal(formataValor(subView.getTxtValorPautaFiscalCofins().getValue())));
			cofins.setValorPrecoMaximo(new BigDecimal(formataValor(subView.getTxtValorPrecoMaximoCofins().getValue())));
			cofinsDAO.saveOrUpdate(cofins);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void salvarIpi(ConfiguracaoTributaria configuracai){
		IPIConfiguracaoTributaria ipi = new IPIConfiguracaoTributaria();	

		try{
			ipi.setConfiguracaoTributaria(currentBean);
			//ipi.setEmpresa(currentBean.getEmpresa());
			ipi.setCst(subView.getTxtCstIPI().getValue());
			ipi.setModalidadeBc(((MODALIDADE_BC)(subView.getCmbModalidadeBcIPI().getValue())).getCodigo());
			String dipi = subView.getTxtDipi().getValue();
			if(Validator.validateString(dipi))ipi.setTipoReceitaDipi(new Integer(dipi));
			ipi.setPorcentoBc(new BigDecimal(subView.getTxtPorcentoBcIPI().getValue()));
			ipi.setAliquotaPorcento(new BigDecimal(subView.getTxtAliquotaBcIPI().getValue()));
			ipi.setAliquotaUnidade(new BigDecimal(subView.getTxtAliquotaUnidadeIPI().getValue()));
			ipi.setValorPautaFiscal(new BigDecimal(formataValor(subView.getTxtValorPautaFiscalIPI().getValue())));
			ipi.setValorPrecoMaximo(new BigDecimal(formataValor(subView.getTxtValorPrecoMaximoIPI().getValue())));
			ipiDAO.saveOrUpdate(ipi);
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	@Override
	protected void quandoNovo() {
		try{
			//subView.filContagemEstoqueDetalhesSubForm(currentBean.getContagemDetalhes());
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Configuracao Tributaria";
	}

	@Override
	protected void remover(List<Serializable> ids) {

		try{
			for(Serializable id : ids){
				ConfiguracaoTributaria configuracao = dao.find(id);

				PISConfiguracaoTributaria pis = pisDAO.buscarPorConfiguracao(configuracao);
				if(pis!=null)pisDAO.delete(pis);

				IPIConfiguracaoTributaria ipi =ipiDAO.buscarPorConfiguracao(configuracao);
				if(ipi!=null)ipiDAO.delete(ipi);

				CofinsConfiguracaoTributaria cofins =cofinsDAO.buscarPorConfiguracao(configuracao);
				if(cofins!=null)cofinsDAO.delete(cofins);

				List<ICMSConfiguracaoTributaria> listaIcms = icmsDAO.buscarPorConfiguracao(configuracao);

				if(listaIcms!=null){
					for(ICMSConfiguracaoTributaria obj : listaIcms){
						icmsDAO.delete(obj);
					}
				}

			}
			dao.deleteAllByIds(ids);

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		System.out.println("");

	}

	@Override
	public boolean isFullSized(){
		return true;
	}

	public List<GrupoTributario> trazerGrupos(){
		return grupoTributarioDAO.listaTodos();
	}

	public List<OperacaoFiscal> trazerOperacoes(){
		return operacaoFiscalDAO.listaTodos();
	}

	public List<UF> listarUfs(){
		return ufDAO.listaTodos();
	}

	public ICMSConfiguracaoTributaria novoIcms(){
		ICMSConfiguracaoTributaria objeto = new ICMSConfiguracaoTributaria();
		currentBean.adicionarIcms(objeto);
		return objeto;
	}

	public CstPis consultarCstPis(String codigo){
		return cstPisDAO.procuraPorCodigo(codigo);
	}

	public CstCofins consultarCstCofins(String codigo){
		return cstCofinsDAO.procuraPorCodigo(codigo);
	}

	public CstIpi consultarCstIpi(String codigo){
		return cstIpiDAO.procuraPorCodigo(codigo);
	}

	public CodigoApuracaoEfd consultarEfd(String codigo){
		return efdDAO.procuraPorCodigo(codigo);
	}

	public TipoReceitaDipi consultarDipi(String codigo){
		return dipiDAO.procuraPorCodigo(codigo);
	}

	public String formataValor(String valor){
		String format = "";
		format = valor.replace("R$","").
				substring(0,valor.indexOf(",")).

				replaceAll( ",","" ).trim();
		return format;
	}

	public BeanItemContainer<Csosnb> carregarCsosnb(){
		BeanItemContainer<Csosnb> container = new BeanItemContainer<>(Csosnb.class);
		for(Csosnb obj : csosnbDAO.listaTodos()){
			container.addBean(obj);
		}
		return container;
	}
	public BeanItemContainer<CstIcmsB> carregarCstB(){
		BeanItemContainer<CstIcmsB> container = new BeanItemContainer<>(CstIcmsB.class);
		for(CstIcmsB obj : cstbDAO.listaTodos()){
			container.addBean(obj);
		}
		return container;
	}

	public BeanItemContainer<Cfop> carregarCfop(){
		BeanItemContainer<Cfop> container = new BeanItemContainer<>(Cfop.class);
		for(Cfop obj : cfopDAO.listaTodos()){
			container.addBean(obj);
		}
		return container;
	}
}
