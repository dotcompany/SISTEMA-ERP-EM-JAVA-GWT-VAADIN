package dc.servicos.dao.ordemservico;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.LaudoTecnico;
import dc.entidade.ordemservico.OrdemServico;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class LaudoTecnicoDAO extends AbstractCrudDAO<LaudoTecnico>{

	@Override
	public Class<LaudoTecnico> getEntityClass() {
		return LaudoTecnico.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"id"};
	}
	
	@Transactional
	public List<LaudoTecnico> listaTodos() {
		return getSession().createQuery("from LaudoTecnico").list();
	}
	
	@Transactional  
	public LaudoTecnico buscaLaudoTecnico(OrdemServico ordemServico){
		
		Criteria c = getSession().createCriteria(LaudoTecnico.class);
		c.add(Restrictions.eq("ordemServico",ordemServico));
		return (LaudoTecnico)c.uniqueResult();
	}
}
