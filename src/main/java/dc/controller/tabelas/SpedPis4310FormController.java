package dc.controller.tabelas;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.tabelas.SpedPis4310;
import dc.servicos.dao.tabelas.SpedPis4310DAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.tabelas.SpedPis4310FormView;



/**
*
* @author Wesley Jr
/*
 *
*/

@Controller
@Scope("prototype")
public class SpedPis4310FormController extends CRUDFormController<SpedPis4310> {

	SpedPis4310FormView subView;
	
	@Autowired
	SpedPis4310DAO spedPis4310DAO;

	private SpedPis4310 currentBean;
	
	@Override
	protected String getNome() {
		return "Sped Pis 4310";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override  
	protected void actionSalvar() {
		
		currentBean.setDescricao(subView.getTxtDescricao().getValue());
		currentBean.setObservacao(subView.getTxtObservacao().getValue());
		subView.getDtFimVigencia().setValue(currentBean.getFimVigencia());
		subView.getDtInicioVigencia().setValue(currentBean.getInicioVigencia());
		try{
			spedPis4310DAO.saveOrUpdate(currentBean);
			mensagemSalvoOK();	
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}


	@Override
	protected void carregar(Serializable id) {
		currentBean = spedPis4310DAO.find(id);
		subView.getTxtDescricao().setValue(currentBean.getDescricao());	
		subView.getTxtObservacao().setValue(currentBean.getObservacao());
		subView.getDtInicioVigencia().setValue(currentBean.getInicioVigencia());
		subView.getDtFimVigencia().setValue(currentBean.getFimVigencia());
	}
	
	/* Callback para quando novo foi acionado. Colocar Programação customizada para essa ação aqui. Ou então deixar em branco, para comportamento padrão */
	@Override
	protected void quandoNovo() {
		
	}

	@Override
	protected void initSubView() {
		subView = new SpedPis4310FormView();
	}

	/* Deve sempre atribuir a current Bean uma nova instancia do bean do formulario.*/
	@Override
	protected void criarNovoBean() {
		currentBean = new SpedPis4310();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		spedPis4310DAO.deleteAllByIds(ids);
		 mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */ 
	@Override
	protected boolean validaSalvar() {
		if(subView.getTxtDescricao().getValue() ==  null || subView.getTxtDescricao().getValue().isEmpty()){
			//Utilizar adicionarErroDeValidacao() para adicionar mensagem de erro para o campo que esta sendo validado
			adicionarErroDeValidacao(subView.getTxtDescricao(),"Não pode ficar em Branco!");
			return false;
		}
		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "spedPis4310Form";
	}

}
