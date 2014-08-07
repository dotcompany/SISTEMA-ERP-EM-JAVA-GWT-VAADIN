package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.ordemservico.SituacaoServico;
import dc.servicos.dao.ordemservico.SituacaoServicoDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.SituacaoServicoFormView;

@Controller
@Scope("prototype")
public class SituacaoServicoFormController extends CRUDFormController<SituacaoServico> {

	private static final long serialVersionUID = 1L;

	SituacaoServicoFormView subView;

	@Autowired
	SituacaoServicoDAO situacaoServicoDAO;

	private SituacaoServico currentBean;

	@Override
	protected String getNome() {
		return "Situação de Serviço";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			currentBean.setDescricao(subView.getTfDescricao().getValue());
			situacaoServicoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = situacaoServicoDAO.find(id);

		subView.getTfDescricao().setValue(currentBean.getDescricao());
	}

	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void initSubView() {
		subView = new SituacaoServicoFormView();
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new SituacaoServico();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		situacaoServicoDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	@Override
	protected boolean validaSalvar() {

		boolean valido = true;

		if (!Validator.validateString(subView.getTfDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTfDescricao(), "Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "tipoServicoForm";
	}

	@Override
	public SituacaoServico getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}
}
