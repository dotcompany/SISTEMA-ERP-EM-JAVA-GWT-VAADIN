package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.financeiro.ParcelaPagar;
import dc.servicos.dao.financeiro.ParcelaPagamentoDAO;
import dc.servicos.dao.financeiro.ParcelaPagarDAO;
import dc.visao.financeiro.ParcelaPagamentoFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class ParcelaPagamentoFormController extends CRUDFormController<ParcelaPagar> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ParcelaPagamentoFormView subView;

	@Autowired
	private ParcelaPagarDAO parcelaPagarDAO;
	@Autowired
	private ParcelaPagamentoDAO parcelaPagamentoDAO;

	private ParcelaPagar currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;
		
		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new ParcelaPagar();
		
	}


	@Override
	protected void initSubView() {
		subView = new ParcelaPagamentoFormView(this);
	}

	@Override
	protected void carregar(Serializable id) {

		currentBean = parcelaPagarDAO.find(id);
		subView.preencheForm(currentBean);
	}

	@Override
	protected void actionSalvar() {
		subView.preencheBean(currentBean);
		try {
			parcelaPagarDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void quandoNovo() {

	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Ponto Abono";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		parcelaPagarDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();

	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "parcelaPagarFormController";
	}

	
	@Override
	protected boolean isFullSized() {
		return true;
	}

}
