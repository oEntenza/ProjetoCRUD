package fatec.DemoMVCKAO.Services;

import fatec.DemoMVCKAO.Models.Produto;
import fatec.DemoMVCKAO.Repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service //para marcar e gerenciar classes que fornecem lógica de negócios ou funcionalidades de serviço em uma aplicação.
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    @Autowired //simplifica a injeção de dependência e facilita o desenvolvimento de aplicações com baixo acoplamento e alta coesão.
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto createProduto(Produto produto) {
        Produto obj = new Produto();
        if (produto == null) { throw new IllegalArgumentException("Produto não pode ser vazio."); }
        if (produto.getPreco() < 0) { throw new IllegalArgumentException("Preço não pode ser menor que zero."); }
        if (produto.getQuantidade() < 0) { throw new IllegalArgumentException("Quantidade não pode ser menor que zero."); }
        obj.setNome(produto.getNome());
        obj.setPreco(produto.getPreco());
        obj.setQuantidade(produto.getQuantidade());

        Produto newProduto = produtoRepository.save(obj);
        return newProduto;
    }

    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    public Produto getProdutoById(UUID id) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        return optionalProduto.orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public Produto updateProduto(UUID id, Produto produto) {
        Produto existingProduto = getProdutoById(id);
        if (produto.getNome() == null) { throw new IllegalArgumentException("Nome não pode ser vazio");}
        if (produto.getPreco() < 0) { throw new IllegalArgumentException("Preço não pode ser menor que zero."); }
        if (produto.getQuantidade() < 0) { throw new IllegalArgumentException("Quantidade não pode ser menor que zero."); }
        existingProduto.setNome(produto.getNome());
        existingProduto.setPreco(produto.getPreco());
        existingProduto.setQuantidade(produto.getQuantidade());
        return produtoRepository.save(existingProduto);
    }

    public void deleteProduto(UUID id) {
        Produto existingProduto = getProdutoById(id);
        produtoRepository.delete(existingProduto);
    }

}