package fatec.DemoMVCKAO.Services;

import fatec.DemoMVCKAO.Models.Produto;
import fatec.DemoMVCKAO.Repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto createProduto(Produto produto) {
        Produto obj = new Produto();
        obj.setNome(produto.getNome());
        obj.setPreco(produto.getPreco());
        obj.setQuantidade(produto.getQuantidade());

        Produto newProduto = produtoRepository.save(obj);
        return newProduto;
    }

    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    public Produto getProdutoById(Integer id) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        return optionalProduto.orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public Produto updateProduto(Integer id, Produto produto) {
        Produto existingProduto = getProdutoById(id);
        existingProduto.setNome(produto.getNome());
        existingProduto.setPreco(produto.getPreco());
        existingProduto.setQuantidade(produto.getQuantidade());
        return produtoRepository.save(existingProduto);
    }

    public void deleteProduto(Integer id) {
        Produto existingProduto = getProdutoById(id);
        produtoRepository.delete(existingProduto);
    }

}
