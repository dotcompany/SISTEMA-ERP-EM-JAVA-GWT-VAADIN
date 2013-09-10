package dc.controller.geral;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.geral.Cidade;
import dc.servicos.dao.geral.CidadeDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.CidadeFormView;


/**
*
* @author Wesley Jr
* **/

@Controller
@Scope("prototype")
public class CidadeFormController extends CRUDFormController<Cidade> {

	CidadeFormView subView;
	
	@Autowired
	CidadeDAO cidadeDAO;

	private Cidade currentBean;
	
	@Override
	protected String getNome() {
		return "Cidade";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override  
	protected void actionSalvar() {
		String nome = subView.getTxtNome().getValue();
		currentBean.setNome(nome);
		try{
			cidadeDAO.saveOrUpdate(currentBean);
			mensagemSalvoOK();	
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}


	@Override
	protected void carregar(Serializable id) {
		currentBean = cidadeDAO.find(id);
		subView.getTxtNome().setValue(currentBean.getNome());
	}
	
	/* Callback para quando novo foi acionado. Colocar Programação customizada para essa a��o aqui. Ou então deixar em branco, para comportamento padr�o */
	@Override
	protected void quandoNovo() {
		
	}

	@Override
	protected void initSubView() {
		subView = new CidadeFormView();
	}

	/* Deve sempre atribuir a current Bean uma nova instancia do bean do formulario.*/
	@Override
	protected void criarNovoBean() {
		currentBean = new Cidade();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		 cidadeDAO.deleteAllByIds(ids);
		 mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */ 
	@Override
	protected boolean validaSalvar() {
		if(subView.getTxtNome().getValue() ==  null || subView.getTxtNome().getValue().isEmpty()){
			//Utilizar adicionarErroDeValidacao() para adicionar mensagem de erro para o campo que esta sendo validado
			adicionarErroDeValidacao(subView.getTxtNome(),"não pode ficar em branco");
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
		return "cidadeForm";
	}

}
