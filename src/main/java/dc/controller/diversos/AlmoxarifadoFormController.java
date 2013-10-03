package dc.controller.diversos;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.diversos.Almoxarifado;
import dc.servicos.dao.diversos.AlmoxarifadoDAO;
import dc.servicos.util.Validator;
import dc.visao.diversos.AlmoxarifadoFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class AlmoxarifadoFormController extends	CRUDFormController<Almoxarifado> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AlmoxarifadoFormView subView;

	@Autowired
	private AlmoxarifadoDAO almoxarifadoDAO;

	private Almoxarifado currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(),
					"Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new Almoxarifado();
	}

	@Override
	protected void initSubView() {
		subView = new AlmoxarifadoFormView();
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = almoxarifadoDAO.find(id);

		subView.getTxtNome().setValue(currentBean.getNome());
	}

	@Override
	protected void actionSalvar() {
		currentBean.setNome(subView.getTxtNome().getValue());

		try {
			almoxarifadoDAO.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void quandoNovo() {

	}

	@Override
	protected String getNome() {
		return "Almoxarifado";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		almoxarifadoDAO.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "almoxarifadoForm";
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

}