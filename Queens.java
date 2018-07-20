package hc.queen;    
import java.util.BitSet;
 
public class Queens {
 
    int k=0;
    int n=0;
    int c=0;
    long iterazioni=0;
    BitSet diagonali_c;
    BitSet diagonali_d;
    BitSet righe;
    BitSet colonne;
    BitSet scacchiera;
 
    public Queens(){
        diagonali_c=new BitSet(14);
        diagonali_d=new BitSet(14);
        colonne=new BitSet(8);
        scacchiera=new BitSet(64);
    }
 
    int p(int r, int c){
        return r*8+c;
    }
 
    boolean check(int riga, int colonna){
        return !(colonne.get(colonna) || diagonali_c.get(riga-colonna+7) || diagonali_d.get(riga+colonna));
    }
 
    void generate(){
        int h=0;
        do{
            if(check(n, h)){
                iterazioni++;
                piazza(n,h);
                n++;
                if(n==8){
                    System.out.println(dump(scacchiera));
                }
                else
                    generate();
 
                n--;
                rimuovi(n,h);
            }
            h++;
        }while(h<8);
    }
 
    void piazza(int riga, int colonna){
        scacchiera.set(p(riga, colonna));
        colonne.set(colonna);
        diagonali_c.set(7+riga-colonna);
        diagonali_d.set(riga+colonna);
    }
 
    void rimuovi(int riga, int colonna){
        scacchiera.clear(p(riga, colonna));
        colonne.clear(colonna);
        //righe.clear(riga);
        diagonali_c.clear(7+riga-colonna);
        diagonali_d.clear(riga+colonna);
    }
 
    public String dump(BitSet scacchiera){
        StringBuffer buffer= new StringBuffer();
        c++;
        for(int a=7;a>=0;a--){
            buffer.append("n"+(a+1)+" |" );
            for(int b=0;b<8;b++){
                if(scacchiera.get(a*8+b))
                    buffer.append("* ");
                else
                    buffer.append("  ");
            }
            buffer.append("|");
        }
        buffer.append(c+"n"+iterazioni);
        return buffer.toString();
    }
}
