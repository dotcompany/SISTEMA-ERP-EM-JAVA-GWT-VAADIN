package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.util.ObjectUtils;
import dc.control.util.classes.AgenciaBancoUtils;
import dc.control.validator.DotErpException;
import dc.entidade.financeiro.AgenciaBancoEntity;
import dc.entidade.financeiro.BancoEntity;
import dc.entidade.geral.diverso.UfEntity;
import dc.servicos.dao.financeiro.AgenciaBancoDAO;
import dc.servicos.dao.financeiro.BancoDAO;
import dc.servicos.dao.geral.UfDAO;
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
	private AgenciaBancoDAO agenciaBancoDAO;

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

			carregarUf();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {
			AgenciaBancoUtils.validateRequiredFields(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			this.currentBean.setNome(this.subView.getTfNome().getValue());
			this.currentBean.setLogradouro(this.subView.getTfLogradouro()
					.getValue());
			this.currentBean.setNumero(this.subView.getTfNumero().getValue());
			this.currentBean.setBairro(this.subView.getTfBairro().getValue());
			this.currentBean.setCep(this.subView.getTfCep().getValue());
			this.currentBean.setMunicipio(this.subView.getTfMunicipio()
					.getValue());
			this.currentBean.setTelefone(this.subView.getTfTelefone()
					.getValue());
			this.currentBean.setContato(this.subView.getTfContato().getValue());
			this.currentBean.setGerente(this.subView.getTfGerente().getValue());
			this.currentBean.setObservacao(this.subView.getTaObservacao()
					.getValue());

			BancoEntity banco = (BancoEntity) this.subView.getMocBanco()
					.getValue();

			this.currentBean.setBanco(banco);

			UfEntity uf = (UfEntity) this.subView.getCbUf().getValue();

			this.currentBean.setSiglaUf(uf.getSigla());
			this.currentBean.setUf(uf);

			this.agenciaBancoDAO.saveOrUpdateAgenciaBanco(this.currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.agenciaBancoDAO.find(id);

			this.subView.getTfNome().setValue(this.currentBean.getNome());
			this.subView.getTfLogradouro().setValue(
					this.currentBean.getLogradouro());
			this.subView.getTfNumero().setValue(this.currentBean.getNumero());
			this.subView.getTfBairro().setValue(this.currentBean.getBairro());
			this.subView.getTfCep().setValue(this.currentBean.getCep());
			this.subView.getTfMunicipio().setValue(
					this.currentBean.getMunicipio());
			this.subView.getTfTelefone().setValue(
					this.currentBean.getTelefone());
			this.subView.getTfContato().setValue(this.currentBean.getContato());
			this.subView.getTfGerente().setValue(this.currentBean.getGerente());
			this.subView.getTaObservacao().setValue(
					this.currentBean.getObservacao());

			BancoEntity banco = this.currentBean.getBanco();

			if (ObjectUtils.isNotBlank(banco)) {
				this.subView.getMocBanco().setValue(banco);
			}

			UfEntity uf = this.currentBean.getUf();

			if (ObjectUtils.isNotBlank(uf)) {
				this.subView.getCbUf().setValue(uf);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		try {
			this.currentBean = new AgenciaBancoEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.agenciaBancoDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	/**
	 * 
	 */

	public void carregarUf() {
		try {
			List<UfEntity> auxLista = this.ufDAO.listaTodos();

			BeanItemContainer<UfEntity> bic = new BeanItemContainer<UfEntity>(
					UfEntity.class, auxLista);

			this.subView.getCbUf().setContainerDataSource(bic);
			this.subView.getCbUf().setItemCaptionPropertyId("nome");
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}