package dc.controller.geral.diverso;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.diverso.AlmoxarifadoEntity;
import dc.model.business.geral.diverso.AlmoxarifadoBusiness;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.diverso.AlmoxarifadoFormView;

@Controller
@Scope("prototype")
public class AlmoxarifadoFormController extends
		CRUDFormController<AlmoxarifadoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AlmoxarifadoFormView subView;

	/**
	 * ENTITY
	 */

	private AlmoxarifadoEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private AlmoxarifadoBusiness<AlmoxarifadoEntity> business;

	/**
	 * DAO
	 */

	/**
	 * CONSTRUTOR
	 */

	public AlmoxarifadoFormController() {
		// TODO Auto-generated constructor stub
	}

	public AlmoxarifadoBusiness<AlmoxarifadoEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Almoxarifado";
	}

	@Override
	protected Component getSubView() {
		return subView;
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
	public AlmoxarifadoEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new AlmoxarifadoFormView(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTfNome().getValue())) {
			adicionarErroDeValidacao(subView.getTfNome(),
					"Não pode ficar em branco");

			valido = false;
		}

		return valido;
	}

	@Override
	protected void actionSalvar() {
		try {
			this.entity.setNome(this.subView.getTfNome().getValue());

			this.business.saveOrUpdate(this.entity);

			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.entity = this.business.find(id);

			this.subView.getTfNome().setValue(this.entity.getNome());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new AlmoxarifadoEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void quandoNovo() {
		try {
			this.entity = new AlmoxarifadoEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.business.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

}