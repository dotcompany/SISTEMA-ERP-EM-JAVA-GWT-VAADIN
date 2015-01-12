package dc.controller.suprimentos.compra;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.util.classes.TipoPedidoUtils;
import dc.control.validator.DotErpException;
import dc.entidade.suprimentos.compra.TipoPedidoEntity;
import dc.model.business.suprimento.compra.TipoPedidoBusiness;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.suprimentos.compra.TipoPedidoFormView;

@Controller
@Scope("prototype")
public class TipoPedidoFormController extends
		CRUDFormController<TipoPedidoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TipoPedidoFormView subView;

	/**
	 * ENTITY
	 */

	private TipoPedidoEntity entity;

	/**
	 * BUSINESS
	 */

	/**
	 * DAO
	 */

	@Autowired
	private TipoPedidoBusiness<TipoPedidoEntity> business;

	/**
	 * CONSTRUTOR
	 */

	public TipoPedidoFormController() {
		// TODO Auto-generated constructor stub
	}

	public TipoPedidoBusiness<TipoPedidoEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Tipo de pedido";
	}

	@Override
	protected Component getSubView() {
		return subView;
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
	public TipoPedidoEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new TipoPedidoFormView(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {
			TipoPedidoUtils.validateRequiredFields(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			this.entity.setCodigo(this.subView.getTfCodigo().getValue());
			this.entity.setNome(this.subView.getTfNome().getValue());
			this.entity.setDescricao(this.subView.getTfDescricao().getValue());

			this.business.saveOrUpdate(this.entity);

			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.entity = this.business.find(id);

			this.subView.getTfCodigo().setValue(this.entity.getCodigo());
			this.subView.getTfNome().setValue(this.entity.getNome());
			this.subView.getTfDescricao().setValue(this.entity.getDescricao());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new TipoPedidoEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void quandoNovo() {
		try {
			this.entity = new TipoPedidoEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.business.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		// TODO Auto-generated method stub

	}

}