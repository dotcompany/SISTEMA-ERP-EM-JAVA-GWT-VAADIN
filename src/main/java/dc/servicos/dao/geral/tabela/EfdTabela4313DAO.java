package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.EfdTabela4313;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class EfdTabela4313DAO extends AbstractCrudDAO<EfdTabela4313>{

	@Override
	public Class<EfdTabela4313> getEntityClass() {
		return EfdTabela4313.class;
	}
	
	@Transactional
	public List<EfdTabela4313> listaTodos() {
		return getSession().createQuery("from EfdTabela4313").list();
	}

	@Transactional
	public List<EfdTabela4313> procuraNomeContendo(String query) {
		return getSession().createQuery("from EfdTabela4313 where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao","observacao", "inicioVigencia","fimVigencia"};
	}


}
