
package frontend;

import backend.*;
public class testMembr {
    public static void main(String[] args){
    member mbr1 = new member("Lisa","Malang","08123456789");
    member mbr2 = new member("Jisoo","Bandung","08112324556");
    member mbr3 = new member("Jennie","Pasuruan","08912345768");
    
    mbr1.save();
    mbr2.save();
    mbr3.save();
    
    mbr2.setAlamat("Malang");
    mbr2.save();
    
    mbr3.delete();
    
    for(member m : new member().getAll())
    {
        System.out.println("Name: " + m.getNama() + ", Address: " + m.getAlamat() + ", Telephone: " + m.getTelepon() );
    }
    }
}
