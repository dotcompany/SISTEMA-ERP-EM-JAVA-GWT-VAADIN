package dc.servicos.dao.financeiro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.IndiceEconomico;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

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
public class IndiceEconomicoDAO extends AbstractCrudDAO<IndiceEconomico> {

	@Override
	public Class<IndiceEconomico> getEntityClass() {
		return IndiceEconomico.class;
	}

	@Transactional
	public List<IndiceEconomico> listaTodos() {
		return getSession().createQuery("from IndiceEconomico").list();
	}

	@Transactional
	public List<IndiceEconomico> listarTodos() {
		try {
			String sql = "FROM IndiceEconomico ent WHERE (1 = 1)";

			List<IndiceEconomico> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<IndiceEconomico>();
		}
	}

	@Transactional
	public List<IndiceEconomico> procuraNomeContendo(String query) {
		try {
			String sql = "FROM IndiceEconomico ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<IndiceEconomico> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<IndiceEconomico>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Nome", "Sigla" };
	}

}