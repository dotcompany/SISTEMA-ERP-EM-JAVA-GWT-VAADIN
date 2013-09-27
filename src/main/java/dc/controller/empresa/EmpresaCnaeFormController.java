package dc.controller.empresa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.empresa.EmpresaCnae;
import dc.entidade.empresa.QuadroSocietario;
import dc.entidade.framework.Empresa;
import dc.entidade.produto.Produto;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.empresa.EmpresaCnaeDAO;
import dc.servicos.dao.produto.ProdutoDAO;
import dc.servicos.util.Validator;
import dc.visao.empresa.EmpresaCnaeFormView;
import dc.visao.empresa.QuadroSocietarioFormView;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class EmpresaCnaeFormController extends CRUDFormController<EmpresaCnae> {

	EmpresaCnaeFormView subView;

	@Autowired
	EmpresaCnaeDAO dao;

	EmpresaCnae currentBean;

	@Override
	public String getViewIdentifier() {
		return "EstoqueForm";
	}

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;
			
		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new EmpresaCnae();
	}

	@Override
	protected void initSubView() {
		subView = new EmpresaCnaeFormView(this);
	}

	@Override
	protected void carregar(Serializable id) {
		// TODO Auto-generated method stub
		currentBean = dao.find((Integer) id);
	}
	
	public Empresa empresaAtual(){
		return SecuritySessionProvider.getUsuario().getConta().getEmpresa();
	}

	@Override
	protected void actionSalvar() {
		
			dao.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		
		
		
	}

	public String formataValor(String valor){
		String format = "";
		format = valor.replace("R$","").
				substring(0,valor.indexOf(",")).
				
				replaceAll( ",","" ).trim();
		return format;
	}

	@Override
	protected void quandoNovo() {
		try{
			//subView.filEstoqueDetalhesSubForm(currentBean.getDetalhes());
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
		return "Empresa Cnae";
	}

	@Override
	protected void remover(List<Serializable> ids) {
	
		dao.deleteAllByIds(ids);

	}
	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		System.out.println("");

	}
	
	@Override
	public boolean isFullSized(){
		return true;
	}
	
	
	
	

}

