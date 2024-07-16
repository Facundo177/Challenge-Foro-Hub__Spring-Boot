package com.alura.forohub.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosCreacionTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje ,
        @NotNull
        Long autorId,
        @NotNull
        Long cursoId

    ) {
}
