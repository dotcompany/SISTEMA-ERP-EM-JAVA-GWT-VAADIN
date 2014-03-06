package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.SituacaoServico;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class SituacaoServicoDAO extends AbstractCrudDAO<SituacaoServico>{

	@Override
	public Class<SituacaoServico> getEntityClass() {
		return SituacaoServico.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"descricao"};
	}
	
	@Transactional
	public List<SituacaoServico> listaTodos() {
		return getSession().createQuery("from SituacaoServico").list();
	}
}


