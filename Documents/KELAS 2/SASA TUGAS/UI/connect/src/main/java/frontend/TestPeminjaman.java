
package frontend;


import backend.*;
public class TestPeminjaman {
    public static void main(String[] args){
        member Lisa = new member().getById(1);
        member Jennie = new member().getById(2);
        books TheGreatGatsby = new books().getById(4);
        books IntroductionAlgorithm = new books().getById(5);
        
        peminjaman test1 = new peminjaman(Jennie, IntroductionAlgorithm, "2020/30/8", "2020/30/8");
        peminjaman test2 = new peminjaman(Lisa, TheGreatGatsby, "2020/12/12", "2020/22/12");
        peminjaman test3 = new peminjaman(Jennie, IntroductionAlgorithm, "2020/4/11", "2020/4/12");
        
        test1.save();
        test2.save();
        test3.save();
        
        test2.setTanggalPinjam("2020/12/12");
        test2.save();
        
        test3.delete();
        
        
        for(peminjaman p : new peminjaman().getAll())
        {
       System.out.println("Member: " + p.getAnggota().getNama() + ","
                      +"Book Title: " + p.getBuku().getJudul() + " , "
                       +"Borrowing Date: " + p.getTanggalPinjam() + " , "
                      +"Returning Date: " + p.getTanggalKembali());
       }
        // test search
       for(peminjaman p : new peminjaman().search("TheGreatGatsby"))
        {
        System.out.println("Member: " + p.getAnggota().getNama() + ","
                       +"Book Title: " + p.getBuku().getJudul() + " , "
                       +"Borrowing Date: " + p.getTanggalPinjam() + " , "
                       +"Returning Date: " + p.getTanggalKembali());
        }
    }
}
