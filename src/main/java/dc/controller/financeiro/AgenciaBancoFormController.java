package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.validator.DotErpException;
import dc.control.validator.classe.AgenciaBancoValidator;
import dc.entidade.financeiro.AgenciaBancoEntity;
import dc.entidade.financeiro.BancoEntity;
import dc.entidade.geral.UfEntity;
import dc.servicos.dao.financeiro.AgenciaBancoDAO;
import dc.servicos.dao.financeiro.BancoDAO;
import dc.servicos.dao.geral.UfDAO;
import dc.servicos.util.Validator;
import dc.visao.financeiro.AgenciaBancoFormView;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class AgenciaBancoFormController extends
		CRUDFormController<AgenciaBancoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AgenciaBancoFormView subView;

	private AgenciaBancoEntity currentBean;

	@Autowired
	private AgenciaBancoDAO agenciaDAO;

	@Autowired
	private BancoDAO bancoDAO;

	@Autowired
	private UfDAO ufDAO;

	@Override
	protected String getNome() {
		return "Agência bancária";
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
	public AgenciaBancoEntity getModelBean() {
		return currentBean;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new AgenciaBancoFormView(this);

			DefaultManyToOneComboModel<BancoEntity> model = new DefaultManyToOneComboModel<BancoEntity>(
					BancoListController.class, this.bancoDAO,
					super.getMainController()) {

				@Override
				public String getCaptionProperty() {
					return "nome";
				}

			};

			this.subView.getMocBanco().setModel(model);

			/*
			 * DefaultManyToOneComboModel<UF> modelUf = new
			 * DefaultManyToOneComboModel<UF>( UFListController.class,
			 * this.ufDAO, super.getMainController()) {
			 * 
			 * @Override public String getCaptionProperty() { return "nome"; }
			 * };
			 * 
			 * this.subView.getCmbUF().setModel(modelUf);
			 */

			// subView.InitCbs(bancoDAO.listaTodos(), ufDAO.listaTodos());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {
			AgenciaBancoValidator.validaSalvar(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.currentBean = new AgenciaBancoEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void actionSalvar() {
		try {
			this.currentBean.setNome(this.subView.getTfNome().getValue());
			this.currentBean.setLogradouro(this.subView.getTfLogradouro()
					.getValue());
			this.currentBean.setBairro(this.subView.getTfBairro().getValue());
			this.currentBean.setCep(this.subView.getTfCep().getValue());
			this.currentBean.setContato(this.subView.getTfContato().getValue());
			this.currentBean.setGerente(this.subView.getTfGerente().getValue());
			this.currentBean.setTelefone(this.subView.getTfTelefone()
					.getValue());
			this.currentBean.setNumero(this.subView.getTfNumero().getValue());

			this.agenciaDAO.saveOrUpdate(this.currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.agenciaDAO.find(id);

			this.subView.getTfNome().setValue(this.currentBean.getNome());
			this.subView.getTfLogradouro().setValue(
					this.currentBean.getLogradouro());
			this.subView.getTfBairro().setValue(this.currentBean.getBairro());
			this.subView.getTfBairro().setValue(this.currentBean.getBairro());
			this.subView.getTfMunicipio().setValue(
					this.currentBean.getMunicipio());
			this.subView.getTfCep().setValue(this.currentBean.getCep());
			this.subView.getTfContato().setValue(this.currentBean.getContato());
			this.subView.getTfGerente().setValue(this.currentBean.getGerente());
			this.subView.getTfTelefone().setValue(
					this.currentBean.getTelefone());
			this.subView.getTfNumero().setValue(this.currentBean.getNumero());

			carregarCombos();

			if (Validator.validateObject(this.currentBean.getUf())) {
				this.subView.getCbUf().setValue(this.currentBean.getUf());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.agenciaDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	void carregarCombos() {
		carregarUFs();
	}

	public List<UfEntity> listarUfs() {
		return ufDAO.listaTodos();
	}

	public BeanItemContainer<String> carregarUFs() {
		BeanItemContainer<String> container = new BeanItemContainer<>(
				String.class);
		List<UfEntity> ufs = listarUfs();

		for (UfEntity u : ufs) {
			container.addBean(u.getSigla());
		}

		return container;
	}

}