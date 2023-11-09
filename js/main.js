document.getElementById('cadastroForm').addEventListener('submit', cadastrarJogo);
var result = 0;
function cadastrarJogo(event) {
    event.preventDefault();

    const name = document.getElementById('name').value;
    const desc = document.getElementById('desc').value;
    const isbn = document.getElementById('isbn').value;

    fetch('http://localhost:8080/livro', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name, desc, isbn}),
    })
        .then(response => response.json())
        .then(data => {
            alert('livro cadastrado com sucesso!');
            document.getElementById('cadastroForm').reset();            
        })
        .catch(error => {
            console.error('Erro ao cadastrar livrp:', error);
        });
}
function pesquisarJogo() {
    const searchId = document.getElementById('searchId').value;

    fetch(`http://localhost:8080/livro/${searchId}`)
        .then(response => {
            if (response.status === 404) {
                return Promise.reject('livro não encontrado');
                result = 0;
            }
            return response.json();
        })
        .then(data => {
            result = 1;
            document.getElementById('name').value = `${data.name}`;
            document.getElementById('desc').value = `${data.desc}`;
            document.getElementById('isbn').value = `${data.isbn}`;
        })
        .catch(error => {
            console.error('Erro ao pesquisar Livro:', error);
            const resultadoPesquisa = document.getElementById('resultadoPesquisa');
            resultadoPesquisa.innerHTML = 'livro não encontrado.';          

        });
}
function atualizarJogo() {
    pesquisarJogo();
    if (result == 1) {
        const name = document.getElementById('name').value;
        const desc = document.getElementById('desc').value;
        const isbn = document.getElementById('isbn').value;
        const searchId = document.getElementById('searchId').value;

        fetch(`http://localhost:8080/livro${searchId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ name, plataform }),
        })
            .then(response => response.json())
            .then(data => {
                alert('livro atualizado com sucesso!');
                document.getElementById('cadastroForm').reset();                
            })
            .catch(error => {
                console.error('Erro ao atualizar livro:', error);
            });
    } else {
        alert('ID não encontrado na base de dados. Nenhum livro foi alterado. Favor pesquisar o livro a ser alterado !!!');
    }
}
function excluirJogo() {
    pesquisarJogo();
    const searchId = document.getElementById('searchId').value;

    if (result == 1) {
        fetch(`http://localhost:8080/livro/${searchId}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.status === 200) {
                    alert('livro excluído com sucesso!');
                    document.getElementById('cadastroForm').reset();
                } else {
                    console.error('Erro ao excluir livro:', response.statusText);
                }
            })
            .catch(error => {
                console.error('Erro ao excluir livro:', error);
            });
    } else {
        alert('ID não encontrado na base de dados. Nenhum livro foi excluído. Favor pesquisar o livro a ser excluído !!!');
    }
}