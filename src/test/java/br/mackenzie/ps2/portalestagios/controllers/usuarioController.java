package br.mackenzie.ps2.portalestagios.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.mackenzie.ps2.portalestagios.entities.Usuario;
import br.mackenzie.ps2.portalestagios.repository.usuarioRepository;

@RestController
public class usuarioController {
    @Autowired
    private usuarioRepository usuarioRep;
    List<Usuario> lstUsuarios = new ArrayList<>();
    //CRUD - USUARIO

    //CREATE
    @PostMapping("/usuario")
    public Usuario createUsuario(@RequestBody Usuario newUsuario){
        if(newUsuario.getLogin() == null || newUsuario.getSenha() == null 
        || newUsuario.getLogin().isEmpty() || newUsuario.getSenha().isEmpty()){

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return usuarioRep.save(newUsuario);
    }

    //READ
    @GetMapping("/usuario")
    public List<Usuario> getAllUsuarios(){
        return usuarioRep.findAll();
    }

    //UPDATE
    @PutMapping("/usuario/{id}")
    public Usuario updateUsuarioById(@RequestBody Usuario newData, @PathVariable Long id){
        Optional<Usuario> optional = usuarioRep.findById(id);
        if(optional.isPresent()){
            Usuario newUsuario = optional.get();
            newUsuario.setLogin(newData.getLogin());
            newUsuario.setSenha(newData.getSenha());
            return usuarioRep.save(newUsuario);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    //DELETE
    @DeleteMapping("/usuario/{id}")
    public Usuario deleteUsuarioById(@PathVariable Long id){
        Optional<Usuario> optional = usuarioRep.findById(id);
        if(optional.isPresent()){
            Usuario del = optional.get();
            usuarioRep.deleteById(id);
            return del;

        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
