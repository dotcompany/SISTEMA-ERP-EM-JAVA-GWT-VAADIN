package dc.servicos.dao.pessoal;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.pessoal.Cliente;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ClienteDAO extends AbstractCrudDAO<Cliente> {

	@Override
	public Class<Cliente> getEntityClass() {
		// TODO Auto-generated method stub
		return Cliente.class;
	}

	@Transactional
	public List<Cliente> listaTodos() {
		return getSession().createQuery("from Cliente").list();
	}

	@Override
	protected String[] getDefaultSearchFields() {

		return new String[] { "pessoa.nome", "desde", "contaTomador", "observacao", "geraFinanceiro", "indicadorPreco", "tipoFrete", "formaDesconto",
				"porcentoDesconto", "limiteCredito" };
	}

	@Transactional
	public List<Cliente> query(String q) {
		q = "%" + q.toLowerCase() + "%";
		return getSession().createQuery("from Cliente where lower(observacao) like :q").setParameter("q", q).list();
	}

}
