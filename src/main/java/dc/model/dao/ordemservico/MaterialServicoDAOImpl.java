package dc.model.dao.ordemservico;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.ordemservico.MaterialServicoEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
@Repository
public class MaterialServicoDAOImpl extends AbstractCrudDAO<MaterialServicoEntity> implements
MaterialServicoDAO<MaterialServicoEntity> {

	private static Logger logger = Logger.getLogger(MaterialServicoDAOImpl.class);

	@Override
	public Class<MaterialServicoEntity> getEntityClass() {
		return MaterialServicoEntity.class;
	}

	public List<MaterialServicoEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<MaterialServicoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<MaterialServicoEntity> query(String value) {
			return null;

	}

	public String[] getDefaultSearchFields() {
		return new String[] { "descricao" };
	}

	@Override
	public List<MaterialServicoEntity> procuraNomeContendo(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MaterialServicoEntity> findByOrdemServico(OrdemServicoEntity ordemServico) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.ordemServico.id = :id";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("id", ordemServico.getId());

			List<MaterialServicoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}