package dc.servicos.dao.suprimentos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.suprimentos.NfeDuplicata;
import dc.entidade.suprimentos.NotaFiscal;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Component
public class DuplicataDAO extends AbstractCrudDAO<NfeDuplicata> {


	@Override
	protected Class<NfeDuplicata> getEntityClass() {
		return NfeDuplicata.class;
	}

	@Override
	protected String[] getDefaultSearchFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional  
	public List<NfeDuplicata> buscarDuplicatasPorNota(NotaFiscal nota){
		List<NfeDuplicata> lista = new ArrayList<>();
		Criteria c = getSession().createCriteria(NfeDuplicata.class);
		c.add(Restrictions.eq("notaFiscal",nota));
		lista = c.list();
		return lista;
	}


}
