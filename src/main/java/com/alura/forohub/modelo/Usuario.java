package com.alura.forohub.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String contrasena;

//    public Usuario(DatosRegistroUsuario datos) {
//        this.nombre = datos.nombre();
//        this.email = datos.email();
//        this.contrasena = datos.contrasena();
//    }
//
//    public void actualizarDatos(DatosActualizarUsuario datosActualizar) {
//        if (datosActualizar.nombre() != null) {
//            this.nombre = datosActualizar.nombre();
//        }
//        if (datosActualizar.email() != null) {
//            this.email = datosActualizar.email();
//        }
//        if (datosActualizar.contrasena() != null) {
//            this.contrasena = datosActualizar.contrasena();
//        }
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
