package onsales.api.repository;

import onsales.api.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EstoqueRepository extends JpaRepository<Estoque, UUID> {
    Estoque findByProdutoId(UUID id);
}
