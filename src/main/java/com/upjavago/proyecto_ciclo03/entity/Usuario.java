package com.upjavago.proyecto_ciclo03.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private long idUsuario;

    @javax.validation.constraints.NotEmpty
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @javax.validation.constraints.NotEmpty
    @Column(name = "apellido", nullable = false)
    private String apellido;

    @javax.validation.constraints.NotEmpty
    @Column(name = "cedula", unique = true)
    private String cedula;

    @Pattern(regexp = "[a-zA-Z0-9!#$%&'*_+-]([\\.]?[a-zA-Z0-9!#$%&'*_+-])+@[a-zA-Z0-9]([^@&%$\\/()=?¿!.,:;]|\\d)+[a-zA-Z0-9][\\.][a-zA-Z]{2,4}([\\.][a-zA-Z]{2})?" ,message = "Debe ser un correo electrónico válido")
    @Column(name = "correo", nullable = false)
    private String correo;

    @javax.validation.constraints.NotEmpty
    @Column(name = "username", unique = true)
    private String username;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])([A-Za-z\\d$@$!%*?&]|[^ ]){8,255}$" , message = "La contraseña debe contener minimo 8 caracteres, máximo 255, una letra mayúscula, una letra minuscula, un número, un caracter especial y sin espacios en blanco")
    @Column(name = "clave", nullable = false)
    private String clave;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol")
    private Rol rol;

    @NotNull
    @Column(name = "tipo_perfil")
    private TipoPerfil tipoPerfil;

    @Column(name = "estado")
    private boolean estado;
}
