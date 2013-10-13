package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.entidade.empresa.EmpresaCnae;
import dc.entidade.financeiro.Sindicato;
import dc.entidade.framework.Empresa;
import dc.entidade.geral.Contato;
import dc.entidade.geral.Endereco;
import dc.entidade.pessoal.Contador;
import dc.entidade.tabelas.CstIcmsB;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.empresa.EmpresaCnaeDAO;
import dc.servicos.dao.financeiro.SindicatoDAO;
import dc.servicos.dao.framework.geral.EmpresaDAO;
import dc.servicos.dao.framework.geral.FpasDAO;
import dc.servicos.dao.pessoal.ContadorDAO;
import dc.servicos.util.Validator;
import dc.visao.financeiro.EmpresaFormView;
import dc.visao.framework.geral.CRUDFormController;
/**
 *
 * @author Wesley Jr
/*
 * Nessa classe ela pega a classe principal que é o CRUD, que tem todos os controllers
 * da Tela, onde quando extendemos herdamos os métodos que temos na tela principal.
 * Temos o botão Novo que é para Criar uma nova Tela, para adicionar informações
 * novas, e dentro temos o Button Salvar que é para salvar as informações no Banco de Dados
 * Temos o carregar também que é para pegar as informações que desejarmos quando
 * formos pesquisar na Tela.
 *
 */

/**
 *
 * @author Wesley Jr
/*
 * Nessa classe ela pega a classe principal que é o CRUD, que tem todos os controllers
 * da Tela, onde quando extendemos herdamos os métodos que temos na tela principal.
 * Temos o botão Novo que é para Criar uma nova Tela, para adicionar informações
 * novas, e dentro temos o Button Salvar que é para salvar as informações no Banco de Dados
 * Temos o carregar também que é para pegar as informações que desejarmos quando
 * formos pesquisar na Tela.
 *
 */

@Controller
@Scope("prototype")
public class EmpresaFormController extends CRUDFormController<Empresa> {

	private  EmpresaFormView subView;

	@Autowired
	private EmpresaDAO empresaDAO;

	/*@Autowired
	private MatrizDAO matrizDAO;*/

	@Autowired
	private ContadorDAO contadorDAO;

	@Autowired
	private SindicatoDAO sindicatoDAO;

	@Autowired
	private FpasDAO fpasDAO;

	@Autowired
	EmpresaCnaeDAO empresaCnaeDAO; 

	private Empresa currentBean;

	@Override
	protected String getNome() {
		return "Empresa";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {

		String razaoSocial = subView.getTxtRazaoSocial().getValue();
		String nomeFantasia = subView.getTxtNomeFantasia().getValue();


		try{

			if(!(Validator.validateString(razaoSocial))){
				throw new ErroValidacaoException("Informe a Razão Social");
			}
			if(!(Validator.validateString(nomeFantasia))){
				throw new ErroValidacaoException("Informe o Nome de Fantasia");
			}

			currentBean.setRazaoSocial(razaoSocial);
			currentBean.setNomeFantasia(nomeFantasia);
    		empresaDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);	
		}catch(ErroValidacaoException e){
			e.montaMensagemErro();
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	private void carregarCombos() {
		/*subView.carregaComboMatrix(matrizDAO
				.getAll(Matriz.class));*/
		//subView.carregaComboContador(contadorDAO
		//		.getAll(Contador.class));
		//subView.carregaComboSindicato(sindicatoDAO
		//		.getAll(Sindicato.class));
		/*subView.carregaComboFpas(FpasDAO
				.getAll(Fpas.class)); */
	}

	@Override
	protected void carregar(Serializable id) {
		carregarCombos();
		currentBean = empresaDAO.find(id);
		//subView.getTxtRazaoSocial().setValue(currentBean.getRazaoSocial());
		//subView.getTxtNomeFantasia().setValue(currentBean.getNomeFantasia());
	}

	@Override
	protected void initSubView() {
		subView = new EmpresaFormView(this);
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new Empresa();
	}

	@Override
	protected void quandoNovo() {
		// TODO Auto-generated method stub

	}

	public Contato novoContato() {
		Contato contato = new Contato();
		this.currentBean.addContato(contato);
		return contato;
	}

	public void removerContato(
			List<Contato> values) { 
		for (Contato contato : values) {
			this.currentBean.removeContato(contato);
		}
		mensagemRemovidoOK();

	}

	public Endereco novoEndereco() {
		Endereco endereco = new Endereco();
		this.currentBean.addEndereco(endereco);
		return endereco;
	}

	public void removerEndereco(
			List<Endereco> values) {
		for (Endereco endereco : values) {
			this.currentBean.removeEndereco(endereco);
		}
		mensagemRemovidoOK();

	}

	@Override
	protected void remover(List ids) {
		empresaDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	@Override
	protected boolean validaSalvar() {
		return true;
	}


	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "empresaForm";
	}
	
	public BeanItemContainer<Empresa> carregarMatrizes(){
		BeanItemContainer<Empresa> container = new BeanItemContainer<>(Empresa.class);
		for(Empresa obj : empresaDAO.buscaMatrizes()){
			container.addBean(obj);
		}
		return container;
	}
	
	public BeanItemContainer<Sindicato> carregarSindicatos(){
		BeanItemContainer<Sindicato> container = new BeanItemContainer<>(Sindicato.class);
		for(Sindicato obj : sindicatoDAO.listaTodos()){
			container.addBean(obj);
		}
		return container;
	}
	
	public BeanItemContainer<EmpresaCnae> carregarEmpresaCnae(){
		BeanItemContainer<EmpresaCnae> container = new BeanItemContainer<>(EmpresaCnae.class);
		for(EmpresaCnae obj : empresaCnaeDAO.listarPrincipais()){
			container.addBean(obj);
		}
		return container;
	}
	
	public BeanItemContainer<Contador> carregarContadores(){
		BeanItemContainer<Contador> container = new BeanItemContainer<>(Contador.class);
		for(Contador obj : contadorDAO.listaTodos()){
			container.addBean(obj);
		}
		return container;
	}

}
