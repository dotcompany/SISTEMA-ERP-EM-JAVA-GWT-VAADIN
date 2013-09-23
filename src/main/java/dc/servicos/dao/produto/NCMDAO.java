package dc.servicos.dao.produto;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.produto.NCM;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*/


@Repository
@SuppressWarnings("unchecked")
public class NCMDAO extends AbstractCrudDAO<NCM>{

	@Override
	public Class<NCM> getEntityClass() {
		return NCM.class;
	}

	@Transactional
	public List<NCM> listaTodos() {
		return getSession().createQuery("from NCM").list();
	}

	@Transactional
	public List<NCM> procuraNomeContendo(String query) {
		return getSession().createQuery("from NCM where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao"};
	}

}
