package dc.controller.tributario;


import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.framework.Empresa;
import dc.entidade.produto.Produto;
import dc.entidade.suprimentos.ContagemEstoque;
import dc.entidade.suprimentos.ContagemEstoqueDetalhe;
import dc.entidade.tributario.OperacaoFiscal;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.produto.ProdutoDAO;
import dc.servicos.dao.suprimentos.ContagemEstoqueDAO;
import dc.servicos.dao.tributario.OperacaoFiscalDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;
import dc.visao.tributario.OperacaoFiscalFormView;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class OperacaoFiscalFormController extends CRUDFormController<OperacaoFiscal> {

	OperacaoFiscalFormView subView;

	@Autowired
	OperacaoFiscalDAO dao;



	OperacaoFiscal currentBean;

	String CAMPO_EM_BRANCO = "Não pode ficar em branco";

	@Override
	public String getViewIdentifier() {
		return "operacaoFiscalForm";
	}

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;




		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new OperacaoFiscal();
	}

	@Override
	protected void initSubView() {
		subView = new OperacaoFiscalFormView(this);

	}

	@Override
	protected void carregar(Serializable id) {
		// TODO Auto-generated method stub
		currentBean = dao.find((Integer) id);
		subView.getCfop().setValue(currentBean.getCfop().toString());
		subView.getDescricao().setValue(currentBean.getDescricao());
		subView.getDescricaoNaNf().setValue(currentBean.getDescricaoNaNF());
		subView.getObservacao().setValue(currentBean.getObservacao());
	}

	public Empresa empresaAtual(){
		return SecuritySessionProvider.getUsuario().getConta().getEmpresa();
	}

	@Override
	protected void actionSalvar() {
		try{
			String cfopStr = subView.getCfop().getValue();
			if(!(Validator.validateNumber(cfopStr))) 
				throw new ErroValidacaoException("Informe apenas números no campo CFOP");

			String descricao = subView.getDescricao().getValue();
			if(!(Validator.validateString(descricao))) 
				throw new ErroValidacaoException("Informe o Campo Descrição");

			String descricaoNF = subView.getDescricaoNaNf().getValue();
			if(!(Validator.validateString(descricaoNF))) 
				throw new ErroValidacaoException("Informe o Campo Descrição na NF");

			currentBean.setCfop(new Integer(cfopStr)); 
			currentBean.setDescricao(descricao);
			currentBean.setDescricaoNaNF(descricaoNF);
			currentBean.setObservacao(subView.getObservacao().getValue());
			currentBean.setEmpresa(empresaAtual());
			dao.saveOrUpdate(currentBean);
			mensagemSalvoOK();
		}catch(ErroValidacaoException e){
			mensagemErro(e.montaMensagemErro());
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
		return "Operacao Fiscal";
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





}
