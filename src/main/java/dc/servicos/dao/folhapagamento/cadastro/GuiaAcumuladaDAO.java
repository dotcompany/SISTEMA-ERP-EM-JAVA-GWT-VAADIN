package dc.servicos.dao.folhapagamento.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.folhapagamento.cadastro.GuiaAcumuladaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class GuiaAcumuladaDAO extends AbstractCrudDAO<GuiaAcumuladaEntity> {

	@Override
	protected Class<GuiaAcumuladaEntity> getEntityClass() {
		return GuiaAcumuladaEntity.class;
	}

	@Transactional
	public List<GuiaAcumuladaEntity> listarTodos() {
		try {
			String sql = "FROM GuiaAcumuladaEntity ent WHERE (1 = 1)";

			List<GuiaAcumuladaEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<GuiaAcumuladaEntity>();
		}
	}

	@Transactional
	public List<GuiaAcumuladaEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM GuiaAcumuladaEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<GuiaAcumuladaEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<GuiaAcumuladaEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Número", "Bem", "Seguradora" };
	}

}