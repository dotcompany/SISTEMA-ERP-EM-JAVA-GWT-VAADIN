package dc.servicos.dao.framework.geral;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.framework.FmMenu;
import dc.entidade.framework.FmModulo;
import dc.entidade.geral.Usuario;

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
public class FmModuloDAO extends AbstractCrudDAO<FmModulo> {

	@Value("${modules.id.adm}")
	public Integer ID_MODULO_ADM_OBRIGATORIO;

	public Integer getID_MODULO_ADM_OBRIGATORIO() {
		return ID_MODULO_ADM_OBRIGATORIO;
	}

	public void setID_MODULO_ADM_OBRIGATORIO(Integer iD_MODULO_ADM_OBRIGATORIO) {
		ID_MODULO_ADM_OBRIGATORIO = iD_MODULO_ADM_OBRIGATORIO;
	}

	public Integer getID_MODULO_SISTEMA_OBRIGATORIO() {
		return ID_MODULO_SISTEMA_OBRIGATORIO;
	}

	public void setID_MODULO_SISTEMA_OBRIGATORIO(
			Integer iD_MODULO_SISTEMA_OBRIGATORIO) {
		ID_MODULO_SISTEMA_OBRIGATORIO = iD_MODULO_SISTEMA_OBRIGATORIO;
	}

	@Value("${modules.id.sistema}")
	public Integer ID_MODULO_SISTEMA_OBRIGATORIO;

	public static Logger logger = Logger.getLogger(FmModuloDAO.class.getName());

	@Override
	public Class<FmModulo> getEntityClass() {
		return FmModulo.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "caption", "urlID" };
	}

	@Transactional
	public List<FmModulo> getAllOrderedByCaption() {
		return getSession().createCriteria(FmModulo.class)
				.setFetchMode("menus", FetchMode.SELECT)
				.addOrder(Order.asc("caption")).list();
	}

	@Transactional
	public List<FmModulo> getAllByUserIdOrderedByCaption(int user_id) {
		String q = "select distinct modulo.* from fm_modulo modulo "
				+ " inner join fm_menu menu on modulo.id = menu.fmmodulo_id "
				+ " inner join papel_menu pm on menu.id = pm.id_menu "
				+ " inner join papel p on pm.id_papel = p.id  "
				+ " inner join usuario u on u.id_papel = p.id and u.id = :user_id "
				+ " order by modulo.caption ";
		return getSession().createSQLQuery(q).addEntity(FmModulo.class)
				.setInteger("user_id", user_id).list();
	}

	@Transactional
	public List<FmModulo> getModulosSelecionaveis() {
		logger.info("Modulo adm: " + this.ID_MODULO_ADM_OBRIGATORIO);
		logger.info("Modulo sistema: " + this.ID_MODULO_SISTEMA_OBRIGATORIO);
		Criteria c = getSession().createCriteria(FmModulo.class);
		c.add(Restrictions.not(Restrictions.in("id", new Integer[] {
				this.ID_MODULO_ADM_OBRIGATORIO,
				this.ID_MODULO_SISTEMA_OBRIGATORIO })));
		return c.addOrder(Order.asc("caption")).list();
	}

	@Transactional
	public List<FmModulo> getModulosObrigatorios() {
		Criteria c = getSession().createCriteria(FmModulo.class);
		c.add(Restrictions.in("id", new Integer[] {
				this.ID_MODULO_ADM_OBRIGATORIO,
				this.ID_MODULO_SISTEMA_OBRIGATORIO }));
		return c.addOrder(Order.asc("caption")).list();
	}

	@Transactional
	public List<FmMenu> getModuloLista(Usuario usuario, String nomeClasse) {
		try {
			String sql = "FROM FmModulo ent WHERE (1 = 1)";

			if (usuario.getAdministrador()) {
				sql += " AND ent.id = " + 7;
			}

			List<FmMenu> auxLista = super.getSession().createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<FmMenu>();
		}
	}

}