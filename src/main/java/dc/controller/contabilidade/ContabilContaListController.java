package dc.controller.contabilidade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.contabilidade.ContabilConta;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class ContabilContaListController extends CRUDListController<ContabilConta> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ContabilContaDAO dao;

	@Autowired
	private ContabilContaFormController contabilContaFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "classificacao", "tipo", "descricao", "dataInclusao", "situacao", "natureza", "patrimonioResultado", "livroCaixa",
				"dfc", "ordem", "codigoReduzido", "codigoEfd" };
	}

	@Override
	public Class<? super ContabilConta> getEntityClass() {
		return ContabilConta.class;
	}

	@Override
	protected String getTitulo() {
		return "Contabil Conta";
	}

	@Override
	protected List<ContabilConta> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<ContabilConta> getFormController() {
		return contabilContaFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaContabilContas";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<ContabilConta> pesquisaDefault() {
		return (List<ContabilConta>) dao.getAll(getEntityClass());
	}

}
