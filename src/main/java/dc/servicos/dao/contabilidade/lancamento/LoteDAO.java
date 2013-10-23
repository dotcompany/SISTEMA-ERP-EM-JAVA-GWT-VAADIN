package dc.servicos.dao.contabilidade.lancamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.lancamento.LoteEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class LoteDAO extends AbstractCrudDAO<LoteEntity> {

	@Override
	public Class<LoteEntity> getEntityClass() {
		return LoteEntity.class;
	}

	@Transactional
	public List<LoteEntity> listarTodos() {
		try {
			String sql = "FROM LoteEntity ent WHERE (1 = 1)";

			List<LoteEntity> auxLista = super.getSession().createQuery(sql)
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<LoteEntity>();
		}
	}

	@Transactional
	public List<LoteEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM LoteEntity ent WHERE (1 = 1) AND ent.competencia LIKE :q";

			List<LoteEntity> auxLista = super.getSession().createQuery(sql)
					.setParameter("q", "%" + query + "%").list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<LoteEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Competência" };
	}

}