package dc.controller.geral.produto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.enums.SimNaoEnum;
import dc.control.util.ClassUtils;
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

	@Autowired
	private UnidadeProdutoDAO unidadeProdutoDAO;

	private UnidadeProdutoEntity currentBean;

	@Override
	protected String getNome() {
		return "Unidade do produto";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			this.currentBean.setSigla(this.subView.getTfSigla().getValue());
			this.currentBean.setNome(this.subView.getTfDescricao().getValue());

			SimNaoEnum en = (this.subView.getCbPodeFracionar().getValue() == "SIM" ? SimNaoEnum.S
					: SimNaoEnum.N);

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
					(SimNaoEnum.valueOf(this.currentBean.getPodeFracionar()
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
		this.subView = new UnidadeProdutoFormView();

		this.subView.InitCbs(getPodeFracionarEn());
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		this.currentBean = new UnidadeProdutoEntity();
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
	protected boolean validaSalvar() {
		if (this.subView.getTfSigla().getValue() == null
				|| this.subView.getTfSigla().getValue().isEmpty()) {
			adicionarErroDeValidacao(this.subView.getTfSigla(),
					"Não pode ficar em branco");

			return false;
		}

		if (this.subView.getTfDescricao().getValue() == null
				|| this.subView.getTfDescricao().getValue().isEmpty()) {
			adicionarErroDeValidacao(this.subView.getTfDescricao(),
					"Não pode ficar em branco");

			return false;
		}

		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	public List<String> getPodeFracionarEn() {
		try {
			List<String> siLista = new ArrayList<String>();

			for (SimNaoEnum en : SimNaoEnum.values()) {
				siLista.add(en.ordinal(), en.toString());
			}

			return siLista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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

}