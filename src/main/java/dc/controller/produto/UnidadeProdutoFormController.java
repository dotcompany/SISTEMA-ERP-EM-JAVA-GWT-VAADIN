package dc.controller.produto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.produto.UnidadeProduto;
import dc.servicos.dao.produto.UnidadeProdutoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.produto.UnidadeProdutoFormView;

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
public class UnidadeProdutoFormController extends CRUDFormController<UnidadeProduto> {

	UnidadeProdutoFormView subView;
	
	@Autowired
	UnidadeProdutoDAO unidadeProdutoDAO;

	private UnidadeProduto currentBean;
	
	@Override
	protected String getNome() {
		return "Unidade Produto";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override  
	protected void actionSalvar() {
		
		currentBean.setSigla(subView.getTxtSigla().getValue());
		currentBean.setDescricao(subView.getTxtDescricao().getValue());
		currentBean.setPodeFracionar(subView.getCbPodeFracionar().toString());
		try{
			unidadeProdutoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);	
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}


	@Override
	protected void carregar(Serializable id) {
		currentBean = unidadeProdutoDAO.find(id);
		subView.getTxtSigla().setValue(currentBean.getSigla());
		subView.getTxtDescricao().setValue(currentBean.getDescricao());	
	}
	
	/* Callback para quando novo foi acionado. Colocar Programação customizada para essa ação aqui. Ou então deixar em branco, para comportamento padrão */
	@Override
	protected void quandoNovo() {
		
	}

	@Override
	protected void initSubView() {
		subView = new UnidadeProdutoFormView();
	}

	/* Deve sempre atribuir a current Bean uma nova instancia do bean do formulario.*/
	@Override
	protected void criarNovoBean() {
		currentBean = new UnidadeProduto();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		unidadeProdutoDAO.deleteAllByIds(ids);
		 mensagemRemovidoOK();
	}

	@Override
	protected boolean validaSalvar() {
		if(subView.getTxtSigla().getValue() ==  null || subView.getTxtSigla().getValue().isEmpty()){
			adicionarErroDeValidacao(subView.getTxtSigla(),"Não pode ficar em branco");
			return false;
		}
		
		if(subView.getTxtDescricao().getValue() ==  null || subView.getTxtDescricao().getValue().isEmpty()){
			adicionarErroDeValidacao(subView.getTxtDescricao(),"Não pode ficar em branco");
			return false;
		}
		
		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "unidadeProdutoForm";
	}

}
