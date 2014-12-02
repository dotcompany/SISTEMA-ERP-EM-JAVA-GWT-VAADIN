package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.EfdTabela4316;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class EfdTabela4316DAO extends AbstractCrudDAO<EfdTabela4316>{

	@Override
	public Class<EfdTabela4316> getEntityClass() {
		return EfdTabela4316.class;
	}
	
	@Transactional
	public List<EfdTabela4316> listaTodos() {
		return getSession().createQuery("from EfdTabela4316").list();
	}

	@Transactional
	public List<EfdTabela4316> procuraNomeContendo(String query) {
		return getSession().createQuery("from EfdTabela4316 where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao","observacao", "inicioVigencia","fimVigencia"};
	}


}
