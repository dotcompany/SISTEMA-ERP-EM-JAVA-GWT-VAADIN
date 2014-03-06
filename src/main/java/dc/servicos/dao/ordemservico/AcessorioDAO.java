package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.Acessorio;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class AcessorioDAO extends AbstractCrudDAO<Acessorio>{

	@Override
	public Class<Acessorio> getEntityClass() {
		return Acessorio.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	}
	
	@Transactional
	public List<Acessorio> listaTodos() {
		return getSession().createQuery("from Acessorio").list();
	}
}
