package dc.controller.geral.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.FornecedorEntity;
import dc.servicos.dao.geral.FornecedorDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class FornecedorListController extends CRUDListController<FornecedorEntity> {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private FornecedorFormController fornecedorFormController;
	
	@Autowired
	private FornecedorDAO dao;

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	protected CRUDFormController<FornecedorEntity> getFormController() {
		return fornecedorFormController;
	}

	@Override
	public String[] getColunas() {
		// TODO Auto-generated method stub
		return new String[] {"pessoa","situacaoForCli","atividadeForCli","desde", "contaRemetente","prazoMedioEntrega","quantidadeParcelas"};
	}

	@Override
	public Class<? super FornecedorEntity> getEntityClass() {
		// TODO Auto-generated method stub
		return FornecedorEntity.class;
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}
	
	public FornecedorListController() {
	}
	
	@Override
	protected List<FornecedorEntity> pesquisa(String valor) {
		try {
			
			List<FornecedorEntity> auxLista = this.dao.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<FornecedorEntity> pesquisaDefault() {
		try {
			
			List<FornecedorEntity> auxLista = this.dao.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}
