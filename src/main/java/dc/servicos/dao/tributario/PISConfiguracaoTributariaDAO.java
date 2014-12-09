package dc.servicos.dao.tributario;


import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tributario.ConfiguracaoTributaria;
import dc.entidade.tributario.PisConfiguracaoTributariaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class PISConfiguracaoTributariaDAO extends AbstractCrudDAO<PisConfiguracaoTributariaEntity> {

	@Override
	public Class<PisConfiguracaoTributariaEntity> getEntityClass() {
		return PisConfiguracaoTributariaEntity.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"empresa"};
	}
	
	@Transactional
	public PisConfiguracaoTributariaEntity buscarPorConfiguracao(ConfiguracaoTributaria configuracao){
		PisConfiguracaoTributariaEntity pis = null;
		Criteria c = getSession().createCriteria(PisConfiguracaoTributariaEntity.class);
		if(configuracao!=null){
			c.add(Restrictions.eq("configuracaoTributaria", configuracao));
			pis = (PisConfiguracaoTributariaEntity)c.uniqueResult();
		}
		return pis;
	}

}

