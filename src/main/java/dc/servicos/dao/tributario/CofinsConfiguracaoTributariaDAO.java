package dc.servicos.dao.tributario;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import dc.entidade.tributario.CofinsConfiguracaoTributariaEntity;
import dc.entidade.tributario.ConfiguracaoTributaria;
import dc.entidade.tributario.PisConfiguracaoTributariaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class CofinsConfiguracaoTributariaDAO extends AbstractCrudDAO<CofinsConfiguracaoTributariaEntity> {

	@Override
	public Class<CofinsConfiguracaoTributariaEntity> getEntityClass() {
		return CofinsConfiguracaoTributariaEntity.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"empresa"};
	}
	
	@Transactional
	public CofinsConfiguracaoTributariaEntity buscarPorConfiguracao(ConfiguracaoTributaria configuracao){
		CofinsConfiguracaoTributariaEntity cofins = null;
		Criteria c = getSession().createCriteria(CofinsConfiguracaoTributariaEntity.class);
		if(configuracao!=null){
			c.add(Restrictions.eq("configuracaoTributaria", configuracao));
			cofins = (CofinsConfiguracaoTributariaEntity)c.uniqueResult();
		}
		return cofins;
	}

}


