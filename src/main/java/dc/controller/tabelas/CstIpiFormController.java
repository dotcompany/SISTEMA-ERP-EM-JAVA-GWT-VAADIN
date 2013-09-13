package dc.controller.tabelas;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.tabelas.CstIpi;
import dc.servicos.dao.tabelas.CstIpiDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.tabelas.CstIpiFormView;



/**
*
* @author Wesley Jr
/*
 *
*/

@Controller
@Scope("prototype")
public class CstIpiFormController extends CRUDFormController<CstIpi> {

	CstIpiFormView subView;
	
	@Autowired
	CstIpiDAO cstIpiDAO;

	private CstIpi currentBean;
	
	@Override
	protected String getNome() {
		return "Cst Ipi";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override  
	protected void actionSalvar() {
		
		currentBean.setCodigo(subView.getTxtCodigo().getValue());
		currentBean.setDescricao(subView.getTxtDescricao().getValue());
		currentBean.setObservacao(subView.getTxtObservacao().getValue());
		try{
			cstIpiDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);	
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}


	@Override
	protected void carregar(Serializable id) {
		currentBean = cstIpiDAO.find(id);
		subView.getTxtCodigo().setValue(currentBean.getCodigo());
		subView.getTxtDescricao().setValue(currentBean.getDescricao());	
		subView.getTxtObservacao().setValue(currentBean.getObservacao());
	}
	
	/* Callback para quando novo foi acionado. Colocar Programação customizada para essa ação aqui. Ou então deixar em branco, para comportamento padrão */
	@Override
	protected void quandoNovo() {
		
	}

	@Override
	protected void initSubView() {
		subView = new CstIpiFormView();
	}

	/* Deve sempre atribuir a current Bean uma nova instancia do bean do formulario.*/
	@Override
	protected void criarNovoBean() {
		currentBean = new CstIpi();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		cstIpiDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */ 
	@Override
	protected boolean validaSalvar() {
		if(subView.getTxtDescricao().getValue() ==  null || subView.getTxtDescricao().getValue().isEmpty()){
			//Utilizar adicionarErroDeValidacao() para adicionar mensagem de erro para o campo que esta sendo validado
			adicionarErroDeValidacao(subView.getTxtDescricao(),"não pode ficar em branco");
			return false;
		}
		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "cstIpiForm";
	}

}
