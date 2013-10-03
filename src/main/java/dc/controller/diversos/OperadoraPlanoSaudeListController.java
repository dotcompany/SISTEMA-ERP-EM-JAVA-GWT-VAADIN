package dc.controller.diversos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.diversos.OperadoraPlanoSaude;
import dc.servicos.dao.diversos.OperadoraPlanoSaudeDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class OperadoraPlanoSaudeListController extends
		CRUDListController<OperadoraPlanoSaude> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private OperadoraPlanoSaudeDAO dao;

	@Autowired
	private OperadoraPlanoSaudeFormController operadoraPlanoSaudeFormController;

	@Override
	protected CRUDFormController<OperadoraPlanoSaude> getFormController() {
		return operadoraPlanoSaudeFormController;
	}

	@Override
	protected String[] getColunas() {
		return new String[] { "registroAns", "nome" };
	}

	@Override
	public String getViewIdentifier() {
		return "listaOperadoraPlanoSaude";
	}

	@Override
	protected Class<? super OperadoraPlanoSaude> getEntityClass() {
		return OperadoraPlanoSaude.class;
	}

	@Override
	protected List<OperadoraPlanoSaude> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return "Operadora Plano Saúde";
	}

	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();

	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<OperadoraPlanoSaude> pesquisaDefault() {
		return (List<OperadoraPlanoSaude>) dao.getAll(getEntityClass());
	}

}