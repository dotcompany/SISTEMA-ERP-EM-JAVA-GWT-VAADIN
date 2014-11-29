package dc.servicos.dao.geral.pessoal;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.pessoal.ClienteEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("pessoalClienteDAO")
public class ClienteDAO extends AbstractCrudDAO<ClienteEntity> {

	@Override
	public Class<ClienteEntity> getEntityClass() {
		// TODO Auto-generated method stub
		return ClienteEntity.class;
	}

	@Transactional
	public List<ClienteEntity> listaTodos() {
		List<ClienteEntity> lista = getSession().createQuery("from Cliente")
				.list();

		return lista;
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] { "pessoa.nome", "desde", "contaTomador",
				"observacao", "geraFinanceiro", "indicadorPreco", "tipoFrete",
				"formaDesconto", "porcentoDesconto", "limiteCredito" };
	}

	@Transactional
	public List<ClienteEntity> query(String q) {
		q = "%" + q.toLowerCase() + "%";

		return getSession()
				.createQuery("from Cliente where lower(observacao) like :q")
				.setParameter("q", q).list();
	}

}