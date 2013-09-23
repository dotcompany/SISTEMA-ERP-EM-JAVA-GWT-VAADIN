package dc.servicos.dao.sistema;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;

import dc.entidade.framework.FmModulo;
import dc.entidade.framework.Papel;
import dc.entidade.framework.PapelMenu;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;


@Repository
public class PapelDAO extends AbstractCrudDAO<Papel>{

	public static Logger logger = Logger.getLogger(PapelDAO.class);
	
	@Override
	public Class<Papel> getEntityClass() {
		return Papel.class;
	}
	

	protected String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	}


	@Transactional
	public dc.entidade.framework.PapelMenu getPapelMenuByPapelAndMenuID(Integer idPapel, Integer idMenu) {
		logger.info("Calling getPapelMenuByPapelAndMenuID query...");
		logger.info("Papel: " + idPapel);
		logger.info("Menu: " + idMenu);
		String hQ = "select pm from PapelMenu as pm " +
				" where pm.papel.id= :papel_id and pm.menu.id = :menu_id ";
		return (PapelMenu)  getSession().createQuery(hQ).setInteger("papel_id",idPapel).setInteger("menu_id",idMenu).uniqueResult();		
	}
	
	@Transactional
	public dc.entidade.framework.PapelMenu getPapelMenuByPapelAndMenuControllerClass(Integer idPapel, String controllerClass) {
		logger.info("Calling getPapelMenuByPapelAndMenuController query...");
		logger.info("Papel: " + idPapel);
		logger.info("Controller: " + controllerClass);
		String hQ = "select pm from PapelMenu as pm " +
				" where pm.papel.id= :papel_id and pm.menu.controllerClass= :controllerName";
		return (PapelMenu)  getSession().createQuery(hQ).setInteger("papel_id",idPapel).setString("controllerClass",controllerClass).uniqueResult();		
	}

	@Transactional
	public List<PapelMenu> getPapelMenusOrdered(Papel p) {
		String hQ = "select pm from PapelMenu as pm " +
				" where pm.papel.id= :papel_id order by menu.parent.id  nulls first , menu.caption";
		//return getSession().createCriteria(PapelMenu.class).add(Restrictions.eq("papel_id",p.getId())).add(Order.asc("pm.m))addOrder(Order.asc("caption")).list();
		return  getSession().createQuery(hQ).setInteger("papel_id",p.getId()).list();
	}



}

