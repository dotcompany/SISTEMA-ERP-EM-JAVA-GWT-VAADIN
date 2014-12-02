package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.EfdTabela4315;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;


/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class EfdTabela4315DAO extends AbstractCrudDAO<EfdTabela4315>{

	@Override
	public Class<EfdTabela4315> getEntityClass() {
		return EfdTabela4315.class;
	}
	
	@Transactional
	public List<EfdTabela4315> listaTodos() {
		return getSession().createQuery("from EfdTabela4315").list();
	}

	@Transactional
	public List<EfdTabela4315> procuraNomeContendo(String query) {
		return getSession().createQuery("from EfdTabela4315 where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao","observacao", "inicioVigencia","fimVigencia"};
	}


}
