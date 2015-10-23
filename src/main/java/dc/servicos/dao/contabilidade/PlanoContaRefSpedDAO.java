package dc.servicos.dao.contabilidade;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.PlanoContaRefSped;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class PlanoContaRefSpedDAO extends AbstractCrudDAO<PlanoContaRefSped> {

	@Override
	public Class<PlanoContaRefSped> getEntityClass() {
		// TODO Auto-generated method stub
		return PlanoContaRefSped.class;
	}

	@Override
	public String[] getDefaultSearchFields() {
		return new String[] { "descricao" };
	}

	@Transactional
	public List<PlanoContaRefSped> listaTodos() {
		return getSession().createQuery("from PlanoContaRefSped").list();
	}

}
