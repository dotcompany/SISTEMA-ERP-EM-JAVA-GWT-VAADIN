package dc.controller.tributario;


import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.framework.Empresa;
import dc.entidade.tributario.ConfiguracaoTributaria;
import dc.entidade.tributario.GrupoTributario;
import dc.entidade.tributario.OperacaoFiscal;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.tributario.ConfiguracaoTributariaDAO;
import dc.servicos.dao.tributario.GrupoTributarioDAO;
import dc.servicos.dao.tributario.OperacaoFiscalDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;
import dc.visao.tributario.ConfiguracaoTributariaFormView;
import dc.visao.tributario.GrupoTributarioFormView;
import dc.visao.tributario.GrupoTributarioFormView.ORIGEM_MERCADORIA;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class ConfiguracaoTributariaFormController extends CRUDFormController<ConfiguracaoTributaria> {

	ConfiguracaoTributariaFormView subView;

	@Autowired
	ConfiguracaoTributariaDAO dao;

	@Autowired
	GrupoTributarioDAO grupoTributarioDAO;

	@Autowired
	OperacaoFiscalDAO operacaoFiscalDAO;

	ConfiguracaoTributaria currentBean;

	String CAMPO_EM_BRANCO = "Não pode ficar em branco";

	@Override
	public String getViewIdentifier() {

		return "ConfiguracaoTributariaForm";
	}

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;




		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new ConfiguracaoTributaria();
	}

	@Override
	protected void initSubView() {
		subView = new ConfiguracaoTributariaFormView(this);

	}

	protected void carregar(Serializable id) {
		// TODO Auto-generated method stub
		currentBean = dao.find((Integer) id);
	}

	public Empresa empresaAtual(){
		return SecuritySessionProvider.getUsuario().getConta().getEmpresa();
	}

	@Override
	protected void actionSalvar() {
		try{
			currentBean.setEmpresa(empresaAtual());
			currentBean.setGrupoTributario((GrupoTributario)subView.getCmbGrupoTributario().getValue());
			currentBean.setOperacaoFiscal((OperacaoFiscal)subView.getCmbOperacaoFiscal().getValue());
			dao.saveOrUpdate(currentBean);
			mensagemSalvoOK();
		}catch(Exception e){
			mensagemErro("Erro!!");
			e.printStackTrace();
		}
	}

	@Override
	protected void quandoNovo() {
		try{
			//subView.filContagemEstoqueDetalhesSubForm(currentBean.getContagemDetalhes());
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Configuracao Tributaria";
	}

	@Override
	protected void remover(List<Serializable> ids) {


	}
	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		System.out.println("");

	}

	@Override
	public boolean isFullSized(){
		return true;
	}

	public List<GrupoTributario> trazerGrupos(){
		return grupoTributarioDAO.listaTodos();
	}

	public List<OperacaoFiscal> trazerOperacoes(){
		return operacaoFiscalDAO.listaTodos();
	}


}
