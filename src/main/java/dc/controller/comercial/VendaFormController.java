package dc.controller.comercial;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.entidade.comercial.Venda;
import dc.entidade.comercial.Venda;
import dc.entidade.comercial.VendaDetalhe;
import dc.entidade.folhapagamento.VendedorEntity;
import dc.entidade.suprimentos.ReajusteEstoque;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.comercial.VendaDAO;
import dc.servicos.dao.comercial.VendaDAO;
import dc.servicos.dao.folhapagamento.VendedorDAO;
import dc.servicos.util.Validator;
import dc.visao.comercial.VendaFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class VendaFormController extends CRUDFormController<Venda> {

	Venda currentBean;

	VendaFormView subView;

	@Autowired
	VendaDAO dao;
	
	@Autowired
	VendedorDAO vendedorDAO;

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
		return "Venda";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isFullSized(){
		return true;
	}
	
	public VendaDetalhe novoDetalhe(){
		VendaDetalhe detalhe = new VendaDetalhe();
		currentBean.adicionarDetalhe(detalhe);
		return detalhe;
	}
	
	public BeanItemContainer<VendedorEntity> carregarVendedores(){
		BeanItemContainer<VendedorEntity> container = new BeanItemContainer<>(VendedorEntity.class);
		for(VendedorEntity c : vendedorDAO.listarTodos()){
			container.addBean(c);
		}
		return container;
	}

}
