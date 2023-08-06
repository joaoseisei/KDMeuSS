console.log("Server.ts OK");

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
btnAtualizar.addEventListener("click", () => {
   fetch("/gradesFiltradas")
       .then(response => response.json())
       .then(data =>{
          atualizarGrade(data, gradeVisualizada);
          gradeVisualizada = (gradeVisualizada + 1) % data.length;
       }).catch(error => erro(error));
});
//-----------------------------------SAIR------------------------------------
const btnSair = document.getElementById("btnSair");
btnSair.addEventListener("click", ()=>{
   console.log("saindo das grades")
   gradeContainer.display="none";
   inputsContainer.display="block";
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
      corMateria(materia);
   });
}
function corMateria(materia){
   const cor = gerarCor();
   materia.horario.forEach(horarios =>{
      horarios.dia.forEach(dias =>{
         horarios.hora.forEach(horas =>{
            const id = horarios.turno + horas + dias;
            const dado = `${materia.nome}`; //<br>${materia.professor}
            adicionarDado(dado, id, cor);
         });
      });
   });
}

/**
 * Retorna uma cor pastel aleatória;
 */
function gerarCor(){
   const vermelho = Math.floor(Math.random() * (230 - 150 + 1)) + 150;
   const verde = Math.floor(Math.random() * (230 - 150 + 1)) + 150;
   const azul = Math.floor(Math.random() * (230 - 150 + 1)) + 150;
   return `rgb(${vermelho}, ${verde}, ${azul})`;
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
function adicionarDado(dado,id, cor){
   const celula = document.getElementById(id);
   celula.innerHTML = dado;
   celula.style.backgroundColor = cor;
}
/**
 * Alerta o erro.
 * @param error Erro a ser alertado.
 */
function erro(error){
   console.error("erro", error);
}