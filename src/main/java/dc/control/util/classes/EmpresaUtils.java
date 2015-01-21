package dc.control.util.classes;

import java.util.Date;

import dc.control.util.DateUtils;
import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.framework.Fpas;
import dc.entidade.geral.CnaeEntity;
import dc.entidade.geral.outro.SindicatoEntity;
import dc.entidade.geral.pessoal.ContadorEntity;
import dc.visao.administrativo.empresa.EmpresaFormView;

public class EmpresaUtils {

	public static void validateRequiredFields(EmpresaFormView subView)
			throws DotErpException {
		String razaoSocial = subView.getTfRazaoSocial().getValue();

		if (StringUtils.isBlank(razaoSocial)) {
			throw new DotErpException(subView.getTfRazaoSocial(),
					"::DotERP - Não pode ficar em branco");
		}

		String nomeFantasia = subView.getTfNomeFantasia().getValue();

		if (StringUtils.isBlank(nomeFantasia)) {
			throw new DotErpException(subView.getTfNomeFantasia(),
					"::DotERP - Não pode ficar em branco");
		}

		ContadorEntity contador = (ContadorEntity) subView.getCbContador()
				.getValue();

		if (ObjectUtils.isBlank(contador)) {
			throw new DotErpException(subView.getCbContador(),
					"::DotERP - Não pode ficar em branco");
		}

		SindicatoEntity sindicato = (SindicatoEntity) subView.getCbSindicato()
				.getValue();

		if (ObjectUtils.isBlank(sindicato)) {
			throw new DotErpException(subView.getCbSindicato(),
					"::DotERP - Não pode ficar em branco");
		}

		Fpas fpas = (Fpas) subView.getCbFpas().getValue();

		if (ObjectUtils.isBlank(fpas)) {
			throw new DotErpException(subView.getCbFpas(),
					"::DotERP - Não pode ficar em branco");
		}

		Date dataInicioAtividades = subView.getPdfDataInicioAtividades()
				.getValue();

		if (DateUtils.isBlank(dataInicioAtividades)) {
			throw new DotErpException(subView.getPdfDataInicioAtividades(),
					"::DotERP - Não pode ficar em branco");
		}

		String cnpj = subView.getMtfCnpj().getValue();

		if (StringUtils.isBlank(cnpj)) {
			throw new DotErpException(subView.getMtfCnpj(),
					"::DotERP - Não pode ficar em branco");
		}

		String inscricaoEstadual = subView.getTfInscricaoEstadual().getValue();

		if (StringUtils.isBlank(inscricaoEstadual)) {
			throw new DotErpException(subView.getTfInscricaoEstadual(),
					"::DotERP - Não pode ficar em branco");
		}

		String inscricaoEstadualSt = subView.getTfInscricaoEstadualSt()
				.getValue();

		if (StringUtils.isBlank(inscricaoEstadualSt)) {
			throw new DotErpException(subView.getTfInscricaoEstadualSt(),
					"::DotERP - Não pode ficar em branco");
		}

		String inscricaoMunicipal = subView.getTfInscricaoMunicipal()
				.getValue();

		if (StringUtils.isBlank(inscricaoMunicipal)) {
			throw new DotErpException(subView.getTfInscricaoMunicipal(),
					"::DotERP - Não pode ficar em branco");
		}

		String inscricaoJuntaComercial = subView.getTfInscricaoJuntaComercial()
				.getValue();

		if (StringUtils.isBlank(inscricaoJuntaComercial)) {
			throw new DotErpException(subView.getTfInscricaoJuntaComercial(),
					"::DotERP - Não pode ficar em branco");
		}

		Date dataInscricaoJuntaComercial = subView
				.getPdfDataInscricaoJuntaComercial().getValue();

		if (DateUtils.isBlank(dataInscricaoJuntaComercial)) {
			throw new DotErpException(
					subView.getPdfDataInscricaoJuntaComercial(),
					"::DotERP - Não pode ficar em branco");
		}

		String suframa = subView.getTfSuframa().getValue();

		if (StringUtils.isBlank(suframa)) {
			throw new DotErpException(subView.getTfSuframa(),
					"::DotERP - Não pode ficar em branco");
		}

		String contato = subView.getTfContato().getValue();

		if (StringUtils.isBlank(contato)) {
			throw new DotErpException(subView.getTfContato(),
					"::DotERP - Não pode ficar em branco");
		}

		String codigoTerceiros = subView.getTfCodigoTerceiros().getValue();

		if (StringUtils.isBlank(codigoTerceiros)) {
			throw new DotErpException(subView.getTfCodigoTerceiros(),
					"::DotERP - Não pode ficar em branco");
		}

		String cei = subView.getTfCei().getValue();

		if (StringUtils.isBlank(cei)) {
			throw new DotErpException(subView.getTfCei(),
					"::DotERP - Não pode ficar em branco");
		}

		String aliquotaPis = subView.getTfAliquotaPis().getValue();

		if (StringUtils.isBlank(aliquotaPis)) {
			throw new DotErpException(subView.getTfAliquotaPis(),
					"::DotERP - Não pode ficar em branco");
		}

		String aliquotaCofins = subView.getTfAliquotaCofins().getValue();

		if (StringUtils.isBlank(aliquotaCofins)) {
			throw new DotErpException(subView.getTfAliquotaCofins(),
					"::DotERP - Não pode ficar em branco");
		}

		String aliquotaSat = subView.getTfAliquotaSat().getValue();

		if (StringUtils.isBlank(aliquotaSat)) {
			throw new DotErpException(subView.getTfAliquotaSat(),
					"::DotERP - Não pode ficar em branco");
		}

		String codigoGps = subView.getTfCodigoGps().getValue();

		if (StringUtils.isBlank(codigoGps)) {
			throw new DotErpException(subView.getTfCodigoGps(),
					"::DotERP - Não pode ficar em branco");
		}

		String codigoMunicipio = subView.getTfMunicipio().getValue();

		if (StringUtils.isBlank(codigoMunicipio)) {
			throw new DotErpException(subView.getTfMunicipio(),
					"::DotERP - Não pode ficar em branco");
		}

		String codigoUf = subView.getTfUf().getValue();

		if (StringUtils.isBlank(codigoUf)) {
			throw new DotErpException(subView.getTfUf(),
					"::DotERP - Não pode ficar em branco");
		}

		CnaeEntity cnaePrincipal = (CnaeEntity) subView.getCbCnaePrincipal()
				.getValue();

		if (ObjectUtils.isBlank(cnaePrincipal)) {
			throw new DotErpException(subView.getCbCnaePrincipal(),
					"::DotERP - Não pode ficar em branco");
		}

		EmpresaEntity empresaMatriz = (EmpresaEntity) subView.getCbMatriz()
				.getValue();

		if (ObjectUtils.isBlank(empresaMatriz)) {
			throw new DotErpException(subView.getCbMatriz(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	public static void validateFieldValue(EmpresaFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(EmpresaFormView subView) {

	}

}