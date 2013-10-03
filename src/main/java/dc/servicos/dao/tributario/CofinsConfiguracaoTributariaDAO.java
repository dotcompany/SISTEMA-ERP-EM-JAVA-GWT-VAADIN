package dc.servicos.dao.tributario;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import dc.entidade.tributario.CofinsConfiguracaoTributaria;
import dc.entidade.tributario.ConfiguracaoTributaria;
import dc.entidade.tributario.PISConfiguracaoTributaria;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class CofinsConfiguracaoTributariaDAO extends AbstractCrudDAO<CofinsConfiguracaoTributaria> {

	@Override
	public Class<CofinsConfiguracaoTributaria> getEntityClass() {
		return CofinsConfiguracaoTributaria.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"empresa"};
	}
	
	@Transactional
	public CofinsConfiguracaoTributaria buscarPorConfiguracao(ConfiguracaoTributaria configuracao){
		CofinsConfiguracaoTributaria cofins = null;
		Criteria c = getSession().createCriteria(CofinsConfiguracaoTributaria.class);
		if(configuracao!=null){
			c.add(Restrictions.eq("configuracaoTributaria", configuracao));
			cofins = (CofinsConfiguracaoTributaria)c.uniqueResult();
		}
		return cofins;
	}

}


