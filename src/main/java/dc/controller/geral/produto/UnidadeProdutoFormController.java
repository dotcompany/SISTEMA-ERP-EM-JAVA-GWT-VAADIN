package dc.controller.geral.produto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.enums.SimNaoEn;
import dc.control.util.ClassUtils;
import dc.control.util.classes.UnidadeProdutoUtils;
import dc.control.validator.DotErpException;
import dc.entidade.geral.produto.UnidadeProdutoEntity;
import dc.servicos.dao.geral.produto.UnidadeProdutoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.produto.UnidadeProdutoFormView;

@Controller
@Scope("prototype")
public class UnidadeProdutoFormController extends
		CRUDFormController<UnidadeProdutoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UnidadeProdutoFormView subView;

	private UnidadeProdutoEntity currentBean;

	@Autowired
	private UnidadeProdutoDAO unidadeProdutoDAO;

	@Override
	protected String getNome() {
		return "Unidade do produto";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected boolean validaSalvar() {
		try {
			UnidadeProdutoUtils.validateRequiredFields(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			this.currentBean.setSigla(this.subView.getTfSigla().getValue());
			this.currentBean.setNome(this.subView.getTfDescricao().getValue());

			SimNaoEn en = SimNaoEn.getEnum(this.subView.getCbPodeFracionar()
					.getValue().toString());

			this.currentBean.setPodeFracionar(en);

			this.unidadeProdutoDAO.saveOrUpdate(this.currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.unidadeProdutoDAO.find(id);

			this.subView.getTfSigla().setValue(this.currentBean.getSigla());
			this.subView.getTfDescricao().setValue(this.currentBean.getNome());
			this.subView.getCbPodeFracionar().setValue(
					(SimNaoEn.valueOf(this.currentBean.getPodeFracionar()
							.name())).toString());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new UnidadeProdutoFormView(this);

			comboPodeFracionar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.currentBean = new UnidadeProdutoEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.unidadeProdutoDAO.deleteAllByIds(ids);

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
	public UnidadeProdutoEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

	/**
	 * COMBOS
	 */

	public void comboPodeFracionar() {
		for (SimNaoEn en : SimNaoEn.values()) {
			this.subView.getCbPodeFracionar().addItem(en);
		}
	}

}