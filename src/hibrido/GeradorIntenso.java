package hibrido;
import java.util.ArrayList;

public class GeradorIntenso {
	public int TMT;
	public int Amount;
	public int numCPU;
	public ArrayList<Tarefa> listTarefasEspera;
	GeradorIntenso(int tempomedio, int numerodetarefas, int cpus){
		listTarefasEspera = new ArrayList<Tarefa>();
		TMT= tempomedio;
		Amount= numerodetarefas;
		numCPU = cpus;
	}
	public void iniciar (int horaDaTarefa) {
		for(int i = 0; i < Amount; i++){
			Tarefa t1 = new Tarefa(1, horaDaTarefa, TMT);
			listTarefasEspera.add(t1);
		}
		
	}
}