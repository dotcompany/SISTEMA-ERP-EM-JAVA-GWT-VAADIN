package dc.controller.comercial;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.entidade.comercial.CondicaoPagamento;
import dc.entidade.comercial.Frete;
import dc.entidade.comercial.TipoNotaFiscal;
import dc.entidade.folhapagamento.VendedorEntity;
import dc.entidade.pessoal.Cliente;
import dc.entidade.pessoal.Transportadora;
import dc.entidade.produto.Produto;
import dc.entidade.suprimentos.ReajusteEstoque;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.comercial.CondicaoPagamentoDAO;
import dc.servicos.dao.comercial.FreteDAO;
import dc.servicos.dao.comercial.TipoNotaFiscalDAO;
import dc.servicos.dao.folhapagamento.VendedorDAO;
import dc.servicos.dao.pessoal.ClienteDAO;
import dc.servicos.dao.pessoal.TransportadoraDAO;
import dc.servicos.dao.produto.ProdutoDAO;
import dc.servicos.util.Validator;
import dc.visao.comercial.FreteFormView;
import dc.visao.comercial.TipoNotaFiscalFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class FreteFormController extends CRUDFormController<Frete> {

	Frete currentBean;

	FreteFormView subView;

	@Autowired
	FreteDAO dao;

	
	@Autowired
	TransportadoraDAO transportadoraDAO;

	
	@Override
	public String getViewIdentifier() {
		return "FreteForm";
	}

	@Override
	protected boolean validaSalvar() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new Frete();

	}

	@Override
	protected void initSubView() {
		subView = new FreteFormView(this);

	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = dao.find(id);


			}

	@Override
	protected void actionSalvar() {

		
		dao.saveOrUpdate(currentBean);
		notifiyFrameworkSaveOK(currentBean);


	}

	@Override
	protected void quandoNovo() {
		// TODO Auto-generated method stub

	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Orçamento de Venda";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		dao.deleteAllByIds(ids);

	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isFullSized(){
		return true;
	}

	
	
	public BeanItemContainer<Transportadora> carregarTransportadoras(){
		BeanItemContainer<Transportadora> container = new BeanItemContainer<>(Transportadora.class);
		for(Transportadora c : transportadoraDAO.listaTodos()){
			container.addBean(c);
		}
		return container;
	}

	
	public BigDecimal formataValor(String valor){
		String format = "";
		format = valor.replace("R$","").
				substring(0,valor.indexOf(",")).

				replaceAll( ",","" ).trim();
		return new BigDecimal(format);
	}

	


}
