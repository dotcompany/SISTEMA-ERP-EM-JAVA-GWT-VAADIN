package dc.servicos.dao.suprimentos.estoque;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.suprimentos.estoque.EstoqueContagemEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class EstoqueContagemDAO extends AbstractCrudDAO<EstoqueContagemEntity> {

	@Override
	public Class<EstoqueContagemEntity> getEntityClass() {
		return EstoqueContagemEntity.class;
	}

	/*
	 * @Transactional public List<AgenciaBanco> listAgencias(Banco banco) {
	 * return
	 * getSession().createQuery("from AgenciaBanco where banco.id = :bid")
	 * .setParameter("bid", banco.getId()).list(); }
	 */

	@Transactional
	public List<EstoqueContagemEntity> listarTodos() {
		try {
			String sql = "FROM EstoqueContagemEntity ent WHERE (1 = 1)";

			List<EstoqueContagemEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<EstoqueContagemEntity>();
		}
	}

	@Transactional
	public List<EstoqueContagemEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM EstoqueContagemEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<EstoqueContagemEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<EstoqueContagemEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "estoque_atualizado" };
	}

}