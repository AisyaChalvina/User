
package backend;

import java.util.ArrayList;
import java.sql.*;
public class books {
    private int idbuku;
    private kategori kategori = new kategori();
    private String judul;
    private String penerbit;
    private String penulis;
    
    public void setIdbuku(int idbuku) {
        this.idbuku = idbuku;
    }

    public void setKategori(kategori Kategori) {
        this.kategori = Kategori;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public int getIdbuku() {
        return idbuku;
    }

    public kategori getKategori() {
        return kategori;
    }

    public String getJudul() {
        return judul;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public String getPenulis() {
        return penulis;
    }
    
    public books(){
        
    }
    
    public books(kategori Kategori, String judul, String penerbit, String penulis){
        this.kategori = Kategori;
        this.judul = judul;
        this.penerbit = penerbit;
        this.penulis = penulis;
    }
    
    public books getById(int id){
        books buku = new books();
        ResultSet rs = DBHelper.selectQuery("SELECT "
                                        + "     b.idbuku AS idbuku, "
                                        + "     b.judul AS judul, "
                                        + "     b.penerbit AS penerbit, "
                                        + "     b.penulis AS penulis, "
                                        + "     k.idkategori AS idkategori, "
                                        + "     k.nama AS nama, "
                                        + "     k.keterangan AS keterangan "
                                        + "     FROM buku b "
                                        + "     LEFT JOIN kategori k ON b.idkategori = k.idkategori "
                                        + "     WHERE b.idbuku = '" + id + "'");
        
        try{
            while(rs.next())
            {
                buku = new books();
                buku.setIdbuku(rs.getInt("idbuku"));
                buku.getKategori().setIdkategori(rs.getInt("idkategori"));
                buku.getKategori().setNama(rs.getString("nama"));
                buku.getKategori().setKeterangan(rs.getString("keterangan"));
                buku.setJudul(rs.getString("judul"));
                buku.setPenerbit(rs.getString("penerbit"));
                buku.setPenulis(rs.getString("penulis"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        return buku;
    }
    
    public ArrayList<books> getAll(){
        ArrayList<books> ListBuku = new ArrayList();
        
        ResultSet rs = DBHelper.selectQuery("SELECT "
                                        + "     b.idbuku AS idbuku, "
                                        + "     b.judul AS judul, "
                                        + "     b.penerbit AS penerbit, "
                                        + "     b.penulis AS penulis, "
                                        + "     k.idkategori AS idkategori, "
                                        + "     k.nama AS nama, "
                                        + "     k.keterangan AS keterangan "
                                        + "     FROM buku b "
                                        + "     LEFT JOIN kategori k ON b.idkategori = k.idkategori ");
        
        try{
            while(rs.next())
            {books buku = new books();
                buku.setIdbuku(rs.getInt("idbuku"));
                buku.getKategori().setIdkategori(rs.getInt("idkategori"));
                buku.getKategori().setNama(rs.getString("nama"));
                buku.getKategori().setKeterangan(rs.getString("keterangan"));
                buku.setJudul(rs.getString("judul"));
                buku.setPenerbit(rs.getString("penerbit"));
                buku.setPenulis(rs.getString("penulis"));
                
                ListBuku.add(buku);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        return ListBuku;
    }
    
    public ArrayList<books> search(String keyword){
        ArrayList<books> ListBuku = new ArrayList();
        
        ResultSet rs = DBHelper.selectQuery("SELECT "
                                        + "     b.idbuku AS idbuku, "
                                        + "     b.judul AS judul, "
                                        + "     b.penerbit AS penerbit, "
                                        + "     b.penulis AS penulis, "
                                        + "     k.idkategori AS idkategori, "
                                        + "     k.nama AS nama, "
                                        + "     k.keterangan AS keterangan "
                                        + "     FROM buku b "
                                        + "     LEFT JOIN kategori k ON b.idkategori = k.idkategori "
                                        + "     WHERE b.judul LIKE '%" + keyword + "%' "
                                        + "         OR b.penerbit LIKE '%" +keyword + "%' "
                                        + "         OR b.penulis LIKE '%" + keyword + "%' ");
        
        try{
            while(rs.next()){
                books buku = new books();
                buku.setIdbuku(rs.getInt("idbuku"));
                buku.getKategori().setIdkategori(rs.getInt("idkategori"));
                buku.getKategori().setNama(rs.getString("nama"));
                buku.getKategori().setKeterangan(rs.getString("keterangan"));
                buku.setJudul(rs.getString("judul"));
                buku.setPenerbit(rs.getString("penerbit"));
                buku.setPenulis(rs.getString("penulis"));
                
                ListBuku.add(buku);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        return ListBuku;
    }
    
    public void save(){
        if (getById(idbuku).getIdbuku() == 0)
        {
            String SQL = "INSERT INTO buku (judul, idkategori, penulis, penerbit) VALUES("
                    + "     '" + this.judul + "', "
                    + "     '" + this.getKategori().getIdkategori() + "', "
                    + "     '" + this.penulis + "', "
                    + "     '" + this.penerbit + "' "
                    + "     )";
            this.idbuku = DBHelper.insertQueryGetId(SQL);
        }
        else
        {String SQL = "UPDATE buku SET "
                    + "     judul = '" + this.judul + "', "
                    + "     idkategori = '" + this.getKategori().getIdkategori() + "', "
                    + "     penulis = '" + this.penerbit + "', "
                    + "     penerbit = '" + this.penulis + "' "
                    + "     WHERE idbuku = '" + this.idbuku + "'";
            DBHelper.executeQuery(SQL);
        }
    }
    
    public void delete(){
        String SQL = "DELETE FROM buku WHERE idbuku = '" + this.idbuku + "'";
        DBHelper.executeQuery(SQL);
    }
}
