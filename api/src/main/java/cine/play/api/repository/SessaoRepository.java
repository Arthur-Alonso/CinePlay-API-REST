package cine.play.api.repository;

import cine.play.api.sessao.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {


    List<Sessao> findAllByAtivoTrue();

    List<Sessao> findByDataAndAtivoTrue(LocalDate data);

}