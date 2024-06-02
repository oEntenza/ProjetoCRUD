function init() {
    document.getElementById('list-section').style.display = 'block';
    document.getElementById('create-section').style.display = 'none';
    document.getElementById('edit-section').style.display = 'none';
    document.getElementById('delete-section').style.display = 'none';
    document.getElementById('read-section').style.display = 'none';
}

function showSection(sectionId) {
    const sections = document.querySelectorAll('.section');
    sections.forEach(section => {
        section.style.display = 'none';
    });
    document.getElementById(sectionId).style.display = 'block';
}

function criarProduto() {
    const nome = document.getElementById('nome').value;
    const quantidade = document.getElementById('quantidade').value;
    const preco = document.getElementById('preco').value;

    fetch('/api/produtos', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ nome, quantidade, preco }),
    })
        .then(response => response.json())
        .then(data => {
            alert('Produto criado com sucesso!');
            window.location.reload();
        })
        .catch(error => {
            console.error('Erro ao criar produto:', error);
        });
}

function editarProduto() {
    const id = document.getElementById('id-edit').value;
    const nome = document.getElementById('nome-edit').value;
    const quantidade = document.getElementById('quantidade-edit').value;
    const preco = document.getElementById('preco-edit').value;

    fetch(`/api/produtos/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ nome, quantidade, preco }),
    })
        .then(response => response.json())
        .then(data => {
            alert('Produto editado com sucesso!');
            window.location.reload();
        })
        .catch(error => {
            console.error('Erro ao editar produto:', error);
        });
}

function deletarProduto() {
    const id = document.getElementById('id-delete').value;

    fetch(`/api/produtos/${id}`, {
        method: 'DELETE',
    })
        .then(response => response.json())
        .then(data => {
            alert('Produto deletado com sucesso!');
            window.location.reload();
        })
        .catch(error => {
            console.error('Erro ao deletar produto:', error);
        });
}

function buscarProduto() {
    const id = document.getElementById('id-read').value;

    fetch(`/api/produtos/${id}`)
        .then(response => response.json())
        .then(data => {
            const resultadoDiv = document.getElementById('resultado-busca');
            if (data) {
                resultadoDiv.innerHTML = `<p>ID: ${data.id}</p>
                                           <p>Nome: ${data.nome}</p>
                                           <p>Quantidade: ${data.quantidade}</p>
                                           <p>Preço: R$ ${data.preco}</p>`;
            } else {
                resultadoDiv.innerHTML = '<p>Produto não encontrado</p>';
            }
        })
        .catch(error => {
            console.error('Erro ao buscar produto:', error);
        });
}
