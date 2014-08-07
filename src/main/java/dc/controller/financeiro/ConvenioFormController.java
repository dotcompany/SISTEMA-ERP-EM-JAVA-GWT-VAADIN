package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.entidade.financeiro.Convenio;
import dc.entidade.geral.UF;
import dc.servicos.dao.financeiro.ConvenioDAO;
import dc.servicos.dao.geral.UFDAO;
import dc.visao.financeiro.ConvenioFormView;
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
public class ConvenioFormController extends CRUDFormController<Convenio> {

	private ConvenioFormView subView;

	@Autowired
	private ConvenioDAO convenioDAO;

	@Autowired
	private UFDAO ufDAO;

	private Convenio currentBean;

	@Override
	protected String getNome() {
		return "Convênio";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {

		try {

			currentBean.setLogradouro(subView.getTxtLogradouro().getValue());
			currentBean.setDataVencimento(subView.getDnDataVencimento().getValue());
			currentBean.setNumero(subView.getTxtNumero().getValue());
			currentBean.setBairro(subView.getTxtBairro().getValue());
			currentBean.setContato(subView.getTxtContato().getValue());
			currentBean.setTelefone(subView.getTxtTelefone().getValue());
			currentBean.setDescricao(subView.getTxtDescricao().getValue());
			currentBean.setCep(subView.getTxtCep().getValue());

			convenioDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = convenioDAO.find(id);
		subView.getTxtLogradouro().setValue(currentBean.getLogradouro());
		subView.getTxtNumero().setValue(currentBean.getNumero());
		subView.getTxtBairro().setValue(currentBean.getBairro());
		subView.getTxtContato().setValue(currentBean.getContato());
		subView.getTxtTelefone().setValue(currentBean.getTelefone());
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
		subView.getTxtCep().setValue(currentBean.getCep());
	}

	void carregarCombos() {
		carregarUFs();
	}

	public List<UF> listarUfs() {
		return ufDAO.listaTodos();
	}

	public BeanItemContainer<String> carregarUFs() {
		BeanItemContainer<String> container = new BeanItemContainer<>(String.class);
		List<UF> ufs = listarUfs();
		for (UF u : ufs) {
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
		subView = new ConvenioFormView(this);

		/*
		 * DefaultManyToOneComboModel<UF> model = new
		 * DefaultManyToOneComboModel<UF>(UFListController.class, this.ufDAO,
		 * super.getMainController()) {
		 * 
		 * @Override public String getCaptionProperty() { return "nome"; } };
		 * this.subView.getCmbUF().setModel(model);
		 */
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new Convenio();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		convenioDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */

	protected boolean validaSalvar() {

		boolean valido = validaCampos();

		return valido;
	}

	private boolean validaCampos() {

		boolean valido = true;

		if (subView.getTxtLogradouro().getValue() == null || subView.getTxtLogradouro().getValue().isEmpty()) {
			adicionarErroDeValidacao(subView.getTxtLogradouro(), "Não pode ficar em Branco!");
			return false;
		}
		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "convenioForm";
	}

	@Override
	public Convenio getModelBean() {
		return currentBean;
	}

}
