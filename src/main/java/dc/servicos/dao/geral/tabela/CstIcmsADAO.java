package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.CstIcmsaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class CstIcmsADAO extends AbstractCrudDAO<CstIcmsaEntity>{

	@Override
	public Class<CstIcmsaEntity> getEntityClass() {
		return CstIcmsaEntity.class;
	}
	
	@Transactional
	public List<CstIcmsaEntity> listaTodos() {
		return getSession().createQuery("from CstIcmsA").list();
	}

	@Transactional
	public List<CstIcmsaEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from CstIcmsA where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao", "observacao"};
	}


}
