package dc.servicos.dao.tributario;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import dc.entidade.tributario.ConfiguracaoTributaria;
import dc.entidade.tributario.IPIConfiguracaoTributaria;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class IPIConfiguracaoTributariaDAO extends AbstractCrudDAO<IPIConfiguracaoTributaria> {

	@Override
	public Class<IPIConfiguracaoTributaria> getEntityClass() {
		return IPIConfiguracaoTributaria.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"empresa"};
	}
	
	@Transactional
	public IPIConfiguracaoTributaria buscarPorConfiguracao(ConfiguracaoTributaria configuracao){
		IPIConfiguracaoTributaria ipi = null;
		Criteria c = getSession().createCriteria(IPIConfiguracaoTributaria.class);
		if(configuracao!=null){
			c.add(Restrictions.eq("configuracaoTributaria", configuracao));
			ipi = (IPIConfiguracaoTributaria)c.uniqueResult();
		}
		return ipi;
	}

}


