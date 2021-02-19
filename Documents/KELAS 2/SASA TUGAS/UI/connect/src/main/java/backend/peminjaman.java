
package backend;

import java.util.ArrayList;
import java.sql.*;
public class peminjaman {
    private int idPeminjaman;
    private books buku = new books();
    private member Member = new member();
    private String tanggalPinjam, tanggalKembali;
    
    public int getIdPeminjaman() {
        return idPeminjaman;
    }

    public void setIdPeminjaman(int idPeminjaman) {
        this.idPeminjaman = idPeminjaman;
    }

    public books getBuku() {
        return buku;
    }

    public void setBuku(books buku) {
        this.buku = buku;
    }

    public member getAnggota() {
        return Member;
    }

    public void setAnggota(member Member) {
        this.Member = Member;
    }

    public String getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(String tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public String getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(String tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }
    
    public peminjaman() {
    }

    public peminjaman(member Member, books buku, String tanggalPinjam, String tanggalKembali) {
        this.Member = Member;
        this.buku = buku;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
    }
    
    public peminjaman getById(int id) {
        peminjaman pmjn = new peminjaman();
        ResultSet rs = DBHelper.selectQuery("SELECT" 
                + " p.idpeminjaman AS idpeminjaman,"
                + " p.idanggota AS idanggota,"
                + " p.idbuku AS idbuku,"
                + " p.tanggalpinjam AS tanggalpinjam,"
                + " p.tanggalkembali AS tanggalkembali,"
                + " a.nama AS nama,"
                + " b.judul AS judul"
                + " FROM peminjaman p "
                + " LEFT JOIN anggota a "
                + " ON p.idanggota = a.idanggota"
                + " LEFT JOIN buku b "
                + " ON p.idbuku = b.idbuku"
                + " WHERE p.idpeminjaman = '" + id + "'");
        try {
            while (rs.next()) {
                pmjn = new peminjaman();
                pmjn.setIdPeminjaman(rs.getInt("idpeminjaman"));
                pmjn.getBuku().setIdbuku(rs.getInt("idbuku"));
                pmjn.getBuku().setJudul(rs.getString("judul"));
                pmjn.getAnggota().setIdanggota(rs.getInt("idanggota"));
                pmjn.getAnggota().setNama(rs.getString("nama"));
                pmjn.setTanggalPinjam(rs.getString("tanggalpinjam"));
                pmjn.setTanggalKembali(rs.getString("tanggalkembali"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pmjn;
    }
    
    public ArrayList<peminjaman> getAll() {
        ArrayList<peminjaman> ListPeminjaman = new ArrayList();
        ResultSet rs = DBHelper.selectQuery("SELECT" 
                + " p.idpeminjaman AS idpeminjaman,"
                + " p.idanggota AS idanggota,"
                + " p.idbuku AS idbuku,"
                + " b.judul AS judul,"
                + " a.nama AS nama,"
                + " p.tanggalpinjam AS tanggalpinjam,"
                + " p.tanggalkembali AS tanggalkembali"
                + " FROM peminjaman p "
                + " LEFT JOIN anggota a "
                + " ON p.idanggota = a.idanggota"
                + " LEFT JOIN buku b "
                + " ON p.idbuku = b.idbuku");
        try {
              while (rs.next()) {
                peminjaman pmjn = new peminjaman();
                pmjn.setIdPeminjaman(rs.getInt("idpeminjaman"));
                pmjn.getBuku().setIdbuku(rs.getInt("idbuku"));
                pmjn.getBuku().setJudul(rs.getString("judul"));
                pmjn.getAnggota().setIdanggota(rs.getInt("idanggota"));
                pmjn.getAnggota().setNama(rs.getString("nama"));
                pmjn.setTanggalPinjam(rs.getString("tanggalpinjam"));
                pmjn.setTanggalKembali(rs.getString("tanggalkembali"));
                ListPeminjaman.add(pmjn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListPeminjaman;
    }
    
    public ArrayList<peminjaman> search(String keyword) {
        ArrayList<peminjaman> ListPeminjaman = new ArrayList();
        String sql = "SELECT" 
                + " p.idpeminjaman AS idpeminjaman,"
                + " p.idanggota AS idanggota,"
                + " p.idbuku AS idbuku,"
                + " b.judul AS judul,"
                + " a.nama AS nama,"
                + " p.tanggalpinjam AS tanggalpinjam,"
                + " p.tanggalkembali AS tanggalkembali"
                + " FROM peminjaman p "
                + " LEFT JOIN anggota a "
                + " ON p.idanggota = a.idanggota"
                + " LEFT JOIN buku b "
                + " ON p.idbuku = b.idbuku"
                + " WHERE b.judul LIKE '%" + keyword + "%' "
                + " OR p.idpeminjaman LIKE '%" + keyword + "%' "
                + " OR p.idanggota LIKE '%" + keyword + "%' "
                + " OR a.nama LIKE '%" + keyword + "%' "
                + " OR p.tanggalpinjam LIKE '%" + keyword + "%' "
                + " OR p.tanggalkembali LIKE '%" + keyword + "%' "
                + " OR p.idbuku LIKE '%" + keyword + "%' ";
        ResultSet rs = DBHelper.selectQuery(sql);
        try {
            while (rs.next()) {
                peminjaman pmjn = new peminjaman();
                pmjn.setIdPeminjaman(rs.getInt("idpeminjaman"));
                pmjn.getBuku().setIdbuku(rs.getInt("idbuku"));
                pmjn.getBuku().setJudul(rs.getString("judul"));
                pmjn.getAnggota().setIdanggota(rs.getInt("idanggota"));
                pmjn.getAnggota().setNama(rs.getString("nama"));
                pmjn.setTanggalPinjam(rs.getString("tanggalpinjam"));
                pmjn.setTanggalKembali(rs.getString("tanggalkembali"));
                ListPeminjaman.add(pmjn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListPeminjaman;
    }
    
    public void save(){
        if (getById(idPeminjaman).getIdPeminjaman()==0) {
            String SQL = "INSERT INTO peminjaman (idanggota,idbuku,tanggalpinjam,tanggalkembali) VALUES("
                       + " '"+this.getAnggota().getIdanggota()+"', "
                       + " '"+this.getBuku().getIdbuku()+"', "
                       + " '"+this.tanggalPinjam+"', "
                       + " '"+this.tanggalKembali+"' )";
            this.idPeminjaman = DBHelper.insertQueryGetId(SQL);
        }else{
              String SQL = "UPDATE peminjaman SET "
                       + "idanggota = '"+this.getAnggota().getIdanggota()+"', "
                       + "idbuku = '"+this.getBuku().getIdbuku()+"', "
                       + "tanggalpinjam = '"+this.tanggalPinjam+"', "
                       + "tanggalkembali = '"+this.tanggalKembali+"' "
                       + "WHERE idpeminjaman = '"+this.idPeminjaman+"'";
            DBHelper.executeQuery(SQL);
        }
    }
    public void delete(){
        String SQL = "DELETE FROM peminjaman WHERE idpeminjaman = '"+this.idPeminjaman+"'";
        DBHelper.executeQuery(SQL);
    }
}
