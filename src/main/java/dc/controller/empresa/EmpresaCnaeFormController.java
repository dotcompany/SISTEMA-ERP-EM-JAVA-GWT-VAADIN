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
import dc.entidade.geral.Cnae;
import dc.entidade.produto.Produto;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.empresa.EmpresaCnaeDAO;
import dc.servicos.dao.geral.CnaeDAO;
import dc.servicos.dao.produto.ProdutoDAO;
import dc.servicos.util.Validator;
import dc.visao.empresa.EmpresaCnaeFormView;
import dc.visao.empresa.EmpresaCnaeFormView.PRINCIPAL;
import dc.visao.empresa.QuadroSocietarioFormView;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;
import dc.visao.suprimentos.NotaFiscalFormView.FORMA_EMISSAO;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class EmpresaCnaeFormController extends CRUDFormController<EmpresaCnae> {

	EmpresaCnaeFormView subView;

	@Autowired
	EmpresaCnaeDAO dao;
	
	@Autowired
	private CnaeDAO cnaeDAO;

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
		subView.getCmbCnae().setValue(currentBean.getCnae());
		subView.getCmbPrincipal().setValue(PRINCIPAL.getPrincipal(currentBean.getPrincipal()));
		subView.getTxtRamoAtividade().setValue(currentBean.getRamoAtividade());
		subView.getTxtObjetoSocial().setValue(currentBean.getObjetoSocial());
	}
	


	@Override
	protected void actionSalvar() {
		String msgErro = "Erro ao realizar operação";
		
		  try{
			 Cnae cnae = (Cnae) subView.getCmbCnae().getValue();
			 String principal = (((PRINCIPAL)subView.getCmbPrincipal()
						.getValue()).getCodigo());
			 String ramoAtividade = subView.getTxtRamoAtividade().getValue();
			 String objetoSocial = subView.getTxtObjetoSocial().getValue();
			 
			 if(cnae == null){
				 msgErro = "Informe o CNAE!";
				 throw new ErroValidacaoException(msgErro);
			 }
			 
			 if(principal == null){
				 msgErro = "Informe Principal!";
				 throw new ErroValidacaoException(msgErro);
			 }
			 
			 if(ramoAtividade == null){
				 msgErro = "Informe Ramo de Atividade!";
				 throw new ErroValidacaoException(msgErro);
			 }
			 
			 if(objetoSocial == null){
				 msgErro = "Informe Objeto Social!";
				 throw new ErroValidacaoException(msgErro);
			 }
			 
			    currentBean.setEmpresa(empresaAtual());
			    currentBean.setCnae(cnae);  
			    currentBean.setPrincipal(principal);
			    currentBean.setRamoAtividade(ramoAtividade);
			    currentBean.setObjetoSocial(objetoSocial);
				dao.saveOrUpdate(currentBean);
				notifiyFrameworkSaveOK(this.currentBean);
		  
		  }catch(ErroValidacaoException e){
			  mensagemErro(e.montaMensagemErro());
		  }catch(Exception e){
			  e.printStackTrace();
			  
		  }
		
		   
				
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
	
	
	 public List<Cnae> trazerListaCnae(){
		 List<Cnae> lista = cnaeDAO.listarTodos();
		 return lista;
	 }
	

}

