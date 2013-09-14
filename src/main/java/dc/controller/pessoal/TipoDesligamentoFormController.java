package dc.controller.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.pessoal.TipoDesligamento;
import dc.servicos.dao.pessoal.TipoDesligamentoDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.pessoal.TipoDesligamentoFormView;


@Controller
@Scope("prototype")
public class TipoDesligamentoFormController extends CRUDFormController<TipoDesligamento> {

	private  TipoDesligamentoFormView subView;

	@Autowired
	private TipoDesligamentoDAO tipoDesligamentoDAO;
	
	private TipoDesligamento currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtDescricao(),
					"Não pode ficar em Branco!");
			valido = false;
		}
		return valido;
	}
	
	@Override
	protected void criarNovoBean() {
		currentBean = new TipoDesligamento();
		
	}

	@Override
	protected void initSubView() {
		subView = new TipoDesligamentoFormView();
		
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = tipoDesligamentoDAO.find(id);
		
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
	}

	@Override
	protected void actionSalvar() {

		currentBean.setDescricao(subView.getTxtDescricao().getValue());
		
		try {
			tipoDesligamentoDAO.saveOrUpdate(currentBean);


			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		
	}

	@Override
	protected void quandoNovo() {
	}

	@Override
	protected String getNome() {
		return "Tipo Desligamento";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		
		tipoDesligamentoDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}
	
	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}
	
	@Override
	public String getViewIdentifier() {
		return "tipoDesligamentoForm";
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