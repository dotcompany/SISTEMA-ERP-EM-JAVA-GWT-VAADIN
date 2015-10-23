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
			String sql = "SELECT ent FROM InformacaoGeralEntity ent INNER JOIN ent.ordemServico os INNER JOIN FETCH ent.carro ca "+
			"LEFT JOIN FETCH ent.statusOs st LEFT JOIN FETCH ent.situacaoServico ss LEFT JOIN FETCH ent.tipoServico tp " +
			"LEFT JOIN FETCH ent.atendente at LEFT JOIN FETCH ent.tipoPagamento tp "+
			"WHERE (1 = 1) ";

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

	public InformacaoGeralEntity findByOrdemServico(OrdemServicoEntity ordemServico) {
		try {

			String sql = "SELECT ent FROM InformacaoGeralEntity ent INNER JOIN ent.ordemServico os INNER JOIN FETCH ent.carro ca "+
			"LEFT JOIN FETCH ent.statusOs st LEFT JOIN FETCH ent.situacaoServico ss LEFT JOIN FETCH ent.tipoServico tp " +
			"LEFT JOIN FETCH ent.atendente at LEFT JOIN FETCH ent.tipoPagamento tp "+
			"WHERE (1 = 1) AND ent.ordemServico.id =:id ";

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