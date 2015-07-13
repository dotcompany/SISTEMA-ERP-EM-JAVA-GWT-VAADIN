package dc.servicos.dao.financeiro;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import dc.entidade.financeiro.LancamentoReceber;
import dc.entidade.financeiro.LctoReceberNtFinanceiraEntity;
import dc.entidade.financeiro.NaturezaFinanceira;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class LctoReceberNtFinanceiraDAO extends
		AbstractCrudDAO<LctoReceberNtFinanceiraEntity> {

	@Override
	public Class<LctoReceberNtFinanceiraEntity> getEntityClass() {
		return LctoReceberNtFinanceiraEntity.class;
	}

	@Transactional
	public List<LctoReceberNtFinanceiraDAO> listaTodos() {
		return getSession().createQuery("from LctoReceberNtFinanceiraEntity").list();
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "valor", "dataInclusao" };
	}

	@Transactional
	public List<LctoReceberNtFinanceiraEntity> findByNatureza(
			LancamentoReceber currentBean) {

		List<LctoReceberNtFinanceiraEntity> lista = new ArrayList<>();

		try {
			if (currentBean != null) {
				lista = getSession()
						.createQuery(
								"from LctoReceberNtFinanceiraEntity i where i.lancamentoReceber = :lancamentoReceber")
						.setParameter("lancamentoReceber", currentBean).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Transactional
	public List<NaturezaFinanceira> findByNaturezaFin(
			LancamentoReceber currentBean) {

		List<NaturezaFinanceira> lista = new ArrayList<>();

		try {
			if (currentBean != null) {
				lista = getSession()
						.createQuery(
								"from NaturezaFinanceira i where i.lancamentoReceber = :lancamentoReceber")
						.setParameter("lancamentoReceber", currentBean).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

}
