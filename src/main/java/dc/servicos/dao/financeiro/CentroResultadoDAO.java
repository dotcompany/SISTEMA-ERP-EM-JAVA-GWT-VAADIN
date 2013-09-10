package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.CentroResultado;
import dc.entidade.framework.FmModulo;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



@Repository
@SuppressWarnings("unchecked")
public class CentroResultadoDAO extends AbstractCrudDAO<CentroResultado>{

	@Override
	protected Class<CentroResultado> getEntityClass() {
		return CentroResultado.class;
	}
	
	
	@Transactional
	public List<CentroResultado> listCentroResultado(CentroResultado centroresultado) {
		return getSession().createQuery("from CentroResultado where centroresultado.id = :bid").setParameter("bid", centroresultado.getId()).list();
	}


	@Transactional
	public List<CentroResultado> listaTodos() {
		return getSession().createQuery("from CentroResultado").list();
	}

	@Transactional
	public List<CentroResultado> procuraNomeContendo(String query) {
		return getSession().createQuery("from CentroResultado where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"descricao", "sofre_rateio","percentual_rateio"};
	}	
}
