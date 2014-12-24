package dc.servicos.dao.ordemservico;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.TipoEfetivacaoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class TipoEfetivacaoDAO extends AbstractCrudDAO<TipoEfetivacaoEntity>{

	@Override
	public Class<TipoEfetivacaoEntity> getEntityClass() {
		return TipoEfetivacaoEntity.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"descricao"};
	} 
	
	@Transactional
	public List<TipoEfetivacaoEntity> listaTodos() {
		return getSession().createQuery("from TipoEfetivacaoEntity").list();
	}
	
	@Transactional  
	public TipoEfetivacaoEntity findByCodigo(Integer codigo){
		
		Criteria c = getSession().createCriteria(TipoEfetivacaoEntity.class);
		c.add(Restrictions.eq("codigo",codigo));
		return (TipoEfetivacaoEntity)c.uniqueResult();
	}
}


