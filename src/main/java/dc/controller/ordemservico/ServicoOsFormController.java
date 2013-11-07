package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.ServicoOsFormView;
import dc.entidade.ordemservico.Grupo;
import dc.entidade.ordemservico.ServicoOs;
import dc.entidade.ordemservico.SubGrupo; 
import dc.servicos.dao.ordemservico.GrupoDAO;
import dc.servicos.dao.ordemservico.ServicoOsDAO;
import dc.servicos.dao.ordemservico.SubGrupoDAO;

/**
*
* @author Paulo Sérgio
*/ 

@Controller
@Scope("prototype")
public class ServicoOsFormController extends CRUDFormController<ServicoOs> {

	private static final long serialVersionUID = 1L;

	ServicoOsFormView subView;
	
	@Autowired
	ServicoOsDAO servicoDAO;
	
	@Autowired
	GrupoDAO grupoDAO;

	@Autowired
	SubGrupoDAO subGrupoDAO;

	private ServicoOs currentBean;
	
	@Override
	protected String getNome() {
		return "Servico";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override  
	protected void actionSalvar() {
	    currentBean.setDescricao(subView.getTfDescricao().getValue());
		currentBean.setGrupo(subView.getCbGrupo().getValue());
		currentBean.setSubGrupo(subView.getCbSubGrupo().getValue()); 
		currentBean.setAliquotaIssqn(Double.parseDouble(subView.getTfAliquotaIssqn().getValue()));
		currentBean.setValorComissaoTecnico(Double.parseDouble(subView.getTfValorComissaoTecnico().getValue()));
		currentBean.setValorComissaoVendedor(Double.parseDouble(subView.getTfValorComissaoVendedor().getValue()));
		currentBean.setValorServico(Double.parseDouble(subView.getTfValorServico().getValue()));
		currentBean.setValorMinimoServico(Double.parseDouble(subView.getTfValorMinimoServico().getValue()));
		currentBean.setGarantiaDia(Integer.parseInt(subView.getTfGarantiaDia().getValue()));
		currentBean.setRetorno(Integer.parseInt(subView.getTfRetorno().getValue()));
		currentBean.setHoraGasta(Double.parseDouble(subView.getTfHoraGasta().getValue()));
//		currentBean.setAtiva(subView.getTfAtiva().getValue());
		currentBean.setValorPromocional(Double.parseDouble(subView.getTfValorPromocional().getValue()));
		currentBean.setVencimentoPromocao(subView.getDtVencimentoPromocao().getValue());
		currentBean.setObservacao(subView.getTaObservacao().getValue());
		try{
			servicoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);	
		}catch (Exception e){
			e.printStackTrace();
		}
	}


	@Override
	protected void carregar(Serializable id) {
		currentBean = servicoDAO.find(id);
		
		subView.getTfDescricao().setValue(currentBean.getDescricao());
		subView.getCbGrupo().setValue(currentBean.getGrupo());
		subView.getCbSubGrupo().setValue(currentBean.getSubGrupo());
		subView.getTfAliquotaIssqn().setValue(currentBean.getAliquotaIssqn().toString());
		subView.getTfValorComissaoTecnico().setValue(currentBean.getValorComissaoTecnico().toString());
		subView.getTfValorComissaoVendedor().setValue(currentBean.getValorComissaoVendedor().toString());
		subView.getTfValorServico().setValue(currentBean.getValorServico().toString());
		subView.getTfValorMinimoServico().setValue(currentBean.getValorMinimoServico().toString());
		subView.getTfGarantiaDia().setValue(currentBean.getGarantiaDia().toString());
		subView.getTfRetorno().setValue(currentBean.getRetorno().toString());
		subView.getTfHoraGasta().setValue(currentBean.getHoraGasta().toString());
		subView.getTfAtiva().setValue(currentBean.getAtiva().toString());
		subView.getTfValorPromocional().setValue(currentBean.getValorPromocional().toString());
//		subView.getTfVencimentoPromocao().setValue(currentBean.getVencimentoPromocao());
		subView.getTaObservacao().setValue(currentBean.getObservacao());
	}
	
	/* Callback para quando novo foi acionado. Colocar ProgramaÃ§Ã£o customizada para essa aÃ§Ã£o aqui. Ou entÃ£o deixar em branco, para comportamento padrÃ£o */
	@Override
	protected void quandoNovo() {
		
	}

	@Override
	protected void initSubView() {
		subView = new ServicoOsFormView();
		
		preencheCombos();
	}

	/* Deve sempre atribuir a current Bean uma nova instancia do bean do formulario.*/
	@Override
	protected void criarNovoBean() {
		currentBean = new ServicoOs();
	}
	
	private void preencheCombos() {

		DefaultManyToOneComboModel<Grupo> grupo = new DefaultManyToOneComboModel<Grupo>(GrupoListController.class,
				this.grupoDAO, super.getMainController());

		this.subView.getCbGrupo().setModel(grupo);
		
		DefaultManyToOneComboModel<SubGrupo> subGrupo = new DefaultManyToOneComboModel<SubGrupo>(SubGrupoListController.class,
				this.subGrupoDAO, super.getMainController());

		this.subView.getCbSubGrupo().setModel(subGrupo);

	}
	
	@Override
	protected void remover(List<Serializable> ids) {
		 servicoDAO.deleteAllByIds(ids);
		 mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */ 
	@Override
	protected boolean validaSalvar() {
		
		boolean valido = true;

		if (!Validator.validateString(subView.getTfDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTfDescricao(), "Não pode ficar em branco");
			valido = false;
		}
		
		return valido;
	}
 
	@Override 
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "servicoForm";
	}
} 
