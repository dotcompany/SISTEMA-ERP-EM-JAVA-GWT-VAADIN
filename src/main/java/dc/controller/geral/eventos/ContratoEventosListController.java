package dc.controller.geral.eventos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.eventos.ContratoEventosEntity;
import dc.servicos.dao.geral.eventos.ContratoEventosDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class ContratoEventosListController extends CRUDListController<ContratoEventosEntity> {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private ContratoEventosFormController contratoFormController;
	
	@Autowired
	private ContratoEventosDAO contratoEventosDAO;

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	protected CRUDFormController<ContratoEventosEntity> getFormController() {
		// TODO Auto-generated method stub
		return contratoFormController;
	}

	@Override
	public String[] getColunas() {
		// TODO Auto-generated method stub
		return new String[] { "unidade", "curso", "dataContrato", "dataPrimeiroEvento" };

	}

	@Override
	public Class<? super ContratoEventosEntity> getEntityClass() {
		// TODO Auto-generated method stub
		return ContratoEventosEntity.class;
	}

	@Override
	protected List<ContratoEventosEntity> pesquisa(String valor) {
		// TODO Auto-generated method stub
		
		try {
			List<ContratoEventosEntity> auxLista = this.contratoEventosDAO.procuraNomeContendo(valor);
			
			//List<ContratoEventosEntity> auxLista = (List<ContratoEventosEntity>) this.contratoFormController.getBusiness().fullTextSearch(valor);


			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}

	}

	@Override
	protected List<ContratoEventosEntity> pesquisaDefault() {
		// TODO Auto-generated method stub
		try {
			List<ContratoEventosEntity> auxLista = this.contratoEventosDAO.listaTodos();
			
			//List<ContratoEventosEntity> auxLista = (List<ContratoEventosEntity>) this.contratoFormController.getBusiness().getAll(getEntityClass());


			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}

	}

	@Override
	protected String getTitulo() {
		// TODO Auto-generated method stub
		return super.getTitulo(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

}
