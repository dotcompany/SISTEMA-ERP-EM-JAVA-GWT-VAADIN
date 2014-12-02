package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.CboEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 *
 * @author Wesley Jr
 *
 */

@Repository
@SuppressWarnings("unchecked")
public class CBODAO extends AbstractCrudDAO<CboEntity> {

	@Override
	public Class<CboEntity> getEntityClass() {
		return CboEntity.class;
	}

	@Transactional
	public List<CboEntity> listaTodos() {
		return getSession().createQuery("from CBO").list();
	}

	@Transactional
	public CboEntity find(String codigo) throws Exception {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.codigo = :codigo";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("codigo", codigo);

			CboEntity entity = (CboEntity) query.uniqueResult();

			if (entity == null) {
				entity = new CboEntity();
			}

			return entity;
		} catch (Exception e) {
			return new CboEntity();
		}
	}

	@Transactional
	public List<CboEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from CBO where nome like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "codigo", "nome", "observacao" };
	}

}