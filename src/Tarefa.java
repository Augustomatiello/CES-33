
public class Tarefa {
	private int NumeroCPUqueCriou;
	private float HoraCriacaoTarefa;
	private float TempodeCPUNecessario;
	
	Tarefa(int NumeroCPU, float HoraCriacao, float TempodeCPU){
		setNumeroCPU(NumeroCPU);
		setHora(HoraCriacao);
		setTempodeCPU(TempodeCPU);
	}
	
	
	void copy(Tarefa t){
		this.NumeroCPUqueCriou = t.getNumeroCPU();
		this.HoraCriacaoTarefa = t.getHora();
		this.TempodeCPUNecessario = t.getTempodeCPU();
	}
	
	void setTempodeCPU(float tempodeCPU) {TempodeCPUNecessario = tempodeCPU;}
	void setNumeroCPU(int num){ NumeroCPUqueCriou = num;}
	void setHora(float HoraCriacao) {HoraCriacaoTarefa = HoraCriacao;}
	float getTempodeCPU() {return TempodeCPUNecessario;}
	int getNumeroCPU(){ return NumeroCPUqueCriou;}
	float  getHora() {return HoraCriacaoTarefa;}
	
}
