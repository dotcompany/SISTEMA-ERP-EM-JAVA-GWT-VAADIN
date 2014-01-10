package dc.servicos.dao.contratos;

import org.springframework.stereotype.Repository;

import dc.entidade.contratos.Template;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class TemplateDAO extends AbstractCrudDAO<Template> {

	@Override
	public Class<Template> getEntityClass() {
		return Template.class;
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao" };
	}

}
