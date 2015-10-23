package dc.servicos.dao.ordemservico;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.InformacaoGeralEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class InformacaoGeralDAO extends AbstractCrudDAO<InformacaoGeralEntity> {

	@Override
	public Class<InformacaoGeralEntity> getEntityClass() {
		return InformacaoGeralEntity.class;
	}

	@Transactional
	public List<InformacaoGeralEntity> listarTodos() {
		try {
			String sql = "FROM InformacaoGeralEntity ent WHERE (1 = 1)";

			List<InformacaoGeralEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<InformacaoGeralEntity>();
		}
	}

	@Transactional
	public List<InformacaoGeralEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM InformacaoGeralEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<InformacaoGeralEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<InformacaoGeralEntity>();
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Colaborador", "Dias afastado", "Data início",
				"Data término" };
	}
	
	@Transactional  
	public InformacaoGeralEntity buscaInformacaoGeral(OrdemServicoEntity ordemServico){
		
		Criteria c = getSession().createCriteria(InformacaoGeralEntity.class);
		c.add(Restrictions.eq("ordemServico",ordemServico));
		return (InformacaoGeralEntity)c.uniqueResult();
	}

}