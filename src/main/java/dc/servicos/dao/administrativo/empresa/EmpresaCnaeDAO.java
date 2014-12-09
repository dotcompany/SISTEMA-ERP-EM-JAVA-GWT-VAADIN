package dc.servicos.dao.administrativo.empresa;

import java.util.ArrayList;
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
		try {
			List<EmpresaCnaeEntity> auxLista = new ArrayList<EmpresaCnaeEntity>();

			String sql = "FROM :entity";
			sql = sql.replace(":entity", getEntityClass().getName());

			auxLista = super.getSession().createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Transactional
	public List<EmpresaCnaeEntity> getCnaePrincipalList() {
		try {
			List<EmpresaCnaeEntity> auxLista = new ArrayList<EmpresaCnaeEntity>();

			String sql = "FROM :entity e WHERE e.principal = '1'";
			sql = sql.replace(":entity", getEntityClass().getName());

			auxLista = super.getSession().createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "dataRegistro" };
	}

}