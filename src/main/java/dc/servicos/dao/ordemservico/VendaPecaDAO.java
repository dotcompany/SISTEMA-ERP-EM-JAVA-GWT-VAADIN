package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.VendaPeca;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class VendaPecaDAO extends AbstractCrudDAO<VendaPeca>{

	@Override
	public Class<VendaPeca> getEntityClass() {
		return VendaPeca.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"id"};
	}
	
	@Transactional
	public List<VendaPeca> listaTodos() {
		return getSession().createQuery("from VendaPeca").list();
	}
}
