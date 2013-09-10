package dc.controller.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.pessoal.TipoAdmissao;
import dc.servicos.dao.pessoal.TipoAdmissaoDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.pessoal.TipoAdmissaoFormView;


@Controller
@Scope("prototype")
public class TipoAdmissaoFormController extends CRUDFormController<TipoAdmissao> {

	private  TipoAdmissaoFormView subView;

	@Autowired
	private TipoAdmissaoDAO tipoAdmissaoDAO;
	
	private TipoAdmissao currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(),
					"não pode ficar em branco");
			valido = false;
		}

		return valido;
	}
	
	@Override
	protected void criarNovoBean() {
		currentBean = new TipoAdmissao();
		
	}

	@Override
	protected void initSubView() {
		subView = new TipoAdmissaoFormView();
		
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = tipoAdmissaoDAO.find(id);
		
		subView.getTxtCodigo().setValue(currentBean.getCodigo());
		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
	}

	@Override
	protected void actionSalvar() {

		currentBean.setCodigo(subView.getTxtCodigo().getValue());
		currentBean.setNome(subView.getTxtNome().getValue());
		currentBean.setDescricao(subView.getTxtDescricao().getValue());
		
		try {
			tipoAdmissaoDAO.saveOrUpdate(currentBean);


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
		return "Tipo Admissão";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		
		tipoAdmissaoDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}
	
	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}
	
	@Override
	public String getViewIdentifier() {
		return "tipoAdmissaoForm";
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