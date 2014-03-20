package dc.servicos.dao.ordemservico;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.InformacaoGeral;
import dc.entidade.ordemservico.OrdemServico;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class InformacaoGeralDAO extends AbstractCrudDAO<InformacaoGeral> {

	@Override
	public Class<InformacaoGeral> getEntityClass() {
		return InformacaoGeral.class;
	}

	@Transactional
	public List<InformacaoGeral> listarTodos() {
		try {
			String sql = "FROM InformacaoGeral ent WHERE (1 = 1)";

			List<InformacaoGeral> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<InformacaoGeral>();
		}
	}

	@Transactional
	public List<InformacaoGeral> procuraNomeContendo(String query) {
		try {
			String sql = "FROM InformacaoGeral ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<InformacaoGeral> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<InformacaoGeral>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Colaborador", "Dias afastado", "Data início",
				"Data término" };
	}
	
	@Transactional  
	public InformacaoGeral buscaInformacaoGeral(OrdemServico ordemServico){
		
		Criteria c = getSession().createCriteria(InformacaoGeral.class);
		c.add(Restrictions.eq("ordemServico",ordemServico));
		return (InformacaoGeral)c.uniqueResult();
	}

}