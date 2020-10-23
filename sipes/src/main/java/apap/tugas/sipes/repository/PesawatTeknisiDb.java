package apap.tugas.sipes.repository;

import apap.tugas.sipes.model.PesawatTeknisiModel;
import apap.tugas.sipes.model.TeknisiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PesawatTeknisiDb extends JpaRepository<PesawatTeknisiModel, Long> {

    List<PesawatTeknisiModel> findAllByPesawatId(Long id);
    List<TeknisiModel> findAllByTeknisiId(Long id);

}
