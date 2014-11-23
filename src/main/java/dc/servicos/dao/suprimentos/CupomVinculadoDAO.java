package dc.servicos.dao.suprimentos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.suprimentos.CupomVinculado;
import dc.entidade.suprimentos.estoque.NotaFiscal;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Component
public class CupomVinculadoDAO extends AbstractCrudDAO<CupomVinculado> {

	@Override
	public Class<CupomVinculado> getEntityClass() {
		return CupomVinculado.class;
	}

	@Override
	protected String[] getDefaultSearchFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<CupomVinculado> buscaCuponsPorNota(NotaFiscal nota) {
		List<CupomVinculado> lista = new ArrayList<>();
		Criteria c = getSession().createCriteria(CupomVinculado.class);
		c.add(Restrictions.eq("notaFiscal", nota));
		lista = c.list();

		return lista;
	}

}