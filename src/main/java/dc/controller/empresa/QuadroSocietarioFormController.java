package dc.controller.empresa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.empresa.QuadroSocietario;
import dc.entidade.framework.Empresa;
import dc.entidade.produto.Produto;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.empresa.QuadroSocietarioDAO;
import dc.servicos.dao.produto.ProdutoDAO;
import dc.servicos.util.Validator;
import dc.visao.empresa.QuadroSocietarioFormView;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class QuadroSocietarioFormController extends CRUDFormController<QuadroSocietario> {

	QuadroSocietarioFormView subView;

	@Autowired
	QuadroSocietarioDAO dao;

	QuadroSocietario currentBean;

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
		currentBean = new QuadroSocietario();
	}

	@Override
	protected void initSubView() {
		subView = new QuadroSocietarioFormView(this);
	}

	@Override
	protected void carregar(Serializable id) {
		// TODO Auto-generated method stub
		currentBean = dao.find((Integer) id);
		subView.getDataRegistro().setValue(currentBean.getDataRegistro());
		subView.getTxtCapitalSocial().setValue(currentBean.getCapitalSocial().toString());
		subView.getTxtValorQuota().setValue(currentBean.getValorQuota().toString());
		subView.getTxtQuantidadeCotas().setValue(currentBean.getQuantidadeCotas().toString());
	}
	
	public Empresa empresaAtual(){
		return SecuritySessionProvider.getUsuario().getConta().getEmpresa();
	}

	@Override
	protected void actionSalvar() {
		String msgErro = "Erro ao realizar operação";
		try{
			String capitalSocial = subView.getTxtCapitalSocial().getValue(); 
			String valorQuota = subView.getTxtValorQuota().getValue();
			String quantidadeCotas = subView.getTxtQuantidadeCotas().getValue();
			
			Date dataRegistro = subView.getDataRegistro().getValue();
			if(dataRegistro == null){
				msgErro = "Informe a Data de Registro";
				throw new ErroValidacaoException(msgErro);
			}
			
			if(capitalSocial==null || capitalSocial.isEmpty()) {
				msgErro = "Informe o Capital Social";
				throw new ErroValidacaoException(msgErro);
			}
			
			if(valorQuota==null || valorQuota.isEmpty()) {
				msgErro = "Informe o Valor da Quota";
				throw new ErroValidacaoException(msgErro);
			}
			
			if(quantidadeCotas==null || quantidadeCotas.isEmpty()) {
				msgErro = "Informe a Quantidade de Cotas";
				throw new ErroValidacaoException(msgErro);
			}
						
			capitalSocial = formataValor(capitalSocial);
	    	valorQuota = formataValor(valorQuota);
	    	currentBean.setDataRegistro(dataRegistro);
			currentBean.setCapitalSocial(new BigDecimal(capitalSocial));
			currentBean.setValorQuota(new BigDecimal(valorQuota));
			currentBean.setQuantidadeCotas(new Integer(quantidadeCotas));
			currentBean.setEmpresa(empresaAtual());
			dao.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		}catch(ErroValidacaoException e){
			mensagemErro(msgErro);
		}catch(Exception e){
			mensagemErro(msgErro);
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
		return "Quadro Societário";
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
