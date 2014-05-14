package dc.servicos.dao.sistema;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.sistema.ConfiguracaoContaEmpresa;
import dc.entidade.sistema.ContaEmpresa;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ContaEmpresaDAO extends AbstractCrudDAO<ContaEmpresa> {

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

		Criteria criteria = getSession().createCriteria(ConfiguracaoContaEmpresa.class);
		criteria.add(Restrictions.eq("conta.id", contaId));

		return (ConfiguracaoContaEmpresa) criteria.uniqueResult();

	}

	@Transactional
	public ContaEmpresa findByEmail(String email) {
		return (ContaEmpresa) getSession().createCriteria(ContaEmpresa.class).add(Restrictions.eq("email", email)).uniqueResult();
	}

	@Transactional
	public ConfiguracaoContaEmpresa findConfiguracaoByIdContaWithModules(Integer contaId) {

		Criteria criteria = getSession().createCriteria(ConfiguracaoContaEmpresa.class);
		criteria.add(Restrictions.eq("conta.id", contaId));

		criteria.createCriteria("modulos").setFetchMode("menus", FetchMode.JOIN);

		// criteria.createCriteria("modulos.menus").setFetchMode("menusFilho",
		// FetchMode.JOIN);
		return (ConfiguracaoContaEmpresa) criteria.uniqueResult();
	}

}
