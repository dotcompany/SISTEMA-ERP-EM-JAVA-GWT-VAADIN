package dc.servicos.dao.framework.geral;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.framework.EmpresaEntity;
import dc.entidade.geral.pessoal.CargoEntity;

@Repository
public class EmpresaDAO extends AbstractCrudDAO<EmpresaEntity> {

	private static String MATRIZ = "1";

	@Override
	public Class<EmpresaEntity> getEntityClass() {
		return EmpresaEntity.class;
	}

	@Transactional
	public List<CargoEntity> listCargos(EmpresaEntity empresa) {
		return getSession()
				.createQuery("from CargoEntity where empresa.id = :bid")
				.setParameter("bid", empresa.getId()).list();
	}

	@Transactional
	public List<EmpresaEntity> listaTodos() {
		return getSession().createQuery("from EmpresaEntity").list();
	}

	@Transactional
	public List<EmpresaEntity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from EmpresaEntity where nomeFantasia like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	@Transactional
	public List<EmpresaEntity> empresaLista() {
		String sql = "SELECT new EmpresaEntity(ent.id, ent.nomeFantasia) FROM Empresa ent";

		List auxLista = getSession().createQuery(sql).list();

		return auxLista;
	}

	@Transactional
	public EmpresaEntity getEmpresa(Integer id) {
		String sql = "SELECT ent.empresa FROM TipoAquisicaoEntity ent"
				+ " WHERE (1 = 1) AND ent.id = :id";

		EmpresaEntity ent = (EmpresaEntity) getSession().createQuery(sql)
				.setParameter("id", id).uniqueResult();

		return ent;
	}

	@Transactional
	public EmpresaEntity findByCNPJ(String cnpj) {
		List<EmpresaEntity> empresas = getSession()
				.createCriteria(EmpresaEntity.class)
				.add(Restrictions.eq("cnpj", cnpj)).list();

		if (!empresas.isEmpty()) {
			return empresas.get(0);
		} else {
			return null;
		}
	}

	@Transactional
	public List<EmpresaEntity> buscaMatrizes() {
		return getSession()
				.createQuery("from EmpresaEntity where tipo = :tipo")
				.setParameter("tipo", MATRIZ).list();
	}

	@Transactional
	public EmpresaEntity findEmpresaByContaEmpresa(Integer contaEmpresaId) {
		return (EmpresaEntity) getSession()
				.createQuery("from EmpresaEntity where conta.id = :c")
				.setParameter("c", contaEmpresaId).uniqueResult();
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] { "nomeFantasia", "razaoSocial" };
	}

}