package dc.controller.geral.outro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.controller.geral.diverso.UfListController;
import dc.entidade.geral.diverso.UfEntity;
import dc.entidade.geral.outro.ConvenioEntity;
import dc.model.business.geral.outro.ConvenioBusiness;
import dc.servicos.dao.geral.UfDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.outro.ConvenioFormView;

@Controller
@Scope("prototype")
public class ConvenioFormController extends CRUDFormController<ConvenioEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ConvenioFormView subView;

	/**
	 * ENTITY
	 */

	private ConvenioEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private ConvenioBusiness<ConvenioEntity> business;

	/**
	 * DAO
	 */

	@Autowired
	private UfDAO ufDAO;

	/**
	 * CONSTRUTOR
	 */

	public ConvenioFormController() {
		// TODO Auto-generated constructor stub
	}

	public ConvenioBusiness<ConvenioEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Convênio";
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
	public ConvenioEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new ConvenioFormView(this);

			DefaultManyToOneComboModel<UfEntity> model = new DefaultManyToOneComboModel<UfEntity>(
					UfListController.class, this.ufDAO,
					super.getMainController());

			this.subView.getMocUf().setModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTfNome().getValue())) {
			adicionarErroDeValidacao(subView.getTfNome(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTfLogradouro().getValue())) {
			adicionarErroDeValidacao(subView.getTfLogradouro(),
					"Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void actionSalvar() {
		try {
			this.entity
					.setLogradouro(this.subView.getTfLogradouro().getValue());
			this.entity.setNome(this.subView.getTfNome().getValue());
			this.entity.setCnpj(this.subView.getTfCnpj().getValue());
			this.entity.setDataVencimento(this.subView.getPdfDataVencimento()
					.getValue());
			this.entity.setNumero(this.subView.getTfNumero().getValue());
			this.entity.setBairro(this.subView.getTfBairro().getValue());
			this.entity.setContato(this.subView.getTfContato().getValue());
			this.entity.setTelefone(this.subView.getTfTelefone().getValue());
			this.entity.setDescricao(this.subView.getTfDescricao().getValue());
			this.entity.setCep(this.subView.getTfCep().getValue());

			this.business.saveOrUpdate(entity);

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

			this.subView.getTfNome().setValue(this.entity.getNome());
			this.subView.getTfCnpj().setValue(this.entity.getCnpj());
			this.subView.getTfLogradouro()
					.setValue(this.entity.getLogradouro());
			this.subView.getTfNumero().setValue(this.entity.getNumero());
			this.subView.getTfBairro().setValue(this.entity.getBairro());
			this.subView.getTfContato().setValue(this.entity.getContato());
			this.subView.getTfTelefone().setValue(this.entity.getTelefone());
			this.subView.getTfDescricao().setValue(this.entity.getDescricao());
			this.subView.getTfCep().setValue(this.entity.getCep());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new ConvenioEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void quandoNovo() {
		try {
			this.entity = new ConvenioEntity();
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

	/**
	 * COMBOS
	 */

}