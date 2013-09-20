package dc.controller.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.framework.Empresa;
import dc.entidade.geral.Pessoa;

import dc.servicos.dao.pessoal.PessoaDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.pessoal.PessoaFormView;
import dc.visao.pessoal.TipoPessoa;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class PessoaFormController extends CRUDFormController<Pessoa> {

	PessoaFormView subView;

	@Autowired
	PessoaDAO dao;
	
	Pessoa currentBean;

	@Override
	public String getViewIdentifier() {
		return "PessoaForm";
	}

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;
		
		
		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new Pessoa();
	}

	@Override
	protected void initSubView() {
		subView = new PessoaFormView(this);

	}

	@Override
	protected void carregar(Serializable id) {
		// TODO Auto-generated method stub
		currentBean = dao.find((Integer) id);
	}
	
	public Empresa empresaAtual(){
		return SecuritySessionProvider.getUsuario().getConta().getEmpresa();
	}

	@Override
	protected void actionSalvar() {
		try{
			TipoPessoa tpPessoa = (TipoPessoa)subView.getCmbTipoPessoa().getValue();
			currentBean.setNome(subView.getTxtNome().getValue());
			currentBean.setTipo(tpPessoa.getCodigo());
			currentBean.setEmail(subView.getTxtEmail().getValue());
			currentBean.setSite(subView.getTxtSite().getValue());
			dao.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		}catch(Exception e){
			mensagemErro("Erro!!");
			e.printStackTrace();
		}
		
	}


	@Override
	protected void quandoNovo() {
		try{
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Pessoa";
	}

	@Override
	protected void remover(List<Serializable> ids) {
	
		dao.deleteAllByIds(ids);

	}
	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		System.out.println("");

	}
	
	@Override
	public boolean isFullSized(){
		return true;
	}
	

	
	

}

