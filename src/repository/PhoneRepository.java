package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.DienThoai;
import model.DienThoaiChinhHang;
import model.DienThoaiXachTay;

public class PhoneRepository {

    private static final String FILE_PATH = "data/mobiles.csv";

    public static List<DienThoai> readFromFile() {
        List<DienThoai> list = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return list;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        String ten = parts[1].trim();
                        double gia = Double.parseDouble(parts[2].trim());
                        int soLuong = Integer.parseInt(parts[3].trim());
                        String nsx = parts[4].trim();

                        try {
                            int thoigianBH = Integer.parseInt(parts[5].trim());
                            String phamviBH = parts[6].trim();
                            list.add(new DienThoaiChinhHang(id, ten, gia, soLuong, nsx, thoigianBH, phamviBH));
                        } catch (NumberFormatException e) {
                            String quocGiaXT = parts[5].trim();
                            String trangThai = parts[6].trim();
                            list.add(new DienThoaiXachTay(id, ten, gia, soLuong, nsx, quocGiaXT, trangThai));
                        }
                    } catch (NumberFormatException e) {
                        // Bo qua dong neu tieu de hoac dinh dang sai
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
        return list;
    }

    public static void writeToFile(List<DienThoai> list) {
        File file = new File(FILE_PATH);
        File dir = file.getParentFile();
        if (dir != null && !dir.exists()) {
            dir.mkdirs();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (DienThoai dt : list) {
                bw.write(dt.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    public static int getNextID() {
        List<DienThoai> list = readFromFile();
        if (list.isEmpty()) {
            return 1;
        }
        return list.get(list.size() - 1).getId() + 1;
    }
}