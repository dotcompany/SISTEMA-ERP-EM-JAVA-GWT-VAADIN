package dc.controller.suprimentos.compra;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClasseUtil;
import dc.entidade.suprimentos.compra.TipoPedidoEntity;
import dc.servicos.dao.suprimentos.compra.TipoPedidoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.suprimentos.compra.TipoPedidoFormView;

@Controller
@Scope("prototype")
public class TipoPedidoFormController extends CRUDFormController<TipoPedidoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TipoPedidoFormView subView;

	@Autowired
	private TipoPedidoDAO tipoPedidoDAO;

	private TipoPedidoEntity currentBean;

	@Override
	protected String getNome() {
		return "Tipo Pedido Compra";
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
			tipoPedidoDAO.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			mensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = tipoPedidoDAO.find(id);
		subView.getLblId().setValue(String.valueOf(currentBean.getId()));
		subView.getTxtCodigo().setValue(currentBean.getCodigo());
		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
	}

	@Override
	protected void initSubView() {
		subView = new TipoPedidoFormView();
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new TipoPedidoEntity();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		tipoPedidoDAO.deleteAllByIds(ids);

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
		// TODO Auto-generated method stub

	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClasseUtil.getUrl(this);
	}

	@Override
	public TipoPedidoEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}