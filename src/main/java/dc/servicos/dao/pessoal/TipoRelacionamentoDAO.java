package dc.servicos.dao.pessoal;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.pessoal.TipoRelacionamento;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*/


@Repository
@SuppressWarnings("unchecked")
public class TipoRelacionamentoDAO extends AbstractCrudDAO<TipoRelacionamento>{

	@Override
	protected Class<TipoRelacionamento> getEntityClass() {
		return TipoRelacionamento.class;
	}

	@Transactional
	public List<TipoRelacionamento> listaTodos() {
		return getSession().createQuery("from TipoRelacionamento").list();
	}

	@Transactional
	public List<TipoRelacionamento> procuraNomeContendo(String query) {
		return getSession().createQuery("from TipoRelacionamento where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo", "nome", "descricao"};
	}

}
