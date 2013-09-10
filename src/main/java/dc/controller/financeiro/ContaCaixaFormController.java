package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.financeiro.ContaCaixa;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.servicos.dao.financeiro.AgenciaBancoDAO;
import dc.servicos.dao.financeiro.ContaCaixaDAO;
import dc.servicos.util.Validator;
import dc.visao.financeiro.ContaCaixaFormView;
import dc.visao.framework.geral.CRUDFormController;


@Controller
@Scope("prototype")
public class ContaCaixaFormController extends CRUDFormController<ContaCaixa> {

	private  ContaCaixaFormView subView;

	@Autowired
	private ContaCaixaDAO contaCaixaDAO;
	
	@Autowired
	private ContabilContaDAO contabilDAO;
	
	@Autowired
	private AgenciaBancoDAO agenciaDAO;

	private ContaCaixa currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtCodigo().getValue())) {
			adicionarErroDeValidacao(subView.getTxtCodigo(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(),
					"Não pode ficar em branco");
			valido = false;
		}
		
		if (!Validator.validateString(subView.getTxtDigito().getValue())) {
			adicionarErroDeValidacao(subView.getTxtDigito(),
					"Não pode ficar em branco");
			valido = false;
		}
		
		if (!Validator.validateString(subView.getTxtDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtDescricao(),
					"Não pode ficar em branco");
			valido = false;
		}
		
		return valido;
	}
	
	@Override
	protected void criarNovoBean() {
		currentBean = new ContaCaixa();
		
	}

	@Override
	protected void initSubView() {
		subView = new ContaCaixaFormView();
		
		subView.InitCbs(contabilDAO.listaTodos(), agenciaDAO.listaTodos());
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = contaCaixaDAO.find(id);
		
		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
		subView.setCbTipo(currentBean.getTipo().toString());
		
		
		subView.getCmbContabilConta().setValue(currentBean.getContabilConta());
		subView.getCmbAgenciaBanco().setValue(currentBean.getAgenciaBanco());
	}

	@Override
	protected void actionSalvar() {

		currentBean.setNome(subView.getTxtNome().getValue());
		currentBean.setDescricao(subView.getTxtDescricao().getValue());
		
		try {
			contaCaixaDAO.saveOrUpdate(currentBean);

			mensagemSalvoOK();
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		
	}

	@Override
	protected void quandoNovo() {
	}

	@Override
	protected String getNome() {
		return "Conta Caixa";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		
		contaCaixaDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}
	
	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}
	
	@Override
	public String getViewIdentifier() {
		return "contaCaixaForm";
	}
	
	@Override
	public boolean isFullSized(){
	   return true;
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

}
