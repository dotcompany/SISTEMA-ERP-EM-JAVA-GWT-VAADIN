package dc.servicos.dao.contabilidade;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.PlanoConta;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class PlanoContaDAO extends AbstractCrudDAO<PlanoConta> {

	@Override
	public Class<PlanoConta> getEntityClass() {
		// TODO Auto-generated method stub
		return PlanoConta.class;
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] { "nome" };
	}

	@Transactional
	public List<PlanoConta> listaTodos() {
		return getSession().createQuery("from PlanoConta").list();
	}

}
