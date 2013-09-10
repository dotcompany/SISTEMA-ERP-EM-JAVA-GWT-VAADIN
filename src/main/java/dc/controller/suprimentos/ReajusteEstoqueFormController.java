package dc.controller.suprimentos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.pessoal.Colaborador;
import dc.entidade.produto.Produto;
import dc.entidade.geral.Usuario;
import dc.entidade.suprimentos.ReajusteEstoque;
import dc.entidade.suprimentos.ReajusteEstoqueDetalhe;
import dc.servicos.dao.produto.ProdutoDAO;
import dc.servicos.dao.suprimentos.ReajusteEstoqueDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;
import dc.visao.suprimentos.ReajusteEstoqueFormView;
import dc.visao.suprimentos.ReajusteEstoqueFormView.TipoReajuste;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class ReajusteEstoqueFormController
extends CRUDFormController<ReajusteEstoque>{


	@Autowired
	ReajusteEstoqueDAO dao;
	
	@Autowired
	ProdutoDAO produtoDAO;

	private ReajusteEstoque currentBean;

	ReajusteEstoqueFormView subView;

	@Override
	protected String getNome() {
		return "Reajuste de Preço";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	public Colaborador buscaColaborador(){
		Usuario usuario = SecuritySessionProvider.getUsuario();
		Colaborador col = usuario.getColaborador();
		return col;
	}

	@Override
	protected void actionSalvar() {
		try {
			currentBean.setDataReajuste(subView.getDataReajuste().getValue());
			currentBean.setPorcentagem(new BigDecimal(subView.getPorcentagem().getValue()));
			currentBean.setTipo(((TipoReajuste)subView.getCmbTipoReajuste()
					.getValue()).getCodigo());
			currentBean.setColaborador(buscaColaborador());
			dao.saveOrUpdate(currentBean);
			mensagemSalvoOK();
		} catch (Exception e) {
			mensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = dao.find((Integer) id);
		subView.carregarTipoReajuste();
		subView.getDataReajuste().setValue(currentBean.getDataReajuste());
		subView.getPorcentagem().setValue(currentBean.getPorcentagem().toString());
	   subView.preencherDetalhesSubForm(currentBean.getDetalhes());
		subView.carregarView(currentBean);
		System.out.println("");
	}

	@Override
	protected void initSubView() {
		subView = new ReajusteEstoqueFormView(this);
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new ReajusteEstoque();
		subView.carregarTipoReajuste();
	}

	@Override
	public String getViewIdentifier() {
		return "requisicaoInternaForm";
	}

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;
		if (!Validator.validateObject(subView.getPorcentagem().getValue())) {
			adicionarErroDeValidacao(subView.getPorcentagem(),
					"Não pode ficar em branco");
			valido = false;
		}
		
		if (!Validator.validateObject(subView.getCmbTipoReajuste().getValue())) {
			adicionarErroDeValidacao(subView.getCmbTipoReajuste(),
					"Não pode ficar em branco");
			valido = false;
		}
		
		if (!Validator.validateNumber(subView.getPorcentagem().getValue())) {
			adicionarErroDeValidacao(subView.getPorcentagem(),
					"Valor Inválido");
			valido = false;
		}
		return valido;
	}

	@Override
	protected void quandoNovo() {
       subView.preencherDetalhesSubForm(currentBean.getDetalhes());
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try{
			dao.deleteAllByIds(ids);
			mensagemRemovidoOK();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		// TODO Auto-generated method stub
		remover(objetos);
	}
	
	public ReajusteEstoqueDetalhe novoDetalhe(){
		ReajusteEstoqueDetalhe detalhe = new ReajusteEstoqueDetalhe();
		currentBean.addDetalhe(detalhe);
		return detalhe;
	}
	
	public List<Produto> buscarProdutos() {
		return produtoDAO.getAll(Produto.class);
	}
	
	@Override
	public boolean isFullSized(){
		return true;
	}

}
