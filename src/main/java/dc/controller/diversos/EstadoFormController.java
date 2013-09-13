package dc.controller.diversos;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;

import dc.entidade.diversos.Estado;
import dc.entidade.diversos.Pais;
import dc.servicos.dao.diversos.EstadoDAO;
import dc.servicos.dao.diversos.PaisDAO;
import dc.visao.diversos.EstadoFormView;
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
public class EstadoFormController extends CRUDFormController<Estado> {

	EstadoFormView subView;
	
	@Autowired
	private EstadoDAO estadoDAO;
	
	@Autowired
	private PaisDAO paisDAO;

	private Estado currentBean;
	
	@Override
	protected String getNome() {
		return "Estado";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override  
	protected void actionSalvar() {
		String nome = subView.getTxtNome().getValue();
		String sigla = subView.getTxtSigla().getValue();
		currentBean.setNome(nome);
		currentBean.setSigla(sigla);
		try{
			estadoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);	
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}


	@Override
	protected void carregar(Serializable id) {
		currentBean = estadoDAO.find(id);
		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtSigla().setValue(currentBean.getSigla());	
		
		
		/* Configura combo PAÍS */
		ManyToOneComboModel<Pais> modelpais = new ManyToOneComboModel<Pais>() {

			@Override
			public void onCriarNovo(String filter) {
				Notification.show("Selecionado Criar Novo: " + filter);
			}

			@Override
			public List<Pais> getResultado(String q) {
				return paisDAO.query(q);
			}

			@Override
			public Class<Pais> getEntityClass() {
				return Pais.class;
			}

			@Override
			public String getCaptionProperty() {
				return "nome";
			}

			@Override
			public void onEditar(Pais value) {
				Notification.show("Selecionado Editar: " + value.getNomeEn());

			}
		};
		subView.getCmbPais().setModel(modelpais);
		subView.getCmbPais().setValue(currentBean.getPaisId());
	}
	
	/* Callback para quando novo foi acionado. Colocar Programação customizada para essa ação aqui. Ou então deixar em branco, para comportamento padrão */
	@Override
	protected void quandoNovo() {
		
	}

	@Override
	protected void initSubView() {
		subView = new EstadoFormView();
	}

	/* Deve sempre atribuir a current Bean uma nova instancia do bean do formulario.*/
	@Override
	protected void criarNovoBean() {
		currentBean = new Estado();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		 estadoDAO.deleteAllByIds(ids);
		 mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */ 
	@Override
	protected boolean validaSalvar() {
		if(subView.getTxtNome().getValue() ==  null || subView.getTxtNome().getValue().isEmpty()){
			//Utilizar adicionarErroDeValidacao() para adicionar mensagem de erro para o campo que esta sendo validado
			adicionarErroDeValidacao(subView.getTxtNome(),"não pode ficar em branco");
			return false;
		}
		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "estadoForm";
	}

}
