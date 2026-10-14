package service;

import java.util.List;
import java.util.Scanner;

import exception.NotFoundProductException;
import model.DienThoai;
import model.DienThoaiChinhHang;
import model.DienThoaiXachTay;
import repository.PhoneRepository;

public class PhoneService {
    private static final Scanner scanner = new Scanner(System.in);

    // 1. Chức năng thêm mới
    public static void themMoi() {
        System.out.println("\n--- THEM MOI DIEN THOAI ---");
        System.out.println("1. Dien thoai chinh hang");
        System.out.println("2. Dien thoai xach tay");
        int choice = getChoice(1, 2);

        int id = PhoneRepository.getNextID();

        System.out.print("Nhap ten dien thoai: ");
        String ten = scanner.nextLine().trim();
        while (ten.isEmpty()) {
            System.out.print("Ten khong duoc de trong. Vui long nhap lai: ");
            ten = scanner.nextLine().trim();
        }

        double gia = getPositiveDouble("Nhap gia ban (>0): ");
        int soLuong = getPositiveInt("Nhap so luong (>0): ");

        System.out.print("Nhap nha san xuat: ");
        String nsx = scanner.nextLine().trim();
        while (nsx.isEmpty()) {
            System.out.print("Nha san xuat khong duoc de trong. Vui long nhap lai: ");
            nsx = scanner.nextLine().trim();
        }

        List<DienThoai> list = PhoneRepository.readFromFile();

        if (choice == 1) {
            int thoiGianBH = getValidWarrantyTime();
            String phamViBH = getValidWarrantyScope();
            list.add(new DienThoaiChinhHang(id, ten, gia, soLuong, nsx, thoiGianBH, phamViBH));
        } else {
            String quocGia = getValidCountry();
            String trangThai = getValidStatus();
            list.add(new DienThoaiXachTay(id, ten, gia, soLuong, nsx, quocGia, trangThai));
        }

        PhoneRepository.writeToFile(list);
        System.out.println("Them moi dien thoai thanh cong! ID tu dong: " + id);
    }

