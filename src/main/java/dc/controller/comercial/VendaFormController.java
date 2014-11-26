package dc.controller.comercial;

import java.io.Serializable;
import java.math.BigDecimal;
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
import dc.entidade.comercial.Venda;
import dc.entidade.comercial.VendaDetalhe;
import dc.entidade.folhapagamento.VendedorEntity;
import dc.entidade.pessoal.ClienteEntity;
import dc.entidade.produto.Produto;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.comercial.CondicaoPagamentoDAO;
import dc.servicos.dao.comercial.ItemOrcamentoDAO;
import dc.servicos.dao.comercial.OrcamentoDAO;
import dc.servicos.dao.comercial.TipoNotaFiscalDAO;
import dc.servicos.dao.comercial.VendaDAO;
import dc.servicos.dao.comercial.VendaDetalheDAO;
import dc.servicos.dao.folhapagamento.VendedorDAO;
import dc.servicos.dao.pessoal.ClienteDAO;
import dc.servicos.dao.produto.ProdutoDAO;
import dc.servicos.util.Validator;
import dc.visao.comercial.VendaFormView;
import dc.visao.comercial.VendaFormView.TIPO_VENDA;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class VendaFormController extends CRUDFormController<Venda> {

	Venda currentBean;

	VendaFormView subView;

	@Autowired
	VendaDAO dao;

	@Autowired
	VendaDetalheDAO detalheDAO;

	@Autowired
	VendedorDAO vendedorDAO;

	@Autowired
	ClienteDAO clienteDAO;

	@Autowired
	CondicaoPagamentoDAO condicaoPagamentoDAO;

	@Autowired
	TipoNotaFiscalDAO tipoNotaFiscalDAO;

	@Autowired
	ProdutoDAO produtoDAO;

	@Autowired
	OrcamentoDAO orcamentoDAO;

	@Autowired
	ItemOrcamentoDAO itemOrcamentoDAO;

	@Override
	public String getViewIdentifier() {
		return "tipoNotaForm";
	}

	@Override
	protected boolean validaSalvar() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new Venda();

	}

	@Override
	protected void initSubView() {
		subView = new VendaFormView(this);

	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = dao.find(id);

		if (currentBean.getOrcamento() == null) {
			subView.getCmbTipoVenda().setValue(TIPO_VENDA.VENDA_DIRETA);
			subView.getCmbOrcamento().setReadOnly(true);
		} else {
			subView.getCmbTipoVenda().setValue(TIPO_VENDA.ORCAMENTO);
			subView.getCmbOrcamento().setReadOnly(false);
			subView.getCmbOrcamento().setValue(currentBean.getOrcamento());
		}

		subView.getCmbCondicoesPagamento().setValue(currentBean.getCondicaoPagamento());

		subView.getCmbVendedor().setValue(currentBean.getVendedor());
		subView.getCmbTipoNotaFiscal().setValue(currentBean.getTipoNotaFiscal());
		subView.getCmbCliente().setValue(currentBean.getCliente());

		subView.getDataSaida().setValue(currentBean.getDataSaida());
		subView.getDataVenda().setValue(currentBean.getDataVenda());
		subView.getTxtHoraSaida().setValue(currentBean.getHoraSaida());

		if (currentBean.getNumeroFatura() != null) {
			subView.getTxtNumeroFatura().setValue(currentBean.getNumeroFatura().toString());
		}

		subView.getTxtLocalEntrega().setValue(currentBean.getLocalEntrega());
		subView.getTxtLocalCobranca().setValue(currentBean.getLocalCobranca());

		if (currentBean.getValorSubTotal() != null) {
			subView.getTxtValorSubTotal().setValue(currentBean.getValorSubTotal().toString());
		}
		if (currentBean.getValorFrete() != null) {
			subView.getTxtValorFrete().setValue(currentBean.getValorFrete().toString());
		}
		if (currentBean.getTaxaComissao() != null) {
			subView.getTxtTaxaComissao().setValue(currentBean.getTaxaComissao().toString());
		}
		if (currentBean.getValorComissao() != null) {
			subView.getTxtValorComissao().setValue(currentBean.getValorComissao().toString());
		}
		if (currentBean.getTaxaDesconto() != null) {
			subView.getTxtTaxaDesconto().setValue(currentBean.getTaxaDesconto().toString());
		}
		if (currentBean.getValorDesconto() != null) {
			subView.getTxtValorDesconto().setValue(currentBean.getValorDesconto().toString());
		}
		if (currentBean.getValorTotal() != null) {
			subView.getTxtValorTotal().setValue(currentBean.getValorTotal().toString());
		}

		subView.getTxtObservacoes().setValue(currentBean.getObservacao());

		List<VendaDetalhe> detalhes = detalheDAO.detalhesPorVenda(currentBean);
		if (detalhes != null) {
			subView.preencheSubForm(detalhes);
		}
	}

	@Override
	protected void actionSalvar() {

		try {

			TipoNotaFiscal tipoNotaFiscal = (TipoNotaFiscal) subView.getCmbTipoNotaFiscal().getValue();
			if (!Validator.validateObject(tipoNotaFiscal)) {
				throw new ErroValidacaoException("Informe Tipo de Nota Fiscal");
			}
			ClienteEntity cliente = (ClienteEntity) subView.getCmbCliente().getValue();
			if (!Validator.validateObject(cliente)) {
				throw new ErroValidacaoException("Informe o Cliente");
			}

			VendedorEntity vendedor = (VendedorEntity) subView.getCmbVendedor().getValue();
			if (!Validator.validateObject(vendedor)) {
				throw new ErroValidacaoException("Informe o Vendedor");
			}

			CondicaoPagamento condicao = (CondicaoPagamento) subView.getCmbCondicoesPagamento().getValue();
			if (!Validator.validateObject(condicao)) {
				throw new ErroValidacaoException("Informe a Condição de Pagamento");
			}

			Orcamento orcamento = (Orcamento) subView.getCmbOrcamento().getValue();
			currentBean.setOrcamento(orcamento);

			currentBean.setTipoNotaFiscal(tipoNotaFiscal);
			currentBean.setCliente(cliente);
			currentBean.setVendedor(vendedor);
			currentBean.setCondicaoPagamento(condicao);

			Date dataVenda = subView.getDataVenda().getValue();
			currentBean.setDataVenda(dataVenda);

			Date dataSaida = subView.getDataSaida().getValue();
			currentBean.setDataSaida(dataSaida);

			String horaSaida = subView.getTxtHoraSaida().getValue();
			currentBean.setHoraSaida(horaSaida);

			String fatura = subView.getTxtNumeroFatura().getValue();
			if (Validator.validateString(fatura)) {
				Integer numeroFatura = new Integer(fatura);
				currentBean.setNumeroFatura(numeroFatura);
			}

			String localEntrega = subView.getTxtLocalEntrega().getValue();
			currentBean.setLocalEntrega(localEntrega);

			String localCobranca = subView.getTxtLocalCobranca().getValue();
			currentBean.setLocalCobranca(localCobranca);

			String valorSubTotal = subView.getTxtValorSubTotal().getValue();
			if (Validator.validateString(valorSubTotal)) {
				valorSubTotal = formataMoeda(valorSubTotal);
				currentBean.setValorSubTotal(new BigDecimal(valorSubTotal));
			}

			String valorFrete = subView.getTxtValorFrete().getValue();
			if (Validator.validateString(valorFrete)) {
				valorFrete = formataMoeda(valorFrete);
				currentBean.setValorFrete(new BigDecimal(valorFrete));
			}

			String taxaComissao = subView.getTxtTaxaComissao().getValue();
			if (Validator.validateString(taxaComissao)) {
				taxaComissao = formataBigDecimal(taxaComissao);
				currentBean.setTaxaComissao(new BigDecimal(taxaComissao));
			}

			String valorComissao = subView.getTxtValorComissao().getValue();
			if (Validator.validateString(valorComissao)) {
				valorComissao = formataMoeda(valorComissao);
				currentBean.setValorComissao(new BigDecimal(valorComissao));
			}

			String taxaDesconto = subView.getTxtTaxaDesconto().getValue();
			if (Validator.validateString(taxaDesconto)) {
				taxaDesconto = formataBigDecimal(taxaDesconto);
				currentBean.setTaxaDesconto(new BigDecimal(taxaDesconto));
			}

			String valorDesconto = subView.getTxtValorDesconto().getValue();
			if (Validator.validateString(valorDesconto)) {
				valorDesconto = formataMoeda(valorDesconto);
				currentBean.setValorDesconto(new BigDecimal(valorDesconto));
			}

			String valorTotal = subView.getTxtValorTotal().getValue();
			if (Validator.validateString(valorTotal)) {
				valorTotal = formataMoeda(valorTotal);
				currentBean.setValorTotal(new BigDecimal(valorTotal));
			}

			String observacao = subView.getTxtObservacoes().getValue();
			currentBean.setObservacao(observacao);

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
		return "Venda";
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
	public boolean isFullSized() {
		return true;
	}

	public VendaDetalhe novoDetalhe() {
		VendaDetalhe detalhe = new VendaDetalhe();
		List<VendaDetalhe> detalhes = detalheDAO.detalhesPorVenda(currentBean);
		detalhe.setVenda(currentBean);
		detalhes.add(detalhe);
		currentBean.setDetalhes(detalhes);
		return detalhe;
	}

	public BeanItemContainer<VendedorEntity> carregarVendedores() {
		BeanItemContainer<VendedorEntity> container = new BeanItemContainer<>(VendedorEntity.class);
		for (VendedorEntity c : vendedorDAO.listarTodos()) {
			container.addBean(c);
		}
		return container;
	}

	public BeanItemContainer<ClienteEntity> carregarClientes() {
		BeanItemContainer<ClienteEntity> container = new BeanItemContainer<>(ClienteEntity.class);
		for (ClienteEntity c : clienteDAO.listaTodos()) {
			container.addBean(c);
		}
		return container;
	}

	public BeanItemContainer<CondicaoPagamento> carregarCondicoesPagamento() {
		BeanItemContainer<CondicaoPagamento> container = new BeanItemContainer<>(CondicaoPagamento.class);
		for (CondicaoPagamento c : condicaoPagamentoDAO.listarTodos()) {
			container.addBean(c);
		}
		return container;
	}

	public BeanItemContainer<TipoNotaFiscal> carregarTipoNotaFiscal() {
		BeanItemContainer<TipoNotaFiscal> container = new BeanItemContainer<>(TipoNotaFiscal.class);
		for (TipoNotaFiscal c : tipoNotaFiscalDAO.listarTodos()) {
			container.addBean(c);
		}
		return container;
	}

	public BeanItemContainer<Produto> carregarProdutos() {
		BeanItemContainer<Produto> container = new BeanItemContainer<>(Produto.class);
		for (Produto p : produtoDAO.listaTodos()) {
			container.addBean(p);
		}
		return container;
	}

	public String formataMoeda(String valor) {
		String format = "";
		format = valor.replace("R$", "").substring(0, valor.indexOf(",")).

		replaceAll(",", "").trim();
		return format;
	}

	public String formataBigDecimal(String valor) {
		String format = "";
		format = valor.replace(",", ".");
		return format;
	}

	public BeanItemContainer<Orcamento> carregarOrcamentos() {
		BeanItemContainer<Orcamento> container = new BeanItemContainer<>(Orcamento.class);
		for (Orcamento p : orcamentoDAO.listaTodos()) {
			container.addBean(p);
		}
		return container;
	}

	public List<ItemOrcamento> carregarItensOrcamento(Orcamento orcamento) {
		return itemOrcamentoDAO.findByOrcamento(orcamento);
	}

	public void preencherDetalhes(List<VendaDetalhe> detalhes) {
		currentBean.setDetalhes(detalhes);
		for (VendaDetalhe detalhe : detalhes) {
			detalhe.setVenda(currentBean);
		}
	}

	@Override
	public Venda getModelBean() {
		return currentBean;
	}

}
