package dc.servicos.dao.administrativo.empresa;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.control.util.StringUtils;
import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.framework.EmpresaSeguimento;
import dc.entidade.geral.PessoaEnderecoEntity;
import dc.entidade.geral.pessoal.CargoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;
import dc.servicos.dao.framework.geral.EmpresaSeguimentoDAO;
import dc.servicos.dao.geral.PessoaEnderecoDAO;

@Repository
public class EmpresaDAO extends AbstractCrudDAO<EmpresaEntity> {

	private static String MATRIZ = "1";

	@Autowired
	private PessoaEnderecoDAO pessoaEnderecoDAO;

	@Autowired
	private EmpresaSeguimentoDAO empresaSeguimentoDAO;

	@Override
	public Class<EmpresaEntity> getEntityClass() {
		return EmpresaEntity.class;
	}

	@Transactional
	public List<EmpresaEntity> listaTodos() {
		try {
			String sql = "SELECT new EmpresaEntity(ent.id, ent.nomeFantasia) FROM EmpresaEntity ent";

			List<EmpresaEntity> auxLista = getSession().createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Transactional
	public List<EmpresaEntity> procuraNomeContendo(String query) {
		try {
			String sql = "SELECT new EmpresaEntity(ent.id, ent.nomeFantasia)"
					+ " FROM EmpresaEntity WHERE nomeFantasia LIKE :q";

			List<EmpresaEntity> auxLista = getSession().createQuery(sql)
					.setParameter("q", "%" + query + "%").list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] { "nomeFantasia", "razaoSocial" };
	}

	@Transactional
	public List<CargoEntity> query(String q) {
		try {
			String sql = "SELECT new EmpresaEntity(ent.id, ent.nomeFantasia)"
					+ " FROM EmpresaEntity WHERE lower(nomeFantasia) LIKE :q";

			q = "%" + q.toLowerCase() + "%";

			return getSession().createQuery(sql).setParameter("q", q).list();
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	/**
	 * *******************************************************
	 */

	@Transactional
	public List<EmpresaEntity> getListEmpresa() {
		try {
			String sql = "SELECT new EmpresaEntity(ent.id, ent.nomeFantasia) FROM EmpresaEntity ent";

			List<EmpresaEntity> auxLista = getSession().createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Transactional
	public EmpresaEntity findEmpresa(Integer id) {
		try {
			String sql = "SELECT ent.empresa FROM TipoAquisicaoEntity ent"
					+ " WHERE (1 = 1) AND ent.id = :id";

			EmpresaEntity ent = (EmpresaEntity) getSession().createQuery(sql)
					.setParameter("id", id).uniqueResult();

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Transactional
	public EmpresaEntity findByCNPJ(String cnpj) {
		try {
			List<EmpresaEntity> auxLista = getSession()
					.createCriteria(EmpresaEntity.class)
					.add(Restrictions.eq("cnpj", cnpj)).list();

			if (!auxLista.isEmpty()) {
				return auxLista.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Transactional
	public List<EmpresaEntity> getEmpresaMatrizList() {
		try {
			List<EmpresaEntity> auxLista = new ArrayList<EmpresaEntity>();

			String sql = "SELECT new EmpresaEntity(ent.id, ent.razaoSocial, ent.nomeFantasia)"
					+ " FROM EmpresaEntity ent WHERE tipo = :tipo";

			auxLista = getSession().createQuery(sql)
					.setParameter("tipo", MATRIZ).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Transactional
	public EmpresaEntity findEmpresaByContaEmpresa(Integer contaEmpresaId) {
		try {
			String sql = "SELECT new EmpresaEntity(ent.id, ent.nomeFantasia)"
					+ " FROM EmpresaEntity WHERE conta.id = :c";

			EmpresaEntity ent = (EmpresaEntity) getSession().createQuery(sql)
					.setParameter("c", contaEmpresaId).uniqueResult();

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Transactional
	public void saveOrUpdateEmpresa(EmpresaEntity empresa) throws Exception {
		try {
			super.saveOrUpdate(empresa);

			List<PessoaEnderecoEntity> auxLista = empresa.getEnderecoList();

			if (auxLista != null && !auxLista.isEmpty()) {
				for (PessoaEnderecoEntity ent : auxLista) {
					ent.setEmpresa(empresa);

					String cep = ent.getCep();

					if (StringUtils.isNotBlank(cep)) {
						cep = StringUtils.removeSpecialCharacters(ent.getCep());
						ent.setCep(cep);
					}

					this.pessoaEnderecoDAO.saveOrUpdate(ent);
				}
			}

			List<EmpresaSeguimento> auxLista1 = empresa
					.getEmpresaSeguimentoList();

			if (auxLista1 != null && !auxLista1.isEmpty()) {
				for (EmpresaSeguimento ent : auxLista1) {
					ent.setEmpresa(empresa);

					this.empresaSeguimentoDAO.saveOrUpdate(ent);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}