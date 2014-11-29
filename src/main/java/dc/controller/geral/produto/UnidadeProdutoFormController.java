package dc.controller.geral.produto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.produto.UnidadeProdutoEntity;
import dc.entidade.type.produto.PodeFracionarType;
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

	UnidadeProdutoFormView subView;

	@Autowired
	UnidadeProdutoDAO unidadeProdutoDAO;

	private UnidadeProdutoEntity currentBean;

	@Override
	protected String getNome() {
		return "Unidade Produto";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		currentBean.setSigla(subView.getTxtSigla().getValue());
		currentBean.setNome(subView.getTxtDescricao().getValue());

		try {
			unidadeProdutoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = unidadeProdutoDAO.find(id);
		subView.getTxtSigla().setValue(currentBean.getSigla());
		subView.getTxtDescricao().setValue(currentBean.getNome());
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
		subView = new UnidadeProdutoFormView();

		this.subView.InitCbs(getUnidadeProdutoPodeFracionarType());
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new UnidadeProdutoEntity();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		unidadeProdutoDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	@Override
	protected boolean validaSalvar() {
		if (subView.getTxtSigla().getValue() == null
				|| subView.getTxtSigla().getValue().isEmpty()) {
			adicionarErroDeValidacao(subView.getTxtSigla(),
					"Não pode ficar em branco");
			return false;
		}

		if (subView.getTxtDescricao().getValue() == null
				|| subView.getTxtDescricao().getValue().isEmpty()) {
			adicionarErroDeValidacao(subView.getTxtDescricao(),
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

	public List<String> getUnidadeProdutoPodeFracionarType() {

		try {
			List<String> siLista = new ArrayList<String>();

			for (PodeFracionarType fd : PodeFracionarType.values()) {
				siLista.add(fd.ordinal(), fd.toString());
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