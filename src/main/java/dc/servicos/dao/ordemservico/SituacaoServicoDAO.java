package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.SituacaoServicoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class SituacaoServicoDAO extends AbstractCrudDAO<SituacaoServicoEntity>{

	@Override
	public Class<SituacaoServicoEntity> getEntityClass() {
		return SituacaoServicoEntity.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"descricao"};
	}
	
	@Transactional
	public List<SituacaoServicoEntity> listaTodos() {
		return getSession().createQuery("from SituacaoServicoEntity").list();
	}
}


