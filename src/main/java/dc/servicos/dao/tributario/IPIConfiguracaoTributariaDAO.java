package dc.servicos.dao.tributario;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import dc.entidade.tributario.ConfiguracaoTributaria;
import dc.entidade.tributario.IpiConfiguracaoTributariaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class IPIConfiguracaoTributariaDAO extends AbstractCrudDAO<IpiConfiguracaoTributariaEntity> {

	@Override
	public Class<IpiConfiguracaoTributariaEntity> getEntityClass() {
		return IpiConfiguracaoTributariaEntity.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"empresa"};
	}
	
	@Transactional
	public IpiConfiguracaoTributariaEntity buscarPorConfiguracao(ConfiguracaoTributaria configuracao){
		IpiConfiguracaoTributariaEntity ipi = null;
		Criteria c = getSession().createCriteria(IpiConfiguracaoTributariaEntity.class);
		if(configuracao!=null){
			c.add(Restrictions.eq("configuracaoTributaria", configuracao));
			ipi = (IpiConfiguracaoTributariaEntity)c.uniqueResult();
		}
		return ipi;
	}

}


