package dc.controller.sistema;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sun.istack.logging.Logger;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Component;

import dc.entidade.framework.FmMenu;
import dc.entidade.framework.FmModulo;
import dc.entidade.framework.Papel;
import dc.entidade.framework.PapelMenu;
import dc.servicos.dao.framework.geral.FmMenuDAO;
import dc.servicos.dao.sistema.PapelDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.sistema.PapelFormView;

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
public class PapelFormController extends CRUDFormController<Papel> {

	PapelFormView subView;
	
	@Autowired
	PapelDAO papelDAO;
	
	@Autowired
	FmMenuDAO menuDAO;

	private Papel currentBean = new Papel();
	
	public static Logger logger = Logger.getLogger(PapelFormController.class);
	
	@Override
	protected String getNome() {
		return "Papel";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override  
	protected void actionSalvar() {
		try{
			papelDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);	
		}catch (Exception e){
			mensagemErro("Problemas ao salvar registro");
			e.printStackTrace();
		}
	}


	@Override
	protected void carregar(Serializable id) {
		currentBean = papelDAO.find(id);
		subView.getBinder().setItemDataSource(this.currentBean);
		subView.populaModulos(papelDAO.getAll(FmModulo.class));
		subView.populaPapelMenu(papelDAO.getPapelMenusOrdered(currentBean));
	}
	
	/* Callback para quando novo foi acionado. Colocar Programação customizada para essa ação aqui. Ou então deixar em branco, para comportamento padrão */
	@Override
	protected void quandoNovo() {
		subView.getBinder().discard();
		subView.getBinder().setItemDataSource(getCurrentBean());
		subView.populaModulos(papelDAO.getAll(FmModulo.class));
	}

	@Override
	protected void initSubView() {
		subView = new PapelFormView(this);
	}

	/* Deve sempre atribuir a current Bean uma nova instancia do bean do formulario.*/
	@Override
	protected void criarNovoBean() {
		currentBean = new Papel();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		 papelDAO.deleteAllByIds(ids);
		 mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */ 
	@Override
	protected boolean validaSalvar() {
		boolean retornoValidacao = true;
		try {
			subView.getBinder().commit();
			Papel papel = subView.getBinder().getItemDataSource().getBean();
			List<PapelMenu> fs = subView.getPapelMenus();
			papel.setPapelMenus(fs);
			if (papel.getNome() == null || papel.getNome().isEmpty()) {
				adicionarErroDeValidacao(subView.getTxtNome(), "não pode ficar em branco");
				retornoValidacao = false;
			}
			if (papel.getPapelMenus() == null || papel.getPapelMenus().isEmpty()) {
				adicionarErroDeValidacao(subView.getTreeTable(), "Ao menos um menu deve ser associado ao papel");
				retornoValidacao = false;
			}
			this.currentBean = papel;
		} catch (CommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			retornoValidacao = false;
		}

		return retornoValidacao;
	}

	public Papel getCurrentBean() {
		return currentBean;
	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		 papelDAO.deleteAll(objetos);
		 mensagemRemovidoOK();		
	}

	public void loadModules(FmModulo modulo){
		List<FmMenu> m = menuDAO.getAllMenusByModuleIdGrouped(modulo.getId());
		subView.buildTree(m,modulo);
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "papelForm";
	}
	
	@Override
	public boolean isFullSized(){
		return true;
	}
	
	
	
	


}
