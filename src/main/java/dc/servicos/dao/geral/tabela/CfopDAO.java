package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.Cfop;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class CfopDAO extends AbstractCrudDAO<Cfop>{

	@Override
	public Class<Cfop> getEntityClass() {
		return Cfop.class;
	}
	
	@Transactional
	public List<Cfop> listaTodos() {
		return getSession().createQuery("from Cfop").list();
	}

	@Transactional
	public List<Cfop> procuraNomeContendo(String query) {
		return getSession().createQuery("from Cfop where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"descricao","aplicacao"};
	}


}
