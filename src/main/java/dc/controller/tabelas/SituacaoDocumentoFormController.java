package dc.controller.tabelas;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.tabelas.SituacaoDocumento;
import dc.servicos.dao.tabelas.SituacaoDocumentoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.tabelas.SituacaoDocumentoFormView;



/**
*
* @author Wesley Jr
/*
 *
*/

@Controller
@Scope("prototype")
public class SituacaoDocumentoFormController extends CRUDFormController<SituacaoDocumento> {

	SituacaoDocumentoFormView subView;
	
	@Autowired
	SituacaoDocumentoDAO situacaoDocumentoDAO;

	private SituacaoDocumento currentBean;
	
	@Override
	protected String getNome() {
		return "Situação Documento";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override  
	protected void actionSalvar() {
		
		currentBean.setDescricao(subView.getTxtDescricao().getValue());
		currentBean.setCodigo(subView.getTxtCodigo().getValue());
		try{
			situacaoDocumentoDAO.saveOrUpdate(currentBean);
			mensagemSalvoOK();	
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}


	@Override
	protected void carregar(Serializable id) {
		currentBean = situacaoDocumentoDAO.find(id);
		subView.getTxtDescricao().setValue(currentBean.getDescricao());	
		subView.getTxtCodigo().setValue(currentBean.getCodigo());
	}
	
	/* Callback para quando novo foi acionado. Colocar Programação customizada para essa ação aqui. Ou então deixar em branco, para comportamento padrão */
	@Override
	protected void quandoNovo() {
		
	}

	@Override
	protected void initSubView() {
		subView = new SituacaoDocumentoFormView();
	}

	/* Deve sempre atribuir a current Bean uma nova instancia do bean do formulario.*/
	@Override
	protected void criarNovoBean() {
		currentBean = new SituacaoDocumento();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		 situacaoDocumentoDAO.deleteAllByIds(ids);
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
		return "situacaoDocumentoForm";
	}

}
