package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.ordemservico.ParametroOs;
import dc.servicos.dao.ordemservico.ParametroOsDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.ParametroOsFormView;
import dc.visao.spring.SecuritySessionProvider;

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

			boolean valido = validaSalvar();
			
			if(valido) {
				currentBean.setLimparBdAut(Boolean.valueOf(subView.getCbLimparBD().getValue().toString()));
				currentBean.setVendedorProduto(Boolean.valueOf(subView.getCbVendedorProduto().getValue().toString()));
				currentBean.setValorPagoPeca(Boolean.valueOf(subView.getCbValorPagoPeca().getValue().toString()));
				currentBean.setDescontoGeral(Boolean.valueOf(subView.getCbDesconto().getValue().toString()));
				currentBean.setTecnicoProduto(Boolean.valueOf(subView.getCbTecnicoProduto().getValue().toString()));
				currentBean.setVendedorAtendente(Boolean.valueOf(subView.getCbVendedorAtendente().getValue().toString()));
				currentBean.setVendedorServico(Boolean.valueOf(subView.getCbVendedorServico().getValue().toString()));
				currentBean.setOrdemServicoSimples(Boolean.valueOf(subView.getCbOsSimplificada().getValue().toString()));
				currentBean.setQtdDiasRevisao(Boolean.valueOf(subView.getCbProximaRevisao().getValue().toString()));
				currentBean.setMatricialTotalLinhas(Integer.valueOf(subView.getTfMatricialLinhas().getValue()));
				currentBean.setMatricialEntrelinhas(Integer.valueOf(subView.getTfEntrelinhas().getValue()));
				currentBean.setQtdDiasPadrao(Integer.valueOf(subView.getTfDiasPadrao().getValue()));
				currentBean.setObsDefeitoPadrao(subView.getTaDefeitoApresentado().getValue());
				currentBean.setObsPadraoOsSimples(subView.getTaObsPadraoSimpes().getValue());
				currentBean.setObsPadrao(subView.getTaObsPadrao().getValue());
				currentBean.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
				

				System.out.println("Vai salvar: "+currentBean.getLimparBdAut());
				dao.saveOrUpdate(currentBean);
				notifiyFrameworkSaveOK(this.currentBean);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Override
	protected void carregar(Serializable id) {
		currentBean = dao.find((Integer) id);
		
		subView.getCbLimparBD().setValue(currentBean.getLimparBdAut());
		subView.getCbVendedorProduto().setValue(currentBean.getVendedorProduto());
		subView.getCbValorPagoPeca().setValue(currentBean.getValorPagoPeca());
		subView.getCbDesconto().setValue(currentBean.getDescontoGeral());;
		subView.getCbTecnicoProduto().setValue(currentBean.getTecnicoProduto());
		subView.getCbVendedorAtendente().setValue(currentBean.getVendedorAtendente());
		subView.getCbVendedorServico().setValue(currentBean.getVendedorServico());
		subView.getCbOsSimplificada().setValue(currentBean.getOrdemServicoSimples());
		subView.getCbProximaRevisao().setValue(currentBean.getQtdDiasRevisao());
		subView.getTfMatricialLinhas().setValue(currentBean.getMatricialTotalLinhas().toString());
		subView.getTfEntrelinhas().setValue(currentBean.getMatricialEntrelinhas().toString());
		subView.getTfDiasPadrao().setValue(currentBean.getQtdDiasPadrao().toString());
		subView.getTaDefeitoApresentado().setValue(currentBean.getObsDefeitoPadrao());
		subView.getTaObsPadraoSimpes().setValue(currentBean.getObsPadraoOsSimples());
		subView.getTaObsPadrao().setValue(currentBean.getObsPadrao());
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
		System.out.println("Chegou no valida salvar");
		
		boolean valido = true;

		if (!Validator.validateObject(subView.getCbLimparBD().getValue())) {
			valido = false;
			mensagemErro("O campo limpar banco de dados automaticamente não pode ficar em branco.");
		}
		if (!Validator.validateObject(subView.getCbVendedorAtendente().getValue())) {
			valido = false;
			mensagemErro("O campo usar vendedor\\atendente não pode ficar em branco.");
		}
		if (!Validator.validateObject(subView.getCbVendedorProduto().getValue())) {
			valido = false;
			mensagemErro("O campo usar vendedor no lançamento de produto não pode ficar em branco.");
		}
		if (!Validator.validateObject(subView.getCbValorPagoPeca().getValue())) {
			valido = false;
			mensagemErro("O campo usar campo valor pago na peça\\produto não pode ficar em branco.");
		}
		if (!Validator.validateObject(subView.getCbDesconto().getValue())) {
			valido = false;
			mensagemErro("O campo usar desconto geral não pode ficar em branco.");
		}
		if (!Validator.validateObject(subView.getCbTecnicoProduto().getValue())) {
			valido = false;
			mensagemErro("O campo usar técnico no lançamento de produto não pode ficar em branco.");
		}
		if (!Validator.validateObject(subView.getCbVendedorServico().getValue())) {
			valido = false;
			mensagemErro("O campo usar vendedor no lançamento de serviço não pode ficar em branco.");
		}
		if (!Validator.validateObject(subView.getCbOsSimplificada().getValue())) {
			valido = false;
			mensagemErro("O campo usar O.S simplifcada não pode ficar em branco.");
		}
		if (!Validator.validateObject(subView.getCbProximaRevisao().getValue())) {
			valido = false;
			mensagemErro("O campo perguntar quantidade dias próxima revisão não pode ficar em branco.");
		}

		return valido;
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

