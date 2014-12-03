package dc.servicos.dao.geral.diverso;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.diverso.PaisEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class PaisDAO extends AbstractCrudDAO<PaisEntity> {

	@Override
	public Class<PaisEntity> getEntityClass() {
		return PaisEntity.class;
	}

	@Transactional
	public List<PaisEntity> listaTodos() {
		try {
			String sql = "FROM PaisEntity ent WHERE (1 = 1)";

			List<PaisEntity> auxLista = super.getSession().createQuery(sql)
					.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PaisEntity>();
		}
	}

	@Transactional
	public List<PaisEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM PaisEntity ent WHERE (1 = 1) AND ent.nomeEn LIKE :q";

			List<PaisEntity> auxLista = super.getSession().createQuery(sql)
					.setParameter("q", "%" + query + "%").list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PaisEntity>();
		}
	}

	@Transactional
	public List<PaisEntity> query(String q) {
		try {
			String sql = "FROM Pais ent WHERE (1 = 1) AND ent.nomeEn LIKE :q";

			List<PaisEntity> auxLista = super.getSession().createQuery(sql)
					.setParameter("q", "%" + q + "%").list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PaisEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "nomeIngles", "nomePtbr", "sigla2", "sigla3" };
	}

}