package dc.controller.geral.produto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.geral.produto.NcmEntity;
import dc.servicos.dao.geral.produto.NCMDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.produto.NCMFormView;

@Controller
@Scope("prototype")
public class NCMFormController extends CRUDFormController<NcmEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private NCMFormView subView;

	@Autowired
	private NCMDAO ncmDAO;

	private NcmEntity currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtCodigo().getValue())) {
			adicionarErroDeValidacao(subView.getTxtCodigo(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtDescricao(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtObservacao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtObservacao(),
					"Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new NcmEntity();
	}

	@Override
	protected void initSubView() {
		subView = new NCMFormView();
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = ncmDAO.find(id);

		subView.getTxtCodigo().setValue(currentBean.getCodigo());
		subView.getTxtDescricao().setValue(currentBean.getNome());
		subView.getTxtObservacao().setValue(currentBean.getObservacao());
	}

	@Override
	protected void actionSalvar() {
		currentBean.setCodigo(subView.getTxtCodigo().getValue());
		currentBean.setNome(subView.getTxtDescricao().getValue());
		currentBean.setObservacao(subView.getTxtObservacao().getValue());

		try {
			ncmDAO.saveOrUpdate(currentBean);

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
		return "NCM";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		ncmDAO.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "ncmForm";
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	public NcmEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}