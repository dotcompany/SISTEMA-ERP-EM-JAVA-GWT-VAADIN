package dc.servicos.dao.framework.geral;

import org.springframework.stereotype.Repository;

import dc.entidade.framework.SeguimentoEntity;

@Repository
public class SeguimentoDAO extends AbstractCrudDAO<SeguimentoEntity> {

	@Override
	public Class<SeguimentoEntity> getEntityClass() {
		return SeguimentoEntity.class;
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao" };
	}

}
