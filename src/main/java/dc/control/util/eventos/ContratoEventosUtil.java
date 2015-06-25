package dc.control.util.eventos;

import dc.control.enums.TipoSemestre;
import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.geral.eventos.ContratoEventosFormView;

public class ContratoEventosUtil {

	public static void validateRequiredFields(ContratoEventosFormView subView) throws DotErpException {
		
		TipoSemestre tipoSemestre = (TipoSemestre) subView.getCbTipoSemestre().getValue();

		if (ObjectUtils.isBlank(tipoSemestre)) {
			throw new DotErpException(subView.getCbTipoSemestre(),"::DotERP - Não pode ficar em branco");
		}

		String curso = subView.getTxtCurso().getValue();

		if (StringUtils.isBlank(curso)) {
			throw new DotErpException(subView.getTxtCurso(),"::DotERP - Não pode ficar em branco");
		}

		String unidade = subView.getTxtUnidade().getValue();

		if (StringUtils.isBlank(unidade)) {
			throw new DotErpException(subView.getTxtUnidade(),"::DotERP - Não pode ficar em branco");
		}

		String ano = subView.getTxtAnoFormatura().getValue();

		if (StringUtils.isBlank(ano)) {
			throw new DotErpException(subView.getTxtAnoFormatura(),	"::DotERP - Não pode ficar em branco");
		}

		//

		if (tipoSemestre.equals(TipoSemestre.P)) {
		}

	}

	//

	public static void clearFormFields(ContratoEventosFormView subView) {

	}

}