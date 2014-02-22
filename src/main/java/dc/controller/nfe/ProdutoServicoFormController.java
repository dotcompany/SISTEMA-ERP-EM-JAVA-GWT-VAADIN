package dc.controller.nfe;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.nfe.NfeDeclaracaoImportacaoEntity;
import dc.entidade.nfe.NfeDetalheEntity;
import dc.entidade.nfe.NfeDetalheImpostoCofinsEntity;
import dc.servicos.dao.nfe.NfeDeclaracaoImportacaoDAO;
import dc.servicos.dao.nfe.NfeDetalheDAO;
import dc.servicos.dao.nfe.NfeDetalheImpostoCofinsDAO;
import dc.servicos.dao.nfe.NfeDetalheImpostoIcmsDAO;
import dc.servicos.dao.nfe.NfeDetalheImpostoIiDAO;
import dc.servicos.dao.nfe.NfeDetalheImpostoIpiDAO;
import dc.servicos.dao.nfe.NfeDetalheImpostoIssqnDAO;
import dc.servicos.dao.nfe.NfeDetalheImpostoPisDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.nfe.ProdutoServicoFormView;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class ProdutoServicoFormController extends
		CRUDFormController<NfeDetalheEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProdutoServicoFormView subView;

	/**
	 * DAO'S
	 */

	@Autowired
	private NfeDetalheDAO nfedDAO;

	@Autowired
	private NfeDeclaracaoImportacaoDAO nfediDAO;

	@Autowired
	private NfeDetalheImpostoCofinsDAO nfedCofinsDAO;

	@Autowired
	private NfeDetalheImpostoIcmsDAO nfediIcmsDAO;

	@Autowired
	private NfeDetalheImpostoIiDAO nfediIiDAO;

	@Autowired
	private NfeDetalheImpostoIpiDAO nfediIpiDAO;

	@Autowired
	private NfeDetalheImpostoIssqnDAO nfediIssqnDAO;

	@Autowired
	private NfeDetalheImpostoPisDAO nfediPisDAO;

	/**
	 * ENTITIES
	 */

	/**
	 * CONSTRUTOR
	 */

	public ProdutoServicoFormController() {

	}

	@Override
	protected String getNome() {
		return "Produto / serviço";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {

		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {

		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {

		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {
		try {

		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new ProdutoServicoFormView(this);

		List<NfeDetalheEntity> auxLista1 = (List<NfeDetalheEntity>) this.nfedDAO
				.listarTodos();

		List<NfeDetalheImpostoCofinsEntity> auxLista2 = (List<NfeDetalheImpostoCofinsEntity>) this.nfedCofinsDAO
				.listarTodos();

		List<NfeDeclaracaoImportacaoEntity> auxLista3 = (List<NfeDeclaracaoImportacaoEntity>) this.nfediDAO
				.listarTodos();

		this.subView.preencherSubForm(auxLista1, auxLista2, auxLista3);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {

		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "";
	}

	/**
	 * COMBOS
	 */

	/**
	 * **************************************
	 */

	@Override
	protected boolean isFullSized() {
		return true;
	}

}