package dc.servicos.dao.contabilidade.demonstrativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.folhapagamento.inss.InssEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class DfcDAO extends AbstractCrudDAO<InssEntity> {

	@Override
	public Class<InssEntity> getEntityClass() {
		return InssEntity.class;
	}

	@Transactional
	public List<InssEntity> listarTodos() {
		try {
			String sql = "FROM InssEntity ent WHERE (1 = 1)";

			List<InssEntity> auxLista = super.getSession().createQuery(sql)
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<InssEntity>();
		}
	}

	@Transactional
	public List<InssEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM InssEntity ent WHERE (1 = 1) AND ent.competencia LIKE :q";

			List<InssEntity> auxLista = super.getSession().createQuery(sql)
					.setParameter("q", "%" + query + "%").list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<InssEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Competência" };
	}

}