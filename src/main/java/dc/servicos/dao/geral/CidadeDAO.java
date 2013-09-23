package dc.servicos.dao.geral;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.Cidade;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class CidadeDAO extends AbstractCrudDAO<Cidade>{

	@Override
	public Class<Cidade> getEntityClass() {
		return Cidade.class;
	}
	
	@Transactional
	public List<Cidade> listaTodos() {
		return getSession().createQuery("from Cidade").list();
	}

	@Override
	protected String[] getDefaultSearchFields() {

		return new String[]{"nome"};
	}

}
