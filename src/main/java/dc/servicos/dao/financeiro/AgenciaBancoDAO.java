package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.AgenciaBancoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class AgenciaBancoDAO extends AbstractCrudDAO<AgenciaBancoEntity> {

	@Override
	public Class<AgenciaBancoEntity> getEntityClass() {
		return AgenciaBancoEntity.class;
	}

	@Transactional
	public List<AgenciaBancoEntity> listaTodos() {
		return getSession().createQuery("from AgenciaBanco").list();
	}

	@Transactional
	public List<AgenciaBancoEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from AgenciaBanco where nome like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "nome", "logradouro" };
	}

	@Transactional
	public List<AgenciaBancoEntity> query(String q) {
		q = "%" + q.toLowerCase() + "%";
		return getSession()
				.createQuery("from AgenciaBanco where lower(nome) like :q")
				.setParameter("q", q).list();
	}

}