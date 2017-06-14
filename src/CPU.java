import java.util.ArrayList;

public class CPU {
 int flag;
 int LimiteTarefas;
 ArrayList <Tarefa> TarefasDaCPU;
 
 CPU(int limite){
	 LimiteTarefas = limite;
	 TarefasDaCPU = new ArrayList<Tarefa>();
	 
 }
 
 int adicionaTarefa(Tarefa t){
	 if(TarefasDaCPU.size() < LimiteTarefas && t.getTempodeCPU()>0){
		 Tarefa aux = new Tarefa(t.getNumeroCPU(),t.getHora(),t.getTempodeCPU());
		 TarefasDaCPU.add(aux);
		 return 1;
	 }
	 else return 0;
 }
 
 
}
