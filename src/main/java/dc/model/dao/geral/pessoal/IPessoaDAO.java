package dc.model.dao.geral.pessoal;

import dc.entidade.geral.pessoal.PessoaEntity;
import dc.entidade.geral.pessoal.PessoaFisicaEntity;
import dc.model.dao.AbstractDAO;

public interface IPessoaDAO extends AbstractDAO<PessoaEntity> {

	PessoaFisicaEntity getPessoaFisica(Integer id);

}