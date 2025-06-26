package cine.play.api.repository;

import cine.play.api.filme.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Long> {

    List<Filme> findAllByAtivoTrue();
    List<Filme> findByTituloContainingIgnoreCaseAndAtivoTrue(String titulo);

}
