package dc.controller.contratos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.contratos.Template;
import dc.servicos.dao.contratos.TemplateDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("unchecked")
public class TemplateListController extends CRUDListController<Template> {
	@Autowired
	TemplateDAO dao;

	@Autowired
	TemplateFormController templateFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "descricao" };
	}

	@Override
	public Class<? super Template> getEntityClass() {
		return Template.class;
	}

	@Override
	protected String getTitulo() {
		return "Template";
	}

	@Override
	protected List<Template> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<Template> getFormController() {
		return templateFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaTemplate";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<Template> pesquisaDefault() {
		return (List<Template>) dao.getAll(getEntityClass());
	}

}
