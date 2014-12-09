package dc.servicos.dao.administrativo.empresa;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.administrativo.empresa.EmpresaCnaeEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class EmpresaCnaeDAO extends AbstractCrudDAO<EmpresaCnaeEntity> {

	@Override
	public Class<EmpresaCnaeEntity> getEntityClass() {
		return EmpresaCnaeEntity.class;
	}

	@Transactional
	public List<EmpresaCnaeEntity> listarTodos() {
		List<EmpresaCnaeEntity> lista = null;
		try {
			String sql = "FROM :entity";
			sql = sql.replace(":entity", getEntityClass().getName());

			lista = super.getSession().createQuery(sql).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Transactional
	public List<EmpresaCnaeEntity> listarPrincipais() {
		List<EmpresaCnaeEntity> lista = null;

		try {
			String sql = "FROM :entity e WHERE e.principal = '1'";
			sql = sql.replace(":entity", getEntityClass().getName());

			lista = super.getSession().createQuery(sql).list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "dataRegistro" };
	}

}