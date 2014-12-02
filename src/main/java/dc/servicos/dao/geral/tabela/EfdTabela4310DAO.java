package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.EfdTabela4310Entity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class EfdTabela4310DAO extends AbstractCrudDAO<EfdTabela4310Entity>{

	@Override
	public Class<EfdTabela4310Entity> getEntityClass() {
		return EfdTabela4310Entity.class;
	}
	
	@Transactional
	public List<EfdTabela4310Entity> listaTodos() {
		return getSession().createQuery("from EfdTabela4310").list();
	}

	@Transactional
	public List<EfdTabela4310Entity> procuraNomeContendo(String query) {
		return getSession().createQuery("from EfdTabela4310 where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao","observacao", "inicioVigencia","fimVigencia"};
	}


}
