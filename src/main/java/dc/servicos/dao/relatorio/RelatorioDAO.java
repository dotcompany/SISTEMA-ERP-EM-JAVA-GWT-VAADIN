package dc.servicos.dao.relatorio;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.framework.Empresa;
import dc.entidade.framework.FmMenu;
import dc.entidade.framework.Papel;
import dc.entidade.framework.Seguimento;
import dc.entidade.geral.Usuario;
import dc.entidade.relatorio.Relatorio;
import dc.entidade.relatorio.TipoRelatorio;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class RelatorioDAO extends AbstractCrudDAO<Relatorio> {

	@Override
	public Class<Relatorio> getEntityClass() {
		return Relatorio.class;
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao" };
	}

	@Transactional
	public List<Relatorio> findRelatoriosByMenuAndUserAndType(FmMenu menu, Usuario usuario, TipoRelatorio tipoRelatorio) {

		if (menu != null && menu.getId() != null) {
			final Session session = sessionFactory.getCurrentSession();

			String hql = "select distinct r from Relatorio r " + "join r.usuarios u " + "join r.papeis p " + "join r.empresas e "
					+ " join r.seguimentos s " + " where r.menu = :menu and r.tipo = :tipo and " + " r.u in (:usuarios)";

			Query query = session.createQuery(hql);
			query.setEntity("menu", menu);
			query.setInteger("tipo", tipoRelatorio.getTipo());

			query.setParameterList("usuarios", new Usuario[] { usuario });
			// List<Article> articles = query.list();

			return query.list();
		} else
			return null;
	}

	@Override
	@Transactional
	public Relatorio find(Serializable id) {
		Relatorio relatorio = super.find(id);

		Hibernate.initialize(relatorio.getSeguimentos());
		Hibernate.initialize(relatorio.getUsuarios());
		Hibernate.initialize(relatorio.getPapeis());
		Hibernate.initialize(relatorio.getEmpresas());
		return relatorio;
	}

	@Transactional
	public void salvar(Relatorio relatorio, List<Usuario> usuariosView, List<Seguimento> seguimentosView, List<Papel> papeisView,
			List<Empresa> empresasView) {
		saveOrUpdate(relatorio);
		criaRelacionamentos(relatorio, usuariosView, seguimentosView, papeisView, empresasView);
		saveOrUpdate(relatorio);
	}

	private Relatorio criaRelacionamentos(Relatorio relatorio, List<Usuario> usuariosView, List<Seguimento> seguimentosView, List<Papel> papeisView,
			List<Empresa> empresasView) {
		Set<Seguimento> seguimentos = new HashSet<>();
		Set<Papel> papeis = new HashSet<>();
		Set<Usuario> usuarios = new HashSet<>();
		Set<Empresa> empresas = new HashSet<>();

		for (Usuario usuario : usuariosView) {
			usuarios.add((Usuario) sessionFactory.getCurrentSession().get(Usuario.class, usuario.getId()));
		}

		for (Seguimento seguimento : seguimentosView) {
			seguimentos.add((Seguimento) sessionFactory.getCurrentSession().get(Seguimento.class, seguimento.getId()));
		}

		for (Papel papel : papeisView) {
			papeis.add((Papel) sessionFactory.getCurrentSession().get(Papel.class, papel.getId()));
		}

		for (Empresa empresa : empresasView) {
			empresas.add((Empresa) sessionFactory.getCurrentSession().get(Empresa.class, empresa.getId()));
		}

		Relatorio dbBean;
		if (relatorio.getId() != null) {
			dbBean = find(relatorio.getId());
			dbBean.getPapeis().retainAll(papeis);
			papeis.removeAll(dbBean.getPapeis());
			dbBean.getPapeis().addAll(papeis);

			dbBean.getEmpresas().retainAll(empresas);
			empresas.removeAll(dbBean.getEmpresas());
			dbBean.getEmpresas().addAll(empresas);

			dbBean.getUsuarios().retainAll(usuarios);
			usuarios.removeAll(dbBean.getUsuarios());
			dbBean.getUsuarios().addAll(usuarios);

			dbBean.getSeguimentos().retainAll(seguimentos);
			seguimentos.removeAll(dbBean.getSeguimentos());
			dbBean.getSeguimentos().addAll(seguimentos);
		} else {
			dbBean = relatorio;
			dbBean.setPapeis(papeis);
		}
		return dbBean;
	}
}
