package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.entidade.financeiro.AgenciaBanco;
import dc.entidade.financeiro.Banco;
import dc.entidade.geral.UfEntity;
import dc.servicos.dao.financeiro.AgenciaBancoDAO;
import dc.servicos.dao.financeiro.BancoDAO;
import dc.servicos.dao.geral.UfDAO;
import dc.servicos.util.Validator;
import dc.visao.financeiro.AgenciaBancoFormView;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;

/** @author Wesley Jr /* Nessa classe ela pega a classe principal que é o CRUD,
 *         que tem todos os controllers da Tela, onde quando extendemos herdamos
 *         os métodos que temos na tela principal. Temos o botão Novo que é para
 *         Criar uma nova Tela, para adicionar informações novas, e dentro temos
 *         o Button Salvar que é para salvar as informações no Banco de Dados
 *         Temos o carregar também que é para pegar as informações que
 *         desejarmos quando formos pesquisar na Tela. */

@Controller
@Scope("prototype")
public class AgenciaBancoFormController extends CRUDFormController<AgenciaBanco> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AgenciaBancoFormView subView;

	@Autowired
	private AgenciaBancoDAO agenciaDAO;

	@Autowired
	private BancoDAO bancoDAO;

	@Autowired
	private UfDAO ufDAO;

	private AgenciaBanco currentBean;

	@Override
	protected String getNome() {
		return "Agência Banco";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		currentBean.setNome(subView.getTxtNomee().getValue());
		currentBean.setLogradouro(subView.getTxtLogradouro().getValue());
		currentBean.setBairro(subView.getTxtBairro().getValue());
		currentBean.setCep(subView.getTxtCep().getValue());
		currentBean.setContato(subView.getTxtContato().getValue());
		currentBean.setGerente(subView.getTxtGerente().getValue());
		currentBean.setTelefone(subView.getTxtTelefone().getValue());
		currentBean.setNumero(subView.getTxtNumero().getValue());

		try {
			agenciaDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {

		currentBean = agenciaDAO.find(id);

		try {
			carregarCombos();

			if (Validator.validateObject(currentBean.getUf())) {
				subView.getCmbUF().setValue(currentBean.getUf());
			}

			if (Validator.validateObject(currentBean.getNome())) {
				subView.getTxtNomee().setValue(currentBean.getNome());
			}
			if (Validator.validateObject(currentBean.getLogradouro())) {
				subView.getTxtLogradouro().setValue(currentBean.getLogradouro());
			}
			if (Validator.validateObject(currentBean.getBairro())) {
				subView.getTxtBairro().setValue(currentBean.getBairro());
			}
			if (Validator.validateObject(currentBean.getBairro())) {
				subView.getTxtBairro().setValue(currentBean.getBairro());
			}
			if (Validator.validateObject(currentBean.getMunicipio())) {
				subView.getTxtMunicipio().setValue(currentBean.getMunicipio());
			}
			if (Validator.validateObject(currentBean.getCep())) {
				subView.getTxtCep().setValue(currentBean.getCep());
			}
			if (Validator.validateObject(currentBean.getContato())) {
				subView.getTxtContato().setValue(currentBean.getContato());
			}
			if (Validator.validateObject(currentBean.getGerente())) {
				subView.getTxtGerente().setValue(currentBean.getGerente());
			}
			if (Validator.validateObject(currentBean.getTelefone())) {
				subView.getTxtTelefone().setValue(currentBean.getTelefone());
			}
			if (Validator.validateObject(currentBean.getNumero())) {
				subView.getTxtNumero().setValue(currentBean.getNumero());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	void carregarCombos() {
		carregarUFs();
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
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void initSubView() {
		this.subView = new AgenciaBancoFormView(this);

		DefaultManyToOneComboModel<Banco> modelBanco = new DefaultManyToOneComboModel<Banco>(BancoListController.class, this.bancoDAO,
				super.getMainController()) {
			@Override
			public String getCaptionProperty() {
				return "nome";
			}
		};

		this.subView.getCmbBanco().setModel(modelBanco);

		/*
		 * DefaultManyToOneComboModel<UF> modelUf = new
		 * DefaultManyToOneComboModel<UF>( UFListController.class, this.ufDAO,
		 * super.getMainController()) {
		 * 
		 * @Override public String getCaptionProperty() { return "nome"; } };
		 * 
		 * this.subView.getCmbUF().setModel(modelUf);
		 */

		// subView.InitCbs(bancoDAO.listaTodos(), ufDAO.listaTodos());
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new AgenciaBanco();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		agenciaDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {

		boolean valido = true;

		if (!Validator.validateString(subView.getTxtNomee().getValue())) {

			adicionarErroDeValidacao(subView.getTxtNomee(), "Não pode ficar em branco");

			adicionarErroDeValidacao(subView.getTxtNomee(), "Não pode ficar em branco");

			valido = false;
		}

		if (!Validator.validateString(subView.getTxtLogradouro().getValue())) {

			adicionarErroDeValidacao(subView.getTxtLogradouro(), "Não pode ficar em branco");

			adicionarErroDeValidacao(subView.getTxtLogradouro(), "Não pode ficar em branco");

			valido = false;
		}

		if (!Validator.validateString(subView.getTxtBairro().getValue())) {

			adicionarErroDeValidacao(subView.getTxtBairro(), "Não pode ficar em branco");

			adicionarErroDeValidacao(subView.getTxtBairro(), "Não pode ficar em branco");

			valido = false;
		}

		if (!Validator.validateString(subView.getTxtNumero().getValue())) {

			adicionarErroDeValidacao(subView.getTxtNumero(), "Não pode ficar em branco");

			adicionarErroDeValidacao(subView.getTxtNumero(), "Não pode ficar em branco");

			valido = false;
		}

		if (!Validator.validateString(subView.getTxtCep().getValue())) {

			adicionarErroDeValidacao(subView.getTxtCep(), "Não pode ficar em branco");

			adicionarErroDeValidacao(subView.getTxtCep(), "Não pode ficar em branco");

			valido = false;
		}

		if (!Validator.validateString(subView.getTxtMunicipio().getValue())) {

			adicionarErroDeValidacao(subView.getTxtMunicipio(), "Não pode ficar em branco");

			adicionarErroDeValidacao(subView.getTxtMunicipio(), "Não pode ficar em branco");

			valido = false;
		}

		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "agenciaBancoForm";
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	public AgenciaBanco getModelBean() {
		return currentBean;
	}

}