package br.com.aweb.sistema_de_vendas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor 
public class Endereco {

    @NotNull(message = "O logradouro é obrigatório")
    @Column(nullable = false, length = 120)
    private String logradouro;

    @Column(nullable = true)
    private Integer numero;

    @Column(nullable = true, length = 200)
    private String complemento;

    @NotNull(message = "O bairro é obrigatório")
    @Column(nullable = false, length = 100)
    private String bairro;

    @NotNull(message = "A cidade é obrigatório")
    @Column(nullable = false, length = 100)
    private String cidade;

    @NotNull(message = "O UF é obrigatório")
    @Column(nullable = false, length = 50)
    private String uf;

    @NotNull(message = "O CEP é obrigatório")
    @Column(nullable = false, length = 10)
    private String cep;
}
