package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.EfdTabela439;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class EfdTabela439DAO extends AbstractCrudDAO<EfdTabela439>{

	@Override
	public Class<EfdTabela439> getEntityClass() {
		return EfdTabela439.class;
	}
	
	@Transactional
	public List<EfdTabela439> listaTodos() {
		return getSession().createQuery("from EfdTabela439").list();
	}

	@Transactional
	public List<EfdTabela439> procuraNomeContendo(String query) {
		return getSession().createQuery("from EfdTabela439 where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao","observacao", "inicioVigencia","fimVigencia"};
	}


}
