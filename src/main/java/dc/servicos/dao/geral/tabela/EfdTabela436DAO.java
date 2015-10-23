package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.EfdTabela436Entity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class EfdTabela436DAO extends AbstractCrudDAO<EfdTabela436Entity> {

	@Override
	public Class<EfdTabela436Entity> getEntityClass() {
		return EfdTabela436Entity.class;
	}

	@Transactional
	public List<EfdTabela436Entity> listaTodos() {
		return getSession().createQuery("from EfdTabela436").list();
	}

	@Transactional
	public List<EfdTabela436Entity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from EfdTabela436 where descricao like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "codigo", "descricao" };
	}

}