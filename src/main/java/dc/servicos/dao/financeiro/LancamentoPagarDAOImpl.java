package dc.servicos.dao.financeiro;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.financeiro.LancamentoPagarEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class LancamentoPagarDAOImpl extends AbstractCrudDAO<LancamentoPagarEntity> implements LancamentoPagarDAOf<LancamentoPagarEntity> {

private static Logger logger = Logger.getLogger(LancamentoPagarDAOImpl.class);

@Override
public Class<LancamentoPagarEntity> getEntityClass() {
	return LancamentoPagarEntity.class;
}

public List<LancamentoPagarEntity> listaTodos() {
	try {
		String sql = "FROM # ent WHERE (1 = 1)";
		sql = sql.replace("#", this.getEntityClass().getName());

		Query query = super.getSession().createQuery(sql);
    	List<LancamentoPagarEntity> auxLista = query.list();

		return auxLista;
	} catch (Exception e) {
		e.printStackTrace();

		throw e;
	}
}

public List<LancamentoPagarEntity> procuraNomeContendo(String value) {
	try {
		String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
		sql = sql.replace("#", this.getEntityClass().getName());

		Query query = super.getSession().createQuery(sql);
		query.setParameter("nome", value);

		List<LancamentoPagarEntity> auxLista = query.list();

		return auxLista;
	} catch (Exception e) {
		e.printStackTrace();
		throw e;
	}
}

public List<LancamentoPagarEntity> query(String value) {
	try {
		String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
		sql = sql.replace("#", getEntityClass().getName());

		value = "%" + value.toLowerCase() + "%";

		Query query = super.getSession().createQuery(sql);
		query.setParameter("q", value);
		List<LancamentoPagarEntity> auxLista = query.list();

		return auxLista;
	} catch (Exception e) {
		e.printStackTrace();
		throw e;
	}
}

public String[] getDefaultSearchFields() {
	return new String[] { "descricao" };
}

}
