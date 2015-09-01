package dc.servicos.dao.financeiro;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.AgenciaBancoEntity;
import dc.entidade.financeiro.BancoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class AgenciaBancoDAO extends AbstractCrudDAO<AgenciaBancoEntity> {

	@Autowired
	private BancoDAO bancoDAO;
	
	@Override
	public Class<AgenciaBancoEntity> getEntityClass() {
		return AgenciaBancoEntity.class;
	}

	@Transactional
	public List<AgenciaBancoEntity> listaTodos() {
		return getSession().createQuery("from AgenciaBanco").list();
	}

	@Transactional
	public List<AgenciaBancoEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from AgenciaBanco where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"banco", "nome", "codigo", "logradouro","cep","telefone","contato"};
	}
	
	@Transactional
	public List<AgenciaBancoEntity> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from AgenciaBancoEntity where lower(nome) like :q").setParameter("q", q).list();
	}
	
	@Transactional
	public AgenciaBancoEntity find(String codigo) throws Exception {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.codigo = :codigo";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("codigo", codigo);

			AgenciaBancoEntity entity = (AgenciaBancoEntity) query.uniqueResult();

			if (entity == null) {
				entity = new AgenciaBancoEntity();
			}

			return entity;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	@Transactional
	public <E> void saveOrUpdate(E o) {
		AgenciaBancoEntity agenciaBancoEntity = (AgenciaBancoEntity)o;
		if(agenciaBancoEntity.getBanco() != null){
			BancoEntity banco = bancoDAO.find(agenciaBancoEntity.getBanco().getId());
			agenciaBancoEntity.setBanco(banco);
		}
		super.saveOrUpdate(o);
	}
}