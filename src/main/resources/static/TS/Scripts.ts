console.log("Scripts.ts OK");

//-----------------------RECEBIMENTO DAS PREFERENCIAS-------------------------
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
//---------------------------------ABRIR GRADE---------------------------------
const btnGerarGrade = document.getElementById("btnGerarGrade");
const gradeContainer = document.getElementById("grade").style;
const inputsContainer = document.getElementById("inputs").style;
btnGerarGrade.addEventListener("click", ()=>{
   gradeVisualizada = 0;
   btnAtualizar.click();
   gradeContainer.display="block";
   inputsContainer.display="none";
});
//---------------------------------ATUALIZAR----------------------------------
const btnAtualizar = document.getElementById("btnAtualizar");
let gradeVisualizada = 0;
let materiasColoridas = [];
btnAtualizar.addEventListener("click", () => {
   const preferencias = salvarInputs()
   fetch("/gradesFiltradas/preferencias", {
      method: 'POST',
      headers: {
         'Content-Type': 'application/json'
      },
      body: JSON.stringify(preferencias)
   })
       .then(response => response.json())
       .then(data =>{
          if(materiasColoridas.length === 0) materiasColoridas = vincularCorMateria(data[0].materias);
          atualizarGrade(data, gradeVisualizada);
          gradeVisualizada = (gradeVisualizada + 1) % data.length;
       }).catch(error => console.error('Erro:', error));
});
//---------------------------------FECHAR GRADE----------------------------------
const btnSair = document.getElementById("btnSair");
btnSair.addEventListener("click", ()=>{
   materiasColoridas = [];
   gradeContainer.display="none";
   inputsContainer.display="block";
});
//------------------------------ADICIONAR MATERIA-------------------------------
const btnAdicionarMateria = document.getElementById("btnAdicionarMateria");
btnAdicionarMateria.addEventListener("click", ()=>{
   adicionarInput();
});
//---------------------------------FUNÇOES----------------------------------
/**
 * Esse método é responsável por adicionar uma grade na tabela através do seu horário..
 * @param data Grade a ser adicionada.
 * @param index Index da grade mostrada na tabela.
 */
function atualizarGrade(data, index){
   limparDados();
   data[index].materias.forEach(materia => {
      adicionarMateria(materia);
   });
}
/**
 * Adiciona uma matéria na grade e define a cor.
 * @param materia Matéria a ser adicionada na grade.
 */
function adicionarMateria(materia){
   materia.horario.forEach(horarios =>{
      horarios.dia.forEach(dias =>{
         horarios.hora.forEach(horas =>{
            const id = horarios.turno + horas + dias;
            const dado = `${materia.nome}`; //<br>${materia.professor}
            adicionarDado(dado, id, pegarCorMateria(materia));
         });
      });
   });
}
/**
 * Retorna a cor da matéria.
 * @param materia Matéria a ser verificada a cor.
 */
function pegarCorMateria(materia){
   const moldeEncontrado = materiasColoridas.find(molde => molde.nome === materia.nome);
   if(moldeEncontrado) return moldeEncontrado.cor;
   return "";
}
/**
 * Retorna uma lista de jsons com nome de materias e uma cor aleatória.
 * @param listaMaterias Lista de materias a serem copiadas.
 */
function vincularCorMateria(listaMaterias){
   return listaMaterias.map(materia => ({
      nome: materia.nome,
      cor: gerarCor()
   }));
}
/**
 * Retorna uma cor pastel aleatória e que não tenha sido usada.
 */
function gerarCor(){
   const vermelho = Math.floor(Math.random() * (106)) + 150;
   const verde = Math.floor(Math.random() * (151)) + 150;
   const azul = Math.floor(Math.random() * (151)) + 150;

   const cor =`rgb(${vermelho}, ${verde}, ${azul})`;
   const corIgual = materiasColoridas.find(molde => molde.cor === cor);

   if(corIgual) return gerarCor();
   return cor;
}
/**
 * Limpa todas as células q o id termina de 1 a 7.
 */
function limparDados(){
   const td = document.querySelectorAll("td");
   td.forEach((celula) => {
      const ultimoCaractere = celula.id.slice(-1);
      if (/[1-7]/.test(ultimoCaractere)){
         celula.innerHTML = "";
         celula.style.backgroundColor = "";
      }
   });
}
/**
 * Adiciona um dado na grade com base no id.
 * @param dado Dado a ser adicionado.
 * @param id Id da célula.
 */
function adicionarDado(dado, id, cor){
   const celula = document.getElementById(id);
   celula.innerHTML = dado;
   celula.style.backgroundColor = cor;
}
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
   inputProfessor.setAttribute("list", "professores");

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
         const nomeInput = linha.querySelector('input[placeholder="Matéria"]').value;
         const professorInput = linha.querySelector('input[placeholder="Professor"]').value;

         if (nomeInput.trim() !== "") {
            const disciplina = {
               nome: nomeInput,
               professor: professorInput.trim() !== "" ? professorInput: null
            };
            preferenciasJSON.push(disciplina);
         }
      });
   });
   return preferenciasJSON;
}