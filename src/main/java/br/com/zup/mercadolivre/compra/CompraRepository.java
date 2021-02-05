package br.com.zup.mercadolivre.compra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface CompraRepository extends JpaRepository<Compra, Long> {

    Optional<Compra> findByTransacaoId(String id);
}
