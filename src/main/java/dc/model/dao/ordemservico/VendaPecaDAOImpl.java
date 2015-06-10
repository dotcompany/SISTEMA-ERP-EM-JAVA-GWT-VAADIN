package dc.model.dao.ordemservico;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.ordemservico.VendaPecaEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
@Repository
public class VendaPecaDAOImpl extends AbstractCrudDAO<VendaPecaEntity> implements
VendaPecaDAO<VendaPecaEntity> {

	private static Logger logger = Logger.getLogger(VendaPecaDAOImpl.class);

	@Override
	public Class<VendaPecaEntity> getEntityClass() {
		return VendaPecaEntity.class;
	}

	public List<VendaPecaEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<VendaPecaEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<VendaPecaEntity> query(String value) {
			return null;

	}

	public String[] getDefaultSearchFields() {
		return new String[] { "descricao" };
	}

	@Override
	public List<VendaPecaEntity> procuraNomeContendo(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<VendaPecaEntity> findByOrdemServico(OrdemServicoEntity ordemServico) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.ordemServico.id = :id";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("id", ordemServico.getId());

			List<VendaPecaEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}