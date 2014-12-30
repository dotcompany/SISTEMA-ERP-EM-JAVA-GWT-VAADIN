package dc.controller.geral.diverso;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.control.validator.classe.CepValidator;
import dc.controller.geral.UfListController;
import dc.entidade.geral.UfEntity;
import dc.entidade.geral.diverso.CepEntity;
import dc.model.business.geral.diverso.CepBusiness;
import dc.servicos.dao.geral.UfDAO;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.diverso.CepFormView;

@Controller
@Scope("prototype")
public class CepFormController extends CRUDFormController<CepEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CepFormView subView;

	/**
	 * ENTITY
	 */

	private CepEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private CepBusiness<CepEntity> business;

	/**
	 * DAO
	 */

	@Autowired
	private UfDAO ufDAO;

	/**
	 * CONSTRUTOR
	 */

	public CepFormController() {
		// TODO Auto-generated constructor stub
	}

	public CepBusiness<CepEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "CEP";
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
	public CepEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new CepFormView(this);

			DefaultManyToOneComboModel<UfEntity> modelUf = new DefaultManyToOneComboModel<UfEntity>(
					UfListController.class, this.ufDAO,
					super.getMainController());

			this.subView.getMocUf().setModel(modelUf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {
			CepValidator.validaSalvar(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			String cep = this.subView.getTfCep().getValue();
			String logradouro = subView.getTfLogradouro().getValue();
			String complemento = subView.getTfComplemento().getValue();
			String bairro = subView.getTfBairro().getValue();
			String municipio = subView.getTfMunicipio().getValue();

			this.entity.setCep(cep);
			this.entity.setLogradouro(logradouro);
			this.entity.setComplemento(complemento);
			this.entity.setBairro(bairro);
			this.entity.setMunicipio(municipio);

			UfEntity uf = this.subView.getMocUf().getValue();

			if (ObjectUtils.isNotBlank(uf)) {
				this.entity.setUf(uf.getSigla());
			} else {
				this.entity.setUf(null);
			}

			this.business.saveOrUpdate(this.entity);

			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.entity = this.business.find(id);

			this.subView.getTfCep().setValue(this.entity.getCep());
			this.subView.getTfLogradouro()
					.setValue(this.entity.getLogradouro());
			this.subView.getTfComplemento().setValue(
					this.entity.getComplemento());
			this.subView.getTfBairro().setValue(this.entity.getBairro());
			this.subView.getTfMunicipio().setValue(this.entity.getMunicipio());

			String sigla = this.entity.getUf();

			if (StringUtils.isNotBlank(sigla)) {
				UfEntity uf = this.ufDAO.find(sigla);

				this.subView.getMocUf().setValue(uf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new CepEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void quandoNovo() {
		try {
			this.entity = new CepEntity();
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

	}

}