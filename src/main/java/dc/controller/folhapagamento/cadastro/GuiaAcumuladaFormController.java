package dc.controller.folhapagamento.cadastro;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.folhapagamento.cadastro.GuiaAcumuladaEntity;
import dc.entidade.framework.Empresa;
import dc.servicos.dao.folhapagamento.cadastro.GuiaAcumuladaDAO;
import dc.visao.folhapagamento.cadastro.GuiaAcumuladaFormView;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class GuiaAcumuladaFormController extends
		CRUDFormController<GuiaAcumuladaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GuiaAcumuladaFormView subView;

	/**
	 * DAO'S
	 */

	@Autowired
	private GuiaAcumuladaDAO pDAO;

	/**
	 * ENTITIES
	 */

	private GuiaAcumuladaEntity pEntity;

	/**
	 * CONSTRUTOR
	 */

	public GuiaAcumuladaFormController() {
		if (this.pEntity == null) {
			this.pEntity = new GuiaAcumuladaEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Guia acumulada";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String gpsTipo = this.subView.getTfGpsTipo().getValue();
			String gpsCompetencia = this.subView.getTfGpsCompetencia()
					.getValue();
			Double gpsValorInss = Double.parseDouble(this.subView
					.getTfGpsValorInss().getValue());
			Double gpsValorOutrasEnt = Double.parseDouble(this.subView
					.getTfGpsValorOutrasEnt().getValue());
			Date gpsDataPagamento = this.subView.getPdfGpsDataPagamento()
					.getValue();
			String irrfCompetencia = this.subView.getTfIrrfCompetencia()
					.getValue();
			Integer irrfCodigoRecolhimento = Integer.parseInt(this.subView
					.getTfIrrfCodigoRecolhimento().getValue());
			Double irrfValorAcumulado = Double.parseDouble(this.subView
					.getTfIrrfValorAcumulado().getValue());
			Date irrfDataPagamento = this.subView.getPdfIrrfDataPagamento()
					.getValue();
			String pisCompetencia = this.subView.getTfPisCompetencia()
					.getValue();
			Double pisValorAcumulado = Double.parseDouble(this.subView
					.getTfPisValorAcumulado().getValue());
			Date pisDataPagamento = this.subView.getPdfPisDataPagamento()
					.getValue();

			this.pEntity.setGpsTipo(gpsTipo);
			this.pEntity.setGpsCompetencia(gpsCompetencia);
			this.pEntity.setGpsValorInss(gpsValorInss);
			this.pEntity.setGpsValorOutrasEnt(gpsValorOutrasEnt);
			this.pEntity.setGpsDataPagamento(gpsDataPagamento);
			this.pEntity.setIrrfCompetencia(irrfCompetencia);
			this.pEntity.setIrrfCodigoRecolhimento(irrfCodigoRecolhimento);
			this.pEntity.setIrrfValorAcumulado(irrfValorAcumulado);
			this.pEntity.setIrrfDataPagamento(irrfDataPagamento);
			this.pEntity.setPisCompetencia(pisCompetencia);
			this.pEntity.setPisValorAcumulado(pisValorAcumulado);
			this.pEntity.setPisDataPagamento(pisDataPagamento);

			/**
			 * Empresa vinda da conta do usuário logado
			 */

			Empresa empresa = SecuritySessionProvider.getUsuario().getConta()
					.getEmpresa();

			this.pEntity.setEmpresa(empresa);

			/**
			 * **************************************
			 */

			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			criarNovo();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = this.pDAO.find(id);

			this.subView.getTfGpsTipo().setValue(this.pEntity.getGpsTipo());
			this.subView.getTfGpsCompetencia().setValue(
					this.pEntity.getGpsCompetencia());
			this.subView.getTfGpsValorInss().setValue(
					this.pEntity.getGpsValorInss().toString());
			this.subView.getTfGpsValorOutrasEnt().setValue(
					this.pEntity.getGpsValorOutrasEnt().toString());
			this.subView.getPdfGpsDataPagamento().setValue(
					this.pEntity.getGpsDataPagamento());
			this.subView.getTfIrrfCompetencia().setValue(
					this.pEntity.getIrrfCompetencia());
			this.subView.getTfIrrfCodigoRecolhimento().setValue(
					this.pEntity.getIrrfCodigoRecolhimento().toString());
			this.subView.getTfIrrfValorAcumulado().setValue(
					this.pEntity.getIrrfValorAcumulado().toString());
			this.subView.getPdfIrrfDataPagamento().setValue(
					this.pEntity.getIrrfDataPagamento());
			this.subView.getTfPisCompetencia().setValue(
					this.pEntity.getPisCompetencia());
			this.subView.getTfPisValorAcumulado().setValue(
					this.pEntity.getPisValorAcumulado().toString());
			this.subView.getPdfPisDataPagamento().setValue(
					this.pEntity.getPisDataPagamento());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {
		try {
			this.pEntity = new GuiaAcumuladaEntity();

			this.subView.getTfGpsTipo().setValue(this.pEntity.getGpsTipo());
			this.subView.getTfGpsCompetencia().setValue(
					this.pEntity.getGpsCompetencia());
			this.subView.getTfGpsValorInss().setValue(
					this.pEntity.getGpsValorInss().toString());
			this.subView.getTfGpsValorOutrasEnt().setValue(
					this.pEntity.getGpsValorOutrasEnt().toString());
			this.subView.getPdfGpsDataPagamento().setValue(
					this.pEntity.getGpsDataPagamento());
			this.subView.getTfIrrfCompetencia().setValue(
					this.pEntity.getIrrfCompetencia());
			this.subView.getTfIrrfCodigoRecolhimento().setValue(
					this.pEntity.getIrrfCodigoRecolhimento().toString());
			this.subView.getTfIrrfValorAcumulado().setValue(
					this.pEntity.getIrrfValorAcumulado().toString());
			this.subView.getPdfIrrfDataPagamento().setValue(
					this.pEntity.getIrrfDataPagamento());
			this.subView.getTfPisCompetencia().setValue(
					this.pEntity.getPisCompetencia());
			this.subView.getTfPisValorAcumulado().setValue(
					this.pEntity.getPisValorAcumulado().toString());
			this.subView.getPdfPisDataPagamento().setValue(
					this.pEntity.getPisDataPagamento());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new GuiaAcumuladaFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			if (this.pDAO == null) {
				this.pDAO = new GuiaAcumuladaDAO();
			}

			this.pEntity = new GuiaAcumuladaEntity();

			this.subView.getTfGpsTipo().setValue(this.pEntity.getGpsTipo());
			this.subView.getTfGpsCompetencia().setValue(
					this.pEntity.getGpsCompetencia());
			this.subView.getTfGpsValorInss().setValue(
					this.pEntity.getGpsValorInss().toString());
			this.subView.getTfGpsValorOutrasEnt().setValue(
					this.pEntity.getGpsValorOutrasEnt().toString());
			this.subView.getPdfGpsDataPagamento().setValue(
					this.pEntity.getGpsDataPagamento());
			this.subView.getTfIrrfCompetencia().setValue(
					this.pEntity.getIrrfCompetencia());
			this.subView.getTfIrrfCodigoRecolhimento().setValue(
					this.pEntity.getIrrfCodigoRecolhimento().toString());
			this.subView.getTfIrrfValorAcumulado().setValue(
					this.pEntity.getIrrfValorAcumulado().toString());
			this.subView.getPdfIrrfDataPagamento().setValue(
					this.pEntity.getIrrfDataPagamento());
			this.subView.getTfPisCompetencia().setValue(
					this.pEntity.getPisCompetencia());
			this.subView.getTfPisValorAcumulado().setValue(
					this.pEntity.getPisValorAcumulado().toString());
			this.subView.getPdfPisDataPagamento().setValue(
					this.pEntity.getPisDataPagamento());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.pDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "folhapagamento_cadastro_guia_acumulada_fc";
	}

	/**
	 * COMBOS
	 */

}