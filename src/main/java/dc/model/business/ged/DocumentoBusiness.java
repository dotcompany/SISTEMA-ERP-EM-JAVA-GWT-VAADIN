
package dc.model.business.ged;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Scope;

import dc.entidade.geral.ged.Documento;

@org.springframework.stereotype.Component
@Scope("singleton")
public interface DocumentoBusiness {
	public void gravarAnexo(Documento documento, List<String> listArquivos, File certificado, String senhaCertificado) throws IOException;

	public Integer getProximoNumeroVersao(Documento documento);
}
