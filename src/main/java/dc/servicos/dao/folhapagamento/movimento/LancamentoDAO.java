package dc.servicos.dao.folhapagamento.movimento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.folhapagamento.movimento.LancamentoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class LancamentoDAO extends AbstractCrudDAO<LancamentoEntity> {

	@Override
	protected Class<LancamentoEntity> getEntityClass() {
		return LancamentoEntity.class;
	}

	@Transactional
	public List<LancamentoEntity> listarTodos() {
		try {
			String sql = "FROM LancamentoEntity ent WHERE (1 = 1)";

			List<LancamentoEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<LancamentoEntity>();
		}
	}

	@Transactional
	public List<LancamentoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM LancamentoEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<LancamentoEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<LancamentoEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Número", "Bem", "Seguradora" };
	}

}