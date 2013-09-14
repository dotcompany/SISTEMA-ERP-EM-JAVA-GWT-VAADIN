package dc.controller.tabelas;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.tabelas.Csosnb;
import dc.servicos.dao.tabelas.CsosnbDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.tabelas.CsosnbFormView;



/**
*
* @author Wesley Jr
/*
 *
*/

@Controller
@Scope("prototype")
public class CsosnbFormController extends CRUDFormController<Csosnb> {

	CsosnbFormView subView;
	
	@Autowired
	CsosnbDAO csosnbDAO;

	private Csosnb currentBean;
	
	@Override
	protected String getNome() {
		return "Csosnb";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override  
	protected void actionSalvar() {
		
		currentBean.setCodigo(subView.getTxtCodigo().getValue());
		currentBean.setObservacao(subView.getTxtObservacao().getValue());
		currentBean.setDescricao(subView.getTxtDescricao().getValue());
		try{
			csosnbDAO.saveOrUpdate(currentBean);
			mensagemSalvoOK();	
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}


	@Override
	protected void carregar(Serializable id) {
		currentBean = csosnbDAO.find(id);
		subView.getTxtCodigo().setValue(currentBean.getCodigo());
		subView.getTxtObservacao().setValue(currentBean.getObservacao());	
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
	}
	
	/* Callback para quando novo foi acionado. Colocar Programação customizada para essa ação aqui. Ou então deixar em branco, para comportamento padrão */
	@Override
	protected void quandoNovo() {
		
	}

	@Override
	protected void initSubView() {
		subView = new CsosnbFormView();
	}

	/* Deve sempre atribuir a current Bean uma nova instancia do bean do formulario.*/
	@Override
	protected void criarNovoBean() {
		currentBean = new Csosnb();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		 csosnbDAO.deleteAllByIds(ids);
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
		return "csosnbForm";
	}

}
