package dc.servicos.dao.folhapagamento.ausencia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.folhapagamento.ausencia.FeriasPeriodoAquisitivoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class FeriasPeriodoAquisitivoDAO extends
		AbstractCrudDAO<FeriasPeriodoAquisitivoEntity> {

	@Override
	protected Class<FeriasPeriodoAquisitivoEntity> getEntityClass() {
		return FeriasPeriodoAquisitivoEntity.class;
	}

	@Transactional
	public List<FeriasPeriodoAquisitivoEntity> listarTodos() {
		try {
			String sql = "FROM FeriasPeriodoAquisitivoEntity ent WHERE (1 = 1)";

			List<FeriasPeriodoAquisitivoEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<FeriasPeriodoAquisitivoEntity>();
		}
	}

	@Transactional
	public List<FeriasPeriodoAquisitivoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM FeriasPeriodoAquisitivoEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<FeriasPeriodoAquisitivoEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<FeriasPeriodoAquisitivoEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Número", "Bem", "Seguradora" };
	}

}