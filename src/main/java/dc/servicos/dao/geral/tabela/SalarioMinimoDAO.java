package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.SalarioMinimo;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class SalarioMinimoDAO extends AbstractCrudDAO<SalarioMinimo>{

	@Override
	public Class<SalarioMinimo> getEntityClass() {
		return SalarioMinimo.class;
	}
	
	@Transactional
	public List<SalarioMinimo> listaTodos() {
		return getSession().createQuery("from SalarioMinimo").list();
	}

	@Transactional
	public List<SalarioMinimo> procuraNomeContendo(String query) {
		return getSession().createQuery("from SalarioMinimo where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"vigencia","valorMensal","valorDiario","valorHora"};
	}


}
