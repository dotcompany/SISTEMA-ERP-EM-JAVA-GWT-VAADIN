package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.SindicatoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class SindicatoDAO extends AbstractCrudDAO<SindicatoEntity> {

	@Override
	public Class<SindicatoEntity> getEntityClass() {
		return SindicatoEntity.class;
	}

	@Transactional
	public List<SindicatoEntity> listaTodos() {
		return getSession().createQuery("from SindicatoEntity").list();
	}

	@Transactional
	public List<SindicatoEntity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from SindicatoEntity where nome like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "nome", "logradouro" };
	}

	@Transactional
	public List<SindicatoEntity> query(String q) {
		q = "%" + q.toLowerCase() + "%";

		return getSession()
				.createQuery("from Sindicato where lower(nome) like :q")
				.setParameter("q", q).list();
	}

}