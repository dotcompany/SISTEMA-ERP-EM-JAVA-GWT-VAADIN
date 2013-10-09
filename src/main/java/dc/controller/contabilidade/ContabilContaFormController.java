package dc.controller.contabilidade;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.contabilidade.ContabilConta;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.visao.contabilidade.ContabilContaFormView;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class ContabilContaFormController extends CRUDFormController<ContabilConta> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ContabilContaFormView subView;

	@Autowired
	private ContabilContaDAO contabilContaDAO;

	private ContabilConta currentBean;

	@Override
	protected String getNome() {
		return "Contabil Conta";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		subView.preencheBean(currentBean);
		try {
			currentBean.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
			contabilContaDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = contabilContaDAO.find(id);
		subView.preencheForm(currentBean);
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padr�o
	 */
	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void initSubView() {
		subView = new ContabilContaFormView();

		carregarCombos();

	}

	private void carregarCombos() {
		/*
		 * DefaultManyToOneComboModel<AtividadeForCli> atividadeModel = new
		 * DefaultManyToOneComboModel<AtividadeForCli>(
		 * AtividadeForCliListController.class, this.atividadeForCliDAO,
		 * super.getMainController());
		 * 
		 * DefaultManyToOneComboModel<SituacaoForCli> situacaoModel = new
		 * DefaultManyToOneComboModel
		 * <SituacaoForCli>(SituacaoForCliListController.class,
		 * this.situacaoForCliDAO, super.getMainController());
		 * 
		 * DefaultManyToOneComboModel<ContabilConta> contabilContaModel = new
		 * DefaultManyToOneComboModel<ContabilConta>(
		 * ContabilContaListController.class, this.contabilContaDAO,
		 * super.getMainController()) {
		 * 
		 * @Override public String getCaptionProperty() { return
		 * "codigoReduzido"; } };
		 * 
		 * DefaultManyToOneComboModel<Pessoa> pessoaModel = new
		 * DefaultManyToOneComboModel<Pessoa>(PessoaListController.class,
		 * this.pessoaDAO, super.getMainController());
		 * 
		 * subView.getCbAtividade().setModel(atividadeModel);
		 * subView.getCbSituacao().setModel(situacaoModel);
		 * subView.getCbContabilConta().setModel(contabilContaModel);
		 * subView.getCbPessoa().setModel(pessoaModel);
		 * 
		 * for (SimNao value : SimNao.values()) {
		 * subView.getCbSofreRentencao().addItem(value);
		 * subView.getCbGerarFaturamento().addItem(value);
		 * subView.getCbOptanteSimples().addItem(value); }
		 * 
		 * for (Localizacao value : Localizacao.values()) {
		 * subView.getCbLocalizacao().addItem(value); }
		 */
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new ContabilConta();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		contabilContaDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	@Override
	protected boolean validaSalvar() {

		boolean valido = true;

		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "contabilContaForm";
	}
}
