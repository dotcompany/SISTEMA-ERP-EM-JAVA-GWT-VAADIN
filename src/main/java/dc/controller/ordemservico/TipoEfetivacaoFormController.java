package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.ordemservico.TipoEfetivacao;
import dc.servicos.dao.ordemservico.TipoEfetivacaoDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.TipoEfetivacaoFormView;

/** @author Paulo Sérgio */

@Controller
@Scope("prototype")
public class TipoEfetivacaoFormController extends CRUDFormController<TipoEfetivacao> {

	private static final long serialVersionUID = 1L;

	TipoEfetivacaoFormView subView;

	@Autowired
	TipoEfetivacaoDAO tipoEfetivacaoDAO;

	private TipoEfetivacao currentBean;

	@Override
	protected String getNome() {
		return "TipoEfetivacao";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		currentBean.setDescricao(subView.getTxtDescricao().getValue());
		try {
			tipoEfetivacaoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = tipoEfetivacaoDAO.find(id);
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
	}

	/*
	 * Callback para quando novo foi acionado. Colocar ProgramaÃ§Ã£o customizada
	 * para essa aÃ§Ã£o aqui. Ou entÃ£o deixar em branco, para comportamento
	 * padrÃ£o
	 */
	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void initSubView() {
		subView = new TipoEfetivacaoFormView();
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new TipoEfetivacao();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		tipoEfetivacaoDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {

		boolean valido = true;

		if (!Validator.validateString(subView.getTxtDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtDescricao(), "Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "tipoEfetivacaoForm";
	}

	@Override
	public TipoEfetivacao getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}
}
