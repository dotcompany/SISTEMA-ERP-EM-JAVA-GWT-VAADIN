package dc.controller.ordemservico;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.entidade.comercial.CondicaoPagamento;
import dc.entidade.comercial.ItemOrcamento;
import dc.entidade.comercial.Orcamento;
import dc.entidade.financeiro.NaturezaFinanceira;
import dc.entidade.ordemservico.Combustivel;
import dc.entidade.ordemservico.Cor;
import dc.entidade.ordemservico.Marca;
import dc.entidade.ordemservico.Modelo;
import dc.entidade.ordemservico.OrcamentoOs;
import dc.entidade.ordemservico.OrcamentoOsItem;
import dc.entidade.suprimentos.PedidoDetalhe;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.ordemservico.CorDAO;
import dc.servicos.dao.ordemservico.MarcaDAO;
import dc.servicos.dao.ordemservico.ModeloDAO;
import dc.servicos.dao.ordemservico.OrcamentoItemOsDAO;
import dc.servicos.dao.ordemservico.OrcamentoOsDAO;
import dc.servicos.util.Validator;
import dc.visao.financeiro.LancamentoPagarFormView;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.OrcamentoOsFormView;

@Controller
@Scope("prototype")
public class OrcamentoOsFormController extends CRUDFormController<OrcamentoOs> {

	private static final long serialVersionUID = 1L;

	private OrcamentoOsFormView subView;

	@Autowired
	MarcaDAO marcaDAO;

	@Autowired
	CorDAO corDAO;

	@Autowired
	ModeloDAO modeloDAO;
	
	@Autowired
	private OrcamentoOsDAO orcamentoOsDAO;


	@Autowired
	private OrcamentoItemOsDAO orcamentoItemOsDAO;

	private OrcamentoOs currentBean;

	@Override
	protected String getNome() {
		return "O.S Simples";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			
			if(!Validator.validateObject(subView.getTfNomeVendedor().getValue())){
				throw new ErroValidacaoException("Informe o Vendedor");
			}else{
				currentBean.setNomeVendedor(subView.getTfNomeVendedor().getValue());
			} 
			
			if(!Validator.validateObject(subView.getTfNome().getValue())){
				throw new ErroValidacaoException("Informe o Nome");
			}else{
				currentBean.setNome(subView.getTfNome().getValue());
			}

			currentBean.setFormaPagamento(subView.getTfFormaPagamento().getValue());
			currentBean.setEndereco(subView.getTfEndereco().getValue());
			currentBean.setFone(subView.getTfFone().getValue());
			currentBean.setAno(Integer.valueOf(subView.getTfAno().getValue()));
			currentBean.setMotor(subView.getTfCvMotor().getValue());
			currentBean.setMotorizacao(subView.getTfMotorizacao().getValue());
			

			if(!Validator.validateObject(subView.getTfPlaca().getValue())){
				throw new ErroValidacaoException("Informe a Placa");
			}else{
				currentBean.setPlaca(subView.getTfPlaca().getValue());
			}

			if(!Validator.validateObject(subView.getCbMarca().getValue())){
				throw new ErroValidacaoException("Informe a Marca");
			}else{
				currentBean.setMarca(subView.getCbMarca().getValue());
			}

			if(!Validator.validateObject(subView.getCbCor().getValue())){
				throw new ErroValidacaoException("Informe a Cor");
			}else{
				currentBean.setCor(subView.getCbCor().getValue());
			}

			if(!Validator.validateObject(subView.getCbModelo().getValue())){
				throw new ErroValidacaoException("Informe o Modelo");
			}else{
				currentBean.setModelo(subView.getCbModelo().getValue());
			}

			orcamentoOsDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			mensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = orcamentoOsDAO.find((Integer) id);
		
		subView.getTfNomeVendedor().setValue(currentBean.getNomeVendedor());
		subView.getTfFormaPagamento().setValue(currentBean.getFormaPagamento());
		subView.getTfNome().setValue(currentBean.getNome());
		subView.getTfEndereco().setValue(currentBean.getEndereco());
		subView.getTfFone().setValue(currentBean.getFone());
		subView.getTfPlaca().setValue(currentBean.getPlaca());
		subView.getCbMarca().setValue(currentBean.getMarca());
		subView.getCbCor().setValue(currentBean.getCor());
		subView.getCbModelo().setValue(currentBean.getModelo());
		subView.getTfCvMotor().setValue(currentBean.getMotor());
		subView.getTfMotorizacao().setValue(currentBean.getMotorizacao());
		subView.getTfAno().setValue(currentBean.getAno().toString());

		BigDecimal valorTotal = currentBean.getValorTotal();
		BigDecimal valorDesconto = currentBean.getValorDesconto();

//		if(valorDesconto!=null ){
//			subView.getTfValorDesconto().setValue(valorTotal.toString());
//		}
//		if(valorTotal!=null ){
//			subView.getTfValorTotal().setValue(valorTotal.toString());
//		}

		List<OrcamentoOsItem> itens = orcamentoItemOsDAO.findByOrcamentoOs(currentBean);

		subView.preencheSubForm(itens);

	}

	@Override
	protected void initSubView() {
		subView = new OrcamentoOsFormView(this);
		preencheCombos();
	}

	private void preencheCombos() {

		DefaultManyToOneComboModel<Marca> marca = new DefaultManyToOneComboModel<Marca>(MarcaListController.class,
				this.marcaDAO, super.getMainController());

		this.subView.getCbMarca().setModel(marca);
		
		DefaultManyToOneComboModel<Cor> cor = new DefaultManyToOneComboModel<Cor>(CorListController.class,
				this.corDAO, super.getMainController());
		this.subView.getCbCor().setModel(cor);

		DefaultManyToOneComboModel<Modelo> modelo = new DefaultManyToOneComboModel<Modelo>(ModeloListController.class,
				this.modeloDAO, super.getMainController());

		this.subView.getCbModelo().setModel(modelo);
	}
	@Override
	protected void criarNovoBean() {
		currentBean = new OrcamentoOs();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		orcamentoOsDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	@Override
	protected boolean validaSalvar() {
		return true;
	}

	@Override
	protected void quandoNovo() {
		subView.preencheSubForm(currentBean.getItens());
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		remover(ids);
	}
	
	public OrcamentoOsItem novoOrcamentoOsItem() {
		OrcamentoOsItem item = new OrcamentoOsItem();
		currentBean.addOrcamentoOsItem(item);
		return item;
	}

	public void removerOrcamentoOsItem(List<OrcamentoOsItem> orcamentoOsItem) {
		for (OrcamentoOsItem orcamentoItem : orcamentoOsItem) {
			currentBean.removeOrcamentoOsItem(orcamentoItem);
		}
		mensagemRemovidoOK();
	}

	@Override
	public String getViewIdentifier() {
		return "orcamentoOsForm";
	}
	
	@Override
	public boolean isFullSized(){
		return true;
	}

	public List<OrcamentoOs> getOrcamentoOsItens() {
		return orcamentoOsDAO.listaTodos();
	}
}
