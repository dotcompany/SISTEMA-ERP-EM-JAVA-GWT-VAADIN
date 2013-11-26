package dc.servicos.dao.folhapagamento;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import dc.entidade.folhapagamento.VendedorEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class VendedorDAO extends AbstractCrudDAO<VendedorEntity> {

	@Override
	public Class<VendedorEntity> getEntityClass() {
		return VendedorEntity.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"dataRegistro"};
	}
	
	@Transactional
	public List<VendedorEntity> listarTodos() {
		List<VendedorEntity> lista = null;
		try {
			String sql = "FROM VendedorEntity";
			lista = super.getSession().createQuery(sql).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
