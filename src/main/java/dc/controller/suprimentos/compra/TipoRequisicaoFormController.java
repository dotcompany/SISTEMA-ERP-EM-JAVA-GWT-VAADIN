package dc.controller.suprimentos.compra;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClasseUtil;
import dc.entidade.suprimentos.TipoRequisicao;
import dc.servicos.dao.suprimentos.TipoRequisicaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.suprimentos.compra.TipoRequisicaoFormView;

@Controller
@Scope("prototype")
public class TipoRequisicaoFormController extends
		CRUDFormController<TipoRequisicao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	TipoRequisicaoFormView subView;

	@Autowired
	TipoRequisicaoDAO tipoRequisicaoDAO;

	private TipoRequisicao currentBean;

	@Override
	protected String getNome() {
		return "Tipo Requisição";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			currentBean.setCodigo(subView.getTxtCodigo().getValue());
			currentBean.setNome(subView.getTxtNome().getValue());
			currentBean.setDescricao(subView.getTxtDescricao().getValue());
			currentBean.setIdEmpresa(1);
			tipoRequisicaoDAO.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			mensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = tipoRequisicaoDAO.find(id);
		subView.getLblId().setValue(String.valueOf(currentBean.getId()));
		subView.getTxtCodigo().setValue(currentBean.getCodigo());
		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
	}

	@Override
	protected void initSubView() {
		subView = new TipoRequisicaoFormView();
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new TipoRequisicao();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		tipoRequisicaoDAO.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	@Override
	protected boolean validaSalvar() {
		return true;
	}

	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClasseUtil.getUrl(this);
	}

	@Override
	public TipoRequisicao getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}