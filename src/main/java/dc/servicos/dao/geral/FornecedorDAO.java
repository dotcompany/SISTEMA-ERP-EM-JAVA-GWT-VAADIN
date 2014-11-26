package dc.servicos.dao.geral;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.FornecedorEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class FornecedorDAO extends AbstractCrudDAO<FornecedorEntity> {

	@Override
	public Class<FornecedorEntity> getEntityClass() {
		return FornecedorEntity.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "pessoa.nome" };
	}

	@Transactional
	public List<FornecedorEntity> listarTodos() {
		try {
			String sql = "FROM Fornecedor ent WHERE (1 = 1)";

			List auxLista = super.getSession().createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<FornecedorEntity>();
		}
	}

}