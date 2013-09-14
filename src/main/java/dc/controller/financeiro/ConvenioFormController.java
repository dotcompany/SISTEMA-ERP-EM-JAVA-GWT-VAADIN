package dc.controller.financeiro;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.financeiro.Convenio;
import dc.servicos.dao.financeiro.ConvenioDAO;
import dc.visao.financeiro.ConvenioFormView;
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
public class ConvenioFormController extends CRUDFormController<Convenio> {

	ConvenioFormView subView;
	
	@Autowired
	ConvenioDAO convenioDAO;

	private Convenio currentBean;
	
	@Override
	protected String getNome() {
		return "Convenio";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override  
	protected void actionSalvar() {
		String logradouro = subView.getTxtLogradouro().getValue();
		Date dataVencimento = subView.getDnDataVencimento().getValue();
		String numero = subView.getTxtNumero().getValue();
		String bairro = subView.getTxtBairro().getValue();
		String contato = subView.getTxtContato().getValue();
		String telefone = subView.getTxtTelefone().getValue();
		String descricao = subView.getTxtDescricao().getValue();
		String cep = subView.getTxtCep().getValue();
		currentBean.setLogradouro(logradouro);
		currentBean.setDataVencimento(dataVencimento);
		currentBean.setNumero(numero);
		currentBean.setBairro(bairro);
		currentBean.setContato(contato);
		currentBean.setTelefone(telefone);
		currentBean.setDescricao(descricao);
		currentBean.setCep(cep);
		try{
			convenioDAO.saveOrUpdate(currentBean);
			mensagemSalvoOK();	
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}


	@Override
	protected void carregar(Serializable id) {
		currentBean = convenioDAO.find(id);
		subView.getTxtLogradouro().setValue(currentBean.getLogradouro());
		subView.getTxtNumero().setValue(currentBean.getNumero());
		subView.getTxtBairro().setValue(currentBean.getBairro());
		subView.getTxtContato().setValue(currentBean.getContato());
		subView.getTxtTelefone().setValue(currentBean.getTelefone());
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
		subView.getTxtCep().setValue(currentBean.getCep());
	}
	
	/* Callback para quando novo foi acionado. Colocar Programação customizada para essa ação aqui. Ou então deixar em branco, para comportamento padrão */
	@Override
	protected void quandoNovo() {
		
	}

	@Override
	protected void initSubView() {
		subView = new ConvenioFormView();
	}

	/* Deve sempre atribuir a current Bean uma nova instancia do bean do formulario.*/
	@Override
	protected void criarNovoBean() {
		currentBean = new Convenio();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		 convenioDAO.deleteAllByIds(ids);
		 mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */ 
	@Override
	protected boolean validaSalvar() {
		if(subView.getTxtLogradouro().getValue() ==  null || subView.getTxtLogradouro().getValue().isEmpty()){
			//Utilizar adicionarErroDeValidacao() para adicionar mensagem de erro para o campo que esta sendo validado
			adicionarErroDeValidacao(subView.getTxtLogradouro(),"Não pode ficar em Branco!");
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
		return "convenioForm";
	}

}
