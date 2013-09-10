package dc.visao.framework.geral;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sun.istack.logging.Logger;

import dc.entidade.framework.Empresa;
import dc.entidade.framework.Papel;
import dc.entidade.geral.PessoaFisica;
import dc.entidade.geral.Usuario;
import dc.entidade.pessoal.Colaborador;
import dc.entidade.sistema.ContaEmpresa;
import dc.framework.mail.MailSender;
import dc.servicos.dao.framework.geral.EmpresaDAO;
import dc.servicos.dao.sistema.ContaEmpresaDAO;
import dc.servicos.dao.sistema.UsuarioDAO;

@Component
@Scope("prototype")
public class CriaContaController implements Serializable, ViewController {
	
	private static final long serialVersionUID = 3908353011945861868L;

	@Autowired
	transient ContaEmpresaDAO dao;
	
	@Autowired
	transient  UsuarioDAO usuarioDao;
	
	@Autowired
	transient EmpresaDAO empresaDao;
	
	CriaContaEmpresaView view;
	
	private ContaEmpresa currentBean;
	
	public static Logger logger = Logger.getLogger(CriaContaController.class);
	
	public void criarConta(ContaEmpresa c){
		currentBean = c;
		boolean saved = false;
		logger.info("Conta empresa, tentativa de cria��o");
		logger.info(String.valueOf(c) + c.getNome() + c.getEmail());
		try{
			currentBean.getEmpresa().setRazaoSocial(currentBean.getEmpresa().getNomeFantasia());
			currentBean.getUsuarioCriador().setLogin(currentBean.getEmail());
			currentBean.getUsuarioCriador().setAdministrador(true);
			currentBean.getUsuarioCriador().setUsernome(currentBean.getNome());
			currentBean.getUsuarioCriador().setDataCadastro(new Date());
			currentBean.getUsuarioCriador().setConfirmado(true);
			currentBean.getUsuarioCriador().setConta(currentBean);
			if(validaConta()){
				dao.save(currentBean);
				saved = true;
			}
		}catch (Exception e){
			view.showErrorMessage("Problemas ao registrar conta. Por favor, tente novamente em instantes.");
			e.printStackTrace();
		}
		
		if(saved){
			MailSender sender = new MailSender();
			try {
				sender.send(currentBean.getEmail(), "Bem vindo ao DotCompany ERP", "Bem vindo ao DotCompany ERP.</br> Clique <a href=\"http://www.dotcompanyerp.com.br\"> aqui</a> para acessar o sistema </br>",true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			view.notifySaveOK(currentBean);
			
		}
		
		
	}
	
	private boolean validaConta() {
		// TODO Auto-generated method stub
		Usuario u = usuarioDao.getUsuarioByLogin(currentBean.getEmail());
		ContaEmpresa c = dao.findByEmail(currentBean.getEmail());
		
		if(u != null || c!= null){
			view.showErrorMessage("E-mail j� é utilizado por outro Usuário do sistema.");
			return false;
		}else{
			Empresa emp = empresaDao.findByCNPJ(currentBean.getEmpresa().getCnpj());
			if(emp != null){
				view.showErrorMessage("CNPJ j� é utilizado por outro Usuário do sistema.");
				return false;
			}
		}
		return true;
	}

	@PostConstruct
	public void init() {
		// TODO Auto-generated method stub
		currentBean = new ContaEmpresa();
		currentBean.setEmpresa(new Empresa());
		Usuario u = new Usuario();
		Papel p = new Papel();
		p.setId(Papel.MASTER_ID);
		u.setPapel(p);
		currentBean.setPrimeiroUsuario(u);
		
	}

	public ContaEmpresa getCurrentBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}
	

	public void setView(CriaContaEmpresaView v){
		view = v;
	}
}
