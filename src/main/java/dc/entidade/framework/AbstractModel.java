package dc.entidade.framework;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class AbstractModel<ID extends Serializable> implements Persistable<ID> {

	@Transient
	private transient final UUID uuid = UUID.randomUUID();
	
	@Override
	public int hashCode() {
		final int prime = 53;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AbstractModel)) {
			return false;
		}
		AbstractModel<?> other = (AbstractModel<?>) obj;
		if (getId() == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!getId().equals(other.getId())) {
			return false;
		}
		return true;
	}

	public boolean isNew() {
		return getId() == null;
	}
	
	public final UUID getUuid() {
		return uuid;
	}
}

