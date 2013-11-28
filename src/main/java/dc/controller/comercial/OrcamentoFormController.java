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
import dc.entidade.comercial.ItemOrcamento;
import dc.entidade.comercial.Orcamento;
import dc.entidade.comercial.TipoNotaFiscal;
import dc.entidade.folhapagamento.VendedorEntity;
import dc.entidade.pessoal.Cliente;
import dc.entidade.pessoal.Transportadora;
import dc.entidade.produto.Produto;
import dc.entidade.suprimentos.ReajusteEstoque;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.comercial.CondicaoPagamentoDAO;
import dc.servicos.dao.comercial.ItemOrcamentoDAO;
import dc.servicos.dao.comercial.OrcamentoDAO;
import dc.servicos.dao.comercial.TipoNotaFiscalDAO;
import dc.servicos.dao.folhapagamento.VendedorDAO;
import dc.servicos.dao.pessoal.ClienteDAO;
import dc.servicos.dao.pessoal.TransportadoraDAO;
import dc.servicos.dao.produto.ProdutoDAO;
import dc.servicos.util.Validator;
import dc.visao.comercial.OrcamentoFormView;
import dc.visao.comercial.TipoNotaFiscalFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class OrcamentoFormController extends CRUDFormController<Orcamento> {

	Orcamento currentBean;

	OrcamentoFormView subView;

	@Autowired
	OrcamentoDAO dao;

	@Autowired
	CondicaoPagamentoDAO condicaoPagamentoDAO;

	@Autowired
	ClienteDAO clienteDAO;

	@Autowired
	TransportadoraDAO transportadoraDAO;

	@Autowired
	VendedorDAO vendedorDAO;
	
	@Autowired
	ItemOrcamentoDAO itemOrcamentoDAO;

	@Autowired
	ProdutoDAO produtoDAO;
	
	
	
	
	@Override
	public String getViewIdentifier() {
		return "orcamentoForm";
	}

	@Override
	protected boolean validaSalvar() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new Orcamento();

	}

	@Override
	protected void initSubView() {
		subView = new OrcamentoFormView(this);

	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = dao.find(id);


		subView.getCmbVendedor().setValue(currentBean.getVendedor());
		subView.getCmbCliente().setValue(currentBean.getCliente());
		subView.getCmbCondicaoPagamento().setValue(currentBean.getCondicaoPagamento());


		subView.getDataCadastro().setValue(currentBean.getDataCadastro());
		subView.getDataEntrega().setValue(currentBean.getDataEntrega());
		subView.getDataValidade().setValue(currentBean.getDataValidade());

		BigDecimal valorSubTotal = currentBean.getValorSubTotal();
		BigDecimal valorFrete = currentBean.getValorFrete();

		BigDecimal taxaComissao = currentBean.getTaxaComissao();
		BigDecimal valorComissao = currentBean.getValorComissao();

		BigDecimal taxaDesconto = currentBean.getTaxaDesconto();
		BigDecimal valorDesconto = currentBean.getValorDesconto();
		BigDecimal valorTotal = currentBean.getValorTotal();

		if(valorSubTotal!=null ){
			subView.getTxtValorSubTotal().setValue(valorSubTotal.toString());
		}

		if(valorFrete!=null ){
			subView.getTxtValorFrete().setValue(valorFrete.toString());
		}

		if(taxaComissao!=null ){
			subView.getTxtTaxaComissao().setValue(taxaComissao.toString());
		}

		if(valorComissao!=null ){
			subView.getTxtValorComissao().setValue(valorComissao.toString());
		}

		if(taxaDesconto!=null ){
			subView.getTxtTaxaDesconto().setValue(taxaDesconto.toString());
		}

		if(valorDesconto!=null ){
			subView.getTxtValorDesconto().setValue(valorDesconto.toString());
		}

		if(valorTotal!=null ){
			subView.getTxtValorTotal().setValue(valorTotal.toString());
		}
		
		List<ItemOrcamento> itens = itemOrcamentoDAO.findByOrcamento(currentBean);
		
		subView.preencheSubForm(itens);
	}

	@Override
	protected void actionSalvar() {

		CondicaoPagamento condicao = (CondicaoPagamento)subView.getCmbCondicaoPagamento().getValue();
		VendedorEntity vendedor = (VendedorEntity)subView.getCmbVendedor().getValue();
		Cliente cliente = (Cliente)subView.getCmbCliente().getValue();

		String valorSubTotal = subView.getTxtValorSubTotal().getValue();
		String valorFrete = subView.getTxtValorFrete().getValue();
		String taxaComissao = subView.getTxtTaxaComissao().getValue();

		String valorComissao = subView.getTxtValorComissao().getValue();
		String taxaDesconto = subView.getTxtTaxaDesconto().getValue();
		String valorDesconto = subView.getTxtValorDesconto().getValue();
		String valorTotal = subView.getTxtValorTotal().getValue();
		
		String observacao = subView.getTxtDescricao().getValue();

		if(Validator.validateObject(condicao)){
			currentBean.setCondicaoPagamento(condicao);
		}

		if(Validator.validateObject(vendedor)){
			currentBean.setVendedor(vendedor);
		}

		if(Validator.validateObject(cliente)){
			currentBean.setCliente(cliente);
		}

		Date dataCadastro = subView.getDataCadastro().getValue();
		Date dataEntrega = subView.getDataEntrega().getValue();
		Date dataValidade = subView.getDataValidade().getValue();

		currentBean.setDataCadastro(dataCadastro);
		currentBean.setDataEntrega(dataEntrega);
		currentBean.setDataValidade(dataValidade);
		currentBean.setObservacao(observacao);

		if(Validator.validateString(valorSubTotal)){
			currentBean.setValorSubTotal(formataValor(valorSubTotal));
		}

		if(Validator.validateString(valorFrete)){
			currentBean.setValorFrete(formataValor(valorFrete));
		}

		if(Validator.validateString(taxaComissao)){
			taxaComissao = taxaComissao.replace(",", ".");
			currentBean.setTaxaComissao(new BigDecimal(taxaComissao));
		}

		if(Validator.validateString(valorComissao)){
			currentBean.setValorComissao(formataValor(valorComissao));
		}

		if(Validator.validateString(taxaDesconto)){
			taxaDesconto = taxaDesconto.replace(",", ".");
			currentBean.setTaxaDesconto(new BigDecimal(taxaDesconto));
		}

		if(Validator.validateString(valorDesconto)){
			currentBean.setValorDesconto(formataValor(valorDesconto));
		}

		if(Validator.validateString(valorTotal)){
			currentBean.setValorTotal(formataValor(valorTotal));
		}

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

	public ItemOrcamento adicionarItem(){
		ItemOrcamento item = new ItemOrcamento();
		List<ItemOrcamento> lista = itemOrcamentoDAO.findByOrcamento(currentBean);
		if(lista==null) lista = new ArrayList<ItemOrcamento>();
		currentBean.setItens(lista);
		currentBean.getItens().add(item);
		item.setOrcamento(currentBean);
		return item;
	}

	public BeanItemContainer<CondicaoPagamento> carregarCondicoes(){
		BeanItemContainer<CondicaoPagamento> container = new BeanItemContainer<>(CondicaoPagamento.class);
		for(CondicaoPagamento c : condicaoPagamentoDAO.listarTodos()){
			container.addBean(c);
		}
		return container;
	}

	public BeanItemContainer<Cliente> carregarClientes(){
		BeanItemContainer<Cliente> container = new BeanItemContainer<>(Cliente.class);
		for(Cliente c : clienteDAO.listaTodos()){
			container.addBean(c);
		}
		return container;
	}

	public BeanItemContainer<Transportadora> carregarTransportadoras(){
		BeanItemContainer<Transportadora> container = new BeanItemContainer<>(Transportadora.class);
		for(Transportadora c : transportadoraDAO.listaTodos()){
			container.addBean(c);
		}
		return container;
	}

	public BeanItemContainer<VendedorEntity> carregarVendedores(){
		BeanItemContainer<VendedorEntity> container = new BeanItemContainer<>(VendedorEntity.class);
		for(VendedorEntity c : vendedorDAO.listarTodos()){
			container.addBean(c);
		}
		return container;
	}
	
	public BeanItemContainer<Produto> carregarProdutos(){
		BeanItemContainer<Produto> container = new BeanItemContainer<>(Produto.class);
		for(Produto p : produtoDAO.listaTodos()){
			container.addBean(p);
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

	public ProdutoDAO getProdutoDAO() {
		return produtoDAO;
	}

	public void setProdutoDAO(ProdutoDAO produtoDAO) {
		this.produtoDAO = produtoDAO;
	}



}
