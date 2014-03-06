package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.TipoServico;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class TipoServicoDAO extends AbstractCrudDAO<TipoServico>{

	@Override
	public Class<TipoServico> getEntityClass() {
		return TipoServico.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"descricao"};
	}
	
	@Transactional
	public List<TipoServico> listaTodos() {
		return getSession().createQuery("from TipoServico").list();
	}
}


