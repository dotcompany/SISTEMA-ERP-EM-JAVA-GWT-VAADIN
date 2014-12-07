package dc.controller.geral.diverso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.diverso.OperadoraPlanoSaudeEntity;
import dc.servicos.dao.geral.diverso.OperadoraPlanoSaudeDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class OperadoraPlanoSaudeListController extends
		CRUDListController<OperadoraPlanoSaudeEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private OperadoraPlanoSaudeDAO dao;

	@Autowired
	private OperadoraPlanoSaudeFormController operadoraPlanoSaudeFormController;

	@Override
	protected CRUDFormController<OperadoraPlanoSaudeEntity> getFormController() {
		return operadoraPlanoSaudeFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "registroAns", "nome" };
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	public Class<? super OperadoraPlanoSaudeEntity> getEntityClass() {
		return OperadoraPlanoSaudeEntity.class;
	}

	@Override
	protected List<OperadoraPlanoSaudeEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
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
	protected List<OperadoraPlanoSaudeEntity> pesquisaDefault() {
		return (List<OperadoraPlanoSaudeEntity>) dao.getAll(getEntityClass());
	}

}