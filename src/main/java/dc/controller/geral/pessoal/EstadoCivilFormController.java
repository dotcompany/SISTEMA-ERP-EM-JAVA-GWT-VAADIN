package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.validator.DotErpException;
import dc.control.validator.classe.EstadoCivilValidator;
import dc.entidade.geral.pessoal.EstadoCivilEntity;
import dc.servicos.dao.geral.pessoal.EstadoCivilDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.pessoal.EstadoCivilFormView;

@Controller
@Scope("prototype")
public class EstadoCivilFormController extends
		CRUDFormController<EstadoCivilEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EstadoCivilFormView subView;

	private EstadoCivilEntity currentBean;

	@Autowired
	private EstadoCivilDAO estadoCivilDAO;

	@Override
	protected boolean validaSalvar() {
		try {
			EstadoCivilValidator.validaSalvar(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.currentBean = new EstadoCivilEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new EstadoCivilFormView(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.estadoCivilDAO.find(id);

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
			this.currentBean.setNome(this.subView.getTfNome().getValue());
			this.currentBean.setDescricao(this.subView.getTfDescricao()
					.getValue());

			this.estadoCivilDAO.saveOrUpdate(this.currentBean);

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
		return "Estado civil";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.estadoCivilDAO.deleteAllByIds(ids);

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
	public EstadoCivilEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}