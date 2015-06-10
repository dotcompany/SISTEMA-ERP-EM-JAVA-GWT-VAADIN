package dc.model.dao.ordemservico;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.ordemservico.EntradaServicoEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
@Repository
public class EntradaServicoDAOImpl extends AbstractCrudDAO<EntradaServicoEntity> implements
EntradaServicoDAO<EntradaServicoEntity> {

	private static Logger logger = Logger.getLogger(EntradaServicoDAOImpl.class);

	@Override
	public Class<EntradaServicoEntity> getEntityClass() {
		return EntradaServicoEntity.class;
	}

	public List<EntradaServicoEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<EntradaServicoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<EntradaServicoEntity> query(String value) {
			return null;

	}

	public String[] getDefaultSearchFields() {
		return new String[] { "descricao" };
	}

	@Override
	public List<EntradaServicoEntity> procuraNomeContendo(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<EntradaServicoEntity> findByOrdemServico(OrdemServicoEntity ordemServico) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.ordemServico.id = :id";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("id", ordemServico.getId());

			List<EntradaServicoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}
}