package fatec.DemoMVCKAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fatec.DemoMVCKAO.Models.Produto;

import java.util.UUID;

@Repository //fornece acesso aos dados persistentes para a entidade Produto.
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
}