package dc.controller.contabilidade.planoconta;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.planoconta.ContaEntity;
import dc.servicos.dao.contabilidade.planoconta.ContaDAO;
import dc.visao.contabilidade.planoconta.ContaFormView;
import dc.visao.framework.geral.CRUDFormController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class ContaFormController extends CRUDFormController<ContaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ContaFormView subView;

	/**
	 * DAO'S
	 */

	@Autowired
	private ContaDAO pDAO;

	/**
	 * ENTITIES
	 */

	private ContaEntity pEntity;

	/**
	 * CONSTRUTOR
	 */

	public ContaFormController() {
		if (this.pEntity == null) {
			this.pEntity = new ContaEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Conta";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String classificacao = this.subView.getTfClassificacao().getValue();
			String tipo = this.subView.getTfTipo().getValue();
			String descricao = this.subView.getTfDescricao().getValue();
			Date dataInclusao = this.subView.getPdfDataInclusao().getValue();
			String situacao = this.subView.getTfSituacao().getValue();
			String natureza = this.subView.getTfNatureza().getValue();
			String patrimonioResultado = this.subView
					.getTfPatrimonioResultado().getValue();
			String dfc = this.subView.getTfDfc().getValue();
			String ordem = this.subView.getTfOrdem().getValue();
			String codigoReduzido = this.subView.getTfCodigoReduzido()
					.getValue();
			String codigoEfd = this.subView.getTfCodigoEfd().getValue();

			this.pEntity.setClassificacao(classificacao);
			this.pEntity.setTipo(tipo);
			this.pEntity.setDescricao(descricao);
			this.pEntity.setDataInclusao(dataInclusao);
			this.pEntity.setSituacao(situacao);
			this.pEntity.setNatureza(natureza);
			this.pEntity.setPatrimonioResultado(patrimonioResultado);
			this.pEntity.setDfc(dfc);
			this.pEntity.setOrdem(ordem);
			this.pEntity.setCodigoReduzido(codigoReduzido);
			this.pEntity.setCodigoEfd(codigoEfd);

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
		this.subView = new ContaFormView(this);
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
				this.pEntity = new ContaEntity();
			} else {
				this.pEntity = this.pDAO.find(id);
			}

			this.subView.getTfClassificacao().setValue(
					this.pEntity.getClassificacao());
			this.subView.getTfTipo().setValue(this.pEntity.getTipo());
			this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());
			this.subView.getPdfDataInclusao().setValue(
					this.pEntity.getDataInclusao());
			this.subView.getTfSituacao().setValue(this.pEntity.getSituacao());
			this.subView.getTfNatureza().setValue(this.pEntity.getNatureza());
			this.subView.getTfPatrimonioResultado().setValue(
					this.pEntity.getPatrimonioResultado());
			this.subView.getTfDfc().setValue(this.pEntity.getDfc());
			this.subView.getTfOrdem().setValue(this.pEntity.getOrdem());
			this.subView.getTfCodigoReduzido().setValue(
					this.pEntity.getCodigoReduzido());
			this.subView.getTfCodigoEfd().setValue(this.pEntity.getCodigoEfd());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}