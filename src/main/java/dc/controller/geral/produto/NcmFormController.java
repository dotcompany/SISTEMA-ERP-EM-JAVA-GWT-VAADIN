package dc.controller.geral.produto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.produto.NcmEntity;
import dc.servicos.dao.geral.produto.NcmDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.produto.NcmFormView;

@Controller
@Scope("prototype")
public class NcmFormController extends CRUDFormController<NcmEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private NcmFormView subView;

	@Autowired
	private NcmDAO ncmDAO;

	private NcmEntity currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtCodigo().getValue())) {
			adicionarErroDeValidacao(subView.getTxtCodigo(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtDescricao(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtObservacao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtObservacao(),
					"Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		this.currentBean = new NcmEntity();
	}

	@Override
	protected void initSubView() {
		this.subView = new NcmFormView();
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.ncmDAO.find(id);

			this.subView.getTxtCodigo().setValue(this.currentBean.getCodigo());
			this.subView.getTxtDescricao().setValue(this.currentBean.getNome());
			this.subView.getTxtObservacao().setValue(
					this.currentBean.getObservacao());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			this.currentBean.setCodigo(this.subView.getTxtCodigo().getValue());
			this.currentBean.setNome(this.subView.getTxtDescricao().getValue());
			this.currentBean.setObservacao(this.subView.getTxtObservacao()
					.getValue());

			this.ncmDAO.saveOrUpdate(this.currentBean);

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
		return "NCM";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.ncmDAO.deleteAllByIds(ids);

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
	public NcmEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}