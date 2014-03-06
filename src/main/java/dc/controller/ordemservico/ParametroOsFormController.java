package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.ordemservico.ParametroOs;
import dc.servicos.dao.ordemservico.ParametroOsDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.ParametroOsFormView;

@Controller
@Scope("prototype")
public class ParametroOsFormController extends CRUDFormController<ParametroOs>{

	private static final long serialVersionUID = 1L;

	@Autowired
	ParametroOsDAO dao;

	private ParametroOs currentBean;

	ParametroOsFormView subView;

	@Override
	protected String getNome() {
		return "Parâmetro de ordem de serviço";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
//			currentBean.setCliente(subView.getCbCliente().getValue());
//			currentBean.setValorServico(new BigDecimal(subView.getTfTotalServico().getValue()));
//			currentBean.setValorPeca(new BigDecimal(subView.getTfTotalProdutoGeral().getValue()));
//			currentBean.setValorFrete(new BigDecimal(subView.getTfTotalFreteGeral().getValue()));
//			currentBean.setValorOutros(new BigDecimal(subView.getTfTotalOutrosGeral().getValue()));
//			currentBean.setValorDesconto(new BigDecimal(subView.getTfDescontoGeral().getValue()));
//			currentBean.setValorTotalOs(new BigDecimal(subView.getTfTotalGeral().getValue()));
			
			dao.saveOrUpdate(currentBean);

//			salvarInformacaoGeral();	
//			salvarLaudoTecnico();
//			salvarEntradaServico();
//			salvarTransporte();
//			salvarFatura();

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			mensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}



	@Override
	protected void carregar(Serializable id) {
		currentBean = dao.find((Integer) id);
		
//		subView.getCbTipoServico().setValue(currentBean.);
		
		
//		subView.carregarFormaPagamento();
//		subView.carregarFormaEmissao();
//		subView.carregarTipoOperacao();
//		subView.carregarFinalidadeEmissao();
//		subView.carregarTipoDANFE();
//		subView.carregarCRT();
	//	subView.carregarView(currentBean);

//		carregarEmitente();
//		carregarCuponsVinculados();	
//		carregarNotasReferenciadas();
//		carregarLocalEntrega(); 
//		carregarLocalRetirada();
//		carregarTransporte();
//		carregarFatura();
//		carregarDuplicatas();

	}

	@Override
	protected void initSubView() {
		subView = new ParametroOsFormView(this);
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new ParametroOs();

	}

	@Override
	public String getViewIdentifier() {
		return "parametroOsForm";
	}

	@Override
	protected boolean validaSalvar() {
		return true;
	}

	@Override
	protected void quandoNovo() {
	}

	@Override
	protected void remover(List<Serializable> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public boolean isFullSized(){
		return true;
	}

}

