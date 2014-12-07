package dc.controller.geral.diverso;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.util.NumberUtils;
import dc.control.validator.DotErpException;
import dc.control.validator.classe.PaisValidator;
import dc.entidade.geral.diverso.PaisEntity;
import dc.servicos.dao.geral.diverso.PaisDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.diverso.PaisFormView;

@Controller
@Scope("prototype")
public class PaisFormController extends CRUDFormController<PaisEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PaisFormView subView;

	/** ENTITIES */

	private PaisEntity currentBean;

	/** DAO'S */

	@Autowired
	private PaisDAO paisDAO;

	/** CONSTRUTOR */

	public PaisFormController() {

	}

	@Override
	protected String getNome() {
		return "Pais";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected boolean validaSalvar() {
		try {
			PaisValidator.validaSalvar(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			this.currentBean.setNomePtbr(this.subView.getTfNome().getValue());
			this.currentBean.setNomeIngles(this.subView.getTfNomeIngles()
					.getValue());
			this.currentBean.setSigla2(this.subView.getTfSigla2().getValue());
			this.currentBean.setSigla3(this.subView.getTfSigla3().getValue());

			String codigo = this.subView.getTfCodigo().getValue();

			if (NumberUtils.isNumber(codigo)) {
				this.currentBean.setCodigo(NumberUtils.toInt(codigo));
			} else {
				this.currentBean.setCodigo(null);
			}

			this.paisDAO.saveOrUpdate(this.currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.paisDAO.find(id);

			this.subView.getTfNome().setValue(this.currentBean.getNomePtbr());
			this.subView.getTfNomeIngles().setValue(
					this.currentBean.getNomeIngles());
			this.subView.getTfSigla2().setValue(this.currentBean.getSigla2());
			this.subView.getTfSigla3().setValue(this.currentBean.getSigla3());
			this.subView.getTfCodigo().setValue(
					this.currentBean.getCodigo().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {
		try {
			this.currentBean = new PaisEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new PaisFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.currentBean = new PaisEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.paisDAO.deleteAllByIds(ids);

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
		return ClassUtils.getUrl(this);
	}

	/** COMBOS */

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	@Override
	public PaisEntity getModelBean() {
		return currentBean;
	}

}