package dc.controller.diversos;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.diversos.Pais;
import dc.servicos.dao.diversos.PaisDAO;
import dc.servicos.util.Validator;
import dc.visao.diversos.PaisFormView;
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
public class PaisFormController extends CRUDFormController<Pais> {

	PaisFormView subView;
	
	@Autowired
	PaisDAO paisDAO;
	
	private Pais currentBean;
	
	@Override
	protected String getNome() {
		return "Pais";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override  
	protected void actionSalvar() {
		
		currentBean.setNomePtbr(subView.getTxtNome().getValue());
		currentBean.setNomeEn(subView.getTxtNomeIngles().getValue());
		currentBean.setSigla2(subView.getTxtSigla2().getValue());
		currentBean.setSigla3(subView.getTxtSigla3().getValue());
		try{
			paisDAO.saveOrUpdate(currentBean);
			mensagemSalvoOK();	
		}catch (Exception e){
			e.printStackTrace();
		}
	}


	@Override
	protected void carregar(Serializable id) {
		currentBean = paisDAO.find(id);
		subView.getTxtNome().setValue(currentBean.getNomePtbr());
		subView.getTxtNomeIngles().setValue(currentBean.getNomeEn());
		subView.getTxtSigla2().setValue(currentBean.getSigla2());
		subView.getTxtSigla3().setValue(currentBean.getSigla3());
	}
	
	/* Callback para quando novo foi acionado. Colocar Programação customizada para essa a��o aqui. Ou então deixar em branco, para comportamento padr�o */
	@Override
	protected void quandoNovo() {
		
	}

	@Override
	protected void initSubView() {
		subView = new PaisFormView();
	}

	/* Deve sempre atribuir a current Bean uma nova instancia do bean do formulario.*/
	@Override
	protected void criarNovoBean() {
		currentBean = new Pais();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		paisDAO.deleteAllByIds(ids);
		 mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */ 
	@Override
	protected boolean validaSalvar() {
		
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtNomeIngles().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNomeIngles(),
					"Não pode ficar em branco");
			valido = false;
		}
		
		if (!Validator.validateString(subView.getTxtSigla2().getValue())) {
			adicionarErroDeValidacao(subView.getTxtSigla2(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtSigla3().getValue())) {
			adicionarErroDeValidacao(subView.getTxtSigla3(),
					"Não pode ficar em branco");
			valido = false;
		}
		
		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "paisForm";
	}

}
