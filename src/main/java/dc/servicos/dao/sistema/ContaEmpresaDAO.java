package dc.servicos.dao.sistema;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.framework.FmModulo;
import dc.entidade.sistema.ConfiguracaoContaEmpresa;
import dc.entidade.sistema.ContaEmpresa;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ContaEmpresaDAO extends AbstractCrudDAO<ContaEmpresa>{

	
	
	@Override
	public Class<ContaEmpresa> getEntityClass() {
		// TODO Auto-generated method stub
		return ContaEmpresa.class;
	}

	@Override
	protected String[] getDefaultSearchFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public ConfiguracaoContaEmpresa findConfiguracaoByIdConta(Integer contaId) {
		// TODO Auto-generated method stub
		return (ConfiguracaoContaEmpresa) getSession().createCriteria(ConfiguracaoContaEmpresa.class).add(Restrictions.eq("conta.id",contaId)).uniqueResult();
	}

	@Transactional
	public ContaEmpresa findByEmail(String email) {
		return (ContaEmpresa) getSession().createCriteria(ContaEmpresa.class).add(Restrictions.eq("email",email)).uniqueResult();
	}

	
	
	

}
