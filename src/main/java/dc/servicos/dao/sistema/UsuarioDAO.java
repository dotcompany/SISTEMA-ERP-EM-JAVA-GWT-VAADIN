package dc.servicos.dao.sistema;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.Usuario;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;


@Repository
public class UsuarioDAO extends AbstractCrudDAO<Usuario>{


	Logger log = LoggerFactory.getLogger(UsuarioDAO.class);
	
	@Override
	protected Class<Usuario> getEntityClass() {
		return Usuario.class;
	}
	
	@Transactional
	public Usuario getUsuarioByLogin(String login) {
		log.info("Logando Usuário {}", login);
		Session s = getSession();
		log.debug("getSession: OK");
		
		log.debug("Procurando Usuário: {}", login);
		try {
			List<Usuario> usuarios = s.createCriteria(Usuario.class).add(Restrictions.eq("login", login)).list();
			log.debug("Encontrado {} usuarios", usuarios.size());
			if(usuarios.size() > 0){
				return usuarios.get(0);	
			}else{
				return null;
			}
		} catch (Exception e) {
			log.error("Erro procurando Usuário {}", login, e);
			throw e;
		}
		
	}
	
	

	protected String[] getDefaultSearchFields() {
		return new String[] {"login"};
	}

	@Transactional
	public String getNomeUsuario(Usuario usuario) {
		Usuario u =  find(usuario.getId());
		return u.getUsernome();
	}

	

	
	
}

