package dc.servicos.dao.suprimentos;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import dc.entidade.suprimentos.NfeLocalRetirada;
import dc.entidade.suprimentos.NotaFiscal;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Component
public class NFeLocalRetiradaDAO extends AbstractCrudDAO<NfeLocalRetirada> {
	
	@Override
	protected Class<NfeLocalRetirada> getEntityClass() {
		return NfeLocalRetirada.class;
	}

	@Override
	protected String[] getDefaultSearchFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional  
	public NfeLocalRetirada buscaRetiradaPorNota(NotaFiscal nota){
		
		Criteria c = getSession().createCriteria(NfeLocalRetirada.class);
		c.add(Restrictions.eq("notaFiscal",nota));
		return (NfeLocalRetirada)c.uniqueResult();
	}

}

