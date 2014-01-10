package dc.controller.geral;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.geral.NivelFormacao;
import dc.servicos.dao.geral.NivelFormacaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.NivelFormacaoFormView;


/**
*
* @author Wesley Jr
* **/

@Controller
@Scope("prototype")
public class NivelFormacaoFormController extends CRUDFormController<NivelFormacao> {

	NivelFormacaoFormView subView;
	
	@Autowired
	NivelFormacaoDAO nivelFormacaoDAO;

	private NivelFormacao currentBean;
	
	@Override
	protected String getNome() {
		return "Nivel Formação";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override  
	protected void actionSalvar() {
		try{
			
			currentBean.setNome(subView.getTxtNome().getValue());
			currentBean.setDescricao(subView.getTxtDescricao().getValue());
			
			nivelFormacaoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);	
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}


	@Override
	protected void carregar(Serializable id) {
		currentBean = nivelFormacaoDAO.find(id);
		subView.getTxtNome().setValue(currentBean.getNome());
	}
	
	/* Callback para quando novo foi acionado. Colocar Programação customizada para essa ação aqui. Ou então deixar em branco, para comportamento padrão */
	@Override
	protected void quandoNovo() {
		
	}

	@Override
	protected void initSubView() {
		subView = new NivelFormacaoFormView();
	}

	/* Deve sempre atribuir a current Bean uma nova instancia do bean do formulario.*/
	@Override
	protected void criarNovoBean() {
		currentBean = new NivelFormacao();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		 nivelFormacaoDAO.deleteAllByIds(ids);
		 mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */ 
	@Override
	protected boolean validaSalvar() {
		if(subView.getTxtNome().getValue() ==  null || subView.getTxtNome().getValue().isEmpty()){
			adicionarErroDeValidacao(subView.getTxtNome(),"Não pode ficar em Branco!");
			return false;
		}
		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "nivelFormacaoForm";
	}

}
