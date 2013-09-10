package dc.visao.spring;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.sun.istack.logging.Logger;

import dc.entidade.geral.Pessoa;
import dc.entidade.geral.Usuario;
import dc.entidade.pessoal.Colaborador;

@org.springframework.stereotype.Component
@Scope("singleton")
public class SecuritySessionProvider implements ApplicationContextAware{
	
	@Value("${security.load}") 
	private String loadFromSpring;
	
	public static Logger logger = Logger.getLogger(SecuritySessionProvider.class);
	
	public static ApplicationContext ctx;
	
	@PostConstruct
	protected void init() {
		if(loadFromSpring != null && Boolean.valueOf(loadFromSpring)){
			logger.info("will load user from spring session");
		}else{
			logger.warning("Authentication DISABLED");
		}
	}
	
	public static Usuario getUsuario(){
		if(ctx!= null){
			SecuritySessionProvider s = ctx.getBean(SecuritySessionProvider.class);
			if(s.shouldLoadUserFromSpring()){
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				if(auth != null){
					Object principal = auth.getPrincipal();
					if(principal instanceof Usuario){
						return (Usuario) principal;
					}else{
						return (Usuario) auth.getDetails();
					}
				}else{
					return null;
				}
			}else{
				Usuario u = new Usuario();
				u.setAdministrador(true);
				Colaborador c = new Colaborador();
				Pessoa p = new Pessoa();
				p.setNome("Nome pessoa");
				c.setPessoa(p);
				u.setConfirmado(true);
				u.setColaborador(c);
				return u;
			}
		}else{
			return null;
		}
		
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		SecuritySessionProvider.ctx= arg0;
	}
	
	public boolean shouldLoadUserFromSpring(){
		return Boolean.valueOf(loadFromSpring);
	}

}
