package dc.controller.geral.diverso;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.entidade.geral.UfEntity;
import dc.entidade.geral.diverso.CepEntity;
import dc.servicos.dao.geral.UfDAO;
import dc.servicos.dao.geral.diverso.CepDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.diverso.CepFormView;

/** @author Wesley Jr /* Nessa classe ela pega a classe principal que Ã© o CRUD,
 *         que tem todos os controllers da Tela, onde quando extendemos herdamos
 *         os mÃ©todos que temos na tela principal. Temos o botÃ£o Novo que Ã©
 *         para Criar uma nova Tela, para adicionar informaÃ§Ãµes novas, e
 *         dentro temos o Button Salvar que Ã© para salvar as informaÃ§Ãµes no
 *         Banco de Dados Temos o carregar tambÃ©m que Ã© para pegar as
 *         informaÃ§Ãµes que desejarmos quando formos pesquisar na Tela. */

@Controller
@Scope("prototype")
public class CepFormController extends CRUDFormController<CepEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CepFormView subView;

	@Autowired
	private CepDAO cepDAO;

	@Autowired
	private UfDAO ufDAO;

	private CepEntity currentBean;

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
	protected void actionSalvar() {

		try {
			cepDAO.saveOrUpdate(currentBean);

			String cep = subView.getTxtCep().getValue();
			String uf = (String) subView.getCmbUf().getValue();
			String logradouro = subView.getTxtLogradouro().getValue();
			String complemento = subView.getTxtComplemento().getValue();
			String bairro = subView.getTxtBairro().getValue();
			String municipio = subView.getTxtMunicipio().getValue();

			if (Validator.validateObject(cep)) {
				currentBean.setCep(cep);
			}
			if (Validator.validateObject(uf)) {
				currentBean.setUf(uf);
			}
			if (Validator.validateObject(logradouro)) {
				currentBean.setLogradouro(logradouro);
			}
			if (Validator.validateObject(complemento)) {
				currentBean.setComplemento(complemento);
			}
			if (Validator.validateObject(bairro)) {
				currentBean.setBairro(bairro);
			}
			if (Validator.validateObject(municipio)) {
				currentBean.setMunicipio(municipio);
			}

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = cepDAO.find(id);

		try {
			carregarCombos();

			if (Validator.validateObject(currentBean.getUf())) {
				subView.getCmbUf().setValue(currentBean.getUf());
			}

			if (Validator.validateObject(currentBean.getCep())) {
				subView.getTxtCep().setValue(currentBean.getCep());
			}
			if (Validator.validateObject(currentBean.getLogradouro())) {
				subView.getTxtLogradouro().setValue(currentBean.getLogradouro());
			}
			if (Validator.validateObject(currentBean.getComplemento())) {
				subView.getTxtComplemento().setValue(currentBean.getComplemento());
			}
			if (Validator.validateObject(currentBean.getBairro())) {
				subView.getTxtBairro().setValue(currentBean.getBairro());
			}
			if (Validator.validateObject(currentBean.getMunicipio())) {
				subView.getTxtMunicipio().setValue(currentBean.getMunicipio());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	void carregarCombos() {
		carregarUFs();
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
			// DefaultManyToOneComboModel<UF> modeluf= new
			// DefaultManyToOneComboModel(UFListController . class , ufDAO ,
			// mainController );

			/*
			 * DefaultManyToOneComboModel<UF> ufModel = new
			 * DefaultManyToOneComboModel<UF>(UFListController.class,
			 * this.ufDAO, super.getMainController()) {
			 * 
			 * @Override public String getCaptionProperty() { return "nome"; }
			 * };
			 */

			// this.subView.getCmbUf().setModel(ufModel);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<UfEntity> listarUfs() {
		return ufDAO.listaTodos();
	}

	public BeanItemContainer<String> carregarUFs() {
		BeanItemContainer<String> container = new BeanItemContainer<>(String.class);
		List<UfEntity> ufs = listarUfs();
		for (UfEntity u : ufs) {
			container.addBean(u.getSigla());
		}

		return container;
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new CepEntity();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		cepDAO.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		if (subView.getTxtCep().getValue() == null || subView.getTxtCep().getValue().isEmpty()) {
			// Utilizar adicionarErroDeValidacao() para adicionar mensagem de
			// erro para o campo que esta sendo validado
			adicionarErroDeValidacao(subView.getTxtCep(), "Não pode ficar em branco!");

			return false;
		}

		return true;
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