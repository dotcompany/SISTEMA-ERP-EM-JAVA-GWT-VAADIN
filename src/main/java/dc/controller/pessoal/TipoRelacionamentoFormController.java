package dc.controller.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.pessoal.TipoRelacionamento;
import dc.servicos.dao.pessoal.TipoRelacionamentoDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.pessoal.TipoRelacionamentoFormView;


@Controller
@Scope("prototype")
public class TipoRelacionamentoFormController extends CRUDFormController<TipoRelacionamento> {

	private  TipoRelacionamentoFormView subView;

	@Autowired
	private TipoRelacionamentoDAO tipoRelacionamentoDAO;
	
	private TipoRelacionamento currentBean;

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
		currentBean = new TipoRelacionamento();
		
	}

	@Override
	protected void initSubView() {
		subView = new TipoRelacionamentoFormView();
		
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = tipoRelacionamentoDAO.find(id);
		
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
			tipoRelacionamentoDAO.saveOrUpdate(currentBean);


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
		return "Tipo Relacionamento";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		
		tipoRelacionamentoDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}
	
	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}
	
	@Override
	public String getViewIdentifier() {
		return "tipoRelacionamentoForm";
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