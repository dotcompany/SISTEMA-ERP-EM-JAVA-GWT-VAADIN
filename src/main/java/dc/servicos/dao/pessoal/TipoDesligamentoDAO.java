package dc.servicos.dao.pessoal;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.pessoal.TipoDesligamento;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*/


@Repository
@SuppressWarnings("unchecked")
public class TipoDesligamentoDAO extends AbstractCrudDAO<TipoDesligamento>{

	@Override
	public Class<TipoDesligamento> getEntityClass() {
		return TipoDesligamento.class;
	}

	@Transactional
	public List<TipoDesligamento> listaTodos() {
		return getSession().createQuery("from TipoDesligamento").list();
	}

	@Transactional
	public List<TipoDesligamento> procuraNomeContendo(String query) {
		return getSession().createQuery("from TipoDesligamento where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"descricao"};
	}

}
