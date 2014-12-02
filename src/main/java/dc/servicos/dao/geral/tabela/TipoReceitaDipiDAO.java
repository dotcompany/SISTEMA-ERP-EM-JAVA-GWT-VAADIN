package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.CstCofins;
import dc.entidade.geral.tabela.CstPis;
import dc.entidade.geral.tabela.TipoReceitaDipi;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class TipoReceitaDipiDAO extends AbstractCrudDAO<TipoReceitaDipi>{

	@Override
	public Class<TipoReceitaDipi> getEntityClass() {
		return TipoReceitaDipi.class;
	}
	
	

	@Transactional
	public List<CstCofins> procuraNomeContendo(String query) {
		return getSession().createQuery("from CstCofins where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao", "observacao"};
	}
	
	@Transactional
	public TipoReceitaDipi procuraPorCodigo(String codigo){
		TipoReceitaDipi tipo = null;
		Criteria c = getSession().createCriteria(TipoReceitaDipi.class);
		if(codigo!=null && !(codigo.isEmpty())){
			c.add(Restrictions.eq("codigo", codigo));
		}
		tipo = (TipoReceitaDipi)c.uniqueResult();
		return tipo;
	}


}
