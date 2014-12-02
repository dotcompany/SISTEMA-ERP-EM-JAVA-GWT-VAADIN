package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.SituacaoColaboradorEntity;
import dc.servicos.dao.geral.pessoal.SituacaoColaboradorDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.pessoal.SituacaoColaboradorFormView;

@Controller
@Scope("prototype")
public class SituacaoColaboradorFormController extends
		CRUDFormController<SituacaoColaboradorEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SituacaoColaboradorFormView subView;

	@Autowired
	private SituacaoColaboradorDAO situacaoColaboradorDAO;

	private SituacaoColaboradorEntity currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		String nome = subView.getTfNome().getValue();

		if (!Validator.validateString(nome)) {
			adicionarErroDeValidacao(this.subView.getTfNome(),
					"Não pode ficar em branco!");

			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new SituacaoColaboradorEntity();
	}

	@Override
	protected void initSubView() {
		subView = new SituacaoColaboradorFormView(this);
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.situacaoColaboradorDAO.find(id);

			this.subView.getTfCodigo().setValue(this.currentBean.getCodigo());
			this.subView.getTfNome().setValue(this.currentBean.getNome());
			this.subView.getTfDescricao().setValue(
					this.currentBean.getDescricao());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			this.currentBean.setCodigo(this.subView.getTfCodigo().getValue());
			this.currentBean.setNome(this.subView.getTfNome().getValue());
			this.currentBean.setDescricao(this.subView.getTfDescricao()
					.getValue());

			this.situacaoColaboradorDAO.saveOrUpdate(this.currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void quandoNovo() {

	}

	@Override
	protected String getNome() {
		return "Situação do colaborador";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.situacaoColaboradorDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	public SituacaoColaboradorEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}