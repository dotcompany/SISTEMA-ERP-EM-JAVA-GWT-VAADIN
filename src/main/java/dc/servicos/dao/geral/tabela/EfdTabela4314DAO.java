package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.EfdTabela4314;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;


/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class EfdTabela4314DAO extends AbstractCrudDAO<EfdTabela4314>{

	@Override
	public Class<EfdTabela4314> getEntityClass() {
		return EfdTabela4314.class;
	}
	
	@Transactional
	public List<EfdTabela4314> listaTodos() {
		return getSession().createQuery("from EfdTabela4314").list();
	}

	@Transactional
	public List<EfdTabela4314> procuraNomeContendo(String query) {
		return getSession().createQuery("from EfdTabela4314 where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao","observacao", "inicioVigencia","fimVigencia"};
	}


}
