package dc.servicos.dao.relatorio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.framework.FmMenu;
import dc.entidade.framework.Papel;
import dc.entidade.framework.SeguimentoEntity;
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

			String hql = "select distinct r from Relatorio r " + " left join r.usuarios u " + " left join r.papeis p " + " left join r.empresas e "
					+ " left join r.seguimentos s " + " where r.menu = :menu and r.tipo = :tipo  ";

			List<SeguimentoEntity> seguimentos = new ArrayList<>();
			if (!usuario.getAdministrador()) {
				seguimentos = session.createQuery("from EmpresaSeguimento e where e.empresa = :emp")
						.setParameter("emp", usuario.getConta().getEmpresa()).list();

				String seguimentosString = "";
				if (seguimentos.size() > 0) {
					seguimentosString = " or s.id in (:seguimentos) ";
				}

				hql += " and ( p.id in (:papeis) or u.id in (:usuarios) or e.id in (:empresas) " + seguimentosString + " )";
			}

			Query query = session.createQuery(hql);
			query.setEntity("menu", menu);
			query.setInteger("tipo", tipoRelatorio.getTipo());

			// Forçar sempre exibir para o admin
			if (!usuario.getAdministrador()) {

				query.setParameterList("papeis", new Integer[] { usuario.getPapel().getId() });
				query.setParameterList("usuarios", new Integer[] { usuario.getId() });
				query.setParameterList("empresas", new Integer[] { usuario.getConta().getEmpresa().getId() });
				if (seguimentos.size() > 0) {
					query.setParameterList("seguimentos", getSeguimentosIds(seguimentos));
				}
			}

			return query.list();
		} else
			return null;
	}

	private Integer[] getSeguimentosIds(List<SeguimentoEntity> seguimentos) {
		if (seguimentos != null) {
			Integer[] ids = new Integer[seguimentos.size()];
			for (int i = 0; i < seguimentos.size(); i++) {
				ids[i] = seguimentos.get(i).getId();
			}
		}
		return new Integer[] {};
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
	public void salvar(Relatorio relatorio, List<Usuario> usuariosView, List<SeguimentoEntity> seguimentosView, List<Papel> papeisView,
			List<EmpresaEntity> empresasView) {
		saveOrUpdate(relatorio);
		criaRelacionamentos(relatorio, usuariosView, seguimentosView, papeisView, empresasView);
		saveOrUpdate(relatorio);
	}

	private Relatorio criaRelacionamentos(Relatorio relatorio, List<Usuario> usuariosView, List<SeguimentoEntity> seguimentosView, List<Papel> papeisView,
			List<EmpresaEntity> empresasView) {
		Set<SeguimentoEntity> seguimentos = new HashSet<>();
		Set<Papel> papeis = new HashSet<>();
		Set<Usuario> usuarios = new HashSet<>();
		Set<EmpresaEntity> empresas = new HashSet<>();

		for (Usuario usuario : usuariosView) {
			usuarios.add((Usuario) sessionFactory.getCurrentSession().get(Usuario.class, usuario.getId()));
		}

		for (SeguimentoEntity seguimento : seguimentosView) {
			seguimentos.add((SeguimentoEntity) sessionFactory.getCurrentSession().get(SeguimentoEntity.class, seguimento.getId()));
		}

		for (Papel papel : papeisView) {
			papeis.add((Papel) sessionFactory.getCurrentSession().get(Papel.class, papel.getId()));
		}

		for (EmpresaEntity empresa : empresasView) {
			empresas.add((EmpresaEntity) sessionFactory.getCurrentSession().get(EmpresaEntity.class, empresa.getId()));
		}

		Relatorio dbBean = null;
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
		}
		return dbBean;
	}
}
