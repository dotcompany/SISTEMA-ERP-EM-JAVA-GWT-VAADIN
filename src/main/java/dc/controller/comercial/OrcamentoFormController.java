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
import dc.entidade.comercial.ItemOrcamento;
import dc.entidade.comercial.Orcamento;
import dc.entidade.folhapagamento.VendedorEntity;
import dc.entidade.geral.pessoal.ClienteEntity;
import dc.entidade.geral.pessoal.TransportadoraEntity;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.comercial.CondicaoPagamentoDAO;
import dc.servicos.dao.comercial.FreteDAO;
import dc.servicos.dao.comercial.ItemOrcamentoDAO;
import dc.servicos.dao.comercial.OrcamentoDAO;
import dc.servicos.dao.folhapagamento.VendedorDAO;
import dc.servicos.dao.geral.pessoal.ClienteDAO;
import dc.servicos.dao.geral.pessoal.TransportadoraDAO;
import dc.servicos.dao.geral.produto.ProdutoDAO;
import dc.servicos.util.Validator;
import dc.visao.comercial.OrcamentoFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class OrcamentoFormController extends CRUDFormController<Orcamento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	@Autowired
	FreteDAO freteDAO;

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
		subView.getCmbCondicaoPagamento().setValue(
				currentBean.getCondicaoPagamento());
		subView.getCmbTransportadora()
				.setValue(currentBean.getTransportadora());

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

		if (valorSubTotal != null) {
			subView.getTxtValorSubTotal().setValue(valorSubTotal.toString());
		}

		if (valorFrete != null) {
			subView.getTxtValorFrete().setValue(valorFrete.toString());
		}

		if (taxaComissao != null) {
			subView.getTxtTaxaComissao().setValue(taxaComissao.toString());
		}

		if (valorComissao != null) {
			subView.getTxtValorComissao().setValue(valorComissao.toString());
		}

		if (taxaDesconto != null) {
			subView.getTxtTaxaDesconto().setValue(taxaDesconto.toString());
		}

		if (valorDesconto != null) {
			subView.getTxtValorDesconto().setValue(valorDesconto.toString());
		}

		if (valorTotal != null) {
			subView.getTxtValorTotal().setValue(valorTotal.toString());
		}

		List<ItemOrcamento> itens = itemOrcamentoDAO
				.findByOrcamento(currentBean);

		subView.preencheSubForm(itens);
	}

	@Override
	protected void actionSalvar() {
		try {
			CondicaoPagamento condicao = (CondicaoPagamento) subView
					.getCmbCondicaoPagamento().getValue();
			VendedorEntity vendedor = (VendedorEntity) subView.getCmbVendedor()
					.getValue();
			ClienteEntity cliente = (ClienteEntity) subView.getCmbCliente()
					.getValue();
			TransportadoraEntity transportadora = (TransportadoraEntity) subView
					.getCmbTransportadora().getValue();

			String valorSubTotal = subView.getTxtValorSubTotal().getValue();
			String valorFrete = subView.getTxtValorFrete().getValue();
			String taxaComissao = subView.getTxtTaxaComissao().getValue();

			String valorComissao = subView.getTxtValorComissao().getValue();
			String taxaDesconto = subView.getTxtTaxaDesconto().getValue();
			String valorDesconto = subView.getTxtValorDesconto().getValue();
			String valorTotal = subView.getTxtValorTotal().getValue();

			String observacao = subView.getTxtDescricao().getValue();

			if (!Validator.validateObject(vendedor)) {
				throw new ErroValidacaoException("Informe o Vendedor");
			}

			if (!Validator.validateObject(cliente)) {
				throw new ErroValidacaoException("Informe o Cliente");
			}

			if (!Validator.validateObject(condicao)) {
				throw new ErroValidacaoException(
						"Informe a Condição de Pagamento");
			}

			if (Validator.validateObject(condicao)) {
				currentBean.setCondicaoPagamento(condicao);
			}

			if (Validator.validateObject(vendedor)) {
				currentBean.setVendedor(vendedor);
			}

			if (Validator.validateObject(cliente)) {
				currentBean.setCliente(cliente);
			}

			if (Validator.validateObject(transportadora)) {
				currentBean.setTransportadora(transportadora);
			}

			Date dataCadastro = subView.getDataCadastro().getValue();
			Date dataEntrega = subView.getDataEntrega().getValue();
			Date dataValidade = subView.getDataValidade().getValue();

			currentBean.setDataCadastro(dataCadastro);
			currentBean.setDataEntrega(dataEntrega);
			currentBean.setDataValidade(dataValidade);
			currentBean.setObservacao(observacao);

			if (Validator.validateString(valorSubTotal)) {
				currentBean.setValorSubTotal(formataValor(valorSubTotal));
			}

			if (Validator.validateString(valorFrete)) {
				currentBean.setValorFrete(formataValor(valorFrete));
			}

			if (Validator.validateString(taxaComissao)) {
				taxaComissao = taxaComissao.replace(",", ".");
				currentBean.setTaxaComissao(new BigDecimal(taxaComissao));
			}

			if (Validator.validateString(valorComissao)) {
				currentBean.setValorComissao(formataValor(valorComissao));
			}

			if (Validator.validateString(taxaDesconto)) {
				taxaDesconto = taxaDesconto.replace(",", ".");
				currentBean.setTaxaDesconto(new BigDecimal(taxaDesconto));
			}

			if (Validator.validateString(valorDesconto)) {
				currentBean.setValorDesconto(formataValor(valorDesconto));
			}

			if (Validator.validateString(valorTotal)) {
				currentBean.setValorTotal(formataValor(valorTotal));
			}

			dao.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(currentBean);
		} catch (ErroValidacaoException e) {
			mensagemErro(e.montaMensagemErro());
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		try {
			for (Serializable id : ids) {
				Orcamento orcamento = dao.find(id);
				List<ItemOrcamento> itens = itemOrcamentoDAO
						.findByOrcamento(orcamento);

				for (ItemOrcamento item : itens) {
					itemOrcamentoDAO.delete(item);
				}
			}

			dao.deleteAllByIds(ids);
			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();
			mensagemErro("Problema ao remover!");
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	public ItemOrcamento adicionarItem() {
		ItemOrcamento item = new ItemOrcamento();
		List<ItemOrcamento> lista = itemOrcamentoDAO
				.findByOrcamento(currentBean);

		if (lista == null)
			lista = new ArrayList<ItemOrcamento>();

		currentBean.setItens(lista);
		currentBean.getItens().add(item);
		item.setOrcamento(currentBean);

		return item;
	}

	public BeanItemContainer<CondicaoPagamento> carregarCondicoes() {
		BeanItemContainer<CondicaoPagamento> container = new BeanItemContainer<>(
				CondicaoPagamento.class);

		for (CondicaoPagamento c : condicaoPagamentoDAO.listarTodos()) {
			container.addBean(c);
		}

		return container;
	}

	public BeanItemContainer<ClienteEntity> carregarClientes() {
		BeanItemContainer<ClienteEntity> container = new BeanItemContainer<>(
				ClienteEntity.class);

		for (ClienteEntity c : clienteDAO.listaTodos()) {
			container.addBean(c);
		}

		return container;
	}

	public BeanItemContainer<TransportadoraEntity> carregarTransportadoras() {
		BeanItemContainer<TransportadoraEntity> container = new BeanItemContainer<>(
				TransportadoraEntity.class);

		for (TransportadoraEntity c : transportadoraDAO.listaTodos()) {
			container.addBean(c);
		}

		return container;
	}

	public BeanItemContainer<VendedorEntity> carregarVendedores() {
		BeanItemContainer<VendedorEntity> container = new BeanItemContainer<>(
				VendedorEntity.class);

		for (VendedorEntity c : vendedorDAO.listarTodos()) {
			container.addBean(c);
		}

		return container;
	}

	public BeanItemContainer<ProdutoEntity> carregarProdutos() {
		BeanItemContainer<ProdutoEntity> container = new BeanItemContainer<>(
				ProdutoEntity.class);

		for (ProdutoEntity p : produtoDAO.listaTodos()) {
			container.addBean(p);
		}

		return container;
	}

	public BeanItemContainer<Frete> carregarFretes() {
		BeanItemContainer<Frete> container = new BeanItemContainer<>(
				Frete.class);

		for (Frete f : freteDAO.listaTodos()) {
			container.addBean(f);
		}

		return container;
	}

	public BigDecimal formataValor(String valor) {
		String format = "";
		format = valor.replace("R$", "").substring(0, valor.indexOf(","))
				.replaceAll(",", "").trim();

		return new BigDecimal(format);
	}

	public ProdutoDAO getProdutoDAO() {
		return produtoDAO;
	}

	public void setProdutoDAO(ProdutoDAO produtoDAO) {
		this.produtoDAO = produtoDAO;
	}

	@Override
	public Orcamento getModelBean() {
		return currentBean;
	}

}