package dc.servicos.dao.framework.geral;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.framework.Fpas;

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
public class FpasDAO extends AbstractCrudDAO<Fpas> {

	@Override
	public Class<Fpas> getEntityClass() {
		return Fpas.class;
	}

	@Transactional
	public List<Fpas> listaTodos() {
		return getSession().createQuery("from Fpas").list();
	}

	@Transactional
	public List<Fpas> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from Fpas where cnae like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] { "cnae", "descricao" };
	}

	@Transactional
	public List<Fpas> fpasLista() {
		String sql = "SELECT new Fpas(ent.id, ent.cnae) FROM Fpas ent";

		List auxLista = getSession().createQuery(sql).list();

		return auxLista;
	}

}