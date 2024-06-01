package fatec.DemoMVCKAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fatec.DemoMVCKAO.Models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
