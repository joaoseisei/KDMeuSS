
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
    const grade = data[index].materias;
    for(let i = 0; i < grade.length; i++){

        const horarios = grade[i].horario;
        for(let k = 0; k <= horarios; k++){
            const dias = horarios[k].dia;
            for(let j = 0; j< dias.length; j++){
                console.log(dias[j]);
            }

        }

    }
    grade.forEach(materia =>{
        console.log(materia)
        const horario = materia.horario;
        horario.forEach((horario)=>{
            const dias = horario.dia
            console.log(dias);
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

function adicionarDado(dado,linha,coluna){
    document.getElementById(linha + coluna).innerHTML = dado;
}

function adicionarMateria(ArrayGrade, index){
    const [dado, linha, coluna] = ArrayGrade[index].split("|");
    adicionarDado(dado, linha, coluna);
}

function gerarGrades(ArrayGrade){
    limparDados()
    for(let i=0; i < ArrayGrade.length; i++) adicionarMateria(ArrayGrade, i);
}
