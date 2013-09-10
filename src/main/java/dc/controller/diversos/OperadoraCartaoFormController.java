package dc.controller.diversos;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.diversos.OperadoraCartao;
import dc.servicos.dao.diversos.OperadoraCartaoDAO;
import dc.visao.diversos.OperadoraCartaoFormView;
import dc.visao.framework.geral.CRUDFormController;

/**
*
* @author Wesley Jr
/*
 * Nessa classe ela pega a classe principal que é o CRUD, que tem todos os controllers
 * da Tela, onde quando extendemos herdamos os métodos que temos na tela principal.
 * Temos o botão Novo que é para Criar uma nova Tela, para adicionar informações
 * novas, e dentro temos o Button Salvar que é para salvar as informações no Banco de Dados
 * Temos o carregar também que é para pegar as informações que desejarmos quando
 * formos pesquisar na Tela.
 *
*/

@Controller
@Scope("prototype")
public class OperadoraCartaoFormController extends CRUDFormController<OperadoraCartao> {

	OperadoraCartaoFormView subView;
	
	@Autowired
	OperadoraCartaoDAO operadoraDAO;

	private OperadoraCartao currentBean;
	
	@Override
	protected String getNome() {
		return "OperadoraCartao";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override  
	protected void actionSalvar() {
		String nome = subView.getTxtNome().getValue();
		String bandeira = subView.getTxtBandeira().getValue();
		String fone1 = subView.getTxtTelefone1().getValue();
		String fone2 = subView.getTxtTelefone2().getValue();
		currentBean.setNome(nome);
		currentBean.setBandeira(bandeira);
		currentBean.setFone1(fone1);
		currentBean.setFone2(fone2);
		try{
			operadoraDAO.saveOrUpdate(currentBean);
			mensagemSalvoOK();	
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}


	@Override
	protected void carregar(Serializable id) {
		currentBean = operadoraDAO.find(id);
		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtBandeira().setValue(currentBean.getBandeira());
		subView.getTxtTelefone1().setValue(currentBean.getFone1());
		subView.getTxtTelefone2().setValue(currentBean.getFone2());
	}
	
	/* Callback para quando novo foi acionado. Colocar Programação customizada para essa a��o aqui. Ou então deixar em branco, para comportamento padr�o */
	@Override
	protected void quandoNovo() {
		
	}

	@Override
	protected void initSubView() {
		subView = new OperadoraCartaoFormView();
	}

	/* Deve sempre atribuir a current Bean uma nova instancia do bean do formulario.*/
	@Override
	protected void criarNovoBean() {
		currentBean = new OperadoraCartao();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		 operadoraDAO.deleteAllByIds(ids);
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
		return "operadoraCartaoForm";
	}

}
