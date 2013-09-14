package dc.controller.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.pessoal.SituacaoColaborador;
import dc.servicos.dao.pessoal.SituacaoColaboradorDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.pessoal.SituacaoColaboradorFormView;


@Controller
@Scope("prototype")
public class SituacaoColaboradorFormController extends CRUDFormController<SituacaoColaborador> {

	private SituacaoColaboradorFormView subView;

	@Autowired
	private SituacaoColaboradorDAO situacaoColaboradorDAO;
	
	private SituacaoColaborador currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(),
					"Não pode ficar em Branco!");
			valido = false;
		}

		return valido;
	}
	
	@Override
	protected void criarNovoBean() {
		currentBean = new SituacaoColaborador();
		
	}

	@Override
	protected void initSubView() {
		subView = new SituacaoColaboradorFormView();
		
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = situacaoColaboradorDAO.find(id);
		
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
			situacaoColaboradorDAO.saveOrUpdate(currentBean);


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
		return "Situação Colaborador";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		
		situacaoColaboradorDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}
	
	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}
	
	@Override
	public String getViewIdentifier() {
		return "situacaoColaboradorForm";
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