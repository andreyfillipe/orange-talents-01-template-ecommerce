package br.com.zup.mercadolivre.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> salvar(@RequestBody @Valid UsuarioRequest request) {
        Usuario usuario = request.toModel();
        usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }
}
