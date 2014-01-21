package dc.controller.diversos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.diversos.Pais;
import dc.servicos.dao.diversos.PaisDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /* Nessa classe temos a Extensão da classe principal que é
 *         crudListController Temos alguns métodos que pegamos, temos a
 *         configuração do Título da Tela; O Método do Button pesquisar, pegando
 *         um valor. e também ele pega algumas informações da classe
 *         FormController
 * 
 */

@Controller
@Scope("prototype")
public class PaisListController extends CRUDListController<Pais> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private PaisDAO dao;

	@Autowired
	private PaisFormController paisFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "nomeEn", "nomePtbr", "sigla2", "sigla3" };
	}

	@Override
	public Class<? super Pais> getEntityClass() {
		return Pais.class;
	}

	@Override
	protected String getTitulo() {
		return "Pais";
	}

	@Override
	protected List<Pais> pesquisa(String valor) {
		try {
			List<Pais> auxLista = (List<Pais>) dao.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<Pais>();
		}
	}

	@Override
	protected CRUDFormController<Pais> getFormController() {
		return paisFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaPais";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<Pais> pesquisaDefault() {
		try {
			List<Pais> auxLista = (List<Pais>) dao.listaTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<Pais>();
		}
	}

	/**
	 * **************************************
	 */

}