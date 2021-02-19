
package frontend;

import backend.*;
public class TestBackend {
    public static void main(String[] args){
        kategori kat1 = new kategori("Novel", "Novel books collection");
        kategori kat2 = new kategori("Reference", "Scientific books");
        kategori kat3 = new kategori("Comic", "Child comics");
        // test insert
        kat1.save();
        kat2.save();
        kat3.save();
        // test update
        kat2.setKeterangan("Scientific books");
        kat2.save();
        // test delete
        kat3.delete();
        // test select all
    for(kategori k : new kategori().getAll()){
    System.out.println("Name: " + k.getNama() + ", Desc: " + k.getKeterangan());
    }
    // test search
    for(kategori k : new kategori().search("Scientific")){
    System.out.println("Name: " + k.getNama() + ", Desc: " + k.getKeterangan());
        }
      }
}
