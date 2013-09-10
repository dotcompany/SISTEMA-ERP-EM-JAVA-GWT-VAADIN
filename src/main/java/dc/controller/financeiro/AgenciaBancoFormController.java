package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;

import dc.entidade.financeiro.AgenciaBanco;
import dc.entidade.financeiro.Banco;
import dc.servicos.dao.financeiro.AgenciaBancoDAO;
import dc.servicos.dao.financeiro.BancoDAO;
import dc.servicos.dao.geral.UFDAO;
import dc.servicos.util.Validator;
import dc.visao.financeiro.AgenciaBancoFormView;
import dc.visao.framework.component.manytoonecombo.ManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;

/**
 * 
 * @author Wesley Jr /* Nessa classe ela pega a classe principal que é o CRUD,
 *         que tem todos os controllers da Tela, onde quando extendemos herdamos
 *         os métodos que temos na tela principal. Temos o botão Novo que é para
 *         Criar uma nova Tela, para adicionar informações novas, e dentro temos
 *         o Button Salvar que é para salvar as informações no Banco de Dados
 *         Temos o carregar também que é para pegar as informações que
 *         desejarmos quando formos pesquisar na Tela.
 * 
 */

@Controller
@Scope("prototype")
public class AgenciaBancoFormController extends
		CRUDFormController<AgenciaBanco> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	AgenciaBancoFormView subView;

	@Autowired
	AgenciaBancoDAO agenciaDAO;

	@Autowired
	BancoDAO bancoDAO;

	@Autowired
	UFDAO ufDAO;

	private AgenciaBanco currentBean;

	@Override
	protected String getNome() {
		return "AgenciaBanco";
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
			mensagemSalvoOK();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = agenciaDAO.find(id);
		subView.getTxtNomee().setValue(currentBean.getNome());
		subView.getTxtLogradouro().setValue(currentBean.getLogradouro());
		subView.getTxtBairro().setValue(currentBean.getBairro());
		subView.getTxtCep().setValue(currentBean.getCep());
		subView.getTxtContato().setValue(currentBean.getContato());
		subView.getTxtGerente().setValue(currentBean.getGerente());
		subView.getTxtTelefone().setValue(currentBean.getTelefone());
		subView.getTxtNumero().setValue(currentBean.getNumero());

		/* Configura combo BANCOOOOO */
		ManyToOneComboModel<Banco> model = new ManyToOneComboModel<Banco>() {

			@Override
			public void onCriarNovo(String filter) {
				Notification.show("Selecionado Criar Novo: " + filter);
			}

			@Override
			public List<Banco> getResultado(String q) {
				return bancoDAO.query(q);
			}

			@Override
			public Class<Banco> getEntityClass() {
				return Banco.class;
			}

			@Override
			public String getCaptionProperty() {
				return "nome";
			}

			@Override
			public void onEditar(Banco value) {
				Notification.show("Selecionado Editar: " + value.getNome());

			}
		};
		//subView.getCmbBanco().setModel(model);
		//subView.getCmbBanco().setValue(currentBean.getBanco());

		/* Configura combo UF */
		/*
		 * ManyToOneComboModel<UF> modeluf = new ManyToOneComboModel<UF>() {
		 * 
		 * @Override public void onCriarNovo(String filter) {
		 * Notification.show("Selecionado Criar Novo: " + filter); }
		 * 
		 * @Override public List<UF> getResultado(String q) { return
		 * ufDAO.query(q); }
		 * 
		 * @Override public Class<UF> getEntityClass() { return UF.class; }
		 * 
		 * @Override public String getCaptionProperty() { return "nome"; }
		 * 
		 * @Override public void onEditar(UF value) {
		 * Notification.show("Selecionado Editar: " + value.getNome());
		 * 
		 * } }; subView.getCmbUF().setModel(modeluf);
		 * subView.getCmbUF().setValue(currentBean.getUf());
		 */

		subView.getCmbUF().setValue(currentBean.getUf());

	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa a��o aqui. Ou então deixar em branco, para comportamento padr�o
	 */
	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void initSubView() {
		subView = new AgenciaBancoFormView();

		subView.InitCbs(bancoDAO.listaTodos(), ufDAO.listaTodos());
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

			adicionarErroDeValidacao(subView.getTxtNomee(),
					"Não pode ficar em branco");

			adicionarErroDeValidacao(subView.getTxtNomee(),
					"Não pode ficar em branco");

			valido = false;
		}

		if (!Validator.validateString(subView.getTxtLogradouro().getValue())) {

			adicionarErroDeValidacao(subView.getTxtLogradouro(),
					"Não pode ficar em branco");

			adicionarErroDeValidacao(subView.getTxtLogradouro(),
					"Não pode ficar em branco");

			valido = false;
		}

		if (!Validator.validateString(subView.getTxtBairro().getValue())) {

			adicionarErroDeValidacao(subView.getTxtBairro(),
					"Não pode ficar em branco");

			adicionarErroDeValidacao(subView.getTxtBairro(),
					"Não pode ficar em branco");

			valido = false;
		}

		if (!Validator.validateString(subView.getTxtNumero().getValue())) {

			adicionarErroDeValidacao(subView.getTxtNumero(),
					"Não pode ficar em branco");

			adicionarErroDeValidacao(subView.getTxtNumero(),
					"Não pode ficar em branco");

			valido = false;
		}

		if (!Validator.validateString(subView.getTxtCep().getValue())) {

			adicionarErroDeValidacao(subView.getTxtCep(),
					"Não pode ficar em branco");

			adicionarErroDeValidacao(subView.getTxtCep(),
					"Não pode ficar em branco");

			valido = false;
		}

		if (!Validator.validateString(subView.getTxtMunicipio().getValue())) {

			adicionarErroDeValidacao(subView.getTxtMunicipio(),
					"Não pode ficar em branco");

			adicionarErroDeValidacao(subView.getTxtMunicipio(),
					"Não pode ficar em branco");

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

}
