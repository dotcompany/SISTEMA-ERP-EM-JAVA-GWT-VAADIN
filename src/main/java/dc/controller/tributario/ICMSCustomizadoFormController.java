package dc.controller.tributario;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.framework.Empresa;
import dc.entidade.geral.UF;
import dc.entidade.produto.Produto;
import dc.entidade.suprimentos.ContagemEstoque;
import dc.entidade.suprimentos.ContagemEstoqueDetalhe;
import dc.entidade.tributario.ICMSCustomizado;
import dc.entidade.tributario.ICMSCustomizadoDetalhe;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.geral.UFDAO;
import dc.servicos.dao.produto.ProdutoDAO;
import dc.servicos.dao.suprimentos.ContagemEstoqueDAO;
import dc.servicos.dao.tributario.ICMSCustomizadoDAO;
import dc.servicos.dao.tributario.ICMSCustomizadoDetalheDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;
import dc.visao.tributario.ICMSCustomizadoFormView;
import dc.visao.tributario.ICMSCustomizadoFormView.ORIGEM_MERCADORIA;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class ICMSCustomizadoFormController extends CRUDFormController<ICMSCustomizado> {

	ICMSCustomizadoFormView subView;

	@Autowired
	ICMSCustomizadoDAO dao;

	@Autowired 
	UFDAO ufDAO;

	@Autowired
	ICMSCustomizadoDetalheDAO detalheDAO;

	ICMSCustomizado currentBean;

	@Override
	public String getViewIdentifier() {
		return "icmsCustomizadoForm";
	}

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;


		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new ICMSCustomizado();
	}

	@Override
	protected void initSubView() {
		subView = new ICMSCustomizadoFormView(this);

	}

	@Override
	protected void carregar(Serializable id) {
		// TODO Auto-generated method stub
		currentBean = dao.find((Integer) id);
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
		subView.carregarOrigemMercadoria();
		subView.getOrigemMercadoria().setValue(ORIGEM_MERCADORIA.getOrigemMercadoria(currentBean.getOrigemMercadoria()));
	}

	public Empresa empresaAtual(){
		return SecuritySessionProvider.getUsuario().getConta().getEmpresa();
	}

	@Override
	protected void actionSalvar() {
		try{

			String descricao = subView.getTxtDescricao().getValue(); 
			String origem = "";

			if(!(Validator.validateString(descricao))) 
				throw new ErroValidacaoException("Informe o Campo Descrição");

			if(!(Validator.validateObject(subView.getOrigemMercadoria().getValue()))) {
				throw new ErroValidacaoException("Informe o Campo Origem da Mercadoria");
			}
			else{
				origem = ((ORIGEM_MERCADORIA)(subView.getOrigemMercadoria().getValue())).getCodigo();
			}

			currentBean.setDescricao(subView.getTxtDescricao().getValue());
			currentBean.setOrigemMercadoria(origem);
			currentBean.setEmpresa(empresaAtual());

			dao.saveOrUpdate(currentBean);		

			notifiyFrameworkSaveOK(currentBean);
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
		return "ICMS Customizado";
	}


	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
	}

	@Override
	public boolean isFullSized(){
		return true;
	}

	@Override
	protected void remover(List<Serializable> ids) {
		// TODO Auto-generated method stub

	}

	public ICMSCustomizadoDetalhe novoDetalhe(){
		ICMSCustomizadoDetalhe detalhe = new ICMSCustomizadoDetalhe();
		currentBean.adicionarDetalhe(detalhe);
		return detalhe;
	}

	public List<UF> listarUfs(){
		return ufDAO.listaTodos();
	}

	public String formataValor(String valor){
		String format = "";
		format = valor.replace("R$","").
				substring(0,valor.indexOf(",")).

				replaceAll( ",","" ).trim();
		return format;
	}

}

