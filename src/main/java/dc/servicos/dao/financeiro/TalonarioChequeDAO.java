package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.TalonarioCheque;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



@Repository
@SuppressWarnings("unchecked")
public class TalonarioChequeDAO extends AbstractCrudDAO<TalonarioCheque> {

	@Override
	public Class<TalonarioCheque> getEntityClass() {
		return TalonarioCheque.class;
	}

	@Transactional
	public List<TalonarioCheque> listaTodos() {
		return getSession().createQuery("from TalonarioCheque").list();
	}

	@Override
	protected String[] getDefaultSearchFields() {

		return new String[] { "talao","statusTalao","numero" };
	}
	
	@Transactional
	public List<TalonarioCheque> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from TalonarioCheque where lower(talao) like :q").setParameter("q", q).list();
	}


}