    // 2. Chức năng xóa
    public static void xoaDienThoai() {
        List<DienThoai> list = PhoneRepository.readFromFile();
        if (list.isEmpty()) {
            System.out.println("Danh sach dien thoai trong, khong the xoa!");
            return;
        }

        while (true) {
            System.out.print("\nNhap ID dien thoai can xoa (nhan Enter de quay lai menu chinh): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Quay lai menu chinh.");
                return;
            }

            try {
                int idXoa;
                try {
                    idXoa = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    throw new NotFoundProductException("ID dien thoai khong ton tai.");
                }

                DienThoai target = null;
                for (DienThoai dt : list) {
                    if (dt.getId() == idXoa) {
                        target = dt;
                        break;
                    }
                }

                if (target == null) {
                    throw new NotFoundProductException("ID dien thoai khong ton tai.");
                }

                System.out.print("Ban co chac chan muon xoa dien thoai ID " + idXoa + " khong? (Yes/No): ");
                String confirm = scanner.nextLine().trim();
                if (confirm.equalsIgnoreCase("Yes")) {
                    list.remove(target);
                    PhoneRepository.writeToFile(list);
                    System.out.println("Xoa dien thoai thanh cong!");
                    System.out.println("\n--- Danh sach dien thoai sau khi xoa ---");
                    hienThiDanhSach();
                } else {
                    System.out.println("Da huy thao tac xoa, quay ve menu chinh.");
                }
                return;

            } catch (NotFoundProductException e) {
                System.out.println("Loi: " + e.getMessage());
                System.out.println("Vui long nhap lai hoac nhan Enter de quay lai menu chinh.");
            }
        }
    }

    // 3. Chức năng xem danh sách
    public static void hienThiDanhSach() {
        List<DienThoai> list = PhoneRepository.readFromFile();
        if (list.isEmpty()) {
            System.out.println("Danh sach dien thoai trong!");
            return;
        }
        System.out.println("\n--- DANH SACH DIEN THOAI ---");
        for (DienThoai dt : list) {
            System.out.println(dt);
        }
    }

    // 4. Chức năng tìm kiếm
    public static void timKiem() {
        List<DienThoai> list = PhoneRepository.readFromFile();
        if (list.isEmpty()) {
            System.out.println("Danh sach dien thoai trong!");
            return;
        }

        System.out.print("\nNhap tu khoa can tim (ID hoac Ten dien thoai): ");
        String keyword = scanner.nextLine().trim();
        while (keyword.isEmpty()) {
            System.out.print("Tu khoa khong duoc de trong. Vui long nhap lai: ");
            keyword = scanner.nextLine().trim();
        }

        String lowerKeyword = keyword.toLowerCase();
        boolean found = false;
        System.out.println("\n--- KET QUA TIM KIEM ---");
        for (DienThoai dt : list) {
            if (String.valueOf(dt.getId()).contains(keyword)
                    || dt.getTenDienThoai().toLowerCase().contains(lowerKeyword)) {
                System.out.println(dt);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay dien thoai nao voi tu khoa: " + keyword);
        }
    }

    // Các hàm validate dữ liệu
    private static int getChoice(int min, int max) {
        while (true) {
            try {
                System.out.print("Chon chuc nang (" + min + " - " + max + "): ");
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= min && choice <= max) {
                    return choice;
                }
                System.out.println("Vui long chon trong khoang tu " + min + " den " + max);
            } catch (NumberFormatException e) {
                System.out.println("Dinh dang khong hop le. Vui long nhap so!");
            }
        }
    }

    private static double getPositiveDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                double val = Double.parseDouble(scanner.nextLine().trim());
                if (val > 0) {
                    return val;
                }
                System.out.println("Gia tri phai lon hon 0. Vui long nhap lai!");
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so hop le!");
            }
        }
    }

    private static int getPositiveInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                int val = Integer.parseInt(scanner.nextLine().trim());
                if (val > 0) {
                    return val;
                }
                System.out.println("Gia tri phai lon hon 0. Vui long nhap lai!");
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so nguyen hop le!");
            }
        }
    }

    private static int getValidWarrantyTime() {
        while (true) {
            int time = getPositiveInt("Nhap thoi gian bao hanh (so ngay, toi da 730 ngay): ");
            if (time <= 730) {
                return time;
            }
            System.out.println("Thoi gian bao hanh khong duoc qua 730 ngay (2 nam). Vui long nhap lai!");
        }
    }

    private static String getValidWarrantyScope() {
        while (true) {
            System.out.print("Nhap pham vi bao hanh (Toan Quoc / Quoc Te): ");
            String scope = scanner.nextLine().trim();
            if (scope.equalsIgnoreCase("Toan Quoc")) {
                return "Toan Quoc";
            }
            if (scope.equalsIgnoreCase("Quoc Te")) {
                return "Quoc Te";
            }
            System.out.println("Pham vi bao hanh chi nhan 2 gia tri: 'Toan Quoc' hoac 'Quoc Te'. Vui long nhap lai!");
        }
    }

    private static String getValidCountry() {
        while (true) {
            System.out.print("Nhap quoc gia xach tay (khong duoc la Viet Nam): ");
            String country = scanner.nextLine().trim();
            if (country.isEmpty()) {
                System.out.println("Quoc gia xach tay khong duoc de trong. Vui long nhap lai!");
            } else if (country.equalsIgnoreCase("Viet Nam") || country.equalsIgnoreCase("Vietnam")) {
                System.out.println("Quoc gia xach tay khong duoc la Viet Nam. Vui long nhap lai!");
            } else {
                return country;
            }
        }
    }

    private static String getValidStatus() {
        while (true) {
            System.out.print("Nhap trang thai (Da sua chua / Chua sua chua): ");
            String status = scanner.nextLine().trim();
            if (status.equalsIgnoreCase("Da sua chua")) {
                return "Da sua chua";
            }
            if (status.equalsIgnoreCase("Chua sua chua")) {
                return "Chua sua chua";
            }
            System.out.println("Trang thai chi nhan 2 gia tri: 'Da sua chua' hoac 'Chua sua chua'. Vui long nhap lai!");
        }
    }
}
