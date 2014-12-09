
package dc.servicos.dao.empresa;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.empresa.EmpresaCnaeEntity;
import dc.entidade.geral.CnaeEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class EmpresaCnaeDAO extends AbstractCrudDAO<EmpresaCnaeEntity> {

	@Override
	public Class<EmpresaCnaeEntity> getEntityClass() {
		return EmpresaCnaeEntity.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"dataRegistro"};
	}
	
	@Transactional
	public List<EmpresaCnaeEntity> listarTodos() {
		List<EmpresaCnaeEntity> lista = null;
		try {
			String sql = "FROM EmpresaCnae";
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
			String sql = "FROM EmpresaCnae e where e.principal='1'";
			lista = super.getSession().createQuery(sql).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
