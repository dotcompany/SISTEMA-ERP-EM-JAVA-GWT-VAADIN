package dc.controller.tabelas;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.tabelas.SefipCodigoRecolhimento;
import dc.servicos.dao.tabelas.SefipCodigoRecolhimentoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.tabelas.SefipCodigoRecolhimentoFormView;



/**
*
* @author Wesley Jr
/*
 *
*/

@Controller
@Scope("prototype")
public class SefipCodigoRecolhimentoFormController extends CRUDFormController<SefipCodigoRecolhimento> {

	SefipCodigoRecolhimentoFormView subView;
	
	@Autowired
	SefipCodigoRecolhimentoDAO sefipCodigoRecolhimentoDAO;

	private SefipCodigoRecolhimento currentBean;
	
	@Override
	protected String getNome() {
		return "Sefip Codigo Recolhimento";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override  
	protected void actionSalvar() {
		
		currentBean.setDescricao(subView.getTxtDescricao().getValue());
		currentBean.setAplicacao(subView.getTxtAplicacao().getValue());
		try{
			sefipCodigoRecolhimentoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);	
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}


	@Override
	protected void carregar(Serializable id) {
		currentBean = sefipCodigoRecolhimentoDAO.find(id);
		subView.getTxtDescricao().setValue(currentBean.getDescricao());	
		subView.getTxtAplicacao().setValue(currentBean.getAplicacao());
	}
	
	/* Callback para quando novo foi acionado. Colocar Programação customizada para essa ação aqui. Ou então deixar em branco, para comportamento padrão */
	@Override
	protected void quandoNovo() {
		
	}

	@Override
	protected void initSubView() {
		subView = new SefipCodigoRecolhimentoFormView();
	}

	/* Deve sempre atribuir a current Bean uma nova instancia do bean do formulario.*/
	@Override
	protected void criarNovoBean() {
		currentBean = new SefipCodigoRecolhimento();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		sefipCodigoRecolhimentoDAO.deleteAllByIds(ids);
		 mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */ 
	@Override
	protected boolean validaSalvar() {
		if(subView.getTxtDescricao().getValue() ==  null || subView.getTxtDescricao().getValue().isEmpty()){
			//Utilizar adicionarErroDeValidacao() para adicionar mensagem de erro para o campo que esta sendo validado
			adicionarErroDeValidacao(subView.getTxtDescricao(),"não pode ficar em branco");
			return false;
		}
		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "sefipCodigoRecolhimentoForm";
	}

}
