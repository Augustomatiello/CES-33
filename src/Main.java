import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {
	int clock = 0;
	static int count = 0;
	static int transmitida = 0;
	static int recebida = 0;
	static ArrayList<CPU> listCPU = new ArrayList<CPU>();

	static int numeroTarefasEmExecucao;
	public static void main(String[] args) {
		// int RETRY = 10;
		int HoraAtual = 0;
		CPU c = new CPU(5);
		CPU d = new CPU(5);
		CPU a = new CPU(5);
		CPU b = new CPU(5);
		CPU e = new CPU(5);
		CPU f = new CPU(5);
		CPU g = new CPU(5);
		CPU h = new CPU(5);
		listCPU.add(c);
		//listCPU.add(d);
		//listCPU.add(a);
		//listCPU.add(b);
		//listCPU.add(e);
		//listCPU.add(f);
		//listCPU.add(g);
		//listCPU.add(h);
		int add = 0;
		int tempoDaUltimaGeracao = 0;
		Tarefa tarefa = new Tarefa(-1,0,0);
		GeradorIntenso g1 = new GeradorIntenso(3, 30, 7);
		//GeradorLeve g1 = new GeradorLeve (3, 30);
		System.out.println("<passou no gerador>");
		g1.iniciar(HoraAtual);
		System.out.println("<numero de tarefas geradas: "+ g1.listTarefasEspera.size() + ">");
		// enquanto ainda tem tarefa na fila e tarefas executando
		while ((g1.listTarefasEspera.size() > 0 || numeroTarefasEmExecucao > 0)&& count <1000) {
			//System.out.println("\n--Entra no while--\n");
			// atualiza as CPUs
			for (int cpu = 0; cpu < listCPU.size(); cpu++) {
				
			
				for (int task = 0; task < listCPU.get(cpu).TarefasDaCPU.size(); task++) {
					
					tarefa.copy(listCPU.get(cpu).TarefasDaCPU.get(task));
					if (tarefa.getHora() + tarefa.getTempodeCPU() <= HoraAtual) {
						//System.out.println("Tarefa adicionada na hora "+tarefa.getHora()+" com tempo de duracao "+tarefa.getTempodeCPU() +" e terminada no instante " + HoraAtual );
						listCPU.get(cpu).TarefasDaCPU.remove(task);
						numeroTarefasEmExecucao--;
					}
				}
			}
			// passou um segundo
			HoraAtual = HoraAtual + 1;
			tempoDaUltimaGeracao ++;
		/*	try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}; //*/
			
			if(tempoDaUltimaGeracao - g1.TMT>0){
				//System.out.println("<passou no gerador>");
				g1.iniciar(HoraAtual+1);
				//System.out.println("<numero de tarefas geradas: "+ g1.listTarefasEspera.size() + ">");
				tempoDaUltimaGeracao = 0;
			}
			// prepara próxima tarefa da lista de espera para mandar para uma
			// CPU
		
			
			for (int t = 0; t < g1.listTarefasEspera.size(); t++) {
				//System.out.println("<Entra na fila de espera>");
				for (int j = 0; j < listCPU.size(); j++) {
					add = listCPU.get(j).adicionaTarefa(g1.listTarefasEspera.get(t));
					if (add == 0)
						{
						g1.listTarefasEspera.get(t).setNumeroCPU(g1.listTarefasEspera.get(t).getNumeroCPU() + 1);
						//System.out.println("Total de tarefas da CPU "+j+": "+listCPU.get(j).TarefasDaCPU.size());
						transmitida ++;
						//System.out.println("->CPU ocupada, tarefa intensa");
						}
					else
					{  // System.out.println("Total de tarefas da CPU "+j+": "+listCPU.get(j).TarefasDaCPU.size());
						//System.out.println("->CPU livre, tarefa leve");
						recebida ++;
						g1.listTarefasEspera.get(t).setHora(HoraAtual);
						break;
					}

				}
				// se tudo der certo, manda pra execucao e tira da espera
				if (add == 1) {
					numeroTarefasEmExecucao++;
					g1.listTarefasEspera.remove(t);
					//System.out.println("Tamanho da lista de espera: "+g1.listTarefasEspera.size());
				}
				// se der errado, atualiza as CPUs um segundo depois e tenta
				// passar as tarefas de novo
				else {
					//System.out.print("SIZE: "+g1.listTarefasEspera.size());
					for (int cpu = 0; cpu < listCPU.size(); cpu++) {
						for (int task = 0; task < listCPU.get(cpu).TarefasDaCPU.size(); task++) {
							tarefa.copy(listCPU.get(cpu).TarefasDaCPU.get(task));
							if (tarefa.getHora() + tarefa.getTempodeCPU() >= HoraAtual) {
								listCPU.get(cpu).TarefasDaCPU.remove(task);
								numeroTarefasEmExecucao--;
							}
						}
					}
					for (int j = 0; j < listCPU.size(); j++) {
						add = listCPU.get(j).adicionaTarefa(g1.listTarefasEspera.get(t));
						if (add == 0)
							{
							g1.listTarefasEspera.get(t).setNumeroCPU(g1.listTarefasEspera.get(t).getNumeroCPU() + 1);
						//	System.out.println("Total de tarefas da CPU "+j+": "+listCPU.get(j).TarefasDaCPU.size());
							transmitida ++;
						//	System.out.println("->CPU ocupada, tarefa intensa");
							}
						else
						{ //  System.out.println("Total de tarefas da CPU "+j+": "+listCPU.get(j).TarefasDaCPU.size());
							//System.out.println("->CPU livre, tarefa leve");
							recebida ++;
							g1.listTarefasEspera.get(t).setHora(HoraAtual);
							break;
						}

					}
					if (add == 1) {
						numeroTarefasEmExecucao++;
						g1.listTarefasEspera.remove(t);
					}
				}
			/*	try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//*/

				// a cada sondagem, passa um segundo
				HoraAtual = HoraAtual + 1;
				tempoDaUltimaGeracao ++;
			} 			
			count ++;
			System.out.println("Recebidas: " + recebida);
			System.out.println("Transmitidas: " + transmitida);

		}
	}
}