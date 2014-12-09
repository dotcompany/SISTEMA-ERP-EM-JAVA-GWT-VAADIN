package dc.servicos.dao.tributario;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tributario.CofinsConfiguracaoTributariaEntity;
import dc.entidade.tributario.ConfiguracaoTributaria;
import dc.entidade.tributario.IcmsConfiguracaoTributariaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ICMSConfiguracaoTributariaDAO extends AbstractCrudDAO<IcmsConfiguracaoTributariaEntity> {

	@Override
	public Class<IcmsConfiguracaoTributariaEntity> getEntityClass() {
		return IcmsConfiguracaoTributariaEntity.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"empresa"};
	}
	
	@Transactional
	public List<IcmsConfiguracaoTributariaEntity> buscarPorConfiguracao(ConfiguracaoTributaria configuracao){
		List<IcmsConfiguracaoTributariaEntity> lista = null;
		Criteria c = getSession().createCriteria(IcmsConfiguracaoTributariaEntity.class);
		if(configuracao!=null){
			c.add(Restrictions.eq("configuracaoTributaria", configuracao));
			
		}
		lista = c.list();
		return lista;
	}


}
 

