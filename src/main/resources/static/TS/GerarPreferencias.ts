//ESSE ARIQUIVO É DESTINADO A PEGAR AS GRADES VIA PROTOCOLO HTTP GET//

console.log("GerarPreferencias.ts OK");

//------------------------------ADICIONAR MATERIA-------------------------------
const btnAdicionarMateria = document.getElementById("btnAdicionarMateria");
btnAdicionarMateria.addEventListener("click", ()=>{
    adicionarInput();
    salvarInputs();
});
//-----------------------------------FUNÇOES------------------------------------
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

function salvarInputs(){

    const preferenciasJSON = [];

    const container = document.getElementById("inputs");
    const inputsContainers= container.querySelectorAll("#inputsContainer");
    inputsContainers.forEach(inputContainer =>{
        const linhas = inputContainer.querySelectorAll("#linha");

        linhas.forEach(linha =>{
            const disciplina ={
                materia: linha.querySelector('input[placeholder="Matéria"]').value,
                professor: linha.querySelector('input[placeholder="Professor"]').value
            };
            preferenciasJSON.push(disciplina);
        });
    });
    console.log(preferenciasJSON);
    return preferenciasJSON;
}