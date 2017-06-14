package algoritmo2;

import java.util.ArrayList;

public class GeradorLeve {
	public int TMT;
	public int Amount;
	public ArrayList<Tarefa> listTarefasEspera;
	GeradorLeve(int tempomedio, int numerodetarefas){
		listTarefasEspera = new ArrayList<Tarefa>();
		TMT= tempomedio;
		Amount= numerodetarefas/3;
	}
	public void iniciar (int horaDaTarefa) {
		for(int i = 0; i < Amount; i++){
			Tarefa t1 = new Tarefa(1, horaDaTarefa, TMT);
			listTarefasEspera.add(t1);
		}
		
	}
}
