package dc.servicos.dao.tributario;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tributario.CofinsConfiguracaoTributaria;
import dc.entidade.tributario.ConfiguracaoTributaria;
import dc.entidade.tributario.ICMSConfiguracaoTributaria;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ICMSConfiguracaoTributariaDAO extends AbstractCrudDAO<ICMSConfiguracaoTributaria> {

	@Override
	public Class<ICMSConfiguracaoTributaria> getEntityClass() {
		return ICMSConfiguracaoTributaria.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"empresa"};
	}
	
	@Transactional
	public List<ICMSConfiguracaoTributaria> buscarPorConfiguracao(ConfiguracaoTributaria configuracao){
		List<ICMSConfiguracaoTributaria> lista = null;
		Criteria c = getSession().createCriteria(ICMSConfiguracaoTributaria.class);
		if(configuracao!=null){
			c.add(Restrictions.eq("configuracaoTributaria", configuracao));
			
		}
		lista = c.list();
		return lista;
	}


}
 

