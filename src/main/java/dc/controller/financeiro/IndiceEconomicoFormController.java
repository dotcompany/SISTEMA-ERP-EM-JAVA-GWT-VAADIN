package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.financeiro.IndiceEconomico;
import dc.servicos.dao.financeiro.IndiceEconomicoDAO;
import dc.visao.financeiro.IndiceEconomicoFormView;
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
public class IndiceEconomicoFormController extends CRUDFormController<IndiceEconomico> {

	IndiceEconomicoFormView subView;
	
	@Autowired
	IndiceEconomicoDAO indiceDAO;

	private IndiceEconomico currentBean;
	
	@Override
	protected String getNome() {
		return "IndiceEconomico";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override  
	protected void actionSalvar() {
		String nome = subView.getTxtNome().getValue();
		String sigla = subView.getTxtSigla().getValue();
		currentBean.setNome(nome);
		currentBean.setSigla(sigla);
		try{
			indiceDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);	
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}


	@Override
	protected void carregar(Serializable id) {
		currentBean = indiceDAO.find(id);
		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtSigla().setValue(currentBean.getSigla());		
	}
	
	/* Callback para quando novo foi acionado. Colocar Programação customizada para essa ação aqui. Ou então deixar em branco, para comportamento padrão */
	@Override
	protected void quandoNovo() {
		
	}

	@Override
	protected void initSubView() {
		subView = new IndiceEconomicoFormView();
	}

	/* Deve sempre atribuir a current Bean uma nova instancia do bean do formulario.*/
	@Override
	protected void criarNovoBean() {
		currentBean = new IndiceEconomico();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		 indiceDAO.deleteAllByIds(ids);
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
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "indiceEconomicoForm";
	}

}
