package dc.servicos.dao.financeiro;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.ConfiguracaoBoleto;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.financeiro.ParcelaPagamento;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class ConfiguracaoBoletoDAO extends AbstractCrudDAO<ConfiguracaoBoleto> {

	@Override
	public Class<ConfiguracaoBoleto> getEntityClass() {
		return ConfiguracaoBoleto.class;
	}

	@Transactional
	public List<ParcelaPagamento> listaTodos() {
		return getSession().createQuery("from ConfiguracaoBoleto").list();
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {};
	}

	@Transactional
	public List<ConfiguracaoBoleto> getConfiguracoesBoletoByContaCaixa(ContaCaixa contaCaixa) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(ConfiguracaoBoleto.class);
		criteria.add(Restrictions.eq("contaCaixa", contaCaixa));

		List<ConfiguracaoBoleto> configuracoes = criteria.list();

		return configuracoes;
	}

}
