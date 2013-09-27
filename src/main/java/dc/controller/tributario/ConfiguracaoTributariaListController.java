package dc.controller.tributario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tributario.ConfiguracaoTributaria;
import dc.servicos.dao.tributario.ConfiguracaoTributariaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class ConfiguracaoTributariaListController extends
		CRUDListController<ConfiguracaoTributaria> {

	@Autowired
	ConfiguracaoTributariaDAO dao;

	@Autowired
	ConfiguracaoTributariaFormController formController;

	@Override
	protected String[] getColunas() {
		return new String[] { "grupoTributario", "operacaoFiscal" };
	}

	@Override
	protected String getTitulo() {
		return "Configuração Tributária";
	}

	@Override
	protected List<ConfiguracaoTributaria> pesquisa(String valor) {
		return new ArrayList<>();
	}

	@Override
	public String getViewIdentifier() {
		return "listaConfiguracaoTributaria";
	}

	@Override
	protected CRUDFormController<ConfiguracaoTributaria> getFormController() {
		return formController;
	}

	@Override
	protected Class<? super ConfiguracaoTributaria> getEntityClass() {
		return ConfiguracaoTributaria.class;
	}

	@Override
	protected List<ConfiguracaoTributaria> pesquisaDefault() {
		/*
		 * List<ContagemEstoque> lista = new ArrayList<>(); try{ lista =
		 * dao.getAll(ContagemEstoque.class); }catch(Exception e){
		 * e.printStackTrace(); }
		 */
		//
		return new ArrayList<>();
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

}