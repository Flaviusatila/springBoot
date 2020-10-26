package com.algaworks.osworks.domain.model;

import com.algaworks.osworks.domain.ValidationGroups;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class Cliente {

        @NotNull(groups = ValidationGroups.ClienteId.class)
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotNull
        @Size(max = 60)
        private String nome;

        @NotNull
        @Size(max = 255)
        private String email;

        @NotNull
        @Column(name = "fone")
        @Size(max = 20)
        private String telefone;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTelefone() {
            return telefone;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            com.algaworks.osworks.domain.model.Cliente cliente = ( com.algaworks.osworks.domain.model.Cliente ) o;
            return Objects.equals( id, cliente.id );
        }

        @Override
        public int hashCode() {
            return Objects.hash( id );
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }
    }

