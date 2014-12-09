package dc.visao.framework.geral;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sun.istack.logging.Logger;

import dc.entidade.framework.EmpresaEntity;
import dc.entidade.framework.EmpresaSeguimento;
import dc.entidade.framework.FmModulo;
import dc.entidade.framework.SeguimentoEntity;
import dc.entidade.geral.Usuario;
import dc.entidade.sistema.ConfiguracaoContaEmpresa;
import dc.entidade.sistema.ContaEmpresa;
import dc.servicos.dao.framework.geral.EmpresaDAO;
import dc.servicos.dao.framework.geral.FmModuloDAO;
import dc.servicos.dao.framework.geral.SeguimentoDAO;
import dc.servicos.dao.sistema.ContaEmpresaDAO;
import dc.servicos.dao.sistema.UsuarioDAO;

@Component
@Scope("prototype")
public class ConfiguraNovaContaController implements Serializable,
		ViewController {

	private static final long serialVersionUID = -3176469284004748548L;

	@Autowired
	transient FmModuloDAO dao;

	@Autowired
	transient ContaEmpresaDAO contaDao;

	@Autowired
	transient UsuarioDAO userDao;

	@Autowired
	transient EmpresaDAO empresaDao;

	@Autowired
	transient SeguimentoDAO seguimentoDao;

	public SeguimentoDAO getSeguimentoDao() {
		return seguimentoDao;
	}

	public void setSeguimentoDao(SeguimentoDAO seguimentoDao) {
		this.seguimentoDao = seguimentoDao;
	}

	private ContaEmpresa currentBean;

	public static Logger logger = Logger
			.getLogger(ConfiguraNovaContaController.class);

	@PostConstruct
	public void init() {

	}

	public ContaEmpresa getCurrentBean() {
		return currentBean;
	}

	public void salvarPrimeiraPergunta(Object value, Integer contaId) {
		ConfiguracaoContaEmpresa conf = contaDao
				.findConfiguracaoByIdConta(contaId);
		EmpresaEntity empresa = empresaDao.findEmpresaByContaEmpresa(contaId);

		System.out.println(empresa);

		if (conf == null) {
			conf = new ConfiguracaoContaEmpresa();
			conf.setConta(contaDao.find(contaId));
		}

		conf.setPergunta1(String.valueOf(value));

		if (value != null && value instanceof SeguimentoEntity) {
			List<EmpresaSeguimento> empresaSeguimentos = new ArrayList<EmpresaSeguimento>();
			EmpresaSeguimento empresaSeguimento = new EmpresaSeguimento();
			empresaSeguimento.setEmpresa(empresa);
			empresaSeguimento.setSeguimento((SeguimentoEntity) value);
			empresaSeguimentos.add(empresaSeguimento);
			empresa.setEmpresaSeguimentoList(empresaSeguimentos);
		}

		contaDao.saveOrUpdate(conf);
		contaDao.saveOrUpdate(empresa);
	}

	public String getNomeUsuario(Usuario usuario) {
		return userDao.getNomeUsuario(usuario);
	}

	public void salvarSegundaPergunta(Object value, Integer contaId) {
		ConfiguracaoContaEmpresa conf = contaDao
				.findConfiguracaoByIdConta(contaId);
		conf.setPergunta2(String.valueOf(value));
		contaDao.saveOrUpdate(conf);
	}

	public List<FmModulo> buscaModulos() {
		return dao.getModulosSelecionaveis();
	}

	public void associaModulos(List<FmModulo> modulosSelecitonados,
			Integer contaId) {
		ConfiguracaoContaEmpresa conf = contaDao
				.findConfiguracaoByIdConta(contaId);
		conf.setModulos(modulosSelecitonados);
		List<FmModulo> obrigatorios = dao.getModulosObrigatorios();
		conf.getModulos().addAll(obrigatorios);
		contaDao.saveOrUpdate(conf);
	}

}