package dc.servicos.dao.pessoal;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.pessoal.TipoAdmissao;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*/


@Repository
@SuppressWarnings("unchecked")
public class TipoAdmissaoDAO extends AbstractCrudDAO<TipoAdmissao>{

	@Override
	protected Class<TipoAdmissao> getEntityClass() {
		return TipoAdmissao.class;
	}

	@Transactional
	public List<TipoAdmissao> listaTodos() {
		return getSession().createQuery("from TipoAdmissao").list();
	}

	@Transactional
	public List<TipoAdmissao> procuraNomeContendo(String query) {
		return getSession().createQuery("from TipoAdmissao where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo", "nome", "descricao"};
	}

}
