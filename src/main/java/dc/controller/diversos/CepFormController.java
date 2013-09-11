package dc.controller.diversos;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;

import dc.entidade.diversos.Cep;
import dc.entidade.geral.UF;
import dc.servicos.dao.diversos.CepDAO;
import dc.servicos.dao.geral.UFDAO;
import dc.visao.diversos.CepFormView;
import dc.visao.framework.component.manytoonecombo.ManyToOneComboModel;
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

@Controller
@Scope("prototype")
public class CepFormController extends CRUDFormController<Cep> {

	CepFormView subView;
	
	@Autowired
	private CepDAO cepDAO;
	
	@Autowired
	private UFDAO ufDAO;

	private Cep currentBean;
	
	@Override
	protected String getNome() {
		return "Cep";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override  
	protected void actionSalvar() {
		String cep = subView.getTxtCep().getValue();
		String logradouro = subView.getTxtLogradouro().getValue();
		String complemento = subView.getTxtComplemento().getValue();
		String bairro = subView.getTxtBairro().getValue();
		String municipio = subView.getTxtMunicipio().getValue();
		currentBean.setCep(cep);
		currentBean.setLogradouro(logradouro);
		currentBean.setComplemento(complemento);
		currentBean.setBairro(bairro);
		currentBean.setMunicipio(municipio);
		try{
			cepDAO.saveOrUpdate(currentBean);
			mensagemSalvoOK();	
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}


	@Override
	protected void carregar(Serializable id) {
		currentBean = cepDAO.find(id);
		subView.getTxtCep().setValue(currentBean.getCep());
		subView.getTxtLogradouro().setValue(currentBean.getLogradouro());	
		subView.getTxtComplemento().setValue(currentBean.getComplemento());
		subView.getTxtBairro().setValue(currentBean.getBairro());
		subView.getTxtMunicipio().setValue(currentBean.getMunicipio());
		
		/* Configura combo PAÍS */
		ManyToOneComboModel<UF> modeluf = new ManyToOneComboModel<UF>() {

			@Override
			public void onCriarNovo(String filter) {
				Notification.show("Selecionado Criar Novo: " + filter);
			}

			@Override
			public List<UF> getResultado(String q) {
				return ufDAO.query(q);
			}

			@Override
			public Class<UF> getEntityClass() {
				return UF.class;
			}

			@Override
			public String getCaptionProperty() {
				return "nome";
			}

			@Override
			public void onEditar(UF value) {
				Notification.show("Selecionado Editar: " + value.getNome());

			}
		};
		subView.getCmbUf().setModel(modeluf);
		subView.getCmbUf().setValue(currentBean.getUf());
	}
	
	/* Callback para quando novo foi acionado. Colocar Programação customizada para essa ação aqui. Ou então deixar em branco, para comportamento padrão */
	@Override
	protected void quandoNovo() {
		
	}

	@Override
	protected void initSubView() {
		subView = new CepFormView();
	}

	/* Deve sempre atribuir a current Bean uma nova instancia do bean do formulario.*/
	@Override
	protected void criarNovoBean() {
		currentBean = new Cep();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		 cepDAO.deleteAllByIds(ids);
		 mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */ 
	@Override
	protected boolean validaSalvar() {
		if(subView.getTxtCep().getValue() ==  null || subView.getTxtCep().getValue().isEmpty()){
			//Utilizar adicionarErroDeValidacao() para adicionar mensagem de erro para o campo que esta sendo validado
			adicionarErroDeValidacao(subView.getTxtCep(),"não pode ficar em branco");
			return false;
		}
		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		
	}

	@Override
	public String getViewIdentifier() {
		return "cepForm";
	}

}
