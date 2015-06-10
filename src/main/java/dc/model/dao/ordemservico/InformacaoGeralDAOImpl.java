package dc.model.dao.ordemservico;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.ordemservico.InformacaoGeralEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
@Repository
public class InformacaoGeralDAOImpl extends AbstractCrudDAO<InformacaoGeralEntity> implements
InformacaoGeralDAO<InformacaoGeralEntity> {

	private static Logger logger = Logger.getLogger(ServicoOsDAOImpl.class);

	@Override
	public Class<InformacaoGeralEntity> getEntityClass() {
		return InformacaoGeralEntity.class;
	}

	public List<InformacaoGeralEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<InformacaoGeralEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<InformacaoGeralEntity> query(String value) {
			return null;

	}

	public String[] getDefaultSearchFields() {
		return new String[] { "descricao" };
	}

	@Override
	public List<InformacaoGeralEntity> procuraNomeContendo(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	public InformacaoGeralEntity findByOrdemServico(OrdemServicoEntity ordemServico) {
		try {
			String sql = "FROM # ent INNER JOIN ent.ordemServico as o WHERE (1 = 1) AND ent.ordemServico.id = :id";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("id", ordemServico.getId());

			InformacaoGeralEntity ent = (InformacaoGeralEntity) query.uniqueResult();

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}