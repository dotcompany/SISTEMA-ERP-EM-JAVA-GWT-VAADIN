package dc.servicos.dao.framework.geral;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.framework.Empresa;
import dc.entidade.framework.Seguimento;

@Repository
public class SeguimentoDAO extends AbstractCrudDAO<Seguimento> {

	@Override
	public Class<Seguimento> getEntityClass() {
		return Seguimento.class;
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao" };
	}

	@Transactional
	public List<Seguimento> listaPorEmpresa(Empresa empresa) {
		// select a.firstName, a.lastName from Book b join b.authors a where
		// b.id = :id
		return getSession().createQuery("select s from Empresa e join").setParameter("emp", empresa).list();
	}

}
