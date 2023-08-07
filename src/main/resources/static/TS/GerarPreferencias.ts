//ESSE ARIQUIVO É DESTINADO A PEGAR AS GRADES VIA PROTOCOLO HTTP GET//

console.log("GerarPreferencias.ts OK");

//-----------------------------SALVAR PREFERENCIAS-------------------------------
const btnSalvar = document.getElementById("btnGerarGrade");
btnSalvar.addEventListener("click", ()=>{
    const preferencias = salvarInputs()
    fetch("/gradesFiltradas/preferencias", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(preferencias)
    });
});
//------------------------------ADICIONAR MATERIA-------------------------------
const btnAdicionarMateria = document.getElementById("btnAdicionarMateria");
btnAdicionarMateria.addEventListener("click", ()=>{
    adicionarInput();
});
//-----------------------------------FUNÇOES------------------------------------
/**
 * Adiciona uma nova div de inputs.
 */
function adicionarInput(){

    const linha = document.createElement("div");
    linha.classList.add("linha");
    linha.setAttribute("id","linha");

    const inputMateria = document.createElement("input");
    inputMateria.classList.add("input");
    inputMateria.type = "text";
    inputMateria.placeholder = "Matéria";
    inputMateria.setAttribute("list", "materias");

    const inputProfessor = document.createElement("input");
    inputProfessor.classList.add("input");
    inputProfessor.type = "text";
    inputProfessor.placeholder = "Professor";
    inputProfessor.setAttribute("list", "materias");

    linha.appendChild(inputMateria);
    linha.appendChild(inputProfessor);

    const inputsContainer = document.getElementById("inputsContainer");
    inputsContainer.appendChild(linha);
}
/**
 * Salva os inputs e retorna um Json das preferências.
 */
function salvarInputs(){

    const preferenciasJSON = [];

    const container = document.getElementById("inputs");
    const inputsContainers= container.querySelectorAll("#inputsContainer");
    inputsContainers.forEach(inputContainer =>{
        const linhas = inputContainer.querySelectorAll("#linha");

        linhas.forEach(linha =>{
            if(linha.querySelector('input[placeholder="Matéria"]').value != ""){
                const disciplina ={
                    nome: linha.querySelector('input[placeholder="Matéria"]').value,
                    professor: linha.querySelector('input[placeholder="Professor"]').value
                };
                preferenciasJSON.push(disciplina);
            }
        });
    });
    console.log(preferenciasJSON);
    return preferenciasJSON;
}

fetch("/gradesFiltradas/listaMaterias")
    .then(response => response.json())
    .then(data  => {
        const datalist = document.getElementById("materias");

        data.forEach(materia => {
            const option = document.createElement("option");
            option.value = materia;
            datalist.appendChild(option);
        });
    }).catch(error => console.error('Erro:', error));
fetch("/gradesFiltradas/listaProfessores")
    .then(response => response.json())
    .then(data  => {
        const datalist = document.getElementById("professores");

        data.forEach(professor => {
            const option = document.createElement("option");
            option.value = professor;
            datalist.appendChild(option);
        });
    }).catch(error => console.error('Erro:', error));