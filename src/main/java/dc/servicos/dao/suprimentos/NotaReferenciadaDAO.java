package dc.servicos.dao.suprimentos;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import dc.entidade.suprimentos.NotaFiscal;
import dc.entidade.suprimentos.NotaReferenciada;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Component
public class NotaReferenciadaDAO 
extends AbstractCrudDAO<NotaReferenciada>{
	
	@Override
	public Class<NotaReferenciada> getEntityClass() {
		return NotaReferenciada.class;
	}

	@Override
	protected String[] getDefaultSearchFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional  
	public List<NotaReferenciada> buscaNotasReferenciadas(NotaFiscal nota){
		List<NotaReferenciada> lista = new ArrayList<>();
		Criteria c = getSession().createCriteria(NotaReferenciada.class);
		c.add(Restrictions.eq("notaFiscal",nota));
		lista = c.list();
		return lista;
	}

}
