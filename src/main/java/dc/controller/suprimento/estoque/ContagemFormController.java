package dc.controller.suprimento.estoque;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.util.classes.ContagemCabecalhoUtils;
import dc.control.validator.DotErpException;
import dc.entidade.suprimentos.estoque.ContagemCabecalhoEntity;
import dc.model.business.suprimento.estoque.ContagemCabecalhoBusiness;
import dc.servicos.dao.geral.produto.ProdutoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.suprimento.estoque.ContagemFormView;

@Controller
@Scope("prototype")
public class ContagemFormController extends
		CRUDFormController<ContagemCabecalhoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ContagemFormView subView;

	/**
	 * ENTITY
	 */

	private ContagemCabecalhoEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private ContagemCabecalhoBusiness<ContagemCabecalhoEntity> business;

	/**
	 * DAO
	 */

	@Autowired
	private ProdutoDAO produtoDAO;

	/**
	 * CONSTRUTOR
	 */

	public ContagemFormController() {
		// TODO Auto-generated constructor stub
	}

	public ContagemCabecalhoBusiness<ContagemCabecalhoEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Contagem";
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
	public ContagemCabecalhoEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new ContagemFormView(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {
			ContagemCabecalhoUtils.validateRequiredFields(null);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	/*
	 * @Override protected boolean validaSalvar() { boolean valido = true;
	 * 
	 * if (!Validator.validateObject(subView.getDataContagem().getValue())) {
	 * adicionarErroDeValidacao(subView.getDataContagem(),
	 * "Não pode ficar em branco"); valido = false; }
	 * 
	 * return valido; }
	 */

	@Override
	protected void actionSalvar() {
		try {
			this.entity.setDataContagem(this.subView.getDataContagem()
					.getValue());

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

			this.subView.getDataContagem().setValue(
					this.entity.getDataContagem());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new ContagemCabecalhoEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void quandoNovo() {
		try {
			this.entity = new ContagemCabecalhoEntity();
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
		try {

		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

}