package Models;

/**
 * Created by Selahattin on 4.10.2017.
 */

public class Haberler {
    private int KaynakID ;
    private int HaberID ;
    private int Okunma ;
    private String KullaniciGuid ;
    private String Ad ;
    private String Soyad ;
    private String Baslik ;
    private String Ozet;
    private String Resim ;
    private String URL ;
    private String EklenmeTarihi ;
    private int Yayinlandi ;
    private String Kaynak ;

    public int getKaynakID() {
        return KaynakID;
    }

    public void setKaynakID(int kaynakID) {
        KaynakID = kaynakID;
    }

    public int getHaberID() {
        return HaberID;
    }

    public void setHaberID(int haberID) {
        HaberID = haberID;
    }

    public int getOkunma() {
        return Okunma;
    }

    public void setOkunma(int okunma) {
        Okunma = okunma;
    }

    public String getKullaniciGuid() {
        return KullaniciGuid;
    }

    public void setKullaniciGuid(String kullaniciGuid) {
        KullaniciGuid = kullaniciGuid;
    }

    public String getAd() {
        return Ad;
    }

    public void setAd(String ad) {
        Ad = ad;
    }

    public String getSoyad() {
        return Soyad;
    }

    public void setSoyad(String soyad) {
        Soyad = soyad;
    }

    public String getBaslik() {
        return Baslik;
    }

    public void setBaslik(String baslik) {
        Baslik = baslik;
    }

    public String getOzet() {
        return Ozet;
    }

    public void setOzet(String ozet) {
        Ozet = ozet;
    }

    public String getResim() {
        return Resim;
    }

    public void setResim(String resim) {
        Resim = resim;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getEklenmeTarihi() {
        return EklenmeTarihi;
    }

    public void setEklenmeTarihi(String eklenmeTarihi) {
        EklenmeTarihi = eklenmeTarihi;
    }

    public int getYayinlandi() {
        return Yayinlandi;
    }

    public void setYayinlandi(int yayinlandi) {
        Yayinlandi = yayinlandi;
    }

    public String getKaynak() {
        return Kaynak;
    }

    public void setKaynak(String kaynak) {
        Kaynak = kaynak;
    }
}
