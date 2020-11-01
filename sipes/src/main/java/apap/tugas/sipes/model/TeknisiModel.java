package apap.tugas.sipes.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "teknisi")
public class TeknisiModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Max(20)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "nomor_telepon", nullable = false)
    private String nomorTelepon;

    @OneToMany(mappedBy = "teknisi", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PesawatTeknisiModel> listPesawatTeknisi;

    @ManyToMany(mappedBy = "listTeknisi")
    private List<PesawatModel> listPesawat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public List<PesawatTeknisiModel> getListPesawatTeknisi() {
        return listPesawatTeknisi;
    }

    public void setListPesawatTeknisi(List<PesawatTeknisiModel> listPesawatTeknisi) {
        this.listPesawatTeknisi = listPesawatTeknisi;
    }

    public List<PesawatModel> getListPesawat() {
        return listPesawat;
    }

    public void setListPesawat(List<PesawatModel> listPesawat) {
        this.listPesawat = listPesawat;
    }
}