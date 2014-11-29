package dc.servicos.dao.framework.geral;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.framework.Empresa;
import dc.entidade.geral.pessoal.CargoEntity;
import dc.entidade.sistema.ContaEmpresa;

/**
 * 
 * @author Wesley Jr /* Nessa classe temos a Extensão a classe principal
 *         abstractCrudDao e dela herdamos alguns métodos, fazemos uma Conexão
 *         com o Banco, uma listagem E aqui herdamos também o Método do
 *         pesquisar, onde nela colocamos os campos que colocamos as anotações
 *         lá no TO (ENTIDADE), que vai ser pesquisado na Tela quando rodar o
 *         projeto.
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class EmpresaDAO extends AbstractCrudDAO<Empresa> {
	
	static String MATRIZ="1";

	@Override
	public Class<Empresa> getEntityClass() {
		return Empresa.class;
	}

	@Transactional
	public List<CargoEntity> listCargos(Empresa empresa) {
		return getSession().createQuery("from Cargo where empresa.id = :bid")
				.setParameter("bid", empresa.getId()).list();
	}

	@Transactional
	public List<Empresa> listaTodos() {
		return getSession().createQuery("from Empresa").list();
	}

	@Transactional
	public List<Empresa> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from Empresa where nomeFantasia like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] { "nomeFantasia", "razaoSocial" };
	}

	@Transactional
	public List<Empresa> empresaLista() {
		String sql = "SELECT new Empresa(ent.id, ent.nomeFantasia) FROM Empresa ent";

		List auxLista = getSession().createQuery(sql).list();

		return auxLista;
	}

	@Transactional
	public Empresa getEmpresa(Integer id) {
		String sql = "SELECT ent.empresa FROM TipoAquisicaoEntity ent"
				+ " WHERE (1 = 1) AND ent.id = :id";

		Empresa ent = (Empresa) getSession().createQuery(sql)
				.setParameter("id", id).uniqueResult();

		return ent;
	}

	@Transactional
	public Empresa findByCNPJ(String cnpj) {
		List<Empresa> empresas = getSession().createCriteria(Empresa.class).add(Restrictions.eq("cnpj",cnpj)).list();
		if(!empresas.isEmpty()){
			return empresas.get(0);
		}else{
			return null;
		}
		
	}
	
	@Transactional
	public List<Empresa> buscaMatrizes(){
		return getSession()
				.createQuery("from Empresa where tipo = :tipo")
				.setParameter("tipo", MATRIZ).list();
	}
	
	@Transactional
	public Empresa findEmpresaByContaEmpresa(Integer contaEmpresaId) {
		return (Empresa) getSession()
				.createQuery("from Empresa where conta.id = :c")
				.setParameter("c", contaEmpresaId).uniqueResult();
	}



}