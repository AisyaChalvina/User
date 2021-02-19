
package frontend;

import backend.*;
public class TestBooks {
    public static void main(String[] args)
    {
        kategori novel = new kategori().getById(1);
        kategori reference = new kategori().getById(2);
        
        books bk1 = new books(novel, "The Great Gatsby", "F. Scott Fitzgerald", "Charles Scribner's Sons");
        books bk2 = new books(reference, "Introduction Algorithm", "Thomas H. Cormen", "Rivest");
        books bk3 = new books(novel, "Bumi Manusia", "Pramoedya Ananta Toer", "Hasta Mitra");
        
        bk1.save();
        bk2.save();
        
        bk2.setJudul("Introduction Algorithm");
        bk2.save();
        
        bk3.delete();
        
        for(books b : new books().getAll())
        {
        System.out.println("Categori: " + b.getKategori().getNama() + ", Title: " + b.getJudul());
        }
        
        for(books b : new books().search("The Great"))
        {
        System.out.println("Category: " + b.getKategori().getNama() + ", Title: " + b.getJudul());
        }
    }
}
