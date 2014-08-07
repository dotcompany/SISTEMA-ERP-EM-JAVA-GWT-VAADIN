package dc.servicos.dao.relatorio;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.framework.FmMenu;
import dc.entidade.geral.Usuario;
import dc.entidade.relatorio.Relatorio;
import dc.entidade.relatorio.TipoRelatorio;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class RelatorioDAO extends AbstractCrudDAO<Relatorio> {

	@Override
	public Class<Relatorio> getEntityClass() {
		return Relatorio.class;
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao" };
	}

	@Transactional
	public List<Relatorio> findRelatoriosByMenuAndUserAndType(FmMenu menu, Usuario usuario, TipoRelatorio tipoRelatorio) {

		if (menu != null && menu.getId() != null) {
			final Session session = sessionFactory.getCurrentSession();
			final Criteria crit = session.createCriteria(Relatorio.class);

			crit.add(Restrictions.eq("menu", menu));

			Disjunction tipoDisjunction = Restrictions.disjunction();
			tipoDisjunction.add(Restrictions.eq("tipo", tipoRelatorio.getTipo()));
			tipoDisjunction.add(Restrictions.eq("tipo", TipoRelatorio.LISTAGEM_FORMULARIO.getTipo()));

			crit.add(tipoDisjunction);

			return crit.list();
		} else
			return null;
	}
}
