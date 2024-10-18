package br.senac.rj.grupo1.carrinhodecompra.interfacefeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.senac.rj.grupo1.carrinhodecompra.dto.NotaFiscalDTO;

@Component
@FeignClient (name="Pagamento", url="10.136.65.166:8080", path="/notas-fiscais")
public interface NotaFiscalFeignClient {
	@PostMapping
	public ResponseEntity<NotaFiscalDTO> adicionarNotaFiscal (@RequestBody NotaFiscalDTO notaFiscalDTO);
}
