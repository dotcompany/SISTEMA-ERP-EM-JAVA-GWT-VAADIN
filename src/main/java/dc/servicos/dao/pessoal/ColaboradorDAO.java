package dc.servicos.dao.pessoal;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.pessoal.Colaborador;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Wesley Jr
 */

@Repository
@SuppressWarnings("unchecked")
public class ColaboradorDAO extends AbstractCrudDAO<Colaborador> {

	@Override
	public Class<Colaborador> getEntityClass() {
		return Colaborador.class;
	}

	@Transactional
	public List<Colaborador> listaTodos() {
		try {
			String sql = "FROM Colaborador ent WHERE (1 = 1)";

			List auxLista = super.getSession().createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<Colaborador>();
		}
	}

	@Transactional
	public List<Colaborador> procuraNomeContendo(String query) {
		return getSession().createQuery("from Colaborador where observacao like :q").setParameter("q", "%" + query + "%").list();
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "pessoa.nome", "codigoTurmaPonto", "dataCadastro", "dataAdmissao", "vencimentoFerias", "dataTransferencia" };
	}

	@Transactional
	public Colaborador getColaboradorByPisNumero(String pisNumero) {
		Criteria criteria = getSession().createCriteria(Colaborador.class);
		criteria.add(Restrictions.eq("pisNumero", pisNumero));
		Colaborador colaborador = (Colaborador) criteria.uniqueResult();
		return colaborador;
	}

	/**
	 * ********************************************************
	 */

	/**
	 * @autor Gutemberg A. Da Silva
	 * 
	 * @module FOLHAPAGAMENTO
	 */

	@Transactional
	public List<Colaborador> colaboradorLista() {
		try {
			String sql = "SELECT new Colaborador(ent.id, ent.matricula) FROM Colaborador ent" + " WHERE (1 = 1)";

			List<Colaborador> auxLista = getSession().createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<Colaborador>();
		}
	}

	/**
	 * ********************************************************
	 */

}