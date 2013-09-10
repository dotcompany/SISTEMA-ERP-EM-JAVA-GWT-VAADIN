	package dc.servicos.dao.framework;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import dc.entidade.framework.AbstractModel;

public abstract interface AbstractCrudDAO<M extends AbstractModel<ID>, ID extends Serializable>
		extends JpaRepository<M, ID> {

}
