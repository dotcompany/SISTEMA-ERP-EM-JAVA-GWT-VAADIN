package dc.servicos.dao.suprimentos.contrato;

import org.springframework.stereotype.Repository;

import dc.entidade.suprimentos.contrato.TemplateEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("suprimentosContratoTemplateDAO")
public class TemplateDAO extends AbstractCrudDAO<TemplateEntity> {

	@Override
	public Class<TemplateEntity> getEntityClass() {
		return TemplateEntity.class;
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao" };
	}

}