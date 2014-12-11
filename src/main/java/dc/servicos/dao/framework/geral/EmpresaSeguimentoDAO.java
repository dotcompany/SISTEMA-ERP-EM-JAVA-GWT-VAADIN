package dc.servicos.dao.framework.geral;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.framework.EmpresaSeguimento;

@Repository
public class EmpresaSeguimentoDAO extends AbstractCrudDAO<EmpresaSeguimento> {

	@Override
	public Class<EmpresaSeguimento> getEntityClass() {
		return EmpresaSeguimento.class;
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao" };
	}

	@Transactional
	public List<EmpresaSeguimento> listaPorEmpresa(EmpresaEntity empresa) {
		return getSession()
				.createQuery("from EmpresaSeguimento e where e.empresa = :emp")
				.setParameter("emp", empresa).list();
	}

	@Transactional
	public List<EmpresaSeguimento> getEmpresaSeguimentoList(
			EmpresaEntity empresa) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.empresa.id = :id";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("id", empresa.getId());

			List<EmpresaSeguimento> auxLista = query.list();

			if (auxLista == null) {
				auxLista = new ArrayList<EmpresaSeguimento>();
			}

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Transactional
	public void saveOrUpdateEmpresaSeguimentoList(
			List<EmpresaSeguimento> auxLista) {
		try {
			for (EmpresaSeguimento ent : auxLista) {
				super.saveOrUpdate(ent);
			}
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}