package dc.controller.sistema;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sun.istack.logging.Logger;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Component;

import dc.controller.geral.pessoal.ColaboradorListController;
import dc.entidade.framework.Papel;
import dc.entidade.geral.Usuario;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.entidade.geral.pessoal.PessoaEntity;
import dc.entidade.geral.pessoal.PessoaFisicaEntity;
import dc.entidade.sistema.ContaEmpresa;
import dc.servicos.dao.geral.pessoal.ColaboradorDAO;
import dc.servicos.dao.geral.pessoal.PessoaDAO;
import dc.servicos.dao.sistema.PapelDAO;
import dc.servicos.dao.sistema.UsuarioDAO;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.component.manytoonecombo.ManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.sistema.UsuarioFormView;
import dc.visao.spring.SecuritySessionProvider;

/** @author Wesley Jr /* Nessa classe ela pega a classe principal que é o CRUD,
 *         que tem todos os controllers da Tela, onde quando extendemos herdamos
 *         os métodos que temos na tela principal. Temos o botão Novo que é para
 *         Criar uma nova Tela, para adicionar informações novas, e dentro temos
 *         o Button Salvar que é para salvar as informações no Banco de Dados
 *         Temos o carregar também que é para pegar as informações que
 *         desejarmos quando formos pesquisar na Tela. */

@Controller
@Scope("prototype")
public class UsuarioFormController extends CRUDFormController<Usuario> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UsuarioFormView subView;

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private ColaboradorDAO colaboradorDAO;

	@Autowired
	private PessoaDAO pessoaDAO;

	@Autowired
	private PapelDAO papelDAO;

	private Usuario currentBean = new Usuario();

	public static Logger logger = Logger.getLogger(UsuarioFormController.class);

	@Override
	protected String getNome() {
		return "Usuário";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			ContaEmpresa conta = SecuritySessionProvider.getUsuario().getConta();
			currentBean.setConta(conta);
			currentBean.setEmpresa(SecuritySessionProvider.getUsuario().getEmpresa());
			usuarioDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			mensagemErro("Problemas ao salvar registro");
			e.printStackTrace();
		}

	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = usuarioDAO.find(id);
		subView.carregaDataCadastro(getCurrentBean().getDataCadastro());
		subView.getBinder().setItemDataSource(getCurrentBean());
		subView.getComboColaborador().setValue(currentBean.getColaborador());
		subView.getComboPapeis().setValue(currentBean.getPapel());

	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {
		getCurrentBean().setConfirmado(false);
		subView.getBinder().discard();
		subView.getBinder().setItemDataSource(getCurrentBean());
		subView.carregaDataCadastro(getCurrentBean().getDataCadastro());
	}

	@Override
	protected void initSubView() {
		subView = new UsuarioFormView(this);

		ManyToOneComboModel<ColaboradorEntity> colaboradorModel = new DefaultManyToOneComboModel<ColaboradorEntity>(ColaboradorListController.class,
				colaboradorDAO, this.getMainController()) {
			@Override
			public String getCaptionProperty() {
				return "pessoa.nome";
			}
		};

		ManyToOneComboModel<Papel> papelModel = new DefaultManyToOneComboModel<Papel>(PapelListController.class, papelDAO, this.getMainController());

		subView.getComboPapeis().setModel(papelModel);
		subView.getComboColaborador().setModel(colaboradorModel);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new Usuario();
		currentBean.setDataCadastro(new Date());
	}

	@Override
	protected void remover(List<Serializable> ids) {
		usuarioDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		boolean retornoValidacao = true;
		try {
			subView.getBinder().commit();
			Usuario u = subView.getBinder().getItemDataSource().getBean();
			currentBean = u;
			u.setColaborador(subView.getComboColaborador().getValue());
			u.setPapel(subView.getComboPapeis().getValue());
			if (u.getColaborador() == null) {
				adicionarErroDeValidacao(subView.getComboColaborador(), "O Usuário deve estar associado a um colaborador");
				retornoValidacao = false;
			}
			if (u.getPapel() == null) {
				adicionarErroDeValidacao(subView.getComboPapeis(), "O Usuário deve estar associado a um papel");
				retornoValidacao = false;
			}

			if (u.getLogin() == null || u.getLogin().isEmpty()) {
				adicionarErroDeValidacao(subView.getLoginTxtField(), "Não pode ficar em branco");
				retornoValidacao = false;
			}

			if (u.getSenha() == null || u.getSenha().isEmpty()) {
				adicionarErroDeValidacao(subView.getSenhaPasswordField(), "Não pode ficar em branco");
				retornoValidacao = false;
			}

		} catch (CommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retornoValidacao;
	}

	public Usuario getCurrentBean() {
		return currentBean;
	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		// TODO Auto-generated method stub
		usuarioDAO.deleteAll(objetos);
		mensagemRemovidoOK();
	}

	public void alteraFormBaseadoEmColaborador(ColaboradorEntity colaborador) {
		if (isNovo()) {
			// dados padrão primeiro cadastro
			getCurrentBean().setDataCadastro(new Date());
			PessoaEntity p = colaborador.getPessoa();
			PessoaFisicaEntity pf = pessoaDAO.getPessoaFisica(p.getId());
			if (pf != null) {
				subView.getLoginTxtField().setValue(pf.getCpf());
				SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
				if (pf.getDataNascimento() != null) {
					String sDate = sdf.format(pf.getDataNascimento());
					subView.getSenhaPasswordField().setValue(sDate);
				}
			}

		}

	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "usuarioForm";
	}

	@Override
	public Usuario getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}
