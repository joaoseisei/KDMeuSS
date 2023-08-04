
var data = {
    nome: "AAAAAAAAAAAAAAAAAA",
    professor: "'123teste'",
    codigo: "123",
    horario: []
};

fetch('/gradesFiltradas/endpoint', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
});

//---------------------------------ATUALIZAR----------------------------------
const btnAtualizar = document.getElementById("bntAtualizar");
btnAtualizar.addEventListener("click", () => {
    fetch("/gradesFiltradas")
        .then(response => response.json())
        .then(data =>{
            atualizarGrade(data, 0);
        }).catch(error => erro(error));
});

function atualizarGrade(data, index){
    limparDados();
    const grade = data[index].materias;
    grade.forEach(materia =>{
        materia.idGrade.forEach(id =>{
            const dado = materia.nome + "<br>" + materia.professor;
            adicionarDado(dado, id);
        });
    });
}
/**
 * Alerta o erro.
 * @param error Erro a ser alertado.
 */
function erro(error){
    console.error("erro", error);
}


/**
 * Limpa todas as células q tem o id de 1 a 7 (todas q tem dias no id).
 */
function limparDados(){
    const td = document.querySelectorAll("td");
    td.forEach((celula) => {
        if(celula.id.match(/^[1-7]/)) celula.innerHTML = "";
    });
}

function adicionarDado(dado,id){
    document.getElementById(id).innerHTML = dado;
}

