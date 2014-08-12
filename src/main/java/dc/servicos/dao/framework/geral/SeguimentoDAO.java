package dc.servicos.dao.framework.geral;

import org.springframework.stereotype.Repository;

import dc.entidade.framework.Seguimento;

@Repository
public class SeguimentoDAO extends AbstractCrudDAO<Seguimento> {

	@Override
	public Class<Seguimento> getEntityClass() {
		return Seguimento.class;
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao" };
	}

}
