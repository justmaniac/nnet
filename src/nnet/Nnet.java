package nnet;
import java.io.IOException;

public class Nnet {
    N Neurons[];
    int[] I;

    public Nnet(int N_COUNT, String Path) throws IOException {
        I = new int[N_COUNT*2];
        for (int i=0; i<I.length; i++) I[i]=0;
        Neurons = new N[N_COUNT];
        for (int i=0; i<N_COUNT; i++) {
            Neurons[i] = new N(N_COUNT*2, I);
            Neurons[i].ReadW(i);
            Neurons[i].SetOutput();
        }
    }

    public void print() {
        for (int i=0; i<Neurons.length; i++) {
            System.out.println("============================================================ " + i);
            Neurons[i].print();
        }
    }

    public void FindAndSetI() {
        double max=0;   // максимальный выход
        int maxi=10;    // индекс нейрона с максимальным выходом
        for (int i=0; i<Neurons.length; i++) {
            Neurons[i].SetOutput();
            if (Neurons[i].O > max &    // ищем нейрон с наибольшим выходом
                    I[i*2] == 0 &       // и соответствующей
                    I[i*2+1] == 0) {    // пустой клеткой
                max = Neurons[i].O;
                maxi=i;
            }
        }
        if (maxi<10) {
            I[maxi*2]=0;
            I[maxi*2+1]=1;
        }
        System.out.println("########## " + maxi);
    }

    public int CheckWin() {
        int win=2;
        for (int i=0; i<18; i+=6)       // горизонтальные крестики
            if (I[i+0]==1 && I[i+2]==1 && I[i+4]==1 && I[i+1]==0 && I[i+3]==0 && I[i+5]==0) win=1;

        for (int i=0; i<6; i+=2)        // вертикальные крестики
            if (I[i+0]==1 && I[i+6]==1 && I[i+12]==1 && I[i+1]==0 && I[i+7]==0 && I[i+13]==0) win=1;

        if (I[0]==1 && I[8]==1 && I[16]==1 && I[1]==0 && I[9]==0 && I[17]==0) win=1;    // диагональ крестики
        if (I[4]==1 && I[8]==1 && I[12]==1 && I[5]==0 && I[9]==0 && I[13]==0) win=1;    // диагональ крестики

        for (int i=0; i<18; i+=6)       // горизонтальные нолики
            if (I[i+0]==0 && I[i+2]==0 && I[i+4]==0 && I[i+1]==1 && I[i+3]==1 && I[i+5]==1) win=0;

        for (int i=0; i<6; i+=2)        // вертикальные нолики
            if (I[i+0]==0 && I[i+6]==0 && I[i+12]==0 && I[i+1]==1 && I[i+7]==1 && I[i+13]==1) win=0;

        if (I[0]==0 && I[8]==0 && I[16]==0 && I[1]==1 && I[9]==1 && I[17]==1) win=0;    // диагональ нолики
        if (I[4]==0 && I[8]==0 && I[12]==0 && I[5]==1 && I[9]==1 && I[13]==1) win=0;    // диагональ нолики

        if (win==1) System.out.println("########## krestiki");
        if (win==0) System.out.println("########## noliki");
        return win;
    }

    public static void main(String[] args) throws IOException {
        final int N_COUNT=9;    // количество нейронов
        Nnet XOnet = new Nnet(N_COUNT, "/home/losevao/NetBeansProjects/nnet/input.txt");
        XOnet.print();
        Form f = new Form(XOnet);
    }
}
