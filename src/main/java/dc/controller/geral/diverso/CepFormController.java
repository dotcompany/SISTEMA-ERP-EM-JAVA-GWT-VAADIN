package dc.controller.geral.diverso;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.control.validator.classe.CepValidator;
import dc.controller.geral.UfListController;
import dc.entidade.geral.UfEntity;
import dc.entidade.geral.diverso.CepEntity;
import dc.servicos.dao.geral.UfDAO;
import dc.servicos.dao.geral.diverso.CepDAO;
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

	private CepEntity currentBean;

	@Autowired
	private CepDAO cepDAO;

	@Autowired
	private UfDAO ufDAO;

	// private MainController mainController;

	@Override
	protected String getNome() {
		return "Cep";
	}

	@Override
	protected Component getSubView() {
		return subView;
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

			this.currentBean.setCep(cep);
			this.currentBean.setLogradouro(logradouro);
			this.currentBean.setComplemento(complemento);
			this.currentBean.setBairro(bairro);
			this.currentBean.setMunicipio(municipio);

			UfEntity uf = this.subView.getMocUf().getValue();

			if (uf != null) {
				this.currentBean.setUf(uf.getSigla());
			} else {
				this.currentBean.setUf(null);
			}

			this.cepDAO.saveOrUpdate(this.currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.cepDAO.find(id);

			this.subView.getTfCep().setValue(this.currentBean.getCep());
			this.subView.getTfLogradouro().setValue(
					this.currentBean.getLogradouro());
			this.subView.getTfComplemento().setValue(
					this.currentBean.getComplemento());
			this.subView.getTfBairro().setValue(this.currentBean.getBairro());
			this.subView.getTfMunicipio().setValue(
					this.currentBean.getMunicipio());

			String sigla = this.currentBean.getUf();

			if (StringUtils.isNotBlank(sigla)) {
				UfEntity uf = this.ufDAO.find(sigla);

				this.subView.getMocUf().setValue(uf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Callback para quando novo foi acionado. Colocar ProgramaÃ§Ã£o customizada
	 * para essa aÃ§Ã£o aqui. Ou entÃ£o deixar em branco, para comportamento
	 * padrÃ£o
	 */
	@Override
	protected void quandoNovo() {

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

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.currentBean = new CepEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.cepDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "cepForm";
	}

	@Override
	public CepEntity getModelBean() {
		return currentBean;
	}

}