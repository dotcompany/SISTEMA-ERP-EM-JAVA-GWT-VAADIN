package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.ColaboradorOs;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class ColaboradorOsDAO extends AbstractCrudDAO<ColaboradorOs> {


	@Override
	public Class<ColaboradorOs> getEntityClass() {
		return ColaboradorOs.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"descricao"};
	}
	
	@Transactional
	public List<ColaboradorOs> listaTodos() {
		return getSession().createQuery("from ColaboradorOs").list();
	}
}
