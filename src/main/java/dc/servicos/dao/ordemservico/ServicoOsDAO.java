package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.ServicoOs;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class ServicoOsDAO extends AbstractCrudDAO<ServicoOs> {


	@Override
	public Class<ServicoOs> getEntityClass() {
		return ServicoOs.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"descricao"};
	}
	
	@Transactional
	public List<ServicoOs> listaTodos() {
		return getSession().createQuery("from ServicoOs").list();
	}
}
