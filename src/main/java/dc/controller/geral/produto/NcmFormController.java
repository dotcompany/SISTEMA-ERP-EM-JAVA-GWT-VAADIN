package dc.controller.geral.produto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.validator.DotErpException;
import dc.control.validator.classe.NcmValidator;
import dc.entidade.geral.produto.NcmEntity;
import dc.servicos.dao.geral.produto.NcmDAO;
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

	private NcmEntity currentBean;

	@Autowired
	private NcmDAO ncmDAO;

	@Override
	protected boolean validaSalvar() {
		try {
			NcmValidator.validaSalvar(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.currentBean = new NcmEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new NcmFormView(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.ncmDAO.find(id);

			this.subView.getTfCodigo().setValue(this.currentBean.getCodigo());
			this.subView.getTfDescricao().setValue(this.currentBean.getNome());
			this.subView.getTfObservacao().setValue(
					this.currentBean.getObservacao());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			this.currentBean.setCodigo(this.subView.getTfCodigo().getValue());
			this.currentBean.setNome(this.subView.getTfDescricao().getValue());
			this.currentBean.setObservacao(this.subView.getTfObservacao()
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