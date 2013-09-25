package dc.servicos.dao.contabilidade;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.ContabilConta;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ContabilContaDAO extends AbstractCrudDAO<ContabilConta> {

	@Override
	public Class<ContabilConta> getEntityClass() {
		// TODO Auto-generated method stub
		return ContabilConta.class;
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return null;
	}
	@Transactional
	public List<ContabilConta> listaTodos() {
		return getSession().createQuery("from ContabilConta").list();
	}
	
	@Transactional
	public List<ContabilConta> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from ContabilConta where lower(classificacao) like :q").setParameter("q", q).list();
	}
}
