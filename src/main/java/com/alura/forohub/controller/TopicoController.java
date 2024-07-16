package com.alura.forohub.controller;

import com.alura.forohub.DTO.DatosActualizarTopico;
import com.alura.forohub.DTO.DatosCreacionTopico;
import com.alura.forohub.DTO.DatosDetalleTopico;
import com.alura.forohub.DTO.DatosListadoTopico;
import com.alura.forohub.modelo.Curso;
import com.alura.forohub.modelo.Topico;
import com.alura.forohub.modelo.Usuario;
import com.alura.forohub.repository.CursoRepository;
import com.alura.forohub.repository.TopicoRepository;
import com.alura.forohub.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;


    //  Registro de un nuevo tópico
    @PostMapping
    @Transactional
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosCreacionTopico datosCreacionTopico,
                                          UriComponentsBuilder uriComponentsBuilder) {

        if (topicoRepository.existsByTituloAndMensaje(datosCreacionTopico.titulo(), datosCreacionTopico.mensaje())) {
            return ResponseEntity.badRequest().body("El tópico ya existe");
        } else if (!usuarioRepository.findById(datosCreacionTopico.autorId()).isPresent()
                || !cursoRepository.findById(datosCreacionTopico.cursoId()).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Topico topico = topicoRepository.save(new Topico(
                datosCreacionTopico.titulo(),
                datosCreacionTopico.mensaje(),
                usuarioRepository.findById(datosCreacionTopico.autorId()).get(),
                cursoRepository.findById(datosCreacionTopico.cursoId()).get()
        ));

        DatosDetalleTopico datosDetalleTopico = new DatosDetalleTopico(topico);

        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosDetalleTopico);

    }


    //  Mostrar todos los tópicos
    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicos(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosListadoTopico::new));
    }


    //  Detallando un tópico
    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> retornaDetalleTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        var datosDetalleTopico = new DatosDetalleTopico(topico);

        return ResponseEntity.ok(datosDetalleTopico);
    }


    //  Actualizar un tópico
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        if (!topicoRepository.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Topico topico = topicoRepository.getReferenceById(id);
        Usuario autor;
        Curso curso;

        if (datosActualizarTopico.autorId() != null){
            if (!usuarioRepository.findById(datosActualizarTopico.autorId()).isPresent()){
                autor = null;
            } else {
                autor = usuarioRepository.findById(datosActualizarTopico.autorId()).get();
            }
        } else {
            autor = null;
        }

        if (datosActualizarTopico.cursoId() != null){
            if (!cursoRepository.findById(datosActualizarTopico.cursoId()).isPresent()){
                curso = null;
            } else {
                curso = cursoRepository.findById(datosActualizarTopico.cursoId()).get();
            }
        } else {
            curso = null;
        }

        topico.actualizarDatos(datosActualizarTopico, autor, curso);

        DatosDetalleTopico datosDetalleTopico = new DatosDetalleTopico(topico);

        return ResponseEntity.ok(datosDetalleTopico);
    }


    //  Eliminar un tópico (delete lógico)
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        if (!topicoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
