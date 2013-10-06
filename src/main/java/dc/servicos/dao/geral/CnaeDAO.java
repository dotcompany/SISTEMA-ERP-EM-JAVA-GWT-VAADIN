package dc.servicos.dao.geral;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.Cnae;
import dc.entidade.geral.Fornecedor;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class CnaeDAO extends AbstractCrudDAO<Cnae> {

	@Override
	public Class<Cnae> getEntityClass() {
		return Cnae.class;
	}



	@Transactional
	public List<Cnae> listarTodos() {
		List<Cnae> lista = null;
		try {
			String sql = "FROM Cnae";
			lista = super.getSession().createQuery(sql).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}



	@Override
	protected String[] getDefaultSearchFields() {
		// TODO Auto-generated method stub
		return null;
	}

}