import java.sql.*;
import java.util.Scanner;

public class TugasBuGusti {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/db_toko";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    static void create() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Masukkan nama barang    : ");
            String nama_barang = input.next();
            System.out.println("Masukkan harga barang   : ");
            String harga_barang = input.next();

            String sql = "INSERT INTO toko (nama_barang, harga_barang) VALUE ('%s','%s')";
            sql = String.format(sql, nama_barang, harga_barang);

            stmt.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void read() {
        String sql = "SELECT * FROM toko";
        try {
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nama_barang = rs.getString("nama_barang");
                String harga_barang = rs.getString("harga_barang");

                System.out.println(
                        String.format("%d. Nama Barang : %s, Harga Barang : %s", id, nama_barang, harga_barang));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void update() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Masukkan ID Barang yang ingin di update : ");
            int id = input.nextInt();
            System.out.println("Masukan nama barang baru                : ");
            String nama_barang = input.next();
            System.out.println("Masukkan harga barang baru              : ");
            String harga_barang = input.next();

            String sql = "UPDATE toko SET nama_barang='%s', harga_barang='%s' WHERE id=%d";
            sql = String.format(sql, nama_barang, harga_barang, id);

            stmt.execute(sql);
            System.out.println("Data telah di update");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void delete() {
        try {
            Scanner input = new Scanner(System.in);

            System.out.println("Masukkan ID yang ingin dihapus           : ");
            int id = input.nextInt();

            String sql = String.format("DELETE FROM toko WHERE id = %d", id);
            stmt.execute(sql);

            System.out.println("Data telah dihapus");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            while (!conn.isClosed()) {
                System.out.println("1. Create" + "\n2. Read" + "\n3. Update" + "\n4. Delete" + "\n5. exit");
                System.out.println("masukan pilihan :");
                int pilih = input.nextInt();

                if (pilih == 1) {
                    create();
                } else if (pilih == 2) {
                    read();
                } else if (pilih == 3) {
                    update();
                } else if (pilih == 4) {
                    delete();
                } else {
                    System.exit(0);
                }
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}