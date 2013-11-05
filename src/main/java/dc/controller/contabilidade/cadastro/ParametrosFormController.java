package dc.controller.contabilidade.cadastro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.cadastro.ParametrosEntity;
import dc.servicos.dao.contabilidade.cadastro.ParametrosDAO;
import dc.visao.contabilidade.cadastro.ParametrosFormView;
import dc.visao.framework.geral.CRUDFormController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller(value = "contabilidadeParametrosFormController")
@Scope("prototype")
public class ParametrosFormController extends
		CRUDFormController<ParametrosEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ParametrosFormView subView;

	/**
	 * DAO'S
	 */

	@Autowired
	private ParametrosDAO pDAO;

	/**
	 * ENTITIES
	 */

	private ParametrosEntity pEntity;

	/**
	 * CONSTRUTOR
	 */

	public ParametrosFormController() {
		if (this.pEntity == null) {
			this.pEntity = new ParametrosEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Parâmetro";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
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
		this.subView = new ParametrosFormView(this);
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
				this.pEntity = new ParametrosEntity();
			} else {
				this.pEntity = this.pDAO.find(id);
			}

			this.subView.getTfMascara().setValue(this.pEntity.getMascara());
			this.subView.getTfNiveis().setValue(
					this.pEntity.getNiveis().toString());
			this.subView.getTfInformarContaPor().setValue(
					this.pEntity.getInformarContaPor());
			this.subView.getTfCompartilhaPlanoConta().setValue(
					this.pEntity.getCompartilhaPlanoConta());
			this.subView.getTfCompartilhaHistoricos().setValue(
					this.pEntity.getCompartilhaHistoricos());
			this.subView.getTfAlteraLancamentoOutro().setValue(
					this.pEntity.getAlteraLancamentoOutro());
			this.subView.getTfHistoricoObrigatorio().setValue(
					this.pEntity.getHistoricoObrigatorio());
			this.subView.getTfPermiteLancamentoZerado().setValue(
					this.pEntity.getPermiteLancamentoZerado());
			this.subView.getTfGeraInformativoSped().setValue(
					this.pEntity.getGeraInformativoSped());
			this.subView.getTfSpedFormaEscritDiario().setValue(
					this.pEntity.getSpedFormaEscritDiario());
			this.subView.getTfSpedNomeLivroDiario().setValue(
					this.pEntity.getSpedNomeLivroDiario());
			this.subView.getTfAssinaturaDireita().setValue(
					this.pEntity.getAssinaturaDireita());
			this.subView.getTfAssinaturaEsquerda().setValue(
					this.pEntity.getAssinaturaEsquerda());
			this.subView.getTfContaAtivo().setValue(
					this.pEntity.getContaAtivo());
			this.subView.getTfContaPassivo().setValue(
					this.pEntity.getContaPassivo());
			this.subView.getTfContaPatrimonioLiquido().setValue(
					this.pEntity.getContaPatrimonioLiquido());
			this.subView.getTfContaDepreciacaoAcumulada().setValue(
					this.pEntity.getContaDepreciacaoAcumulada());
			this.subView.getTfContaCapitalSocial().setValue(
					this.pEntity.getContaCapitalSocial());
			this.subView.getTfContaResultadoExercicio().setValue(
					this.pEntity.getContaResultadoExercicio());
			this.subView.getTfContaPrejuizoAcumulado().setValue(
					this.pEntity.getContaPrejuizoAcumulado());
			this.subView.getTfContaLucroAcumulado().setValue(
					this.pEntity.getContaLucroAcumulado());
			this.subView.getTfContaTituloPagar().setValue(
					this.pEntity.getContaTituloPagar());
			this.subView.getTfContaTituloReceber().setValue(
					this.pEntity.getContaTituloReceber());
			this.subView.getTfContaJurosPassivo().setValue(
					this.pEntity.getContaJurosPassivo());
			this.subView.getTfContaJurosAtivo().setValue(
					this.pEntity.getContaJurosAtivo());
			this.subView.getTfContaDescontoObtido().setValue(
					this.pEntity.getContaDescontoObtido());
			this.subView.getTfContaDescontoConcedido().setValue(
					this.pEntity.getContaDescontoConcedido());
			this.subView.getTfContaCmv().setValue(this.pEntity.getContaCmv());
			this.subView.getTfContaVenda().setValue(
					this.pEntity.getContaVenda());
			this.subView.getTfContaVendaServico().setValue(
					this.pEntity.getContaVendaServico());
			this.subView.getTfContaEstoque().setValue(
					this.pEntity.getContaEstoque());
			this.subView.getTfContaApuraResultado().setValue(
					this.pEntity.getContaApuraResultado());
			this.subView.getTfContaJurosApropriar().setValue(
					this.pEntity.getContaJurosApropriar());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}