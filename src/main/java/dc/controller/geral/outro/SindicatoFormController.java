package dc.controller.geral.outro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.enums.TipoSindicatoEn;
import dc.control.util.ClassUtils;
import dc.control.util.NumberUtils;
import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.controller.contabilidade.ContabilContaListController;
import dc.controller.geral.diverso.UfListController;
import dc.entidade.contabilidade.ContabilContaEntity;
import dc.entidade.geral.diverso.UfEntity;
import dc.entidade.geral.outro.SindicatoEntity;
import dc.model.business.geral.diverso.UfBusiness;
import dc.model.business.geral.outro.SindicatoBusiness;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.servicos.dao.geral.UfDAO;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.outro.SindicatoFormView;

@Controller
@Scope("prototype")
public class SindicatoFormController extends
		CRUDFormController<SindicatoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SindicatoFormView subView;

	/**
	 * ENTITY
	 */

	private SindicatoEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private SindicatoBusiness<SindicatoEntity> business;

	@Autowired
	private UfBusiness<UfEntity> ufBusiness;

	/**
	 * DAO
	 */

	@Autowired
	private ContabilContaDAO contabilContaDAO;

	@Autowired
	private UfDAO ufDAO;

	/**
	 * CONSTRUTOR
	 */

	public SindicatoFormController() {
		// TODO Auto-generated constructor stub
	}

	public SindicatoBusiness<SindicatoEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Sindicato";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	public SindicatoEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new SindicatoFormView(this);

			DefaultManyToOneComboModel<ContabilContaEntity> modelcontabilConta = new DefaultManyToOneComboModel<ContabilContaEntity>(
					ContabilContaListController.class, this.contabilContaDAO,
					super.getMainController()) {

				@Override
				public String getCaptionProperty() {
					return "descricao";
				}

			};

			this.subView.getMocContabilConta().setModel(modelcontabilConta);

			DefaultManyToOneComboModel<UfEntity> modelUf = new DefaultManyToOneComboModel<UfEntity>(
					UfListController.class, this.ufDAO,
					super.getMainController());

			this.subView.getMocUf().setModel(modelUf);

			//

			comboTipoSindicato();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean validaSalvar() {
		boolean valido = true;

		String nome = this.subView.getTfNome().getValue();

		if (StringUtils.isBlank(nome)) {
			adicionarErroDeValidacao(this.subView.getTfNome(),
					"Não pode ficar em branco!");

			valido = false;
		}

		String logradouro = this.subView.getTfLogradouro().getValue();

		if (StringUtils.isBlank(logradouro)) {
			adicionarErroDeValidacao(this.subView.getTfLogradouro(),
					"Não pode ficar em branco!");

			valido = false;
		}

		return valido;
	}

	@Override
	protected void actionSalvar() {
		try {
			ContabilContaEntity contabilConta = this.subView
					.getMocContabilConta().getValue();

			this.entity.setContabilConta(contabilConta);

			this.entity.setNome(this.subView.getTfNome().getValue());
			this.entity
					.setLogradouro(this.subView.getTfLogradouro().getValue());

			this.entity.setEmail(this.subView.getTfEmail().getValue());
			this.entity.setDataBase(this.subView.getPdfDataBase().getValue());
			this.entity.setBairro(this.subView.getTfBairro().getValue());
			this.entity.setCnpj(this.subView.getTfCnpj().getValue());

			String codigoAgencia = this.subView.getTfCodigoAgencia().getValue();

			if (NumberUtils.isNumber(codigoAgencia)) {
				this.entity.setCodigoAgencia(NumberUtils.toInt(codigoAgencia));
			} else {
				this.entity.setCodigoAgencia(null);
			}

			String codigoBanco = this.subView.getTfCodigoBanco().getValue();

			if (NumberUtils.isNumber(codigoBanco)) {
				this.entity.setCodigoBanco(NumberUtils.toInt(codigoBanco));
			} else {
				this.entity.setCodigoBanco(null);
			}

			this.entity.setCodigoCedente(this.subView.getTfCodigoCedente()
					.getValue());
			this.entity
					.setContaBanco(this.subView.getTfContaBanco().getValue());

			this.entity.setFone1(this.subView.getTfTelefone1().getValue());
			this.entity.setFone2(this.subView.getTfTelefone2().getValue());
			this.entity.setNumero(this.subView.getTfNumero().getValue());

			String pisoSalarial = this.subView.getTfPisoSalarial().getValue();

			if (NumberUtils.isNumber(pisoSalarial)) {
				this.entity.setPisoSalarial(NumberUtils
						.createBigDecimal(pisoSalarial));
			} else {
				this.entity.setPisoSalarial(null);
			}

			TipoSindicatoEn tipoSindicatoEn = (TipoSindicatoEn) this.subView
					.getCbTipo().getValue();

			this.entity.setTipoSindicato(tipoSindicatoEn);

			UfEntity uf = this.subView.getMocUf().getValue();

			if (ObjectUtils.isNotBlank(uf)) {
				this.entity.setUfSigla(uf.getSigla());
			}

			this.business.saveOrUpdate(this.entity);

			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.entity = this.business.find(id);

			this.subView.getTfNome().setValue(this.entity.getNome());
			this.subView.getTfLogradouro()
					.setValue(this.entity.getLogradouro());
			this.subView.getTfEmail().setValue(this.entity.getEmail());
			this.subView.getPdfDataBase().setValue(this.entity.getDataBase());
			this.subView.getTfBairro().setValue(this.entity.getBairro());
			this.subView.getTfCnpj().setValue(this.entity.getCnpj());

			if (NumberUtils.isNotBlank(this.entity.getCodigoAgencia())) {
				this.subView.getTfCodigoAgencia().setValue(
						this.entity.getCodigoAgencia().toString());
			}

			if (NumberUtils.isNotBlank(this.entity.getCodigoBanco())) {
				this.subView.getTfCodigoBanco().setValue(
						this.entity.getCodigoBanco().toString());
			}

			this.subView.getTfCodigoCedente().setValue(
					this.entity.getCodigoCedente());
			this.subView.getTfContaBanco()
					.setValue(this.entity.getContaBanco());
			this.subView.getTfTelefone1().setValue(this.entity.getFone1());
			this.subView.getTfTelefone2().setValue(this.entity.getFone2());
			this.subView.getTfNumero().setValue(this.entity.getNumero());

			if (NumberUtils.isNotBlank(this.entity.getPisoSalarial())) {
				this.subView.getTfPisoSalarial().setValue(
						this.entity.getPisoSalarial().toString());
			}

			ContabilContaEntity contabilConta = this.entity.getContabilConta();

			if (ObjectUtils.isNotBlank(contabilConta)) {
				this.subView.getMocContabilConta().setValue(contabilConta);
			}

			TipoSindicatoEn tipoSindicatoEn = this.entity.getTipoSindicato();

			this.subView.getCbTipo().setValue(tipoSindicatoEn);

			String ufSigla = this.entity.getUfSigla();

			if (StringUtils.isNotBlank(ufSigla)) {
				UfEntity uf = this.ufBusiness.getObject(ufSigla);

				this.subView.getMocUf().setValue(uf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new SindicatoEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void quandoNovo() {
		try {
			this.entity = new SindicatoEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.business.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		try {

		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/**
	 * 
	 */

	public void comboTipoSindicato() {
		for (TipoSindicatoEn en : TipoSindicatoEn.values()) {
			this.subView.getCbTipo().addItem(en);
		}
	}

}