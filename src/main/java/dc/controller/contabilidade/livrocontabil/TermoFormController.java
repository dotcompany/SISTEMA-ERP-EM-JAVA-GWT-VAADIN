package dc.controller.contabilidade.livrocontabil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.livrocontabil.LivroEntity;
import dc.entidade.contabilidade.livrocontabil.TermoEntity;
import dc.servicos.dao.contabilidade.livrocontabil.LivroDAO;
import dc.servicos.dao.contabilidade.livrocontabil.TermoDAO;
import dc.visao.contabilidade.livrocontabil.TermoFormView;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class TermoFormController extends CRUDFormController<TermoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TermoFormView subView;

	/**
	 * DAO'S
	 */

	@Autowired
	private TermoDAO pDAO;

	@Autowired
	private LivroDAO lDAO;

	/**
	 * ENTITIES
	 */

	private TermoEntity pEntity;

	/**
	 * CONSTRUTOR
	 */

	public TermoFormController() {
		if (this.pEntity == null) {
			this.pEntity = new TermoEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Termo";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String aberturaEncerramento = this.subView
					.getTfAberturaEncerramento().getValue();
			String numero = this.subView.getTfNumero().getValue();
			String paginaInicial = this.subView.getTfPaginaInicial().getValue();
			String paginaFinal = this.subView.getTfPaginaFinal().getValue();
			String registrado = this.subView.getTfRegistrado().getValue();
			String numeroRegistro = this.subView.getTfNumeroRegistro()
					.getValue();
			Date dataDespacho = this.subView.getPdfDataDespacho().getValue();
			Date dataAbertura = this.subView.getPdfDataAbertura().getValue();
			Date dataEncerramento = this.subView.getPdfDataEncerramento()
					.getValue();
			Date escrituracaoInicio = this.subView.getPdfEscrituracaoInicio()
					.getValue();
			Date escrituracaoFim = this.subView.getPdfEscrituracaoFim()
					.getValue();
			String texto = this.subView.getTfTexto().getValue();

			this.pEntity.setAberturaEncerramento(aberturaEncerramento);
			this.pEntity.setNumero(Integer.parseInt(numero));
			this.pEntity.setPaginaInicial(Integer.parseInt(paginaInicial));
			this.pEntity.setPaginaFinal(Integer.parseInt(paginaFinal));
			this.pEntity.setRegistrado(registrado);
			this.pEntity.setNumeroRegistro(numeroRegistro);
			this.pEntity.setDataDespacho(dataDespacho);
			this.pEntity.setDataAbertura(dataAbertura);
			this.pEntity.setDataEncerramento(dataEncerramento);
			this.pEntity.setEscrituracaoInicio(escrituracaoInicio);
			this.pEntity.setEscrituracaoFim(escrituracaoFim);
			this.pEntity.setTexto(texto);

			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			novoObjeto(0);
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			novoObjeto(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {
		try {
			novoObjeto(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new TermoFormView(this);

		popularCombo();
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			novoObjeto(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.pDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getViewIdentifier() {
		String sUrl = ClasseUtil.getUrl(this);

		return sUrl;
	}

	/**
	 * COMBOS
	 */

	private void popularCombo() {
		try {
			DefaultManyToOneComboModel<LivroEntity> model = new DefaultManyToOneComboModel<LivroEntity>(
					LivroListController.class, this.lDAO,
					super.getMainController());

			this.subView.getCbLivro().setModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * **************************************
	 */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	/**
	 * **************************************
	 */

	private void novoObjeto(Serializable id) {
		try {
			if (id.equals(0) || id == null) {
				this.pEntity = new TermoEntity();
			} else {
				this.pEntity = this.pDAO.find(id);
			}

			this.subView.getTfAberturaEncerramento().setValue(
					this.pEntity.getAberturaEncerramento());
			this.subView.getTfNumero().setValue(
					this.pEntity.getNumero().toString());
			this.subView.getTfPaginaInicial().setValue(
					this.pEntity.getPaginaInicial().toString());
			this.subView.getTfPaginaFinal().setValue(
					this.pEntity.getPaginaFinal().toString());
			this.subView.getTfRegistrado().setValue(
					this.pEntity.getRegistrado());
			this.subView.getTfNumeroRegistro().setValue(
					this.pEntity.getNumeroRegistro());
			this.subView.getPdfDataDespacho().setValue(
					this.pEntity.getDataDespacho());
			this.subView.getPdfDataAbertura().setValue(
					this.pEntity.getDataAbertura());
			this.subView.getPdfDataEncerramento().setValue(
					this.pEntity.getDataEncerramento());
			this.subView.getPdfEscrituracaoInicio().setValue(
					this.pEntity.getEscrituracaoInicio());
			this.subView.getPdfEscrituracaoFim().setValue(
					this.pEntity.getEscrituracaoFim());
			this.subView.getTfTexto().setValue(this.pEntity.getTexto());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}