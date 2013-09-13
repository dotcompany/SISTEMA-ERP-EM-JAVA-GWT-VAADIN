package dc.servicos.dao.suprimentos;



import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.suprimentos.NfeLocalEntrega;
import dc.entidade.suprimentos.NotaFiscal;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Component
public class NFeLocalEntregaDAO extends AbstractCrudDAO<NfeLocalEntrega> {
	
	@Override
	protected Class<NfeLocalEntrega> getEntityClass() {
		return NfeLocalEntrega.class;
	}

	@Override
	protected String[] getDefaultSearchFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional  
	public NfeLocalEntrega buscaEntregaPorNota(NotaFiscal nota){
		
		Criteria c = getSession().createCriteria(NfeLocalEntrega.class);
		c.add(Restrictions.eq("notaFiscal",nota));
		return (NfeLocalEntrega)c.uniqueResult();
	}

}
