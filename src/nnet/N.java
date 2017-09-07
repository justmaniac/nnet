package nnet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

class N {
    int[] W;    // веса входов
    double O;   // выход
    int[] I;

    N(int W_COUNT, int[] I) {
        this.W = new int[W_COUNT];
        this.I=I;
    }

    void SetOutput() {  // вычисление выходного сигнала
        O=0;
        for (int i=0; i<W.length; i++) O+=I[i]*W[i];
        O=Act(O);
    }

    double Act (double arg) {     // функция активации
////                return 1.0/(1+2^(int)(10.0-arg));
//                if (arg==0) return 0;
//                return -1/(0.02*arg+1)+1;
        return arg;
    }

    void print () {
        int sum=0;
        for (int i=0; i<W.length; i++) {
            System.out.print(I[i] + "x" + W[i]);
            if (i%2==1) System.out.print("\t\t");
            else System.out.print("\t");
            if (i%6==5) System.out.println();
            sum+=I[i] * W[i];
        }
//                System.out.println(sum + "\n" + O);
        System.out.println(O);
    }

    void NewRandomW () { for (int i=0; i<W.length; i++) W[i] = (int)(Math.random() * 100); }

    void ReadW(int index) throws IOException { this.W = LoadFileToInt("/home/losevao/NetBeansProjects/nnet/W" + index + ".txt"); }

    void WriteW(int index) throws IOException { WriteFileFromInt("/home/losevao/NetBeansProjects/nnet/W" + index + ".txt", this.W); }

    int[] LoadFileToInt (String Path) throws IOException {
        ArrayList<String> Arr = new ArrayList();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(Path), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) Arr.add(line);
        } catch (IOException e) {}
        int[] Result = new int[Arr.size()];
        for (int i=0; i<Arr.size(); i++) Result[i] = Integer.parseInt(Arr.get(i));
        return Result;
    }

    void WriteFileFromInt (String Path, int[] Arr) throws IOException {
        String s = "";
        for (int i=0; i<Arr.length; i++) s += Integer.toString(Arr[i]) + "\n";
        try {
            FileWriter fw = new FileWriter(Path);
            fw.write(s);
            fw.close();
        } catch (IOException ex) {}
    }
}
