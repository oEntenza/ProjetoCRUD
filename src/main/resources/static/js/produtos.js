function init() {
    listarProdutos();
}

function buscarProduto() {
    const id = document.getElementById('id-read').value;

    fetch(`http://localhost:8080/produto/${id}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Produto não encontrado');
        }
    })
    .then(data => {
        const resultadoDiv = document.getElementById('resultado-busca');
        resultadoDiv.innerHTML = `
            <h3>Resultado da Busca</h3>
            <p>ID: ${data.id}</p>
            <p>Nome: ${data.nome}</p>
            <p>Quantidade: ${data.quantidade}</p>
            <p>Preço: R$ ${data.preco}</p>
        `;
    })
    .catch(error => {
        console.error('Erro ao buscar produto:', error);
        const resultadoDiv = document.getElementById('resultado-busca');
        resultadoDiv.innerHTML = `<p>${error.message}</p>`;
    });
}

function deletarProduto() {
    const id = document.getElementById('id-delete').value;
    fetch(`http://localhost:8080/produto/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (response.ok) {
            alert('Produto deletado com sucesso!');
        } else if (response.status === 400) {
            throw new Error('Erro ao deletar produto. Verifique o ID e tente novamente.');
        } else {
            throw new Error('Erro desconhecido ao deletar produto.');
        }
    })
    .catch(error => {
        console.error('Erro ao deletar produto:', error);
        alert(error.message);
    });
}

function editarProduto() {
    const form = document.getElementById('edit-form');

    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }

    const id = document.getElementById('id-edit').value;
    const nome = document.getElementById('nome-edit').value;
    const quantidade = document.getElementById('quantidade-edit').value;
    const preco = document.getElementById('preco-edit').value;

    const produto = {
        nome: nome,
        quantidade: quantidade,
        preco: preco
    };

    fetch(`http://localhost:8080/produto/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(produto)
    })
    .then(response => {
        if (!response.ok) {
            return response.json().then(data => { throw new Error(data.message || 'Erro ao editar produto'); });
        }
        return response.json();
    })
    .then(data => {
        form.reset();
        alert('Produto editado com sucesso!');
    })
    .catch(error => {
        console.error('Erro ao editar produto:', error);
        alert(error.message);
    });
}

function criarProduto() {
    const form = document.getElementById('create-form');

    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }

    const nome = document.getElementById('nome').value;
    const quantidade = document.getElementById('quantidade').value;
    const preco = document.getElementById('preco').value;

    const produto = {
        nome: nome,
        quantidade: quantidade,
        preco: preco
    };

    fetch('http://localhost:8080/produto/create-produto', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(produto)
    })
    .then(response => {
        return response.json().then(data => ({ status: response.status, body: data }));
    })
    .then(response => {
        if (response.status === 201) {
            form.reset();
            alert('Produto criado com sucesso!');
        } else if (response.status === 400) {
            let errorMessages = '';
            for (const [key, value] of Object.entries(response.body)) {
                errorMessages += `${value}\n`;
            }
            alert(errorMessages);
        } else {
            throw new Error('Erro ao criar produto');
        }
    })
    .catch(error => {
        console.error('Erro ao criar produto:', error);
        alert('Erro ao criar produto. Verifique os campos e tente novamente.');
    });
}

function listarProdutos() {
    fetch('http://localhost:8080/produto/all', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Erro ao listar produtos');
        }
    })
    .then(data => {
        const tbody = document.querySelector('table tbody');
        tbody.innerHTML = '';
        data.forEach(produto => {
            const newRow = document.createElement('tr');
            newRow.innerHTML = `
                <td>${produto.id}</td>
                <td>${produto.nome}</td>
                <td>${produto.quantidade}</td>
                <td>R$ ${produto.preco}</td>
            `;
            tbody.appendChild(newRow);
        });
    })
    .catch(error => {
        console.error('Erro:', error);
    });
}

document.addEventListener("scroll", function(){
    const parallax = document.querySelector(".parallax");
    let scrollPosition = window.pageYOffset;
    parallax.style.backgroundPositionY = -scrollPosition * 0.5 + "px";
});

function showSection(sectionId) {
    if (sectionId == 'list-section') {
        listarProdutos();
        console.log("funfou");
    }
    const sections = document.querySelectorAll('.section');
    sections.forEach(section => {
        section.classList.remove('active');
    });
    document.getElementById(sectionId).classList.add('active');
}
