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

import dc.controller.folhapagamento.ausencia.VendedorListController;
import dc.controller.geral.pessoal.ClienteListController;
import dc.controller.geral.pessoal.TransportadoraListController;
import dc.entidade.comercial.CondicaoPagamento;
import dc.entidade.comercial.Frete;
import dc.entidade.comercial.ItemOrcamento;
import dc.entidade.comercial.Orcamento;
import dc.entidade.comercial.Venda;
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
import dc.visao.comercial.VendaFormView;
import dc.visao.framework.DCFieldGroup;
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
		try {
			subView = new OrcamentoFormView(this);
			this.fieldGroup = new DCFieldGroup<>(Orcamento.class);
		
			
			//fieldGroup.bind(this.subView.getTxtResponsavel(), "responsavel");
			//fieldGroup.bind(this.subView.getTxtPlaca(), "placa");
			//fieldGroup.bind(this.subView.getCmbVenda(), "vendaCabecalho");
			
			 this.subView.getCmbVendedor().configuraCombo(
						"colaborador", VendedorListController.class, this.vendedorDAO, this.getMainController());
			 this.subView.getCmbCliente().configuraCombo(
						"pessoa", ClienteListController.class, this.clienteDAO, this.getMainController());
			 this.subView.getCmbTransportadora().configuraCombo(
						"observacao", TransportadoraListController.class, this.transportadoraDAO, this.getMainController());
			 this.subView.getCmbCondicaoPagamento().configuraCombo(
						"nome", CondicaoPagamentoListController.class, this.condicaoPagamentoDAO, this.getMainController());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = dao.find(id);

		List<ItemOrcamento> itens = itemOrcamentoDAO
				.findByOrcamento(currentBean);

		subView.preencheSubForm(itens);
	}

	@Override
	protected void actionSalvar() {
		try {
			dao.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
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