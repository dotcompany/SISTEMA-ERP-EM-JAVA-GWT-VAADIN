package dc.entidade.framework;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class AbstractMultiEmpresaModel<ID extends Serializable>
		extends AbstractModel<ID> {

	@OneToOne()
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa2) {
		this.empresa = empresa2;
	}

}