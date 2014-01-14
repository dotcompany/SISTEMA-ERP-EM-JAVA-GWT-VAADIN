package dc.controller.tabelas;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.tabelas.SefipCategoriaTrabalho;
import dc.servicos.dao.tabelas.SefipCategoriaTrabalhoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.tabelas.SefipCategoriaTrabalhoFormView;



/**
*
* @author Wesley Jr
/*
 *
*/

@Controller
@Scope("prototype")
public class SefipCategoriaTrabalhoFormController extends CRUDFormController<SefipCategoriaTrabalho> {

	SefipCategoriaTrabalhoFormView subView;
	
	@Autowired
	SefipCategoriaTrabalhoDAO sefipDAO;

	private SefipCategoriaTrabalho currentBean;
	
	@Override
	protected String getNome() {
		return "Categoria de Trabalho - SEFIP";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override  
	protected void actionSalvar() {
		
		currentBean.setNome(subView.getTxtNome().getValue());
		try{
			sefipDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);	
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}


	@Override
	protected void carregar(Serializable id) {
		currentBean = sefipDAO.find(id);
		subView.getTxtNome().setValue(currentBean.getNome());	
	}
	
	/* Callback para quando novo foi acionado. Colocar Programação customizada para essa ação aqui. Ou então deixar em branco, para comportamento padrão */
	@Override
	protected void quandoNovo() {
		
	}

	@Override
	protected void initSubView() {
		subView = new SefipCategoriaTrabalhoFormView();
	}

	/* Deve sempre atribuir a current Bean uma nova instancia do bean do formulario.*/
	@Override
	protected void criarNovoBean() {
		currentBean = new SefipCategoriaTrabalho();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		sefipDAO.deleteAllByIds(ids);
		 mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */ 
	@Override
	protected boolean validaSalvar() {
		if(subView.getTxtNome().getValue() ==  null || subView.getTxtNome().getValue().isEmpty()){
			//Utilizar adicionarErroDeValidacao() para adicionar mensagem de erro para o campo que esta sendo validado
			adicionarErroDeValidacao(subView.getTxtNome(),"Não pode ficar em Branco!");
			return false;
		}
		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "sefipForm";
	}

}
