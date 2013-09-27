package dc.servicos.dao.diversos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.diversos.Pais;
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
public class PaisDAO extends AbstractCrudDAO<Pais> {

	@Override
	public Class<Pais> getEntityClass() {
		return Pais.class;
	}

	@Transactional
	public List<Pais> listaTodos() {
		try {
			String sql = "FROM Pais ent WHERE (1 = 1)";

			List<Pais> auxLista = super.getSession().createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<Pais>();
		}
	}

	@Transactional
	public List<Pais> procuraNomeContendo(String query) {
		try {
			String sql = "FROM Pais ent WHERE (1 = 1) AND ent.nomeEn LIKE :q";

			List<Pais> auxLista = super.getSession().createQuery(sql)
					.setParameter("q", "%" + query + "%").list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<Pais>();
		}
	}

	@Transactional
	public List<Pais> query(String q) {
		try {
			String sql = "FROM Pais ent WHERE (1 = 1) AND ent.nomeEn LIKE :q";

			List<Pais> auxLista = super.getSession().createQuery(sql)
					.setParameter("q", "%" + q + "%").list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<Pais>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "nomeEn", "nomePtbr", "sigla2", "sigla3" };
	}

}