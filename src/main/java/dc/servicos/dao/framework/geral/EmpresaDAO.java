package dc.servicos.dao.framework.geral;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.framework.EmpresaEntity;
import dc.entidade.geral.pessoal.CargoEntity;

@Repository
public class EmpresaDAO extends AbstractCrudDAO<EmpresaEntity> {

	private static String MATRIZ = "1";

	@Override
	public Class<EmpresaEntity> getEntityClass() {
		return EmpresaEntity.class;
	}

	@Transactional
	public List<EmpresaEntity> listaTodos() {
		try {
			String sql = "SELECT new EmpresaEntity(ent.id, ent.nomeFantasia) FROM EmpresaEntity ent";

			List<EmpresaEntity> auxLista = getSession().createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Transactional
	public List<EmpresaEntity> procuraNomeContendo(String query) {
		try {
			String sql = "SELECT new EmpresaEntity(ent.id, ent.nomeFantasia)"
					+ " FROM EmpresaEntity WHERE nomeFantasia LIKE :q";

			List<EmpresaEntity> auxLista = getSession().createQuery(sql)
					.setParameter("q", "%" + query + "%").list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] { "nomeFantasia", "razaoSocial" };
	}

	@Transactional
	public List<CargoEntity> query(String q) {
		try {
			String sql = "SELECT new EmpresaEntity(ent.id, ent.nomeFantasia)"
					+ " FROM EmpresaEntity WHERE lower(nomeFantasia) LIKE :q";

			q = "%" + q.toLowerCase() + "%";

			return getSession().createQuery(sql).setParameter("q", q).list();
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	/**
	 * *******************************************************
	 */

	@Transactional
	public List<EmpresaEntity> getListEmpresa() {
		try {
			String sql = "SELECT new EmpresaEntity(ent.id, ent.nomeFantasia) FROM EmpresaEntity ent";

			List<EmpresaEntity> auxLista = getSession().createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Transactional
	public EmpresaEntity findEmpresa(Integer id) {
		try {
			String sql = "SELECT ent.empresa FROM TipoAquisicaoEntity ent"
					+ " WHERE (1 = 1) AND ent.id = :id";

			EmpresaEntity ent = (EmpresaEntity) getSession().createQuery(sql)
					.setParameter("id", id).uniqueResult();

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Transactional
	public EmpresaEntity findByCNPJ(String cnpj) {
		try {
			List<EmpresaEntity> auxLista = getSession()
					.createCriteria(EmpresaEntity.class)
					.add(Restrictions.eq("cnpj", cnpj)).list();

			if (!auxLista.isEmpty()) {
				return auxLista.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Transactional
	public List<EmpresaEntity> getListEmpresaMatriz() {
		try {
			String sql = "SELECT new EmpresaEntity(ent.id, ent.nomeFantasia)"
					+ " FROM EmpresaEntity ent WHERE tipo = :tipo";

			List<EmpresaEntity> auxLista = getSession().createQuery(sql)
					.setParameter("tipo", MATRIZ).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Transactional
	public EmpresaEntity findEmpresaByContaEmpresa(Integer contaEmpresaId) {
		try {
			String sql = "SELECT new EmpresaEntity(ent.id, ent.nomeFantasia)"
					+ " FROM EmpresaEntity WHERE conta.id = :c";

			EmpresaEntity ent = (EmpresaEntity) getSession().createQuery(sql)
					.setParameter("c", contaEmpresaId).uniqueResult();

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}