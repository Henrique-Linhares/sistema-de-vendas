package br.com.aweb.sistema_de_vendas.model;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome é obrigatório")
    @Column(nullable = false, length = 100)
    private String nomeCompleto;

    @NotNull(message = "O email é obrigatório")
    @Column(nullable = false)
    @Email
    private String email;

    @NotNull(message = "O cpf é obrigatório")
    @Column(nullable = false)
    @CPF 
    private String cpf;

    @NotNull(message = "O telefone é obrigatório")
    @Column(nullable = false, length = 20)
    private String telefone;

    @Embedded
    @Valid 
    private Endereco endereco;
}
