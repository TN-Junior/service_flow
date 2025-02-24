package com.tary.ServiceFlow.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    String nome;

    @Column(nullable = false, unique = true, length = 14)
    String cpfCnpj;

    @Column(nullable = false, unique = true, length = 100)
    String email;

    @Column(length = 15)
    String telefone;

    @Column(nullable = false)
    private LocalDateTime dataCadastro = LocalDateTime.now();
}
