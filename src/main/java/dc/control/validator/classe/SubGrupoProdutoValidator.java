package dc.control.validator.classe;

import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.entidade.geral.produto.GrupoEntity;
import dc.visao.geral.produto.SubGrupoProdutoFormView;

public class SubGrupoProdutoValidator {

	public static void validaSalvar(SubGrupoProdutoFormView subView)
			throws DotErpException {
		String nome = subView.getTfNome().getValue();

		if (StringUtils.isBlank(nome)) {
			throw new DotErpException(subView.getTfNome(),
					"::DotERP - Não pode ficar em branco");
		}

		String descricao = subView.getTfDescricao().getValue();

		if (StringUtils.isBlank(descricao)) {
			throw new DotErpException(subView.getTfDescricao(),
					"::DotERP - Não pode ficar em branco");
		}

		GrupoEntity grupo = subView.getMocGrupoProduto().getValue();

		if (ObjectUtils.isBlank(grupo)) {
			throw new DotErpException(subView.getMocGrupoProduto(),
					"::DotERP - Não pode ficar em branco");
		}
	}

}