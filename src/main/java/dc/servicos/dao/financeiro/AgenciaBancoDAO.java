package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dc.entidade.financeiro.AgenciaBancoEntity;
import dc.entidade.financeiro.BancoEntity;
import dc.entidade.geral.diverso.UfEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;
import dc.servicos.dao.geral.UfDAO;

@Repository
public class AgenciaBancoDAO extends AbstractCrudDAO<AgenciaBancoEntity> {

	/**
	 * DAOS
	 */

	@Autowired
	private UfDAO ufDAO;

	@Autowired
	private BancoDAO bancoDAO;

	/**
	 * 
	 */

	@Override
	public Class<AgenciaBancoEntity> getEntityClass() {
		return AgenciaBancoEntity.class;
	}

	// @Transactional
	public List<AgenciaBancoEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());
			// sql = sql.replace("-", this.getEntityClass().getSimpleName()
			// + "(ent.id, ent.nome, ent.sigla)");

			return getSession().createQuery(sql).list();
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	// @Transactional
	public List<AgenciaBancoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			return getSession().createQuery(sql)
					.setParameter("q", "%" + query + "%").list();
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	// @Transactional
	public List<UfEntity> query(String q) {
		String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
		sql = sql.replace("#", getEntityClass().getName());

		q = "%" + q.toLowerCase() + "%";

		return getSession().createQuery(sql).setParameter("q", q).list();
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "nome", "logradouro" };
	}

	// @Transactional
	public void saveOrUpdateAgenciaBanco(AgenciaBancoEntity entity)
			throws Exception {
		try {
			BancoEntity banco = this.bancoDAO.find(entity.getBanco().getId());
			UfEntity uf = this.ufDAO.find(entity.getUf().getId());

			entity.setBanco(banco);
			entity.setUf(uf);

			super.saveOrUpdate(entity);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<AgenciaBancoEntity> listAll() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());
			// sql = sql.replace("-", this.getEntityClass().getSimpleName()
			// + "(ent.id, ent.nome, ent.sigla)");

			return getSession().createQuery(sql).list();
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}