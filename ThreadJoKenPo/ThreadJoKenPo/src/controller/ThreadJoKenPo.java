package controller;

import java.util.concurrent.Semaphore;

public class ThreadJoKenPo extends Thread {
	private String jogador1;
	private String jogador2;
	private static int time1 = 0;
	private static int time2 = 0;
	private static int jogo = 0;
	private Semaphore semaphoro;

	public ThreadJoKenPo(String jogador1, String jogador2, Semaphore semaforo) {
		this.jogador1 = jogador1;
		this.jogador2 = jogador2;
		this.semaphoro = semaforo;

	}

	private void Duelo() {
		int vitoria1 = 0;
		int vitoria2 = 0;
		int j1;
		int j2;
		// tesoura = 1;
		// papel = 2;
		// pedra = 3;

		while (vitoria1 < 3 && vitoria2 < 3) {

			try {
				sleep(500);
			} catch (InterruptedException e1) {
				System.out.println("Deu ruim no sleep");
				e1.printStackTrace();
			}

			j1 = (int) ((Math.random() * 3) + 1);
			j2 = (int) ((Math.random() * 3) + 1);

			switch (j1) {
			case 1:
				if (j1 == j2) {
					System.out
							.println(jogador1 + " jogou tesoura. " + jogador2 + " jogou tesoura. Resultado = empate\n");
				} else {
					if (j2 == 2) {

						System.out.println(jogador1 + " jogou tesoura. " + jogador2
								+ " jogou papel. Resultado = Vitória de " + jogador1 + "\n");
						vitoria1++;
					}
					if (j2 == 3) {

						System.out.println(jogador1 + " jogou tesoura. " + jogador2
								+ " jogou pedra. Resultado = Vitória de " + jogador2 + "\n");
						vitoria2++;
					}
				}
				break;
			case 2:
				if (j1 == j2) {
					System.out.println(jogador1 + " jogou papel. " + jogador2 + " jogou papel. Resultado = Empate\n");
				} else {
					if (j2 == 1) {

						System.out.println(jogador1 + " jogou papel. " + jogador2
								+ " jogou tesoura. Resultado = Vitória de " + jogador2 + "\n");
						vitoria2++;
					}
					if (j2 == 3) {

						System.out.println(jogador1 + " jogou papel. " + jogador2
								+ " jogou pedra. Resultado = Vitória de " + jogador1 + "\n");
						vitoria1++;
					}
				}

				break;
			case 3:
				if (j1 == j2) {
					System.out.println(jogador1 + " jogou pedra. " + jogador2 + " jogou pedra. Resultado = empate\n");
				} else {
					if (j2 == 1) {

						System.out.println(jogador1 + " jogou pedra. " + jogador2
								+ " jogou tesoura. Resultado = Vitória de " + jogador1 + "\n");
						vitoria1++;
					}
					if (j2 == 2) {

						System.out.println(jogador1 + " jogou pedra. " + jogador2
								+ " jogou papel. Resultado = Vitória de " + jogador2 + "\n");
						vitoria2++;
					}

				}

				break;

			}
		}

		jogo++;
		if (vitoria1 == 3) {
			time1++;
			System.out.println(jogador1 + " ganhou,parabéns você é incrivel \n");

		}
		if(vitoria2 == 3) {
			time2++;
			System.out.println(jogador2 + " ganhou,parabéns você é incrivel \n");

		}
		
		Pontos();

	}

	@Override
	public void run() {

		try {
			semaphoro.acquire();

			Duelo();

			
		} catch (InterruptedException e) {
			System.out.println("Deu ruim no duelo");
			e.printStackTrace();
		} finally {
			semaphoro.release();
		}

	}

	private void Pontos() {
		System.out.println("Time A = "+time1+", Time B = "+time2+", jogos = "+jogo+"\n");
		if (jogo == 5) {
			if (time1 > time2) {
				System.out.println("Time A ganhou, fez " + time1 + " pontos\n");
			} else if (time2 > time1) {
				System.out.println("Time B ganhou, fez " + time2 + " pontos\n");
			} else {
				System.out.println("Empatou\n");
			}
		} else {
			if (time1 == time2) {
				System.out.println("Está empatado\n");
			} else if (time1 > time2) {
				System.out.println("Time A está ganhando, fez " + time1 + " pontos. Time B fez " + time2 + "\n");
			} else {
				System.out.println("Time B está ganhando, fez " + time2 + " pontos. Time A fez " + time1 + "\n");
			}

		}

	}
}
