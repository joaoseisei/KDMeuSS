
//---------------------------------ATUALIZAR----------------------------------
const btnAtualizar = document.getElementById("bntAtualizar");
let gradeVisualizada = 0;
btnAtualizar.addEventListener("click", () => {
    fetch("/gradesFiltradas")
        .then(response => response.json())
        .then(data =>{
            atualizarGrade(data, gradeVisualizada);
            gradeVisualizada = (gradeVisualizada + 1) % data.length;
        }).catch(error => erro(error));
});
//---------------------------------LIMPAR-----------------------------------
const btnLimpar = document.getElementById("btnLimpar");
btnLimpar.addEventListener("click", limparDados);
//---------------------------------FUNÇOES----------------------------------
/**
 * Esse método é responsável por adicionar uma grade na tabela com base no seu index.
 * @param data Grade a ser adicionada.
 * @param index Index da grade mostrada na tabela.
 */
function atualizarGrade(data, index){
    limparDados();
    data[index].materias.forEach(materia => {
        materia.idGrade.forEach(id => {
            const dado =`${materia.nome}<br>${materia.professor}`;
            adicionarDado(dado, id);
        });
    });
}
/**
 * Limpa todas as células q o id termina de 1 a 7.
 */
function limparDados(){
    const td = document.querySelectorAll("td");
    td.forEach((celula) => {
        const ultimoCaractere = celula.id.slice(-1);
        if (/[1-7]/.test(ultimoCaractere)) celula.innerHTML = "";
    });
}
/**
 * Adiciona um dado na grade com base no id.
 * @param dado Dado a ser adicionado.
 * @param id Id da célula.
 */
function adicionarDado(dado,id){
    document.getElementById(id).innerHTML = dado;
}
/**
* Alerta o erro.
* @param error Erro a ser alertado.
*/
function erro(error){
    console.error("erro", error);
}