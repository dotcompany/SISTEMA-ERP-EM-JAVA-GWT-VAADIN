package dc.servicos.dao.tributario;


import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tributario.ConfiguracaoTributaria;
import dc.entidade.tributario.PISConfiguracaoTributaria;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class PISConfiguracaoTributariaDAO extends AbstractCrudDAO<PISConfiguracaoTributaria> {

	@Override
	public Class<PISConfiguracaoTributaria> getEntityClass() {
		return PISConfiguracaoTributaria.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"empresa"};
	}
	
	@Transactional
	public PISConfiguracaoTributaria buscarPorConfiguracao(ConfiguracaoTributaria configuracao){
		PISConfiguracaoTributaria pis = null;
		Criteria c = getSession().createCriteria(PISConfiguracaoTributaria.class);
		if(configuracao!=null){
			c.add(Restrictions.eq("configuracaoTributaria", configuracao));
			pis = (PISConfiguracaoTributaria)c.uniqueResult();
		}
		return pis;
	}

}

